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
import b.h.m.c0.c;
import b.h.m.t;
import b.j.a.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f840b = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f841c = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f843e;
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
    public final c f844f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f845g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f846h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f847i;
    public float j;
    public Paint k;
    public final b.j.a.c l;
    public final b.j.a.c m;
    public final f n;
    public final f o;
    public int p;
    public boolean q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    public int v;
    public boolean w;
    public boolean x;
    public d y;
    public List<d> z;

    public class a implements View.OnApplyWindowInsetsListener {
        public a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).K(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    public class b extends b.h.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f854d = new Rect();

        public b() {
        }

        @Override // b.h.m.a
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

        @Override // b.h.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            if (DrawerLayout.f842d) {
                super.g(view, cVar);
            } else {
                b.h.m.c0.c cVarQ = b.h.m.c0.c.Q(cVar);
                super.g(view, cVarQ);
                cVar.A0(view);
                Object objF = t.F(view);
                if (objF instanceof View) {
                    cVar.s0((View) objF);
                }
                o(cVar, cVarQ);
                cVarQ.S();
                n(cVar, (ViewGroup) view);
            }
            cVar.c0(DrawerLayout.class.getName());
            cVar.j0(false);
            cVar.k0(false);
            cVar.T(c.a.f2612a);
            cVar.T(c.a.f2613b);
        }

        @Override // b.h.m.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f842d || DrawerLayout.w(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        public final void n(b.h.m.c0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (DrawerLayout.w(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        public final void o(b.h.m.c0.c cVar, b.h.m.c0.c cVar2) {
            Rect rect = this.f854d;
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

    public static final class c extends b.h.m.a {
        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            if (DrawerLayout.w(view)) {
                return;
            }
            cVar.s0(null);
        }
    }

    public interface d {
        void a(int i2);

        void b(View view);

        void c(View view, float f2);

        void d(View view);
    }

    public class f extends c.AbstractC0050c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f860a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.j.a.c f861b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f862c = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        }

        public f(int i2) {
            this.f860a = i2;
        }

        @Override // b.j.a.c.AbstractC0050c
        public int a(View view, int i2, int i3) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i2, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i2, width));
        }

        @Override // b.j.a.c.AbstractC0050c
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // b.j.a.c.AbstractC0050c
        public int d(View view) {
            if (DrawerLayout.this.z(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // b.j.a.c.AbstractC0050c
        public void f(int i2, int i3) {
            View viewK = (i2 & 1) == 1 ? DrawerLayout.this.k(3) : DrawerLayout.this.k(5);
            if (viewK == null || DrawerLayout.this.o(viewK) != 0) {
                return;
            }
            this.f861b.c(viewK, i3);
        }

        @Override // b.j.a.c.AbstractC0050c
        public boolean g(int i2) {
            return false;
        }

        @Override // b.j.a.c.AbstractC0050c
        public void h(int i2, int i3) {
            DrawerLayout.this.postDelayed(this.f862c, 160L);
        }

        @Override // b.j.a.c.AbstractC0050c
        public void i(View view, int i2) {
            ((e) view.getLayoutParams()).f858c = false;
            n();
        }

        @Override // b.j.a.c.AbstractC0050c
        public void j(int i2) {
            DrawerLayout.this.O(this.f860a, i2, this.f861b.w());
        }

        @Override // b.j.a.c.AbstractC0050c
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = (DrawerLayout.this.c(view, 3) ? i2 + r3 : DrawerLayout.this.getWidth() - i2) / view.getWidth();
            DrawerLayout.this.M(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // b.j.a.c.AbstractC0050c
        public void l(View view, float f2, float f3) {
            int i2;
            float fR = DrawerLayout.this.r(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i2 = (f2 > 0.0f || (f2 == 0.0f && fR > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f2 < 0.0f || (f2 == 0.0f && fR > 0.5f)) {
                    width2 -= width;
                }
                i2 = width2;
            }
            this.f861b.N(i2, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // b.j.a.c.AbstractC0050c
        public boolean m(View view, int i2) {
            return DrawerLayout.this.z(view) && DrawerLayout.this.c(view, this.f860a) && DrawerLayout.this.o(view) == 0;
        }

        public final void n() {
            View viewK = DrawerLayout.this.k(this.f860a == 3 ? 5 : 3);
            if (viewK != null) {
                DrawerLayout.this.d(viewK);
            }
        }

        public void o() {
            View viewK;
            int width;
            int iX = this.f861b.x();
            boolean z = this.f860a == 3;
            if (z) {
                viewK = DrawerLayout.this.k(3);
                width = (viewK != null ? -viewK.getWidth() : 0) + iX;
            } else {
                viewK = DrawerLayout.this.k(5);
                width = DrawerLayout.this.getWidth() - iX;
            }
            if (viewK != null) {
                if (((!z || viewK.getLeft() >= width) && (z || viewK.getLeft() <= width)) || DrawerLayout.this.o(viewK) != 0) {
                    return;
                }
                e eVar = (e) viewK.getLayoutParams();
                this.f861b.P(viewK, width, viewK.getTop());
                eVar.f858c = true;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f862c);
        }

        public void q(b.j.a.c cVar) {
            this.f861b = cVar;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f842d = i2 >= 19;
        f843e = i2 >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static String s(int i2) {
        return (i2 & 3) == 3 ? "LEFT" : (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
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
            return ((e) view.getLayoutParams()).f857b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final boolean B(float f2, float f3, View view) {
        if (this.O == null) {
            this.O = new Rect();
        }
        view.getHitRect(this.O);
        return this.O.contains((int) f2, (int) f3);
    }

    public final boolean C(Drawable drawable, int i2) {
        if (drawable == null || !b.h.f.l.a.h(drawable)) {
            return false;
        }
        b.h.f.l.a.m(drawable, i2);
        return true;
    }

    public void D(View view, float f2) {
        float fR = r(view);
        float width = view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (fR * width));
        if (!c(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        M(view, f2);
    }

    public void E(View view) {
        F(view, true);
    }

    public void F(View view, boolean z) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.r) {
            eVar.f857b = 1.0f;
            eVar.f859d = 1;
            N(view, true);
        } else if (z) {
            eVar.f859d |= 2;
            if (c(view, 3)) {
                this.l.P(view, 0, view.getTop());
            } else {
                this.m.P(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            D(view, 1.0f);
            O(eVar.f856a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void G(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.z) == null) {
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
        if (f843e) {
            return;
        }
        this.D = H();
        this.E = I();
    }

    public void K(Object obj, boolean z) {
        this.H = obj;
        this.I = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    public void L(int i2, int i3) {
        View viewK;
        int iB = b.h.m.d.b(i3, t.z(this));
        if (i3 == 3) {
            this.s = i2;
        } else if (i3 == 5) {
            this.t = i2;
        } else if (i3 == 8388611) {
            this.u = i2;
        } else if (i3 == 8388613) {
            this.v = i2;
        }
        if (i2 != 0) {
            (iB == 3 ? this.l : this.m).b();
        }
        if (i2 != 1) {
            if (i2 == 2 && (viewK = k(iB)) != null) {
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

    public void M(View view, float f2) {
        e eVar = (e) view.getLayoutParams();
        if (f2 == eVar.f857b) {
            return;
        }
        eVar.f857b = f2;
        j(view, f2);
    }

    public final void N(View view, boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((z || z(childAt)) && !(z && childAt == view)) {
                t.u0(childAt, 4);
            } else {
                t.u0(childAt, 1);
            }
        }
    }

    public void O(int i2, int i3, View view) {
        int iA = this.l.A();
        int iA2 = this.m.A();
        int i4 = 2;
        if (iA == 1 || iA2 == 1) {
            i4 = 1;
        } else if (iA != 2 && iA2 != 2) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            float f2 = ((e) view.getLayoutParams()).f857b;
            if (f2 == 0.0f) {
                h(view);
            } else if (f2 == 1.0f) {
                i(view);
            }
        }
        if (i4 != this.p) {
            this.p = i4;
            List<d> list = this.z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.z.get(size).a(i4);
                }
            }
        }
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.z == null) {
            this.z = new ArrayList();
        }
        this.z.add(dVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!z(childAt)) {
                this.N.add(childAt);
            } else if (y(childAt)) {
                childAt.addFocusables(arrayList, i2, i3);
                z = true;
            }
        }
        if (!z) {
            int size = this.N.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = this.N.get(i5);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i2, i3);
                }
            }
        }
        this.N.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (l() != null || z(view)) {
            t.u0(view, 4);
        } else {
            t.u0(view, 1);
        }
        if (f842d) {
            return;
        }
        t.k0(view, this.f844f);
    }

    public void b() {
        if (this.x) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.x = true;
    }

    public boolean c(View view, int i2) {
        return (q(view) & i2) == i2;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            fMax = Math.max(fMax, ((e) getChildAt(i2).getLayoutParams()).f857b);
        }
        this.j = fMax;
        boolean zN = this.l.n(true);
        boolean zN2 = this.m.n(true);
        if (zN || zN2) {
            t.a0(this);
        }
    }

    public void d(View view) {
        e(view, true);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.j <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = getChildAt(i2);
            if (B(x, y, childAt) && !x(childAt) && dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
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
    public boolean drawChild(Canvas canvas, View view, long j) {
        int height = getHeight();
        boolean zX = x(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i2 = 0;
        if (zX) {
            int childCount = getChildCount();
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0 && t(childAt) && z(childAt) && childAt.getHeight() >= height) {
                    if (c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i3) {
                            i3 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, width, getHeight());
            i2 = i3;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        float f2 = this.j;
        if (f2 > 0.0f && zX) {
            this.k.setColor((this.f847i & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f2)) << 24));
            canvas.drawRect(i2, 0.0f, width, getHeight(), this.k);
        } else if (this.D != null && c(view, 3)) {
            int intrinsicWidth = this.D.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.l.x(), 1.0f));
            this.D.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.D.setAlpha((int) (fMax * 255.0f));
            this.D.draw(canvas);
        } else if (this.E != null && c(view, 5)) {
            int intrinsicWidth2 = this.E.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.m.x(), 1.0f));
            this.E.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.E.setAlpha((int) (fMax2 * 255.0f));
            this.E.draw(canvas);
        }
        return zDrawChild;
    }

    public void e(View view, boolean z) {
        if (!z(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.r) {
            eVar.f857b = 0.0f;
            eVar.f859d = 0;
        } else if (z) {
            eVar.f859d |= 4;
            if (c(view, 3)) {
                this.l.P(view, -view.getWidth(), view.getTop());
            } else {
                this.m.P(view, getWidth(), view.getTop());
            }
        } else {
            D(view, 0.0f);
            O(eVar.f856a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void f() {
        g(false);
    }

    public void g(boolean z) {
        int childCount = getChildCount();
        boolean zP = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            e eVar = (e) childAt.getLayoutParams();
            if (z(childAt) && (!z || eVar.f858c)) {
                zP |= c(childAt, 3) ? this.l.P(childAt, -childAt.getWidth(), childAt.getTop()) : this.m.P(childAt, getWidth(), childAt.getTop());
                eVar.f858c = false;
            }
        }
        this.n.p();
        this.o.p();
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
        if (f843e) {
            return this.f845g;
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
        if ((eVar.f859d & 1) == 1) {
            eVar.f859d = 0;
            List<d> list = this.z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.z.get(size).d(view);
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
        if ((eVar.f859d & 1) == 0) {
            eVar.f859d = 1;
            List<d> list = this.z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.z.get(size).b(view);
                }
            }
            N(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public void j(View view, float f2) {
        List<d> list = this.z;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.z.get(size).c(view, f2);
            }
        }
    }

    public View k(int i2) {
        int iB = b.h.m.d.b(i2, t.z(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((q(childAt) & 7) == iB) {
                return childAt;
            }
        }
        return null;
    }

    public View l() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((e) childAt.getLayoutParams()).f859d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public View m() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (z(childAt) && A(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public int n(int i2) {
        int iZ = t.z(this);
        if (i2 == 3) {
            int i3 = this.s;
            if (i3 != 3) {
                return i3;
            }
            int i4 = iZ == 0 ? this.u : this.v;
            if (i4 != 3) {
                return i4;
            }
            return 0;
        }
        if (i2 == 5) {
            int i5 = this.t;
            if (i5 != 3) {
                return i5;
            }
            int i6 = iZ == 0 ? this.v : this.u;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        }
        if (i2 == 8388611) {
            int i7 = this.u;
            if (i7 != 3) {
                return i7;
            }
            int i8 = iZ == 0 ? this.s : this.t;
            if (i8 != 3) {
                return i8;
            }
            return 0;
        }
        if (i2 != 8388613) {
            return 0;
        }
        int i9 = this.v;
        if (i9 != 3) {
            return i9;
        }
        int i10 = iZ == 0 ? this.t : this.s;
        if (i10 != 3) {
            return i10;
        }
        return 0;
    }

    public int o(View view) {
        if (z(view)) {
            return n(((e) view.getLayoutParams()).f856a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r = true;
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
            b.j.a.c r1 = r6.l
            boolean r1 = r1.O(r7)
            b.j.a.c r2 = r6.m
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
            b.j.a.c r7 = r6.l
            boolean r7 = r7.e(r4)
            if (r7 == 0) goto L38
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.n
            r7.p()
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.o
            r7.p()
            goto L38
        L31:
            r6.g(r2)
            r6.w = r3
            r6.x = r3
        L38:
            r7 = r3
            goto L64
        L3a:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.A = r0
            r6.B = r7
            float r4 = r6.j
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5f
            b.j.a.c r4 = r6.l
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
            r6.w = r3
            r6.x = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.u()
            if (r7 != 0) goto L74
            boolean r7 = r6.x
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
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !v()) {
            return super.onKeyDown(i2, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        View viewM = m();
        if (viewM != null && o(viewM) == 0) {
            f();
        }
        return viewM != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        float f2;
        int i6;
        boolean z2 = true;
        this.q = true;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (x(childAt)) {
                    int i9 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i9, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i9, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f3 = measuredWidth;
                        i6 = (-measuredWidth) + ((int) (eVar.f857b * f3));
                        f2 = (measuredWidth + i6) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i7 - r11) / f4;
                        i6 = i7 - ((int) (eVar.f857b * f4));
                    }
                    boolean z3 = f2 != eVar.f857b ? z2 : false;
                    int i10 = eVar.f856a & 112;
                    if (i10 == 16) {
                        int i11 = i5 - i3;
                        int i12 = (i11 - measuredHeight) / 2;
                        int i13 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i12 < i13) {
                            i12 = i13;
                        } else {
                            int i14 = i12 + measuredHeight;
                            int i15 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i14 > i11 - i15) {
                                i12 = (i11 - i15) - measuredHeight;
                            }
                        }
                        childAt.layout(i6, i12, measuredWidth + i6, measuredHeight + i12);
                    } else if (i10 != 80) {
                        int i16 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i6, i16, measuredWidth + i6, measuredHeight + i16);
                    } else {
                        int i17 = i5 - i3;
                        childAt.layout(i6, (i17 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i6, i17 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z3) {
                        M(childAt, f2);
                    }
                    int i18 = eVar.f857b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
            i8++;
            z2 = true;
        }
        this.q = false;
        this.r = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
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
        int i4 = 0;
        boolean z = this.H != null && t.w(this);
        int iZ = t.z(this);
        int childCount = getChildCount();
        int i5 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z) {
                    int iB = b.h.m.d.b(eVar.f856a, iZ);
                    if (t.w(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.H;
                            if (iB == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i4, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iB == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i4, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.H;
                        if (iB == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i4, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iB == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i4, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
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
                        throw new IllegalStateException("Child " + childAt + " at index " + i5 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (f843e) {
                        float fU = t.u(childAt);
                        float f2 = this.f845g;
                        if (fU != f2) {
                            t.r0(childAt, f2);
                        }
                    }
                    int iQ = q(childAt) & 7;
                    int i6 = iQ == 3 ? 1 : i4;
                    if ((i6 != 0 && z2) || (i6 == 0 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + s(iQ) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (i6 != 0) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i2, this.f846h + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i3, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i5++;
            i4 = 0;
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
        super.onRestoreInstanceState(savedState.j());
        int i2 = savedState.f848d;
        if (i2 != 0 && (viewK = k(i2)) != null) {
            E(viewK);
        }
        int i3 = savedState.f849e;
        if (i3 != 3) {
            L(i3, 3);
        }
        int i4 = savedState.f850f;
        if (i4 != 3) {
            L(i4, 5);
        }
        int i5 = savedState.f851g;
        if (i5 != 3) {
            L(i5, 8388611);
        }
        int i6 = savedState.f852h;
        if (i6 != 3) {
            L(i6, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        J();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            e eVar = (e) getChildAt(i2).getLayoutParams();
            int i3 = eVar.f859d;
            boolean z = i3 == 1;
            boolean z2 = i3 == 2;
            if (z || z2) {
                savedState.f848d = eVar.f856a;
                break;
            }
        }
        savedState.f849e = this.s;
        savedState.f850f = this.t;
        savedState.f851g = this.u;
        savedState.f852h = this.v;
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
            b.j.a.c r0 = r6.l
            r0.F(r7)
            b.j.a.c r0 = r6.m
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
            r6.w = r2
            r6.x = r2
            goto L76
        L22:
            float r0 = r7.getX()
            float r7 = r7.getY()
            b.j.a.c r3 = r6.l
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.u(r4, r5)
            if (r3 == 0) goto L5f
            boolean r3 = r6.x(r3)
            if (r3 == 0) goto L5f
            float r3 = r6.A
            float r0 = r0 - r3
            float r3 = r6.B
            float r7 = r7 - r3
            b.j.a.c r3 = r6.l
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
            r6.w = r2
            goto L76
        L66:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.A = r0
            r6.B = r7
            r6.w = r2
            r6.x = r2
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public CharSequence p(int i2) {
        int iB = b.h.m.d.b(i2, t.z(this));
        if (iB == 3) {
            return this.F;
        }
        if (iB == 5) {
            return this.G;
        }
        return null;
    }

    public int q(View view) {
        return b.h.m.d.b(((e) view.getLayoutParams()).f856a, t.z(this));
    }

    public float r(View view) {
        return ((e) view.getLayoutParams()).f857b;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.w = z;
        if (z) {
            g(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.q) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f2) {
        this.f845g = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (z(childAt)) {
                t.r0(childAt, this.f845g);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.y;
        if (dVar2 != null) {
            G(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.y = dVar;
    }

    public void setDrawerLockMode(int i2) {
        L(i2, 3);
        L(i2, 5);
    }

    public void setScrimColor(int i2) {
        this.f847i = i2;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.C = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i2) {
        this.C = new ColorDrawable(i2);
        invalidate();
    }

    public final boolean u() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((e) getChildAt(i2).getLayoutParams()).f858c) {
                return true;
            }
        }
        return false;
    }

    public final boolean v() {
        return m() != null;
    }

    public boolean x(View view) {
        return ((e) view.getLayoutParams()).f856a == 0;
    }

    public boolean y(View view) {
        if (z(view)) {
            return (((e) view.getLayoutParams()).f859d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean z(View view) {
        int iB = b.h.m.d.b(((e) view.getLayoutParams()).f856a, t.z(view));
        return ((iB & 3) == 0 && (iB & 5) == 0) ? false : true;
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f844f = new c();
        this.f847i = -1728053248;
        this.k = new Paint();
        this.r = true;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.v = 3;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.f846h = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        f fVar = new f(3);
        this.n = fVar;
        f fVar2 = new f(5);
        this.o = fVar2;
        b.j.a.c cVarO = b.j.a.c.o(this, 1.0f, fVar);
        this.l = cVarO;
        cVarO.L(1);
        cVarO.M(f3);
        fVar.q(cVarO);
        b.j.a.c cVarO2 = b.j.a.c.o(this, 1.0f, fVar2);
        this.m = cVarO2;
        cVarO2.L(2);
        cVarO2.M(f3);
        fVar2.q(cVarO2);
        setFocusableInTouchMode(true);
        t.u0(this, 1);
        t.k0(this, new b());
        setMotionEventSplittingEnabled(false);
        if (t.w(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a());
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f840b);
                try {
                    this.C = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.C = null;
            }
        }
        this.f845g = f2 * 10.0f;
        this.N = new ArrayList<>();
    }

    public void setStatusBarBackground(int i2) {
        this.C = i2 != 0 ? b.h.e.a.d(getContext(), i2) : null;
        invalidate();
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f856a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f857b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f858c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f859d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f856a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f841c);
            this.f856a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i2, int i3) {
            super(i2, i3);
            this.f856a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f856a = 0;
            this.f856a = eVar.f856a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f856a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f856a = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f850f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f851g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f852h;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f848d = 0;
            this.f848d = parcel.readInt();
            this.f849e = parcel.readInt();
            this.f850f = parcel.readInt();
            this.f851g = parcel.readInt();
            this.f852h = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f848d);
            parcel.writeInt(this.f849e);
            parcel.writeInt(this.f850f);
            parcel.writeInt(this.f851g);
            parcel.writeInt(this.f852h);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f848d = 0;
        }
    }
}
