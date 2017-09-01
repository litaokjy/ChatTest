package kjy.com.mqtt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kjy.com.mqtt.R;
import kjy.com.mqtt.base.BaseFragment;

/**
 * 显示原图
 * Created by eiboran-android001 on 2017/9/1.
 */

public class ImageFragment extends BaseFragment {
    private View view = null;
    private ImageView iv_image;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.image_fragment, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
//        iv_image.setImageResource(R.drawable.b_j);
        Glide
                .with(getActivity())
                .load("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg")
                .skipMemoryCache(true)
                .error(R.mipmap.ic_launcher)
                .into(iv_image);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
