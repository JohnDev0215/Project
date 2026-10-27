package com.android.internal.app;

import android.content.Context;
import android.content.res.Resources;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.internal.R;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;

/* JADX INFO: loaded from: classes4.dex */
public class ResolverMultiProfilePagerAdapter extends AbstractMultiProfilePagerAdapter {
    private final ResolverProfileDescriptor[] mItems;
    private final boolean mShouldShowNoCrossProfileIntentsEmptyState;
    private boolean mUseLayoutWithDefault;

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    String getMetricsCategory() {
        return "intent_resolver";
    }

    ResolverMultiProfilePagerAdapter(Context context, ResolverListAdapter resolverListAdapter, UserHandle userHandle, UserHandle userHandle2) {
        super(context, 0, userHandle, userHandle2);
        this.mItems = new ResolverProfileDescriptor[]{createProfileDescriptor(resolverListAdapter)};
        this.mShouldShowNoCrossProfileIntentsEmptyState = true;
    }

    ResolverMultiProfilePagerAdapter(Context context, ResolverListAdapter resolverListAdapter, ResolverListAdapter resolverListAdapter2, int i, UserHandle userHandle, UserHandle userHandle2, boolean z) {
        super(context, i, userHandle, userHandle2);
        this.mItems = new ResolverProfileDescriptor[]{createProfileDescriptor(resolverListAdapter), createProfileDescriptor(resolverListAdapter2)};
        this.mShouldShowNoCrossProfileIntentsEmptyState = z;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    void updateAfterConfigChange() {
        super.updateAfterConfigChange();
        for (ResolverProfileDescriptor resolverProfileDescriptor : this.mItems) {
            View viewFindViewById = resolverProfileDescriptor.rootView.findViewById(R.id.resolver_empty_state_container);
            Resources resources = getContext().getResources();
            viewFindViewById.setPadding(viewFindViewById.getPaddingLeft(), resources.getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_top), viewFindViewById.getPaddingRight(), resources.getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_bottom));
        }
    }

    private ResolverProfileDescriptor createProfileDescriptor(ResolverListAdapter resolverListAdapter) {
        return new ResolverProfileDescriptor((ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.resolver_list_per_profile, (ViewGroup) null, false), resolverListAdapter);
    }

    ListView getListViewForIndex(int i) {
        return getItem(i).listView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverProfileDescriptor getItem(int i) {
        return this.mItems[i];
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    int getItemCount() {
        return this.mItems.length;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    void setupListAdapter(int i) {
        getItem(i).listView.setAdapter((ListAdapter) getItem(i).resolverListAdapter);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getAdapterForIndex(int i) {
        return this.mItems[i].resolverListAdapter;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter, com.android.internal.widget.PagerAdapter
    public ViewGroup instantiateItem(ViewGroup viewGroup, int i) {
        setupListAdapter(i);
        return super.instantiateItem(viewGroup, i);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    ResolverListAdapter getListAdapterForUserHandle(UserHandle userHandle) {
        if (getActiveListAdapter().getUserHandle().equals(userHandle)) {
            return getActiveListAdapter();
        }
        if (getInactiveListAdapter() == null || !getInactiveListAdapter().getUserHandle().equals(userHandle)) {
            return null;
        }
        return getInactiveListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getActiveListAdapter() {
        return getAdapterForIndex(getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getInactiveListAdapter() {
        if (getCount() == 1) {
            return null;
        }
        return getAdapterForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getPersonalListAdapter() {
        return getAdapterForIndex(0);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getWorkListAdapter() {
        return getAdapterForIndex(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getCurrentRootAdapter() {
        return getActiveListAdapter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ListView getActiveAdapterView() {
        return getListViewForIndex(getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    ViewGroup getInactiveAdapterView() {
        if (getCount() == 1) {
            return null;
        }
        return getListViewForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    boolean allowShowNoCrossProfileIntentsEmptyState() {
        return this.mShouldShowNoCrossProfileIntentsEmptyState;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showWorkProfileOffEmptyState(ResolverListAdapter resolverListAdapter, View.OnClickListener onClickListener) {
        showEmptyState(resolverListAdapter, R.drawable.ic_work_apps_off, R.string.resolver_turn_on_work_apps, 0, onClickListener);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoPersonalToWorkIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_access_work_apps, R.string.resolver_cant_access_work_apps_explanation);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoWorkToPersonalIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        showEmptyState(resolverListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cant_access_personal_apps, R.string.resolver_cant_access_personal_apps_explanation);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoPersonalAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_personal_apps_available_resolve, 0);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void showNoWorkAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        showEmptyState(resolverListAdapter, R.drawable.ic_no_apps, R.string.resolver_no_work_apps_available_resolve, 0);
    }

    void setUseLayoutWithDefault(boolean z) {
        this.mUseLayoutWithDefault = z;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void setupContainerPadding(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), this.mUseLayoutWithDefault ? view.getPaddingBottom() : 0);
    }

    class ResolverProfileDescriptor extends AbstractMultiProfilePagerAdapter.ProfileDescriptor {
        final ListView listView;
        private ResolverListAdapter resolverListAdapter;

        ResolverProfileDescriptor(ViewGroup viewGroup, ResolverListAdapter resolverListAdapter) {
            super(viewGroup);
            this.resolverListAdapter = resolverListAdapter;
            this.listView = (ListView) viewGroup.findViewById(R.id.resolver_list);
        }
    }
}
