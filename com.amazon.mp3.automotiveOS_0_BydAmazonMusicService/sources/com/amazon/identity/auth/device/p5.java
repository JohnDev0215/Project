package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class p5 {
    public static final b b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ComponentName f489a;

    /* JADX INFO: compiled from: DCP */
    public static class a implements b {
        /* JADX WARN: Removed duplicated region for block: B:14:0x002b A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(com.amazon.identity.auth.device.z5 r4, android.content.ComponentName r5) {
            /*
                r3 = this;
                r0 = 0
                r1 = 128(0x80, float:1.8E-43)
                android.content.pm.PackageManager r2 = r4.b     // Catch: java.lang.Exception -> La android.content.pm.PackageManager.NameNotFoundException -> L2d
                android.content.pm.ServiceInfo r5 = r2.getServiceInfo(r5, r1)     // Catch: java.lang.Exception -> La android.content.pm.PackageManager.NameNotFoundException -> L2d
                goto L14
            La:
                r2 = move-exception
                com.amazon.identity.auth.device.z5.a(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                android.content.pm.PackageManager r2 = r4.b     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                android.content.pm.ServiceInfo r5 = r2.getServiceInfo(r5, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
            L14:
                if (r5 != 0) goto L17
                goto L28
            L17:
                java.lang.String r1 = r5.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                boolean r4 = r4.b(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                if (r4 != 0) goto L29
                java.lang.String r4 = com.amazon.identity.auth.device.z5.f     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
                java.lang.String r1 = "Cannot get ServiceInfo from package since it is not signed with the Amazon Cert."
                com.amazon.identity.auth.device.ga.b(r4, r1, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
            L28:
                r5 = 0
            L29:
                if (r5 == 0) goto L2c
                r0 = 1
            L2c:
                return r0
            L2d:
                r4 = move-exception
                throw r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2f
            L2f:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.p5.a.a(com.amazon.identity.auth.device.z5, android.content.ComponentName):boolean");
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
    }

    public p5(ComponentName componentName) {
        this.f489a = componentName;
    }

    public static ComponentName a(Context context, String str, b bVar) {
        z5 z5Var = new z5(context, false);
        ComponentName[] componentNameArr = {new ComponentName("com.amazon.imp", str), new ComponentName("com.amazon.sso", str), new ComponentName("com.amazon.dcp", str), new ComponentName("com.amazon.fv", str), new ComponentName("com.amazon.canary", str), new ComponentName(context, str)};
        for (int i = 0; i < 6; i++) {
            ComponentName componentName = componentNameArr[i];
            if (((a) bVar).a(z5Var, componentName)) {
                return componentName;
            }
        }
        Iterator it = ((HashSet) z5Var.a()).iterator();
        while (it.hasNext()) {
            ComponentName componentName2 = new ComponentName((String) it.next(), str);
            if (((a) bVar).a(z5Var, componentName2)) {
                return componentName2;
            }
        }
        return null;
    }
}
