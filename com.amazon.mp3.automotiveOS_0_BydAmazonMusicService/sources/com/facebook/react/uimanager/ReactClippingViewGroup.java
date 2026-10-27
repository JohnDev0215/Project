package com.facebook.react.uimanager;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes6.dex */
public interface ReactClippingViewGroup {
    void getClippingRect(Rect rect);

    boolean getRemoveClippedSubviews();

    void updateClippingRect();
}
