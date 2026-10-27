package b.b.b.b;

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
import b.b.b.a.d;
import b.b.c.a;
import b.b.c.b;
import com.byd.info.BtCallInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: HfpManage.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f581a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.c.b f584d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f582b = a.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f585e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f586f = false;
    public volatile boolean g = false;
    public ArrayList<b.b.b.a.c> h = new ArrayList<>();
    public ArrayList<b.b.b.a.b> i = new ArrayList<>();
    public ArrayList<b.b.b.a.a> j = new ArrayList<>();
    public ArrayList<d> k = new ArrayList<>();
    public final int l = 100001;
    public final int m = 100002;
    public final int n = 100003;
    public final int o = 100004;
    public final int p = 100005;
    public final int q = 100006;
    public final int r = 100007;
    public final int s = 100008;
    public final int t = 100009;
    public final int u = 100010;
    public final int v = 100011;
    public final int w = 100012;
    public final int x = 100013;
    public Handler y = new HandlerC0025a(Looper.myLooper());
    public ServiceConnection z = new b();
    public a.AbstractBinderC0026a A = new c();

    /* JADX INFO: renamed from: b.b.b.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HfpManage.java */
    public class HandlerC0025a extends Handler {
        public HandlerC0025a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = 0;
            switch (message.what) {
                case 100001:
                    int i2 = message.arg1;
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).e(i2);
                        i++;
                    }
                    return;
                case 100002:
                    int i3 = message.arg1;
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).f(i3);
                        i++;
                    }
                    return;
                case 100003:
                    Bundle data = message.getData();
                    String string = data.getString("title");
                    String string2 = data.getString("artist");
                    String string3 = data.getString("album");
                    String string4 = data.getString("iconUrl");
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).c(string, string2, string3, string4);
                        i++;
                    }
                    return;
                case 100004:
                    long jLongValue = ((Long) message.obj).longValue();
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).n(jLongValue);
                        i++;
                    }
                    return;
                case 100005:
                    long jLongValue2 = ((Long) message.obj).longValue();
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).m(jLongValue2);
                        i++;
                    }
                    return;
                case 100006:
                    int i4 = message.arg1;
                    while (i < a.this.h.size()) {
                        ((b.b.b.a.c) a.this.h.get(i)).k(i4);
                        i++;
                    }
                    return;
                case 100007:
                    int i5 = message.arg1;
                    while (i < a.this.i.size()) {
                        ((b.b.b.a.b) a.this.i.get(i)).o(i5);
                        i++;
                    }
                    return;
                case 100008:
                    List<BtCallInfo> list = (List) message.obj;
                    while (i < a.this.j.size()) {
                        ((b.b.b.a.a) a.this.j.get(i)).b(list);
                        i++;
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
                    if (aVar.a(aVar.f585e)) {
                        return;
                    }
                    a.this.y.sendEmptyMessageDelayed(100013, 800L);
                    return;
            }
            while (i < a.this.k.size()) {
                ((d) a.this.k.get(i)).j(message.arg1);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.f584d = b.a.d0(iBinder);
            int i = 1;
            a.this.g = true;
            Log.d("DANDAN", "onServiceConnected======");
            try {
                a.this.f584d.L(a.this.A);
                Message messageObtainMessage = a.this.y.obtainMessage();
                messageObtainMessage.what = 100001;
                if (!a.this.f584d.z()) {
                    i = 0;
                }
                messageObtainMessage.arg1 = i;
                a.this.y.sendMessage(messageObtainMessage);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                Log.d("DANDAN", "onServiceDisconnected======");
                a.this.g = false;
                a.this.f584d = null;
                a.this.y.sendEmptyMessageDelayed(100013, 800L);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: HfpManage.java */
    public class c extends a.AbstractBinderC0026a {
        public c() {
        }

        @Override // b.b.c.a
        public void H(BtCallInfo btCallInfo) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100009;
            messageObtainMessage.obj = btCallInfo;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void J(int i) throws RemoteException {
        }

        @Override // b.b.c.a
        public void Y(int i) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100010;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void b(List<BtCallInfo> list) throws RemoteException {
            Log.d("DANDAN", "CallState_Change ======================" + list.size());
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100008;
            messageObtainMessage.obj = list;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void c(String str, String str2, String str3, String str4) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            Bundle bundle = new Bundle();
            messageObtainMessage.what = 100003;
            bundle.putString("title", str);
            bundle.putString("artist", str2);
            bundle.putString("album", str3);
            bundle.putString("iconUrl", str4);
            messageObtainMessage.setData(bundle);
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void e(int i) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100001;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void f(int i) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100002;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void j(int i) throws RemoteException {
            Log.d("DANDAN", "SyncContactsState===================================== = state = " + i);
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100012;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void k(int i) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100006;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void m(long j) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100005;
            messageObtainMessage.obj = Long.valueOf(j);
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void n(long j) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100004;
            messageObtainMessage.obj = Long.valueOf(j);
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void o(int i) throws RemoteException {
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100007;
            messageObtainMessage.arg1 = i;
            a.this.y.sendMessage(messageObtainMessage);
        }

        @Override // b.b.c.a
        public void t(boolean z) throws RemoteException {
        }

        @Override // b.b.c.a
        public void w(boolean z) throws RemoteException {
            a.this.f586f = z;
            Message messageObtainMessage = a.this.y.obtainMessage();
            messageObtainMessage.what = 100011;
            messageObtainMessage.obj = Boolean.valueOf(z);
            a.this.y.sendMessage(messageObtainMessage);
        }
    }

    public static a o() {
        if (f581a == null) {
            synchronized (a.class) {
                if (f581a == null) {
                    f581a = new a();
                }
            }
        }
        return f581a;
    }

    public final boolean a(String str) {
        Log.d("DANDAN", "BindBTService ============");
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.byd.bluetoothservice.BTService");
        return this.f583c.bindService(intent, this.z, 1);
    }

    public int n() {
        b.b.c.b bVar;
        try {
            if (!this.g || (bVar = this.f584d) == null) {
                return 0;
            }
            return bVar.V();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
