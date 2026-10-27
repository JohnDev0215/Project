package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import android.text.TextUtils;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzae extends MediaRouter.Callback {
    private static final Logger zza = new Logger("MediaRouterCallback");
    private final zzu zzb;

    public zzae(zzu zzuVar) {
        this.zzb = (zzu) Preconditions.checkNotNull(zzuVar);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzb.zzf(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteAdded", zzu.class.getSimpleName());
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzb.zzg(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteChanged", zzu.class.getSimpleName());
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzb.zzh(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteRemoved", zzu.class.getSimpleName());
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        CastDevice fromBundle;
        CastDevice fromBundle2;
        zza.d("onRouteSelected with reason = %d, routeId = %s", Integer.valueOf(i), routeInfo.getId());
        if (routeInfo.getPlaybackType() != 1) {
            return;
        }
        try {
            String id = routeInfo.getId();
            String id2 = routeInfo.getId();
            if (id2 != null && id2.endsWith("-groupRoute") && (fromBundle = CastDevice.getFromBundle(routeInfo.getExtras())) != null) {
                String deviceId = fromBundle.getDeviceId();
                Iterator<MediaRouter.RouteInfo> it = mediaRouter.getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaRouter.RouteInfo next = it.next();
                    String id3 = next.getId();
                    if (id3 != null && !id3.endsWith("-groupRoute") && (fromBundle2 = CastDevice.getFromBundle(next.getExtras())) != null && TextUtils.equals(fromBundle2.getDeviceId(), deviceId)) {
                        zza.d("routeId is changed from %s to %s", id2, next.getId());
                        id2 = next.getId();
                        break;
                    }
                }
            }
            if (this.zzb.zze() >= 220400000) {
                this.zzb.zzj(id2, id, routeInfo.getExtras());
            } else {
                this.zzb.zzi(id2, routeInfo.getExtras());
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteSelected", zzu.class.getSimpleName());
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        Logger logger = zza;
        logger.d("onRouteUnselected with reason = %d, routeId = %s", Integer.valueOf(i), routeInfo.getId());
        if (routeInfo.getPlaybackType() != 1) {
            logger.d("skip route unselection for non-cast route", new Object[0]);
            return;
        }
        try {
            this.zzb.zzk(routeInfo.getId(), routeInfo.getExtras(), i);
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteUnselected", zzu.class.getSimpleName());
        }
    }
}
