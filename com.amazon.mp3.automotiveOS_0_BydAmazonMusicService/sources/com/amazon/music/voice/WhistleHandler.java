package com.amazon.music.voice;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.superbowl.Voice;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.music.voice.AlexaErrorHandler;
import com.amazon.music.voice.ui.AlexaUiListener;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class WhistleHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WhistleHandler.class.getSimpleName());
    private static final long WAIT_FOR_ALEXA_DIRECTIVE_DELAY_MILLIS = TimeUnit.SECONDS.toMillis(10);
    private final Runnable displayAlexaErrorOnNoDirectivesRunnable;
    private final SpeechRecognizerPlugin.RecognizeSpeechListener recognizeSpeechListener;
    private final Handler handler = new Handler();
    private final Voice.DirectiveProcessingListener directiveProcessingListener = createAlexaDirectiveProcessingListener();
    private final AlexaUiListener alexaUiListener = createAlexaUiListener();

    public WhistleHandler(Voice voice, AlexaErrorHandler alexaErrorHandler) {
        this.displayAlexaErrorOnNoDirectivesRunnable = createDisplayErrorOnNoDirectivesRunnable(voice, alexaErrorHandler);
        this.recognizeSpeechListener = createRecognizeSpeechListener(voice);
    }

    @NonNull
    private Runnable createDisplayErrorOnNoDirectivesRunnable(final Voice voice, final AlexaErrorHandler alexaErrorHandler) {
        return new Runnable() { // from class: com.amazon.music.voice.WhistleHandler.1
            @Override // java.lang.Runnable
            public void run() {
                if (voice.getNumberOfSequencedDirectives() == 0) {
                    WhistleHandler.LOG.debug("no directives, displaying an error");
                    alexaErrorHandler.handleError(AlexaErrorHandler.AlexaErrorType.ON_NO_DIRECTIVE_RECEIVED_TIMEOUT);
                } else {
                    WhistleHandler.LOG.debug("there are {} directives in a queue", Integer.valueOf(voice.getNumberOfSequencedDirectives()));
                }
            }
        };
    }

    private SpeechRecognizerPlugin.RecognizeSpeechListener createRecognizeSpeechListener(final Voice voice) {
        return new SpeechRecognizerPlugin.SimpleRecognizeSpeechListener() { // from class: com.amazon.music.voice.WhistleHandler.2
            @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
            public void onRecognizeSpeechCompleted() {
                WhistleHandler.LOG.debug("onRecognizeSpeechCompleted -- ");
                if (voice.getNumberOfSequencedDirectives() == 0) {
                    WhistleHandler.LOG.debug("submitting handler.postDelayed(displayAlexaErro...");
                    WhistleHandler.this.scheduleErrorDisplayingTask();
                    return;
                }
                WhistleHandler.LOG.debug("onRecognizeSpeechCompleted, we have " + voice.getNumberOfSequencedDirectives() + " directives in a queue");
            }
        };
    }

    private Voice.DirectiveProcessingListener createAlexaDirectiveProcessingListener() {
        return new Voice.SimpleDirectiveProcessingListener() { // from class: com.amazon.music.voice.WhistleHandler.3
            @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
            public void onDirectiveReceived(Directive directive) {
                super.onDirectiveReceived(directive);
                WhistleHandler.this.cancelErrorDisplayingTask();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleErrorDisplayingTask() {
        this.handler.postDelayed(this.displayAlexaErrorOnNoDirectivesRunnable, WAIT_FOR_ALEXA_DIRECTIVE_DELAY_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelErrorDisplayingTask() {
        LOG.debug("Removing error on no directive callback.");
        this.handler.removeCallbacks(this.displayAlexaErrorOnNoDirectivesRunnable);
    }

    private AlexaUiListener.SimpleAlexaUiListener createAlexaUiListener() {
        return new AlexaUiListener.SimpleAlexaUiListener() { // from class: com.amazon.music.voice.WhistleHandler.4
            @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
            public void onAlexaUiClosed() {
                WhistleHandler.this.cancelErrorDisplayingTask();
            }
        };
    }

    public SpeechRecognizerPlugin.RecognizeSpeechListener getRecognizeSpeechListener() {
        return this.recognizeSpeechListener;
    }

    public Voice.DirectiveProcessingListener getDirectiveProcessingListener() {
        return this.directiveProcessingListener;
    }

    public AlexaUiListener getAlexaUiListener() {
        return this.alexaUiListener;
    }
}
