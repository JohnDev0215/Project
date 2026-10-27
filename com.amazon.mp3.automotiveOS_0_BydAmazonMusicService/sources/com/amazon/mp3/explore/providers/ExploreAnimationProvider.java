package com.amazon.mp3.explore.providers;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.explore.providers.AnimationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreAnimationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreAnimationProvider;", "Lcom/amazon/music/explore/providers/AnimationProvider;", "()V", "slideOut", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreAnimationProvider implements AnimationProvider {
    @Override // com.amazon.music.explore.providers.AnimationProvider
    public void slideOut(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
    }
}
