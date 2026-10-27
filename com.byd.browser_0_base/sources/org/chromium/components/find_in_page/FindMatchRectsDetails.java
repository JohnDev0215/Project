package org.chromium.components.find_in_page;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public final class FindMatchRectsDetails {
    public final int a;
    public final RectF[] b;
    public final RectF c;

    public FindMatchRectsDetails(int i, int i2, RectF rectF) {
        this.a = i;
        this.b = new RectF[i2];
        this.c = rectF;
    }
}
