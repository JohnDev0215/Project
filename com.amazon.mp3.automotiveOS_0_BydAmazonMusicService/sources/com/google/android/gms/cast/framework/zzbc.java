package com.google.android.gms.cast.framework;

import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbc extends zzas {
    final /* synthetic */ SessionProvider zza;

    /* synthetic */ zzbc(SessionProvider sessionProvider, zzbb zzbbVar) {
        this.zza = sessionProvider;
    }

    @Override // com.google.android.gms.cast.framework.zzat
    @Nullable
    public final IObjectWrapper zzb(String str) {
        Session sessionCreateSession = this.zza.createSession(str);
        if (sessionCreateSession == null) {
            return null;
        }
        return sessionCreateSession.zzo();
    }

    @Override // com.google.android.gms.cast.framework.zzat
    public final String zzc() {
        return this.zza.getCategory();
    }

    @Override // com.google.android.gms.cast.framework.zzat
    public final boolean zzd() {
        return this.zza.isSessionRecoverable();
    }
}
