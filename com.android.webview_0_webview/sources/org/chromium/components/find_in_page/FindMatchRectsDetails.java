package org.chromium.components.find_in_page;

import android.graphics.RectF;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FindMatchRectsDetails {
    public final int a;
    public final RectF[] b;
    public final RectF c;

    public FindMatchRectsDetails(int i, int i2, RectF rectF) {
        this.a = i;
        this.b = new RectF[i2];
        this.c = rectF;
    }
}
