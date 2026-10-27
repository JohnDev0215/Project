package com.google.android.gms.internal.cast;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzap extends zzr {
    private static final Logger zza = new Logger("MediaRouterProxy");
    private final MediaRouter zzb;
    private final Map zzc = new HashMap();

    @Nullable
    private zzax zzd;

    public zzap(MediaRouter mediaRouter, CastOptions castOptions) {
        this.zzb = mediaRouter;
        if (Build.VERSION.SDK_INT > 30) {
            boolean zZzd = castOptions.zzd();
            boolean zZze = castOptions.zze();
            mediaRouter.setRouterParams(new MediaRouterParams.Builder().setOutputSwitcherEnabled(zZzd).setTransferToLocalEnabled(zZze).build());
            zza.d("output switcher = %b, transfer to local = %b", Boolean.valueOf(zZzd), Boolean.valueOf(zZze));
            if (zZzd) {
                zzl.zzd(zzkk.CAST_OUTPUT_SWITCHER_ENABLED);
            }
            if (zZze) {
                this.zzd = new zzax();
                mediaRouter.setOnPrepareTransferListener(new zzam(this.zzd));
                zzl.zzd(zzkk.CAST_TRANSFER_TO_LOCAL_ENABLED);
            }
        }
    }

    private final void zzr(@Nullable MediaRouteSelector mediaRouteSelector, int i) {
        Set set = (Set) this.zzc.get(mediaRouteSelector);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.zzb.addCallback(mediaRouteSelector, (MediaRouter.Callback) it.next(), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzs, reason: merged with bridge method [inline-methods] */
    public final void zzp(@Nullable MediaRouteSelector mediaRouteSelector) {
        Set set = (Set) this.zzc.get(mediaRouteSelector);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.zzb.removeCallback((MediaRouter.Callback) it.next());
        }
    }

    @Override // com.google.android.gms.internal.cast.zzs
    @Nullable
    public final Bundle zzb(String str) {
        for (MediaRouter.RouteInfo routeInfo : this.zzb.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                return routeInfo.getExtras();
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final String zzc() {
        return this.zzb.getSelectedRoute().getId();
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzd(@Nullable Bundle bundle, final int i) {
        final MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzr(mediaRouteSelectorFromBundle, i);
        } else {
            new zzcv(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzao
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzo(mediaRouteSelectorFromBundle, i);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zze(@Nullable Bundle bundle, zzu zzuVar) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        if (!this.zzc.containsKey(mediaRouteSelectorFromBundle)) {
            this.zzc.put(mediaRouteSelectorFromBundle, new HashSet());
        }
        ((Set) this.zzc.get(mediaRouteSelectorFromBundle)).add(new zzae(zzuVar));
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzf() {
        Iterator it = this.zzc.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((Set) it.next()).iterator();
            while (it2.hasNext()) {
                this.zzb.removeCallback((MediaRouter.Callback) it2.next());
            }
        }
        this.zzc.clear();
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzg(@Nullable Bundle bundle) {
        final MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzp(mediaRouteSelectorFromBundle);
        } else {
            new zzcv(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzan
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzp(mediaRouteSelectorFromBundle);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzh() {
        MediaRouter mediaRouter = this.zzb;
        mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzi(String str) {
        zza.d("select route with routeId = %s", str);
        for (MediaRouter.RouteInfo routeInfo : this.zzb.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                zza.d("media route is found and selected", new Object[0]);
                this.zzb.selectRoute(routeInfo);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final void zzj(int i) {
        this.zzb.unselect(i);
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final boolean zzk() {
        MediaRouter.RouteInfo bluetoothRoute = this.zzb.getBluetoothRoute();
        return bluetoothRoute != null && this.zzb.getSelectedRoute().getId().equals(bluetoothRoute.getId());
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final boolean zzl() {
        MediaRouter.RouteInfo defaultRoute = this.zzb.getDefaultRoute();
        return defaultRoute != null && this.zzb.getSelectedRoute().getId().equals(defaultRoute.getId());
    }

    @Override // com.google.android.gms.internal.cast.zzs
    public final boolean zzm(@Nullable Bundle bundle, int i) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return false;
        }
        return this.zzb.isRouteAvailable(mediaRouteSelectorFromBundle, i);
    }

    @Nullable
    public final zzax zzn() {
        return this.zzd;
    }

    final /* synthetic */ void zzo(MediaRouteSelector mediaRouteSelector, int i) {
        synchronized (this.zzc) {
            zzr(mediaRouteSelector, i);
        }
    }

    public final void zzq(MediaSessionCompat mediaSessionCompat) {
        this.zzb.setMediaSessionCompat(mediaSessionCompat);
    }
}
