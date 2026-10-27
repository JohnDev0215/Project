package com.qtxa.report;

import com.qtxa.qtxadefend.w;

/* JADX INFO: loaded from: classes.dex */
public class JniQtxaManager {
    public static boolean library = false;

    public interface RegisterCallback {
        void callback(int i, String str, byte[] bArr);
    }

    public interface RuleCallback {
        void callback(int i, byte[] bArr);
    }

    static {
        try {
            System.loadLibrary("iddsecuritylogjni");
            library = true;
        } catch (Throwable th) {
            library = false;
            th.printStackTrace();
        }
    }

    public static void java_loadRule(String str, int i, int i2, int i3, int i4, RuleCallback ruleCallback) {
        if (library) {
            loadRule(str, i, i2, i3, i4, ruleCallback);
            return;
        }
        if (ruleCallback != null) {
            ruleCallback.callback(3, null);
        }
        System.err.println("java_loadRule load library error!!!");
    }

    public static void java_queryRegister(int i, RegisterCallback registerCallback) {
        if (library) {
            queryRegister(i, registerCallback);
            return;
        }
        if (registerCallback != null) {
            registerCallback.callback(3, "server not exists", null);
        }
        System.err.println("java_queryRegister load library error!!!");
    }

    public static void java_rollBack(String str, int i, int i2, int i3, int i4, RuleCallback ruleCallback) {
        if (library) {
            rollBack(str, i, i2, i3, i4, ruleCallback);
            return;
        }
        if (ruleCallback != null) {
            ruleCallback.callback(3, null);
        }
        System.err.println("java_rollBack load library error!!!");
    }

    public static int java_sendJson(String str) {
        w.b("Idd RealVIN......" + str);
        if (library) {
            try {
                return sendJson(str);
            } catch (Throwable th) {
                System.err.println("java_sendJson load library error!!!" + th.toString());
            }
        }
        System.err.println("java_sendJson load library error!!!");
        return 0;
    }

    public static int java_setVinSn(String str, String str2) {
        w.b("java_setVinSn......vin:" + str + ";sn:" + str2);
        if (library) {
            return setVinSn(str, str2);
        }
        System.err.println("java_setVinSn load library error!!!");
        return 0;
    }

    public static native void loadRule(String str, int i, int i2, int i3, int i4, RuleCallback ruleCallback);

    public static native void queryRegister(int i, RegisterCallback registerCallback);

    public static native void rollBack(String str, int i, int i2, int i3, int i4, RuleCallback ruleCallback);

    public static native int sendJson(String str);

    public static native int setVinSn(String str, String str2);
}
