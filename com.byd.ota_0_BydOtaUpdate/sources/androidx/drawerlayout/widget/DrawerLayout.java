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
import b.f.m.b0.c;
import b.f.m.s;
import b.h.a.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f477c = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f478d = {R.attr.layout_gravity};
    public static final boolean s;
    public static final boolean x;
    public float E3;
    public int F3;
    public int G3;
    public float H3;
    public Paint I3;
    public final b.h.a.a J3;
    public final b.h.a.a K3;
    public final f L3;
    public final f M3;
    public int N3;
    public boolean O3;
    public boolean P3;
    public int Q3;
    public int R3;
    public int S3;
    public int T3;
    public boolean U3;
    public boolean V3;
    public d W3;
    public List<d> X3;
    public float Y3;
    public float Z3;
    public Drawable a4;
    public Drawable b4;
    public Drawable c4;
    public CharSequence d4;
    public CharSequence e4;
    public Object f4;
    public boolean g4;
    public Drawable h4;
    public Drawable i4;
    public Drawable j4;
    public Drawable k4;
    public final ArrayList<View> l4;
    public Rect m4;
    public Matrix n4;
    public final c y;

    public class a implements View.OnApplyWindowInsetsListener {
        public a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).M(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    public class b extends b.f.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f480d = new Rect();

        public b() {
        }

        @Override // b.f.m.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.a(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View viewN = DrawerLayout.this.n();
            if (viewN == null) {
                return true;
            }
            CharSequence charSequenceQ = DrawerLayout.this.q(DrawerLayout.this.r(viewN));
            if (charSequenceQ == null) {
                return true;
            }
            text.add(charSequenceQ);
            return true;
        }

        @Override // b.f.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // b.f.m.a
        public void g(View view, b.f.m.b0.c cVar) {
            if (DrawerLayout.s) {
                super.g(view, cVar);
            } else {
                b.f.m.b0.c cVarJ = b.f.m.b0.c.J(cVar);
                super.g(view, cVarJ);
                cVar.j0(view);
                Object objY = s.y(view);
                if (objY instanceof View) {
                    cVar.f0((View) objY);
                }
                o(cVar, cVarJ);
                cVarJ.L();
                n(cVar, (ViewGroup) view);
            }
            cVar.T(DrawerLayout.class.getName());
            cVar.Z(false);
            cVar.a0(false);
            cVar.M(c.a.f1761a);
            cVar.M(c.a.f1762b);
        }

        @Override // b.f.m.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.s || DrawerLayout.y(view)) {
                return super.i(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        public final void n(b.f.m.b0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (DrawerLayout.y(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        public final void o(b.f.m.b0.c cVar, b.f.m.b0.c cVar2) {
            Rect rect = this.f480d;
            cVar2.k(rect);
            cVar.Q(rect);
            cVar2.l(rect);
            cVar.R(rect);
            cVar.k0(cVar2.I());
            cVar.d0(cVar2.r());
            cVar.T(cVar2.m());
            cVar.X(cVar2.o());
            cVar.Y(cVar2.B());
            cVar.U(cVar2.A());
            cVar.Z(cVar2.C());
            cVar.a0(cVar2.D());
            cVar.O(cVar2.x());
            cVar.i0(cVar2.H());
            cVar.c0(cVar2.E());
            cVar.a(cVar2.j());
        }
    }

    public static final class c extends b.f.m.a {
        @Override // b.f.m.a
        public void g(View view, b.f.m.b0.c cVar) {
            super.g(view, cVar);
            if (DrawerLayout.y(view)) {
                return;
            }
            cVar.f0(null);
        }
    }

    public interface d {
        void a(int i2);

        void b(View view);

        void c(View view, float f2);

        void d(View view);
    }

    public class f extends a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f486a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.h.a.a f487b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f488c = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        }

        public f(int i2) {
            this.f486a = i2;
        }

        @Override // b.h.a.a.c
        public int a(View view, int i2, int i3) {
            if (DrawerLayout.this.c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i2, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i2, width));
        }

        @Override // b.h.a.a.c
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // b.h.a.a.c
        public int d(View view) {
            if (DrawerLayout.this.B(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // b.h.a.a.c
        public void f(int i2, int i3) {
            View viewL = (i2 & 1) == 1 ? DrawerLayout.this.l(3) : DrawerLayout.this.l(5);
            if (viewL == null || DrawerLayout.this.p(viewL) != 0) {
                return;
            }
            this.f487b.b(viewL, i3);
        }

        @Override // b.h.a.a.c
        public boolean g(int i2) {
            return false;
        }

        @Override // b.h.a.a.c
        public void h(int i2, int i3) {
            DrawerLayout.this.postDelayed(this.f488c, 160L);
        }

        @Override // b.h.a.a.c
        public void i(View view, int i2) {
            ((e) view.getLayoutParams()).f484c = false;
            n();
        }

        @Override // b.h.a.a.c
        public void j(int i2) {
            DrawerLayout.this.Q(this.f486a, i2, this.f487b.v());
        }

        @Override // b.h.a.a.c
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = (DrawerLayout.this.c(view, 3) ? i2 + r3 : DrawerLayout.this.getWidth() - i2) / view.getWidth();
            DrawerLayout.this.O(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // b.h.a.a.c
        public void l(View view, float f2, float f3) {
            int i2;
            float fS = DrawerLayout.this.s(view);
            int width = view.getWidth();
            if (DrawerLayout.this.c(view, 3)) {
                i2 = (f2 > 0.0f || (f2 == 0.0f && fS > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f2 < 0.0f || (f2 == 0.0f && fS > 0.5f)) {
                    width2 -= width;
                }
                i2 = width2;
            }
            this.f487b.M(i2, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // b.h.a.a.c
        public boolean m(View view, int i2) {
            return DrawerLayout.this.B(view) && DrawerLayout.this.c(view, this.f486a) && DrawerLayout.this.p(view) == 0;
        }

        public final void n() {
            View viewL = DrawerLayout.this.l(this.f486a == 3 ? 5 : 3);
            if (viewL != null) {
                DrawerLayout.this.d(viewL);
            }
        }

        public void o() {
            View viewL;
            int width;
            int iW = this.f487b.w();
            boolean z = this.f486a == 3;
            if (z) {
                viewL = DrawerLayout.this.l(3);
                width = (viewL != null ? -viewL.getWidth() : 0) + iW;
            } else {
                viewL = DrawerLayout.this.l(5);
                width = DrawerLayout.this.getWidth() - iW;
            }
            if (viewL != null) {
                if (((!z || viewL.getLeft() >= width) && (z || viewL.getLeft() <= width)) || DrawerLayout.this.p(viewL) != 0) {
                    return;
                }
                e eVar = (e) viewL.getLayoutParams();
                this.f487b.O(viewL, width, viewL.getTop());
                eVar.f484c = true;
                DrawerLayout.this.invalidate();
                n();
                DrawerLayout.this.b();
            }
        }

        public void p() {
            DrawerLayout.this.removeCallbacks(this.f488c);
        }

        public void q(b.h.a.a aVar) {
            this.f487b = aVar;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        s = i2 >= 19;
        x = i2 >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static String u(int i2) {
        return (i2 & 3) == 3 ? "LEFT" : (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    public static boolean v(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    public static boolean y(View view) {
        return (s.t(view) == 4 || s.t(view) == 2) ? false : true;
    }

    public boolean A(View view) {
        if (B(view)) {
            return (((e) view.getLayoutParams()).f485d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean B(View view) {
        int iA = b.f.m.d.a(((e) view.getLayoutParams()).f482a, s.v(view));
        return ((iA & 3) == 0 && (iA & 5) == 0) ? false : true;
    }

    public boolean C(View view) {
        if (B(view)) {
            return ((e) view.getLayoutParams()).f483b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final boolean D(float f2, float f3, View view) {
        if (this.m4 == null) {
            this.m4 = new Rect();
        }
        view.getHitRect(this.m4);
        return this.m4.contains((int) f2, (int) f3);
    }

    public final boolean E(Drawable drawable, int i2) {
        if (drawable == null || !b.f.g.l.a.g(drawable)) {
            return false;
        }
        b.f.g.l.a.l(drawable, i2);
        return true;
    }

    public void F(View view, float f2) {
        float fS = s(view);
        float width = view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (fS * width));
        if (!c(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        O(view, f2);
    }

    public void G(View view) {
        H(view, true);
    }

    public void H(View view, boolean z) {
        if (!B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.P3) {
            eVar.f483b = 1.0f;
            eVar.f485d = 1;
            P(view, true);
        } else if (z) {
            eVar.f485d |= 2;
            if (c(view, 3)) {
                this.J3.O(view, 0, view.getTop());
            } else {
                this.K3.O(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            F(view, 1.0f);
            Q(eVar.f482a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void I(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.X3) == null) {
            return;
        }
        list.remove(dVar);
    }

    public final Drawable J() {
        int iV = s.v(this);
        if (iV == 0) {
            Drawable drawable = this.h4;
            if (drawable != null) {
                E(drawable, iV);
                return this.h4;
            }
        } else {
            Drawable drawable2 = this.i4;
            if (drawable2 != null) {
                E(drawable2, iV);
                return this.i4;
            }
        }
        return this.j4;
    }

    public final Drawable K() {
        int iV = s.v(this);
        if (iV == 0) {
            Drawable drawable = this.i4;
            if (drawable != null) {
                E(drawable, iV);
                return this.i4;
            }
        } else {
            Drawable drawable2 = this.h4;
            if (drawable2 != null) {
                E(drawable2, iV);
                return this.h4;
            }
        }
        return this.k4;
    }

    public final void L() {
        if (x) {
            return;
        }
        this.b4 = J();
        this.c4 = K();
    }

    public void M(Object obj, boolean z) {
        this.f4 = obj;
        this.g4 = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    public void N(int i2, int i3) {
        View viewL;
        int iA = b.f.m.d.a(i3, s.v(this));
        if (i3 == 3) {
            this.Q3 = i2;
        } else if (i3 == 5) {
            this.R3 = i2;
        } else if (i3 == 8388611) {
            this.S3 = i2;
        } else if (i3 == 8388613) {
            this.T3 = i2;
        }
        if (i2 != 0) {
            (iA == 3 ? this.J3 : this.K3).a();
        }
        if (i2 != 1) {
            if (i2 == 2 && (viewL = l(iA)) != null) {
                G(viewL);
                return;
            }
            return;
        }
        View viewL2 = l(iA);
        if (viewL2 != null) {
            d(viewL2);
        }
    }

    public void O(View view, float f2) {
        e eVar = (e) view.getLayoutParams();
        if (f2 == eVar.f483b) {
            return;
        }
        eVar.f483b = f2;
        j(view, f2);
    }

    public final void P(View view, boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((z || B(childAt)) && !(z && childAt == view)) {
                s.Y(childAt, 4);
            } else {
                s.Y(childAt, 1);
            }
        }
    }

    public void Q(int i2, int i3, View view) {
        int iZ = this.J3.z();
        int iZ2 = this.K3.z();
        int i4 = 2;
        if (iZ == 1 || iZ2 == 1) {
            i4 = 1;
        } else if (iZ != 2 && iZ2 != 2) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            float f2 = ((e) view.getLayoutParams()).f483b;
            if (f2 == 0.0f) {
                h(view);
            } else if (f2 == 1.0f) {
                i(view);
            }
        }
        if (i4 != this.N3) {
            this.N3 = i4;
            List<d> list = this.X3;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.X3.get(size).a(i4);
                }
            }
        }
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.X3 == null) {
            this.X3 = new ArrayList();
        }
        this.X3.add(dVar);
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
            if (!B(childAt)) {
                this.l4.add(childAt);
            } else if (A(childAt)) {
                childAt.addFocusables(arrayList, i2, i3);
                z = true;
            }
        }
        if (!z) {
            int size = this.l4.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = this.l4.get(i5);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i2, i3);
                }
            }
        }
        this.l4.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (m() != null || B(view)) {
            s.Y(view, 4);
        } else {
            s.Y(view, 1);
        }
        if (s) {
            return;
        }
        s.T(view, this.y);
    }

    public void b() {
        if (this.V3) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.V3 = true;
    }

    public boolean c(View view, int i2) {
        return (r(view) & i2) == i2;
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
            fMax = Math.max(fMax, ((e) getChildAt(i2).getLayoutParams()).f483b);
        }
        this.H3 = fMax;
        boolean zM = this.J3.m(true);
        boolean zM2 = this.K3.m(true);
        if (zM || zM2) {
            s.N(this);
        }
    }

    public void d(View view) {
        e(view, true);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.H3 <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x2 = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = getChildAt(i2);
            if (D(x2, y, childAt) && !z(childAt) && k(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        int height = getHeight();
        boolean z = z(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i2 = 0;
        if (z) {
            int childCount = getChildCount();
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0 && v(childAt) && B(childAt) && childAt.getHeight() >= height) {
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
        float f2 = this.H3;
        if (f2 > 0.0f && z) {
            this.I3.setColor((this.G3 & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f2)) << 24));
            canvas.drawRect(i2, 0.0f, width, getHeight(), this.I3);
        } else if (this.b4 != null && c(view, 3)) {
            int intrinsicWidth = this.b4.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.J3.w(), 1.0f));
            this.b4.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.b4.setAlpha((int) (fMax * 255.0f));
            this.b4.draw(canvas);
        } else if (this.c4 != null && c(view, 5)) {
            int intrinsicWidth2 = this.c4.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.K3.w(), 1.0f));
            this.c4.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.c4.setAlpha((int) (fMax2 * 255.0f));
            this.c4.draw(canvas);
        }
        return zDrawChild;
    }

    public void e(View view, boolean z) {
        if (!B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        e eVar = (e) view.getLayoutParams();
        if (this.P3) {
            eVar.f483b = 0.0f;
            eVar.f485d = 0;
        } else if (z) {
            eVar.f485d |= 4;
            if (c(view, 3)) {
                this.J3.O(view, -view.getWidth(), view.getTop());
            } else {
                this.K3.O(view, getWidth(), view.getTop());
            }
        } else {
            F(view, 0.0f);
            Q(eVar.f482a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void f() {
        g(false);
    }

    public void g(boolean z) {
        int childCount = getChildCount();
        boolean zO = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            e eVar = (e) childAt.getLayoutParams();
            if (B(childAt) && (!z || eVar.f484c)) {
                zO |= c(childAt, 3) ? this.J3.O(childAt, -childAt.getWidth(), childAt.getTop()) : this.K3.O(childAt, getWidth(), childAt.getTop());
                eVar.f484c = false;
            }
        }
        this.L3.p();
        this.M3.p();
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
        if (x) {
            return this.E3;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.a4;
    }

    public void h(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f485d & 1) == 1) {
            eVar.f485d = 0;
            List<d> list = this.X3;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.X3.get(size).d(view);
                }
            }
            P(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    public void i(View view) {
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f485d & 1) == 0) {
            eVar.f485d = 1;
            List<d> list = this.X3;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.X3.get(size).b(view);
                }
            }
            P(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public void j(View view, float f2) {
        List<d> list = this.X3;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.X3.get(size).c(view, f2);
            }
        }
    }

    public final boolean k(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent motionEventT = t(motionEvent, view);
            boolean zDispatchGenericMotionEvent = view.dispatchGenericMotionEvent(motionEventT);
            motionEventT.recycle();
            return zDispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean zDispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return zDispatchGenericMotionEvent2;
    }

    public View l(int i2) {
        int iA = b.f.m.d.a(i2, s.v(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((r(childAt) & 7) == iA) {
                return childAt;
            }
        }
        return null;
    }

    public View m() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((e) childAt.getLayoutParams()).f485d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public View n() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (B(childAt) && C(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public int o(int i2) {
        int iV = s.v(this);
        if (i2 == 3) {
            int i3 = this.Q3;
            if (i3 != 3) {
                return i3;
            }
            int i4 = iV == 0 ? this.S3 : this.T3;
            if (i4 != 3) {
                return i4;
            }
            return 0;
        }
        if (i2 == 5) {
            int i5 = this.R3;
            if (i5 != 3) {
                return i5;
            }
            int i6 = iV == 0 ? this.T3 : this.S3;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        }
        if (i2 == 8388611) {
            int i7 = this.S3;
            if (i7 != 3) {
                return i7;
            }
            int i8 = iV == 0 ? this.Q3 : this.R3;
            if (i8 != 3) {
                return i8;
            }
            return 0;
        }
        if (i2 != 8388613) {
            return 0;
        }
        int i9 = this.T3;
        if (i9 != 3) {
            return i9;
        }
        int i10 = iV == 0 ? this.R3 : this.Q3;
        if (i10 != 3) {
            return i10;
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P3 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.P3 = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.g4 || this.a4 == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.f4) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.a4.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.a4.draw(canvas);
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
            b.h.a.a r1 = r6.J3
            boolean r1 = r1.N(r7)
            b.h.a.a r2 = r6.K3
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
            b.h.a.a r7 = r6.J3
            boolean r7 = r7.d(r4)
            if (r7 == 0) goto L38
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.L3
            r7.p()
            androidx.drawerlayout.widget.DrawerLayout$f r7 = r6.M3
            r7.p()
            goto L38
        L31:
            r6.g(r2)
            r6.U3 = r3
            r6.V3 = r3
        L38:
            r7 = r3
            goto L64
        L3a:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.Y3 = r0
            r6.Z3 = r7
            float r4 = r6.H3
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5f
            b.h.a.a r4 = r6.J3
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.t(r0, r7)
            if (r7 == 0) goto L5f
            boolean r7 = r6.z(r7)
            if (r7 == 0) goto L5f
            r7 = r2
            goto L60
        L5f:
            r7 = r3
        L60:
            r6.U3 = r3
            r6.V3 = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.w()
            if (r7 != 0) goto L74
            boolean r7 = r6.V3
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
        if (i2 != 4 || !x()) {
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
        View viewN = n();
        if (viewN != null && p(viewN) == 0) {
            f();
        }
        return viewN != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        float f2;
        int i6;
        boolean z2 = true;
        this.O3 = true;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z(childAt)) {
                    int i9 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i9, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i9, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (c(childAt, 3)) {
                        float f3 = measuredWidth;
                        i6 = (-measuredWidth) + ((int) (eVar.f483b * f3));
                        f2 = (measuredWidth + i6) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i7 - r11) / f4;
                        i6 = i7 - ((int) (eVar.f483b * f4));
                    }
                    boolean z3 = f2 != eVar.f483b ? z2 : false;
                    int i10 = eVar.f482a & 112;
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
                        O(childAt, f2);
                    }
                    int i18 = eVar.f483b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
            i8++;
            z2 = true;
        }
        this.O3 = false;
        this.P3 = false;
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
        boolean z = this.f4 != null && s.s(this);
        int iV = s.v(this);
        int childCount = getChildCount();
        int i5 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z) {
                    int iA = b.f.m.d.a(eVar.f482a, iV);
                    if (s.s(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.f4;
                            if (iA == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i4, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iA == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i4, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.f4;
                        if (iA == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i4, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iA == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i4, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) eVar).leftMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) eVar).topMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) eVar).rightMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (z(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) eVar).leftMargin) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) eVar).topMargin) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                } else {
                    if (!B(childAt)) {
                        throw new IllegalStateException("Child " + childAt + " at index " + i5 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (x) {
                        float fQ = s.q(childAt);
                        float f2 = this.E3;
                        if (fQ != f2) {
                            s.X(childAt, f2);
                        }
                    }
                    int iR = r(childAt) & 7;
                    int i6 = iR == 3 ? 1 : i4;
                    if ((i6 != 0 && z2) || (i6 == 0 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + u(iR) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (i6 != 0) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i2, this.F3 + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i3, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i5++;
            i4 = 0;
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        View viewL;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        int i2 = savedState.s;
        if (i2 != 0 && (viewL = l(i2)) != null) {
            G(viewL);
        }
        int i3 = savedState.x;
        if (i3 != 3) {
            N(i3, 3);
        }
        int i4 = savedState.y;
        if (i4 != 3) {
            N(i4, 5);
        }
        int i5 = savedState.E3;
        if (i5 != 3) {
            N(i5, 8388611);
        }
        int i6 = savedState.F3;
        if (i6 != 3) {
            N(i6, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        L();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            e eVar = (e) getChildAt(i2).getLayoutParams();
            int i3 = eVar.f485d;
            boolean z = i3 == 1;
            boolean z2 = i3 == 2;
            if (z || z2) {
                savedState.s = eVar.f482a;
                break;
            }
        }
        savedState.x = this.Q3;
        savedState.y = this.R3;
        savedState.E3 = this.S3;
        savedState.F3 = this.T3;
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
            b.h.a.a r0 = r6.J3
            r0.E(r7)
            b.h.a.a r0 = r6.K3
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
            r6.U3 = r2
            r6.V3 = r2
            goto L76
        L22:
            float r0 = r7.getX()
            float r7 = r7.getY()
            b.h.a.a r3 = r6.J3
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.t(r4, r5)
            if (r3 == 0) goto L5f
            boolean r3 = r6.z(r3)
            if (r3 == 0) goto L5f
            float r3 = r6.Y3
            float r0 = r0 - r3
            float r3 = r6.Z3
            float r7 = r7 - r3
            b.h.a.a r3 = r6.J3
            int r3 = r3.y()
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r0 = r0 + r7
            int r3 = r3 * r3
            float r7 = (float) r3
            int r7 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r7 >= 0) goto L5f
            android.view.View r7 = r6.m()
            if (r7 == 0) goto L5f
            int r7 = r6.p(r7)
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
            r6.U3 = r2
            goto L76
        L66:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.Y3 = r0
            r6.Z3 = r7
            r6.U3 = r2
            r6.V3 = r2
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public int p(View view) {
        if (B(view)) {
            return o(((e) view.getLayoutParams()).f482a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public CharSequence q(int i2) {
        int iA = b.f.m.d.a(i2, s.v(this));
        if (iA == 3) {
            return this.d4;
        }
        if (iA == 5) {
            return this.e4;
        }
        return null;
    }

    public int r(View view) {
        return b.f.m.d.a(((e) view.getLayoutParams()).f482a, s.v(this));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.U3 = z;
        if (z) {
            g(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.O3) {
            return;
        }
        super.requestLayout();
    }

    public float s(View view) {
        return ((e) view.getLayoutParams()).f483b;
    }

    public void setDrawerElevation(float f2) {
        this.E3 = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (B(childAt)) {
                s.X(childAt, this.E3);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.W3;
        if (dVar2 != null) {
            I(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.W3 = dVar;
    }

    public void setDrawerLockMode(int i2) {
        N(i2, 3);
        N(i2, 5);
    }

    public void setScrimColor(int i2) {
        this.G3 = i2;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.a4 = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i2) {
        this.a4 = new ColorDrawable(i2);
        invalidate();
    }

    public final MotionEvent t(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.n4 == null) {
                this.n4 = new Matrix();
            }
            matrix.invert(this.n4);
            motionEventObtain.transform(this.n4);
        }
        return motionEventObtain;
    }

    public final boolean w() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((e) getChildAt(i2).getLayoutParams()).f484c) {
                return true;
            }
        }
        return false;
    }

    public final boolean x() {
        return n() != null;
    }

    public boolean z(View view) {
        return ((e) view.getLayoutParams()).f482a == 0;
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
        this.y = new c();
        this.G3 = -1728053248;
        this.I3 = new Paint();
        this.P3 = true;
        this.Q3 = 3;
        this.R3 = 3;
        this.S3 = 3;
        this.T3 = 3;
        this.h4 = null;
        this.i4 = null;
        this.j4 = null;
        this.k4 = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.F3 = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        f fVar = new f(3);
        this.L3 = fVar;
        f fVar2 = new f(5);
        this.M3 = fVar2;
        b.h.a.a aVarN = b.h.a.a.n(this, 1.0f, fVar);
        this.J3 = aVarN;
        aVarN.K(1);
        aVarN.L(f3);
        fVar.q(aVarN);
        b.h.a.a aVarN2 = b.h.a.a.n(this, 1.0f, fVar2);
        this.K3 = aVarN2;
        aVarN2.K(2);
        aVarN2.L(f3);
        fVar2.q(aVarN2);
        setFocusableInTouchMode(true);
        s.Y(this, 1);
        s.T(this, new b());
        setMotionEventSplittingEnabled(false);
        if (s.s(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a());
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f477c);
                try {
                    this.a4 = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.a4 = null;
            }
        }
        this.E3 = f2 * 10.0f;
        this.l4 = new ArrayList<>();
    }

    public void setStatusBarBackground(int i2) {
        this.a4 = i2 != 0 ? b.f.f.a.c(getContext(), i2) : null;
        invalidate();
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f482a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f483b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f484c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f485d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f482a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f478d);
            this.f482a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i2, int i3) {
            super(i2, i3);
            this.f482a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f482a = 0;
            this.f482a = eVar.f482a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f482a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f482a = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int E3;
        public int F3;
        public int s;
        public int x;
        public int y;

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
            this.s = 0;
            this.s = parcel.readInt();
            this.x = parcel.readInt();
            this.y = parcel.readInt();
            this.E3 = parcel.readInt();
            this.F3 = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.s);
            parcel.writeInt(this.x);
            parcel.writeInt(this.y);
            parcel.writeInt(this.E3);
            parcel.writeInt(this.F3);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.s = 0;
        }
    }
}
