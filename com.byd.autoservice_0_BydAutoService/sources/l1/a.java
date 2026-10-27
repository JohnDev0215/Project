package l1;

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
import k1.d;
import m1.a;
import m1.b;

/* JADX INFO: compiled from: HfpManage.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f3685a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f3687c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m1.b f3688d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3686b = a.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3689e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3690f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f3691g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<k1.c> f3692h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<k1.b> f3693i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<k1.a> f3694j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<d> f3695k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f3696l = 100001;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f3697m = 100002;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f3698n = 100003;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f3699o = 100004;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f3700p = 100005;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f3701q = 100006;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f3702r = 100007;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f3703s = 100008;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f3704t = 100009;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f3705u = 100010;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f3706v = 100011;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f3707w = 100012;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f3708x = 100013;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Handler f3709y = new HandlerC0034a(Looper.myLooper());

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ServiceConnection f3710z = new b();
    public a.AbstractBinderC0038a A = new c();

    /* JADX INFO: renamed from: l1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HfpManage.java */
    public class HandlerC0034a extends Handler {
        public HandlerC0034a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i3 = 0;
            switch (message.what) {
                case 100001:
                    int i4 = message.arg1;
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).d(i4);
                        i3++;
                    }
                    return;
                case 100002:
                    int i5 = message.arg1;
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).f(i5);
                        i3++;
                    }
                    return;
                case 100003:
                    Bundle data = message.getData();
                    String string = data.getString("title");
                    String string2 = data.getString("artist");
                    String string3 = data.getString("album");
                    String string4 = data.getString("iconUrl");
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).c(string, string2, string3, string4);
                        i3++;
                    }
                    return;
                case 100004:
                    long jLongValue = ((Long) message.obj).longValue();
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).h(jLongValue);
                        i3++;
                    }
                    return;
                case 100005:
                    long jLongValue2 = ((Long) message.obj).longValue();
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).g(jLongValue2);
                        i3++;
                    }
                    return;
                case 100006:
                    int i6 = message.arg1;
                    while (i3 < a.this.f3692h.size()) {
                        ((k1.c) a.this.f3692h.get(i3)).e(i6);
                        i3++;
                    }
                    return;
                case 100007:
                    int i7 = message.arg1;
                    while (i3 < a.this.f3693i.size()) {
                        ((k1.b) a.this.f3693i.get(i3)).i(i7);
                        i3++;
                    }
                    return;
                case 100008:
                    List<BtCallInfo> list = (List) message.obj;
                    while (i3 < a.this.f3694j.size()) {
                        ((k1.a) a.this.f3694j.get(i3)).b(list);
                        i3++;
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
                    if (aVar.a(aVar.f3689e)) {
                        return;
                    }
                    a.this.f3709y.sendEmptyMessageDelayed(100013, 800L);
                    return;
            }
            while (i3 < a.this.f3695k.size()) {
                ((d) a.this.f3695k.get(i3)).a(message.arg1);
                i3++;
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.f3688d = b.a.F(iBinder);
            int i3 = 1;
            a.this.f3691g = true;
            Log.d("DANDAN", "onServiceConnected======");
            try {
                a.this.f3688d.w(a.this.A);
                Message messageObtainMessage = a.this.f3709y.obtainMessage();
                messageObtainMessage.what = 100001;
                if (!a.this.f3688d.x()) {
                    i3 = 0;
                }
                messageObtainMessage.arg1 = i3;
                a.this.f3709y.sendMessage(messageObtainMessage);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                Log.d("DANDAN", "onServiceDisconnected======");
                a.this.f3691g = false;
                a.this.f3688d = null;
                a.this.f3709y.sendEmptyMessageDelayed(100013, 800L);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class c extends a.AbstractBinderC0038a {
        public c() {
        }

        @Override // m1.a
        public void B(int i3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100010;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void a(int i3) throws RemoteException {
            Log.d("DANDAN", "SyncContactsState===================================== = state = " + i3);
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100012;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void b(List<BtCallInfo> list) throws RemoteException {
            Log.d("DANDAN", "CallState_Change ======================" + list.size());
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100008;
            messageObtainMessage.obj = list;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void c(String str, String str2, String str3, String str4) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            Bundle bundle = new Bundle();
            messageObtainMessage.what = 100003;
            bundle.putString("title", str);
            bundle.putString("artist", str2);
            bundle.putString("album", str3);
            bundle.putString("iconUrl", str4);
            messageObtainMessage.setData(bundle);
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void d(int i3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100001;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void e(int i3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100006;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void f(int i3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100002;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void g(long j3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100005;
            messageObtainMessage.obj = Long.valueOf(j3);
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void h(long j3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100004;
            messageObtainMessage.obj = Long.valueOf(j3);
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void i(int i3) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100007;
            messageObtainMessage.arg1 = i3;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void q(boolean z3) throws RemoteException {
        }

        @Override // m1.a
        public void r(BtCallInfo btCallInfo) throws RemoteException {
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100009;
            messageObtainMessage.obj = btCallInfo;
            a.this.f3709y.sendMessage(messageObtainMessage);
        }

        @Override // m1.a
        public void s(int i3) throws RemoteException {
        }

        @Override // m1.a
        public void v(boolean z3) throws RemoteException {
            a.this.f3690f = z3;
            Message messageObtainMessage = a.this.f3709y.obtainMessage();
            messageObtainMessage.what = 100011;
            messageObtainMessage.obj = Boolean.valueOf(z3);
            a.this.f3709y.sendMessage(messageObtainMessage);
        }
    }

    public static a o() {
        if (f3685a == null) {
            synchronized (a.class) {
                if (f3685a == null) {
                    f3685a = new a();
                }
            }
        }
        return f3685a;
    }

    public final boolean a(String str) {
        Log.d("DANDAN", "BindBTService ============");
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.byd.bluetoothservice.BTService");
        return this.f3687c.bindService(intent, this.f3710z, 1);
    }

    public int n() {
        m1.b bVar;
        try {
            if (!this.f3691g || (bVar = this.f3688d) == null) {
                return 0;
            }
            return bVar.A();
        } catch (RemoteException e3) {
            e3.printStackTrace();
            return 0;
        }
    }

    public void p(Context context, String str) {
        this.f3687c = context;
        this.f3689e = str;
        this.f3709y.sendEmptyMessage(100013);
    }

    public void q(k1.a aVar) {
        boolean z3 = false;
        int i3 = 0;
        while (true) {
            if (i3 >= this.f3694j.size()) {
                break;
            }
            if (this.f3694j.get(i3) == aVar) {
                z3 = true;
                break;
            }
            i3++;
        }
        if (z3) {
            return;
        }
        this.f3694j.add(aVar);
    }
}
