package com.amazon.music.voice.ui;

import com.amazon.music.voice.ui.scrim.ScrimController;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ScrimControllerState implements Serializable {
    private ScrimController.AnimationState chromeAnimationState;
    private int chromeVisibility;
    private int dialogButtonsContainerVisibility;
    private int errorMessageContainerVisibility;
    private String errorMessageText;
    private List<String> hints;
    private int hintsContainerVisibility;
    private int messageTextViewVisibility;
    private String positiveButtonText;

    public ScrimControllerState(ScrimController.AnimationState animationState, int i, List<String> list, int i2, int i3, String str, String str2, int i4, int i5) {
        this.chromeAnimationState = animationState;
        this.hintsContainerVisibility = i;
        this.hints = list;
        this.errorMessageContainerVisibility = i2;
        this.messageTextViewVisibility = i3;
        this.errorMessageText = str;
        this.positiveButtonText = str2;
        this.dialogButtonsContainerVisibility = i4;
        this.chromeVisibility = i5;
    }

    public ScrimController.AnimationState getChromeAnimationState() {
        return this.chromeAnimationState;
    }

    public int getHintsContainerVisibility() {
        return this.hintsContainerVisibility;
    }

    public List<String> getHints() {
        return this.hints;
    }

    public int getErrorMessageContainerVisibility() {
        return this.errorMessageContainerVisibility;
    }

    public int getMessageTextViewVisibility() {
        return this.messageTextViewVisibility;
    }

    public String getErrorMessageText() {
        return this.errorMessageText;
    }

    public String getPositiveButtonText() {
        return this.positiveButtonText;
    }

    public int getDialogButtonsContainerVisibility() {
        return this.dialogButtonsContainerVisibility;
    }

    public int getChromeVisibility() {
        return this.chromeVisibility;
    }
}
