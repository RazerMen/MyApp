package videoplayer.myapp.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import videoplayer.myapp.R;
import videoplayer.myapp.base.NetAudioBean;
import videoplayer.myapp.utils.Utils;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public class VideoHoder extends BaseViewHolder {

    Utils utils;
    TextView tvContext;
    JCVideoPlayerStandard jcvVideoplayer;
    TextView tvPlayNums;
    TextView tvVideoDuration;
    ImageView ivCommant;
    TextView tvCommantContext;
    private ImageView ivHeadpic;
    private TextView tvName;
    private TextView tvTimeRefresh;
    private TextView tvVideoKindText;
    private TextView tvShenheDingNumber;
    private TextView tvShenheCaiNumber;
    private TextView tvPostsNumber;
    private Context mContext;

    public VideoHoder(View convertView,Context mContext) {
        super(convertView);
        this.mContext = mContext;
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        utils = new Utils();
        tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
        tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
        ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
        tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
        jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
    }

    public VideoHoder(View convertView) {
        super(convertView);
    }

    public void setData(NetAudioBean.ListBean mediaItem) {
        super.setData(mediaItem);

        //设置文本-所有的都有,只有广告没有哦
        tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());

        //视频特有的------------------------
        //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
        boolean setUp = jcvVideoplayer.setUp(
                mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                "");
        //加载图片
        if (setUp) {
//                ImageLoader.getInstance().displayImage(mediaItem.getVideo().getThumbnail().get(0),
//                        jcvVideoplayer.thumbImageView);
            Glide.with(mContext).load(mediaItem.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
        }
        tvPlayNums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
        tvVideoDuration.setText(utils.stringForTime(mediaItem.getVideo().getDuration() * 1000) + "");

    }
}
