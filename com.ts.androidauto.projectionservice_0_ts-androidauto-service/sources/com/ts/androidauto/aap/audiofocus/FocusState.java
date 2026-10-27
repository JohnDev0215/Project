package com.ts.androidauto.aap.audiofocus;

/* JADX INFO: loaded from: classes.dex */
public interface FocusState {
    FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction);

    FocusState doMdReqActionAndGetNextState(FocusState focusState, int i);

    void enterState(boolean z, int i);

    void exitState();
}
