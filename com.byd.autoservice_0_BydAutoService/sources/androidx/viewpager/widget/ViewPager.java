package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import l0.b0;
import l0.p;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1661b = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Comparator<f> f1662c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Interpolator f1663d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f1664e = new m();
    public boolean A;
    public boolean B;
    public int C;
    public boolean D;
    public boolean E;
    public int F;
    public int G;
    public int H;
    public float I;
    public float J;
    public float K;
    public float L;
    public int M;
    public VelocityTracker N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public boolean S;
    public EdgeEffect T;
    public EdgeEffect U;
    public boolean V;
    public boolean W;
    public boolean X;
    public int Y;
    public List<j> Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public j f1665a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public j f1666b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public List<i> f1667c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public k f1668d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f1669e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1670f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f1671f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList<f> f1672g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public ArrayList<View> f1673g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f1674h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final Runnable f1675h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f1676i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public int f1677i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public b1.a f1678j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1679k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1680l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Parcelable f1681m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ClassLoader f1682n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Scroller f1683o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1684p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public l f1685q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1686r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Drawable f1687s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1688t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1689u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f1690v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f1691w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1692x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1693y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1694z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1695d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Parcelable f1696e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ClassLoader f1697f;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f1695d + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f1695d);
            parcel.writeParcelable(this.f1696e, i3);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f1695d = parcel.readInt();
            this.f1696e = parcel.readParcelable(classLoader);
            this.f1697f = classLoader;
        }
    }

    public static class a implements Comparator<f> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f1702b - fVar2.f1702b;
        }
    }

    public static class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            float f4 = f3 - 1.0f;
            return (f4 * f4 * f4 * f4 * f4) + 1.0f;
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.E();
        }
    }

    public class d implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f1699a = new Rect();

        public d() {
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            b0 b0VarW = t.W(view, b0Var);
            if (b0VarW.m()) {
                return b0VarW;
            }
            Rect rect = this.f1699a;
            rect.left = b0VarW.f();
            rect.top = b0VarW.h();
            rect.right = b0VarW.g();
            rect.bottom = b0VarW.e();
            int childCount = ViewPager.this.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                b0 b0VarG = t.g(ViewPager.this.getChildAt(i3), b0VarW);
                rect.left = Math.min(b0VarG.f(), rect.left);
                rect.top = Math.min(b0VarG.h(), rect.top);
                rect.right = Math.min(b0VarG.g(), rect.right);
                rect.bottom = Math.min(b0VarG.e(), rect.bottom);
            }
            return b0VarW.n(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f1701a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1702b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1703c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1704d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1705e;
    }

    public class h extends l0.a {
        public h() {
        }

        @Override // l0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b1.a aVar;
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.f1678j) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.e());
            accessibilityEvent.setFromIndex(ViewPager.this.f1679k);
            accessibilityEvent.setToIndex(ViewPager.this.f1679k);
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            cVar.b0(ViewPager.class.getName());
            cVar.v0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        @Override // l0.a
        public boolean j(View view, int i3, Bundle bundle) {
            if (super.j(view, i3, bundle)) {
                return true;
            }
            if (i3 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f1679k + 1);
                return true;
            }
            if (i3 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f1679k - 1);
            return true;
        }

        public final boolean n() {
            b1.a aVar = ViewPager.this.f1678j;
            return aVar != null && aVar.e() > 1;
        }
    }

    public interface i {
        void b(ViewPager viewPager, b1.a aVar, b1.a aVar2);
    }

    public interface j {
        void a(int i3, float f3, int i4);

        void c(int i3);

        void d(int i3);
    }

    public interface k {
        void a(View view, float f3);
    }

    public class l extends DataSetObserver {
        public l() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.h();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.h();
        }
    }

    public static class m implements Comparator<View> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z3 = gVar.f1706a;
            return z3 != gVar2.f1706a ? z3 ? 1 : -1 : gVar.f1710e - gVar2.f1710e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f1672g = new ArrayList<>();
        this.f1674h = new f();
        this.f1676i = new Rect();
        this.f1680l = -1;
        this.f1681m = null;
        this.f1682n = null;
        this.f1690v = -3.4028235E38f;
        this.f1691w = Float.MAX_VALUE;
        this.C = 1;
        this.M = -1;
        this.V = true;
        this.W = false;
        this.f1675h0 = new c();
        this.f1677i0 = 0;
        v();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z3) {
        if (this.A != z3) {
            this.A = z3;
        }
    }

    public static boolean w(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    public boolean A() {
        int i3 = this.f1679k;
        if (i3 <= 0) {
            return false;
        }
        N(i3 - 1, true);
        return true;
    }

    public boolean B() {
        b1.a aVar = this.f1678j;
        if (aVar == null || this.f1679k >= aVar.e() - 1) {
            return false;
        }
        N(this.f1679k + 1, true);
        return true;
    }

    public final boolean C(int i3) {
        if (this.f1672g.size() == 0) {
            if (this.V) {
                return false;
            }
            this.X = false;
            y(0, 0.0f, 0);
            if (this.X) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarT = t();
        int clientWidth = getClientWidth();
        int i4 = this.f1686r;
        int i5 = clientWidth + i4;
        float f3 = clientWidth;
        int i6 = fVarT.f1702b;
        float f4 = ((i3 / f3) - fVarT.f1705e) / (fVarT.f1704d + (i4 / f3));
        this.X = false;
        y(i6, f4, (int) (i5 * f4));
        if (this.X) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final boolean D(float f3) {
        boolean z3;
        boolean z4;
        float f4 = this.I - f3;
        this.I = f3;
        float scrollX = getScrollX() + f4;
        float clientWidth = getClientWidth();
        float f5 = this.f1690v * clientWidth;
        float f6 = this.f1691w * clientWidth;
        boolean z5 = false;
        f fVar = this.f1672g.get(0);
        ArrayList<f> arrayList = this.f1672g;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.f1702b != 0) {
            f5 = fVar.f1705e * clientWidth;
            z3 = false;
        } else {
            z3 = true;
        }
        if (fVar2.f1702b != this.f1678j.e() - 1) {
            f6 = fVar2.f1705e * clientWidth;
            z4 = false;
        } else {
            z4 = true;
        }
        if (scrollX < f5) {
            if (z3) {
                this.T.onPull(Math.abs(f5 - scrollX) / clientWidth);
                z5 = true;
            }
            scrollX = f5;
        } else if (scrollX > f6) {
            if (z4) {
                this.U.onPull(Math.abs(scrollX - f6) / clientWidth);
                z5 = true;
            }
            scrollX = f6;
        }
        int i3 = (int) scrollX;
        this.I += scrollX - i3;
        scrollTo(i3, getScrollY());
        C(i3);
        return z5;
    }

    public void E() {
        F(this.f1679k);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00f7 A[PHI: r7 r10 r15
  0x00f7: PHI (r7v6 int) = (r7v5 int), (r7v4 int), (r7v9 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
  0x00f7: PHI (r10v9 int) = (r10v1 int), (r10v8 int), (r10v12 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
  0x00f7: PHI (r15v7 float) = (r15v5 float), (r15v6 float), (r15v4 float) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void F(int r18) {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.F(int):void");
    }

    public final void G(int i3, int i4, int i5, int i6) {
        if (i4 > 0 && !this.f1672g.isEmpty()) {
            if (!this.f1683o.isFinished()) {
                this.f1683o.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i4 - getPaddingLeft()) - getPaddingRight()) + i6)) * (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)), getScrollY());
                return;
            }
        }
        f fVarU = u(this.f1679k);
        int iMin = (int) ((fVarU != null ? Math.min(fVarU.f1705e, this.f1691w) : 0.0f) * ((i3 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            g(false);
            scrollTo(iMin, getScrollY());
        }
    }

    public final void H() {
        int i3 = 0;
        while (i3 < getChildCount()) {
            if (!((g) getChildAt(i3).getLayoutParams()).f1706a) {
                removeViewAt(i3);
                i3--;
            }
            i3++;
        }
    }

    public void I(i iVar) {
        List<i> list = this.f1667c0;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public void J(j jVar) {
        List<j> list = this.Z;
        if (list != null) {
            list.remove(jVar);
        }
    }

    public final void K(boolean z3) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z3);
        }
    }

    public final boolean L() {
        this.M = -1;
        o();
        this.T.onRelease();
        this.U.onRelease();
        return this.T.isFinished() || this.U.isFinished();
    }

    public final void M(int i3, boolean z3, int i4, boolean z4) {
        f fVarU = u(i3);
        int clientWidth = fVarU != null ? (int) (getClientWidth() * Math.max(this.f1690v, Math.min(fVarU.f1705e, this.f1691w))) : 0;
        if (z3) {
            R(clientWidth, 0, i4);
            if (z4) {
                k(i3);
                return;
            }
            return;
        }
        if (z4) {
            k(i3);
        }
        g(false);
        scrollTo(clientWidth, 0);
        C(clientWidth);
    }

    public void N(int i3, boolean z3) {
        this.B = false;
        O(i3, z3, false);
    }

    public void O(int i3, boolean z3, boolean z4) {
        P(i3, z3, z4, 0);
    }

    public void P(int i3, boolean z3, boolean z4, int i4) {
        b1.a aVar = this.f1678j;
        if (aVar == null || aVar.e() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z4 && this.f1679k == i3 && this.f1672g.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 >= this.f1678j.e()) {
            i3 = this.f1678j.e() - 1;
        }
        int i5 = this.C;
        int i6 = this.f1679k;
        if (i3 > i6 + i5 || i3 < i6 - i5) {
            for (int i7 = 0; i7 < this.f1672g.size(); i7++) {
                this.f1672g.get(i7).f1703c = true;
            }
        }
        boolean z5 = this.f1679k != i3;
        if (!this.V) {
            F(i3);
            M(i3, z3, i4, z5);
        } else {
            this.f1679k = i3;
            if (z5) {
                k(i3);
            }
            requestLayout();
        }
    }

    public j Q(j jVar) {
        j jVar2 = this.f1666b0;
        this.f1666b0 = jVar;
        return jVar2;
    }

    public void R(int i3, int i4, int i5) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.f1683o;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.f1684p ? this.f1683o.getCurrX() : this.f1683o.getStartX();
            this.f1683o.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i6 = scrollX;
        int scrollY = getScrollY();
        int i7 = i3 - i6;
        int i8 = i4 - scrollY;
        if (i7 == 0 && i8 == 0) {
            g(false);
            E();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i9 = clientWidth / 2;
        float f3 = clientWidth;
        float f4 = i9;
        float fM = f4 + (m(Math.min(1.0f, (Math.abs(i7) * 1.0f) / f3)) * f4);
        int iAbs = Math.abs(i5);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fM / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i7) / ((f3 * this.f1678j.h(this.f1679k)) + this.f1686r)) + 1.0f) * 100.0f), 600);
        this.f1684p = false;
        this.f1683o.startScroll(i6, scrollY, i7, i8, iMin);
        t.a0(this);
    }

    public final void S() {
        if (this.f1671f0 != 0) {
            ArrayList<View> arrayList = this.f1673g0;
            if (arrayList == null) {
                this.f1673g0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                this.f1673g0.add(getChildAt(i3));
            }
            Collections.sort(this.f1673g0, f1664e);
        }
    }

    public f a(int i3, int i4) {
        f fVar = new f();
        fVar.f1702b = i3;
        fVar.f1701a = this.f1678j.j(this, i3);
        fVar.f1704d = this.f1678j.h(i3);
        if (i4 < 0 || i4 >= this.f1672g.size()) {
            this.f1672g.add(fVar);
        } else {
            this.f1672g.add(i4, fVar);
        }
        return fVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i3, int i4) {
        f fVarS;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1702b == this.f1679k) {
                    childAt.addFocusables(arrayList, i3, i4);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i4 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarS;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1702b == this.f1679k) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zW = gVar.f1706a | w(view);
        gVar.f1706a = zW;
        if (!this.f1694z) {
            super.addView(view, i3, layoutParams);
        } else {
            if (zW) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f1709d = true;
            addViewInLayout(view, i3, layoutParams);
        }
    }

    public void b(i iVar) {
        if (this.f1667c0 == null) {
            this.f1667c0 = new ArrayList();
        }
        this.f1667c0.add(iVar);
    }

    public void c(j jVar) {
        if (this.Z == null) {
            this.Z = new ArrayList();
        }
        this.Z.add(jVar);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i3) {
        if (this.f1678j == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i3 < 0 ? scrollX > ((int) (((float) clientWidth) * this.f1690v)) : i3 > 0 && scrollX < ((int) (((float) clientWidth) * this.f1691w));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.f1684p = true;
        if (this.f1683o.isFinished() || !this.f1683o.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f1683o.getCurrX();
        int currY = this.f1683o.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!C(currX)) {
                this.f1683o.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.a0(this);
    }

    public boolean d(int i3) {
        boolean z3;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zA = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z3 = false;
                    break;
                }
                if (parent == this) {
                    z3 = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z3) {
                StringBuilder sb = new StringBuilder();
                sb.append(viewFindFocus.getClass().getSimpleName());
                for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                viewFindFocus = null;
            }
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i3);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i3 == 17) {
                zRequestFocus = (viewFindFocus == null || q(this.f1676i, viewFindNextFocus).left < q(this.f1676i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : A();
            } else if (i3 == 66) {
                zRequestFocus = (viewFindFocus == null || q(this.f1676i, viewFindNextFocus).left > q(this.f1676i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : B();
            }
            zA = zRequestFocus;
        } else if (i3 == 17 || i3 == 1) {
            zA = A();
        } else if (i3 == 66 || i3 == 2) {
            zA = B();
        }
        if (zA) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i3));
        }
        return zA;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || p(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarS;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1702b == this.f1679k && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b1.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.f1678j) != null && aVar.e() > 1)) {
            if (!this.T.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.f1690v * width);
                this.T.setSize(height, width);
                zDraw = false | this.T.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.U.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.f1691w + 1.0f)) * width2);
                this.U.setSize(height2, width2);
                zDraw |= this.U.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.T.finish();
            this.U.finish();
        }
        if (zDraw) {
            t.a0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1687s;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e(f fVar, int i3, f fVar2) {
        int i4;
        int i5;
        f fVar3;
        f fVar4;
        int iE = this.f1678j.e();
        int clientWidth = getClientWidth();
        float f3 = clientWidth > 0 ? this.f1686r / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i6 = fVar2.f1702b;
            int i7 = fVar.f1702b;
            if (i6 < i7) {
                float fH = fVar2.f1705e + fVar2.f1704d + f3;
                int i8 = i6 + 1;
                int i9 = 0;
                while (i8 <= fVar.f1702b && i9 < this.f1672g.size()) {
                    f fVar5 = this.f1672g.get(i9);
                    while (true) {
                        fVar4 = fVar5;
                        if (i8 <= fVar4.f1702b || i9 >= this.f1672g.size() - 1) {
                            break;
                        }
                        i9++;
                        fVar5 = this.f1672g.get(i9);
                    }
                    while (i8 < fVar4.f1702b) {
                        fH += this.f1678j.h(i8) + f3;
                        i8++;
                    }
                    fVar4.f1705e = fH;
                    fH += fVar4.f1704d + f3;
                    i8++;
                }
            } else if (i6 > i7) {
                int size = this.f1672g.size() - 1;
                float fH2 = fVar2.f1705e;
                while (true) {
                    i6--;
                    if (i6 < fVar.f1702b || size < 0) {
                        break;
                    }
                    f fVar6 = this.f1672g.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i6 >= fVar3.f1702b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.f1672g.get(size);
                    }
                    while (i6 > fVar3.f1702b) {
                        fH2 -= this.f1678j.h(i6) + f3;
                        i6--;
                    }
                    fH2 -= fVar3.f1704d + f3;
                    fVar3.f1705e = fH2;
                }
            }
        }
        int size2 = this.f1672g.size();
        float fH3 = fVar.f1705e;
        int i10 = fVar.f1702b;
        int i11 = i10 - 1;
        this.f1690v = i10 == 0 ? fH3 : -3.4028235E38f;
        int i12 = iE - 1;
        this.f1691w = i10 == i12 ? (fVar.f1704d + fH3) - 1.0f : Float.MAX_VALUE;
        int i13 = i3 - 1;
        while (i13 >= 0) {
            f fVar7 = this.f1672g.get(i13);
            while (true) {
                i5 = fVar7.f1702b;
                if (i11 <= i5) {
                    break;
                }
                fH3 -= this.f1678j.h(i11) + f3;
                i11--;
            }
            fH3 -= fVar7.f1704d + f3;
            fVar7.f1705e = fH3;
            if (i5 == 0) {
                this.f1690v = fH3;
            }
            i13--;
            i11--;
        }
        float fH4 = fVar.f1705e + fVar.f1704d + f3;
        int i14 = fVar.f1702b + 1;
        int i15 = i3 + 1;
        while (i15 < size2) {
            f fVar8 = this.f1672g.get(i15);
            while (true) {
                i4 = fVar8.f1702b;
                if (i14 >= i4) {
                    break;
                }
                fH4 += this.f1678j.h(i14) + f3;
                i14++;
            }
            if (i4 == i12) {
                this.f1691w = (fVar8.f1704d + fH4) - 1.0f;
            }
            fVar8.f1705e = fH4;
            fH4 += fVar8.f1704d + f3;
            i15++;
            i14++;
        }
        this.W = false;
    }

    public boolean f(View view, boolean z3, int i3, int i4, int i5) {
        int i6;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i7 = i4 + scrollX;
                if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && (i6 = i5 + scrollY) >= childAt.getTop() && i6 < childAt.getBottom() && f(childAt, true, i3, i7 - childAt.getLeft(), i6 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z3 && view.canScrollHorizontally(-i3);
    }

    public final void g(boolean z3) {
        boolean z4 = this.f1677i0 == 2;
        if (z4) {
            setScrollingCacheEnabled(false);
            if (!this.f1683o.isFinished()) {
                this.f1683o.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f1683o.getCurrX();
                int currY = this.f1683o.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        C(currX);
                    }
                }
            }
        }
        this.B = false;
        for (int i3 = 0; i3 < this.f1672g.size(); i3++) {
            f fVar = this.f1672g.get(i3);
            if (fVar.f1703c) {
                fVar.f1703c = false;
                z4 = true;
            }
        }
        if (z4) {
            if (z3) {
                t.b0(this, this.f1675h0);
            } else {
                this.f1675h0.run();
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public b1.a getAdapter() {
        return this.f1678j;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i3, int i4) {
        if (this.f1671f0 == 2) {
            i4 = (i3 - 1) - i4;
        }
        return ((g) this.f1673g0.get(i4).getLayoutParams()).f1711f;
    }

    public int getCurrentItem() {
        return this.f1679k;
    }

    public int getOffscreenPageLimit() {
        return this.C;
    }

    public int getPageMargin() {
        return this.f1686r;
    }

    public void h() {
        int iE = this.f1678j.e();
        this.f1670f = iE;
        boolean z3 = this.f1672g.size() < (this.C * 2) + 1 && this.f1672g.size() < iE;
        int iMax = this.f1679k;
        int i3 = 0;
        boolean z4 = false;
        while (i3 < this.f1672g.size()) {
            f fVar = this.f1672g.get(i3);
            int iF = this.f1678j.f(fVar.f1701a);
            if (iF != -1) {
                if (iF == -2) {
                    this.f1672g.remove(i3);
                    i3--;
                    if (!z4) {
                        this.f1678j.s(this);
                        z4 = true;
                    }
                    this.f1678j.b(this, fVar.f1702b, fVar.f1701a);
                    int i4 = this.f1679k;
                    if (i4 == fVar.f1702b) {
                        iMax = Math.max(0, Math.min(i4, iE - 1));
                    }
                } else {
                    int i5 = fVar.f1702b;
                    if (i5 != iF) {
                        if (i5 == this.f1679k) {
                            iMax = iF;
                        }
                        fVar.f1702b = iF;
                    }
                }
                z3 = true;
            }
            i3++;
        }
        if (z4) {
            this.f1678j.d(this);
        }
        Collections.sort(this.f1672g, f1662c);
        if (z3) {
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                g gVar = (g) getChildAt(i6).getLayoutParams();
                if (!gVar.f1706a) {
                    gVar.f1708c = 0.0f;
                }
            }
            O(iMax, false, true);
            requestLayout();
        }
    }

    public final int i(int i3, float f3, int i4, int i5) {
        if (Math.abs(i5) <= this.Q || Math.abs(i4) <= this.O) {
            i3 += (int) (f3 + (i3 >= this.f1679k ? 0.4f : 0.6f));
        } else if (i4 <= 0) {
            i3++;
        }
        if (this.f1672g.size() <= 0) {
            return i3;
        }
        return Math.max(this.f1672g.get(0).f1702b, Math.min(i3, this.f1672g.get(r4.size() - 1).f1702b));
    }

    public final void j(int i3, float f3, int i4) {
        j jVar = this.f1665a0;
        if (jVar != null) {
            jVar.a(i3, f3, i4);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                j jVar2 = this.Z.get(i5);
                if (jVar2 != null) {
                    jVar2.a(i3, f3, i4);
                }
            }
        }
        j jVar3 = this.f1666b0;
        if (jVar3 != null) {
            jVar3.a(i3, f3, i4);
        }
    }

    public final void k(int i3) {
        j jVar = this.f1665a0;
        if (jVar != null) {
            jVar.d(i3);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.Z.get(i4);
                if (jVar2 != null) {
                    jVar2.d(i3);
                }
            }
        }
        j jVar3 = this.f1666b0;
        if (jVar3 != null) {
            jVar3.d(i3);
        }
    }

    public final void l(int i3) {
        j jVar = this.f1665a0;
        if (jVar != null) {
            jVar.c(i3);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.Z.get(i4);
                if (jVar2 != null) {
                    jVar2.c(i3);
                }
            }
        }
        j jVar3 = this.f1666b0;
        if (jVar3 != null) {
            jVar3.c(i3);
        }
    }

    public float m(float f3) {
        return (float) Math.sin((f3 - 0.5f) * 0.47123894f);
    }

    public final void n(boolean z3) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).setLayerType(z3 ? this.f1669e0 : 0, null);
        }
    }

    public final void o() {
        this.D = false;
        this.E = false;
        VelocityTracker velocityTracker = this.N;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.N = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.V = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f1675h0);
        Scroller scroller = this.f1683o;
        if (scroller != null && !scroller.isFinished()) {
            this.f1683o.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i3;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.f1686r <= 0 || this.f1687s == null || this.f1672g.size() <= 0 || this.f1678j == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f5 = this.f1686r / width;
        int i4 = 0;
        f fVar = this.f1672g.get(0);
        float f6 = fVar.f1705e;
        int size = this.f1672g.size();
        int i5 = fVar.f1702b;
        int i6 = this.f1672g.get(size - 1).f1702b;
        while (i5 < i6) {
            while (true) {
                i3 = fVar.f1702b;
                if (i5 <= i3 || i4 >= size) {
                    break;
                }
                i4++;
                fVar = this.f1672g.get(i4);
            }
            if (i5 == i3) {
                float f7 = fVar.f1705e;
                float f8 = fVar.f1704d;
                f3 = (f7 + f8) * width;
                f6 = f7 + f8 + f5;
            } else {
                float fH = this.f1678j.h(i5);
                f3 = (f6 + fH) * width;
                f6 += fH + f5;
            }
            if (this.f1686r + f3 > scrollX) {
                f4 = f5;
                this.f1687s.setBounds(Math.round(f3), this.f1688t, Math.round(this.f1686r + f3), this.f1689u);
                this.f1687s.draw(canvas);
            } else {
                f4 = f5;
            }
            if (f3 > scrollX + r2) {
                return;
            }
            i5++;
            f5 = f4;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            L();
            return false;
        }
        if (action != 0) {
            if (this.D) {
                return true;
            }
            if (this.E) {
                return false;
            }
        }
        if (action == 0) {
            float x3 = motionEvent.getX();
            this.K = x3;
            this.I = x3;
            float y3 = motionEvent.getY();
            this.L = y3;
            this.J = y3;
            this.M = motionEvent.getPointerId(0);
            this.E = false;
            this.f1684p = true;
            this.f1683o.computeScrollOffset();
            if (this.f1677i0 != 2 || Math.abs(this.f1683o.getFinalX() - this.f1683o.getCurrX()) <= this.R) {
                g(false);
                this.D = false;
            } else {
                this.f1683o.abortAnimation();
                this.B = false;
                E();
                this.D = true;
                K(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i3 = this.M;
            if (i3 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i3);
                float x4 = motionEvent.getX(iFindPointerIndex);
                float f3 = x4 - this.I;
                float fAbs = Math.abs(f3);
                float y4 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y4 - this.L);
                if (f3 != 0.0f && !x(this.I, f3) && f(this, false, (int) f3, (int) x4, (int) y4)) {
                    this.I = x4;
                    this.J = y4;
                    this.E = true;
                    return false;
                }
                int i4 = this.H;
                if (fAbs > i4 && fAbs * 0.5f > fAbs2) {
                    this.D = true;
                    K(true);
                    setScrollState(1);
                    float f4 = this.K;
                    float f5 = this.H;
                    this.I = f3 > 0.0f ? f4 + f5 : f4 - f5;
                    this.J = y4;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i4) {
                    this.E = true;
                }
                if (this.D && D(x4)) {
                    t.a0(this);
                }
            }
        } else if (action == 6) {
            z(motionEvent);
        }
        if (this.N == null) {
            this.N = VelocityTracker.obtain();
        }
        this.N.addMovement(motionEvent);
        return this.D;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        g gVar;
        g gVar2;
        int i5;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i3), ViewGroup.getDefaultSize(0, i4));
        int measuredWidth = getMeasuredWidth();
        this.G = Math.min(measuredWidth / 10, this.F);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i6 = 0;
        while (true) {
            boolean z3 = true;
            int i7 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            if (i6 >= childCount) {
                break;
            }
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f1706a) {
                int i8 = gVar2.f1707b;
                int i9 = i8 & 7;
                int i10 = i8 & 112;
                boolean z4 = i10 == 48 || i10 == 80;
                if (i9 != 3 && i9 != 5) {
                    z3 = false;
                }
                int i11 = Integer.MIN_VALUE;
                if (z4) {
                    i5 = Integer.MIN_VALUE;
                    i11 = 1073741824;
                } else {
                    i5 = z3 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i12 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i12 != -2) {
                    if (i12 == -1) {
                        i12 = paddingLeft;
                    }
                    i11 = 1073741824;
                } else {
                    i12 = paddingLeft;
                }
                int i13 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i13 == -2) {
                    i13 = measuredHeight;
                    i7 = i5;
                } else if (i13 == -1) {
                    i13 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i12, i11), View.MeasureSpec.makeMeasureSpec(i13, i7));
                if (z4) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z3) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i6++;
        }
        this.f1692x = View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.f1693y = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.f1694z = true;
        E();
        this.f1694z = false;
        int childCount2 = getChildCount();
        for (int i14 = 0; i14 < childCount2; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f1706a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.f1708c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), this.f1693y);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i3, Rect rect) {
        int i4;
        int i5;
        f fVarS;
        int childCount = getChildCount();
        int i6 = -1;
        if ((i3 & 2) != 0) {
            i6 = childCount;
            i4 = 0;
            i5 = 1;
        } else {
            i4 = childCount - 1;
            i5 = -1;
        }
        while (i4 != i6) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1702b == this.f1679k && childAt.requestFocus(i3, rect)) {
                return true;
            }
            i4 += i5;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        b1.a aVar = this.f1678j;
        if (aVar != null) {
            aVar.m(savedState.f1696e, savedState.f1697f);
            O(savedState.f1695d, false, true);
        } else {
            this.f1680l = savedState.f1695d;
            this.f1681m = savedState.f1696e;
            this.f1682n = savedState.f1697f;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1695d = this.f1679k;
        b1.a aVar = this.f1678j;
        if (aVar != null) {
            savedState.f1696e = aVar.n();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        if (i3 != i5) {
            int i7 = this.f1686r;
            G(i3, i5, i7, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? A() : d(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? B() : d(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return d(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return d(1);
                }
            }
        }
        return false;
    }

    public final Rect q(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    public f r(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return s(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.f1694z) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public f s(View view) {
        for (int i3 = 0; i3 < this.f1672g.size(); i3++) {
            f fVar = this.f1672g.get(i3);
            if (this.f1678j.k(view, fVar.f1701a)) {
                return fVar;
            }
        }
        return null;
    }

    public void setAdapter(b1.a aVar) {
        b1.a aVar2 = this.f1678j;
        if (aVar2 != null) {
            aVar2.q(null);
            this.f1678j.s(this);
            for (int i3 = 0; i3 < this.f1672g.size(); i3++) {
                f fVar = this.f1672g.get(i3);
                this.f1678j.b(this, fVar.f1702b, fVar.f1701a);
            }
            this.f1678j.d(this);
            this.f1672g.clear();
            H();
            this.f1679k = 0;
            scrollTo(0, 0);
        }
        b1.a aVar3 = this.f1678j;
        this.f1678j = aVar;
        this.f1670f = 0;
        if (aVar != null) {
            if (this.f1685q == null) {
                this.f1685q = new l();
            }
            this.f1678j.q(this.f1685q);
            this.B = false;
            boolean z3 = this.V;
            this.V = true;
            this.f1670f = this.f1678j.e();
            if (this.f1680l >= 0) {
                this.f1678j.m(this.f1681m, this.f1682n);
                O(this.f1680l, false, true);
                this.f1680l = -1;
                this.f1681m = null;
                this.f1682n = null;
            } else if (z3) {
                requestLayout();
            } else {
                E();
            }
        }
        List<i> list = this.f1667c0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.f1667c0.size();
        for (int i4 = 0; i4 < size; i4++) {
            this.f1667c0.get(i4).b(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i3) {
        this.B = false;
        O(i3, !this.V, false);
    }

    public void setOffscreenPageLimit(int i3) {
        if (i3 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i3 + " too small; defaulting to 1");
            i3 = 1;
        }
        if (i3 != this.C) {
            this.C = i3;
            E();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.f1665a0 = jVar;
    }

    public void setPageMargin(int i3) {
        int i4 = this.f1686r;
        this.f1686r = i3;
        int width = getWidth();
        G(width, width, i3, i4);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f1687s = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i3) {
        if (this.f1677i0 == i3) {
            return;
        }
        this.f1677i0 = i3;
        if (this.f1668d0 != null) {
            n(i3 != 0);
        }
        l(i3);
    }

    public final f t() {
        int i3;
        int clientWidth = getClientWidth();
        float f3 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f4 = clientWidth > 0 ? this.f1686r / clientWidth : 0.0f;
        f fVar = null;
        int i4 = 0;
        int i5 = -1;
        boolean z3 = true;
        float f5 = 0.0f;
        while (i4 < this.f1672g.size()) {
            f fVar2 = this.f1672g.get(i4);
            if (!z3 && fVar2.f1702b != (i3 = i5 + 1)) {
                fVar2 = this.f1674h;
                fVar2.f1705e = f3 + f5 + f4;
                fVar2.f1702b = i3;
                fVar2.f1704d = this.f1678j.h(i3);
                i4--;
            }
            f3 = fVar2.f1705e;
            float f6 = fVar2.f1704d + f3 + f4;
            if (!z3 && scrollX < f3) {
                return fVar;
            }
            if (scrollX < f6 || i4 == this.f1672g.size() - 1) {
                return fVar2;
            }
            i5 = fVar2.f1702b;
            f5 = fVar2.f1704d;
            i4++;
            z3 = false;
            fVar = fVar2;
        }
        return fVar;
    }

    public f u(int i3) {
        for (int i4 = 0; i4 < this.f1672g.size(); i4++) {
            f fVar = this.f1672g.get(i4);
            if (fVar.f1702b == i3) {
                return fVar;
            }
        }
        return null;
    }

    public void v() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f1683o = new Scroller(context, f1663d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f3 = context.getResources().getDisplayMetrics().density;
        this.H = viewConfiguration.getScaledPagingTouchSlop();
        this.O = (int) (400.0f * f3);
        this.P = viewConfiguration.getScaledMaximumFlingVelocity();
        this.T = new EdgeEffect(context);
        this.U = new EdgeEffect(context);
        this.Q = (int) (25.0f * f3);
        this.R = (int) (2.0f * f3);
        this.F = (int) (f3 * 16.0f);
        t.j0(this, new h());
        if (t.x(this) == 0) {
            t.t0(this, 1);
        }
        t.v0(this, new d());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1687s;
    }

    public final boolean x(float f3, float f4) {
        return (f3 < ((float) this.G) && f4 > 0.0f) || (f3 > ((float) (getWidth() - this.G)) && f4 < 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void y(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.Y
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6b
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r1
        L1b:
            if (r7 >= r6) goto L6b
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r9 = (androidx.viewpager.widget.ViewPager.g) r9
            boolean r10 = r9.f1706a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f1707b
            r9 = r9 & 7
            if (r9 == r2) goto L4d
            r10 = 3
            if (r9 == r10) goto L47
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5c
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
            goto L59
        L47:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5c
        L4d:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
        L59:
            r11 = r9
            r9 = r3
            r3 = r11
        L5c:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L67
            r8.offsetLeftAndRight(r3)
        L67:
            r3 = r9
        L68:
            int r7 = r7 + 1
            goto L1b
        L6b:
            r12.j(r13, r14, r15)
            androidx.viewpager.widget.ViewPager$k r13 = r12.f1668d0
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.f1706a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.f1668d0
            r3.a(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.X = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.y(int, float, int):void");
    }

    public final void z(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.M) {
            int i3 = actionIndex == 0 ? 1 : 0;
            this.I = motionEvent.getX(i3);
            this.M = motionEvent.getPointerId(i3);
            VelocityTracker velocityTracker = this.N;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1706a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1707b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1708c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1709d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1710e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1711f;

        public g() {
            super(-1, -1);
            this.f1708c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1708c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f1661b);
            this.f1707b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i3) {
        setPageMarginDrawable(b0.a.d(getContext(), i3));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1672g = new ArrayList<>();
        this.f1674h = new f();
        this.f1676i = new Rect();
        this.f1680l = -1;
        this.f1681m = null;
        this.f1682n = null;
        this.f1690v = -3.4028235E38f;
        this.f1691w = Float.MAX_VALUE;
        this.C = 1;
        this.M = -1;
        this.V = true;
        this.W = false;
        this.f1675h0 = new c();
        this.f1677i0 = 0;
        v();
    }
}
