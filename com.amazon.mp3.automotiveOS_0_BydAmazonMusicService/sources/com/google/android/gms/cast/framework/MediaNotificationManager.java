package com.google.android.gms.cast.framework;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class MediaNotificationManager {
    private final SessionManager zza;

    public MediaNotificationManager(@NonNull SessionManager sessionManager) {
        this.zza = sessionManager;
    }

    public void updateNotification() {
        CastSession currentCastSession = this.zza.getCurrentCastSession();
        if (currentCastSession != null) {
            currentCastSession.zzd().zzg(true);
        }
    }
}
