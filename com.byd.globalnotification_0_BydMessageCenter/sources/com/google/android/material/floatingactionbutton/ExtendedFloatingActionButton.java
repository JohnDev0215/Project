package com.google.android.material.floatingactionbutton;

import a.h.k.q;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.k;
import b.d.a.a.l.g;
import b.d.a.a.y.h;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    public final h s;
    public final h t;
    public final h u;
    public final h v;
    public boolean w;
    public boolean x;

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f2434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2435b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2436c;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2435b = false;
            this.f2436c = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.ExtendedFloatingActionButton_Behavior_Layout);
            this.f2435b = typedArrayObtainStyledAttributes.getBoolean(k.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f2436c = typedArrayObtainStyledAttributes.getBoolean(k.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public final boolean B(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f2435b || this.f2436c) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).f1519f == view.getId();
        }

        public final boolean C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!B(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f2434a == null) {
                this.f2434a = new Rect();
            }
            Rect rect = this.f2434a;
            b.d.a.a.z.d.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                ExtendedFloatingActionButton.i(extendedFloatingActionButton, this.f2436c ? extendedFloatingActionButton.s : extendedFloatingActionButton.v);
                return true;
            }
            ExtendedFloatingActionButton.i(extendedFloatingActionButton, this.f2436c ? extendedFloatingActionButton.t : extendedFloatingActionButton.u);
            return true;
        }

        public final boolean D(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!B(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                ExtendedFloatingActionButton.i(extendedFloatingActionButton, this.f2436c ? extendedFloatingActionButton.s : extendedFloatingActionButton.v);
                return true;
            }
            ExtendedFloatingActionButton.i(extendedFloatingActionButton, this.f2436c ? extendedFloatingActionButton.t : extendedFloatingActionButton.u);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void f(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean g(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                C(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.f ? ((CoordinatorLayout.f) layoutParams).f1514a instanceof BottomSheetBehavior : false) {
                    D(view2, extendedFloatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean k(CoordinatorLayout coordinatorLayout, View view, int i) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            List<View> listD = coordinatorLayout.d(extendedFloatingActionButton);
            int size = listD.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = listD.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.f ? ((CoordinatorLayout.f) layoutParams).f1514a instanceof BottomSheetBehavior : false) && D(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (C(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.q(extendedFloatingActionButton, i);
            return true;
        }
    }

    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        public void set(View view, Float f2) {
            View view2 = view;
            view2.getLayoutParams().width = f2.intValue();
            view2.requestLayout();
        }
    }

    public static class b extends Property<View, Float> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        public void set(View view, Float f2) {
            View view2 = view;
            view2.getLayoutParams().height = f2.intValue();
            view2.requestLayout();
        }
    }

    public static class c extends Property<View, Float> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(q.t(view));
        }

        @Override // android.util.Property
        public void set(View view, Float f2) {
            View view2 = view;
            view2.setPaddingRelative(f2.intValue(), view2.getPaddingTop(), q.s(view2), view2.getPaddingBottom());
        }
    }

    public static class d extends Property<View, Float> {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(q.s(view));
        }

        @Override // android.util.Property
        public void set(View view, Float f2) {
            View view2 = view;
            view2.setPaddingRelative(q.t(view2), view2.getPaddingTop(), f2.intValue(), view2.getPaddingBottom());
        }
    }

    public static abstract class e {
    }

    static {
        new a(Float.class, "width");
        new b(Float.class, "height");
        new c(Float.class, "paddingStart");
        new d(Float.class, "paddingEnd");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void i(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r4, b.d.a.a.y.h r5) {
        /*
            r0 = 0
            if (r4 == 0) goto L5d
            boolean r1 = r5.c()
            if (r1 == 0) goto La
            goto L5c
        La:
            boolean r1 = a.h.k.q.C(r4)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L21
            int r1 = r4.getVisibility()
            if (r1 == 0) goto L1a
            r1 = r3
            goto L1b
        L1a:
            r1 = r2
        L1b:
            if (r1 != 0) goto L28
            boolean r1 = r4.x
            if (r1 == 0) goto L28
        L21:
            boolean r1 = r4.isInEditMode()
            if (r1 != 0) goto L28
            goto L29
        L28:
            r2 = r3
        L29:
            if (r2 != 0) goto L32
            r5.g()
            r5.a(r0)
            goto L5c
        L32:
            r4.measure(r3, r3)
            android.animation.AnimatorSet r0 = r5.b()
            b.d.a.a.y.a r1 = new b.d.a.a.y.a
            r1.<init>(r4, r5)
            r0.addListener(r1)
            java.util.List r4 = r5.f()
            java.util.Iterator r4 = r4.iterator()
        L49:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L59
            java.lang.Object r5 = r4.next()
            android.animation.Animator$AnimatorListener r5 = (android.animation.Animator.AnimatorListener) r5
            r0.addListener(r5)
            goto L49
        L59:
            r0.start()
        L5c:
            return
        L5d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.i(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton, b.d.a.a.y.h):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return null;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        return 0;
    }

    public g getExtendMotionSpec() {
        throw null;
    }

    public g getHideMotionSpec() {
        throw null;
    }

    public g getShowMotionSpec() {
        throw null;
    }

    public g getShrinkMotionSpec() {
        throw null;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.w && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.w = false;
            throw null;
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.x = z;
    }

    public void setExtendMotionSpec(g gVar) {
        throw null;
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(g.b(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.w == z) {
            return;
        }
        h hVar = null;
        if (hVar.c()) {
            return;
        }
        hVar.g();
    }

    public void setHideMotionSpec(g gVar) {
        throw null;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(g.b(getContext(), i));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (this.w) {
            q.t(this);
            getPaddingEnd();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        boolean z = this.w;
    }

    public void setShowMotionSpec(g gVar) {
        throw null;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(g.b(getContext(), i));
    }

    public void setShrinkMotionSpec(g gVar) {
        throw null;
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(g.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        getTextColors();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        getTextColors();
    }
}
