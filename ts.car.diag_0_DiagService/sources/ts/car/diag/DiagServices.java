package ts.car.diag;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import c.a.a.a.f;
import c.a.a.a.g;
import c.a.a.a.i;
import com.byd.mediacenter.api.media.MediaControl;
import com.ts.diag.AidlShort;
import com.ts.diag.DidConfigurationWordInfo;
import com.ts.diag.DtcData;
import com.ts.diag.IDiag;
import com.ts.diag.IDiagOtaCallBack;
import com.ts.diag.IDiagReportCallBack;
import com.ts.diag.ReadResp;
import com.ts.diag.Subscribe;
import com.ts.diag.WriteReq;
import com.ts.diag.WriteResp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import ts.car.diag.b.e;
import ts.car.diag.manager.GwBtManagerImpl;
import ts.car.diag.manager.Receiver;
import ts.car.diag.manager.h;
import ts.car.diag.manager.i;
import ts.car.diag.manager.j;
import ts.car.diag.manager.l;
import ts.car.diag.manager.m;
import ts.car.diag.manager.n;
import ts.car.diag.manager.o;
import ts.car.diag.manager.q;
import ts.car.diag.manager.r;
import ts.car.diag.manager.s;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class DiagServices extends Service implements IHwBinder.DeathRecipient {
    private static DiagServices G;
    private ts.car.diag.c.a A;
    private ts.car.diag.b.a B;
    private HashMap<Short, HashMap<Integer, ArrayList<Byte>>> C;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1538c;
    private ts.car.diag.b.c f;
    private e g;
    private ts.car.diag.b.d h;
    private o i;
    private i j;
    private j k;
    private n l;
    private h m;
    private m n;
    private l o;
    private r p;
    private c q;
    private c.a.a.a.h r;
    private GwBtManagerImpl.GwBtDeviceReciver s;
    private Handler t;
    private String v;
    private String w;
    private String x;
    private Receiver y;
    private Receiver z;
    private ArrayList<Short> d = new ArrayList<>();
    private ArrayList<Short> e = new ArrayList<>();
    public List<Subscribe> u = new ArrayList();
    private boolean D = false;
    private final IDiag.Stub E = new a();
    private s F = new b(this);

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends IDiag.Stub {

        /* JADX INFO: renamed from: ts.car.diag.DiagServices$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        class RunnableC0049a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1540c;
            final /* synthetic */ IDiagReportCallBack d;

            RunnableC0049a(String str, IDiagReportCallBack iDiagReportCallBack) {
                this.f1540c = str;
                this.d = iDiagReportCallBack;
            }

            @Override // java.lang.Runnable
            public void run() {
                Subscribe next;
                Iterator<Subscribe> it = DiagServices.this.u.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (next.getClassName().equals(this.f1540c)) {
                            break;
                        }
                    }
                }
                if (next != null) {
                    DiagServices.this.u.remove(next);
                }
                DiagServices.this.u.add(new Subscribe(this.d, this.f1540c));
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AidlShort f1541c;
            final /* synthetic */ String d;

            b(AidlShort aidlShort, String str) {
                this.f1541c = aidlShort;
                this.d = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DiagServices.this.A(this.f1541c, this.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class c implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ WriteReq f1542c;
            final /* synthetic */ String d;

            c(WriteReq writeReq, String str) {
                this.f1542c = writeReq;
                this.d = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DiagServices.this.C(this.f1542c, this.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class d implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ byte f1543c;
            final /* synthetic */ String d;

            d(byte b2, String str) {
                this.f1543c = b2;
                this.d = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DiagServices.this.B(this.f1543c, this.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        a() {
        }

        public int readDidConfigurationWordInfo(DidConfigurationWordInfo didConfigurationWordInfo) {
            q.h("DiagServices", "readDidConfigurationWordInfo DidConfigurationWordInfo : " + didConfigurationWordInfo);
            HashMap map = (HashMap) DiagServices.this.C.get(Short.valueOf(didConfigurationWordInfo.did));
            int i = -1;
            if (map != null) {
                try {
                    ArrayList arrayList = (ArrayList) map.get(Integer.valueOf(didConfigurationWordInfo.index));
                    int iByteValue = 0;
                    if (arrayList != null) {
                        for (int i2 = didConfigurationWordInfo.end; i2 >= didConfigurationWordInfo.begin; i2--) {
                            iByteValue = (iByteValue << 1) | ((Byte) arrayList.get(i2)).byteValue();
                        }
                        i = iByteValue;
                    } else {
                        q.b("DiagServices", "didByte.list == null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            q.h("DiagServices", "readDidConfigurationWordInfo res : " + i);
            return i;
        }

        public void readReport(AidlShort aidlShort, String str) {
            q.h("DiagServices", "readReport enter");
            if (DiagServices.this.r == null) {
                q.b("DiagServices", "readReport: service is null");
            } else {
                DiagServices.this.t.post(new b(aidlShort, str));
            }
        }

        public void registerDiagOtaCallback(IDiagOtaCallBack iDiagOtaCallBack) {
            ts.car.diag.a.a.a().c(iDiagOtaCallBack);
        }

        public void requestOta7dfState() {
            q.h("DiagServices", " requestOta7dfState state ");
            if (DiagServices.this.r == null) {
                q.b("DiagServices", "requestOta7dfState,service is null");
            } else {
                DiagServices.this.r.d(DiagServices.this.q);
            }
        }

        public void requestReportDTC(byte b2, String str) {
            DiagServices.this.t.post(new d(b2, str));
        }

        public void sendDtcNumberToHal(int i, int i2) {
            q.h("DiagServices", " sendDtcNumberToHal dtcNumber " + Integer.toHexString(i) + " result " + i2);
            if (DiagServices.this.r == null) {
                q.b("DiagServices", "sendDtcNumberToHal: service is null");
                return;
            }
            int iE = DiagServices.this.r.e(i, i2);
            StringBuilder sb = new StringBuilder();
            sb.append(" sendDtcNumberToHal ");
            sb.append(Integer.toHexString(i));
            sb.append(" reportDtcResult ");
            sb.append(iE);
            sb.append(" ");
            sb.append(iE == 0);
            q.h("DiagServices", sb.toString());
        }

        public void subscribe(IDiagReportCallBack iDiagReportCallBack, String str) {
            q.h("DiagServices", "subscribe className:" + str);
            DiagServices.this.t.post(new RunnableC0049a(str, iDiagReportCallBack));
        }

        public void unregisterDiagOtaCallback(IDiagOtaCallBack iDiagOtaCallBack) {
            ts.car.diag.a.a.a().d(iDiagOtaCallBack);
        }

        public void writeReport(WriteReq writeReq, String str) {
            q.h("DiagServices", "writeReport " + writeReq.toString());
            if (DiagServices.this.r == null) {
                q.b("DiagServices", "readReport: service is null");
            } else {
                DiagServices.this.t.post(new c(writeReq, str));
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements s {
        b(DiagServices diagServices) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c extends i.a {

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ short f1545c;

            a(short s) {
                this.f1545c = s;
            }

            @Override // java.lang.Runnable
            public void run() {
                DiagServices.this.f.j(this.f1545c);
                q.h("DiagServices", "Read  halReadFromFramework " + Integer.toHexString(this.f1545c));
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ f f1546c;

            b(f fVar) {
                this.f1546c = fVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                DiagServices.this.g.m(this.f1546c, this.f1546c.f1293c.get(0).byteValue());
                q.h("DiagServices", "Write  halWriteToFramework " + Integer.toHexString(this.f1546c.f1291a));
            }
        }

        /* JADX INFO: renamed from: ts.car.diag.DiagServices$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        class RunnableC0050c implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c.a.a.a.c f1547c;

            RunnableC0050c(c.a.a.a.c cVar) {
                this.f1547c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                DiagServices.this.h.z(this.f1547c);
                q.h("DiagServices", "RoutineControl  handleRoutineControl " + Integer.toHexString(this.f1547c.f1285b));
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class d implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c.a.a.a.b f1548c;

            d(c.a.a.a.b bVar) {
                this.f1548c = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                DiagServices diagServices = DiagServices.this;
                c.a.a.a.b bVar = this.f1548c;
                diagServices.t(bVar.f1281a, bVar.d);
            }
        }

        public c() {
        }

        @Override // c.a.a.a.i
        public void a(byte b2) {
            q.h("DiagServices", " diag_getDiagTesterStatusReport state " + ((int) b2));
            ts.car.diag.a.a.a().b(b2);
        }

        @Override // c.a.a.a.i
        public void h(c.a.a.a.a aVar, int i) {
            q.h("DiagServices", "diag_getDTCDataReport: diag_dtcData: " + aVar.toString());
            for (Subscribe subscribe : DiagServices.this.u) {
                if (subscribe.getClassName().equals(DiagServices.this.x)) {
                    DtcData dtcData = new DtcData();
                    ArrayList<String> arrayListU = DiagServices.this.u(aVar.f1280c);
                    q.h("DiagServices", "diag_getDTCDataReport: list: " + arrayListU);
                    arrayListU.addAll(DiagServices.this.p.j());
                    q.h("DiagServices", "diag_getDTCDataReport + netDtcData: list: " + arrayListU);
                    dtcData.setData(arrayListU);
                    dtcData.setStatus((byte) 0);
                    RemoteCallbackList remoteCallbackList = new RemoteCallbackList();
                    remoteCallbackList.register(subscribe.getiDiagReportCallBack());
                    int iBeginBroadcast = remoteCallbackList.beginBroadcast();
                    q.h("DiagServices", "diag_getDTCDataReport: dtcData: " + dtcData);
                    for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                        remoteCallbackList.getBroadcastItem(i2).readDTCDataReport(dtcData, MediaControl.PARAM_SUCCESS);
                    }
                    remoteCallbackList.finishBroadcast();
                }
            }
        }

        @Override // c.a.a.a.i
        public int i(short s) {
            q.h("DiagServices", "diag_readInfoFromClients " + Integer.toHexString(s));
            Integer num = 1;
            if (DiagServices.this.B.e(s)) {
                q.h("DiagServices", "isPkiReadDid");
                return Integer.valueOf(DiagServices.this.B.i(s)).intValue();
            }
            Iterator it = DiagServices.this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((Short) it.next()).equals(Short.valueOf(s))) {
                    num = 0;
                    break;
                }
            }
            DiagServices.this.t.postDelayed(new a(s), 500L);
            return num.intValue();
        }

        @Override // c.a.a.a.i
        public void j(g gVar) {
            q.h("DiagServices", "diag_writeInfoToHalReport: " + gVar.toString());
            for (Subscribe subscribe : DiagServices.this.u) {
                if (subscribe.getClassName().equals(DiagServices.this.w)) {
                    WriteResp writeResp = new WriteResp();
                    writeResp.setDid(gVar.f1294a);
                    writeResp.setRes(gVar.f1295b);
                    RemoteCallbackList remoteCallbackList = new RemoteCallbackList();
                    remoteCallbackList.register(subscribe.getiDiagReportCallBack());
                    int iBeginBroadcast = remoteCallbackList.beginBroadcast();
                    for (int i = 0; i < iBeginBroadcast; i++) {
                        remoteCallbackList.getBroadcastItem(i).writeInfoToHalReport(writeResp, MediaControl.PARAM_SUCCESS);
                    }
                    remoteCallbackList.finishBroadcast();
                }
            }
            if (gVar.f1295b != 0 || gVar.f1294a == -3679) {
                return;
            }
            DiagServices.this.r.g(DiagServices.this.q, gVar.f1294a);
        }

        @Override // c.a.a.a.i
        public int n(c.a.a.a.c cVar) {
            q.h("DiagServices", "diag_routineControlRequest");
            Integer num = 1;
            if (DiagServices.this.B.f(cVar.f1285b)) {
                q.h("DiagServices", "isPkiRoutineControlDid");
                return Integer.valueOf(DiagServices.this.B.j(cVar)).intValue();
            }
            Iterator it = DiagServices.this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((Short) it.next()).equals(Short.valueOf(cVar.f1285b))) {
                    num = 0;
                    break;
                }
            }
            DiagServices.this.t.postDelayed(new RunnableC0050c(cVar), 500L);
            return num.intValue();
        }

        @Override // c.a.a.a.i
        public void p(int i) {
        }

        @Override // c.a.a.a.i
        public int q(f fVar) {
            q.h("DiagServices", "diag_writeInfoToClients " + Integer.toHexString(fVar.f1291a));
            Integer num = 1;
            short s = fVar.f1291a;
            if (DiagServices.this.B.g(s)) {
                q.h("DiagServices", "isPkiWriteDid");
                return Integer.valueOf(DiagServices.this.B.o(fVar)).intValue();
            }
            Iterator it = DiagServices.this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((Short) it.next()).equals(Short.valueOf(s))) {
                    num = 0;
                    break;
                }
            }
            DiagServices.this.t.postDelayed(new b(fVar), 500L);
            return num.intValue();
        }

        @Override // c.a.a.a.i
        public void t(c.a.a.a.b bVar) {
            q.h("DiagServices", "diag_readInfoFromHalReport: " + bVar.toString());
            for (Subscribe subscribe : DiagServices.this.u) {
                if (subscribe.getClassName().equals(DiagServices.this.v)) {
                    ReadResp readResp = new ReadResp();
                    readResp.setDataLength(bVar.f1283c);
                    readResp.setDid(bVar.f1281a);
                    readResp.setData(bVar.d);
                    RemoteCallbackList remoteCallbackList = new RemoteCallbackList();
                    remoteCallbackList.register(subscribe.getiDiagReportCallBack());
                    int iBeginBroadcast = remoteCallbackList.beginBroadcast();
                    for (int i = 0; i < iBeginBroadcast; i++) {
                        remoteCallbackList.getBroadcastItem(i).readInfoFromHalReport(readResp, MediaControl.PARAM_SUCCESS);
                    }
                    remoteCallbackList.finishBroadcast();
                }
            }
            DiagServices.this.t.post(new d(bVar));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 109) {
                return;
            }
            DiagServices.this.G();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(AidlShort aidlShort, String str) {
        q.h("DiagServices", "readReport className " + str);
        for (Subscribe subscribe : this.u) {
            q.h("DiagServices", "readReport sub.getClassName() " + subscribe.getClassName());
            if (subscribe.getClassName().equals(str)) {
                this.v = str;
                short aidlShort2 = aidlShort.getAidlShort();
                q.h("DiagServices", "ReadReport did " + Integer.toHexString(aidlShort2));
                q.h("DiagServices", "ReadReport result : " + this.r.g(this.q, aidlShort2));
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(byte b2, String str) {
        Iterator<Subscribe> it = this.u.iterator();
        while (it.hasNext()) {
            if (it.next().getClassName().equals(str)) {
                this.x = str;
                q.h("DiagServices", "requestReportDTC mask " + ((int) b2));
                c.a.a.a.h hVar = this.r;
                if (hVar == null) {
                    q.b("DiagServices", "requestReportDTC: service is null");
                    return;
                }
                hVar.o(this.q, b2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(WriteReq writeReq, String str) {
        q.h("DiagServices", "writeReport className: " + str);
        for (Subscribe subscribe : this.u) {
            q.h("DiagServices", "writeReport sub.getClassName() " + subscribe.getClassName());
            if (subscribe.getClassName().equals(str)) {
                this.w = str;
                Short shValueOf = Short.valueOf((short) writeReq.getDid());
                q.h("DiagServices", "writeReport did: " + Integer.toHexString(shValueOf.shortValue()));
                f fVar = new f();
                fVar.f1291a = shValueOf.shortValue();
                fVar.f1292b = (short) writeReq.getDataLength();
                fVar.f1293c.addAll(writeReq.getData());
                q.h("DiagServices", "writeReport req: " + fVar.toString());
                q.h("DiagServices", "WriteReport result : " + this.r.l(this.q, fVar));
                return;
            }
        }
    }

    private void D() {
        this.s = new GwBtManagerImpl.GwBtDeviceReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        registerReceiver(this.s, intentFilter);
    }

    private void E() {
        this.y = new Receiver();
        registerReceiver(this.y, new IntentFilter("com.action.exportLogs"));
        this.z = new Receiver();
        registerReceiver(this.z, new IntentFilter("com.action.getExportLogsResult"));
    }

    public static void F(DiagServices diagServices) {
        G = diagServices;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        try {
            this.r = c.a.a.a.h.u();
            q.h("DiagServices", " tryConnectService mService " + this.r);
        } catch (Exception e) {
            q.b("DiagServices", "tryConnectService failed, msg:" + e.getMessage());
        }
        c.a.a.a.h hVar = this.r;
        if (hVar == null) {
            this.t.sendEmptyMessageDelayed(109, 1000L);
            return;
        }
        hVar.asBinder().linkToDeath(this, 0L);
        I();
        int iV = 0;
        try {
            iV = this.r.v(this.q, this.d, this.e);
        } catch (RemoteException e2) {
            q.b("DiagServices", "diag_subscribe failed, msg:" + e2.getMessage());
        }
        q.h("DiagServices", "subscribe status: " + iV);
    }

    private void H() {
        Receiver receiver = this.y;
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        Receiver receiver2 = this.z;
        if (receiver2 != null) {
            unregisterReceiver(receiver2);
        }
    }

    private void I() {
        q.h("DiagServices", "updateDiagService, hasCreated : " + this.D);
        if (this.D) {
            this.p.v(this.r);
            ts.car.diag.c.b.g().m(this.r);
            this.f.B(this.r);
            this.g.q(this.r);
            this.h.O(this.r);
            this.B.n(this.r);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(short s, ArrayList<Byte> arrayList) {
        HashMap<Integer, ArrayList<Byte>> map = this.C.get(Short.valueOf(s));
        if (map == null) {
            map = new HashMap<>();
            this.C.put(Short.valueOf(s), map);
        }
        q.h("DiagServices", " analysisDidData did : " + ((int) s) + " data : " + arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Byte> arrayList2 = new ArrayList<>(8);
            Byte b2 = arrayList.get(i);
            for (int i2 = 0; i2 < 8; i2++) {
                arrayList2.add(Byte.valueOf((byte) ((b2.byteValue() >> i2) & 1)));
            }
            map.put(Integer.valueOf(i), arrayList2);
            q.h("DiagServices", " analysisDidData index : " + i + " list : " + arrayList2);
        }
    }

    public static DiagServices w() {
        return G;
    }

    private void y() {
        this.f1538c = getApplicationContext();
        this.j = ts.car.diag.manager.i.h();
        this.i = o.a(this.f1538c);
        this.k = j.c(this.f1538c);
        this.i.k(this.f1538c);
        this.j.b(this.f1538c);
        D();
        this.k.f(this.f1538c);
        this.l = new n(this.f1538c, this.F);
        this.m = new h(this.f1538c);
        this.n = new m(this.f1538c);
        this.o = new l(this.f1538c);
        this.p = new r(this.f1538c);
        HandlerThread handlerThread = new HandlerThread("DiagServices");
        handlerThread.start();
        this.t = new d(handlerThread.getLooper());
        this.d.add((short) -718);
        this.d.add((short) -704);
        this.d.add((short) -698);
        this.d.add((short) -697);
        this.d.add((short) -687);
        this.d.add((short) -686);
        this.d.add((short) -685);
        this.d.add((short) -671);
        this.d.add((short) -670);
        this.d.add((short) -669);
        this.d.add((short) -668);
        this.d.add((short) -667);
        this.d.add((short) -633);
        this.d.add((short) -638);
        this.d.add((short) -638);
        this.d.addAll(ts.car.diag.b.a.d);
        this.d.addAll(ts.car.diag.b.a.f);
        this.d.add((short) 4660);
        this.d.add((short) -3706);
        this.e.add((short) -508);
        this.e.add((short) -480);
        this.e.add((short) -464);
        this.e.addAll(ts.car.diag.b.a.e);
        this.e.add((short) -414);
        this.e.add((short) 4660);
        this.d.add((short) -693);
        this.d.add((short) -692);
        this.d.add((short) -691);
        this.e.add((short) -504);
        this.e.add((short) -505);
        this.e.add((short) -476);
        this.e.add((short) -475);
        this.e.add((short) -474);
        this.e.add((short) 8979);
        this.d.add((short) -763);
        this.e.add((short) 8983);
    }

    private void z() {
        Context applicationContext = getApplicationContext();
        this.f = new ts.car.diag.b.c(this.r, applicationContext);
        this.g = new e(this.r, applicationContext);
        this.h = new ts.car.diag.b.d(this.r);
        this.f.n(this.i, this.j, this.k, this.l, this.m, this.n);
        this.g.n(this.i, this.j, this.k, this.l, this.m, this.n);
        this.h.N(applicationContext);
        this.h.A(this.i, this.j, this.k, this.l, this.m, this.n, this.o);
        ts.car.diag.b.a aVar = new ts.car.diag.b.a();
        this.B = aVar;
        aVar.d(this, this.r);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        q.h("DiagServices", "onBind ");
        return this.E;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        F(this);
        y();
        try {
            this.q = new c();
            q.h("DiagServices", "getServiceFromHal");
            G();
        } catch (Exception e) {
            e.printStackTrace();
        }
        z();
        E();
        ts.car.diag.c.a aVar = new ts.car.diag.c.a();
        this.A = aVar;
        aVar.b(this);
        this.p.k(this.r);
        ts.car.diag.c.b.g().h(this);
        ts.car.diag.c.b.g().l(this.q);
        ts.car.diag.c.b.g().m(this.r);
        ts.car.diag.c.b.g().n(this.B);
        q.f(this);
        this.C = new HashMap<>();
        try {
            this.r.g(this.q, (short) 8448);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        this.D = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        q.b("DiagServices", "onDestroy");
        c.a.a.a.h hVar = this.r;
        if (hVar != null) {
            try {
                hVar.m(this.q);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        GwBtManagerImpl.GwBtDeviceReciver gwBtDeviceReciver = this.s;
        if (gwBtDeviceReciver != null) {
            unregisterReceiver(gwBtDeviceReciver);
        }
        h hVar2 = this.m;
        if (hVar2 != null) {
            hVar2.c();
        }
        H();
        this.A.c();
        this.B.h(this);
        this.p.r();
        this.D = false;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.u.clear();
        q.h("DiagServices", "onUnbind ");
        return super.onUnbind(intent);
    }

    public void serviceDied(long j) {
        q.b("DiagServices", "binderDied: tryConnectService!!!");
        try {
            if (this.r != null) {
                this.r.m(this.q);
                if (this.r.asBinder() != null) {
                    this.r.asBinder().unlinkToDeath(this);
                }
            }
        } catch (Exception e) {
            q.b("DiagServices", "unlinkToDeath failed, msg:" + e.getMessage());
        }
        this.r = null;
        if (this.D) {
            this.p.v(null);
            ts.car.diag.c.b.g().m(null);
            this.f.B(null);
            this.g.q(null);
            this.h.O(null);
            this.B.n(null);
        }
        G();
    }

    public ArrayList<String> u(ArrayList<Byte> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        q.h("DiagServices", "byteToString list size = " + arrayList.size());
        try {
            if (arrayList.size() >= 4 && arrayList.size() % 4 == 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 4;
                    if (i2 > arrayList.size()) {
                        break;
                    }
                    arrayList2.add(v(Integer.toHexString(arrayList.get(i).byteValue() & 255)) + v(Integer.toHexString(arrayList.get(i + 1).byteValue() & 255)) + v(Integer.toHexString(arrayList.get(i + 2).byteValue() & 255)));
                    i = i2;
                }
            }
        } catch (Exception e) {
            q.b("DiagServices", e.getMessage());
            arrayList2.add(e.getMessage());
        }
        return arrayList2;
    }

    public String v(String str) {
        if (str.length() >= 2) {
            return str;
        }
        return "0" + str;
    }

    public void x(c.a.a.a.c cVar) {
    }
}
