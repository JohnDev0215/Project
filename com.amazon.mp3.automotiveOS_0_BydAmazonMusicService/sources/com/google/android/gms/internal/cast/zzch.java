package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@TargetApi(19)
public final class zzch extends zzcg {
    final /* synthetic */ zzcj zza;
    private final zzco zzb;

    public zzch(zzcj zzcjVar, zzco zzcoVar) {
        this.zza = zzcjVar;
        this.zzb = zzcoVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cast.zzcg, com.google.android.gms.internal.cast.zzcq
    public final void zzb(int i, int i2, Surface surface) {
        zzcl.zza.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzb.getContext().getSystemService("display");
        if (displayManager == null) {
            zzcl.zza.e("Unable to get the display manager", new Object[0]);
            this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        zzcl.zzf(this.zza.zzc);
        this.zza.zzc.zzc = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / 1080, surface, 2);
        zzcl zzclVar = this.zza.zzc;
        if (zzclVar.zzc == null) {
            zzcl.zza.e("Unable to create virtual display", new Object[0]);
            this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
        } else {
            if (zzclVar.zzc.getDisplay() == null) {
                zzcl.zza.e("Virtual display does not have a display", new Object[0]);
                this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
                return;
            }
            try {
                ((zzcr) this.zzb.getService()).zzf(this, this.zza.zzc.zzc.getDisplay().getDisplayId());
            } catch (RemoteException | IllegalStateException unused) {
                zzcl.zza.e("Unable to provision the route's new virtual Display", new Object[0]);
                this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzcg, com.google.android.gms.internal.cast.zzcq
    public final void zzc() {
        zzcl.zza.d("onConnectedWithDisplay", new Object[0]);
        zzcl zzclVar = this.zza.zzc;
        if (zzclVar.zzc == null) {
            zzcl.zza.e("There is no virtual display", new Object[0]);
            this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        Display display = zzclVar.zzc.getDisplay();
        if (display != null) {
            this.zza.setResult(new zzck(display));
        } else {
            zzcl.zza.e("Virtual display no longer has a display", new Object[0]);
            this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzcg, com.google.android.gms.internal.cast.zzcq
    public final void zzd(int i) throws RemoteException {
        zzcl.zza.d("onError: %d", Integer.valueOf(i));
        zzcl.zzf(this.zza.zzc);
        this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
    }
}
