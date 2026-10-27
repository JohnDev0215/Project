package com.byd.car.internal.diagnosis;

import android.content.Context;
import com.byd.car.DiCarLog;
import com.byd.car.adas.ICarAdasService;
import com.byd.car.cabin.ICarCabinService;
import com.byd.car.carinfo.ICarInfoService;
import com.byd.car.property.ICarPropertyService;
import com.byd.spi.Spi;
import java.lang.reflect.Method;
import spi.c;
import spi.i6;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosisManager {
    private static DiagnosisManager sInstance;

    private DiagnosisManager() {
    }

    private boolean checkApiProguardState(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            i6.a(DiCarLog.TAG_CAR, "proguard exception: api " + str + " is proguard");
            return false;
        }
    }

    private boolean checkServiceConnectState(Context context, Class cls) {
        if (Spi.getService(context, cls) != null) {
            return true;
        }
        StringBuilder sbK = c.k("service exception：service ");
        sbK.append(cls.getName());
        sbK.append(" connect failed");
        i6.a(DiCarLog.TAG_CAR, sbK.toString());
        return false;
    }

    private boolean checkServiceProguardState(String str) {
        try {
            Class.forName(str);
            Method[] declaredMethods = Class.forName(str + "$Stub").getDeclaredMethods();
            int length = declaredMethods.length;
            boolean z = true;
            boolean z2 = true;
            for (int i = 0; i < length; i++) {
                Method method = declaredMethods[i];
                if (method.getName().equals("asInterface")) {
                    z = false;
                }
                if (method.getName().equals("asBinder")) {
                    z2 = false;
                }
            }
            if (!z && !z2) {
                return true;
            }
            i6.a(DiCarLog.TAG_CAR, "proguard exception：service " + str + " is proguard");
            return false;
        } catch (ClassNotFoundException unused) {
            i6.a(DiCarLog.TAG_CAR, "proguard exception：service " + str + " is proguard");
            return false;
        }
    }

    public static DiagnosisManager get() {
        if (sInstance == null) {
            synchronized (DiagnosisManager.class) {
                if (sInstance == null) {
                    sInstance = new DiagnosisManager();
                }
            }
        }
        return sInstance;
    }

    public void diagnosis(Context context) {
        String str;
        i6.c(DiCarLog.TAG_CAR, "==========DiCar diagnosis begin==========");
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            str = (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, "apps.setting.product.inswver");
        } catch (IllegalArgumentException e) {
            i6.a("SystemPropertyUtil", e.getMessage());
            str = "";
        } catch (Exception e2) {
            i6.a("SystemPropertyUtil", e2.getMessage());
            str = "";
        }
        i6.c(DiCarLog.TAG_CAR, "SOC Version: " + str);
        i6.c(DiCarLog.TAG_CAR, "DiCar Version: 2.0.0-beta.2");
        boolean zCheckApiProguardState = checkApiProguardState("com.byd.car.ICarInfoManager");
        boolean zCheckApiProguardState2 = checkApiProguardState("com.byd.car.ICarCabinManager");
        boolean zCheckApiProguardState3 = checkApiProguardState("com.byd.car.ICarAdasManager");
        boolean zCheckApiProguardState4 = checkApiProguardState("com.byd.car.property.ICarPropertyManager");
        boolean zCheckServiceProguardState = checkServiceProguardState("com.byd.car.adas.ICarAdasService");
        boolean zCheckServiceProguardState2 = checkServiceProguardState("com.byd.car.carinfo.ICarInfoService");
        boolean zCheckServiceProguardState3 = checkServiceProguardState("com.byd.car.cabin.ICarCabinService");
        boolean zCheckServiceProguardState4 = checkServiceProguardState("com.byd.car.property.ICarPropertyService");
        if (zCheckApiProguardState && zCheckApiProguardState2 && zCheckApiProguardState3 && zCheckApiProguardState4 && zCheckServiceProguardState && zCheckServiceProguardState2 && zCheckServiceProguardState3 && zCheckServiceProguardState4) {
            i6.c(DiCarLog.TAG_CAR, "sdk proguard diagnosis:pass!!");
        }
        boolean zCheckServiceConnectState = checkServiceConnectState(context, ICarInfoService.class);
        boolean zCheckServiceConnectState2 = checkServiceConnectState(context, ICarCabinService.class);
        boolean zCheckServiceConnectState3 = checkServiceConnectState(context, ICarAdasService.class);
        boolean zCheckServiceConnectState4 = checkServiceConnectState(context, ICarPropertyService.class);
        if (zCheckServiceConnectState && zCheckServiceConnectState2 && zCheckServiceConnectState3 && zCheckServiceConnectState4) {
            i6.c(DiCarLog.TAG_CAR, "service connect diagnosis:pass!!");
        }
        i6.c(DiCarLog.TAG_CAR, "==========DiCar diagnosis end==========");
    }
}
