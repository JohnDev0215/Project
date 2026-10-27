package e5;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: PhysicalMasDialogUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f4644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f4645b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f4646c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActivityManager f4647d;

    public b(Context context) {
        this.f4644a = context;
    }

    public final boolean a(Context context) {
        if (this.f4647d == null) {
            this.f4647d = (ActivityManager) context.getSystemService("activity");
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = this.f4647d.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals("com.byd.carsettings") && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public int b(Context context, String str) {
        return Settings.System.getInt(context.getContentResolver(), str, 1);
    }

    public boolean c() {
        a aVar = this.f4645b;
        if (aVar == null || !aVar.f()) {
            return false;
        }
        this.f4645b.d();
        return true;
    }

    public boolean d() {
        a aVar = this.f4646c;
        if (aVar == null || !aVar.f()) {
            return false;
        }
        this.f4646c.d();
        return true;
    }

    public final boolean e() {
        g5.a.a(" BYDAutoSettingDevice.getInstance(context).getPhysicalkeySecuConfig()  : " + BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecuConfig());
        g5.a.a(" BYDAutoSettingDevice.getInstance(context).getPhysicalkeyConfig()  : " + BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeyConfig());
        g5.a.a(" BYDAutoSettingDevice.getInstance(context).getPhysicalkeySecupConfig()  : " + BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecupConfig());
        g5.a.a(" BYDAutoSettingDevice.getInstance(context).getPhysicalkeySecurlConfig()  : " + BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecurlConfig());
        g5.a.a(" BYDAutoSettingDevice.getInstance(context).getPhysicalkeySecurrConfig()  : " + BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecurrConfig());
        return BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecuConfig() == 2 || BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeyConfig() == 2 || BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecupConfig() == 2 || BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecurlConfig() == 2 || BYDAutoSettingDevice.getInstance(this.f4644a).getPhysicalkeySecurrConfig() == 2;
    }

    public boolean f() {
        int supportSeatMassage = BYDAutoSettingDevice.getInstance(this.f4644a).getSupportSeatMassage(1);
        g5.a.a(" massage mainState  : " + supportSeatMassage);
        return supportSeatMassage == 1;
    }

    public boolean g() {
        int supportSeatMassage = BYDAutoSettingDevice.getInstance(this.f4644a).getSupportSeatMassage(2);
        g5.a.a(" massage passageState  : " + supportSeatMassage);
        return supportSeatMassage == 1;
    }

    public boolean h() {
        int i8;
        try {
            ContentResolver contentResolver = this.f4644a.getContentResolver();
            int autoType = BYDAutoBodyworkDevice.getInstance(this.f4644a).getAutoType();
            g5.a.b("PhysicalMasDialogUtils", "isSeatMassageOnForegroundInMyCar isNeedShow carType : " + autoType);
            Cursor cursorQuery = contentResolver.query(Uri.parse((autoType == 123 || autoType == 60) ? "content://com.byd.mycar.ts.provider.MyCarProvider" : "content://com.byd.mycar.provider.MyCarProvider"), new String[]{"mycar"}, null, null, null);
            if (cursorQuery == null) {
                return false;
            }
            String string = null;
            while (cursorQuery.moveToNext()) {
                string = cursorQuery.getString(0);
            }
            if (Integer.parseInt(Build.VERSION.SDK) < 14) {
                cursorQuery.close();
            }
            i8 = new JSONObject(string).getInt("isSeatMassageOnForeground");
        } catch (Exception e8) {
            Log.e("PhysicalMasDialogUtils", "exception : " + e8.getMessage());
            i8 = 0;
        }
        return i8 == 1;
    }

    public final boolean i(int i8) {
        boolean z7 = false;
        int i9 = Settings.System.getInt(this.f4644a.getContentResolver(), "sys.byd.safety.warn", 0);
        g5.a.b("PhysicalMasDialogUtils", "massage turn dialog isNeedShow carType : " + BYDAutoBodyworkDevice.getInstance(this.f4644a).getAutoType());
        if (i9 == 1 && e() && j() && l() && !h()) {
            z7 = true;
        }
        g5.a.b("PhysicalMasDialogUtils", "massage turn dialog isNeedShow : " + z7);
        return z7;
    }

    public final boolean j() {
        if (a(this.f4644a)) {
            return !k();
        }
        g5.a.b("PhysicalMasDialogUtils", "setting app is not onForeground");
        return true;
    }

    public final boolean k() {
        boolean z7 = 1 == b(this.f4644a, "massage_dialog");
        g5.a.b("PhysicalMasDialogUtils", "massage dialog is show in setting : " + z7);
        return z7;
    }

    public final boolean l() {
        boolean z7 = 1 == b(this.f4644a, "massage_switch");
        g5.a.b("PhysicalMasDialogUtils", "massage turn dialog toggle isOpen : " + z7);
        return z7;
    }

    public void m(int i8, int i9, int i10) {
        g5.a.b("PhysicalMasDialogUtils", "updateLevel , mode : " + i8 + " level : " + i9);
        if (i8 >= 7 || i8 < 1) {
            return;
        }
        if (i10 == 1) {
            if (this.f4645b == null) {
                this.f4645b = new a(this.f4644a, i10);
            }
            this.f4645b.j(i8, i9, i10, i(i10));
        } else if (i10 == 2) {
            if (this.f4646c == null) {
                this.f4646c = new a(this.f4644a, i10);
            }
            this.f4646c.j(i8, i9, i10, i(i10));
        }
    }

    public void n(int i8, int i9, int i10) {
        g5.a.b("PhysicalMasDialogUtils", "updateMode , mode : " + i8 + " level : " + i9);
        if (i10 == 1 && f()) {
            if (this.f4645b == null) {
                this.f4645b = new a(this.f4644a, i10);
            }
            if (i8 < 1 || i8 > 6) {
                return;
            }
            this.f4645b.k(i8, i9, i10, i(i10));
            g5.a.b("PhysicalMasDialogUtils", "mainDlg updateMode , mode: " + i8 + " level : " + i9 + " location : " + i10);
            return;
        }
        if (i10 == 2 && g()) {
            if (this.f4646c == null) {
                this.f4646c = new a(this.f4644a, i10);
            }
            if (i8 < 1 || i8 > 6) {
                return;
            }
            this.f4646c.k(i8, i9, i10, i(i10));
            g5.a.b("PhysicalMasDialogUtils", "passengerDlg updateMode , mode: " + i8 + " level : " + i9 + " location : " + i10);
        }
    }
}
