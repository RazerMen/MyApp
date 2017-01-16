package videoplayer.myapp.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import videoplayer.myapp.base.BaseFragment;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public class LocalVideoFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;

    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("本地视频");
    }

    @Override
    public void onRefrshData() {
        super.onRefrshData();
        textView.setText("本地视频刷新");
    }
}
