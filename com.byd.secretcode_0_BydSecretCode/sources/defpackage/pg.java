package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShareUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class pg {
    public static pg a;
    public Context b = null;
    public List<File> c = new ArrayList();

    public static pg c() {
        if (a == null) {
            a = new pg();
        }
        return a;
    }

    public List a(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listB = b(context);
        ng.a("ban", "getAllApps packageInfos.size() / " + listB.size());
        for (PackageInfo packageInfo : listB) {
            lg lgVar = new lg();
            lgVar.g(packageInfo.packageName);
            lgVar.e(packageInfo.applicationInfo.name);
            lgVar.f(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            lgVar.h(packageInfo.applicationInfo.loadIcon(packageManager));
            lgVar.i(packageInfo.versionCode);
            lgVar.j(packageInfo.versionName);
            ng.a("ban", "getShareAppList appInfo / " + lgVar.toString());
            arrayList.add(lgVar);
        }
        return arrayList;
    }

    public List b(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }
}
