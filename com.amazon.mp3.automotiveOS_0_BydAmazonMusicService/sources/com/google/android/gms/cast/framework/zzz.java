package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzz extends com.google.android.gms.internal.cast.zzb implements zzaa {
    public static zzaa zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext");
        return iInterfaceQueryLocalInterface instanceof zzaa ? (zzaa) iInterfaceQueryLocalInterface : new zzy(iBinder);
    }
}
