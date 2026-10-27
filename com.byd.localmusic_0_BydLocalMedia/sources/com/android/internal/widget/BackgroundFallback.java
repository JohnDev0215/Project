package com.android.internal.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class BackgroundFallback {
    private Drawable mBackgroundFallback;

    public void setDrawable(Drawable drawable) {
        this.mBackgroundFallback = drawable;
    }

    public Drawable getDrawable() {
        return this.mBackgroundFallback;
    }

    public boolean hasFallback() {
        return this.mBackgroundFallback != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.view.ViewGroup r19, android.view.ViewGroup r20, android.graphics.Canvas r21, android.view.View r22, android.view.View r23, android.view.View r24) {
        /*
            Method dump skipped, instruction units count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.BackgroundFallback.draw(android.view.ViewGroup, android.view.ViewGroup, android.graphics.Canvas, android.view.View, android.view.View, android.view.View):void");
    }

    private boolean isOpaque(Drawable drawable) {
        return drawable != null && drawable.getOpacity() == -1;
    }

    private boolean viewsCoverEntireWidth(View view, View view2, int i) {
        return view.getLeft() <= 0 && view.getRight() >= view2.getLeft() && view2.getRight() >= i;
    }
}
