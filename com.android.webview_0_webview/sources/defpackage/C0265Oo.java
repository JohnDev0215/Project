package defpackage;

import org.chromium.android_webview.AwSettings;

/* JADX INFO: renamed from: Oo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0265Oo {
    public boolean a;
    public boolean b;
    public int c;
    public int d;
    public float e = 1.0f;
    public boolean f;
    public boolean g;
    public double h;
    public boolean i;
    public int j;
    public InterfaceC0247No k;

    public final void a(int i, int i2, float f) {
        float f2 = this.e;
        int i3 = (int) (((double) (i2 * f2)) * this.h);
        boolean z = false;
        boolean z2 = f2 != f;
        boolean z3 = !this.b && (!this.i || i3 < this.j);
        boolean z4 = !this.a || z3;
        if ((this.d != i && !this.a) || ((this.c != i2 && z3) || (z2 && z4))) {
            z = true;
        }
        this.d = i;
        this.c = i2;
        this.e = f;
        if (z) {
            if (this.f) {
                this.g = true;
            } else {
                ((C0103Fn) this.k).a.u.requestLayout();
            }
        }
    }

    public final void b() {
        InterfaceC0247No interfaceC0247No = this.k;
        C0103Fn c0103Fn = (C0103Fn) interfaceC0247No;
        boolean z = c0103Fn.a.u.getLayoutParams() != null && c0103Fn.a.u.getLayoutParams().height == -2;
        AwSettings awSettings = ((C0103Fn) interfaceC0247No).a.S;
        synchronized (awSettings.f) {
            if (awSettings.G != z) {
                awSettings.G = z;
                awSettings.m0.b();
            }
        }
    }
}
