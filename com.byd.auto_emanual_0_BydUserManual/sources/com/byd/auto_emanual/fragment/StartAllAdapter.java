package com.byd.auto_emanual.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StartAllAdapter extends RecyclerView.Adapter<StartAllHolder> {
    private static final String TAG = "StartAllAdapter";
    private List<ClassifyData> data;
    private LinearLayoutManager linearLayoutManager;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public StartAllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getContext()) { // from class: com.byd.auto_emanual.fragment.StartAllAdapter.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.linearLayoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(0);
        return new StartAllHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.start_all_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(StartAllHolder holder, int position) {
        holder.number.setText(String.valueOf(position + 1));
        holder.title.setText(this.data.get(position).getModelTypeName());
        StartSubAdapter startSubAdapter = new StartSubAdapter();
        if (holder.subRecyclerView.getLayoutManager() == null) {
            holder.subRecyclerView.setLayoutManager(this.linearLayoutManager);
        }
        holder.subRecyclerView.setAdapter(startSubAdapter);
        startSubAdapter.setData(this.data.get(position));
        Context context = holder.itemView.getContext();
        holder.number.setTextColor(ContextCompat.getColor(context, R.color.start_all_item_number_text_color));
        holder.number.setBackground(ContextCompat.getDrawable(context, R.drawable.icon_start_all_number_background));
        holder.title.setTextColor(ContextCompat.getColor(context, R.color.start_all_item_title_text_color));
        holder.line.setBackgroundColor(ContextCompat.getColor(context, R.color.start_line_color));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ClassifyData> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class StartAllHolder extends RecyclerView.ViewHolder {
        private View line;
        private TextView number;
        private RecyclerView subRecyclerView;
        private TextView title;

        public StartAllHolder(View itemView) {
            super(itemView);
            this.number = (TextView) itemView.findViewById(R.id.sub_number);
            this.title = (TextView) itemView.findViewById(R.id.start_all_title);
            this.line = itemView.findViewById(R.id.line);
            this.subRecyclerView = (RecyclerView) itemView.findViewById(R.id.start_sub);
        }
    }

    public void setData(List<ClassifyData> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
