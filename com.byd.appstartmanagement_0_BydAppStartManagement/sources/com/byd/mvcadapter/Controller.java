package com.byd.mvcadapter;

import android.os.DeadObjectException;
import android.os.Handler;
import b.a.c.d;
import b.a.c.g;
import b.a.c.h;
import b.a.c.k;
import b.a.c.r;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class Controller extends b.a.c.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExecutorService f614d = Executors.newCachedThreadPool(new a());

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
        public AtomicInteger f615a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("WatchThread-" + this.f615a.getAndIncrement());
            return thread;
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public r f617a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public r.g f618b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f619c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object[] f620d;
        public int e;
        public long f;

        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f621b;

            public a(Object obj) {
                this.f621b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.f(bVar.f617a, this.f621b, b.this.f619c, b.this.f620d);
            }
        }

        public b(r rVar, r.g gVar, String str, Object[] objArr, int i, long j) {
            this.f617a = rVar;
            this.f618b = gVar;
            this.f619c = str;
            this.f620d = objArr;
            this.e = i;
            this.f = j;
        }

        public Object e() throws AsyncResultException, EmptyResultException {
            Object objB = this.f618b.b();
            a aVar = null;
            if ((!this.f618b.f() && this.f > this.f618b.e()) || objB == null) {
                throw new EmptyResultException(Controller.this, aVar);
            }
            if (!(objB instanceof h)) {
                return f(this.f617a, objB, this.f619c, this.f620d);
            }
            Handler handler = ((h) objB).getHandler();
            if (handler == null) {
                return f(this.f617a, objB, this.f619c, this.f620d);
            }
            handler.post(new a(objB));
            throw new AsyncResultException(Controller.this, aVar);
        }

        public final Object f(r rVar, Object obj, String str, Object[] objArr) {
            Object objA;
            Future<?> futureSubmit = Controller.this.f614d.submit(Controller.this.new c(rVar, Thread.currentThread().getName(), obj.getClass().getName(), str));
            if (objArr != null) {
                try {
                    objA = objArr.length == 0 ? RefUtils.a(obj, str, new Object[0]) : RefUtils.a(obj, str, objArr);
                } catch (RefUtils.InvocationTargetError e) {
                    if (!(e.getCause() instanceof DeadObjectException)) {
                        Controller.this.f212c.b(e);
                    }
                    objA = null;
                }
            }
            futureSubmit.cancel(true);
            return objA;
        }

        public String g() {
            return this.f618b.c();
        }

        public int h() {
            return this.e;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f623b = 5;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f624c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f625d;
        public r e;
        public String f;
        public String g;

        public c(r rVar, String str, String str2, String str3) {
            this.e = rVar;
            this.f625d = str;
            this.f = str2;
            this.g = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(this.f623b * 1000);
                    k kVar = Controller.this.f212c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Controller.this.g(this.e));
                    sb.append("[");
                    sb.append(this.f625d);
                    sb.append("]");
                    sb.append(this.f);
                    sb.append(" ");
                    sb.append(this.g);
                    sb.append(" run time: ");
                    int i = this.f624c + this.f623b;
                    this.f624c = i;
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
    @Override // b.a.c.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T c(b.a.c.l r32) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.Controller.c(b.a.c.l):java.lang.Object");
    }

    public final String g(r rVar) {
        return rVar instanceof d ? "[M]" : rVar instanceof g ? "[V]" : "[E]";
    }
}
