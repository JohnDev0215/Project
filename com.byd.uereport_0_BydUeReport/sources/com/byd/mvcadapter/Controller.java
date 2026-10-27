package com.byd.mvcadapter;

import android.os.DeadObjectException;
import android.os.Handler;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import t3.f;
import t3.g;
import t3.i;
import t3.o;

/* JADX INFO: loaded from: classes.dex */
public class Controller extends t3.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExecutorService f2750d = Executors.newCachedThreadPool(new a());

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
        public AtomicInteger f2751a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("WatchThread-" + this.f2751a.getAndIncrement());
            return thread;
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o f2753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public o.f f2754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2755c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object[] f2756d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2757e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f2758f;

        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ Object f2760d;

            public a(Object obj) {
                this.f2760d = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.f(bVar.f2753a, this.f2760d, b.this.f2755c, b.this.f2756d);
            }
        }

        public b(o oVar, o.f fVar, String str, Object[] objArr, int i8, long j8) {
            this.f2753a = oVar;
            this.f2754b = fVar;
            this.f2755c = str;
            this.f2756d = objArr;
            this.f2757e = i8;
            this.f2758f = j8;
        }

        public Object e() throws AsyncResultException, EmptyResultException {
            Object objA = this.f2754b.a();
            a aVar = null;
            if ((!this.f2754b.d() && this.f2758f > this.f2754b.c()) || objA == null) {
                throw new EmptyResultException(Controller.this, aVar);
            }
            if (!(objA instanceof g)) {
                return f(this.f2753a, objA, this.f2755c, this.f2756d);
            }
            Handler handlerA = ((g) objA).a();
            if (handlerA == null) {
                return f(this.f2753a, objA, this.f2755c, this.f2756d);
            }
            handlerA.post(new a(objA));
            throw new AsyncResultException(Controller.this, aVar);
        }

        public final Object f(o oVar, Object obj, String str, Object[] objArr) {
            Object objA;
            Future<?> futureSubmit = Controller.this.f2750d.submit(Controller.this.new c(oVar, Thread.currentThread().getName(), obj.getClass().getName(), str));
            if (objArr != null) {
                try {
                    objA = objArr.length == 0 ? RefUtils.a(obj, str, new Object[0]) : RefUtils.a(obj, str, objArr);
                } catch (RefUtils.InvocationTargetError e8) {
                    if (!(e8.getCause() instanceof DeadObjectException)) {
                        Controller.this.f8021c.b(e8);
                    }
                    objA = null;
                }
            }
            futureSubmit.cancel(true);
            return objA;
        }

        public String g() {
            return this.f2754b.b();
        }

        public int h() {
            return this.f2757e;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2762d = 5;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2763e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f2764f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public o f2765g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f2766h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public String f2767i;

        public c(o oVar, String str, String str2, String str3) {
            this.f2765g = oVar;
            this.f2764f = str;
            this.f2766h = str2;
            this.f2767i = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(this.f2762d * 1000);
                    i iVar = Controller.this.f8021c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Controller.this.g(this.f2765g));
                    sb.append("[");
                    sb.append(this.f2764f);
                    sb.append("]");
                    sb.append(this.f2766h);
                    sb.append(" ");
                    sb.append(this.f2767i);
                    sb.append(" run time: ");
                    int i8 = this.f2763e + this.f2762d;
                    this.f2763e = i8;
                    sb.append(i8);
                    sb.append("s");
                    iVar.a(sb.toString());
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a6  */
    @Override // t3.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T c(t3.j r32) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.Controller.c(t3.j):java.lang.Object");
    }

    public final String g(o oVar) {
        return oVar instanceof t3.c ? "[M]" : oVar instanceof f ? "[V]" : "[E]";
    }
}
