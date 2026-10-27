package com.amazon.mp3.featuregate;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.featuregate.rules.general.DebugRule;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PCAEvaluator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006H\u0007J\u0006\u0010\u0010\u001a\u00020\u0011R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/featuregate/PCAEvaluator;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "computedValue", "getComputedValue", "()Z", "setComputedValue", "(Z)V", "sharedPreferences", "Landroid/content/SharedPreferences;", Constants.ENABLE_DISABLE, "isDebug", "toggle", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PCAEvaluator {
    private static volatile PCAEvaluator INSTANCE;
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PCAEvaluator.class.getSimpleName();

    @JvmStatic
    public static final PCAEvaluator getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    @JvmOverloads
    public final boolean isEnabled() {
        return isEnabled$default(this, false, 1, null);
    }

    public PCAEvaluator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…AG, Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
    }

    public final boolean getComputedValue() {
        return this.sharedPreferences.getBoolean("com.amazon.music.sharedPreferences.computedPCAFeatureCheck", false);
    }

    public final void setComputedValue(boolean z) {
        this.sharedPreferences.edit().putBoolean("com.amazon.music.sharedPreferences.computedPCAFeatureCheck", z).apply();
    }

    public static /* synthetic */ boolean isEnabled$default(PCAEvaluator pCAEvaluator, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = new DebugRule().getValue();
        }
        return pCAEvaluator.isEnabled(z);
    }

    @JvmOverloads
    public final boolean isEnabled(boolean isDebug) {
        if (Intrinsics.areEqual("release", "releasePCA")) {
            return true;
        }
        if (isDebug) {
            return getComputedValue();
        }
        return false;
    }

    public final void toggle() {
        setComputedValue(!getComputedValue());
    }

    /* JADX INFO: compiled from: PCAEvaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/featuregate/PCAEvaluator$Companion;", "", "()V", "INSTANCE", "Lcom/amazon/mp3/featuregate/PCAEvaluator;", "TAG", "", "kotlin.jvm.PlatformType", "userProfileKey", "getInstance", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PCAEvaluator getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PCAEvaluator pCAEvaluator = PCAEvaluator.INSTANCE;
            if (pCAEvaluator == null) {
                synchronized (this) {
                    pCAEvaluator = PCAEvaluator.INSTANCE;
                    if (pCAEvaluator == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                        pCAEvaluator = new PCAEvaluator(applicationContext);
                        PCAEvaluator.INSTANCE = pCAEvaluator;
                    }
                }
            }
            return pCAEvaluator;
        }
    }
}
