package com.qtxa.device;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.qtxa.annotation.NoConfusion;
import com.qtxa.device.a;
import com.qtxa.qtxadefend.b;
import com.qtxa.qtxadefend.w;
import com.qtxa.report.DefineFlag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager extends a implements NoConfusion {
    private static final String carJar = "/system/framework/sgmw-e230-vin-sn.jar";
    BYDAutoBodyworkDevice bydAutoBodyworkDevice;
    private Context mContext;
    public String RealVIN_ok = "";
    public int autoTypeValue_ok = 0;
    public String mccmnc_ok = "";
    public int getVin = 0;
    public int getSnTime = 0;
    String VIN_ok = "";
    String Sn_ok = "";
    public int getJsonTime = 0;

    public static void can_test() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("onlineHas", "0");
            jSONObject.put("abnormalCanEvent", "0");
            jSONObject.put("abnormalCanLength", "0");
            jSONObject.put("abnormalCanID", "0");
            jSONObject.put("abnormalCanCycle", "0");
            jSONObject.put("abnormalAutomobilePartsAlarm", "0");
            jSONObject.put("abnormalAssociatonAutomobilePartsAlarm", "0");
            w.d("absBYDAutoBodyworkListener_showInfo: " + ("onlineHas       :0\tabnormalCanEvent       :0\tabnormalCanLength       :0\tabnormalCanID       :0\tabnormalCanCycle       :0\tabnormalAutomobilePartsAlarm       :0\tabnormalAssociatonAutomobilePartsAlarm       :0\t"));
            w.i(DefineFlag.CAN_LOG, 3, "can_event:", jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
            w.d("can_test:" + th.toString());
        }
    }

    public static String getImei(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId();
            return TextUtils.isEmpty(deviceId) ? telephonyManager.getSubscriberId() : deviceId;
        } catch (Exception e) {
            w.d("getVirtualId2:" + e.toString());
            return "";
        }
    }

    @Override // com.qtxa.device.a
    public String getJson() {
        try {
            String strMccmnc2 = mccmnc2();
            String realAutoVIN = this.bydAutoBodyworkDevice.getRealAutoVIN();
            int autoType = this.bydAutoBodyworkDevice.getAutoType();
            if (autoType == 0) {
                autoType = 255;
            }
            if (TextUtils.isEmpty(strMccmnc2)) {
                strMccmnc2 = "";
            }
            if (this.getJsonTime > 0) {
                if (!TextUtils.isEmpty(strMccmnc2)) {
                    this.mccmnc_ok = strMccmnc2;
                }
                if (!TextUtils.isEmpty(realAutoVIN)) {
                    this.RealVIN_ok = realAutoVIN;
                }
                if (autoType != 0) {
                    this.autoTypeValue_ok = autoType;
                }
            } else {
                this.mccmnc_ok = strMccmnc2;
                this.RealVIN_ok = realAutoVIN;
                this.autoTypeValue_ok = autoType;
            }
            String str = "{\"cmd\":1,\"tvin\":\"" + this.RealVIN_ok + "\",\"mcd\":\"" + this.autoTypeValue_ok + "\",\"sim\":\"" + this.mccmnc_ok + "\"}";
            this.getJsonTime++;
            return str;
        } catch (Throwable th) {
            w.d("Idd RealVIN......" + th.toString());
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.qtxa.device.a
    public String getSN() {
        try {
            String str = getprop("persist.vendor.bosch.cfg.diag.sn");
            if (this.getSnTime <= 0 || !TextUtils.isEmpty(str)) {
                this.Sn_ok = str;
            }
            this.getSnTime++;
            return this.Sn_ok;
        } catch (Throwable th) {
            w.d("Idd getSN......" + th.toString());
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.qtxa.device.a
    public String getVIN() {
        try {
            String autoVIN = this.bydAutoBodyworkDevice.getAutoVIN();
            if (!TextUtils.isEmpty(autoVIN) && autoVIN.length() > 17) {
                autoVIN = autoVIN.substring(autoVIN.length() - 17);
            }
            if (this.getVin <= 0 || !TextUtils.isEmpty(autoVIN)) {
                this.VIN_ok = autoVIN;
            }
            this.getVin++;
            return this.VIN_ok;
        } catch (Throwable th) {
            w.d("Idd getVIN......" + th.toString());
            th.printStackTrace();
            return null;
        }
    }

    public String getprop(String str) {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(new Build(), str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    @Override // com.qtxa.device.a
    public void init(Context context, a.b bVar) {
        try {
            if (b.c) {
                can_test();
            }
            this.mContext = context;
            this.bydAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            w.d("Idd init......:" + this.bydAutoBodyworkDevice);
        } catch (Throwable th) {
            w.d("Idd init1......" + th.toString());
            th.printStackTrace();
        }
    }

    public String mccmnc1() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getSimOperator();
    }

    public String mccmnc2() {
        String strSubstring = "";
        try {
            b.a.a.a.a service = b.a.a.a.a.getService();
            if (service == null) {
                return "";
            }
            String strA = service.a();
            strSubstring = strA.substring(0, 5);
            w.d("mccmnc2:" + ("mccmnc       :" + strSubstring + "\r\nimsi       :" + strA + "\r\n"));
            return strSubstring;
        } catch (Throwable th) {
            Log.w("cwstest", " ITbox.getService()" + th.toString());
            return strSubstring;
        }
    }

    @Override // com.qtxa.device.a
    public void uninit(Context context) {
        w.d("Idd uninit......");
    }
}
