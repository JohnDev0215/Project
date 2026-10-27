package c.a.d.s;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: DeviceInfoUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f3680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoBodyworkDevice f3682c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TelephonyManager f3683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoVersionDevice f3684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoTestDevice f3685f;

    public e(Context context) {
        this.f3681b = context;
        e();
    }

    public static e b(Context context) {
        if (f3680a == null) {
            synchronized (e.class) {
                if (f3680a == null) {
                    f3680a = new e(context);
                }
            }
        }
        return f3680a;
    }

    public int a(Context context) throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        JSONArray jSONArray;
        int i2;
        String str = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open("countrycode.json");
        } catch (IOException e2) {
            e = e2;
            inputStreamOpen = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
            try {
                inputStreamOpen.close();
            } catch (IOException e3) {
                e3.printStackTrace();
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
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                inputStreamOpen.close();
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            e.printStackTrace();
            try {
                inputStreamOpen.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        }
        if (str == null) {
            return -1;
        }
        int i3 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", -1);
        try {
            jSONArray = new JSONArray(str);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        for (i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            String string = jSONObject.getString("countrycode");
            if (jSONObject.getString("number").equals("" + i3)) {
                return Integer.parseInt(string);
            }
            return -1;
        }
        return -1;
    }

    public String c() {
        return this.f3684e.getBydSystemVersion();
    }

    public String d() {
        return this.f3682c.getRealAutoVIN();
    }

    public final void e() {
        if (this.f3682c == null) {
            this.f3682c = BYDAutoBodyworkDevice.getInstance(this.f3681b);
        }
        if (this.f3683d == null) {
            this.f3683d = (TelephonyManager) this.f3681b.getSystemService("phone");
        }
        if (this.f3684e == null) {
            this.f3684e = BYDAutoVersionDevice.getInstance(this.f3681b);
        }
        if (this.f3685f == null) {
            this.f3685f = BYDAutoTestDevice.getInstance(this.f3681b);
        }
    }
}
