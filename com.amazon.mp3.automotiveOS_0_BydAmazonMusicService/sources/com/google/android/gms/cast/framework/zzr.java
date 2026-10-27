package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzr extends com.google.android.gms.cast.zzq {
    final /* synthetic */ CastSession zza;

    /* synthetic */ zzr(CastSession castSession, zzq zzqVar) {
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zza() {
        CastSession castSession = this.zza;
        if (castSession.zze != null) {
            try {
                if (castSession.zzi != null) {
                    castSession.zzi.zzn();
                }
                this.zza.zze.zzh(null);
            } catch (RemoteException e) {
                CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnected", zzad.class.getSimpleName());
            }
            CastSession.zzj(this.zza);
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzb(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze != null) {
            try {
                castSession.zze.zzi(new ConnectionResult(i));
            } catch (RemoteException e) {
                CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnectionFailed", zzad.class.getSimpleName());
            }
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzc(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze != null) {
            try {
                castSession.zze.zzj(i);
            } catch (RemoteException e) {
                CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnectionSuspended", zzad.class.getSimpleName());
            }
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzd(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze != null) {
            try {
                castSession.zze.zzi(new ConnectionResult(i));
            } catch (RemoteException e) {
                CastSession.zzb.d(e, "Unable to call %s on %s.", "onDisconnected", zzad.class.getSimpleName());
            }
        }
    }
}
