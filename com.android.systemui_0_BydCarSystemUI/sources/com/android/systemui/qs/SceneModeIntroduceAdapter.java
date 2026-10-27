package com.android.systemui.qs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SceneModeIntroduceAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<SceneModeIntroduceBean> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene_mode_introduce, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String name = this.mList.get(position).getName();
        if (QSIconConstants.icon.containsKey(name)) {
            holder.mImageView.setImageResource(QSIconConstants.icon.get(name).intValue());
        }
        holder.mTextView.setText(name);
        holder.mTextContent.setText(this.mList.get(position).getContent());
    }

    public void refreshList(List<SceneModeIntroduceBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SceneModeIntroduceBean> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextContent;
        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mImageView = (ImageView) itemView.findViewById(R.id.iv_item);
            this.mTextView = (TextView) itemView.findViewById(R.id.tv_item);
            this.mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
