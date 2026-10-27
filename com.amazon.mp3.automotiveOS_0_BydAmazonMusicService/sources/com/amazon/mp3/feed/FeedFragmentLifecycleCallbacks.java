package com.amazon.mp3.feed;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.reactnative.AmazonReactFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedFragmentLifeCycleCallbacks.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/feed/FeedFragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "Landroidx/fragment/app/Fragment;", "fragment", "", "onFragmentResumed", "onFragmentDestroyed", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "getActionBarProvider", "()Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "<init>", "(Lcom/amazon/mp3/actionbar/ActionBarProvider;Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FeedFragmentLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks {
    private final ActionBarProvider actionBarProvider;
    private final FragmentActivity activity;

    public FeedFragmentLifecycleCallbacks(ActionBarProvider actionBarProvider, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(actionBarProvider, "actionBarProvider");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.actionBarProvider = actionBarProvider;
        this.activity = activity;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        super.onFragmentResumed(fragmentManager, fragment);
        if ((fragment instanceof AmazonReactFragment) && "Feed".equals(((AmazonReactFragment) fragment).getMainComponentName())) {
            FragmentActivity fragmentActivity = this.activity;
            this.actionBarProvider.setHeaderView(new ActionBarView(fragmentActivity, fragmentActivity.getResources().getString(R.string.activity_feed_title), null, false, true, null, 44, null));
            this.actionBarProvider.requestHomeButtonAsBack();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        super.onFragmentDestroyed(fragmentManager, fragment);
        if ((fragment instanceof AmazonReactFragment) && "Feed".equals(((AmazonReactFragment) fragment).getMainComponentName())) {
            this.actionBarProvider.removeHeaderView();
        }
    }
}
