package com.google.android.gms.internal.play_billing;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzcg {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName(ParserUtil.UTF_8);
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzbe zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzbe.zza;
        zzbc zzbcVar = new zzbc(bArr, 0, 0, false, null);
        try {
            zzbcVar.zza(0);
            zzf = zzbcVar;
        } catch (zzci e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    static int zzb(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, zzb);
    }
}
