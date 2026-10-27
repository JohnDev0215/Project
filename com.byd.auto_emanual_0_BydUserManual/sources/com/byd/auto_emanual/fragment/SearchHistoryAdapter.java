package com.byd.auto_emanual.fragment;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.ResourceUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Callback callback;
    private List<String> data;

    public interface Callback {
        void doHistoryItemClick(String history);

        void updateState(int historyItemCount);
    }

    public SearchHistoryAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_history_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(this.data.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SearchHistoryAdapter$_7whvB4G8SaxPc3ENZQxpoBbC8I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$SearchHistoryAdapter(holder, view);
            }
        });
        Context context = holder.itemView.getContext();
        ((GradientDrawable) holder.layout.getBackground()).setColor(ResourceUtil.getColor(context, R.attr.search_history_item_background));
        holder.textView.setTextColor(ResourceUtil.getColor(context, R.attr.search_history_item_text_color));
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SearchHistoryAdapter(ViewHolder viewHolder, View view) {
        Callback callback = this.callback;
        if (callback != null) {
            callback.doHistoryItemClick(viewHolder.textView.getText().toString());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (ListUtil.isNullOrEmpty(this.data)) {
            return 0;
        }
        return Math.min(this.data.size(), 10);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.layout = (RelativeLayout) itemView.findViewById(R.id.search_key_display_item);
            this.textView = (TextView) itemView.findViewById(R.id.search_history_item_text);
        }
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
        Callback callback = this.callback;
        if (callback != null) {
            callback.updateState(getItemCount());
        }
    }
}
