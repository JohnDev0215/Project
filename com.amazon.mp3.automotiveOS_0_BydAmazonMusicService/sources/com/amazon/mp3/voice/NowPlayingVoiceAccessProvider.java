package com.amazon.mp3.voice;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.MarketAlexaUiListener;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NowPlayingVoiceAccessProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "mIsAlexaEnabled", "", "mMarketAlexaUiListener", "Lcom/amazon/mp3/MarketAlexaUiListener;", "initAlexa", "", "onAlexaClicked", "alexaEntryPoint", "Lcom/amazon/music/voice/AlexaEntryPoint;", "onDestroy", "sendAlexaClickMetric", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NowPlayingVoiceAccessProvider {
    private static final String TAG = NowPlayingVoiceAccessProvider.class.getSimpleName();
    private final FragmentActivity activity;
    private boolean mIsAlexaEnabled;
    private MarketAlexaUiListener mMarketAlexaUiListener;

    public NowPlayingVoiceAccessProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.mIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
    }

    public final void initAlexa() {
        boolean zIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
        this.mIsAlexaEnabled = zIsAlexaEnabled;
        if (zIsAlexaEnabled) {
            if (!VoiceManagerSingleton.isInitialized() || VoiceManagerFactory.shouldRefresh()) {
                VoiceManagerFactory.initializeVoiceManager(this.activity.getApplicationContext());
            }
            if (VoiceManagerSingleton.isInitialized()) {
                this.mMarketAlexaUiListener = new MarketAlexaUiListener();
                VoiceManagerSingleton.getInstance().addAlexaUiListener(this.mMarketAlexaUiListener);
            } else {
                Log.error(TAG, "The VoiceManger is not initialized.");
            }
        }
    }

    public final void onAlexaClicked(AlexaEntryPoint alexaEntryPoint) {
        Intrinsics.checkNotNullParameter(alexaEntryPoint, "alexaEntryPoint");
        sendAlexaClickMetric();
        new AlexaIngressHelper(this.activity).handleAlexaIngress(alexaEntryPoint);
    }

    public final void onDestroy() {
        if (this.mIsAlexaEnabled && this.mMarketAlexaUiListener != null && VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().removeAlexaUiListener(this.mMarketAlexaUiListener);
            MarketAlexaUiListener marketAlexaUiListener = this.mMarketAlexaUiListener;
            if (marketAlexaUiListener != null) {
                marketAlexaUiListener.destroy();
            }
            this.mMarketAlexaUiListener = null;
        }
    }

    private final void sendAlexaClickMetric() {
        PageType pageType;
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            pageType = PageType.NOW_PLAYING_STAGE;
        } else {
            pageType = PageType.NOW_PLAYING;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_ALEXA_NP).withInteractionType(InteractionType.TAP).withPageType(pageType).build());
    }
}
