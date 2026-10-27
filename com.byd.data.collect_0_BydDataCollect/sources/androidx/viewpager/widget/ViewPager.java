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
import b.g.k.b0;
import b.g.k.p;
import b.g.k.t;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f2064b = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Comparator<f> f2065c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Interpolator f2066d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final m f2067e = new m();
    private boolean A;
    private boolean B;
    private int C;
    private boolean D;
    private boolean E;
    private int F;
    private int G;
    private int H;
    private float I;
    private float J;
    private float K;
    private float L;
    private int M;
    private VelocityTracker N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private EdgeEffect T;
    private EdgeEffect U;
    private boolean V;
    private boolean W;
    private boolean X;
    private int Y;
    private List<j> Z;
    private j a0;
    private j b0;
    private List<i> c0;
    private k d0;
    private int e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2068f;
    private int f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ArrayList<f> f2069g;
    private ArrayList<View> g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f f2070h;
    private final Runnable h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f2071i;
    private int i0;
    androidx.viewpager.widget.a j;
    int k;
    private int l;
    private Parcelable m;
    private ClassLoader n;
    private Scroller o;
    private boolean p;
    private l q;
    private int r;
    private Drawable s;
    private int t;
    private int u;
    private float v;
    private float w;
    private int x;
    private int y;
    private boolean z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2072d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Parcelable f2073e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        ClassLoader f2074f;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f2072d + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f2072d);
            parcel.writeParcelable(this.f2073e, i2);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f2072d = parcel.readInt();
            this.f2073e = parcel.readParcelable(classLoader);
            this.f2074f = classLoader;
        }
    }

    static class a implements Comparator<f> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f2079b - fVar2.f2079b;
        }
    }

    static class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.E();
        }
    }

    class d implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Rect f2076a = new Rect();

        d() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            b0 b0VarW = t.W(view, b0Var);
            if (b0VarW.n()) {
                return b0VarW;
            }
            Rect rect = this.f2076a;
            rect.left = b0VarW.g();
            rect.top = b0VarW.i();
            rect.right = b0VarW.h();
            rect.bottom = b0VarW.f();
            int childCount = ViewPager.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                b0 b0VarG = t.g(ViewPager.this.getChildAt(i2), b0VarW);
                rect.left = Math.min(b0VarG.g(), rect.left);
                rect.top = Math.min(b0VarG.i(), rect.top);
                rect.right = Math.min(b0VarG.h(), rect.right);
                rect.bottom = Math.min(b0VarG.f(), rect.bottom);
            }
            return b0VarW.o(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f2078a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2079b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2080c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f2081d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f2082e;

        f() {
        }
    }

    class h extends b.g.k.a {
        h() {
        }

        private boolean n() {
            androidx.viewpager.widget.a aVar = ViewPager.this.j;
            return aVar != null && aVar.e() > 1;
        }

        @Override // b.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            androidx.viewpager.widget.a aVar;
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(n());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.j) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.e());
            accessibilityEvent.setFromIndex(ViewPager.this.k);
            accessibilityEvent.setToIndex(ViewPager.this.k);
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.b0(ViewPager.class.getName());
            cVar.u0(n());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        @Override // b.g.k.a
        public boolean j(View view, int i2, Bundle bundle) {
            if (super.j(view, i2, bundle)) {
                return true;
            }
            if (i2 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.k + 1);
                return true;
            }
            if (i2 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.k - 1);
            return true;
        }
    }

    public interface i {
        void b(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2);
    }

    public interface j {
        void a(int i2, float f2, int i3);

        void c(int i2);

        void d(int i2);
    }

    public interface k {
        void a(View view, float f2);
    }

    private class l extends DataSetObserver {
        l() {
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

    static class m implements Comparator<View> {
        m() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z = gVar.f2083a;
            return z != gVar2.f2083a ? z ? 1 : -1 : gVar.f2087e - gVar2.f2087e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f2069g = new ArrayList<>();
        this.f2070h = new f();
        this.f2071i = new Rect();
        this.l = -1;
        this.m = null;
        this.n = null;
        this.v = -3.4028235E38f;
        this.w = Float.MAX_VALUE;
        this.C = 1;
        this.M = -1;
        this.V = true;
        this.W = false;
        this.h0 = new c();
        this.i0 = 0;
        v();
    }

    private boolean C(int i2) {
        if (this.f2069g.size() == 0) {
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
        int i3 = this.r;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = fVarT.f2079b;
        float f3 = ((i2 / f2) - fVarT.f2082e) / (fVarT.f2081d + (i3 / f2));
        this.X = false;
        y(i5, f3, (int) (i4 * f3));
        if (this.X) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean D(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.I - f2;
        this.I = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.v * clientWidth;
        float f5 = this.w * clientWidth;
        boolean z3 = false;
        f fVar = this.f2069g.get(0);
        ArrayList<f> arrayList = this.f2069g;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.f2079b != 0) {
            f4 = fVar.f2082e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (fVar2.f2079b != this.j.e() - 1) {
            f5 = fVar2.f2082e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.T.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.U.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.I += scrollX - i2;
        scrollTo(i2, getScrollY());
        C(i2);
        return z3;
    }

    private void G(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.f2069g.isEmpty()) {
            if (!this.o.isFinished()) {
                this.o.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        f fVarU = u(this.k);
        int iMin = (int) ((fVarU != null ? Math.min(fVarU.f2082e, this.w) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            g(false);
            scrollTo(iMin, getScrollY());
        }
    }

    private void H() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).f2083a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void K(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean L() {
        this.M = -1;
        o();
        this.T.onRelease();
        this.U.onRelease();
        return this.T.isFinished() || this.U.isFinished();
    }

    private void M(int i2, boolean z, int i3, boolean z2) {
        f fVarU = u(i2);
        int clientWidth = fVarU != null ? (int) (getClientWidth() * Math.max(this.v, Math.min(fVarU.f2082e, this.w))) : 0;
        if (z) {
            R(clientWidth, 0, i3);
            if (z2) {
                k(i2);
                return;
            }
            return;
        }
        if (z2) {
            k(i2);
        }
        g(false);
        scrollTo(clientWidth, 0);
        C(clientWidth);
    }

    private void S() {
        if (this.f0 != 0) {
            ArrayList<View> arrayList = this.g0;
            if (arrayList == null) {
                this.g0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.g0.add(getChildAt(i2));
            }
            Collections.sort(this.g0, f2067e);
        }
    }

    private void e(f fVar, int i2, f fVar2) {
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int iE = this.j.e();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.r / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i5 = fVar2.f2079b;
            int i6 = fVar.f2079b;
            if (i5 < i6) {
                float fH = fVar2.f2082e + fVar2.f2081d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= fVar.f2079b && i8 < this.f2069g.size()) {
                    f fVar5 = this.f2069g.get(i8);
                    while (true) {
                        fVar4 = fVar5;
                        if (i7 <= fVar4.f2079b || i8 >= this.f2069g.size() - 1) {
                            break;
                        }
                        i8++;
                        fVar5 = this.f2069g.get(i8);
                    }
                    while (i7 < fVar4.f2079b) {
                        fH += this.j.h(i7) + f2;
                        i7++;
                    }
                    fVar4.f2082e = fH;
                    fH += fVar4.f2081d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.f2069g.size() - 1;
                float fH2 = fVar2.f2082e;
                while (true) {
                    i5--;
                    if (i5 < fVar.f2079b || size < 0) {
                        break;
                    }
                    f fVar6 = this.f2069g.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i5 >= fVar3.f2079b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.f2069g.get(size);
                    }
                    while (i5 > fVar3.f2079b) {
                        fH2 -= this.j.h(i5) + f2;
                        i5--;
                    }
                    fH2 -= fVar3.f2081d + f2;
                    fVar3.f2082e = fH2;
                }
            }
        }
        int size2 = this.f2069g.size();
        float fH3 = fVar.f2082e;
        int i9 = fVar.f2079b;
        int i10 = i9 - 1;
        this.v = i9 == 0 ? fH3 : -3.4028235E38f;
        int i11 = iE - 1;
        this.w = i9 == i11 ? (fVar.f2081d + fH3) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            f fVar7 = this.f2069g.get(i12);
            while (true) {
                i4 = fVar7.f2079b;
                if (i10 <= i4) {
                    break;
                }
                fH3 -= this.j.h(i10) + f2;
                i10--;
            }
            fH3 -= fVar7.f2081d + f2;
            fVar7.f2082e = fH3;
            if (i4 == 0) {
                this.v = fH3;
            }
            i12--;
            i10--;
        }
        float fH4 = fVar.f2082e + fVar.f2081d + f2;
        int i13 = fVar.f2079b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            f fVar8 = this.f2069g.get(i14);
            while (true) {
                i3 = fVar8.f2079b;
                if (i13 >= i3) {
                    break;
                }
                fH4 += this.j.h(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.w = (fVar8.f2081d + fH4) - 1.0f;
            }
            fVar8.f2082e = fH4;
            fH4 += fVar8.f2081d + f2;
            i14++;
            i13++;
        }
        this.W = false;
    }

    private void g(boolean z) {
        boolean z2 = this.i0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.o.isFinished()) {
                this.o.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.o.getCurrX();
                int currY = this.o.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        C(currX);
                    }
                }
            }
        }
        this.B = false;
        for (int i2 = 0; i2 < this.f2069g.size(); i2++) {
            f fVar = this.f2069g.get(i2);
            if (fVar.f2080c) {
                fVar.f2080c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                t.b0(this, this.h0);
            } else {
                this.h0.run();
            }
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int i(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.Q || Math.abs(i3) <= this.O) {
            i2 += (int) (f2 + (i2 >= this.k ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.f2069g.size() <= 0) {
            return i2;
        }
        return Math.max(this.f2069g.get(0).f2079b, Math.min(i2, this.f2069g.get(r4.size() - 1).f2079b));
    }

    private void j(int i2, float f2, int i3) {
        j jVar = this.a0;
        if (jVar != null) {
            jVar.a(i2, f2, i3);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.Z.get(i4);
                if (jVar2 != null) {
                    jVar2.a(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.b0;
        if (jVar3 != null) {
            jVar3.a(i2, f2, i3);
        }
    }

    private void k(int i2) {
        j jVar = this.a0;
        if (jVar != null) {
            jVar.d(i2);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.Z.get(i3);
                if (jVar2 != null) {
                    jVar2.d(i2);
                }
            }
        }
        j jVar3 = this.b0;
        if (jVar3 != null) {
            jVar3.d(i2);
        }
    }

    private void l(int i2) {
        j jVar = this.a0;
        if (jVar != null) {
            jVar.c(i2);
        }
        List<j> list = this.Z;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.Z.get(i3);
                if (jVar2 != null) {
                    jVar2.c(i2);
                }
            }
        }
        j jVar3 = this.b0;
        if (jVar3 != null) {
            jVar3.c(i2);
        }
    }

    private void n(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.e0 : 0, null);
        }
    }

    private void o() {
        this.D = false;
        this.E = false;
        VelocityTracker velocityTracker = this.N;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.N = null;
        }
    }

    private Rect q(Rect rect, View view) {
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

    private void setScrollingCacheEnabled(boolean z) {
        if (this.A != z) {
            this.A = z;
        }
    }

    private f t() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.r / clientWidth : 0.0f;
        f fVar = null;
        int i3 = 0;
        int i4 = -1;
        boolean z = true;
        float f4 = 0.0f;
        while (i3 < this.f2069g.size()) {
            f fVar2 = this.f2069g.get(i3);
            if (!z && fVar2.f2079b != (i2 = i4 + 1)) {
                fVar2 = this.f2070h;
                fVar2.f2082e = f2 + f4 + f3;
                fVar2.f2079b = i2;
                fVar2.f2081d = this.j.h(i2);
                i3--;
            }
            f2 = fVar2.f2082e;
            float f5 = fVar2.f2081d + f2 + f3;
            if (!z && scrollX < f2) {
                return fVar;
            }
            if (scrollX < f5 || i3 == this.f2069g.size() - 1) {
                return fVar2;
            }
            i4 = fVar2.f2079b;
            f4 = fVar2.f2081d;
            i3++;
            z = false;
            fVar = fVar2;
        }
        return fVar;
    }

    private static boolean w(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    private boolean x(float f2, float f3) {
        return (f2 < ((float) this.G) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.G)) && f3 < 0.0f);
    }

    private void z(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.M) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.I = motionEvent.getX(i2);
            this.M = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.N;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    boolean A() {
        int i2 = this.k;
        if (i2 <= 0) {
            return false;
        }
        N(i2 - 1, true);
        return true;
    }

    boolean B() {
        androidx.viewpager.widget.a aVar = this.j;
        if (aVar == null || this.k >= aVar.e() - 1) {
            return false;
        }
        N(this.k + 1, true);
        return true;
    }

    void E() {
        F(this.k);
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
    void F(int r18) {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.F(int):void");
    }

    public void I(i iVar) {
        List<i> list = this.c0;
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

    public void N(int i2, boolean z) {
        this.B = false;
        O(i2, z, false);
    }

    void O(int i2, boolean z, boolean z2) {
        P(i2, z, z2, 0);
    }

    void P(int i2, boolean z, boolean z2, int i3) {
        androidx.viewpager.widget.a aVar = this.j;
        if (aVar == null || aVar.e() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.k == i2 && this.f2069g.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.j.e()) {
            i2 = this.j.e() - 1;
        }
        int i4 = this.C;
        int i5 = this.k;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.f2069g.size(); i6++) {
                this.f2069g.get(i6).f2080c = true;
            }
        }
        boolean z3 = this.k != i2;
        if (!this.V) {
            F(i2);
            M(i2, z, i3, z3);
        } else {
            this.k = i2;
            if (z3) {
                k(i2);
            }
            requestLayout();
        }
    }

    j Q(j jVar) {
        j jVar2 = this.b0;
        this.b0 = jVar;
        return jVar2;
    }

    void R(int i2, int i3, int i4) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.o;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.p ? this.o.getCurrX() : this.o.getStartX();
            this.o.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            g(false);
            E();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float fM = f3 + (m(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int iAbs = Math.abs(i4);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fM / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i6) / ((f2 * this.j.h(this.k)) + this.r)) + 1.0f) * 100.0f), 600);
        this.p = false;
        this.o.startScroll(i5, scrollY, i6, i7, iMin);
        t.a0(this);
    }

    f a(int i2, int i3) {
        f fVar = new f();
        fVar.f2079b = i2;
        fVar.f2078a = this.j.j(this, i2);
        fVar.f2081d = this.j.h(i2);
        if (i3 < 0 || i3 >= this.f2069g.size()) {
            this.f2069g.add(fVar);
        } else {
            this.f2069g.add(i3, fVar);
        }
        return fVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        f fVarS;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f2079b == this.k) {
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
        f fVarS;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f2079b == this.k) {
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
        boolean zW = gVar.f2083a | w(view);
        gVar.f2083a = zW;
        if (!this.z) {
            super.addView(view, i2, layoutParams);
        } else {
            if (zW) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f2086d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public void b(i iVar) {
        if (this.c0 == null) {
            this.c0 = new ArrayList();
        }
        this.c0.add(iVar);
    }

    public void c(j jVar) {
        if (this.Z == null) {
            this.Z = new ArrayList();
        }
        this.Z.add(jVar);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.j == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.v)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.w));
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.p = true;
        if (this.o.isFinished() || !this.o.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.o.getCurrX();
        int currY = this.o.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!C(currX)) {
                this.o.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.a0(this);
    }

    public boolean d(int i2) {
        boolean z;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zA = false;
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
                zRequestFocus = (viewFindFocus == null || q(this.f2071i, viewFindNextFocus).left < q(this.f2071i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : A();
            } else if (i2 == 66) {
                zRequestFocus = (viewFindFocus == null || q(this.f2071i, viewFindNextFocus).left > q(this.f2071i, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : B();
            }
            zA = zRequestFocus;
        } else if (i2 == 17 || i2 == 1) {
            zA = A();
        } else if (i2 == 66 || i2 == 2) {
            zA = B();
        }
        if (zA) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
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
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f2079b == this.k && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        androidx.viewpager.widget.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.j) != null && aVar.e() > 1)) {
            if (!this.T.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.v * width);
                this.T.setSize(height, width);
                zDraw = false | this.T.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.U.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.w + 1.0f)) * width2);
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
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.s;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    protected boolean f(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && f(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
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
        return this.j;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.f0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) this.g0.get(i3).getLayoutParams()).f2088f;
    }

    public int getCurrentItem() {
        return this.k;
    }

    public int getOffscreenPageLimit() {
        return this.C;
    }

    public int getPageMargin() {
        return this.r;
    }

    void h() {
        int iE = this.j.e();
        this.f2068f = iE;
        boolean z = this.f2069g.size() < (this.C * 2) + 1 && this.f2069g.size() < iE;
        int iMax = this.k;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.f2069g.size()) {
            f fVar = this.f2069g.get(i2);
            int iF = this.j.f(fVar.f2078a);
            if (iF != -1) {
                if (iF == -2) {
                    this.f2069g.remove(i2);
                    i2--;
                    if (!z2) {
                        this.j.s(this);
                        z2 = true;
                    }
                    this.j.b(this, fVar.f2079b, fVar.f2078a);
                    int i3 = this.k;
                    if (i3 == fVar.f2079b) {
                        iMax = Math.max(0, Math.min(i3, iE - 1));
                    }
                } else {
                    int i4 = fVar.f2079b;
                    if (i4 != iF) {
                        if (i4 == this.k) {
                            iMax = iF;
                        }
                        fVar.f2079b = iF;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.j.d(this);
        }
        Collections.sort(this.f2069g, f2065c);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                g gVar = (g) getChildAt(i5).getLayoutParams();
                if (!gVar.f2083a) {
                    gVar.f2085c = 0.0f;
                }
            }
            O(iMax, false, true);
            requestLayout();
        }
    }

    float m(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.V = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.h0);
        Scroller scroller = this.o;
        if (scroller != null && !scroller.isFinished()) {
            this.o.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.r <= 0 || this.s == null || this.f2069g.size() <= 0 || this.j == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.r / width;
        int i3 = 0;
        f fVar = this.f2069g.get(0);
        float f5 = fVar.f2082e;
        int size = this.f2069g.size();
        int i4 = fVar.f2079b;
        int i5 = this.f2069g.get(size - 1).f2079b;
        while (i4 < i5) {
            while (true) {
                i2 = fVar.f2079b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                fVar = this.f2069g.get(i3);
            }
            if (i4 == i2) {
                float f6 = fVar.f2082e;
                float f7 = fVar.f2081d;
                f2 = (f6 + f7) * width;
                f5 = f6 + f7 + f4;
            } else {
                float fH = this.j.h(i4);
                f2 = (f5 + fH) * width;
                f5 += fH + f4;
            }
            if (this.r + f2 > scrollX) {
                f3 = f4;
                this.s.setBounds(Math.round(f2), this.t, Math.round(this.r + f2), this.u);
                this.s.draw(canvas);
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
            float x = motionEvent.getX();
            this.K = x;
            this.I = x;
            float y = motionEvent.getY();
            this.L = y;
            this.J = y;
            this.M = motionEvent.getPointerId(0);
            this.E = false;
            this.p = true;
            this.o.computeScrollOffset();
            if (this.i0 != 2 || Math.abs(this.o.getFinalX() - this.o.getCurrX()) <= this.R) {
                g(false);
                this.D = false;
            } else {
                this.o.abortAnimation();
                this.B = false;
                E();
                this.D = true;
                K(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.M;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(iFindPointerIndex);
                float f2 = x2 - this.I;
                float fAbs = Math.abs(f2);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y2 - this.L);
                if (f2 != 0.0f && !x(this.I, f2) && f(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.I = x2;
                    this.J = y2;
                    this.E = true;
                    return false;
                }
                int i3 = this.H;
                if (fAbs > i3 && fAbs * 0.5f > fAbs2) {
                    this.D = true;
                    K(true);
                    setScrollState(1);
                    float f3 = this.K;
                    float f4 = this.H;
                    this.I = f2 > 0.0f ? f3 + f4 : f3 - f4;
                    this.J = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i3) {
                    this.E = true;
                }
                if (this.D && D(x2)) {
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
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        g gVar;
        g gVar2;
        int i4;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i2), ViewGroup.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.G = Math.min(measuredWidth / 10, this.F);
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
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f2083a) {
                int i7 = gVar2.f2084b;
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
        this.x = View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.y = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.z = true;
        E();
        this.z = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f2083a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.f2085c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), this.y);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        f fVarS;
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
            if (childAt.getVisibility() == 0 && (fVarS = s(childAt)) != null && fVarS.f2079b == this.k && childAt.requestFocus(i2, rect)) {
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
        androidx.viewpager.widget.a aVar = this.j;
        if (aVar != null) {
            aVar.m(savedState.f2073e, savedState.f2074f);
            O(savedState.f2072d, false, true);
        } else {
            this.l = savedState.f2072d;
            this.m = savedState.f2073e;
            this.n = savedState.f2074f;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2072d = this.k;
        androidx.viewpager.widget.a aVar = this.j;
        if (aVar != null) {
            savedState.f2073e = aVar.n();
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.r;
            G(i2, i4, i6, i6);
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

    f r(View view) {
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
        if (this.z) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    f s(View view) {
        for (int i2 = 0; i2 < this.f2069g.size(); i2++) {
            f fVar = this.f2069g.get(i2);
            if (this.j.k(view, fVar.f2078a)) {
                return fVar;
            }
        }
        return null;
    }

    public void setAdapter(androidx.viewpager.widget.a aVar) {
        androidx.viewpager.widget.a aVar2 = this.j;
        if (aVar2 != null) {
            aVar2.q(null);
            this.j.s(this);
            for (int i2 = 0; i2 < this.f2069g.size(); i2++) {
                f fVar = this.f2069g.get(i2);
                this.j.b(this, fVar.f2079b, fVar.f2078a);
            }
            this.j.d(this);
            this.f2069g.clear();
            H();
            this.k = 0;
            scrollTo(0, 0);
        }
        androidx.viewpager.widget.a aVar3 = this.j;
        this.j = aVar;
        this.f2068f = 0;
        if (aVar != null) {
            if (this.q == null) {
                this.q = new l();
            }
            this.j.q(this.q);
            this.B = false;
            boolean z = this.V;
            this.V = true;
            this.f2068f = this.j.e();
            if (this.l >= 0) {
                this.j.m(this.m, this.n);
                O(this.l, false, true);
                this.l = -1;
                this.m = null;
                this.n = null;
            } else if (z) {
                requestLayout();
            } else {
                E();
            }
        }
        List<i> list = this.c0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.c0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.c0.get(i3).b(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.B = false;
        O(i2, !this.V, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.C) {
            this.C = i2;
            E();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.a0 = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.r;
        this.r = i2;
        int width = getWidth();
        G(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.s = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    void setScrollState(int i2) {
        if (this.i0 == i2) {
            return;
        }
        this.i0 = i2;
        if (this.d0 != null) {
            n(i2 != 0);
        }
        l(i2);
    }

    f u(int i2) {
        for (int i3 = 0; i3 < this.f2069g.size(); i3++) {
            f fVar = this.f2069g.get(i3);
            if (fVar.f2079b == i2) {
                return fVar;
            }
        }
        return null;
    }

    void v() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.o = new Scroller(context, f2066d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.H = viewConfiguration.getScaledPagingTouchSlop();
        this.O = (int) (400.0f * f2);
        this.P = viewConfiguration.getScaledMaximumFlingVelocity();
        this.T = new EdgeEffect(context);
        this.U = new EdgeEffect(context);
        this.Q = (int) (25.0f * f2);
        this.R = (int) (2.0f * f2);
        this.F = (int) (f2 * 16.0f);
        t.j0(this, new h());
        if (t.x(this) == 0) {
            t.t0(this, 1);
        }
        t.v0(this, new d());
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.s;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void y(int r13, float r14, int r15) {
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
            boolean r10 = r9.f2083a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f2084b
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
            androidx.viewpager.widget.ViewPager$k r13 = r12.d0
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.f2083a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.d0
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

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2083a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2084b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f2085c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2086d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2087e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2088f;

        public g() {
            super(-1, -1);
            this.f2085c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2085c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f2064b);
            this.f2084b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(androidx.core.content.a.d(getContext(), i2));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2069g = new ArrayList<>();
        this.f2070h = new f();
        this.f2071i = new Rect();
        this.l = -1;
        this.m = null;
        this.n = null;
        this.v = -3.4028235E38f;
        this.w = Float.MAX_VALUE;
        this.C = 1;
        this.M = -1;
        this.V = true;
        this.W = false;
        this.h0 = new c();
        this.i0 = 0;
        v();
    }
}
