package com.amazon.mp3.voice;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.casting.CastingAlertDialogActivity;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.SplashScreen;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingUtil;
import com.amazon.mp3.voice.onboarding.OnboardingWorkflowManagerFactory;
import com.amazon.mp3.voice.onboarding.view.AlexaOnboardingActivity;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.aom.onboarding.OnboardingWorkflowManager;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaIngressHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u000e\u001a\u00020\u0006H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/voice/AlexaIngressHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "closeOnboardingFlowIfRequired", "", "alexaEntryPoint", "Lcom/amazon/music/voice/AlexaEntryPoint;", "getOnboardingWorkflowManager", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowManager;", "handleAlexaIngress", "handleAlexaSplashIngress", "launchAlexaOnboardingFlow", "showCastingAlertDialogWhenAlexaInvoked", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class AlexaIngressHelper {
    private static final String TAG = AlexaIngressHelper.class.getSimpleName();
    private final Context context;

    public AlexaIngressHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public void handleAlexaIngress(AlexaEntryPoint alexaEntryPoint) {
        Intrinsics.checkNotNullParameter(alexaEntryPoint, "alexaEntryPoint");
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("handleAlexaIngress : alexaEntryPoint ", alexaEntryPoint));
        if (!CastingUtil.isAoMAllowed()) {
            showCastingAlertDialogWhenAlexaInvoked();
            return;
        }
        if (getOnboardingWorkflowManager().isOnboardingCompleted()) {
            if (VoiceManagerSingleton.isInitialized()) {
                closeOnboardingFlowIfRequired(alexaEntryPoint);
                VoiceManagerSingleton.getInstance().startRecording(alexaEntryPoint);
                return;
            }
            Log.error(str, "Alexa ingress from " + alexaEntryPoint + " failed. VoiceManager is not initialized");
            return;
        }
        launchAlexaOnboardingFlow(alexaEntryPoint);
    }

    public final void handleAlexaSplashIngress() {
        if (getOnboardingWorkflowManager().isOnboardingCompleted()) {
            Log.debug(TAG, "handleAlexaSplashIngress: Already completed onboarding flow");
            return;
        }
        SplashScreen splashScreen = SplashScreen.ALEXA_ONBOARDING;
        if (!splashScreen.canShow(this.context)) {
            Log.debug(TAG, "handleAlexaSplashIngress: Already shown splash screen");
            return;
        }
        splashScreen.setShown(this.context);
        Log.debug(TAG, "handleAlexaSplashIngress: launching Alexa onboarding splash screen");
        launchAlexaOnboardingFlow(AlexaEntryPoint.SPLASH_SCREEN);
    }

    @VisibleForTesting
    public final void launchAlexaOnboardingFlow(AlexaEntryPoint alexaEntryPoint) {
        Intrinsics.checkNotNullParameter(alexaEntryPoint, "alexaEntryPoint");
        if (alexaEntryPoint == AlexaEntryPoint.WIDGET) {
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            StyleSheetProvider.initializeStyleSheet(applicationContext);
        }
        if (VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().closeAlexaUi();
        }
        AlexaOnboardingUtil.INSTANCE.startOnboardingFlow(this.context);
    }

    private final void closeOnboardingFlowIfRequired(AlexaEntryPoint alexaEntryPoint) {
        if (alexaEntryPoint != AlexaEntryPoint.WAKE_WORD) {
            return;
        }
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Log.debug(TAG, Intrinsics.stringPlus("closeOnboardingFlowIfRequired : Current activity ", currentActivity));
        if (currentActivity instanceof AlexaOnboardingActivity) {
            currentActivity.finish();
            MetricsHolder.getManager().handleEvent(FlexEvent.builder("immediateAOMUsage").build());
        }
    }

    @VisibleForTesting
    public final OnboardingWorkflowManager getOnboardingWorkflowManager() {
        return new OnboardingWorkflowManagerFactory().createOnboardingWorkflowManager();
    }

    @VisibleForTesting
    public final void showCastingAlertDialogWhenAlexaInvoked() {
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Log.info(TAG, "Unable to use Alexa when Casting is active");
        currentActivity.startActivity(CastingAlertDialogActivity.getCastingAlertIntentForAoM(currentActivity));
    }
}
