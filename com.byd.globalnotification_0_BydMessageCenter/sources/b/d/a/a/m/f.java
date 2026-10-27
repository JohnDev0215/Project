package b.d.a.a.m;

import a.b.k.l;
import a.h.k.q;
import a.h.k.w;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends g<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f2006d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f2007e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2008f;
    public int g;

    public f() {
        this.f2006d = new Rect();
        this.f2007e = new Rect();
        this.f2008f = 0;
    }

    public f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2006d = new Rect();
        this.f2007e = new Rect();
        this.f2008f = 0;
    }

    @Override // b.d.a.a.m.g
    public void C(CoordinatorLayout coordinatorLayout, View view, int i) {
        int bottom;
        AppBarLayout appBarLayoutF = ((AppBarLayout.ScrollingViewBehavior) this).F(coordinatorLayout.d(view));
        if (appBarLayoutF != null) {
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
            Rect rect = this.f2006d;
            rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, appBarLayoutF.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((appBarLayoutF.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
            w lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && q.m(coordinatorLayout) && !view.getFitsSystemWindows()) {
                rect.left = lastWindowInsets.b() + rect.left;
                rect.right -= lastWindowInsets.c();
            }
            Rect rect2 = this.f2007e;
            int i2 = fVar.f1516c;
            if (i2 == 0) {
                i2 = 8388659;
            }
            Gravity.apply(i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            int iE = E(appBarLayoutF);
            view.layout(rect2.left, rect2.top - iE, rect2.right, rect2.bottom - iE);
            bottom = rect2.top - appBarLayoutF.getBottom();
        } else {
            coordinatorLayout.q(view, i);
            bottom = 0;
        }
        this.f2008f = bottom;
    }

    public final int E(View view) {
        int i;
        if (this.g == 0) {
            return 0;
        }
        float f2 = 0.0f;
        if (view instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            CoordinatorLayout.c cVar = ((CoordinatorLayout.f) appBarLayout.getLayoutParams()).f1514a;
            int iE = cVar instanceof AppBarLayout.BaseBehavior ? ((AppBarLayout.BaseBehavior) cVar).E() : 0;
            if ((downNestedPreScrollRange == 0 || totalScrollRange + iE > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                f2 = 1.0f + (iE / i);
            }
        }
        int i2 = this.g;
        return l.i.k((int) (f2 * i2), 0, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        w lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if (i5 != -1 && i5 != -2) {
            return false;
        }
        AppBarLayout appBarLayoutF = ((AppBarLayout.ScrollingViewBehavior) this).F(coordinatorLayout.d(view));
        if (appBarLayoutF == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (q.m(appBarLayoutF) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.a() + lastWindowInsets.d();
        }
        coordinatorLayout.r(view, i, i2, View.MeasureSpec.makeMeasureSpec((size + appBarLayoutF.getTotalScrollRange()) - appBarLayoutF.getMeasuredHeight(), i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
        return true;
    }
}
