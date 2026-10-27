package com.byd.card.adapter;

import android.app.UiStyleConfig;
import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.forbidden.ForbiddenManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MoreRecentAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MoreAppAdapter";
    private Context context;
    private OnRecentItemClickListener listener;
    private List<AppOrShortcutItem> mList;

    public interface OnRecentItemClickListener {
        void onItemClick(View view, AppOrShortcutItem popAddCardItem);
    }

    public MoreRecentAppAdapter(Context context, OnRecentItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void updateAdapter(List<AppOrShortcutItem> list) {
        this.mList = list;
        updateForbidden(ForbiddenManager.isPGear());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate;
        View viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.item_more_recent_appinfo, parent, false);
        int uiStyle = UiStyleConfig.getInstance(this.context).getUiStyle();
        if (uiStyle == 1) {
            viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.denza_item_more_recent_appinfo, parent, false);
        } else {
            if (uiStyle != 2) {
                viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_more_recent_appinfo, parent, false);
            }
            return new ViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final AppOrShortcutItem appOrShortcutItem = this.mList.get(position);
        String itemName = appOrShortcutItem.getItemName();
        if (itemName.contains("-")) {
            setAppName(viewHolder, itemName);
        } else {
            viewHolder.appName.setText(itemName);
        }
        viewHolder.appIcon.setBackground(appOrShortcutItem.getItemDrawable());
        viewHolder.mItem.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.MoreRecentAppAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MoreRecentAppAdapter.this.listener != null) {
                    MoreRecentAppAdapter.this.listener.onItemClick(view, appOrShortcutItem);
                }
            }
        });
        viewHolder.mItem.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.card.adapter.MoreRecentAppAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (appOrShortcutItem.isForbidden()) {
            viewHolder.appIcon.setAlpha(0.4f);
            viewHolder.appName.setAlpha(0.4f);
        } else {
            viewHolder.appIcon.setAlpha(1.0f);
            viewHolder.appName.setAlpha(1.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AppOrShortcutItem> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private void setAppName(ViewHolder viewHolder, String name) {
        TextPaint paint = viewHolder.appName.getPaint();
        paint.setTextSize(viewHolder.appName.getTextSize());
        if (((int) paint.measureText(name)) > 140) {
            int iLastIndexOf = name.lastIndexOf("-");
            viewHolder.appName.setText(String.format("%s\n%s", name.substring(0, iLastIndexOf) + "-", name.substring(iLastIndexOf + 1)));
            return;
        }
        viewHolder.appName.setText(name);
    }

    public void updateForbidden(boolean isPGear) {
        ForbiddenManager.updateForbidden(this.mList, isPGear);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView appIcon;
        private TextView appName;
        private RelativeLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItem = (RelativeLayout) itemView.findViewById(R.id.app_item);
            this.appIcon = (ImageView) itemView.findViewById(R.id.app_icon);
            this.appName = (TextView) itemView.findViewById(R.id.app_name);
        }
    }
}
