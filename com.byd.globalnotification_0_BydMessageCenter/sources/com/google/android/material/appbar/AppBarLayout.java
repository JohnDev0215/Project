package com.google.android.material.appbar;

import a.b.k.l;
import a.h.k.f;
import a.h.k.q;
import a.h.k.x.b;
import android.R;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.e0.g;
import b.d.a.a.k;
import b.d.a.a.m.b;
import b.d.a.a.m.c;
import b.d.a.a.m.d;
import b.d.a.a.m.e;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2353d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2354e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2355f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j;
    public WeakReference<View> k;
    public ValueAnimator l;
    public int[] m;
    public Drawable n;

    public static class BaseBehavior<T extends AppBarLayout> extends e<T> {
        public int k;
        public int l;
        public ValueAnimator m;
        public int n;
        public boolean o;
        public float p;
        public WeakReference<View> q;

        public static class a extends a.j.a.a {
            public static final Parcelable.Creator<a> CREATOR = new C0055a();

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f2356c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public float f2357d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public boolean f2358e;

            /* JADX INFO: renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$a$a, reason: collision with other inner class name */
            public static class C0055a implements Parcelable.ClassLoaderCreator<a> {
                @Override // android.os.Parcelable.Creator
                public Object createFromParcel(Parcel parcel) {
                    return new a(parcel, null);
                }

                @Override // android.os.Parcelable.Creator
                public Object[] newArray(int i) {
                    return new a[i];
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new a(parcel, classLoader);
                }
            }

            public a(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f2356c = parcel.readInt();
                this.f2357d = parcel.readFloat();
                this.f2358e = parcel.readByte() != 0;
            }

            public a(Parcelable parcelable) {
                super(parcelable);
            }

            @Override // a.j.a.a, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(this.f892a, i);
                parcel.writeInt(this.f2356c);
                parcel.writeFloat(this.f2357d);
                parcel.writeByte(this.f2358e ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
            this.n = -1;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.n = -1;
        }

        public static boolean J(int i, int i2) {
            return (i & i2) == i2;
        }

        @Override // b.d.a.a.m.e
        public int E() {
            return B() + this.k;
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00d0  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00d7  */
        @Override // b.d.a.a.m.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int H(androidx.coordinatorlayout.widget.CoordinatorLayout r8, android.view.View r9, int r10, int r11, int r12) {
            /*
                Method dump skipped, instruction units count: 232
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.H(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public final void I(CoordinatorLayout coordinatorLayout, T t, int i, float f2) {
            int iAbs = Math.abs(E() - i);
            float fAbs = Math.abs(f2);
            int iRound = fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t.getHeight()) + 1.0f) * 150.0f);
            int iE = E();
            if (iE == i) {
                ValueAnimator valueAnimator = this.m;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.m.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.m;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.m = valueAnimator3;
                valueAnimator3.setInterpolator(b.d.a.a.l.a.f1970e);
                this.m.addUpdateListener(new b(this, coordinatorLayout, t));
            } else {
                valueAnimator2.cancel();
            }
            this.m.setDuration(Math.min(iRound, 600));
            this.m.setIntValues(iE, i);
            this.m.start();
        }

        public final View K(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof f) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public void L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int[] iArr) {
            int i2;
            int downNestedPreScrollRange;
            if (i != 0) {
                if (i < 0) {
                    i2 = -appBarLayout.getTotalScrollRange();
                    downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange() + i2;
                } else {
                    i2 = -appBarLayout.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i3 = i2;
                int i4 = downNestedPreScrollRange;
                if (i3 != i4) {
                    iArr[1] = F(coordinatorLayout, appBarLayout, i, i3, i4);
                }
            }
            if (appBarLayout.i) {
                appBarLayout.c(appBarLayout.d(view));
            }
        }

        public void M(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int[] iArr) {
            if (i < 0) {
                iArr[1] = F(coordinatorLayout, appBarLayout, i, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i == 0) {
                P(coordinatorLayout, appBarLayout);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean N(androidx.coordinatorlayout.widget.CoordinatorLayout r3, com.google.android.material.appbar.AppBarLayout r4, android.view.View r5, int r6, int r7) {
            /*
                r2 = this;
                r6 = r6 & 2
                r0 = 1
                r1 = 0
                if (r6 == 0) goto L2a
                boolean r6 = r4.i
                if (r6 != 0) goto L2b
                int r6 = r4.getTotalScrollRange()
                if (r6 == 0) goto L12
                r6 = r0
                goto L13
            L12:
                r6 = r1
            L13:
                if (r6 == 0) goto L26
                int r3 = r3.getHeight()
                int r5 = r5.getHeight()
                int r3 = r3 - r5
                int r4 = r4.getHeight()
                if (r3 > r4) goto L26
                r3 = r0
                goto L27
            L26:
                r3 = r1
            L27:
                if (r3 == 0) goto L2a
                goto L2b
            L2a:
                r0 = r1
            L2b:
                if (r0 == 0) goto L34
                android.animation.ValueAnimator r3 = r2.m
                if (r3 == 0) goto L34
                r3.cancel()
            L34:
                r3 = 0
                r2.q = r3
                r2.l = r7
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.N(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int):boolean");
        }

        public final void O(CoordinatorLayout coordinatorLayout, T t) {
            int iE = E();
            int childCount = t.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = t.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                a aVar = (a) childAt.getLayoutParams();
                if (J(aVar.f2359a, 32)) {
                    top -= ((LinearLayout.LayoutParams) aVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) aVar).bottomMargin;
                }
                int i2 = -iE;
                if (top <= i2 && bottom >= i2) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                View childAt2 = t.getChildAt(i);
                a aVar2 = (a) childAt2.getLayoutParams();
                int i3 = aVar2.f2359a;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int iQ = -childAt2.getBottom();
                    if (i == t.getChildCount() - 1) {
                        iQ += t.getTopInset();
                    }
                    if (J(i3, 2)) {
                        iQ += q.q(childAt2);
                    } else if (J(i3, 5)) {
                        int iQ2 = q.q(childAt2) + iQ;
                        if (iE < iQ2) {
                            i4 = iQ2;
                        } else {
                            iQ = iQ2;
                        }
                    }
                    if (J(i3, 32)) {
                        i4 += ((LinearLayout.LayoutParams) aVar2).topMargin;
                        iQ -= ((LinearLayout.LayoutParams) aVar2).bottomMargin;
                    }
                    if (iE < (iQ + i4) / 2) {
                        i4 = iQ;
                    }
                    I(coordinatorLayout, t, l.i.k(i4, -t.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        public final void P(CoordinatorLayout coordinatorLayout, T t) {
            q.P(coordinatorLayout, b.a.f860f.a());
            q.P(coordinatorLayout, b.a.g.a());
            View viewK = K(coordinatorLayout);
            if (viewK == null || t.getTotalScrollRange() == 0 || !(((CoordinatorLayout.f) viewK.getLayoutParams()).f1514a instanceof ScrollingViewBehavior)) {
                return;
            }
            if (E() != (-t.getTotalScrollRange()) && viewK.canScrollVertically(1)) {
                q.R(coordinatorLayout, b.a.f860f, null, new d(this, t, false));
            }
            if (E() != 0) {
                if (!viewK.canScrollVertically(-1)) {
                    q.R(coordinatorLayout, b.a.g, null, new d(this, t, true));
                    return;
                }
                int i = -t.getDownNestedPreScrollRange();
                if (i != 0) {
                    q.R(coordinatorLayout, b.a.g, null, new c(this, coordinatorLayout, t, viewK, i));
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void Q(androidx.coordinatorlayout.widget.CoordinatorLayout r7, T r8, int r9, int r10, boolean r11) {
            /*
                r6 = this;
                int r0 = java.lang.Math.abs(r9)
                int r1 = r8.getChildCount()
                r2 = 0
                r3 = r2
            La:
                if (r3 >= r1) goto L20
                android.view.View r4 = r8.getChildAt(r3)
                int r5 = r4.getTop()
                if (r0 < r5) goto L1d
                int r5 = r4.getBottom()
                if (r0 > r5) goto L1d
                goto L21
            L1d:
                int r3 = r3 + 1
                goto La
            L20:
                r4 = 0
            L21:
                if (r4 == 0) goto L9d
                android.view.ViewGroup$LayoutParams r0 = r4.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$a r0 = (com.google.android.material.appbar.AppBarLayout.a) r0
                int r0 = r0.f2359a
                r1 = r0 & 1
                r3 = 1
                if (r1 == 0) goto L5b
                int r1 = a.h.k.q.q(r4)
                if (r10 <= 0) goto L48
                r10 = r0 & 12
                if (r10 == 0) goto L48
                int r9 = -r9
                int r10 = r4.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L5b
                goto L59
            L48:
                r10 = r0 & 2
                if (r10 == 0) goto L5b
                int r9 = -r9
                int r10 = r4.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L5b
            L59:
                r9 = r3
                goto L5c
            L5b:
                r9 = r2
            L5c:
                boolean r10 = r8.i
                if (r10 == 0) goto L68
                android.view.View r6 = r6.K(r7)
                boolean r9 = r8.d(r6)
            L68:
                boolean r6 = r8.c(r9)
                if (r11 != 0) goto L9a
                if (r6 == 0) goto L9d
                java.util.List r6 = r7.e(r8)
                int r7 = r6.size()
                r9 = r2
            L79:
                if (r9 >= r7) goto L98
                java.lang.Object r10 = r6.get(r9)
                android.view.View r10 = (android.view.View) r10
                android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$f r10 = (androidx.coordinatorlayout.widget.CoordinatorLayout.f) r10
                androidx.coordinatorlayout.widget.CoordinatorLayout$c r10 = r10.f1514a
                boolean r11 = r10 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r11 == 0) goto L95
                com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior r10 = (com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior) r10
                int r6 = r10.g
                if (r6 == 0) goto L98
                r2 = r3
                goto L98
            L95:
                int r9 = r9 + 1
                goto L79
            L98:
                if (r2 == 0) goto L9d
            L9a:
                r8.jumpDrawablesToCurrentState()
            L9d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.Q(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        @Override // b.d.a.a.m.g, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean k(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.k(coordinatorLayout, appBarLayout, i);
            int pendingAction = appBarLayout.getPendingAction();
            int i2 = this.n;
            if (i2 >= 0 && (pendingAction & 8) == 0) {
                View childAt = appBarLayout.getChildAt(i2);
                int i3 = -childAt.getBottom();
                G(coordinatorLayout, appBarLayout, this.o ? appBarLayout.getTopInset() + childAt.getMinimumHeight() + i3 : Math.round(childAt.getHeight() * this.p) + i3);
            } else if (pendingAction != 0) {
                boolean z = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i4 = -appBarLayout.getUpNestedPreScrollRange();
                    if (z) {
                        I(coordinatorLayout, appBarLayout, i4, 0.0f);
                    } else {
                        G(coordinatorLayout, appBarLayout, i4);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z) {
                        I(coordinatorLayout, appBarLayout, 0, 0.0f);
                    } else {
                        G(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            appBarLayout.f2355f = 0;
            this.n = -1;
            D(l.i.k(B(), -appBarLayout.getTotalScrollRange(), 0));
            Q(coordinatorLayout, appBarLayout, B(), 0, true);
            appBarLayout.f2350a = B();
            if (!appBarLayout.willNotDraw()) {
                appBarLayout.postInvalidateOnAnimation();
            }
            P(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.r(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ void p(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            L(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ void r(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            M(coordinatorLayout, (AppBarLayout) view, i4, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void u(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            if (!(parcelable instanceof a)) {
                this.n = -1;
                return;
            }
            a aVar = (a) parcelable;
            this.n = aVar.f2356c;
            this.p = aVar.f2357d;
            this.o = aVar.f2358e;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public Parcelable v(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int iB = B();
            int childCount = appBarLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + iB;
                if (childAt.getTop() + iB <= 0 && bottom >= 0) {
                    a aVar = new a(absSavedState);
                    aVar.f2356c = i;
                    aVar.f2358e = bottom == appBarLayout.getTopInset() + q.q(childAt);
                    aVar.f2357d = bottom / childAt.getHeight();
                    return aVar;
                }
            }
            return absSavedState;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean x(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            return N(coordinatorLayout, (AppBarLayout) view, view2, i, i2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void z(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.l == 0 || i == 1) {
                O(coordinatorLayout, appBarLayout);
                if (appBarLayout.i) {
                    appBarLayout.c(appBarLayout.d(view2));
                }
            }
            this.q = new WeakReference<>(view2);
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends b.d.a.a.m.f {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.ScrollingViewBehavior_Layout);
            this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public AppBarLayout F(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean g(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout.c cVar = ((CoordinatorLayout.f) view2.getLayoutParams()).f1514a;
            if (cVar instanceof BaseBehavior) {
                q.I(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) cVar).k) + this.f2008f) - E(view2));
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.i) {
                return false;
            }
            appBarLayout.c(appBarLayout.d(view));
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                q.P(coordinatorLayout, b.a.f860f.a());
                q.P(coordinatorLayout, b.a.g.a());
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean t(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout appBarLayoutF = F(coordinatorLayout.d(view));
            if (appBarLayoutF != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.f2006d;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    appBarLayoutF.b(false, !z);
                    return true;
                }
            }
            return false;
        }
    }

    public static class a extends LinearLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Interpolator f2360b;

        public a(int i, int i2) {
            super(i, i2);
            this.f2359a = 1;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2359a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.AppBarLayout_Layout);
            this.f2359a = typedArrayObtainStyledAttributes.getInt(k.AppBarLayout_Layout_layout_scrollFlags, 0);
            if (typedArrayObtainStyledAttributes.hasValue(k.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.f2360b = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(k.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2359a = 1;
        }

        public a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2359a = 1;
        }

        public a(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2359a = 1;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LinearLayout.LayoutParams ? new a((LinearLayout.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new a((ViewGroup.MarginLayoutParams) layoutParams) : new a(layoutParams);
    }

    public void b(boolean z, boolean z2) {
        this.f2355f = (z ? 1 : 2) | (z2 ? 4 : 0) | 8;
        requestLayout();
    }

    public boolean c(boolean z) {
        if (this.h == z) {
            return false;
        }
        this.h = z;
        refreshDrawableState();
        if (this.i && (getBackground() instanceof g)) {
            g gVar = (g) getBackground();
            float dimension = getResources().getDimension(b.d.a.a.d.design_appbar_elevation);
            float f2 = z ? 0.0f : dimension;
            if (!z) {
                dimension = 0.0f;
            }
            ValueAnimator valueAnimator = this.l;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, dimension);
            this.l = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(getResources().getInteger(b.d.a.a.g.app_bar_elevation_anim_duration));
            this.l.setInterpolator(b.d.a.a.l.a.f1966a);
            this.l.addUpdateListener(new b.d.a.a.m.a(this, gVar));
            this.l.start();
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public boolean d(View view) {
        int i;
        if (this.k == null && (i = this.j) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.j);
            }
            if (viewFindViewById != null) {
                this.k = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.k;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view = view2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.n != null && getTopInset() > 0) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.f2350a);
            this.n.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.n;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final boolean e() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        return (childAt.getVisibility() == 8 || q.m(childAt)) ? false : true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new a(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<AppBarLayout> getBehavior() {
        return new Behavior();
    }

    public int getDownNestedPreScrollRange() {
        int iMin;
        int iQ;
        int i = this.f2352c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            a aVar = (a) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = aVar.f2359a;
            if ((i3 & 5) != 5) {
                if (i2 > 0) {
                    break;
                }
            } else {
                int i4 = ((LinearLayout.LayoutParams) aVar).topMargin + ((LinearLayout.LayoutParams) aVar).bottomMargin;
                if ((i3 & 8) != 0) {
                    iQ = q.q(childAt);
                } else if ((i3 & 2) != 0) {
                    iQ = measuredHeight - q.q(childAt);
                } else {
                    iMin = i4 + measuredHeight;
                    if (childCount == 0 && q.m(childAt)) {
                        iMin = Math.min(iMin, measuredHeight - getTopInset());
                    }
                    i2 += iMin;
                }
                iMin = iQ + i4;
                if (childCount == 0) {
                    iMin = Math.min(iMin, measuredHeight - getTopInset());
                }
                i2 += iMin;
            }
        }
        int iMax = Math.max(0, i2);
        this.f2352c = iMax;
        return iMax;
    }

    public int getDownNestedScrollRange() {
        int i = this.f2353d;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int iQ = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            a aVar = (a) childAt.getLayoutParams();
            int measuredHeight = ((LinearLayout.LayoutParams) aVar).topMargin + ((LinearLayout.LayoutParams) aVar).bottomMargin + childAt.getMeasuredHeight();
            int i3 = aVar.f2359a;
            if ((i3 & 1) == 0) {
                break;
            }
            iQ += measuredHeight;
            if ((i3 & 2) != 0) {
                iQ -= q.q(childAt);
                break;
            }
            i2++;
        }
        int iMax = Math.max(0, iQ);
        this.f2353d = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.j;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int iQ = q.q(this);
        if (iQ == 0) {
            int childCount = getChildCount();
            iQ = childCount >= 1 ? getChildAt(childCount - 1).getMinimumHeight() : 0;
            if (iQ == 0) {
                return getHeight() / 3;
            }
        }
        return (iQ * 2) + topInset;
    }

    public int getPendingAction() {
        return this.f2355f;
    }

    public Drawable getStatusBarForeground() {
        return this.n;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.f2351b;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int iQ = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            a aVar = (a) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = aVar.f2359a;
            if ((i3 & 1) == 0) {
                break;
            }
            int topInset = measuredHeight + ((LinearLayout.LayoutParams) aVar).topMargin + ((LinearLayout.LayoutParams) aVar).bottomMargin + iQ;
            if (i2 == 0 && q.m(childAt)) {
                topInset -= getTopInset();
            }
            iQ = topInset;
            if ((i3 & 2) != 0) {
                iQ -= q.q(childAt);
                break;
            }
            i2++;
        }
        int iMax = Math.max(0, iQ);
        this.f2351b = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof g) {
            l.i.r0(this, (g) background);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        if (this.m == null) {
            this.m = new int[4];
        }
        int[] iArr = this.m;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        iArr[0] = this.g ? b.d.a.a.b.state_liftable : -b.d.a.a.b.state_liftable;
        iArr[1] = (this.g && this.h) ? b.d.a.a.b.state_lifted : -b.d.a.a.b.state_lifted;
        iArr[2] = this.g ? b.d.a.a.b.state_collapsible : -b.d.a.a.b.state_collapsible;
        iArr[3] = (this.g && this.h) ? b.d.a.a.b.state_collapsed : -b.d.a.a.b.state_collapsed;
        return LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference<View> weakReference = this.k;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.k = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        boolean z3 = true;
        if (q.m(this) && e()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(topInset);
            }
        }
        this.f2351b = -1;
        this.f2352c = -1;
        this.f2353d = -1;
        this.f2354e = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            }
            if (((a) getChildAt(i5).getLayoutParams()).f2360b != null) {
                this.f2354e = true;
                break;
            }
            i5++;
        }
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (!this.i) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
                    break;
                }
                int i7 = ((a) getChildAt(i6).getLayoutParams()).f2359a;
                if ((i7 & 1) == 1 && (i7 & 10) != 0) {
                    z2 = true;
                    break;
                }
                i6++;
            }
            if (!z2) {
                z3 = false;
            }
        }
        if (this.g != z3) {
            this.g = z3;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && q.m(this) && e()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = l.i.k(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
            } else if (mode == 0) {
                measuredHeight += getTopInset();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        this.f2351b = -1;
        this.f2352c = -1;
        this.f2353d = -1;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        l.i.l0(this, f2);
    }

    public void setExpanded(boolean z) {
        b(z, q.C(this));
    }

    public void setLiftOnScroll(boolean z) {
        this.i = z;
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.j = i;
        WeakReference<View> weakReference = this.k;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.k = null;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.n;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.n = drawableMutate;
            boolean z = false;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.n.setState(getDrawableState());
                }
                this.n.setLayoutDirection(q.p(this));
                this.n.setVisible(getVisibility() == 0, false);
                this.n.setCallback(this);
            }
            if (this.n != null && getTopInset() > 0) {
                z = true;
            }
            setWillNotDraw(true ^ z);
            q.M(this);
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(a.b.l.a.a.b(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        int integer = getResources().getInteger(b.d.a.a.g.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j = integer;
        stateListAnimator.addState(new int[]{R.attr.enabled, b.d.a.a.b.state_liftable, -b.d.a.a.b.state_lifted}, ObjectAnimator.ofFloat(this, "elevation", 0.0f).setDuration(j));
        stateListAnimator.addState(new int[]{R.attr.enabled}, ObjectAnimator.ofFloat(this, "elevation", f2).setDuration(j));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(this, "elevation", 0.0f).setDuration(0L));
        setStateListAnimator(stateListAnimator);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.n;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new a(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }
}
