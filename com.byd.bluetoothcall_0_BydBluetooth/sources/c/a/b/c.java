package c.a.b;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import c.a.a.e;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PbapBluetoothManage.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a */
    private static volatile c f2608a;
    private Context m;
    private c.a.a.a n;

    /* JADX INFO: renamed from: b */
    private String f2609b = "PbapBluetoothManage";

    /* JADX INFO: renamed from: c */
    private ArrayList<e> f2610c = new ArrayList<>();

    /* JADX INFO: renamed from: d */
    private Sync$SyncType f2611d = Sync$SyncType.PHONE_BOOK;

    /* JADX INFO: renamed from: e */
    private Sync$SyncState f2612e = Sync$SyncState.SYNC_END;
    private final int f = 10001;
    private final int g = 10002;
    private final int h = 10003;
    private final int i = 10004;
    private final int j = 10005;
    private final int k = 10006;
    private final int l = 10007;
    private Handler o = new a(Looper.getMainLooper());

    /* JADX INFO: compiled from: PbapBluetoothManage.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = 0;
            switch (message.what) {
                case 10001:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncState(c.this.f2611d, c.this.f2612e);
                        i++;
                    }
                    break;
                case 10002:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncPhoeBook_data((List) message.obj);
                        i++;
                    }
                    break;
                case 10003:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncCallLog_data((List) message.obj);
                        i++;
                    }
                    break;
                case 10004:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncCallLogOne_data((CallLogInfo) message.obj);
                        i++;
                    }
                    break;
                case 10005:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncProgress((ContactsInfo) message.obj, message.arg1, message.arg2);
                        i++;
                    }
                    break;
                case 10006:
                    if (c.this.n != null) {
                        c.this.n.d();
                    }
                    break;
                case 10007:
                    while (i < c.this.f2610c.size()) {
                        ((e) c.this.f2610c.get(i)).SyncBookTotal(message.arg1);
                        i++;
                    }
                    break;
            }
        }
    }

    public static c t() {
        if (f2608a == null) {
            synchronized (c.class) {
                if (f2608a == null) {
                    f2608a = new c();
                }
            }
        }
        return f2608a;
    }

    public void A(e eVar) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.f2610c.size()) {
                break;
            }
            if (this.f2610c.get(i) == eVar) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.f2610c.add(eVar);
    }

    public void B(e eVar) {
        for (int i = 0; i < this.f2610c.size(); i++) {
            if (this.f2610c.get(i) == eVar) {
                this.f2610c.remove(i);
                return;
            }
        }
    }

    public void a() {
        c.a.a.a aVar = this.n;
        if (aVar != null) {
            aVar.f();
        }
    }

    public CallLogInfo b(String str) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.g(str, bluetoothDeviceE0.getAddress());
    }

    public List<CallLogInfo> c(String str) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.e(str, bluetoothDeviceE0.getAddress());
    }

    public List<ContactsInfo> d(String str) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.i(str, bluetoothDeviceE0.getAddress());
    }

    public void e() {
        c.a.a.a aVar = this.n;
        if (aVar != null) {
            aVar.c();
        }
    }

    public void f(CallLogInfo callLogInfo) {
        c.a.a.a aVar = this.n;
        if (aVar != null) {
            aVar.b(callLogInfo);
        }
    }

    public void g() {
        c.a.a.a aVar = this.n;
        if (aVar != null) {
            aVar.d();
        }
    }

    public void h(int i) {
        for (int i2 = 0; i2 < this.f2610c.size(); i2++) {
            this.f2610c.get(i2).SyncBookTotal(i);
        }
    }

    public void i(List<ContactsInfo> list) {
        Message messageObtainMessage = this.o.obtainMessage();
        messageObtainMessage.what = 10002;
        messageObtainMessage.obj = list;
        this.o.sendMessage(messageObtainMessage);
    }

    public void j(List<ContactsInfo> list) {
        Message messageObtainMessage = this.o.obtainMessage();
        messageObtainMessage.what = 10002;
        messageObtainMessage.obj = list;
        this.o.sendMessage(messageObtainMessage);
    }

    public void k(CallLogInfo callLogInfo) {
        CallLogInfo callLogInfo2 = new CallLogInfo();
        callLogInfo2.w(callLogInfo.n());
        callLogInfo2.z(callLogInfo.q());
        callLogInfo2.A(callLogInfo.r());
        callLogInfo2.v(callLogInfo.m());
        callLogInfo2.x(callLogInfo.o());
        Message messageObtainMessage = this.o.obtainMessage();
        messageObtainMessage.what = 10004;
        messageObtainMessage.obj = callLogInfo2;
        this.o.sendMessage(messageObtainMessage);
    }

    public void l(List<CallLogInfo> list) {
        Message messageObtainMessage = this.o.obtainMessage();
        messageObtainMessage.what = 10003;
        messageObtainMessage.obj = list;
        this.o.sendMessage(messageObtainMessage);
    }

    public void m(ContactsInfo contactsInfo, int i, int i2) {
        for (int i3 = 0; i3 < this.f2610c.size(); i3++) {
            this.f2610c.get(i3).SyncProgress(contactsInfo, i, i2);
        }
    }

    public void n(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
        this.f2611d = sync$SyncType;
        this.f2612e = sync$SyncState;
        for (int i = 0; i < this.f2610c.size(); i++) {
            this.f2610c.get(i).SyncState(this.f2611d, this.f2612e);
        }
    }

    public void s(String str) {
        c.a.a.a aVar = this.n;
        if (aVar != null) {
            aVar.h(str);
        }
    }

    public void u(Context context) {
        this.m = context;
    }

    public List<ContactsInfo> v(String str) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.a(str, bluetoothDeviceE0.getAddress());
    }

    public List<CallLogInfo> w(Sync$SyncType sync$SyncType) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.l(sync$SyncType, bluetoothDeviceE0.getAddress());
    }

    public CallLogInfo x() {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.k(bluetoothDeviceE0.getAddress());
    }

    public List<ContactsInfo> y(String str) {
        c.a.a.a aVar;
        BluetoothDevice bluetoothDeviceE0 = b.f0().e0();
        if (bluetoothDeviceE0 == null || (aVar = this.n) == null) {
            return null;
        }
        return aVar.j(str, bluetoothDeviceE0.getAddress());
    }

    public void z(c.a.a.a aVar) {
        this.n = aVar;
    }
}
