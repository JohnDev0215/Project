package b.b.g.p;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.byd.sdkupdate.impl.OtaSdkManager;
import com.byd.sdkupdate.impl.OtaSdkManagerCallback;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: OtaManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OtaSdkManager f1392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f1394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ConcurrentHashMap<String, b.b.g.p.j.c> f1395d;

    /* JADX INFO: compiled from: OtaManager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f1392a.setOtaUpdate();
            b.b.m.c.a.a("OtaManager", "---requestUpdate---called on ota service.");
        }
    }

    /* JADX INFO: compiled from: OtaManager.java */
    public class b implements OtaSdkManager.ServiceConnectionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f1397a;

        public b(f fVar) {
            this.f1397a = new WeakReference<>(fVar);
        }

        @Override // com.byd.sdkupdate.impl.OtaSdkManager.ServiceConnectionCallback
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            WeakReference<f> weakReference = this.f1397a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1397a.get().i(true);
        }

        @Override // com.byd.sdkupdate.impl.OtaSdkManager.ServiceConnectionCallback
        public void onServiceDisconnected(ComponentName componentName) {
            WeakReference<f> weakReference = this.f1397a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1397a.get().i(false);
        }
    }

    /* JADX INFO: compiled from: OtaManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f1399a = new f(null);

        public static f a() {
            return f1399a;
        }
    }

    /* JADX INFO: compiled from: OtaManager.java */
    public class d implements OtaSdkManagerCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f1400a;

        public d(f fVar) {
            this.f1400a = new WeakReference<>(fVar);
        }

        @Override // com.byd.sdkupdate.impl.OtaSdkManagerCallback
        public void onUpdateFlag(boolean z) {
            WeakReference<f> weakReference = this.f1400a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1400a.get().j(z);
        }

        @Override // com.byd.sdkupdate.impl.OtaSdkManagerCallback
        public void onUpdateTime(long j) {
            WeakReference<f> weakReference = this.f1400a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1400a.get().k(j);
        }
    }

    public /* synthetic */ f(a aVar) {
        this();
    }

    public void e() {
        this.f1392a.setCancelDelay();
    }

    public long f() {
        return this.f1392a.getUpdateTime();
    }

    public boolean g() {
        OtaSdkManager otaSdkManager = this.f1392a;
        if (otaSdkManager == null) {
            return false;
        }
        return otaSdkManager.getUpdateFlag();
    }

    public void h(Context context) {
        if (context == null) {
            return;
        }
        if (this.f1394c == null) {
            this.f1394c = new b(this);
        }
        this.f1392a = OtaSdkManager.getInstance(context.getApplicationContext(), this.f1394c);
        if (this.f1393b == null) {
            this.f1393b = new d(this);
        }
        this.f1392a.registerCallback(this.f1393b);
    }

    public final void i(boolean z) {
        b.b.m.c.a.a("OtaManager", "--onServiceConnectChanged--connected: " + z);
        if (z) {
            boolean updateFlag = this.f1392a.getUpdateFlag();
            Iterator<b.b.g.p.j.c> it = this.f1395d.values().iterator();
            while (it.hasNext()) {
                it.next().onUpdateFlag(updateFlag);
            }
            Iterator<b.b.g.p.j.c> it2 = this.f1395d.values().iterator();
            while (it2.hasNext()) {
                it2.next().onUpdateTime(f());
            }
            Iterator<b.b.g.p.j.c> it3 = this.f1395d.values().iterator();
            while (it3.hasNext()) {
                it3.next().onServiceConnectChanged(true);
            }
        }
    }

    public final void j(boolean z) {
        b.b.m.c.a.a("OtaManager", "--onUpdateFlag--flag: " + z);
        Iterator<b.b.g.p.j.c> it = this.f1395d.values().iterator();
        while (it.hasNext()) {
            it.next().onUpdateFlag(z);
        }
    }

    public final void k(long j) {
        b.b.m.c.a.a("OtaManager", "--onUpdateTime--time: " + j);
        Iterator<b.b.g.p.j.c> it = this.f1395d.values().iterator();
        while (it.hasNext()) {
            it.next().onUpdateTime(j);
        }
    }

    public void l(String str, b.b.g.p.j.c cVar) {
        this.f1395d.put(str, cVar);
    }

    public void m() {
        OtaSdkManager otaSdkManager = this.f1392a;
        if (otaSdkManager != null) {
            d dVar = this.f1393b;
            if (dVar != null) {
                otaSdkManager.unregisterCallback(dVar);
            }
            try {
                this.f1392a.destoryManager();
            } catch (RuntimeException unused) {
            }
        }
        ConcurrentHashMap<String, b.b.g.p.j.c> concurrentHashMap = this.f1395d;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public void n() {
        b.b.m.c.a.a("OtaManager", "---requestUpdate---");
        b.b.a.f.h.a().j(new a());
    }

    public void o() {
        OtaSdkManager otaSdkManager = this.f1392a;
        if (otaSdkManager != null) {
            otaSdkManager.setUserClickCheckState();
        }
    }

    public void p(String str) {
        this.f1395d.remove(str);
    }

    public f() {
        this.f1395d = new ConcurrentHashMap<>();
    }
}
