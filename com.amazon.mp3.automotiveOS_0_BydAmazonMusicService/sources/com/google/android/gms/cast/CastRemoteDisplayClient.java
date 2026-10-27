package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@TargetApi(19)
@Deprecated
public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.AbstractClientBuilder zza;
    private static final Api zzb;
    private final Logger zzc;

    @Nullable
    private VirtualDisplay zzd;

    static {
        zzx zzxVar = new zzx();
        zza = zzxVar;
        zzb = new Api("CastRemoteDisplay.API", zzxVar, com.google.android.gms.cast.internal.zzai.zzd);
    }

    CastRemoteDisplayClient(Context context) {
        super(context, (Api<Api.ApiOptions.NoOptions>) zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zzc = new Logger("CastRemoteDisplay");
    }

    static /* bridge */ /* synthetic */ void zzd(CastRemoteDisplayClient castRemoteDisplayClient) {
        VirtualDisplay virtualDisplay = castRemoteDisplayClient.zzd;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                Logger logger = castRemoteDisplayClient.zzc;
                int displayId = castRemoteDisplayClient.zzd.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                logger.d(sb.toString(), new Object[0]);
            }
            VirtualDisplay virtualDisplay2 = castRemoteDisplayClient.zzd;
            if (virtualDisplay2 != null) {
                virtualDisplay2.release();
                castRemoteDisplayClient.zzd = null;
            }
        }
    }

    @NonNull
    public Task<Display> startRemoteDisplay(@NonNull CastDevice castDevice, @NonNull String str, @CastRemoteDisplay.Configuration int i, @Nullable PendingIntent pendingIntent) {
        return zze(castDevice, str, i, pendingIntent, null);
    }

    @NonNull
    public Task<Void> stopRemoteDisplay() {
        return doWrite(TaskApiCall.builder().setMethodKey(8402).run(new RemoteCall() { // from class: com.google.android.gms.cast.zzv
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.cast.zzcr) ((com.google.android.gms.internal.cast.zzcm) obj).getService()).zzi(new zzz(this.zza, (TaskCompletionSource) obj2));
            }
        }).build());
    }

    public final Task zze(final CastDevice castDevice, final String str, @CastRemoteDisplay.Configuration final int i, @Nullable final PendingIntent pendingIntent, @Nullable final zzah zzahVar) {
        final byte[] bArr = null;
        return doWrite(TaskApiCall.builder().setMethodKey(8401).run(new RemoteCall(i, zzahVar, pendingIntent, castDevice, str, bArr) { // from class: com.google.android.gms.cast.zzw
            public final /* synthetic */ int zzb;
            public final /* synthetic */ PendingIntent zzc;
            public final /* synthetic */ CastDevice zzd;
            public final /* synthetic */ String zze;
            public final /* synthetic */ zzah zzf;

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                CastRemoteDisplayClient castRemoteDisplayClient = this.zza;
                int i2 = this.zzb;
                zzah zzahVar2 = this.zzf;
                PendingIntent pendingIntent2 = this.zzc;
                CastDevice castDevice2 = this.zzd;
                String str2 = this.zze;
                com.google.android.gms.internal.cast.zzcm zzcmVar = (com.google.android.gms.internal.cast.zzcm) obj;
                Bundle bundle = new Bundle();
                bundle.putInt("configuration", i2);
                ((com.google.android.gms.internal.cast.zzcr) zzcmVar.getService()).zzh(new zzy(castRemoteDisplayClient, (TaskCompletionSource) obj2, zzcmVar, zzahVar2, null), pendingIntent2, castDevice2.getDeviceId(), str2, bundle);
            }
        }).build());
    }
}
