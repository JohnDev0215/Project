package org.chromium.content_public.browser;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigationEntry {
    public final String a;
    public final String b;
    public final String c;
    public Bitmap d;

    public NavigationEntry(int i, String str, String str2, String str3, String str4, String str5, Bitmap bitmap, int i2, long j) {
        this.a = str;
        this.b = str3;
        this.c = str5;
        this.d = bitmap;
    }
}
