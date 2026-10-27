package defpackage;

import android.media.MediaRouter;
import defpackage.ae1;

/* JADX INFO: compiled from: MediaRouterJellybeanMr1.java */
/* JADX INFO: loaded from: classes.dex */
public final class be1<T extends ae1> extends xd1<T> {
    public be1(T t) {
        super(t);
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        ((ae1) this.a).f(routeInfo);
    }
}
