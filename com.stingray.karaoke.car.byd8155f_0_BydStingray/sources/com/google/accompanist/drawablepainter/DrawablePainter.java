package com.google.accompanist.drawablepainter;

import A.I0;
import A7.m;
import C7.a;
import E0.H;
import I4.c;
import T.C0735d;
import T.C0738e0;
import T.InterfaceC0769u0;
import T.S;
import a1.k;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import h6.j;
import h6.r;
import kotlin.Metadata;
import m0.f;
import m7.n;
import n0.AbstractC1874c;
import n0.C1883l;
import n0.InterfaceC1889r;
import p0.C2071b;
import r0.AbstractC2268a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/accompanist/drawablepainter/DrawablePainter;", "Lr0/a;", "LT/u0;", "drawablepainter_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DrawablePainter extends AbstractC2268a implements InterfaceC0769u0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Drawable f12734q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f12735r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0738e0 f12736s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final n f12737t;

    public DrawablePainter(Drawable drawable) {
        m.f(drawable, "drawable");
        this.f12734q = drawable;
        S s6 = S.f8224q;
        this.f12735r = C0735d.O(0, s6);
        Object obj = c.f2942a;
        this.f12736s = C0735d.O(new f((drawable.getIntrinsicWidth() < 0 || drawable.getIntrinsicHeight() < 0) ? 9205357640488583168L : j.f(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight())), s6);
        this.f12737t = r.o(new I0(this, 22));
        if (drawable.getIntrinsicWidth() < 0 || drawable.getIntrinsicHeight() < 0) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // T.InterfaceC0769u0
    public final void a() {
        Drawable.Callback callback = (Drawable.Callback) this.f12737t.getValue();
        Drawable drawable = this.f12734q;
        drawable.setCallback(callback);
        drawable.setVisible(true, true);
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f12734q.setAlpha(AbstractC2673a.v(a.W(f10 * 255), 0, 255));
        return true;
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
        d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // T.InterfaceC0769u0
    public final void d() {
        Drawable drawable = this.f12734q;
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        drawable.setVisible(false, false);
        drawable.setCallback(null);
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f12734q.setColorFilter(c1883l != null ? c1883l.f19439a : null);
        return true;
    }

    @Override // r0.AbstractC2268a
    public final void f(k kVar) {
        int i7;
        m.f(kVar, "layoutDirection");
        int iOrdinal = kVar.ordinal();
        if (iOrdinal != 0) {
            i7 = 1;
            if (iOrdinal != 1) {
                throw new A9.k();
            }
        } else {
            i7 = 0;
        }
        this.f12734q.setLayoutDirection(i7);
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        return ((f) this.f12736s.getValue()).f18890a;
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) {
        C2071b c2071b = h10.l;
        InterfaceC1889r interfaceC1889rM = c2071b.f20638m.m();
        ((Number) this.f12735r.getValue()).intValue();
        int iW = a.W(f.d(c2071b.e()));
        int iW2 = a.W(f.b(c2071b.e()));
        Drawable drawable = this.f12734q;
        drawable.setBounds(0, 0, iW, iW2);
        try {
            interfaceC1889rM.m();
            drawable.draw(AbstractC1874c.a(interfaceC1889rM));
        } finally {
            interfaceC1889rM.k();
        }
    }
}
