package com.byd.share;

import android.app.Activity;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.byd.auto_photo.R;
import com.byd.util.w;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f2908a = ViewConfiguration.getDoubleTapTimeout();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2909b = ViewConfiguration.getLongPressTimeout();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Activity f2910c;
    private WindowManager d;
    private WindowManager.LayoutParams e;
    private volatile boolean f;
    private View g;
    private Point h;
    private Point i;
    private int j;
    private int k;
    private com.byd.share.a l;
    private int m;
    private boolean n;
    private boolean o = false;
    private int p;
    private boolean q;
    private e r;

    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            w.c("share icon touch : " + motionEvent.getX() + "x" + motionEvent.getY() + " - " + d.this.g.getWidth() + "x" + d.this.g.getHeight());
            return d.this.i(motionEvent);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w.c("share icon onClick");
            if (d.this.r != null) {
                d.this.r.onShareIconClick();
            }
        }
    }

    class c implements View.OnLongClickListener {
        c() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            w.c("share icon onLongClick");
            if (d.this.r == null) {
                return true;
            }
            d.this.r.onShareIconClick();
            return true;
        }
    }

    /* JADX INFO: renamed from: com.byd.share.d$d, reason: collision with other inner class name */
    public class ViewOnTouchListenerC0081d implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private float f2914a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f2915b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private View f2916c;
        private f d;
        private boolean e;

        private ViewOnTouchListenerC0081d(View view) {
            this.f2916c = view;
            this.d = new f(this);
        }

        /* synthetic */ ViewOnTouchListenerC0081d(d dVar, View view, a aVar) {
            this(view);
        }

        private void b() {
            WindowManager.LayoutParams layoutParams;
            int i;
            int i2 = d.this.i.x / 2;
            if (d.this.e.x < i2) {
                layoutParams = d.this.e;
                i = 0;
            } else {
                if (d.this.e.x < i2) {
                    return;
                }
                layoutParams = d.this.e;
                i = d.this.i.x;
            }
            layoutParams.x = i;
            d.this.d.updateViewLayout(d.this.g, d.this.e);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
        
            if (r8 != 3) goto L39;
         */
        /* JADX WARN: Removed duplicated region for block: B:24:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b6  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
            /*
                Method dump skipped, instruction units count: 275
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.share.d.ViewOnTouchListenerC0081d.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    public interface e {
        void onShareIconClick();
    }

    private static class f extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<ViewOnTouchListenerC0081d> f2917a;

        f(ViewOnTouchListenerC0081d viewOnTouchListenerC0081d) {
            super(Looper.getMainLooper());
            this.f2917a = new WeakReference<>(viewOnTouchListenerC0081d);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2917a.get() == null) {
                removeCallbacksAndMessages(null);
                return;
            }
            int i = message.what;
            if (i == 3) {
                removeCallbacksAndMessages(null);
                this.f2917a.get().f2916c.performClick();
            } else if (i == 4) {
                removeMessages(3);
            } else {
                if (i != 5) {
                    return;
                }
                removeCallbacksAndMessages(null);
                this.f2917a.get().f2916c.performLongClick();
            }
        }
    }

    public d(Activity activity) {
        this.f2910c = activity;
        this.n = activity.getResources().getInteger(R.integer.support_share_more_screen_send) == 1;
        this.l = com.byd.share.a.s(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x >= 0 && x <= this.g.getWidth() && y >= 0 && y <= this.g.getHeight()) {
            return false;
        }
        if (!k()) {
            return true;
        }
        j();
        return true;
    }

    public void j() {
        View view = this.g;
        if (view == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.e;
        layoutParams.flags |= 8;
        this.d.updateViewLayout(view, layoutParams);
        this.q = false;
    }

    public boolean k() {
        return this.q;
    }

    public void l() {
        if (this.f) {
            this.d.removeView(this.g);
            this.f = false;
            w.c("hideWindow");
        }
    }

    public boolean m() {
        return this.f;
    }

    public void n() {
        if (this.f) {
            l();
        }
        this.r = null;
        this.l = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.e = null;
        this.f2910c = null;
        this.d = null;
    }

    public void o(e eVar) {
        this.r = eVar;
    }

    public void p(int i, int i2) {
        View view;
        View.OnTouchListener aVar;
        if (!this.n) {
            w.c("ShareIconWindow can't show");
            return;
        }
        if (!this.l.F()) {
            w.c("not support more dev share");
            this.l.J();
            return;
        }
        if (this.f) {
            w.c("share icon is showing");
            return;
        }
        this.f = true;
        if (this.d == null) {
            if (this.j == 0) {
                this.j = this.f2910c.getResources().getDimensionPixelSize(R.dimen.share_window_icon_width);
                this.k = this.f2910c.getResources().getDimensionPixelSize(R.dimen.share_window_icon_height);
            }
            this.d = (WindowManager) this.f2910c.getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.e = layoutParams;
            layoutParams.width = this.j;
            layoutParams.height = this.k;
            a aVar2 = null;
            View viewInflate = LayoutInflater.from(this.f2910c).inflate(R.layout.more_share_window, (ViewGroup) null);
            this.g = viewInflate;
            if (this.m != 0) {
                ((ImageView) viewInflate.findViewById(R.id.iv_share_icon)).setImageResource(this.m);
            }
            if (this.o) {
                view = this.g;
                aVar = new ViewOnTouchListenerC0081d(this, view, aVar2);
            } else {
                view = this.g;
                aVar = new a();
            }
            view.setOnTouchListener(aVar);
            this.g.setOnClickListener(new b());
            this.g.setOnLongClickListener(new c());
            if (this.h == null) {
                this.h = new Point();
                this.d.getDefaultDisplay().getRealSize(this.h);
                Point point = this.h;
                this.i = new Point(point.x - this.j, point.y - this.k);
            }
            if (this.o) {
                WindowManager.LayoutParams layoutParams2 = this.e;
                layoutParams2.y = ((this.h.y - this.k) / 2) + i2;
                layoutParams2.x = (this.i.x - i) - this.p;
                layoutParams2.gravity = 8388659;
                w.c("window location : " + this.e.x + " " + this.e.y + " " + this.h + " " + this.i);
            } else {
                WindowManager.LayoutParams layoutParams3 = this.e;
                layoutParams3.gravity = 8388629;
                layoutParams3.x = i + this.p;
            }
            WindowManager.LayoutParams layoutParams4 = this.e;
            layoutParams4.type = 1002;
            layoutParams4.format = 1;
            layoutParams4.flags = 262152;
        }
        WindowManager.LayoutParams layoutParams5 = this.e;
        layoutParams5.alpha = 1.0f;
        try {
            this.d.addView(this.g, layoutParams5);
            w.c("share icon show");
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f = false;
            w.c("share icon show exception : " + e2.getMessage());
        }
    }

    public void q() {
        WindowManager.LayoutParams layoutParams;
        WindowManager windowManager;
        View view = this.g;
        if (view == null || (layoutParams = this.e) == null || (windowManager = this.d) == null) {
            return;
        }
        try {
            windowManager.updateViewLayout(view, layoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean r(int i, int i2) {
        if (this.h != null && this.d != null) {
            Point point = new Point();
            this.d.getDefaultDisplay().getRealSize(point);
            w.c("updateWindowBound : " + this.h + " " + point);
            Point point2 = this.h;
            int i3 = point2.x;
            int i4 = point.x;
            if (i3 == i4 && point2.y == point.y) {
                return false;
            }
            point2.set(i4, point.y);
            Point point3 = this.h;
            Point point4 = new Point(point3.x - this.j, point3.y - this.k);
            this.i = point4;
            WindowManager.LayoutParams layoutParams = this.e;
            if (layoutParams != null) {
                if (!this.o) {
                    layoutParams.x = i + this.p;
                    return true;
                }
                layoutParams.y = ((this.h.y - this.k) / 2) + i2;
                layoutParams.x = (point4.x - i) - this.p;
                return true;
            }
        }
        return false;
    }
}
