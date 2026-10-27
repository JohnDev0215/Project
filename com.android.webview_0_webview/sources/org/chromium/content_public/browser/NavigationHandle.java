package org.chromium.content_public.browser;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigationHandle {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public Integer d;
    public String e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j;

    public NavigationHandle(long j, String str, boolean z, boolean z2, boolean z3) {
        this.e = str;
        this.a = z;
        this.c = z2;
        this.b = z3;
    }

    private void didRedirect(String str) {
        this.e = str;
    }

    private void release() {
    }

    public void didFinish(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, int i3) {
        this.e = str;
        this.h = z;
        this.f = z2;
        this.i = z3;
        this.g = z4;
        this.d = i == -1 ? null : Integer.valueOf(i);
        this.j = i2;
    }
}
