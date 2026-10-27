package ts.car.diag.b;

import android.content.Context;
import c.a.a.a.f;
import c.a.a.a.g;
import c.a.a.a.h;
import com.ts.lib.pki.PkiInstallManager;
import com.ts.lib.pki.bean.PkiReadReq;
import com.ts.lib.pki.bean.PkiReadResp;
import com.ts.lib.pki.bean.PkiRoutineControlReq;
import com.ts.lib.pki.bean.PkiRoutineControlResp;
import com.ts.lib.pki.bean.PkiWriteReq;
import com.ts.lib.pki.bean.PkiWriteResp;
import com.ts.lib.pki.callback.PkiInstallCallBack;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a {
    public static List<Short> d = new ArrayList();
    public static List<Short> e = new ArrayList();
    public static List<Short> f = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private C0051a f1552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f1553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PkiInstallManager f1554c;

    /* JADX INFO: renamed from: ts.car.diag.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0051a implements PkiInstallCallBack {
        C0051a() {
        }

        public void onConnectState(boolean z) {
            q.g("onConnectState " + z);
        }

        public void onPkiReadResp(PkiReadResp pkiReadResp) {
            if (pkiReadResp != null) {
                a.this.k(pkiReadResp);
            }
        }

        public void onPkiRoutineControlResp(PkiRoutineControlResp pkiRoutineControlResp) {
            if (pkiRoutineControlResp != null) {
                a.this.l(pkiRoutineControlResp);
            }
        }

        public void onPkiWriteResp(PkiWriteResp pkiWriteResp) {
            if (pkiWriteResp != null) {
                a.this.m(pkiWriteResp);
            }
        }
    }

    static {
        d.add((short) 8960);
        d.add((short) -3700);
        d.add((short) 8964);
        d.add((short) 9216);
        f.add((short) 8961);
        f.add((short) 8962);
        f.add((short) 8963);
        f.add((short) 8965);
        f.add((short) 8966);
        f.add((short) 8967);
        f.add((short) 8968);
        f.add((short) 8969);
        f.add((short) 8976);
        f.add((short) 8977);
        e.add((short) 8960);
        e.add((short) 8961);
        e.add((short) 8962);
        e.add((short) 8963);
        e.add((short) 8964);
        e.add((short) 8965);
        e.add((short) 8966);
        e.add((short) 8967);
        e.add((short) 8968);
        e.add((short) 8969);
        e.add((short) 8976);
        e.add((short) 8977);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(PkiReadResp pkiReadResp) {
        if (this.f1553b == null) {
            q.b("DiagPkiManager", "sendReadRespToHal mService == null");
            return;
        }
        try {
            c.a.a.a.b bVar = new c.a.a.a.b();
            bVar.f1282b = pkiReadResp.getRes();
            bVar.f1281a = pkiReadResp.getDid();
            bVar.d = pkiReadResp.getData();
            bVar.f1283c = (short) pkiReadResp.getData().size();
            q.h("DiagPkiManager", "sendReadRespToHal" + ((int) bVar.f1281a));
            this.f1553b.f(bVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(PkiRoutineControlResp pkiRoutineControlResp) {
        if (this.f1553b == null) {
            q.b("DiagPkiManager", "sendRoutineControlRespToHal mService == null");
            return;
        }
        try {
            c.a.a.a.d dVar = new c.a.a.a.d();
            dVar.f1287a = pkiRoutineControlResp.getControlType();
            dVar.f1288b = pkiRoutineControlResp.getRid();
            dVar.f1289c = pkiRoutineControlResp.getStatusRecord();
            q.h("DiagPkiManager", "sendRoutineControlRespToHal" + ((int) dVar.f1288b));
            this.f1553b.s(dVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(PkiWriteResp pkiWriteResp) {
        if (this.f1553b == null) {
            q.b("DiagPkiManager", "sendWriteRespToHal mService == null");
            return;
        }
        try {
            g gVar = new g();
            gVar.f1294a = pkiWriteResp.getDid();
            gVar.f1295b = pkiWriteResp.getRes();
            q.h("DiagPkiManager", "sendWriteRespToHal" + ((int) gVar.f1294a));
            this.f1553b.k(gVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d(Context context, h hVar) {
        this.f1552a = new C0051a();
        this.f1553b = hVar;
        PkiInstallManager pkiInstallManager = PkiInstallManager.getInstance(context.getApplicationContext());
        this.f1554c = pkiInstallManager;
        pkiInstallManager.registerCallBack(this.f1552a);
    }

    public boolean e(short s) {
        boolean zContains = d.contains(Short.valueOf(s));
        q.a("DiagPkiManager", " did " + ((int) s) + " isPkiReadDid " + zContains);
        return zContains;
    }

    public boolean f(short s) {
        boolean zContains = e.contains(Short.valueOf(s));
        q.a("DiagPkiManager", " did " + ((int) s) + " isPkiRoutineControlDid " + zContains);
        return zContains;
    }

    public boolean g(short s) {
        boolean zContains = f.contains(Short.valueOf(s));
        q.a("DiagPkiManager", " did " + ((int) s) + " isPkiWriteDid " + zContains);
        return zContains;
    }

    public void h(Context context) {
        PkiInstallManager pkiInstallManager = this.f1554c;
        if (pkiInstallManager != null) {
            pkiInstallManager.onDestroy();
        }
        q.h("DiagPkiManager", "onDestroy");
        PkiInstallManager.getInstance(context).unregisterCallBack(this.f1552a);
    }

    public int i(short s) {
        q.h("DiagPkiManager", "  readPkiData.rid " + Integer.toHexString(s));
        if (!this.f1554c.isConnect()) {
            this.f1554c.tryConnect();
            q.b("DiagPkiManager", " readPkiData ,PkiInstallManager has not connect");
            return 1;
        }
        PkiReadReq pkiReadReq = new PkiReadReq();
        pkiReadReq.setDid(s);
        q.h("DiagPkiManager", " readPkiData " + ((int) s));
        this.f1554c.readPkiData(pkiReadReq);
        return 0;
    }

    public int j(c.a.a.a.c cVar) {
        try {
            q.h("DiagPkiManager", "  routineControlReq.rid " + Integer.toHexString(cVar.f1285b) + " routineControlReq.controlType " + ((int) cVar.f1284a));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!this.f1554c.isConnect()) {
            this.f1554c.tryConnect();
            q.b("DiagPkiManager", " writePkiData ,PkiInstallManager has not connect");
            return 1;
        }
        PkiRoutineControlReq pkiRoutineControlReq = new PkiRoutineControlReq();
        pkiRoutineControlReq.setControlType(cVar.f1284a);
        pkiRoutineControlReq.setRid(cVar.f1285b);
        pkiRoutineControlReq.setOptionRecord(cVar.f1286c);
        this.f1554c.routineControlPkiData(pkiRoutineControlReq);
        return 0;
    }

    public void n(h hVar) {
        this.f1553b = hVar;
    }

    public int o(f fVar) {
        try {
            q.h("DiagPkiManager", "  routineControlReq.rid " + Integer.toHexString(fVar.f1291a));
            byte[] bArr = new byte[fVar.f1293c.size()];
            for (int i = 0; i < fVar.f1293c.size(); i++) {
                bArr[i] = fVar.f1293c.get(i).byteValue();
                q.a("DiagPkiManager", "  writePkiData data[i] " + ((int) bArr[i]));
            }
            q.h("DiagPkiManager", "  writePkiData " + new String(bArr, StandardCharsets.UTF_8));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!this.f1554c.isConnect()) {
            this.f1554c.tryConnect();
            q.b("DiagPkiManager", " writePkiData ,PkiInstallManager has not connect");
            return 1;
        }
        PkiWriteReq pkiWriteReq = new PkiWriteReq();
        pkiWriteReq.setData(fVar.f1293c);
        pkiWriteReq.setDid(fVar.f1291a);
        this.f1554c.writePkiData(pkiWriteReq);
        return 0;
    }
}
