package com.ts.car.dcm.common.data;

/* JADX INFO: loaded from: classes.dex */
public class DcmUtils {
    public static int boolToInt(boolean z) {
        return z ? 1 : 0;
    }

    public static boolean intToBool(int value) {
        return value != 0;
    }

    public static boolean isProjectionService(int service) {
        return service == 2 || service == 1 || service == 3;
    }

    public static int getConnectionTypeFromCapability(int capa) {
        if (capa == 8 || capa == 64 || capa == 128 || capa == 256) {
            return 1;
        }
        if (capa == 512 || capa == 1024) {
            return 2;
        }
        if (capa != 2048) {
            return 0;
        }
        return 3;
    }

    public static int getSrvTypeByCapability(int capability) {
        if (capability != 8) {
            if (capability != 64) {
                if (capability != 128) {
                    if (capability != 256) {
                        if (capability != 512) {
                            if (capability != 1024) {
                                if (capability != 2048) {
                                    return 0;
                                }
                            }
                        }
                    }
                    return 2;
                }
                return 3;
            }
            return 1;
        }
        return 4;
    }

    public static void logStack(String tag) {
        new Exception(tag + " ,Stack trace").printStackTrace();
    }
}
