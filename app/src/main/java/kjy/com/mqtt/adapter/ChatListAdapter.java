package kjy.com.mqtt.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import kjy.com.mqtt.R;
import kjy.com.mqtt.bean.ChatList;
import kjy.com.mqtt.bean.Constant;
import kjy.com.mqtt.databinding.ItemChatReceiveImageBinding;
import kjy.com.mqtt.databinding.ItemChatReceiveTextBinding;
import kjy.com.mqtt.databinding.ItemChatSendOutImageBinding;
import kjy.com.mqtt.databinding.ItemChatSendOutTextBinding;


public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MyItemClickListener mItemClickListener;
    private List<ChatList> list;
    private LayoutInflater inflater;
    private Context context;
    private int variableId;
    private ItemChatSendOutTextBinding itemChatSendOutTextBinding;
    private ItemChatSendOutImageBinding itemChatSendOutImageBinding;
    private ItemChatReceiveTextBinding itemChatReceiveTextBinding;
    private ItemChatReceiveImageBinding itemChatReceiveImageBinding;

    public ChatListAdapter(Context context, List<ChatList> list, int variableId) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.variableId = variableId;
    }

    /**
     * 添加一条数据并刷新界面
     *
     * @param chatList
     */
    public void addData(ChatList chatList) {
        list.add(list.size(), chatList);
        notifyItemInserted(list.size());
    }

    /**
     * 根据不同的条目返回不同布局类型的方法
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        ChatList chatList = list.get(position);
        if (chatList.getTypes() == ChatList.Type.SendOut) {
            if (chatList.getMultipleOptions() == ChatList.Type.Text) {
                return Constant.SendOutText;
            } else if (chatList.getMultipleOptions() == ChatList.Type.Image) {
                return Constant.SendOutImage;
            }
        } else if (chatList.getTypes() == ChatList.Type.Receive) {
            if (chatList.getMultipleOptions() == ChatList.Type.Text) {
                return Constant.ReceiveText;
            } else if (chatList.getMultipleOptions() == ChatList.Type.Image) {
                return Constant.ReceiveImage;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constant.SendOutText) {
            itemChatSendOutTextBinding = DataBindingUtil.inflate(inflater, R.layout.item_chat_send_out_text, parent, false);
            ChatListAdapter.ViewHolderSendOutText viewHolder = new ChatListAdapter.ViewHolderSendOutText(itemChatSendOutTextBinding.getRoot(), mItemClickListener);
            viewHolder.setBinding(itemChatSendOutTextBinding);
            return viewHolder;
        } else if (viewType == Constant.SendOutImage) {
            itemChatSendOutImageBinding = DataBindingUtil.inflate(inflater, R.layout.item_chat_send_out_image, parent, false);
            ChatListAdapter.ViewHolderSendOutImage viewHolder = new ChatListAdapter.ViewHolderSendOutImage(itemChatSendOutImageBinding.getRoot(), mItemClickListener);
            viewHolder.setBinding(itemChatSendOutImageBinding);
            return viewHolder;
        } else if (viewType == Constant.ReceiveText) {
            itemChatReceiveTextBinding = DataBindingUtil.inflate(inflater, R.layout.item_chat_receive_text, parent, false);
            ChatListAdapter.ViewHolderReceiveText viewHolder = new ChatListAdapter.ViewHolderReceiveText(itemChatReceiveTextBinding.getRoot(), mItemClickListener);
            viewHolder.setBinding(itemChatReceiveTextBinding);
            return viewHolder;
        } else if (viewType == Constant.ReceiveImage) {
            itemChatReceiveImageBinding = DataBindingUtil.inflate(inflater, R.layout.item_chat_receive_image, parent, false);
            ChatListAdapter.ViewHolderReceiveImage viewHolder = new ChatListAdapter.ViewHolderReceiveImage(itemChatReceiveImageBinding.getRoot(), mItemClickListener);
            viewHolder.setBinding(itemChatReceiveImageBinding);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderSendOutText) {
            ((ViewHolderSendOutText) holder).getBinding().setVariable(variableId, list.get(position));
            ((ViewHolderSendOutText) holder).getBinding().executePendingBindings();
        } else if (holder instanceof ViewHolderSendOutImage){
            Glide
                    .with(context)
                    .load(list.get(position).getImages())
                    //禁止缓存
                    .skipMemoryCache(true)
                    .error(R.mipmap.ic_launcher)
                    .into(((ViewHolderSendOutImage) holder).iv_send_image);
            ((ViewHolderSendOutImage) holder).getBinding().setVariable(variableId, list.get(position));
            ((ViewHolderSendOutImage) holder).getBinding().executePendingBindings();
        } else if (holder instanceof ViewHolderReceiveText) {
            ((ViewHolderReceiveText) holder).getBinding().setVariable(variableId, list.get(position));
            ((ViewHolderReceiveText) holder).getBinding().executePendingBindings();
        } else if (holder instanceof ViewHolderReceiveImage){
            Glide
                    .with(context)
                    .load(list.get(position).getImages())
                    //禁止缓存
                    .skipMemoryCache(true)
                    .error(R.mipmap.ic_launcher)
                    .into(((ViewHolderReceiveImage) holder).iv_from_image);
            ((ViewHolderReceiveImage) holder).getBinding().setVariable(variableId, list.get(position));
            ((ViewHolderReceiveImage) holder).getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolderSendOutText extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ChatListAdapter.MyItemClickListener myItemClickListener;
        private ViewDataBinding binding;
        public ViewHolderSendOutText(View itemView, ChatListAdapter.MyItemClickListener myItemClickListener) {
            super(itemView);
            this.myItemClickListener = myItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (myItemClickListener != null) {
                myItemClickListener.onItemClick(v, getPosition());
            }
        }
        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public static class ViewHolderSendOutImage extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ChatListAdapter.MyItemClickListener myItemClickListener;
        private ViewDataBinding binding;
        private ImageView iv_send_image;
        public ViewHolderSendOutImage(View itemView, ChatListAdapter.MyItemClickListener myItemClickListener) {
            super(itemView);
            this.myItemClickListener = myItemClickListener;
            iv_send_image = (ImageView) itemView.findViewById(R.id.iv_send_image);
        }

        @Override
        public void onClick(View v) {
            if (myItemClickListener != null) {
                myItemClickListener.onItemClick(v, getPosition());
            }
        }
        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public static class ViewHolderReceiveText extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ChatListAdapter.MyItemClickListener myItemClickListener;
        private ViewDataBinding binding;
        public ViewHolderReceiveText(View itemView, ChatListAdapter.MyItemClickListener myItemClickListener) {
            super(itemView);
            this.myItemClickListener = myItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (myItemClickListener != null) {
                myItemClickListener.onItemClick(v, getPosition());
            }
        }
        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public static class ViewHolderReceiveImage extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ChatListAdapter.MyItemClickListener myItemClickListener;
        private ViewDataBinding binding;
        private ImageView iv_from_image;
        public ViewHolderReceiveImage(View itemView, ChatListAdapter.MyItemClickListener myItemClickListener) {
            super(itemView);
            this.myItemClickListener = myItemClickListener;
            iv_from_image = (ImageView) itemView.findViewById(R.id.iv_from_image);
        }

        @Override
        public void onClick(View v) {
            if (myItemClickListener != null) {
                myItemClickListener.onItemClick(v, getPosition());
            }
        }
        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(ChatListAdapter.MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }
}
