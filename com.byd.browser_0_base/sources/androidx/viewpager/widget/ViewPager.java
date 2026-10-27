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
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import defpackage.C0214v;
import defpackage.bz;
import defpackage.en3;
import defpackage.in3;
import defpackage.kv1;
import defpackage.om3;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    public static final int[] d0 = {R.attr.layout_gravity};
    public static final a e0 = new a();
    public static final b f0 = new b();
    public boolean A;
    public int B;
    public int C;
    public int D;
    public boolean E;
    public float F;
    public float G;
    public float H;
    public float I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f24J;
    public VelocityTracker K;
    public int L;
    public int M;
    public int N;
    public int O;
    public EdgeEffect P;
    public EdgeEffect Q;
    public boolean R;
    public boolean S;
    public int T;
    public ArrayList U;
    public h V;
    public h W;
    public ArrayList a0;
    public final c b0;
    public int c0;
    public int e;
    public final ArrayList<e> f;
    public final e g;
    public final Rect h;
    public kv1 i;
    public int j;
    public int k;
    public Parcelable l;
    public Scroller m;
    public boolean n;
    public i o;
    public int p;
    public Drawable q;
    public int r;
    public int s;
    public float t;
    public float u;
    public boolean v;
    public boolean w;
    public boolean x;
    public int y;
    public boolean z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int g;
        public Parcelable h;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.g + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g);
            parcel.writeParcelable(this.h, i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.g = parcel.readInt();
            this.h = parcel.readParcelable(classLoader);
        }
    }

    public class a implements Comparator<e> {
        @Override // java.util.Comparator
        public final int compare(e eVar, e eVar2) {
            return eVar.b - eVar2.b;
        }
    }

    public class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewPager viewPager = ViewPager.this;
            viewPager.setScrollState(0);
            viewPager.q();
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface d {
    }

    public static class e {
        public Object a;
        public int b;
        public boolean c;
        public float d;
        public float e;
    }

    public class f extends C0214v {
        public f() {
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
        @Override // defpackage.C0214v
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void c(android.view.View r2, android.view.accessibility.AccessibilityEvent r3) {
            /*
                r1 = this;
                super.c(r2, r3)
                java.lang.String r2 = "androidx.viewpager.widget.ViewPager"
                r3.setClassName(r2)
                androidx.viewpager.widget.ViewPager r1 = androidx.viewpager.widget.ViewPager.this
                kv1 r2 = r1.i
                if (r2 == 0) goto L16
                int r2 = r2.b()
                r0 = 1
                if (r2 <= r0) goto L16
                goto L17
            L16:
                r0 = 0
            L17:
                r3.setScrollable(r0)
                int r2 = r3.getEventType()
                r0 = 4096(0x1000, float:5.74E-42)
                if (r2 != r0) goto L37
                kv1 r2 = r1.i
                if (r2 == 0) goto L37
                int r2 = r2.b()
                r3.setItemCount(r2)
                int r2 = r1.j
                r3.setFromIndex(r2)
                int r1 = r1.j
                r3.setToIndex(r1)
            L37:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.f.c(android.view.View, android.view.accessibility.AccessibilityEvent):void");
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
            accessibilityNodeInfoCompat.n("androidx.viewpager.widget.ViewPager");
            ViewPager viewPager = ViewPager.this;
            kv1 kv1Var = viewPager.i;
            accessibilityNodeInfoCompat.s(kv1Var != null && kv1Var.b() > 1);
            if (viewPager.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.a(4096);
            }
            if (viewPager.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.a(8192);
            }
        }

        @Override // defpackage.C0214v
        public final boolean g(View view, int i, Bundle bundle) {
            if (super.g(view, i, bundle)) {
                return true;
            }
            ViewPager viewPager = ViewPager.this;
            if (i == 4096) {
                if (!viewPager.canScrollHorizontally(1)) {
                    return false;
                }
                viewPager.setCurrentItem(viewPager.j + 1);
                return true;
            }
            if (i != 8192 || !viewPager.canScrollHorizontally(-1)) {
                return false;
            }
            viewPager.setCurrentItem(viewPager.j - 1);
            return true;
        }
    }

    public interface g {
        void a(ViewPager viewPager, kv1 kv1Var, kv1 kv1Var2);
    }

    public interface h {
        void b(int i);

        void c(int i);

        void d(int i, float f);
    }

    public class i extends DataSetObserver {
        public i() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            ViewPager.this.e();
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            ViewPager.this.e();
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f = new ArrayList<>();
        this.g = new e();
        this.h = new Rect();
        this.k = -1;
        this.l = null;
        this.t = -3.4028235E38f;
        this.u = Float.MAX_VALUE;
        this.y = 1;
        this.E = true;
        this.f24J = -1;
        this.R = true;
        this.b0 = new c();
        this.c0 = 0;
        k(context);
    }

    public static boolean c(int i2, int i3, int i4, View view, boolean z) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && c(i2, i6 - childAt.getLeft(), i5 - childAt.getTop(), childAt, true)) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.w != z) {
            this.w = z;
        }
    }

    public final e a(int i2, int i3) {
        e eVar = new e();
        eVar.b = i2;
        eVar.a = this.i.d(i2, this);
        this.i.getClass();
        eVar.d = 1.0f;
        ArrayList<e> arrayList = this.f;
        if (i3 < 0 || i3 >= arrayList.size()) {
            arrayList.add(eVar);
        } else {
            arrayList.add(i3, eVar);
        }
        return eVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        e eVarH;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (eVarH = h(childAt)) != null && eVarH.b == this.j) {
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

    public void addOnAdapterChangeListener(g gVar) {
        if (this.a0 == null) {
            this.a0 = new ArrayList();
        }
        this.a0.add(gVar);
    }

    public void addOnPageChangeListener(h hVar) {
        if (this.U == null) {
            this.U = new ArrayList();
        }
        this.U.add(hVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addTouchables(ArrayList<View> arrayList) {
        e eVarH;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (eVarH = h(childAt)) != null && eVarH.b == this.j) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean z = layoutParams2.a | (view.getClass().getAnnotation(d.class) != null);
        layoutParams2.a = z;
        if (!this.v) {
            super.addView(view, i2, layoutParams);
        } else {
            if (z) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(int r8) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.b(int):boolean");
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i2) {
        if (this.i == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.t)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.u));
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        this.n = true;
        if (this.m.isFinished() || !this.m.computeScrollOffset()) {
            d(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.m.getCurrX();
        int currY = this.m.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!o(currX)) {
                this.m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.j(this);
    }

    public final void d(boolean z) {
        boolean z2 = this.c0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.m.isFinished()) {
                this.m.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.m.getCurrX();
                int currY = this.m.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        o(currX);
                    }
                }
            }
        }
        this.x = false;
        int i2 = 0;
        while (true) {
            ArrayList<e> arrayList = this.f;
            if (i2 >= arrayList.size()) {
                break;
            }
            e eVar = arrayList.get(i2);
            if (eVar.c) {
                eVar.c = false;
                z2 = true;
            }
            i2++;
        }
        if (z2) {
            c cVar = this.b0;
            if (!z) {
                cVar.run();
            } else {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.k(this, cVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            boolean r0 = super.dispatchKeyEvent(r6)
            r1 = 1
            if (r0 != 0) goto L66
            int r0 = r6.getAction()
            r2 = 0
            if (r0 != 0) goto L61
            int r0 = r6.getKeyCode()
            r3 = 21
            r4 = 2
            if (r0 == r3) goto L48
            r3 = 22
            if (r0 == r3) goto L36
            r3 = 61
            if (r0 == r3) goto L20
            goto L61
        L20:
            boolean r0 = r6.hasNoModifiers()
            if (r0 == 0) goto L2b
            boolean r5 = r5.b(r4)
            goto L62
        L2b:
            boolean r6 = r6.hasModifiers(r1)
            if (r6 == 0) goto L61
            boolean r5 = r5.b(r1)
            goto L62
        L36:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L41
            boolean r5 = r5.n()
            goto L62
        L41:
            r6 = 66
            boolean r5 = r5.b(r6)
            goto L62
        L48:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L5a
            int r6 = r5.j
            if (r6 <= 0) goto L61
            int r6 = r6 - r1
            r5.x = r2
            r5.v(r6, r2, r1, r2)
            r5 = r1
            goto L62
        L5a:
            r6 = 17
            boolean r5 = r5.b(r6)
            goto L62
        L61:
            r5 = r2
        L62:
            if (r5 == 0) goto L65
            goto L66
        L65:
            r1 = r2
        L66:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        e eVarH;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (eVarH = h(childAt)) != null && eVarH.b == this.j && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        kv1 kv1Var;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (kv1Var = this.i) != null && kv1Var.b() > 1)) {
            if (!this.P.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate(getPaddingTop() + (-height), this.t * width);
                this.P.setSize(height, width);
                zDraw = false | this.P.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.Q.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.u + 1.0f)) * width2);
                this.Q.setSize(height2, width2);
                zDraw |= this.Q.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.P.finish();
            this.Q.finish();
        }
        if (zDraw) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.q;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e() {
        int iB = this.i.b();
        this.e = iB;
        ArrayList<e> arrayList = this.f;
        boolean z = arrayList.size() < (this.y * 2) + 1 && arrayList.size() < iB;
        int i2 = this.j;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            e eVar = arrayList.get(i3);
            kv1 kv1Var = this.i;
            Object obj = eVar.a;
            kv1Var.getClass();
        }
        Collections.sort(arrayList, e0);
        if (z) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.a) {
                    layoutParams.c = 0.0f;
                }
            }
            v(i2, 0, false, true);
            requestLayout();
        }
    }

    public final void f(int i2) {
        h hVar = this.V;
        if (hVar != null) {
            hVar.c(i2);
        }
        ArrayList arrayList = this.U;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                h hVar2 = (h) this.U.get(i3);
                if (hVar2 != null) {
                    hVar2.c(i2);
                }
            }
        }
        h hVar3 = this.W;
        if (hVar3 != null) {
            hVar3.c(i2);
        }
    }

    public final Rect g(View view, Rect rect) {
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
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public kv1 getAdapter() {
        return this.i;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i2, int i3) {
        throw null;
    }

    public int getCurrentItem() {
        return this.j;
    }

    public int getOffscreenPageLimit() {
        return this.y;
    }

    public int getPageMargin() {
        return this.p;
    }

    public final e h(View view) {
        int i2 = 0;
        while (true) {
            ArrayList<e> arrayList = this.f;
            if (i2 >= arrayList.size()) {
                return null;
            }
            e eVar = arrayList.get(i2);
            if (this.i.e(view, eVar.a)) {
                return eVar;
            }
            i2++;
        }
    }

    public final e i() {
        e eVar;
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.p / clientWidth : 0.0f;
        int i3 = 0;
        boolean z = true;
        e eVar2 = null;
        int i4 = -1;
        float f4 = 0.0f;
        while (true) {
            ArrayList<e> arrayList = this.f;
            if (i3 >= arrayList.size()) {
                return eVar2;
            }
            e eVar3 = arrayList.get(i3);
            if (z || eVar3.b == (i2 = i4 + 1)) {
                eVar = eVar3;
            } else {
                float f5 = f2 + f4 + f3;
                e eVar4 = this.g;
                eVar4.e = f5;
                eVar4.b = i2;
                this.i.getClass();
                eVar4.d = 1.0f;
                i3--;
                eVar = eVar4;
            }
            f2 = eVar.e;
            float f6 = eVar.d + f2 + f3;
            if (!z && scrollX < f2) {
                return eVar2;
            }
            if (scrollX < f6 || i3 == arrayList.size() - 1) {
                break;
            }
            int i5 = eVar.b;
            float f7 = eVar.d;
            i3++;
            z = false;
            e eVar5 = eVar;
            i4 = i5;
            f4 = f7;
            eVar2 = eVar5;
        }
        return eVar;
    }

    public final e j(int i2) {
        int i3 = 0;
        while (true) {
            ArrayList<e> arrayList = this.f;
            if (i3 >= arrayList.size()) {
                return null;
            }
            e eVar = arrayList.get(i3);
            if (eVar.b == i2) {
                return eVar;
            }
            i3++;
        }
    }

    public final void k(Context context) {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        this.m = new Scroller(context, f0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.D = viewConfiguration.getScaledPagingTouchSlop();
        this.L = (int) (400.0f * f2);
        this.M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.P = new EdgeEffect(context);
        this.Q = new EdgeEffect(context);
        this.N = (int) (25.0f * f2);
        this.O = (int) (2.0f * f2);
        this.B = (int) (f2 * 16.0f);
        om3.k(this, new f());
        if (om3.d.b(this) == 0) {
            om3.d.p(this, 1);
        }
        om3.i.m(this, new en3(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(int r12, int r13, float r14) {
        /*
            r11 = this;
            int r13 = r11.T
            r0 = 0
            r1 = 1
            if (r13 <= 0) goto L6b
            int r13 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = r0
        L1b:
            if (r6 >= r5) goto L6b
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r8 = (androidx.viewpager.widget.ViewPager.LayoutParams) r8
            boolean r9 = r8.a
            if (r9 != 0) goto L2c
            goto L68
        L2c:
            int r8 = r8.b
            r8 = r8 & 7
            if (r8 == r1) goto L4d
            r9 = 3
            if (r8 == r9) goto L47
            r9 = 5
            if (r8 == r9) goto L3a
            r8 = r2
            goto L5c
        L3a:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
            goto L59
        L47:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L5c
        L4d:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
        L59:
            r10 = r8
            r8 = r2
            r2 = r10
        L5c:
            int r2 = r2 + r13
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L67
            r7.offsetLeftAndRight(r2)
        L67:
            r2 = r8
        L68:
            int r6 = r6 + 1
            goto L1b
        L6b:
            androidx.viewpager.widget.ViewPager$h r13 = r11.V
            if (r13 == 0) goto L72
            r13.d(r12, r14)
        L72:
            java.util.ArrayList r13 = r11.U
            if (r13 == 0) goto L8c
            int r13 = r13.size()
        L7a:
            if (r0 >= r13) goto L8c
            java.util.ArrayList r2 = r11.U
            java.lang.Object r2 = r2.get(r0)
            androidx.viewpager.widget.ViewPager$h r2 = (androidx.viewpager.widget.ViewPager.h) r2
            if (r2 == 0) goto L89
            r2.d(r12, r14)
        L89:
            int r0 = r0 + 1
            goto L7a
        L8c:
            androidx.viewpager.widget.ViewPager$h r13 = r11.W
            if (r13 == 0) goto L93
            r13.d(r12, r14)
        L93:
            r11.S = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.l(int, int, float):void");
    }

    public final void m(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f24J) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.F = motionEvent.getX(i2);
            this.f24J = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.K;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean n() {
        kv1 kv1Var = this.i;
        if (kv1Var == null || this.j >= kv1Var.b() - 1) {
            return false;
        }
        int i2 = this.j + 1;
        this.x = false;
        v(i2, 0, true, false);
        return true;
    }

    public final boolean o(int i2) {
        if (this.f.size() == 0) {
            if (this.R) {
                return false;
            }
            this.S = false;
            l(0, 0, 0.0f);
            if (this.S) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        e eVarI = i();
        int clientWidth = getClientWidth();
        int i3 = this.p;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = eVarI.b;
        float f3 = ((i2 / f2) - eVarI.e) / (eVarI.d + (i3 / f2));
        this.S = false;
        l(i5, (int) (i4 * f3), f3);
        if (this.S) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.R = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.b0);
        Scroller scroller = this.m;
        if (scroller != null && !scroller.isFinished()) {
            this.m.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i2;
        float f2;
        ArrayList<e> arrayList;
        float f3;
        super.onDraw(canvas);
        if (this.p <= 0 || this.q == null) {
            return;
        }
        ArrayList<e> arrayList2 = this.f;
        if (arrayList2.size() <= 0 || this.i == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.p / width;
        int i3 = 0;
        e eVar = arrayList2.get(0);
        float f5 = eVar.e;
        int size = arrayList2.size();
        int i4 = eVar.b;
        int i5 = arrayList2.get(size - 1).b;
        while (i4 < i5) {
            while (true) {
                i2 = eVar.b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                eVar = arrayList2.get(i3);
            }
            if (i4 == i2) {
                float f6 = eVar.e;
                float f7 = eVar.d;
                f2 = (f6 + f7) * width;
                f5 = f6 + f7 + f4;
            } else {
                this.i.getClass();
                f2 = (f5 + 1.0f) * width;
                f5 = 1.0f + f4 + f5;
            }
            if (this.p + f2 > scrollX) {
                arrayList = arrayList2;
                f3 = f4;
                this.q.setBounds(Math.round(f2), this.r, Math.round(this.p + f2), this.s);
                this.q.draw(canvas);
            } else {
                arrayList = arrayList2;
                f3 = f4;
            }
            if (f2 > scrollX + r3) {
                return;
            }
            i4++;
            arrayList2 = arrayList;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            t();
            return false;
        }
        if (action != 0) {
            if (this.z) {
                return true;
            }
            if (this.A) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.H = x;
            this.F = x;
            float y = motionEvent.getY();
            this.I = y;
            this.G = y;
            this.f24J = motionEvent.getPointerId(0);
            this.A = false;
            this.n = true;
            this.m.computeScrollOffset();
            if (this.c0 == 2 && Math.abs(this.m.getFinalX() - this.m.getCurrX()) > this.O) {
                this.m.abortAnimation();
                this.x = false;
                q();
                this.z = true;
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                setScrollState(1);
            } else if (EdgeEffectCompat.a(this.P) == 0.0f && EdgeEffectCompat.a(this.Q) == 0.0f) {
                d(false);
                this.z = false;
            } else {
                this.z = true;
                setScrollState(1);
                if (EdgeEffectCompat.a(this.P) != 0.0f) {
                    EdgeEffectCompat.b(this.P, 0.0f, 1.0f - (this.G / getHeight()));
                }
                if (EdgeEffectCompat.a(this.Q) != 0.0f) {
                    EdgeEffectCompat.b(this.Q, 0.0f, this.G / getHeight());
                }
            }
        } else if (action == 2) {
            int i2 = this.f24J;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(iFindPointerIndex);
                float f2 = x2 - this.F;
                float fAbs = Math.abs(f2);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y2 - this.I);
                if (f2 != 0.0f) {
                    float f3 = this.F;
                    if (!(!this.E && ((f3 < ((float) this.C) && f2 > 0.0f) || (f3 > ((float) (getWidth() - this.C)) && f2 < 0.0f))) && c((int) f2, (int) x2, (int) y2, this, false)) {
                        this.F = x2;
                        this.G = y2;
                        this.A = true;
                        return false;
                    }
                }
                float f4 = this.D;
                if (fAbs > f4 && fAbs * 0.5f > fAbs2) {
                    this.z = true;
                    ViewParent parent2 = getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    setScrollState(1);
                    this.F = f2 > 0.0f ? this.H + this.D : this.H - this.D;
                    this.G = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > f4) {
                    this.A = true;
                }
                if (this.z && p(x2, y2)) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    om3.d.j(this);
                }
            }
        } else if (action == 6) {
            m(motionEvent);
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        return this.z;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        int i4;
        setMeasuredDimension(View.getDefaultSize(0, i2), View.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.C = Math.min(measuredWidth / 10, this.B);
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
            if (childAt.getVisibility() != 8 && (layoutParams2 = (LayoutParams) childAt.getLayoutParams()) != null && layoutParams2.a) {
                int i7 = layoutParams2.b;
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
                int i11 = ((ViewGroup.LayoutParams) layoutParams2).width;
                if (i11 != -2) {
                    if (i11 == -1) {
                        i11 = paddingLeft;
                    }
                    i10 = 1073741824;
                } else {
                    i11 = paddingLeft;
                }
                int i12 = ((ViewGroup.LayoutParams) layoutParams2).height;
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
        View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        this.v = true;
        q();
        this.v = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || !layoutParams.a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * layoutParams.c), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        e eVarH;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (eVarH = h(childAt)) != null && eVarH.b == this.j && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i5;
        }
        return false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        if (this.i != null) {
            v(savedState.g, 0, false, true);
        } else {
            this.k = savedState.g;
            this.l = savedState.h;
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.g = this.j;
        kv1 kv1Var = this.i;
        if (kv1Var != null) {
            kv1Var.getClass();
            savedState.h = null;
        }
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.p;
            s(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00dd  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean p(float f2, float f3) {
        boolean z;
        float f4 = this.F - f2;
        this.F = f2;
        float height = f3 / getHeight();
        float width = f4 / getWidth();
        float fB = (EdgeEffectCompat.a(this.P) != 0.0f ? -EdgeEffectCompat.b(this.P, -width, 1.0f - height) : EdgeEffectCompat.a(this.Q) != 0.0f ? EdgeEffectCompat.b(this.Q, width, height) : 0.0f) * getWidth();
        float f5 = f4 - fB;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = fB != 0.0f;
        if (Math.abs(f5) < 1.0E-4f) {
            return z4;
        }
        float scrollX = getScrollX() + f5;
        float clientWidth = getClientWidth();
        float f6 = this.t * clientWidth;
        float f7 = this.u * clientWidth;
        ArrayList<e> arrayList = this.f;
        e eVar = arrayList.get(0);
        e eVar2 = arrayList.get(arrayList.size() - 1);
        if (eVar.b != 0) {
            f6 = eVar.e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (eVar2.b != this.i.b() - 1) {
            f7 = eVar2.e * clientWidth;
        } else {
            z3 = true;
        }
        if (scrollX < f6) {
            if (z) {
                EdgeEffectCompat.b(this.P, (f6 - scrollX) / clientWidth, 1.0f - (f3 / getHeight()));
            } else {
                z2 = z4;
            }
            z4 = z2;
            scrollX = f6;
        } else if (scrollX > f7) {
            if (z3) {
                EdgeEffectCompat.b(this.Q, (scrollX - f7) / clientWidth, f3 / getHeight());
            } else {
                z2 = z4;
            }
            z4 = z2;
            scrollX = f7;
        }
        int i2 = (int) scrollX;
        this.F = (scrollX - i2) + this.F;
        scrollTo(i2, getScrollY());
        o(i2);
        return z4;
    }

    public final void q() {
        r(this.j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0059, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00df A[PHI: r2 r6 r12
  0x00df: PHI (r2v21 int) = (r2v20 int), (r2v9 int), (r2v23 int) binds: [B:58:0x00d6, B:55:0x00c2, B:49:0x00ae] A[DONT_GENERATE, DONT_INLINE]
  0x00df: PHI (r6v6 int) = (r6v1 int), (r6v5 int), (r6v8 int) binds: [B:58:0x00d6, B:55:0x00c2, B:49:0x00ae] A[DONT_GENERATE, DONT_INLINE]
  0x00df: PHI (r12v5 float) = (r12v3 float), (r12v4 float), (r12v2 float) binds: [B:58:0x00d6, B:55:0x00c2, B:49:0x00ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0163 A[PHI: r1 r10
  0x0163: PHI (r1v17 float) = (r1v15 float), (r1v16 float), (r1v14 float) binds: [B:92:0x015a, B:89:0x0144, B:83:0x012c] A[DONT_GENERATE, DONT_INLINE]
  0x0163: PHI (r10v29 int) = (r10v27 int), (r10v28 int), (r10v26 int) binds: [B:92:0x015a, B:89:0x0144, B:83:0x012c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(int r15) {
        /*
            Method dump skipped, instruction units count: 862
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.r(int):void");
    }

    public void removeOnAdapterChangeListener(g gVar) {
        ArrayList arrayList = this.a0;
        if (arrayList != null) {
            arrayList.remove(gVar);
        }
    }

    public void removeOnPageChangeListener(h hVar) {
        ArrayList arrayList = this.U;
        if (arrayList != null) {
            arrayList.remove(hVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.v) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public final void s(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.f.isEmpty()) {
            if (!this.m.isFinished()) {
                this.m.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        e eVarJ = j(this.j);
        int iMin = (int) ((eVarJ != null ? Math.min(eVarJ.e, this.u) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            d(false);
            scrollTo(iMin, getScrollY());
        }
    }

    public void setAdapter(kv1 kv1Var) {
        ArrayList<e> arrayList;
        kv1 kv1Var2 = this.i;
        if (kv1Var2 != null) {
            synchronized (kv1Var2) {
            }
            this.i.getClass();
            int i2 = 0;
            while (true) {
                arrayList = this.f;
                if (i2 >= arrayList.size()) {
                    break;
                }
                e eVar = arrayList.get(i2);
                this.i.a(this, eVar.b, eVar.a);
                i2++;
            }
            this.i.getClass();
            arrayList.clear();
            int i3 = 0;
            while (i3 < getChildCount()) {
                if (!((LayoutParams) getChildAt(i3).getLayoutParams()).a) {
                    removeViewAt(i3);
                    i3--;
                }
                i3++;
            }
            this.j = 0;
            scrollTo(0, 0);
        }
        kv1 kv1Var3 = this.i;
        this.i = kv1Var;
        this.e = 0;
        if (kv1Var != null) {
            if (this.o == null) {
                this.o = new i();
            }
            synchronized (this.i) {
            }
            this.x = false;
            boolean z = this.R;
            this.R = true;
            this.e = this.i.b();
            if (this.k >= 0) {
                this.i.getClass();
                v(this.k, 0, false, true);
                this.k = -1;
                this.l = null;
            } else if (z) {
                requestLayout();
            } else {
                q();
            }
        }
        ArrayList arrayList2 = this.a0;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            return;
        }
        int size = this.a0.size();
        for (int i4 = 0; i4 < size; i4++) {
            ((g) this.a0.get(i4)).a(this, kv1Var3, kv1Var);
        }
    }

    public void setCurrentItem(int i2) {
        this.x = false;
        v(i2, 0, !this.R, false);
    }

    public void setDragInGutterEnabled(boolean z) {
        this.E = z;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.y) {
            this.y = i2;
            q();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(h hVar) {
        this.V = hVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.p;
        this.p = i2;
        int width = getWidth();
        s(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.q = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i2) {
        if (this.c0 == i2) {
            return;
        }
        this.c0 = i2;
        h hVar = this.V;
        if (hVar != null) {
            hVar.b(i2);
        }
        ArrayList arrayList = this.U;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                h hVar2 = (h) this.U.get(i3);
                if (hVar2 != null) {
                    hVar2.b(i2);
                }
            }
        }
        h hVar3 = this.W;
        if (hVar3 != null) {
            hVar3.b(i2);
        }
    }

    public final boolean t() {
        this.f24J = -1;
        this.z = false;
        this.A = false;
        VelocityTracker velocityTracker = this.K;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.K = null;
        }
        this.P.onRelease();
        this.Q.onRelease();
        return (this.P.isFinished() && this.Q.isFinished()) ? false : true;
    }

    public final void u(int i2, int i3, boolean z, boolean z2) {
        int scrollX;
        int iAbs;
        e eVarJ = j(i2);
        int iMax = eVarJ != null ? (int) (Math.max(this.t, Math.min(eVarJ.e, this.u)) * getClientWidth()) : 0;
        if (!z) {
            if (z2) {
                f(i2);
            }
            d(false);
            scrollTo(iMax, 0);
            o(iMax);
            return;
        }
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
        } else {
            Scroller scroller = this.m;
            if ((scroller == null || scroller.isFinished()) ? false : true) {
                scrollX = this.n ? this.m.getCurrX() : this.m.getStartX();
                this.m.abortAnimation();
                setScrollingCacheEnabled(false);
            } else {
                scrollX = getScrollX();
            }
            int i4 = scrollX;
            int scrollY = getScrollY();
            int i5 = iMax - i4;
            int i6 = 0 - scrollY;
            if (i5 == 0 && i6 == 0) {
                d(false);
                q();
                setScrollState(0);
            } else {
                setScrollingCacheEnabled(true);
                setScrollState(2);
                int clientWidth = getClientWidth();
                int i7 = clientWidth / 2;
                float f2 = clientWidth;
                float f3 = i7;
                float fSin = (((float) Math.sin((Math.min(1.0f, (Math.abs(i5) * 1.0f) / f2) - 0.5f) * 0.47123894f)) * f3) + f3;
                int iAbs2 = Math.abs(i3);
                if (iAbs2 > 0) {
                    iAbs = Math.round(Math.abs(fSin / iAbs2) * 1000.0f) * 4;
                } else {
                    this.i.getClass();
                    iAbs = (int) (((Math.abs(i5) / ((f2 * 1.0f) + this.p)) + 1.0f) * 100.0f);
                }
                int iMin = Math.min(iAbs, 600);
                this.n = false;
                this.m.startScroll(i4, scrollY, i5, i6, iMin);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.j(this);
            }
        }
        if (z2) {
            f(i2);
        }
    }

    public final void v(int i2, int i3, boolean z, boolean z2) {
        kv1 kv1Var = this.i;
        if (kv1Var == null || kv1Var.b() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        ArrayList<e> arrayList = this.f;
        if (!z2 && this.j == i2 && arrayList.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.i.b()) {
            i2 = this.i.b() - 1;
        }
        int i4 = this.y;
        int i5 = this.j;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                arrayList.get(i6).c = true;
            }
        }
        boolean z3 = this.j != i2;
        if (!this.R) {
            r(i2);
            u(i2, i3, z, z3);
        } else {
            this.j = i2;
            if (z3) {
                f(i2);
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.q;
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean a;
        public final int b;
        public float c;
        public boolean d;

        public LayoutParams() {
            super(-1, -1);
            this.c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.d0);
            this.b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i2) {
        Context context = getContext();
        Object obj = bz.a;
        setPageMarginDrawable(context.getDrawable(i2));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new ArrayList<>();
        this.g = new e();
        this.h = new Rect();
        this.k = -1;
        this.l = null;
        this.t = -3.4028235E38f;
        this.u = Float.MAX_VALUE;
        this.y = 1;
        this.E = true;
        this.f24J = -1;
        this.R = true;
        this.b0 = new c();
        this.c0 = 0;
        k(context);
    }
}
