package videoplayer.myapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import videoplayer.myapp.base.BaseFragment;
import videoplayer.myapp.fragment.LocalAudioFragment;
import videoplayer.myapp.fragment.LocalVideoFragment;
import videoplayer.myapp.fragment.NetAudioFragment;
import videoplayer.myapp.fragment.NetVideoFragment;
import videoplayer.myapp.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    //Fragment页面的下标位置
    private int position;
    //缓存的Feagment
    private Fragment tempFragment;
    private boolean isExit = false;

    SensorManager sensorManager;
    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        //Android6.0动态获取权限
        isGrantExternalRW(this);

        initFragment();
        //设置RadioGroup的监听
        initListener();

    }



    private void initListener() {
        //设置RadioGroup选中状态变化的监听
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_local_video:
                        position = 0;
                        break;
                    case R.id.rb_local_audio:
                        position = 1;
                        break;
                    case R.id.rb_net_audio:
                        position = 2;
                        break;
                    case R.id.rb_net_video:
                        position = 3;
                        break;
                    case R.id.rb_net_recy:
                        position = 4;
                        break;
                }
                //当前的Fragment
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        //默认为本地视频
        rg_main.check(R.id.rb_local_video);
    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            //开启事物
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //切换
            if (currentFragment != null) {
                //判断是否添加过
                if (!currentFragment.isAdded()) {
                    //把之前显示的隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果没有添加过就添加
                    ft.add(R.id.fl_mainc_content, currentFragment);
                } else {
                    //把之前的隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果添加了就直接显示
                    ft.show(currentFragment);
                }
                //提交事物
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoFragment());
        fragments.add(new LocalAudioFragment());
        fragments.add(new NetAudioFragment());
        fragments.add(new NetVideoFragment());
        fragments.add(new RecyclerViewFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
            return false;
        }
        return true;
    }

    //双击退出

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(position != 0) {
                //选中首页
                rg_main.check(R.id.rb_local_video);
                return true;
            }else if(!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                },2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
