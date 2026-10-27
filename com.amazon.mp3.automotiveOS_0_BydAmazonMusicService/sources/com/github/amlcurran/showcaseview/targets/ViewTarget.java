package com.github.amlcurran.showcaseview.targets;

import android.graphics.Point;
import android.view.View;

/* JADX INFO: loaded from: classes6.dex */
public class ViewTarget implements Target {
    private final View mView;

    public ViewTarget(View view) {
        this.mView = view;
    }

    @Override // com.github.amlcurran.showcaseview.targets.Target
    public Point getPoint() {
        int[] iArr = new int[2];
        this.mView.getLocationInWindow(iArr);
        return new Point(iArr[0] + (this.mView.getWidth() / 2), iArr[1] + (this.mView.getHeight() / 2));
    }
}
