package com.google.android.gms.cast.internal;

import android.content.Context;
import com.google.android.gms.flags.Flag;
import com.google.android.gms.flags.FlagRegistry;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzaa {
    public static final Flag zza = Flag.define(0, "gms:cast:remote_display_enabled", Boolean.FALSE);

    public static final void zza(Context context) {
        FlagRegistry.initialize(context);
    }
}
