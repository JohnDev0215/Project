package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
public final class zzt {
    private static final Logger zza = new Logger("DiscoveryManager");
    private final zzah zzb;

    zzt(zzah zzahVar) {
        this.zzb = zzahVar;
    }

    @Nullable
    public final IObjectWrapper zza() {
        try {
            return this.zzb.zze();
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedThis", zzah.class.getSimpleName());
            return null;
        }
    }
}
