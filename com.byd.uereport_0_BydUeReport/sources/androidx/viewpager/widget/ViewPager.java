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
import o0.b0;
import o0.p;
import o0.t;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1866d = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<f> f1867e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Interpolator f1868f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n f1869g = new n();
    public int A;
    public boolean B;
    public boolean C;
    public boolean D;
    public int E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;
    public int J;
    public float K;
    public float L;
    public float M;
    public float N;
    public int O;
    public VelocityTracker P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public boolean U;
    public EdgeEffect V;
    public EdgeEffect W;
    public boolean X;
    public boolean Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f1870a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public List<j> f1871b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public j f1872c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public j f1873d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public List<i> f1874e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public k f1875f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f1876g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1877h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f1878h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList<f> f1879i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public ArrayList<View> f1880i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final f f1881j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final Runnable f1882j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Rect f1883k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f1884k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public n1.a f1885l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1886m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1887n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Parcelable f1888o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ClassLoader f1889p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Scroller f1890q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1891r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public l f1892s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1893t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Drawable f1894u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1895v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1896w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f1897x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1898y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1899z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1900f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Parcelable f1901g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public ClassLoader f1902h;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f1900f + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f1900f);
            parcel.writeParcelable(this.f1901g, i8);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f1900f = parcel.readInt();
            this.f1901g = parcel.readParcelable(classLoader);
            this.f1902h = classLoader;
        }
    }

    public static class a implements Comparator<f> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f1907b - fVar2.f1907b;
        }
    }

    public static class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f8) {
            float f9 = f8 - 1.0f;
            return (f9 * f9 * f9 * f9 * f9) + 1.0f;
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
        public final Rect f1904a = new Rect();

        public d() {
        }

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            b0 b0VarX = t.X(view, b0Var);
            if (b0VarX.m()) {
                return b0VarX;
            }
            Rect rect = this.f1904a;
            rect.left = b0VarX.f();
            rect.top = b0VarX.h();
            rect.right = b0VarX.g();
            rect.bottom = b0VarX.e();
            int childCount = ViewPager.this.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                b0 b0VarH = t.h(ViewPager.this.getChildAt(i8), b0VarX);
                rect.left = Math.min(b0VarH.f(), rect.left);
                rect.top = Math.min(b0VarH.h(), rect.top);
                rect.right = Math.min(b0VarH.g(), rect.right);
                rect.bottom = Math.min(b0VarH.e(), rect.bottom);
            }
            return b0VarX.n(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f1906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1907b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1909d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1910e;
    }

    public class h extends o0.a {
        public h() {
        }

        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            n1.a aVar;
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.f1885l) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.e());
            accessibilityEvent.setFromIndex(ViewPager.this.f1886m);
            accessibilityEvent.setToIndex(ViewPager.this.f1886m);
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            cVar.c0(ViewPager.class.getName());
            cVar.x0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        @Override // o0.a
        public boolean j(View view, int i8, Bundle bundle) {
            if (super.j(view, i8, bundle)) {
                return true;
            }
            if (i8 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f1886m + 1);
                return true;
            }
            if (i8 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f1886m - 1);
            return true;
        }

        public final boolean n() {
            n1.a aVar = ViewPager.this.f1885l;
            return aVar != null && aVar.e() > 1;
        }
    }

    public interface i {
        void a(ViewPager viewPager, n1.a aVar, n1.a aVar2);
    }

    public interface j {
        void onPageScrollStateChanged(int i8);

        void onPageScrolled(int i8, float f8, int i9);

        void onPageSelected(int i8);
    }

    public interface k {
        void transformPage(View view, float f8);
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

    public static class m implements j {
        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i8) {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i8, float f8, int i9) {
        }
    }

    public static class n implements Comparator<View> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z7 = gVar.f1911a;
            return z7 != gVar2.f1911a ? z7 ? 1 : -1 : gVar.f1915e - gVar2.f1915e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f1879i = new ArrayList<>();
        this.f1881j = new f();
        this.f1883k = new Rect();
        this.f1887n = -1;
        this.f1888o = null;
        this.f1889p = null;
        this.f1897x = -3.4028235E38f;
        this.f1898y = Float.MAX_VALUE;
        this.E = 1;
        this.O = -1;
        this.X = true;
        this.Y = false;
        this.f1882j0 = new c();
        this.f1884k0 = 0;
        v();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z7) {
        if (this.C != z7) {
            this.C = z7;
        }
    }

    public static boolean w(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    public boolean A() {
        int i8 = this.f1886m;
        if (i8 <= 0) {
            return false;
        }
        N(i8 - 1, true);
        return true;
    }

    public boolean B() {
        n1.a aVar = this.f1885l;
        if (aVar == null || this.f1886m >= aVar.e() - 1) {
            return false;
        }
        N(this.f1886m + 1, true);
        return true;
    }

    public final boolean C(int i8) {
        if (this.f1879i.size() == 0) {
            if (this.X) {
                return false;
            }
            this.Z = false;
            y(0, 0.0f, 0);
            if (this.Z) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarT = t();
        int clientWidth = getClientWidth();
        int i9 = this.f1893t;
        int i10 = clientWidth + i9;
        float f8 = clientWidth;
        int i11 = fVarT.f1907b;
        float f9 = ((i8 / f8) - fVarT.f1910e) / (fVarT.f1909d + (i9 / f8));
        this.Z = false;
        y(i11, f9, (int) (i10 * f9));
        if (this.Z) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final boolean D(float f8) {
        boolean z7;
        boolean z8;
        float f9 = this.K - f8;
        this.K = f8;
        float scrollX = getScrollX() + f9;
        float clientWidth = getClientWidth();
        float f10 = this.f1897x * clientWidth;
        float f11 = this.f1898y * clientWidth;
        boolean z9 = false;
        f fVar = this.f1879i.get(0);
        ArrayList<f> arrayList = this.f1879i;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.f1907b != 0) {
            f10 = fVar.f1910e * clientWidth;
            z7 = false;
        } else {
            z7 = true;
        }
        if (fVar2.f1907b != this.f1885l.e() - 1) {
            f11 = fVar2.f1910e * clientWidth;
            z8 = false;
        } else {
            z8 = true;
        }
        if (scrollX < f10) {
            if (z7) {
                this.V.onPull(Math.abs(f10 - scrollX) / clientWidth);
                z9 = true;
            }
            scrollX = f10;
        } else if (scrollX > f11) {
            if (z8) {
                this.W.onPull(Math.abs(scrollX - f11) / clientWidth);
                z9 = true;
            }
            scrollX = f11;
        }
        int i8 = (int) scrollX;
        this.K += scrollX - i8;
        scrollTo(i8, getScrollY());
        C(i8);
        return z9;
    }

    public void E() {
        F(this.f1886m);
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

    public final void G(int i8, int i9, int i10, int i11) {
        if (i9 > 0 && !this.f1879i.isEmpty()) {
            if (!this.f1890q.isFinished()) {
                this.f1890q.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i9 - getPaddingLeft()) - getPaddingRight()) + i11)) * (((i8 - getPaddingLeft()) - getPaddingRight()) + i10)), getScrollY());
                return;
            }
        }
        f fVarU = u(this.f1886m);
        int iMin = (int) ((fVarU != null ? Math.min(fVarU.f1910e, this.f1898y) : 0.0f) * ((i8 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            g(false);
            scrollTo(iMin, getScrollY());
        }
    }

    public final void H() {
        int i8 = 0;
        while (i8 < getChildCount()) {
            if (!((g) getChildAt(i8).getLayoutParams()).f1911a) {
                removeViewAt(i8);
                i8--;
            }
            i8++;
        }
    }

    public void I(i iVar) {
        List<i> list = this.f1874e0;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public void J(j jVar) {
        List<j> list = this.f1871b0;
        if (list != null) {
            list.remove(jVar);
        }
    }

    public final void K(boolean z7) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z7);
        }
    }

    public final boolean L() {
        this.O = -1;
        o();
        this.V.onRelease();
        this.W.onRelease();
        return this.V.isFinished() || this.W.isFinished();
    }

    public final void M(int i8, boolean z7, int i9, boolean z8) {
        f fVarU = u(i8);
        int clientWidth = fVarU != null ? (int) (getClientWidth() * Math.max(this.f1897x, Math.min(fVarU.f1910e, this.f1898y))) : 0;
        if (z7) {
            R(clientWidth, 0, i9);
            if (z8) {
                k(i8);
                return;
            }
            return;
        }
        if (z8) {
            k(i8);
        }
        g(false);
        scrollTo(clientWidth, 0);
        C(clientWidth);
    }

    public void N(int i8, boolean z7) {
        this.D = false;
        O(i8, z7, false);
    }

    public void O(int i8, boolean z7, boolean z8) {
        P(i8, z7, z8, 0);
    }

    public void P(int i8, boolean z7, boolean z8, int i9) {
        n1.a aVar = this.f1885l;
        if (aVar == null || aVar.e() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z8 && this.f1886m == i8 && this.f1879i.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i8 < 0) {
            i8 = 0;
        } else if (i8 >= this.f1885l.e()) {
            i8 = this.f1885l.e() - 1;
        }
        int i10 = this.E;
        int i11 = this.f1886m;
        if (i8 > i11 + i10 || i8 < i11 - i10) {
            for (int i12 = 0; i12 < this.f1879i.size(); i12++) {
                this.f1879i.get(i12).f1908c = true;
            }
        }
        boolean z9 = this.f1886m != i8;
        if (!this.X) {
            F(i8);
            M(i8, z7, i9, z9);
        } else {
            this.f1886m = i8;
            if (z9) {
                k(i8);
            }
            requestLayout();
        }
    }

    public j Q(j jVar) {
        j jVar2 = this.f1873d0;
        this.f1873d0 = jVar;
        return jVar2;
    }

    public void R(int i8, int i9, int i10) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.f1890q;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.f1891r ? this.f1890q.getCurrX() : this.f1890q.getStartX();
            this.f1890q.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i11 = scrollX;
        int scrollY = getScrollY();
        int i12 = i8 - i11;
        int i13 = i9 - scrollY;
        if (i12 == 0 && i13 == 0) {
            g(false);
            E();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i14 = clientWidth / 2;
        float f8 = clientWidth;
        float f9 = i14;
        float fM = f9 + (m(Math.min(1.0f, (Math.abs(i12) * 1.0f) / f8)) * f9);
        int iAbs = Math.abs(i10);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fM / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i12) / ((f8 * this.f1885l.h(this.f1886m)) + this.f1893t)) + 1.0f) * 100.0f), 600);
        this.f1891r = false;
        this.f1890q.startScroll(i11, scrollY, i12, i13, iMin);
        t.b0(this);
    }

    public final void S() {
        if (this.f1878h0 != 0) {
            ArrayList<View> arrayList = this.f1880i0;
            if (arrayList == null) {
                this.f1880i0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                this.f1880i0.add(getChildAt(i8));
            }
            Collections.sort(this.f1880i0, f1869g);
        }
    }

    public f a(int i8, int i9) {
        f fVar = new f();
        fVar.f1907b = i8;
        fVar.f1906a = this.f1885l.j(this, i8);
        fVar.f1909d = this.f1885l.h(i8);
        if (i9 < 0 || i9 >= this.f1879i.size()) {
            this.f1879i.add(fVar);
        } else {
            this.f1879i.add(i9, fVar);
        }
        return fVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i8, int i9) {
        f fVarS;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i10 = 0; i10 < getChildCount(); i10++) {
                View childAt = getChildAt(i10);
                if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1907b == this.f1886m) {
                    childAt.addFocusables(arrayList, i8, i9);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i9 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarS;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1907b == this.f1886m) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zW = gVar.f1911a | w(view);
        gVar.f1911a = zW;
        if (!this.B) {
            super.addView(view, i8, layoutParams);
        } else {
            if (zW) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f1914d = true;
            addViewInLayout(view, i8, layoutParams);
        }
    }

    public void b(i iVar) {
        if (this.f1874e0 == null) {
            this.f1874e0 = new ArrayList();
        }
        this.f1874e0.add(iVar);
    }

    public void c(j jVar) {
        if (this.f1871b0 == null) {
            this.f1871b0 = new ArrayList();
        }
        this.f1871b0.add(jVar);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i8) {
        if (this.f1885l == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i8 < 0 ? scrollX > ((int) (((float) clientWidth) * this.f1897x)) : i8 > 0 && scrollX < ((int) (((float) clientWidth) * this.f1898y));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.f1891r = true;
        if (this.f1890q.isFinished() || !this.f1890q.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f1890q.getCurrX();
        int currY = this.f1890q.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!C(currX)) {
                this.f1890q.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.b0(this);
    }

    public boolean d(int i8) {
        boolean z7;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zA = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z7 = false;
                    break;
                }
                if (parent == this) {
                    z7 = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z7) {
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
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i8);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i8 == 17) {
                zRequestFocus = (viewFindFocus == null || q(this.f1883k, viewFindNextFocus).left < q(this.f1883k, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : A();
            } else if (i8 == 66) {
                zRequestFocus = (viewFindFocus == null || q(this.f1883k, viewFindNextFocus).left > q(this.f1883k, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : B();
            }
            zA = zRequestFocus;
        } else if (i8 == 17 || i8 == 1) {
            zA = A();
        } else if (i8 == 66 || i8 == 2) {
            zA = B();
        }
        if (zA) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i8));
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
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1907b == this.f1886m && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        n1.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.f1885l) != null && aVar.e() > 1)) {
            if (!this.V.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.f1897x * width);
                this.V.setSize(height, width);
                zDraw = false | this.V.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.W.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.f1898y + 1.0f)) * width2);
                this.W.setSize(height2, width2);
                zDraw |= this.W.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.V.finish();
            this.W.finish();
        }
        if (zDraw) {
            t.b0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1894u;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e(f fVar, int i8, f fVar2) {
        int i9;
        int i10;
        f fVar3;
        f fVar4;
        int iE = this.f1885l.e();
        int clientWidth = getClientWidth();
        float f8 = clientWidth > 0 ? this.f1893t / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i11 = fVar2.f1907b;
            int i12 = fVar.f1907b;
            if (i11 < i12) {
                float fH = fVar2.f1910e + fVar2.f1909d + f8;
                int i13 = i11 + 1;
                int i14 = 0;
                while (i13 <= fVar.f1907b && i14 < this.f1879i.size()) {
                    f fVar5 = this.f1879i.get(i14);
                    while (true) {
                        fVar4 = fVar5;
                        if (i13 <= fVar4.f1907b || i14 >= this.f1879i.size() - 1) {
                            break;
                        }
                        i14++;
                        fVar5 = this.f1879i.get(i14);
                    }
                    while (i13 < fVar4.f1907b) {
                        fH += this.f1885l.h(i13) + f8;
                        i13++;
                    }
                    fVar4.f1910e = fH;
                    fH += fVar4.f1909d + f8;
                    i13++;
                }
            } else if (i11 > i12) {
                int size = this.f1879i.size() - 1;
                float fH2 = fVar2.f1910e;
                while (true) {
                    i11--;
                    if (i11 < fVar.f1907b || size < 0) {
                        break;
                    }
                    f fVar6 = this.f1879i.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i11 >= fVar3.f1907b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.f1879i.get(size);
                    }
                    while (i11 > fVar3.f1907b) {
                        fH2 -= this.f1885l.h(i11) + f8;
                        i11--;
                    }
                    fH2 -= fVar3.f1909d + f8;
                    fVar3.f1910e = fH2;
                }
            }
        }
        int size2 = this.f1879i.size();
        float fH3 = fVar.f1910e;
        int i15 = fVar.f1907b;
        int i16 = i15 - 1;
        this.f1897x = i15 == 0 ? fH3 : -3.4028235E38f;
        int i17 = iE - 1;
        this.f1898y = i15 == i17 ? (fVar.f1909d + fH3) - 1.0f : Float.MAX_VALUE;
        int i18 = i8 - 1;
        while (i18 >= 0) {
            f fVar7 = this.f1879i.get(i18);
            while (true) {
                i10 = fVar7.f1907b;
                if (i16 <= i10) {
                    break;
                }
                fH3 -= this.f1885l.h(i16) + f8;
                i16--;
            }
            fH3 -= fVar7.f1909d + f8;
            fVar7.f1910e = fH3;
            if (i10 == 0) {
                this.f1897x = fH3;
            }
            i18--;
            i16--;
        }
        float fH4 = fVar.f1910e + fVar.f1909d + f8;
        int i19 = fVar.f1907b + 1;
        int i20 = i8 + 1;
        while (i20 < size2) {
            f fVar8 = this.f1879i.get(i20);
            while (true) {
                i9 = fVar8.f1907b;
                if (i19 >= i9) {
                    break;
                }
                fH4 += this.f1885l.h(i19) + f8;
                i19++;
            }
            if (i9 == i17) {
                this.f1898y = (fVar8.f1909d + fH4) - 1.0f;
            }
            fVar8.f1910e = fH4;
            fH4 += fVar8.f1909d + f8;
            i20++;
            i19++;
        }
        this.Y = false;
    }

    public boolean f(View view, boolean z7, int i8, int i9, int i10) {
        int i11;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i12 = i9 + scrollX;
                if (i12 >= childAt.getLeft() && i12 < childAt.getRight() && (i11 = i10 + scrollY) >= childAt.getTop() && i11 < childAt.getBottom() && f(childAt, true, i8, i12 - childAt.getLeft(), i11 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z7 && view.canScrollHorizontally(-i8);
    }

    public final void g(boolean z7) {
        boolean z8 = this.f1884k0 == 2;
        if (z8) {
            setScrollingCacheEnabled(false);
            if (!this.f1890q.isFinished()) {
                this.f1890q.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f1890q.getCurrX();
                int currY = this.f1890q.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        C(currX);
                    }
                }
            }
        }
        this.D = false;
        for (int i8 = 0; i8 < this.f1879i.size(); i8++) {
            f fVar = this.f1879i.get(i8);
            if (fVar.f1908c) {
                fVar.f1908c = false;
                z8 = true;
            }
        }
        if (z8) {
            if (z7) {
                t.d0(this, this.f1882j0);
            } else {
                this.f1882j0.run();
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

    public n1.a getAdapter() {
        return this.f1885l;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i8, int i9) {
        if (this.f1878h0 == 2) {
            i9 = (i8 - 1) - i9;
        }
        return ((g) this.f1880i0.get(i9).getLayoutParams()).f1916f;
    }

    public int getCurrentItem() {
        return this.f1886m;
    }

    public int getOffscreenPageLimit() {
        return this.E;
    }

    public int getPageMargin() {
        return this.f1893t;
    }

    public void h() {
        int iE = this.f1885l.e();
        this.f1877h = iE;
        boolean z7 = this.f1879i.size() < (this.E * 2) + 1 && this.f1879i.size() < iE;
        int iMax = this.f1886m;
        int i8 = 0;
        boolean z8 = false;
        while (i8 < this.f1879i.size()) {
            f fVar = this.f1879i.get(i8);
            int iF = this.f1885l.f(fVar.f1906a);
            if (iF != -1) {
                if (iF == -2) {
                    this.f1879i.remove(i8);
                    i8--;
                    if (!z8) {
                        this.f1885l.t(this);
                        z8 = true;
                    }
                    this.f1885l.b(this, fVar.f1907b, fVar.f1906a);
                    int i9 = this.f1886m;
                    if (i9 == fVar.f1907b) {
                        iMax = Math.max(0, Math.min(i9, iE - 1));
                    }
                } else {
                    int i10 = fVar.f1907b;
                    if (i10 != iF) {
                        if (i10 == this.f1886m) {
                            iMax = iF;
                        }
                        fVar.f1907b = iF;
                    }
                }
                z7 = true;
            }
            i8++;
        }
        if (z8) {
            this.f1885l.d(this);
        }
        Collections.sort(this.f1879i, f1867e);
        if (z7) {
            int childCount = getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                g gVar = (g) getChildAt(i11).getLayoutParams();
                if (!gVar.f1911a) {
                    gVar.f1913c = 0.0f;
                }
            }
            O(iMax, false, true);
            requestLayout();
        }
    }

    public final int i(int i8, float f8, int i9, int i10) {
        if (Math.abs(i10) <= this.S || Math.abs(i9) <= this.Q) {
            i8 += (int) (f8 + (i8 >= this.f1886m ? 0.4f : 0.6f));
        } else if (i9 <= 0) {
            i8++;
        }
        if (this.f1879i.size() <= 0) {
            return i8;
        }
        return Math.max(this.f1879i.get(0).f1907b, Math.min(i8, this.f1879i.get(r4.size() - 1).f1907b));
    }

    public final void j(int i8, float f8, int i9) {
        j jVar = this.f1872c0;
        if (jVar != null) {
            jVar.onPageScrolled(i8, f8, i9);
        }
        List<j> list = this.f1871b0;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                j jVar2 = this.f1871b0.get(i10);
                if (jVar2 != null) {
                    jVar2.onPageScrolled(i8, f8, i9);
                }
            }
        }
        j jVar3 = this.f1873d0;
        if (jVar3 != null) {
            jVar3.onPageScrolled(i8, f8, i9);
        }
    }

    public final void k(int i8) {
        j jVar = this.f1872c0;
        if (jVar != null) {
            jVar.onPageSelected(i8);
        }
        List<j> list = this.f1871b0;
        if (list != null) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                j jVar2 = this.f1871b0.get(i9);
                if (jVar2 != null) {
                    jVar2.onPageSelected(i8);
                }
            }
        }
        j jVar3 = this.f1873d0;
        if (jVar3 != null) {
            jVar3.onPageSelected(i8);
        }
    }

    public final void l(int i8) {
        j jVar = this.f1872c0;
        if (jVar != null) {
            jVar.onPageScrollStateChanged(i8);
        }
        List<j> list = this.f1871b0;
        if (list != null) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                j jVar2 = this.f1871b0.get(i9);
                if (jVar2 != null) {
                    jVar2.onPageScrollStateChanged(i8);
                }
            }
        }
        j jVar3 = this.f1873d0;
        if (jVar3 != null) {
            jVar3.onPageScrollStateChanged(i8);
        }
    }

    public float m(float f8) {
        return (float) Math.sin((f8 - 0.5f) * 0.47123894f);
    }

    public final void n(boolean z7) {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            getChildAt(i8).setLayerType(z7 ? this.f1876g0 : 0, null);
        }
    }

    public final void o() {
        this.F = false;
        this.G = false;
        VelocityTracker velocityTracker = this.P;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.P = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.X = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f1882j0);
        Scroller scroller = this.f1890q;
        if (scroller != null && !scroller.isFinished()) {
            this.f1890q.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i8;
        float f8;
        float f9;
        super.onDraw(canvas);
        if (this.f1893t <= 0 || this.f1894u == null || this.f1879i.size() <= 0 || this.f1885l == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f10 = this.f1893t / width;
        int i9 = 0;
        f fVar = this.f1879i.get(0);
        float f11 = fVar.f1910e;
        int size = this.f1879i.size();
        int i10 = fVar.f1907b;
        int i11 = this.f1879i.get(size - 1).f1907b;
        while (i10 < i11) {
            while (true) {
                i8 = fVar.f1907b;
                if (i10 <= i8 || i9 >= size) {
                    break;
                }
                i9++;
                fVar = this.f1879i.get(i9);
            }
            if (i10 == i8) {
                float f12 = fVar.f1910e;
                float f13 = fVar.f1909d;
                f8 = (f12 + f13) * width;
                f11 = f12 + f13 + f10;
            } else {
                float fH = this.f1885l.h(i10);
                f8 = (f11 + fH) * width;
                f11 += fH + f10;
            }
            if (this.f1893t + f8 > scrollX) {
                f9 = f10;
                this.f1894u.setBounds(Math.round(f8), this.f1895v, Math.round(this.f1893t + f8), this.f1896w);
                this.f1894u.draw(canvas);
            } else {
                f9 = f10;
            }
            if (f8 > scrollX + r2) {
                return;
            }
            i10++;
            f10 = f9;
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
            if (this.F) {
                return true;
            }
            if (this.G) {
                return false;
            }
        }
        if (action == 0) {
            float x7 = motionEvent.getX();
            this.M = x7;
            this.K = x7;
            float y7 = motionEvent.getY();
            this.N = y7;
            this.L = y7;
            this.O = motionEvent.getPointerId(0);
            this.G = false;
            this.f1891r = true;
            this.f1890q.computeScrollOffset();
            if (this.f1884k0 != 2 || Math.abs(this.f1890q.getFinalX() - this.f1890q.getCurrX()) <= this.T) {
                g(false);
                this.F = false;
            } else {
                this.f1890q.abortAnimation();
                this.D = false;
                E();
                this.F = true;
                K(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i8 = this.O;
            if (i8 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i8);
                float x8 = motionEvent.getX(iFindPointerIndex);
                float f8 = x8 - this.K;
                float fAbs = Math.abs(f8);
                float y8 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y8 - this.N);
                if (f8 != 0.0f && !x(this.K, f8) && f(this, false, (int) f8, (int) x8, (int) y8)) {
                    this.K = x8;
                    this.L = y8;
                    this.G = true;
                    return false;
                }
                int i9 = this.J;
                if (fAbs > i9 && fAbs * 0.5f > fAbs2) {
                    this.F = true;
                    K(true);
                    setScrollState(1);
                    float f9 = this.M;
                    float f10 = this.J;
                    this.K = f8 > 0.0f ? f9 + f10 : f9 - f10;
                    this.L = y8;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i9) {
                    this.G = true;
                }
                if (this.F && D(x8)) {
                    t.b0(this);
                }
            }
        } else if (action == 6) {
            z(motionEvent);
        }
        if (this.P == null) {
            this.P = VelocityTracker.obtain();
        }
        this.P.addMovement(motionEvent);
        return this.F;
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
    public void onMeasure(int i8, int i9) {
        g gVar;
        g gVar2;
        int i10;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i8), ViewGroup.getDefaultSize(0, i9));
        int measuredWidth = getMeasuredWidth();
        this.I = Math.min(measuredWidth / 10, this.H);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i11 = 0;
        while (true) {
            boolean z7 = true;
            int i12 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            if (i11 >= childCount) {
                break;
            }
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f1911a) {
                int i13 = gVar2.f1912b;
                int i14 = i13 & 7;
                int i15 = i13 & 112;
                boolean z8 = i15 == 48 || i15 == 80;
                if (i14 != 3 && i14 != 5) {
                    z7 = false;
                }
                int i16 = Integer.MIN_VALUE;
                if (z8) {
                    i10 = Integer.MIN_VALUE;
                    i16 = 1073741824;
                } else {
                    i10 = z7 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i17 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i17 != -2) {
                    if (i17 == -1) {
                        i17 = paddingLeft;
                    }
                    i16 = 1073741824;
                } else {
                    i17 = paddingLeft;
                }
                int i18 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i18 == -2) {
                    i18 = measuredHeight;
                    i12 = i10;
                } else if (i18 == -1) {
                    i18 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i17, i16), View.MeasureSpec.makeMeasureSpec(i18, i12));
                if (z8) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z7) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i11++;
        }
        this.f1899z = View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.A = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.B = true;
        E();
        this.B = false;
        int childCount2 = getChildCount();
        for (int i19 = 0; i19 < childCount2; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f1911a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.f1913c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), this.A);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i8, Rect rect) {
        int i9;
        int i10;
        f fVarS;
        int childCount = getChildCount();
        int i11 = -1;
        if ((i8 & 2) != 0) {
            i11 = childCount;
            i9 = 0;
            i10 = 1;
        } else {
            i9 = childCount - 1;
            i10 = -1;
        }
        while (i9 != i11) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f1907b == this.f1886m && childAt.requestFocus(i8, rect)) {
                return true;
            }
            i9 += i10;
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
        n1.a aVar = this.f1885l;
        if (aVar != null) {
            aVar.n(savedState.f1901g, savedState.f1902h);
            O(savedState.f1900f, false, true);
        } else {
            this.f1887n = savedState.f1900f;
            this.f1888o = savedState.f1901g;
            this.f1889p = savedState.f1902h;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1900f = this.f1886m;
        n1.a aVar = this.f1885l;
        if (aVar != null) {
            savedState.f1901g = aVar.o();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        if (i8 != i10) {
            int i12 = this.f1893t;
            G(i8, i10, i12, i12);
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
        if (this.B) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public f s(View view) {
        for (int i8 = 0; i8 < this.f1879i.size(); i8++) {
            f fVar = this.f1879i.get(i8);
            if (this.f1885l.k(view, fVar.f1906a)) {
                return fVar;
            }
        }
        return null;
    }

    public void setAdapter(n1.a aVar) {
        n1.a aVar2 = this.f1885l;
        if (aVar2 != null) {
            aVar2.r(null);
            this.f1885l.t(this);
            for (int i8 = 0; i8 < this.f1879i.size(); i8++) {
                f fVar = this.f1879i.get(i8);
                this.f1885l.b(this, fVar.f1907b, fVar.f1906a);
            }
            this.f1885l.d(this);
            this.f1879i.clear();
            H();
            this.f1886m = 0;
            scrollTo(0, 0);
        }
        n1.a aVar3 = this.f1885l;
        this.f1885l = aVar;
        this.f1877h = 0;
        if (aVar != null) {
            if (this.f1892s == null) {
                this.f1892s = new l();
            }
            this.f1885l.r(this.f1892s);
            this.D = false;
            boolean z7 = this.X;
            this.X = true;
            this.f1877h = this.f1885l.e();
            if (this.f1887n >= 0) {
                this.f1885l.n(this.f1888o, this.f1889p);
                O(this.f1887n, false, true);
                this.f1887n = -1;
                this.f1888o = null;
                this.f1889p = null;
            } else if (z7) {
                requestLayout();
            } else {
                E();
            }
        }
        List<i> list = this.f1874e0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.f1874e0.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f1874e0.get(i9).a(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i8) {
        this.D = false;
        O(i8, !this.X, false);
    }

    public void setOffscreenPageLimit(int i8) {
        if (i8 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i8 + " too small; defaulting to 1");
            i8 = 1;
        }
        if (i8 != this.E) {
            this.E = i8;
            E();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.f1872c0 = jVar;
    }

    public void setPageMargin(int i8) {
        int i9 = this.f1893t;
        this.f1893t = i8;
        int width = getWidth();
        G(width, width, i8, i9);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f1894u = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i8) {
        if (this.f1884k0 == i8) {
            return;
        }
        this.f1884k0 = i8;
        if (this.f1875f0 != null) {
            n(i8 != 0);
        }
        l(i8);
    }

    public final f t() {
        int i8;
        int clientWidth = getClientWidth();
        float f8 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f9 = clientWidth > 0 ? this.f1893t / clientWidth : 0.0f;
        f fVar = null;
        int i9 = 0;
        int i10 = -1;
        boolean z7 = true;
        float f10 = 0.0f;
        while (i9 < this.f1879i.size()) {
            f fVar2 = this.f1879i.get(i9);
            if (!z7 && fVar2.f1907b != (i8 = i10 + 1)) {
                fVar2 = this.f1881j;
                fVar2.f1910e = f8 + f10 + f9;
                fVar2.f1907b = i8;
                fVar2.f1909d = this.f1885l.h(i8);
                i9--;
            }
            f8 = fVar2.f1910e;
            float f11 = fVar2.f1909d + f8 + f9;
            if (!z7 && scrollX < f8) {
                return fVar;
            }
            if (scrollX < f11 || i9 == this.f1879i.size() - 1) {
                return fVar2;
            }
            i10 = fVar2.f1907b;
            f10 = fVar2.f1909d;
            i9++;
            z7 = false;
            fVar = fVar2;
        }
        return fVar;
    }

    public f u(int i8) {
        for (int i9 = 0; i9 < this.f1879i.size(); i9++) {
            f fVar = this.f1879i.get(i9);
            if (fVar.f1907b == i8) {
                return fVar;
            }
        }
        return null;
    }

    public void v() {
        setWillNotDraw(false);
        setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
        setFocusable(true);
        Context context = getContext();
        this.f1890q = new Scroller(context, f1868f);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f8 = context.getResources().getDisplayMetrics().density;
        this.J = viewConfiguration.getScaledPagingTouchSlop();
        this.Q = (int) (400.0f * f8);
        this.R = viewConfiguration.getScaledMaximumFlingVelocity();
        this.V = new EdgeEffect(context);
        this.W = new EdgeEffect(context);
        this.S = (int) (25.0f * f8);
        this.T = (int) (2.0f * f8);
        this.H = (int) (f8 * 16.0f);
        t.l0(this, new h());
        if (t.y(this) == 0) {
            t.v0(this, 1);
        }
        t.y0(this, new d());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1894u;
    }

    public final boolean x(float f8, float f9) {
        return (f8 < ((float) this.I) && f9 > 0.0f) || (f8 > ((float) (getWidth() - this.I)) && f9 < 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void y(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.f1870a0
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
            boolean r10 = r9.f1911a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f1912b
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
            androidx.viewpager.widget.ViewPager$k r13 = r12.f1875f0
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.f1911a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.f1875f0
            r3.transformPage(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.Z = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.y(int, float, int):void");
    }

    public final void z(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.O) {
            int i8 = actionIndex == 0 ? 1 : 0;
            this.K = motionEvent.getX(i8);
            this.O = motionEvent.getPointerId(i8);
            VelocityTracker velocityTracker = this.P;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1911a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1912b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1913c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1914d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1915e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1916f;

        public g() {
            super(-1, -1);
            this.f1913c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1913c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f1866d);
            this.f1912b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i8) {
        setPageMarginDrawable(e0.a.d(getContext(), i8));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1879i = new ArrayList<>();
        this.f1881j = new f();
        this.f1883k = new Rect();
        this.f1887n = -1;
        this.f1888o = null;
        this.f1889p = null;
        this.f1897x = -3.4028235E38f;
        this.f1898y = Float.MAX_VALUE;
        this.E = 1;
        this.O = -1;
        this.X = true;
        this.Y = false;
        this.f1882j0 = new c();
        this.f1884k0 = 0;
        v();
    }
}
