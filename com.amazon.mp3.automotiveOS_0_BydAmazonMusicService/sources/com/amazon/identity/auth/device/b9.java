package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b9 {
    public static final long e = wa.c(2, TimeUnit.MILLISECONDS);
    public static b9 f;
    public c c;
    public AtomicLong d = new AtomicLong(0);
    public final Executor b = new ja("MAPTokenOperationThreadPool");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Queue<c> f242a = new ArrayDeque();

    /* JADX INFO: compiled from: DCP */
    public class a extends c {
        public final ta d;

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.b9$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0053a extends TimerTask {
            public C0053a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ta taVar = a.this.d;
                synchronized (taVar) {
                    taVar.c = true;
                }
                ga.a("TokenJobQueue");
                b9 b9Var = b9.this;
                b9 b9Var2 = b9.f;
                b9Var.b();
            }
        }

        public a(d dVar, Callback callback, ta taVar) {
            super(dVar, callback);
            this.d = taVar;
            b9.this.d.set(new Date().getTime());
        }

        @Override // com.amazon.identity.auth.device.b9.c
        public void a() {
            boolean z;
            super.a();
            ga.a("TokenJobQueue");
            ta taVar = this.d;
            synchronized (taVar) {
                taVar.f584a.cancel();
                taVar.b = true;
            }
            ta taVar2 = this.d;
            synchronized (taVar2) {
                z = taVar2.c;
            }
            if (z) {
                return;
            }
            ta taVar3 = this.d;
            synchronized (taVar3) {
                taVar3.c = true;
            }
            this.b.a();
            ga.a("TokenJobQueue");
            b9.this.b();
        }

        @Override // com.amazon.identity.auth.device.b9.c
        public void b() {
            String.format("Scheduled running blocking job %s.", this.b.a());
            ga.a("TokenJobQueue");
            ta taVar = this.d;
            C0053a c0053a = new C0053a();
            long j = b9.e;
            synchronized (taVar) {
                Log.i(ga.a("TaskScheduler"), "Schedule a delayed task");
                if (taVar.b) {
                    Log.i(ga.a("TaskScheduler"), "The timer has been canceled, skip scheduling");
                } else {
                    taVar.f584a.schedule(c0053a, j);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b extends c {
        public b(d dVar, Callback callback) {
            super(dVar, callback);
        }

        @Override // com.amazon.identity.auth.device.b9.c
        public void b() {
            Log.i(ga.a("TokenJobQueue"), String.format("Scheduled running concurrent job %s.", this.b.a()));
            b9.this.b();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Callback f244a;
        public final d b;

        public c(d dVar, Callback callback) {
            this.f244a = callback;
            this.b = dVar;
        }

        public abstract void b();

        public void a() {
            Log.i(ga.a("TokenJobQueue"), String.format("Finish executing task %s.", this.b.a()));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface d {
        String a();

        void a(Callback callback);

        boolean b();
    }

    public static synchronized b9 a() {
        if (f == null) {
            f = new b9();
        }
        return f;
    }

    public final synchronized void b() {
        ga.a("TokenJobQueue");
        c cVarPoll = this.f242a.poll();
        this.c = cVarPoll;
        if (cVarPoll != null) {
            String.format("Popping task %s off TokenJobQueue and process it.", cVarPoll.b.a());
            ga.a("TokenJobQueue");
            c cVar = this.c;
            cVar.getClass();
            Log.i(ga.a("TokenJobQueue"), "Begin executing task " + cVar.b.a());
            try {
                b9.this.b.execute(new d9(cVar, new c9(cVar)));
                ga.a("TokenJobQueue");
                cVar.b();
            } catch (Throwable th) {
                ga.a("TokenJobQueue");
                cVar.b();
                throw th;
            }
        }
    }

    public synchronized void a(d dVar, Callback callback) {
        c bVar;
        try {
            String.format("Pushing task %s into TokenJobQueue.", dVar.a());
            ga.a("TokenJobQueue");
            Queue<c> queue = this.f242a;
            if (dVar.b()) {
                bVar = new a(dVar, callback, new ta());
            } else {
                bVar = new b(dVar, callback);
            }
            queue.offer(bVar);
            this.f242a.size();
            ga.a("TokenJobQueue");
        } finally {
            if (this.c == null) {
                ga.a("TokenJobQueue");
                b();
            }
        }
    }
}
