package d.c.a.a.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import d.c.a.a.a.a;
import d.c.a.a.a.b;

/* JADX INFO: compiled from: SpeechFrontendRecordAgent.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f4054a = "SpeechFrontendRecordAgent";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile a f4055b;
    private d.c.a.a.c.a g;
    private b h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4056c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f4057d = null;
    private String e = "com.iflytek.cutefly.speechclient.hmi";
    private String f = "com.iflytek.autofly.speechfrontend.service.SpeechFrontendService";
    private boolean i = false;
    private int j = 0;
    private ServiceConnection k = new ServiceConnectionC0129a();

    /* JADX INFO: renamed from: d.c.a.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SpeechFrontendRecordAgent.java */
    class ServiceConnectionC0129a implements ServiceConnection {
        ServiceConnectionC0129a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            d.c.a.b.b.a.a(a.f4054a, "onServiceConnected with name: " + componentName + " service: " + iBinder);
            a.this.i = false;
            a.this.j = 0;
            if (iBinder == null) {
                a.this.n(false, 1001);
                return;
            }
            a.this.h = b.a.q0(iBinder);
            if (a.this.h != null) {
                try {
                    a.this.h.k(a.this.f4056c);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            a.this.n(true, 1000);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            d.c.a.b.b.a.a(a.f4054a, "onServiceDisconnected: ");
            a.this.i = false;
            a.this.h = null;
            a.this.n(false, 1001);
        }
    }

    private Intent h(Intent intent, String str) {
        ComponentName componentName = new ComponentName(str, this.f);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    public static a i() {
        if (f4055b == null) {
            synchronized (a.class) {
                if (f4055b == null) {
                    f4055b = new a();
                }
            }
        }
        return f4055b;
    }

    private synchronized void j() {
        if (this.h != null) {
            d.c.a.b.b.a.a(f4054a, "service already connected");
            n(true, 1000);
        } else if (!this.i) {
            Intent intentH = h(new Intent(this.f), this.e);
            try {
                this.i = true;
                boolean zBindService = this.f4057d.bindService(intentH, this.k, 1);
                d.c.a.b.b.a.a(f4054a, "bind service is：" + zBindService);
                if (!zBindService) {
                    this.i = false;
                    n(false, 1001);
                }
            } catch (Exception e) {
                d.c.a.b.b.a.a(f4054a, "bind service failed : " + e.getMessage());
                this.i = false;
                n(false, 1002);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(boolean z, int i) {
        d.c.a.b.b.a.a(f4054a, "stateCallback state: " + z + " code: " + i);
        d.c.a.a.c.a aVar = this.g;
        if (aVar != null) {
            aVar.onInitCallback(z, i);
        } else {
            d.c.a.b.b.a.a(f4054a, BuildConfig.FLAVOR);
        }
        if (z) {
            return;
        }
        d.c.a.b.b.a.a(f4054a, " check retry count: " + this.j);
        int i2 = this.j;
        if (i2 < 5) {
            this.j = i2 + 1;
            j();
        }
    }

    public int k(Context context, d.c.a.a.c.a aVar) {
        d.c.a.b.b.a.a(f4054a, "start init session");
        if (context == null) {
            d.c.a.b.b.a.a(f4054a, "init context is null");
            return -1;
        }
        if (aVar == null) {
            d.c.a.b.b.a.a(f4054a, "init callback is null");
            return -1;
        }
        this.f4056c = context.getPackageName();
        this.f4057d = context;
        this.g = aVar;
        j();
        return 0;
    }

    public int l(String str, String str2) {
        d.c.a.b.b.a.a(f4054a, "setParam() with param: " + str + " value: " + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            d.c.a.b.b.a.a(f4054a, "param or value is empty");
            return -1;
        }
        b bVar = this.h;
        if (bVar == null) {
            d.c.a.b.b.a.a(f4054a, "iRecordService is null");
            return -1;
        }
        try {
            int iJ = bVar.j(str, str2);
            d.c.a.b.b.a.a(f4054a, "ret is: " + iJ);
            return iJ;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int m(int i, a.AbstractBinderC0126a abstractBinderC0126a) {
        d.c.a.b.b.a.a(f4054a, "start record param: " + i);
        if (abstractBinderC0126a == null) {
            d.c.a.b.b.a.a(f4054a, "listener is null");
            return -1;
        }
        b bVar = this.h;
        if (bVar == null) {
            d.c.a.b.b.a.a(f4054a, "iRecordService is null");
            return -1;
        }
        try {
            int iJ0 = bVar.j0(i, abstractBinderC0126a);
            d.c.a.b.b.a.a(f4054a, "start record ret = " + iJ0);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int o() {
        d.c.a.b.b.a.a(f4054a, "stop record");
        b bVar = this.h;
        if (bVar == null) {
            d.c.a.b.b.a.a(f4054a, "iRecordService is null");
            return -1;
        }
        try {
            int iF0 = bVar.f0();
            d.c.a.b.b.a.a(f4054a, "stop record ret = " + iF0);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
