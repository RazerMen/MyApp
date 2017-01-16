package videoplayer.myapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时两秒
                startMainActivity();
            }
        }, 2000);
    }

    private void startMainActivity() {
        //1、跳转到主页面
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //2、关闭当前页面
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //按下离开
        startMainActivity();
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();//移除所有消息
        handler.removeCallbacksAndMessages(null);

    }
}
