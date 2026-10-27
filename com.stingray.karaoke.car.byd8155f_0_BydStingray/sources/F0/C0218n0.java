package F0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.HashMap;

/* JADX INFO: renamed from: F0.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0218n0 extends ViewGroup {
    public final HashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap f2231m;

    public C0218n0(Context context) {
        super(context);
        setClipChildren(false);
        this.l = new HashMap();
        this.f2231m = new HashMap();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public final HashMap<d1.i, E0.F> getHolderToLayoutNode() {
        return this.l;
    }

    public final HashMap<E0.F, d1.i> getLayoutNodeToHolder() {
        return this.f2231m;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final /* bridge */ /* synthetic */ ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onDescendantInvalidated(View view, View view2) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        for (d1.i iVar : this.l.keySet()) {
            iVar.layout(iVar.getLeft(), iVar.getTop(), iVar.getRight(), iVar.getBottom());
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        int i11;
        if (!(View.MeasureSpec.getMode(i7) == 1073741824)) {
            l9.d.g0("widthMeasureSpec should be EXACTLY");
            throw null;
        }
        if (!(View.MeasureSpec.getMode(i10) == 1073741824)) {
            l9.d.g0("heightMeasureSpec should be EXACTLY");
            throw null;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i7), View.MeasureSpec.getSize(i10));
        for (d1.i iVar : this.l.keySet()) {
            int i12 = iVar.f13296C;
            if (i12 != Integer.MIN_VALUE && (i11 = iVar.f13297D) != Integer.MIN_VALUE) {
                iVar.measure(i12, i11);
            }
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        cleanupLayoutState(this);
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            E0.F f10 = (E0.F) this.l.get(childAt);
            if (childAt.isLayoutRequested() && f10 != null) {
                E0.F.s0(f10, false, 7);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
