package k3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import h2.AbstractC1343A;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class E1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f17757b;
    public static final String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D1 f17758a;

    static {
        e2.G.a("media3.session");
        int i7 = AbstractC1343A.f15195a;
        f17757b = Integer.toString(0, 36);
        c = Integer.toString(1, 36);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public E1(android.content.Context r13, android.content.ComponentName r14) {
        /*
            r12 = this;
            r12.<init>()
            java.lang.String r0 = "context must not be null"
            h2.AbstractC1346c.h(r13, r0)
            android.content.pm.PackageManager r13 = r13.getPackageManager()
            java.lang.String r0 = r14.getPackageName()
            r1 = 0
            android.content.pm.ApplicationInfo r0 = r13.getApplicationInfo(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L19
            int r0 = r0.uid     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L19
        L17:
            r2 = r0
            goto L1b
        L19:
            r0 = -1
            goto L17
        L1b:
            java.lang.String r0 = "androidx.media3.session.MediaLibraryService"
            boolean r0 = a(r13, r0, r14)
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == 0) goto L28
            r13 = 2
        L26:
            r3 = r13
            goto L3b
        L28:
            java.lang.String r0 = "androidx.media3.session.MediaSessionService"
            boolean r0 = a(r13, r0, r14)
            if (r0 == 0) goto L32
            r13 = 1
            goto L26
        L32:
            java.lang.String r0 = "android.media.browse.MediaBrowserService"
            boolean r13 = a(r13, r0, r14)
            if (r13 == 0) goto L5d
            r3 = r1
        L3b:
            if (r3 == r1) goto L55
            k3.F1 r13 = new k3.F1
            java.lang.String r6 = r14.getPackageName()
            java.lang.String r7 = r14.getClassName()
            android.os.Bundle r10 = android.os.Bundle.EMPTY
            r4 = 0
            r11 = 0
            r5 = 0
            r9 = 0
            r1 = r13
            r8 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r12.f17758a = r13
            goto L5c
        L55:
            k3.G1 r13 = new k3.G1
            r13.<init>(r14, r2)
            r12.f17758a = r13
        L5c:
            return
        L5d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "Failed to resolve SessionToken for "
            r13.<init>(r0)
            r13.append(r14)
            java.lang.String r14 = ". Manifest doesn't declare one of either MediaSessionService, MediaLibraryService, MediaBrowserService or MediaBrowserServiceCompat. Use service's full name."
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.E1.<init>(android.content.Context, android.content.ComponentName):void");
    }

    public static boolean a(PackageManager packageManager, String str, ComponentName componentName) {
        ServiceInfo serviceInfo;
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (listQueryIntentServices != null) {
            for (int i7 = 0; i7 < listQueryIntentServices.size(); i7++) {
                ResolveInfo resolveInfo = listQueryIntentServices.get(i7);
                if (resolveInfo != null && (serviceInfo = resolveInfo.serviceInfo) != null && TextUtils.equals(serviceInfo.name, componentName.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        D1 d12 = this.f17758a;
        boolean z6 = d12 instanceof F1;
        String str = f17757b;
        if (z6) {
            bundle.putInt(str, 0);
        } else {
            bundle.putInt(str, 1);
        }
        bundle.putBundle(c, d12.s());
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof E1) {
            return this.f17758a.equals(((E1) obj).f17758a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f17758a.hashCode();
    }

    public final String toString() {
        return this.f17758a.toString();
    }

    public E1(int i7, int i10, int i11, String str, InterfaceC1655m interfaceC1655m, Bundle bundle, MediaSession.Token token) {
        str.getClass();
        IBinder iBinderAsBinder = interfaceC1655m.asBinder();
        bundle.getClass();
        this.f17758a = new F1(i7, 0, i10, i11, str, "", null, iBinderAsBinder, bundle, token);
    }
}
