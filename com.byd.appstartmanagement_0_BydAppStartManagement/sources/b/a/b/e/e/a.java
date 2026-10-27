package b.a.b.e.e;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: ScrollerHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.a.b.e.e.b f188a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f189b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f190c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f191d;
    public int e;
    public int f;
    public Runnable g;
    public Runnable h;
    public Runnable i;
    public Runnable j;
    public Runnable k;

    /* JADX INFO: renamed from: b.a.b.e.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ScrollerHandler.java */
    public class RunnableC0019a implements Runnable {
        public RunnableC0019a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.a.b.c.a.a(this, "moveUpRunnable");
            if (a.this.f188a != null) {
                a.this.f188a.c(a.this.f189b);
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
            b.a.b.c.a.a(this, "moveDownRunnable");
            if (a.this.f188a != null) {
                a.this.f188a.d(a.this.f190c);
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
            b.a.b.c.a.a(this, "moveLeftRunnable");
            if (a.this.f188a != null) {
                a.this.f188a.e(a.this.f191d);
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
            b.a.b.c.a.a(this, "moveRightRunnable");
            if (a.this.f188a != null) {
                a.this.f188a.a(a.this.e);
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
            b.a.b.c.a.a(this, "moveInvalidRunnable");
            if (a.this.f188a != null) {
                a.this.f188a.b(a.this.f);
            }
        }
    }

    public a(Looper looper) {
        super(looper);
        this.g = new RunnableC0019a();
        this.h = new b();
        this.i = new c();
        this.j = new d();
        this.k = new e();
    }

    public void g(int i) {
        this.f190c = i;
        b.a.b.c.a.a(this, "moveDown");
        post(this.h);
    }

    public void h(int i) {
        this.f = i;
        b.a.b.c.a.a(this, "moveInvalid");
        removeCallbacksAndMessages(null);
        post(this.k);
    }

    public void i(int i) {
        this.f191d = i;
        b.a.b.c.a.a(this, "moveLeft");
        post(this.i);
    }

    public void j(int i) {
        this.e = i;
        b.a.b.c.a.a(this, "moveRight");
        post(this.j);
    }

    public void k(int i) {
        this.f189b = i;
        b.a.b.c.a.a(this, "moveUp");
        post(this.g);
    }

    public void l(b.a.b.e.e.b bVar) {
        this.f188a = bVar;
    }
}
