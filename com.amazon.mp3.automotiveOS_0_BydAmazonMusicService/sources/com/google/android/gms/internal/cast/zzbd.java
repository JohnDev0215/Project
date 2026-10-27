package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbd implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzbf zza;

    zzbd(zzbf zzbfVar) {
        this.zza = zzbfVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            zzbf zzbfVar = this.zza;
            if (zzbfVar.zzd != null) {
                zzbfVar.zzd.setVisibility(4);
            }
            this.zza.zza.setVisibility(0);
            this.zza.zza.setImageBitmap(bitmap);
            zzbf zzbfVar2 = this.zza;
            if (zzbfVar2.zzf != null) {
                zzbfVar2.zzf.zza();
            }
        }
    }
}
