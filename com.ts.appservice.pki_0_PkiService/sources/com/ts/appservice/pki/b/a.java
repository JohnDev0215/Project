package com.ts.appservice.pki.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ts.appservice.pki.c.d;
import com.ts.lib.pki.IPkiInstallManager;
import com.ts.lib.pki.bean.PkiReadReq;
import com.ts.lib.pki.bean.PkiReadResp;
import com.ts.lib.pki.bean.PkiRoutineControlReq;
import com.ts.lib.pki.bean.PkiRoutineControlResp;
import com.ts.lib.pki.bean.PkiWriteReq;
import com.ts.lib.pki.bean.PkiWriteResp;
import com.ts.lib.pki.callback.IPkiInstallCallBack;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a extends IPkiInstallManager.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.ts.appservice.pki.a.a f13a = new com.ts.appservice.pki.a.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f14b;
    private final HandlerThread c;
    private d d;
    private com.ts.appservice.pki.c.c e;

    /* JADX INFO: renamed from: com.ts.appservice.pki.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class RunnableC0000a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ PkiReadReq f15a;

        RunnableC0000a(PkiReadReq pkiReadReq) {
            this.f15a = pkiReadReq;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ts.appservice.pki.c.c cVar = a.this.e;
            short did = this.f15a.getDid();
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " readPkiData did " + Integer.toHexString(did));
            int i = 0;
            ArrayList<Byte> arrayListA = null;
            if (did == -3700) {
                String strF0 = a.this.d.f0();
                if (TextUtils.isEmpty(strF0)) {
                    i = 1;
                } else {
                    arrayListA = com.ts.appservice.pki.d.a.a(strF0.getBytes(StandardCharsets.US_ASCII));
                }
                a.this.i(this.f15a, i, arrayListA);
                return;
            }
            if (did == 8960) {
                ArrayList<Byte> arrayListU = cVar.u();
                a.this.i(this.f15a, a.this.g(arrayListU), arrayListU);
                return;
            }
            if (did == 8964) {
                ArrayList<Byte> arrayListS = cVar.s();
                a.this.i(this.f15a, a.this.g(arrayListS), arrayListS);
                return;
            }
            if (did != 9216) {
                a.this.i(this.f15a, 1, null);
                com.ts.appservice.pki.d.b.f("PkiInstallBinder", "readPkiData, has not this did " + Integer.toHexString(did));
                return;
            }
            String strA0 = a.this.d.a0();
            if (TextUtils.isEmpty(strA0)) {
                i = 1;
            } else {
                arrayListA = new ArrayList<>();
                for (int i2 = 0; i2 < strA0.length(); i2++) {
                    try {
                        arrayListA.add(Byte.valueOf((byte) (strA0.charAt(i2) - '0')));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", "ICCID byte data : " + arrayListA);
            a.this.i(this.f15a, i, arrayListA);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ PkiWriteReq f17a;

        b(PkiWriteReq pkiWriteReq) {
            this.f17a = pkiWriteReq;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            com.ts.appservice.pki.c.c cVar = a.this.e;
            short did = this.f17a.getDid();
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writePkiData did " + Integer.toHexString(did));
            ArrayList<Byte> data = this.f17a.getData();
            boolean z = 0;
            z = 0;
            if (data != null) {
                if (did == 8976) {
                    boolean zN = cVar.N(data);
                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeDeviceRsaKey result " + zN);
                    z = zN;
                } else if (did != 8977) {
                    switch (did) {
                        case 8961:
                            boolean zS = cVar.S(data);
                            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeRootRsa2048Cert result " + zS);
                            z = zS;
                            break;
                        case 8962:
                            boolean zQ = cVar.Q(data);
                            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeIdentityRsa2048Cert result " + zQ);
                            z = zQ;
                            break;
                        case 8963:
                            boolean zL = cVar.L(data);
                            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeDeviceRsa2048Cert result " + zL);
                            z = zL;
                            break;
                        default:
                            switch (did) {
                                case 8965:
                                    boolean zR = cVar.R(data);
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeRootEcc256Cert result " + zR);
                                    z = zR;
                                    break;
                                case 8966:
                                    boolean zP = cVar.P(data);
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeIdentityEcc256Cert result " + zP);
                                    z = zP;
                                    break;
                                case 8967:
                                    boolean zK = cVar.K(data);
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeDeviceEcc256Cert result " + zK);
                                    z = zK;
                                    break;
                                case 8968:
                                    boolean zJ = cVar.J(data);
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeAesKey result " + zJ);
                                    z = zJ;
                                    break;
                                case 8969:
                                    boolean zM = cVar.M(data);
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writeDeviceRsaCert result " + zM);
                                    z = zM;
                                    break;
                                default:
                                    com.ts.appservice.pki.d.b.f("PkiInstallBinder", "writePkiData, has not this did " + Integer.toHexString(did));
                                    break;
                            }
                            break;
                    }
                } else {
                    boolean zO = cVar.O(data);
                    com.ts.appservice.pki.d.b.b("PkiInstallBinder", " writeEngineerPublicKey result " + zO);
                    z = zO;
                }
            }
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " writePkiData did " + Integer.toHexString(did) + " result " + z);
            a.this.k(this.f17a, z ^ 1);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ PkiRoutineControlReq f19a;

        c(PkiRoutineControlReq pkiRoutineControlReq) {
            this.f19a = pkiRoutineControlReq;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v0 */
        /* JADX WARN: Type inference failed for: r5v16 */
        /* JADX WARN: Type inference failed for: r5v17 */
        /* JADX WARN: Type inference failed for: r5v18 */
        /* JADX WARN: Type inference failed for: r5v19 */
        /* JADX WARN: Type inference failed for: r5v2 */
        /* JADX WARN: Type inference failed for: r5v20 */
        /* JADX WARN: Type inference failed for: r5v21 */
        /* JADX WARN: Type inference failed for: r5v22 */
        /* JADX WARN: Type inference failed for: r5v23 */
        /* JADX WARN: Type inference failed for: r5v24 */
        /* JADX WARN: Type inference failed for: r5v25 */
        /* JADX WARN: Type inference failed for: r5v26 */
        /* JADX WARN: Type inference failed for: r5v27 */
        /* JADX WARN: Type inference failed for: r5v28 */
        /* JADX WARN: Type inference failed for: r5v29 */
        /* JADX WARN: Type inference failed for: r5v30 */
        /* JADX WARN: Type inference failed for: r5v31 */
        @Override // java.lang.Runnable
        public void run() {
            com.ts.appservice.pki.c.c cVar = a.this.e;
            short rid = this.f19a.getRid();
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", " routineControlPkiData did " + Integer.toHexString(rid));
            byte controlType = this.f19a.getControlType();
            ?? F = 0;
            F = 0;
            F = 0;
            if (rid == 8976) {
                F = cVar.f();
            } else if (rid != 8977) {
                switch (rid) {
                    case 8960:
                        if (controlType == 1) {
                            F = cVar.C();
                        } else if (controlType == 3) {
                            F = cVar.v();
                        }
                        break;
                    case 8961:
                        F = cVar.l();
                        break;
                    case 8962:
                        F = cVar.j();
                        break;
                    case 8963:
                        F = cVar.d();
                        break;
                    case 8964:
                        if (controlType == 1) {
                            F = cVar.B();
                        } else if (controlType == 3) {
                            F = cVar.t();
                        }
                        break;
                    case 8965:
                        F = cVar.k();
                        break;
                    case 8966:
                        F = cVar.i();
                        break;
                    case 8967:
                        F = cVar.c();
                        break;
                    case 8968:
                        F = cVar.a();
                        break;
                    case 8969:
                        F = cVar.e();
                        break;
                    default:
                        com.ts.appservice.pki.d.b.f("PkiInstallBinder", "routineControlPkiData, has not this did " + Integer.toHexString(rid));
                        break;
                }
            } else {
                F = cVar.h();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(Byte.valueOf((byte) (F ^ 1)));
            a.this.j(this.f19a, arrayList);
        }
    }

    public a() {
        HandlerThread handlerThread = new HandlerThread("com.ts.appservice.pki.PkiInstallBinder");
        this.c = handlerThread;
        handlerThread.start();
        this.f14b = new Handler(this.c.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int g(ArrayList<Byte> arrayList) {
        return (arrayList == null || arrayList.size() <= 0) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(PkiReadReq pkiReadReq, int i, ArrayList<Byte> arrayList) {
        PkiReadResp pkiReadResp = new PkiReadResp();
        short did = pkiReadReq.getDid();
        pkiReadResp.setDid(did);
        pkiReadResp.setData(arrayList);
        pkiReadResp.setRes(i);
        StringBuilder sb = new StringBuilder();
        sb.append(" notifyReadResult did ");
        sb.append(Integer.toHexString(did));
        sb.append(" result ");
        sb.append(i);
        sb.append(" data is ");
        sb.append(arrayList != null ? "not" : "");
        sb.append(" null");
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", sb.toString());
        this.f13a.a(pkiReadResp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(PkiRoutineControlReq pkiRoutineControlReq, ArrayList<Byte> arrayList) {
        PkiRoutineControlResp pkiRoutineControlResp = new PkiRoutineControlResp();
        pkiRoutineControlResp.setControlType(pkiRoutineControlReq.getControlType());
        short rid = pkiRoutineControlReq.getRid();
        pkiRoutineControlResp.setRid(rid);
        pkiRoutineControlResp.setStatusRecord(arrayList);
        StringBuilder sb = new StringBuilder();
        sb.append(" notifyRoutineControlResult rid ");
        sb.append(Integer.toHexString(rid));
        sb.append(" data is ");
        sb.append(arrayList != null ? "not" : "");
        sb.append(" null");
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", sb.toString());
        this.f13a.c(pkiRoutineControlResp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(PkiWriteReq pkiWriteReq, int i) {
        PkiWriteResp pkiWriteResp = new PkiWriteResp();
        pkiWriteResp.setRes(i);
        short did = pkiWriteReq.getDid();
        pkiWriteResp.setDid(did);
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", " notifyWriteResult did " + Integer.toHexString(did) + " result " + i);
        this.f13a.b(pkiWriteResp);
    }

    public void h(d dVar, com.ts.appservice.pki.c.c cVar) {
        this.d = dVar;
        this.e = cVar;
    }

    public void l() {
        HandlerThread handlerThread = this.c;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        com.ts.appservice.pki.a.a aVar = this.f13a;
        if (aVar != null) {
            aVar.d();
        }
    }

    public void readPkiData(PkiReadReq pkiReadReq) {
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", "readPkiData " + pkiReadReq);
        if (pkiReadReq != null) {
            this.f14b.post(new RunnableC0000a(pkiReadReq));
        } else {
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", "readPkiData readReq is null");
        }
    }

    public void registerCallBack(IPkiInstallCallBack iPkiInstallCallBack) {
        this.f13a.e(iPkiInstallCallBack);
    }

    public void routineControlPkiData(PkiRoutineControlReq pkiRoutineControlReq) {
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", "writePkiData " + pkiRoutineControlReq);
        if (pkiRoutineControlReq != null) {
            this.f14b.post(new c(pkiRoutineControlReq));
        } else {
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", "writePkiData readReq is null");
        }
    }

    public void unregisterCallBack(IPkiInstallCallBack iPkiInstallCallBack) {
        this.f13a.f(iPkiInstallCallBack);
    }

    public void writePkiData(PkiWriteReq pkiWriteReq) {
        com.ts.appservice.pki.d.b.f("PkiInstallBinder", "writePkiData " + pkiWriteReq);
        if (pkiWriteReq != null) {
            this.f14b.post(new b(pkiWriteReq));
        } else {
            com.ts.appservice.pki.d.b.f("PkiInstallBinder", "writePkiData readReq is null");
        }
    }
}
