package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzam implements MediaRouter.OnPrepareTransferListener {
    private static final Logger zza = new Logger("MediaRouterOPTListener");
    private final zzax zzb;
    private final Handler zzc = new zzcv(Looper.getMainLooper());

    public zzam(zzax zzaxVar) {
        this.zzb = (zzax) Preconditions.checkNotNull(zzaxVar);
    }

    @Override // androidx.mediarouter.media.MediaRouter.OnPrepareTransferListener
    public final ListenableFuture onPrepareTransfer(final MediaRouter.RouteInfo routeInfo, final MediaRouter.RouteInfo routeInfo2) {
        zza.d("Prepare transfer from Route(%s) to Route(%s)", routeInfo, routeInfo2);
        final zzoi zzoiVarZzj = zzoi.zzj();
        this.zzc.post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzal
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(routeInfo, routeInfo2, zzoiVarZzj);
            }
        });
        return zzoiVarZzj;
    }

    final /* synthetic */ void zza(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2, zzoi zzoiVar) {
        this.zzb.zze(routeInfo, routeInfo2, zzoiVar);
    }
}
