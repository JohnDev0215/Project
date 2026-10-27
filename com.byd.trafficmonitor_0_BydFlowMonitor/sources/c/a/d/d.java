package c.a.d;

import android.os.RemoteException;
import com.bydux.api.common.tools.log.BydLogHelper;
import i.a.a.a.d;

/* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d f3560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i.a.a.a.c f3561b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f3562c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3563d;

    /* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
    public class b extends d.a {
        public b() {
        }

        @Override // i.a.a.a.d
        public void b(byte b2) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void d(byte b2) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void e(byte b2) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void i(String str) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void j(i.a.a.a.b bVar) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void n(byte b2) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void o(byte b2) throws RemoteException {
            BydLogHelper.logD("onRilConnectChanged :" + ((int) b2));
            c cVar = d.this.f3563d;
            if (cVar != null) {
                cVar.a(b2);
            }
        }
    }

    /* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
    public interface c {
        void a(int i2);
    }

    public d() {
        g();
    }

    public static d e() {
        if (f3560a == null) {
            synchronized (d.class) {
                if (f3560a == null) {
                    f3560a = new d();
                }
            }
        }
        return f3560a;
    }

    public int a() {
        i.a.a.a.c cVar = this.f3561b;
        if (cVar == null) {
            return -1;
        }
        try {
            return cVar.m();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public String b() {
        i.a.a.a.c cVar = this.f3561b;
        if (cVar == null) {
            return null;
        }
        try {
            return cVar.h();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String c() {
        i.a.a.a.c cVar = this.f3561b;
        if (cVar == null) {
            return null;
        }
        try {
            return cVar.f();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String d() {
        i.a.a.a.c cVar = this.f3561b;
        if (cVar == null) {
            return null;
        }
        try {
            return cVar.k();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int f() {
        i.a.a.a.c cVar = this.f3561b;
        if (cVar == null) {
            return -1;
        }
        try {
            return cVar.g();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public final void g() {
        BydLogHelper.logD("tBox init----------- ");
        try {
            this.f3561b = i.a.a.a.c.p();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        this.f3562c = new b();
    }
}
