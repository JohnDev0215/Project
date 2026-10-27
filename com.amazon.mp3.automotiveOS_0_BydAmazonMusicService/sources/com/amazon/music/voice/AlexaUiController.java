package com.amazon.music.voice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.alexa.voice.ExoAudioPlayer;
import com.amazon.alexa.voice.superbowl.Voice;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin;
import com.amazon.music.voice.text.TextPlugin;
import com.amazon.music.voice.ui.AlexaHintClickListener;
import com.amazon.music.voice.ui.AlexaHintsViewModelProvider;
import com.amazon.music.voice.ui.AlexaUIStyleProvider;
import com.amazon.music.voice.ui.AlexaUiListener;
import com.amazon.music.voice.ui.ControllerTransaction;
import com.amazon.music.voice.ui.Router;
import com.amazon.music.voice.ui.VoiceComponent;
import com.amazon.music.voice.ui.scrim.ScrimController;
import com.amazon.music.voice.ui.scrim.ScrimProvider;
import com.amazon.music.voice.ui.transitions.ScrimCloseTransition;
import com.amazon.music.voice.ui.transitions.ScrimOpenTransition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaUiController extends AlexaUiListener.SimpleAlexaUiListener implements AlexaHintClickListener {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaUiController.class.getSimpleName());
    private Activity activity;
    private ExoAudioPlayer alexaSpeechPlayer;
    private MusicAudioPlayerPlugin audioPlayerPlugin;
    private Context context;
    private EarconSpeechRecordingListener earconSpeechRecordingListener;
    private Handler handler;
    private RecognizeSpeechController recognizeSpeechController;
    private Bundle savedState;
    private ScrimController scrimController;
    private ScrimProvider scrimProvider;
    private Router scrimRouter;
    private SpeechRecognizerPlugin speechRecognizerPlugin;
    private SpeechSynthesizerPlugin speechSynthesizerPlugin;
    private TextPlugin textPlugin;
    private Voice voice;
    private VoiceComponent voiceComponent;
    private boolean keepUiVisible = false;
    private boolean isTextRequestInitiated = false;
    private Runnable softCloseAlexaUiRunnable = new Runnable() { // from class: com.amazon.music.voice.AlexaUiController.1
        @Override // java.lang.Runnable
        public void run() {
            if (AlexaUiController.this.isAlexaUiVisible() && AlexaUiController.this.voice.getNumberOfSequencedDirectives() <= 0) {
                AlexaUiController.this.softCloseAlexaUi();
            }
        }
    };

    public AlexaUiController(Context context, ScrimProvider scrimProvider, SpeechSynthesizerPlugin speechSynthesizerPlugin, SpeechRecognizerPlugin speechRecognizerPlugin, RecognizeSpeechController recognizeSpeechController, MusicAudioPlayerPlugin musicAudioPlayerPlugin, TextPlugin textPlugin, ExoAudioPlayer exoAudioPlayer, EarconSpeechRecordingListener earconSpeechRecordingListener) {
        this.context = context;
        this.scrimProvider = scrimProvider;
        this.speechSynthesizerPlugin = speechSynthesizerPlugin;
        this.speechRecognizerPlugin = speechRecognizerPlugin;
        this.recognizeSpeechController = recognizeSpeechController;
        this.audioPlayerPlugin = musicAudioPlayerPlugin;
        this.textPlugin = textPlugin;
        this.alexaSpeechPlayer = exoAudioPlayer;
        this.earconSpeechRecordingListener = earconSpeechRecordingListener;
        this.voiceComponent = new VoiceComponent(scrimProvider);
        this.scrimRouter = new Router(this.context, this.voiceComponent);
        this.scrimController = new ScrimController(context);
        addAlexaUiListener(this);
        this.scrimController.addAlexaHintClickListener(this);
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void attach(Activity activity, ViewGroup viewGroup, Voice voice) {
        if (this.activity != activity) {
            this.scrimRouter.detach();
            this.scrimRouter.attach(viewGroup);
            this.activity = activity;
            this.scrimController.setActivity(activity);
        }
        this.voice = voice;
        Bundle bundle = this.savedState;
        if (bundle != null) {
            this.scrimController.restoreState(bundle);
        }
    }

    public Activity getActivity() {
        return this.activity;
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.AlexaUiController$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$PositiveButtonDestination;

        static {
            int[] iArr = new int[ScrimController.PositiveButtonDestination.values().length];
            $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$PositiveButtonDestination = iArr;
            try {
                iArr[ScrimController.PositiveButtonDestination.NETWORK_SETTINGS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onPositiveButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination) {
        if (AnonymousClass2.$SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$PositiveButtonDestination[positiveButtonDestination.ordinal()] == 1) {
            openSettingsActivity("android.settings.SETTINGS");
            closeAlexaUi();
            return;
        }
        LOG.error("Alexa view positive button pressed with an unhandled positiveButtonDestination : " + positiveButtonDestination);
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onCancelButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination) {
        closeAlexaUi();
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onAlexaUiClicked() {
        if (isAlexaSpeechPlaying()) {
            this.alexaSpeechPlayer.pause();
            this.audioPlayerPlugin.cancel();
        }
        closeAlexaUi();
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onAlexaUiClosed() {
        this.isTextRequestInitiated = false;
    }

    private void openSettingsActivity(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        this.context.startActivity(intent);
    }

    public void showAlexaView() {
        if (!isAlexaUiVisible()) {
            hideKeyboard();
            cancelCloseAlexaUiTask();
            this.scrimRouter.pushController(new ControllerTransaction(this.scrimController, new ScrimCloseTransition(), new ScrimOpenTransition(), "scrim"));
            this.scrimController.notifyViewOpened();
            return;
        }
        LOG.debug("Alexa is already visible.");
    }

    private void hideKeyboard() {
        View currentFocus;
        InputMethodManager inputMethodManager;
        Activity activity = this.activity;
        if (activity == null || (currentFocus = activity.getCurrentFocus()) == null || (inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void onSaveInstanceState() {
        this.savedState = this.scrimController.saveState();
    }

    public void addAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.scrimController.addAlexaUiListener(alexaUiListener);
    }

    public void removeAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.scrimController.removeAlexaUiListener(alexaUiListener);
    }

    public boolean isAlexaUiVisible() {
        return this.scrimRouter.getCurrentController() != null;
    }

    private void closeAlexaUiComponents() {
        Voice voice = this.voice;
        if (voice != null) {
            voice.cancel();
        }
        stopAllAnimations();
        this.savedState = null;
        this.speechRecognizerPlugin.cancel();
        this.speechSynthesizerPlugin.cancel();
        this.textPlugin.cancel();
        this.recognizeSpeechController.stopRecognizeSpeech();
        this.scrimRouter.popCurrentController();
        pauseAndStopSpeechMessagesPlayer();
    }

    public void closeAlexaUi() {
        LOG.debug("Closing Alexa UI");
        closeAlexaUiComponents();
        this.scrimController.notifyViewClosed();
    }

    private void pauseAndStopSpeechMessagesPlayer() {
        this.alexaSpeechPlayer.pause();
        this.alexaSpeechPlayer.stop();
    }

    public void onRecognizeSpeechCancelled() {
        if (isAlexaUiVisible() && !this.isTextRequestInitiated) {
            ScrimController.AlexaHintsScrollState hintsScrollState = getHintsScrollState();
            if (hintsScrollState.isScrolling()) {
                this.recognizeSpeechController.startRecognizeSpeech(false);
                return;
            }
            long timeSinceLastScroll = getTimeSinceLastScroll(Long.valueOf(hintsScrollState.getLastScrollTime()));
            if (timeSinceLastScroll < CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                this.recognizeSpeechController.startRecognizeSpeech(CoroutineLiveDataKt.DEFAULT_TIMEOUT - timeSinceLastScroll, false);
            } else {
                this.earconSpeechRecordingListener.onSpeechRecordingTaskCancelled();
                softCloseAlexaUi();
            }
        }
    }

    @VisibleForTesting
    long getTimeSinceLastScroll(Long l) {
        return System.currentTimeMillis() - l.longValue();
    }

    @VisibleForTesting
    public ScrimController.AlexaHintsScrollState getHintsScrollState() {
        return this.scrimController.getAlexaHintsScrollState();
    }

    public void softCloseAlexaUi() {
        if (isAlexaSpeechPlaying() || this.keepUiVisible || this.speechRecognizerPlugin.isListening()) {
            LOG.debug("Not closing Alexa UI. isAlexaSpeechPlaying: " + isAlexaSpeechPlaying() + " uiVisible: " + this.keepUiVisible + " isListening: " + this.speechRecognizerPlugin.isListening());
            return;
        }
        closeAlexaUi();
    }

    public void startCloseAlexaUiTask() {
        this.handler.postDelayed(this.softCloseAlexaUiRunnable, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    public void startShortCloseAlexaUiTask() {
        this.handler.postDelayed(this.softCloseAlexaUiRunnable, 300L);
    }

    public void cancelCloseAlexaUiTask() {
        this.handler.removeCallbacks(this.softCloseAlexaUiRunnable);
    }

    public void toggleNetworkMessageVisibility(boolean z) {
        showAlexaView();
        this.scrimController.toggleNetworkMessageVisibility(z);
    }

    public void toggleHintsVisibility(boolean z) {
        showAlexaView();
        this.scrimController.toggleHintsVisibility(z);
    }

    public void toggleGenericErrorMessageVisibility(boolean z) {
        showAlexaView();
        this.scrimController.toggleGenericErrorMessageVisibility(z);
    }

    public void notifyOnSsmlSpeechDomain(String str) {
        this.scrimController.notifyOnSsmlSpeechDomain(str);
    }

    public void setKeepUiVisible(boolean z) {
        this.keepUiVisible = z;
    }

    public void setAlexaAccessProvider(AlexaAccessProvider alexaAccessProvider) {
        this.scrimController.setAlexaAccessProvider(alexaAccessProvider);
    }

    public void setAlexaUIStyleProvider(AlexaUIStyleProvider alexaUIStyleProvider) {
        this.scrimController.setAlexaUIStyleProvider(alexaUIStyleProvider);
    }

    public void setAlexaHintsViewModelProvider(AlexaHintsViewModelProvider alexaHintsViewModelProvider) {
        this.scrimController.setAlexaHintsViewModelProvider(alexaHintsViewModelProvider);
    }

    public void startProcessingAnimation() {
        showAlexaView();
        this.scrimController.startProcessingAnimation();
    }

    public void startListeningAnimation() {
        showAlexaView();
        this.scrimController.startListeningAnimation();
    }

    public void stopProcessingAnimation() {
        if (isAlexaUiVisible()) {
            this.scrimController.stopProcessingAnimation();
        }
    }

    public void startResponseAnimation() {
        showAlexaView();
        this.scrimController.startResponseAnimation();
    }

    public void stopAllAnimations() {
        if (isAlexaUiVisible()) {
            this.scrimController.stopAllAnimations();
        }
    }

    private boolean isAlexaSpeechPlaying() {
        return this.alexaSpeechPlayer.isPlaying();
    }

    public void setRefMarkerAndAssociateTag(String str, String str2) {
        this.scrimController.setRefMarkerAndAssociateTag(str, str2);
    }

    @Override // com.amazon.music.voice.ui.AlexaHintClickListener
    public void onAlexaHintClicked(@NonNull String str) {
        this.isTextRequestInitiated = true;
        this.speechRecognizerPlugin.cancel();
        this.textPlugin.sendTextMessageEvent(str);
        startProcessingAnimation();
    }
}
