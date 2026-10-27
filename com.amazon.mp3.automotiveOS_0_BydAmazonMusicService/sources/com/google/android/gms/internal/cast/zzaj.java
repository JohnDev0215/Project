package com.google.android.gms.internal.cast;

import android.content.Context;
import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzaj {
    public MediaRouter zza;
    private final Context zzb;

    zzaj(Context context) {
        this.zzb = context;
    }

    public final MediaRouter zza() {
        if (this.zza == null) {
            this.zza = MediaRouter.getInstance(this.zzb);
        }
        return this.zza;
    }

    public final void zzb(MediaRouter.Callback callback) {
        MediaRouter mediaRouterZza = zza();
        if (mediaRouterZza != null) {
            mediaRouterZza.removeCallback(callback);
        }
    }
}
