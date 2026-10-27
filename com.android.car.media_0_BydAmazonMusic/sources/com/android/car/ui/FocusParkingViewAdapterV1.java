package com.android.car.ui;

import android.view.View;
import com.android.car.ui.rotary.FocusParkingView;
import spi.c4;

/* JADX INFO: loaded from: classes2.dex */
public class FocusParkingViewAdapterV1 implements c4 {
    private final FocusParkingView mFocusParkingView;

    public FocusParkingViewAdapterV1(FocusParkingView focusParkingView) {
        this.mFocusParkingView = focusParkingView;
    }

    public View getView() {
        return this.mFocusParkingView;
    }

    public void setShouldRestoreFocus(boolean z) {
        this.mFocusParkingView.setShouldRestoreFocus(z);
    }
}
