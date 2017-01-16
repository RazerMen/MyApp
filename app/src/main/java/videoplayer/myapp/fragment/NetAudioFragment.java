package videoplayer.myapp.fragment;

import android.app.Notification;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import videoplayer.myapp.R;
import videoplayer.myapp.adapter.NetAudioFragmentAdapter;
import videoplayer.myapp.base.BaseFragment;
import videoplayer.myapp.base.NetAudioBean;
import videoplayer.myapp.utils.CacheUtils;
import videoplayer.myapp.utils.Constant;

import static videoplayer.myapp.R.id.listview;
import static videoplayer.myapp.R.id.progressbar;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {

    private static final String TAG = NetAudioFragment.class.getSimpleName();

    @Bind(listview)
    ListView listView;

    @Bind(progressbar)
    ProgressBar progressBar;

    @Bind(R.id.tv_nomedia)
    TextView tv_nomedia;
    private List<NetAudioBean.ListBean> datas;
    private NetAudioFragmentAdapter myAdapter;
    private Notification.Builder tvNomedia;

    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "网络音频数据初始化了");

        String saveJson = CacheUtils.getString(mContext, Constant.NET_AUDIO_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        getDataFromNet();
    }


    private void getDataFromNet() {
        RequestParams reques = new RequestParams(Constant.NET_AUDIO_URL);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CacheUtils.putString(mContext, Constant.NET_AUDIO_URL, result);
                LogUtil.e("onSuccess==" + result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }

    /**
     * 使用Gson解析json数据
     * @param json
     * @return
     */

    private List<NetAudioBean.ListBean> parsedJson(String json) {
        NetAudioBean netAudioBean = new Gson().fromJson(json,NetAudioBean.class);
        return netAudioBean.getList();
    }

    //适配器代码
    private void processData(String result) {
        //NetAudioBean netAudioBean = paraseJson(result);
//        LogUtil.e(netAudioBean.getList().get(0).getText()+"-----------");
//
//        datas = netAudioBean.getList();

        if(datas != null && datas.size() >0){
            //有视频
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tvNomedia.setVisibility(View.GONE);
            }
            //设置适配器
            myAdapter = new NetAudioFragmentAdapter(mContext,datas);
            listView.setAdapter(myAdapter);
        }else{
            //没有视频
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tvNomedia.setVisibility(View.VISIBLE);
            }
        }

        progressBar.setVisibility(View.GONE);
    }



//
//    @Override
//    public void onRefrshData() {
//        super.onRefrshData();
//        ButterKnife.unbind(this);
//    }
}
