package com.google.android.gms.cast.framework.media;

import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzm {

    @Nullable
    public final MediaSessionCompat.Token zza;
    public final boolean zzb;
    public final int zzc;

    @Nullable
    public final String zzd;
    public final String zze;
    public final boolean zzf;
    public final boolean zzg;

    public zzm(boolean z, int i, @Nullable String str, String str2, @Nullable MediaSessionCompat.Token token, boolean z2, boolean z3) {
        this.zzb = z;
        this.zzc = i;
        this.zzd = str;
        this.zze = str2;
        this.zza = token;
        this.zzf = z2;
        this.zzg = z3;
    }
}
