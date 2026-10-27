package a.h.h;

import android.os.Handler;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public class k<T> implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Callable<T> f770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.h.j.a<T> f771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f772c;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a.h.j.a f773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f774b;

        public a(k kVar, a.h.j.a aVar, Object obj) {
            this.f773a = aVar;
            this.f774b = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            this.f773a.accept(this.f774b);
        }
    }

    public k(Handler handler, Callable<T> callable, a.h.j.a<T> aVar) {
        this.f770a = callable;
        this.f771b = aVar;
        this.f772c = handler;
    }

    @Override // java.lang.Runnable
    public void run() {
        T tCall;
        try {
            tCall = this.f770a.call();
        } catch (Exception unused) {
            tCall = null;
        }
        this.f772c.post(new a(this, this.f771b, tCall));
    }
}
