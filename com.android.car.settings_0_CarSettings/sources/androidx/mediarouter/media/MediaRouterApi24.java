package androidx.mediarouter.media;

import android.media.MediaRouter;

/* JADX INFO: loaded from: classes4.dex */
final class MediaRouterApi24 {

    public static final class RouteInfo {
        public static int getDeviceType(Object routeObj) {
            return ((MediaRouter.RouteInfo) routeObj).getDeviceType();
        }

        private RouteInfo() {
        }
    }

    private MediaRouterApi24() {
    }
}
