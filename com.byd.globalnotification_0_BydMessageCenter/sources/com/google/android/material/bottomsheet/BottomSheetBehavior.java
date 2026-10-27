package com.google.android.material.bottomsheet;

import a.b.k.l;
import a.h.k.q;
import a.h.k.x.b;
import a.j.b.e;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.e0.g;
import b.d.a.a.i;
import b.d.a.a.j;
import b.d.a.a.k;
import b.d.a.a.z.m;
import b.d.a.a.z.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {
    public static final int Y = j.Widget_Design_BottomSheet_Modal;
    public float A;
    public int B;
    public float C;
    public boolean D;
    public boolean E;
    public boolean F;
    public int G;
    public a.j.b.e H;
    public boolean I;
    public int J;
    public boolean K;
    public int L;
    public int M;
    public int N;
    public WeakReference<V> O;
    public WeakReference<View> P;
    public final ArrayList<d> Q;
    public VelocityTracker R;
    public int S;
    public int T;
    public boolean U;
    public Map<View, Integer> V;
    public int W;
    public final e.c X;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2385c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2386d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2388f;
    public int g;
    public int h;
    public boolean i;
    public g j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public int s;
    public b.d.a.a.e0.j t;
    public boolean u;
    public BottomSheetBehavior<V>.f v;
    public ValueAnimator w;
    public int x;
    public int y;
    public int z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup.LayoutParams f2390b;

        public a(BottomSheetBehavior bottomSheetBehavior, View view, ViewGroup.LayoutParams layoutParams) {
            this.f2389a = view;
            this.f2390b = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2389a.setLayoutParams(this.f2390b);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2391a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2392b;

        public b(View view, int i) {
            this.f2391a = view;
            this.f2392b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.L(this.f2391a, this.f2392b);
        }
    }

    public class c extends e.c {
        public c() {
        }

        @Override // a.j.b.e.c
        public int a(View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // a.j.b.e.c
        public int b(View view, int i, int i2) {
            int iG = BottomSheetBehavior.this.G();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return l.i.k(i, iG, bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B);
        }

        @Override // a.j.b.e.c
        public int d(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B;
        }

        @Override // a.j.b.e.c
        public void f(int i) {
            if (i == 1) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.F) {
                    bottomSheetBehavior.K(1);
                }
            }
        }

        @Override // a.j.b.e.c
        public void g(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.E(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0084, code lost:
        
            if (java.lang.Math.abs(r8.getTop() - r7.f2394a.G()) < java.lang.Math.abs(r8.getTop() - r7.f2394a.z)) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0086, code lost:
        
            r9 = r7.f2394a.G();
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00be, code lost:
        
            if (java.lang.Math.abs(r9 - r7.f2394a.z) < java.lang.Math.abs(r9 - r7.f2394a.B)) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00f6, code lost:
        
            if (r9 < java.lang.Math.abs(r9 - r10.B)) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0108, code lost:
        
            if (java.lang.Math.abs(r9 - r0) < java.lang.Math.abs(r9 - r7.f2394a.B)) goto L42;
         */
        @Override // a.j.b.e.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void h(android.view.View r8, float r9, float r10) {
            /*
                Method dump skipped, instruction units count: 273
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.c.h(android.view.View, float, float):void");
        }

        @Override // a.j.b.e.c
        public boolean i(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.G;
            if (i2 == 1 || bottomSheetBehavior.U) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.S == i) {
                WeakReference<View> weakReference = bottomSheetBehavior.P;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.O;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    public static abstract class d {
        public abstract void a(View view, float f2);

        public abstract void b(View view, int i);
    }

    public static class e extends a.j.a.a {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2395c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2396d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2397e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2398f;
        public boolean g;

        public static class a implements Parcelable.ClassLoaderCreator<e> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new e(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new e[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public e createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new e(parcel, classLoader);
            }
        }

        public e(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2395c = parcel.readInt();
            this.f2396d = parcel.readInt();
            this.f2397e = parcel.readInt() == 1;
            this.f2398f = parcel.readInt() == 1;
            this.g = parcel.readInt() == 1;
        }

        public e(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f2395c = bottomSheetBehavior.G;
            this.f2396d = bottomSheetBehavior.f2387e;
            this.f2397e = bottomSheetBehavior.f2384b;
            this.f2398f = bottomSheetBehavior.D;
            this.g = bottomSheetBehavior.E;
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeInt(this.f2395c);
            parcel.writeInt(this.f2396d);
            parcel.writeInt(this.f2397e ? 1 : 0);
            parcel.writeInt(this.f2398f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f2399a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2400b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2401c;

        public f(View view, int i) {
            this.f2399a = view;
            this.f2401c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.j.b.e eVar = BottomSheetBehavior.this.H;
            if (eVar == null || !eVar.i(true)) {
                BottomSheetBehavior.this.K(this.f2401c);
            } else {
                q.N(this.f2399a, this);
            }
            this.f2400b = false;
        }
    }

    public BottomSheetBehavior() {
        this.f2383a = 0;
        this.f2384b = true;
        this.f2385c = false;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new c();
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int dimensionPixelOffset;
        int i;
        super(context, attributeSet);
        this.f2383a = 0;
        this.f2384b = true;
        this.f2385c = false;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new c();
        this.h = context.getResources().getDimensionPixelSize(b.d.a.a.d.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.BottomSheetBehavior_Layout);
        this.i = typedArrayObtainStyledAttributes.hasValue(k.BottomSheetBehavior_Layout_shapeAppearance);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(k.BottomSheetBehavior_Layout_backgroundTint);
        if (zHasValue) {
            D(context, attributeSet, zHasValue, l.i.y(context, typedArrayObtainStyledAttributes, k.BottomSheetBehavior_Layout_backgroundTint));
        } else {
            D(context, attributeSet, zHasValue, null);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.w = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.w.addUpdateListener(new b.d.a.a.p.a(this));
        this.C = typedArrayObtainStyledAttributes.getDimension(k.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (typedArrayObtainStyledAttributes.hasValue(k.BottomSheetBehavior_Layout_android_maxWidth)) {
            this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.BottomSheetBehavior_Layout_android_maxWidth, -1);
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(k.BottomSheetBehavior_Layout_behavior_peekHeight);
        I((typedValuePeekValue == null || (i = typedValuePeekValue.data) != -1) ? typedArrayObtainStyledAttributes.getDimensionPixelSize(k.BottomSheetBehavior_Layout_behavior_peekHeight, -1) : i);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_behavior_hideable, false);
        if (this.D != z) {
            this.D = z;
            if (!z && this.G == 5) {
                J(4);
            }
            P();
        }
        this.m = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (this.f2384b != z2) {
            this.f2384b = z2;
            if (this.O != null) {
                B();
            }
            K((this.f2384b && this.G == 6) ? 3 : this.G);
            P();
        }
        this.E = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        this.F = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_behavior_draggable, true);
        this.f2383a = typedArrayObtainStyledAttributes.getInt(k.BottomSheetBehavior_Layout_behavior_saveFlags, 0);
        float f2 = typedArrayObtainStyledAttributes.getFloat(k.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f);
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.A = f2;
        if (this.O != null) {
            this.z = (int) ((1.0f - f2) * this.N);
        }
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(k.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (typedValuePeekValue2 == null || typedValuePeekValue2.type != 16) {
            dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(k.BottomSheetBehavior_Layout_behavior_expandedOffset, 0);
            if (dimensionPixelOffset < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        } else {
            dimensionPixelOffset = typedValuePeekValue2.data;
            if (dimensionPixelOffset < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        }
        this.x = dimensionPixelOffset;
        this.n = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.o = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.p = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.q = typedArrayObtainStyledAttributes.getBoolean(k.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        typedArrayObtainStyledAttributes.recycle();
        this.f2386d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.G == 1 && actionMasked == 0) {
            return true;
        }
        a.j.b.e eVar = this.H;
        if (eVar != null) {
            eVar.n(motionEvent);
        }
        if (actionMasked == 0) {
            this.S = -1;
            VelocityTracker velocityTracker = this.R;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.R = null;
            }
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (this.H != null && actionMasked == 2 && !this.I) {
            float fAbs = Math.abs(this.T - motionEvent.getY());
            a.j.b.e eVar2 = this.H;
            if (fAbs > eVar2.f902b) {
                eVar2.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.I;
    }

    public final void B() {
        int iC = C();
        if (this.f2384b) {
            this.B = Math.max(this.N - iC, this.y);
        } else {
            this.B = this.N - iC;
        }
    }

    public final int C() {
        int iMin;
        int i;
        if (this.f2388f) {
            iMin = Math.min(Math.max(this.g, this.N - ((this.M * 9) / 16)), this.L);
        } else {
            if (!this.m && !this.n && (i = this.l) > 0) {
                return Math.max(this.f2387e, i + this.h);
            }
            iMin = this.f2387e;
        }
        return iMin + this.r;
    }

    public final void D(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.i) {
            this.t = b.d.a.a.e0.j.b(context, attributeSet, b.d.a.a.b.bottomSheetStyle, Y).a();
            g gVar = new g(this.t);
            this.j = gVar;
            gVar.f1828a.f1836b = new b.d.a.a.w.a(context);
            gVar.x();
            if (z && colorStateList != null) {
                this.j.q(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.j.setTint(typedValue.data);
        }
    }

    public void E(int i) {
        float f2;
        float fG;
        V v = this.O.get();
        if (v == null || this.Q.isEmpty()) {
            return;
        }
        int i2 = this.B;
        if (i > i2 || i2 == G()) {
            int i3 = this.B;
            f2 = i3 - i;
            fG = this.N - i3;
        } else {
            int i4 = this.B;
            f2 = i4 - i;
            fG = i4 - G();
        }
        float f3 = f2 / fG;
        for (int i5 = 0; i5 < this.Q.size(); i5++) {
            this.Q.get(i5).a(v, f3);
        }
    }

    public View F(View view) {
        if (q.D(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewF = F(viewGroup.getChildAt(i));
            if (viewF != null) {
                return viewF;
            }
        }
        return null;
    }

    public int G() {
        if (this.f2384b) {
            return this.y;
        }
        return Math.max(this.x, this.q ? 0 : this.s);
    }

    public final void H(V v, b.a aVar, int i) {
        q.R(v, aVar, null, new b.d.a.a.p.c(this, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void I(int r4) {
        /*
            r3 = this;
            r0 = 1
            r1 = -1
            r2 = 0
            if (r4 != r1) goto Lc
            boolean r4 = r3.f2388f
            if (r4 != 0) goto L15
            r3.f2388f = r0
            goto L1f
        Lc:
            boolean r1 = r3.f2388f
            if (r1 != 0) goto L17
            int r1 = r3.f2387e
            if (r1 == r4) goto L15
            goto L17
        L15:
            r0 = r2
            goto L1f
        L17:
            r3.f2388f = r2
            int r4 = java.lang.Math.max(r2, r4)
            r3.f2387e = r4
        L1f:
            if (r0 == 0) goto L24
            r3.S(r2)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.I(int):void");
    }

    public void J(int i) {
        if (i == this.G) {
            return;
        }
        if (this.O != null) {
            M(i);
            return;
        }
        if (i == 4 || i == 3 || i == 6 || (this.D && i == 5)) {
            this.G = i;
        }
    }

    public void K(int i) {
        V v;
        if (this.G == i) {
            return;
        }
        this.G = i;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i == 3) {
            R(true);
        } else if (i == 6 || i == 5 || i == 4) {
            R(false);
        }
        Q(i);
        for (int i2 = 0; i2 < this.Q.size(); i2++) {
            this.Q.get(i2).b(v, i);
        }
        P();
    }

    public void L(View view, int i) {
        int iG;
        int i2;
        if (i == 4) {
            iG = this.B;
        } else if (i == 6) {
            int i3 = this.z;
            if (!this.f2384b || i3 > (i2 = this.y)) {
                iG = i3;
            } else {
                i = 3;
                iG = i2;
            }
        } else if (i == 3) {
            iG = G();
        } else {
            if (!this.D || i != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i);
            }
            iG = this.N;
        }
        O(view, i, iG, false);
    }

    public final void M(int i) {
        V v = this.O.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && q.B(v)) {
            v.post(new b(v, i));
        } else {
            L(v, i);
        }
    }

    public boolean N(View view, float f2) {
        if (this.E) {
            return true;
        }
        if (view.getTop() < this.B) {
            return false;
        }
        return Math.abs(((f2 * 0.1f) + ((float) view.getTop())) - ((float) this.B)) / ((float) C()) > 0.5f;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void O(android.view.View r5, int r6, int r7, boolean r8) {
        /*
            r4 = this;
            a.j.b.e r0 = r4.H
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L30
            if (r8 == 0) goto L13
            int r8 = r5.getLeft()
            boolean r7 = r0.t(r8, r7)
            if (r7 == 0) goto L30
            goto L2f
        L13:
            int r8 = r5.getLeft()
            r0.r = r5
            r3 = -1
            r0.f903c = r3
            boolean r7 = r0.l(r8, r7, r2, r2)
            if (r7 != 0) goto L2d
            int r8 = r0.f901a
            if (r8 != 0) goto L2d
            android.view.View r8 = r0.r
            if (r8 == 0) goto L2d
            r8 = 0
            r0.r = r8
        L2d:
            if (r7 == 0) goto L30
        L2f:
            r2 = r1
        L30:
            if (r2 == 0) goto L54
            r7 = 2
            r4.K(r7)
            r4.Q(r6)
            com.google.android.material.bottomsheet.BottomSheetBehavior<V>$f r7 = r4.v
            if (r7 != 0) goto L44
            com.google.android.material.bottomsheet.BottomSheetBehavior$f r7 = new com.google.android.material.bottomsheet.BottomSheetBehavior$f
            r7.<init>(r5, r6)
            r4.v = r7
        L44:
            com.google.android.material.bottomsheet.BottomSheetBehavior<V>$f r7 = r4.v
            boolean r8 = r7.f2400b
            r7.f2401c = r6
            if (r8 != 0) goto L57
            a.h.k.q.N(r5, r7)
            com.google.android.material.bottomsheet.BottomSheetBehavior<V>$f r4 = r4.v
            r4.f2400b = r1
            goto L57
        L54:
            r4.K(r6)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.O(android.view.View, int, int, boolean):void");
    }

    public final void P() {
        V v;
        int i;
        b.a aVar;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        q.P(v, 524288);
        q.P(v, 262144);
        q.P(v, 1048576);
        int i2 = this.W;
        if (i2 != -1) {
            q.P(v, i2);
        }
        if (!this.f2384b && this.G != 6) {
            String string = v.getResources().getString(i.bottomsheet_action_expand_halfway);
            b.d.a.a.p.c cVar = new b.d.a.a.p.c(this, 6);
            List<b.a> listH = q.h(v);
            int i3 = -1;
            int i4 = 0;
            while (true) {
                int[] iArr = q.f827d;
                if (i4 >= iArr.length || i3 != -1) {
                    break;
                }
                int i5 = iArr[i4];
                boolean z = true;
                for (int i6 = 0; i6 < listH.size(); i6++) {
                    z &= listH.get(i6).a() != i5;
                }
                if (z) {
                    i3 = i5;
                }
                i4++;
            }
            if (i3 != -1) {
                q.a(v, new b.a(null, i3, string, cVar, null));
            }
            this.W = i3;
        }
        if (this.D && this.G != 5) {
            H(v, b.a.j, 5);
        }
        int i7 = this.G;
        if (i7 == 3) {
            i = this.f2384b ? 4 : 6;
            aVar = b.a.i;
        } else {
            if (i7 != 4) {
                if (i7 != 6) {
                    return;
                }
                H(v, b.a.i, 4);
                H(v, b.a.h, 3);
                return;
            }
            i = this.f2384b ? 3 : 6;
            aVar = b.a.h;
        }
        H(v, aVar, i);
    }

    public final void Q(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.u != z) {
            this.u = z;
            if (this.j == null || (valueAnimator = this.w) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.w.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.w.setFloatValues(1.0f - f2, f2);
            this.w.start();
        }
    }

    public final void R(boolean z) {
        Map<View, Integer> map;
        int iIntValue;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.V != null) {
                    return;
                } else {
                    this.V = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.O.get()) {
                    if (z) {
                        this.V.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.f2385c) {
                            iIntValue = 4;
                            q.b0(childAt, iIntValue);
                        }
                    } else if (this.f2385c && (map = this.V) != null && map.containsKey(childAt)) {
                        iIntValue = this.V.get(childAt).intValue();
                        q.b0(childAt, iIntValue);
                    }
                }
            }
            if (!z) {
                this.V = null;
            } else if (this.f2385c) {
                this.O.get().sendAccessibilityEvent(8);
            }
        }
    }

    public final void S(boolean z) {
        V v;
        if (this.O != null) {
            B();
            if (this.G != 4 || (v = this.O.get()) == null) {
                return;
            }
            if (z) {
                M(this.G);
            } else {
                v.requestLayout();
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void f(CoordinatorLayout.f fVar) {
        this.O = null;
        this.H = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void i() {
        this.O = null;
        this.H = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean j(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        a.j.b.e eVar;
        if (!v.isShown() || !this.F) {
            this.I = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.S = -1;
            VelocityTracker velocityTracker = this.R;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.R = null;
            }
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.T = (int) motionEvent.getY();
            if (this.G != 2) {
                WeakReference<View> weakReference = this.P;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.j(view, x, this.T)) {
                    this.S = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.U = true;
                }
            }
            this.I = this.S == -1 && !coordinatorLayout.j(v, x, this.T);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.U = false;
            this.S = -1;
            if (this.I) {
                this.I = false;
                return false;
            }
        }
        if (!this.I && (eVar = this.H) != null && eVar.u(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.P;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.I || this.G == 1 || coordinatorLayout.j(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.H == null || Math.abs(((float) this.T) - motionEvent.getY()) <= ((float) this.H.f902b)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, int i) {
        int iG;
        g gVar;
        if (q.m(coordinatorLayout) && !v.getFitsSystemWindows()) {
            v.setFitsSystemWindows(true);
        }
        if (this.O == null) {
            this.g = coordinatorLayout.getResources().getDimensionPixelSize(b.d.a.a.d.design_bottom_sheet_peek_height_min);
            boolean z = (this.m || this.f2388f) ? false : true;
            if (this.n || this.o || this.p || z) {
                q.c0(v, new b.d.a.a.z.l(new b.d.a.a.p.b(this, z), new o(v.getPaddingStart(), v.getPaddingTop(), v.getPaddingEnd(), v.getPaddingBottom())));
                if (v.isAttachedToWindow()) {
                    v.requestApplyInsets();
                } else {
                    v.addOnAttachStateChangeListener(new m());
                }
            }
            this.O = new WeakReference<>(v);
            if (this.i && (gVar = this.j) != null) {
                v.setBackground(gVar);
            }
            g gVar2 = this.j;
            if (gVar2 != null) {
                float elevation = this.C;
                if (elevation == -1.0f) {
                    elevation = v.getElevation();
                }
                gVar2.p(elevation);
                boolean z2 = this.G == 3;
                this.u = z2;
                this.j.r(z2 ? 0.0f : 1.0f);
            }
            P();
            if (v.getImportantForAccessibility() == 0) {
                v.setImportantForAccessibility(1);
            }
            int measuredWidth = v.getMeasuredWidth();
            int i2 = this.k;
            if (measuredWidth > i2 && i2 != -1) {
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.width = this.k;
                v.post(new a(this, v, layoutParams));
            }
        }
        if (this.H == null) {
            this.H = new a.j.b.e(coordinatorLayout.getContext(), coordinatorLayout, this.X);
        }
        int top = v.getTop();
        coordinatorLayout.q(v, i);
        this.M = coordinatorLayout.getWidth();
        this.N = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.L = height;
        int i3 = this.N;
        int i4 = i3 - height;
        int i5 = this.s;
        if (i4 < i5) {
            if (!this.q) {
                i3 -= i5;
            }
            this.L = i3;
        }
        this.y = Math.max(0, this.N - this.L);
        this.z = (int) ((1.0f - this.A) * this.N);
        B();
        int i6 = this.G;
        if (i6 == 3) {
            iG = G();
        } else if (i6 == 6) {
            iG = this.z;
        } else if (this.D && i6 == 5) {
            iG = this.N;
        } else {
            int i7 = this.G;
            if (i7 != 4) {
                if (i7 == 1 || i7 == 2) {
                    v.offsetTopAndBottom(top - v.getTop());
                }
                this.P = new WeakReference<>(F(v));
                return true;
            }
            iG = this.B;
        }
        v.offsetTopAndBottom(iG);
        this.P = new WeakReference<>(F(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean n(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
        WeakReference<View> weakReference = this.P;
        return (weakReference == null || view != weakReference.get() || this.G == 3) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void p(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        int i4;
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.P;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i5 = top - i2;
        if (i2 > 0) {
            if (i5 < G()) {
                iArr[1] = top - G();
                q.I(v, -iArr[1]);
                i4 = 3;
                K(i4);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i2;
                q.I(v, -i2);
                K(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            int i6 = this.B;
            if (i5 > i6 && !this.D) {
                iArr[1] = top - i6;
                q.I(v, -iArr[1]);
                i4 = 4;
                K(i4);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i2;
                q.I(v, -i2);
                K(1);
            }
        }
        E(v.getTop());
        this.J = i2;
        this.K = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void r(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void u(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        e eVar = (e) parcelable;
        int i = this.f2383a;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.f2387e = eVar.f2396d;
            }
            int i2 = this.f2383a;
            if (i2 == -1 || (i2 & 2) == 2) {
                this.f2384b = eVar.f2397e;
            }
            int i3 = this.f2383a;
            if (i3 == -1 || (i3 & 4) == 4) {
                this.D = eVar.f2398f;
            }
            int i4 = this.f2383a;
            if (i4 == -1 || (i4 & 8) == 8) {
                this.E = eVar.g;
            }
        }
        int i5 = eVar.f2395c;
        if (i5 == 1 || i5 == 2) {
            this.G = 4;
        } else {
            this.G = i5;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable v(CoordinatorLayout coordinatorLayout, V v) {
        return new e((Parcelable) View.BaseSavedState.EMPTY_STATE, (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean x(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.J = 0;
        this.K = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0084, code lost:
    
        if (r4 < java.lang.Math.abs(r4 - r3.B)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0098, code lost:
    
        if (java.lang.Math.abs(r4 - r1) < java.lang.Math.abs(r4 - r3.B)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b3, code lost:
    
        if (java.lang.Math.abs(r4 - r3.z) < java.lang.Math.abs(r4 - r3.B)) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0058  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(androidx.coordinatorlayout.widget.CoordinatorLayout r4, V r5, android.view.View r6, int r7) {
        /*
            r3 = this;
            int r4 = r5.getTop()
            int r7 = r3.G()
            r0 = 3
            if (r4 != r7) goto Lf
            r3.K(r0)
            return
        Lf:
            java.lang.ref.WeakReference<android.view.View> r4 = r3.P
            if (r4 == 0) goto Lc3
            java.lang.Object r4 = r4.get()
            if (r6 != r4) goto Lc3
            boolean r4 = r3.K
            if (r4 != 0) goto L1f
            goto Lc3
        L1f:
            int r4 = r3.J
            r6 = 4
            r7 = 6
            if (r4 <= 0) goto L34
            boolean r4 = r3.f2384b
            if (r4 == 0) goto L2a
            goto L75
        L2a:
            int r4 = r5.getTop()
            int r6 = r3.z
            if (r4 <= r6) goto L86
            goto Lb7
        L34:
            boolean r4 = r3.D
            if (r4 == 0) goto L58
            android.view.VelocityTracker r4 = r3.R
            if (r4 != 0) goto L3e
            r4 = 0
            goto L4d
        L3e:
            r1 = 1000(0x3e8, float:1.401E-42)
            float r2 = r3.f2386d
            r4.computeCurrentVelocity(r1, r2)
            android.view.VelocityTracker r4 = r3.R
            int r1 = r3.S
            float r4 = r4.getYVelocity(r1)
        L4d:
            boolean r4 = r3.N(r5, r4)
            if (r4 == 0) goto L58
            int r4 = r3.N
            r0 = 5
            goto Lbd
        L58:
            int r4 = r3.J
            if (r4 != 0) goto L9b
            int r4 = r5.getTop()
            boolean r1 = r3.f2384b
            if (r1 == 0) goto L78
            int r7 = r3.y
            int r7 = r4 - r7
            int r7 = java.lang.Math.abs(r7)
            int r1 = r3.B
            int r4 = r4 - r1
            int r4 = java.lang.Math.abs(r4)
            if (r7 >= r4) goto Lba
        L75:
            int r4 = r3.y
            goto Lbd
        L78:
            int r1 = r3.z
            if (r4 >= r1) goto L8b
            int r6 = r3.B
            int r6 = r4 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r4 >= r6) goto Lb5
        L86:
            int r4 = r3.G()
            goto Lbd
        L8b:
            int r0 = r4 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r3.B
            int r4 = r4 - r1
            int r4 = java.lang.Math.abs(r4)
            if (r0 >= r4) goto Lba
            goto Lb5
        L9b:
            boolean r4 = r3.f2384b
            if (r4 == 0) goto La0
            goto Lba
        La0:
            int r4 = r5.getTop()
            int r0 = r3.z
            int r0 = r4 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r3.B
            int r4 = r4 - r1
            int r4 = java.lang.Math.abs(r4)
            if (r0 >= r4) goto Lba
        Lb5:
            int r6 = r3.z
        Lb7:
            r4 = r6
            r0 = r7
            goto Lbd
        Lba:
            int r4 = r3.B
            r0 = r6
        Lbd:
            r6 = 0
            r3.O(r5, r0, r4, r6)
            r3.K = r6
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.z(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }
}
