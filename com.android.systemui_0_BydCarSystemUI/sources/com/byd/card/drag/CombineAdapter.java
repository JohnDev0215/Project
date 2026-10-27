package com.byd.card.drag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CombineAdapter extends RecyclerView.Adapter<ViewHolder> {
    private String TAG = "MyAdapter";
    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public CombineAdapter(List<Subject> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.mLiLayoutInflater.inflate(R.layout.item_linear, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(this.datas.get(position).getTitle());
        holder.img.setImageResource(this.datas.get(position).getImg());
    }

    public List<Subject> getDatas() {
        return this.datas;
    }

    public void update(List<Subject> list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Subject> list = this.datas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        LinearLayout ll_hidden;
        LinearLayout ll_item;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            this.ll_hidden = (LinearLayout) itemView.findViewById(R.id.ll_hidden);
        }
    }
}
