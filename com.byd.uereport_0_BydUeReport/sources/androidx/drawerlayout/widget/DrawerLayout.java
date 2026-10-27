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
import o0.t;
import p0.c;
import t0.c;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1320d = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f1321e = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f1322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f1323g;
    public d A;
    public List<d> B;
    public float C;
    public float D;
    public Drawable E;
    public Drawable F;
    public Drawable G;
    public CharSequence H;
    public CharSequence I;
    public Object J;
    public boolean K;
    public Drawable L;
    public Drawable M;
    public Drawable N;
    public Drawable O;
    public final ArrayList<View> P;
    public Rect Q;
    public Matrix R;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c f1324h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1325i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1326j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1327k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1328l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint f1329m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t0.c f1330n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final t0.c f1331o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f f1332p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f f1333q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1334r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1335s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1336t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1337u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1338v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1339w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1340x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1341y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1342z;

    public class a implements View.OnApplyWindowInsetsListener {
        public a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).K(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    public class b extends o0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f1349d = new Rect();

        public b() {
        }

        @Override // o0.a
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

        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            if (DrawerLayout.f1322f) {
                super.g(view, cVar);
            } else {
                p0.c cVarQ = p0.c.Q(cVar);
                super.g(view, cVarQ);
                cVar.A0(view);
                Object objG = t.G(view);
                if (objG instanceof View) {
                    cVar.s0((View) objG);
                }
                o(cVar, cVarQ);
                cVarQ.S();
                n(cVar, (ViewGroup) view);
            }
            cVar.c0(DrawerLayout.class.getName());
            cVar.j0(false);
            cVar.k0(false);
            cVar.T(c.a.f6968a);
            cVar.T(c.a.f6969b);
        }

        @Override // o0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f1322f || DrawerLayout.w(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        public final void n(p0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (DrawerLayout.w(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        public final void o(p0.c cVar, p0.c cVar2) {
            Rect rect = this.f1349d;
            cVar2.m(rect);
            cVar.X(rect);
            cVar2.n(rect);
            cVar.Y(rect);
            cVar.D0(cVar2.N());
            cVar.q0(cVar2.v());
            cVar.c0(cVar2.p());
            cVar.g0(cVar2.r());
            cVar.h0(cVar2.F());
            cVar.d0(cVar2.E());
            cVar.j0(cVar2.G());
            cVar.k0(cVar2.H());
            cVar.V(cVar2.B());
            cVar.y0(cVar2.L());
            cVar.n0(cVar2.I());
            cVar.a(cVar2.k());
        }
    }

    public static final class c extends o0.a {
        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            if (DrawerLayout.w(view)) {
                return;
            }
            cVar.s0(null);
        }
    }

    public interface d {
        void a(int i8);

        void b(View view, float f8);

        void c(View view);

        void d(View view);
    }

    public class f extends c.AbstractC0123c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1355a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public t0.c f1356b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f1357c = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        }

        public f(int i8) {
            this.f1355a = i8;
        }

        @Override // t0.c.AbstractC0123c
        public int a(View view, int i8, int i9) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i8, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i8, width));
        }

        @Override // t0.c.AbstractC0123c
        public int b(View view, int i8, int i9) {
            return view.getTop();
        }

        @Override // t0.c.AbstractC0123c
        public int d(View view) {
            if (DrawerLayout.this.z(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // t0.c.AbstractC0123c
        public void f(int i8, int i9) {
            View viewK = (i8 & 1) == 1 ? DrawerLayout.this.k(3) : DrawerLayout.this.k(5);
            if (viewK == null || DrawerLayout.this.o(viewK) != 0) {
                return;
            }
            this.f1356b.c(viewK, i9);
        }

        @Override // t0.c.AbstractC0123c
        public boolean g(int i8) {
            return false;
        }

        @Override // t0.c.AbstractC0123c
        public void h(int i8, int i9) {
            DrawerLayout.this.postDelayed(this.f1357c, 160L);
        }

        @Override // t0.c.AbstractC0123c
        public void i(View view, int i8) {
            ((e) view.getLayoutParams()).f1353c = false;
            n();
        }

        @Override // t0.c.AbstractC0123c
        public void j(int i8) {
            DrawerLayout.this.O(this.f1355a, i8, this.f1356b.w());
        }

        @Override // t0.c.AbstractC0123c
        public void k(View view, int i8, int i9, int i10, int i11) {
            float width = (DrawerLayout.this.c(view, 3) ? i8 + r3 : DrawerLayout.this.getWidth() - i8) / view.getWidth();
            DrawerLayout.this.M(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // t0.c.AbstractC0123c
        public void l(View view, float f8, float f9) {
            int i8;
            float fR = DrawerLayout.this.r(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i8 = (f8 > 0.0f || (f8 == 0.0f && fR > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f8 < 0.0f || (f8 == 0.0f && fR > 0.5f)) {
                    width2 -= width;
                }
                i8 = width2;
            }
            this.f1356b.N(i8, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // t0.c.AbstractC0123c
        public boolean m(View view, int i8) {
            return DrawerLayout.this.z(view) && DrawerLayout.this.c(view, this.f1355a) && DrawerLayout.this.o(view) == 0;
        }

        public final void n() {
            View viewK = DrawerLayout.this.k(this.f1355a == 3 ? 5 : 3);
            if (viewK != null) {
                DrawerLayout.this.d(viewK);
            }
        }

        public void o() {
            View viewK;
            int width;
            int iX = this.f1356b.x();
            boolean z7 = this.f1355a == 3;
            if (z7) {
                viewK = DrawerLayout.this.k(3);
                width = (viewK != null ? -viewK.getWidth() : 0) + iX;
            } else {
                viewK = DrawerLayout.this.k(5);
                width = DrawerLayout.this.getWidth() - iX;
            }
            if (viewK != null) {
                if (((!z7 || viewK.getLeft() >= width) && (z7 || viewK.getLeft() <= width)) || DrawerLayout.this.o(viewK) != 0) {
                    return;
                }
                e eVar = (e) viewK.getLayoutParams();
                this.f1356b.P(viewK, width, viewK.getTop());
                eVar.f1353c = true;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f1357c);
        }

        public void q(t0.c cVar) {
            this.f1356b = cVar;
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        f1322f = i8 >= 19;
        f1323g = i8 >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static String s(int i8) {
        return (i8 & 3) == 3 ? "LEFT" : (i8 & 5) == 5 ? "RIGHT" : Integer.toHexString(i8);
    }

    public static boolean t(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    public static boolean w(View view) {
        return (t.y(view) == 4 || t.y(view) == 2) ? false : true;
    }

    public boolean A(View view) {
        if (z(view)) {
            return ((e) view.getLayoutParams()).f1352b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final boolean B(float f8, float f9, View view) {
        if (this.Q == null) {
            this.Q = new Rect();
        }
        view.getHitRect(this.Q);
        return this.Q.contains((int) f8, (int) f9);
    }

    public final boolean C(Drawable drawable, int i8) {
        if (drawable == null || !h0.a.h(drawable)) {
            return false;
        }
        h0.a.m(drawable, i8);
        return true;
    }

    public void D(View view, float f8) {
        float fR = r(view);
        float width = view.getWidth();
        int i8 = ((int) (width * f8)) - ((int) (fR * width));
        if (!c(view, 3)) {
            i8 = -i8;
        }
        view.offsetLeftAndRight(i8);
        M(view, f8);
    }

    public void E(View view) {
        F(view, true);
    }

    public void F(View view, boolean z7) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.f1336t) {
            eVar.f1352b = 1.0f;
            eVar.f1354d = 1;
            N(view, true);
        } else if (z7) {
            eVar.f1354d |= 2;
            if (c(view, 3)) {
                this.f1330n.P(view, 0, view.getTop());
            } else {
                this.f1331o.P(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            D(view, 1.0f);
            O(eVar.f1351a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void G(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.B) == null) {
            return;
        }
        list.remove(dVar);
    }

    public final Drawable H() {
        int iA = t.A(this);
        if (iA == 0) {
            Drawable drawable = this.L;
            if (drawable != null) {
                C(drawable, iA);
                return this.L;
            }
        } else {
            Drawable drawable2 = this.M;
            if (drawable2 != null) {
                C(drawable2, iA);
                return this.M;
            }
        }
        return this.N;
    }

    public final Drawable I() {
        int iA = t.A(this);
        if (iA == 0) {
            Drawable drawable = this.M;
            if (drawable != null) {
                C(drawable, iA);
                return this.M;
            }
        } else {
            Drawable drawable2 = this.L;
            if (drawable2 != null) {
                C(drawable2, iA);
                return this.L;
            }
        }
        return this.O;
    }

    public final void J() {
        if (f1323g) {
            return;
        }
        this.F = H();
        this.G = I();
    }

    public void K(Object obj, boolean z7) {
        this.J = obj;
        this.K = z7;
        setWillNotDraw(!z7 && getBackground() == null);
        requestLayout();
    }

    public void L(int i8, int i9) {
        View viewK;
        int iB = o0.d.b(i9, t.A(this));
        if (i9 == 3) {
            this.f1337u = i8;
        } else if (i9 == 5) {
            this.f1338v = i8;
        } else if (i9 == 8388611) {
            this.f1339w = i8;
        } else if (i9 == 8388613) {
            this.f1340x = i8;
        }
        if (i8 != 0) {
            (iB == 3 ? this.f1330n : this.f1331o).b();
        }
        if (i8 != 1) {
            if (i8 == 2 && (viewK = k(iB)) != null) {
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

    public void M(View view, float f8) {
        e eVar = (e) view.getLayoutParams();
        if (f8 == eVar.f1352b) {
            return;
        }
        eVar.f1352b = f8;
        j(view, f8);
    }

    public final void N(View view, boolean z7) {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if ((z7 || z(childAt)) && !(z7 && childAt == view)) {
                t.v0(childAt, 4);
            } else {
                t.v0(childAt, 1);
            }
        }
    }

    public void O(int i8, int i9, View view) {
        int iA = this.f1330n.A();
        int iA2 = this.f1331o.A();
        int i10 = 2;
        if (iA == 1 || iA2 == 1) {
            i10 = 1;
        } else if (iA != 2 && iA2 != 2) {
            i10 = 0;
        }
        if (view != null && i9 == 0) {
            float f8 = ((e) view.getLayoutParams()).f1352b;
            if (f8 == 0.0f) {
                h(view);
            } else if (f8 == 1.0f) {
                i(view);
            }
        }
        if (i10 != this.f1334r) {
            this.f1334r = i10;
            List<d> list = this.B;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.B.get(size).a(i10);
                }
            }
        }
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.B == null) {
            this.B = new ArrayList();
        }
        this.B.add(dVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i8, int i9) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z7 = false;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (!z(childAt)) {
                this.P.add(childAt);
            } else if (y(childAt)) {
                childAt.addFocusables(arrayList, i8, i9);
                z7 = true;
            }
        }
        if (!z7) {
            int size = this.P.size();
            for (int i11 = 0; i11 < size; i11++) {
                View view = this.P.get(i11);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i8, i9);
                }
            }
        }
        this.P.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i8, layoutParams);
        if (l() != null || z(view)) {
            t.v0(view, 4);
        } else {
            t.v0(view, 1);
        }
        if (f1322f) {
            return;
        }
        t.l0(view, this.f1324h);
    }

    public void b() {
        if (this.f1342z) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            getChildAt(i8).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.f1342z = true;
    }

    public boolean c(View view, int i8) {
        return (q(view) & i8) == i8;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i8 = 0; i8 < childCount; i8++) {
            fMax = Math.max(fMax, ((e) getChildAt(i8).getLayoutParams()).f1352b);
        }
        this.f1328l = fMax;
        boolean zN = this.f1330n.n(true);
        boolean zN2 = this.f1331o.n(true);
        if (zN || zN2) {
            t.b0(this);
        }
    }

    public void d(View view) {
        e(view, true);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f1328l <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x7 = motionEvent.getX();
        float y7 = motionEvent.getY();
        for (int i8 = childCount - 1; i8 >= 0; i8--) {
            View childAt = getChildAt(i8);
            if (B(x7, y7, childAt) && !x(childAt) && dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
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
    public boolean drawChild(Canvas canvas, View view, long j8) {
        int height = getHeight();
        boolean zX = x(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i8 = 0;
        if (zX) {
            int childCount = getChildCount();
            int i9 = 0;
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                if (childAt != view && childAt.getVisibility() == 0 && t(childAt) && z(childAt) && childAt.getHeight() >= height) {
                    if (c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i9) {
                            i9 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i9, 0, width, getHeight());
            i8 = i9;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j8);
        canvas.restoreToCount(iSave);
        float f8 = this.f1328l;
        if (f8 > 0.0f && zX) {
            this.f1329m.setColor((this.f1327k & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f8)) << 24));
            canvas.drawRect(i8, 0.0f, width, getHeight(), this.f1329m);
        } else if (this.F != null && c(view, 3)) {
            int intrinsicWidth = this.F.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.f1330n.x(), 1.0f));
            this.F.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.F.setAlpha((int) (fMax * 255.0f));
            this.F.draw(canvas);
        } else if (this.G != null && c(view, 5)) {
            int intrinsicWidth2 = this.G.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.f1331o.x(), 1.0f));
            this.G.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.G.setAlpha((int) (fMax2 * 255.0f));
            this.G.draw(canvas);
        }
        return zDrawChild;
    }

    public void e(View view, boolean z7) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.f1336t) {
            eVar.f1352b = 0.0f;
            eVar.f1354d = 0;
        } else if (z7) {
            eVar.f1354d |= 4;
            if (c(view, 3)) {
                this.f1330n.P(view, -view.getWidth(), view.getTop());
            } else {
                this.f1331o.P(view, getWidth(), view.getTop());
            }
        } else {
            D(view, 0.0f);
            O(eVar.f1351a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void f() {
        g(false);
    }

    public void g(boolean z7) {
        int childCount = getChildCount();
        boolean zP = false;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            e eVar = (e) childAt.getLayoutParams();
            if (z(childAt) && (!z7 || eVar.f1353c)) {
                zP |= c(childAt, 3) ? this.f1330n.P(childAt, -childAt.getWidth(), childAt.getTop()) : this.f1331o.P(childAt, getWidth(), childAt.getTop());
                eVar.f1353c = false;
            }
        }
        this.f1332p.p();
        this.f1333q.p();
        if (zP) {
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
        if (f1323g) {
            return this.f1325i;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.E;
    }

    public final MotionEvent getTransformedMotionEvent(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.R == null) {
                this.R = new Matrix();
            }
            matrix.invert(this.R);
            motionEventObtain.transform(this.R);
        }
        return motionEventObtain;
    }

    public void h(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1354d & 1) == 1) {
            eVar.f1354d = 0;
            List<d> list = this.B;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.B.get(size).d(view);
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
        if ((eVar.f1354d & 1) == 0) {
            eVar.f1354d = 1;
            List<d> list = this.B;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.B.get(size).c(view);
                }
            }
            N(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public void j(View view, float f8) {
        List<d> list = this.B;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.B.get(size).b(view, f8);
            }
        }
    }

    public View k(int i8) {
        int iB = o0.d.b(i8, t.A(this)) & 7;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if ((q(childAt) & 7) == iB) {
                return childAt;
            }
        }
        return null;
    }

    public View l() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if ((((e) childAt.getLayoutParams()).f1354d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public View m() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (z(childAt) && A(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public int n(int i8) {
        int iA = t.A(this);
        if (i8 == 3) {
            int i9 = this.f1337u;
            if (i9 != 3) {
                return i9;
            }
            int i10 = iA == 0 ? this.f1339w : this.f1340x;
            if (i10 != 3) {
                return i10;
            }
            return 0;
        }
        if (i8 == 5) {
            int i11 = this.f1338v;
            if (i11 != 3) {
                return i11;
            }
            int i12 = iA == 0 ? this.f1340x : this.f1339w;
            if (i12 != 3) {
                return i12;
            }
            return 0;
        }
        if (i8 == 8388611) {
            int i13 = this.f1339w;
            if (i13 != 3) {
                return i13;
            }
            int i14 = iA == 0 ? this.f1337u : this.f1338v;
            if (i14 != 3) {
                return i14;
            }
            return 0;
        }
        if (i8 != 8388613) {
            return 0;
        }
        int i15 = this.f1340x;
        if (i15 != 3) {
            return i15;
        }
        int i16 = iA == 0 ? this.f1338v : this.f1337u;
        if (i16 != 3) {
            return i16;
        }
        return 0;
    }

    public int o(View view) {
        if (z(view)) {
            return n(((e) view.getLayoutParams()).f1351a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1336t = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1336t = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.K || this.E == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.J) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.E.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.E.draw(canvas);
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
            t0.c r1 = r6.f1330n
            boolean r1 = r1.O(r7)
            t0.c r2 = r6.f1331o
            boolean r2 = r2.O(r7)
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
            t0.c r7 = r6.f1330n
            boolean r7 = r7.e(r4)
            if (r7 == 0) goto L38
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.f1332p
            r7.p()
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.f1333q
            r7.p()
            goto L38
        L31:
            r6.g(r2)
            r6.f1341y = r3
            r6.f1342z = r3
        L38:
            r7 = r3
            goto L64
        L3a:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.C = r0
            r6.D = r7
            float r4 = r6.f1328l
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5f
            t0.c r4 = r6.f1330n
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.u(r0, r7)
            if (r7 == 0) goto L5f
            boolean r7 = r6.x(r7)
            if (r7 == 0) goto L5f
            r7 = r2
            goto L60
        L5f:
            r7 = r3
        L60:
            r6.f1341y = r3
            r6.f1342z = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.u()
            if (r7 != 0) goto L74
            boolean r7 = r6.f1342z
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
    public boolean onKeyDown(int i8, KeyEvent keyEvent) {
        if (i8 != 4 || !v()) {
            return super.onKeyDown(i8, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i8, KeyEvent keyEvent) {
        if (i8 != 4) {
            return super.onKeyUp(i8, keyEvent);
        }
        View viewM = m();
        if (viewM != null && o(viewM) == 0) {
            f();
        }
        return viewM != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        float f8;
        int i12;
        boolean z8 = true;
        this.f1335s = true;
        int i13 = i10 - i8;
        int childCount = getChildCount();
        int i14 = 0;
        while (i14 < childCount) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (x(childAt)) {
                    int i15 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i15, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i15, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f9 = measuredWidth;
                        i12 = (-measuredWidth) + ((int) (eVar.f1352b * f9));
                        f8 = (measuredWidth + i12) / f9;
                    } else {
                        float f10 = measuredWidth;
                        f8 = (i13 - r11) / f10;
                        i12 = i13 - ((int) (eVar.f1352b * f10));
                    }
                    boolean z9 = f8 != eVar.f1352b ? z8 : false;
                    int i16 = eVar.f1351a & 112;
                    if (i16 == 16) {
                        int i17 = i11 - i9;
                        int i18 = (i17 - measuredHeight) / 2;
                        int i19 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i18 < i19) {
                            i18 = i19;
                        } else {
                            int i20 = i18 + measuredHeight;
                            int i21 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i20 > i17 - i21) {
                                i18 = (i17 - i21) - measuredHeight;
                            }
                        }
                        childAt.layout(i12, i18, measuredWidth + i12, measuredHeight + i18);
                    } else if (i16 != 80) {
                        int i22 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i12, i22, measuredWidth + i12, measuredHeight + i22);
                    } else {
                        int i23 = i11 - i9;
                        childAt.layout(i12, (i23 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i12, i23 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z9) {
                        M(childAt, f8);
                    }
                    int i24 = eVar.f1352b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i24) {
                        childAt.setVisibility(i24);
                    }
                }
            }
            i14++;
            z8 = true;
        }
        this.f1335s = false;
        this.f1336t = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int mode2 = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i8);
        int size2 = View.MeasureSpec.getSize(i9);
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
        int i10 = 0;
        boolean z7 = this.J != null && t.x(this);
        int iA = t.A(this);
        int childCount = getChildCount();
        int i11 = 0;
        boolean z8 = false;
        boolean z9 = false;
        while (i11 < childCount) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z7) {
                    int iB = o0.d.b(eVar.f1351a, iA);
                    if (t.x(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.J;
                            if (iB == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i10, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iB == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i10, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.J;
                        if (iB == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i10, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iB == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i10, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
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
                        throw new IllegalStateException("Child " + childAt + " at index " + i11 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (f1323g) {
                        float fV = t.v(childAt);
                        float f8 = this.f1325i;
                        if (fV != f8) {
                            t.s0(childAt, f8);
                        }
                    }
                    int iQ = q(childAt) & 7;
                    int i12 = iQ == 3 ? 1 : i10;
                    if ((i12 != 0 && z8) || (i12 == 0 && z9)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + s(iQ) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (i12 != 0) {
                        z8 = true;
                    } else {
                        z9 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i8, this.f1326j + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i9, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i11++;
            i10 = 0;
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
        int i8 = savedState.f1343f;
        if (i8 != 0 && (viewK = k(i8)) != null) {
            E(viewK);
        }
        int i9 = savedState.f1344g;
        if (i9 != 3) {
            L(i9, 3);
        }
        int i10 = savedState.f1345h;
        if (i10 != 3) {
            L(i10, 5);
        }
        int i11 = savedState.f1346i;
        if (i11 != 3) {
            L(i11, 8388611);
        }
        int i12 = savedState.f1347j;
        if (i12 != 3) {
            L(i12, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i8) {
        J();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            e eVar = (e) getChildAt(i8).getLayoutParams();
            int i9 = eVar.f1354d;
            boolean z7 = i9 == 1;
            boolean z8 = i9 == 2;
            if (z7 || z8) {
                savedState.f1343f = eVar.f1351a;
                break;
            }
        }
        savedState.f1344g = this.f1337u;
        savedState.f1345h = this.f1338v;
        savedState.f1346i = this.f1339w;
        savedState.f1347j = this.f1340x;
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
            t0.c r0 = r6.f1330n
            r0.F(r7)
            t0.c r0 = r6.f1331o
            r0.F(r7)
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
            r6.f1341y = r2
            r6.f1342z = r2
            goto L76
        L22:
            float r0 = r7.getX()
            float r7 = r7.getY()
            t0.c r3 = r6.f1330n
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.u(r4, r5)
            if (r3 == 0) goto L5f
            boolean r3 = r6.x(r3)
            if (r3 == 0) goto L5f
            float r3 = r6.C
            float r0 = r0 - r3
            float r3 = r6.D
            float r7 = r7 - r3
            t0.c r3 = r6.f1330n
            int r3 = r3.z()
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
            r6.f1341y = r2
            goto L76
        L66:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.C = r0
            r6.D = r7
            r6.f1341y = r2
            r6.f1342z = r2
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public CharSequence p(int i8) {
        int iB = o0.d.b(i8, t.A(this));
        if (iB == 3) {
            return this.H;
        }
        if (iB == 5) {
            return this.I;
        }
        return null;
    }

    public int q(View view) {
        return o0.d.b(((e) view.getLayoutParams()).f1351a, t.A(this));
    }

    public float r(View view) {
        return ((e) view.getLayoutParams()).f1352b;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        super.requestDisallowInterceptTouchEvent(z7);
        this.f1341y = z7;
        if (z7) {
            g(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1335s) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f8) {
        this.f1325i = f8;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (z(childAt)) {
                t.s0(childAt, this.f1325i);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.A;
        if (dVar2 != null) {
            G(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.A = dVar;
    }

    public void setDrawerLockMode(int i8) {
        L(i8, 3);
        L(i8, 5);
    }

    public void setScrimColor(int i8) {
        this.f1327k = i8;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.E = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i8) {
        this.E = new ColorDrawable(i8);
        invalidate();
    }

    public final boolean u() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            if (((e) getChildAt(i8).getLayoutParams()).f1353c) {
                return true;
            }
        }
        return false;
    }

    public final boolean v() {
        return m() != null;
    }

    public boolean x(View view) {
        return ((e) view.getLayoutParams()).f1351a == 0;
    }

    public boolean y(View view) {
        if (z(view)) {
            return (((e) view.getLayoutParams()).f1354d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean z(View view) {
        int iB = o0.d.b(((e) view.getLayoutParams()).f1351a, t.A(view));
        return ((iB & 3) == 0 && (iB & 5) == 0) ? false : true;
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1324h = new c();
        this.f1327k = -1728053248;
        this.f1329m = new Paint();
        this.f1336t = true;
        this.f1337u = 3;
        this.f1338v = 3;
        this.f1339w = 3;
        this.f1340x = 3;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
        float f8 = getResources().getDisplayMetrics().density;
        this.f1326j = (int) ((64.0f * f8) + 0.5f);
        float f9 = 400.0f * f8;
        f fVar = new f(3);
        this.f1332p = fVar;
        f fVar2 = new f(5);
        this.f1333q = fVar2;
        t0.c cVarO = t0.c.o(this, 1.0f, fVar);
        this.f1330n = cVarO;
        cVarO.L(1);
        cVarO.M(f9);
        fVar.q(cVarO);
        t0.c cVarO2 = t0.c.o(this, 1.0f, fVar2);
        this.f1331o = cVarO2;
        cVarO2.L(2);
        cVarO2.M(f9);
        fVar2.q(cVarO2);
        setFocusableInTouchMode(true);
        t.v0(this, 1);
        t.l0(this, new b());
        setMotionEventSplittingEnabled(false);
        if (t.x(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a());
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f1320d);
                try {
                    this.E = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.E = null;
            }
        }
        this.f1325i = f8 * 10.0f;
        this.P = new ArrayList<>();
    }

    public void setStatusBarBackground(int i8) {
        this.E = i8 != 0 ? e0.a.d(getContext(), i8) : null;
        invalidate();
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1351a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1352b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1353c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1354d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1351a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f1321e);
            this.f1351a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i8, int i9) {
            super(i8, i9);
            this.f1351a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f1351a = 0;
            this.f1351a = eVar.f1351a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1351a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1351a = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1343f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1344g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1345h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1346i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1347j;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1343f = 0;
            this.f1343f = parcel.readInt();
            this.f1344g = parcel.readInt();
            this.f1345h = parcel.readInt();
            this.f1346i = parcel.readInt();
            this.f1347j = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f1343f);
            parcel.writeInt(this.f1344g);
            parcel.writeInt(this.f1345h);
            parcel.writeInt(this.f1346i);
            parcel.writeInt(this.f1347j);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f1343f = 0;
        }
    }
}
