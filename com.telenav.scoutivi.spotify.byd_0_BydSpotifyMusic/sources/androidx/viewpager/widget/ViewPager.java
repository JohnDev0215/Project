package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
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
import i0.c0;
import i0.p;
import i0.u;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    static final int[] f3215k0 = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private static final Comparator<f> f3216l0 = new a();

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private static final Interpolator f3217m0 = new b();

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private static final l f3218n0 = new l();
    private int A;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private float G;
    private float H;
    private float I;
    private float J;
    private int K;
    private VelocityTracker L;
    private int M;
    private int N;
    private int O;
    private int P;
    private boolean Q;
    private EdgeEffect R;
    private EdgeEffect S;
    private boolean T;
    private boolean U;
    private boolean V;
    private int W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private List<j> f3219a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private j f3220b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private j f3221c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private List<i> f3222d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3223e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private k f3224e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private int f3225f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ArrayList<f> f3226g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private int f3227g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f f3228h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private ArrayList<View> f3229h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f3230i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final Runnable f3231i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f3232j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private int f3233j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f3234k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Parcelable f3235l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ClassLoader f3236m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Scroller f3237n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f3238o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f3239p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Drawable f3240q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f3241r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f3242s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f3243t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f3244u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f3245v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f3246w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f3247x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f3248y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f3249z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f3250h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Parcelable f3251i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        ClassLoader f3252j;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f3250h + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f3250h);
            parcel.writeParcelable(this.f3251i, i7);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f3250h = parcel.readInt();
            this.f3251i = parcel.readParcelable(classLoader);
            this.f3252j = classLoader;
        }
    }

    static class a implements Comparator<f> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f3257b - fVar2.f3257b;
        }
    }

    static class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = f7 - 1.0f;
            return (f8 * f8 * f8 * f8 * f8) + 1.0f;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.z();
        }
    }

    class d implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Rect f3254a = new Rect();

        d() {
        }

        @Override // i0.p
        public c0 a(View view, c0 c0Var) {
            c0 c0VarW = u.W(view, c0Var);
            if (c0VarW.l()) {
                return c0VarW;
            }
            Rect rect = this.f3254a;
            rect.left = c0VarW.g();
            rect.top = c0VarW.i();
            rect.right = c0VarW.h();
            rect.bottom = c0VarW.f();
            int childCount = ViewPager.this.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                c0 c0VarF = u.f(ViewPager.this.getChildAt(i7), c0VarW);
                rect.left = Math.min(c0VarF.g(), rect.left);
                rect.top = Math.min(c0VarF.i(), rect.top);
                rect.right = Math.min(c0VarF.h(), rect.right);
                rect.bottom = Math.min(c0VarF.f(), rect.bottom);
            }
            return c0VarW.m(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f3256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3257b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3258c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f3259d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f3260e;

        f() {
        }
    }

    class h extends i0.a {
        h() {
        }

        private boolean n() {
            Objects.requireNonNull(ViewPager.this);
            return false;
        }

        @Override // i0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() == 4096) {
                Objects.requireNonNull(ViewPager.this);
            }
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.P(ViewPager.class.getName());
            cVar.h0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        @Override // i0.a
        public boolean j(View view, int i7, Bundle bundle) {
            if (super.j(view, i7, bundle)) {
                return true;
            }
            if (i7 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f3232j + 1);
                return true;
            }
            if (i7 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f3232j - 1);
            return true;
        }
    }

    public interface i {
        void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2);
    }

    public interface j {
        void a(int i7, float f7, int i8);

        void b(int i7);

        void c(int i7);
    }

    public interface k {
        void a(View view, float f7);
    }

    static class l implements Comparator<View> {
        l() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z6 = gVar.f3261a;
            return z6 != gVar2.f3261a ? z6 ? 1 : -1 : gVar.f3265e - gVar2.f3265e;
        }
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3226g = new ArrayList<>();
        this.f3228h = new f();
        this.f3230i = new Rect();
        this.f3234k = -1;
        this.f3235l = null;
        this.f3236m = null;
        this.f3243t = -3.4028235E38f;
        this.f3244u = Float.MAX_VALUE;
        this.A = 1;
        this.K = -1;
        this.T = true;
        this.U = false;
        this.f3231i0 = new c();
        this.f3233j0 = 0;
        q();
    }

    private void B(int i7, int i8, int i9, int i10) {
        if (i8 > 0 && !this.f3226g.isEmpty()) {
            if (!this.f3237n.isFinished()) {
                this.f3237n.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i8 - getPaddingLeft()) - getPaddingRight()) + i10)) * (((i7 - getPaddingLeft()) - getPaddingRight()) + i9)), getScrollY());
                return;
            }
        }
        f fVarP = p(this.f3232j);
        int iMin = (int) ((fVarP != null ? Math.min(fVarP.f3260e, this.f3244u) : 0.0f) * ((i7 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            e(false);
            scrollTo(iMin, getScrollY());
        }
    }

    private void E(boolean z6) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z6);
        }
    }

    private boolean F() {
        this.K = -1;
        k();
        this.R.onRelease();
        this.S.onRelease();
        return this.R.isFinished() || this.S.isFinished();
    }

    private void G(int i7, boolean z6, int i8, boolean z7) {
        f fVarP = p(i7);
        int clientWidth = fVarP != null ? (int) (getClientWidth() * Math.max(this.f3243t, Math.min(fVarP.f3260e, this.f3244u))) : 0;
        if (z6) {
            K(clientWidth, 0, i8);
            if (z7) {
                g(i7);
                return;
            }
            return;
        }
        if (z7) {
            g(i7);
        }
        e(false);
        scrollTo(clientWidth, 0);
        x(clientWidth);
    }

    private void L() {
        if (this.f3227g0 != 0) {
            ArrayList<View> arrayList = this.f3229h0;
            if (arrayList == null) {
                this.f3229h0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                this.f3229h0.add(getChildAt(i7));
            }
            Collections.sort(this.f3229h0, f3218n0);
        }
    }

    private void e(boolean z6) {
        boolean z7 = this.f3233j0 == 2;
        if (z7) {
            setScrollingCacheEnabled(false);
            if (!this.f3237n.isFinished()) {
                this.f3237n.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f3237n.getCurrX();
                int currY = this.f3237n.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        x(currX);
                    }
                }
            }
        }
        this.f3249z = false;
        for (int i7 = 0; i7 < this.f3226g.size(); i7++) {
            f fVar = this.f3226g.get(i7);
            if (fVar.f3258c) {
                fVar.f3258c = false;
                z7 = true;
            }
        }
        if (z7) {
            if (z6) {
                u.c0(this, this.f3231i0);
            } else {
                this.f3231i0.run();
            }
        }
    }

    private void f(int i7, float f7, int i8) {
        j jVar = this.f3220b0;
        if (jVar != null) {
            jVar.a(i7, f7, i8);
        }
        List<j> list = this.f3219a0;
        if (list != null) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                j jVar2 = this.f3219a0.get(i9);
                if (jVar2 != null) {
                    jVar2.a(i7, f7, i8);
                }
            }
        }
        j jVar3 = this.f3221c0;
        if (jVar3 != null) {
            jVar3.a(i7, f7, i8);
        }
    }

    private void g(int i7) {
        j jVar = this.f3220b0;
        if (jVar != null) {
            jVar.c(i7);
        }
        List<j> list = this.f3219a0;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                j jVar2 = this.f3219a0.get(i8);
                if (jVar2 != null) {
                    jVar2.c(i7);
                }
            }
        }
        j jVar3 = this.f3221c0;
        if (jVar3 != null) {
            jVar3.c(i7);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void h(int i7) {
        j jVar = this.f3220b0;
        if (jVar != null) {
            jVar.b(i7);
        }
        List<j> list = this.f3219a0;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                j jVar2 = this.f3219a0.get(i8);
                if (jVar2 != null) {
                    jVar2.b(i7);
                }
            }
        }
        j jVar3 = this.f3221c0;
        if (jVar3 != null) {
            jVar3.b(i7);
        }
    }

    private void j(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            getChildAt(i7).setLayerType(z6 ? this.f3225f0 : 0, null);
        }
    }

    private void k() {
        this.B = false;
        this.C = false;
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.L = null;
        }
    }

    private Rect m(Rect rect, View view) {
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

    private f o() {
        int i7;
        int clientWidth = getClientWidth();
        float f7 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f8 = clientWidth > 0 ? this.f3239p / clientWidth : 0.0f;
        int i8 = -1;
        float f9 = 0.0f;
        int i9 = 0;
        f fVar = null;
        boolean z6 = true;
        while (i9 < this.f3226g.size()) {
            f fVar2 = this.f3226g.get(i9);
            if (!z6 && fVar2.f3257b != (i7 = i8 + 1)) {
                f fVar3 = this.f3228h;
                fVar3.f3260e = f7 + f9 + f8;
                fVar3.f3257b = i7;
                throw null;
            }
            f7 = fVar2.f3260e;
            float f10 = fVar2.f3259d + f7 + f8;
            if (!z6 && scrollX < f7) {
                return fVar;
            }
            if (scrollX < f10 || i9 == this.f3226g.size() - 1) {
                return fVar2;
            }
            i8 = fVar2.f3257b;
            f9 = fVar2.f3259d;
            i9++;
            z6 = false;
            fVar = fVar2;
        }
        return fVar;
    }

    private static boolean r(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    private boolean s(float f7, float f8) {
        return (f7 < ((float) this.E) && f8 > 0.0f) || (f7 > ((float) (getWidth() - this.E)) && f8 < 0.0f);
    }

    private void setScrollingCacheEnabled(boolean z6) {
        if (this.f3248y != z6) {
            this.f3248y = z6;
        }
    }

    private void u(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.K) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.G = motionEvent.getX(i7);
            this.K = motionEvent.getPointerId(i7);
            VelocityTracker velocityTracker = this.L;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean x(int i7) {
        if (this.f3226g.size() == 0) {
            if (this.T) {
                return false;
            }
            this.V = false;
            t(0, 0.0f, 0);
            if (this.V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarO = o();
        int clientWidth = getClientWidth();
        int i8 = this.f3239p;
        int i9 = clientWidth + i8;
        float f7 = clientWidth;
        int i10 = fVarO.f3257b;
        float f8 = ((i7 / f7) - fVarO.f3260e) / (fVarO.f3259d + (i8 / f7));
        this.V = false;
        t(i10, f8, (int) (i9 * f8));
        if (this.V) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean y(float f7) {
        this.G = f7;
        getScrollX();
        getClientWidth();
        f fVar = this.f3226g.get(0);
        f fVar2 = this.f3226g.get(r0.size() - 1);
        int i7 = fVar.f3257b;
        int i8 = fVar2.f3257b;
        throw null;
    }

    void A(int i7) {
        int i8 = this.f3232j;
        if (i8 != i7) {
            p(i8);
            this.f3232j = i7;
        }
        L();
    }

    public void C(i iVar) {
        List<i> list = this.f3222d0;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public void D(j jVar) {
        List<j> list = this.f3219a0;
        if (list != null) {
            list.remove(jVar);
        }
    }

    public void H(int i7, boolean z6) {
        this.f3249z = false;
        I(i7, z6, false);
    }

    void I(int i7, boolean z6, boolean z7) {
        J(i7, z6, z7, 0);
    }

    void J(int i7, boolean z6, boolean z7, int i8) {
        setScrollingCacheEnabled(false);
    }

    void K(int i7, int i8, int i9) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.f3237n;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.f3238o ? this.f3237n.getCurrX() : this.f3237n.getStartX();
            this.f3237n.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i10 = scrollX;
        int scrollY = getScrollY();
        int i11 = i7 - i10;
        int i12 = i8 - scrollY;
        if (i11 == 0 && i12 == 0) {
            e(false);
            z();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        float f7 = clientWidth / 2;
        float fI = f7 + (i(Math.min(1.0f, (Math.abs(i11) * 1.0f) / clientWidth)) * f7);
        int iAbs = Math.abs(i9);
        if (iAbs <= 0) {
            throw null;
        }
        int iMin = Math.min(Math.round(Math.abs(fI / iAbs) * 1000.0f) * 4, 600);
        this.f3238o = false;
        this.f3237n.startScroll(i10, scrollY, i11, i12, iMin);
        u.b0(this);
    }

    public void a(i iVar) {
        if (this.f3222d0 == null) {
            this.f3222d0 = new ArrayList();
        }
        this.f3222d0.add(iVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i7, int i8) {
        f fVarN;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() == 0 && (fVarN = n(childAt)) != null && fVarN.f3257b == this.f3232j) {
                    childAt.addFocusables(arrayList, i7, i8);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i8 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarN;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 0 && (fVarN = n(childAt)) != null && fVarN.f3257b == this.f3232j) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zR = gVar.f3261a | r(view);
        gVar.f3261a = zR;
        if (!this.f3247x) {
            super.addView(view, i7, layoutParams);
        } else {
            if (zR) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f3264d = true;
            addViewInLayout(view, i7, layoutParams);
        }
    }

    public void b(j jVar) {
        if (this.f3219a0 == null) {
            this.f3219a0 = new ArrayList();
        }
        this.f3219a0.add(jVar);
    }

    public boolean c(int i7) {
        boolean z6;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zV = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z6 = false;
                    break;
                }
                if (parent == this) {
                    z6 = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z6) {
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
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i7);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i7 == 17) {
                zRequestFocus = (viewFindFocus == null || m(this.f3230i, viewFindNextFocus).left < m(this.f3230i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : v();
            } else if (i7 == 66) {
                zRequestFocus = (viewFindFocus == null || m(this.f3230i, viewFindNextFocus).left > m(this.f3230i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : w();
            }
            zV = zRequestFocus;
        } else if (i7 == 17 || i7 == 1) {
            zV = v();
        } else if (i7 == 66 || i7 == 2) {
            zV = w();
        }
        if (zV) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i7));
        }
        return zV;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i7) {
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.f3238o = true;
        if (this.f3237n.isFinished() || !this.f3237n.computeScrollOffset()) {
            e(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f3237n.getCurrX();
        int currY = this.f3237n.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!x(currX)) {
                this.f3237n.abortAnimation();
                scrollTo(0, currY);
            }
        }
        u.b0(this);
    }

    protected boolean d(View view, boolean z6, int i7, int i8, int i9) {
        int i10;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i11 = i8 + scrollX;
                if (i11 >= childAt.getLeft() && i11 < childAt.getRight() && (i10 = i9 + scrollY) >= childAt.getTop() && i10 < childAt.getBottom() && d(childAt, true, i7, i11 - childAt.getLeft(), i10 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z6 && view.canScrollHorizontally(-i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || l(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarN;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 0 && (fVarN = n(childAt)) != null && fVarN.f3257b == this.f3232j && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean zDraw = false;
        if (getOverScrollMode() != 0) {
            this.R.finish();
            this.S.finish();
        } else {
            if (!this.R.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.f3243t * width);
                this.R.setSize(height, width);
                zDraw = false | this.R.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.S.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.f3244u + 1.0f)) * width2);
                this.S.setSize(height2, width2);
                zDraw |= this.S.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        }
        if (zDraw) {
            u.b0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f3240q;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public androidx.viewpager.widget.a getAdapter() {
        return null;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i7, int i8) {
        if (this.f3227g0 == 2) {
            i8 = (i7 - 1) - i8;
        }
        return ((g) this.f3229h0.get(i8).getLayoutParams()).f3266f;
    }

    public int getCurrentItem() {
        return this.f3232j;
    }

    public int getOffscreenPageLimit() {
        return this.A;
    }

    public int getPageMargin() {
        return this.f3239p;
    }

    float i(float f7) {
        return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
    }

    public boolean l(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? v() : c(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? w() : c(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return c(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return c(1);
                }
            }
        }
        return false;
    }

    f n(View view) {
        if (this.f3226g.size() <= 0) {
            return null;
        }
        Object obj = this.f3226g.get(0).f3256a;
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.f3231i0);
        Scroller scroller = this.f3237n;
        if (scroller != null && !scroller.isFinished()) {
            this.f3237n.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f3239p <= 0 || this.f3240q == null) {
            return;
        }
        this.f3226g.size();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            F();
            return false;
        }
        if (action != 0) {
            if (this.B) {
                return true;
            }
            if (this.C) {
                return false;
            }
        }
        if (action == 0) {
            float x6 = motionEvent.getX();
            this.I = x6;
            this.G = x6;
            float y6 = motionEvent.getY();
            this.J = y6;
            this.H = y6;
            this.K = motionEvent.getPointerId(0);
            this.C = false;
            this.f3238o = true;
            this.f3237n.computeScrollOffset();
            if (this.f3233j0 != 2 || Math.abs(this.f3237n.getFinalX() - this.f3237n.getCurrX()) <= this.P) {
                e(false);
                this.B = false;
            } else {
                this.f3237n.abortAnimation();
                this.f3249z = false;
                z();
                this.B = true;
                E(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i7 = this.K;
            if (i7 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i7);
                float x7 = motionEvent.getX(iFindPointerIndex);
                float f7 = x7 - this.G;
                float fAbs = Math.abs(f7);
                float y7 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y7 - this.J);
                if (f7 != 0.0f && !s(this.G, f7) && d(this, false, (int) f7, (int) x7, (int) y7)) {
                    this.G = x7;
                    this.H = y7;
                    this.C = true;
                    return false;
                }
                int i8 = this.F;
                if (fAbs > i8 && fAbs * 0.5f > fAbs2) {
                    this.B = true;
                    E(true);
                    setScrollState(1);
                    float f8 = this.I;
                    float f9 = this.F;
                    this.G = f7 > 0.0f ? f8 + f9 : f8 - f9;
                    this.H = y7;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i8) {
                    this.C = true;
                }
                if (this.B && y(x7)) {
                    u.b0(this);
                }
            }
        } else if (action == 6) {
            u(motionEvent);
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        return this.B;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        g gVar;
        g gVar2;
        int i9;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i7), ViewGroup.getDefaultSize(0, i8));
        int measuredWidth = getMeasuredWidth();
        this.E = Math.min(measuredWidth / 10, this.D);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i10 = 0;
        while (true) {
            boolean z6 = true;
            int i11 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f3261a) {
                int i12 = gVar2.f3262b;
                int i13 = i12 & 7;
                int i14 = i12 & 112;
                boolean z7 = i14 == 48 || i14 == 80;
                if (i13 != 3 && i13 != 5) {
                    z6 = false;
                }
                int i15 = Integer.MIN_VALUE;
                if (z7) {
                    i9 = Integer.MIN_VALUE;
                    i15 = 1073741824;
                } else {
                    i9 = z6 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i16 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i16 != -2) {
                    if (i16 == -1) {
                        i16 = paddingLeft;
                    }
                    i15 = 1073741824;
                } else {
                    i16 = paddingLeft;
                }
                int i17 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i17 == -2) {
                    i17 = measuredHeight;
                    i11 = i9;
                } else if (i17 == -1) {
                    i17 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i16, i15), View.MeasureSpec.makeMeasureSpec(i17, i11));
                if (z7) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z6) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i10++;
        }
        this.f3245v = View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.f3246w = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.f3247x = true;
        z();
        this.f3247x = false;
        int childCount2 = getChildCount();
        for (int i18 = 0; i18 < childCount2; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f3261a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.f3263c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), this.f3246w);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i7, Rect rect) {
        int i8;
        int i9;
        f fVarN;
        int childCount = getChildCount();
        int i10 = -1;
        if ((i7 & 2) != 0) {
            i10 = childCount;
            i8 = 0;
            i9 = 1;
        } else {
            i8 = childCount - 1;
            i9 = -1;
        }
        while (i8 != i10) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 0 && (fVarN = n(childAt)) != null && fVarN.f3257b == this.f3232j && childAt.requestFocus(i7, rect)) {
                return true;
            }
            i8 += i9;
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
        super.onRestoreInstanceState(savedState.b());
        this.f3234k = savedState.f3250h;
        this.f3235l = savedState.f3251i;
        this.f3236m = savedState.f3252j;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3250h = this.f3232j;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 != i9) {
            int i11 = this.f3239p;
            B(i7, i9, i11, i11);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.Q) {
            return true;
        }
        if (motionEvent.getAction() == 0) {
            motionEvent.getEdgeFlags();
        }
        return false;
    }

    f p(int i7) {
        for (int i8 = 0; i8 < this.f3226g.size(); i8++) {
            f fVar = this.f3226g.get(i8);
            if (fVar.f3257b == i7) {
                return fVar;
            }
        }
        return null;
    }

    void q() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f3237n = new Scroller(context, f3217m0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.F = viewConfiguration.getScaledPagingTouchSlop();
        this.M = (int) (400.0f * f7);
        this.N = viewConfiguration.getScaledMaximumFlingVelocity();
        this.R = new EdgeEffect(context);
        this.S = new EdgeEffect(context);
        this.O = (int) (25.0f * f7);
        this.P = (int) (2.0f * f7);
        this.D = (int) (f7 * 16.0f);
        u.k0(this, new h());
        if (u.w(this) == 0) {
            u.t0(this, 1);
        }
        u.v0(this, new d());
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.f3247x) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(androidx.viewpager.widget.a aVar) {
        this.f3223e = 0;
        List<i> list = this.f3222d0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.f3222d0.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f3222d0.get(i7).a(this, null, aVar);
        }
    }

    public void setCurrentItem(int i7) {
        this.f3249z = false;
        I(i7, !this.T, false);
    }

    public void setOffscreenPageLimit(int i7) {
        if (i7 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i7 + " too small; defaulting to 1");
            i7 = 1;
        }
        if (i7 != this.A) {
            this.A = i7;
            z();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.f3220b0 = jVar;
    }

    public void setPageMargin(int i7) {
        int i8 = this.f3239p;
        this.f3239p = i7;
        int width = getWidth();
        B(width, width, i7, i8);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f3240q = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    void setScrollState(int i7) {
        if (this.f3233j0 == i7) {
            return;
        }
        this.f3233j0 = i7;
        if (this.f3224e0 != null) {
            j(i7 != 0);
        }
        h(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void t(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.W
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
            boolean r10 = r9.f3261a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f3262b
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
            r12.f(r13, r14, r15)
            androidx.viewpager.widget.ViewPager$k r13 = r12.f3224e0
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.f3261a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.f3224e0
            r3.a(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.V = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.t(int, float, int):void");
    }

    boolean v() {
        int i7 = this.f3232j;
        if (i7 <= 0) {
            return false;
        }
        H(i7 - 1, true);
        return true;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3240q;
    }

    boolean w() {
        return false;
    }

    void z() {
        A(this.f3232j);
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3261a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3262b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f3263c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3264d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f3265e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f3266f;

        public g() {
            super(-1, -1);
            this.f3263c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3263c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f3215k0);
            this.f3262b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i7) {
        setPageMarginDrawable(x.a.c(getContext(), i7));
    }
}
