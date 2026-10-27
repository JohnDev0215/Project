package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ShowableListMenu;

/* JADX INFO: loaded from: classes.dex */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float e;
    public final int f;
    public final int g;
    public final View h;
    public Runnable i;
    public Runnable j;
    public boolean k;
    public int l;
    public final int[] m = new int[2];

    public class DisallowIntercept implements Runnable {
        public DisallowIntercept() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewParent parent = ForwardingListener.this.h.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public class TriggerLongPress implements Runnable {
        public TriggerLongPress() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ForwardingListener forwardingListener = ForwardingListener.this;
            forwardingListener.a();
            View view = forwardingListener.h;
            if (view.isEnabled() && !view.isLongClickable() && forwardingListener.c()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                view.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
                forwardingListener.k = true;
            }
        }
    }

    public ForwardingListener(View view) {
        this.h = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f = tapTimeout;
        this.g = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        Runnable runnable = this.j;
        View view = this.h;
        if (runnable != null) {
            view.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.i;
        if (runnable2 != null) {
            view.removeCallbacks(runnable2);
        }
    }

    public abstract ShowableListMenu b();

    public boolean c() {
        ShowableListMenu showableListMenuB = b();
        if (showableListMenuB == null || showableListMenuB.b()) {
            return true;
        }
        showableListMenuB.a();
        return true;
    }

    public boolean d() {
        ShowableListMenu showableListMenuB = b();
        if (showableListMenuB == null || !showableListMenuB.b()) {
            return true;
        }
        showableListMenuB.dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010f  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ForwardingListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.k = false;
        this.l = -1;
        Runnable runnable = this.i;
        if (runnable != null) {
            this.h.removeCallbacks(runnable);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
