package videoplayer.myapp.holder;

import android.view.View;
import android.widget.TextView;

import videoplayer.myapp.R;
import videoplayer.myapp.base.NetAudioBean;

/**
 * Created by 猫奴 on 2017/1/16.
 */

public class TextHolder extends BaseViewHolder {
    TextView tvContext;

    public TextHolder(View convertView) {
        super(convertView);
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);


    }

    public void setData(NetAudioBean.ListBean mediaItem) {
        super.setData(mediaItem);
        //设置文本-所有的都有
        tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
    }
}
