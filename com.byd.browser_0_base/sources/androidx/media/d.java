package androidx.media;

import android.os.IBinder;
import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ IBinder g;
    public final /* synthetic */ MediaBrowserServiceCompat.h h;

    public d(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str, IBinder iBinder) {
        this.h = hVar;
        this.e = jVar;
        this.f = str;
        this.g = iBinder;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r8 = this;
            androidx.media.MediaBrowserServiceCompat$i r0 = r8.e
            androidx.media.MediaBrowserServiceCompat$j r0 = (androidx.media.MediaBrowserServiceCompat.j) r0
            android.os.IBinder r0 = r0.a()
            androidx.media.MediaBrowserServiceCompat$h r1 = r8.h
            androidx.media.MediaBrowserServiceCompat r2 = androidx.media.MediaBrowserServiceCompat.this
            m8<android.os.IBinder, androidx.media.MediaBrowserServiceCompat$b> r2 = r2.h
            java.lang.Object r0 = r2.get(r0)
            androidx.media.MediaBrowserServiceCompat$b r0 = (androidx.media.MediaBrowserServiceCompat.b) r0
            java.lang.String r2 = "MBServiceCompat"
            java.lang.String r3 = r8.f
            if (r0 != 0) goto L2c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "removeSubscription for callback that isn't registered id="
            r8.<init>(r0)
            r8.append(r3)
            java.lang.String r8 = r8.toString()
            android.util.Log.w(r2, r8)
            return
        L2c:
            androidx.media.MediaBrowserServiceCompat r1 = androidx.media.MediaBrowserServiceCompat.this
            r1.getClass()
            java.util.HashMap<java.lang.String, java.util.List<qv1<android.os.IBinder, android.os.Bundle>>> r0 = r0.e
            android.os.IBinder r8 = r8.g
            r1 = 1
            r4 = 0
            if (r8 != 0) goto L42
            java.lang.Object r8 = r0.remove(r3)     // Catch: java.lang.Throwable -> L86
            if (r8 == 0) goto L40
            goto L6d
        L40:
            r1 = r4
            goto L6d
        L42:
            java.lang.Object r5 = r0.get(r3)     // Catch: java.lang.Throwable -> L86
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.Throwable -> L86
            if (r5 == 0) goto L40
            java.util.Iterator r6 = r5.iterator()     // Catch: java.lang.Throwable -> L86
        L4e:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L63
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> L86
            qv1 r7 = (defpackage.qv1) r7     // Catch: java.lang.Throwable -> L86
            F r7 = r7.a     // Catch: java.lang.Throwable -> L86
            if (r8 != r7) goto L4e
            r6.remove()     // Catch: java.lang.Throwable -> L86
            r4 = r1
            goto L4e
        L63:
            int r8 = r5.size()     // Catch: java.lang.Throwable -> L86
            if (r8 != 0) goto L40
            r0.remove(r3)     // Catch: java.lang.Throwable -> L86
            goto L40
        L6d:
            if (r1 != 0) goto L85
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "removeSubscription called for "
            r8.<init>(r0)
            r8.append(r3)
            java.lang.String r0 = " which is not subscribed"
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            android.util.Log.w(r2, r8)
        L85:
            return
        L86:
            r8 = move-exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media.d.run():void");
    }
}
