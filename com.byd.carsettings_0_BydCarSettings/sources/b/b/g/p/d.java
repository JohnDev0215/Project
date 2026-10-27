package b.b.g.p;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.INotificationManager;
import android.app.UiModeManager;
import android.app.timedetector.TimeDetector;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.time.BYDAutoTimeDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.net.http.SslCertificate;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.security.IKeyChainService;
import android.security.KeyChain;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import b.b.d.b;
import b.b.g.g.f;
import b.b.g.n.q.m;
import b.b.g.n.q.p;
import b.b.g.n.q.t;
import b.b.g.o.a;
import b.b.g.o.c;
import b.b.g.r.l;
import b.b.g.r.v;
import com.android.internal.app.LocalePicker;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydDate;
import com.byd.carsettings.sound.model.Manufactor;
import com.byd.carsettings.system.BydStorageManager;
import com.byd.carsettings.system.model.BydLanguage;
import com.byd.carsettings.system.model.ClockStyle;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.BydSettingsApi;
import com.byd.systemsettings.hotspot.HotspotManager;
import com.byd.systemsettings.wifi2.BydWifiManager;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydSystemSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public UiModeManager f1366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoVersionDevice f1367b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoSettingDevice f1368c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public KeyChain.KeyChainConnection f1369d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public INotificationManager f1370e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.b.g.n.q.h f1371f;
    public b.b.g.n.q.a g;
    public BYDAutoTimeDevice h;
    public volatile boolean i;
    public volatile long j;

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1372a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1373b;

        public a(Context context, int i) {
            this.f1372a = context;
            this.f1373b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.j = System.currentTimeMillis();
            d dVar = d.this;
            dVar.A0(this.f1372a, dVar.x(this.f1373b));
        }
    }

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SparseArray f1375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f1376b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.b.g.p.j.b f1377c;

        public b(SparseArray sparseArray, Context context, b.b.g.p.j.b bVar) {
            this.f1375a = sparseArray;
            this.f1376b = context;
            this.f1377c = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.d("BydSystemSettings", "---resetSettingOptions----Thread START.");
            if (this.f1375a.size() >= 5) {
                b.b.m.c.a.d("BydSystemSettings", "---resetSettingOptions---reset prepare.");
                d.this.f1368c.resetSystemSetting(1);
                SystemClock.sleep(500L);
            }
            boolean z = false;
            int i = 0;
            for (int i2 = 0; i2 < this.f1375a.size(); i2++) {
                Integer num = (Integer) this.f1375a.valueAt(i2);
                if (num != null) {
                    if (i > 0) {
                        SystemClock.sleep(500L);
                    }
                    i++;
                    b.b.m.c.a.d("BydSystemSettings", "---resetSettingOptions--do reset, item:" + num);
                    int iIntValue = num.intValue();
                    if (iIntValue == 0) {
                        d.this.o0(this.f1376b);
                        d.this.n0();
                        z = true;
                    } else if (iIntValue == 1) {
                        d.this.f1368c.setAdasRest(new int[]{1, 1, 1});
                        SystemClock.sleep(300L);
                    } else if (iIntValue == 2) {
                        d.this.f1368c.newEnergyDefaultReset(1);
                        SystemClock.sleep(300L);
                        d.this.f1368c.outChargeDefaultReset(1);
                        SystemClock.sleep(300L);
                    } else if (iIntValue == 3) {
                        d.this.f1368c.setCarsettingRest(new int[]{1, 1, 1, 1, 1, 1, 1, 1});
                        SystemClock.sleep(500L);
                        d.this.f1368c.driveSettingsReset(1);
                        SystemClock.sleep(300L);
                        d.this.m0();
                        SystemClock.sleep(300L);
                        d.this.t0(this.f1376b);
                        SystemClock.sleep(300L);
                        d.this.q0();
                        SystemClock.sleep(300L);
                        d.this.r0();
                        SystemClock.sleep(300L);
                        d.this.l0();
                    } else if (iIntValue == 4) {
                        d.this.f1368c.carHealthDefaultReset(1);
                        SystemClock.sleep(300L);
                        v.d().k("KEY_MAINTAIN_MILE_STATE");
                        v.d().k("KEY_MAINTAIN_TIME_STATE");
                        v.d().k("KEY_MAINTAIN_MILE_1");
                        v.d().k("KEY_MAINTAIN_TIME");
                        v.d().k("KEY_MAINTAIN_MILE_TARGET");
                        v.d().k("KEY_MAINTAIN_TIME_TARGET");
                        EventBus.getDefault().post(new b.b.a.c.a(200009));
                    }
                }
            }
            if (z) {
                b.b.m.c.a.d("BydSystemSettings", "---resetSettingOptions----reset DiLinkSettingOptionsWithLifecycle.");
                d.this.p0(this.f1376b, null);
            }
            b.b.g.p.j.b bVar = this.f1377c;
            if (bVar != null) {
                bVar.d();
            }
            if (z) {
                d.this.A0(this.f1376b, 2);
            }
            b.b.m.c.a.d("BydSystemSettings", "---resetSettingOptions----Thread END.");
        }
    }

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class c implements IServiceConnectionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BYDAutoAudioDevice f1379a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f1380b;

        public c(BYDAutoAudioDevice bYDAutoAudioDevice, boolean z) {
            this.f1379a = bYDAutoAudioDevice;
            this.f1380b = z;
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceConnected(boolean z) {
            BYDAutoAudioDevice bYDAutoAudioDevice;
            b.b.a.h.a.b(this, "onServiceConnected : " + z);
            if (!z || (bYDAutoAudioDevice = this.f1379a) == null) {
                return;
            }
            bYDAutoAudioDevice.setVSPState(!this.f1380b ? 1 : 0);
            this.f1379a.unregisterConnectionListener(this);
            b.b.a.h.a.b(c.class.getSimpleName(), "avas : state " + (!this.f1380b ? 1 : 0));
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceDisconnected(boolean z) {
        }
    }

    /* JADX INFO: renamed from: b.b.g.p.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class RunnableC0064d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.d.b f1382a;

        public RunnableC0064d(b.b.d.b bVar) {
            this.f1382a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1382a.Q();
            String str = SystemProperties.get("persist.bluetooth.default.name");
            b.b.m.c.a.a("BydSystemSettings", "Rest BT NAME:" + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f1382a.M(str);
        }
    }

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.g.p.j.b f1384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f1385b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ BydLanguage f1386c;

        public e(b.b.g.p.j.b bVar, Context context, BydLanguage bydLanguage) {
            this.f1384a = bVar;
            this.f1385b = context;
            this.f1386c = bydLanguage;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.g("BydSystemSettings", "---restoreFactorySettings----Thread START.");
            b.b.g.p.j.b bVar = this.f1384a;
            if (bVar != null) {
                bVar.c();
            }
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset CAN prepare.");
            d.this.f1368c.resetSystemSetting(1);
            SystemClock.sleep(500L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset DiLink CAN.");
            d.this.n0();
            SystemClock.sleep(300L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset ADAS.");
            d.this.f1368c.setAdasRest(new int[]{1, 1, 1});
            SystemClock.sleep(300L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset CarSettings.");
            d.this.f1368c.setCarsettingRest(new int[]{1, 1, 1, 1, 1, 1, 1, 1});
            SystemClock.sleep(500L);
            d.this.f1368c.driveSettingsReset(1);
            SystemClock.sleep(300L);
            d.this.m0();
            SystemClock.sleep(300L);
            d.this.t0(this.f1385b);
            SystemClock.sleep(300L);
            d.this.q0();
            SystemClock.sleep(300L);
            d.this.r0();
            SystemClock.sleep(300L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset NewEnergy and CarHealth.");
            d.this.f1368c.driveSettingsReset(1);
            SystemClock.sleep(300L);
            d.this.f1368c.newEnergyDefaultReset(1);
            SystemClock.sleep(300L);
            d.this.f1368c.carHealthDefaultReset(1);
            SystemClock.sleep(300L);
            d.this.f1368c.outChargeDefaultReset(1);
            SystemClock.sleep(300L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset SystemSettings.");
            d.this.o0(this.f1385b);
            SystemClock.sleep(300L);
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset AudioSettings.");
            b.b.g.o.a aVarA = a.b.a();
            aVarA.A(this.f1385b);
            aVarA.f0(b.b.g.o.a.f1309d);
            SystemClock.sleep(300L);
            m.p0().g2(1);
            SystemClock.sleep(300L);
            if (!ActivityManager.isUserAMonkey()) {
                d.this.f1368c.factoryResetAll(1);
                SystemClock.sleep(500L);
            }
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings----reset DiLinkSettingOptionsWithLifecycle.");
            d.this.p0(this.f1385b, this.f1386c);
            d.this.l0();
            b.b.g.p.j.b bVar2 = this.f1384a;
            if (bVar2 != null) {
                bVar2.d();
            }
            d.this.A0(this.f1385b, 2);
            b.b.m.c.a.g("BydSystemSettings", "---restoreFactorySettings----Thread END.");
        }
    }

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BydLanguage f1388a;

        public f(BydLanguage bydLanguage) {
            this.f1388a = bydLanguage;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.a("BydSystemSettings", "setLanguage:" + this.f1388a);
            m.p0().I2(this.f1388a.getCanValue());
            d.this.m(this.f1388a.getLocale());
        }
    }

    /* JADX INFO: compiled from: BydSystemSettings.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f1390a = new d(null);

        public static d a() {
            return f1390a;
        }
    }

    public /* synthetic */ d(a aVar) {
        this();
    }

    public int A() {
        int iV = this.f1371f.v();
        b.b.m.c.a.a("BydSystemSettings", "getDistanceUnit:" + iV);
        return iV;
    }

    public void A0(Context context, int i) {
        b.b.m.c.a.a("BydSystemSettings", "--setDayNightMode--mode = " + i);
        if (context == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        Settings.Global.putInt(context.getContentResolver(), "ui_night_mode", i);
        try {
            this.f1366a.setNightMode(i);
        } catch (Exception e2) {
            b.b.m.c.a.c("BydSystemSettings", "--setDayNightMode--e: " + e2);
        }
    }

    public String B() {
        return "";
    }

    public void B0(Context context, int i) {
        b.b.m.c.a.a("BydSystemSettings", "--setDayNightModeByIndex--index = " + i);
        b.b.a.f.h.a().d(new a(context, i));
    }

    public List<b.b.g.m.d.b> C(Context context) {
        if (context == null) {
            return null;
        }
        List<InputMethodInfo> listD = D(context);
        ArrayList arrayList = new ArrayList();
        String str = Settings.Secure.getString(context.getContentResolver(), "default_input_method").split("/")[0];
        b.b.m.c.a.a("BydSystemSettings", "--getEnabledInputMethodList getCurInputMethod---pkg = " + str);
        for (InputMethodInfo inputMethodInfo : listD) {
            b.b.g.m.d.b bVar = new b.b.g.m.d.b();
            bVar.h(inputMethodInfo);
            String string = inputMethodInfo.loadLabel(context.getPackageManager()).toString();
            if ("com.iqqijni.car.keyboard".equals(inputMethodInfo.getPackageName())) {
                bVar.j(context.getString(R.string.kika_input_sub_lable));
            }
            bVar.i(string);
            bVar.g(str.equals(inputMethodInfo.getPackageName()));
            arrayList.add(bVar);
        }
        return arrayList;
    }

    public void C0(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ComponentName[] componentNameArr = new ComponentName[listQueryIntentActivities.size()];
        b.b.m.c.a.a("BydSystemSettings", "--setDefaultLauncher---pkg = " + str);
        ComponentName componentName = null;
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ActivityInfo activityInfo = listQueryIntentActivities.get(i).activityInfo;
            b.b.m.c.a.a("BydSystemSettings", "--setDefaultLauncher---packageName = " + activityInfo.packageName);
            ComponentName componentName2 = new ComponentName(activityInfo.packageName, activityInfo.name);
            componentNameArr[i] = componentName2;
            if (activityInfo.packageName.equals(str)) {
                componentName = componentName2;
            }
        }
        b.b.m.c.a.a("BydSystemSettings", "--setDefaultLauncher---newHome = " + componentName);
        if (componentName == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MAIN");
        intentFilter.addCategory("android.intent.category.HOME");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        packageManager.replacePreferredActivity(intentFilter, 1048576, componentNameArr, componentName);
    }

    public List<InputMethodInfo> D(Context context) {
        if (context == null) {
            return null;
        }
        return ((InputMethodManager) context.getSystemService("input_method")).getEnabledInputMethodList();
    }

    public void D0(BydLanguage bydLanguage) {
        if (bydLanguage == null) {
            return;
        }
        b.b.m.c.a.a("BydSystemSettings", "---setLanguage---language:" + bydLanguage);
        b.b.a.f.h.a().j(new f(bydLanguage));
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    public String E(Context context) {
        return context == null ? "" : p.c().a(context);
    }

    public void E0(Context context, int i) {
        b.b.m.c.a.a("BydSystemSettings", "--setLauncherMode---index = " + i);
        String str = "com.neusoft.na.navigation";
        if (i != 0 && i == 1) {
            str = "com.android.launcher3";
        }
        C0(context, str);
        b.b.g.p.c.b("persist.sys.defualt.launcher", String.valueOf(1));
    }

    @SuppressLint({"NewApi", "HardwareIds"})
    public String F(Context context) {
        return context == null ? "" : p.c().b(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void F0(Context context, BydDate bydDate) {
        if (AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        int hour = bydDate.getHour();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, bydDate.getYear());
        calendar.set(2, bydDate.getMonth() - 1);
        calendar.set(5, bydDate.getDay());
        if (bydDate.isH24()) {
            calendar.set(11, hour);
        } else {
            calendar.set(9, !bydDate.isAm() ? 1 : 0);
            calendar.set(10, hour);
        }
        calendar.set(12, bydDate.getMinute());
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        b.b.m.c.a.a("BydSystemSettings", "--setLocalTime---time = " + timeInMillis + ", d = " + bydDate);
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if ((Settings.Global.getInt(contentResolver, "auto_time_zone") > 0) != false) {
                Settings.Global.putInt(contentResolver, "auto_time_zone", 0);
            }
            if ((Settings.Global.getInt(contentResolver, CarSettings.UserTableData.AUTO_TIME) > 0) != false) {
                Settings.Global.putInt(contentResolver, CarSettings.UserTableData.AUTO_TIME, 0);
            }
        } catch (Settings.SettingNotFoundException e2) {
            b.b.m.c.a.c("BydSystemSettings", "--doSetLocalTime--e:" + e2);
        }
        if (timeInMillis / 1000 < 2147483647L) {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setTimeZone(TimeZone.getDefault().getID());
        }
        ((TimeDetector) context.getSystemService(TimeDetector.class)).suggestManualTime(TimeDetector.createManualTimeSuggestion(timeInMillis, "Settings: Set date"));
        context.sendBroadcast(new Intent("android.intent.action.TIME_SET"));
    }

    public String G() {
        return this.f1367b.getInstrumentHwVersion();
    }

    public void G0(Context context, boolean z) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.a("BydSystemSettings", "---setMemoryPlaybackState----enable：" + z);
        Settings.System.putInt(context.getContentResolver(), "byd_memory_playback_state", z ? 1 : 0);
    }

    public String H() {
        return this.f1367b.getInstrumentSwVersion();
    }

    public void H0(Context context, int i) {
        if (context == null) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        Settings.Global.putInt(context.getContentResolver(), BydSettingsApi.NAVIBAR_MODE_ON_SCREENSAVER, i);
    }

    public String I() {
        return b.b.g.p.c.a("isa_active_data_version", null);
    }

    public void I0(String str, int i, boolean z) {
        try {
            this.f1370e.setNotificationsEnabledForPackage(str, i, z);
        } catch (RemoteException e2) {
            b.b.m.c.a.c("BydSystemSettings", "--setNotificationsEnabledForPackage---e: " + e2.toString());
        }
    }

    public KeyChain.KeyChainConnection J() {
        return this.f1369d;
    }

    public void J0(Context context, ClockStyle clockStyle) {
        b.b.m.c.a.a("BydSystemSettings", "--setScreenSaverClockStyle---style.value = " + clockStyle.value);
        if (context != null) {
            Settings.Global.putInt(context.getContentResolver(), "byd_screensaver_mode", clockStyle.value);
        }
    }

    public int K(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return 1;
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        b.b.m.c.a.a("BydSystemSettings", "--getLauncherMode---pkg = " + str);
        return (str == null || !"com.android.launcher3".equals(str)) ? 0 : 1;
    }

    public void K0(Context context, boolean z) {
        b.b.m.c.a.a("BydSystemSettings", "--setTimeAutoSync--enable:" + z);
        if (AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        Settings.Global.putInt(context.getContentResolver(), "auto_time_zone", z ? 1 : 0);
    }

    public boolean L(Context context) {
        return context != null && Settings.System.getInt(context.getContentResolver(), "byd_memory_playback_state", 1) > 0;
    }

    public void L0(Context context, boolean z) {
        if (context == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        b.b.m.c.a.d("BydSystemSettings", "--setTimeSystem--is24H = " + z);
        this.h.setTimeFormat(z ? 1 : 0);
    }

    public int M() {
        int iC = this.f1371f.C(2);
        b.b.m.c.a.a("BydSystemSettings", "getOliUnit:" + iC);
        return iC;
    }

    public void M0(int i, int i2) {
        b.b.m.c.a.a("BydSystemSettings", "setUnit-unitName:" + i + ",unitValue:" + i2);
        this.f1371f.W(i, i2);
    }

    @SuppressLint({"MissingPermission"})
    public String N(Context context) {
        return context == null ? "" : ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }

    public void N0(Context context) {
        String str = Settings.Secure.getString(context.getContentResolver(), "default_input_method").split("/")[0];
        for (InputMethodInfo inputMethodInfo : ((InputMethodManager) context.getSystemService("input_method")).getEnabledInputMethodList()) {
            if (str.equals(inputMethodInfo.getPackageName())) {
                String settingsActivity = inputMethodInfo.getSettingsActivity();
                if (settingsActivity != null && !settingsActivity.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setClassName(str, settingsActivity);
                    try {
                        context.startActivity(intent);
                        return;
                    } catch (Exception e2) {
                        b.b.m.c.a.a("BydSystemSettings", "--startActivity---Exception = " + e2);
                        return;
                    }
                }
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                b.b.m.c.a.a("BydSystemSettings", "--startDefInputMethodSettings---in = " + launchIntentForPackage);
                if (launchIntentForPackage != null) {
                    try {
                        context.startActivity(launchIntentForPackage);
                        return;
                    } catch (Exception e3) {
                        b.b.m.c.a.a("BydSystemSettings", "--startActivity---Exception = " + e3);
                        return;
                    }
                }
                return;
            }
        }
    }

    public int O() {
        int iC = this.f1371f.C(3);
        b.b.m.c.a.a("BydSystemSettings", "getPowerUnit:" + iC);
        return iC;
    }

    public void O0(Context context, b.b.g.p.j.d dVar) {
        if (context == null || dVar == null) {
            return;
        }
        context.getContentResolver().unregisterContentObserver(dVar);
    }

    public int P() {
        int iC = this.f1371f.C(1);
        b.b.m.c.a.a("BydSystemSettings", "getPressureUnit:" + iC);
        return iC;
    }

    public ClockStyle Q(Context context) {
        if (context == null) {
            return ClockStyle.CLOSE;
        }
        int i = Settings.Global.getInt(context.getContentResolver(), "byd_screensaver_mode", ClockStyle.CLOSE.value);
        b.b.m.c.a.a("BydSystemSettings", "--getScreenSaverClockStyle---mode = " + i);
        return ClockStyle.getStyle(i);
    }

    public int R() {
        int iB = this.g.b();
        b.b.m.c.a.a("BydSystemSettings", "getTemperatureUnit:" + iB);
        return iB;
    }

    public int S(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        b.b.m.c.a.a("BydSystemSettings", "--getTimeSystem--v:" + string);
        return TimeManagerImpl.TIME_FORMAT_TWELVE.equals(string) ? 1 : 0;
    }

    @RequiresApi(api = 26)
    public List<b.b.g.m.d.e> T(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Map<String, Object> map : b.b.g.p.l.b.g(context)) {
            b.b.g.m.d.e eVar = new b.b.g.m.d.e();
            eVar.g(String.valueOf(map.get("id")));
            eVar.h(String.valueOf(map.get("display_label")));
            eVar.f(String.valueOf(map.get("gmt")));
            arrayList.add(eVar);
        }
        return arrayList;
    }

    public String U() {
        return this.f1367b.getVehicleSN();
    }

    public boolean V(Context context) {
        boolean z;
        PackageInfo packageInfo;
        try {
            z = SystemProperties.getBoolean("persist.sys.byd.navi_launcher", true);
        } catch (RuntimeException unused) {
            z = true;
        }
        PackageInfo packageInfo2 = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.neusoft.na.navigation", 0);
        } catch (PackageManager.NameNotFoundException unused2) {
            packageInfo = null;
        }
        try {
            packageInfo2 = context.getPackageManager().getPackageInfo("com.huawei.maps.auto.app", 0);
        } catch (PackageManager.NameNotFoundException unused3) {
        }
        b.b.m.c.a.d("BydSystemSettings", "--hasLauncherModeSelector---value: " + z + ", navPackageInfo: " + packageInfo + ",huaweiPackageInfo:" + packageInfo2);
        return z && !(packageInfo == null && packageInfo2 == null);
    }

    public void W(Context context) {
        if (this.i || context == null) {
            return;
        }
        this.i = false;
        Context applicationContext = context.getApplicationContext();
        this.f1367b = BYDAutoVersionDevice.getInstance(context.getApplicationContext());
        this.f1368c = BYDAutoSettingDevice.getInstance(context.getApplicationContext());
        UiModeManager uiModeManager = (UiModeManager) applicationContext.getSystemService("uimode");
        this.f1366a = uiModeManager;
        int currentModeType = uiModeManager.getCurrentModeType();
        b.b.m.c.a.a("BydSystemSettings", "---init---UiType = " + currentModeType);
        this.f1366a.enableCarMode(currentModeType);
        this.f1370e = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        this.f1371f = b.b.g.n.q.h.m();
        this.g = b.b.g.n.q.a.a();
        this.h = BYDAutoTimeDevice.getInstance(context);
        this.i = true;
    }

    public InputMethodInfo X(Context context) {
        for (InputMethodInfo inputMethodInfo : D(context)) {
            if ("com.android.inputmethod.latin".equals(inputMethodInfo.getPackageName())) {
                return inputMethodInfo;
            }
        }
        return null;
    }

    public boolean Y(Context context) {
        int i;
        if (context == null) {
            return true;
        }
        b.b.m.c.a.d("BydSystemSettings", "--isBrightnessAutoMode--");
        try {
            i = Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode");
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            i = 0;
        }
        b.b.m.c.a.d("BydSystemSettings", "--isBrightnessAutoMode--mode = " + i);
        return i == 1;
    }

    public boolean Z(Context context) {
        int i;
        if (context == null) {
            return true;
        }
        try {
            i = Settings.System.getInt(context.getContentResolver(), "multimedia_brightness_linkage");
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            i = 1;
        }
        b.b.m.c.a.d("BydSystemSettings", "--isBrightnessLinkageEnable--mode: " + i);
        return i == 1;
    }

    public final boolean a0(List<InputMethodInfo> list, InputMethodInfo inputMethodInfo) {
        Iterator<InputMethodInfo> it = list.iterator();
        while (it.hasNext()) {
            if (inputMethodInfo.getPackageName().equals(it.next().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public InputMethodInfo b0(Context context) {
        for (InputMethodInfo inputMethodInfo : D(context)) {
            if ("com.iqqijni.car.keyboard".equals(inputMethodInfo.getPackageName())) {
                return inputMethodInfo;
            }
        }
        return null;
    }

    public boolean c0(Context context) {
        if (context == null) {
            return false;
        }
        String string = context.getResources().getConfiguration().getLocales().get(0).toString();
        b.b.m.c.a.a("BydSystemSettings", "--isLanguageChinese--langTag:" + string);
        if (string == null) {
            return false;
        }
        return string.startsWith("zh");
    }

    public boolean d0(String str, int i) {
        b.b.m.c.a.a("BydSystemSettings", ", pkg: " + str);
        try {
            return this.f1370e.areNotificationsEnabledForPackage(str, i);
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean e0(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "auto_time_zone") > 0;
        } catch (Settings.SettingNotFoundException e2) {
            b.b.m.c.a.c("BydSystemSettings", "--isTimeAutoSync--e:" + e2);
            return false;
        }
    }

    public boolean f0() {
        return g0(b.b.g.p.c.a(BYDAutoSysPropertiesDevice.COUNTRY_CODE, null));
    }

    public boolean g0(String str) {
        if (str == null) {
            return true;
        }
        b.b.m.c.a.a("BydSystemSettings", "---isWifiUnsupport5GHzBand----countryCode：" + str);
        String[] strArr = b.b.g.f.a.f758a;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void h0(Context context, String str, b.b.g.p.j.d dVar) {
        if (context == null || dVar == null) {
            return;
        }
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(str), false, dVar, -1);
    }

    public void i0(Context context, String str, b.b.g.p.j.d dVar) {
        if (context == null || dVar == null) {
            return;
        }
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(str), false, dVar, -1);
    }

    public void j0() {
        this.i = false;
    }

    public void k(Context context, b.b.g.m.d.b bVar) {
        if (context == null || bVar == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        String string = Settings.Secure.getString(contentResolver, "enabled_input_methods");
        StringBuilder sb = new StringBuilder(string);
        String id = bVar.a().getId();
        if (string.contains(id)) {
            return;
        }
        sb.append(":");
        sb.append(id);
        Settings.Secure.putString(contentResolver, "enabled_input_methods", sb.toString());
    }

    public void k0(Context context, b.b.g.m.d.b bVar) {
        if (context == null || bVar == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        String string = Settings.Secure.getString(contentResolver, "enabled_input_methods");
        StringBuilder sb = new StringBuilder(string);
        String id = bVar.a().getId();
        if (string.contains(id)) {
            int iIndexOf = sb.indexOf(id);
            sb.delete(iIndexOf != 0 ? iIndexOf - 1 : iIndexOf, iIndexOf + id.length());
            Settings.Secure.putString(contentResolver, "enabled_input_methods", sb.toString());
        }
    }

    public void l(Context context, InputMethodInfo inputMethodInfo) {
        if (context == null || inputMethodInfo == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        Settings.Secure.putString(context.getContentResolver(), "default_input_method", inputMethodInfo.getId());
        b.b.m.c.a.a("BydSystemSettings", "--changeInputMethod---in = " + inputMethodInfo);
    }

    public void l0() {
        SystemClock.sleep(300L);
        m.p0().X1(1);
    }

    public void m(Locale locale) {
        if (AppConfig.b() == AppConfig.AppMode.DEBUG || locale == null) {
            return;
        }
        b.b.m.c.a.a("BydSystemSettings", "--changeSystemLanguage--locale:" + locale.toString());
        InputMethodInfo inputMethodInfoB0 = b0(BydApp.getContext());
        if (inputMethodInfoB0 == null || !(locale.equals(Locale.JAPANESE) || locale.equals(Locale.KOREAN) || locale.equals(Locale.SIMPLIFIED_CHINESE) || locale.equals(Locale.forLanguageTag("zh-HK")))) {
            InputMethodInfo inputMethodInfoX = X(BydApp.getContext());
            if (inputMethodInfoX != null) {
                l(BydApp.getContext(), inputMethodInfoX);
            }
        } else {
            l(BydApp.getContext(), inputMethodInfoB0);
        }
        LocalePicker.updateLocale(locale);
        f.b.a().c(null);
    }

    public final void m0() {
        t.b().k(1);
        t.b().a();
        t.b().i(1);
        t.b().d();
        t.b().l(1);
        t.b().g();
    }

    public String n() {
        return "";
    }

    public final void n0() {
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkCanSettings----START--");
        try {
            b.b.g.o.c cVarA = c.d.a();
            cVarA.D();
            SystemClock.sleep(300L);
            cVarA.w();
            SystemClock.sleep(300L);
            cVarA.x();
            SystemClock.sleep(300L);
            cVarA.Q(4);
            SystemClock.sleep(300L);
            cVarA.H();
            SystemClock.sleep(300L);
            cVarA.G();
            SystemClock.sleep(300L);
        } catch (RuntimeException e2) {
            b.b.m.c.a.c("BydSystemSettings", "---resetDiLinkCanSettings---e: " + e2);
        }
        this.f1368c.setDlinkRest(new int[]{1, 1, 1, 1});
        SystemClock.sleep(300L);
        this.f1368c.clockDefaultReset(1);
        SystemClock.sleep(300L);
        this.f1368c.languageDefaultReset(1);
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkCanSettings----END--");
    }

    public int o(Context context) {
        int i;
        if (context == null) {
            return 0;
        }
        b.b.m.c.a.d("BydSystemSettings", "--getBrightness--");
        try {
            i = Settings.System.getInt(context.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            i = 0;
        }
        b.b.m.c.a.d("BydSystemSettings", "--getBrightness--brightness = " + i);
        return i;
    }

    public final void o0(Context context) {
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkSettingOptions----START--");
        p.c().g(context, true);
        b.b.d.b bVarA = b.f.a();
        bVarA.h(true);
        if (bVarA.v()) {
            SystemClock.sleep(300L);
        }
        b.b.a.f.h.a().e(new RunnableC0064d(bVarA), 2000L);
        BydWifiManager bydWifiManager = BydWifiManager.Singleton.get();
        bydWifiManager.forgetAllNetwork();
        bydWifiManager.setEnabled(false);
        HotspotManager hotspotManager = HotspotManager.Singleton.get();
        hotspotManager.resetConfiguration(context);
        hotspotManager.setHotspotEnable(context, false);
        b.b.g.o.a aVarA = a.b.a();
        aVarA.A(context);
        aVarA.U();
        J0(context, ClockStyle.CLOSE);
        v0(context, 169);
        w0(context, true);
        E0(context, 1);
        K0(context, true);
        L0(context, true);
        G0(context, true);
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkSettingOptions----END--");
    }

    public String p() {
        return this.f1367b.getHwVersion();
    }

    public final void p0(Context context, BydLanguage bydLanguage) {
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkSettingOptionsWithLifecycle----START--language: " + bydLanguage);
        if (bydLanguage == null || bydLanguage.getLocale() == null) {
            int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
            b.b.m.c.a.a("BydSystemSettings", "---setDefaultLanguage----codeNum--" + i);
            BYDAutoTestDevice.getInstance(context).setDefaultLanguage(i);
        } else {
            m(bydLanguage.getLocale());
        }
        b.b.m.c.a.a("BydSystemSettings", "---resetDiLinkSettingOptionsWithLifecycle----END--");
    }

    public String q(Context context) {
        return this.f1367b.getBydSystemVersion();
    }

    public final void q0() {
        b.b.g.n.q.h.m().R(1);
        b.b.g.n.q.h.m().U(1);
        SystemClock.sleep(300L);
        m.p0().l3(241);
        SystemClock.sleep(300L);
        b.b.g.n.q.h.m().N(8);
        SystemClock.sleep(300L);
    }

    public b.b.g.m.d.f r(Context context) {
        Manufactor manufactor;
        b.b.g.o.h.a aVarD = c.d.a().d();
        String strName = (aVarD == null || (manufactor = aVarD.f1342a) == null) ? "" : manufactor.name();
        b.b.g.m.d.f fVar = new b.b.g.m.d.f();
        fVar.h(q(context));
        fVar.k(p());
        fVar.o(H());
        fVar.n(G());
        fVar.m(F(context));
        fVar.l(E(context));
        fVar.i(t());
        fVar.p(N(context));
        fVar.j(B());
        fVar.f(strName);
        fVar.g(n());
        fVar.r(U());
        String strI = I();
        b.b.m.c.a.a("BydSystemSettings", "---getBydVersionInfo---isaActDataVersion:" + strI);
        if (TextUtils.isEmpty(strI)) {
            strI = "EU.1.2.1508.1";
        }
        fVar.q(strI);
        return fVar;
    }

    public final void r0() {
        m.p0().L1(1);
    }

    public Map<String, List<b.b.g.m.d.a>> s(Context context) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        map.put("root_cert", arrayList);
        map.put("user_cert", arrayList2);
        try {
            KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(context);
            this.f1369d = keyChainConnectionBind;
            if (keyChainConnectionBind != null) {
                IKeyChainService service = keyChainConnectionBind.getService();
                List<String> list = service.getSystemCaAliases().getList();
                List<String> list2 = service.getUserCaAliases().getList();
                y0(arrayList, list, service);
                y0(arrayList2, list2, service);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("BydSystemSettings", "getCertificates e" + e2);
        }
        return map;
    }

    public void s0(Context context, SparseArray<Integer> sparseArray, b.b.g.p.j.b bVar) {
        b.b.m.c.a.a("BydSystemSettings", "---resetSettingOptions---options:" + sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            return;
        }
        Thread thread = new Thread(new b(sparseArray, context, bVar));
        thread.setName("CarSettings-resetSettingOptions");
        thread.setPriority(10);
        thread.start();
    }

    public String[] t() {
        String[] mcuVersion = this.f1367b.getMcuVersion();
        return (mcuVersion == null || mcuVersion.length == 0) ? new String[0] : mcuVersion;
    }

    public final void t0(Context context) {
        b.b.g.o.a aVarA = a.b.a();
        aVarA.A(context);
        aVarA.f0(b.b.g.o.a.f1310e);
        Settings.System.putInt(context.getContentResolver(), "byd_report_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_start_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_park_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_door_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_seatbelt_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_leave_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_key_power_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_night_run_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_oil_low_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_auto_park_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "byd_uncheck_key_toggle", 1);
        Settings.System.putInt(context.getContentResolver(), "dms_waring_tips", 2);
        Settings.System.putInt(context.getContentResolver(), "forget_phone_tips", 1);
        Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", 1);
        if (BYDAutoTestDevice.getInstance(b.b.a.a.a.b().a()).getAutoAudioSource() != 0) {
            m.p0().c2(2);
        }
        boolean zD = l.d(R.array.avas_code_num);
        BYDAutoAudioDevice bYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a());
        bYDAutoAudioDevice.registerConnectionListener(new c(bYDAutoAudioDevice, zD));
        BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).setNavigationMediaState(1);
        m.p0().d2(0, 1);
        m.p0().d2(1, 1);
        m.p0().d2(2, 1);
    }

    public b.b.g.m.d.b u(Context context) {
        if (context == null) {
            return null;
        }
        List<InputMethodInfo> listD = D(context);
        String str = Settings.Secure.getString(context.getContentResolver(), "default_input_method").split("/")[0];
        b.b.m.c.a.a("BydSystemSettings", "--getCurInputMethod---pkg = " + str);
        b.b.g.m.d.b bVar = new b.b.g.m.d.b();
        for (InputMethodInfo inputMethodInfo : listD) {
            b.b.m.c.a.a("BydSystemSettings", "--getCurInputMethod---info = " + inputMethodInfo);
            if (str.equals(inputMethodInfo.getPackageName())) {
                bVar.h(inputMethodInfo);
                bVar.i(inputMethodInfo.loadLabel(context.getPackageManager()).toString());
            }
        }
        return bVar;
    }

    public void u0(Context context, BydLanguage bydLanguage, b.b.g.p.j.b bVar) {
        b.b.m.c.a.g("BydSystemSettings", "---restoreFactorySettings---onPrepare---");
        if (bVar != null) {
            bVar.b();
        }
        int i = BydStorageManager.i();
        b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings---onCheckStatus---availableSize: " + i + "%");
        if (bVar != null) {
            bVar.a(i);
        }
        if (i < 10) {
            b.b.m.c.a.d("BydSystemSettings", "---restoreFactorySettings---clearDataOnLowAvailableSize---");
            BydStorageManager.e(context);
        }
        Thread thread = new Thread(new e(bVar, context, bydLanguage));
        thread.setName("CarSettings-restoreFactorySettings");
        thread.setPriority(10);
        thread.start();
    }

    public Locale v(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public void v0(Context context, int i) {
        if (context == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        b.b.m.c.a.d("BydSystemSettings", "--setBrightness--brightness = " + i);
        Settings.System.putInt(context.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, i);
    }

    public TimeZone w() {
        return Calendar.getInstance().getTimeZone();
    }

    public void w0(Context context, boolean z) {
        if (context == null) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        b.b.m.c.a.d("BydSystemSettings", "--setBrightnessLinkageEnable--enable：" + z + ", mode: " + (z ? 1 : 0));
        Settings.System.putInt(contentResolver, "multimedia_brightness_linkage", z ? 1 : 0);
    }

    public final int x(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 1;
        }
        return 2;
    }

    public void x0(Context context, boolean z) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.d("BydSystemSettings", "--setBrightnessMode--auto：" + z + ", mode: " + (z ? 1 : 0));
        Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", z ? 1 : 0);
    }

    public int y(int i) {
        int i2 = i != 0 ? i != 1 ? 0 : 1 : 2;
        b.b.m.c.a.a("BydSystemSettings", "--getDayNightModeIndex--index = " + i2);
        return i2;
    }

    public final void y0(List<b.b.g.m.d.a> list, List<String> list2, IKeyChainService iKeyChainService) throws RemoteException {
        for (String str : list2) {
            X509Certificate certificate = KeyChain.toCertificate(iKeyChainService.getEncodedCaCertificate(str, true));
            SslCertificate sslCertificate = new SslCertificate(certificate);
            boolean zContainsCaAlias = iKeyChainService.containsCaAlias(str);
            String cName = sslCertificate.getIssuedTo().getCName();
            String oName = sslCertificate.getIssuedTo().getOName();
            String uName = sslCertificate.getIssuedTo().getUName();
            String str2 = "";
            if (oName.isEmpty()) {
                if (cName.isEmpty()) {
                    cName = sslCertificate.getIssuedTo().getDName();
                }
            } else if (cName.isEmpty()) {
                cName = oName;
                str2 = uName;
            } else {
                str2 = cName;
                cName = oName;
            }
            b.b.g.m.d.a aVar = new b.b.g.m.d.a();
            aVar.h(certificate);
            aVar.i(zContainsCaAlias);
            aVar.k(cName);
            aVar.j(str2);
            aVar.g(str);
            list.add(aVar);
            list.sort(aVar);
            b.b.m.c.a.a("BydSystemSettings", ", subjectPrimary: " + cName + ", subjectSecondary: " + str2 + ", isSelected: " + zContainsCaAlias);
        }
    }

    public List<b.b.g.m.d.b> z(Context context) {
        if (context == null) {
            return null;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        List<InputMethodInfo> inputMethodList = inputMethodManager.getInputMethodList();
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        ArrayList arrayList = new ArrayList();
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
            if (!a0(enabledInputMethodList, inputMethodInfo)) {
                b.b.g.m.d.b bVar = new b.b.g.m.d.b();
                bVar.h(inputMethodInfo);
                bVar.i(inputMethodInfo.loadLabel(context.getPackageManager()).toString().replaceAll("\\s*", ""));
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public void z0(Context context, String str) {
        if (AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setTimeZone(str);
    }

    public d() {
        this.i = false;
        this.j = 0L;
    }
}
