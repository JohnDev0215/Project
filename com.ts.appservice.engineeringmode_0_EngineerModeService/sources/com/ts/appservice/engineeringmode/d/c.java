package com.ts.appservice.engineeringmode.d;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.SystemProperties;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ts.appservice.engineeringmode.bean.DtcBean;
import com.ts.diag.DtcData;
import com.ts.diag.ReadResp;
import com.ts.diag.WriteResp;
import com.ts.diag.manager.ConnectCallBack;
import com.ts.diag.manager.DiagManager;
import com.ts.diag.manager.DiagReportCallBack;
import com.ts.lib.engineeringmode.bean.TSDiagnoseDeviceStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ts.appservice.engineeringmode.a.a f37a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ts.appservice.engineeringmode.c.a f38b;
    Context c;
    private DiagManager d;
    private HashMap<String, DtcBean> e;
    private HashMap<String, String> g;
    private boolean h;
    private List<DtcBean> i;
    private HashMap<String, List<String>> f = new HashMap<>();
    ConnectCallBack j = new a();
    DiagReportCallBack k = new b();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements ConnectCallBack {
        a() {
        }

        public void onConnectState(boolean z) {
            c.this.f37a.c(z);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements DiagReportCallBack {
        b() {
        }

        public void readDTCDataReport(DtcData dtcData, String str) {
            com.ts.appservice.engineeringmode.e.a.g("DtcManager", "readDTCDataReport: dtcData " + dtcData.toString());
            c.this.i(dtcData);
            c.this.h = true;
            c.this.f38b.a();
        }

        public void readInfoFromHalReport(ReadResp readResp, String str) {
        }

        public void writeInfoToHalReport(WriteResp writeResp, String str) {
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.engineeringmode.d.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0004c extends TypeToken<ArrayList<DtcBean>> {
        C0004c(c cVar) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d extends TypeToken<HashMap<String, String>> {
        d(c cVar) {
        }
    }

    public c(com.ts.appservice.engineeringmode.c.a aVar, com.ts.appservice.engineeringmode.a.a aVar2, Context context) {
        this.f38b = aVar;
        this.f37a = aVar2;
        this.c = context;
        DiagManager diagManager = DiagManager.getInstance(context.getApplicationContext());
        this.d = diagManager;
        diagManager.registerConnectCallBack(this.j);
        this.d.registerDiagCallBack(this.k);
        g();
    }

    private void g() {
        AssetManager assets = this.c.getApplicationContext().getAssets();
        String strA = com.ts.appservice.engineeringmode.e.b.a(assets, "data.json");
        Gson gson = new Gson();
        this.i = (List) gson.fromJson(strA, new C0004c(this).getType());
        this.e = new HashMap<>();
        List<DtcBean> list = this.i;
        if (list != null) {
            for (DtcBean dtcBean : list) {
                this.e.put(dtcBean.getDtcByte(), dtcBean);
            }
        } else {
            com.ts.appservice.engineeringmode.e.a.c("DtcManager", "init mDtcBeanList is error");
        }
        HashMap<String, String> map = (HashMap) gson.fromJson(com.ts.appservice.engineeringmode.e.b.a(assets, "supplierName.json"), new d(this).getType());
        this.g = map;
        if (map == null) {
            this.g = new HashMap<>();
            com.ts.appservice.engineeringmode.e.a.c("DtcManager", "init mAllSupplierName is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(DtcData dtcData) {
        HashMap<String, List<String>> map = new HashMap<>();
        if (dtcData != null) {
            List<String> data = dtcData.getData();
            if (data != null && data.size() > 0) {
                for (String str : data) {
                    DtcBean dtcBean = this.e.get(str.toUpperCase());
                    if (dtcBean != null) {
                        String deviceType = dtcBean.getDeviceType();
                        if (!map.containsKey(deviceType)) {
                            map.put(deviceType, new ArrayList());
                        }
                        map.get(deviceType).add(dtcBean.getDtcType());
                    } else {
                        com.ts.appservice.engineeringmode.e.a.g("DtcManager", "has not this dtc number " + str);
                    }
                }
            }
            this.f = map;
        }
    }

    public boolean e() {
        return this.h;
    }

    public TSDiagnoseDeviceStatus f(String str) {
        List<String> list = this.f.get(str);
        com.ts.appservice.engineeringmode.e.a.g("DtcManager", "getTSDiagnoseDeviceStatus in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatus = new TSDiagnoseDeviceStatus();
        if (list != null) {
            for (String str2 : list) {
                byte b2 = -1;
                int iHashCode = str2.hashCode();
                if (iHashCode != 49) {
                    if (iHashCode != 57) {
                        if (iHashCode != 51) {
                            if (iHashCode == 52 && str2.equals("4")) {
                                b2 = 2;
                            }
                        } else if (str2.equals("3")) {
                            b2 = 1;
                        }
                    } else if (str2.equals("9")) {
                        b2 = 3;
                    }
                } else if (str2.equals("1")) {
                    b2 = 0;
                }
                if (b2 == 0) {
                    tSDiagnoseDeviceStatus.setShortOut(true);
                } else if (b2 == 1) {
                    tSDiagnoseDeviceStatus.setConnection(false);
                } else if (b2 == 2) {
                    tSDiagnoseDeviceStatus.setCuttingOut(true);
                } else if (b2 == 3) {
                    tSDiagnoseDeviceStatus.setOverCurrent(true);
                }
            }
        }
        String str3 = this.g.get(str);
        if (str3 != null && str3.length() != 0) {
            String str4 = SystemProperties.get(str3);
            com.ts.appservice.engineeringmode.e.a.g("DtcManager", "supplierName : " + str4);
            tSDiagnoseDeviceStatus.setSupplierName(str4);
        }
        com.ts.appservice.engineeringmode.e.a.g("DtcManager", "getTSDiagnoseDeviceStatus out");
        return tSDiagnoseDeviceStatus;
    }

    public boolean h() {
        return this.d.isConnect();
    }

    public void j(boolean z) {
        this.h = z;
    }

    public void k() {
        boolean zIsConnect = this.d.isConnect();
        com.ts.appservice.engineeringmode.e.a.g("DtcManager", "updateDtcData: diagConnectState " + zIsConnect);
        if (zIsConnect) {
            this.h = false;
            this.d.requestReportDTC((byte) 1);
        }
    }
}
