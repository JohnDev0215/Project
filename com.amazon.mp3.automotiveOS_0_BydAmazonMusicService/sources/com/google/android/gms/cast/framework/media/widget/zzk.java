package com.google.android.gms.cast.framework.media.widget;

import android.os.Looper;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.internal.cast.zzcv;
import java.util.TimerTask;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzk extends TimerTask {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ ExpandedControllerActivity zzb;

    zzk(ExpandedControllerActivity expandedControllerActivity, RemoteMediaClient remoteMediaClient) {
        this.zzb = expandedControllerActivity;
        this.zza = remoteMediaClient;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        new zzcv(Looper.getMainLooper()).post(new zzj(this));
    }
}
