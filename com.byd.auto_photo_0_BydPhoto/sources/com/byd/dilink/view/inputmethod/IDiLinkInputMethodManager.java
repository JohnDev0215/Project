package com.byd.dilink.view.inputmethod;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkInputMethodManager {
    void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener);

    int getCurTokenDisplayId();

    boolean getInputShownState();

    void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener);
}
