package b.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AppManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f149a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<String> f150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<String> f151c;

    /* JADX INFO: renamed from: b.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppManager.java */
    public static class C0015b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f152a = new b();

        public static b a() {
            return f152a;
        }
    }

    public void a(Context context, b.a.a.a aVar, boolean z) {
        b.a.d.b.a.a(f149a, "--enableAutoStart--name: " + aVar.a() + ", enable: " + z);
        context.getPackageManager().setComponentEnabledSetting(aVar.b(), z ? 2 : 1, 1);
        aVar.g(z);
    }

    public List<b.a.a.a> b(Context context) {
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
                    if ((applicationInfo.flags & 1) == 0 || this.f151c.contains(applicationInfo.packageName)) {
                        if (!this.f150b.contains(applicationInfo.packageName)) {
                            b.a.a.a aVar = new b.a.a.a();
                            aVar.h(applicationInfo.loadIcon(packageManager));
                            PackageInfo packageInfo = new PackageInfo();
                            packageInfo.applicationInfo = applicationInfo;
                            aVar.h(d.a(applicationInfo.packageName, packageInfo, packageManager));
                            aVar.e(applicationInfo.loadLabel(packageManager).toString());
                            aVar.i(applicationInfo.packageName);
                            aVar.j(applicationInfo.uid);
                            ComponentName componentName = new ComponentName(applicationInfo.packageName, next.activityInfo.name);
                            aVar.f(componentName);
                            aVar.g(packageManager.getComponentEnabledSetting(componentName) == 2);
                            if (!arrayList.contains(aVar)) {
                                arrayList.add(aVar);
                            }
                        }
                    }
                }
            }
            b.a.d.b.a.a(f149a, "getDisableSelfStartApps list size is " + arrayList.size());
        }
        return arrayList;
    }

    public b() {
        this.f150b = new ArrayList();
        this.f151c = new ArrayList();
    }
}
