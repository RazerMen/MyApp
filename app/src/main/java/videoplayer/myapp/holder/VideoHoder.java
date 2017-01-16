package videoplayer.myapp.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import videoplayer.myapp.R;
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

    public VideoHoder(View convertView) {
        super(convertView);
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        utils = new Utils();
        tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
        tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
        ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
        tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
        jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
    }
}
