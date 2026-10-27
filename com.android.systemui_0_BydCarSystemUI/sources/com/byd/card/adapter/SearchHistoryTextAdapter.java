package com.byd.card.adapter;

import android.app.UiStyleConfig;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.bean.SearchHistory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchHistoryTextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SearchHistoryTextAdapter";
    private Context context;
    private SparseArray<Boolean> deletes = new SparseArray<>();
    private OnHistoryTextDeleteListener historyTextDeleteListener;
    private List<SearchHistory> list;
    private OnHistoryTextClickListener listener;
    private OnHistoryTextLongClickListener longClickListener;

    public interface OnHistoryTextClickListener {
        void onHistoryClick(String text);
    }

    public interface OnHistoryTextDeleteListener {
        void onHistoryDeleteAll();

        void onHistoryDeleteSingle(SearchHistory searchHistory);
    }

    public interface OnHistoryTextLongClickListener {
        void onHistoryLongClick(SearchHistory history);
    }

    public SearchHistoryTextAdapter(Context context, OnHistoryTextClickListener listener, OnHistoryTextDeleteListener historyTextDeleteListener) {
        this.context = context;
        this.listener = listener;
        this.historyTextDeleteListener = historyTextDeleteListener;
    }

    public List<SearchHistory> getList() {
        return this.list;
    }

    public void updateAdapter(List<SearchHistory> list) {
        this.list = list;
        this.deletes.clear();
        for (int i = 0; i < list.size(); i++) {
            this.deletes.put(i, false);
        }
        notifyDataSetChanged();
    }

    public void clearDelete() {
        if (this.list == null) {
            return;
        }
        this.deletes.clear();
        for (int i = 0; i < this.list.size(); i++) {
            this.deletes.put(i, false);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate;
        int uiStyle = UiStyleConfig.getInstance(this.context).getUiStyle();
        View viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.item_history_text, parent, false);
        if (uiStyle == 1) {
            viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.denza_item_history_text, parent, false);
        } else {
            if (uiStyle != 2) {
                viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_history_text, parent, false);
            }
            return new ViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.historyTxt.setText(this.list.get(position).text);
        if (this.deletes.get(position).booleanValue()) {
            viewHolder.delete.setVisibility(0);
            viewHolder.delete.setBackground(this.context.getDrawable(R.mipmap.icon_light_public_redx));
        } else {
            viewHolder.delete.setVisibility(8);
        }
        viewHolder.mItem.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.SearchHistoryTextAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SearchHistoryTextAdapter.this.listener != null) {
                    SearchHistoryTextAdapter.this.listener.onHistoryClick(((SearchHistory) SearchHistoryTextAdapter.this.list.get(position)).text);
                }
            }
        });
        viewHolder.mItem.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.card.adapter.SearchHistoryTextAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (SearchHistoryTextAdapter.this.longClickListener != null) {
                    SearchHistoryTextAdapter.this.longClickListener.onHistoryLongClick((SearchHistory) SearchHistoryTextAdapter.this.list.get(position));
                }
                for (int i = 0; i < SearchHistoryTextAdapter.this.list.size(); i++) {
                    if (position == i) {
                        SearchHistoryTextAdapter.this.deletes.put(position, true);
                    } else {
                        SearchHistoryTextAdapter.this.deletes.put(i, false);
                    }
                }
                SearchHistoryTextAdapter.this.notifyDataSetChanged();
                return true;
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.SearchHistoryTextAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SearchHistoryTextAdapter.this.historyTextDeleteListener != null) {
                    SearchHistoryTextAdapter.this.historyTextDeleteListener.onHistoryDeleteSingle((SearchHistory) SearchHistoryTextAdapter.this.list.get(position));
                }
                SearchHistoryTextAdapter.this.list.remove(SearchHistoryTextAdapter.this.list.get(position));
                viewHolder.delete.setVisibility(8);
                SearchHistoryTextAdapter.this.notifyItemChanged(position, Integer.valueOf(r3.list.size() - 1));
            }
        });
    }

    public List<SearchHistory> getHistoryDate() {
        return this.list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SearchHistory> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView delete;
        private TextView historyTxt;
        private RelativeLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItem = (RelativeLayout) itemView.findViewById(R.id.app_item);
            this.historyTxt = (TextView) itemView.findViewById(R.id.history_txt);
            this.delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }

    public void setOnHistoryTextLongClickListener(OnHistoryTextLongClickListener listener) {
        this.longClickListener = listener;
    }
}
