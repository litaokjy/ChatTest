package kjy.com.mqtt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import kjy.com.mqtt.R;
import kjy.com.mqtt.base.BaseActivity;
import kjy.com.mqtt.base.BaseFragment;
import kjy.com.mqtt.fragment.ImageFragment;

/**
 * Created by eiboran-android001 on 2017/9/1.
 */

public class JumpActivity extends BaseActivity {

    public static final String BUNDLE_KEY_DISPLAY_MY_SET_UP = "BUNDLE_KEY_DISPLAY_MY_SET_UP";
    public static final int DISPLAY_IMAGE = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        initFragment();
    }
    private void initFragment() {
        int displayType = getIntent().getIntExtra(BUNDLE_KEY_DISPLAY_MY_SET_UP, DISPLAY_IMAGE);
        BaseFragment fragment = null;
        switch (displayType) {
            case DISPLAY_IMAGE:
                fragment = new ImageFragment();
                break;
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fl_train, fragment);
        trans.commitAllowingStateLoss();
    }
}
