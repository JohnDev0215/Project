package p;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class mw0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ nw0 e;

    public /* synthetic */ mw0(nw0 nw0Var, int i) {
        this.c = i;
        this.e = nw0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        nw0 nw0Var = this.e;
        switch (i) {
            case 0:
                ViewParent parent = nw0Var.l.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                nw0Var.a();
                View view = nw0Var.l;
                if (view.isEnabled() && !view.isLongClickable() && nw0Var.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    nw0Var.o = true;
                    break;
                }
                break;
        }
    }
}
