package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.images.WebImage;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbc extends UIController {
    private final ImageView zza;
    private final ImageHints zzb;
    private final Bitmap zzc;

    @Nullable
    private final ImagePicker zzd;
    private final com.google.android.gms.cast.framework.media.internal.zzb zze;

    public zzbc(ImageView imageView, Context context, @NonNull ImageHints imageHints, int i) {
        this.zza = imageView;
        this.zzb = imageHints;
        this.zzc = BitmapFactory.decodeResource(context.getResources(), i);
        CastContext castContextZza = CastContext.zza(context);
        if (castContextZza != null) {
            CastMediaOptions castMediaOptions = castContextZza.getCastOptions().getCastMediaOptions();
            this.zzd = castMediaOptions != null ? castMediaOptions.getImagePicker() : null;
        } else {
            this.zzd = null;
        }
        this.zze = new com.google.android.gms.cast.framework.media.internal.zzb(context.getApplicationContext());
    }

    private final void zzb() {
        MediaInfo media;
        WebImage webImageOnPickImage;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zza.setImageBitmap(this.zzc);
            return;
        }
        MediaQueueItem preloadedItem = remoteMediaClient.getPreloadedItem();
        Uri imageUri = null;
        if (preloadedItem != null && (media = preloadedItem.getMedia()) != null) {
            MediaMetadata metadata = media.getMetadata();
            ImagePicker imagePicker = this.zzd;
            imageUri = (imagePicker == null || metadata == null || (webImageOnPickImage = imagePicker.onPickImage(metadata, this.zzb)) == null || webImageOnPickImage.getUrl() == null) ? MediaUtils.getImageUri(media, 0) : webImageOnPickImage.getUrl();
        }
        if (imageUri == null) {
            this.zza.setImageBitmap(this.zzc);
        } else {
            this.zze.zzd(imageUri);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zze.zzc(new zzbb(this));
        this.zza.setImageBitmap(this.zzc);
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zze.zza();
        this.zza.setImageBitmap(this.zzc);
        super.onSessionEnded();
    }
}
