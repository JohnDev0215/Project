package com.google.android.gms.cast.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public class zzd extends zzp {
    private final List zzb;

    public zzd(String str, String str2, @Nullable String str3) {
        super(str, "MediaControlChannel", null);
        this.zzb = Collections.synchronizedList(new ArrayList());
    }

    protected final List zza() {
        return this.zzb;
    }

    protected final void zzb() {
        synchronized (this.zzb) {
            Iterator it = this.zzb.iterator();
            while (it.hasNext()) {
                ((zzat) it.next()).zzc(CastStatusCodes.CANCELED);
            }
        }
    }

    protected final void zzc(zzat zzatVar) {
        this.zzb.add(zzatVar);
    }
}
