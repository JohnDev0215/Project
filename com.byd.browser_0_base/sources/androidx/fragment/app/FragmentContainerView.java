package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.fragment.R$id;
import androidx.fragment.R$styleable;
import defpackage.gq2;
import defpackage.hp0;
import defpackage.i21;
import defpackage.om3;
import defpackage.pt3;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: FragmentContainerView.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFragmentContainerView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FragmentContainerView.kt\nandroidx/fragment/app/FragmentContainerView\n+ 2 Context.kt\nandroidx/core/content/ContextKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,348:1\n55#2,6:349\n55#2,6:355\n1855#3,2:361\n*S KotlinDebug\n*F\n+ 1 FragmentContainerView.kt\nandroidx/fragment/app/FragmentContainerView\n*L\n113#1:349,6\n135#1:355,6\n221#1:361,2\n*E\n"})
public final class FragmentContainerView extends FrameLayout {
    public final ArrayList e;
    public final ArrayList f;
    public View.OnApplyWindowInsetsListener g;
    public boolean h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context) {
        super(context);
        i21.e(context, "context");
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.h = true;
    }

    public final void a(View view) {
        if (this.f.contains(view)) {
            this.e.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        i21.e(view, "child");
        Object tag = view.getTag(R$id.fragment_container_view_tag);
        if ((tag instanceof Fragment ? (Fragment) tag : null) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.").toString());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        pt3 pt3VarG;
        i21.e(windowInsets, "insets");
        pt3 pt3VarI = pt3.i(null, windowInsets);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.g;
        if (onApplyWindowInsetsListener != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets);
            i21.d(windowInsetsOnApplyWindowInsets, "onApplyWindowInsetsListe…lyWindowInsets(v, insets)");
            pt3VarG = pt3.i(null, windowInsetsOnApplyWindowInsets);
        } else {
            pt3VarG = om3.g(this, pt3VarI);
        }
        i21.d(pt3VarG, "if (applyWindowInsetsLis…, insetsCompat)\n        }");
        if (!pt3VarG.a.m()) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                om3.c(getChildAt(i), pt3VarG);
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        i21.e(canvas, "canvas");
        if (this.h) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                super.drawChild(canvas, (View) it.next(), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        i21.e(canvas, "canvas");
        i21.e(view, "child");
        if (this.h) {
            ArrayList arrayList = this.e;
            if ((!arrayList.isEmpty()) && arrayList.contains(view)) {
                return false;
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        i21.e(view, "view");
        this.f.remove(view);
        if (this.e.remove(view)) {
            this.h = true;
        }
        super.endViewTransition(view);
    }

    public final <F extends Fragment> F getFragment() {
        FragmentActivity fragmentActivity;
        Fragment fragment;
        FragmentManager fragmentManagerX1;
        View view = this;
        while (true) {
            fragmentActivity = null;
            if (view == null) {
                fragment = null;
                break;
            }
            Object tag = view.getTag(R$id.fragment_container_view_tag);
            fragment = tag instanceof Fragment ? (Fragment) tag : null;
            if (fragment != null) {
                break;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        if (fragment == null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                if (context instanceof FragmentActivity) {
                    fragmentActivity = (FragmentActivity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (fragmentActivity == null) {
                throw new IllegalStateException("View " + this + " is not within a subclass of FragmentActivity.");
            }
            fragmentManagerX1 = fragmentActivity.x1();
        } else {
            if (!fragment.V()) {
                throw new IllegalStateException("The Fragment " + fragment + " that owns View " + this + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
            }
            fragmentManagerX1 = fragment.J();
        }
        return (F) fragmentManagerX1.A(getId());
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        i21.e(windowInsets, "insets");
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViewsInLayout() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                super.removeAllViewsInLayout();
                return;
            } else {
                View childAt = getChildAt(childCount);
                i21.d(childAt, "view");
                a(childAt);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        i21.e(view, "view");
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i) {
        View childAt = getChildAt(i);
        i21.d(childAt, "view");
        a(childAt);
        super.removeViewAt(i);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        i21.e(view, "view");
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i, int i2) {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            View childAt = getChildAt(i4);
            i21.d(childAt, "view");
            a(childAt);
        }
        super.removeViews(i, i2);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i, int i2) {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            View childAt = getChildAt(i4);
            i21.d(childAt, "view");
            a(childAt);
        }
        super.removeViewsInLayout(i, i2);
    }

    public final void setDrawDisappearingViewsLast(boolean z) {
        this.h = z;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        i21.e(onApplyWindowInsetsListener, "listener");
        this.g = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        i21.e(view, "view");
        if (view.getParent() == this) {
            this.f.add(view);
        }
        super.startViewTransition(view);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        i21.e(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context, AttributeSet attributeSet, int i) {
        String str;
        super(context, attributeSet, i);
        i21.e(context, "context");
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.h = true;
        if (attributeSet != null) {
            String classAttribute = attributeSet.getClassAttribute();
            int[] iArr = R$styleable.FragmentContainerView;
            i21.d(iArr, "FragmentContainerView");
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
            if (classAttribute == null) {
                classAttribute = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_name);
                str = "android:name";
            } else {
                str = "class";
            }
            typedArrayObtainStyledAttributes.recycle();
            if (classAttribute == null || isInEditMode()) {
                return;
            }
            throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + str + "=\"" + classAttribute + '\"');
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context, AttributeSet attributeSet, FragmentManager fragmentManager) {
        View view;
        super(context, attributeSet);
        i21.e(context, "context");
        i21.e(attributeSet, "attrs");
        i21.e(fragmentManager, "fm");
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.h = true;
        String classAttribute = attributeSet.getClassAttribute();
        int[] iArr = R$styleable.FragmentContainerView;
        i21.d(iArr, "FragmentContainerView");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_name) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        Fragment fragmentA = fragmentManager.A(id);
        if (classAttribute != null && fragmentA == null) {
            if (id == -1) {
                throw new IllegalStateException(gq2.g("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
            }
            f fVarF = fragmentManager.F();
            context.getClassLoader();
            Fragment fragmentA2 = fVarF.a(classAttribute);
            i21.d(fragmentA2, "fm.fragmentFactory.insta…ontext.classLoader, name)");
            fragmentA2.H = true;
            hp0<?> hp0Var = fragmentA2.w;
            if ((hp0Var == null ? null : hp0Var.k) != null) {
                fragmentA2.H = true;
            }
            a aVar = new a(fragmentManager);
            aVar.p = true;
            fragmentA2.I = this;
            aVar.d(getId(), fragmentA2, string, 1);
            aVar.c();
            aVar.r.x(aVar, true);
        }
        for (h hVar : fragmentManager.c.h()) {
            Fragment fragment = hVar.c;
            if (fragment.A == getId() && (view = fragment.f12J) != null && view.getParent() == null) {
                fragment.I = this;
                hVar.b();
            }
        }
    }
}
