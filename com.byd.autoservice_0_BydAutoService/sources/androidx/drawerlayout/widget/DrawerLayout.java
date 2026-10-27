package androidx.drawerlayout.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.List;
import l0.t;
import m0.c;
import p0.c;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1233b = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1234c = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1235d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f1236e;
    public float A;
    public float B;
    public Drawable C;
    public Drawable D;
    public Drawable E;
    public CharSequence F;
    public CharSequence G;
    public Object H;
    public boolean I;
    public Drawable J;
    public Drawable K;
    public Drawable L;
    public Drawable M;
    public final ArrayList<View> N;
    public Rect O;
    public Matrix P;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f1237f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1238g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1239h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1240i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f1241j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f1242k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final p0.c f1243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p0.c f1244m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f1245n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f f1246o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1247p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1248q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1249r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1250s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1251t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1252u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1253v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1254w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1255x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public d f1256y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public List<d> f1257z;

    public class a implements View.OnApplyWindowInsetsListener {
        public a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).K(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    public class b extends l0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f1264d = new Rect();

        public b() {
        }

        @Override // l0.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.a(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View viewM = DrawerLayout.this.m();
            if (viewM == null) {
                return true;
            }
            CharSequence charSequenceP = DrawerLayout.this.p(DrawerLayout.this.q(viewM));
            if (charSequenceP == null) {
                return true;
            }
            text.add(charSequenceP);
            return true;
        }

        @Override // l0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            if (DrawerLayout.f1235d) {
                super.g(view, cVar);
            } else {
                m0.c cVarP = m0.c.P(cVar);
                super.g(view, cVarP);
                cVar.y0(view);
                Object objF = t.F(view);
                if (objF instanceof View) {
                    cVar.q0((View) objF);
                }
                o(cVar, cVarP);
                cVarP.R();
                n(cVar, (ViewGroup) view);
            }
            cVar.b0(DrawerLayout.class.getName());
            cVar.i0(false);
            cVar.j0(false);
            cVar.S(c.a.f3802a);
            cVar.S(c.a.f3803b);
        }

        @Override // l0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f1235d || DrawerLayout.w(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        public final void n(m0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = viewGroup.getChildAt(i3);
                if (DrawerLayout.w(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        public final void o(m0.c cVar, m0.c cVar2) {
            Rect rect = this.f1264d;
            cVar2.m(rect);
            cVar.W(rect);
            cVar2.n(rect);
            cVar.X(rect);
            cVar.B0(cVar2.M());
            cVar.o0(cVar2.u());
            cVar.b0(cVar2.p());
            cVar.f0(cVar2.r());
            cVar.g0(cVar2.E());
            cVar.c0(cVar2.D());
            cVar.i0(cVar2.F());
            cVar.j0(cVar2.G());
            cVar.U(cVar2.A());
            cVar.w0(cVar2.K());
            cVar.m0(cVar2.H());
            cVar.a(cVar2.k());
        }
    }

    public static final class c extends l0.a {
        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            if (DrawerLayout.w(view)) {
                return;
            }
            cVar.q0(null);
        }
    }

    public interface d {
        void a(int i3);

        void b(View view);

        void c(View view, float f3);

        void d(View view);
    }

    public class f extends c.AbstractC0049c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1270a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public p0.c f1271b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f1272c = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        }

        public f(int i3) {
            this.f1270a = i3;
        }

        @Override // p0.c.AbstractC0049c
        public int a(View view, int i3, int i4) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i3, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i3, width));
        }

        @Override // p0.c.AbstractC0049c
        public int b(View view, int i3, int i4) {
            return view.getTop();
        }

        @Override // p0.c.AbstractC0049c
        public int d(View view) {
            if (DrawerLayout.this.z(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // p0.c.AbstractC0049c
        public void f(int i3, int i4) {
            View viewK = (i3 & 1) == 1 ? DrawerLayout.this.k(3) : DrawerLayout.this.k(5);
            if (viewK == null || DrawerLayout.this.o(viewK) != 0) {
                return;
            }
            this.f1271b.b(viewK, i4);
        }

        @Override // p0.c.AbstractC0049c
        public boolean g(int i3) {
            return false;
        }

        @Override // p0.c.AbstractC0049c
        public void h(int i3, int i4) {
            DrawerLayout.this.postDelayed(this.f1272c, 160L);
        }

        @Override // p0.c.AbstractC0049c
        public void i(View view, int i3) {
            ((e) view.getLayoutParams()).f1268c = false;
            n();
        }

        @Override // p0.c.AbstractC0049c
        public void j(int i3) {
            DrawerLayout.this.O(this.f1270a, i3, this.f1271b.v());
        }

        @Override // p0.c.AbstractC0049c
        public void k(View view, int i3, int i4, int i5, int i6) {
            float width = (DrawerLayout.this.c(view, 3) ? i3 + r3 : DrawerLayout.this.getWidth() - i3) / view.getWidth();
            DrawerLayout.this.M(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // p0.c.AbstractC0049c
        public void l(View view, float f3, float f4) {
            int i3;
            float fR = DrawerLayout.this.r(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i3 = (f3 > 0.0f || (f3 == 0.0f && fR > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f3 < 0.0f || (f3 == 0.0f && fR > 0.5f)) {
                    width2 -= width;
                }
                i3 = width2;
            }
            this.f1271b.M(i3, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // p0.c.AbstractC0049c
        public boolean m(View view, int i3) {
            return DrawerLayout.this.z(view) && DrawerLayout.this.c(view, this.f1270a) && DrawerLayout.this.o(view) == 0;
        }

        public final void n() {
            View viewK = DrawerLayout.this.k(this.f1270a == 3 ? 5 : 3);
            if (viewK != null) {
                DrawerLayout.this.d(viewK);
            }
        }

        public void o() {
            View viewK;
            int width;
            int iW = this.f1271b.w();
            boolean z3 = this.f1270a == 3;
            if (z3) {
                viewK = DrawerLayout.this.k(3);
                width = (viewK != null ? -viewK.getWidth() : 0) + iW;
            } else {
                viewK = DrawerLayout.this.k(5);
                width = DrawerLayout.this.getWidth() - iW;
            }
            if (viewK != null) {
                if (((!z3 || viewK.getLeft() >= width) && (z3 || viewK.getLeft() <= width)) || DrawerLayout.this.o(viewK) != 0) {
                    return;
                }
                e eVar = (e) viewK.getLayoutParams();
                this.f1271b.O(viewK, width, viewK.getTop());
                eVar.f1268c = true;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f1272c);
        }

        public void q(p0.c cVar) {
            this.f1271b = cVar;
        }
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        f1235d = i3 >= 19;
        f1236e = i3 >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static String s(int i3) {
        return (i3 & 3) == 3 ? "LEFT" : (i3 & 5) == 5 ? "RIGHT" : Integer.toHexString(i3);
    }

    public static boolean t(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    public static boolean w(View view) {
        return (t.x(view) == 4 || t.x(view) == 2) ? false : true;
    }

    public boolean A(View view) {
        if (z(view)) {
            return ((e) view.getLayoutParams()).f1267b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final boolean B(float f3, float f4, View view) {
        if (this.O == null) {
            this.O = new Rect();
        }
        view.getHitRect(this.O);
        return this.O.contains((int) f3, (int) f4);
    }

    public final boolean C(Drawable drawable, int i3) {
        if (drawable == null || !e0.a.h(drawable)) {
            return false;
        }
        e0.a.m(drawable, i3);
        return true;
    }

    public void D(View view, float f3) {
        float fR = r(view);
        float width = view.getWidth();
        int i3 = ((int) (width * f3)) - ((int) (fR * width));
        if (!c(view, 3)) {
            i3 = -i3;
        }
        view.offsetLeftAndRight(i3);
        M(view, f3);
    }

    public void E(View view) {
        F(view, true);
    }

    public void F(View view, boolean z3) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.f1249r) {
            eVar.f1267b = 1.0f;
            eVar.f1269d = 1;
            N(view, true);
        } else if (z3) {
            eVar.f1269d |= 2;
            if (c(view, 3)) {
                this.f1243l.O(view, 0, view.getTop());
            } else {
                this.f1244m.O(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            D(view, 1.0f);
            O(eVar.f1266a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void G(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.f1257z) == null) {
            return;
        }
        list.remove(dVar);
    }

    public final Drawable H() {
        int iZ = t.z(this);
        if (iZ == 0) {
            Drawable drawable = this.J;
            if (drawable != null) {
                C(drawable, iZ);
                return this.J;
            }
        } else {
            Drawable drawable2 = this.K;
            if (drawable2 != null) {
                C(drawable2, iZ);
                return this.K;
            }
        }
        return this.L;
    }

    public final Drawable I() {
        int iZ = t.z(this);
        if (iZ == 0) {
            Drawable drawable = this.K;
            if (drawable != null) {
                C(drawable, iZ);
                return this.K;
            }
        } else {
            Drawable drawable2 = this.J;
            if (drawable2 != null) {
                C(drawable2, iZ);
                return this.J;
            }
        }
        return this.M;
    }

    public final void J() {
        if (f1236e) {
            return;
        }
        this.D = H();
        this.E = I();
    }

    public void K(Object obj, boolean z3) {
        this.H = obj;
        this.I = z3;
        setWillNotDraw(!z3 && getBackground() == null);
        requestLayout();
    }

    public void L(int i3, int i4) {
        View viewK;
        int iB = l0.d.b(i4, t.z(this));
        if (i4 == 3) {
            this.f1250s = i3;
        } else if (i4 == 5) {
            this.f1251t = i3;
        } else if (i4 == 8388611) {
            this.f1252u = i3;
        } else if (i4 == 8388613) {
            this.f1253v = i3;
        }
        if (i3 != 0) {
            (iB == 3 ? this.f1243l : this.f1244m).a();
        }
        if (i3 != 1) {
            if (i3 == 2 && (viewK = k(iB)) != null) {
                E(viewK);
                return;
            }
            return;
        }
        View viewK2 = k(iB);
        if (viewK2 != null) {
            d(viewK2);
        }
    }

    public void M(View view, float f3) {
        e eVar = (e) view.getLayoutParams();
        if (f3 == eVar.f1267b) {
            return;
        }
        eVar.f1267b = f3;
        j(view, f3);
    }

    public final void N(View view, boolean z3) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((z3 || z(childAt)) && !(z3 && childAt == view)) {
                t.t0(childAt, 4);
            } else {
                t.t0(childAt, 1);
            }
        }
    }

    public void O(int i3, int i4, View view) {
        int iZ = this.f1243l.z();
        int iZ2 = this.f1244m.z();
        int i5 = 2;
        if (iZ == 1 || iZ2 == 1) {
            i5 = 1;
        } else if (iZ != 2 && iZ2 != 2) {
            i5 = 0;
        }
        if (view != null && i4 == 0) {
            float f3 = ((e) view.getLayoutParams()).f1267b;
            if (f3 == 0.0f) {
                h(view);
            } else if (f3 == 1.0f) {
                i(view);
            }
        }
        if (i5 != this.f1247p) {
            this.f1247p = i5;
            List<d> list = this.f1257z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1257z.get(size).a(i5);
                }
            }
        }
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.f1257z == null) {
            this.f1257z = new ArrayList();
        }
        this.f1257z.add(dVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i3, int i4) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (!z(childAt)) {
                this.N.add(childAt);
            } else if (y(childAt)) {
                childAt.addFocusables(arrayList, i3, i4);
                z3 = true;
            }
        }
        if (!z3) {
            int size = this.N.size();
            for (int i6 = 0; i6 < size; i6++) {
                View view = this.N.get(i6);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i3, i4);
                }
            }
        }
        this.N.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i3, layoutParams);
        if (l() != null || z(view)) {
            t.t0(view, 4);
        } else {
            t.t0(view, 1);
        }
        if (f1235d) {
            return;
        }
        t.j0(view, this.f1237f);
    }

    public void b() {
        if (this.f1255x) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.f1255x = true;
    }

    public boolean c(View view, int i3) {
        return (q(view) & i3) == i3;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            fMax = Math.max(fMax, ((e) getChildAt(i3).getLayoutParams()).f1267b);
        }
        this.f1241j = fMax;
        boolean zM = this.f1243l.m(true);
        boolean zM2 = this.f1244m.m(true);
        if (zM || zM2) {
            t.a0(this);
        }
    }

    public void d(View view) {
        e(view, true);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f1241j <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x3 = motionEvent.getX();
        float y3 = motionEvent.getY();
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt = getChildAt(i3);
            if (B(x3, y3, childAt) && !x(childAt) && dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    public final boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent transformedMotionEvent = getTransformedMotionEvent(motionEvent, view);
            boolean zDispatchGenericMotionEvent = view.dispatchGenericMotionEvent(transformedMotionEvent);
            transformedMotionEvent.recycle();
            return zDispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean zDispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return zDispatchGenericMotionEvent2;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j3) {
        int height = getHeight();
        boolean zX = x(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i3 = 0;
        if (zX) {
            int childCount = getChildCount();
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != view && childAt.getVisibility() == 0 && t(childAt) && z(childAt) && childAt.getHeight() >= height) {
                    if (c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i4) {
                            i4 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i4, 0, width, getHeight());
            i3 = i4;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j3);
        canvas.restoreToCount(iSave);
        float f3 = this.f1241j;
        if (f3 > 0.0f && zX) {
            this.f1242k.setColor((this.f1240i & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f3)) << 24));
            canvas.drawRect(i3, 0.0f, width, getHeight(), this.f1242k);
        } else if (this.D != null && c(view, 3)) {
            int intrinsicWidth = this.D.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.f1243l.w(), 1.0f));
            this.D.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.D.setAlpha((int) (fMax * 255.0f));
            this.D.draw(canvas);
        } else if (this.E != null && c(view, 5)) {
            int intrinsicWidth2 = this.E.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.f1244m.w(), 1.0f));
            this.E.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.E.setAlpha((int) (fMax2 * 255.0f));
            this.E.draw(canvas);
        }
        return zDrawChild;
    }

    public void e(View view, boolean z3) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.f1249r) {
            eVar.f1267b = 0.0f;
            eVar.f1269d = 0;
        } else if (z3) {
            eVar.f1269d |= 4;
            if (c(view, 3)) {
                this.f1243l.O(view, -view.getWidth(), view.getTop());
            } else {
                this.f1244m.O(view, getWidth(), view.getTop());
            }
        } else {
            D(view, 0.0f);
            O(eVar.f1266a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void f() {
        g(false);
    }

    public void g(boolean z3) {
        int childCount = getChildCount();
        boolean zO = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            e eVar = (e) childAt.getLayoutParams();
            if (z(childAt) && (!z3 || eVar.f1268c)) {
                zO |= c(childAt, 3) ? this.f1243l.O(childAt, -childAt.getWidth(), childAt.getTop()) : this.f1244m.O(childAt, getWidth(), childAt.getTop());
                eVar.f1268c = false;
            }
        }
        this.f1245n.p();
        this.f1246o.p();
        if (zO) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public float getDrawerElevation() {
        if (f1236e) {
            return this.f1238g;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.C;
    }

    public final MotionEvent getTransformedMotionEvent(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.P == null) {
                this.P = new Matrix();
            }
            matrix.invert(this.P);
            motionEventObtain.transform(this.P);
        }
        return motionEventObtain;
    }

    public void h(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1269d & 1) == 1) {
            eVar.f1269d = 0;
            List<d> list = this.f1257z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1257z.get(size).d(view);
                }
            }
            N(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    public void i(View view) {
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1269d & 1) == 0) {
            eVar.f1269d = 1;
            List<d> list = this.f1257z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1257z.get(size).b(view);
                }
            }
            N(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public void j(View view, float f3) {
        List<d> list = this.f1257z;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f1257z.get(size).c(view, f3);
            }
        }
    }

    public View k(int i3) {
        int iB = l0.d.b(i3, t.z(this)) & 7;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if ((q(childAt) & 7) == iB) {
                return childAt;
            }
        }
        return null;
    }

    public View l() {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((((e) childAt.getLayoutParams()).f1269d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public View m() {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (z(childAt) && A(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public int n(int i3) {
        int iZ = t.z(this);
        if (i3 == 3) {
            int i4 = this.f1250s;
            if (i4 != 3) {
                return i4;
            }
            int i5 = iZ == 0 ? this.f1252u : this.f1253v;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
        if (i3 == 5) {
            int i6 = this.f1251t;
            if (i6 != 3) {
                return i6;
            }
            int i7 = iZ == 0 ? this.f1253v : this.f1252u;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        }
        if (i3 == 8388611) {
            int i8 = this.f1252u;
            if (i8 != 3) {
                return i8;
            }
            int i9 = iZ == 0 ? this.f1250s : this.f1251t;
            if (i9 != 3) {
                return i9;
            }
            return 0;
        }
        if (i3 != 8388613) {
            return 0;
        }
        int i10 = this.f1253v;
        if (i10 != 3) {
            return i10;
        }
        int i11 = iZ == 0 ? this.f1251t : this.f1250s;
        if (i11 != 3) {
            return i11;
        }
        return 0;
    }

    public int o(View view) {
        if (z(view)) {
            return n(((e) view.getLayoutParams()).f1266a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1249r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1249r = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.I || this.C == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.H) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.C.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.C.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getActionMasked()
            p0.c r1 = r6.f1243l
            boolean r1 = r1.N(r7)
            p0.c r2 = r6.f1244m
            boolean r2 = r2.N(r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L31
            r7 = 2
            r4 = 3
            if (r0 == r7) goto L1e
            if (r0 == r4) goto L31
            goto L38
        L1e:
            p0.c r7 = r6.f1243l
            boolean r7 = r7.d(r4)
            if (r7 == 0) goto L38
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.f1245n
            r7.p()
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.f1246o
            r7.p()
            goto L38
        L31:
            r6.g(r2)
            r6.f1254w = r3
            r6.f1255x = r3
        L38:
            r7 = r3
            goto L64
        L3a:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.A = r0
            r6.B = r7
            float r4 = r6.f1241j
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5f
            p0.c r4 = r6.f1243l
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.t(r0, r7)
            if (r7 == 0) goto L5f
            boolean r7 = r6.x(r7)
            if (r7 == 0) goto L5f
            r7 = r2
            goto L60
        L5f:
            r7 = r3
        L60:
            r6.f1254w = r3
            r6.f1255x = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.u()
            if (r7 != 0) goto L74
            boolean r7 = r6.f1255x
            if (r7 == 0) goto L73
            goto L74
        L73:
            r2 = r3
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i3, KeyEvent keyEvent) {
        if (i3 != 4 || !v()) {
            return super.onKeyDown(i3, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i3, KeyEvent keyEvent) {
        if (i3 != 4) {
            return super.onKeyUp(i3, keyEvent);
        }
        View viewM = m();
        if (viewM != null && o(viewM) == 0) {
            f();
        }
        return viewM != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        float f3;
        int i7;
        boolean z4 = true;
        this.f1248q = true;
        int i8 = i5 - i3;
        int childCount = getChildCount();
        int i9 = 0;
        while (i9 < childCount) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (x(childAt)) {
                    int i10 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i10, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i10, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f4 = measuredWidth;
                        i7 = (-measuredWidth) + ((int) (eVar.f1267b * f4));
                        f3 = (measuredWidth + i7) / f4;
                    } else {
                        float f5 = measuredWidth;
                        f3 = (i8 - r11) / f5;
                        i7 = i8 - ((int) (eVar.f1267b * f5));
                    }
                    boolean z5 = f3 != eVar.f1267b ? z4 : false;
                    int i11 = eVar.f1266a & 112;
                    if (i11 == 16) {
                        int i12 = i6 - i4;
                        int i13 = (i12 - measuredHeight) / 2;
                        int i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i13 < i14) {
                            i13 = i14;
                        } else {
                            int i15 = i13 + measuredHeight;
                            int i16 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i15 > i12 - i16) {
                                i13 = (i12 - i16) - measuredHeight;
                            }
                        }
                        childAt.layout(i7, i13, measuredWidth + i7, measuredHeight + i13);
                    } else if (i11 != 80) {
                        int i17 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i7, i17, measuredWidth + i7, measuredHeight + i17);
                    } else {
                        int i18 = i6 - i4;
                        childAt.layout(i7, (i18 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i18 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z5) {
                        M(childAt, f3);
                    }
                    int i19 = eVar.f1267b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i19) {
                        childAt.setVisibility(i19);
                    }
                }
            }
            i9++;
            z4 = true;
        }
        this.f1248q = false;
        this.f1249r = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size = View.MeasureSpec.getSize(i3);
        int size2 = View.MeasureSpec.getSize(i4);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        int i5 = 0;
        boolean z3 = this.H != null && t.w(this);
        int iZ = t.z(this);
        int childCount = getChildCount();
        int i6 = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z3) {
                    int iB = l0.d.b(eVar.f1266a, iZ);
                    if (t.w(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.H;
                            if (iB == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i5, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iB == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i5, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.H;
                        if (iB == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i5, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iB == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i5, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) eVar).leftMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) eVar).topMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) eVar).rightMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (x(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) eVar).leftMargin) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) eVar).topMargin) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                } else {
                    if (!z(childAt)) {
                        throw new IllegalStateException("Child " + childAt + " at index " + i6 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (f1236e) {
                        float fU = t.u(childAt);
                        float f3 = this.f1238g;
                        if (fU != f3) {
                            t.q0(childAt, f3);
                        }
                    }
                    int iQ = q(childAt) & 7;
                    int i7 = iQ == 3 ? 1 : i5;
                    if ((i7 != 0 && z4) || (i7 == 0 && z5)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + s(iQ) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (i7 != 0) {
                        z4 = true;
                    } else {
                        z5 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i3, this.f1239h + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i4, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i6++;
            i5 = 0;
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        View viewK;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        int i3 = savedState.f1258d;
        if (i3 != 0 && (viewK = k(i3)) != null) {
            E(viewK);
        }
        int i4 = savedState.f1259e;
        if (i4 != 3) {
            L(i4, 3);
        }
        int i5 = savedState.f1260f;
        if (i5 != 3) {
            L(i5, 5);
        }
        int i6 = savedState.f1261g;
        if (i6 != 3) {
            L(i6, 8388611);
        }
        int i7 = savedState.f1262h;
        if (i7 != 3) {
            L(i7, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i3) {
        J();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            e eVar = (e) getChildAt(i3).getLayoutParams();
            int i4 = eVar.f1269d;
            boolean z3 = i4 == 1;
            boolean z4 = i4 == 2;
            if (z3 || z4) {
                savedState.f1258d = eVar.f1266a;
                break;
            }
        }
        savedState.f1259e = this.f1250s;
        savedState.f1260f = this.f1251t;
        savedState.f1261g = this.f1252u;
        savedState.f1262h = this.f1253v;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            p0.c r0 = r6.f1243l
            r0.E(r7)
            p0.c r0 = r6.f1244m
            r0.E(r7)
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L66
            if (r0 == r1) goto L22
            r7 = 3
            if (r0 == r7) goto L1a
            goto L76
        L1a:
            r6.g(r1)
            r6.f1254w = r2
            r6.f1255x = r2
            goto L76
        L22:
            float r0 = r7.getX()
            float r7 = r7.getY()
            p0.c r3 = r6.f1243l
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.t(r4, r5)
            if (r3 == 0) goto L5f
            boolean r3 = r6.x(r3)
            if (r3 == 0) goto L5f
            float r3 = r6.A
            float r0 = r0 - r3
            float r3 = r6.B
            float r7 = r7 - r3
            p0.c r3 = r6.f1243l
            int r3 = r3.y()
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r0 = r0 + r7
            int r3 = r3 * r3
            float r7 = (float) r3
            int r7 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r7 >= 0) goto L5f
            android.view.View r7 = r6.l()
            if (r7 == 0) goto L5f
            int r7 = r6.o(r7)
            r0 = 2
            if (r7 != r0) goto L5d
            goto L5f
        L5d:
            r7 = r2
            goto L60
        L5f:
            r7 = r1
        L60:
            r6.g(r7)
            r6.f1254w = r2
            goto L76
        L66:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.A = r0
            r6.B = r7
            r6.f1254w = r2
            r6.f1255x = r2
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public CharSequence p(int i3) {
        int iB = l0.d.b(i3, t.z(this));
        if (iB == 3) {
            return this.F;
        }
        if (iB == 5) {
            return this.G;
        }
        return null;
    }

    public int q(View view) {
        return l0.d.b(((e) view.getLayoutParams()).f1266a, t.z(this));
    }

    public float r(View view) {
        return ((e) view.getLayoutParams()).f1267b;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z3) {
        super.requestDisallowInterceptTouchEvent(z3);
        this.f1254w = z3;
        if (z3) {
            g(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1248q) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f3) {
        this.f1238g = f3;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (z(childAt)) {
                t.q0(childAt, this.f1238g);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.f1256y;
        if (dVar2 != null) {
            G(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.f1256y = dVar;
    }

    public void setDrawerLockMode(int i3) {
        L(i3, 3);
        L(i3, 5);
    }

    public void setScrimColor(int i3) {
        this.f1240i = i3;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.C = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i3) {
        this.C = new ColorDrawable(i3);
        invalidate();
    }

    public final boolean u() {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            if (((e) getChildAt(i3).getLayoutParams()).f1268c) {
                return true;
            }
        }
        return false;
    }

    public final boolean v() {
        return m() != null;
    }

    public boolean x(View view) {
        return ((e) view.getLayoutParams()).f1266a == 0;
    }

    public boolean y(View view) {
        if (z(view)) {
            return (((e) view.getLayoutParams()).f1269d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean z(View view) {
        int iB = l0.d.b(((e) view.getLayoutParams()).f1266a, t.z(view));
        return ((iB & 3) == 0 && (iB & 5) == 0) ? false : true;
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1237f = new c();
        this.f1240i = -1728053248;
        this.f1242k = new Paint();
        this.f1249r = true;
        this.f1250s = 3;
        this.f1251t = 3;
        this.f1252u = 3;
        this.f1253v = 3;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        setDescendantFocusability(262144);
        float f3 = getResources().getDisplayMetrics().density;
        this.f1239h = (int) ((64.0f * f3) + 0.5f);
        float f4 = 400.0f * f3;
        f fVar = new f(3);
        this.f1245n = fVar;
        f fVar2 = new f(5);
        this.f1246o = fVar2;
        p0.c cVarN = p0.c.n(this, 1.0f, fVar);
        this.f1243l = cVarN;
        cVarN.K(1);
        cVarN.L(f4);
        fVar.q(cVarN);
        p0.c cVarN2 = p0.c.n(this, 1.0f, fVar2);
        this.f1244m = cVarN2;
        cVarN2.K(2);
        cVarN2.L(f4);
        fVar2.q(cVarN2);
        setFocusableInTouchMode(true);
        t.t0(this, 1);
        t.j0(this, new b());
        setMotionEventSplittingEnabled(false);
        if (t.w(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a());
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f1233b);
                try {
                    this.C = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.C = null;
            }
        }
        this.f1238g = f3 * 10.0f;
        this.N = new ArrayList<>();
    }

    public void setStatusBarBackground(int i3) {
        this.C = i3 != 0 ? b0.a.d(getContext(), i3) : null;
        invalidate();
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1268c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1269d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1266a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f1234c);
            this.f1266a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i3, int i4) {
            super(i3, i4);
            this.f1266a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f1266a = 0;
            this.f1266a = eVar.f1266a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1266a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1266a = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1258d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1259e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1260f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1261g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1262h;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1258d = 0;
            this.f1258d = parcel.readInt();
            this.f1259e = parcel.readInt();
            this.f1260f = parcel.readInt();
            this.f1261g = parcel.readInt();
            this.f1262h = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f1258d);
            parcel.writeInt(this.f1259e);
            parcel.writeInt(this.f1260f);
            parcel.writeInt(this.f1261g);
            parcel.writeInt(this.f1262h);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f1258d = 0;
        }
    }
}
