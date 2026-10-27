package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public final class zzcl implements CastRemoteDisplayApi {
    private static final Logger zza = new Logger("CastRemoteDisplayApiImpl");
    private final Api zzb;

    @Nullable
    private VirtualDisplay zzc;
    private final zzct zzd = new zzcd(this);

    public zzcl(Api api) {
        this.zzb = api;
    }

    static /* bridge */ /* synthetic */ void zzf(zzcl zzclVar) {
        VirtualDisplay virtualDisplay = zzclVar.zzc;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                Logger logger = zza;
                int displayId = virtualDisplay.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                logger.d(sb.toString(), new Object[0]);
            }
            virtualDisplay.release();
        }
        zzclVar.zzc = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zza.d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzce(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zza.d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzcf(this, googleApiClient));
    }
}
