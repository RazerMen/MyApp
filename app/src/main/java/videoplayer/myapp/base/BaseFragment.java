package videoplayer.myapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public abstract class BaseFragment extends Fragment {
    //上下文
    public Context mContext;

    /**
     * 当系统创建当前的BaseFragment类的时候回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //抽象方法，让孩子狮子，强制子类实现
    public abstract View initView();

    /**
     * 当Activity创建成功的时候回调该方法
     * 初始化数据
     * 联网请求数据
     *  绑定数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要：
     * 1、联网请求网络的时候重写方法
     *2、绑定数据
     */
    public void initData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            onRefrshData();
        }
    }

    /**
     * 当子类要刷新数据的时候重写该方法
     */
    public void onRefrshData() {

    }
}
