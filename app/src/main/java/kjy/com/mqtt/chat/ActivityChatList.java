package kjy.com.mqtt.chat;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import kjy.com.mqtt.BR;
import kjy.com.mqtt.R;
import kjy.com.mqtt.adapter.ChatListAdapter;
import kjy.com.mqtt.base.BaseActivity;
import kjy.com.mqtt.bean.ChatList;
import kjy.com.mqtt.util.MediaPlayerManager;
import kjy.com.mqtt.util.UIHelper;
import kjy.com.mqtt.widget.MyButton;


public class ActivityChatList extends BaseActivity implements MyButton.AudioFinishRecorderCallBack, TextWatcher,
        View.OnFocusChangeListener {
    private RecyclerView lv_chart;
    private ChatListAdapter chatListAdapter;
    private List<ChatList> list = new ArrayList<>();
    private ChatList chatList;
    private ChatList.Builder builder = new ChatList.Builder();
    private MyButton id_recorder_button;
    private View animView;
    //判断语音和文字
    private boolean myMethod = true;
    private EditText et_text;
    private ImageView iv_image3;
    private TextView tv_fa_song;
    private View v_line;
    private FrameLayout fl_train;
    private boolean myTrain = true;
    private boolean myTrain1 = true;
    private FrameLayout fl_train1;

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
        id_recorder_button.setFinishRecorderCallBack(this);
        findViewById(R.id.iv_image1).setOnClickListener(this);
        findViewById(R.id.iv_image2).setOnClickListener(this);
        iv_image3 = (ImageView) findViewById(R.id.iv_image3);
        iv_image3.setOnClickListener(this);
        et_text = (EditText) findViewById(R.id.et_text);
        tv_fa_song = (TextView) findViewById(R.id.tv_fa_song);
        tv_fa_song.setOnClickListener(this);
        et_text.addTextChangedListener(this);
        v_line = findViewById(R.id.v_line);
        fl_train = (FrameLayout) findViewById(R.id.fl_train);
        et_text.setOnFocusChangeListener(this);
        disableShowInput();
        fl_train1 = (FrameLayout) findViewById(R.id.fl_train1);
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
            } else if (i == 11) {
                chatList = builder.Types("SendOut").MultipleOptions("Player").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 12) {
                chatList = builder.Types("Receive").MultipleOptions("Player").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 13) {
                chatList = builder.Types("SendOut").MultipleOptions("Player").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else if (i == 16) {
                chatList = builder.Types("SendOut").MultipleOptions("Player").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            } else {
                chatList = builder.Types("Receive").MultipleOptions("Player").Time("19:55:01").Images("http://www.ace.eiboran.net/Uploads/course/591173a220f39.jpg").build();
            }
            list.add(chatList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //显示语音或文字输入框
            case R.id.iv_image1:
                if (myMethod){
                    id_recorder_button.setVisibility(View.INVISIBLE);
                    et_text.setVisibility(View.VISIBLE);
                    v_line.setVisibility(View.VISIBLE);
                    fl_train.setVisibility(View.GONE);
                    fl_train1.setVisibility(View.GONE);
                    show();
                    myTrain = true;
                    myTrain1 = true;
                    myMethod = false;
                } else {
                    show();
                    et_text.setVisibility(View.INVISIBLE);
                    v_line.setVisibility(View.INVISIBLE);
                    id_recorder_button.setVisibility(View.VISIBLE);
                    myMethod = true;
                }
                break;
            //显示表情输入框
            case R.id.iv_image2:
                if (myTrain){
                    hintKbTwo();
                    id_recorder_button.setVisibility(View.INVISIBLE);
                    et_text.setVisibility(View.VISIBLE);
                    v_line.setVisibility(View.VISIBLE);
                    fl_train1.setVisibility(View.GONE);
                    fl_train.setVisibility(View.VISIBLE);
                    myTrain1 = true;
                    myTrain = false;
                    myMethod = true;
                } else {
                    fl_train.setVisibility(View.GONE);
                    myTrain = true;
                }
                break;
            //显示录制视频和图片输入框
            case R.id.iv_image3:
                if (myTrain1){
                    hintKbTwo();
                    fl_train.setVisibility(View.GONE);
                    fl_train1.setVisibility(View.VISIBLE);
                    myTrain = true;
                    myMethod = true;
                    myTrain1 = false;
                } else {
                    fl_train1.setVisibility(View.GONE);
                    myTrain1 = true;
                }
                break;
            //发送消息按钮
            case R.id.tv_fa_song:
                if (et_text.getText().length() > 0) {
                    chatList = builder.Types("SendOut").MultipleOptions("Text").Time("09:55:01").Content(et_text.getText().toString()).build();
                    chatListAdapter.addData(chatList);
                    lv_chart.smoothScrollToPosition(list.size() - 1);
                    et_text.getText().clear();
                }
                break;
        }
    }

    /**
     * 隐藏显示键盘
     */
    private void show() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 点击事件隐藏键盘
     */
    public void disableShowInput() {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            et_text.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(et_text, false);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 隐藏键盘
     */
    private void hintKbTwo() {
        et_text.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void showRecycler() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true); //关键
        lv_chart.setLayoutManager(linearLayoutManager);
        chatListAdapter = new ChatListAdapter(this, list, BR.Chat);
        lv_chart.setAdapter(chatListAdapter);
        lv_chart.smoothScrollToPosition(list.size() - 1);
        chatListAdapter.setItemClickListener(new ChatListAdapter.MyItemClickListener() {

            @Override
            public void onItemChatImage(View view, int position) {
                UIHelper.showMySetUpFragment(ActivityChatList.this);
            }

            @Override
            public void onItemChatPlayer(View view, int position) {
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

    @Override
    public void onFinish(float seconds, String filePath) {
        chatList = builder.Types("SendOut").MultipleOptions("Voice").Time("19:55:01").Voice(seconds).FilePath(filePath).build();
        chatListAdapter.addData(chatList);
        lv_chart.smoothScrollToPosition(list.size() - 1);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * 输入文字后的状态
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        if (et_text.getText().length() > 0) {
            iv_image3.setVisibility(View.GONE);
            tv_fa_song.setVisibility(View.VISIBLE);
        } else {
            iv_image3.setVisibility(View.VISIBLE);
            tv_fa_song.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
