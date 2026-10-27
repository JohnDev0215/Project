package com.google.android.gms.internal.cast;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbt extends UIController {
    private final TextView zza;

    public zzbt(@NonNull TextView textView) {
        this.zza = textView;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        String strZza;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null || (strZza = com.google.android.gms.cast.framework.media.internal.zzq.zza(metadata)) == null) {
            return;
        }
        this.zza.setText(strZza);
    }
}
