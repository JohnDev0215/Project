package com.ts.appservice.engineeringmode.d;

import android.os.Bundle;
import com.ts.lib.engineeringmode.bean.TsNetMap;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxmanager.NetMap;
import com.ts.qnxmanager.QnxManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d {
    private static d d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final QnxManager f41a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ts.appservice.engineeringmode.a.a f42b;
    private IQnxCallBack.Stub c = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends IQnxCallBack.Stub {
        a() {
        }

        public void onMessageChanged(Bundle bundle) {
            if (bundle != null) {
                try {
                    if (bundle.containsKey("subtype")) {
                        int i = bundle.getInt("subtype", -1);
                        if (i == 113) {
                            com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", "onMessageChanged subtype : " + i);
                            String string = bundle.getString("ecuDialId");
                            String string2 = bundle.getString("diagRsp");
                            com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", "ecuDialId : " + string + " diagRsp : " + string2);
                            if (d.this.f42b != null) {
                                d.this.f42b.h(string, string2);
                            } else {
                                com.ts.appservice.engineeringmode.e.a.c("EcuDiagManager", "mEngineerCallBack == null");
                            }
                        } else if (i == 115) {
                            com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", "onMessageChanged subtype : " + i);
                            int i2 = bundle.getInt("iviObdOperationType");
                            int i3 = bundle.getInt("iviObdStatus");
                            com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", "iviObdOperationType : " + i2 + " iviObdStatus : " + i3);
                            if (d.this.f42b != null) {
                                d.this.f42b.g(i2, i3);
                            } else {
                                com.ts.appservice.engineeringmode.e.a.c("EcuDiagManager", "mEngineerCallBack == null");
                            }
                        } else if (i == 116) {
                            d.this.f(bundle);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public d() {
        QnxManager qnxManager = QnxManager.getInstance();
        this.f41a = qnxManager;
        qnxManager.registerCallback(8, 0, this.c);
    }

    public static synchronized d c() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(Bundle bundle) {
        int i = bundle.getInt("ipbRepStatus");
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", "onEnergyTestChange ipbRepStatus : " + i);
        com.ts.appservice.engineeringmode.a.a aVar = this.f42b;
        if (aVar != null) {
            aVar.e(i);
        } else {
            com.ts.appservice.engineeringmode.e.a.c("EcuDiagManager", "mEngineerCallBack == null");
        }
    }

    private boolean g(int i, Bundle bundle) {
        try {
            this.f41a.sendMessage(8, i, (String) null, bundle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private ArrayList<NetMap> j(List<TsNetMap> list) {
        ArrayList<NetMap> arrayList = new ArrayList<>(list.size());
        for (TsNetMap tsNetMap : list) {
            arrayList.add(new NetMap(tsNetMap.getNetSag(), tsNetMap.getIds()));
        }
        return arrayList;
    }

    public boolean d(int i, int i2, List<TsNetMap> list, boolean z) {
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", " obdRequest.in iviObdOperationType : " + i + " ignCycleNum : " + i2 + " netMapList : " + list + " mapOpenStatus : " + z);
        Bundle bundle = new Bundle();
        bundle.putInt("iviObdOperationType", i);
        bundle.putInt("ignCycleNum", i2);
        bundle.putParcelableArrayList("netMapList", j(list));
        bundle.putBoolean("mapOpenStatus", z);
        boolean zG = g(24, bundle);
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", " obdRequest.out = " + zG);
        return zG;
    }

    public boolean e(String str, String str2) {
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", " obdRequest.in id : " + str + " data : " + str2);
        Bundle bundle = new Bundle();
        bundle.putString("ecuId", "");
        bundle.putString("ecuDialId", str);
        bundle.putString("diagReq", str2);
        boolean zG = g(21, bundle);
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", " obdRequest.out = " + zG);
        return zG;
    }

    public void h(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("ipbTestStatus", i);
        com.ts.appservice.engineeringmode.e.a.g("EcuDiagManager", " setEnergyTest.out = " + g(25, bundle));
    }

    public void i(com.ts.appservice.engineeringmode.a.a aVar) {
        this.f42b = aVar;
    }
}
