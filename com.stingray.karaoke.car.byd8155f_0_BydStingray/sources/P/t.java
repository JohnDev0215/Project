package P;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import n0.AbstractC1861K;
import n0.C1891t;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class t extends View {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int[] f5252q = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f5253r = new int[0];
    public E l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Boolean f5254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Long f5255n;
    public A2.l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public A7.o f5256p;

    private final void setRippleState(boolean z6) {
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        Runnable runnable = this.o;
        if (runnable != null) {
            removeCallbacks(runnable);
            runnable.run();
        }
        Long l = this.f5255n;
        long jLongValue = jCurrentAnimationTimeMillis - (l != null ? l.longValue() : 0L);
        if (z6 || jLongValue >= 5) {
            int[] iArr = z6 ? f5252q : f5253r;
            E e10 = this.l;
            if (e10 != null) {
                e10.setState(iArr);
            }
        } else {
            A2.l lVar = new A2.l(this, 6);
            this.o = lVar;
            postDelayed(lVar, 50L);
        }
        this.f5255n = Long.valueOf(jCurrentAnimationTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRippleState$lambda$2(t tVar) {
        E e10 = tVar.l;
        if (e10 != null) {
            e10.setState(f5253r);
        }
        tVar.o = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(C.m mVar, boolean z6, long j3, int i7, long j10, float f10, InterfaceC2772a interfaceC2772a) {
        if (this.l == null || !Boolean.valueOf(z6).equals(this.f5254m)) {
            E e10 = new E(z6);
            setBackground(e10);
            this.l = e10;
            this.f5254m = Boolean.valueOf(z6);
        }
        E e11 = this.l;
        A7.m.c(e11);
        this.f5256p = (A7.o) interfaceC2772a;
        Integer num = e11.f5207n;
        if (num == null || num.intValue() != i7) {
            e11.f5207n = Integer.valueOf(i7);
            D.f5205a.a(e11, i7);
        }
        e(j3, j10, f10);
        if (z6) {
            e11.setHotspot(m0.c.d(mVar.f834a), m0.c.e(mVar.f834a));
        } else {
            e11.setHotspot(e11.getBounds().centerX(), e11.getBounds().centerY());
        }
        setRippleState(true);
    }

    public final void c() {
        this.f5256p = null;
        A2.l lVar = this.o;
        if (lVar != null) {
            removeCallbacks(lVar);
            A2.l lVar2 = this.o;
            A7.m.c(lVar2);
            lVar2.run();
        } else {
            E e10 = this.l;
            if (e10 != null) {
                e10.setState(f5253r);
            }
        }
        E e11 = this.l;
        if (e11 == null) {
            return;
        }
        e11.setVisible(false, false);
        unscheduleDrawable(e11);
    }

    public final void d() {
        setRippleState(false);
    }

    public final void e(long j3, long j10, float f10) {
        E e10 = this.l;
        if (e10 == null) {
            return;
        }
        long jB = C1891t.b(AbstractC2673a.s(f10, 1.0f), j10);
        C1891t c1891t = e10.f5206m;
        if (!(c1891t == null ? false : C1891t.c(c1891t.f19453a, jB))) {
            e10.f5206m = new C1891t(jB);
            e10.setColor(ColorStateList.valueOf(AbstractC1861K.E(jB)));
        }
        Rect rect = new Rect(0, 0, C7.a.W(m0.f.d(j3)), C7.a.W(m0.f.b(j3)));
        setLeft(rect.left);
        setTop(rect.top);
        setRight(rect.right);
        setBottom(rect.bottom);
        e10.setBounds(rect);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.a] */
    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        ?? r02 = this.f5256p;
        if (r02 != 0) {
            r02.a();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
    }
}
