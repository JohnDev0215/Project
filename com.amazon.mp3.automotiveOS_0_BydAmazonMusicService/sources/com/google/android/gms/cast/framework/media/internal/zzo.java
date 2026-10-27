package com.google.android.gms.cast.framework.media.internal;

import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.google.android.gms.cast.MediaSeekOptions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzo extends MediaSessionCompat.Callback {
    final /* synthetic */ zzp zza;

    zzo(zzp zzpVar) {
        this.zza = zzpVar;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        this.zza.zzj.togglePlayback();
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPause() {
        this.zza.zzj.togglePlayback();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPlay() {
        this.zza.zzj.togglePlayback();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSeekTo(long j) {
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(j);
        this.zza.zzj.seek(builder.build());
    }
}
