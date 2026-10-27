package a.p.a;

import a.g.k.t;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
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
public class b extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f643b = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Comparator<d> f644c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Interpolator f645d = new InterpolatorC0028b();
    private static final k e = new k();
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
    private boolean P;
    private EdgeEffect Q;
    private EdgeEffect R;
    private boolean S;
    private boolean T;
    private int U;
    private List<g> V;
    private g W;
    private g X;
    private List<f> Y;
    private h Z;
    private int a0;
    private int b0;
    private ArrayList<View> c0;
    private final Runnable d0;
    private int e0;
    private int f;
    private final ArrayList<d> g;
    private final d h;
    private final Rect i;
    a.p.a.a j;
    int k;
    private int l;
    private Parcelable m;
    private ClassLoader n;
    private Scroller o;
    private boolean p;
    private i q;
    private int r;
    private Drawable s;
    private int t;
    private int u;
    private float v;
    private float w;
    private int x;
    private int y;
    private boolean z;

    static class a implements Comparator<d> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            return dVar.f647b - dVar2.f647b;
        }
    }

    /* JADX INFO: renamed from: a.p.a.b$b, reason: collision with other inner class name */
    static class InterpolatorC0028b implements Interpolator {
        InterpolatorC0028b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface c {
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f646a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f647b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f648c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f649d;
        float e;

        d() {
        }
    }

    public static class e extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f650a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f651b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f652c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f653d;
        int e;
        int f;

        public e() {
            super(-1, -1);
            this.f652c = 0.0f;
        }

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f652c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f643b);
            this.f651b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public interface f {
        void a(b bVar, a.p.a.a aVar, a.p.a.a aVar2);
    }

    public interface g {
        void a(int i, float f, int i2);

        void b(int i);

        void c(int i);
    }

    public interface h {
        void a(View view, float f);
    }

    private class i extends DataSetObserver {
        i() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            b.this.f();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            b.this.f();
        }
    }

    public static class j extends a.i.a.a {
        public static final Parcelable.Creator<j> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f655d;
        Parcelable e;
        ClassLoader f;

        static class a implements Parcelable.ClassLoaderCreator<j> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public j createFromParcel(Parcel parcel) {
                return new j(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public j createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new j(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public j[] newArray(int i) {
                return new j[i];
            }
        }

        j(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? j.class.getClassLoader() : classLoader;
            this.f655d = parcel.readInt();
            this.e = parcel.readParcelable(classLoader);
            this.f = classLoader;
        }

        public j(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f655d + "}";
        }

        @Override // a.i.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f655d);
            parcel.writeParcelable(this.e, i);
        }
    }

    static class k implements Comparator<View> {
        k() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            e eVar = (e) view.getLayoutParams();
            e eVar2 = (e) view2.getLayoutParams();
            boolean z = eVar.f650a;
            return z != eVar2.f650a ? z ? 1 : -1 : eVar.e - eVar2.e;
        }
    }

    private void B(int i2, int i3, int i4, int i5) {
        int iMin;
        if (i3 <= 0 || this.g.isEmpty()) {
            d dVarQ = q(this.k);
            iMin = (int) ((dVarQ != null ? Math.min(dVarQ.e, this.w) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
            if (iMin == getScrollX()) {
                return;
            } else {
                e(false);
            }
        } else if (!this.o.isFinished()) {
            this.o.setFinalX(getCurrentItem() * getClientWidth());
            return;
        } else {
            iMin = (int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4));
        }
        scrollTo(iMin, getScrollY());
    }

    private void E(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean F() {
        this.M = -1;
        l();
        this.Q.onRelease();
        this.R.onRelease();
        return this.Q.isFinished() || this.R.isFinished();
    }

    private void G(int i2, boolean z, int i3, boolean z2) {
        d dVarQ = q(i2);
        int clientWidth = dVarQ != null ? (int) (getClientWidth() * Math.max(this.v, Math.min(dVarQ.e, this.w))) : 0;
        if (z) {
            K(clientWidth, 0, i3);
            if (z2) {
                h(i2);
                return;
            }
            return;
        }
        if (z2) {
            h(i2);
        }
        e(false);
        scrollTo(clientWidth, 0);
        x(clientWidth);
    }

    private void L() {
        if (this.b0 != 0) {
            ArrayList<View> arrayList = this.c0;
            if (arrayList == null) {
                this.c0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.c0.add(getChildAt(i2));
            }
            Collections.sort(this.c0, e);
        }
    }

    private void e(boolean z) {
        boolean z2 = this.e0 == 2;
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
                        x(currX);
                    }
                }
            }
        }
        this.B = false;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            d dVar = this.g.get(i2);
            if (dVar.f648c) {
                dVar.f648c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                t.Z(this, this.d0);
            } else {
                this.d0.run();
            }
        }
    }

    private void g(int i2, float f2, int i3) {
        g gVar = this.W;
        if (gVar != null) {
            gVar.a(i2, f2, i3);
        }
        List<g> list = this.V;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                g gVar2 = this.V.get(i4);
                if (gVar2 != null) {
                    gVar2.a(i2, f2, i3);
                }
            }
        }
        g gVar3 = this.X;
        if (gVar3 != null) {
            gVar3.a(i2, f2, i3);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void h(int i2) {
        g gVar = this.W;
        if (gVar != null) {
            gVar.c(i2);
        }
        List<g> list = this.V;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                g gVar2 = this.V.get(i3);
                if (gVar2 != null) {
                    gVar2.c(i2);
                }
            }
        }
        g gVar3 = this.X;
        if (gVar3 != null) {
            gVar3.c(i2);
        }
    }

    private void i(int i2) {
        g gVar = this.W;
        if (gVar != null) {
            gVar.b(i2);
        }
        List<g> list = this.V;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                g gVar2 = this.V.get(i3);
                if (gVar2 != null) {
                    gVar2.b(i2);
                }
            }
        }
        g gVar3 = this.X;
        if (gVar3 != null) {
            gVar3.b(i2);
        }
    }

    private void k(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.a0 : 0, null);
        }
    }

    private void l() {
        this.D = false;
        this.E = false;
        VelocityTracker velocityTracker = this.N;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.N = null;
        }
    }

    private Rect n(Rect rect, View view) {
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

    private d p() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.r / clientWidth : 0.0f;
        int i3 = -1;
        float f4 = 0.0f;
        int i4 = 0;
        d dVar = null;
        boolean z = true;
        while (i4 < this.g.size()) {
            d dVar2 = this.g.get(i4);
            if (!z && dVar2.f647b != (i2 = i3 + 1)) {
                d dVar3 = this.h;
                dVar3.e = f2 + f4 + f3;
                dVar3.f647b = i2;
                throw null;
            }
            f2 = dVar2.e;
            float f5 = dVar2.f649d + f2 + f3;
            if (!z && scrollX < f2) {
                return dVar;
            }
            if (scrollX < f5 || i4 == this.g.size() - 1) {
                return dVar2;
            }
            i3 = dVar2.f647b;
            f4 = dVar2.f649d;
            i4++;
            z = false;
            dVar = dVar2;
        }
        return dVar;
    }

    private static boolean r(View view) {
        return view.getClass().getAnnotation(c.class) != null;
    }

    private boolean s(float f2, float f3) {
        return (f2 < ((float) this.G) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.G)) && f3 < 0.0f);
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.A != z) {
            this.A = z;
        }
    }

    private void u(MotionEvent motionEvent) {
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

    private boolean x(int i2) {
        if (this.g.size() == 0) {
            if (this.S) {
                return false;
            }
            this.T = false;
            t(0, 0.0f, 0);
            if (this.T) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        d dVarP = p();
        int clientWidth = getClientWidth();
        int i3 = this.r;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = dVarP.f647b;
        float f3 = ((i2 / f2) - dVarP.e) / (dVarP.f649d + (i3 / f2));
        this.T = false;
        t(i5, f3, (int) (i4 * f3));
        if (this.T) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean y(float f2) {
        this.I = f2;
        getScrollX();
        getClientWidth();
        d dVar = this.g.get(0);
        d dVar2 = this.g.get(r0.size() - 1);
        int i2 = dVar.f647b;
        int i3 = dVar2.f647b;
        throw null;
    }

    void A(int i2) {
        int i3 = this.k;
        if (i3 != i2) {
            q(i3);
            this.k = i2;
        }
        if (this.j == null) {
            L();
        } else if (this.B) {
            L();
        } else if (getWindowToken() != null) {
            throw null;
        }
    }

    public void C(f fVar) {
        List<f> list = this.Y;
        if (list != null) {
            list.remove(fVar);
        }
    }

    public void D(g gVar) {
        List<g> list = this.V;
        if (list != null) {
            list.remove(gVar);
        }
    }

    public void H(int i2, boolean z) {
        this.B = false;
        I(i2, z, false);
    }

    void I(int i2, boolean z, boolean z2) {
        J(i2, z, z2, 0);
    }

    void J(int i2, boolean z, boolean z2, int i3) {
        if (this.j != null) {
            throw null;
        }
        setScrollingCacheEnabled(false);
    }

    void K(int i2, int i3, int i4) {
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
            e(false);
            z();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        float f2 = clientWidth / 2;
        float fJ = f2 + (j(Math.min(1.0f, (Math.abs(i6) * 1.0f) / clientWidth)) * f2);
        int iAbs = Math.abs(i4);
        if (iAbs <= 0) {
            throw null;
        }
        int iMin = Math.min(Math.round(Math.abs(fJ / iAbs) * 1000.0f) * 4, 600);
        this.p = false;
        this.o.startScroll(i5, scrollY, i6, i7, iMin);
        t.Y(this);
    }

    public void a(f fVar) {
        if (this.Y == null) {
            this.Y = new ArrayList();
        }
        this.Y.add(fVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        d dVarO;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (dVarO = o(childAt)) != null && dVarO.f647b == this.k) {
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
        d dVarO;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (dVarO = o(childAt)) != null && dVarO.f647b == this.k) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        e eVar = (e) layoutParams;
        boolean zR = eVar.f650a | r(view);
        eVar.f650a = zR;
        if (!this.z) {
            super.addView(view, i2, layoutParams);
        } else {
            if (zR) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            eVar.f653d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public void b(g gVar) {
        if (this.V == null) {
            this.V = new ArrayList();
        }
        this.V.add(gVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(int r7) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.a.b.c(int):boolean");
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
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.p = true;
        if (this.o.isFinished() || !this.o.computeScrollOffset()) {
            e(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.o.getCurrX();
        int currY = this.o.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!x(currX)) {
                this.o.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.Y(this);
    }

    protected boolean d(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && d(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        d dVarO;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (dVarO = o(childAt)) != null && dVarO.f647b == this.k && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0) {
            if (!this.Q.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.v * width);
                this.Q.setSize(height, width);
                zDraw = false | this.Q.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.R.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.w + 1.0f)) * width2);
                this.R.setSize(height2, width2);
                zDraw |= this.R.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            if (overScrollMode == 1 && this.j != null) {
                throw null;
            }
            this.Q.finish();
            this.R.finish();
        }
        if (zDraw) {
            t.Y(this);
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

    void f() {
        throw null;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public a.p.a.a getAdapter() {
        return this.j;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.b0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((e) this.c0.get(i3).getLayoutParams()).f;
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

    float j(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    public boolean m(KeyEvent keyEvent) {
        int i2;
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return c(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return c(1);
                        }
                    }
                } else {
                    if (keyEvent.hasModifiers(2)) {
                        return w();
                    }
                    i2 = 66;
                }
            } else {
                if (keyEvent.hasModifiers(2)) {
                    return v();
                }
                i2 = 17;
            }
            return c(i2);
        }
        return false;
    }

    d o(View view) {
        if (this.g.size() <= 0) {
            return null;
        }
        Object obj = this.g.get(0).f646a;
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.S = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.d0);
        Scroller scroller = this.o;
        if (scroller != null && !scroller.isFinished()) {
            this.o.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (this.r <= 0 || this.s == null || this.g.size() <= 0 || this.j == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        int i3 = 0;
        d dVar = this.g.get(0);
        float f2 = dVar.e;
        int size = this.g.size();
        int i4 = this.g.get(size - 1).f647b;
        for (int i5 = dVar.f647b; i5 < i4; i5++) {
            while (true) {
                i2 = dVar.f647b;
                if (i5 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                dVar = this.g.get(i3);
            }
            if (i5 != i2) {
                throw null;
            }
            float f3 = (dVar.e + dVar.f649d) * width;
            if (this.r + f3 > scrollX) {
                this.s.setBounds(Math.round(f3), this.t, Math.round(this.r + f3), this.u);
                this.s.draw(canvas);
            }
            if (f3 > scrollX + r1) {
                return;
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            F();
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
            if (this.e0 != 2 || Math.abs(this.o.getFinalX() - this.o.getCurrX()) <= this.O) {
                e(false);
                this.D = false;
            } else {
                this.o.abortAnimation();
                this.B = false;
                z();
                this.D = true;
                E(true);
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
                if (f2 != 0.0f && !s(this.I, f2) && d(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.I = x2;
                    this.J = y2;
                    this.E = true;
                    return false;
                }
                int i3 = this.H;
                if (fAbs > i3 && fAbs * 0.5f > fAbs2) {
                    this.D = true;
                    E(true);
                    setScrollState(1);
                    float f3 = this.K;
                    float f4 = this.H;
                    this.I = f2 > 0.0f ? f3 + f4 : f3 - f4;
                    this.J = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i3) {
                    this.E = true;
                }
                if (this.D && y(x2)) {
                    t.Y(this);
                }
            }
        } else if (action == 6) {
            u(motionEvent);
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
        throw new UnsupportedOperationException("Method not decompiled: a.p.a.b.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        e eVar;
        e eVar2;
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
            int i6 = 1073741824;
            if (i5 >= childCount) {
                break;
            }
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8 && (eVar2 = (e) childAt.getLayoutParams()) != null && eVar2.f650a) {
                int i7 = eVar2.f651b;
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
                int i11 = ((ViewGroup.LayoutParams) eVar2).width;
                if (i11 != -2) {
                    if (i11 == -1) {
                        i11 = paddingLeft;
                    }
                    i10 = 1073741824;
                } else {
                    i11 = paddingLeft;
                }
                int i12 = ((ViewGroup.LayoutParams) eVar2).height;
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
        this.x = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.y = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.z = true;
        z();
        this.z = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((eVar = (e) childAt2.getLayoutParams()) == null || !eVar.f650a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * eVar.f652c), 1073741824), this.y);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        d dVarO;
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
            if (childAt.getVisibility() == 0 && (dVarO = o(childAt)) != null && dVarO.f647b == this.k && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof j)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        j jVar = (j) parcelable;
        super.onRestoreInstanceState(jVar.u());
        if (this.j != null) {
            throw null;
        }
        this.l = jVar.f655d;
        this.m = jVar.e;
        this.n = jVar.f;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        j jVar = new j(super.onSaveInstanceState());
        jVar.f655d = this.k;
        if (this.j == null) {
            return jVar;
        }
        throw null;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.r;
            B(i2, i4, i6, i6);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.P) {
            return true;
        }
        if ((motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) && this.j != null) {
            throw null;
        }
        return false;
    }

    d q(int i2) {
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            d dVar = this.g.get(i3);
            if (dVar.f647b == i2) {
                return dVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.z) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(a.p.a.a aVar) {
        a.p.a.a aVar2 = this.j;
        if (aVar2 != null) {
            throw null;
        }
        this.f = 0;
        if (aVar != null) {
            if (this.q != null) {
                throw null;
            }
            this.q = new i();
            throw null;
        }
        List<f> list = this.Y;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.Y.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.Y.get(i2).a(this, aVar2, aVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.B = false;
        I(i2, !this.S, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.C) {
            this.C = i2;
            z();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(g gVar) {
        this.W = gVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.r;
        this.r = i2;
        int width = getWidth();
        B(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(androidx.core.content.a.d(getContext(), i2));
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
        if (this.e0 == i2) {
            return;
        }
        this.e0 = i2;
        if (this.Z != null) {
            k(i2 != 0);
        }
        i(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void t(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.U
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
            a.p.a.b$e r9 = (a.p.a.b.e) r9
            boolean r10 = r9.f650a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.f651b
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
            r12.g(r13, r14, r15)
            a.p.a.b$h r13 = r12.Z
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            a.p.a.b$e r0 = (a.p.a.b.e) r0
            boolean r0 = r0.f650a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            a.p.a.b$h r3 = r12.Z
            r3.a(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.T = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.a.b.t(int, float, int):void");
    }

    boolean v() {
        int i2 = this.k;
        if (i2 <= 0) {
            return false;
        }
        H(i2 - 1, true);
        return true;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.s;
    }

    boolean w() {
        if (this.j == null) {
            return false;
        }
        throw null;
    }

    void z() {
        A(this.k);
    }
}
