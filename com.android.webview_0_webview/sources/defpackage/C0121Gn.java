package defpackage;

import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Gn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0121Gn implements InterfaceC0445Yo {
    public final /* synthetic */ AwContents a;

    public C0121Gn(AwContents awContents, C1838yn c1838yn) {
        this.a = awContents;
    }

    public int a() {
        return this.a.u.getScrollX();
    }

    public int b() {
        return this.a.u.getScrollY();
    }

    public void c(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.a.L.e(i, i2, i3, i4, i5, i6, 0, 0, z);
    }
}
