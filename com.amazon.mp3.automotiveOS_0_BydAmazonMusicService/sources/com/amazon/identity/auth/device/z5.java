package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.framework.TrustedAppUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z5 {
    public static final String f = "com.amazon.identity.auth.device.z5";
    public static volatile l6 g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f701a;
    public final PackageManager b;
    public final boolean c;
    public final Context d;
    public volatile Set<Signature> e;

    public z5(Context context) {
        this(context, false);
    }

    public static ProviderInfo a(Uri uri, int i, PackageManager packageManager) {
        try {
            return packageManager.resolveContentProvider(uri.getAuthority(), i);
        } catch (Exception e) {
            a(e);
            return packageManager.resolveContentProvider(uri.getAuthority(), i);
        }
    }

    public List<ProviderInfo> b() {
        ProviderInfo[] providerInfoArr;
        ArrayList arrayList = new ArrayList();
        Iterator it = ((HashSet) a()).iterator();
        while (it.hasNext()) {
            try {
                PackageInfo packageInfoA = a((String) it.next(), 8, this.b);
                if (packageInfoA != null && (providerInfoArr = packageInfoA.providers) != null) {
                    for (ProviderInfo providerInfo : providerInfoArr) {
                        if (a(providerInfo)) {
                            arrayList.add(providerInfo);
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(ga.a(f), "Caught NameNotFoundException querying for package that existed a moment ago", e);
            }
        }
        return arrayList;
    }

    public z5(Context context, boolean z) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        if (g == null) {
            ga.c(f, "Trying to use default signature based package trust logic. This should be on 3P device");
            a(new m6());
        }
        this.d = context;
        this.f701a = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        this.b = packageManager;
        if (z) {
            this.e = TrustedAppUtils.b(context, packageManager);
            this.c = false;
        } else {
            this.e = TrustedAppUtils.a(context, packageManager);
            this.c = IsolatedModeSwitcher.isAppInStaticIsolatedMode(context);
        }
    }

    public static synchronized void a(l6 l6Var) {
        g = l6Var;
        Log.i(ga.a(f), "Setting package trust logic as: signature based package trust logic");
    }

    public List<ResolveInfo> a(Intent intent, int i) {
        List<ResolveInfo> listQueryIntentServices;
        try {
            listQueryIntentServices = this.b.queryIntentServices(intent, i);
        } catch (Exception e) {
            a(e);
            listQueryIntentServices = this.b.queryIntentServices(intent, i);
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            if (a(resolveInfo.serviceInfo.packageName, false)) {
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    public boolean b(String str) {
        return ((m6) g).a(this.d, str, true);
    }

    public final boolean a(String str, boolean z) {
        return ((m6) g).a(this.d, str, z);
    }

    public PackageInfo a(String str, int i) throws PackageManager.NameNotFoundException, SecurityException {
        if (((m6) g).a(this.d, str, true)) {
            return a(str, i, this.b);
        }
        Log.e(ga.a(f), "Package is not trusted");
        throw new SecurityException("Package is not trusted");
    }

    public boolean a(int i) {
        int iCheckSignatures;
        int iCheckSignatures2;
        String[] packagesForUid;
        boolean z;
        int iMyUid = Process.myUid();
        if (xa.a()) {
            try {
                iCheckSignatures = this.b.checkSignatures(i, iMyUid);
            } catch (Exception e) {
                a(e);
                iCheckSignatures = this.b.checkSignatures(i, iMyUid);
            }
            return iCheckSignatures == 0;
        }
        if (i == iMyUid) {
            return true;
        }
        if (this.c) {
            ga.a(f);
            return false;
        }
        try {
            iCheckSignatures2 = this.b.checkSignatures(i, iMyUid);
        } catch (Exception e2) {
            a(e2);
            iCheckSignatures2 = this.b.checkSignatures(i, iMyUid);
        }
        if (iCheckSignatures2 == 0) {
            return true;
        }
        if (this.e == null) {
            return false;
        }
        try {
            packagesForUid = this.b.getPackagesForUid(i);
        } catch (Exception e3) {
            a(e3);
            packagesForUid = this.b.getPackagesForUid(i);
        }
        if (packagesForUid == null) {
            Log.e(ga.a(f), "Package name not found for the uid");
            return false;
        }
        int length = packagesForUid.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            try {
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(ga.a(f), "Package name not found");
            }
            if (a(packagesForUid[i2])) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            Log.e(ga.a(f), String.format("Other uid and my uid are do not have matching signatures (result: %d). The trusted app check also failed.", Integer.valueOf(iCheckSignatures2)));
        }
        return z;
    }

    public final boolean a(String str) throws PackageManager.NameNotFoundException {
        Signature[] signatureArr = a(str, 64, this.b).signatures;
        Set<Signature> set = this.e;
        if (set == null) {
            return false;
        }
        for (Signature signature : signatureArr) {
            if (set.contains(signature)) {
                return true;
            }
        }
        return false;
    }

    public static PackageInfo a(String str, int i, PackageManager packageManager) throws PackageManager.NameNotFoundException {
        try {
            return packageManager.getPackageInfo(str, i);
        } catch (PackageManager.NameNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            a(e2);
            return packageManager.getPackageInfo(str, i);
        }
    }

    public static boolean a(ProviderInfo providerInfo) {
        ApplicationInfo applicationInfo;
        return providerInfo != null && providerInfo.enabled && (applicationInfo = providerInfo.applicationInfo) != null && applicationInfo.enabled;
    }

    public Set<String> a() {
        List<PackageInfo> installedPackages;
        try {
            installedPackages = this.b.getInstalledPackages(0);
        } catch (Exception e) {
            a(e);
            installedPackages = this.b.getInstalledPackages(0);
        }
        HashSet hashSet = new HashSet();
        for (PackageInfo packageInfo : installedPackages) {
            if (a(packageInfo.packageName, false)) {
                hashSet.add(packageInfo.packageName);
            }
        }
        return hashSet;
    }

    public static void a(Exception exc) {
        Log.w(ga.a(f), String.format("PackageManager call failed; retrying. Error Message : %s", exc.getMessage()));
        yd.a("PackageManagerError", new String[0]);
    }
}
