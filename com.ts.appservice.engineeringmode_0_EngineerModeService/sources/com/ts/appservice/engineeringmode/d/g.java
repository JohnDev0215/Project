package com.ts.appservice.engineeringmode.d;

import android.os.Bundle;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxmanager.QnxManager;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g {
    private static g f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private QnxManager f48a;
    private com.ts.appservice.engineeringmode.a.a c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bundle f49b = new Bundle();
    private final Object d = new Object();
    private IQnxCallBack.Stub e = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends IQnxCallBack.Stub {
        a() {
        }

        public void onMessageChanged(Bundle bundle) {
            synchronized (g.this.d) {
                g.this.c(bundle);
            }
        }
    }

    public g() {
        QnxManager qnxManager = QnxManager.getInstance();
        this.f48a = qnxManager;
        qnxManager.registerCallback(10, 0, this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.containsKey("subtype") && bundle.containsKey("subtype")) {
                    int i = bundle.getInt("msgType");
                    int i2 = bundle.getInt("subtype");
                    com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "msgType : " + i + " subType : " + i2);
                    if (i == 10) {
                        if (i2 == 101) {
                            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("sensorId");
                            float[] floatArray = bundle.getFloatArray("temperatureValue");
                            if (integerArrayList == null || floatArray == null) {
                                com.ts.appservice.engineeringmode.e.a.c("TemperatureManager", " Temperature data error， arrayList :" + integerArrayList + "array: " + floatArray);
                            } else {
                                for (int i3 = 0; i3 < integerArrayList.size(); i3++) {
                                    Integer num = integerArrayList.get(i3);
                                    String strValueOf = String.valueOf(floatArray[i3]);
                                    int iIntValue = num.intValue();
                                    if (iIntValue == 2) {
                                        com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "soc temperature : " + strValueOf);
                                        this.f49b.putString("SOC_T", strValueOf);
                                    } else if (iIntValue == 17) {
                                        com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "amplifier temperature : " + strValueOf);
                                        this.f49b.putString("AMPLIFIER_T", strValueOf);
                                    } else if (iIntValue == 20) {
                                        com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "pcb temperature : " + strValueOf);
                                        this.f49b.putString("PCB_T", strValueOf);
                                    } else if (iIntValue != 25) {
                                        com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "this is not Temperature id : " + num);
                                    } else {
                                        com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "large_power temperature : " + strValueOf);
                                        this.f49b.putString("LARGE_POWER_T", strValueOf);
                                    }
                                }
                            }
                        }
                        if (i2 == 102 && bundle.containsKey("tftLcdTemp")) {
                            String string = bundle.getString("tftLcdTemp");
                            com.ts.appservice.engineeringmode.e.a.a("TemperatureManager", "tft temperature : " + string);
                            this.f49b.putString("TFT_T", string);
                        }
                        if (this.c != null) {
                            this.c.a(this.f49b);
                        } else {
                            com.ts.appservice.engineeringmode.e.a.c("TemperatureManager", " onMessageChanged.null == mEngineerCallBack");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized g d() {
        if (f == null) {
            f = new g();
        }
        return f;
    }

    public void e(com.ts.appservice.engineeringmode.a.a aVar) {
        this.c = aVar;
    }
}
