package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Trace;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.reactnative.GenericReactExperienceInitializer;
import com.amazon.music.destination.ReactNativeDestination;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativeDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0018\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u0002¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/navigation/ReactNativeDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/ReactNativeDestination;", "()V", "navigateTo", "", "context", "Landroid/content/Context;", "destination", "navigateToWithFragmentActivity", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReactNativeDestinationHandler implements DestinationHandler<ReactNativeDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, ReactNativeDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Trace.beginSection("AMA.ReactNativeDestinationHandler.navigateTo");
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Activity previousActivity = AndroidUtils.getPreviousActivity();
        FragmentActivity fragmentActivity = null;
        FragmentActivity fragmentActivity2 = currentActivity instanceof FragmentActivity ? (FragmentActivity) currentActivity : null;
        if (fragmentActivity2 != null) {
            fragmentActivity = fragmentActivity2;
        } else if (previousActivity instanceof FragmentActivity) {
            fragmentActivity = (FragmentActivity) previousActivity;
        }
        navigateToWithFragmentActivity(fragmentActivity, destination);
        Trace.endSection();
    }

    public final void navigateToWithFragmentActivity(FragmentActivity activity, ReactNativeDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (activity == null) {
            return;
        }
        new GenericReactExperienceInitializer().startExperience(destination, activity);
    }
}
