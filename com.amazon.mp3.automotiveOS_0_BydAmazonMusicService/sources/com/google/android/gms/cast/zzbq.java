package com.google.android.gms.cast;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
final class zzbq extends com.google.android.gms.cast.internal.zzaf {
    final /* synthetic */ zzbr zza;

    zzbq(zzbr zzbrVar) {
        this.zza = zzbrVar;
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzb(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zza.zzp = applicationMetadata;
        this.zza.zzq = str;
        zzbr.zzC(this.zza, new com.google.android.gms.cast.internal.zzq(new Status(0), applicationMetadata, str, str2, z));
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzc(int i) {
        this.zza.zzT(i);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzd(final int i) {
        zzbr.zzE(this.zza, i);
        zzbr zzbrVar = this.zza;
        if (zzbrVar.zzx != null) {
            zzbr.zzn(zzbrVar).post(new Runnable() { // from class: com.google.android.gms.cast.zzbj
                @Override // java.lang.Runnable
                public final void run() {
                    zzbq zzbqVar = this.zza;
                    zzbqVar.zza.zzx.onApplicationDisconnected(i);
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zze(int i) {
        zzbr.zzE(this.zza, i);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzf(final com.google.android.gms.cast.internal.zza zzaVar) {
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbn
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                zzbr.zzy(zzbqVar.zza, zzaVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzg(int i) {
        zzbr.zzE(this.zza, i);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzh(String str, byte[] bArr) {
        zzbr.zzg.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzi(final int i) {
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbk
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                int i2 = i;
                if (i2 != 0) {
                    zzbqVar.zza.zzz = 1;
                    synchronized (zzbqVar.zza.zzy) {
                        Iterator it = zzbqVar.zza.zzy.iterator();
                        while (it.hasNext()) {
                            ((zzq) it.next()).zzb(i2);
                        }
                    }
                    zzbqVar.zza.zzR();
                    return;
                }
                zzbqVar.zza.zzz = 2;
                zzbqVar.zza.zzk = true;
                zzbqVar.zza.zzl = true;
                synchronized (zzbqVar.zza.zzy) {
                    Iterator it2 = zzbqVar.zza.zzy.iterator();
                    while (it2.hasNext()) {
                        ((zzq) it2.next()).zza();
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzj(final com.google.android.gms.cast.internal.zzy zzyVar) {
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbo
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                zzbr.zzz(zzbqVar.zza, zzyVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzk(final int i) {
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbl
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                int i2 = i;
                zzbr.zzx(zzbqVar.zza);
                zzbqVar.zza.zzz = 1;
                synchronized (zzbqVar.zza.zzy) {
                    Iterator it = zzbqVar.zza.zzy.iterator();
                    while (it.hasNext()) {
                        ((zzq) it.next()).zzd(i2);
                    }
                }
                zzbqVar.zza.zzR();
                zzbr zzbrVar = zzbqVar.zza;
                zzbrVar.zzP(zzbrVar.zza);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzl(String str, long j) {
        zzbr.zzD(this.zza, j, 0);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzm(String str, long j, int i) {
        zzbr.zzD(this.zza, j, i);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzn(String str, double d, boolean z) {
        zzbr.zzg.d("Deprecated callback: \"onStatusReceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzo(final int i) {
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbm
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                int i2 = i;
                zzbqVar.zza.zzz = 3;
                synchronized (zzbqVar.zza.zzy) {
                    Iterator it = zzbqVar.zza.zzy.iterator();
                    while (it.hasNext()) {
                        ((zzq) it.next()).zzc(i2);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzp(final String str, final String str2) {
        zzbr.zzg.d("Receive (type=text, ns=%s) %s", str, str2);
        zzbr.zzn(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbp
            @Override // java.lang.Runnable
            public final void run() {
                Cast.MessageReceivedCallback messageReceivedCallback;
                zzbq zzbqVar = this.zza;
                String str3 = str;
                String str4 = str2;
                synchronized (zzbqVar.zza.zze) {
                    messageReceivedCallback = (Cast.MessageReceivedCallback) zzbqVar.zza.zze.get(str3);
                }
                if (messageReceivedCallback != null) {
                    messageReceivedCallback.onMessageReceived(zzbqVar.zza.zzw, str3, str4);
                } else {
                    zzbr.zzg.d("Discarded message for unknown namespace '%s'", str3);
                }
            }
        });
    }
}
