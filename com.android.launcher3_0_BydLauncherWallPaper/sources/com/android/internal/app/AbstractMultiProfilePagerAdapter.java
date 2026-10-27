package com.android.internal.app;

import android.app.AppGlobals;
import android.app.admin.DevicePolicyEventLogger;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ResolverActivity;
import com.android.internal.widget.PagerAdapter;
import com.android.internal.widget.ViewPager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractMultiProfilePagerAdapter extends PagerAdapter {
    static final int PROFILE_PERSONAL = 0;
    static final int PROFILE_WORK = 1;
    private static final String TAG = "AbstractMultiProfilePagerAdapter";
    private final Context mContext;
    private int mCurrentPage;
    private Injector mInjector;
    private boolean mIsWaitingToEnableWorkProfile;
    private Set<Integer> mLoadedPages = new HashSet();
    private OnProfileSelectedListener mOnProfileSelectedListener;
    private OnSwitchOnWorkSelectedListener mOnSwitchOnWorkSelectedListener;
    private final UserHandle mPersonalProfileUserHandle;
    private final UserHandle mWorkProfileUserHandle;

    public interface Injector {
        boolean hasCrossProfileIntents(List<Intent> list, int i, int i2);

        boolean isQuietModeEnabled(UserHandle userHandle);

        void requestQuietModeEnabled(boolean z, UserHandle userHandle);
    }

    public interface OnProfileSelectedListener {
        void onProfilePageStateChanged(int i);

        void onProfileSelected(int i);
    }

    interface OnSwitchOnWorkSelectedListener {
        void onSwitchOnWorkSelected();
    }

    @interface Profile {
    }

    boolean allowShowNoCrossProfileIntentsEmptyState() {
        return true;
    }

    abstract ViewGroup getActiveAdapterView();

    public abstract ResolverListAdapter getActiveListAdapter();

    public abstract Object getAdapterForIndex(int i);

    abstract Object getCurrentRootAdapter();

    abstract ViewGroup getInactiveAdapterView();

    public abstract ResolverListAdapter getInactiveListAdapter();

    abstract ProfileDescriptor getItem(int i);

    abstract int getItemCount();

    abstract ResolverListAdapter getListAdapterForUserHandle(UserHandle userHandle);

    abstract String getMetricsCategory();

    @Override // com.android.internal.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return null;
    }

    public abstract ResolverListAdapter getPersonalListAdapter();

    public abstract ResolverListAdapter getWorkListAdapter();

    @Override // com.android.internal.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    protected void setupContainerPadding(View view) {
    }

    abstract void setupListAdapter(int i);

    protected abstract void showNoPersonalAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter);

    protected abstract void showNoPersonalToWorkIntentsEmptyState(ResolverListAdapter resolverListAdapter);

    protected abstract void showNoWorkAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter);

    protected abstract void showNoWorkToPersonalIntentsEmptyState(ResolverListAdapter resolverListAdapter);

    protected abstract void showWorkProfileOffEmptyState(ResolverListAdapter resolverListAdapter, View.OnClickListener onClickListener);

    AbstractMultiProfilePagerAdapter(Context context, int i, UserHandle userHandle, UserHandle userHandle2) {
        this.mContext = (Context) Objects.requireNonNull(context);
        this.mCurrentPage = i;
        this.mPersonalProfileUserHandle = userHandle;
        this.mWorkProfileUserHandle = userHandle2;
        this.mInjector = new AnonymousClass1((UserManager) context.getSystemService(UserManager.class));
    }

    /* JADX INFO: renamed from: com.android.internal.app.AbstractMultiProfilePagerAdapter$1, reason: invalid class name */
    class AnonymousClass1 implements Injector {
        final /* synthetic */ UserManager val$userManager;

        AnonymousClass1(UserManager userManager) {
            this.val$userManager = userManager;
        }

        @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.Injector
        public boolean hasCrossProfileIntents(List<Intent> list, int i, int i2) {
            return AbstractMultiProfilePagerAdapter.this.hasCrossProfileIntents(list, i, i2);
        }

        @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.Injector
        public boolean isQuietModeEnabled(UserHandle userHandle) {
            return this.val$userManager.isQuietModeEnabled(userHandle);
        }

        @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.Injector
        public void requestQuietModeEnabled(final boolean z, final UserHandle userHandle) {
            Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
            final UserManager userManager = this.val$userManager;
            executor.execute(new Runnable() { // from class: com.android.internal.app.-$$Lambda$AbstractMultiProfilePagerAdapter$1$SIKP7jNB4_XmkVh4dcvNRBJmT2Y
                @Override // java.lang.Runnable
                public final void run() {
                    userManager.requestQuietModeEnabled(z, userHandle);
                }
            });
            AbstractMultiProfilePagerAdapter.this.mIsWaitingToEnableWorkProfile = true;
        }
    }

    protected void markWorkProfileEnabledBroadcastReceived() {
        this.mIsWaitingToEnableWorkProfile = false;
    }

    protected boolean isWaitingToEnableWorkProfile() {
        return this.mIsWaitingToEnableWorkProfile;
    }

    public void setInjector(Injector injector) {
        this.mInjector = injector;
    }

    protected boolean isQuietModeEnabled(UserHandle userHandle) {
        return this.mInjector.isQuietModeEnabled(userHandle);
    }

    void setOnProfileSelectedListener(OnProfileSelectedListener onProfileSelectedListener) {
        this.mOnProfileSelectedListener = onProfileSelectedListener;
    }

    void setOnSwitchOnWorkSelectedListener(OnSwitchOnWorkSelectedListener onSwitchOnWorkSelectedListener) {
        this.mOnSwitchOnWorkSelectedListener = onSwitchOnWorkSelectedListener;
    }

    Context getContext() {
        return this.mContext;
    }

    void setupViewPager(ViewPager viewPager) {
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.android.internal.app.AbstractMultiProfilePagerAdapter.2
            @Override // com.android.internal.widget.ViewPager.SimpleOnPageChangeListener, com.android.internal.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                AbstractMultiProfilePagerAdapter.this.mCurrentPage = i;
                if (!AbstractMultiProfilePagerAdapter.this.mLoadedPages.contains(Integer.valueOf(i))) {
                    AbstractMultiProfilePagerAdapter.this.rebuildActiveTab(true);
                    AbstractMultiProfilePagerAdapter.this.mLoadedPages.add(Integer.valueOf(i));
                }
                if (AbstractMultiProfilePagerAdapter.this.mOnProfileSelectedListener != null) {
                    AbstractMultiProfilePagerAdapter.this.mOnProfileSelectedListener.onProfileSelected(i);
                }
            }

            @Override // com.android.internal.widget.ViewPager.SimpleOnPageChangeListener, com.android.internal.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (AbstractMultiProfilePagerAdapter.this.mOnProfileSelectedListener != null) {
                    AbstractMultiProfilePagerAdapter.this.mOnProfileSelectedListener.onProfilePageStateChanged(i);
                }
            }
        });
        viewPager.setAdapter(this);
        viewPager.setCurrentItem(this.mCurrentPage);
        this.mLoadedPages.add(Integer.valueOf(this.mCurrentPage));
    }

    void clearInactiveProfileCache() {
        if (this.mLoadedPages.size() == 1) {
            return;
        }
        this.mLoadedPages.remove(Integer.valueOf(1 - this.mCurrentPage));
    }

    @Override // com.android.internal.widget.PagerAdapter
    public ViewGroup instantiateItem(ViewGroup viewGroup, int i) {
        ProfileDescriptor item = getItem(i);
        viewGroup.addView(item.rootView);
        return item.rootView;
    }

    @Override // com.android.internal.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // com.android.internal.widget.PagerAdapter
    public int getCount() {
        return getItemCount();
    }

    protected int getCurrentPage() {
        return this.mCurrentPage;
    }

    public UserHandle getCurrentUserHandle() {
        return getActiveListAdapter().mResolverListController.getUserHandle();
    }

    boolean rebuildActiveTab(boolean z) {
        return rebuildTab(getActiveListAdapter(), z);
    }

    boolean rebuildInactiveTab(boolean z) {
        if (getItemCount() == 1) {
            return false;
        }
        return rebuildTab(getInactiveListAdapter(), z);
    }

    private int userHandleToPageIndex(UserHandle userHandle) {
        return userHandle.equals(getPersonalListAdapter().mResolverListController.getUserHandle()) ? 0 : 1;
    }

    private boolean rebuildTab(ResolverListAdapter resolverListAdapter, boolean z) {
        if (shouldShowNoCrossProfileIntentsEmptyState(resolverListAdapter)) {
            resolverListAdapter.postListReadyRunnable(z, true);
            return false;
        }
        return resolverListAdapter.rebuildList(z);
    }

    private boolean shouldShowNoCrossProfileIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        UserHandle userHandle = resolverListAdapter.getUserHandle();
        return (UserHandle.myUserId() == userHandle.getIdentifier() || !allowShowNoCrossProfileIntentsEmptyState() || this.mInjector.hasCrossProfileIntents(resolverListAdapter.getIntents(), UserHandle.myUserId(), userHandle.getIdentifier())) ? false : true;
    }

    void updateAfterConfigChange() {
        for (int i = 0; i < getItemCount(); i++) {
            ViewGroup emptyStateView = getItem(i).getEmptyStateView();
            updateIconVisibility((ImageView) emptyStateView.findViewById(R.id.resolver_empty_state_icon), emptyStateView);
        }
    }

    private void updateIconVisibility(ImageView imageView, ViewGroup viewGroup) {
        if (isSpinnerShowing(viewGroup)) {
            imageView.setVisibility(4);
        } else if (this.mWorkProfileUserHandle != null && !getContext().getResources().getBoolean(R.bool.resolver_landscape_phone)) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    void showEmptyResolverListEmptyState(ResolverListAdapter resolverListAdapter) {
        if (maybeShowNoCrossProfileIntentsEmptyState(resolverListAdapter) || maybeShowWorkProfileOffEmptyState(resolverListAdapter)) {
            return;
        }
        maybeShowNoAppsAvailableEmptyState(resolverListAdapter);
    }

    private boolean maybeShowNoCrossProfileIntentsEmptyState(ResolverListAdapter resolverListAdapter) {
        if (!shouldShowNoCrossProfileIntentsEmptyState(resolverListAdapter)) {
            return false;
        }
        if (resolverListAdapter.getUserHandle().equals(this.mPersonalProfileUserHandle)) {
            DevicePolicyEventLogger.createEvent(158).setStrings(getMetricsCategory()).write();
            showNoWorkToPersonalIntentsEmptyState(resolverListAdapter);
        } else {
            DevicePolicyEventLogger.createEvent(159).setStrings(getMetricsCategory()).write();
            showNoPersonalToWorkIntentsEmptyState(resolverListAdapter);
        }
        return true;
    }

    private boolean maybeShowWorkProfileOffEmptyState(final ResolverListAdapter resolverListAdapter) {
        if (!resolverListAdapter.getUserHandle().equals(this.mWorkProfileUserHandle) || !this.mInjector.isQuietModeEnabled(this.mWorkProfileUserHandle) || resolverListAdapter.getCount() == 0) {
            return false;
        }
        DevicePolicyEventLogger.createEvent(157).setStrings(getMetricsCategory()).write();
        showWorkProfileOffEmptyState(resolverListAdapter, new View.OnClickListener() { // from class: com.android.internal.app.-$$Lambda$AbstractMultiProfilePagerAdapter$VJfFiFX8KT6kjDnfQcCO2B2XS5w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$maybeShowWorkProfileOffEmptyState$0$AbstractMultiProfilePagerAdapter(resolverListAdapter, view);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$maybeShowWorkProfileOffEmptyState$0$AbstractMultiProfilePagerAdapter(ResolverListAdapter resolverListAdapter, View view) {
        showSpinner(getItem(userHandleToPageIndex(resolverListAdapter.getUserHandle())).getEmptyStateView());
        OnSwitchOnWorkSelectedListener onSwitchOnWorkSelectedListener = this.mOnSwitchOnWorkSelectedListener;
        if (onSwitchOnWorkSelectedListener != null) {
            onSwitchOnWorkSelectedListener.onSwitchOnWorkSelected();
        }
        this.mInjector.requestQuietModeEnabled(false, this.mWorkProfileUserHandle);
    }

    private void maybeShowNoAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        UserHandle userHandle = resolverListAdapter.getUserHandle();
        if (this.mWorkProfileUserHandle != null && (UserHandle.myUserId() == userHandle.getIdentifier() || !hasAppsInOtherProfile(resolverListAdapter))) {
            DevicePolicyEventLogger.createEvent(160).setStrings(getMetricsCategory()).setBoolean(userHandle == this.mPersonalProfileUserHandle).write();
            if (userHandle == this.mPersonalProfileUserHandle) {
                showNoPersonalAppsAvailableEmptyState(resolverListAdapter);
                return;
            } else {
                showNoWorkAppsAvailableEmptyState(resolverListAdapter);
                return;
            }
        }
        if (this.mWorkProfileUserHandle == null) {
            showConsumerUserNoAppsAvailableEmptyState(resolverListAdapter);
        }
    }

    protected void showEmptyState(ResolverListAdapter resolverListAdapter, int i, int i2, int i3) {
        showEmptyState(resolverListAdapter, i, i2, i3, null);
    }

    protected void showEmptyState(ResolverListAdapter resolverListAdapter, int i, int i2, int i3, View.OnClickListener onClickListener) {
        ProfileDescriptor item = getItem(userHandleToPageIndex(resolverListAdapter.getUserHandle()));
        item.rootView.findViewById(R.id.resolver_list).setVisibility(8);
        ViewGroup emptyStateView = item.getEmptyStateView();
        resetViewVisibilitiesForWorkProfileEmptyState(emptyStateView);
        emptyStateView.setVisibility(0);
        setupContainerPadding(emptyStateView.findViewById(R.id.resolver_empty_state_container));
        ((TextView) emptyStateView.findViewById(R.id.resolver_empty_state_title)).setText(i2);
        TextView textView = (TextView) emptyStateView.findViewById(R.id.resolver_empty_state_subtitle);
        if (i3 != 0) {
            textView.setVisibility(0);
            textView.setText(i3);
        } else {
            textView.setVisibility(8);
        }
        Button button = (Button) emptyStateView.findViewById(R.id.resolver_empty_state_button);
        button.setVisibility(onClickListener != null ? 0 : 8);
        button.setOnClickListener(onClickListener);
        ImageView imageView = (ImageView) emptyStateView.findViewById(R.id.resolver_empty_state_icon);
        imageView.setImageResource(i);
        updateIconVisibility(imageView, emptyStateView);
        resolverListAdapter.markTabLoaded();
    }

    private void showConsumerUserNoAppsAvailableEmptyState(ResolverListAdapter resolverListAdapter) {
        ProfileDescriptor item = getItem(userHandleToPageIndex(resolverListAdapter.getUserHandle()));
        item.rootView.findViewById(R.id.resolver_list).setVisibility(8);
        ViewGroup emptyStateView = item.getEmptyStateView();
        resetViewVisibilitiesForConsumerUserEmptyState(emptyStateView);
        emptyStateView.setVisibility(0);
        resolverListAdapter.markTabLoaded();
    }

    private boolean isSpinnerShowing(View view) {
        return view.findViewById(R.id.resolver_empty_state_progress).getVisibility() == 0;
    }

    private void showSpinner(View view) {
        view.findViewById(R.id.resolver_empty_state_icon).setVisibility(4);
        view.findViewById(R.id.resolver_empty_state_title).setVisibility(4);
        view.findViewById(R.id.resolver_empty_state_button).setVisibility(4);
        view.findViewById(R.id.resolver_empty_state_progress).setVisibility(0);
        view.findViewById(16908292).setVisibility(8);
    }

    private void resetViewVisibilitiesForWorkProfileEmptyState(View view) {
        view.findViewById(R.id.resolver_empty_state_icon).setVisibility(0);
        view.findViewById(R.id.resolver_empty_state_title).setVisibility(0);
        view.findViewById(R.id.resolver_empty_state_subtitle).setVisibility(0);
        view.findViewById(R.id.resolver_empty_state_button).setVisibility(4);
        view.findViewById(R.id.resolver_empty_state_progress).setVisibility(8);
        view.findViewById(16908292).setVisibility(8);
    }

    private void resetViewVisibilitiesForConsumerUserEmptyState(View view) {
        view.findViewById(R.id.resolver_empty_state_icon).setVisibility(8);
        view.findViewById(R.id.resolver_empty_state_title).setVisibility(8);
        view.findViewById(R.id.resolver_empty_state_subtitle).setVisibility(8);
        view.findViewById(R.id.resolver_empty_state_button).setVisibility(8);
        view.findViewById(R.id.resolver_empty_state_progress).setVisibility(8);
        view.findViewById(16908292).setVisibility(0);
    }

    protected void showListView(ResolverListAdapter resolverListAdapter) {
        ProfileDescriptor item = getItem(userHandleToPageIndex(resolverListAdapter.getUserHandle()));
        item.rootView.findViewById(R.id.resolver_list).setVisibility(0);
        item.rootView.findViewById(R.id.resolver_empty_state).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasCrossProfileIntents(List<Intent> list, int i, int i2) {
        IPackageManager packageManager = AppGlobals.getPackageManager();
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Iterator<Intent> it = list.iterator();
        while (it.hasNext()) {
            if (IntentForwarderActivity.canForward(it.next(), i, i2, packageManager, contentResolver) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAppsInOtherProfile(ResolverListAdapter resolverListAdapter) {
        if (this.mWorkProfileUserHandle == null) {
            return false;
        }
        Iterator<ResolverActivity.ResolvedComponentInfo> it = resolverListAdapter.getResolversForUser(UserHandle.of(UserHandle.myUserId())).iterator();
        while (it.hasNext()) {
            if (it.next().getResolveInfoAt(0).targetUserId != -2) {
                return true;
            }
        }
        return false;
    }

    boolean shouldShowEmptyStateScreen(ResolverListAdapter resolverListAdapter) {
        return (resolverListAdapter.getUnfilteredCount() == 0 && resolverListAdapter.getPlaceholderCount() == 0) || (resolverListAdapter.getUserHandle().equals(this.mWorkProfileUserHandle) && isQuietModeEnabled(this.mWorkProfileUserHandle));
    }

    protected class ProfileDescriptor {
        private final ViewGroup mEmptyStateView;
        final ViewGroup rootView;

        ProfileDescriptor(ViewGroup viewGroup) {
            this.rootView = viewGroup;
            this.mEmptyStateView = (ViewGroup) viewGroup.findViewById(R.id.resolver_empty_state);
        }

        protected ViewGroup getEmptyStateView() {
            return this.mEmptyStateView;
        }
    }
}
