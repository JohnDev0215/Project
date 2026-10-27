package b.d.a.a.m;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<V extends View> extends g<V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2000d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OverScroller f2001e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2002f;
    public int g;
    public int h;
    public int i;
    public VelocityTracker j;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CoordinatorLayout f2003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final V f2004b;

        public a(CoordinatorLayout coordinatorLayout, V v) {
            this.f2003a = coordinatorLayout;
            this.f2004b = v;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f2004b == null || (overScroller = e.this.f2001e) == null) {
                return;
            }
            if (overScroller.computeScrollOffset()) {
                e eVar = e.this;
                eVar.G(this.f2003a, this.f2004b, eVar.f2001e.getCurrY());
                this.f2004b.postOnAnimation(this);
                return;
            }
            e eVar2 = e.this;
            CoordinatorLayout coordinatorLayout = this.f2003a;
            V v = this.f2004b;
            AppBarLayout.BaseBehavior baseBehavior = (AppBarLayout.BaseBehavior) eVar2;
            if (baseBehavior == null) {
                throw null;
            }
            AppBarLayout appBarLayout = (AppBarLayout) v;
            baseBehavior.O(coordinatorLayout, appBarLayout);
            if (appBarLayout.i) {
                appBarLayout.c(appBarLayout.d(baseBehavior.K(coordinatorLayout)));
            }
        }
    }

    public e() {
        this.g = -1;
        this.i = -1;
    }

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        this.i = -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean A(androidx.coordinatorlayout.widget.CoordinatorLayout r21, V r22, android.view.MotionEvent r23) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.m.e.A(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract int E();

    public final int F(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return H(coordinatorLayout, v, E() - i, i2, i3);
    }

    public int G(CoordinatorLayout coordinatorLayout, V v, int i) {
        return H(coordinatorLayout, v, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int H(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean j(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        View view;
        int iFindPointerIndex;
        if (this.i < 0) {
            this.i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f2002f) {
            int i = this.g;
            if (i == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y - this.h) > this.i) {
                this.h = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.g = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            WeakReference<View> weakReference = ((AppBarLayout.BaseBehavior) this).q;
            boolean z = (weakReference == null || ((view = weakReference.get()) != null && view.isShown() && !view.canScrollVertically(-1))) && coordinatorLayout.j(v, x, y2);
            this.f2002f = z;
            if (z) {
                this.h = y2;
                this.g = motionEvent.getPointerId(0);
                if (this.j == null) {
                    this.j = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.f2001e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f2001e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }
}
