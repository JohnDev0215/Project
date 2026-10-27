package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzo implements RemoteMediaClient.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    /* synthetic */ zzo(ExpandedControllerActivity expandedControllerActivity, zzn zznVar) {
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        this.zza.zzr();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        this.zza.zzq();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        expandedControllerActivity.zzu.setText(expandedControllerActivity.getResources().getString(R.string.cast_expanded_controller_loading));
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        RemoteMediaClient remoteMediaClientZzl = this.zza.zzl();
        if (remoteMediaClientZzl != null && remoteMediaClientZzl.hasMediaSession()) {
            this.zza.zzL = false;
            this.zza.zzp();
            this.zza.zzr();
        } else {
            ExpandedControllerActivity expandedControllerActivity = this.zza;
            if (expandedControllerActivity.zzL) {
                return;
            }
            expandedControllerActivity.finish();
        }
    }
}
