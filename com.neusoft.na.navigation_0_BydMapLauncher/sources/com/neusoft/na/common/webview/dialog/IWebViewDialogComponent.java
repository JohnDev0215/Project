package com.neusoft.na.common.webview.dialog;

/* JADX INFO: loaded from: classes2.dex */
public interface IWebViewDialogComponent {
    void dismissDialog();

    boolean isInMultiWindowMode();

    boolean isShowingDialog();

    void multiWindowModeChanged(boolean z);

    void setDayNightMode();

    void showDialog();
}
