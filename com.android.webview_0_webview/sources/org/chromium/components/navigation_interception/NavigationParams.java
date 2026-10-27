package org.chromium.components.navigation_interception;

import android.text.TextUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigationParams {
    public final String a;
    public final String b;
    public final boolean c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;

    public NavigationParams(String str, String str2, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.a = str;
        this.b = TextUtils.isEmpty(str2) ? null : str2;
        this.c = z2;
        this.d = i;
        this.e = z3;
        this.f = z5;
        this.g = z6;
    }

    public static NavigationParams create(String str, String str2, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return new NavigationParams(str, str2, z, z2, i, z3, z4, z5, z6, z7);
    }
}
