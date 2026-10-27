package androidx.coordinatorlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.customview.view.AbsSavedState;
import defpackage.C0175b;
import defpackage.bz;
import defpackage.in3;
import defpackage.la0;
import defpackage.om3;
import defpackage.pt3;
import defpackage.sl1;
import defpackage.tl1;
import defpackage.u22;
import defpackage.ul1;
import defpackage.vm3;
import defpackage.wm2;
import defpackage.wr1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements sl1, tl1 {
    public static final u22 A;
    public static final String w;
    public static final Class<?>[] x;
    public static final ThreadLocal<Map<String, Constructor<Behavior>>> y;
    public static final g z;
    public final ArrayList e;
    public final la0 f;
    public final ArrayList g;
    public final int[] h;
    public final int[] i;
    public boolean j;
    public boolean k;
    public final int[] l;
    public View m;
    public View n;
    public f o;
    public boolean p;
    public pt3 q;
    public boolean r;
    public Drawable s;
    public ViewGroup.OnHierarchyChangeListener t;
    public a u;
    public final ul1 v;

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public boolean a(View view, Rect rect) {
            return false;
        }

        public boolean b(View view, View view2) {
            return false;
        }

        public boolean d(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean g(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean h(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        public boolean i(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            return false;
        }

        public boolean j(View view) {
            return false;
        }

        public void l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            iArr[0] = iArr[0] + i2;
            iArr[1] = iArr[1] + i3;
        }

        public boolean m(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public Parcelable o(View view) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        public boolean p(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            return false;
        }

        public boolean r(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }

        public void f() {
        }

        public void c(e eVar) {
        }

        public void e(CoordinatorLayout coordinatorLayout, View view) {
        }

        public void n(View view, Parcelable parcelable) {
        }

        public void q(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        }

        public void k(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        }
    }

    public class a implements wr1 {
        public a() {
        }

        @Override // defpackage.wr1
        public final pt3 k(View view, pt3 pt3Var) {
            CoordinatorLayout coordinatorLayout = CoordinatorLayout.this;
            if (!Objects.equals(coordinatorLayout.q, pt3Var)) {
                coordinatorLayout.q = pt3Var;
                boolean z = pt3Var.e() > 0;
                coordinatorLayout.r = z;
                coordinatorLayout.setWillNotDraw(!z && coordinatorLayout.getBackground() == null);
                pt3.i iVar = pt3Var.a;
                if (!iVar.m()) {
                    int childCount = coordinatorLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = coordinatorLayout.getChildAt(i);
                        WeakHashMap<View, in3> weakHashMap = om3.a;
                        if (om3.d.a(childAt) && ((e) childAt.getLayoutParams()).a != null && iVar.m()) {
                            break;
                        }
                    }
                }
                coordinatorLayout.requestLayout();
            }
            return pt3Var;
        }
    }

    public interface b {
        Behavior getBehavior();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    public @interface c {
        Class<? extends Behavior> value();
    }

    public class d implements ViewGroup.OnHierarchyChangeListener {
        public d() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.t;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout coordinatorLayout = CoordinatorLayout.this;
            coordinatorLayout.v(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = coordinatorLayout.t;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public class f implements ViewTreeObserver.OnPreDrawListener {
        public f() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            CoordinatorLayout.this.v(0);
            return true;
        }
    }

    public static class g implements Comparator<View> {
        @Override // java.util.Comparator
        public final int compare(View view, View view2) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            float fH = om3.i.h(view);
            float fH2 = om3.i.h(view2);
            if (fH > fH2) {
                return -1;
            }
            return fH < fH2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        w = r0 != null ? r0.getName() : null;
        z = new g();
        x = new Class[]{Context.class, AttributeSet.class};
        y = new ThreadLocal<>();
        A = new u22(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public static void C(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int i2 = eVar.i;
        if (i2 != i) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            view.offsetLeftAndRight(i - i2);
            eVar.i = i;
        }
    }

    public static void D(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int i2 = eVar.j;
        if (i2 != i) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            view.offsetTopAndBottom(i - i2);
            eVar.j = i;
        }
    }

    public static Rect f() {
        Rect rect = (Rect) A.a();
        return rect == null ? new Rect() : rect;
    }

    private int getFullContentHeight() {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            e eVar = (e) childAt.getLayoutParams();
            height += childAt.getHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
        }
        return height;
    }

    public static void r(int i, Rect rect, Rect rect2, e eVar, int i2, int i3) {
        int i4 = eVar.c;
        if (i4 == 0) {
            i4 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = eVar.d;
        if ((i5 & 7) == 0) {
            i5 |= 8388611;
        }
        if ((i5 & 112) == 0) {
            i5 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i5, i);
        int i6 = absoluteGravity & 7;
        int i7 = absoluteGravity & 112;
        int i8 = absoluteGravity2 & 7;
        int i9 = absoluteGravity2 & 112;
        int iWidth = i8 != 1 ? i8 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i9 != 16 ? i9 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i6 == 1) {
            iWidth -= i2 / 2;
        } else if (i6 != 5) {
            iWidth -= i2;
        }
        if (i7 == 16) {
            iHeight -= i3 / 2;
        } else if (i7 != 80) {
            iHeight -= i3;
        }
        rect2.set(iWidth, iHeight, i2 + iWidth, i3 + iHeight);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static e t(View view) {
        e eVar = (e) view.getLayoutParams();
        if (!eVar.b) {
            if (view instanceof b) {
                Behavior behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                eVar.b(behavior);
                eVar.b = true;
            } else {
                c cVar = null;
                for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    cVar = (c) superclass.getAnnotation(c.class);
                    if (cVar != null) {
                        break;
                    }
                }
                if (cVar != null) {
                    try {
                        eVar.b(cVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        Log.e("CoordinatorLayout", "Default behavior class " + cVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e2);
                    }
                }
                eVar.b = true;
            }
        }
        return eVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void A() {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.A():void");
    }

    public final void B() {
        View view = this.m;
        if (view != null) {
            Behavior behavior = ((e) view.getLayoutParams()).a;
            if (behavior != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                behavior.r(this, this.m, motionEventObtain);
                motionEventObtain.recycle();
            }
            this.m = null;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((e) getChildAt(i).getLayoutParams()).m = false;
        }
        this.j = false;
    }

    public final void E() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (!om3.d.a(this)) {
            om3.i.m(this, null);
            return;
        }
        if (this.u == null) {
            this.u = new a();
        }
        om3.i.m(this, this.u);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(@SuppressLint({"InvalidNullabilityOverride"}) KeyEvent keyEvent) {
        boolean zDispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        if (zDispatchKeyEvent || keyEvent.getAction() != 0) {
            return zDispatchKeyEvent;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 19 && keyCode != 20 && keyCode != 62) {
            return zDispatchKeyEvent;
        }
        int fullContentHeight = keyEvent.getKeyCode() == 62 ? keyEvent.isShiftPressed() ? -getFullContentHeight() : getFullContentHeight() - getHeight() : keyEvent.isAltPressed() ? getHeight() : (int) (getHeight() * 0.1f);
        View focusedChild = this;
        while (true) {
            if (focusedChild == null) {
                focusedChild = null;
                break;
            }
            if (focusedChild.isFocused()) {
                break;
            }
            focusedChild = focusedChild instanceof ViewGroup ? ((ViewGroup) focusedChild).getFocusedChild() : null;
        }
        if (keyEvent.getKeyCode() == 19) {
            fullContentHeight = -fullContentHeight;
        }
        l(this, focusedChild, 2, 1);
        j(focusedChild, 0, 0, 0, fullContentHeight, 1, this.h);
        n(focusedChild, 1);
        return this.h[1] > 0;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        Behavior behavior = ((e) view.getLayoutParams()).a;
        if (behavior != null) {
            behavior.getClass();
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.s;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public final void g(e eVar, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin));
        rect.set(iMax, iMax2, i + iMax, i2 + iMax2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public final List<View> getDependencySortedChildren() {
        A();
        return Collections.unmodifiableList(this.e);
    }

    public final pt3 getLastWindowInsets() {
        return this.q;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        ul1 ul1Var = this.v;
        return ul1Var.b | ul1Var.a;
    }

    public Drawable getStatusBarBackground() {
        return this.s;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public final void h(View view) {
        ArrayList arrayList = (ArrayList) ((wm2) this.f.g).get(view);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            View view2 = (View) arrayList.get(i);
            Behavior behavior = ((e) view2.getLayoutParams()).a;
            if (behavior != null) {
                behavior.d(this, view2, view);
            }
        }
    }

    public final void i(View view, Rect rect, boolean z2) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z2) {
            q(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    @Override // defpackage.tl1
    public final void j(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z2 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i5) && (behavior = eVar.a) != null) {
                    int[] iArr2 = this.h;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.l(this, childAt, i2, i3, i4, iArr2);
                    iMax = i3 > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i4 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z2 = true;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + iMax2;
        if (z2) {
            v(1);
        }
    }

    @Override // defpackage.sl1
    public final void k(View view, int i, int i2, int i3, int i4, int i5) {
        j(view, i, i2, i3, i4, 0, this.i);
    }

    @Override // defpackage.sl1
    public final boolean l(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                Behavior behavior = eVar.a;
                if (behavior != null) {
                    boolean zP = behavior.p(this, childAt, view, view2, i, i2);
                    z2 |= zP;
                    if (i2 == 0) {
                        eVar.n = zP;
                    } else if (i2 == 1) {
                        eVar.o = zP;
                    }
                } else if (i2 == 0) {
                    eVar.n = false;
                } else if (i2 == 1) {
                    eVar.o = false;
                }
            }
        }
        return z2;
    }

    @Override // defpackage.sl1
    public final void m(View view, View view2, int i, int i2) {
        ul1 ul1Var = this.v;
        if (i2 == 1) {
            ul1Var.b = i;
        } else {
            ul1Var.a = i;
        }
        this.n = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ((e) getChildAt(i3).getLayoutParams()).getClass();
        }
    }

    @Override // defpackage.sl1
    public final void n(View view, int i) {
        ul1 ul1Var = this.v;
        if (i == 1) {
            ul1Var.b = 0;
        } else {
            ul1Var.a = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.a(i)) {
                Behavior behavior = eVar.a;
                if (behavior != null) {
                    behavior.q(this, childAt, view, i);
                }
                if (i == 0) {
                    eVar.n = false;
                } else if (i == 1) {
                    eVar.o = false;
                }
                eVar.p = false;
            }
        }
        this.n = null;
    }

    @Override // defpackage.sl1
    public final void o(View view, int i, int i2, int[] iArr, int i3) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z2 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i3) && (behavior = eVar.a) != null) {
                    int[] iArr2 = this.h;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.k(this, childAt, view, i, i2, iArr2, i3);
                    int[] iArr3 = this.h;
                    iMax = i > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    iMax2 = i2 > 0 ? Math.max(iMax2, iArr3[1]) : Math.min(iMax2, iArr3[1]);
                    z2 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z2) {
            v(1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        B();
        if (this.p) {
            if (this.o == null) {
                this.o = new f();
            }
            getViewTreeObserver().addOnPreDrawListener(this.o);
        }
        if (this.q == null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.d.a(this)) {
                om3.h.c(this);
            }
        }
        this.k = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        B();
        if (this.p && this.o != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.o);
        }
        View view = this.n;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.k = false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.r || this.s == null) {
            return;
        }
        pt3 pt3Var = this.q;
        int iE = pt3Var != null ? pt3Var.e() : 0;
        if (iE > 0) {
            this.s.setBounds(0, 0, getWidth(), iE);
            this.s.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            B();
        }
        boolean z2 = z(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            this.m = null;
            B();
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        Behavior behavior;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC = om3.e.c(this);
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) arrayList.get(i5);
            if (view.getVisibility() != 8 && ((behavior = ((e) view.getLayoutParams()).a) == null || !behavior.h(this, view, iC))) {
                w(view, iC);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0196  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0)) {
                    Behavior behavior = eVar.a;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean zJ = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0) && (behavior = eVar.a) != null) {
                    zJ |= behavior.j(view);
                }
            }
        }
        return zJ;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        o(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        k(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        m(view, view2, i, 0);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        SparseArray<Parcelable> sparseArray = savedState.g;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = t(childAt).a;
            if (id != -1 && behavior != null && (parcelable2 = sparseArray.get(id)) != null) {
                behavior.n(childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableO;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = ((e) childAt.getLayoutParams()).a;
            if (id != -1 && behavior != null && (parcelableO = behavior.o(childAt)) != null) {
                sparseArray.append(id, parcelableO);
            }
        }
        savedState.g = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return l(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        n(view, 0);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        View view = this.m;
        boolean z3 = false;
        if (view != null) {
            Behavior behavior = ((e) view.getLayoutParams()).a;
            z2 = behavior != null ? behavior.r(this, this.m, motionEvent) : false;
        } else {
            z2 = z(motionEvent, 1);
            if (actionMasked != 0 && z2) {
                z3 = true;
            }
        }
        if (this.m == null || actionMasked == 3) {
            z2 |= super.onTouchEvent(motionEvent);
        } else if (z3) {
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setAction(3);
            super.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.m = null;
            B();
        }
        return z2;
    }

    public final List<View> p(View view) {
        wm2 wm2Var = (wm2) this.f.g;
        int i = wm2Var.g;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = (ArrayList) wm2Var.i(i2);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(wm2Var.f(i2));
            }
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    public final void q(View view, Rect rect) {
        ThreadLocal<Matrix> threadLocal = vm3.a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal<Matrix> threadLocal2 = vm3.a;
        Matrix matrix = threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        vm3.a(this, view, matrix);
        ThreadLocal<RectF> threadLocal3 = vm3.b;
        RectF rectF = threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        Behavior behavior = ((e) view.getLayoutParams()).a;
        if (behavior == null || !behavior.m(this, view, rect, z2)) {
            return super.requestChildRectangleOnScreen(view, rect, z2);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
        if (!z2 || this.j) {
            return;
        }
        if (this.m == null) {
            int childCount = getChildCount();
            MotionEvent motionEventObtain = null;
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                Behavior behavior = ((e) childAt.getLayoutParams()).a;
                if (behavior != null) {
                    if (motionEventObtain == null) {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    behavior.g(this, childAt, motionEventObtain);
                }
            }
            if (motionEventObtain != null) {
                motionEventObtain.recycle();
            }
        }
        B();
        this.j = true;
    }

    public final int s(int i) {
        int[] iArr = this.l;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        }
        if (i >= 0 && i < iArr.length) {
            return iArr[i];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
        return 0;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z2) {
        super.setFitsSystemWindows(z2);
        E();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.t = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.s;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.s = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.s.setState(getDrawableState());
                }
                Drawable drawable3 = this.s;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                drawable3.setLayoutDirection(om3.e.c(this));
                this.s.setVisible(getVisibility() == 0, false);
                this.s.setCallback(this);
            }
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.j(this);
        }
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i) {
        Drawable drawable;
        if (i != 0) {
            Context context = getContext();
            Object obj = bz.a;
            drawable = context.getDrawable(i);
        } else {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z2 = i == 0;
        Drawable drawable = this.s;
        if (drawable == null || drawable.isVisible() == z2) {
            return;
        }
        this.s.setVisible(z2, false);
    }

    public final boolean u(View view, int i, int i2) {
        u22 u22Var = A;
        Rect rectF = f();
        q(view, rectF);
        try {
            return rectF.contains(i, i2);
        } finally {
            rectF.setEmpty();
            u22Var.b(rectF);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(int r26) {
        /*
            Method dump skipped, instruction units count: 779
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.v(int):void");
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.s;
    }

    public final void w(View view, int i) {
        Rect rectF;
        Rect rectF2;
        e eVar = (e) view.getLayoutParams();
        View view2 = eVar.k;
        int i2 = 0;
        if (view2 == null && eVar.f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        u22 u22Var = A;
        if (view2 != null) {
            rectF = f();
            rectF2 = f();
            try {
                q(view2, rectF);
                e eVar2 = (e) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                r(i, rectF, rectF2, eVar2, measuredWidth, measuredHeight);
                g(eVar2, rectF2, measuredWidth, measuredHeight);
                view.layout(rectF2.left, rectF2.top, rectF2.right, rectF2.bottom);
                return;
            } finally {
                rectF.setEmpty();
                u22Var.b(rectF);
                rectF2.setEmpty();
                u22Var.b(rectF2);
            }
        }
        int i3 = eVar.e;
        if (i3 < 0) {
            e eVar3 = (e) view.getLayoutParams();
            rectF = f();
            rectF.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar3).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar3).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) eVar3).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) eVar3).bottomMargin);
            if (this.q != null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.d.a(this) && !om3.d.a(view)) {
                    rectF.left = this.q.c() + rectF.left;
                    rectF.top = this.q.e() + rectF.top;
                    rectF.right -= this.q.d();
                    rectF.bottom -= this.q.b();
                }
            }
            rectF2 = f();
            int i4 = eVar3.c;
            if ((i4 & 7) == 0) {
                i4 |= 8388611;
            }
            if ((i4 & 112) == 0) {
                i4 |= 48;
            }
            Gravity.apply(i4, view.getMeasuredWidth(), view.getMeasuredHeight(), rectF, rectF2, i);
            view.layout(rectF2.left, rectF2.top, rectF2.right, rectF2.bottom);
            return;
        }
        e eVar4 = (e) view.getLayoutParams();
        int i5 = eVar4.c;
        if (i5 == 0) {
            i5 = 8388661;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i5, i);
        int i6 = absoluteGravity & 7;
        int i7 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth2 = view.getMeasuredWidth();
        int measuredHeight2 = view.getMeasuredHeight();
        if (i == 1) {
            i3 = width - i3;
        }
        int iS = s(i3) - measuredWidth2;
        if (i6 == 1) {
            iS += measuredWidth2 / 2;
        } else if (i6 == 5) {
            iS += measuredWidth2;
        }
        if (i7 == 16) {
            i2 = 0 + (measuredHeight2 / 2);
        } else if (i7 == 80) {
            i2 = measuredHeight2 + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar4).leftMargin, Math.min(iS, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) eVar4).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar4).topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) eVar4).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth2 + iMax, measuredHeight2 + iMax2);
    }

    public final void x(View view, int i, int i2, int i3) {
        measureChildWithMargins(view, i, i2, i3, 0);
    }

    public final boolean y(Behavior behavior, View view, MotionEvent motionEvent, int i) {
        if (i == 0) {
            return behavior.g(this, view, motionEvent);
        }
        if (i == 1) {
            return behavior.r(this, view, motionEvent);
        }
        throw new IllegalArgumentException();
    }

    public final boolean z(MotionEvent motionEvent, int i) {
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = this.g;
        arrayList.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            arrayList.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        g gVar = z;
        if (gVar != null) {
            Collections.sort(arrayList, gVar);
        }
        int size = arrayList.size();
        MotionEvent motionEventObtain = null;
        boolean zY = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) arrayList.get(i3);
            e eVar = (e) view.getLayoutParams();
            Behavior behavior = eVar.a;
            if (!(zY || z3) || actionMasked == 0) {
                if (!z3 && !zY && behavior != null && (zY = y(behavior, view, motionEvent, i))) {
                    this.m = view;
                    if (actionMasked != 3 && actionMasked != 1) {
                        for (int i4 = 0; i4 < i3; i4++) {
                            View view2 = (View) arrayList.get(i4);
                            Behavior behavior2 = ((e) view2.getLayoutParams()).a;
                            if (behavior2 != null) {
                                if (motionEventObtain == null) {
                                    motionEventObtain = MotionEvent.obtain(motionEvent);
                                    motionEventObtain.setAction(3);
                                }
                                y(behavior2, view2, motionEventObtain, i);
                            }
                        }
                    }
                }
                if (eVar.a == null) {
                    eVar.m = false;
                }
                boolean z4 = eVar.m;
                if (z4) {
                    z2 = true;
                } else {
                    z2 = z4 | false;
                    eVar.m = z2;
                }
                z3 = z2 && !z4;
                if (z2 && !z3) {
                    break;
                }
            } else if (behavior != null) {
                if (motionEventObtain == null) {
                    motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.setAction(3);
                }
                y(behavior, view, motionEventObtain, i);
            }
        }
        arrayList.clear();
        if (motionEventObtain != null) {
            motionEventObtain.recycle();
        }
        return zY;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i);
        this.e = new ArrayList();
        this.f = new la0(0);
        this.g = new ArrayList();
        this.h = new int[2];
        this.i = new int[2];
        this.v = new ul1();
        if (i == 0) {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, i, 0);
        }
        if (i == 0) {
            int[] iArr = R$styleable.CoordinatorLayout;
            int i2 = R$style.Widget_Support_CoordinatorLayout;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, i2);
        } else {
            int[] iArr2 = R$styleable.CoordinatorLayout;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.n.b(this, context, iArr2, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            int[] intArray = resources.getIntArray(resourceId);
            this.l = intArray;
            float f2 = resources.getDisplayMetrics().density;
            int length = intArray.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.l[i3] = (int) (r12[i3] * f2);
            }
        }
        this.s = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        E();
        super.setOnHierarchyChangeListener(new d());
        WeakHashMap<View, in3> weakHashMap3 = om3.a;
        if (om3.d.b(this) == 0) {
            om3.d.p(this, 1);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public SparseArray<Parcelable> g;

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

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i = parcel.readInt();
            int[] iArr = new int[i];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.g = new SparseArray<>(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.g.append(iArr[i2], parcelableArray[i2]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            SparseArray<Parcelable> sparseArray = this.g;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.g.keyAt(i2);
                parcelableArr[i2] = this.g.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public Behavior a;
        public boolean b;
        public final int c;
        public int d;
        public final int e;
        public final int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public View k;
        public View l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public final Rect q;

        public e() {
            super(-2, -2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public final boolean a(int i) {
            if (i == 0) {
                return this.n;
            }
            if (i != 1) {
                return false;
            }
            return this.o;
        }

        public final void b(Behavior behavior) {
            Behavior behavior2 = this.a;
            if (behavior2 != behavior) {
                if (behavior2 != null) {
                    behavior2.f();
                }
                this.a = behavior;
                this.b = true;
                if (behavior != null) {
                    behavior.c(this);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public e(Context context, AttributeSet attributeSet) {
            Behavior behaviorNewInstance;
            super(context, attributeSet);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
            this.c = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.d = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.e = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i = R$styleable.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            this.b = zHasValue;
            if (zHasValue) {
                String string = typedArrayObtainStyledAttributes.getString(i);
                String str = CoordinatorLayout.w;
                if (TextUtils.isEmpty(string)) {
                    behaviorNewInstance = null;
                } else {
                    if (string.startsWith(".")) {
                        string = context.getPackageName() + string;
                    } else if (string.indexOf(46) < 0) {
                        String str2 = CoordinatorLayout.w;
                        if (!TextUtils.isEmpty(str2)) {
                            string = str2 + '.' + string;
                        }
                    }
                    try {
                        ThreadLocal<Map<String, Constructor<Behavior>>> threadLocal = CoordinatorLayout.y;
                        Map<String, Constructor<Behavior>> map = threadLocal.get();
                        if (map == null) {
                            map = new HashMap<>();
                            threadLocal.set(map);
                        }
                        Constructor<Behavior> constructor = map.get(string);
                        if (constructor == null) {
                            constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.x);
                            constructor.setAccessible(true);
                            map.put(string, constructor);
                        }
                        behaviorNewInstance = constructor.newInstance(context, attributeSet);
                    } catch (Exception e) {
                        throw new RuntimeException(C0175b.j("Could not inflate Behavior subclass ", string), e);
                    }
                }
                this.a = behaviorNewInstance;
            }
            typedArrayObtainStyledAttributes.recycle();
            Behavior behavior = this.a;
            if (behavior != null) {
                behavior.c(this);
            }
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }
    }
}
