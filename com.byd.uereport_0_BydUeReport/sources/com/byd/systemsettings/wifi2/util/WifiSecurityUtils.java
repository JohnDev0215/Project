package com.byd.systemsettings.wifi2.util;

import android.net.wifi.WifiConfiguration;
import com.byd.systemsettings.wifi2.model.CipherType;

/* JADX INFO: loaded from: classes2.dex */
public class WifiSecurityUtils {

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.util.WifiSecurityUtils$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType;

        static {
            int[] iArr = new int[CipherType.values().length];
            $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType = iArr;
            try {
                iArr[CipherType.UNKNOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA3_PERSONAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.SAE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static String toSecurity(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.contains("WPA")) {
            if (str.contains("WPA-")) {
                sb.append("WPA");
            }
            if (str.contains("WPA2-")) {
                if (sb.toString().length() > 0) {
                    sb.append("/");
                }
                sb.append("WPA2");
            }
            if (str.contains("WPA3-") || (str.contains("WPA2-") && str.contains("+SAE"))) {
                if (sb.toString().length() > 0) {
                    sb.append("/");
                }
                sb.append("WPA3");
            }
        } else if (str.contains("WEP")) {
            sb.append("WEP");
        }
        if (str.contains("PSK-")) {
            sb.append(" PSK");
        }
        if (str.contains("RSN-SAE-CCMP")) {
            sb.append("WPA3-Personal");
        }
        return sb.toString();
    }

    public static String toSecurity(CipherType cipherType) {
        if (cipherType == null) {
            return "";
        }
        switch (AnonymousClass1.$SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[cipherType.ordinal()]) {
            case 1:
            case 2:
                return "";
            case 3:
                return "WEP";
            case 4:
                return "WPA PSK";
            case 5:
                return "WPA/WPA2 PSK";
            case 6:
                return "WPA2/WPA3 PSK";
            case 7:
                return "WPA3";
            case 8:
                return "SAE";
            default:
                return cipherType.toString();
        }
    }

    public static String toSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            sb.append("WPA");
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            if (sb.toString().length() > 0) {
                sb.append("/");
            }
            sb.append("WPA2");
        }
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            if (sb.toString().length() > 0) {
                sb.append("/");
            }
            sb.append("WPA3");
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            sb.append(" PSK");
        }
        if (wifiConfiguration.allowedKeyManagement.get(2)) {
            sb.append("WEP");
        }
        return sb.toString();
    }
}
