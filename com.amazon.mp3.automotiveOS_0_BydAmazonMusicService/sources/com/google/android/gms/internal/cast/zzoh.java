package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final /* synthetic */ class zzoh {
    public static /* synthetic */ boolean zza(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!zzoh$$ExternalSyntheticBackportWithForwarding0.m(unsafe, obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
