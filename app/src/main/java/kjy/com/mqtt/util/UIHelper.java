package kjy.com.mqtt.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kjy.com.mqtt.activity.JumpActivity;

/**
 * Created by eiboran-android001 on 2017/9/1.
 */

public class UIHelper {
    public static void showMySetUpFragment(Context context){
        Intent intent = new Intent(context, JumpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(JumpActivity.BUNDLE_KEY_DISPLAY_MY_SET_UP, JumpActivity.DISPLAY_IMAGE);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
