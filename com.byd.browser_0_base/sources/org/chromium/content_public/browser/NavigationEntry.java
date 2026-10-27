package org.chromium.content_public.browser;

import android.graphics.Bitmap;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public final class NavigationEntry {
    public final int a;
    public final GURL b;
    public final GURL c;
    public final GURL d;
    public final String e;
    public Bitmap f;

    public NavigationEntry(int i, GURL gurl, GURL gurl2, GURL gurl3, String str, Bitmap bitmap) {
        this.a = i;
        this.b = gurl;
        this.d = gurl2;
        this.c = gurl3;
        this.e = str;
        this.f = bitmap;
    }
}
