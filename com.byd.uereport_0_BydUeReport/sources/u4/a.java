package u4;

import android.content.Context;
import android.os.RemoteException;
import com.bydux.api.common.tools.log.BydLogHelper;
import m7.d;

/* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f8199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f8200b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte f8201c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte f8202d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final byte f8203e = 3;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final byte f8204f = 4;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m7.c f8205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b f8206h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f8207i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public c f8208j;

    /* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
    public class b extends d.a {
        public b() {
        }

        @Override // m7.d
        public void c(byte b8) throws RemoteException {
        }

        @Override // m7.d
        public void d(byte b8) throws RemoteException {
        }

        @Override // m7.d
        public void f(byte b8) throws RemoteException {
            BydLogHelper.logD("onApn2Changed  state :" + ((int) b8));
        }

        @Override // m7.d
        public void g(byte b8) throws RemoteException {
            BydLogHelper.logD("onRilConnectChanged :" + ((int) b8));
            if (a.this.f8207i != null && b8 == 1) {
                d.w(a.this.f8207i).t();
            }
            c cVar = a.this.f8208j;
            if (cVar != null) {
                cVar.a(b8);
            }
        }

        @Override // m7.d
        public void j(byte b8) throws RemoteException {
        }

        @Override // m7.d
        public void l(String str) throws RemoteException {
        }

        @Override // m7.d
        public void m(m7.b bVar) throws RemoteException {
            byte b8 = bVar.f6433a;
            if (b8 == 0 || b8 == 1) {
                c cVar = a.this.f8208j;
                if (cVar != null) {
                    cVar.a(0);
                    return;
                }
                return;
            }
            c cVar2 = a.this.f8208j;
            if (cVar2 != null) {
                cVar2.a(1);
            }
        }
    }

    /* JADX INFO: compiled from: BYDAutoTBoxDeviceInfo.java */
    public interface c {
        void a(int i8);
    }

    public a(Context context) {
        this.f8207i = context.getApplicationContext();
        d();
    }

    public static a c(Context context) {
        if (f8199a == null) {
            synchronized (a.class) {
                if (f8199a == null) {
                    f8199a = new a(context);
                }
            }
        }
        return f8199a;
    }

    public String b() {
        m7.c cVar = this.f8205g;
        if (cVar == null) {
            return null;
        }
        try {
            return cVar.k();
        } catch (RemoteException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public void d() {
        BydLogHelper.logD("tBox init----------- ");
        try {
            this.f8205g = m7.c.n();
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
        this.f8206h = new b();
    }

    public void e() {
        BydLogHelper.logD("tBox register----------- ");
        try {
            m7.c cVar = this.f8205g;
            if (cVar != null) {
                cVar.h(this.f8206h);
            }
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public void f() {
        BydLogHelper.logD("unRegister ");
        try {
            m7.c cVar = this.f8205g;
            if (cVar != null) {
                cVar.b(this.f8206h);
            }
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }
}
