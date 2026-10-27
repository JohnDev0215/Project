package com.amazon.music.voice.ui;

import com.amazon.music.voice.ui.scrim.ScrimController;

/* JADX INFO: loaded from: classes4.dex */
public interface AlexaUiListener {

    public static class SimpleAlexaUiListener implements AlexaUiListener {
        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiClicked() {
        }

        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiClosed() {
        }

        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiOpened() {
        }

        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onCancelButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination) {
        }

        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onPositiveButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination) {
        }

        @Override // com.amazon.music.voice.ui.AlexaUiListener
        public void onSsmlSpeechDomain(String str) {
        }
    }

    void onAlexaUiClicked();

    void onAlexaUiClosed();

    void onAlexaUiOpened();

    void onCancelButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination);

    void onPositiveButtonPressed(ScrimController.PositiveButtonDestination positiveButtonDestination);

    void onSsmlSpeechDomain(String str);
}
