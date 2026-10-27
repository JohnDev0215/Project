package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
class MediaRoute2ProviderServiceAdapter extends MediaRoute2ProviderService {
    public static final String SERVICE_INTERFACE = "android.media.MediaRoute2ProviderService";
    private volatile MediaRouteProviderDescriptor mProviderDescriptor;
    final MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mServiceImpl;
    private static final String TAG = "MR2ProviderService";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private final Object mLock = new Object();
    final Map<String, MediaRouteProvider.DynamicGroupRouteController> mControllers = new ArrayMap();
    final SparseArray<String> mSessionIdMap = new SparseArray<>();
    final Map<String, Messenger> mMessengers = new ArrayMap();

    MediaRoute2ProviderServiceAdapter(MediaRouteProviderService.MediaRouteProviderServiceImplApi30 serviceImpl) {
        this.mServiceImpl = serviceImpl;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetRouteVolume(long requestId, String routeId, int volume) {
        MediaRouteProvider.RouteController controller = this.mServiceImpl.getControllerForRouteId(routeId);
        if (controller == null) {
            Log.w(TAG, "onSetRouteVolume: Couldn't find a controller for routeId=" + routeId);
            notifyRequestFailed(requestId, 3);
            return;
        }
        controller.onSetVolume(volume);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetSessionVolume(long requestId, String sessionId, int volume) {
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "onSetSessionVolume: Couldn't find a session");
            notifyRequestFailed(requestId, 4);
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController controller = getController(sessionId);
        if (controller == null) {
            Log.w(TAG, "onSetSessionVolume: Couldn't find a controller");
            notifyRequestFailed(requestId, 3);
        } else {
            controller.onSetVolume(volume);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onCreateSession(long requestId, String packageName, String routeId, Bundle sessionHints) {
        MediaRouteProvider.DynamicGroupRouteController controller;
        MediaRouteProvider provider = getMediaRouteProvider();
        MediaRouteDescriptor selectedRoute = getRouteDescriptor(routeId, "onCreateSession");
        if (selectedRoute == null) {
            notifyRequestFailed(requestId, 3);
            return;
        }
        if (this.mProviderDescriptor.supportsDynamicGroupRoute()) {
            controller = provider.onCreateDynamicGroupRouteController(routeId);
            if (controller == null) {
                Log.w(TAG, "onCreateSession: Couldn't create a dynamic controller");
                notifyRequestFailed(requestId, 1);
                return;
            }
        } else {
            MediaRouteProvider.RouteController routeController = provider.onCreateRouteController(routeId);
            if (routeController == null) {
                Log.w(TAG, "onCreateSession: Couldn't create a controller");
                notifyRequestFailed(requestId, 1);
                return;
            }
            controller = new DynamicGroupRouteControllerProxy(routeController);
        }
        String sessionId = assignSessionId(controller);
        controller.onSelect();
        RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(sessionId, packageName).addSelectedRoute(routeId).setName(selectedRoute.getName()).setVolumeHandling(selectedRoute.getVolumeHandling()).setVolume(selectedRoute.getVolume()).setVolumeMax(selectedRoute.getVolumeMax());
        Messenger messenger = new Messenger(new IncomingHandler(this, sessionId));
        this.mMessengers.put(sessionId, messenger);
        Bundle controlHints = new Bundle();
        controlHints.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", messenger);
        builder.setControlHints(controlHints).build();
        RoutingSessionInfo sessionInfo = builder.build();
        notifySessionCreated(requestId, sessionInfo);
        this.mServiceImpl.setDynamicRoutesChangedListener(controller);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onReleaseSession(long requestId, String sessionId) {
        MediaRouteProvider.DynamicGroupRouteController controller;
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            return;
        }
        updateMemberRouteControllers(null, sessionInfo, null);
        synchronized (this.mLock) {
            controller = this.mControllers.remove(sessionId);
            this.mMessengers.remove(sessionId);
        }
        if (controller == null) {
            Log.w(TAG, "onReleaseSession: Couldn't find a controller");
            notifyRequestFailed(requestId, 4);
        } else {
            controller.onUnselect(0);
            controller.onRelease();
            notifySessionReleased(sessionId);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSelectRoute(long requestId, String sessionId, String routeId) {
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "onSelectRoute: Couldn't find a session");
            notifyRequestFailed(requestId, 4);
        } else {
            if (getRouteDescriptor(routeId, "onSelectRoute") == null) {
                notifyRequestFailed(requestId, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(sessionId);
            if (controller == null) {
                Log.w(TAG, "onSelectRoute: Couldn't find a controller");
                notifyRequestFailed(requestId, 3);
            } else {
                controller.onAddMemberRoute(routeId);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDeselectRoute(long requestId, String sessionId, String routeId) {
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "onDeselectRoute: Couldn't find a session");
            notifyRequestFailed(requestId, 4);
        } else {
            if (getRouteDescriptor(routeId, "onDeselectRoute") == null) {
                notifyRequestFailed(requestId, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(sessionId);
            if (controller == null) {
                Log.w(TAG, "onDeselectRoute: Couldn't find a controller");
                notifyRequestFailed(requestId, 3);
            } else {
                controller.onRemoveMemberRoute(routeId);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onTransferToRoute(long requestId, String sessionId, String routeId) {
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "onTransferToRoute: Couldn't find a session");
            notifyRequestFailed(requestId, 4);
        } else {
            if (getRouteDescriptor(routeId, "onTransferToRoute") == null) {
                notifyRequestFailed(requestId, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(sessionId);
            if (controller == null) {
                Log.w(TAG, "onTransferToRoute: Couldn't find a controller");
                notifyRequestFailed(requestId, 3);
            } else {
                controller.onUpdateMemberRoutes(Collections.singletonList(routeId));
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDiscoveryPreferenceChanged(RouteDiscoveryPreference preference) {
        MediaRouteSelector selector = new MediaRouteSelector.Builder().addControlCategories((Collection) preference.getPreferredFeatures().stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$nHCoRGqeXOfAyZcF9QiL0A7Yx-o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toControlCategory((String) obj);
            }
        }).collect(Collectors.toList())).build();
        this.mServiceImpl.setBaseDiscoveryRequest(new MediaRouteDiscoveryRequest(selector, preference.shouldPerformActiveScan()));
    }

    public void setProviderDescriptor(MediaRouteProviderDescriptor descriptor) {
        this.mProviderDescriptor = descriptor;
        List<MediaRouteDescriptor> routeDescriptors = descriptor == null ? Collections.emptyList() : descriptor.getRoutes();
        notifyRoutes(((Map) routeDescriptors.stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$Nj9OYR6TduhgFYKcn-h4bkjilY0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toFwkMediaRoute2Info((MediaRouteDescriptor) obj);
            }
        }).filter(new Predicate() { // from class: androidx.mediarouter.media.-$$Lambda$8fo3R-Przkq5mg2wxR3lAN3cgNY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((MediaRoute2Info) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$zA6-X6GUoq4-2PKz5R8c7BbTrQE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaRoute2Info) obj).getId();
            }
        }, new Function() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$7qAEsKlKB806oaKa6yx0w_wmnBk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRoute2ProviderServiceAdapter.lambda$setProviderDescriptor$1((MediaRoute2Info) obj);
            }
        }, new BinaryOperator() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$kAB5NMaZMpZ3LLziosuBx-l4x40
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MediaRoute2ProviderServiceAdapter.lambda$setProviderDescriptor$2((MediaRoute2Info) obj, (MediaRoute2Info) obj2);
            }
        }))).values());
    }

    static /* synthetic */ MediaRoute2Info lambda$setProviderDescriptor$1(MediaRoute2Info r) {
        return r;
    }

    static /* synthetic */ MediaRoute2Info lambda$setProviderDescriptor$2(MediaRoute2Info a, MediaRoute2Info b) {
        return b;
    }

    private MediaRouteProvider.DynamicGroupRouteController getController(String sessionId) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController;
        synchronized (this.mLock) {
            dynamicGroupRouteController = this.mControllers.get(sessionId);
        }
        return dynamicGroupRouteController;
    }

    private MediaRouteDescriptor getRouteDescriptor(String routeId, String description) {
        MediaRouteProvider provider = getMediaRouteProvider();
        if (provider == null || this.mProviderDescriptor == null) {
            Log.w(TAG, description + ": no provider info");
            return null;
        }
        List<MediaRouteDescriptor> routes = this.mProviderDescriptor.getRoutes();
        for (MediaRouteDescriptor route : routes) {
            if (TextUtils.equals(route.getId(), routeId)) {
                return route;
            }
        }
        Log.w(TAG, description + ": Couldn't find a route : " + routeId);
        return null;
    }

    public void setDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRoute, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> descriptors) {
        String sessionId = null;
        synchronized (this.mLock) {
            Iterator<Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController>> it = this.mControllers.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController> entry = it.next();
                if (entry.getValue() == controller) {
                    sessionId = entry.getKey();
                    break;
                }
            }
        }
        if (sessionId == null) {
            Log.w(TAG, "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(sessionInfo).clearSelectedRoutes().clearSelectableRoutes().clearDeselectableRoutes().clearTransferableRoutes();
        if (groupRoute != null) {
            builder.setName(groupRoute.getName()).setVolume(groupRoute.getVolume()).setVolumeMax(groupRoute.getVolumeMax()).setVolumeHandling(groupRoute.getVolumeHandling());
        }
        for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor descriptor : descriptors) {
            String routeId = descriptor.getRouteDescriptor().getId();
            if (descriptor.mSelectionState == 2 || descriptor.mSelectionState == 3) {
                builder.addSelectedRoute(routeId);
            }
            if (descriptor.isGroupable()) {
                builder.addSelectableRoute(routeId);
            }
            if (descriptor.isUnselectable()) {
                builder.addDeselectableRoute(routeId);
            }
            if (descriptor.isTransferable()) {
                builder.addTransferableRoute(routeId);
            }
        }
        RoutingSessionInfo newSessionInfo = builder.build();
        updateMemberRouteControllers(groupRoute.getId(), sessionInfo, newSessionInfo);
        notifySessionUpdated(newSessionInfo);
    }

    void onControlRequest(final Messenger messenger, final int requestId, final String sessionId, final Intent intent) {
        RoutingSessionInfo sessionInfo = getSessionInfo(sessionId);
        if (sessionInfo == null) {
            Log.w(TAG, "onCustomCommand: Couldn't find a session");
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController controller = getController(sessionId);
        if (controller == null) {
            Log.w(TAG, "onControlRequest: Couldn't find a controller");
            notifyRequestFailed(requestId, 3);
        } else {
            MediaRouter.ControlRequestCallback callback = new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.MediaRoute2ProviderServiceAdapter.1
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle data) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d(MediaRoute2ProviderServiceAdapter.TAG, "Route control request succeeded, sessionId=" + sessionId + ", intent=" + intent + ", data=" + data);
                    }
                    sendReply(messenger, 3, requestId, 0, data, null);
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String error, Bundle data) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d(MediaRoute2ProviderServiceAdapter.TAG, "Route control request failed, sessionId=" + sessionId + ", intent=" + intent + ", error=" + error + ", data=" + data);
                    }
                    if (error != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, error);
                        sendReply(messenger, 4, requestId, 0, data, bundle);
                        return;
                    }
                    sendReply(messenger, 4, requestId, 0, data, null);
                }

                void sendReply(Messenger messenger2, int what, int requestId2, int arg, Object obj, Bundle data) {
                    Message msg = Message.obtain();
                    msg.what = what;
                    msg.arg1 = requestId2;
                    msg.arg2 = arg;
                    msg.obj = obj;
                    msg.setData(data);
                    try {
                        messenger2.send(msg);
                    } catch (DeadObjectException e) {
                    } catch (RemoteException ex) {
                        Log.e(MediaRoute2ProviderServiceAdapter.TAG, "Could not send message to the client.", ex);
                    }
                }
            };
            controller.onControlRequest(intent, callback);
        }
    }

    void updateMemberRouteControllers(String groupId, RoutingSessionInfo oldSession, RoutingSessionInfo newSession) {
        List<String> oldRouteIds = oldSession == null ? Collections.emptyList() : oldSession.getSelectedRoutes();
        List<String> newRouteIds = newSession == null ? Collections.emptyList() : newSession.getSelectedRoutes();
        for (String routeId : newRouteIds) {
            MediaRouteProvider.RouteController controller = this.mServiceImpl.getControllerForRouteId(routeId);
            if (controller == null) {
                MediaRouteProvider.RouteController controller2 = this.mServiceImpl.createRouteControllerWithoutClient(routeId, groupId);
                controller2.onSelect();
            }
        }
        for (String routeId2 : oldRouteIds) {
            if (!newRouteIds.contains(routeId2)) {
                this.mServiceImpl.releaseRouteControllerForRouteId(routeId2);
            }
        }
    }

    void addRouteController(MediaRouteProvider.RouteController routeController, int controllerId, String packageName, String routeId) {
        MediaRouteProvider.DynamicGroupRouteController controller;
        MediaRouteDescriptor descriptor = getRouteDescriptor(routeId, "addRouteController");
        if (descriptor == null) {
            return;
        }
        if (routeController instanceof MediaRouteProvider.DynamicGroupRouteController) {
            controller = (MediaRouteProvider.DynamicGroupRouteController) routeController;
        } else {
            controller = new DynamicGroupRouteControllerProxy(routeController);
        }
        String sessionId = assignSessionId(controller);
        this.mSessionIdMap.put(controllerId, sessionId);
        RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(sessionId, packageName).addSelectedRoute(routeId).setName(descriptor.getName()).setVolumeHandling(descriptor.getVolumeHandling()).setVolume(descriptor.getVolume()).setVolumeMax(descriptor.getVolumeMax());
        RoutingSessionInfo sessionInfo = builder.build();
        notifySessionCreated(0L, sessionInfo);
    }

    void removeRouteController(int controllerId) {
        String sessionId = this.mSessionIdMap.get(controllerId);
        if (sessionId == null) {
            return;
        }
        this.mSessionIdMap.remove(controllerId);
        synchronized (this.mLock) {
            this.mControllers.remove(sessionId);
            notifySessionReleased(sessionId);
        }
    }

    private MediaRouteProvider getMediaRouteProvider() {
        MediaRouteProviderService service = this.mServiceImpl.getService();
        if (service == null) {
            return null;
        }
        return service.getMediaRouteProvider();
    }

    private String assignSessionId(MediaRouteProvider.DynamicGroupRouteController controller) {
        String sessionId;
        synchronized (this.mLock) {
            do {
                sessionId = UUID.randomUUID().toString();
            } while (this.mControllers.containsKey(sessionId));
            this.mControllers.put(sessionId, controller);
        }
        return sessionId;
    }

    private static class DynamicGroupRouteControllerProxy extends MediaRouteProvider.DynamicGroupRouteController {
        private final MediaRouteProvider.RouteController mRouteController;

        DynamicGroupRouteControllerProxy(MediaRouteProvider.RouteController routeController) {
            this.mRouteController = routeController;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRouteController.onRelease();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mRouteController.onSelect();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int reason) {
            this.mRouteController.onUnselect(reason);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int volume) {
            this.mRouteController.onSetVolume(volume);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int delta) {
            this.mRouteController.onUpdateVolume(delta);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback callback) {
            return this.mRouteController.onControlRequest(intent, callback);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> routeIds) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String routeId) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String routeId) {
        }
    }

    static class IncomingHandler extends Handler {
        private final MediaRoute2ProviderServiceAdapter mServiceAdapter;
        private final String mSessionId;

        IncomingHandler(MediaRoute2ProviderServiceAdapter serviceAdapter, String sessionId) {
            super(Looper.myLooper());
            this.mServiceAdapter = serviceAdapter;
            this.mSessionId = sessionId;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Messenger messenger = msg.replyTo;
            int what = msg.what;
            int requestId = msg.arg1;
            Object obj = msg.obj;
            if (what == 9 && (obj instanceof Intent)) {
                this.mServiceAdapter.onControlRequest(messenger, requestId, this.mSessionId, (Intent) obj);
            }
        }
    }
}
