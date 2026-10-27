package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzaj extends com.google.android.gms.internal.cast.zzb implements zzak {
    public static zzak zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.IReconnectionService");
        return iInterfaceQueryLocalInterface instanceof zzak ? (zzak) iInterfaceQueryLocalInterface : new zzai(iBinder);
    }
}
