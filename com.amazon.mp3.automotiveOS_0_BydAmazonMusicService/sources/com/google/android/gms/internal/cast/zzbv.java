package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbv extends zzbw implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final long zzb;
    private final String zzc;
    private boolean zzd = true;

    public zzbv(TextView textView, long j, String str) {
        this.zza = textView;
        this.zzb = j;
        this.zzc = str;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        if (this.zzd) {
            TextView textView = this.zza;
            if (j == -1000) {
                j = j2;
            }
            textView.setText(DateUtils.formatElapsedTime(j / 1000));
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzb);
            if (remoteMediaClient.hasMediaSession()) {
                this.zza.setText(DateUtils.formatElapsedTime(remoteMediaClient.getApproximateStreamPosition() / 1000));
            } else {
                this.zza.setText(this.zzc);
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setText(this.zzc);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.internal.cast.zzbw
    public final void zza(boolean z) {
        this.zzd = z;
    }

    @Override // com.google.android.gms.internal.cast.zzbw
    public final void zzb(long j) {
        this.zza.setText(DateUtils.formatElapsedTime(j / 1000));
    }
}
