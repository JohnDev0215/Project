package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbb implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzbc zza;

    zzbb(zzbc zzbcVar) {
        this.zza = zzbcVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(@Nullable Bitmap bitmap) {
        if (bitmap != null) {
            this.zza.zza.setImageBitmap(bitmap);
        }
    }
}
