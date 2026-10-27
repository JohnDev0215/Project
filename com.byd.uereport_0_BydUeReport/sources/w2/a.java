package w2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.byd.info.BtCallInfo;
import java.util.ArrayList;
import java.util.List;
import v2.d;
import x2.a;
import x2.b;

/* JADX INFO: compiled from: HfpManage.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f8713a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f8715c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x2.b f8716d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f8714b = a.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8717e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8718f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f8719g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<v2.c> f8720h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<v2.b> f8721i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<v2.a> f8722j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<d> f8723k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f8724l = 100001;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f8725m = 100002;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f8726n = 100003;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f8727o = 100004;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f8728p = 100005;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f8729q = 100006;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f8730r = 100007;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f8731s = 100008;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f8732t = 100009;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f8733u = 100010;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f8734v = 100011;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f8735w = 100012;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f8736x = 100013;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Handler f8737y = new HandlerC0137a(Looper.myLooper());

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ServiceConnection f8738z = new b();
    public a.AbstractBinderC0142a A = new c();

    /* JADX INFO: renamed from: w2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HfpManage.java */
    public class HandlerC0137a extends Handler {
        public HandlerC0137a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i8 = 0;
            switch (message.what) {
                case 100001:
                    int i9 = message.arg1;
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).d(i9);
                        i8++;
                    }
                    return;
                case 100002:
                    int i10 = message.arg1;
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).e(i10);
                        i8++;
                    }
                    return;
                case 100003:
                    Bundle data = message.getData();
                    String string = data.getString("title");
                    String string2 = data.getString("artist");
                    String string3 = data.getString("album");
                    String string4 = data.getString("iconUrl");
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).b(string, string2, string3, string4);
                        i8++;
                    }
                    return;
                case 100004:
                    long jLongValue = ((Long) message.obj).longValue();
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).j(jLongValue);
                        i8++;
                    }
                    return;
                case 100005:
                    long jLongValue2 = ((Long) message.obj).longValue();
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).i(jLongValue2);
                        i8++;
                    }
                    return;
                case 100006:
                    int i11 = message.arg1;
                    while (i8 < a.this.f8720h.size()) {
                        ((v2.c) a.this.f8720h.get(i8)).h(i11);
                        i8++;
                    }
                    return;
                case 100007:
                    int i12 = message.arg1;
                    while (i8 < a.this.f8721i.size()) {
                        ((v2.b) a.this.f8721i.get(i8)).k(i12);
                        i8++;
                    }
                    return;
                case 100008:
                    List<BtCallInfo> list = (List) message.obj;
                    while (i8 < a.this.f8722j.size()) {
                        ((v2.a) a.this.f8722j.get(i8)).CallState_Change(list);
                        i8++;
                    }
                    return;
                case 100009:
                case 100010:
                case 100011:
                default:
                    return;
                case 100012:
                    break;
                case 100013:
                    a aVar = a.this;
                    if (aVar.a(aVar.f8717e)) {
                        return;
                    }
                    a.this.f8737y.sendEmptyMessageDelayed(100013, 800L);
                    return;
            }
            while (i8 < a.this.f8723k.size()) {
                ((d) a.this.f8723k.get(i8)).SyncContactsState(message.arg1);
                i8++;
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.f8716d = b.a.A0(iBinder);
            int i8 = 1;
            a.this.f8719g = true;
            Log.d("DANDAN", "onServiceConnected======");
            try {
                a.this.f8716d.Z(a.this.A);
                Message messageObtainMessage = a.this.f8737y.obtainMessage();
                messageObtainMessage.what = 100001;
                if (!a.this.f8716d.B()) {
                    i8 = 0;
                }
                messageObtainMessage.arg1 = i8;
                a.this.f8737y.sendMessage(messageObtainMessage);
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                Log.d("DANDAN", "onServiceDisconnected======");
                a.this.f8719g = false;
                a.this.f8716d = null;
                a.this.f8737y.sendEmptyMessageDelayed(100013, 800L);
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class c extends a.AbstractBinderC0142a {
        public c() {
        }

        @Override // x2.a
        public void CallState_Change(List<BtCallInfo> list) throws RemoteException {
            Log.d("DANDAN", "CallState_Change ======================" + list.size());
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100008;
            messageObtainMessage.obj = list;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void SyncContactsState(int i8) throws RemoteException {
            Log.d("DANDAN", "SyncContactsState===================================== = state = " + i8);
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100012;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void U(BtCallInfo btCallInfo) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100009;
            messageObtainMessage.obj = btCallInfo;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void W(int i8) throws RemoteException {
        }

        @Override // x2.a
        public void b(String str, String str2, String str3, String str4) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            Bundle bundle = new Bundle();
            messageObtainMessage.what = 100003;
            bundle.putString("title", str);
            bundle.putString("artist", str2);
            bundle.putString("album", str3);
            bundle.putString("iconUrl", str4);
            messageObtainMessage.setData(bundle);
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void d(int i8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100001;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void e(int i8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100002;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void h(int i8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100006;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void i(long j8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100005;
            messageObtainMessage.obj = Long.valueOf(j8);
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void j(long j8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100004;
            messageObtainMessage.obj = Long.valueOf(j8);
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void k(int i8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100007;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void s(boolean z7) throws RemoteException {
        }

        @Override // x2.a
        public void t0(int i8) throws RemoteException {
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100010;
            messageObtainMessage.arg1 = i8;
            a.this.f8737y.sendMessage(messageObtainMessage);
        }

        @Override // x2.a
        public void w(boolean z7) throws RemoteException {
            a.this.f8718f = z7;
            Message messageObtainMessage = a.this.f8737y.obtainMessage();
            messageObtainMessage.what = 100011;
            messageObtainMessage.obj = Boolean.valueOf(z7);
            a.this.f8737y.sendMessage(messageObtainMessage);
        }
    }

    public static a p() {
        if (f8713a == null) {
            synchronized (a.class) {
                if (f8713a == null) {
                    f8713a = new a();
                }
            }
        }
        return f8713a;
    }

    public final boolean a(String str) {
        Log.d("DANDAN", "BindBTService ============");
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.byd.bluetoothservice.BTService");
        return this.f8715c.bindService(intent, this.f8738z, 1);
    }

    public void b() {
        x2.b bVar;
        try {
            if (!this.f8719g || (bVar = this.f8716d) == null) {
                return;
            }
            bVar.Q();
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public int o() {
        x2.b bVar;
        try {
            if (!this.f8719g || (bVar = this.f8716d) == null) {
                return 0;
            }
            return bVar.q0();
        } catch (RemoteException e8) {
            e8.printStackTrace();
            return 0;
        }
    }

    public int q() {
        x2.b bVar;
        try {
            if (!this.f8719g || (bVar = this.f8716d) == null) {
                return 0;
            }
            return bVar.k0();
        } catch (RemoteException e8) {
            e8.printStackTrace();
            return 0;
        }
    }

    public void r(Context context, String str) {
        this.f8715c = context;
        this.f8717e = str;
        this.f8737y.sendEmptyMessage(100013);
    }

    public void s(d dVar) {
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= this.f8723k.size()) {
                break;
            }
            if (this.f8723k.get(i8) == dVar) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (z7) {
            return;
        }
        this.f8723k.add(dVar);
    }

    public void t(v2.a aVar) {
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= this.f8722j.size()) {
                break;
            }
            if (this.f8722j.get(i8) == aVar) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (z7) {
            return;
        }
        this.f8722j.add(aVar);
    }

    public void u(d dVar) {
        for (int i8 = 0; i8 < this.f8723k.size(); i8++) {
            if (this.f8723k.get(i8) == dVar) {
                this.f8723k.remove(i8);
                return;
            }
        }
    }

    public void v(v2.a aVar) {
        for (int i8 = 0; i8 < this.f8722j.size(); i8++) {
            if (this.f8722j.get(i8) == aVar) {
                this.f8722j.remove(i8);
                return;
            }
        }
    }
}
