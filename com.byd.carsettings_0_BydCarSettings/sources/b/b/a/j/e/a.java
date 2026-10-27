package b.b.a.j.e;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: ScrollerHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.a.j.e.b f552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f556e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f557f;
    public Runnable g;
    public Runnable h;
    public Runnable i;
    public Runnable j;
    public Runnable k;

    /* JADX INFO: renamed from: b.b.a.j.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class RunnableC0024a implements Runnable {
        public RunnableC0024a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.a.h.a.a(this, "moveUpRunnable");
            if (a.this.f552a != null) {
                a.this.f552a.g(a.this.f553b);
            }
            a.this.postDelayed(this, 300L);
        }
    }

    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.a.h.a.a(this, "moveDownRunnable");
            if (a.this.f552a != null) {
                a.this.f552a.j(a.this.f554c);
            }
            a.this.postDelayed(this, 300L);
        }
    }

    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.a.h.a.a(this, "moveLeftRunnable");
            if (a.this.f552a != null) {
                a.this.f552a.k(a.this.f555d);
            }
            a.this.postDelayed(this, 300L);
        }
    }

    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.a.h.a.a(this, "moveRightRunnable");
            if (a.this.f552a != null) {
                a.this.f552a.b(a.this.f556e);
            }
            a.this.postDelayed(this, 300L);
        }
    }

    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.a.h.a.a(this, "moveInvalidRunnable");
            if (a.this.f552a != null) {
                a.this.f552a.d(a.this.f557f);
            }
        }
    }

    public a(Looper looper) {
        super(looper);
        this.g = new RunnableC0024a();
        this.h = new b();
        this.i = new c();
        this.j = new d();
        this.k = new e();
    }

    public void g(int i) {
        this.f554c = i;
        b.b.a.h.a.a(this, "moveDown");
        post(this.h);
    }

    public void h(int i) {
        this.f557f = i;
        b.b.a.h.a.a(this, "moveInvalid");
        removeCallbacksAndMessages(null);
        post(this.k);
    }

    public void i(int i) {
        this.f555d = i;
        b.b.a.h.a.a(this, "moveLeft");
        post(this.i);
    }

    public void j(int i) {
        this.f556e = i;
        b.b.a.h.a.a(this, "moveRight");
        post(this.j);
    }

    public void k(int i) {
        this.f553b = i;
        b.b.a.h.a.a(this, "moveUp");
        post(this.g);
    }

    public void l(b.b.a.j.e.b bVar) {
        this.f552a = bVar;
    }
}
