package androidx.mediarouter.media;

import android.media.MediaRouter;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(24)
final class MediaRouterApi24 {

    public static final class RouteInfo {
        public static int getDeviceType(Object obj) {
            return ((MediaRouter.RouteInfo) obj).getDeviceType();
        }

        private RouteInfo() {
        }
    }

    private MediaRouterApi24() {
    }
}
