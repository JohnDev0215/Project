package com.amazon.music.voice;

import com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin;
import com.amazon.music.voice.AlexaErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaSpeechSynthesizerListener implements SpeechSynthesizerPlugin.SynthesizeSpeechListener {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaSpeechSynthesizerListener.class.getSimpleName());
    private AlexaErrorHandler alexaErrorHandler;
    private AlexaUiController alexaUiController;

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
    public void onSynthesizeSpeechCompleted(String str) {
    }

    public AlexaSpeechSynthesizerListener(AlexaUiController alexaUiController, AlexaErrorHandler alexaErrorHandler) {
        this.alexaUiController = alexaUiController;
        this.alexaErrorHandler = alexaErrorHandler;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
    public void onSynthesizeSpeechStarted(String str) {
        this.alexaUiController.stopProcessingAnimation();
        this.alexaUiController.startResponseAnimation();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
    public void onSynthesizeSpeechCancelled(String str) {
        this.alexaUiController.stopAllAnimations();
        this.alexaUiController.softCloseAlexaUi();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
    public void onSynthesizeSpeechFailed(String str, Throwable th) {
        this.alexaErrorHandler.handleError(AlexaErrorHandler.AlexaErrorType.ON_SPEECH_SYNTHESIZE_ERROR, th);
    }
}
