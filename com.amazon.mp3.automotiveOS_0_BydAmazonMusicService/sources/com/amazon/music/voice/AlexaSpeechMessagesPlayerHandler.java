package com.amazon.music.voice;

import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.superbowl.Voice;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.music.voice.ui.AlexaUiListener;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaSpeechMessagesPlayerHandler {
    private AlexaUiController alexaUiController;
    private AudioPlayerProxy audioPlayerProxy;
    private SpeechRecognizerPlugin speechRecognizerPlugin;
    private Voice voice;
    private final AlexaUiListener.SimpleAlexaUiListener alexaUiListener = new AlexaUiListener.SimpleAlexaUiListener() { // from class: com.amazon.music.voice.AlexaSpeechMessagesPlayerHandler.1
        @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiClosed() {
            AlexaSpeechMessagesPlayerHandler.this.stopAlexaSpeechMessagesPlayer();
        }
    };
    private final AudioPlayer.SimpleCallback simpleCallback = new AudioPlayer.SimpleCallback() { // from class: com.amazon.music.voice.AlexaSpeechMessagesPlayerHandler.2
        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackStarted() {
            if (AlexaSpeechMessagesPlayerHandler.this.speechRecognizerPlugin.isListening()) {
                AlexaSpeechMessagesPlayerHandler.this.audioPlayerProxy.stopAlexaSpeechMessagesPlayer();
            } else {
                AlexaSpeechMessagesPlayerHandler.this.alexaUiController.startResponseAnimation();
                AlexaSpeechMessagesPlayerHandler.this.alexaUiController.cancelCloseAlexaUiTask();
            }
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackEnded() {
            Logger.debug("onPlaybackEnded");
            AlexaSpeechMessagesPlayerHandler.this.alexaUiController.setKeepUiVisible(false);
            if (AlexaSpeechMessagesPlayerHandler.this.voice.getNumberOfSequencedDirectives() == 0 && AlexaSpeechMessagesPlayerHandler.this.alexaUiController.isAlexaUiVisible() && !AlexaSpeechMessagesPlayerHandler.this.speechRecognizerPlugin.isListening()) {
                AlexaSpeechMessagesPlayerHandler.this.alexaUiController.startCloseAlexaUiTask();
            }
        }
    };

    public AlexaSpeechMessagesPlayerHandler(Voice voice, AlexaUiController alexaUiController, AudioPlayerProxy audioPlayerProxy, SpeechRecognizerPlugin speechRecognizerPlugin) {
        this.voice = voice;
        this.alexaUiController = alexaUiController;
        this.audioPlayerProxy = audioPlayerProxy;
        this.speechRecognizerPlugin = speechRecognizerPlugin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAlexaSpeechMessagesPlayer() {
        this.audioPlayerProxy.stopAlexaSpeechMessagesPlayer();
    }

    public AlexaUiListener.SimpleAlexaUiListener getAlexaUiListener() {
        return this.alexaUiListener;
    }

    public AudioPlayer.SimpleCallback getSimpleCallback() {
        return this.simpleCallback;
    }
}
