package com.google.android.gms.flags;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.flags.Flag;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes6.dex */
public class FlagRegistry {
    private final Collection<Flag> zza = new ArrayList();
    private final Collection<Flag.StringFlag> zzb = new ArrayList();
    private final Collection<Flag.StringFlag> zzc = new ArrayList();

    @KeepForSdk
    public static void initialize(@NonNull Context context) {
        Singletons.zza().zzb(context);
    }

    public final void zza(@NonNull Flag flag) {
        this.zza.add(flag);
    }
}
