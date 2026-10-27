package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzd;
import com.google.android.gms.internal.play_billing.zzfv;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zzap implements ServiceConnection {
    final /* synthetic */ BillingClientImpl zza;
    private final Object zzb = new Object();
    private boolean zzc = false;
    private BillingClientStateListener zzd;

    /* synthetic */ zzap(BillingClientImpl billingClientImpl, BillingClientStateListener billingClientStateListener, zzao zzaoVar) {
        this.zza = billingClientImpl;
        this.zzd = billingClientStateListener;
    }

    private final void zzd(BillingResult billingResult) {
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingSetupFinished(billingResult);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzi("BillingClient", "Billing service connected.");
        this.zza.zzf = zzd.zzo(iBinder);
        BillingClientImpl billingClientImpl = this.zza;
        if (billingClientImpl.zzL(new Callable() { // from class: com.android.billingclient.api.zzam
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zza();
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzan
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb();
            }
        }, billingClientImpl.zzH()) == null) {
            zzd(this.zza.zzJ());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzj("BillingClient", "Billing service disconnected.");
        int i = zzfv.zzb;
        this.zza.zzf = null;
        this.zza.zza = 0;
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingServiceDisconnected();
            }
        }
    }

    final /* synthetic */ Object zza() throws Exception {
        Bundle bundle;
        int iZzr;
        synchronized (this.zzb) {
            if (this.zzc) {
                return null;
            }
            if (TextUtils.isEmpty(null)) {
                bundle = null;
            } else {
                bundle = new Bundle();
                bundle.putString("accountName", null);
            }
            int i = 3;
            try {
                String packageName = this.zza.zze.getPackageName();
                iZzr = 3;
                int i2 = 19;
                while (true) {
                    if (i2 < 3) {
                        i2 = 0;
                        break;
                    }
                    if (bundle == null) {
                        try {
                            iZzr = this.zza.zzf.zzr(i2, packageName, "subs");
                        } catch (Exception e) {
                            e = e;
                            i = iZzr;
                            zzb.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                            this.zza.zza = 0;
                            this.zza.zzf = null;
                            iZzr = i;
                        }
                    } else {
                        iZzr = this.zza.zzf.zzc(i2, packageName, "subs", bundle);
                    }
                    if (iZzr == 0) {
                        break;
                    }
                    i2--;
                }
                boolean z = true;
                this.zza.zzi = i2 >= 5;
                this.zza.zzh = i2 >= 3;
                if (i2 < 3) {
                    zzb.zzi("BillingClient", "In-app billing API does not support subscription on this device.");
                }
                int i3 = 19;
                while (true) {
                    if (i3 < 3) {
                        break;
                    }
                    iZzr = bundle == null ? this.zza.zzf.zzr(i3, packageName, "inapp") : this.zza.zzf.zzc(i3, packageName, "inapp", bundle);
                    if (iZzr == 0) {
                        this.zza.zzj = i3;
                        break;
                    }
                    i3--;
                }
                BillingClientImpl billingClientImpl = this.zza;
                billingClientImpl.zzu = billingClientImpl.zzj >= 19;
                BillingClientImpl billingClientImpl2 = this.zza;
                billingClientImpl2.zzt = billingClientImpl2.zzj >= 18;
                BillingClientImpl billingClientImpl3 = this.zza;
                billingClientImpl3.zzs = billingClientImpl3.zzj >= 17;
                BillingClientImpl billingClientImpl4 = this.zza;
                billingClientImpl4.zzr = billingClientImpl4.zzj >= 16;
                BillingClientImpl billingClientImpl5 = this.zza;
                billingClientImpl5.zzq = billingClientImpl5.zzj >= 15;
                BillingClientImpl billingClientImpl6 = this.zza;
                billingClientImpl6.zzp = billingClientImpl6.zzj >= 14;
                BillingClientImpl billingClientImpl7 = this.zza;
                billingClientImpl7.zzo = billingClientImpl7.zzj >= 12;
                BillingClientImpl billingClientImpl8 = this.zza;
                billingClientImpl8.zzn = billingClientImpl8.zzj >= 10;
                BillingClientImpl billingClientImpl9 = this.zza;
                billingClientImpl9.zzm = billingClientImpl9.zzj >= 9;
                BillingClientImpl billingClientImpl10 = this.zza;
                billingClientImpl10.zzl = billingClientImpl10.zzj >= 8;
                BillingClientImpl billingClientImpl11 = this.zza;
                if (billingClientImpl11.zzj < 6) {
                    z = false;
                }
                billingClientImpl11.zzk = z;
                if (this.zza.zzj < 3) {
                    zzb.zzj("BillingClient", "In-app billing API version 3 is not supported on this device.");
                }
                if (iZzr == 0) {
                    this.zza.zza = 2;
                } else {
                    this.zza.zza = 0;
                    this.zza.zzf = null;
                }
            } catch (Exception e2) {
                e = e2;
            }
            if (iZzr == 0) {
                zzd(zzbc.zzl);
            } else {
                zzd(zzbc.zza);
            }
            return null;
        }
    }

    final /* synthetic */ void zzb() {
        this.zza.zza = 0;
        this.zza.zzf = null;
        zzd(zzbc.zzn);
    }
}
