package com.android.internal.app;

import android.content.Context;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
import com.android.internal.app.ChooserActivity;
import com.android.internal.widget.GridLayoutManager;
import com.android.internal.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserMultiProfilePagerAdapter extends AbstractMultiProfilePagerAdapter {
    private static final int SINGLE_CELL_SPAN_SIZE = 1;
    private int mBottomOffset;
    private final boolean mIsSendAction;
    private final ChooserProfileDescriptor[] mItems;
    private int mMaxTargetsPerRow;

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    String getMetricsCategory() {
        return "intent_chooser";
    }

    ChooserMultiProfilePagerAdapter(Context context, ChooserActivity.ChooserGridAdapter chooserGridAdapter, UserHandle userHandle, UserHandle userHandle2, boolean z, int i) {
        super(context, 0, userHandle, userHandle2);
        this.mItems = new ChooserProfileDescriptor[]{createProfileDescriptor(chooserGridAdapter)};
        this.mIsSendAction = z;
        this.mMaxTargetsPerRow = i;
    }

    ChooserMultiProfilePagerAdapter(Context context, ChooserActivity.ChooserGridAdapter chooserGridAdapter, ChooserActivity.ChooserGridAdapter chooserGridAdapter2, int i, UserHandle userHandle, UserHandle userHandle2, boolean z, int i2) {
        super(context, i, userHandle, userHandle2);
        this.mItems = new ChooserProfileDescriptor[]{createProfileDescriptor(chooserGridAdapter), createProfileDescriptor(chooserGridAdapter2)};
        this.mIsSendAction = z;
        this.mMaxTargetsPerRow = i2;
    }

    private ChooserProfileDescriptor createProfileDescriptor(ChooserActivity.ChooserGridAdapter chooserGridAdapter) {
        return new ChooserProfileDescriptor((ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.chooser_list_per_profile, (ViewGroup) null, false), chooserGridAdapter);
    }

    RecyclerView getListViewForIndex(int i) {
        return getItem(i).recyclerView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserProfileDescriptor getItem(int i) {
        return this.mItems[i];
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    int getItemCount() {
        return this.mItems.length;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserActivity.ChooserGridAdapter getAdapterForIndex(int i) {
        return this.mItems[i].chooserGridAdapter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getListAdapterForUserHandle(UserHandle userHandle) {
        if (getActiveListAdapter().getUserHandle().equals(userHandle)) {
            return getActiveListAdapter();
        }
        if (getInactiveListAdapter() == null || !getInactiveListAdapter().getUserHandle().equals(userHandle)) {
            return null;
        }
        return getInactiveListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    void setupListAdapter(int i) {
        RecyclerView recyclerView = getItem(i).recyclerView;
        final ChooserActivity.ChooserGridAdapter chooserGridAdapter = getItem(i).chooserGridAdapter;
        final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        gridLayoutManager.setSpanCount(this.mMaxTargetsPerRow);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.android.internal.app.ChooserMultiProfilePagerAdapter.1
            @Override // com.android.internal.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                if (chooserGridAdapter.shouldCellSpan(i2)) {
                    return 1;
                }
                return gridLayoutManager.getSpanCount();
            }
        });
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getActiveListAdapter() {
        return getAdapterForIndex(getCurrentPage()).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getInactiveListAdapter() {
        if (getCount() == 1) {
            return null;
        }
        return getAdapterForIndex(1 - getCurrentPage()).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getPersonalListAdapter() {
        return getAdapterForIndex(0).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getWorkListAdapter() {
        return getAdapterForIndex(1).getListAdapter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserActivity.ChooserGridAdapter getCurrentRootAdapter() {
        return getAdapterForIndex(getCurrentPage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public RecyclerView getActiveAdapterView() {
        return getListViewForIndex(getCurrentPage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public RecyclerView getInactiveAdapterView() {
        if (getCount() == 1) {
            return null;
        }
        return getListViewForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showWorkProfileOffEmptyState(ResolverListAdapter resolverListAdapter, View.OnClickListener onClickListener) {
        showEmptyState(resolverListAdapter, R.drawable.ic_work_apps_off, R.string.resolver_turn_on_work_apps, 0, onClickListener);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoPersonalToWorkIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_share_with_work_apps, R.string.resolver_cant_share_with_work_apps_explanation);
        } else {
            showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_access_work_apps, R.string.resolver_cant_access_work_apps_explanation);
        }
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoWorkToPersonalIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_share_with_personal_apps, R.string.resolver_cant_share_with_personal_apps_explanation);
        } else {
            showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_access_personal_apps, R.string.resolver_cant_access_personal_apps_explanation);
        }
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoPersonalAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_personal_apps_available_share, 0);
        } else {
            showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_personal_apps_available_resolve, 0);
        }
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoWorkAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_work_apps_available_share, 0);
        } else {
            showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_work_apps_available_resolve, 0);
        }
    }

    void setEmptyStateBottomOffset(int i) {
        this.mBottomOffset = i;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void setupContainerPadding(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), getContext().getResources().getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_bottom) + this.mBottomOffset);
    }

    class ChooserProfileDescriptor extends AbstractMultiProfilePagerAdapter.ProfileDescriptor {
        private ChooserActivity.ChooserGridAdapter chooserGridAdapter;
        private RecyclerView recyclerView;

        ChooserProfileDescriptor(ViewGroup viewGroup, ChooserActivity.ChooserGridAdapter chooserGridAdapter) {
            super(viewGroup);
            this.chooserGridAdapter = chooserGridAdapter;
            this.recyclerView = (RecyclerView) viewGroup.findViewById(R.id.resolver_list);
        }
    }
}
