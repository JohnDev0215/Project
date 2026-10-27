package com.github.amlcurran.showcaseview;

import android.graphics.Rect;
import android.util.Log;

/* JADX INFO: loaded from: classes6.dex */
class ShowcaseAreaCalculator {
    private final Rect mShowcaseRect = new Rect();

    ShowcaseAreaCalculator() {
    }

    public boolean calculateShowcaseRect(float f, float f2, ShowcaseDrawer showcaseDrawer) {
        int i = (int) f;
        int i2 = (int) f2;
        int showcaseWidth = showcaseDrawer.getShowcaseWidth();
        int showcaseHeight = showcaseDrawer.getShowcaseHeight();
        Rect rect = this.mShowcaseRect;
        int i3 = showcaseWidth / 2;
        int i4 = i - i3;
        if (rect.left == i4 && rect.top == i2 - (showcaseHeight / 2)) {
            return false;
        }
        Log.d("ShowcaseView", "Recalculated");
        Rect rect2 = this.mShowcaseRect;
        rect2.left = i4;
        int i5 = showcaseHeight / 2;
        rect2.top = i2 - i5;
        rect2.right = i + i3;
        rect2.bottom = i2 + i5;
        return true;
    }

    public Rect getShowcaseRect() {
        return this.mShowcaseRect;
    }
}
