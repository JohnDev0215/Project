package com.amazon.mp3.haptics;

import android.os.VibrationEffect;
import android.os.Vibrator;
import com.amazon.mp3.AmazonApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HapticsUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/haptics/HapticsUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HapticsUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = HapticsUtil.class.getSimpleName();

    /* JADX INFO: compiled from: HapticsUtil.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/haptics/HapticsUtil$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "hapticFeedbackOnPlayback", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void hapticFeedbackOnPlayback() {
            if (AmazonApplication.getCapabilities().isHapticsOnPlaybackExperimentEnabled()) {
                Object systemService = AmazonApplication.getApplication().getSystemService("vibrator");
                if (systemService == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.os.Vibrator");
                }
                Vibrator vibrator = (Vibrator) systemService;
                if (vibrator.hasVibrator()) {
                    VibrationEffect vibrationEffectCreatePredefined = VibrationEffect.createPredefined(0);
                    Intrinsics.checkNotNullExpressionValue(vibrationEffectCreatePredefined, "createPredefined(VibrationEffect.EFFECT_CLICK)");
                    vibrator.vibrate(vibrationEffectCreatePredefined);
                }
            }
        }
    }
}
