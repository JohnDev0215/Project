package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaj implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzaj(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        if (task.isSuccessful()) {
            this.zza.zzv("remote display stopped");
        } else {
            this.zza.zzv("Unable to stop the remote display, result unsuccessful");
            CastRemoteDisplayLocalService.Callbacks callbacks = (CastRemoteDisplayLocalService.Callbacks) this.zza.zzg.get();
            if (callbacks != null) {
                callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        }
        this.zza.zzn = null;
    }
}
