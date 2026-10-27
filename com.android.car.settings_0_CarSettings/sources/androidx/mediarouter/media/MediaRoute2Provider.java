package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
class MediaRoute2Provider extends MediaRouteProvider {
    final Callback mCallback;
    final Map<MediaRouter2.RoutingController, DynamicMediaRoute2Controller> mControllerMap;
    private final Handler mHandler;
    private final Executor mHandlerExecutor;
    final MediaRouter2 mMediaRouter2;
    private final MediaRouter2.RouteCallback mRouteCallback;
    private List<MediaRoute2Info> mRoutes;
    private final MediaRouter2.TransferCallback mTransferCallback;
    static final String TAG = "MR2Provider";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    MediaRoute2Provider(Context context, Callback callback) {
        super(context);
        this.mControllerMap = new ArrayMap();
        this.mRouteCallback = new RouteCallback();
        this.mTransferCallback = new TransferCallback();
        this.mRoutes = new ArrayList();
        this.mMediaRouter2 = MediaRouter2.getInstance(context);
        this.mCallback = callback;
        final Handler handler = new Handler();
        this.mHandler = handler;
        Objects.requireNonNull(handler);
        this.mHandlerExecutor = new Executor() { // from class: androidx.mediarouter.media.-$$Lambda$LfzJt661qZfn2w-6SYHFbD3aMy0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest request) {
        if (MediaRouter.getGlobalCallbackCount() > 0) {
            this.mMediaRouter2.registerRouteCallback(this.mHandlerExecutor, this.mRouteCallback, MediaRouter2Utils.toDiscoveryPreference(request));
            this.mMediaRouter2.registerTransferCallback(this.mHandlerExecutor, this.mTransferCallback);
        } else {
            this.mMediaRouter2.unregisterRouteCallback(this.mRouteCallback);
            this.mMediaRouter2.unregisterTransferCallback(this.mTransferCallback);
        }
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String routeId) {
        return new MediaRoute2Controller(routeId, null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String routeId, String routeGroupId) {
        return new MediaRoute2Controller(routeId, routeGroupId);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController onCreateDynamicGroupRouteController(String initialMemberRouteId) {
        for (Map.Entry<MediaRouter2.RoutingController, DynamicMediaRoute2Controller> entry : this.mControllerMap.entrySet()) {
            DynamicMediaRoute2Controller controller = entry.getValue();
            if (TextUtils.equals(initialMemberRouteId, controller.mInitialMemberRouteId)) {
                return controller;
            }
        }
        return null;
    }

    public void transferTo(String routeId) {
        MediaRoute2Info route = getRouteById(routeId);
        if (route == null) {
            Log.w(TAG, "Specified route not found. routeId=" + routeId);
            return;
        }
        this.mMediaRouter2.transferTo(route);
    }

    protected void refreshRoutes() {
        List<MediaRoute2Info> newRoutes = (List) this.mMediaRouter2.getRoutes().stream().distinct().filter(new Predicate() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2Provider$VT-gS4VAy5vSwKN7_lZ6W4L_NPw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MediaRoute2Provider.lambda$refreshRoutes$0((MediaRoute2Info) obj);
            }
        }).collect(Collectors.toList());
        if (newRoutes.equals(this.mRoutes)) {
            return;
        }
        this.mRoutes = newRoutes;
        List<MediaRouteDescriptor> routeDescriptors = (List) newRoutes.stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$853YVfaGw0-G4oNUYI6Z1ujaq6k
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toMediaRouteDescriptor((MediaRoute2Info) obj);
            }
        }).filter(new Predicate() { // from class: androidx.mediarouter.media.-$$Lambda$jMO9OfSzscMxGho8zZuPtPiQlPo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((MediaRouteDescriptor) obj);
            }
        }).collect(Collectors.toList());
        MediaRouteProviderDescriptor descriptor = new MediaRouteProviderDescriptor.Builder().setSupportsDynamicGroupRoute(true).addRoutes(routeDescriptors).build();
        setDescriptor(descriptor);
    }

    static /* synthetic */ boolean lambda$refreshRoutes$0(MediaRoute2Info r) {
        return !r.isSystemRoute();
    }

    MediaRoute2Info getRouteById(String routeId) {
        if (routeId == null) {
            return null;
        }
        for (MediaRoute2Info route : this.mRoutes) {
            if (TextUtils.equals(route.getId(), routeId)) {
                return route;
            }
        }
        return null;
    }

    static Messenger getMessengerFromRoutingController(MediaRouter2.RoutingController controller) {
        Bundle controlHints;
        if (controller == null || (controlHints = controller.getControlHints()) == null) {
            return null;
        }
        return (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
    }

    static String getSessionIdForRouteController(MediaRouteProvider.RouteController controller) {
        MediaRouter2.RoutingController routingController;
        if ((controller instanceof DynamicMediaRoute2Controller) && (routingController = ((DynamicMediaRoute2Controller) controller).mRoutingController) != null) {
            return routingController.getId();
        }
        return null;
    }

    private class RouteCallback extends MediaRouter2.RouteCallback {
        RouteCallback() {
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesAdded(List<MediaRoute2Info> routes) {
            MediaRoute2Provider.this.refreshRoutes();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesRemoved(List<MediaRoute2Info> routes) {
            MediaRoute2Provider.this.refreshRoutes();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesChanged(List<MediaRoute2Info> routes) {
            MediaRoute2Provider.this.refreshRoutes();
        }
    }

    static abstract class Callback {
        public abstract void onReleaseController(MediaRouteProvider.RouteController routeController);

        public abstract void onSelectFallbackRoute(int i);

        public abstract void onSelectRoute(String str, int i);

        Callback() {
        }
    }

    final class TransferCallback extends MediaRouter2.TransferCallback {
        TransferCallback() {
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransfer(MediaRouter2.RoutingController oldController, MediaRouter2.RoutingController newController) {
            MediaRoute2Provider.this.mControllerMap.remove(oldController);
            if (newController == MediaRoute2Provider.this.mMediaRouter2.getSystemController()) {
                MediaRoute2Provider.this.mCallback.onSelectFallbackRoute(3);
                return;
            }
            List<MediaRoute2Info> selectedRoutes = newController.getSelectedRoutes();
            if (selectedRoutes.isEmpty()) {
                Log.w(MediaRoute2Provider.TAG, "Selected routes are empty. This shouldn't happen.");
                return;
            }
            String routeId = selectedRoutes.get(0).getId();
            DynamicMediaRoute2Controller controller = MediaRoute2Provider.this.new DynamicMediaRoute2Controller(routeId, newController);
            MediaRoute2Provider.this.mControllerMap.put(newController, controller);
            MediaRoute2Provider.this.mCallback.onSelectRoute(routeId, 3);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransferFailure(MediaRoute2Info requestedRoute) {
            Log.w(MediaRoute2Provider.TAG, "Transfer failed. requestedRoute=" + requestedRoute);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onStop(MediaRouter2.RoutingController routingController) {
            MediaRouteProvider.RouteController routeController = MediaRoute2Provider.this.mControllerMap.remove(routingController);
            MediaRoute2Provider.this.mCallback.onReleaseController(routeController);
        }
    }

    private class MediaRoute2Controller extends MediaRouteProvider.RouteController {
        final String mRouteGroupId;
        final String mRouteId;

        MediaRoute2Controller(String routeId, String routeGroupId) {
            this.mRouteId = routeId;
            this.mRouteGroupId = routeGroupId;
        }
    }

    private class DynamicMediaRoute2Controller extends MediaRouteProvider.DynamicGroupRouteController {
        final String mInitialMemberRouteId;
        final Messenger mReceiveMessenger;
        final MediaRouter2.RoutingController mRoutingController;
        final Messenger mServiceMessenger;
        final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks = new SparseArray<>();
        int mNextRequestId = 0;

        DynamicMediaRoute2Controller(String initialMemberRouteId, MediaRouter2.RoutingController routingController) {
            this.mInitialMemberRouteId = initialMemberRouteId;
            this.mRoutingController = routingController;
            Messenger messengerFromRoutingController = MediaRoute2Provider.getMessengerFromRoutingController(routingController);
            this.mServiceMessenger = messengerFromRoutingController;
            this.mReceiveMessenger = messengerFromRoutingController == null ? null : new Messenger(new ReceiveHandler());
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int volume) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(volume);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int delta) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(routingController.getVolume() + delta);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback callback) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null || routingController.isReleased() || this.mServiceMessenger == null) {
                return false;
            }
            int requestId = this.mNextRequestId;
            this.mNextRequestId = requestId + 1;
            Message msg = Message.obtain();
            msg.what = 9;
            msg.arg1 = requestId;
            msg.obj = intent;
            msg.replyTo = this.mReceiveMessenger;
            try {
                this.mServiceMessenger.send(msg);
                if (callback != null) {
                    this.mPendingCallbacks.put(requestId, callback);
                    return true;
                }
                return true;
            } catch (DeadObjectException e) {
                return false;
            } catch (RemoteException ex) {
                Log.e(MediaRoute2Provider.TAG, "Could not send control request to service.", ex);
                return false;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRoutingController.release();
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

        class ReceiveHandler extends Handler {
            ReceiveHandler() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                int what = msg.what;
                int requestId = msg.arg1;
                int i = msg.arg2;
                Object obj = msg.obj;
                Bundle data = msg.peekData();
                MediaRouter.ControlRequestCallback callback = DynamicMediaRoute2Controller.this.mPendingCallbacks.get(requestId);
                if (callback == null) {
                    Log.w(MediaRoute2Provider.TAG, "Pending callback not found for control request.");
                    return;
                }
                DynamicMediaRoute2Controller.this.mPendingCallbacks.remove(requestId);
                if (what == 3) {
                    callback.onResult((Bundle) obj);
                } else if (what == 4) {
                    String error = data == null ? null : data.getString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR);
                    callback.onError(error, (Bundle) obj);
                }
            }
        }
    }
}
