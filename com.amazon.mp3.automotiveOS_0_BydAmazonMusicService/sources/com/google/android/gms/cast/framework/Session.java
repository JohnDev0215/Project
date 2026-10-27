package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class Session {
    private static final Logger zza = new Logger("Session");

    @Nullable
    private final zzan zzb;
    private final zzaz zzc;

    protected Session(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        zzaz zzazVar = new zzaz(this, null);
        this.zzc = zzazVar;
        this.zzb = com.google.android.gms.internal.cast.zzm.zzd(context, str, str2, zzazVar);
    }

    protected abstract void end(boolean z);

    @Nullable
    public final String getCategory() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzh();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "getCategory", zzan.class.getSimpleName());
            }
        }
        return null;
    }

    @Nullable
    public final String getSessionId() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzi();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "getSessionId", zzan.class.getSimpleName());
            }
        }
        return null;
    }

    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return 0L;
    }

    public boolean isConnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzp();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isConnected", zzan.class.getSimpleName());
            }
        }
        return false;
    }

    public boolean isConnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzq();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isConnecting", zzan.class.getSimpleName());
            }
        }
        return false;
    }

    public boolean isDisconnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzr();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isDisconnected", zzan.class.getSimpleName());
            }
        }
        return true;
    }

    public boolean isDisconnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzs();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isDisconnecting", zzan.class.getSimpleName());
            }
        }
        return false;
    }

    public boolean isResuming() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzt();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isResuming", zzan.class.getSimpleName());
            }
        }
        return false;
    }

    public boolean isSuspended() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzu();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "isSuspended", zzan.class.getSimpleName());
            }
        }
        return false;
    }

    protected final void notifyFailedToResumeSession(int i) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzj(i);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifyFailedToResumeSession", zzan.class.getSimpleName());
            }
        }
    }

    protected final void notifyFailedToStartSession(int i) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzk(i);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifyFailedToStartSession", zzan.class.getSimpleName());
            }
        }
    }

    protected final void notifySessionEnded(int i) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzl(i);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifySessionEnded", zzan.class.getSimpleName());
            }
        }
    }

    protected final void notifySessionResumed(boolean z) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzm(z);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifySessionResumed", zzan.class.getSimpleName());
            }
        }
    }

    protected final void notifySessionStarted(@NonNull String str) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzn(str);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifySessionStarted", zzan.class.getSimpleName());
            }
        }
    }

    protected final void notifySessionSuspended(int i) {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                zzanVar.zzo(i);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "notifySessionSuspended", zzan.class.getSimpleName());
            }
        }
    }

    protected void onResuming(@NonNull Bundle bundle) {
    }

    protected void onStarting(@Nullable Bundle bundle) {
    }

    protected abstract void resume(@NonNull Bundle bundle);

    protected abstract void start(@NonNull Bundle bundle);

    protected void zzm(@NonNull Bundle bundle) {
    }

    public final int zzn() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                if (zzanVar.zze() >= 211100000) {
                    return this.zzb.zzf();
                }
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "getSessionStartType", zzan.class.getSimpleName());
            }
        }
        return 0;
    }

    @Nullable
    public final IObjectWrapper zzo() {
        zzan zzanVar = this.zzb;
        if (zzanVar != null) {
            try {
                return zzanVar.zzg();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "getWrappedObject", zzan.class.getSimpleName());
            }
        }
        return null;
    }
}
