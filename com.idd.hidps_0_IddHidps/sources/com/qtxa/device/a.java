package com.qtxa.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.qtxa.annotation.d;
import com.qtxa.qtxadefend.w;

/* JADX INFO: loaded from: classes.dex */
@d("com.qtxa.device.DeviceManager")
public abstract class a {
    private static a inst = createInstance();

    public interface b {
        void a(String str);
    }

    private static class c extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Context f54a;

        private c() {
        }

        private static boolean a(byte[] bArr) {
            if (bArr == null) {
                return false;
            }
            for (int i = 0; i < bArr.length; i++) {
                if ((bArr[i] <= 47 || bArr[i] >= 58) && ((bArr[i] <= 64 || bArr[i] >= 91) && (bArr[i] <= 96 || bArr[i] >= 123))) {
                    return false;
                }
            }
            return true;
        }

        private static String b(String str) {
            if (str == null) {
                return null;
            }
            byte[] bytes = str.getBytes();
            if (!a(bytes)) {
                return null;
            }
            String str2 = new String(bytes);
            int length = str2.length();
            if (length >= 17) {
                return str2.substring(0, 16);
            }
            for (int i = 0; i < 17 - length; i++) {
                str2 = str2 + "a";
            }
            return str2;
        }

        private String c(Context context) {
            String strB;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                String deviceId = telephonyManager.getDeviceId();
                w.d("getVirtualId1:" + deviceId);
                String strB2 = b(deviceId);
                if (strB2 != null) {
                    return strB2;
                }
                String subscriberId = telephonyManager.getSubscriberId();
                w.d("getVirtualId1:" + subscriberId);
                String strB3 = b(subscriberId);
                if (strB3 != null) {
                    return strB3;
                }
            } catch (Exception e) {
                w.d("getVirtualId2:" + e.toString());
            }
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.serialno", null);
                w.d("getVirtualId1:" + str);
                String strB4 = b(str);
                if (strB4 != null) {
                    return strB4;
                }
            } catch (Exception e2) {
                w.d("getVirtualId3:" + e2.toString());
            }
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (Build.VERSION.SDK_INT == 27) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo.isConnected() && activeNetworkInfo.getExtraInfo() != null && (strB = b(activeNetworkInfo.getExtraInfo().replace("\"", ""))) != null) {
                        return strB;
                    }
                }
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String strB5 = b(connectionInfo.getSSID().replace("\"", ""));
                    if (strB5 != null) {
                        return strB5;
                    }
                }
            } catch (Exception e3) {
                w.d("getVirtualId4:" + e3.toString());
            }
            return null;
        }

        @Override // com.qtxa.device.a
        public String getJson() {
            return "";
        }

        @Override // com.qtxa.device.a
        public String getSN() {
            w.d("default getSN......");
            String strC = c(this.f54a);
            if (TextUtils.isEmpty(strC)) {
                return "SN-QTXA-TEST6789";
            }
            return "sn" + strC;
        }

        @Override // com.qtxa.device.a
        public String getVIN() {
            w.d("default getVIN......");
            String strC = c(this.f54a);
            return !TextUtils.isEmpty(strC) ? strC : "LMVVFZEAZ4K001111";
        }

        @Override // com.qtxa.device.a
        public void init(Context context, b bVar) {
            this.f54a = context;
            w.d("default init......");
            if (bVar != null) {
                bVar.a("default init");
            }
        }

        @Override // com.qtxa.device.a
        public void uninit(Context context) {
            w.d("default uninit......");
        }
    }

    private static a createInstance() {
        a aVar = (a) com.qtxa.annotation.c.c(a.class);
        return aVar == null ? new c() : aVar;
    }

    public static a getInstance() {
        return inst;
    }

    public abstract String getJson();

    public abstract String getSN();

    public abstract String getVIN();

    public abstract void init(Context context, b bVar);

    public abstract void uninit(Context context);
}
