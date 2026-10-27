package com.amazon.music.voice;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.music.voice.GUIPlaybackPlugin;
import com.amazon.music.voice.ui.AlexaUiListener;
import com.visualon.OSMPUtils.voMimeTypes;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaEarconToneController {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaEarconToneController.class.getSimpleName());
    private final Context context;
    private EarconTone lastPlayedState;
    private MediaPlayer toneMediaPlayer;
    private final float TONE_VOLUME_MAX_PERCENT_THRESHOLD = 0.3f;
    private final float TONE_VOLUME_LEVEL_AT_MAX_DEVICE_VOLUME = 0.05f;
    private SpeechRecognizerPlugin.SimpleRecognizeSpeechListener simpleRecognizeSpeechListener = new SpeechRecognizerPlugin.SimpleRecognizeSpeechListener() { // from class: com.amazon.music.voice.AlexaEarconToneController.1
        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechRecordingCompleted() {
            AlexaEarconToneController.this.playFinishedListeningTone();
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechFailed(Throwable th) {
            AlexaEarconToneController.this.playCancelledTone();
        }
    };
    private GUIPlaybackPlugin.SimpleUnsupportedActionHandler simpleUnsupportedActionHandler = new GUIPlaybackPlugin.SimpleUnsupportedActionHandler() { // from class: com.amazon.music.voice.AlexaEarconToneController.2
        @Override // com.amazon.music.voice.GUIPlaybackPlugin.SimpleUnsupportedActionHandler, com.amazon.music.voice.UnsupportedActionHandler
        public void onUnsupportedActionReceived() {
            AlexaEarconToneController.this.playErrorTone();
        }
    };
    private EarconSpeechRecordingListener earconSpeechRecordingListener = new EarconSpeechRecordingListener() { // from class: com.amazon.music.voice.AlexaEarconToneController.3
        @Override // com.amazon.music.voice.EarconSpeechRecordingListener
        public void onSpeechRecordingTaskStarted(boolean z) {
            if (z) {
                AlexaEarconToneController.this.playListeningTone();
            }
        }

        @Override // com.amazon.music.voice.EarconSpeechRecordingListener
        public void onSpeechRecordingTaskCancelled() {
            AlexaEarconToneController.this.playCancelledTone();
        }
    };
    private AlexaUiListener.SimpleAlexaUiListener simpleAlexaUiListener = new AlexaUiListener.SimpleAlexaUiListener() { // from class: com.amazon.music.voice.AlexaEarconToneController.4
        @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiClicked() {
            AlexaEarconToneController.this.playCancelledTone();
        }
    };
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.amazon.music.voice.AlexaEarconToneController.5
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (AlexaEarconToneController.this.toneMediaPlayer == null) {
                return;
            }
            AlexaEarconToneController.this.resetToneMediaPlayer();
        }
    };

    public enum EarconTone {
        LISTENING,
        FINISHED_LISTENING,
        CANCELLED,
        ERROR
    }

    public AlexaEarconToneController(Context context) {
        Validate.notNull(context);
        this.context = context;
    }

    @VisibleForTesting
    void playListeningTone() {
        EarconTone earconTone = EarconTone.LISTENING;
        this.lastPlayedState = earconTone;
        createTonePlayerForEarcon(earconTone);
        startToneMediaPlayer();
    }

    @VisibleForTesting
    void playFinishedListeningTone() {
        EarconTone earconTone = EarconTone.FINISHED_LISTENING;
        this.lastPlayedState = earconTone;
        createTonePlayerForEarcon(earconTone);
        startToneMediaPlayer();
    }

    @VisibleForTesting
    void playCancelledTone() {
        EarconTone earconTone = this.lastPlayedState;
        EarconTone earconTone2 = EarconTone.CANCELLED;
        if (earconTone == earconTone2) {
            return;
        }
        this.lastPlayedState = earconTone2;
        createTonePlayerForEarcon(earconTone2);
        startToneMediaPlayer();
    }

    @VisibleForTesting
    void playErrorTone() {
        EarconTone earconTone = EarconTone.ERROR;
        this.lastPlayedState = earconTone;
        createTonePlayerForEarcon(earconTone);
        startToneMediaPlayer();
    }

    void startToneMediaPlayer() {
        MediaPlayer mediaPlayer = this.toneMediaPlayer;
        if (mediaPlayer == null) {
            LOG.error("toneMediaPlayer is null. Not playing Alexa tone for state :" + this.lastPlayedState);
            return;
        }
        mediaPlayer.start();
    }

    @VisibleForTesting
    void createTonePlayerForEarcon(EarconTone earconTone) {
        Validate.notNull(earconTone);
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone[earconTone.ordinal()];
        if (i == 1) {
            createTonePlayerWithResourceId(R$raw.earcon_listening);
            return;
        }
        if (i == 2) {
            createTonePlayerWithResourceId(R$raw.earcon_finished_listening);
            return;
        }
        if (i == 3 || i == 4) {
            createTonePlayerWithResourceId(R$raw.earcon_cancel);
            return;
        }
        LOG.debug("Trying to set dataSource for unhandled EarCon " + earconTone);
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.AlexaEarconToneController$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone;

        static {
            int[] iArr = new int[EarconTone.values().length];
            $SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone = iArr;
            try {
                iArr[EarconTone.LISTENING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone[EarconTone.FINISHED_LISTENING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone[EarconTone.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaEarconToneController$EarconTone[EarconTone.CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @VisibleForTesting
    void createTonePlayerWithResourceId(int i) {
        if (this.toneMediaPlayer != null) {
            resetToneMediaPlayer();
        }
        MediaPlayer mediaPlayerCreate = MediaPlayer.create(this.context, i);
        this.toneMediaPlayer = mediaPlayerCreate;
        if (mediaPlayerCreate != null) {
            mediaPlayerCreate.setOnCompletionListener(this.onCompletionListener);
            setTonePlayerVolume();
        }
    }

    private void setTonePlayerVolume() {
        try {
            if (((AudioManager) this.context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO)) == null) {
                LOG.warn("Audio Manager is null. Exiting.");
                return;
            }
            if (r0.getStreamVolume(3) / r0.getStreamMaxVolume(3) > 0.3f) {
                float fLog = (float) (1.0d / ((1.0d - Math.log(1.0f - r1)) + (Math.pow(r1 + 1.0f, 4.0d) / 4.0d)));
                if (fLog == 0.0f) {
                    fLog = 0.05f;
                }
                this.toneMediaPlayer.setVolume(fLog, fLog);
            }
        } catch (NullPointerException e) {
            LOG.error("Setting Tone Player volume caused a NPE. Ex: " + e);
        }
    }

    void resetToneMediaPlayer() {
        MediaPlayer mediaPlayer = this.toneMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        this.toneMediaPlayer.release();
        this.toneMediaPlayer = null;
    }

    public SpeechRecognizerPlugin.SimpleRecognizeSpeechListener getSimpleRecognizeSpeechListener() {
        return this.simpleRecognizeSpeechListener;
    }

    public GUIPlaybackPlugin.SimpleUnsupportedActionHandler getSimpleUnsupportedActionHandler() {
        return this.simpleUnsupportedActionHandler;
    }

    public EarconSpeechRecordingListener getEarconSpeechRecordingListener() {
        return this.earconSpeechRecordingListener;
    }

    public AlexaUiListener.SimpleAlexaUiListener getSimpleAlexaUiListener() {
        return this.simpleAlexaUiListener;
    }
}
