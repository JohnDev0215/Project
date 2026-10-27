package b.b.l;

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
import b.b.l.f;
import b.b.l.q;
import b.b.l.r;
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
    public ArrayList<C0082d> m = new ArrayList<>();

    /* JADX INFO: compiled from: IModel.java */
    public class a implements h {
        public a() {
        }

        @Override // b.b.l.d.h
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

        @Override // b.b.l.d.e
        public void a(int i, ComponentName componentName, IInterface iInterface) {
            d.this.x(i, componentName, iInterface);
        }

        @Override // b.b.l.d.e
        public void b(int i, ComponentName componentName, IInterface iInterface) {
            d.this.Q(i, componentName, iInterface);
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f1960a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public PackageManager f1961b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Handler f1962c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Intent f1963d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public j f1964e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1965f;

        public c(Context context, Handler handler, Intent intent, j jVar) {
            this.f1960a = context;
            this.f1961b = context.getPackageManager();
            this.f1962c = handler;
            this.f1963d = intent;
            this.f1964e = jVar;
        }

        public void a() {
            this.f1965f = 10;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (d.this.N(this.f1961b, this.f1963d)) {
                    this.f1960a.bindService(this.f1963d, this.f1964e, 1);
                } else {
                    int i = this.f1965f;
                    if (i > 0) {
                        this.f1965f = i - 1;
                        d.this.f2017a.d("BindRunnable intent: " + this.f1963d.toString() + " not exist, retry after 1000 ms");
                        this.f1962c.postDelayed(this, 1000L);
                    } else {
                        d.this.f2017a.d("BindRunnable intent: " + this.f1963d.toString() + " not exist, abandon bind");
                    }
                }
            } catch (RuntimeException e2) {
                d.this.f2017a.a("BindRunnable intent " + this.f1963d.toString() + " error");
                d.this.f2017a.b(e2);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.l.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IModel.java */
    public static class C0082d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1966a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ComponentName f1967b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public IInterface f1968c;

        public C0082d(int i, ComponentName componentName, IInterface iInterface) {
            this.f1966a = i;
            this.f1967b = componentName;
            this.f1968c = iInterface;
        }

        public String d() {
            String packageName = this.f1967b.getPackageName();
            String className = this.f1967b.getClassName();
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
        public Handler f1969b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c f1970c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public f f1971d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f f1972e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1973f = true;
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
            d.R(this.f1969b, this.f1970c, 0L);
            if ("com.byd.mvcadapter.rpc.RpcRequest".equals(className)) {
                this.f1971d = fVar;
            } else {
                this.f1972e = fVar;
            }
            return this;
        }

        public void e(j jVar) {
            this.j = jVar;
        }

        @Override // b.b.l.r.e
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public g a(r.c cVar) {
            d.R(this.f1969b, this.f1970c, 0L);
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
        public ComponentName f1974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f1975b;

        public i(ComponentName componentName, h hVar) {
            this.f1974a = componentName;
            this.f1975b = hVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            h hVar = this.f1975b;
            if (hVar != null) {
                hVar.a(this.f1974a);
            }
        }
    }

    /* JADX INFO: compiled from: IModel.java */
    public static class j implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1976a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1977b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1978c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f1979d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ComponentName f1980e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public h f1981f;
        public g g;
        public e h;
        public k i;
        public int j;
        public b.b.l.f k;
        public o l;
        public boolean m = false;

        public j(String str, String str2, String str3, d dVar, h hVar, g gVar, e eVar, k kVar) {
            this.f1976a = str;
            this.f1977b = str2;
            this.f1978c = str3;
            this.f1979d = dVar;
            this.f1981f = hVar;
            this.g = gVar;
            this.h = eVar;
            this.i = kVar;
        }

        public String b() {
            return this.f1977b;
        }

        public String c() {
            return this.f1976a;
        }

        public int d() {
            return this.j;
        }

        public b.b.l.f e() {
            return this.k;
        }

        public void f(boolean z) {
            this.m = z;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            f fVar;
            f fVar2;
            this.i.d("onServiceConnected: " + componentName);
            this.f1980e = componentName;
            b.b.l.f fVarD0 = f.a.d0(iBinder);
            this.k = fVarD0;
            try {
                this.j = fVarD0.W();
            } catch (RemoteException e2) {
                this.i.b(e2);
            }
            IInterface iInterfaceE = this.f1979d.E(this.j, componentName);
            if (iInterfaceE != null) {
                this.k = (b.b.l.f) iInterfaceE;
            }
            this.l = new o(this.f1979d);
            b.b.l.f fVar3 = this.k;
            if (fVar3 != null) {
                r.e eVarP = this.f1979d.P(fVar3, q.a.class);
                g gVar = this.g;
                eVarP.a(gVar != null ? gVar.f2033a : null);
                try {
                    b.b.l.f fVar4 = this.k;
                    o oVar = this.l;
                    String str = this.f1978c;
                    int iMyPid = Process.myPid();
                    g gVar2 = this.g;
                    fVar4.N(oVar, str, iMyPid, gVar2 != null ? gVar2.b() : 0L);
                    g gVar3 = this.g;
                    if (gVar3 != null) {
                        gVar3.g(this.j);
                    }
                    this.k.asBinder().linkToDeath(new i(componentName, this.f1981f), 0);
                    e eVar = this.h;
                    if (eVar != null) {
                        eVar.a(this.j, componentName, this.k);
                    }
                } catch (RemoteException e3) {
                    this.i.b(e3);
                }
            }
            g gVar4 = this.g;
            if (gVar4 != null && (fVar2 = gVar4.f1971d) != null) {
                fVar2.connected(componentName, this.k);
            }
            g gVar5 = this.g;
            if (gVar5 == null || (fVar = gVar5.f1972e) == null) {
                return;
            }
            fVar.connected(componentName, this.k);
        }

        @Override // android.content.ServiceConnection
        public synchronized void onServiceDisconnected(ComponentName componentName) {
            if (this.k != null) {
                this.i.d("onServiceDisconnected: " + componentName);
                this.f1979d.S(this.k);
                try {
                    this.k.O(this.l);
                } catch (RemoteException unused) {
                }
                g gVar = this.g;
                if (gVar != null) {
                    gVar.g(0);
                    f fVar = this.g.f1971d;
                    if (fVar != null) {
                        fVar.disconnected(componentName, this.k);
                    }
                    f fVar2 = this.g.f1972e;
                    if (fVar2 != null) {
                        fVar2.disconnected(componentName, this.k);
                    }
                    g gVar2 = this.g;
                    if (gVar2.f1973f && !this.m) {
                        d.R(gVar2.f1969b, gVar2.f1970c, gVar2.g);
                    }
                    this.m = false;
                }
                e eVar = this.h;
                if (eVar != null) {
                    eVar.b(this.j, componentName, this.k);
                }
                this.k = null;
            }
        }

        public String toString() {
            return this.j + "/" + this.f1976a + "/" + this.f1977b + "/" + this.f1978c + "/" + this.k;
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
        this.f2017a.d("connectSystemService: " + str + ", client: " + str2);
        g gVarH = H();
        StringBuilder sb = new StringBuilder();
        sb.append("SystemService_");
        sb.append(str);
        j jVar = new j("SystemService", sb.toString(), str2, this, this.k, gVarH, this.l, this.f2017a);
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
            this.f2017a.d("disconnect: " + str);
        }
        j jVarI = I(str, str2);
        if (jVarI != null) {
            if (z) {
                this.j.remove(jVarI);
            }
            jVarI.f(z);
            jVarI.onServiceDisconnected(jVarI.f1980e);
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
        synchronized (C0082d.class) {
            for (C0082d c0082d : this.m) {
                if (c0082d.f1966a == i2 && c0082d.f1967b.equals(componentName)) {
                    return c0082d.f1968c;
                }
            }
            return null;
        }
    }

    public final C0082d F(int i2, ComponentName componentName, IInterface iInterface) {
        for (C0082d c0082d : this.m) {
            if (c0082d.f1966a == i2 && c0082d.f1967b.equals(componentName) && c0082d.f1968c.equals(iInterface)) {
                return c0082d;
            }
        }
        return null;
    }

    public int G(IInterface iInterface) {
        synchronized (C0082d.class) {
            for (C0082d c0082d : this.m) {
                if (c0082d.f1968c == iInterface) {
                    return c0082d.f1966a;
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
        b.b.l.f fVarE;
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
        synchronized (C0082d.class) {
            C0082d c0082dF = F(i2, componentName, iInterface);
            if (c0082dF != null) {
                this.m.remove(c0082dF);
            }
        }
    }

    public void S(IInterface iInterface) {
        s(0, iInterface);
    }

    @Override // b.b.l.r
    public String i(IInterface iInterface) {
        synchronized (C0082d.class) {
            for (C0082d c0082d : this.m) {
                if (c0082d.f1968c == iInterface) {
                    return c0082d.d();
                }
            }
            return null;
        }
    }

    public final void x(int i2, ComponentName componentName, IInterface iInterface) {
        synchronized (C0082d.class) {
            if (F(i2, componentName, iInterface) == null) {
                this.m.add(new C0082d(i2, componentName, iInterface));
            }
        }
    }

    public g y(Context context, Intent intent, String str) {
        String className;
        this.f2017a.d("connect: " + intent + ", client: " + str);
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
            this.f2017a.a("can not connect to " + intent.toString());
        } else {
            j jVar = new j(str3, str2, str, this, this.k, gVarH, this.l, this.f2017a);
            this.j.add(jVar);
            c cVar = new c(context, this.f2020d, intent.setClassName(str3, str2), jVar);
            Handler handler = this.f2020d;
            gVarH.f1969b = handler;
            gVarH.f1970c = cVar;
            R(handler, cVar, 0L);
        }
        return gVarH;
    }

    public g z(Context context, String str, String str2) {
        this.f2017a.d("connect: " + str + ", client: " + str2);
        Intent intent = new Intent();
        intent.setClassName(str, D(context, str));
        return y(context, intent, str2);
    }
}
