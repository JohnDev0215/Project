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
import b.g.k.c0.c;
import b.g.k.t;
import b.i.a.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f959b = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f960c = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final boolean f961d;
    private static final boolean e;
    private float A;
    private float B;
    private Drawable C;
    private Drawable D;
    private Drawable E;
    private CharSequence F;
    private CharSequence G;
    private Object H;
    private boolean I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private Drawable M;
    private final ArrayList<View> N;
    private Rect O;
    private Matrix P;
    private final c f;
    private float g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private final b.i.a.c l;
    private final b.i.a.c m;
    private final f n;
    private final f o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private d y;
    private List<d> z;

    class a implements View.OnApplyWindowInsetsListener {
        a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).K(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    class b extends b.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Rect f964d = new Rect();

        b() {
        }

        private void n(b.g.k.c0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.w(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        private void o(b.g.k.c0.c cVar, b.g.k.c0.c cVar2) {
            Rect rect = this.f964d;
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

        @Override // b.g.k.a
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

        @Override // b.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            if (DrawerLayout.f961d) {
                super.g(view, cVar);
            } else {
                b.g.k.c0.c cVarP = b.g.k.c0.c.P(cVar);
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
            cVar.S(c.a.f1734a);
            cVar.S(c.a.f1735b);
        }

        @Override // b.g.k.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f961d || DrawerLayout.w(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    static final class c extends b.g.k.a {
        c() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            if (DrawerLayout.w(view)) {
                return;
            }
            cVar.q0(null);
        }
    }

    public interface d {
        void a(int i);

        void b(View view);

        void c(View view, float f);

        void d(View view);
    }

    private class f extends c.AbstractC0045c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private b.i.a.c f970b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f971c = new a();

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        }

        f(int i) {
            this.f969a = i;
        }

        private void n() {
            View viewK = DrawerLayout.this.k(this.f969a == 3 ? 5 : 3);
            if (viewK != null) {
                DrawerLayout.this.d(viewK);
            }
        }

        @Override // b.i.a.c.AbstractC0045c
        public int a(View view, int i, int i2) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        @Override // b.i.a.c.AbstractC0045c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // b.i.a.c.AbstractC0045c
        public int d(View view) {
            if (DrawerLayout.this.z(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // b.i.a.c.AbstractC0045c
        public void f(int i, int i2) {
            View viewK = (i & 1) == 1 ? DrawerLayout.this.k(3) : DrawerLayout.this.k(5);
            if (viewK == null || DrawerLayout.this.o(viewK) != 0) {
                return;
            }
            this.f970b.b(viewK, i2);
        }

        @Override // b.i.a.c.AbstractC0045c
        public boolean g(int i) {
            return false;
        }

        @Override // b.i.a.c.AbstractC0045c
        public void h(int i, int i2) {
            DrawerLayout.this.postDelayed(this.f971c, 160L);
        }

        @Override // b.i.a.c.AbstractC0045c
        public void i(View view, int i) {
            ((e) view.getLayoutParams()).f967c = false;
            n();
        }

        @Override // b.i.a.c.AbstractC0045c
        public void j(int i) {
            DrawerLayout.this.O(this.f969a, i, this.f970b.v());
        }

        @Override // b.i.a.c.AbstractC0045c
        public void k(View view, int i, int i2, int i3, int i4) {
            float width = (DrawerLayout.this.c(view, 3) ? i + r3 : DrawerLayout.this.getWidth() - i) / view.getWidth();
            DrawerLayout.this.M(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // b.i.a.c.AbstractC0045c
        public void l(View view, float f, float f2) {
            int i;
            float fR = DrawerLayout.this.r(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && fR > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && fR > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.f970b.M(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // b.i.a.c.AbstractC0045c
        public boolean m(View view, int i) {
            return DrawerLayout.this.z(view) && DrawerLayout.this.c(view, this.f969a) && DrawerLayout.this.o(view) == 0;
        }

        void o() {
            View viewK;
            int width;
            int iW = this.f970b.w();
            boolean z = this.f969a == 3;
            if (z) {
                viewK = DrawerLayout.this.k(3);
                width = (viewK != null ? -viewK.getWidth() : 0) + iW;
            } else {
                viewK = DrawerLayout.this.k(5);
                width = DrawerLayout.this.getWidth() - iW;
            }
            if (viewK != null) {
                if (((!z || viewK.getLeft() >= width) && (z || viewK.getLeft() <= width)) || DrawerLayout.this.o(viewK) != 0) {
                    return;
                }
                e eVar = (e) viewK.getLayoutParams();
                this.f970b.O(viewK, width, viewK.getTop());
                eVar.f967c = true;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f971c);
        }

        public void q(b.i.a.c cVar) {
            this.f970b = cVar;
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        f961d = i >= 19;
        e = i >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    private boolean B(float f2, float f3, View view) {
        if (this.O == null) {
            this.O = new Rect();
        }
        view.getHitRect(this.O);
        return this.O.contains((int) f2, (int) f3);
    }

    private boolean C(Drawable drawable, int i) {
        if (drawable == null || !androidx.core.graphics.drawable.a.h(drawable)) {
            return false;
        }
        androidx.core.graphics.drawable.a.m(drawable, i);
        return true;
    }

    private Drawable H() {
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

    private Drawable I() {
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

    private void J() {
        if (e) {
            return;
        }
        this.D = H();
        this.E = I();
    }

    private void N(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || z(childAt)) && !(z && childAt == view)) {
                t.t0(childAt, 4);
            } else {
                t.t0(childAt, 1);
            }
        }
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent, View view) {
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

    private MotionEvent getTransformedMotionEvent(MotionEvent motionEvent, View view) {
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

    static String s(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    private static boolean t(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    private boolean u() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((e) getChildAt(i).getLayoutParams()).f967c) {
                return true;
            }
        }
        return false;
    }

    private boolean v() {
        return m() != null;
    }

    static boolean w(View view) {
        return (t.x(view) == 4 || t.x(view) == 2) ? false : true;
    }

    public boolean A(View view) {
        if (z(view)) {
            return ((e) view.getLayoutParams()).f966b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    void D(View view, float f2) {
        float fR = r(view);
        float width = view.getWidth();
        int i = ((int) (width * f2)) - ((int) (fR * width));
        if (!c(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
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
            eVar.f966b = 1.0f;
            eVar.f968d = 1;
            N(view, true);
        } else if (z) {
            eVar.f968d |= 2;
            if (c(view, 3)) {
                this.l.O(view, 0, view.getTop());
            } else {
                this.m.O(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            D(view, 1.0f);
            O(eVar.f965a, 0, view);
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

    public void K(Object obj, boolean z) {
        this.H = obj;
        this.I = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    public void L(int i, int i2) {
        View viewK;
        int iB = b.g.k.d.b(i2, t.z(this));
        if (i2 == 3) {
            this.s = i;
        } else if (i2 == 5) {
            this.t = i;
        } else if (i2 == 8388611) {
            this.u = i;
        } else if (i2 == 8388613) {
            this.v = i;
        }
        if (i != 0) {
            (iB == 3 ? this.l : this.m).a();
        }
        if (i != 1) {
            if (i == 2 && (viewK = k(iB)) != null) {
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

    void M(View view, float f2) {
        e eVar = (e) view.getLayoutParams();
        if (f2 == eVar.f966b) {
            return;
        }
        eVar.f966b = f2;
        j(view, f2);
    }

    void O(int i, int i2, View view) {
        int iZ = this.l.z();
        int iZ2 = this.m.z();
        int i3 = 2;
        if (iZ == 1 || iZ2 == 1) {
            i3 = 1;
        } else if (iZ != 2 && iZ2 != 2) {
            i3 = 0;
        }
        if (view != null && i2 == 0) {
            float f2 = ((e) view.getLayoutParams()).f966b;
            if (f2 == 0.0f) {
                h(view);
            } else if (f2 == 1.0f) {
                i(view);
            }
        }
        if (i3 != this.p) {
            this.p = i3;
            List<d> list = this.z;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.z.get(size).a(i3);
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
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!z(childAt)) {
                this.N.add(childAt);
            } else if (y(childAt)) {
                childAt.addFocusables(arrayList, i, i2);
                z = true;
            }
        }
        if (!z) {
            int size = this.N.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = this.N.get(i4);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i, i2);
                }
            }
        }
        this.N.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (l() != null || z(view)) {
            t.t0(view, 4);
        } else {
            t.t0(view, 1);
        }
        if (f961d) {
            return;
        }
        t.j0(view, this.f);
    }

    void b() {
        if (this.x) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.x = true;
    }

    boolean c(View view, int i) {
        return (q(view) & i) == i;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((e) getChildAt(i).getLayoutParams()).f966b);
        }
        this.j = fMax;
        boolean zM = this.l.m(true);
        boolean zM2 = this.m.m(true);
        if (zM || zM2) {
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
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (B(x, y, childAt) && !x(childAt) && dispatchTransformedGenericPointerEvent(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        int height = getHeight();
        boolean zX = x(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i = 0;
        if (zX) {
            int childCount = getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && t(childAt) && z(childAt) && childAt.getHeight() >= height) {
                    if (c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i2) {
                            i2 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i2, 0, width, getHeight());
            i = i2;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        float f2 = this.j;
        if (f2 > 0.0f && zX) {
            this.k.setColor((this.i & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f2)) << 24));
            canvas.drawRect(i, 0.0f, width, getHeight(), this.k);
        } else if (this.D != null && c(view, 3)) {
            int intrinsicWidth = this.D.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.l.w(), 1.0f));
            this.D.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.D.setAlpha((int) (fMax * 255.0f));
            this.D.draw(canvas);
        } else if (this.E != null && c(view, 5)) {
            int intrinsicWidth2 = this.E.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.m.w(), 1.0f));
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
            eVar.f966b = 0.0f;
            eVar.f968d = 0;
        } else if (z) {
            eVar.f968d |= 4;
            if (c(view, 3)) {
                this.l.O(view, -view.getWidth(), view.getTop());
            } else {
                this.m.O(view, getWidth(), view.getTop());
            }
        } else {
            D(view, 0.0f);
            O(eVar.f965a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void f() {
        g(false);
    }

    void g(boolean z) {
        int childCount = getChildCount();
        boolean zO = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            e eVar = (e) childAt.getLayoutParams();
            if (z(childAt) && (!z || eVar.f967c)) {
                zO |= c(childAt, 3) ? this.l.O(childAt, -childAt.getWidth(), childAt.getTop()) : this.m.O(childAt, getWidth(), childAt.getTop());
                eVar.f967c = false;
            }
        }
        this.n.p();
        this.o.p();
        if (zO) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public float getDrawerElevation() {
        if (e) {
            return this.g;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.C;
    }

    void h(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f968d & 1) == 1) {
            eVar.f968d = 0;
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

    void i(View view) {
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f968d & 1) == 0) {
            eVar.f968d = 1;
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

    void j(View view, float f2) {
        List<d> list = this.z;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.z.get(size).c(view, f2);
            }
        }
    }

    View k(int i) {
        int iB = b.g.k.d.b(i, t.z(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((q(childAt) & 7) == iB) {
                return childAt;
            }
        }
        return null;
    }

    View l() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((e) childAt.getLayoutParams()).f968d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    View m() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (z(childAt) && A(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public int n(int i) {
        int iZ = t.z(this);
        if (i == 3) {
            int i2 = this.s;
            if (i2 != 3) {
                return i2;
            }
            int i3 = iZ == 0 ? this.u : this.v;
            if (i3 != 3) {
                return i3;
            }
            return 0;
        }
        if (i == 5) {
            int i4 = this.t;
            if (i4 != 3) {
                return i4;
            }
            int i5 = iZ == 0 ? this.v : this.u;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
        if (i == 8388611) {
            int i6 = this.u;
            if (i6 != 3) {
                return i6;
            }
            int i7 = iZ == 0 ? this.s : this.t;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        }
        if (i != 8388613) {
            return 0;
        }
        int i8 = this.v;
        if (i8 != 3) {
            return i8;
        }
        int i9 = iZ == 0 ? this.t : this.s;
        if (i9 != 3) {
            return i9;
        }
        return 0;
    }

    public int o(View view) {
        if (z(view)) {
            return n(((e) view.getLayoutParams()).f965a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
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
            b.i.a.c r1 = r6.l
            boolean r1 = r1.N(r7)
            b.i.a.c r2 = r6.m
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
            b.i.a.c r7 = r6.l
            boolean r7 = r7.d(r4)
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
            b.i.a.c r4 = r6.l
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
            r6.w = r3
            r6.x = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.u()
            if (r7 != 0) goto L74
            boolean r6 = r6.x
            if (r6 == 0) goto L73
            goto L74
        L73:
            r2 = r3
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !v()) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View viewM = m();
        if (viewM != null && o(viewM) == 0) {
            f();
        }
        return viewM != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f2;
        int i5;
        boolean z2 = true;
        this.q = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (x(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f3 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (eVar.f966b * f3));
                        f2 = (measuredWidth + i5) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i6 - r11) / f4;
                        i5 = i6 - ((int) (eVar.f966b * f4));
                    }
                    boolean z3 = f2 != eVar.f966b ? z2 : false;
                    int i9 = eVar.f965a & 112;
                    if (i9 == 16) {
                        int i10 = i4 - i2;
                        int i11 = (i10 - measuredHeight) / 2;
                        int i12 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i11 < i12) {
                            i11 = i12;
                        } else {
                            int i13 = i11 + measuredHeight;
                            int i14 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i13 > i10 - i14) {
                                i11 = (i10 - i14) - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i11, measuredWidth + i5, measuredHeight + i11);
                    } else if (i9 != 80) {
                        int i15 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i5, i15, measuredWidth + i5, measuredHeight + i15);
                    } else {
                        int i16 = i4 - i2;
                        childAt.layout(i5, (i16 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i16 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z3) {
                        M(childAt, f2);
                    }
                    int i17 = eVar.f966b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i17) {
                        childAt.setVisibility(i17);
                    }
                }
            }
            i7++;
            z2 = true;
        }
        this.q = false;
        this.r = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
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
        int i3 = 0;
        boolean z = this.H != null && t.w(this);
        int iZ = t.z(this);
        int childCount = getChildCount();
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z) {
                    int iB = b.g.k.d.b(eVar.f965a, iZ);
                    if (t.w(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.H;
                            if (iB == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i3, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iB == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i3, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.H;
                        if (iB == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i3, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iB == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i3, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
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
                        throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (e) {
                        float fU = t.u(childAt);
                        float f2 = this.g;
                        if (fU != f2) {
                            t.q0(childAt, f2);
                        }
                    }
                    int iQ = q(childAt) & 7;
                    int i5 = iQ == 3 ? 1 : i3;
                    if ((i5 != 0 && z2) || (i5 == 0 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + s(iQ) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (i5 != 0) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i, this.h + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i2, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i4++;
            i3 = 0;
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View viewK;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        int i = savedState.f962d;
        if (i != 0 && (viewK = k(i)) != null) {
            E(viewK);
        }
        int i2 = savedState.e;
        if (i2 != 3) {
            L(i2, 3);
        }
        int i3 = savedState.f;
        if (i3 != 3) {
            L(i3, 5);
        }
        int i4 = savedState.g;
        if (i4 != 3) {
            L(i4, 8388611);
        }
        int i5 = savedState.h;
        if (i5 != 3) {
            L(i5, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        J();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            e eVar = (e) getChildAt(i).getLayoutParams();
            int i2 = eVar.f968d;
            boolean z = i2 == 1;
            boolean z2 = i2 == 2;
            if (z || z2) {
                savedState.f962d = eVar.f965a;
                break;
            }
        }
        savedState.e = this.s;
        savedState.f = this.t;
        savedState.g = this.u;
        savedState.h = this.v;
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
            b.i.a.c r0 = r6.l
            r0.E(r7)
            b.i.a.c r0 = r6.m
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
            r6.w = r2
            r6.x = r2
            goto L76
        L22:
            float r0 = r7.getX()
            float r7 = r7.getY()
            b.i.a.c r3 = r6.l
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
            b.i.a.c r3 = r6.l
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

    public CharSequence p(int i) {
        int iB = b.g.k.d.b(i, t.z(this));
        if (iB == 3) {
            return this.F;
        }
        if (iB == 5) {
            return this.G;
        }
        return null;
    }

    int q(View view) {
        return b.g.k.d.b(((e) view.getLayoutParams()).f965a, t.z(this));
    }

    float r(View view) {
        return ((e) view.getLayoutParams()).f966b;
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
        this.g = f2;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (z(childAt)) {
                t.q0(childAt, this.g);
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

    public void setDrawerLockMode(int i) {
        L(i, 3);
        L(i, 5);
    }

    public void setScrimColor(int i) {
        this.i = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.C = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.C = new ColorDrawable(i);
        invalidate();
    }

    boolean x(View view) {
        return ((e) view.getLayoutParams()).f965a == 0;
    }

    public boolean y(View view) {
        if (z(view)) {
            return (((e) view.getLayoutParams()).f968d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    boolean z(View view) {
        int iB = b.g.k.d.b(((e) view.getLayoutParams()).f965a, t.z(view));
        return ((iB & 3) == 0 && (iB & 5) == 0) ? false : true;
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new c();
        this.i = -1728053248;
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
        this.h = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        f fVar = new f(3);
        this.n = fVar;
        f fVar2 = new f(5);
        this.o = fVar2;
        b.i.a.c cVarN = b.i.a.c.n(this, 1.0f, fVar);
        this.l = cVarN;
        cVarN.K(1);
        cVarN.L(f3);
        fVar.q(cVarN);
        b.i.a.c cVarN2 = b.i.a.c.n(this, 1.0f, fVar2);
        this.m = cVarN2;
        cVarN2.K(2);
        cVarN2.L(f3);
        fVar2.q(cVarN2);
        setFocusableInTouchMode(true);
        t.t0(this, 1);
        t.j0(this, new b());
        setMotionEventSplittingEnabled(false);
        if (t.w(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a());
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f959b);
                try {
                    this.C = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.C = null;
            }
        }
        this.g = f2 * 10.0f;
        this.N = new ArrayList<>();
    }

    public void setStatusBarBackground(int i) {
        this.C = i != 0 ? androidx.core.content.a.d(getContext(), i) : null;
        invalidate();
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f965a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f966b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f967c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f968d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f965a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f960c);
            this.f965a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f965a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f965a = 0;
            this.f965a = eVar.f965a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f965a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f965a = 0;
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f962d;
        int e;
        int f;
        int g;
        int h;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f962d = 0;
            this.f962d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f962d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f962d = 0;
        }
    }
}
