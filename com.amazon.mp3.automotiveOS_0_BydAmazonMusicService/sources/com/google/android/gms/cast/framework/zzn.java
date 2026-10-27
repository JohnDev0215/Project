package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbr;
import com.google.android.gms.cast.zzbs;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzn extends zzw {
    final /* synthetic */ CastSession zza;

    /* synthetic */ zzn(CastSession castSession, zzm zzmVar) {
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzb(int i) {
        CastSession.zzh(this.zza, i);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzc(final String str, final String str2) {
        CastSession castSession = this.zza;
        if (castSession.zzh != null) {
            final zzbr zzbrVar = (zzbr) castSession.zzh;
            final zzbs zzbsVar = null;
            zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall(str, str2, zzbsVar) { // from class: com.google.android.gms.cast.zzbf
                public final /* synthetic */ String zzb;
                public final /* synthetic */ String zzc;

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    this.zza.zzF(this.zzb, this.zzc, null, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8407).build()).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.cast.framework.zzk
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    CastSession.zzi(this.zza.zza, "joinApplication", task);
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzd(final String str, final LaunchOptions launchOptions) {
        CastSession castSession = this.zza;
        if (castSession.zzh != null) {
            final zzbr zzbrVar = (zzbr) castSession.zzh;
            zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbe
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    zzbrVar.zzG(str, launchOptions, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8406).build()).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.cast.framework.zzl
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    CastSession.zzi(this.zza.zza, TetheredMessageKey.launchApplication, task);
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zze(final String str) {
        CastSession castSession = this.zza;
        if (castSession.zzh != null) {
            final zzbr zzbrVar = (zzbr) castSession.zzh;
            zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbc
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    zzbrVar.zzM(str, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8409).build());
        }
    }
}
