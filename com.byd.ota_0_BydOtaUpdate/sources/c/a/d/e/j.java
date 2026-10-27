package c.a.d.e;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.DiagnosticInfo;
import android.hardware.bydauto.ota.EcuInfo;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import c.a.b.d.l;
import c.a.h.b.b;
import com.byd.repository.bean.request.RequestGetDTCInfo;
import com.byd.repository.bean.request.RequestGetEcuDiagnosticInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: OtaTestCommandStub.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2395a = j.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2396b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile boolean f2397c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f2398d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f2399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f2400f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.d.a f2401g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.g.d f2402h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.a.g.c f2403i;
    public c.a.a.a j;
    public BYDAutoOtaDevice k;
    public BYDAutoBodyworkDevice l;
    public BYDAutoGearboxDevice m;
    public BYDAutoChargingDevice n;
    public OtaUpgradeManager o;
    public Bundle p;
    public c.a.h.b.a q;

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class a extends AbsBYDAutoOtaListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f2404a;

        public a(Object obj) {
            this.f2404a = obj;
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void on7dfExistState(boolean z) {
            super.on7dfExistState(z);
            synchronized (this.f2404a) {
                if (j.this.q != null) {
                    j.this.M("onCallback", String.valueOf(z));
                }
                this.f2404a.notifyAll();
            }
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onGetAQSlot(int i2) {
            super.onGetAQSlot(i2);
            if (j.this.q != null) {
                j.this.M("onCallback", String.valueOf(i2));
            }
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class c extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2407a;

        public c(int i2) {
            this.f2407a = i2;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "case 32->onQnxOtaState resultCode = " + i2);
            j.this.j.n(this.f2407a == 1);
            if (j.this.q != null) {
                j.this.M("onCallback", String.valueOf(i2));
            }
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class d extends AbsMessageCallback {
        public d() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "case 33->onAndroidOtaState resultCode = " + i2);
            if (j.this.q != null) {
                j.this.M("onCallback", String.valueOf(i2));
            }
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class e extends AbsMessageCallback {
        public e() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onSetAQSlot(int i2) {
            super.onSetAQSlot(i2);
            if (j.this.q != null) {
                j.this.M("onCallback", String.valueOf(i2));
            }
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class f extends AbsMessageCallback {
        public f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            Toast.makeText(j.this.f2400f, "getEcuInfo have completed!", 1).show();
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onEcuInfo(List<EcuInfo> list) {
            super.onEcuInfo(list);
            boolean unused = j.f2396b = false;
            l.d().i(j.this.f2399e);
            if (list == null || list.size() < 0) {
                c.a.b.d.e.e(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList is null or size < 0");
                return;
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList.size() = " + list.size());
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (EcuInfo ecuInfo : list) {
                sb.append("ecuId = ");
                sb.append(ecuInfo.getEcuId());
                sb.append(",ecuSoftCode = ");
                String ecuConfigVer = "null";
                sb.append(TextUtils.isEmpty(ecuInfo.getEcuSoftCode()) ? "null" : ecuInfo.getEcuSoftCode());
                sb.append(",ecuSoftVer = ");
                sb.append(TextUtils.isEmpty(ecuInfo.getEcuSoftVer()) ? "null" : ecuInfo.getEcuSoftVer());
                sb.append(",ecuHardVer = ");
                sb.append(TextUtils.isEmpty(ecuInfo.getEcuHardVer()) ? "null" : ecuInfo.getEcuHardVer());
                sb.append(",ecuConfigCode = ");
                sb.append(TextUtils.isEmpty(ecuInfo.getEcuConfigCode()) ? "null" : ecuInfo.getEcuConfigCode());
                sb.append(",ecuConfigVer = ");
                if (!TextUtils.isEmpty(ecuInfo.getEcuConfigVer())) {
                    ecuConfigVer = ecuInfo.getEcuConfigVer();
                }
                sb.append(ecuConfigVer);
                sb.append("\n");
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onEcuInfo->" + sb.toString());
            l.d().b(new Runnable() { // from class: c.a.d.e.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2382c.b();
                }
            });
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class g extends AbsMessageCallback {
        public g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            Toast.makeText(j.this.f2400f, "getDiagnosticTroubleCode have completed!", 1).show();
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onGetDiagnosticTroubleCode(List<DiagnosticInfo> list) {
            super.onGetDiagnosticTroubleCode(list);
            boolean unused = j.f2397c = false;
            l.d().i(j.this.f2399e);
            if (list == null || list.size() < 0) {
                c.a.b.d.e.e(AbsMessageCallback.TAG, "onGetDiagnosticTroubleCode->infos is null or size < 0");
                return;
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onGetDiagnosticTroubleCode->infos.size() = " + list.size());
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (DiagnosticInfo diagnosticInfo : list) {
                sb.append("ecuId = ");
                sb.append(diagnosticInfo.getEcuId());
                sb.append(",ecuDiaId = ");
                sb.append(diagnosticInfo.getEcuDiaId());
                sb.append(",ecuReprogramType = ");
                sb.append(diagnosticInfo.getEcuReprogramType());
                sb.append(",readDtcInfoType = ");
                sb.append(diagnosticInfo.getReadDtcInfoType());
                sb.append(",ecuDtcInfo = ");
                sb.append(diagnosticInfo.getEcuDtcInfo());
                sb.append("\n");
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onGetDiagnosticTroubleCode->" + sb.toString());
            l.d().b(new Runnable() { // from class: c.a.d.e.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2383c.b();
                }
            });
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class h extends AbsMessageCallback {
        public h() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            Toast.makeText(j.this.f2400f, "clearDiagnosticTroubleCode have completed!", 1).show();
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onClearDiagnosticTroubleCode(List<DiagnosticInfo> list) {
            super.onClearDiagnosticTroubleCode(list);
            boolean unused = j.f2398d = false;
            l.d().i(j.this.f2399e);
            if (list == null || list.size() < 0) {
                c.a.b.d.e.e(AbsMessageCallback.TAG, "onClearDiagnosticTroubleCode->infos is null or size < 0");
                return;
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onClearDiagnosticTroubleCode->infos.size() = " + list.size());
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (DiagnosticInfo diagnosticInfo : list) {
                sb.append("ecuId = ");
                sb.append(diagnosticInfo.getEcuId());
                sb.append(",ecuDiaId = ");
                sb.append(diagnosticInfo.getEcuDiaId());
                sb.append(",ecuReprogramType = ");
                sb.append(diagnosticInfo.getEcuReprogramType());
                sb.append(",ecuDtcId = ");
                sb.append(diagnosticInfo.getEcuDtcId());
                sb.append("\n");
            }
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onClearDiagnosticTroubleCode->" + sb.toString());
            l.d().b(new Runnable() { // from class: c.a.d.e.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2384c.b();
                }
            });
        }
    }

    /* JADX INFO: compiled from: OtaTestCommandStub.java */
    public class i implements Runnable {
        public i() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            Toast.makeText(j.this.f2400f, "timeout!!!", 1).show();
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean unused = j.f2396b = false;
            boolean unused2 = j.f2397c = false;
            boolean unused3 = j.f2398d = false;
            c.a.b.d.e.c(j.f2395a, "TimeOutRunnable timeout!!!");
            l.d().b(new Runnable() { // from class: c.a.d.e.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2385c.b();
                }
            });
        }

        public /* synthetic */ i(j jVar, a aVar) {
            this();
        }
    }

    public j(Context context, c.a.g.c cVar, c.a.a.a aVar, c.a.d.a aVar2, c.a.g.d dVar, OtaUpgradeManager otaUpgradeManager) {
        this.f2400f = context;
        this.f2401g = aVar2;
        this.f2402h = dVar;
        this.f2403i = cVar;
        this.j = aVar;
        this.o = otaUpgradeManager;
        this.k = BYDAutoOtaDevice.getInstance(context);
        this.l = BYDAutoBodyworkDevice.getInstance(context);
        this.m = BYDAutoGearboxDevice.getInstance(context);
        this.n = BYDAutoChargingDevice.getInstance(context);
    }

    public final void M(String str, String str2) {
        try {
            Bundle bundle = new Bundle();
            this.p = bundle;
            bundle.putString(str, str2);
            this.q.b(this.p);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public final void N() {
        ArrayList arrayList = new ArrayList();
        c.a.b.d.e.c(f2395a, "clearDiagnosticTroubleCode->diagnosticInfoList.size() = " + arrayList.size());
        f2398d = true;
        this.f2399e = new i(this, null);
        l.d().c(this.f2399e, (long) (arrayList.size() * 3 * 1000));
        this.k.clearDiagnosticTroubleCode(arrayList, new h());
    }

    public final void O() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RequestGetDTCInfo("STEBU_ACCP_R_BYD_00100001", "714/71C"));
        c.a.b.d.e.c(f2395a, "getDiagnosticTroubleCode->diagnosticInfoList.size() = " + arrayList.size());
        f2397c = true;
        this.f2399e = new i(this, null);
        l.d().c(this.f2399e, (long) (arrayList.size() * 3 * 1000));
        this.k.getDiagnosticTroubleCode(arrayList, new g());
    }

    public final void P() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RequestGetEcuDiagnosticInfo("STEBU_TBOX_Neusoft_00100001", "7A3/7AB", "F195/F194", "F193", ""));
        arrayList.add(new RequestGetEcuDiagnosticInfo("STEBU_TBOX_Desay_00100001", "7A3/7AB", "F195/F194", "F193", ""));
        arrayList.add(new RequestGetEcuDiagnosticInfo("STEBU_Media_Autolink_00100001", "741/749", "F195/F194", "F193", ""));
        c.a.b.d.e.c(f2395a, "getEcuInfo->list.size() = " + arrayList.size());
        f2396b = true;
        this.f2399e = new i(this, null);
        l.d().c(this.f2399e, (long) ((arrayList.size() * 7 * 1000) + BYDAutoEngineDevice.ENGINE_SPEED_MAX));
        this.k.getEcuInfo(arrayList, new f());
    }

    @Override // c.a.h.b.b
    public void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException {
    }

    @Override // c.a.h.b.b
    public void c(Bundle bundle) throws RemoteException {
        String str;
        if (bundle != null) {
            String string = bundle.getString("method_name", "");
            str = f2395a;
            c.a.b.d.e.c(str, "execute->method_name = " + string);
            string.hashCode();
            switch (string) {
                case "getDtc":
                    if (!f2397c) {
                        O();
                        break;
                    }
                    break;
                case "reboot":
                    this.f2401g.e1();
                    break;
                case "deleteOtaDir":
                    this.f2402h.f(false);
                    this.j.m(false, "OtaTestCommandStub deleteOtaDir");
                    c.a.b.d.e.c(str, "dealOtaTestBroadcast->deleteOtaDir");
                    String str2 = c.a.b.b.a.f2320a;
                    c.a.b.d.d.s("chmod", "777", str2);
                    String str3 = c.a.b.b.a.f2321b;
                    c.a.b.d.d.s("chmod", "777", str3);
                    c.a.b.d.d.s("chmod", "777", str3 + "strategy.json");
                    c.a.b.d.d.s("chmod", "777", str3 + "diagnoseEcuInfo.json");
                    c.a.b.d.d.s("chmod", "777", str3 + "submitReportInfo.json");
                    c.a.b.d.d.s("chmod", "777", str3 + "updateResultInfo.json");
                    c.a.b.d.d.g(str2);
                    break;
                case "getEcuInfo":
                    if (!f2396b) {
                        P();
                        break;
                    }
                    break;
                case "loginIn":
                    l lVarD = l.d();
                    c.a.d.a aVar = this.f2401g;
                    Objects.requireNonNull(aVar);
                    lVarD.b(new c.a.d.e.h(aVar));
                    if (!this.f2403i.n()) {
                        this.f2403i.i(true);
                        this.f2403i.d();
                        break;
                    }
                    break;
                case "showDialog":
                    l lVarD2 = l.d();
                    c.a.d.a aVar2 = this.f2401g;
                    Objects.requireNonNull(aVar2);
                    lVarD2.b(new c.a.d.e.a(aVar2));
                    break;
                case "controlPre":
                    this.j.l();
                    break;
                case "clearDtc":
                    if (!f2398d) {
                        N();
                        break;
                    }
                    break;
                case "remotePre":
                    this.f2403i.k();
                    break;
            }
        }
    }

    @Override // c.a.h.b.b
    public void n(c.a.h.b.a aVar) throws RemoteException {
        this.q = aVar;
    }

    @Override // c.a.h.b.b
    public String o(int i2) throws RemoteException {
        if (i2 == 17) {
            return String.valueOf(this.k.getChargeAndDischargeSystemState());
        }
        if (i2 == 37) {
            return String.valueOf(this.k.getBackLightState());
        }
        if (i2 == 42) {
            this.k.getAQSlot(new b());
            return "null";
        }
        switch (i2) {
            case 0:
                return String.valueOf(this.m.getEPBState());
            case 1:
                return String.valueOf(this.l.getEnergyType());
            case 2:
                return String.valueOf(this.l.getPowerLevel());
            case 3:
                return String.valueOf(this.l.getAntiTheftStatus());
            case 4:
                return String.valueOf(this.m.getGear());
            case 5:
                return String.valueOf(this.l.getDoorState(5));
            case 6:
                return String.valueOf(this.n.getBatteryManagementDeviceState());
            case 7:
                return String.valueOf(this.k.getVehicleSpeed());
            case 8:
                return String.valueOf(this.k.getTurnOffState());
            default:
                switch (i2) {
                    case 10:
                        return String.valueOf(this.k.getSmartPowerUsable());
                    case 11:
                        return String.valueOf(this.k.getSmartPowerState());
                    case 12:
                        return String.valueOf(this.k.getSmartPowerException());
                    case 13:
                        return String.valueOf(this.k.getDcState());
                    case 14:
                        return String.valueOf(this.k.getSocValue());
                    default:
                        switch (i2) {
                            case 19:
                                return String.valueOf(this.k.getDischargeMainContactorState());
                            case 20:
                                return String.valueOf(this.k.getIg1RelayState());
                            case 21:
                                return String.valueOf(this.k.getIg3RelayState());
                            case 22:
                                return String.valueOf(this.k.getIg4RelayState());
                            case 23:
                                return String.valueOf(this.k.getBcmOtaState());
                            case 24:
                                return String.valueOf(this.k.getProhibitPowerOnState());
                            case 25:
                                return String.valueOf(this.k.getLFDoorLockStatus());
                            case 26:
                                Object obj = new Object();
                                a aVar = new a(obj);
                                this.k.registerListener((AbsBYDAutoOtaListener) aVar);
                                this.k.get7dfExistState();
                                synchronized (obj) {
                                    try {
                                        obj.wait(1000L);
                                        this.k.unregisterListener((AbsBYDAutoOtaListener) aVar);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    break;
                                }
                                return "null";
                            default:
                                return "null";
                        }
                }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // c.a.h.b.b
    public String t(int i2, int i3) throws Throwable {
        switch (i2) {
            case 27:
                return String.valueOf(this.k.setSmartPower(i3));
            case 28:
                return String.valueOf(this.k.setEclPower(i3));
            case 29:
                return String.valueOf(this.k.setQuitSmartCharge(i3));
            case 30:
                return String.valueOf(this.k.setQuitChargeAndDischarge(i3));
            case 31:
                if (i3 != 66 && i3 != 77 && i3 != 88 && i3 != 99) {
                    Bundle bundle = new Bundle();
                    this.p = bundle;
                    bundle.putInt("otaModel", i3);
                    return String.valueOf(this.o.executeCMD(this.p));
                }
                if (i3 == 66) {
                    Settings.Global.putInt(this.f2400f.getContentResolver(), "adb_enabled", 0);
                } else if (i3 == 77) {
                    Settings.Global.putInt(this.f2400f.getContentResolver(), "adb_enabled", 1);
                } else if (i3 == 88) {
                    Settings.Global.putInt(this.f2400f.getContentResolver(), "adb_wifi_enabled", 0);
                } else if (i3 == 99) {
                    Settings.Global.putInt(this.f2400f.getContentResolver(), "adb_wifi_enabled", 1);
                }
                return "null";
            case 32:
                return String.valueOf(this.k.setOtaPower(i3));
            case 33:
                return String.valueOf(this.k.setMultimediaSelfUpdate(i3));
            case 34:
                return String.valueOf(this.k.setHighVoltageCmd(i3));
            case 35:
                return String.valueOf(this.k.setBackupAreaDownload(i3));
            case 36:
                return String.valueOf(this.k.setBackLightState(i3));
            case 37:
            case 42:
            default:
                return "null";
            case 38:
                if (i3 == 0) {
                    this.f2402h.f(false);
                    this.j.m(false, "OtaTestCommandStub case 31");
                } else if (i3 == 1) {
                    c.a.b.c.a.k().X(0);
                    c.a.b.c.a.k().W(0);
                    this.f2402h.f(true);
                }
                return "null";
            case 39:
                this.k.setQnxOtaState(i3, new c(i3));
                return "null";
            case 40:
                this.k.setAndroidOtaState(i3, new d());
                return "null";
            case 41:
                this.k.setAQSlot(i3, new e());
                return "null";
            case 43:
                c.a.g.l.l.l(i3 == 1);
                return String.valueOf(c.a.g.l.l.f());
        }
    }

    @Override // c.a.h.b.b
    public void u(c.a.h.b.a aVar) throws RemoteException {
        this.q = null;
    }

    @Override // c.a.h.b.b
    public String x(int i2, int i3) throws RemoteException {
        return i2 != 9 ? i2 != 18 ? i2 != 15 ? i2 != 16 ? "null" : String.valueOf(this.k.getDischargeState(i3)) : String.valueOf(this.k.getBatteryVoltage(i3)) : String.valueOf(this.k.getSmartChargeState(i3)) : String.valueOf(this.k.getBatteryTemp(i3));
    }
}
