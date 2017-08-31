package kjy.com.mqtt.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kjy.com.mqtt.BR;
import kjy.com.mqtt.R;
import kjy.com.mqtt.adapter.ChatListAdapter;
import kjy.com.mqtt.base.BaseActivity;
import kjy.com.mqtt.bean.ChatList;


public class ActivityChatList extends BaseActivity {
    private RecyclerView lv_chart;
    private ChatListAdapter chatListAdapter;
    private List<ChatList> list = new ArrayList<>();
    private ChatList chatList;
    private ChatList.Builder builder = new ChatList.Builder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        lv_chart = (RecyclerView) findViewById(R.id.lv_chart);
        for (int i = 0; i < 10; i ++){
            if (i == 0) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Content("广东省根深蒂固十点半").build();
            } else if (i == 1){
                chatList = builder.Types("SendOut").MultipleOptions("Text").Content("时的gas的gas的高大上v阿萨德").build();
            } else if (i == 2){
                chatList = builder.Types("Receive").MultipleOptions("Image").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 3){
                chatList = builder.Types("SendOut").MultipleOptions("Image").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 4) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Content("HAHASTEASDG").build();
            } else if (i == 5){
                chatList = builder.Types("SendOut").MultipleOptions("Text").Content("你好").build();
            } else if (i == 6){
                chatList = builder.Types("SendOut").MultipleOptions("Image").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 7) {
                chatList = builder.Types("Receive").MultipleOptions("Text").Content("GGDGAEG").build();
            } else if (i == 8){
                chatList = builder.Types("SendOut").MultipleOptions("Image").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 9){
                chatList = builder.Types("Receive").MultipleOptions("Text").Content("FASDFASDFASDFSDA").build();
            } else {
                chatList = builder.Types("SendOut").MultipleOptions("Image").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            }
            list.add(chatList);
        }
        showRecycler();
    }

    public void showRecycler() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_chart.setLayoutManager(linearLayoutManager);
        chatListAdapter = new ChatListAdapter(this, list, BR.Chat);
        lv_chart.setAdapter(chatListAdapter);
        chatListAdapter.setItemClickListener(new ChatListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}
