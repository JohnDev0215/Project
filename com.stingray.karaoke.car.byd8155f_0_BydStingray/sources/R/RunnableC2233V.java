package r;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import q.ViewOnTouchListenerC2144a;

/* JADX INFO: renamed from: r.V, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2233V implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ViewOnTouchListenerC2144a f21456m;

    public /* synthetic */ RunnableC2233V(ViewOnTouchListenerC2144a viewOnTouchListenerC2144a, int i7) {
        this.l = i7;
        this.f21456m = viewOnTouchListenerC2144a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ViewParent parent = this.f21456m.o.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                ViewOnTouchListenerC2144a viewOnTouchListenerC2144a = this.f21456m;
                viewOnTouchListenerC2144a.a();
                View view = viewOnTouchListenerC2144a.o;
                if (view.isEnabled() && !view.isLongClickable() && viewOnTouchListenerC2144a.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    viewOnTouchListenerC2144a.f21013r = true;
                    break;
                }
                break;
        }
    }
}
