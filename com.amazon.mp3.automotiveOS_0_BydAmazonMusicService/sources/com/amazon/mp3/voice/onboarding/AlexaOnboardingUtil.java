package com.amazon.mp3.voice.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.onboarding.provider.AlexaTOUSettingProvider;
import com.amazon.mp3.voice.onboarding.provider.AlexaWakeWordSettingProvider;
import com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AlexaOnboardingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaOnboardingUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(AlexaOnboardingUtil.class).getSimpleName();

    /* JADX INFO: compiled from: AlexaOnboardingUtil.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingUtil$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "clearOnboardingSettings", "", "context", "Landroid/content/Context;", "startOnboardingFlow", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AlexaOnboardingUtil.TAG;
        }

        public final void clearOnboardingSettings(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            new AlexaTOUSettingProvider(context).clearTOUSetting();
            new AlexaWakeWordSettingProvider(context).clearWakeWordSetting();
        }

        public final void startOnboardingFlow(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Log.debug(getTAG(), "displayOnboardingView");
            Intent intent = new Intent(context, (Class<?>) AlexaOnboardingActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
        }
    }
}
