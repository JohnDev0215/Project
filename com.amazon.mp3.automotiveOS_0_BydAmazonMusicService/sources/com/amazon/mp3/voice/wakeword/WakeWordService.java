package com.amazon.mp3.voice.wakeword;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.configuration.WakeWordModelProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenWakeLock;
import com.amazon.mp3.voice.AlexaIngressHelper;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.music.wakeword.WakeWordController;
import com.amazon.music.wakeword.config.WakeWordConfigurationProvider;
import com.amazon.pryon.android.asr.PryonLite;
import com.amazon.pryon.android.asr.PryonLiteCallbacks;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordService extends Service {
    private static final String TAG = WakeWordService.class.getSimpleName();
    private final BroadcastReceiver mClientMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.voice.wakeword.WakeWordService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.warning(WakeWordService.TAG, "The intent action is null. Doing nothing.");
                return;
            }
            switch (action) {
                case "com.amazon.mp3.wakewordcommand.app.in.background":
                    WakeWordService.this.mWakeWordController.notifyAppFocusChanged(true);
                    return;
                case "com.amazon.mp3.wakewordcommand.wakeword.user.disabled":
                    WakeWordService.this.mWakeWordController.setUserEnabled(false);
                    WakeWordService.this.mWakeWordController.stopWakeWordDetection();
                    return;
                case "com.amazon.mp3.wakewordcommand.wakeword.user.enabled":
                    WakeWordService.this.mWakeWordController.setUserEnabled(true);
                    WakeWordService.this.mWakeWordController.startWakeWordDetection();
                    return;
                case "wakeword.change.detection.threshold":
                    if (intent.getExtras() != null) {
                        WakeWordService.this.mWakeWordController.setDetectionThreshold(intent.getExtras().getInt("wakeword.detection.threshold", 1));
                        break;
                    } else {
                        return;
                    }
                    break;
                case "com.amazon.mp3.wakewordcommand.app.in.foreground":
                    if (!VoiceManagerSingleton.isInitialized() || VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
                        return;
                    }
                    WakeWordService.this.mWakeWordController.notifyAppFocusChanged(false);
                    return;
                case "com.amazon.mp3.wakewordcommand.alexa.ui.closed":
                    WakeWordService.this.mWakeWordController.alexaUiClosed();
                    return;
                case "com.amazon.mp3.wakewordcommand.alexa.ui.opened":
                    WakeWordService.this.mWakeWordController.alexaUiOpened();
                    return;
                case "com.amazon.mp3.wakewordcommand.microphone.permisson.revoked":
                    return;
            }
            Log.info(WakeWordService.TAG, "Default handling for ");
        }
    };
    private PryonLiteCallbacks mPryonLiteCallbacks = new PryonLiteCallbacks() { // from class: com.amazon.mp3.voice.wakeword.WakeWordService.2
        @Override // com.amazon.pryon.android.asr.PryonLiteCallbacks
        public void vadStateChanged(final int state) {
        }

        @Override // com.amazon.pryon.android.asr.PryonLiteCallbacks
        public void wakeWordDetected(final String wakeWord, final long beginSampleIndex, final long endSampleIndex) {
            WakeWordService.this.wakeWordDetected();
            ScreenWakeLock.onWakeWordDetected();
            Log.debug(WakeWordService.TAG, "onWakeWordDetected");
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder("wakeWordDetected").withInteractionType(InteractionType.VOICE).withPageType(WakeWordService.this.mWakeWordMetricsHelper.getPageType()).withExperienceMode(WakeWordService.this.mWakeWordMetricsHelper.getExperienceMode().getMetricsValue()).build());
        }
    };
    private WakeWordController mWakeWordController;
    private WakeWordMetricsHelper mWakeWordMetricsHelper;

    @Override // android.app.Service
    public IBinder onBind(final Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        registerReceivers();
        byte[] modelFromTerritory = WakeWordModelProvider.getModelFromTerritory(getApplicationContext(), AccountDetailUtil.getMusicTerritoryOfResidence());
        if (modelFromTerritory == null) {
            Log.error(TAG, "The PryonLite model is null. Stopping the WakeWord service.");
            stopSelf();
            return;
        }
        int detectionSensitivity = WakeWordConfigurationProvider.getConfiguration().getDetectionSensitivity();
        Log.info(TAG, "Initializing WakeWord with sensitivity: " + detectionSensitivity);
        PryonLite.Config config = new PryonLite.Config();
        config.useVad = false;
        config.detectThreshold = detectionSensitivity;
        config.model = modelFromTerritory;
        config.lowLatency = true;
        this.mWakeWordController = new WakeWordController(getApplicationContext(), config, this.mPryonLiteCallbacks, new WakeWordAppForegroundProvider());
        this.mWakeWordMetricsHelper = new WakeWordMetricsHelper();
    }

    @Override // android.app.Service
    public void onTaskRemoved(final Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (intent != null && intent.getBooleanExtra("start.wakeword.detection.with.service", true)) {
            WakeWordController wakeWordController = this.mWakeWordController;
            wakeWordController.setEnabled(intent.getBooleanExtra("set.wakeword.controller.is.enabled", wakeWordController.isEnabled()));
            this.mWakeWordController.startWakeWordDetection();
        }
        super.onStartCommand(intent, flags, startId);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceivers();
        this.mWakeWordController.exit();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean stopService(final Intent name) {
        return super.stopService(name);
    }

    void wakeWordDetected() {
        Context applicationContext = getApplicationContext();
        if (CastingUtil.isCastingToAlexa()) {
            BauhausToastUtils.showTextToast(applicationContext, getString(R.string.alexa_hands_free_disabled_when_casting_setting_text, CastingUtil.getCastingDeviceName(applicationContext)), 0);
            CastingUIMetricsLogger.recordWakeWordDisabledContentView();
        } else {
            if (shouldWakeWordBeInactive()) {
                return;
            }
            this.mWakeWordController.stopWakeWordDetection();
            getAlexaIngressHelper().handleAlexaIngress(AlexaEntryPoint.WAKE_WORD);
        }
    }

    @VisibleForTesting
    boolean shouldWakeWordBeInactive() {
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
        return playbackController.getCurrentMediaItem() != null && playbackController.getPlayStatus() != null && playbackController.getCurrentMediaItem().getType().equals(MediaItem.Type.AD_CONTENT) && playbackController.getPlayStatus().shouldBePlaying();
    }

    @VisibleForTesting
    AlexaIngressHelper getAlexaIngressHelper() {
        return new AlexaIngressHelper(getApplicationContext());
    }

    private void registerReceivers() {
        registerClientMessageReceiver();
    }

    private void registerClientMessageReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1000);
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.wakeword.user.disabled");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.wakeword.user.enabled");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.app.in.background");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.app.in.foreground");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.alexa.ui.opened");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.alexa.ui.closed");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.microphone.permisson.revoked");
        registerReceiver(this.mClientMessageReceiver, intentFilter);
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mClientMessageReceiver);
    }
}
