package com.amazon.avod.playbackclient.displaymode;

import android.view.View;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutUpdater implements View.OnLayoutChangeListener {
    protected abstract void update();

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int width = view.getWidth();
        int i9 = i7 - i5;
        int height = view.getHeight();
        int i10 = i8 - i6;
        if (width == i9 && height == i10) {
            return;
        }
        DLog.logf("view %d size changed from (w=%s h=%s) to (w=%s h=%s)", Integer.valueOf(view.getId()), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(width), Integer.valueOf(height));
        update();
    }
}
