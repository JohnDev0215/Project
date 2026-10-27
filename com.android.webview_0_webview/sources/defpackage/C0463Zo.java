package defpackage;

import J.N;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Zo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0463Zo {
    public final InterfaceC0445Yo a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public boolean i;
    public int j;
    public int k;

    public C0463Zo(InterfaceC0445Yo interfaceC0445Yo) {
        this.a = interfaceC0445Yo;
    }

    public final boolean a(int i, int i2) {
        int iA = ((C0121Gn) this.a).a();
        int iB = ((C0121Gn) this.a).b();
        int iB2 = b(i) - iA;
        int iC = c(i2) - iB;
        if (iB2 == 0 && iC == 0) {
            return false;
        }
        InterfaceC0445Yo interfaceC0445Yo = this.a;
        int i3 = iA + iB2;
        int i4 = iB + iC;
        long jMin = Math.min((Math.max(Math.abs(iB2), Math.abs(iC)) * 1000) / 480, 750);
        C0121Gn c0121Gn = (C0121Gn) interfaceC0445Yo;
        if (!c0121Gn.a.t(0)) {
            AwContents awContents = c0121Gn.a;
            N.MgUWjwjs(awContents.s, awContents, i3, i4, jMin);
        }
        ((C0121Gn) this.a).a.postInvalidateOnAnimation();
        return true;
    }

    public final int b(int i) {
        return Math.min(this.d, Math.max(0, i));
    }

    public final int c(int i) {
        return Math.min(this.e, Math.max(0, i));
    }

    public int d() {
        return this.g + this.e;
    }

    public final void e(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        int iA = ((C0121Gn) this.a).a();
        int iB = ((C0121Gn) this.a).b();
        int i3 = this.d;
        int i4 = this.e;
        ((C0121Gn) this.a).c(i, i2, iA, iB, i3, i4, this.h);
    }

    public final void f(int i, int i2) {
        int iB = b(i);
        int iC = c(i2);
        if (this.h) {
            this.j = iB;
            this.k = iC;
            this.i = true;
        } else {
            if (iB == this.b && iC == this.c) {
                return;
            }
            this.b = iB;
            this.c = iC;
            C0121Gn c0121Gn = (C0121Gn) this.a;
            if (c0121Gn.a.t(0)) {
                return;
            }
            AwContents awContents = c0121Gn.a;
            N.Mnc0PDKE(awContents.s, awContents, iB, iC);
        }
    }
}
