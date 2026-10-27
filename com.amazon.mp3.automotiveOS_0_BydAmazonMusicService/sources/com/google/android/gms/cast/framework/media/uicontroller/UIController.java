package com.google.android.gms.cast.framework.media.uicontroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class UIController {

    @Nullable
    private RemoteMediaClient zza;

    @Nullable
    protected RemoteMediaClient getRemoteMediaClient() {
        return this.zza;
    }

    public void onMediaStatusUpdated() {
    }

    public void onSendingRemoteMediaRequest() {
    }

    public void onSessionConnected(@NonNull CastSession castSession) {
        this.zza = castSession != null ? castSession.getRemoteMediaClient() : null;
    }

    public void onSessionEnded() {
        this.zza = null;
    }
}
