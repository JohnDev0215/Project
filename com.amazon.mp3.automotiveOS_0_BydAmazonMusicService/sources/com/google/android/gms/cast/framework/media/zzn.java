package com.google.android.gms.cast.framework.media;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.images.WebImage;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzn {

    @Nullable
    public final Uri zza;
    public Bitmap zzb;

    public zzn(@Nullable WebImage webImage) {
        this.zza = webImage == null ? null : webImage.getUrl();
    }
}
