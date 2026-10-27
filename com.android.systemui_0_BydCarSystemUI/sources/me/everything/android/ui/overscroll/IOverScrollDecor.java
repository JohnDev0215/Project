package me.everything.android.ui.overscroll;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface IOverScrollDecor {
    void detach();

    int getCurrentState();

    View getView();

    void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener);

    void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener);
}
