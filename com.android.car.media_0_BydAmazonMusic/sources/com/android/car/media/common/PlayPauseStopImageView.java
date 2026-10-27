package com.android.car.media.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class PlayPauseStopImageView extends ImageView {
    public static final int[] b = {com.android.car.ui.R$attr.state_pause};
    public static final int[] c = {com.android.car.ui.R$attr.state_stop};
    public static final int[] d = {com.android.car.ui.R$attr.state_play};
    public static final int[] e = {com.android.car.ui.R$attr.state_disabled};
    public int a;

    public PlayPauseStopImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
    }

    public int getAction() {
        return this.a;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        int i2 = this.a;
        if (i2 == 0) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, e);
        } else if (i2 == 1) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, d);
        } else if (i2 == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, c);
        } else if (i2 != 3) {
            StringBuilder sbK = c.k("Unknown action: ");
            sbK.append(this.a);
            Log.e("PlayPauseStopImageView", sbK.toString());
        } else {
            View.mergeDrawableStates(iArrOnCreateDrawableState, b);
        }
        if (getBackground() != null) {
            getBackground().setState(iArrOnCreateDrawableState);
        }
        return iArrOnCreateDrawableState;
    }

    public void setAction(int i) {
        this.a = i;
        refreshDrawableState();
    }
}
