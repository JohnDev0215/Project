package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbr extends UIController {
    private final View zza;
    private final int zzb;

    public zzbr(View view, int i) {
        this.zza = view;
        this.zzb = i;
        view.setEnabled(false);
    }

    private final void zza() {
        Integer indexById;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            MediaStatus mediaStatus = (MediaStatus) Preconditions.checkNotNull(remoteMediaClient.getMediaStatus());
            if ((mediaStatus.isMediaCommandSupported(64L) || mediaStatus.getQueueRepeatMode() != 0 || ((indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId())) != null && indexById.intValue() < mediaStatus.getQueueItemCount() - 1)) && !remoteMediaClient.isPlayingAd()) {
                this.zza.setVisibility(0);
                this.zza.setEnabled(true);
                return;
            }
        }
        this.zza.setVisibility(this.zzb);
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        super.onSessionEnded();
    }
}
