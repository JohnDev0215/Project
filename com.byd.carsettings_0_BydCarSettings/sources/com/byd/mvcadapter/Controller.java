package com.byd.mvcadapter;

import android.os.DeadObjectException;
import android.os.Handler;
import b.b.l.d;
import b.b.l.g;
import b.b.l.h;
import b.b.l.k;
import b.b.l.r;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class Controller extends b.b.l.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExecutorService f3660d = Executors.newCachedThreadPool(new a());

    public class AsyncResultException extends Exception {
        private static final long serialVersionUID = 1120416814665937200L;

        public AsyncResultException() {
        }

        public /* synthetic */ AsyncResultException(Controller controller, a aVar) {
            this();
        }
    }

    public class EmptyResultException extends Exception {
        private static final long serialVersionUID = 1120416814665937200L;

        public EmptyResultException() {
        }

        public /* synthetic */ EmptyResultException(Controller controller, a aVar) {
            this();
        }
    }

    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f3661a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("WatchThread-" + this.f3661a.getAndIncrement());
            return thread;
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public r f3663a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public r.g f3664b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3665c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object[] f3666d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3667e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f3668f;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Object f3669a;

            public a(Object obj) {
                this.f3669a = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.f(bVar.f3663a, this.f3669a, b.this.f3665c, b.this.f3666d);
            }
        }

        public b(r rVar, r.g gVar, String str, Object[] objArr, int i, long j) {
            this.f3663a = rVar;
            this.f3664b = gVar;
            this.f3665c = str;
            this.f3666d = objArr;
            this.f3667e = i;
            this.f3668f = j;
        }

        public Object e() throws AsyncResultException, EmptyResultException {
            Object objB = this.f3664b.b();
            a aVar = null;
            if ((!this.f3664b.f() && this.f3668f > this.f3664b.e()) || objB == null) {
                throw new EmptyResultException(Controller.this, aVar);
            }
            if (!(objB instanceof h)) {
                return f(this.f3663a, objB, this.f3665c, this.f3666d);
            }
            Handler handler = ((h) objB).getHandler();
            if (handler == null) {
                return f(this.f3663a, objB, this.f3665c, this.f3666d);
            }
            handler.post(new a(objB));
            throw new AsyncResultException(Controller.this, aVar);
        }

        public final Object f(r rVar, Object obj, String str, Object[] objArr) {
            Object objA;
            Future<?> futureSubmit = Controller.this.f3660d.submit(Controller.this.new c(rVar, Thread.currentThread().getName(), obj.getClass().getName(), str));
            if (objArr != null) {
                try {
                    objA = objArr.length == 0 ? RefUtils.a(obj, str, new Object[0]) : RefUtils.a(obj, str, objArr);
                } catch (RefUtils.InvocationTargetError e2) {
                    if (!(e2.getCause() instanceof DeadObjectException)) {
                        Controller.this.f1957c.b(e2);
                    }
                    objA = null;
                }
            }
            futureSubmit.cancel(true);
            return objA;
        }

        public String g() {
            return this.f3664b.c();
        }

        public int h() {
            return this.f3667e;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3671a = 5;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3672b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3673c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public r f3674d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3675e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f3676f;

        public c(r rVar, String str, String str2, String str3) {
            this.f3674d = rVar;
            this.f3673c = str;
            this.f3675e = str2;
            this.f3676f = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(this.f3671a * 1000);
                    k kVar = Controller.this.f1957c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Controller.this.g(this.f3674d));
                    sb.append("[");
                    sb.append(this.f3673c);
                    sb.append("]");
                    sb.append(this.f3675e);
                    sb.append(" ");
                    sb.append(this.f3676f);
                    sb.append(" run time: ");
                    int i = this.f3672b + this.f3671a;
                    this.f3672b = i;
                    sb.append(i);
                    sb.append("s");
                    kVar.a(sb.toString());
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a6  */
    @Override // b.b.l.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T c(b.b.l.l r32) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.Controller.c(b.b.l.l):java.lang.Object");
    }

    public final String g(r rVar) {
        return rVar instanceof d ? "[M]" : rVar instanceof g ? "[V]" : "[E]";
    }
}
