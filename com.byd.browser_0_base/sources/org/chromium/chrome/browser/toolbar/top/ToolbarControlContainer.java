package org.chromium.chrome.browser.toolbar.top;

import J.N;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import defpackage.ao3;
import defpackage.bq;
import defpackage.cc3;
import defpackage.dr;
import defpackage.dx2;
import defpackage.e00;
import defpackage.gm;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.pn3;
import defpackage.qb3;
import defpackage.t41;
import defpackage.tx;
import defpackage.vj;
import defpackage.yj1;
import defpackage.zp1;
import java.util.function.BooleanSupplier;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ToolbarControlContainer extends OptimizedFrameLayout implements e00 {
    public static final /* synthetic */ int j = 0;
    public final float f;
    public qb3 g;
    public ToolbarViewResourceFrameLayout h;
    public a i;

    public static class ToolbarViewResourceFrameLayout extends ViewResourceFrameLayout {
        public boolean h;

        public ToolbarViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
        public final pn3 j() {
            return new b(this, bq.h0.a());
        }

        @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
        public final boolean l() {
            return this.h && getVisibility() == 0;
        }
    }

    public class a extends dx2 {
        public a(Context context, dx2.a aVar) {
            super(context, aVar);
        }

        @Override // defpackage.dx2
        public final boolean b(MotionEvent motionEvent) {
            int i = ToolbarControlContainer.j;
            ToolbarControlContainer toolbarControlContainer = ToolbarControlContainer.this;
            toolbarControlContainer.getClass();
            if (motionEvent.getY() <= toolbarControlContainer.f) {
                return false;
            }
            qb3 qb3Var = toolbarControlContainer.g;
            return (qb3Var == null || !qb3Var.e()) && !t41.f.f(toolbarControlContainer.getContext(), toolbarControlContainer);
        }
    }

    public static class b extends pn3 {
        public final int[] l;
        public final Rect m;
        public final Rect n;
        public final View o;
        public final dr p;
        public qb3 q;
        public int r;
        public tx s;
        public nw2<Tab> t;
        public zp1<Boolean> u;
        public vj v;
        public BooleanSupplier w;
        public int x;

        public b(View view, boolean z) {
            super(view, z);
            this.l = new int[2];
            this.m = new Rect();
            this.n = new Rect();
            this.p = new dr(19, this);
            this.x = -1;
            this.o = view;
        }

        public static void j(b bVar, Boolean bool) {
            bVar.getClass();
            if (!cc3.b() || bVar.q == null || bVar.v == null || bVar.w == null) {
                return;
            }
            yj1 yj1Var = cc3.c;
            if (yj1Var.a()) {
                oj2.U(0, 2, "Android.TopToolbar.InMotionStage");
            }
            if (!Boolean.TRUE.equals(bool)) {
                int i = bVar.x;
                if (i == -1) {
                    bVar.b();
                    return;
                } else {
                    bVar.v.u(i);
                    bVar.x = -1;
                    return;
                }
            }
            if (super.h() && bVar.w.getAsBoolean()) {
                gm gmVarK = bVar.q.k();
                if (yj1Var.a() && bool != null) {
                    oj2.U(1, 2, "Android.TopToolbar.InMotionStage");
                }
                if (gmVarK.b == 3) {
                    bVar.f.setEmpty();
                } else if (gmVarK.a) {
                    bVar.x = bVar.v.w(bVar.x);
                    tx txVar = bVar.s;
                    txVar.f.p(txVar);
                    gm.a(gm.b(11));
                }
            }
        }

        @Override // defpackage.fm
        public final void d(Canvas canvas, Rect rect) {
            oj2.U(1, 3, "Android.Toolbar.BitmapCapture");
            canvas.save();
            View view = this.o;
            canvas.clipRect(0, 0, view.getWidth(), view.getHeight());
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.restore();
            rect.set(0, 0, view.getWidth(), view.getHeight());
            this.q.l(true);
        }

        @Override // defpackage.fm
        public final void e() {
            this.q.l(false);
            this.q.c(false);
        }

        @Override // defpackage.pn3
        public final long f() {
            qb3 qb3Var = this.q;
            View view = this.o;
            int[] iArr = this.l;
            qb3Var.j(view, iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = view.getWidth();
            int height = this.q.getHeight() + iArr[1];
            Rect rect = this.n;
            rect.set(i, i2, width, height);
            qb3 qb3Var2 = this.q;
            Rect rect2 = this.m;
            qb3Var2.h(rect2);
            rect2.offset(iArr[0], iArr[1]);
            return N.MbtZqOic(rect.left, rect.top, rect.right, rect.bottom, rect2.left, rect2.top, rect2.right, rect2.bottom, (view.getHeight() - this.q.getHeight()) - this.r);
        }

        @Override // defpackage.pn3
        public final boolean h() {
            nw2<Tab> nw2Var;
            if (!super.h()) {
                gm.a(gm.b(2));
                return false;
            }
            if (cc3.b()) {
                if (this.s != null && (nw2Var = this.t) != null) {
                    Tab tab = nw2Var.get();
                    if (!(tab == null || tab.isNativePage()) && this.s.a()) {
                        tx txVar = this.s;
                        txVar.f.p(txVar);
                        gm.a(gm.b(9));
                        return false;
                    }
                }
                zp1<Boolean> zp1Var = this.u;
                if (zp1Var != null) {
                    if (Boolean.TRUE.equals(zp1Var.get())) {
                        gm.a(gm.b(11));
                        return false;
                    }
                }
            }
            qb3 qb3Var = this.q;
            gm gmVarK = qb3Var == null ? null : qb3Var.k();
            if (gmVarK != null && gmVarK.b == 3) {
                this.f.setEmpty();
            }
            gm.a(gmVarK);
            if (gmVarK == null) {
                return false;
            }
            return gmVarK.a;
        }
    }

    public ToolbarControlContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context.getResources().getDimension(R$dimen.tab_strip_height);
    }

    @Override // defpackage.e00
    public final void b(int i) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarControlContainer.initWithToolbar", null);
        try {
            this.h = (ToolbarViewResourceFrameLayout) findViewById(R$id.toolbar_container);
            ViewStub viewStub = (ViewStub) findViewById(R$id.toolbar_stub);
            viewStub.setLayoutResource(i);
            viewStub.inflate();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean gatherTransparentRegion(Region region) {
        float translationY = getTranslationY();
        setTranslationY(0.0f);
        int[] iArr = ao3.a;
        getLocationInWindow(iArr);
        int i = iArr[0];
        region.op(i, iArr[1], (getRight() + i) - getLeft(), (getBottom() + iArr[1]) - getTop(), Region.Op.DIFFERENCE);
        setTranslationY(translationY);
        return true;
    }

    public int getToolbarBackgroundColor() {
        qb3 qb3Var = this.g;
        if (qb3Var == null) {
            return 0;
        }
        return qb3Var.a();
    }

    @Override // defpackage.e00
    public pn3 getToolbarResourceAdapter() {
        return this.h.getResourceAdapter();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!(Float.compare(0.0f, getTranslationY()) == 0 && this.h.getVisibility() == 0)) {
            return true;
        }
        if (this.i != null) {
            if (!(motionEvent.getY() <= this.f)) {
                return this.i.a(motionEvent);
            }
        }
        return false;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.i == null) {
            return false;
        }
        if (!(Float.compare(0.0f, getTranslationY()) == 0 && this.h.getVisibility() == 0)) {
            return true;
        }
        if (motionEvent.getActionMasked() == 0) {
            if (!(motionEvent.getY() <= this.f)) {
                return true;
            }
        }
        return this.i.a(motionEvent);
    }

    public void setReadyForBitmapCapture(boolean z) {
        this.h.h = z;
    }

    @Override // defpackage.e00
    public void setSwipeHandler(dx2.a aVar) {
        this.i = new a(getContext(), aVar);
    }

    @Override // defpackage.e00
    public View getView() {
        return this;
    }
}
