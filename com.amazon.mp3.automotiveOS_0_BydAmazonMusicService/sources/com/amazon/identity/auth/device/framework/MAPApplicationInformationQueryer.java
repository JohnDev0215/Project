package com.amazon.identity.auth.device.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.identity.auth.device.api.MAPInformationProvider;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.j5;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.xa;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z0;
import com.amazon.identity.auth.device.z5;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPApplicationInformationQueryer {
    public static final String f = "com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer";
    public static final Comparator<j5> g = new a();
    public static MAPApplicationInformationQueryer h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f322a;
    public final z5 b;
    public Map<String, j5> c = new HashMap();
    public Map<String, Integer> d = new HashMap();
    public boolean e = true;

    /* JADX INFO: compiled from: DCP */
    public static class MAPApplicationCacheInvalidator extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AtomicReference<MAPApplicationCacheInvalidator> f323a = new AtomicReference<>();

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            if (data == null) {
                Log.e(ga.a(MAPApplicationInformationQueryer.f), "The system broadcast contains null data. Ignoring the broadcast");
                return;
            }
            String schemeSpecificPart = data.getSchemeSpecificPart();
            if (schemeSpecificPart != null) {
                String action = intent.getAction();
                String str = MAPApplicationInformationQueryer.f;
                String.format("Notified by action %s to invalidate app cache for %s", action, schemeSpecificPart);
                ga.a(str);
                if (TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED")) {
                    MAPApplicationInformationQueryer mAPApplicationInformationQueryerA = MAPApplicationInformationQueryer.a(context);
                    synchronized (mAPApplicationInformationQueryerA) {
                        mAPApplicationInformationQueryerA.e = true;
                    }
                } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                    MAPApplicationInformationQueryer.a(context).c(schemeSpecificPart);
                    ga.a(str);
                    z0.a(context).a();
                } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_CHANGED") || TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED")) {
                    MAPApplicationInformationQueryer.a(context).c(schemeSpecificPart);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class a implements Comparator<j5> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
        @Override // java.util.Comparator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int compare(com.amazon.identity.auth.device.j5 r5, com.amazon.identity.auth.device.j5 r6) {
            /*
                r4 = this;
                com.amazon.identity.auth.device.j5 r5 = (com.amazon.identity.auth.device.j5) r5
                com.amazon.identity.auth.device.j5 r6 = (com.amazon.identity.auth.device.j5) r6
                r0 = 0
                r1 = -1
                if (r5 != 0) goto Lb
                if (r6 == 0) goto L3d
                goto L33
            Lb:
                if (r6 != 0) goto Le
                goto L37
            Le:
                r5.f()     // Catch: com.amazon.identity.auth.device.framework.RemoteMAPException -> L11
            L11:
                r6.f()     // Catch: com.amazon.identity.auth.device.framework.RemoteMAPException -> L14
            L14:
                java.lang.Integer r2 = r5.d
                java.lang.Integer r3 = r6.d
                int r2 = com.amazon.identity.auth.device.e.a(r2, r3)
                if (r2 == 0) goto L20
            L1e:
                r0 = r2
                goto L3d
            L20:
                java.lang.Integer r2 = r5.e
                java.lang.Integer r3 = r6.e
                int r2 = com.amazon.identity.auth.device.e.a(r2, r3)
                if (r2 == 0) goto L2b
                goto L1e
            L2b:
                java.lang.String r5 = r5.b
                java.lang.String r6 = r6.b
                if (r5 != 0) goto L35
                if (r6 == 0) goto L3d
            L33:
                r0 = r1
                goto L3d
            L35:
                if (r6 != 0) goto L39
            L37:
                r0 = 1
                goto L3d
            L39:
                int r0 = r5.compareTo(r6)
            L3d:
                int r0 = r0 * r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer.a.compare(java.lang.Object, java.lang.Object):int");
        }
    }

    public MAPApplicationInformationQueryer(Context context, z5 z5Var) {
        this.f322a = t5.a(context.getApplicationContext());
        this.b = z5Var;
    }

    public static synchronized MAPApplicationInformationQueryer a(Context context) {
        if (h == null || xa.a()) {
            h = new MAPApplicationInformationQueryer(context, new z5(context));
        }
        return h;
    }

    public synchronized String b(String str) {
        j5 j5VarA = a(str);
        if (j5VarA != null) {
            try {
                String strD = j5VarA.d();
                if (!TextUtils.isEmpty(strD)) {
                    return strD;
                }
            } catch (RemoteMAPException unused) {
                String str2 = f;
                Log.e(ga.a(str2), String.format("Unable to get device serial number for the calling package.", new Object[0]));
                return null;
            }
        }
        return null;
    }

    public synchronized List<j5> c() {
        ArrayList arrayList;
        Map<String, j5> mapB = b();
        arrayList = new ArrayList();
        arrayList.addAll(mapB.values());
        Collections.sort(arrayList, g);
        return arrayList;
    }

    public synchronized void d() {
        this.c = new HashMap();
        this.e = true;
        this.d.clear();
    }

    public synchronized void e(String str) {
        if (str == null) {
            return;
        }
        a(str);
        int iIntValue = this.d.containsKey(str) ? 1 + this.d.get(str).intValue() : 1;
        ga.a(f);
        this.d.put(str, Integer.valueOf(iIntValue));
    }

    public final synchronized void f(String str) {
        ProviderInfo[] providerInfoArr;
        try {
            try {
                providerInfoArr = this.b.a(str, 8).providers;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(ga.a(f), String.format("Tried to get MAP info for non-existant package. Error message : %s", e.getMessage()));
                yd.a("MAPPackageNameNotFound", str);
            }
        } catch (SecurityException e2) {
            Log.w(ga.a(f), String.format("Tried to get MAP info for untrusted package. Error message : %s", e2.getMessage()));
            yd.a("MAPPackageIncorrectlySigned", str);
        }
        if (providerInfoArr == null) {
            ga.a(f);
            this.c.remove(str);
            return;
        }
        for (ProviderInfo providerInfo : providerInfoArr) {
            if (z5.a(providerInfo)) {
                String str2 = providerInfo.authority;
                if (str2 != null && str2.startsWith(MAPInformationProvider.TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
                    j5 j5Var = new j5(this.f322a, providerInfo);
                    this.c.put(str, j5Var);
                    a(j5Var);
                    return;
                }
            } else {
                String str3 = f;
                String.format("Content Provider for %s is not enabled", providerInfo.packageName);
                ga.a(str3);
            }
        }
        this.c.remove(str);
    }

    public synchronized void g(String str) {
        j5 j5Var;
        if (str == null) {
            return;
        }
        String str2 = f;
        ga.a(str2);
        if (this.d.containsKey(str)) {
            int iIntValue = this.d.get(str).intValue();
            ga.a(str2);
            int i = iIntValue <= 0 ? 0 : iIntValue - 1;
            this.d.put(str, Integer.valueOf(i));
            ga.a(str2);
            if (i == 0 && (j5Var = this.c.get(str)) != null && j5Var.n) {
                ga.a(str2);
                this.c.remove(str);
                this.e = true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean d(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r1.d     // Catch: java.lang.Throwable -> L1c
            boolean r0 = r0.containsKey(r2)     // Catch: java.lang.Throwable -> L1c
            if (r0 == 0) goto L19
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r1.d     // Catch: java.lang.Throwable -> L1c
            java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Throwable -> L1c
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch: java.lang.Throwable -> L1c
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L1c
            if (r2 <= 0) goto L19
            r2 = 1
            goto L1a
        L19:
            r2 = 0
        L1a:
            monitor-exit(r1)
            return r2
        L1c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer.d(java.lang.String):boolean");
    }

    public synchronized void c(String str) {
        String str2 = f;
        ga.a(str2);
        if (this.c.containsKey(str)) {
            if (d(str)) {
                this.c.get(str).n = true;
                String.format("The package info for %s is locked for usage. Will clean it later.", str);
                ga.a(str2);
            } else {
                ga.a(str2);
                this.c.remove(str);
                this.e = true;
            }
        } else {
            ga.a(str2);
        }
    }

    public synchronized j5 a(String str) {
        if (this.c.get(str) == null && this.e && !d(str)) {
            String str2 = f;
            Log.i(ga.a(str2), "Populate change for remote MAP info.");
            Log.i(ga.a(str2), "CacheContainsPartialResults? " + this.e);
            f(str);
        }
        return this.c.get(str);
    }

    public final synchronized Map<String, j5> b() {
        if (this.c == null || this.e) {
            AtomicReference<MAPApplicationCacheInvalidator> atomicReference = MAPApplicationCacheInvalidator.f323a;
            if (!(atomicReference.get() != null)) {
                String str = f;
                ga.a(str);
                t5 t5Var = this.f322a;
                MAPApplicationCacheInvalidator mAPApplicationCacheInvalidator = new MAPApplicationCacheInvalidator();
                if (!CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, mAPApplicationCacheInvalidator)) {
                    ga.c(str, "%s is already registered", "MAPApplicationCacheInvalidator");
                } else {
                    ga.a(str, "Initializing MAPCache cleaner %s.", "MAPApplicationCacheInvalidator");
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                    intentFilter.addDataScheme("package");
                    try {
                        t5Var.getApplicationContext().registerReceiver(mAPApplicationCacheInvalidator, intentFilter);
                    } catch (Exception e) {
                        Log.w(ga.a(f), "Failed to register receiver", e);
                    }
                }
            }
            HashMap map = new HashMap();
            if (qe.a(this.f322a)) {
                String packageName = this.f322a.getPackageName();
                f(packageName);
                j5 j5Var = this.c.get(packageName);
                if (j5Var != null) {
                    map.put(packageName, j5Var);
                } else {
                    map.put(packageName, new j5(this.f322a));
                }
            } else {
                ArrayList<ProviderInfo> arrayList = new ArrayList();
                for (ProviderInfo providerInfo : (ArrayList) this.b.b()) {
                    String str2 = providerInfo.authority;
                    if (str2 != null && str2.startsWith(MAPInformationProvider.TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
                        arrayList.add(providerInfo);
                    }
                }
                for (ProviderInfo providerInfo2 : arrayList) {
                    if (d(providerInfo2.packageName)) {
                        j5 j5Var2 = this.c.get(providerInfo2.packageName);
                        if (j5Var2 != null) {
                            map.put(providerInfo2.packageName, j5Var2);
                        }
                    } else {
                        map.put(providerInfo2.packageName, new j5(this.f322a, providerInfo2));
                    }
                }
            }
            this.c = map;
            this.e = false;
        }
        return this.c;
    }

    public synchronized Collection<j5> a() {
        return new ArrayList(b().values());
    }

    public final void a(j5 j5Var) {
        String strC;
        try {
            strC = j5Var.c();
        } catch (RemoteMAPException unused) {
            Log.w(ga.a(f), "Couldn't determine override device type/DSN for remoteMAPInfo Package");
            strC = null;
        }
        String str = f;
        String.format("Get map info for %s, device type: %s", j5Var.b, strC);
        ga.a(str);
    }
}
