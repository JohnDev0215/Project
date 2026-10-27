package io.objectbox.sync.internal;

import io.objectbox.sync.ConnectivityMonitor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Platform {
    @Nullable
    public ConnectivityMonitor getConnectivityMonitor() {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.objectbox.sync.internal.Platform findPlatform() {
        /*
            java.lang.Object r0 = io.objectbox.BoxStore.getContext()
            if (r0 == 0) goto L3c
            r1 = 0
            java.lang.String r2 = "android.content.Context"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            java.lang.String r3 = "io.objectbox.android.internal.AndroidPlatform"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            java.lang.String r4 = "create"
            r5 = 1
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            r7 = 0
            r6[r7] = r2     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            java.lang.reflect.Method r2 = r3.getMethod(r4, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            r3[r7] = r0     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            java.lang.Object r0 = r2.invoke(r1, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            io.objectbox.sync.internal.Platform r0 = (io.objectbox.sync.internal.Platform) r0     // Catch: java.lang.reflect.InvocationTargetException -> L2a java.lang.IllegalAccessException -> L2d java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L31
            return r0
        L2a:
            r0 = move-exception
        L2b:
            r1 = r0
            goto L31
        L2d:
            r0 = move-exception
            goto L2b
        L2f:
            r0 = move-exception
            goto L2b
        L31:
            if (r1 != 0) goto L34
            goto L3c
        L34:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r2 = "AndroidPlatform could not be created."
            r0.<init>(r2, r1)
            throw r0
        L3c:
            io.objectbox.sync.internal.Platform r0 = new io.objectbox.sync.internal.Platform
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.objectbox.sync.internal.Platform.findPlatform():io.objectbox.sync.internal.Platform");
    }
}
