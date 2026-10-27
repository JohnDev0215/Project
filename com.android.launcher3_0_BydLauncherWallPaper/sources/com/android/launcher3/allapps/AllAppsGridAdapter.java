package com.android.launcher3.allapps;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.model.AppLaunchTracker;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.util.PackageManagerHelper;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final String TAG = "AppsGridAdapter";
    public static final int VIEW_TYPE_ALL_APPS_DIVIDER = 16;
    public static final int VIEW_TYPE_EMPTY_SEARCH = 4;
    public static final int VIEW_TYPE_ICON = 2;
    public static final int VIEW_TYPE_MASK_DIVIDER = 16;
    public static final int VIEW_TYPE_MASK_ICON = 2;
    public static final int VIEW_TYPE_SEARCH_MARKET = 8;
    public static final int VIEW_TYPE_WORK_TAB_FOOTER = 32;
    private final AlphabeticalAppsList mApps;
    private final int mAppsPerRow;
    private BindViewCallback mBindViewCallback;
    private String mEmptySearchMessage;
    private final GridLayoutManager mGridLayoutMgr;
    private final GridSpanSizer mGridSizer;
    private View.OnFocusChangeListener mIconFocusListener;
    private final Launcher mLauncher;
    private final LayoutInflater mLayoutInflater;
    private Intent mMarketSearchIntent;

    public interface BindViewCallback {
        void onBindView(ViewHolder holder);
    }

    public static boolean isViewType(int viewType, int viewTypeMask) {
        return (viewType & viewTypeMask) != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class AppsGridLayoutManager extends GridLayoutManager {
        public AppsGridLayoutManager(Context context) {
            super(context, 1, 1, false);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(event);
            AccessibilityRecordCompat accessibilityRecordCompatAsRecord = AccessibilityEventCompat.asRecord(event);
            accessibilityRecordCompatAsRecord.setItemCount(AllAppsGridAdapter.this.mApps.getNumFilteredApps());
            accessibilityRecordCompatAsRecord.setFromIndex(Math.max(0, accessibilityRecordCompatAsRecord.getFromIndex() - getRowsNotForAccessibility(accessibilityRecordCompatAsRecord.getFromIndex())));
            accessibilityRecordCompatAsRecord.setToIndex(Math.max(0, accessibilityRecordCompatAsRecord.getToIndex() - getRowsNotForAccessibility(accessibilityRecordCompatAsRecord.getToIndex())));
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
            return super.getRowCountForAccessibility(recycler, state) - getRowsNotForAccessibility(AllAppsGridAdapter.this.mApps.getAdapterItems().size() - 1);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfoForItem(recycler, state, host, info);
            ViewGroup.LayoutParams layoutParams = host.getLayoutParams();
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = info.getCollectionItemInfo();
            if (!(layoutParams instanceof GridLayoutManager.LayoutParams) || collectionItemInfo == null) {
                return;
            }
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(collectionItemInfo.getRowIndex() - getRowsNotForAccessibility(((GridLayoutManager.LayoutParams) layoutParams).getViewAdapterPosition()), collectionItemInfo.getRowSpan(), collectionItemInfo.getColumnIndex(), collectionItemInfo.getColumnSpan(), collectionItemInfo.isHeading(), collectionItemInfo.isSelected()));
        }

        private int getRowsNotForAccessibility(int adapterPosition) {
            List<AlphabeticalAppsList.AdapterItem> adapterItems = AllAppsGridAdapter.this.mApps.getAdapterItems();
            int iMax = Math.max(adapterPosition, AllAppsGridAdapter.this.mApps.getAdapterItems().size() - 1);
            int i = 0;
            for (int i2 = 0; i2 <= iMax; i2++) {
                if (!AllAppsGridAdapter.isViewType(adapterItems.get(i2).viewType, 2)) {
                    i++;
                }
            }
            return i;
        }
    }

    public class GridSpanSizer extends GridLayoutManager.SpanSizeLookup {
        public GridSpanSizer() {
            setSpanIndexCacheEnabled(true);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int position) {
            if (AllAppsGridAdapter.isIconViewType(AllAppsGridAdapter.this.mApps.getAdapterItems().get(position).viewType)) {
                return 1;
            }
            return AllAppsGridAdapter.this.mAppsPerRow;
        }
    }

    public AllAppsGridAdapter(Launcher launcher, AlphabeticalAppsList apps) {
        Resources resources = launcher.getResources();
        this.mLauncher = launcher;
        this.mApps = apps;
        this.mEmptySearchMessage = resources.getString(R.string.all_apps_loading_message);
        GridSpanSizer gridSpanSizer = new GridSpanSizer();
        this.mGridSizer = gridSpanSizer;
        AppsGridLayoutManager appsGridLayoutManager = new AppsGridLayoutManager(launcher);
        this.mGridLayoutMgr = appsGridLayoutManager;
        appsGridLayoutManager.setSpanSizeLookup(gridSpanSizer);
        this.mLayoutInflater = LayoutInflater.from(launcher);
        int i = launcher.getDeviceProfile().inv.numColumns;
        this.mAppsPerRow = i;
        appsGridLayoutManager.setSpanCount(i);
    }

    public static boolean isDividerViewType(int viewType) {
        return isViewType(viewType, 16);
    }

    public static boolean isIconViewType(int viewType) {
        return isViewType(viewType, 2);
    }

    public void setIconFocusListener(View.OnFocusChangeListener focusListener) {
        this.mIconFocusListener = focusListener;
    }

    public void setLastSearchQuery(String query) {
        this.mEmptySearchMessage = this.mLauncher.getResources().getString(R.string.all_apps_no_search_results, query);
        this.mMarketSearchIntent = PackageManagerHelper.getMarketSearchIntent(this.mLauncher, query);
    }

    public void setBindViewCallback(BindViewCallback cb) {
        this.mBindViewCallback = cb;
    }

    public GridLayoutManager getLayoutManager() {
        return this.mGridLayoutMgr;
    }

    public /* synthetic */ void lambda$onCreateViewHolder$0$AllAppsGridAdapter(View view) {
        this.mLauncher.lambda$startActivitySafely$0$Launcher(view, this.mMarketSearchIntent, null, AppLaunchTracker.CONTAINER_SEARCH);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            BubbleTextView bubbleTextView = (BubbleTextView) this.mLayoutInflater.inflate(R.layout.all_apps_icon, parent, false);
            bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
            bubbleTextView.setOnLongClickListener(ItemLongClickListener.INSTANCE_ALL_APPS);
            bubbleTextView.setLongPressTimeoutFactor(1.0f);
            bubbleTextView.setOnFocusChangeListener(this.mIconFocusListener);
            bubbleTextView.getLayoutParams().height = this.mLauncher.getDeviceProfile().allAppsCellHeightPx;
            return new ViewHolder(bubbleTextView);
        }
        if (viewType == 4) {
            return new ViewHolder(this.mLayoutInflater.inflate(R.layout.all_apps_empty_search, parent, false));
        }
        if (viewType == 8) {
            View viewInflate = this.mLayoutInflater.inflate(R.layout.all_apps_search_market, parent, false);
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsGridAdapter$PXaqiivuGYjPLMZ1wxu6g2uCpYY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreateViewHolder$0$AllAppsGridAdapter(view);
                }
            });
            return new ViewHolder(viewInflate);
        }
        if (viewType == 16) {
            return new ViewHolder(this.mLayoutInflater.inflate(R.layout.all_apps_divider, parent, false));
        }
        if (viewType == 32) {
            return new ViewHolder(this.mLayoutInflater.inflate(R.layout.work_tab_footer, parent, false));
        }
        throw new RuntimeException("Unexpected view type");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == 2) {
            AppInfo appInfo = this.mApps.getAdapterItems().get(position).appInfo;
            BubbleTextView bubbleTextView = (BubbleTextView) holder.itemView;
            bubbleTextView.reset();
            bubbleTextView.applyFromApplicationInfo(appInfo);
        } else if (itemViewType == 4) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(this.mEmptySearchMessage);
            textView.setGravity(this.mApps.hasNoFilteredResults() ? 17 : 8388627);
        } else if (itemViewType == 8) {
            TextView textView2 = (TextView) holder.itemView;
            if (this.mMarketSearchIntent != null) {
                textView2.setVisibility(0);
            } else {
                textView2.setVisibility(8);
            }
        } else if (itemViewType == 32) {
            ((WorkModeSwitch) holder.itemView.findViewById(R.id.work_mode_toggle)).refresh();
            TextView textView3 = (TextView) holder.itemView.findViewById(R.id.managed_by_label);
            textView3.setText(UserManagerCompat.getInstance(textView3.getContext()).isAnyProfileQuietModeEnabled() ? R.string.work_mode_off_label : R.string.work_mode_on_label);
        }
        BindViewCallback bindViewCallback = this.mBindViewCallback;
        if (bindViewCallback != null) {
            bindViewCallback.onBindView(holder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mApps.getAdapterItems().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.mApps.getAdapterItems().get(position).viewType;
    }
}
