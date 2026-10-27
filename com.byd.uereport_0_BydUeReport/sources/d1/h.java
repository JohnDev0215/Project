package d1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.MultiInstanceInvalidationService;
import d1.d;
import d1.e;
import d1.g;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f4380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g.c f4381e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d1.e f4382f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Executor f4383g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d1.d f4384h = new a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f4385i = new AtomicBoolean(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ServiceConnection f4386j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Runnable f4387k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Runnable f4388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Runnable f4389m;

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class a extends d.a {

        /* JADX INFO: renamed from: d1.h$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
        public class RunnableC0027a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ String[] f4391d;

            public RunnableC0027a(String[] strArr) {
                this.f4391d = strArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                h.this.f4380d.e(this.f4391d);
            }
        }

        public a() {
        }

        @Override // d1.d
        public void A(String[] strArr) {
            h.this.f4383g.execute(new RunnableC0027a(strArr));
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            h.this.f4382f = e.a.A0(iBinder);
            h hVar = h.this;
            hVar.f4383g.execute(hVar.f4387k);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            h hVar = h.this;
            hVar.f4383g.execute(hVar.f4388l);
            h.this.f4382f = null;
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                h hVar = h.this;
                d1.e eVar = hVar.f4382f;
                if (eVar != null) {
                    hVar.f4379c = eVar.K(hVar.f4384h, hVar.f4378b);
                    h hVar2 = h.this;
                    hVar2.f4380d.a(hVar2.f4381e);
                }
            } catch (RemoteException e8) {
                Log.w("ROOM", "Cannot register multi-instance invalidation callback", e8);
            }
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f4380d.g(hVar.f4381e);
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f4380d.g(hVar.f4381e);
            try {
                h hVar2 = h.this;
                d1.e eVar = hVar2.f4382f;
                if (eVar != null) {
                    eVar.p0(hVar2.f4384h, hVar2.f4379c);
                }
            } catch (RemoteException e8) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e8);
            }
            h hVar3 = h.this;
            hVar3.f4377a.unbindService(hVar3.f4386j);
        }
    }

    /* JADX INFO: compiled from: MultiInstanceInvalidationClient.java */
    public class f extends g.c {
        public f(String[] strArr) {
            super(strArr);
        }

        @Override // d1.g.c
        public boolean a() {
            return true;
        }

        @Override // d1.g.c
        public void b(Set<String> set) {
            if (h.this.f4385i.get()) {
                return;
            }
            try {
                h hVar = h.this;
                d1.e eVar = hVar.f4382f;
                if (eVar != null) {
                    eVar.d0(hVar.f4379c, (String[]) set.toArray(new String[0]));
                }
            } catch (RemoteException e8) {
                Log.w("ROOM", "Cannot broadcast invalidation", e8);
            }
        }
    }

    public h(Context context, String str, g gVar, Executor executor) {
        b bVar = new b();
        this.f4386j = bVar;
        this.f4387k = new c();
        this.f4388l = new d();
        this.f4389m = new e();
        Context applicationContext = context.getApplicationContext();
        this.f4377a = applicationContext;
        this.f4378b = str;
        this.f4380d = gVar;
        this.f4383g = executor;
        this.f4381e = new f((String[]) gVar.f4354b.keySet().toArray(new String[0]));
        applicationContext.bindService(new Intent(applicationContext, (Class<?>) MultiInstanceInvalidationService.class), bVar, 1);
    }
}
