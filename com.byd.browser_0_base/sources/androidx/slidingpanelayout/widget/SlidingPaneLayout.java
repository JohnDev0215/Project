package androidx.slidingpanelayout.widget;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.slidingpanelayout.widget.a;
import androidx.window.layout.WindowInfoTracker;
import defpackage.C0214v;
import defpackage.a8;
import defpackage.bz;
import defpackage.d00;
import defpackage.dh0;
import defpackage.dh3;
import defpackage.ea2;
import defpackage.fs2;
import defpackage.gq2;
import defpackage.ii0;
import defpackage.in3;
import defpackage.ir2;
import defpackage.m00;
import defpackage.m31;
import defpackage.n00;
import defpackage.n01;
import defpackage.o00;
import defpackage.oj2;
import defpackage.om3;
import defpackage.p31;
import defpackage.pt3;
import defpackage.qm3;
import defpackage.t00;
import defpackage.u70;
import defpackage.v83;
import defpackage.vi3;
import defpackage.wa0;
import defpackage.xa0;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {
    public int e;
    public int f;
    public Drawable g;
    public Drawable h;
    public boolean i;
    public View j;
    public float k;
    public float l;
    public int m;
    public boolean n;
    public int o;
    public float p;
    public float q;
    public final CopyOnWriteArrayList r;
    public d s;
    public final qm3 t;
    public boolean u;
    public boolean v;
    public final Rect w;
    public int x;
    public final a y;
    public androidx.slidingpanelayout.widget.a z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean g;
        public int h;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.h);
        }

        public SavedState(Parcel parcel) {
            super(parcel, null);
            this.g = parcel.readInt() != 0;
            this.h = parcel.readInt();
        }
    }

    public class a implements a.InterfaceC0028a {
    }

    public class b extends C0214v {
        public final Rect d = new Rect();

        public b() {
        }

        @Override // defpackage.C0214v
        public final void c(View view, AccessibilityEvent accessibilityEvent) {
            super.c(view, accessibilityEvent);
            accessibilityEvent.setClassName("androidx.slidingpanelayout.widget.SlidingPaneLayout");
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain);
            this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoObtain);
            Rect rect = this.d;
            accessibilityNodeInfoCompat2.f(rect);
            accessibilityNodeInfoCompat.m(rect);
            accessibilityNodeInfo.setVisibleToUser(accessibilityNodeInfoObtain.isVisibleToUser());
            accessibilityNodeInfo.setPackageName(accessibilityNodeInfoObtain.getPackageName());
            accessibilityNodeInfoCompat.n(accessibilityNodeInfoObtain.getClassName());
            accessibilityNodeInfoCompat.r(accessibilityNodeInfoCompat2.g());
            accessibilityNodeInfo.setEnabled(accessibilityNodeInfoObtain.isEnabled());
            accessibilityNodeInfoCompat.o(accessibilityNodeInfoObtain.isClickable());
            accessibilityNodeInfo.setFocusable(accessibilityNodeInfoObtain.isFocusable());
            accessibilityNodeInfo.setFocused(accessibilityNodeInfoObtain.isFocused());
            accessibilityNodeInfo.setAccessibilityFocused(accessibilityNodeInfoObtain.isAccessibilityFocused());
            accessibilityNodeInfo.setSelected(accessibilityNodeInfoObtain.isSelected());
            accessibilityNodeInfo.setLongClickable(accessibilityNodeInfoObtain.isLongClickable());
            accessibilityNodeInfoCompat.a(accessibilityNodeInfoObtain.getActions());
            accessibilityNodeInfo.setMovementGranularities(accessibilityNodeInfoObtain.getMovementGranularities());
            accessibilityNodeInfoCompat.n("androidx.slidingpanelayout.widget.SlidingPaneLayout");
            accessibilityNodeInfoCompat.c = -1;
            accessibilityNodeInfo.setSource(view);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            Object objE = om3.d.e(view);
            if (objE instanceof View) {
                accessibilityNodeInfoCompat.b = -1;
                accessibilityNodeInfo.setParent((View) objE);
            }
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            int childCount = slidingPaneLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = slidingPaneLayout.getChildAt(i);
                if (!slidingPaneLayout.a(childAt) && childAt.getVisibility() == 0) {
                    om3.d.p(childAt, 1);
                    accessibilityNodeInfo.addChild(childAt);
                }
            }
        }

        @Override // defpackage.C0214v
        public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (SlidingPaneLayout.this.a(view)) {
                return false;
            }
            return super.f(viewGroup, view, accessibilityEvent);
        }
    }

    public class c extends qm3.c {
        public c() {
        }

        @Override // qm3.c
        public final int a(View view, int i) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            LayoutParams layoutParams = (LayoutParams) slidingPaneLayout.j.getLayoutParams();
            if (!slidingPaneLayout.b()) {
                int paddingLeft = slidingPaneLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                return Math.min(Math.max(i, paddingLeft), slidingPaneLayout.m + paddingLeft);
            }
            int width = slidingPaneLayout.getWidth() - (slidingPaneLayout.j.getWidth() + (slidingPaneLayout.getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin));
            return Math.max(Math.min(i, width), width - slidingPaneLayout.m);
        }

        @Override // qm3.c
        public final int b(View view, int i) {
            return view.getTop();
        }

        @Override // qm3.c
        public final int c(View view) {
            return SlidingPaneLayout.this.m;
        }

        @Override // qm3.c
        public final void e(int i, int i2) {
            if (l()) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                slidingPaneLayout.t.c(slidingPaneLayout.j, i2);
            }
        }

        @Override // qm3.c
        public final void f(int i) {
            if (l()) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                slidingPaneLayout.t.c(slidingPaneLayout.j, i);
            }
        }

        @Override // qm3.c
        public final void g(View view, int i) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            int childCount = slidingPaneLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = slidingPaneLayout.getChildAt(i2);
                if (childAt.getVisibility() == 4) {
                    childAt.setVisibility(0);
                }
            }
        }

        @Override // qm3.c
        public final void h(int i) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            if (slidingPaneLayout.t.a == 0) {
                float f = slidingPaneLayout.k;
                CopyOnWriteArrayList copyOnWriteArrayList = slidingPaneLayout.r;
                if (f != 1.0f) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        ((d) it.next()).b();
                    }
                    slidingPaneLayout.sendAccessibilityEvent(32);
                    slidingPaneLayout.u = true;
                    return;
                }
                slidingPaneLayout.f(slidingPaneLayout.j);
                Iterator it2 = copyOnWriteArrayList.iterator();
                while (it2.hasNext()) {
                    ((d) it2.next()).a();
                }
                slidingPaneLayout.sendAccessibilityEvent(32);
                slidingPaneLayout.u = false;
            }
        }

        @Override // qm3.c
        public final void i(View view, int i, int i2) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            if (slidingPaneLayout.j == null) {
                slidingPaneLayout.k = 0.0f;
            } else {
                boolean zB = slidingPaneLayout.b();
                LayoutParams layoutParams = (LayoutParams) slidingPaneLayout.j.getLayoutParams();
                int width = slidingPaneLayout.j.getWidth();
                if (zB) {
                    i = (slidingPaneLayout.getWidth() - i) - width;
                }
                float paddingRight = (i - ((zB ? slidingPaneLayout.getPaddingRight() : slidingPaneLayout.getPaddingLeft()) + (zB ? ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin))) / slidingPaneLayout.m;
                slidingPaneLayout.k = paddingRight;
                if (slidingPaneLayout.o != 0) {
                    slidingPaneLayout.d(paddingRight);
                }
                Iterator it = slidingPaneLayout.r.iterator();
                while (it.hasNext()) {
                    ((d) it.next()).c();
                }
            }
            slidingPaneLayout.invalidate();
        }

        @Override // qm3.c
        public final void j(View view, float f, float f2) {
            int paddingLeft;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            if (slidingPaneLayout.b()) {
                int paddingRight = slidingPaneLayout.getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                if (f < 0.0f || (f == 0.0f && slidingPaneLayout.k > 0.5f)) {
                    paddingRight += slidingPaneLayout.m;
                }
                paddingLeft = (slidingPaneLayout.getWidth() - paddingRight) - slidingPaneLayout.j.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + slidingPaneLayout.getPaddingLeft();
                if (f > 0.0f || (f == 0.0f && slidingPaneLayout.k > 0.5f)) {
                    paddingLeft += slidingPaneLayout.m;
                }
            }
            slidingPaneLayout.t.t(paddingLeft, view.getTop());
            slidingPaneLayout.invalidate();
        }

        @Override // qm3.c
        public final boolean k(View view, int i) {
            if (l()) {
                return ((LayoutParams) view.getLayoutParams()).b;
            }
            return false;
        }

        public final boolean l() {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            if (slidingPaneLayout.n || slidingPaneLayout.getLockMode() == 3) {
                return false;
            }
            if (slidingPaneLayout.c() && slidingPaneLayout.getLockMode() == 1) {
                return false;
            }
            return slidingPaneLayout.c() || slidingPaneLayout.getLockMode() != 2;
        }
    }

    public interface d {
        void a();

        void b();

        void c();
    }

    public static class e extends FrameLayout {
        public e(View view) {
            super(view.getContext());
            addView(view);
        }

        @Override // android.view.View
        public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.View
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return true;
        }
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    private n01 getSystemGestureInsets() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        pt3 pt3VarA = om3.j.a(this);
        if (pt3VarA != null) {
            return pt3VarA.a.i();
        }
        return null;
    }

    private void setFoldingFeatureObserver(androidx.slidingpanelayout.widget.a aVar) {
        this.z = aVar;
        aVar.setOnFoldingFeatureChangeListener(this.y);
    }

    public final boolean a(View view) {
        if (view == null) {
            return false;
        }
        return this.i && ((LayoutParams) view.getLayoutParams()).c && this.k > 0.0f;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() == 1) {
            super.addView(new e(view), i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    public final boolean b() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(this) == 1;
    }

    public final boolean c() {
        return !this.i || this.k == 0.0f;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        qm3 qm3Var = this.t;
        if (qm3Var.h()) {
            if (!this.i) {
                qm3Var.a();
            } else {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.j(this);
            }
        }
    }

    public final void d(float f) {
        boolean zB = b();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != this.j) {
                float f2 = 1.0f - this.l;
                int i2 = this.o;
                this.l = f;
                int i3 = ((int) (f2 * i2)) - ((int) ((1.0f - f) * i2));
                if (zB) {
                    i3 = -i3;
                }
                childAt.offsetLeftAndRight(i3);
            }
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i;
        int right;
        super.draw(canvas);
        Drawable drawable = b() ? this.h : this.g;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (b()) {
            right = childAt.getRight();
            i = intrinsicWidth + right;
        } else {
            int left = childAt.getLeft();
            int i2 = left - intrinsicWidth;
            i = left;
            right = i2;
        }
        drawable.setBounds(right, top, i, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean zB = b() ^ c();
        qm3 qm3Var = this.t;
        if (zB) {
            qm3Var.q = 1;
            n01 systemGestureInsets = getSystemGestureInsets();
            if (systemGestureInsets != null) {
                qm3Var.o = Math.max(qm3Var.p, systemGestureInsets.a);
            }
        } else {
            qm3Var.q = 2;
            n01 systemGestureInsets2 = getSystemGestureInsets();
            if (systemGestureInsets2 != null) {
                qm3Var.o = Math.max(qm3Var.p, systemGestureInsets2.c);
            }
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int iSave = canvas.save();
        if (this.i && !layoutParams.b && this.j != null) {
            Rect rect = this.w;
            canvas.getClipBounds(rect);
            if (b()) {
                rect.left = Math.max(rect.left, this.j.getRight());
            } else {
                rect.right = Math.min(rect.right, this.j.getLeft());
            }
            canvas.clipRect(rect);
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    public final boolean e(float f) {
        int paddingLeft;
        if (!this.i) {
            return false;
        }
        boolean zB = b();
        LayoutParams layoutParams = (LayoutParams) this.j.getLayoutParams();
        if (zB) {
            int paddingRight = getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            paddingLeft = (int) (getWidth() - (((f * this.m) + paddingRight) + this.j.getWidth()));
        } else {
            paddingLeft = (int) ((f * this.m) + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
        }
        View view = this.j;
        if (!this.t.v(view, paddingLeft, view.getTop())) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.j(this);
        return true;
    }

    public final void f(View view) {
        int left;
        int right;
        int top;
        int bottom;
        boolean z;
        View view2 = view;
        boolean zB = b();
        int width = zB ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = zB ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !view.isOpaque()) {
            left = 0;
            right = 0;
            top = 0;
            bottom = 0;
        } else {
            left = view.getLeft();
            right = view.getRight();
            top = view.getTop();
            bottom = view.getBottom();
        }
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt == view2) {
                return;
            }
            if (childAt.getVisibility() == 8) {
                z = zB;
            } else {
                z = zB;
                childAt.setVisibility((Math.max(zB ? paddingLeft : width, childAt.getLeft()) < left || Math.max(paddingTop, childAt.getTop()) < top || Math.min(zB ? width : paddingLeft, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4);
            }
            i++;
            view2 = view;
            zB = z;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Deprecated
    public int getCoveredFadeColor() {
        return this.f;
    }

    public final int getLockMode() {
        return this.x;
    }

    public int getParallaxDistance() {
        return this.o;
    }

    @Deprecated
    public int getSliderFadeColor() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, dh0, java.lang.Object, m00] */
    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() throws IllegalAccessException, InvocationTargetException {
        Activity activity;
        m00 m00VarPlus;
        super.onAttachedToWindow();
        this.v = true;
        if (this.z != null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                } else {
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            if (activity != null) {
                androidx.slidingpanelayout.widget.a aVar = this.z;
                aVar.getClass();
                ir2 ir2Var = aVar.c;
                if (ir2Var != null) {
                    ir2Var.Q0(null);
                }
                Executor executor = aVar.b;
                if (executor instanceof wa0) {
                }
                ii0 ii0Var = new ii0(executor);
                m00.b bVar = ii0Var.get(m31.a.e);
                m00 m00VarPlus2 = ii0Var;
                if (bVar == null) {
                    m00VarPlus2 = ii0Var.plus(new p31(null));
                }
                androidx.slidingpanelayout.widget.b bVar2 = new androidx.slidingpanelayout.widget.b(aVar, activity, null);
                ?? r1 = dh0.e;
                if (((Boolean) m00VarPlus2.fold(Boolean.FALSE, o00.f)).booleanValue()) {
                    ea2 ea2Var = new ea2();
                    ea2Var.e = r1;
                    m00VarPlus = ((m00) m00VarPlus2.fold(r1, new n00(ea2Var))).plus((m00) ea2Var.e);
                } else {
                    m00VarPlus = m00VarPlus2.plus(r1);
                }
                u70 u70Var = xa0.a;
                if (m00VarPlus != u70Var && m00VarPlus.get(d00.a.e) == null) {
                    m00VarPlus = m00VarPlus.plus(u70Var);
                }
                ir2 ir2Var2 = new ir2(m00VarPlus, true);
                int iM = gq2.m(1);
                if (iM == 0) {
                    try {
                        a8.e0(oj2.v(oj2.g(ir2Var2, ir2Var2, bVar2)), vi3.a, null);
                    } finally {
                        ir2Var2.c(a8.v(th));
                    }
                } else if (iM != 1) {
                    if (iM == 2) {
                        oj2.v(oj2.g(ir2Var2, ir2Var2, bVar2)).c(vi3.a);
                    } else {
                        if (iM != 3) {
                            throw new fs2();
                        }
                        try {
                            m00 m00Var = ir2Var2.f;
                            Object objC = v83.c(m00Var, null);
                            try {
                                dh3.a(bVar2);
                                Object objD = bVar2.d(ir2Var2, ir2Var2);
                                if (objD != t00.COROUTINE_SUSPENDED) {
                                    ir2Var2.c(objD);
                                }
                            } finally {
                                v83.a(m00Var, objC);
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
                aVar.c = ir2Var2;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() throws IllegalAccessException, InvocationTargetException {
        ir2 ir2Var;
        super.onDetachedFromWindow();
        this.v = true;
        androidx.slidingpanelayout.widget.a aVar = this.z;
        if (aVar == null || (ir2Var = aVar.c) == null) {
            return;
        }
        ir2Var.Q0(null);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = this.i;
        qm3 qm3Var = this.t;
        if (!z2 && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            qm3Var.getClass();
            this.u = qm3.l(childAt, x, y);
        }
        if (!this.i || (this.n && actionMasked != 0)) {
            qm3Var.b();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1) {
            qm3Var.b();
            return false;
        }
        if (actionMasked == 0) {
            this.n = false;
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            this.p = x2;
            this.q = y2;
            qm3Var.getClass();
            if (qm3.l(this.j, (int) x2, (int) y2) && a(this.j)) {
                z = true;
            }
            return qm3Var.u(motionEvent) || z;
        }
        if (actionMasked == 2) {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            float fAbs = Math.abs(x3 - this.p);
            float fAbs2 = Math.abs(y3 - this.q);
            if (fAbs > qm3Var.b && fAbs2 > fAbs) {
                qm3Var.b();
                this.n = true;
                return false;
            }
        }
        z = false;
        if (qm3Var.u(motionEvent)) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean zB = b();
        int i10 = i3 - i;
        int paddingRight = zB ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = zB ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.v) {
            this.k = (this.i && this.u) ? 0.0f : 1.0f;
        }
        int i11 = paddingRight;
        int i12 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() == 8) {
                i5 = i11;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.b) {
                    int i13 = i10 - paddingLeft;
                    int iMin = (Math.min(paddingRight, i13) - i11) - (((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                    this.m = iMin;
                    int i14 = zB ? ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    layoutParams.c = (measuredWidth / 2) + ((i11 + i14) + iMin) > i13;
                    int i15 = (int) (iMin * this.k);
                    this.k = i15 / iMin;
                    i5 = i14 + i15 + i11;
                    i6 = 0;
                } else if (!this.i || (i7 = this.o) == 0) {
                    i5 = paddingRight;
                    i6 = 0;
                } else {
                    i6 = (int) ((1.0f - this.k) * i7);
                    i5 = paddingRight;
                }
                if (zB) {
                    i9 = (i10 - i5) + i6;
                    i8 = i9 - measuredWidth;
                } else {
                    i8 = i5 - i6;
                    i9 = i8 + measuredWidth;
                }
                childAt.layout(i8, paddingTop, i9, childAt.getMeasuredHeight() + paddingTop);
                paddingRight = Math.abs(0) + childAt.getWidth() + paddingRight;
            }
            i12++;
            i11 = i5;
        }
        if (this.v) {
            if (this.i && this.o != 0) {
                d(this.k);
            }
            f(this.j);
        }
        this.v = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0088 A[PHI: r15
  0x0088: PHI (r15v2 float) = (r15v1 float), (r15v3 float) binds: [B:19:0x007f, B:21:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01bd  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v17 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        if (savedState.g) {
            if (!this.i) {
                this.u = true;
            }
            if (this.v || e(0.0f)) {
                this.u = true;
            }
        } else {
            if (!this.i) {
                this.u = false;
            }
            if (this.v || e(1.0f)) {
                this.u = false;
            }
        }
        this.u = savedState.g;
        setLockMode(savedState.h);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.g = this.i ? c() : this.u;
        savedState.h = this.x;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.v = true;
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.i) {
            return super.onTouchEvent(motionEvent);
        }
        qm3 qm3Var = this.t;
        qm3Var.m(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.p = x;
            this.q = y;
        } else if (actionMasked == 1 && a(this.j)) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float f = x2 - this.p;
            float f2 = y2 - this.q;
            int i = qm3Var.b;
            if ((f2 * f2) + (f * f) < i * i && qm3.l(this.j, (int) x2, (int) y2)) {
                if (!this.i) {
                    this.u = false;
                }
                if (this.v || e(1.0f)) {
                    this.u = false;
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (view.getParent() instanceof e) {
            super.removeView((View) view.getParent());
        } else {
            super.removeView(view);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.i) {
            return;
        }
        this.u = view == this.j;
    }

    @Deprecated
    public void setCoveredFadeColor(int i) {
        this.f = i;
    }

    public final void setLockMode(int i) {
        this.x = i;
    }

    @Deprecated
    public void setPanelSlideListener(d dVar) {
        d dVar2 = this.s;
        CopyOnWriteArrayList copyOnWriteArrayList = this.r;
        if (dVar2 != null) {
            copyOnWriteArrayList.remove(dVar2);
        }
        if (dVar != null) {
            copyOnWriteArrayList.add(dVar);
        }
        this.s = dVar;
    }

    public void setParallaxDistance(int i) {
        this.o = i;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.g = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.h = drawable;
    }

    @Deprecated
    public void setShadowResource(int i) {
        setShadowDrawableLeft(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setShadowDrawableLeft(context.getDrawable(i));
    }

    public void setShadowResourceRight(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setShadowDrawableRight(context.getDrawable(i));
    }

    @Deprecated
    public void setSliderFadeColor(int i) {
        this.e = i;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int[] d = {R.attr.layout_weight};
        public final float a;
        public boolean b;
        public boolean c;

        public LayoutParams() {
            super(-1, -1);
            this.a = 0.0f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0.0f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d);
            this.a = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.k = 1.0f;
        this.r = new CopyOnWriteArrayList();
        this.v = true;
        this.w = new Rect();
        this.y = new a();
        float f = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        om3.k(this, new b());
        om3.m(this);
        qm3 qm3VarI = qm3.i(this, 0.5f, new c());
        this.t = qm3VarI;
        qm3VarI.s(f * 400.0f);
        setFoldingFeatureObserver(new androidx.slidingpanelayout.widget.a(WindowInfoTracker.getOrCreate(context), bz.d(context)));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
