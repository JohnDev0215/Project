package com.byd.mvcadapter;

import android.os.DeadObjectException;
import android.os.Handler;
import c.a.b.f;
import c.a.b.g;
import c.a.b.i;
import c.a.b.o;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class Controller extends c.a.b.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExecutorService f4335d = Executors.newCachedThreadPool(new a());

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
        public AtomicInteger f4336a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("WatchThread-" + this.f4336a.getAndIncrement());
            return thread;
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o f4338a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public o.f f4339b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4340c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object[] f4341d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4342e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f4343f;

        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f4345b;

            public a(Object obj) {
                this.f4345b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.f(bVar.f4338a, this.f4345b, b.this.f4340c, b.this.f4341d);
            }
        }

        public b(o oVar, o.f fVar, String str, Object[] objArr, int i2, long j) {
            this.f4338a = oVar;
            this.f4339b = fVar;
            this.f4340c = str;
            this.f4341d = objArr;
            this.f4342e = i2;
            this.f4343f = j;
        }

        public Object e() throws AsyncResultException, EmptyResultException {
            Object objA = this.f4339b.a();
            a aVar = null;
            if ((!this.f4339b.d() && this.f4343f > this.f4339b.c()) || objA == null) {
                throw new EmptyResultException(Controller.this, aVar);
            }
            if (!(objA instanceof g)) {
                return f(this.f4338a, objA, this.f4340c, this.f4341d);
            }
            Handler handlerA = ((g) objA).a();
            if (handlerA == null) {
                return f(this.f4338a, objA, this.f4340c, this.f4341d);
            }
            handlerA.post(new a(objA));
            throw new AsyncResultException(Controller.this, aVar);
        }

        public final Object f(o oVar, Object obj, String str, Object[] objArr) {
            Object objA;
            Future<?> futureSubmit = Controller.this.f4335d.submit(Controller.this.new c(oVar, Thread.currentThread().getName(), obj.getClass().getName(), str));
            if (objArr != null) {
                try {
                    objA = objArr.length == 0 ? RefUtils.a(obj, str, new Object[0]) : RefUtils.a(obj, str, objArr);
                } catch (RefUtils.InvocationTargetError e2) {
                    if (!(e2.getCause() instanceof DeadObjectException)) {
                        Controller.this.f3452c.b(e2);
                    }
                    objA = null;
                }
            }
            futureSubmit.cancel(true);
            return objA;
        }

        public String g() {
            return this.f4339b.b();
        }

        public int h() {
            return this.f4342e;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4347b = 5;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4348c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4349d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public o f4350e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f4351f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f4352g;

        public c(o oVar, String str, String str2, String str3) {
            this.f4350e = oVar;
            this.f4349d = str;
            this.f4351f = str2;
            this.f4352g = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(this.f4347b * 1000);
                    i iVar = Controller.this.f3452c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Controller.this.g(this.f4350e));
                    sb.append("[");
                    sb.append(this.f4349d);
                    sb.append("]");
                    sb.append(this.f4351f);
                    sb.append(" ");
                    sb.append(this.f4352g);
                    sb.append(" run time: ");
                    int i2 = this.f4348c + this.f4347b;
                    this.f4348c = i2;
                    sb.append(i2);
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
    @Override // c.a.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T c(c.a.b.j r32) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.Controller.c(c.a.b.j):java.lang.Object");
    }

    public final String g(o oVar) {
        return oVar instanceof c.a.b.c ? "[M]" : oVar instanceof f ? "[V]" : "[E]";
    }
}
