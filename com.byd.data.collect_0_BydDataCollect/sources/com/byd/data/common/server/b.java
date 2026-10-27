package com.byd.data.common.server;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.collectdata.BYDAutoCollectDataDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import com.byd.data.common.base.gson.BooleanDefaultAdapter;
import com.byd.data.common.base.gson.CustomJsonAdapter;
import com.byd.data.common.base.gson.DoubleDefaultAdapter;
import com.byd.data.common.base.gson.IntegerDefaultAdapter;
import com.byd.data.common.base.gson.LongDefaultAdapter;
import com.byd.data.common.base.gson.StringNullAdapter;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.gson.DataCollectBean;
import com.byd.data.common.entiry.gson.DataEntity;
import com.byd.data.common.entiry.gson.DriverMotorData;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.google.gson.Gson;
import com.google.gson.e;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okio.BufferedSource;
import okio.Okio;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AppConfigFile.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends BYDAutoStudyListener {

    /* JADX INFO: renamed from: a */
    private static volatile b f3456a;

    /* JADX INFO: renamed from: b */
    private final String f3457b;

    /* JADX INFO: renamed from: c */
    public final byte f3458c;

    /* JADX INFO: renamed from: d */
    public final String f3459d;

    /* JADX INFO: renamed from: e */
    public final String f3460e;

    /* JADX INFO: renamed from: f */
    public final String f3461f;

    /* JADX INFO: renamed from: g */
    private final String f3462g;

    /* JADX INFO: renamed from: h */
    public final String f3463h;

    /* JADX INFO: renamed from: i */
    public final String f3464i;
    private final String j;
    private final String k;
    private DataCollectBean l;
    private Context m;
    private ContentObserver n;
    public short o;
    private byte p;
    public volatile byte q;
    private Gson r;
    public byte s;

    /* JADX INFO: compiled from: AppConfigFile.java */
    class a extends com.google.gson.v.a<DataCollectBean> {
        a() {
        }
    }

    /* JADX INFO: renamed from: com.byd.data.common.server.b$b */
    /* JADX INFO: compiled from: AppConfigFile.java */
    class C0068b extends ContentObserver {
        C0068b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            b.this.v(false);
        }
    }

    private b(Context context) {
        String str = "AppConfigFile" + com.byd.data.common.i.b.f3423a;
        this.f3457b = str;
        this.f3458c = (byte) SystemProperties.getInt("persist.vendor.byd.vehicle.type", 0);
        this.f3459d = "/vendor/etc/byd/config/";
        this.f3460e = "cloud/CountryArea.conf";
        this.f3461f = "CanCollectEventConfig.conf";
        this.f3462g = "local/VehicleData.conf";
        this.f3463h = "BmsConfig.conf";
        this.f3464i = "persist.sys.vehicle_privacy_status";
        this.j = "drive_motor_num";
        StringBuilder sb = new StringBuilder();
        sb.append("local/");
        sb.append(r() ? "HevExtension.conf" : "EvExtension.conf");
        this.k = sb.toString();
        this.o = (short) SystemProperties.getInt("persist.vendor.byd.vehicle.code", 0);
        this.m = context;
        int i2 = SystemProperties.getInt("persist.sys.vehicle_privacy_status", 0);
        this.p = (byte) (i2 << 2);
        f.g(str, " AppConfigFile() end privacy=" + i2 + ", level =" + ((int) this.s));
    }

    private void c(DataCollectBean dataCollectBean) {
        DriverMotorData driverMotorData = dataCollectBean.driverMotorData;
        if (r()) {
            if (dataCollectBean.a()) {
                List<DataEntity> list = driverMotorData.driverMotorControlTemp;
                a.c cVar = a.c.FRONT;
                DataEntity dataEntity = list.get(cVar.ordinal());
                if (dataEntity.canId != 1242) {
                    dataEntity.j((short) 1242, 31, false);
                }
                List<DataEntity> list2 = driverMotorData.driverMotorControlTemp;
                a.c cVar2 = a.c.REAR;
                list2.get(cVar2.ordinal()).g((short) 1242, 43, 4, 44, 3);
                driverMotorData.driverMotorTorque.get(cVar2.ordinal()).j((short) 1242, 62, true);
                driverMotorData.driverMotorTemperature.get(cVar.ordinal()).j((short) 1242, 34, false);
                driverMotorData.driverMotorTemperature.get(cVar2.ordinal()).g((short) 1242, 44, 4, 45, 3);
            } else {
                DataEntity dataEntity2 = driverMotorData.motorMCUGeneratrixVolt;
                List<DataEntity> list3 = driverMotorData.driverMotorTorque;
                a.c cVar3 = a.c.REAR;
                list3.get(cVar3.ordinal()).j(dataEntity2.canId, dataEntity2.startIndex, true);
                if (dataCollectBean.b()) {
                    List<DataEntity> list4 = driverMotorData.driverMotorControlTemp;
                    a.c cVar4 = a.c.FRONT;
                    DataEntity dataEntity3 = list4.get(cVar4.ordinal());
                    DataEntity dataEntity4 = driverMotorData.driverMotorTemperature.get(cVar4.ordinal());
                    DataEntity dataEntity5 = driverMotorData.driverMotorControlTemp.get(cVar3.ordinal());
                    DataEntity dataEntity6 = driverMotorData.driverMotorTemperature.get(cVar3.ordinal());
                    if (dataEntity3 != null && dataEntity3.canId != 1124) {
                        dataEntity3.g((short) 1124, 1, 0, 1, 7);
                    }
                    if (dataEntity4 != null && dataEntity4.canId != 1124) {
                        dataEntity4.g((short) 1124, 2, 0, 2, 7);
                    }
                    if (dataEntity5 != null && dataEntity5.canId != 1124) {
                        dataEntity5.g((short) 1124, 3, 0, 3, 7);
                    }
                    if (dataEntity6 != null && dataEntity6.canId != 1124) {
                        dataEntity6.g((short) 1124, 4, 0, 4, 7);
                    }
                }
            }
            dataCollectBean.vehicleData.gearValue.h(5);
        } else {
            List<DataEntity> list5 = driverMotorData.driverMotorControlTemp;
            a.c cVar5 = a.c.REAR;
            list5.get(cVar5.ordinal()).g((short) 1102, 0, 0, 0, 7);
            driverMotorData.driverMotorTorque.get(cVar5.ordinal()).canId = (short) 594;
        }
        if (this.q <= 0 || this.q % 2 != 0) {
            return;
        }
        byte b2 = (byte) (driverMotorData.totalSize - 2);
        driverMotorData.driverMotorControlTemp.get(1).k(b2);
        driverMotorData.driverMotorSpeed.get(1).k(b2);
        driverMotorData.driverMotorTemperature.get(1).k(b2);
        driverMotorData.driverMotorTorque.get(1).k(b2);
        driverMotorData.motorMCUGeneratrixCurrent.get(1).k(b2);
    }

    public static b e() {
        return f3456a;
    }

    public static b h(Context context) {
        if (f3456a == null) {
            synchronized (b.class) {
                if (f3456a == null) {
                    f3456a = new b(context);
                }
            }
        }
        return f3456a;
    }

    private byte j(Context context) {
        int iA = g.a("drive_motor_num");
        if (iA <= 0) {
            iA = BYDAutoStudy.getInstance(context).getDriveConfig();
            f.g(this.f3457b, "BYDAutoStudy driveConfig =" + iA);
            if (iA <= 0) {
                return (byte) 0;
            }
            g.d("drive_motor_num", iA);
        }
        k(context, iA);
        return this.q;
    }

    private void k(Context context, int i2) {
        int iMax = Math.max(1, i2);
        this.l.dmVersion = (byte) BYDAutoCollectDataDevice.getInstance(context).getDmVersionCode();
        f.g(this.f3457b, "driveConfig = " + iMax + ", dmVer =" + ((int) this.l.dmVersion));
        this.q = (byte) Math.min(iMax, (this.l.c() ? a.b.FRONT_DRIVER_DUAL_MOTOR : a.b.FOUR_DRIVER).ordinal());
        c(this.l);
        com.byd.data.common.g.a.i().g().g(this.l);
    }

    private void m() {
        byte transportMode = (byte) BYDAutoVehicleHealthDevice.getInstance(this.m).getTransportMode();
        this.p = (byte) (this.p | transportMode);
        f.g(this.f3457b, "CanNativeHelper transportMode =" + ((int) transportMode) + ", cloudSwi =" + ((int) this.p));
    }

    private boolean t(Context context, boolean z) {
        BufferedSource bufferedSourceC;
        f.c(this.f3457b, " readJsonConfigFile() Begin isHev = " + z);
        boolean z2 = false;
        try {
            bufferedSourceC = Okio.c(Okio.i(new File("/vendor/etc/byd/config/" + this.k)));
        } catch (IOException | JSONException e2) {
            e2.printStackTrace();
        }
        try {
            String strO = bufferedSourceC.O();
            BufferedSource bufferedSourceC2 = Okio.c(Okio.i(new File("/vendor/etc/byd/config/local/VehicleData.conf")));
            try {
                JSONObject jSONObject = new JSONObject(bufferedSourceC2.O());
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("DataStructure");
                if (jSONObjectOptJSONObject != null) {
                    jSONObjectOptJSONObject.put("evExtData", new JSONObject(strO));
                }
                this.l = (DataCollectBean) b().j(jSONObject.optString("DataStructure"), new a().e());
                z2 = true;
                v(true);
                BYDAutoStudy.getInstance(context).registerConnectListener(this);
                bufferedSourceC2.close();
                bufferedSourceC.close();
                f.c(this.f3457b, " readJsonConfigFile() end ret = " + z2 + ", configVersion = " + this.l.evExtData.version);
                return z2;
            } finally {
            }
        } catch (Throwable th) {
            if (bufferedSourceC != null) {
                try {
                    bufferedSourceC.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void u() {
        if (this.n != null) {
            return;
        }
        this.n = new C0068b(new Handler());
        this.m.getContentResolver().registerContentObserver(Settings.Global.getUriFor("persist.sys.vehicle_privacy_status"), false, this.n);
    }

    public void v(boolean z) {
        int i2 = SystemProperties.getInt("persist.sys.vehicle_privacy_status", 0);
        boolean z2 = i2 != 1;
        int i3 = (this.p & 12) >> 2;
        f.g(this.f3457b, "privacy =" + i2 + ", pre =" + i3);
        if (z) {
            u();
        }
        if (i2 == i3) {
            return;
        }
        boolean z3 = z2 == d();
        this.p = (byte) ((this.p & 3) | (i2 << 2));
        if (z3 || z) {
            return;
        }
        f.g(this.f3457b, "cloudSwitch =" + ((int) this.p));
        com.byd.data.common.c.c.b bVarG = com.byd.data.common.g.a.i().g();
        if (bVarG == null) {
            return;
        }
        if (i() == 1) {
            bVarG.n();
        } else if (s()) {
            bVarG.t();
        } else if (i2 == 1) {
            bVarG.m();
        }
    }

    public Gson b() {
        Gson gson = this.r;
        if (gson != null) {
            return gson;
        }
        Gson gsonB = new e().c(Integer.class, new IntegerDefaultAdapter()).c(Integer.TYPE, new IntegerDefaultAdapter()).c(Double.class, new DoubleDefaultAdapter()).c(Double.TYPE, new DoubleDefaultAdapter()).c(Long.class, new LongDefaultAdapter()).c(Long.TYPE, new LongDefaultAdapter()).c(Boolean.class, new BooleanDefaultAdapter()).c(Boolean.TYPE, new BooleanDefaultAdapter()).c(String.class, new StringNullAdapter()).c(Short.class, new CustomJsonAdapter()).c(Short.TYPE, new CustomJsonAdapter()).b();
        this.r = gsonB;
        return gsonB;
    }

    public boolean d() {
        return ((this.p >>> 2) & 3) != 1;
    }

    public Context f() {
        return this.m;
    }

    public DataCollectBean g() {
        return this.l;
    }

    public byte i() {
        return (byte) (this.p & 3);
    }

    @Override // android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener, a.a.d
    public void isConnected(boolean z) {
        if (z) {
            BYDAutoStudy bYDAutoStudy = BYDAutoStudy.getInstance(this.m);
            m();
            int driveConfig = bYDAutoStudy.getDriveConfig();
            int cloudServieEv = BYDAutoTestDevice.getInstance(this.m).getCloudServieEv();
            f.g(this.f3457b, "BYDAutoStudy connect driveConfig =" + driveConfig + ",testEv=" + cloudServieEv);
            if (cloudServieEv == 1 && this.s < 61) {
                l(false, 61);
            }
            k(this.m, driveConfig);
            g.d("drive_motor_num", this.q);
        }
    }

    public void l(boolean z, int i2) {
        int i3;
        if (z) {
            i3 = SystemProperties.getInt("persist.vendor.byd.countrycode", 30);
            i2 = SystemProperties.getInt("persist.vendor.byd.data.collect.debug.on", 0);
            if (i2 < 61 && (g.a("log_level") == 1 || i3 == 86)) {
                i2 |= 61;
            }
        } else {
            i3 = 0;
        }
        if (i2 > 0) {
            i2 |= 61;
        }
        byte b2 = (byte) i2;
        this.s = b2;
        f.f3426a = (b2 & 1) == 1;
        f.g(this.f3457b, "initLogFlag level=" + ((int) this.s) + ",countryCode =" + i3 + "debug=" + f.f3426a);
    }

    public boolean n(Context context) {
        if (this.l == null && t(context, r()) && this.q == 0) {
            return false;
        }
        if (this.q == 0 && j(context) == 0) {
            return false;
        }
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
        int powerLevel = bYDAutoBodyworkDevice.getPowerLevel();
        if (1 == bYDAutoBodyworkDevice.hasMessage(149) || !(powerLevel == 0 || powerLevel == 255)) {
            return true;
        }
        f.g(this.f3457b, "powerLevel =" + powerLevel);
        return false;
    }

    public boolean o() {
        return ((this.s >>> 3) & 1) == 1;
    }

    public boolean p() {
        return ((this.s >>> 2) & 1) == 1;
    }

    public boolean q() {
        return ((this.s >>> 1) & 1) == 1;
    }

    public boolean r() {
        return this.f3458c == 2;
    }

    public boolean s() {
        byte b2 = this.p;
        return ((b2 & 12) == 4 || (b2 & 3) == 1) ? false : true;
    }

    public void w() {
        BYDAutoStudy.getInstance(this.m).unregisterListener(this);
        Context context = this.m;
        if (context != null && this.n != null) {
            context.getContentResolver().unregisterContentObserver(this.n);
            this.n = null;
        }
        this.m = null;
    }

    public void x(byte b2) {
        this.p = (byte) ((b2 & 3) | (this.p & 12));
    }
}
