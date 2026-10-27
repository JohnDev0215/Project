package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$integer;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.C0214v;
import defpackage.cn3;
import defpackage.go3;
import defpackage.in3;
import defpackage.n3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pt3;
import defpackage.ql1;
import defpackage.r83;
import defpackage.sc1;
import defpackage.u5;
import defpackage.uc1;
import defpackage.wr1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {
    public static final int x = R$style.Widget_Design_AppBarLayout;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public int j;
    public pt3 k;
    public ArrayList l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public WeakReference<View> r;
    public ValueAnimator s;
    public final ArrayList t;
    public int[] u;
    public Drawable v;
    public Behavior w;

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        public int j;
        public int k;
        public ValueAnimator l;
        public SavedState m;
        public WeakReference<View> n;
        public b o;
        public boolean p;

        public class a extends C0214v {
            public a() {
            }

            @Override // defpackage.C0214v
            public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
                accessibilityNodeInfoCompat.s(BaseBehavior.this.p);
                accessibilityNodeInfoCompat.n(ScrollView.class.getName());
            }
        }

        public static abstract class b<T extends AppBarLayout> {
        }

        public BaseBehavior() {
        }

        public static View C(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof ql1) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x005e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void H(androidx.coordinatorlayout.widget.CoordinatorLayout r7, com.google.android.material.appbar.AppBarLayout r8, int r9, int r10, boolean r11) {
            /*
                int r0 = java.lang.Math.abs(r9)
                int r1 = r8.getChildCount()
                r2 = 0
                r3 = r2
            La:
                r4 = 0
                if (r3 >= r1) goto L21
                android.view.View r5 = r8.getChildAt(r3)
                int r6 = r5.getTop()
                if (r0 < r6) goto L1e
                int r6 = r5.getBottom()
                if (r0 > r6) goto L1e
                goto L22
            L1e:
                int r3 = r3 + 1
                goto La
            L21:
                r5 = r4
            L22:
                r0 = 1
                if (r5 == 0) goto L5e
                android.view.ViewGroup$LayoutParams r1 = r5.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r1 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r1
                int r1 = r1.a
                r3 = r1 & 1
                if (r3 == 0) goto L5e
                java.util.WeakHashMap<android.view.View, in3> r3 = defpackage.om3.a
                int r3 = om3.d.c(r5)
                if (r10 <= 0) goto L4b
                r10 = r1 & 12
                if (r10 == 0) goto L4b
                int r9 = -r9
                int r10 = r5.getBottom()
                int r10 = r10 - r3
                int r1 = r8.getTopInset()
                int r10 = r10 - r1
                if (r9 < r10) goto L5e
                goto L5c
            L4b:
                r10 = r1 & 2
                if (r10 == 0) goto L5e
                int r9 = -r9
                int r10 = r5.getBottom()
                int r10 = r10 - r3
                int r1 = r8.getTopInset()
                int r10 = r10 - r1
                if (r9 < r10) goto L5e
            L5c:
                r9 = r0
                goto L5f
            L5e:
                r9 = r2
            L5f:
                boolean r10 = r8.p
                if (r10 == 0) goto L6b
                android.view.View r9 = C(r7)
                boolean r9 = r8.g(r9)
            L6b:
                boolean r9 = r8.f(r9)
                if (r11 != 0) goto Lb3
                if (r9 == 0) goto Lb6
                la0 r7 = r7.f
                java.lang.Object r7 = r7.g
                wm2 r7 = (defpackage.wm2) r7
                java.lang.Object r7 = r7.get(r8)
                java.util.ArrayList r7 = (java.util.ArrayList) r7
                if (r7 != 0) goto L82
                goto L87
            L82:
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>(r7)
            L87:
                if (r4 != 0) goto L8d
                java.util.List r4 = java.util.Collections.emptyList()
            L8d:
                int r7 = r4.size()
                r9 = r2
            L92:
                if (r9 >= r7) goto Lb1
                java.lang.Object r10 = r4.get(r9)
                android.view.View r10 = (android.view.View) r10
                android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$e r10 = (androidx.coordinatorlayout.widget.CoordinatorLayout.e) r10
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r10 = r10.a
                boolean r11 = r10 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r11 == 0) goto Lae
                com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior r10 = (com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior) r10
                int r7 = r10.f
                if (r7 == 0) goto Lb1
                r2 = r0
                goto Lb1
            Lae:
                int r9 = r9 + 1
                goto L92
            Lb1:
                if (r2 == 0) goto Lb6
            Lb3:
                r8.jumpDrawablesToCurrentState()
            Lb6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.H(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        public final void B(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int iAbs = Math.abs(x() - i);
            float fAbs = Math.abs(0.0f);
            int iRound = fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / appBarLayout.getHeight()) + 1.0f) * 150.0f);
            int iX = x();
            if (iX == i) {
                ValueAnimator valueAnimator = this.l;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.l.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.l;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.l = valueAnimator3;
                valueAnimator3.setInterpolator(u5.e);
                this.l.addUpdateListener(new com.google.android.material.appbar.b(this, coordinatorLayout, appBarLayout));
            } else {
                valueAnimator2.cancel();
            }
            this.l.setDuration(Math.min(iRound, 600));
            this.l.setIntValues(iX, i);
            this.l.start();
        }

        public final void D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int[] iArr) {
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
                    iArr[1] = z(coordinatorLayout, appBarLayout, x() - i, i3, i4);
                }
            }
            if (appBarLayout.p) {
                appBarLayout.f(appBarLayout.g(view));
            }
        }

        public final SavedState E(Parcelable parcelable, T t) {
            int iS = s();
            int childCount = t.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = t.getChildAt(i);
                int bottom = childAt.getBottom() + iS;
                if (childAt.getTop() + iS <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.f;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z = iS == 0;
                    savedState.h = z;
                    savedState.g = !z && (-iS) >= t.getTotalScrollRange();
                    savedState.i = i;
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    savedState.k = bottom == t.getTopInset() + om3.d.c(childAt);
                    savedState.j = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public final void F(CoordinatorLayout coordinatorLayout, T t) {
            int paddingTop = t.getPaddingTop() + t.getTopInset();
            int iX = x() - paddingTop;
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
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if ((layoutParams.a & 32) == 32) {
                    top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                int i2 = -iX;
                if (top <= i2 && bottom >= i2) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                View childAt2 = t.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.a;
                if ((i3 & 17) == 17) {
                    int topInset = -childAt2.getTop();
                    int iC = -childAt2.getBottom();
                    if (i == 0) {
                        WeakHashMap<View, in3> weakHashMap = om3.a;
                        if (om3.d.a(t) && om3.d.a(childAt2)) {
                            topInset -= t.getTopInset();
                        }
                    }
                    if ((i3 & 2) == 2) {
                        WeakHashMap<View, in3> weakHashMap2 = om3.a;
                        iC += om3.d.c(childAt2);
                    } else {
                        if ((i3 & 5) == 5) {
                            WeakHashMap<View, in3> weakHashMap3 = om3.a;
                            int iC2 = om3.d.c(childAt2) + iC;
                            if (iX < iC2) {
                                topInset = iC2;
                            } else {
                                iC = iC2;
                            }
                        }
                    }
                    if ((i3 & 32) == 32) {
                        topInset += ((LinearLayout.LayoutParams) layoutParams2).topMargin;
                        iC -= ((LinearLayout.LayoutParams) layoutParams2).bottomMargin;
                    }
                    if (iX < (iC + topInset) / 2) {
                        topInset = iC;
                    }
                    B(coordinatorLayout, t, n3.w(topInset + paddingTop, -t.getTotalScrollRange(), 0));
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x00b7  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void G(androidx.coordinatorlayout.widget.CoordinatorLayout r12, T r13) {
            /*
                r11 = this;
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a.o
                int r0 = r0.a()
                defpackage.om3.i(r12, r0)
                r0 = 0
                defpackage.om3.f(r12, r0)
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a.p
                int r1 = r1.a()
                defpackage.om3.i(r12, r1)
                defpackage.om3.f(r12, r0)
                int r1 = r13.getTotalScrollRange()
                if (r1 != 0) goto L20
                return
            L20:
                int r1 = r12.getChildCount()
                r2 = r0
            L25:
                r3 = 0
                if (r2 >= r1) goto L3d
                android.view.View r4 = r12.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$e r5 = (androidx.coordinatorlayout.widget.CoordinatorLayout.e) r5
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r5 = r5.a
                boolean r5 = r5 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r5 == 0) goto L3a
                r8 = r4
                goto L3e
            L3a:
                int r2 = r2 + 1
                goto L25
            L3d:
                r8 = r3
            L3e:
                if (r8 != 0) goto L41
                return
            L41:
                int r1 = r13.getChildCount()
                r2 = r0
            L46:
                r10 = 1
                if (r2 >= r1) goto L5c
                android.view.View r4 = r13.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
                int r4 = r4.a
                if (r4 == 0) goto L59
                r1 = r10
                goto L5d
            L59:
                int r2 = r2 + 1
                goto L46
            L5c:
                r1 = r0
            L5d:
                if (r1 != 0) goto L60
                return
            L60:
                android.view.View$AccessibilityDelegate r1 = om3.n.a(r12)
                if (r1 == 0) goto L68
                r1 = r10
                goto L69
            L68:
                r1 = r0
            L69:
                if (r1 != 0) goto L73
                com.google.android.material.appbar.AppBarLayout$BaseBehavior$a r1 = new com.google.android.material.appbar.AppBarLayout$BaseBehavior$a
                r1.<init>()
                defpackage.om3.k(r12, r1)
            L73:
                int r1 = r11.x()
                int r2 = r13.getTotalScrollRange()
                int r2 = -r2
                if (r1 == r2) goto L89
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a.o
                com.google.android.material.appbar.d r2 = new com.google.android.material.appbar.d
                r2.<init>(r13, r0)
                defpackage.om3.j(r12, r1, r3, r2)
                r0 = r10
            L89:
                int r1 = r11.x()
                if (r1 == 0) goto Lb7
                r1 = -1
                boolean r1 = r8.canScrollVertically(r1)
                if (r1 == 0) goto Lac
                int r1 = r13.getDownNestedPreScrollRange()
                int r9 = -r1
                if (r9 == 0) goto Lb7
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a.p
                com.google.android.material.appbar.c r1 = new com.google.android.material.appbar.c
                r4 = r1
                r5 = r11
                r6 = r12
                r7 = r13
                r4.<init>(r5, r6, r7, r8, r9)
                defpackage.om3.j(r12, r0, r3, r1)
                goto Lb8
            Lac:
                androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r0 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a.p
                com.google.android.material.appbar.d r1 = new com.google.android.material.appbar.d
                r1.<init>(r13, r10)
                defpackage.om3.j(r12, r0, r3, r1)
                goto Lb8
            Lb7:
                r10 = r0
            Lb8:
                r11.p = r10
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.G(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout):void");
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i) {
            int iRound;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.h(coordinatorLayout, appBarLayout, i);
            int pendingAction = appBarLayout.getPendingAction();
            SavedState savedState = this.m;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i2 = -appBarLayout.getUpNestedPreScrollRange();
                        if (z) {
                            B(coordinatorLayout, appBarLayout, i2);
                        } else {
                            A(coordinatorLayout, appBarLayout, i2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z) {
                            B(coordinatorLayout, appBarLayout, 0);
                        } else {
                            A(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState.g) {
                A(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState.h) {
                A(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState.i);
                int i3 = -childAt.getBottom();
                if (this.m.k) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    iRound = appBarLayout.getTopInset() + om3.d.c(childAt) + i3;
                } else {
                    iRound = Math.round(childAt.getHeight() * this.m.j) + i3;
                }
                A(coordinatorLayout, appBarLayout, iRound);
            }
            appBarLayout.j = 0;
            this.m = null;
            int iW = n3.w(s(), -appBarLayout.getTotalScrollRange(), 0);
            cn3 cn3Var = this.a;
            if (cn3Var != null) {
                cn3Var.b(iW);
            } else {
                this.b = iW;
            }
            H(coordinatorLayout, appBarLayout, s(), 0, true);
            appBarLayout.c(s());
            G(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean i(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.e) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.x(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void k(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            D(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i3 < 0) {
                iArr[1] = z(coordinatorLayout, appBarLayout, x() - i3, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i3 == 0) {
                G(coordinatorLayout, appBarLayout);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void n(View view, Parcelable parcelable) {
            if (!(parcelable instanceof SavedState)) {
                this.m = null;
            } else {
                SavedState savedState = this.m;
                this.m = (SavedState) parcelable;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final Parcelable o(View view) {
            android.view.AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState savedStateE = E(absSavedState, (AppBarLayout) view);
            return savedStateE == null ? absSavedState : savedStateE;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean p(androidx.coordinatorlayout.widget.CoordinatorLayout r2, android.view.View r3, android.view.View r4, android.view.View r5, int r6, int r7) {
            /*
                r1 = this;
                com.google.android.material.appbar.AppBarLayout r3 = (com.google.android.material.appbar.AppBarLayout) r3
                r5 = r6 & 2
                r6 = 0
                if (r5 == 0) goto L2c
                boolean r5 = r3.p
                r0 = 1
                if (r5 != 0) goto L2b
                int r5 = r3.getTotalScrollRange()
                if (r5 == 0) goto L14
                r5 = r0
                goto L15
            L14:
                r5 = r6
            L15:
                if (r5 == 0) goto L28
                int r2 = r2.getHeight()
                int r4 = r4.getHeight()
                int r2 = r2 - r4
                int r3 = r3.getHeight()
                if (r2 > r3) goto L28
                r2 = r0
                goto L29
            L28:
                r2 = r6
            L29:
                if (r2 == 0) goto L2c
            L2b:
                r6 = r0
            L2c:
                if (r6 == 0) goto L35
                android.animation.ValueAnimator r2 = r1.l
                if (r2 == 0) goto L35
                r2.cancel()
            L35:
                r2 = 0
                r1.n = r2
                r1.k = r7
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.p(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, android.view.View, int, int):boolean");
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void q(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.k == 0 || i == 1) {
                F(coordinatorLayout, appBarLayout);
                if (appBarLayout.p) {
                    appBarLayout.f(appBarLayout.g(view2));
                }
            }
            this.n = new WeakReference<>(view2);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final boolean u(View view) {
            WeakReference<View> weakReference;
            View view2;
            return (this.o == null && (weakReference = this.n) != null && ((view2 = weakReference.get()) == null || !view2.isShown() || view2.canScrollVertically(-1))) ? false : true;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int v(View view) {
            return -((AppBarLayout) view).getDownNestedScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int w(View view) {
            return ((AppBarLayout) view).getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int x() {
            return s() + this.j;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final void y(View view, CoordinatorLayout coordinatorLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            F(coordinatorLayout, appBarLayout);
            if (appBarLayout.p) {
                appBarLayout.f(appBarLayout.g(C(coordinatorLayout)));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0148  */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int z(androidx.coordinatorlayout.widget.CoordinatorLayout r19, android.view.View r20, int r21, int r22, int r23) {
            /*
                Method dump skipped, instruction units count: 340
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.z(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();
            public boolean g;
            public boolean h;
            public int i;
            public float j;
            public boolean k;

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
                this.g = parcel.readByte() != 0;
                this.h = parcel.readByte() != 0;
                this.i = parcel.readInt();
                this.j = parcel.readFloat();
                this.k = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(this.e, i);
                parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.i);
                parcel.writeFloat(this.j);
                parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class a extends BaseBehavior.b<AppBarLayout> {
        }

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean b(View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
            int iW;
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.e) view2.getLayoutParams()).a;
            if (behavior instanceof BaseBehavior) {
                int bottom = (view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).j + this.e;
                if (this.f == 0) {
                    iW = 0;
                } else {
                    float fV = v(view2);
                    int i = this.f;
                    iW = n3.w((int) (fV * i), 0, i);
                }
                int i2 = bottom - iW;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                view.offsetTopAndBottom(i2);
            }
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.p) {
                    appBarLayout.f(appBarLayout.g(view));
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void e(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                om3.i(coordinatorLayout, AccessibilityNodeInfoCompat.a.o.a());
                om3.f(coordinatorLayout, 0);
                om3.i(coordinatorLayout, AccessibilityNodeInfoCompat.a.p.a());
                om3.f(coordinatorLayout, 0);
                om3.k(coordinatorLayout, null);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean m(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout appBarLayout;
            List<View> listP = coordinatorLayout.p(view);
            int size = listP.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    appBarLayout = null;
                    break;
                }
                View view2 = listP.get(i);
                if (view2 instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) view2;
                    break;
                }
                i++;
            }
            if (appBarLayout != null) {
                rect.offset(view.getLeft(), view.getTop());
                int width = coordinatorLayout.getWidth();
                int height = coordinatorLayout.getHeight();
                Rect rect2 = this.c;
                rect2.set(0, 0, width, height);
                if (!rect2.contains(rect)) {
                    appBarLayout.d(false, !z, true);
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final AppBarLayout u(List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final float v(View view) {
            int i;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.e) appBarLayout.getLayoutParams()).a;
                int iX = behavior instanceof BaseBehavior ? ((BaseBehavior) behavior).x() : 0;
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iX > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iX / i) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final int w(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : view.getMeasuredHeight();
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements wr1 {
        public a() {
        }

        @Override // defpackage.wr1
        public final pt3 k(View view, pt3 pt3Var) {
            AppBarLayout appBarLayout = AppBarLayout.this;
            appBarLayout.getClass();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            pt3 pt3Var2 = om3.d.a(appBarLayout) ? pt3Var : null;
            if (!Objects.equals(appBarLayout.k, pt3Var2)) {
                appBarLayout.k = pt3Var2;
                appBarLayout.setWillNotDraw(!(appBarLayout.v != null && appBarLayout.getTopInset() > 0));
                appBarLayout.requestLayout();
            }
            return pt3Var;
        }
    }

    public interface b<T extends AppBarLayout> {
        void a(int i);
    }

    public static abstract class c {
    }

    public static class d extends c {
        public final Rect a = new Rect();
        public final Rect b = new Rect();
    }

    public interface e {
        void a();
    }

    public interface f extends b<AppBarLayout> {
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public static LayoutParams a(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LinearLayout.LayoutParams ? new LayoutParams((LinearLayout.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public void addLiftOnScrollListener(e eVar) {
        this.t.add(eVar);
    }

    public void addOnOffsetChangedListener(b bVar) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        if (bVar == null || this.l.contains(bVar)) {
            return;
        }
        this.l.add(bVar);
    }

    public final void b() {
        Behavior behavior = this.w;
        BaseBehavior.SavedState savedStateE = (behavior == null || this.f == -1 || this.j != 0) ? null : behavior.E(AbsSavedState.f, this);
        this.f = -1;
        this.g = -1;
        this.h = -1;
        if (savedStateE != null) {
            Behavior behavior2 = this.w;
            if (behavior2.m != null) {
                return;
            }
            behavior2.m = savedStateE;
        }
    }

    public final void c(int i) {
        this.e = i;
        if (!willNotDraw()) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
        ArrayList arrayList = this.l;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                b bVar = (b) this.l.get(i2);
                if (bVar != null) {
                    bVar.a(i);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d(boolean z, boolean z2, boolean z3) {
        this.j = (z ? 1 : 2) | (z2 ? 4 : 0) | (z3 ? 8 : 0);
        requestLayout();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.v != null && getTopInset() > 0) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.e);
            this.v.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.v;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final boolean f(boolean z) {
        if (!(!this.m) || this.o == z) {
            return false;
        }
        this.o = z;
        refreshDrawableState();
        if (!this.p || !(getBackground() instanceof sc1)) {
            return true;
        }
        sc1 sc1Var = (sc1) getBackground();
        float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
        float f2 = z ? 0.0f : dimension;
        if (!z) {
            dimension = 0.0f;
        }
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, dimension);
        this.s = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(getResources().getInteger(R$integer.app_bar_elevation_anim_duration));
        this.s.setInterpolator(u5.a);
        this.s.addUpdateListener(new com.google.android.material.appbar.a(this, sc1Var));
        this.s.start();
        return true;
    }

    public final boolean g(View view) {
        int i;
        if (this.r == null && (i = this.q) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.q);
            }
            if (viewFindViewById != null) {
                this.r = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.r;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view = view2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        Behavior behavior = new Behavior();
        this.w = behavior;
        return behavior;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getDownNestedPreScrollRange() {
        /*
            r9 = this;
            int r0 = r9.g
            r1 = -1
            if (r0 == r1) goto L6
            return r0
        L6:
            int r0 = r9.getChildCount()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        Le:
            if (r0 < 0) goto L60
            android.view.View r3 = r9.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
            int r5 = r3.getMeasuredHeight()
            int r6 = r4.a
            r7 = r6 & 5
            r8 = 5
            if (r7 != r8) goto L5a
            int r7 = r4.topMargin
            int r4 = r4.bottomMargin
            int r7 = r7 + r4
            r4 = r6 & 8
            if (r4 == 0) goto L36
            java.util.WeakHashMap<android.view.View, in3> r4 = defpackage.om3.a
            int r4 = om3.d.c(r3)
        L34:
            int r4 = r4 + r7
            goto L45
        L36:
            r4 = r6 & 2
            if (r4 == 0) goto L43
            java.util.WeakHashMap<android.view.View, in3> r4 = defpackage.om3.a
            int r4 = om3.d.c(r3)
            int r4 = r5 - r4
            goto L34
        L43:
            int r4 = r7 + r5
        L45:
            if (r0 != 0) goto L58
            java.util.WeakHashMap<android.view.View, in3> r6 = defpackage.om3.a
            boolean r3 = om3.d.a(r3)
            if (r3 == 0) goto L58
            int r3 = r9.getTopInset()
            int r5 = r5 - r3
            int r4 = java.lang.Math.min(r4, r5)
        L58:
            int r2 = r2 + r4
            goto L5d
        L5a:
            if (r2 <= 0) goto L5d
            goto L60
        L5d:
            int r0 = r0 + (-1)
            goto Le
        L60:
            int r0 = java.lang.Math.max(r1, r2)
            r9.g = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public int getDownNestedScrollRange() {
        int i = this.h;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int iC = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + childAt.getMeasuredHeight();
            int i3 = layoutParams.a;
            if ((i3 & 1) == 0) {
                break;
            }
            iC += measuredHeight;
            if ((i3 & 2) != 0) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                iC -= om3.d.c(childAt);
                break;
            }
            i2++;
        }
        int iMax = Math.max(0, iC);
        this.h = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.q;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC = om3.d.c(this);
        if (iC == 0) {
            int childCount = getChildCount();
            iC = childCount >= 1 ? om3.d.c(getChildAt(childCount - 1)) : 0;
            if (iC == 0) {
                return getHeight() / 3;
            }
        }
        return (iC * 2) + topInset;
    }

    public int getPendingAction() {
        return this.j;
    }

    public Drawable getStatusBarForeground() {
        return this.v;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        pt3 pt3Var = this.k;
        if (pt3Var != null) {
            return pt3Var.e();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.f;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int iC = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = layoutParams.a;
            if ((i3 & 1) == 0) {
                break;
            }
            int topInset = measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + iC;
            if (i2 == 0) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.d.a(childAt)) {
                    topInset -= getTopInset();
                }
            }
            iC = topInset;
            if ((i3 & 2) != 0) {
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                iC -= om3.d.c(childAt);
                break;
            }
            i2++;
        }
        int iMax = Math.max(0, iC);
        this.f = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public final boolean h() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return !om3.d.a(childAt);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.d0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.u == null) {
            this.u = new int[4];
        }
        int[] iArr = this.u;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.n;
        int i2 = R$attr.state_liftable;
        if (!z) {
            i2 = -i2;
        }
        iArr[0] = i2;
        iArr[1] = (z && this.o) ? R$attr.state_lifted : -R$attr.state_lifted;
        int i3 = R$attr.state_collapsible;
        if (!z) {
            i3 = -i3;
        }
        iArr[2] = i3;
        iArr[3] = (z && this.o) ? R$attr.state_collapsed : -R$attr.state_collapsed;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference<View> weakReference = this.r;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.r = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z3 = true;
        if (om3.d.a(this) && h()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(topInset);
            }
        }
        b();
        this.i = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            }
            if (((LayoutParams) getChildAt(i5).getLayoutParams()).c != null) {
                this.i = true;
                break;
            }
            i5++;
        }
        Drawable drawable = this.v;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.m) {
            return;
        }
        if (!this.p) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
                    break;
                }
                int i7 = ((LayoutParams) getChildAt(i6).getLayoutParams()).a;
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
        if (this.n != z3) {
            this.n = z3;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.d.a(this) && h()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = n3.w(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        b();
    }

    public void removeOnOffsetChangedListener(b bVar) {
        ArrayList arrayList = this.l;
        if (arrayList == null || bVar == null) {
            return;
        }
        arrayList.remove(bVar);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        oj2.c0(this, f2);
    }

    public void setExpanded(boolean z) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        d(z, om3.g.c(this), true);
    }

    public void setLiftOnScroll(boolean z) {
        this.p = z;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.q = -1;
        if (view != null) {
            this.r = new WeakReference<>(view);
            return;
        }
        WeakReference<View> weakReference = this.r;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.r = null;
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.q = i;
        WeakReference<View> weakReference = this.r;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.r = null;
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.m = z;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.v;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.v = drawableMutate;
            boolean z = false;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.v.setState(getDrawableState());
                }
                Drawable drawable3 = this.v;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                drawable3.setLayoutDirection(om3.e.c(this));
                this.v.setVisible(getVisibility() == 0, false);
                this.v.setCallback(this);
            }
            if (this.v != null && getTopInset() > 0) {
                z = true;
            }
            setWillNotDraw(!z);
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.j(this);
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(AppCompatResources.a(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        go3.a(this, f2);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.v;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.v;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.appBarLayoutStyle);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Illegal instructions before constructor call */
    public AppBarLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = x;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.j = 0;
        this.t = new ArrayList();
        Context context2 = getContext();
        setOrientation(1);
        if (getOutlineProvider() == ViewOutlineProvider.BACKGROUND) {
            setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
        Context context3 = getContext();
        TypedArray typedArrayD = r83.d(context3, attributeSet, go3.a, i, i2, new int[0]);
        try {
            if (typedArrayD.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, typedArrayD.getResourceId(0, 0)));
            }
            typedArrayD.recycle();
            TypedArray typedArrayD2 = r83.d(context2, attributeSet, R$styleable.AppBarLayout, i, i2, new int[0]);
            Drawable drawable = typedArrayD2.getDrawable(R$styleable.AppBarLayout_android_background);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(this, drawable);
            if (getBackground() instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) getBackground();
                sc1 sc1Var = new sc1();
                sc1Var.n(ColorStateList.valueOf(colorDrawable.getColor()));
                sc1Var.k(context2);
                om3.d.n(this, sc1Var);
            }
            int i3 = R$styleable.AppBarLayout_expanded;
            if (typedArrayD2.hasValue(i3)) {
                d(typedArrayD2.getBoolean(i3, false), false, false);
            }
            if (typedArrayD2.hasValue(R$styleable.AppBarLayout_elevation)) {
                go3.a(this, typedArrayD2.getDimensionPixelSize(r13, 0));
            }
            int i4 = R$styleable.AppBarLayout_android_keyboardNavigationCluster;
            if (typedArrayD2.hasValue(i4)) {
                setKeyboardNavigationCluster(typedArrayD2.getBoolean(i4, false));
            }
            int i5 = R$styleable.AppBarLayout_android_touchscreenBlocksFocus;
            if (typedArrayD2.hasValue(i5)) {
                setTouchscreenBlocksFocus(typedArrayD2.getBoolean(i5, false));
            }
            this.p = typedArrayD2.getBoolean(R$styleable.AppBarLayout_liftOnScroll, false);
            this.q = typedArrayD2.getResourceId(R$styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
            setStatusBarForeground(typedArrayD2.getDrawable(R$styleable.AppBarLayout_statusBarForeground));
            typedArrayD2.recycle();
            om3.i.m(this, new a());
        } catch (Throwable th) {
            typedArrayD.recycle();
            throw th;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void removeOnOffsetChangedListener(f fVar) {
        removeOnOffsetChangedListener((b) fVar);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addOnOffsetChangedListener(f fVar) {
        addOnOffsetChangedListener((b) fVar);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public final int a;
        public c b;
        public final Interpolator c;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            this.b = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollEffect, 0) != 1 ? null : new d();
            int i = R$styleable.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                this.c = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-1, -2);
            this.a = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }
    }
}
