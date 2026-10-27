package com.google.android.gms.cast.internal;

import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzai {
    public static final Api.ClientKey zza = new Api.ClientKey();
    public static final Api.ClientKey zzb = new Api.ClientKey();
    public static final Api.ClientKey zzc = new Api.ClientKey();
    public static final Api.ClientKey zzd = new Api.ClientKey();
    public static final Api.ClientKey zze = new Api.ClientKey();
    public static final Api.ClientKey zzf = new Api.ClientKey();

    @Nullable
    public static final Charset zzg;
    public static final String zzh;

    static {
        Charset charsetForName = null;
        try {
            charsetForName = Charset.forName(ParserUtil.UTF_8);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
        }
        zzg = charsetForName;
        zzh = CastUtils.zzc("com.google.cast.multizone");
    }
}
