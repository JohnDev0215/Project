package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import defpackage.tt1;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: TrustedWebActivityServiceConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class eg3 {
    public final Context a;
    public final HashMap b = new HashMap();

    /* JADX INFO: compiled from: TrustedWebActivityServiceConnectionPool.java */
    public static class a extends AsyncTask<Void, Void, Exception> {
        public final Context a;
        public final Intent b;
        public final hx c;

        public a(Context context, Intent intent, hx hxVar) {
            this.a = context.getApplicationContext();
            this.b = intent;
            this.c = hxVar;
        }

        @Override // android.os.AsyncTask
        public final Exception doInBackground(Void[] voidArr) {
            hx hxVar = this.c;
            Context context = this.a;
            try {
                if (context.bindService(this.b, hxVar, 4097)) {
                    return null;
                }
                context.unbindService(hxVar);
                return new IllegalStateException("Could not bind to the service");
            } catch (SecurityException e) {
                Log.w("TWAConnectionPool", "SecurityException while binding.", e);
                return e;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onPostExecute(java.lang.Exception r10) {
            /*
                r9 = this;
                java.lang.Exception r10 = (java.lang.Exception) r10
                if (r10 == 0) goto L53
                hx r9 = r9.c
                java.util.ArrayList r0 = r9.i
                java.util.Iterator r1 = r0.iterator()
            Lc:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L46
                java.lang.Object r2 = r1.next()
                ml r2 = (defpackage.ml) r2
                r3 = 1
                r2.d = r3
                ol<T> r4 = r2.b
                r5 = 0
                r6 = 0
                if (r4 == 0) goto L3c
                ol$a r4 = r4.f
                r4.getClass()
                s$c r7 = new s$c
                r7.<init>(r10)
                s$a r8 = defpackage.AbstractC0208s.i
                boolean r7 = r8.b(r4, r5, r7)
                if (r7 == 0) goto L38
                defpackage.AbstractC0208s.c(r4)
                r4 = r3
                goto L39
            L38:
                r4 = r6
            L39:
                if (r4 == 0) goto L3c
                goto L3d
            L3c:
                r3 = r6
            L3d:
                if (r3 == 0) goto Lc
                r2.a = r5
                r2.b = r5
                r2.c = r5
                goto Lc
            L46:
                r0.clear()
                java.lang.Runnable r0 = r9.e
                r0.run()
                r0 = 3
                r9.g = r0
                r9.j = r10
            L53:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: eg3.a.onPostExecute(java.lang.Object):void");
        }
    }

    public eg3(Context context) {
        this.a = context.getApplicationContext();
    }

    public static Intent a(Context context, Uri uri, HashSet hashSet, boolean z) {
        boolean zA;
        if (hashSet.size() == 0) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        String str = null;
        while (it.hasNext()) {
            String str2 = it.next().activityInfo.packageName;
            Iterator it2 = hashSet.iterator();
            while (true) {
                if (it2.hasNext()) {
                    try {
                        zA = new tt1.a().a(str2, context.getPackageManager(), ((jb3) it2.next()).a);
                    } catch (PackageManager.NameNotFoundException | IOException e) {
                        Log.e("PackageIdentity", "Could not check if package matches token.", e);
                        zA = false;
                    }
                    if (zA) {
                        str = str2;
                        break;
                    }
                }
            }
        }
        if (str == null) {
            if (z) {
                Log.w("TWAConnectionPool", "No TWA candidates for " + uri + " have been registered.");
            }
            return null;
        }
        Intent intent2 = new Intent();
        intent2.setPackage(str);
        intent2.setAction("android.support.customtabs.trusted.TRUSTED_WEB_ACTIVITY_SERVICE");
        ResolveInfo resolveInfoResolveService = context.getPackageManager().resolveService(intent2, 131072);
        if (resolveInfoResolveService == null) {
            if (z) {
                Log.w("TWAConnectionPool", "Could not find TWAService for ".concat(str));
            }
            return null;
        }
        if (z) {
            Log.i("TWAConnectionPool", "Found " + resolveInfoResolveService.serviceInfo.name + " to handle request for " + uri);
        }
        Intent intent3 = new Intent();
        intent3.setComponent(new ComponentName(str, resolveInfoResolveService.serviceInfo.name));
        return intent3;
    }
}
