package g1;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.util.Log;
import com.bydux.api.common.tools.log.BydLogHelper;
import i1.f;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AutoProperties.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2919b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2918a = "AutoProperties";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2920c = BYDAutoSysPropertiesDevice.COUNTRY_CODE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f2921d = "persist.sys.byd.countrycode";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2922e = "persist.vendor.byd.cfg.huc.config.area";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f2923f = "47";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2924g = 1001;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f2925h = "persist.sys.byd.navi_launcher";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2926i = "/storage/emulated/0/collect2/";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2927j = "countrycode.txt";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f2928k = BYDAutoSysPropertiesDevice.RIGHT_RUDDER_VALUE;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f2929l = "persist.vendor.byd.cfg.huc.steering.wheel.position";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f2930m = BYDAutoSysPropertiesDevice.UI_STYLE;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f2931n = BYDAutoSysPropertiesDevice.DX_GLOBAL_VALUE;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2932o = BYDAutoSysPropertiesDevice.DX_GLOBAL_VALUE;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f2933p = "002.011.005.001.02.47.01.02";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f2934q = BYDAutoSysPropertiesDevice.CAR_VIN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f2935r = "tes0000000000000000";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f2936s = "sys.byd.isUSBExist";

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f2937t = BYDAutoSysPropertiesDevice.SD_EXIST;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f2938u = "sys.byd.mUsbotgUuid";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f2939v = "sys.byd.mSdcardUuid";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f2940w = "com.neusoft.na.navigation";

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final String f2941x = "com.huawei.maps.auto.app";

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2942y = "0";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f2943z = "";
    public String A = "Build.BRAND";
    public String B = "Build.MANUFACTURER";
    public String C = "BYD Auto";
    public StorageManager D = null;
    public BYDAutoTestDevice E = null;
    public String F = "47";
    public List<String> G = Arrays.asList("47", "31", "44", "45", "351", "32", "46", "41", "43", "39", "34", "358", "353", "30", "49", "33", "359", "357", "385", "420", "36", "372", "371", "370", "352", "356", "48", "40", "421", "386", "354", "423", "377", "379", "382", "355", "55", "56", "598", "57", "506", "51", "593", "595", "591", "1242", "507", "52", "502", "1876", "65", "977", "61", "66", "92", "60", "62", "63", "64", "84", "997");
    public List<String> H = Arrays.asList("86", "95", "856", "855", "63", "689", "687", "84", "692", "976", "82", "886", "850", "56", "598", "57", "1890", "506", "51", "840", "593", "595", "55", "591", "1242", "507", "599", "52", "502", "966", "971", "965", "968", "20", "233", "974", "212", "973", "238", "216", "961", "962", "262", "261", "244", "47", "45", "31", "351", "32", "46", "41", "43", "39", "34", "358", "30", "49", "33", "359", "385", "420", "372", "36", "371", "370", "352", "48", "40", "421", "386", "354", "423", "90", "380", "972", "382", "381", "387", "389", "355", "374", "994", "375", "373", "997", "996", "992", "998", "377", "7", "379", "503", "504", "505", "297", "970", "131", "132", "133", "134", "135", "136", "137", "225", "221", "250", "234", "229", "241", "54", "5997", "251", "237", "227", "228", "253", "252", "223", "220", "224", "226", "240", "243", "242", "257", "236", "222", "235", "211");
    public List<String> I = Arrays.asList("65", "852", "853", "977", "61", "66", "81", "94", "92", "91", "60", "62", "64", "975", "673", "1876", "27", "263", "260", "230", "267", "44", "353", "357", "356", "1868", "1268", "1246", "1345", "597", "1758", "592", "880", "960", "254");
    public final List<String> J = Arrays.asList("51", "52", "55", "57", "60", "63", "66", "91", "92", "94", "95", "212", "216", "502", "505", "506", "507", "591", "593", "595", "598", "599", "673", "687", "689", "853", "855", "856", "961", "962", "965", "966", "968", "971", "973", "974", "975", "976", "977", "998", "1242", "1876", "1890", "27", "230", "263", "260", "267", "997", "355", "373", "379", "377", "503", "54", "1868", "1268", "297", "1246", "1345", "597", "1758", "592", "5997", "504", "254", "248", "256", "255", "258", "264");
    public final Handler K = new HandlerC0014a();

    /* JADX INFO: renamed from: g1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoProperties.java */
    public class HandlerC0014a extends Handler {
        public HandlerC0014a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1001) {
                a.this.l();
                a.this.i();
                a.this.j();
                BydLogHelper.logD(a.this.f2918a, "MESSAGE_READ_OFFLINE ");
                a.this.k();
            }
        }
    }

    public a(Context context) {
        this.f2919b = null;
        this.f2919b = context;
    }

    public final void f() {
        g();
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "47");
        String str2 = SystemProperties.get("persist.sys.byd.countrycode", "47");
        BydLogHelper.logD(this.f2918a, "getCountryCode mCountryCode =" + this.F + ", properCode = " + str + ", COUNTRY_PROPERTIES = " + SystemProperties.get(BYDAutoSysPropertiesDevice.COUNTRY_CODE));
        if (this.F.isEmpty() && str.isEmpty()) {
            SystemProperties.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "47");
            BydLogHelper.logD(this.f2918a, "getCountryCode 5555");
        } else if (!this.F.isEmpty() && str.isEmpty()) {
            SystemProperties.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, this.F);
            BydLogHelper.logD(this.f2918a, "getCountryCode 6666");
        } else if (this.F.isEmpty() || str.isEmpty() || this.F.equals(str)) {
            SystemProperties.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "47");
            BydLogHelper.logD(this.f2918a, "getCountryCode 8888");
        } else {
            SystemProperties.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, this.F);
            BydLogHelper.logD(this.f2918a, "getCountryCode 7777");
        }
        BydLogHelper.logD(this.f2918a, "getCountryCode mCountryCode =" + this.F + ", PersistCode = " + str2 + ", COUNTRY_PERSIST_PROPERTIES = " + SystemProperties.get("persist.sys.byd.countrycode"));
        if (this.F.isEmpty() && str2.isEmpty()) {
            SystemProperties.set("persist.sys.byd.countrycode", "47");
            BydLogHelper.logD(this.f2918a, "getCountryCode 1111");
        } else if (!this.F.isEmpty() && str2.isEmpty()) {
            SystemProperties.set("persist.sys.byd.countrycode", this.F);
            BydLogHelper.logD(this.f2918a, "getCountryCode 2222");
        } else if (this.F.isEmpty() || str2.isEmpty() || this.F.equals(str2)) {
            BydLogHelper.logD(this.f2918a, "getCountryCode 4444");
        } else {
            SystemProperties.set("persist.sys.byd.countrycode", this.F);
            BydLogHelper.logD(this.f2918a, "getCountryCode 3333");
        }
        BydLogHelper.logD(this.f2918a, "getCountryCode countrycode =" + SystemProperties.get(BYDAutoSysPropertiesDevice.COUNTRY_CODE) + ", mCountryCode.equals(PersistCode) = " + this.F.equals(str2) + ", mCountryCode.equals(properCode) = " + this.F.equals(str));
        if (f.a().b(this.f2919b.getApplicationContext(), "com.neusoft.na.navigation") || f.a().b(this.f2919b.getApplicationContext(), "com.huawei.maps.auto.app")) {
            SystemProperties.set("persist.sys.byd.navi_launcher", "true");
        } else {
            SystemProperties.set("persist.sys.byd.navi_launcher", "false");
        }
    }

    public final void g() {
        String str;
        IOException e3;
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = this.f2919b.getResources().getAssets().open("countrycode.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
        } catch (IOException e4) {
            str = null;
            e3 = e4;
        }
        try {
            inputStreamOpen.close();
        } catch (IOException e5) {
            e3 = e5;
            e3.printStackTrace();
        }
        if (str == null) {
            Log.d(this.f2918a, "content is null ");
            return;
        }
        int i3 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        BydLogHelper.logD(this.f2918a, "parseJson codeNum =" + i3);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i4);
                String string = jSONObject.getString("countrycode");
                String string2 = jSONObject.getString("number");
                if (!string2.isEmpty() && string2.equals(String.valueOf(i3))) {
                    this.F = string;
                    BydLogHelper.logD(this.f2918a, "parseJson number =" + string2 + ",mCountryCode =" + this.F);
                }
            }
        } catch (JSONException unused) {
        }
    }

    public void h() {
        f();
        this.K.sendEmptyMessageDelayed(1001, 0L);
    }

    public final void i() {
        String str = SystemProperties.get(this.f2931n);
        if (str == null || !str.isEmpty()) {
            return;
        }
        SystemProperties.set(this.f2931n, "ROM");
    }

    public final void j() {
        String str = SystemProperties.get(this.f2932o);
        if (str == null || !str.isEmpty()) {
            return;
        }
        SystemProperties.set(this.f2932o, this.f2933p);
    }

    public final void k() {
        String str = SystemProperties.get(this.f2929l, "0");
        BydLogHelper.logD(this.f2918a, "setRightRudder position =" + str);
        if (str == null || str.equals("")) {
            SystemProperties.set(this.f2928k, "false");
        } else if (Integer.valueOf(str).intValue() == 0) {
            SystemProperties.set(this.f2928k, "false");
        } else if (Integer.valueOf(str).intValue() == 1) {
            SystemProperties.set(this.f2928k, "true");
        }
    }

    public final void l() {
        String str = SystemProperties.get(this.f2930m);
        if (str == null || !str.isEmpty()) {
            return;
        }
        SystemProperties.set(this.f2930m, this.f2942y);
    }
}
