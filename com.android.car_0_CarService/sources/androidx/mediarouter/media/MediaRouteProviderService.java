package androidx.mediarouter.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.android.launcher3.icons.cache.BaseIconCache;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaRouteProviderService extends Service {
    static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    final MediaRouteProviderServiceImpl mImpl;
    MediaRouteProvider mProvider;
    private final MediaRouteProvider.Callback mProviderCallback;
    static final String TAG = "MediaRouteProviderSrv";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
    final Messenger mReceiveMessenger = new Messenger(this.mReceiveHandler);
    final PrivateHandler mPrivateHandler = new PrivateHandler();

    interface MediaRouteProviderServiceImpl {
        void attachBaseContext(Context context);

        MediaRouteProvider.Callback getProviderCallback();

        boolean onAddMemberRoute(Messenger messenger, int i, int i2, String str);

        IBinder onBind(Intent intent);

        void onBinderDied(Messenger messenger);

        boolean onCreateDynamicGroupRouteController(Messenger messenger, int i, int i2, String str);

        boolean onCreateRouteController(Messenger messenger, int i, int i2, String str, String str2);

        boolean onRegisterClient(Messenger messenger, int i, int i2, String str);

        boolean onReleaseRouteController(Messenger messenger, int i, int i2);

        boolean onRemoveMemberRoute(Messenger messenger, int i, int i2, String str);

        boolean onRouteControlRequest(Messenger messenger, int i, int i2, Intent intent);

        boolean onSelectRoute(Messenger messenger, int i, int i2);

        boolean onSetDiscoveryRequest(Messenger messenger, int i, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest);

        boolean onSetRouteVolume(Messenger messenger, int i, int i2, int i3);

        boolean onUnregisterClient(Messenger messenger, int i);

        boolean onUnselectRoute(Messenger messenger, int i, int i2, int i3);

        boolean onUpdateMemberRoutes(Messenger messenger, int i, int i2, List<String> list);

        boolean onUpdateRouteVolume(Messenger messenger, int i, int i2, int i3);
    }

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    public MediaRouteProviderService() {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new MediaRouteProviderServiceImplApi30(this);
        } else {
            this.mImpl = new MediaRouteProviderServiceImplBase(this);
        }
        this.mProviderCallback = this.mImpl.getProviderCallback();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.mImpl.attachBaseContext(context);
    }

    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    void ensureProvider() {
        MediaRouteProvider provider;
        if (this.mProvider == null && (provider = onCreateMediaRouteProvider()) != null) {
            String providerPackage = provider.getMetadata().getPackageName();
            if (!providerPackage.equals(getPackageName())) {
                throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + providerPackage + ".  Service package name: " + getPackageName() + BaseIconCache.EMPTY_CLASS_NAME);
            }
            this.mProvider = provider;
            provider.setCallback(this.mProviderCallback);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        MediaRouteProvider mediaRouteProvider = this.mProvider;
        if (mediaRouteProvider != null) {
            mediaRouteProvider.setCallback(null);
        }
        super.onDestroy();
    }

    static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor descriptor, int clientVersion) {
        if (descriptor == null) {
            return null;
        }
        MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder(descriptor);
        builder.setRoutes(null);
        for (MediaRouteDescriptor route : descriptor.getRoutes()) {
            if (clientVersion >= route.getMinClientVersion() && clientVersion <= route.getMaxClientVersion()) {
                builder.addRoute(route);
            }
        }
        return builder.build().asBundle();
    }

    static void sendGenericFailure(Messenger messenger, int requestId) {
        if (requestId != 0) {
            sendReply(messenger, 0, requestId, 0, null, null);
        }
    }

    static void sendGenericSuccess(Messenger messenger, int requestId) {
        if (requestId != 0) {
            sendReply(messenger, 1, requestId, 0, null, null);
        }
    }

    static void sendReply(Messenger messenger, int what, int requestId, int arg, Object obj, Bundle data) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.arg1 = requestId;
        msg.arg2 = arg;
        msg.obj = obj;
        msg.setData(data);
        try {
            messenger.send(msg);
        } catch (DeadObjectException e) {
        } catch (RemoteException ex) {
            Log.e(TAG, "Could not send message to " + getClientId(messenger), ex);
        }
    }

    static String getClientId(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }

    private final class PrivateHandler extends Handler {
        PrivateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                MediaRouteProviderService.this.mImpl.onBinderDied((Messenger) msg.obj);
            }
        }
    }

    private static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService service) {
            this.mServiceRef = new WeakReference<>(service);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r18) {
            /*
                r17 = this;
                r0 = r18
                android.os.Messenger r9 = r0.replyTo
                boolean r1 = androidx.mediarouter.media.MediaRouteProviderProtocol.isValidRemoteMessenger(r9)
                java.lang.String r10 = "MediaRouteProviderSrv"
                if (r1 == 0) goto L98
                int r11 = r0.what
                int r12 = r0.arg1
                int r13 = r0.arg2
                java.lang.Object r14 = r0.obj
                android.os.Bundle r15 = r18.peekData()
                r1 = 0
                r2 = 1
                if (r11 != r2) goto L41
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                if (r2 < r3) goto L41
                r8 = r17
                java.lang.ref.WeakReference<androidx.mediarouter.media.MediaRouteProviderService> r2 = r8.mServiceRef
                java.lang.Object r2 = r2.get()
                androidx.mediarouter.media.MediaRouteProviderService r2 = (androidx.mediarouter.media.MediaRouteProviderService) r2
                android.content.pm.PackageManager r2 = r2.getPackageManager()
                int r3 = r0.sendingUid
                java.lang.String[] r2 = r2.getPackagesForUid(r3)
                if (r2 == 0) goto L43
                int r3 = r2.length
                if (r3 <= 0) goto L43
                r3 = 0
                r1 = r2[r3]
                r16 = r1
                goto L45
            L41:
                r8 = r17
            L43:
                r16 = r1
            L45:
                r1 = r17
                r2 = r11
                r3 = r9
                r4 = r12
                r5 = r13
                r6 = r14
                r7 = r15
                r8 = r16
                boolean r1 = r1.processMessage(r2, r3, r4, r5, r6, r7, r8)
                if (r1 != 0) goto L97
                boolean r1 = androidx.mediarouter.media.MediaRouteProviderService.DEBUG
                if (r1 == 0) goto L94
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = androidx.mediarouter.media.MediaRouteProviderService.getClientId(r9)
                r1.append(r2)
                java.lang.String r2 = ": Message failed, what="
                r1.append(r2)
                r1.append(r11)
                java.lang.String r2 = ", requestId="
                r1.append(r2)
                r1.append(r12)
                java.lang.String r2 = ", arg="
                r1.append(r2)
                r1.append(r13)
                java.lang.String r2 = ", obj="
                r1.append(r2)
                r1.append(r14)
                java.lang.String r2 = ", data="
                r1.append(r2)
                r1.append(r15)
                java.lang.String r1 = r1.toString()
                android.util.Log.d(r10, r1)
            L94:
                androidx.mediarouter.media.MediaRouteProviderService.sendGenericFailure(r9, r12)
            L97:
                goto La1
            L98:
                boolean r1 = androidx.mediarouter.media.MediaRouteProviderService.DEBUG
                if (r1 == 0) goto La1
                java.lang.String r1 = "Ignoring message without valid reply messenger."
                android.util.Log.d(r10, r1)
            La1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouteProviderService.ReceiveHandler.handleMessage(android.os.Message):void");
        }

        private boolean processMessage(int what, Messenger messenger, int requestId, int arg, Object obj, Bundle data, String packageName) {
            MediaRouteProviderService service = this.mServiceRef.get();
            if (service != null) {
                switch (what) {
                    case 1:
                        return service.mImpl.onRegisterClient(messenger, requestId, arg, packageName);
                    case 2:
                        return service.mImpl.onUnregisterClient(messenger, requestId);
                    case 3:
                        String routeId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
                        String routeGroupId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP);
                        if (routeId != null) {
                            return service.mImpl.onCreateRouteController(messenger, requestId, arg, routeId, routeGroupId);
                        }
                        break;
                    case 4:
                        return service.mImpl.onReleaseRouteController(messenger, requestId, arg);
                    case 5:
                        return service.mImpl.onSelectRoute(messenger, requestId, arg);
                    case 6:
                        int reason = data != null ? data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, 0) : 0;
                        return service.mImpl.onUnselectRoute(messenger, requestId, arg, reason);
                    case 7:
                        int volume = data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, -1);
                        if (volume >= 0) {
                            return service.mImpl.onSetRouteVolume(messenger, requestId, arg, volume);
                        }
                        break;
                    case 8:
                        int delta = data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, 0);
                        if (delta != 0) {
                            return service.mImpl.onUpdateRouteVolume(messenger, requestId, arg, delta);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return service.mImpl.onRouteControlRequest(messenger, requestId, arg, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            MediaRouteDiscoveryRequest request = MediaRouteDiscoveryRequest.fromBundle((Bundle) obj);
                            return service.mImpl.onSetDiscoveryRequest(messenger, requestId, (request == null || !request.isValid()) ? null : request);
                        }
                        break;
                    case 11:
                        String initialMemberId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (initialMemberId != null) {
                            return service.mImpl.onCreateDynamicGroupRouteController(messenger, requestId, arg, initialMemberId);
                        }
                        break;
                    case 12:
                        String memberId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (memberId != null) {
                            return service.mImpl.onAddMemberRoute(messenger, requestId, arg, memberId);
                        }
                        break;
                    case 13:
                        String memberId2 = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (memberId2 != null) {
                            return service.mImpl.onRemoveMemberRoute(messenger, requestId, arg, memberId2);
                        }
                        break;
                    case 14:
                        ArrayList<String> memberIds = data.getStringArrayList(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_IDS);
                        if (memberIds != null) {
                            return service.mImpl.onUpdateMemberRoutes(messenger, requestId, arg, memberIds);
                        }
                        break;
                }
            }
            return false;
        }
    }

    static class MediaRouteProviderServiceImplBase implements MediaRouteProviderServiceImpl {
        MediaRouteDiscoveryRequest mBaseDiscoveryRequest;
        final ArrayList<ClientRecord> mClients = new ArrayList<>();
        MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
        final MediaRouteProviderService mService;

        MediaRouteProviderServiceImplBase(MediaRouteProviderService service) {
            this.mService = service;
        }

        public MediaRouteProviderService getService() {
            return this.mService;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            if (intent.getAction().equals("android.media.MediaRouteProviderService")) {
                this.mService.ensureProvider();
                if (this.mService.getMediaRouteProvider() != null) {
                    return this.mService.mReceiveMessenger.getBinder();
                }
                return null;
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public MediaRouteProvider.Callback getProviderCallback() {
            return new ProviderCallbackBase();
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRegisterClient(Messenger messenger, int requestId, int version, String packageName) {
            if (version >= 1) {
                int index = findClient(messenger);
                if (index < 0) {
                    ClientRecord client = createClientRecord(messenger, version, packageName);
                    if (client.register()) {
                        this.mClients.add(client);
                        if (MediaRouteProviderService.DEBUG) {
                            Log.d(MediaRouteProviderService.TAG, client + ": Registered, version=" + version);
                        }
                        if (requestId != 0) {
                            MediaRouteProviderDescriptor descriptor = this.mService.getMediaRouteProvider().getDescriptor();
                            MediaRouteProviderService.sendReply(messenger, 2, requestId, 2, MediaRouteProviderService.createDescriptorBundleForClientVersion(descriptor, client.mVersion), null);
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnregisterClient(Messenger messenger, int requestId) {
            int index = findClient(messenger);
            if (index >= 0) {
                ClientRecord client = this.mClients.remove(index);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Unregistered");
                }
                client.dispose();
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void onBinderDied(Messenger messenger) {
            int index = findClient(messenger);
            if (index >= 0) {
                ClientRecord client = this.mClients.remove(index);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Binder died");
                }
                client.dispose();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateRouteController(Messenger messenger, int requestId, int controllerId, String routeId, String routeGroupId) {
            ClientRecord client = getClient(messenger);
            if (client != null && client.createRouteController(routeId, routeGroupId, controllerId)) {
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route controller created, controllerId=" + controllerId + ", routeId=" + routeId + ", routeGroupId=" + routeGroupId);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateDynamicGroupRouteController(Messenger messenger, int requestId, int controllerId, String initialMemberRouteId) {
            Bundle bundle;
            ClientRecord client = getClient(messenger);
            if (client != null && (bundle = client.createDynamicGroupRouteController(initialMemberRouteId, controllerId)) != null) {
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route controller created, controllerId=" + controllerId + ", initialMemberRouteId=" + initialMemberRouteId);
                }
                MediaRouteProviderService.sendReply(messenger, 6, requestId, 2, bundle, null);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onAddMemberRoute(Messenger messenger, int requestId, int controllerId, String memberId) {
            ClientRecord client = getClient(messenger);
            if (client != null) {
                MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
                if (controller instanceof MediaRouteProvider.DynamicGroupRouteController) {
                    ((MediaRouteProvider.DynamicGroupRouteController) controller).onAddMemberRoute(memberId);
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Added a member route, controllerId=" + controllerId + ", memberId=" + memberId);
                    }
                    MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRemoveMemberRoute(Messenger messenger, int requestId, int controllerId, String memberId) {
            ClientRecord client = getClient(messenger);
            if (client != null) {
                MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
                if (controller instanceof MediaRouteProvider.DynamicGroupRouteController) {
                    ((MediaRouteProvider.DynamicGroupRouteController) controller).onRemoveMemberRoute(memberId);
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Removed a member route, controllerId=" + controllerId + ", memberId=" + memberId);
                    }
                    MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateMemberRoutes(Messenger messenger, int requestId, int controllerId, List<String> memberIds) {
            ClientRecord client = getClient(messenger);
            if (client != null) {
                MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
                if (controller instanceof MediaRouteProvider.DynamicGroupRouteController) {
                    ((MediaRouteProvider.DynamicGroupRouteController) controller).onUpdateMemberRoutes(memberIds);
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Updated list of member routes, controllerId=" + controllerId + ", memberIds=" + memberIds);
                    }
                    MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onReleaseRouteController(Messenger messenger, int requestId, int controllerId) {
            ClientRecord client = getClient(messenger);
            if (client != null && client.releaseRouteController(controllerId)) {
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route controller released, controllerId=" + controllerId);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSelectRoute(Messenger messenger, int requestId, int controllerId) {
            MediaRouteProvider.RouteController controller;
            ClientRecord client = getClient(messenger);
            if (client != null && (controller = client.getRouteController(controllerId)) != null) {
                controller.onSelect();
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route selected, controllerId=" + controllerId);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnselectRoute(Messenger messenger, int requestId, int controllerId, int reason) {
            MediaRouteProvider.RouteController controller;
            ClientRecord client = getClient(messenger);
            if (client != null && (controller = client.getRouteController(controllerId)) != null) {
                controller.onUnselect(reason);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route unselected, controllerId=" + controllerId);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetRouteVolume(Messenger messenger, int requestId, int controllerId, int volume) {
            MediaRouteProvider.RouteController controller;
            ClientRecord client = getClient(messenger);
            if (client != null && (controller = client.getRouteController(controllerId)) != null) {
                controller.onSetVolume(volume);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route volume changed, controllerId=" + controllerId + ", volume=" + volume);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateRouteVolume(Messenger messenger, int requestId, int controllerId, int delta) {
            MediaRouteProvider.RouteController controller;
            ClientRecord client = getClient(messenger);
            if (client != null && (controller = client.getRouteController(controllerId)) != null) {
                controller.onUpdateVolume(delta);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Route volume updated, controllerId=" + controllerId + ", delta=" + delta);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRouteControlRequest(final Messenger messenger, final int requestId, final int controllerId, final Intent intent) {
            MediaRouteProvider.RouteController controller;
            final ClientRecord client = getClient(messenger);
            if (client != null && (controller = client.getRouteController(controllerId)) != null) {
                MediaRouter.ControlRequestCallback callback = null;
                if (requestId != 0) {
                    callback = new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.1
                        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                        public void onResult(Bundle data) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d(MediaRouteProviderService.TAG, client + ": Route control request succeeded, controllerId=" + controllerId + ", intent=" + intent + ", data=" + data);
                            }
                            if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                                MediaRouteProviderService.sendReply(messenger, 3, requestId, 0, data, null);
                            }
                        }

                        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                        public void onError(String error, Bundle data) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d(MediaRouteProviderService.TAG, client + ": Route control request failed, controllerId=" + controllerId + ", intent=" + intent + ", error=" + error + ", data=" + data);
                            }
                            if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                                if (error != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, error);
                                    MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, data, bundle);
                                    return;
                                }
                                MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, data, null);
                            }
                        }
                    };
                }
                if (controller.onControlRequest(intent, callback)) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Route control request delivered, controllerId=" + controllerId + ", intent=" + intent);
                        return true;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetDiscoveryRequest(Messenger messenger, int requestId, MediaRouteDiscoveryRequest request) {
            ClientRecord client = getClient(messenger);
            if (client != null) {
                boolean actuallyChanged = client.setDiscoveryRequest(request);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Set discovery request, request=" + request + ", actuallyChanged=" + actuallyChanged + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
                }
                MediaRouteProviderService.sendGenericSuccess(messenger, requestId);
                return true;
            }
            return false;
        }

        void sendDescriptorChanged(MediaRouteProviderDescriptor descriptor) {
            int count = this.mClients.size();
            for (int i = 0; i < count; i++) {
                ClientRecord client = this.mClients.get(i);
                MediaRouteProviderService.sendReply(client.mMessenger, 5, 0, 0, MediaRouteProviderService.createDescriptorBundleForClientVersion(descriptor, client.mVersion), null);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, client + ": Sent descriptor change event, descriptor=" + descriptor);
                }
            }
        }

        boolean setBaseDiscoveryRequest(MediaRouteDiscoveryRequest request) {
            if (!ObjectsCompat.equals(this.mBaseDiscoveryRequest, request)) {
                this.mBaseDiscoveryRequest = request;
                return updateCompositeDiscoveryRequest();
            }
            return false;
        }

        boolean updateCompositeDiscoveryRequest() {
            MediaRouteDiscoveryRequest composite = null;
            MediaRouteSelector.Builder selectorBuilder = null;
            boolean activeScan = false;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mBaseDiscoveryRequest;
            if (mediaRouteDiscoveryRequest != null) {
                activeScan = mediaRouteDiscoveryRequest.isActiveScan();
                selectorBuilder = new MediaRouteSelector.Builder(this.mBaseDiscoveryRequest.getSelector());
            }
            int count = this.mClients.size();
            for (int i = 0; i < count; i++) {
                MediaRouteDiscoveryRequest request = this.mClients.get(i).mDiscoveryRequest;
                if (request != null && (!request.getSelector().isEmpty() || request.isActiveScan())) {
                    activeScan |= request.isActiveScan();
                    if (composite == null) {
                        composite = request;
                    } else {
                        if (selectorBuilder == null) {
                            selectorBuilder = new MediaRouteSelector.Builder(composite.getSelector());
                        }
                        selectorBuilder.addSelector(request.getSelector());
                    }
                }
            }
            if (selectorBuilder != null) {
                composite = new MediaRouteDiscoveryRequest(selectorBuilder.build(), activeScan);
            }
            if (!ObjectsCompat.equals(this.mCompositeDiscoveryRequest, composite)) {
                this.mCompositeDiscoveryRequest = composite;
                this.mService.getMediaRouteProvider().setDiscoveryRequest(composite);
                return true;
            }
            return false;
        }

        private ClientRecord getClient(Messenger messenger) {
            int index = findClient(messenger);
            if (index >= 0) {
                return this.mClients.get(index);
            }
            return null;
        }

        int findClient(Messenger messenger) {
            int count = this.mClients.size();
            for (int i = 0; i < count; i++) {
                ClientRecord client = this.mClients.get(i);
                if (client.hasMessenger(messenger)) {
                    return i;
                }
            }
            return -1;
        }

        class ClientRecord implements IBinder.DeathRecipient {
            public MediaRouteDiscoveryRequest mDiscoveryRequest;
            public final Messenger mMessenger;
            public final String mPackageName;
            public final int mVersion;
            final SparseArray<MediaRouteProvider.RouteController> mControllers = new SparseArray<>();
            final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord.1
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRoute, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> routes) {
                    ClientRecord.this.sendDynamicRouteDescriptors(controller, groupRoute, routes);
                }
            };

            ClientRecord(Messenger messenger, int version, String packageName) {
                this.mMessenger = messenger;
                this.mVersion = version;
                this.mPackageName = packageName;
            }

            public boolean register() {
                try {
                    this.mMessenger.getBinder().linkToDeath(this, 0);
                    return true;
                } catch (RemoteException e) {
                    binderDied();
                    return false;
                }
            }

            public void dispose() {
                int count = this.mControllers.size();
                for (int i = 0; i < count; i++) {
                    this.mControllers.valueAt(i).onRelease();
                }
                this.mControllers.clear();
                this.mMessenger.getBinder().unlinkToDeath(this, 0);
                setDiscoveryRequest(null);
            }

            public boolean hasMessenger(Messenger other) {
                return this.mMessenger.getBinder() == other.getBinder();
            }

            public boolean createRouteController(String routeId, String routeGroupId, int controllerId) {
                MediaRouteProvider.RouteController controller;
                if (this.mControllers.indexOfKey(controllerId) < 0) {
                    if (routeGroupId == null) {
                        controller = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(routeId);
                    } else {
                        controller = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(routeId, routeGroupId);
                    }
                    if (controller != null) {
                        this.mControllers.put(controllerId, controller);
                        return true;
                    }
                    return false;
                }
                return false;
            }

            public Bundle createDynamicGroupRouteController(String initialMemberRouteId, int controllerId) {
                MediaRouteProvider.DynamicGroupRouteController controller;
                if (this.mControllers.indexOfKey(controllerId) < 0 && (controller = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateDynamicGroupRouteController(initialMemberRouteId)) != null) {
                    controller.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(MediaRouteProviderServiceImplBase.this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
                    this.mControllers.put(controllerId, controller);
                    Bundle bundle = new Bundle();
                    bundle.putString(MediaRouteProviderProtocol.DATA_KEY_GROUPABLE_SECION_TITLE, controller.getGroupableSelectionTitle());
                    bundle.putString(MediaRouteProviderProtocol.DATA_KEY_TRANSFERABLE_SECTION_TITLE, controller.getTransferableSectionTitle());
                    return bundle;
                }
                return null;
            }

            public boolean releaseRouteController(int controllerId) {
                MediaRouteProvider.RouteController controller = this.mControllers.get(controllerId);
                if (controller != null) {
                    this.mControllers.remove(controllerId);
                    controller.onRelease();
                    return true;
                }
                return false;
            }

            public MediaRouteProvider.RouteController getRouteController(int controllerId) {
                return this.mControllers.get(controllerId);
            }

            public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest request) {
                if (!ObjectsCompat.equals(this.mDiscoveryRequest, request)) {
                    this.mDiscoveryRequest = request;
                    return MediaRouteProviderServiceImplBase.this.updateCompositeDiscoveryRequest();
                }
                return false;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                MediaRouteProviderServiceImplBase.this.mService.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
            }

            public String toString() {
                return MediaRouteProviderService.getClientId(this.mMessenger);
            }

            void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRoute, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> descriptors) {
                int index = this.mControllers.indexOfValue(controller);
                if (index < 0) {
                    Log.w(MediaRouteProviderService.TAG, "Ignoring unknown dynamic group route controller: " + controller);
                    return;
                }
                int controllerId = this.mControllers.keyAt(index);
                ArrayList<Bundle> dynamicRouteBundles = new ArrayList<>();
                for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor descriptor : descriptors) {
                    dynamicRouteBundles.add(descriptor.toBundle());
                }
                Bundle bundle = new Bundle();
                if (groupRoute != null) {
                    bundle.putParcelable(MediaRouteProviderProtocol.DATA_KEY_GROUP_ROUTE_DESCRIPTOR, groupRoute.asBundle());
                }
                bundle.putParcelableArrayList(MediaRouteProviderProtocol.DATA_KEY_DYNAMIC_ROUTE_DESCRIPTORS, dynamicRouteBundles);
                MediaRouteProviderService.sendReply(this.mMessenger, 7, 0, controllerId, bundle, null);
            }
        }

        ClientRecord createClientRecord(Messenger messenger, int version, String packageName) {
            return new ClientRecord(messenger, version, packageName);
        }

        class ProviderCallbackBase extends MediaRouteProvider.Callback {
            ProviderCallbackBase() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(MediaRouteProvider provider, MediaRouteProviderDescriptor descriptor) {
                MediaRouteProviderServiceImplBase.this.sendDescriptorChanged(descriptor);
            }
        }
    }

    static class MediaRouteProviderServiceImplApi30 extends MediaRouteProviderServiceImplBase {
        final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener;
        MediaRoute2ProviderServiceAdapter mMR2ProviderServiceAdapter;
        final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;

        public /* synthetic */ void lambda$new$0$MediaRouteProviderService$MediaRouteProviderServiceImplApi30(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRoute, Collection routes) {
            this.mMR2ProviderServiceAdapter.setDynamicRouteDescriptor(controller, groupRoute, routes);
        }

        MediaRouteProviderServiceImplApi30(MediaRouteProviderService instance) {
            super(instance);
            this.mRouteIdToControllerMap = new ArrayMap();
            this.mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRouteProviderService$MediaRouteProviderServiceImplApi30$5ZnMIBnILgRv9MPxLYf0aIsoP5k
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public final void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection collection) {
                    this.f$0.lambda$new$0$MediaRouteProviderService$MediaRouteProviderServiceImplApi30(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            };
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            this.mService.ensureProvider();
            if (this.mMR2ProviderServiceAdapter == null) {
                this.mMR2ProviderServiceAdapter = new MediaRoute2ProviderServiceAdapter(this);
                if (this.mService.getBaseContext() != null) {
                    this.mMR2ProviderServiceAdapter.attachBaseContext(this.mService);
                }
            }
            IBinder binder = super.onBind(intent);
            if (binder != null) {
                return binder;
            }
            return this.mMR2ProviderServiceAdapter.onBind(intent);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
            MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter = this.mMR2ProviderServiceAdapter;
            if (mediaRoute2ProviderServiceAdapter != null) {
                mediaRoute2ProviderServiceAdapter.attachBaseContext(context);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        void sendDescriptorChanged(MediaRouteProviderDescriptor descriptor) {
            super.sendDescriptorChanged(descriptor);
            this.mMR2ProviderServiceAdapter.setProviderDescriptor(descriptor);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        MediaRouteProviderServiceImplBase.ClientRecord createClientRecord(Messenger messenger, int version, String packageName) {
            return new ClientRecord(messenger, version, packageName);
        }

        MediaRouteProvider.RouteController getControllerForRouteId(String routeId) {
            return this.mRouteIdToControllerMap.get(routeId);
        }

        MediaRouteProvider.RouteController createRouteControllerWithoutClient(String routeId, String routeGroupId) {
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController;
            MediaRouteProvider.RouteController controller = this.mRouteIdToControllerMap.get(routeId);
            if (controller != null) {
                return controller;
            }
            if (routeGroupId == null) {
                routeControllerOnCreateRouteController = this.mService.getMediaRouteProvider().onCreateRouteController(routeId);
            } else {
                routeControllerOnCreateRouteController = this.mService.getMediaRouteProvider().onCreateRouteController(routeId, routeGroupId);
            }
            MediaRouteProvider.RouteController controller2 = routeControllerOnCreateRouteController;
            if (controller2 != null) {
                this.mRouteIdToControllerMap.put(routeId, controller2);
            }
            return controller2;
        }

        boolean releaseRouteControllerForRouteId(String routeId) {
            MediaRouteProvider.RouteController controller = this.mRouteIdToControllerMap.remove(routeId);
            if (controller == null) {
                return false;
            }
            controller.onUnselect(0);
            controller.onRelease();
            return true;
        }

        void setDynamicRoutesChangedListener(MediaRouteProvider.DynamicGroupRouteController controller) {
            controller.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
        }

        class ClientRecord extends MediaRouteProviderServiceImplBase.ClientRecord {
            ClientRecord(Messenger messenger, int version, String packageName) {
                super(messenger, version, packageName);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean createRouteController(String routeId, String routeGroupId, int controllerId) {
                MediaRouteProvider.RouteController controller = MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.get(routeId);
                if (controller != null) {
                    this.mControllers.put(controllerId, controller);
                    return true;
                }
                boolean result = super.createRouteController(routeId, routeGroupId, controllerId);
                if (routeGroupId == null && result && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.addRouteController(this.mControllers.get(controllerId), controllerId, this.mPackageName, routeId);
                }
                if (result) {
                    MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.put(routeId, this.mControllers.get(controllerId));
                }
                return result;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDynamicGroupRouteController(String initialMemberRouteId, int controllerId) {
                Bundle result = super.createDynamicGroupRouteController(initialMemberRouteId, controllerId);
                if (result != null && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.addRouteController(this.mControllers.get(controllerId), controllerId, this.mPackageName, initialMemberRouteId);
                }
                return result;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean releaseRouteController(int controllerId) {
                MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.removeRouteController(controllerId);
                MediaRouteProvider.RouteController controller = this.mControllers.get(controllerId);
                if (controller != null) {
                    Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> it = MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<String, MediaRouteProvider.RouteController> entry = it.next();
                        if (entry.getValue() == controller) {
                            MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.remove(entry.getKey());
                            break;
                        }
                    }
                }
                return super.releaseRouteController(controllerId);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRoute, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> descriptors) {
                super.sendDynamicRouteDescriptors(controller, groupRoute, descriptors);
                if (MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.setDynamicRouteDescriptor(controller, groupRoute, descriptors);
                }
            }
        }
    }
}
