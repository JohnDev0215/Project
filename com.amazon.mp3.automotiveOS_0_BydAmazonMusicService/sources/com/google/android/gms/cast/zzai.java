package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzai implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzai(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        if (!task.isSuccessful()) {
            CastRemoteDisplayLocalService.zza.e("Connection was not successful", new Object[0]);
            CastRemoteDisplayLocalService.zzo(this.zza);
            return;
        }
        CastRemoteDisplayLocalService.zza.d("startRemoteDisplay successful", new Object[0]);
        synchronized (CastRemoteDisplayLocalService.zzc) {
            if (CastRemoteDisplayLocalService.zze == null) {
                CastRemoteDisplayLocalService.zza.d("Remote Display started but session already cancelled", new Object[0]);
                CastRemoteDisplayLocalService.zzo(this.zza);
                return;
            }
            CastRemoteDisplayLocalService.zzl(this.zza, (Display) task.getResult());
            CastRemoteDisplayLocalService.zzd.set(false);
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
            Context context = castRemoteDisplayLocalService.zzo;
            ServiceConnection serviceConnection = castRemoteDisplayLocalService.zzp;
            if (context != null && serviceConnection != null) {
                try {
                    ConnectionTracker.getInstance().unbindService(context, serviceConnection);
                } catch (IllegalArgumentException unused) {
                    CastRemoteDisplayLocalService.zza.d("No need to unbind service, already unbound", new Object[0]);
                }
            }
            this.zza.zzp = null;
            this.zza.zzo = null;
        }
    }
}
