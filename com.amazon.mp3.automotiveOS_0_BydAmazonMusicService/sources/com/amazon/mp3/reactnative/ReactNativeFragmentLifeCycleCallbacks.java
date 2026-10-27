package com.amazon.mp3.reactnative;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativeFragmentLifeCycleCallbacks.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/reactnative/ReactNativeFragmentLifeCycleCallbacks;", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "Landroidx/fragment/app/Fragment;", "fragment", "", "onFragmentResumed", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "getActionBarProvider", "()Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "<init>", "(Lcom/amazon/mp3/actionbar/ActionBarProvider;Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ReactNativeFragmentLifeCycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks {
    private final ActionBarProvider actionBarProvider;
    private final FragmentActivity activity;

    public ReactNativeFragmentLifeCycleCallbacks(ActionBarProvider actionBarProvider, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(actionBarProvider, "actionBarProvider");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.actionBarProvider = actionBarProvider;
        this.activity = activity;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
        ActionBar supportActionBar;
        MusicHomeActivity musicHomeActivity;
        ActionBar supportActionBar2;
        ActionBar supportActionBar3;
        ActionBar supportActionBar4;
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        super.onFragmentResumed(fragmentManager, fragment);
        if (this.activity.getSupportFragmentManager().findFragmentById(R.id.music_screen_container) instanceof GenericReactFragment) {
            FragmentActivity fragmentActivity = this.activity;
            MusicHomeActivity musicHomeActivity2 = fragmentActivity instanceof MusicHomeActivity ? (MusicHomeActivity) fragmentActivity : null;
            if ((musicHomeActivity2 == null || (supportActionBar3 = musicHomeActivity2.getSupportActionBar()) == null || !supportActionBar3.isShowing()) ? false : true) {
                FragmentActivity fragmentActivity2 = this.activity;
                musicHomeActivity = fragmentActivity2 instanceof MusicHomeActivity ? (MusicHomeActivity) fragmentActivity2 : null;
                if (musicHomeActivity == null || (supportActionBar4 = musicHomeActivity.getSupportActionBar()) == null) {
                    return;
                }
                supportActionBar4.hide();
                return;
            }
            return;
        }
        FragmentActivity fragmentActivity3 = this.activity;
        MusicHomeActivity musicHomeActivity3 = fragmentActivity3 instanceof MusicHomeActivity ? (MusicHomeActivity) fragmentActivity3 : null;
        if ((musicHomeActivity3 == null || (supportActionBar = musicHomeActivity3.getSupportActionBar()) == null || supportActionBar.isShowing()) ? false : true) {
            FragmentActivity fragmentActivity4 = this.activity;
            musicHomeActivity = fragmentActivity4 instanceof MusicHomeActivity ? (MusicHomeActivity) fragmentActivity4 : null;
            if (musicHomeActivity == null || (supportActionBar2 = musicHomeActivity.getSupportActionBar()) == null) {
                return;
            }
            supportActionBar2.show();
        }
    }
}
