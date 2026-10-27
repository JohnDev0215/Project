package b.a.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import b.a.c.f;
import b.a.c.q;
import b.a.c.r;
import com.byd.mvcadapter.RemoteModelService;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: IModel.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends r {
    public HashMap<String, String> i = new HashMap<>();
    public ArrayList<j> j = new ArrayList<>();
    public h k = new a();
    public e l = new b();
    public ArrayList<C0020d> m = new ArrayList<>();

    /* JADX INFO: compiled from: IModel.java */
    public class a implements h {
        public a() {
        }

        @Override // b.a.c.d.h
        public void a(ComponentName componentName) {
            if (componentName != null) {
                d.this.C(null, componentName.getPackageName(), componentName.getClassName(), false);
            }
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public class b implements e {
        public b() {
        }

        @Override // b.a.c.d.e
        public void a(int i, ComponentName componentName, IInterface iInterface) {
            d.this.x(i, componentName, iInterface);
        }

        @Override // b.a.c.d.e
        public void b(int i, ComponentName componentName, IInterface iInterface) {
            d.this.Q(i, componentName, iInterface);
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Context f215b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public PackageManager f216c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Handler f217d;
        public Intent e;
        public j f;
        public int g;

        public c(Context context, Handler handler, Intent intent, j jVar) {
            this.f215b = context;
            this.f216c = context.getPackageManager();
            this.f217d = handler;
            this.e = intent;
            this.f = jVar;
        }

        public void a() {
            this.g = 10;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (d.this.N(this.f216c, this.e)) {
                    this.f215b.bindService(this.e, this.f, 1);
                } else {
                    int i = this.g;
                    if (i > 0) {
                        this.g = i - 1;
                        d.this.f259a.d("BindRunnable intent: " + this.e.toString() + " not exist, retry after 1000 ms");
                        this.f217d.postDelayed(this, 1000L);
                    } else {
                        d.this.f259a.d("BindRunnable intent: " + this.e.toString() + " not exist, abandon bind");
                    }
                }
            } catch (RuntimeException e) {
                d.this.f259a.a("BindRunnable intent " + this.e.toString() + " error");
                d.this.f259a.b(e);
            }
        }
    }

    /* JADX INFO: renamed from: b.a.c.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IModel.java */
    public static class C0020d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f218a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ComponentName f219b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public IInterface f220c;

        public C0020d(int i, ComponentName componentName, IInterface iInterface) {
            this.f218a = i;
            this.f219b = componentName;
            this.f220c = iInterface;
        }

        public String d() {
            String packageName = this.f219b.getPackageName();
            String className = this.f219b.getClassName();
            if (className.startsWith(packageName)) {
                className = className.replaceFirst(packageName, "");
            }
            return packageName + "/" + className;
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public interface e {
        void a(int i, ComponentName componentName, IInterface iInterface);

        void b(int i, ComponentName componentName, IInterface iInterface);
    }

    /* JADX INFO: compiled from: IModel.java */
    public interface f {
        void connected(ComponentName componentName, IInterface iInterface);

        void disconnected(ComponentName componentName, IInterface iInterface);
    }

    /* JADX INFO: compiled from: IModel.java */
    public static class g extends r.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Handler f221b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c f222c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public f f223d;
        public f e;
        public boolean f = true;
        public long g = 0;
        public int h = 0;
        public long i = s.a();
        public j j;
        public IBinder k;

        public long b() {
            return this.i;
        }

        public int c() {
            return this.h;
        }

        public g d(f fVar) {
            String className = Thread.currentThread().getStackTrace()[3].getClassName();
            d.R(this.f221b, this.f222c, 0L);
            if ("com.byd.mvcadapter.rpc.RpcRequest".equals(className)) {
                this.f223d = fVar;
            } else {
                this.e = fVar;
            }
            return this;
        }

        public void e(j jVar) {
            this.j = jVar;
        }

        @Override // b.a.c.r.e
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public g a(r.c cVar) {
            d.R(this.f221b, this.f222c, 0L);
            return (g) super.a(cVar);
        }

        public void g(int i) {
            this.h = i;
        }

        public void h(IBinder iBinder) {
            this.k = iBinder;
        }

        public void i() {
            j jVar = this.j;
            if (jVar != null) {
                this.j.onServiceConnected(new ComponentName(jVar.c(), this.j.b()), this.k);
            }
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public interface h {
        void a(ComponentName componentName);
    }

    /* JADX INFO: compiled from: IModel.java */
    public static class i implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ComponentName f224a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f225b;

        public i(ComponentName componentName, h hVar) {
            this.f224a = componentName;
            this.f225b = hVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            h hVar = this.f225b;
            if (hVar != null) {
                hVar.a(this.f224a);
            }
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public static class j implements ServiceConnection {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f226b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f227c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f228d;
        public d e;
        public ComponentName f;
        public h g;
        public g h;
        public e i;
        public k j;
        public int k;
        public b.a.c.f l;
        public o m;
        public boolean n = false;

        public j(String str, String str2, String str3, d dVar, h hVar, g gVar, e eVar, k kVar) {
            this.f226b = str;
            this.f227c = str2;
            this.f228d = str3;
            this.e = dVar;
            this.g = hVar;
            this.h = gVar;
            this.i = eVar;
            this.j = kVar;
        }

        public String b() {
            return this.f227c;
        }

        public String c() {
            return this.f226b;
        }

        public int d() {
            return this.k;
        }

        public b.a.c.f e() {
            return this.l;
        }

        public void f(boolean z) {
            this.n = z;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            f fVar;
            f fVar2;
            this.j.d("onServiceConnected: " + componentName);
            this.f = componentName;
            b.a.c.f fVarB = f.a.B(iBinder);
            this.l = fVarB;
            try {
                this.k = fVarB.v();
            } catch (RemoteException e) {
                this.j.b(e);
            }
            IInterface iInterfaceE = this.e.E(this.k, componentName);
            if (iInterfaceE != null) {
                this.l = (b.a.c.f) iInterfaceE;
            }
            this.m = new o(this.e);
            b.a.c.f fVar3 = this.l;
            if (fVar3 != null) {
                r.e eVarP = this.e.P(fVar3, q.a.class);
                g gVar = this.h;
                eVarP.a(gVar != null ? gVar.f271a : null);
                try {
                    b.a.c.f fVar4 = this.l;
                    o oVar = this.m;
                    String str = this.f228d;
                    int iMyPid = Process.myPid();
                    g gVar2 = this.h;
                    fVar4.q(oVar, str, iMyPid, gVar2 != null ? gVar2.b() : 0L);
                    g gVar3 = this.h;
                    if (gVar3 != null) {
                        gVar3.g(this.k);
                    }
                    this.l.asBinder().linkToDeath(new i(componentName, this.g), 0);
                    e eVar = this.i;
                    if (eVar != null) {
                        eVar.a(this.k, componentName, this.l);
                    }
                } catch (RemoteException e2) {
                    this.j.b(e2);
                }
            }
            g gVar4 = this.h;
            if (gVar4 != null && (fVar2 = gVar4.f223d) != null) {
                fVar2.connected(componentName, this.l);
            }
            g gVar5 = this.h;
            if (gVar5 == null || (fVar = gVar5.e) == null) {
                return;
            }
            fVar.connected(componentName, this.l);
        }

        @Override // android.content.ServiceConnection
        public synchronized void onServiceDisconnected(ComponentName componentName) {
            if (this.l != null) {
                this.j.d("onServiceDisconnected: " + componentName);
                this.e.S(this.l);
                try {
                    this.l.r(this.m);
                } catch (RemoteException unused) {
                }
                g gVar = this.h;
                if (gVar != null) {
                    gVar.g(0);
                    f fVar = this.h.f223d;
                    if (fVar != null) {
                        fVar.disconnected(componentName, this.l);
                    }
                    f fVar2 = this.h.e;
                    if (fVar2 != null) {
                        fVar2.disconnected(componentName, this.l);
                    }
                    g gVar2 = this.h;
                    if (gVar2.f && !this.n) {
                        d.R(gVar2.f221b, gVar2.f222c, gVar2.g);
                    }
                    this.n = false;
                }
                e eVar = this.i;
                if (eVar != null) {
                    eVar.b(this.k, componentName, this.l);
                }
                this.l = null;
            }
        }

        public String toString() {
            return this.k + "/" + this.f226b + "/" + this.f227c + "/" + this.f228d + "/" + this.l;
        }
    }

    public static void R(Handler handler, c cVar, long j2) {
        if (handler == null || cVar == null) {
            return;
        }
        handler.removeCallbacks(cVar);
        cVar.a();
        handler.postDelayed(cVar, j2);
    }

    public g A(Context context, String str, String str2) {
        this.f259a.d("connectSystemService: " + str + ", client: " + str2);
        g gVarH = H();
        StringBuilder sb = new StringBuilder();
        sb.append("SystemService_");
        sb.append(str);
        j jVar = new j("SystemService", sb.toString(), str2, this, this.k, gVarH, this.l, this.f259a);
        this.j.add(jVar);
        IBinder iBinder = (IBinder) RefUtils.b(RefUtils.c("android.os.ServiceManager", null), "getService", str);
        gVarH.e(jVar);
        gVarH.h(iBinder);
        return gVarH;
    }

    public void B(Context context, String str) {
        C(context, str, this.i.get(str), true);
    }

    public final void C(Context context, String str, String str2, boolean z) {
        if (z) {
            this.f259a.d("disconnect: " + str);
        }
        j jVarI = I(str, str2);
        if (jVarI != null) {
            if (z) {
                this.j.remove(jVarI);
            }
            jVarI.f(z);
            jVarI.onServiceDisconnected(jVarI.f);
            if (context == null || jVarI.e() == null) {
                return;
            }
            context.unbindService(jVarI);
        }
    }

    public final String D(Context context, String str) {
        ServiceInfo[] serviceInfoArr;
        String name = RemoteModelService.class.getName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 4);
            if (packageInfo != null && (serviceInfoArr = packageInfo.services) != null) {
                ClassLoader classLoader = context.createPackageContext(str, 3).getClassLoader();
                boolean z = false;
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    String str2 = serviceInfo.name;
                    try {
                        Class<?> clsLoadClass = classLoader.loadClass(name);
                        Class<?> clsLoadClass2 = classLoader.loadClass(str2);
                        if ((clsLoadClass != null && clsLoadClass.isAssignableFrom(clsLoadClass2)) || RemoteModelService.class.isAssignableFrom(clsLoadClass2)) {
                            if (!name.equals(str2)) {
                                this.i.put(str, str2);
                                return str2;
                            }
                            z = true;
                        }
                    } catch (ClassNotFoundException unused) {
                    }
                }
                if (z) {
                    this.i.put(str, name);
                    return name;
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        this.i.put(str, name);
        return name;
    }

    public IInterface E(int i2, ComponentName componentName) {
        synchronized (C0020d.class) {
            for (C0020d c0020d : this.m) {
                if (c0020d.f218a == i2 && c0020d.f219b.equals(componentName)) {
                    return c0020d.f220c;
                }
            }
            return null;
        }
    }

    public final C0020d F(int i2, ComponentName componentName, IInterface iInterface) {
        for (C0020d c0020d : this.m) {
            if (c0020d.f218a == i2 && c0020d.f219b.equals(componentName) && c0020d.f220c.equals(iInterface)) {
                return c0020d;
            }
        }
        return null;
    }

    public int G(IInterface iInterface) {
        synchronized (C0020d.class) {
            for (C0020d c0020d : this.m) {
                if (c0020d.f220c == iInterface) {
                    return c0020d.f218a;
                }
            }
            return 0;
        }
    }

    public g H() {
        return new g();
    }

    public final j I(String str, String str2) {
        for (j jVar : this.j) {
            if (jVar != null && jVar.c().equals(str) && (str2 == null || str2.equals(jVar.b()))) {
                return jVar;
            }
        }
        return null;
    }

    public ArrayList<j> J() {
        return this.j;
    }

    public void K() {
    }

    public boolean L(String str) {
        return M(str, this.i.get(str));
    }

    public boolean M(String str, String str2) {
        b.a.c.f fVarE;
        j jVarI = I(str, str2);
        return (jVarI == null || (fVarE = jVarI.e()) == null || !fVarE.asBinder().pingBinder()) ? false : true;
    }

    public boolean N(PackageManager packageManager, Intent intent) {
        return packageManager.queryIntentServices(intent, 0).size() > 0;
    }

    public l O(l lVar) {
        return lVar.e(0);
    }

    public r.e P(IInterface iInterface, Class<?>... clsArr) {
        return m(0, iInterface, clsArr);
    }

    public final void Q(int i2, ComponentName componentName, IInterface iInterface) {
        synchronized (C0020d.class) {
            C0020d c0020dF = F(i2, componentName, iInterface);
            if (c0020dF != null) {
                this.m.remove(c0020dF);
            }
        }
    }

    public void S(IInterface iInterface) {
        s(0, iInterface);
    }

    @Override // b.a.c.r
    public String i(IInterface iInterface) {
        synchronized (C0020d.class) {
            for (C0020d c0020d : this.m) {
                if (c0020d.f220c == iInterface) {
                    return c0020d.d();
                }
            }
            return null;
        }
    }

    public final void x(int i2, ComponentName componentName, IInterface iInterface) {
        synchronized (C0020d.class) {
            if (F(i2, componentName, iInterface) == null) {
                this.m.add(new C0020d(i2, componentName, iInterface));
            }
        }
    }

    public g y(Context context, Intent intent, String str) {
        String className;
        this.f259a.d("connect: " + intent + ", client: " + str);
        g gVarH = H();
        String packageName = null;
        if (intent.getComponent() != null) {
            packageName = intent.getComponent().getPackageName();
            className = intent.getComponent().getClassName();
        } else {
            className = null;
        }
        if (packageName == null || className == null) {
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 32);
            if (listQueryIntentServices.size() > 0) {
                ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
                packageName = serviceInfo.packageName;
                className = serviceInfo.name;
            }
        }
        String str2 = className;
        String str3 = packageName;
        if (str3 == null || str2 == null) {
            this.f259a.a("can not connect to " + intent.toString());
        } else {
            j jVar = new j(str3, str2, str, this, this.k, gVarH, this.l, this.f259a);
            this.j.add(jVar);
            c cVar = new c(context, this.f262d, intent.setClassName(str3, str2), jVar);
            Handler handler = this.f262d;
            gVarH.f221b = handler;
            gVarH.f222c = cVar;
            R(handler, cVar, 0L);
        }
        return gVarH;
    }

    public g z(Context context, String str, String str2) {
        this.f259a.d("connect: " + str + ", client: " + str2);
        Intent intent = new Intent();
        intent.setClassName(str, D(context, str));
        return y(context, intent, str2);
    }
}
