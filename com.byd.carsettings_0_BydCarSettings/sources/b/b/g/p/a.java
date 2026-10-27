package b.b.g.p;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import b.b.g.p.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AppManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1357a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List<String> f1358b = Arrays.asList(b.b.f.b.f651a, b.b.f.b.f653c, "com.byd.datashared", "vendor.qti.hardware.cacert.server", "com.byd.uereport", "android", "com.byd.data.collect", "com.byd.vrassistant", "com.byd.autoservice", "com.qualcomm.location", "com.byd.ota", "com.byd.card", "com.byd.sing", "com.byd.water.wallpaper", "com.byd.logtools", "com.byd.secretcode", "com.byd.engineermode", "com.byd.globalnotification", "com.telenav.scoutivi.spotify.byd", "com.byd.bluetoothcall", "com.byd.cleardata", "com.idd.hidps", "com.desay.engmode", "com.desaysv.ivi.vds.log", "com.desaysv.ivi.vds.upgrade", "com.amazon.mp3.automotiveOS", "skplanet.musicmate", "deezer.android.app", "com.aspiro.tidal", "com.iloen.melon", "tunein.player", "de.ard.audiothek", "au.com.shiftyjelly.pocketcasts", "com.clearchannel.iheartradio.controller", "com.audials", "com.radioline.android.radioline.auto", "com.overdrive.mobile.android.automotive.libby", "com.audible.application", "com.radiofrance.radio.radiofrance.android", "de.ard.sportschau.automotive", "de.heute.mobile", "com.bloomberg.android.plus");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f1359c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<String> f1360d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<String> f1361e;

    /* JADX INFO: compiled from: AppManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1362a = new a();

        public static a a() {
            return f1362a;
        }
    }

    public void a(Context context, b.b.g.p.k.a aVar, boolean z) {
        b.b.m.c.a.a(f1357a, "--enableAutoStart--name: " + aVar.a() + ", enable: " + z);
        context.getPackageManager().setComponentEnabledSetting(aVar.b(), z ? 2 : 1, 1);
        aVar.i(z);
    }

    public void b(b.b.g.p.k.a aVar, boolean z) {
        b.b.m.c.a.a(f1357a, ", pkg: " + aVar.d() + ", enable: " + z);
        this.f1359c.I0(aVar.d(), aVar.e(), z);
    }

    public List<b.b.g.p.k.a> c(Context context) {
        ActivityInfo activityInfo;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(new Intent("android.intent.action.BOOT_COMPLETED"), 512);
        if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() != 0) {
            Iterator<ResolveInfo> it = listQueryBroadcastReceivers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next != null && (activityInfo = next.activityInfo) != null) {
                    ApplicationInfo applicationInfo = activityInfo.applicationInfo;
                    if ((applicationInfo.flags & 1) == 0 || this.f1361e.contains(applicationInfo.packageName)) {
                        if (!this.f1360d.contains(applicationInfo.packageName)) {
                            b.b.g.p.k.a aVar = new b.b.g.p.k.a();
                            aVar.j(applicationInfo.loadIcon(packageManager));
                            PackageInfo packageInfo = new PackageInfo();
                            packageInfo.applicationInfo = applicationInfo;
                            aVar.j(h.f(applicationInfo.packageName, packageInfo, packageManager));
                            aVar.g(applicationInfo.loadLabel(packageManager).toString());
                            aVar.k(applicationInfo.packageName);
                            aVar.l(applicationInfo.uid);
                            ComponentName componentName = new ComponentName(applicationInfo.packageName, next.activityInfo.name);
                            aVar.h(componentName);
                            aVar.i(packageManager.getComponentEnabledSetting(componentName) == 2);
                            if (!arrayList.contains(aVar)) {
                                arrayList.add(aVar);
                            }
                        }
                    }
                }
            }
            b.b.m.c.a.a(f1357a, "getDisableSelfStartApps list size is " + arrayList.size());
        }
        return arrayList;
    }

    @SuppressLint({"WrongConstant"})
    public List<b.b.g.p.k.a> d(Context context) {
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        try {
            for (PackageInfo packageInfo : installedPackages) {
                String string = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                String str = packageInfo.applicationInfo.packageName;
                Drawable drawableF = h.f(str, packageInfo, packageManager);
                int i = packageInfo.applicationInfo.uid;
                if (!g.a() && !"com.byd.camera.remotectrl".equals(str) && !f1358b.contains(str) && (string == null || (!string.contains("com.") && !string.contains("ts.")))) {
                    if ((!str.contains("com.ts.") && !str.contains("com.android.") && !str.contains("com.core.") && !str.contains("com.google.android.") && !str.contains("android.ext.") && !str.contains("com.qualcomm.")) || str.equals("com.android.car.media")) {
                        boolean zD0 = this.f1359c.d0(str, i);
                        b.b.m.c.a.a(f1357a, ", pkg: " + str + ", enable: " + zD0);
                        arrayList.add(new b.b.g.p.k.a(drawableF, string, zD0, str, i));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public void e(Context context) {
        d dVarA = d.g.a();
        this.f1359c = dVarA;
        dVarA.W(context.getApplicationContext());
    }

    public a() {
        this.f1360d = new ArrayList();
        this.f1361e = new ArrayList();
    }
}
