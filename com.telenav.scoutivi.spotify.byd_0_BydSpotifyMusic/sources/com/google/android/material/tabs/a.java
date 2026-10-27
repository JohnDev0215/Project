package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: ElasticTabIndicatorInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
class a extends b {
    a() {
    }

    private static float e(float f7) {
        return (float) (1.0d - Math.cos((((double) f7) * 3.141592653589793d) / 2.0d));
    }

    private static float f(float f7) {
        return (float) Math.sin((((double) f7) * 3.141592653589793d) / 2.0d);
    }

    @Override // com.google.android.material.tabs.b
    void c(TabLayout tabLayout, View view, View view2, float f7, Drawable drawable) {
        float f8;
        float fE;
        RectF rectFA = b.a(tabLayout, view);
        RectF rectFA2 = b.a(tabLayout, view2);
        if (rectFA.left < rectFA2.left) {
            f8 = e(f7);
            fE = f(f7);
        } else {
            f8 = f(f7);
            fE = e(f7);
        }
        drawable.setBounds(p2.a.c((int) rectFA.left, (int) rectFA2.left, f8), drawable.getBounds().top, p2.a.c((int) rectFA.right, (int) rectFA2.right, fE), drawable.getBounds().bottom);
    }
}
