package kjy.com.mqtt.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseViewInterface {
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void initView(){

    }

    @Override
    public void initData() {

    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
