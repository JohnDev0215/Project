package e0;

import android.graphics.Typeface;
import android.os.Handler;
import e0.e;
import e0.f;

/* JADX INFO: compiled from: CallbackWithHandler.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f.c f5997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f5998b;

    /* JADX INFO: renamed from: e0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CallbackWithHandler.java */
    class RunnableC0065a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ f.c f5999e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Typeface f6000g;

        RunnableC0065a(f.c cVar, Typeface typeface) {
            this.f5999e = cVar;
            this.f6000g = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f5999e.b(this.f6000g);
        }
    }

    /* JADX INFO: compiled from: CallbackWithHandler.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ f.c f6002e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ int f6003g;

        b(f.c cVar, int i7) {
            this.f6002e = cVar;
            this.f6003g = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6002e.a(this.f6003g);
        }
    }

    a(f.c cVar, Handler handler) {
        this.f5997a = cVar;
        this.f5998b = handler;
    }

    private void a(int i7) {
        this.f5998b.post(new b(this.f5997a, i7));
    }

    private void c(Typeface typeface) {
        this.f5998b.post(new RunnableC0065a(this.f5997a, typeface));
    }

    void b(e.C0066e c0066e) {
        if (c0066e.a()) {
            c(c0066e.f6026a);
        } else {
            a(c0066e.f6027b);
        }
    }
}
