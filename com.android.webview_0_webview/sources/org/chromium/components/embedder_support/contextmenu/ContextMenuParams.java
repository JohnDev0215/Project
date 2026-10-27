package org.chromium.components.embedder_support.contextmenu;

import android.text.TextUtils;
import defpackage.C0984jE;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContextMenuParams {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public ContextMenuParams(int i, String str, String str2, String str3, String str4, String str5, String str6, C0984jE c0984jE, boolean z, int i2, int i3, int i4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str6;
        this.e = str5;
        TextUtils.isEmpty(str2);
    }

    public static ContextMenuParams create(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, boolean z, int i3, int i4, int i5) {
        return new ContextMenuParams(i, str, str2, str3, str4, str5, str6, TextUtils.isEmpty(str7) ? null : new C0984jE(str7, i2), z, i3, i4, i5);
    }
}
