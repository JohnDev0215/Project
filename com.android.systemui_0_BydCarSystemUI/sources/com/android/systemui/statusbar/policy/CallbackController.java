package com.android.systemui.statusbar.policy;

/* JADX INFO: loaded from: classes.dex */
public interface CallbackController<T> {
    void addCallback(T listener);

    void removeCallback(T listener);
}
