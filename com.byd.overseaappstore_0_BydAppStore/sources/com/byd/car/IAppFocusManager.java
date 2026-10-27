package com.byd.car;

/* JADX INFO: loaded from: classes.dex */
public interface IAppFocusManager {
    int getCurrentResourceFocusAppCode(int i);

    String getCurrentResourceFocusPackageName(int i);

    void registerResourceFocusChanged(int i, int i2, IFocusChangeListener iFocusChangeListener);

    int releaseResourceFocus(int i, int i2);

    int requestResourceFocus(int i, int i2);

    void unregisterResourceFocusChanged(int i, int i2);
}
