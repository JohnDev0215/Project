package a.l.d;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<View> f1011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<View> f1012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1013c;

    public l(Context context, AttributeSet attributeSet, r rVar) {
        super(context, attributeSet);
        this.f1013c = true;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.c.FragmentContainerView);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(a.l.c.FragmentContainerView_android_name) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(a.l.c.FragmentContainerView_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        Fragment fragmentE = rVar.E(id);
        if (classAttribute == null || fragmentE != null) {
            return;
        }
        if (id <= 0) {
            throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + classAttribute + (string != null ? b.a.a.a.a.c(" with tag ", string) : ""));
        }
        Fragment fragmentA = rVar.H().a(context.getClassLoader(), classAttribute);
        fragmentA.A(attributeSet, null);
        a aVar = new a(rVar);
        aVar.o = true;
        fragmentA.E = this;
        aVar.c(getId(), fragmentA, string, 1);
        if (aVar.g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        r rVar2 = aVar.q;
        if (rVar2.n == null || rVar2.w) {
            return;
        }
        rVar2.z(true);
        if (aVar.a(rVar2.y, rVar2.z)) {
            rVar2.f1026b = true;
            try {
                rVar2.U(rVar2.y, rVar2.z);
            } finally {
                rVar2.f();
            }
        }
        rVar2.e0();
        rVar2.w();
        rVar2.f1027c.b();
    }

    public final void a(View view) {
        ArrayList<View> arrayList;
        if (view.getAnimation() != null || ((arrayList = this.f1012b) != null && arrayList.contains(view))) {
            if (this.f1011a == null) {
                this.f1011a = new ArrayList<>();
            }
            this.f1011a.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Object tag = view.getTag(a.l.b.fragment_container_view_tag);
        if ((tag instanceof Fragment ? (Fragment) tag : null) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup
    public boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        Object tag = view.getTag(a.l.b.fragment_container_view_tag);
        if ((tag instanceof Fragment ? (Fragment) tag : null) != null) {
            return super.addViewInLayout(view, i, layoutParams, z);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.f1013c && this.f1011a != null) {
            for (int i = 0; i < this.f1011a.size(); i++) {
                super.drawChild(canvas, this.f1011a.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        ArrayList<View> arrayList;
        if (!this.f1013c || (arrayList = this.f1011a) == null || arrayList.size() <= 0 || !this.f1011a.contains(view)) {
            return super.drawChild(canvas, view, j);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        ArrayList<View> arrayList = this.f1012b;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList<View> arrayList2 = this.f1011a;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.f1013c = true;
            }
        }
        super.endViewTransition(view);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(windowInsets));
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z) {
        if (z) {
            a(view);
        }
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        a(getChildAt(i));
        super.removeViewAt(i);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViews(i, i2);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViewsInLayout(i, i2);
    }

    public void setDrawDisappearingViewsLast(boolean z) {
        this.f1013c = z;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.f1012b == null) {
                this.f1012b = new ArrayList<>();
            }
            this.f1012b.add(view);
        }
        super.startViewTransition(view);
    }
}
