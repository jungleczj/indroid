package com.telecom.indroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.telecom.indroid.model.LoginResult;
import com.telecom.indroid.model.User;
import com.telecom.indroid.storage.AppInfo;
import com.telecom.indroid.storage.UserStorage;
import com.telecom.indroid.util.Encrypt;
import com.telecom.indroid.util.PhoneInfo;
import com.telecom.indroid.view.NumberProgressBar;
import com.telecom.indroid.view.OnProgressBarListener;
import com.telecom.indroid.webservice.WebService;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A login screen that offers login via user/password.
 */
public class LoginActivity extends Activity implements OnProgressBarListener{
    //自定义进度条
    private Timer timer;
    private NumberProgressBar bnp;

    private EditText edtUserName = null;
    private EditText edtPassWord = null;
    private Button btnLogin = null;

    // private TextView lbVersion = null;
    //private CustomProgressDialog progressDialog = null;
    private final int LOGIN_REQ = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        bnp.setVisibility(View.INVISIBLE);

        // 得到按钮控件
        edtUserName = (EditText) findViewById(R.id.username);
        edtPassWord = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.user_sign_in_button);

        //setTxtTitle("登录");

//        // 从SharedPreferences获取上次登录成功的用户名
        User user = UserStorage.getUser(this);
        // User user = getUser();
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        if (userName != "") {
            edtUserName.setText(userName);
            edtPassWord.requestFocus();
        }

        // 点击登录按钮
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.setClass(LoginActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
                // TODO Auto-generated method stub
//                String userName = edtUserName.getText().toString().trim();
//                if (userName.equals("")) {
//                    Toast.makeText(LoginActivity.this, "用户名不能为空",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String passWord = edtPassWord.getText().toString().trim();
//                if (passWord.equals("")) {
//                    Toast.makeText(LoginActivity.this, "密码不能为空",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                btnLogin.setClickable(false);
//                btnLogin.setBackgroundResource(R.color.colorPrimaryDark);
//                bnp.setVisibility(View.VISIBLE);
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                bnp.incrementProgressBy(2);
//                            }
//                        });
//                    }
//                }, 20, 80);
//                new Thread(loginRunable).start();
           }
       });
                // String version = getVersionName();
                // lbVersion.setText("当前版本:" + version);
    }

    /**
     * 处理从服务器返回的登录信息
     */
    Handler loginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 停止进度对话框
            //progressDialog.dismiss();

            // 取出登录结果和用户信息
            User user = (User) msg.obj;
            LoginResult loginResult = (LoginResult) msg.getData()
                    .getSerializable("loginResult");
            // 登录成功

            if (loginResult.isSuccess()) {
                while(bnp.getProgress()==100) {
                        // 用户名存到SharedPreferences
                        UserStorage.saveUser(LoginActivity.this, user);
                        // 号码、随机数和设备类型列表存到全局变量
                        AppInfo appInfo = (AppInfo) getApplicationContext();
                        appInfo.setTelno(user.getUserName());
    //               AppInfo appInfo = (AppInfo) getApplicationContext();
                        appInfo.resetAll();
                        appInfo.setTelno(user.getUserName());
                        appInfo.setRandom(loginResult.getRandom());
    //                appInfo.getEquipTypes().addAll(loginResult.getEquipTypes());
    //                appInfo.getQueryTypes().addAll(loginResult.getQueryTypes());
    //                appInfo.setConfigPara(loginResult.getConfigPara());
    //                appInfo.setQueryArea(loginResult.getQueryArea());

                        Intent intent = new Intent();
                        intent.putExtra("loginResult", loginResult);
                        setResult(RESULT_OK, intent);
                        intent.setClass(LoginActivity.this, SearchActivity.class);
                        startActivityForResult(intent, LOGIN_REQ);
                        finish();
                        break;
                }
            }
            // 登录失败，显示失败信息
            else {
                Toast.makeText(LoginActivity.this, loginResult.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
                btnLogin.setClickable(true);
                btnLogin.setBackgroundResource(R.color.colorPrimary);
                bnp.setProgress(0);
                bnp.setVisibility(View.INVISIBLE);
                timer.cancel();
        }
    };

    /**
     * 进程-登录服务器
     */
    Runnable loginRunable = new Runnable() {
        @Override
        public void run() {
            String userName = edtUserName.getText().toString().trim();
            String passWord = edtPassWord.getText().toString().trim();
            String version = PhoneInfo.getVersionName(LoginActivity.this); // getVersionName();
            String meid = PhoneInfo.getMeid(LoginActivity.this);// getMeid();
            String terType = android.os.Build.MODEL;
            String androidVersion = android.os.Build.VERSION.RELEASE;
            // 登录
            LoginResult loginResult = WebService.Login(userName,
                    Encrypt.getMD5(passWord), version, meid, terType,
                    androidVersion);
            // 发送到Handle处理
            Message msg = new Message();
            User user = new User();
            user.setUserName(userName);
            user.setPassWord("");
            msg.obj = user;
            Bundle loginBundle = new Bundle();
            loginBundle.putSerializable("loginResult", loginResult);
            msg.setData(loginBundle);
            loginHandler.sendMessage(msg);
        }
    };

    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            bnp.setProgress(0);
        }
    }
}
