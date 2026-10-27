package com.google.android.gms.cast.framework.media.internal;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzh extends com.google.android.gms.internal.cast.zzb implements zzi {
    public static zzi zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
        return iInterfaceQueryLocalInterface instanceof zzi ? (zzi) iInterfaceQueryLocalInterface : new zzg(iBinder);
    }
}
