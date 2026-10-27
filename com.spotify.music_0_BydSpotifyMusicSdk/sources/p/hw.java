package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.provider.Settings;
import com.spotify.base.java.logging.Logger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hw implements kr1 {
    public final /* synthetic */ int a = 1;
    public final Object b;

    public hw(List list) {
        v03.j(list, "packageValidators");
        this.b = list;
    }

    @Override // p.kr1
    public final boolean a(int i, String str) {
        PackageInfo packageInfo;
        Object obj = this.b;
        switch (this.a) {
            case 0:
                v03.j(str, "packageName");
                List list = (List) obj;
                if ((list instanceof Collection) && list.isEmpty()) {
                    return false;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((kr1) it.next()).a(i, str)) {
                        return true;
                    }
                }
                return false;
            default:
                v03.j(str, "packageName");
                try {
                    packageInfo = ((Context) obj).getPackageManager().getPackageInfo(str, 4096);
                    break;
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo = null;
                }
                if (packageInfo == null) {
                    Logger.g("Package \"%s\" does not exist.", str);
                    return false;
                }
                int i2 = packageInfo.applicationInfo.uid;
                if (i2 == i) {
                    return c(packageInfo);
                }
                Logger.g("UID mismatch; expected %d for package \"%s\" but caller's actual UID is %d.", Integer.valueOf(i2), str, Integer.valueOf(i));
                return false;
        }
    }

    @Override // p.kr1
    public final boolean b(String str) {
        PackageInfo packageInfo;
        Object obj = this.b;
        switch (this.a) {
            case 0:
                v03.j(str, "packageName");
                List list = (List) obj;
                if ((list instanceof Collection) && list.isEmpty()) {
                    return false;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((kr1) it.next()).b(str)) {
                        return true;
                    }
                }
                return false;
            default:
                v03.j(str, "packageName");
                try {
                    packageInfo = ((Context) obj).getPackageManager().getPackageInfo(str, 4096);
                    break;
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo = null;
                }
                if (packageInfo != null) {
                    return c(packageInfo);
                }
                Logger.g("Package \"%s\" does not exist.", str);
                return false;
        }
    }

    public final boolean c(PackageInfo packageInfo) {
        HashSet hashSet;
        if (packageInfo.applicationInfo.uid == Process.myUid() || packageInfo.applicationInfo.uid == 1000) {
            return true;
        }
        String[] strArr = packageInfo.requestedPermissions;
        int[] iArr = packageInfo.requestedPermissionsFlags;
        Integer numValueOf = strArr != null ? Integer.valueOf(kotlin.collections.b.r0(strArr, "android.permission.MEDIA_CONTENT_CONTROL")) : null;
        if (iArr != null && numValueOf != null && -1 != numValueOf.intValue() && (iArr[numValueOf.intValue()] & 2) != 0) {
            return true;
        }
        String str = packageInfo.packageName;
        v03.i(str, "packageInfo.packageName");
        Context context = (Context) this.b;
        Object obj = mo1.b;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (mo1.b) {
            if (string != null) {
                try {
                    if (!string.equals(mo1.c)) {
                        String[] strArrSplit = string.split(":", -1);
                        HashSet hashSet2 = new HashSet(strArrSplit.length);
                        for (String str2 : strArrSplit) {
                            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str2);
                            if (componentNameUnflattenFromString != null) {
                                hashSet2.add(componentNameUnflattenFromString.getPackageName());
                            }
                        }
                        mo1.d = hashSet2;
                        mo1.c = string;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            hashSet = mo1.d;
        }
        v03.i(hashSet, "getEnabledListenerPackages(context)");
        return hashSet.contains(str);
    }

    public hw(Context context) {
        v03.j(context, "context");
        this.b = context;
    }
}
