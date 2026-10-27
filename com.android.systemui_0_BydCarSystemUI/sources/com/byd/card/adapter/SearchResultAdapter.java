package com.byd.card.adapter;

import android.app.Activity;
import android.app.UiStyleConfig;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.Utilities;
import com.android.systemui.R;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.CarPlayAndAutoManager;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SearchResultAdapter";
    private Activity activity;
    private OnSearchResultItemClickListener listener;
    private List<AppOrShortcutItem> mList;
    private ViewGroup viewGroup;

    public interface OnSearchResultItemClickListener {
        void onSearchResultItemClick(AppOrShortcutItem item);
    }

    public SearchResultAdapter(Activity activity, OnSearchResultItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    public void updateAdapter(List<AppOrShortcutItem> list) {
        this.mList = list;
        updateForbidden(ForbiddenManager.isPGear());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate;
        View viewInflate2 = LayoutInflater.from(this.activity).inflate(R.layout.item_search_result, parent, false);
        this.viewGroup = parent;
        int uiStyle = UiStyleConfig.getInstance(this.activity).getUiStyle();
        if (uiStyle == 1) {
            viewInflate2 = LayoutInflater.from(this.activity).inflate(R.layout.denza_item_search_result, parent, false);
        } else {
            if (uiStyle != 2) {
                viewInflate = LayoutInflater.from(this.activity).inflate(R.layout.item_search_result, parent, false);
            }
            return new ViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final AppOrShortcutItem appOrShortcutItem = this.mList.get(position);
        viewHolder.appName.setText(appOrShortcutItem.getItemName());
        final Drawable itemDrawable = appOrShortcutItem.getItemDrawable();
        viewHolder.appIcon.setBackground(itemDrawable);
        viewHolder.mItem.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.SearchResultAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (appOrShortcutItem.isForbidden()) {
                    ToastUtils.show(view.getContext(), R.string.open_app_on_p_gear);
                    return;
                }
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                if (appOrShortcutItem instanceof AppInfo) {
                    CarPlayAndAutoManager.getInstance().jumpApplication(SearchResultAdapter.this.activity, (AppInfo) appOrShortcutItem, SearchResultAdapter.this.activity, SearchResultAdapter.this.viewGroup, view, ((BitmapDrawable) itemDrawable).getBitmap(), 51, 51, iArr[0] + ((int) Utilities.dp2Px(SearchResultAdapter.this.activity, 25.5f)), iArr[1], false);
                } else {
                    EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
                    AppUtils.openShortcut(SearchResultAdapter.this.activity, (ShortcutInfo) appOrShortcutItem, SearchResultAdapter.this.activity, SearchResultAdapter.this.viewGroup, view, ((BitmapDrawable) itemDrawable).getBitmap(), 51, 51, iArr[0] + ((int) Utilities.dp2Px(SearchResultAdapter.this.activity, 25.5f)), iArr[1], false);
                }
                if (SearchResultAdapter.this.listener != null) {
                    SearchResultAdapter.this.listener.onSearchResultItemClick((AppOrShortcutItem) SearchResultAdapter.this.mList.get(position));
                }
            }
        });
        if (appOrShortcutItem.isForbidden()) {
            viewHolder.mItem.setAlpha(0.4f);
        } else {
            viewHolder.mItem.setAlpha(1.0f);
        }
        if (position == this.mList.size() - 1) {
            viewHolder.divider.setVisibility(8);
        } else {
            viewHolder.divider.setVisibility(0);
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

    public void updateForbidden(boolean isPGear) {
        ForbiddenManager.updateForbidden(this.mList, isPGear);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView appIcon;
        private TextView appName;
        private View divider;
        private LinearLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItem = (LinearLayout) itemView.findViewById(R.id.result_app_item);
            this.appIcon = (ImageView) itemView.findViewById(R.id.result_app_icon);
            this.appName = (TextView) itemView.findViewById(R.id.result_app_name);
            this.divider = itemView.findViewById(R.id.item_search_result_divider);
        }
    }
}
