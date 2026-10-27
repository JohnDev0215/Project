package com.google.android.gms.internal.cast;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbl extends UIController {
    private final ImageView zza;
    private final String zzb;
    private final String zzc;
    private final Context zzd;

    @Nullable
    private Cast.Listener zze;

    public zzbl(ImageView imageView, Context context) {
        this.zza = imageView;
        Context applicationContext = context.getApplicationContext();
        this.zzd = applicationContext;
        this.zzb = applicationContext.getString(R.string.cast_mute);
        this.zzc = applicationContext.getString(R.string.cast_unmute);
        imageView.setEnabled(false);
        this.zze = null;
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
        if (this.zze == null) {
            this.zze = new zzbk(this);
        }
        castSession.addCastListener(this.zze);
        super.onSessionConnected(castSession);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        Cast.Listener listener;
        this.zza.setEnabled(false);
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzd).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && (listener = this.zze) != null) {
            currentCastSession.removeCastListener(listener);
        }
        super.onSessionEnded();
    }

    protected final void zza() {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzd).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            this.zza.setEnabled(false);
            return;
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zza.setEnabled(false);
        } else {
            this.zza.setEnabled(true);
        }
        boolean zIsMute = currentCastSession.isMute();
        this.zza.setSelected(zIsMute);
        this.zza.setContentDescription(zIsMute ? this.zzc : this.zzb);
    }
}
