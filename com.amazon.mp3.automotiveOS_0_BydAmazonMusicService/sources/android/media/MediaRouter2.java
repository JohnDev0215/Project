package android.media;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.content.Context;
import android.os.Bundle;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class MediaRouter2 {

    public /* synthetic */ class RoutingController {
        static {
            throw new NoClassDefFoundError();
        }

        public native /* synthetic */ void deselectRoute(@NonNull MediaRoute2Info mediaRoute2Info);

        @Nullable
        public native /* synthetic */ Bundle getControlHints();

        @NonNull
        public native /* synthetic */ List<MediaRoute2Info> getDeselectableRoutes();

        @NonNull
        public native /* synthetic */ String getId();

        @NonNull
        public native /* synthetic */ List<MediaRoute2Info> getSelectableRoutes();

        @NonNull
        public native /* synthetic */ List<MediaRoute2Info> getSelectedRoutes();

        public native /* synthetic */ int getVolume();

        public native /* synthetic */ int getVolumeHandling();

        public native /* synthetic */ int getVolumeMax();

        public native /* synthetic */ boolean isReleased();

        public native /* synthetic */ void release();

        public native /* synthetic */ void selectRoute(@NonNull MediaRoute2Info mediaRoute2Info);

        public native /* synthetic */ void setVolume(int i);
    }

    static {
        throw new NoClassDefFoundError();
    }

    @NonNull
    public static native /* synthetic */ MediaRouter2 getInstance(@NonNull Context context);

    @NonNull
    public native /* synthetic */ List<MediaRoute2Info> getRoutes();

    @NonNull
    public native /* synthetic */ RoutingController getSystemController();

    public native /* synthetic */ void registerControllerCallback(@NonNull Executor executor, @NonNull ControllerCallback controllerCallback);

    public native /* synthetic */ void registerRouteCallback(@NonNull Executor executor, @NonNull RouteCallback routeCallback, @NonNull RouteDiscoveryPreference routeDiscoveryPreference);

    public native /* synthetic */ void registerTransferCallback(@NonNull Executor executor, @NonNull TransferCallback transferCallback);

    public native /* synthetic */ void transferTo(@NonNull MediaRoute2Info mediaRoute2Info);

    public native /* synthetic */ void unregisterControllerCallback(@NonNull ControllerCallback controllerCallback);

    public native /* synthetic */ void unregisterRouteCallback(@NonNull RouteCallback routeCallback);

    public native /* synthetic */ void unregisterTransferCallback(@NonNull TransferCallback transferCallback);
}
