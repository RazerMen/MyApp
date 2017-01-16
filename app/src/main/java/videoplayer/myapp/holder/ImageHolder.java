package videoplayer.myapp.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import videoplayer.myapp.R;
import videoplayer.myapp.base.NetAudioBean;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public class ImageHolder extends BaseViewHolder {

    TextView tvContext;
    ImageView ivImageIcon;
    private Context mContext;


    public ImageHolder(View convertView, Context context) {
        super(convertView);
        this.mContext = context;
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);

    }

    public void setData(NetAudioBean.ListBean mediaItem) {

        super.setData(mediaItem);
        //设置文本-所有的都有
        tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
        //图片特有的

        ivImageIcon.setImageResource(R.drawable.bg_item);
        if (mediaItem.getImage() != null && mediaItem.getImage() != null && mediaItem.getImage().getSmall() != null)
            Glide.with(mContext).load(mediaItem.getImage().
                    getDownload_url().get(0)).
                    placeholder(R.drawable.bg_item).
                    error(R.drawable.bg_item).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(ivImageIcon);
    }
}

