package com.google.android.gms.internal.cast;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbq extends UIController implements RemoteMediaClient.ProgressListener {
    private final SeekBar zza;
    private final long zzb;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzc;
    private boolean zzd = true;
    private Boolean zze;

    @Nullable
    private Drawable zzf;

    public zzbq(SeekBar seekBar, long j, com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar) {
        this.zzf = null;
        this.zza = seekBar;
        this.zzb = j;
        this.zzc = zzaVar;
        seekBar.setEnabled(false);
        this.zzf = com.google.android.gms.cast.framework.media.widget.zzr.zzd(seekBar);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzb);
        }
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        zzb();
    }

    public final void zza(boolean z) {
        this.zzd = z;
    }

    @VisibleForTesting
    final void zzb() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zza.setMax(this.zzc.zzb());
            this.zza.setProgress(this.zzc.zza());
            this.zza.setEnabled(false);
            return;
        }
        if (this.zzd) {
            this.zza.setMax(this.zzc.zzb());
            if (remoteMediaClient.isLiveStream() && this.zzc.zzm()) {
                this.zza.setProgress(this.zzc.zzc());
            } else {
                this.zza.setProgress(this.zzc.zza());
            }
            if (remoteMediaClient.isPlayingAd()) {
                this.zza.setEnabled(false);
            } else {
                this.zza.setEnabled(true);
            }
            RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
            if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession()) {
                return;
            }
            Boolean bool = this.zze;
            if (bool == null || bool.booleanValue() != remoteMediaClient2.zzq()) {
                Boolean boolValueOf = Boolean.valueOf(remoteMediaClient2.zzq());
                this.zze = boolValueOf;
                if (!boolValueOf.booleanValue()) {
                    this.zza.setThumb(new ColorDrawable(0));
                    this.zza.setClickable(false);
                    this.zza.setOnTouchListener(new zzbp(this));
                } else {
                    Drawable drawable = this.zzf;
                    if (drawable != null) {
                        this.zza.setThumb(drawable);
                    }
                    this.zza.setClickable(true);
                    this.zza.setOnTouchListener(null);
                }
            }
        }
    }
}
