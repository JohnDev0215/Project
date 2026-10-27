package defpackage;

import android.media.MediaRouter;
import defpackage.yd1;

/* JADX INFO: compiled from: MediaRouterJellybean.java */
/* JADX INFO: loaded from: classes.dex */
public final class zd1<T extends yd1> extends MediaRouter.VolumeCallback {
    public final T a;

    public zd1(T t) {
        this.a = t;
    }

    @Override // android.media.MediaRouter.VolumeCallback
    public final void onVolumeSetRequest(MediaRouter.RouteInfo routeInfo, int i) {
        this.a.i(i, routeInfo);
    }

    @Override // android.media.MediaRouter.VolumeCallback
    public final void onVolumeUpdateRequest(MediaRouter.RouteInfo routeInfo, int i) {
        this.a.c(i, routeInfo);
    }
}
