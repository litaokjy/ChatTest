package kjy.com.mqtt.chat;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kjy.com.mqtt.BR;
import kjy.com.mqtt.R;
import kjy.com.mqtt.adapter.ChatListAdapter;
import kjy.com.mqtt.base.BaseActivity;
import kjy.com.mqtt.bean.ChatList;
import kjy.com.mqtt.util.MediaPlayerManager;
import kjy.com.mqtt.widget.MyButton;


public class ActivityChatList extends BaseActivity {
    private RecyclerView lv_chart;
    private ChatListAdapter chatListAdapter;
    private List<ChatList> list = new ArrayList<>();
    private ChatList chatList;
    private ChatList.Builder builder = new ChatList.Builder();
    private MyButton id_recorder_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        initView();
        initData();
        showRecycler();
    }

    @Override
    public void initView() {
        super.initView();
        lv_chart = (RecyclerView) findViewById(R.id.lv_chart);
        id_recorder_button = (MyButton) findViewById(R.id.id_recorder_button);
        id_recorder_button.setFinishRecorderCallBack(new MyButton.AudioFinishRecorderCallBack() {
            @Override
            public void onFinish(float seconds, String filePath) {
                chatList = builder.Types("SendOut").MultipleOptions("Voice").Time("19:55:01").Voice(seconds).FilePath(filePath).build();
                chatListAdapter.addData(chatList);
                lv_chart.smoothScrollToPosition(list.size() - 1);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 17; i++) {
            if (i == 0) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Time("09:55:01").Content("广东省根深蒂固十点半").build();
            } else if (i == 1) {
                chatList = builder.Types("SendOut").MultipleOptions("Text").Time("7月08日 09:55:01").Content("时的gas的gas的高大上v阿萨德").build();
            } else if (i == 2) {
                chatList = builder.Types("Receive").MultipleOptions("Image").Time("7月08日 09:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 3) {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Time("7月08日 09:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 4) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Time("7月08日 09:55:01").Content("HAHASTEASDG").build();
            } else if (i == 5) {
                chatList = builder.Types("SendOut").MultipleOptions("Text").Time("7月08日 09:55:01").Content("你好").build();
            } else if (i == 6) {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Time("09:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 7) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Time("09:55:01").Content("GGDGAEG").build();
            } else if (i == 8) {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Time("12:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 9) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Time("19:55:01").Content("FASDFASDFASDFSDA").build();
            } else if (i == 10) {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            }
            list.add(chatList);
        }
    }

    private View animView;

    public void showRecycler() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_chart.setLayoutManager(linearLayoutManager);
        chatListAdapter = new ChatListAdapter(this, list, BR.Chat);
        lv_chart.setAdapter(chatListAdapter);
        chatListAdapter.setItemClickListener(new ChatListAdapter.MyItemClickListener() {
            @Override
            public void onItemChatImage(View view, int position) {
                Log.i("image", "222222");

            }

            @Override
            public void onItemChatHead(View view, int position) {
                Log.i("image", "111111");
            }

            @Override
            public void onItemChatVoice(View view, int position) {
                //声音播放动画
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                animView = view.findViewById(R.id.tv_item_send_txt);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) animView.getBackground();
                animation.start();
                // 播放录音
                MediaPlayerManager.playSound(list.get(position).getFilePath(), new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        //播放完成后修改图片
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }
}
