package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.d;
import androidx.room.e;
import androidx.room.g;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f1339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1341c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final g f1342d;
    final g.c e;
    androidx.room.e f;
    final Executor g;
    final androidx.room.d h = new a();
    final AtomicBoolean i = new AtomicBoolean(false);
    final ServiceConnection j;
    final Runnable k;
    final Runnable l;
    private final Runnable m;

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class a extends d.a {

        /* JADX INFO: renamed from: androidx.room.h$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
        class RunnableC0026a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String[] f1344b;

            RunnableC0026a(String[] strArr) {
                this.f1344b = strArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                h.this.f1342d.e(this.f1344b);
            }
        }

        a() {
        }

        @Override // androidx.room.d
        public void f(String[] strArr) {
            h.this.g.execute(new RunnableC0026a(strArr));
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class b implements ServiceConnection {
        b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            h.this.f = e.a.j(iBinder);
            h hVar = h.this;
            hVar.g.execute(hVar.k);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            h hVar = h.this;
            hVar.g.execute(hVar.l);
            h.this.f = null;
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                h hVar = h.this;
                androidx.room.e eVar = hVar.f;
                if (eVar != null) {
                    hVar.f1341c = eVar.c(hVar.h, hVar.f1340b);
                    h hVar2 = h.this;
                    hVar2.f1342d.a(hVar2.e);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
            }
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f1342d.g(hVar.e);
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f1342d.g(hVar.e);
            try {
                h hVar2 = h.this;
                androidx.room.e eVar = hVar2.f;
                if (eVar != null) {
                    eVar.h(hVar2.h, hVar2.f1341c);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
            }
            h hVar3 = h.this;
            hVar3.f1339a.unbindService(hVar3.j);
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    class f extends g.c {
        f(String[] strArr) {
            super(strArr);
        }

        @Override // androidx.room.g.c
        boolean a() {
            return true;
        }

        @Override // androidx.room.g.c
        public void b(Set<String> set) {
            if (h.this.i.get()) {
                return;
            }
            try {
                h hVar = h.this;
                androidx.room.e eVar = hVar.f;
                if (eVar != null) {
                    eVar.e(hVar.f1341c, (String[]) set.toArray(new String[0]));
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot broadcast invalidation", e);
            }
        }
    }

    h(Context context, String str, g gVar, Executor executor) {
        b bVar = new b();
        this.j = bVar;
        this.k = new c();
        this.l = new d();
        this.m = new e();
        Context applicationContext = context.getApplicationContext();
        this.f1339a = applicationContext;
        this.f1340b = str;
        this.f1342d = gVar;
        this.g = executor;
        this.e = new f((String[]) gVar.f1326b.keySet().toArray(new String[0]));
        applicationContext.bindService(new Intent(applicationContext, (Class<?>) MultiInstanceInvalidationService.class), bVar, 1);
    }
}
