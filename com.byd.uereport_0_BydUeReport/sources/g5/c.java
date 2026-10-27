package g5;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.SystemProperties;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: CarInfoUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    @Deprecated
    public static int a() {
        return SystemProperties.getInt("persist.sys.byd.countrycode", -1);
    }

    public static int b(Context context) throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        JSONArray jSONArray;
        int i8;
        String str = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open("countrycode.json");
        } catch (IOException e8) {
            e = e8;
            inputStreamOpen = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
            try {
                inputStreamOpen.close();
            } catch (IOException e9) {
                e9.printStackTrace();
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                String str2 = new String(bArr);
                try {
                    inputStreamOpen.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                inputStreamOpen.close();
                throw th;
            }
        } catch (IOException e11) {
            e = e11;
            e.printStackTrace();
            try {
                inputStreamOpen.close();
            } catch (IOException e12) {
                e12.printStackTrace();
            }
        }
        if (str == null) {
            return -1;
        }
        int i9 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", -1);
        a.i("configArea :" + i9);
        try {
            jSONArray = new JSONArray(str);
        } catch (Exception e13) {
            e13.printStackTrace();
        }
        for (i8 = 0; i8 < jSONArray.length(); i8++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i8);
            String string = jSONObject.getString("countrycode");
            if (jSONObject.getString("number").equals("" + i9)) {
                return Integer.parseInt(string);
            }
            return -1;
        }
        return -1;
    }

    public static int c(Context context) throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        JSONArray jSONArray;
        int i8;
        String str = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open("countrycode.json");
        } catch (IOException e8) {
            e = e8;
            inputStreamOpen = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
            try {
                inputStreamOpen.close();
            } catch (IOException e9) {
                e9.printStackTrace();
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                String str2 = new String(bArr);
                try {
                    inputStreamOpen.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                inputStreamOpen.close();
                throw th;
            }
        } catch (IOException e11) {
            e = e11;
            e.printStackTrace();
            try {
                inputStreamOpen.close();
            } catch (IOException e12) {
                e12.printStackTrace();
            }
        }
        if (str == null) {
            return -1;
        }
        int i9 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        a.i("configArea :" + i9);
        try {
            jSONArray = new JSONArray(str);
        } catch (Exception e13) {
            e13.printStackTrace();
        }
        for (i8 = 0; i8 < jSONArray.length(); i8++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i8);
            String string = jSONObject.getString("countrycode");
            if ((i9 + "").equals(jSONObject.getString("number"))) {
                return Integer.parseInt(string);
            }
            return -1;
        }
        return -1;
    }

    public static double d(Context context) {
        double d8 = BYDAutoInstrumentDevice.getInstance(context).getMileageUnit() == 2 ? 3379.5299999999997d : 3500.0d;
        BydLogHelper.logI("getMaintainMileageDefault", "[保养里程]----getMaintainMileageDefault----defaultMile:" + d8);
        return d8;
    }

    public static int e() {
        int iIntValue = 0;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            if (objInvoke instanceof Integer) {
                iIntValue = ((Integer) objInvoke).intValue();
            }
        } catch (Exception e8) {
            a.c("getSteeringWheelPosition Exception = " + e8);
        }
        a.a("getSteeringWheelPosition position = " + iIntValue);
        return iIntValue;
    }

    public static boolean f(Context context) throws Throwable {
        boolean zEquals = "1".equals(l.a("persist.sys.vehicle_privacy_status", "1"));
        r4.f.b("[智慧充电]----[隐私协议]:" + zEquals);
        return zEquals;
    }

    public static boolean g(Context context) {
        int autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
        a.i("carType:" + autoType);
        switch (autoType) {
            case 0:
            case 14:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
            case 224:
                return true;
            default:
                return false;
        }
    }

    public static boolean h() {
        try {
            int iIntValue = Integer.valueOf(l.a("persist.sys.energytype", "1")).intValue();
            a.i("isCarEVByProp:" + iIntValue);
            return iIntValue != 2;
        } catch (Exception e8) {
            a.c(e8.getMessage());
            return true;
        }
    }

    public static boolean i(Context context) {
        return BYDAutoBodyworkDevice.getInstance(context).getAutoType() == 143;
    }
}
