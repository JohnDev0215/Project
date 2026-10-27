package h;

import android.window.BackEvent;

/* JADX INFO: renamed from: h.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1330b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f15092a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f15093b;
    public final float c;
    public final int d;

    public C1330b(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        C1329a c1329a = C1329a.f15091a;
        float fD = c1329a.d(backEvent);
        float fE = c1329a.e(backEvent);
        float fB = c1329a.b(backEvent);
        int iC = c1329a.c(backEvent);
        this.f15092a = fD;
        this.f15093b = fE;
        this.c = fB;
        this.d = iC;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BackEventCompat{touchX=");
        sb.append(this.f15092a);
        sb.append(", touchY=");
        sb.append(this.f15093b);
        sb.append(", progress=");
        sb.append(this.c);
        sb.append(", swipeEdge=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.d, '}');
    }
}
