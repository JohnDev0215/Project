package com.google.android.gms.internal.cast;

import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzba extends UIController implements RemoteMediaClient.ProgressListener {
    private final View zza;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzb;

    public zzba(View view, com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar) {
        this.zza = view;
        this.zzb = zzaVar;
        view.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, 1000L);
        }
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        this.zza.setEnabled(false);
        super.onSessionEnded();
        zza();
    }

    @VisibleForTesting
    final void zza() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        boolean z = false;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isPlayingAd()) {
            this.zza.setEnabled(false);
            return;
        }
        if (!remoteMediaClient.isLiveStream()) {
            this.zza.setEnabled(true);
            return;
        }
        View view = this.zza;
        if (remoteMediaClient.zzq() && !this.zzb.zzm()) {
            z = true;
        }
        view.setEnabled(z);
    }
}
