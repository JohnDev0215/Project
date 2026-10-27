package com.google.android.gms.cast.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public class Logger {

    @NonNull
    protected final String zza;
    private final boolean zzb;
    private boolean zzc;
    private String zzd;

    @KeepForSdk
    public Logger(@NonNull String str) {
        Preconditions.checkNotEmpty(str, "The log tag cannot be null or empty.");
        this.zza = str;
        this.zzb = str.length() <= 23;
        this.zzc = false;
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull Object... objArr) {
        if (zzd()) {
            Log.d(this.zza, zza(str, objArr));
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull Object... objArr) {
        Log.e(this.zza, zza(str, objArr));
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull Object... objArr) {
        Log.i(this.zza, zza(str, objArr));
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull Object... objArr) {
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull Object... objArr) {
        Log.w(this.zza, zza(str, objArr));
    }

    @NonNull
    protected final String zza(@NonNull String str, @NonNull Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.zzd)) {
            return str;
        }
        String strValueOf = String.valueOf(this.zzd);
        String strValueOf2 = String.valueOf(str);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public final void zzb(boolean z) {
        this.zzc = true;
    }

    public final void zzc(@NonNull String str) {
        this.zzd = TextUtils.isEmpty(null) ? null : String.format("[%s] ", null);
    }

    public final boolean zzd() {
        return this.zzc || (this.zzb && Log.isLoggable(this.zza, 3));
    }

    @KeepForSdk
    public void e(@NonNull Throwable th, @NonNull String str, @NonNull Object... objArr) {
        Log.e(this.zza, zza(str, objArr), th);
    }

    @KeepForSdk
    public void i(@NonNull Throwable th, @NonNull String str, @NonNull Object... objArr) {
        Log.i(this.zza, zza(str, objArr), th);
    }

    @KeepForSdk
    public void w(@NonNull Throwable th, @NonNull String str, @NonNull Object... objArr) {
        Log.w(this.zza, zza(str, objArr), th);
    }

    @KeepForSdk
    public void d(@NonNull Throwable th, @NonNull String str, @NonNull Object... objArr) {
        if (zzd()) {
            Log.d(this.zza, zza(str, objArr), th);
        }
    }
}
