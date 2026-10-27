package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class SessionProvider {
    private final Context zza;
    private final String zzb;
    private final zzbc zzc = new zzbc(this, null);

    protected SessionProvider(@NonNull Context context, @NonNull String str) {
        this.zza = ((Context) Preconditions.checkNotNull(context)).getApplicationContext();
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    @Nullable
    public abstract Session createSession(@NonNull String str);

    @NonNull
    public final String getCategory() {
        return this.zzb;
    }

    @NonNull
    public final Context getContext() {
        return this.zza;
    }

    public abstract boolean isSessionRecoverable();

    @NonNull
    public final IBinder zza() {
        return this.zzc;
    }
}
