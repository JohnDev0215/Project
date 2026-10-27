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
import b.f.m.a0;
import b.f.m.p;
import b.f.m.s;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f684c = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Comparator<f> f685d = new a();
    public static final Interpolator s = new b();
    public static final m x = new m();
    public List<i> A4;
    public k B4;
    public int C4;
    public int D4;
    public final ArrayList<f> E3;
    public ArrayList<View> E4;
    public final f F3;
    public final Runnable F4;
    public final Rect G3;
    public int G4;
    public b.r.a.a H3;
    public int I3;
    public int J3;
    public Parcelable K3;
    public ClassLoader L3;
    public Scroller M3;
    public boolean N3;
    public l O3;
    public int P3;
    public Drawable Q3;
    public int R3;
    public int S3;
    public float T3;
    public float U3;
    public int V3;
    public int W3;
    public boolean X3;
    public boolean Y3;
    public boolean Z3;
    public int a4;
    public boolean b4;
    public boolean c4;
    public int d4;
    public int e4;
    public int f4;
    public float g4;
    public float h4;
    public float i4;
    public float j4;
    public int k4;
    public VelocityTracker l4;
    public int m4;
    public int n4;
    public int o4;
    public int p4;
    public boolean q4;
    public EdgeEffect r4;
    public EdgeEffect s4;
    public boolean t4;
    public boolean u4;
    public boolean v4;
    public int w4;
    public List<j> x4;
    public int y;
    public j y4;
    public j z4;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int s;
        public Parcelable x;
        public ClassLoader y;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.s + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.s);
            parcel.writeParcelable(this.x, i2);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.s = parcel.readInt();
            this.x = parcel.readParcelable(classLoader);
            this.y = classLoader;
        }
    }

    public static class a implements Comparator<f> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f690b - fVar2.f690b;
        }
    }

    public static class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.D();
        }
    }

    public class d implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f687a = new Rect();

        public d() {
        }

        @Override // b.f.m.p
        public a0 a(View view, a0 a0Var) {
            a0 a0VarL = s.L(view, a0Var);
            if (a0VarL.k()) {
                return a0VarL;
            }
            Rect rect = this.f687a;
            rect.left = a0VarL.e();
            rect.top = a0VarL.g();
            rect.right = a0VarL.f();
            rect.bottom = a0VarL.d();
            int childCount = ViewPager.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                a0 a0VarF = s.f(ViewPager.this.getChildAt(i2), a0VarL);
                rect.left = Math.min(a0VarF.e(), rect.left);
                rect.top = Math.min(a0VarF.g(), rect.top);
                rect.right = Math.min(a0VarF.f(), rect.right);
                rect.bottom = Math.min(a0VarF.d(), rect.bottom);
            }
            return a0VarL.l(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f689a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f690b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f691c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f692d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f693e;
    }

    public class h extends b.f.m.a {
        public h() {
        }

        @Override // b.f.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b.r.a.a aVar;
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.H3) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.e());
            accessibilityEvent.setFromIndex(ViewPager.this.I3);
            accessibilityEvent.setToIndex(ViewPager.this.I3);
        }

        @Override // b.f.m.a
        public void g(View view, b.f.m.b0.c cVar) {
            super.g(view, cVar);
            cVar.T(ViewPager.class.getName());
            cVar.h0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        @Override // b.f.m.a
        public boolean j(View view, int i2, Bundle bundle) {
            if (super.j(view, i2, bundle)) {
                return true;
            }
            if (i2 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.I3 + 1);
                return true;
            }
            if (i2 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.I3 - 1);
            return true;
        }

        public final boolean n() {
            b.r.a.a aVar = ViewPager.this.H3;
            return aVar != null && aVar.e() > 1;
        }
    }

    public interface i {
        void b(ViewPager viewPager, b.r.a.a aVar, b.r.a.a aVar2);
    }

    public interface j {
        void a(int i2, float f2, int i3);

        void c(int i2);

        void d(int i2);
    }

    public interface k {
        void a(View view, float f2);
    }

    public class l extends DataSetObserver {
        public l() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.g();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.g();
        }
    }

    public static class m implements Comparator<View> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z = gVar.f694a;
            return z != gVar2.f694a ? z ? 1 : -1 : gVar.f698e - gVar2.f698e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.E3 = new ArrayList<>();
        this.F3 = new f();
        this.G3 = new Rect();
        this.J3 = -1;
        this.K3 = null;
        this.L3 = null;
        this.T3 = -3.4028235E38f;
        this.U3 = Float.MAX_VALUE;
        this.a4 = 1;
        this.k4 = -1;
        this.t4 = true;
        this.u4 = false;
        this.F4 = new c();
        this.G4 = 0;
        u();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.Y3 != z) {
            this.Y3 = z;
        }
    }

    public static boolean v(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    public boolean A() {
        b.r.a.a aVar = this.H3;
        if (aVar == null || this.I3 >= aVar.e() - 1) {
            return false;
        }
        L(this.I3 + 1, true);
        return true;
    }

    public final boolean B(int i2) {
        if (this.E3.size() == 0) {
            if (this.t4) {
                return false;
            }
            this.v4 = false;
            x(0, 0.0f, 0);
            if (this.v4) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarS = s();
        int clientWidth = getClientWidth();
        int i3 = this.P3;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = fVarS.f690b;
        float f3 = ((i2 / f2) - fVarS.f693e) / (fVarS.f692d + (i3 / f2));
        this.v4 = false;
        x(i5, f3, (int) (i4 * f3));
        if (this.v4) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final boolean C(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.g4 - f2;
        this.g4 = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.T3 * clientWidth;
        float f5 = this.U3 * clientWidth;
        boolean z3 = false;
        f fVar = this.E3.get(0);
        ArrayList<f> arrayList = this.E3;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.f690b != 0) {
            f4 = fVar.f693e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (fVar2.f690b != this.H3.e() - 1) {
            f5 = fVar2.f693e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.r4.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.s4.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.g4 += scrollX - i2;
        scrollTo(i2, getScrollY());
        B(i2);
        return z3;
    }

    public void D() {
        E(this.I3);
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
    public void E(int r18) {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.E(int):void");
    }

    public final void F(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.E3.isEmpty()) {
            if (!this.M3.isFinished()) {
                this.M3.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        f fVarT = t(this.I3);
        int iMin = (int) ((fVarT != null ? Math.min(fVarT.f693e, this.U3) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            f(false);
            scrollTo(iMin, getScrollY());
        }
    }

    public final void G() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).f694a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public void H(i iVar) {
        List<i> list = this.A4;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public final void I(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public final boolean J() {
        this.k4 = -1;
        n();
        this.r4.onRelease();
        this.s4.onRelease();
        return this.r4.isFinished() || this.s4.isFinished();
    }

    public final void K(int i2, boolean z, int i3, boolean z2) {
        f fVarT = t(i2);
        int clientWidth = fVarT != null ? (int) (getClientWidth() * Math.max(this.T3, Math.min(fVarT.f693e, this.U3))) : 0;
        if (z) {
            P(clientWidth, 0, i3);
            if (z2) {
                j(i2);
                return;
            }
            return;
        }
        if (z2) {
            j(i2);
        }
        f(false);
        scrollTo(clientWidth, 0);
        B(clientWidth);
    }

    public void L(int i2, boolean z) {
        this.Z3 = false;
        M(i2, z, false);
    }

    public void M(int i2, boolean z, boolean z2) {
        N(i2, z, z2, 0);
    }

    public void N(int i2, boolean z, boolean z2, int i3) {
        b.r.a.a aVar = this.H3;
        if (aVar == null || aVar.e() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.I3 == i2 && this.E3.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.H3.e()) {
            i2 = this.H3.e() - 1;
        }
        int i4 = this.a4;
        int i5 = this.I3;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.E3.size(); i6++) {
                this.E3.get(i6).f691c = true;
            }
        }
        boolean z3 = this.I3 != i2;
        if (!this.t4) {
            E(i2);
            K(i2, z, i3, z3);
        } else {
            this.I3 = i2;
            if (z3) {
                j(i2);
            }
            requestLayout();
        }
    }

    public j O(j jVar) {
        j jVar2 = this.z4;
        this.z4 = jVar;
        return jVar2;
    }

    public void P(int i2, int i3, int i4) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.M3;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.N3 ? this.M3.getCurrX() : this.M3.getStartX();
            this.M3.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            f(false);
            D();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float fL = f3 + (l(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int iAbs = Math.abs(i4);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fL / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i6) / ((f2 * this.H3.h(this.I3)) + this.P3)) + 1.0f) * 100.0f), 600);
        this.N3 = false;
        this.M3.startScroll(i5, scrollY, i6, i7, iMin);
        s.N(this);
    }

    public final void Q() {
        if (this.D4 != 0) {
            ArrayList<View> arrayList = this.E4;
            if (arrayList == null) {
                this.E4 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.E4.add(getChildAt(i2));
            }
            Collections.sort(this.E4, x);
        }
    }

    public f a(int i2, int i3) {
        f fVar = new f();
        fVar.f690b = i2;
        fVar.f689a = this.H3.j(this, i2);
        fVar.f692d = this.H3.h(i2);
        if (i3 < 0 || i3 >= this.E3.size()) {
            this.E3.add(fVar);
        } else {
            this.E3.add(i3, fVar);
        }
        return fVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        f fVarR;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f690b == this.I3) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarR;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f690b == this.I3) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zV = gVar.f694a | v(view);
        gVar.f694a = zV;
        if (!this.X3) {
            super.addView(view, i2, layoutParams);
        } else {
            if (zV) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f697d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public void b(i iVar) {
        if (this.A4 == null) {
            this.A4 = new ArrayList();
        }
        this.A4.add(iVar);
    }

    public boolean c(int i2) {
        boolean z;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean z2 = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z = false;
                    break;
                }
                if (parent == this) {
                    z = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z) {
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
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i2);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i2 == 17) {
                zRequestFocus = (viewFindFocus == null || p(this.G3, viewFindNextFocus).left < p(this.G3, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : z();
            } else if (i2 == 66) {
                zRequestFocus = (viewFindFocus == null || p(this.G3, viewFindNextFocus).left > p(this.G3, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : A();
            }
            z2 = zRequestFocus;
        } else if (i2 == 17 || i2 == 1) {
            z2 = z();
        } else if (i2 == 66 || i2 == 2) {
            z2 = A();
        }
        if (z2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z2;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.H3 == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.T3)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.U3));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.N3 = true;
        if (this.M3.isFinished() || !this.M3.computeScrollOffset()) {
            f(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.M3.getCurrX();
        int currY = this.M3.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!B(currX)) {
                this.M3.abortAnimation();
                scrollTo(0, currY);
            }
        }
        s.N(this);
    }

    public final void d(f fVar, int i2, f fVar2) {
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int iE = this.H3.e();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.P3 / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i5 = fVar2.f690b;
            int i6 = fVar.f690b;
            if (i5 < i6) {
                float fH = fVar2.f693e + fVar2.f692d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= fVar.f690b && i8 < this.E3.size()) {
                    f fVar5 = this.E3.get(i8);
                    while (true) {
                        fVar4 = fVar5;
                        if (i7 <= fVar4.f690b || i8 >= this.E3.size() - 1) {
                            break;
                        }
                        i8++;
                        fVar5 = this.E3.get(i8);
                    }
                    while (i7 < fVar4.f690b) {
                        fH += this.H3.h(i7) + f2;
                        i7++;
                    }
                    fVar4.f693e = fH;
                    fH += fVar4.f692d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.E3.size() - 1;
                float fH2 = fVar2.f693e;
                while (true) {
                    i5--;
                    if (i5 < fVar.f690b || size < 0) {
                        break;
                    }
                    f fVar6 = this.E3.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i5 >= fVar3.f690b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.E3.get(size);
                    }
                    while (i5 > fVar3.f690b) {
                        fH2 -= this.H3.h(i5) + f2;
                        i5--;
                    }
                    fH2 -= fVar3.f692d + f2;
                    fVar3.f693e = fH2;
                }
            }
        }
        int size2 = this.E3.size();
        float fH3 = fVar.f693e;
        int i9 = fVar.f690b;
        int i10 = i9 - 1;
        this.T3 = i9 == 0 ? fH3 : -3.4028235E38f;
        int i11 = iE - 1;
        this.U3 = i9 == i11 ? (fVar.f692d + fH3) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            f fVar7 = this.E3.get(i12);
            while (true) {
                i4 = fVar7.f690b;
                if (i10 <= i4) {
                    break;
                }
                fH3 -= this.H3.h(i10) + f2;
                i10--;
            }
            fH3 -= fVar7.f692d + f2;
            fVar7.f693e = fH3;
            if (i4 == 0) {
                this.T3 = fH3;
            }
            i12--;
            i10--;
        }
        float fH4 = fVar.f693e + fVar.f692d + f2;
        int i13 = fVar.f690b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            f fVar8 = this.E3.get(i14);
            while (true) {
                i3 = fVar8.f690b;
                if (i13 >= i3) {
                    break;
                }
                fH4 += this.H3.h(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.U3 = (fVar8.f692d + fH4) - 1.0f;
            }
            fVar8.f693e = fH4;
            fH4 += fVar8.f692d + f2;
            i14++;
            i13++;
        }
        this.u4 = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || o(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarR;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f690b == this.I3 && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b.r.a.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.H3) != null && aVar.e() > 1)) {
            if (!this.r4.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.T3 * width);
                this.r4.setSize(height, width);
                zDraw = false | this.r4.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.s4.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.U3 + 1.0f)) * width2);
                this.s4.setSize(height2, width2);
                zDraw |= this.s4.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.r4.finish();
            this.s4.finish();
        }
        if (zDraw) {
            s.N(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.Q3;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public boolean e(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && e(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
    }

    public final void f(boolean z) {
        boolean z2 = this.G4 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.M3.isFinished()) {
                this.M3.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.M3.getCurrX();
                int currY = this.M3.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        B(currX);
                    }
                }
            }
        }
        this.Z3 = false;
        for (int i2 = 0; i2 < this.E3.size(); i2++) {
            f fVar = this.E3.get(i2);
            if (fVar.f691c) {
                fVar.f691c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                s.O(this, this.F4);
            } else {
                this.F4.run();
            }
        }
    }

    public void g() {
        int iE = this.H3.e();
        this.y = iE;
        boolean z = this.E3.size() < (this.a4 * 2) + 1 && this.E3.size() < iE;
        int iMax = this.I3;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.E3.size()) {
            f fVar = this.E3.get(i2);
            int iF = this.H3.f(fVar.f689a);
            if (iF != -1) {
                if (iF == -2) {
                    this.E3.remove(i2);
                    i2--;
                    if (!z2) {
                        this.H3.s(this);
                        z2 = true;
                    }
                    this.H3.b(this, fVar.f690b, fVar.f689a);
                    int i3 = this.I3;
                    if (i3 == fVar.f690b) {
                        iMax = Math.max(0, Math.min(i3, iE - 1));
                    }
                } else {
                    int i4 = fVar.f690b;
                    if (i4 != iF) {
                        if (i4 == this.I3) {
                            iMax = iF;
                        }
                        fVar.f690b = iF;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.H3.d(this);
        }
        Collections.sort(this.E3, f685d);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                g gVar = (g) getChildAt(i5).getLayoutParams();
                if (!gVar.f694a) {
                    gVar.f696c = 0.0f;
                }
            }
            M(iMax, false, true);
            requestLayout();
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

    public b.r.a.a getAdapter() {
        return this.H3;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.D4 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) this.E4.get(i3).getLayoutParams()).f699f;
    }

    public int getCurrentItem() {
        return this.I3;
    }

    public int getOffscreenPageLimit() {
        return this.a4;
    }

    public int getPageMargin() {
        return this.P3;
    }

    public final int h(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.o4 || Math.abs(i3) <= this.m4) {
            i2 += (int) (f2 + (i2 >= this.I3 ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.E3.size() <= 0) {
            return i2;
        }
        return Math.max(this.E3.get(0).f690b, Math.min(i2, this.E3.get(r4.size() - 1).f690b));
    }

    public final void i(int i2, float f2, int i3) {
        j jVar = this.y4;
        if (jVar != null) {
            jVar.a(i2, f2, i3);
        }
        List<j> list = this.x4;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.x4.get(i4);
                if (jVar2 != null) {
                    jVar2.a(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.z4;
        if (jVar3 != null) {
            jVar3.a(i2, f2, i3);
        }
    }

    public final void j(int i2) {
        j jVar = this.y4;
        if (jVar != null) {
            jVar.d(i2);
        }
        List<j> list = this.x4;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.x4.get(i3);
                if (jVar2 != null) {
                    jVar2.d(i2);
                }
            }
        }
        j jVar3 = this.z4;
        if (jVar3 != null) {
            jVar3.d(i2);
        }
    }

    public final void k(int i2) {
        j jVar = this.y4;
        if (jVar != null) {
            jVar.c(i2);
        }
        List<j> list = this.x4;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.x4.get(i3);
                if (jVar2 != null) {
                    jVar2.c(i2);
                }
            }
        }
        j jVar3 = this.z4;
        if (jVar3 != null) {
            jVar3.c(i2);
        }
    }

    public float l(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    public final void m(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.C4 : 0, null);
        }
    }

    public final void n() {
        this.b4 = false;
        this.c4 = false;
        VelocityTracker velocityTracker = this.l4;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l4 = null;
        }
    }

    public boolean o(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? z() : c(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? A() : c(66);
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

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.t4 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.F4);
        Scroller scroller = this.M3;
        if (scroller != null && !scroller.isFinished()) {
            this.M3.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.P3 <= 0 || this.Q3 == null || this.E3.size() <= 0 || this.H3 == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.P3 / width;
        int i3 = 0;
        f fVar = this.E3.get(0);
        float f5 = fVar.f693e;
        int size = this.E3.size();
        int i4 = fVar.f690b;
        int i5 = this.E3.get(size - 1).f690b;
        while (i4 < i5) {
            while (true) {
                i2 = fVar.f690b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                fVar = this.E3.get(i3);
            }
            if (i4 == i2) {
                float f6 = fVar.f693e;
                float f7 = fVar.f692d;
                f2 = (f6 + f7) * width;
                f5 = f6 + f7 + f4;
            } else {
                float fH = this.H3.h(i4);
                f2 = (f5 + fH) * width;
                f5 += fH + f4;
            }
            if (this.P3 + f2 > scrollX) {
                f3 = f4;
                this.Q3.setBounds(Math.round(f2), this.R3, Math.round(this.P3 + f2), this.S3);
                this.Q3.draw(canvas);
            } else {
                f3 = f4;
            }
            if (f2 > scrollX + r2) {
                return;
            }
            i4++;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            J();
            return false;
        }
        if (action != 0) {
            if (this.b4) {
                return true;
            }
            if (this.c4) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.i4 = x2;
            this.g4 = x2;
            float y = motionEvent.getY();
            this.j4 = y;
            this.h4 = y;
            this.k4 = motionEvent.getPointerId(0);
            this.c4 = false;
            this.N3 = true;
            this.M3.computeScrollOffset();
            if (this.G4 != 2 || Math.abs(this.M3.getFinalX() - this.M3.getCurrX()) <= this.p4) {
                f(false);
                this.b4 = false;
            } else {
                this.M3.abortAnimation();
                this.Z3 = false;
                D();
                this.b4 = true;
                I(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.k4;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x3 = motionEvent.getX(iFindPointerIndex);
                float f2 = x3 - this.g4;
                float fAbs = Math.abs(f2);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y2 - this.j4);
                if (f2 != 0.0f && !w(this.g4, f2) && e(this, false, (int) f2, (int) x3, (int) y2)) {
                    this.g4 = x3;
                    this.h4 = y2;
                    this.c4 = true;
                    return false;
                }
                int i3 = this.f4;
                if (fAbs > i3 && fAbs * 0.5f > fAbs2) {
                    this.b4 = true;
                    I(true);
                    setScrollState(1);
                    float f3 = this.i4;
                    float f4 = this.f4;
                    this.g4 = f2 > 0.0f ? f3 + f4 : f3 - f4;
                    this.h4 = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i3) {
                    this.c4 = true;
                }
                if (this.b4 && C(x3)) {
                    s.N(this);
                }
            }
        } else if (action == 6) {
            y(motionEvent);
        }
        if (this.l4 == null) {
            this.l4 = VelocityTracker.obtain();
        }
        this.l4.addMovement(motionEvent);
        return this.b4;
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
    public void onMeasure(int i2, int i3) {
        g gVar;
        g gVar2;
        int i4;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i2), ViewGroup.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.e4 = Math.min(measuredWidth / 10, this.d4);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i5 = 0;
        while (true) {
            boolean z = true;
            int i6 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            if (i5 >= childCount) {
                break;
            }
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f694a) {
                int i7 = gVar2.f695b;
                int i8 = i7 & 7;
                int i9 = i7 & 112;
                boolean z2 = i9 == 48 || i9 == 80;
                if (i8 != 3 && i8 != 5) {
                    z = false;
                }
                int i10 = Integer.MIN_VALUE;
                if (z2) {
                    i4 = Integer.MIN_VALUE;
                    i10 = 1073741824;
                } else {
                    i4 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i11 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i11 != -2) {
                    if (i11 == -1) {
                        i11 = paddingLeft;
                    }
                    i10 = 1073741824;
                } else {
                    i11 = paddingLeft;
                }
                int i12 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i12 == -2) {
                    i12 = measuredHeight;
                    i6 = i4;
                } else if (i12 == -1) {
                    i12 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i11, i10), View.MeasureSpec.makeMeasureSpec(i12, i6));
                if (z2) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i5++;
        }
        this.V3 = View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.W3 = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.X3 = true;
        D();
        this.X3 = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f694a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.f696c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), this.W3);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        f fVarR;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
        }
        while (i3 != i5) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f690b == this.I3 && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
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
        super.onRestoreInstanceState(savedState.a());
        b.r.a.a aVar = this.H3;
        if (aVar != null) {
            aVar.m(savedState.x, savedState.y);
            M(savedState.s, false, true);
        } else {
            this.J3 = savedState.s;
            this.K3 = savedState.x;
            this.L3 = savedState.y;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.s = this.I3;
        b.r.a.a aVar = this.H3;
        if (aVar != null) {
            savedState.x = aVar.n();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.P3;
            F(i2, i4, i6, i6);
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

    public final Rect p(Rect rect, View view) {
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

    public f q(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return r(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public f r(View view) {
        for (int i2 = 0; i2 < this.E3.size(); i2++) {
            f fVar = this.E3.get(i2);
            if (this.H3.k(view, fVar.f689a)) {
                return fVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.X3) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public final f s() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.P3 / clientWidth : 0.0f;
        f fVar = null;
        int i3 = 0;
        int i4 = -1;
        boolean z = true;
        float f4 = 0.0f;
        while (i3 < this.E3.size()) {
            f fVar2 = this.E3.get(i3);
            if (!z && fVar2.f690b != (i2 = i4 + 1)) {
                fVar2 = this.F3;
                fVar2.f693e = f2 + f4 + f3;
                fVar2.f690b = i2;
                fVar2.f692d = this.H3.h(i2);
                i3--;
            }
            f2 = fVar2.f693e;
            float f5 = fVar2.f692d + f2 + f3;
            if (!z && scrollX < f2) {
                return fVar;
            }
            if (scrollX < f5 || i3 == this.E3.size() - 1) {
                return fVar2;
            }
            i4 = fVar2.f690b;
            f4 = fVar2.f692d;
            i3++;
            z = false;
            fVar = fVar2;
        }
        return fVar;
    }

    public void setAdapter(b.r.a.a aVar) {
        b.r.a.a aVar2 = this.H3;
        if (aVar2 != null) {
            aVar2.q(null);
            this.H3.s(this);
            for (int i2 = 0; i2 < this.E3.size(); i2++) {
                f fVar = this.E3.get(i2);
                this.H3.b(this, fVar.f690b, fVar.f689a);
            }
            this.H3.d(this);
            this.E3.clear();
            G();
            this.I3 = 0;
            scrollTo(0, 0);
        }
        b.r.a.a aVar3 = this.H3;
        this.H3 = aVar;
        this.y = 0;
        if (aVar != null) {
            if (this.O3 == null) {
                this.O3 = new l();
            }
            this.H3.q(this.O3);
            this.Z3 = false;
            boolean z = this.t4;
            this.t4 = true;
            this.y = this.H3.e();
            if (this.J3 >= 0) {
                this.H3.m(this.K3, this.L3);
                M(this.J3, false, true);
                this.J3 = -1;
                this.K3 = null;
                this.L3 = null;
            } else if (z) {
                requestLayout();
            } else {
                D();
            }
        }
        List<i> list = this.A4;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.A4.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.A4.get(i3).b(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.Z3 = false;
        M(i2, !this.t4, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.a4) {
            this.a4 = i2;
            D();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.y4 = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.P3;
        this.P3 = i2;
        int width = getWidth();
        F(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.Q3 = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i2) {
        if (this.G4 == i2) {
            return;
        }
        this.G4 = i2;
        if (this.B4 != null) {
            m(i2 != 0);
        }
        k(i2);
    }

    public f t(int i2) {
        for (int i3 = 0; i3 < this.E3.size(); i3++) {
            f fVar = this.E3.get(i3);
            if (fVar.f690b == i2) {
                return fVar;
            }
        }
        return null;
    }

    public void u() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.M3 = new Scroller(context, s);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f4 = viewConfiguration.getScaledPagingTouchSlop();
        this.m4 = (int) (400.0f * f2);
        this.n4 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.r4 = new EdgeEffect(context);
        this.s4 = new EdgeEffect(context);
        this.o4 = (int) (25.0f * f2);
        this.p4 = (int) (2.0f * f2);
        this.d4 = (int) (f2 * 16.0f);
        s.T(this, new h());
        if (s.t(this) == 0) {
            s.Y(this, 1);
        }
        s.a0(this, new d());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.Q3;
    }

    public final boolean w(float f2, float f3) {
        return (f2 < ((float) this.e4) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.e4)) && f3 < 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void x(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.w4
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
            boolean r10 = r9.f694a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f695b
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
            r12.i(r13, r14, r15)
            androidx.viewpager.widget.ViewPager$k r13 = r12.B4
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.f694a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.B4
            r3.a(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.v4 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.x(int, float, int):void");
    }

    public final void y(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.k4) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.g4 = motionEvent.getX(i2);
            this.k4 = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.l4;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean z() {
        int i2 = this.I3;
        if (i2 <= 0) {
            return false;
        }
        L(i2 - 1, true);
        return true;
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f694a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f695b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f696c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f697d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f698e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f699f;

        public g() {
            super(-1, -1);
            this.f696c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f696c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f684c);
            this.f695b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(b.f.f.a.c(getContext(), i2));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E3 = new ArrayList<>();
        this.F3 = new f();
        this.G3 = new Rect();
        this.J3 = -1;
        this.K3 = null;
        this.L3 = null;
        this.T3 = -3.4028235E38f;
        this.U3 = Float.MAX_VALUE;
        this.a4 = 1;
        this.k4 = -1;
        this.t4 = true;
        this.u4 = false;
        this.F4 = new c();
        this.G4 = 0;
        u();
    }
}
