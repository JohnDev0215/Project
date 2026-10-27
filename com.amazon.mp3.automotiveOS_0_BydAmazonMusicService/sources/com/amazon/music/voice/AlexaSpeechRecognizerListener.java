package com.amazon.music.voice;

import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.music.voice.AlexaErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaSpeechRecognizerListener extends SpeechRecognizerPlugin.SimpleRecognizeSpeechListener {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaSpeechRecognizerListener.class.getSimpleName());
    private AlexaErrorHandler alexaErrorHandler;
    private AlexaUiController alexaUiController;

    public AlexaSpeechRecognizerListener(AlexaUiController alexaUiController, AlexaErrorHandler alexaErrorHandler) {
        this.alexaUiController = alexaUiController;
        this.alexaErrorHandler = alexaErrorHandler;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
    public void onRecognizeSpeechStarted(boolean z) {
        if (z) {
            LOG.debug("Recognize speech started for multi-turn utterance");
        } else {
            LOG.debug("Recognize speech started");
        }
        this.alexaUiController.setKeepUiVisible(false);
        this.alexaUiController.showAlexaView();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
    public void onRecognizeSpeechCancelled() {
        LOG.debug("Recognize speech cancelled");
        this.alexaUiController.onRecognizeSpeechCancelled();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
    public void onRecognizeSpeechRecordingCompleted() {
        LOG.debug("Recognize speech recording completed");
        this.alexaUiController.startProcessingAnimation();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
    public void onRecognizeSpeechCompleted() {
        LOG.info("Recognize speech completed");
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
    public void onRecognizeSpeechFailed(Throwable th) {
        LOG.error("Recognize speech failed", th);
        this.alexaErrorHandler.handleError(AlexaErrorHandler.AlexaErrorType.ON_SPEECH_RECOGNITION_ERROR, th);
    }
}
