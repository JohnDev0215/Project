package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class ReconnectionService extends Service {
    private static final Logger zza = new Logger("ReconnectionService");

    @Nullable
    private zzak zzb;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        zzak zzakVar = this.zzb;
        if (zzakVar != null) {
            try {
                return zzakVar.zzf(intent);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onBind", zzak.class.getSimpleName());
            }
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        zzak zzakVarZzc = com.google.android.gms.internal.cast.zzm.zzc(this, sharedInstance.getSessionManager().zzb(), sharedInstance.zzc().zza());
        this.zzb = zzakVarZzc;
        if (zzakVarZzc != null) {
            try {
                zzakVarZzc.zzg();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onCreate", zzak.class.getSimpleName());
            }
            super.onCreate();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        zzak zzakVar = this.zzb;
        if (zzakVar != null) {
            try {
                zzakVar.zzh();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onDestroy", zzak.class.getSimpleName());
            }
            super.onDestroy();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@NonNull Intent intent, int i, int i2) {
        zzak zzakVar = this.zzb;
        if (zzakVar != null) {
            try {
                return zzakVar.zze(intent, i, i2);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onStartCommand", zzak.class.getSimpleName());
            }
        }
        return 2;
    }
}
