package com.amazon.music.ghostlistening;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DefaultGhostListeningDialog extends GhostListeningDialog {
    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public boolean hasTitle() {
        return false;
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public int getTitle() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public int getMessage() {
        return R.string.ghost_listening_dialog_main_text;
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public int getPositiveButtonText() {
        return R.string.ghost_listening_dialog_positive_button_text;
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public int getNegativeButtonText() {
        return R.string.ghost_listening_dialog_negative_button_text;
    }
}
