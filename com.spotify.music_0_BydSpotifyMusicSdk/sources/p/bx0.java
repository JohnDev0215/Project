package p;

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
import com.spotify.music.R;
import com.spotify.webapi.service.models.Search;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bx0 extends FrameLayout {
    public final ArrayList c;
    public final ArrayList e;
    public View.OnApplyWindowInsetsListener k;
    public boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bx0(Context context, AttributeSet attributeSet, ox0 ox0Var) {
        View view;
        super(context, attributeSet);
        v03.j(context, "context");
        v03.j(attributeSet, "attrs");
        v03.j(ox0Var, "fm");
        this.c = new ArrayList();
        this.e = new ArrayList();
        this.l = true;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, vz1.b, 0, 0);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(0) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        ww0 ww0VarA = ox0Var.A(id);
        if (classAttribute != null && ww0VarA == null) {
            if (id == -1) {
                throw new IllegalStateException(u4.j("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
            }
            ix0 ix0VarC = ox0Var.C();
            context.getClassLoader();
            ww0 ww0VarA2 = ix0VarC.a(classAttribute);
            v03.i(ww0VarA2, "fm.fragmentFactory.insta…ontext.classLoader, name)");
            ww0VarA2.D = id;
            ww0VarA2.E = id;
            ww0VarA2.F = string;
            ww0VarA2.z = ox0Var;
            yw0 yw0Var = ox0Var.t;
            ww0VarA2.A = yw0Var;
            ww0VarA2.K = true;
            if ((yw0Var == null ? null : yw0Var.q) != null) {
                ww0VarA2.K = true;
            }
            sk skVar = new sk(ox0Var);
            skVar.o = true;
            ww0VarA2.L = this;
            int id2 = getId();
            String str = ww0VarA2.S;
            if (str != null) {
                ux0.d(ww0VarA2, str);
            }
            Class<?> cls = ww0VarA2.getClass();
            int modifiers = cls.getModifiers();
            if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
                throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
            }
            if (string != null) {
                String str2 = ww0VarA2.F;
                if (str2 != null && !string.equals(str2)) {
                    throw new IllegalStateException("Can't change tag of fragment " + ww0VarA2 + ": was " + ww0VarA2.F + " now " + string);
                }
                ww0VarA2.F = string;
            }
            if (id2 != 0) {
                if (id2 == -1) {
                    throw new IllegalArgumentException("Can't add fragment " + ww0VarA2 + " with tag " + string + " to container view with no id");
                }
                int i = ww0VarA2.D;
                if (i != 0 && i != id2) {
                    throw new IllegalStateException("Can't change container ID of fragment " + ww0VarA2 + ": was " + ww0VarA2.D + " now " + id2);
                }
                ww0VarA2.D = id2;
                ww0VarA2.E = id2;
            }
            skVar.b(new vx0(1, ww0VarA2));
            ox0 ox0Var2 = skVar.f182p;
            ww0VarA2.z = ox0Var2;
            if (skVar.g) {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
            ox0Var2.y(skVar, true);
        }
        for (androidx.fragment.app.b bVar : ox0Var.c.n()) {
            ww0 ww0Var = bVar.c;
            if (ww0Var.E == getId() && (view = ww0Var.M) != null && view.getParent() == null) {
                ww0Var.L = this;
                bVar.b();
            }
        }
    }

    public final void a(View view) {
        if (this.e.contains(view)) {
            this.c.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        v03.j(view, "child");
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof ww0 ? (ww0) tag : null) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.").toString());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        fx2 fx2VarG;
        v03.j(windowInsets, "insets");
        fx2 fx2VarG2 = fx2.g(windowInsets, null);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.k;
        if (onApplyWindowInsetsListener != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets);
            v03.i(windowInsetsOnApplyWindowInsets, "onApplyWindowInsetsListe…lyWindowInsets(v, insets)");
            fx2VarG = fx2.g(windowInsetsOnApplyWindowInsets, null);
        } else {
            WeakHashMap weakHashMap = zt2.a;
            WindowInsets windowInsetsF = fx2VarG2.f();
            if (windowInsetsF != null) {
                WindowInsets windowInsetsB = mt2.b(this, windowInsetsF);
                if (!windowInsetsB.equals(windowInsetsF)) {
                    fx2VarG2 = fx2.g(windowInsetsB, this);
                }
            }
            fx2VarG = fx2VarG2;
        }
        if (!fx2VarG.a.m()) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                WeakHashMap weakHashMap2 = zt2.a;
                WindowInsets windowInsetsF2 = fx2VarG.f();
                if (windowInsetsF2 != null) {
                    WindowInsets windowInsetsA = mt2.a(childAt, windowInsetsF2);
                    if (!windowInsetsA.equals(windowInsetsF2)) {
                        fx2.g(windowInsetsA, childAt);
                    }
                }
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        v03.j(canvas, "canvas");
        if (this.l) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                super.drawChild(canvas, (View) it.next(), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        v03.j(canvas, "canvas");
        v03.j(view, "child");
        if (this.l) {
            ArrayList arrayList = this.c;
            if ((!arrayList.isEmpty()) && arrayList.contains(view)) {
                return false;
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        v03.j(view, Search.Type.VIEW);
        this.e.remove(view);
        if (this.c.remove(view)) {
            this.l = true;
        }
        super.endViewTransition(view);
    }

    public final <F extends ww0> F getFragment() {
        zw0 zw0Var;
        ww0 ww0Var;
        ox0 ox0VarD;
        View view = this;
        while (true) {
            zw0Var = null;
            if (view == null) {
                ww0Var = null;
                break;
            }
            Object tag = view.getTag(R.id.fragment_container_view_tag);
            ww0Var = tag instanceof ww0 ? (ww0) tag : null;
            if (ww0Var != null) {
                break;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        if (ww0Var == null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                if (context instanceof zw0) {
                    zw0Var = (zw0) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (zw0Var == null) {
                throw new IllegalStateException("View " + this + " is not within a subclass of FragmentActivity.");
            }
            ox0VarD = ((yw0) zw0Var.z.e).t;
        } else {
            if (!ww0Var.m()) {
                throw new IllegalStateException("The Fragment " + ww0Var + " that owns View " + this + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
            }
            ox0VarD = ww0Var.d();
        }
        return (F) ox0VarD.A(getId());
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        v03.j(windowInsets, "insets");
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
                v03.i(childAt, Search.Type.VIEW);
                a(childAt);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        v03.j(view, Search.Type.VIEW);
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i) {
        View childAt = getChildAt(i);
        v03.i(childAt, Search.Type.VIEW);
        a(childAt);
        super.removeViewAt(i);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        v03.j(view, Search.Type.VIEW);
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i, int i2) {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            View childAt = getChildAt(i4);
            v03.i(childAt, Search.Type.VIEW);
            a(childAt);
        }
        super.removeViews(i, i2);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i, int i2) {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            View childAt = getChildAt(i4);
            v03.i(childAt, Search.Type.VIEW);
            a(childAt);
        }
        super.removeViewsInLayout(i, i2);
    }

    public final void setDrawDisappearingViewsLast(boolean z) {
        this.l = z;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        v03.j(onApplyWindowInsetsListener, "listener");
        this.k = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        v03.j(view, Search.Type.VIEW);
        if (view.getParent() == this) {
            this.e.add(view);
        }
        super.startViewTransition(view);
    }
}
