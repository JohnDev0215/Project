package com.amazon.mp3.auto.signin;

import android.app.Activity;
import android.widget.ImageView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.LaunchingAnimationController;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutomotiveLaunchingAnimationController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveLaunchingAnimationController;", "Lcom/amazon/mp3/activity/LaunchingAnimationController;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "initLoadingScreen", "", "initLogoScreen", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveLaunchingAnimationController extends LaunchingAnimationController {
    private final Activity activity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutomotiveLaunchingAnimationController(Activity activity) {
        super(activity, false);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    @Override // com.amazon.mp3.activity.LaunchingAnimationController
    public void initLoadingScreen() {
        initLogoScreen();
    }

    private final void initLogoScreen() {
        ((ImageView) this.activity.findViewById(R.id.logo_animation_iv)).setImageResource(R.drawable.logo_amazonmusic_00000);
        this.activity.findViewById(R.id.subscription_badge).setVisibility(8);
    }
}
