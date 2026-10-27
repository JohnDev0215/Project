package android.media;

import android.app.job.JobInfo;
import android.content.Context;
import android.media.IMediaRouter2;
import android.media.IMediaRouterService;
import android.media.MediaRouter2;
import android.media.RouteDiscoveryPreference;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaRouter2 {
    private static final long MANAGER_REQUEST_ID_NONE = 0;
    private static final int TRANSFER_TIMEOUT_MS = 30000;
    private static MediaRouter2 sInstance;
    private final Context mContext;
    final Handler mHandler;
    private final IMediaRouterService mMediaRouterService;
    private volatile OnGetControllerHintsListener mOnGetControllerHintsListener;
    private final String mPackageName;
    MediaRouter2Stub mStub;
    final RoutingController mSystemController;
    private static final String TAG = "MR2";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final Object sRouterLock = new Object();
    private final CopyOnWriteArrayList<RouteCallbackRecord> mRouteCallbackRecords = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<TransferCallbackRecord> mTransferCallbackRecords = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<ControllerCallbackRecord> mControllerCallbackRecords = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<ControllerCreationRequest> mControllerCreationRequests = new CopyOnWriteArrayList<>();
    final Map<String, MediaRoute2Info> mRoutes = new ArrayMap();
    private RouteDiscoveryPreference mDiscoveryPreference = RouteDiscoveryPreference.EMPTY;
    private final Map<String, RoutingController> mNonSystemRoutingControllers = new ArrayMap();
    private final AtomicInteger mNextRequestId = new AtomicInteger(1);
    private boolean mShouldUpdateRoutes = true;
    private volatile List<MediaRoute2Info> mFilteredRoutes = Collections.emptyList();

    public static abstract class ControllerCallback {
        public void onControllerUpdated(RoutingController routingController) {
        }
    }

    public interface OnGetControllerHintsListener {
        Bundle onGetControllerHints(MediaRoute2Info mediaRoute2Info);
    }

    public static abstract class RouteCallback {
        public void onRoutesAdded(List<MediaRoute2Info> list) {
        }

        public void onRoutesChanged(List<MediaRoute2Info> list) {
        }

        public void onRoutesRemoved(List<MediaRoute2Info> list) {
        }
    }

    public static abstract class TransferCallback {
        public void onStop(RoutingController routingController) {
        }

        public void onTransfer(RoutingController routingController, RoutingController routingController2) {
        }

        public void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
        }
    }

    public static MediaRouter2 getInstance(Context context) {
        MediaRouter2 mediaRouter2;
        Objects.requireNonNull(context, "context must not be null");
        synchronized (sRouterLock) {
            if (sInstance == null) {
                sInstance = new MediaRouter2(context.getApplicationContext());
            }
            mediaRouter2 = sInstance;
        }
        return mediaRouter2;
    }

    private MediaRouter2(Context context) {
        List<MediaRoute2Info> systemRoutes;
        this.mContext = context;
        IMediaRouterService iMediaRouterServiceAsInterface = IMediaRouterService.Stub.asInterface(ServiceManager.getService(Context.MEDIA_ROUTER_SERVICE));
        this.mMediaRouterService = iMediaRouterServiceAsInterface;
        this.mPackageName = context.getPackageName();
        this.mHandler = new Handler(Looper.getMainLooper());
        RoutingSessionInfo systemSessionInfo = null;
        try {
            systemRoutes = iMediaRouterServiceAsInterface.getSystemRoutes();
            try {
                systemSessionInfo = iMediaRouterServiceAsInterface.getSystemSessionInfo();
            } catch (RemoteException e) {
                e = e;
                Log.e(TAG, "Unable to get current system's routes / session info", e);
            }
        } catch (RemoteException e2) {
            e = e2;
            systemRoutes = null;
        }
        if (systemRoutes == null || systemRoutes.isEmpty()) {
            throw new RuntimeException("Null or empty currentSystemRoutes. Something is wrong.");
        }
        if (systemSessionInfo == null) {
            throw new RuntimeException("Null currentSystemSessionInfo. Something is wrong.");
        }
        for (MediaRoute2Info mediaRoute2Info : systemRoutes) {
            this.mRoutes.put(mediaRoute2Info.getId(), mediaRoute2Info);
        }
        this.mSystemController = new SystemRoutingController(systemSessionInfo);
    }

    static boolean checkRouteListContainsRouteId(List<MediaRoute2Info> list, String str) {
        Iterator<MediaRoute2Info> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, it.next().getId())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005b A[Catch: all -> 0x005d, DONT_GENERATE, TryCatch #2 {, blocks: (B:4:0x0022, B:6:0x0026, B:7:0x002b, B:10:0x0036, B:11:0x003e, B:13:0x0042, B:15:0x0048, B:18:0x0053, B:19:0x005b), top: B:28:0x0022, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void registerRouteCallback(java.util.concurrent.Executor r2, android.media.MediaRouter2.RouteCallback r3, android.media.RouteDiscoveryPreference r4) {
        /*
            r1 = this;
            java.lang.String r0 = "executor must not be null"
            java.util.Objects.requireNonNull(r2, r0)
            java.lang.String r0 = "callback must not be null"
            java.util.Objects.requireNonNull(r3, r0)
            java.lang.String r0 = "preference must not be null"
            java.util.Objects.requireNonNull(r4, r0)
            android.media.MediaRouter2$RouteCallbackRecord r0 = new android.media.MediaRouter2$RouteCallbackRecord
            r0.<init>(r2, r3, r4)
            java.util.concurrent.CopyOnWriteArrayList<android.media.MediaRouter2$RouteCallbackRecord> r2 = r1.mRouteCallbackRecords
            r2.remove(r0)
            java.util.concurrent.CopyOnWriteArrayList<android.media.MediaRouter2$RouteCallbackRecord> r2 = r1.mRouteCallbackRecords
            r2.addIfAbsent(r0)
            java.lang.Object r2 = android.media.MediaRouter2.sRouterLock
            monitor-enter(r2)
            android.media.MediaRouter2$MediaRouter2Stub r3 = r1.mStub     // Catch: java.lang.Throwable -> L5d
            if (r3 != 0) goto L3e
            android.media.MediaRouter2$MediaRouter2Stub r3 = new android.media.MediaRouter2$MediaRouter2Stub     // Catch: java.lang.Throwable -> L5d
            r3.<init>()     // Catch: java.lang.Throwable -> L5d
            android.media.IMediaRouterService r4 = r1.mMediaRouterService     // Catch: android.os.RemoteException -> L35 java.lang.Throwable -> L5d
            java.lang.String r0 = r1.mPackageName     // Catch: android.os.RemoteException -> L35 java.lang.Throwable -> L5d
            r4.registerRouter2(r3, r0)     // Catch: android.os.RemoteException -> L35 java.lang.Throwable -> L5d
            r1.mStub = r3     // Catch: android.os.RemoteException -> L35 java.lang.Throwable -> L5d
            goto L3e
        L35:
            r3 = move-exception
            java.lang.String r4 = "MR2"
            java.lang.String r0 = "registerRouteCallback: Unable to register MediaRouter2."
            android.util.Log.e(r4, r0, r3)     // Catch: java.lang.Throwable -> L5d
        L3e:
            android.media.MediaRouter2$MediaRouter2Stub r3 = r1.mStub     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L5b
            boolean r3 = r1.updateDiscoveryPreferenceIfNeededLocked()     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L5b
            android.media.IMediaRouterService r3 = r1.mMediaRouterService     // Catch: android.os.RemoteException -> L52 java.lang.Throwable -> L5d
            android.media.MediaRouter2$MediaRouter2Stub r4 = r1.mStub     // Catch: android.os.RemoteException -> L52 java.lang.Throwable -> L5d
            android.media.RouteDiscoveryPreference r1 = r1.mDiscoveryPreference     // Catch: android.os.RemoteException -> L52 java.lang.Throwable -> L5d
            r3.setDiscoveryRequestWithRouter2(r4, r1)     // Catch: android.os.RemoteException -> L52 java.lang.Throwable -> L5d
            goto L5b
        L52:
            r1 = move-exception
            java.lang.String r3 = "MR2"
            java.lang.String r4 = "registerRouteCallback: Unable to set discovery request."
            android.util.Log.e(r3, r4, r1)     // Catch: java.lang.Throwable -> L5d
        L5b:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L5d
            return
        L5d:
            r1 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L5d
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaRouter2.registerRouteCallback(java.util.concurrent.Executor, android.media.MediaRouter2$RouteCallback, android.media.RouteDiscoveryPreference):void");
    }

    public void unregisterRouteCallback(RouteCallback routeCallback) {
        Objects.requireNonNull(routeCallback, "callback must not be null");
        if (!this.mRouteCallbackRecords.remove(new RouteCallbackRecord(null, routeCallback, null))) {
            Log.w(TAG, "unregisterRouteCallback: Ignoring unknown callback");
            return;
        }
        synchronized (sRouterLock) {
            if (this.mStub == null) {
                return;
            }
            if (updateDiscoveryPreferenceIfNeededLocked()) {
                try {
                    this.mMediaRouterService.setDiscoveryRequestWithRouter2(this.mStub, this.mDiscoveryPreference);
                } catch (RemoteException e) {
                    Log.e(TAG, "unregisterRouteCallback: Unable to set discovery request.", e);
                }
                if (this.mRouteCallbackRecords.isEmpty() && this.mNonSystemRoutingControllers.isEmpty()) {
                    try {
                        this.mMediaRouterService.unregisterRouter2(this.mStub);
                    } catch (RemoteException e2) {
                        Log.e(TAG, "Unable to unregister media router.", e2);
                    }
                    this.mStub = null;
                }
                this.mShouldUpdateRoutes = true;
                return;
            }
            if (this.mRouteCallbackRecords.isEmpty()) {
                this.mMediaRouterService.unregisterRouter2(this.mStub);
                this.mStub = null;
            }
            this.mShouldUpdateRoutes = true;
            return;
        }
    }

    private boolean updateDiscoveryPreferenceIfNeededLocked() {
        RouteDiscoveryPreference routeDiscoveryPreferenceBuild = new RouteDiscoveryPreference.Builder((Collection<RouteDiscoveryPreference>) this.mRouteCallbackRecords.stream().map(new Function() { // from class: android.media.-$$Lambda$MediaRouter2$mIT98zombFRZmtR8qUN3422UC0Q
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaRouter2.RouteCallbackRecord) obj).mPreference;
            }
        }).collect(Collectors.toList())).build();
        if (Objects.equals(this.mDiscoveryPreference, routeDiscoveryPreferenceBuild)) {
            return false;
        }
        this.mDiscoveryPreference = routeDiscoveryPreferenceBuild;
        this.mShouldUpdateRoutes = true;
        return true;
    }

    public List<MediaRoute2Info> getRoutes() {
        synchronized (sRouterLock) {
            if (this.mShouldUpdateRoutes) {
                this.mShouldUpdateRoutes = false;
                ArrayList arrayList = new ArrayList();
                for (MediaRoute2Info mediaRoute2Info : this.mRoutes.values()) {
                    if (mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                        arrayList.add(mediaRoute2Info);
                    }
                }
                this.mFilteredRoutes = Collections.unmodifiableList(arrayList);
            }
        }
        return this.mFilteredRoutes;
    }

    public void registerTransferCallback(Executor executor, TransferCallback transferCallback) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(transferCallback, "callback must not be null");
        if (this.mTransferCallbackRecords.addIfAbsent(new TransferCallbackRecord(executor, transferCallback))) {
            return;
        }
        Log.w(TAG, "registerTransferCallback: Ignoring the same callback");
    }

    public void unregisterTransferCallback(TransferCallback transferCallback) {
        Objects.requireNonNull(transferCallback, "callback must not be null");
        if (this.mTransferCallbackRecords.remove(new TransferCallbackRecord(null, transferCallback))) {
            return;
        }
        Log.w(TAG, "unregisterTransferCallback: Ignoring an unknown callback");
    }

    public void registerControllerCallback(Executor executor, ControllerCallback controllerCallback) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(controllerCallback, "callback must not be null");
        if (this.mControllerCallbackRecords.addIfAbsent(new ControllerCallbackRecord(executor, controllerCallback))) {
            return;
        }
        Log.w(TAG, "registerControllerCallback: Ignoring the same callback");
    }

    public void unregisterControllerCallback(ControllerCallback controllerCallback) {
        Objects.requireNonNull(controllerCallback, "callback must not be null");
        if (this.mControllerCallbackRecords.remove(new ControllerCallbackRecord(null, controllerCallback))) {
            return;
        }
        Log.w(TAG, "unregisterControllerCallback: Ignoring an unknown callback");
    }

    public void setOnGetControllerHintsListener(OnGetControllerHintsListener onGetControllerHintsListener) {
        this.mOnGetControllerHintsListener = onGetControllerHintsListener;
    }

    public void transferTo(MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        Log.v(TAG, "Transferring to route: " + mediaRoute2Info);
        transfer(getCurrentController(), mediaRoute2Info);
    }

    public void stop() {
        getCurrentController().release();
    }

    void transfer(RoutingController routingController, MediaRoute2Info mediaRoute2Info) {
        boolean zContainsKey;
        Objects.requireNonNull(routingController, "controller must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        synchronized (sRouterLock) {
            zContainsKey = this.mRoutes.containsKey(mediaRoute2Info.getId());
        }
        if (!zContainsKey) {
            notifyTransferFailure(mediaRoute2Info);
        } else if (routingController.getRoutingSessionInfo().getTransferableRoutes().contains(mediaRoute2Info.getId())) {
            routingController.transferToRoute(mediaRoute2Info);
        } else {
            requestCreateController(routingController, mediaRoute2Info, 0L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void requestCreateController(android.media.MediaRouter2.RoutingController r11, android.media.MediaRoute2Info r12, long r13) {
        /*
            r10 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = r10.mNextRequestId
            int r0 = r0.getAndIncrement()
            android.media.MediaRouter2$ControllerCreationRequest r9 = new android.media.MediaRouter2$ControllerCreationRequest
            r1 = r9
            r2 = r0
            r3 = r13
            r5 = r12
            r6 = r11
            r1.<init>(r2, r3, r5, r6)
            java.util.concurrent.CopyOnWriteArrayList<android.media.MediaRouter2$ControllerCreationRequest> r1 = r10.mControllerCreationRequests
            r1.add(r9)
            android.media.MediaRouter2$OnGetControllerHintsListener r1 = r10.mOnGetControllerHintsListener
            if (r1 == 0) goto L26
            android.os.Bundle r1 = r1.onGetControllerHints(r12)
            if (r1 == 0) goto L27
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>(r1)
            r8 = r2
            goto L28
        L26:
            r1 = 0
        L27:
            r8 = r1
        L28:
            java.lang.Object r1 = android.media.MediaRouter2.sRouterLock
            monitor-enter(r1)
            android.media.MediaRouter2$MediaRouter2Stub r2 = r10.mStub     // Catch: java.lang.Throwable -> L54
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L54
            if (r2 == 0) goto L53
            android.media.IMediaRouterService r1 = r10.mMediaRouterService     // Catch: android.os.RemoteException -> L3d
            android.media.RoutingSessionInfo r6 = r11.getRoutingSessionInfo()     // Catch: android.os.RemoteException -> L3d
            r3 = r0
            r4 = r13
            r7 = r12
            r1.requestCreateSessionWithRouter2(r2, r3, r4, r6, r7, r8)     // Catch: android.os.RemoteException -> L3d
            goto L53
        L3d:
            r11 = move-exception
            java.lang.String r0 = "MR2"
            java.lang.String r1 = "createControllerForTransfer: Failed to request for creating a controller."
            android.util.Log.e(r0, r1, r11)
            java.util.concurrent.CopyOnWriteArrayList<android.media.MediaRouter2$ControllerCreationRequest> r11 = r10.mControllerCreationRequests
            r11.remove(r9)
            r0 = 0
            int r11 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r11 != 0) goto L53
            r10.notifyTransferFailure(r12)
        L53:
            return
        L54:
            r10 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L54
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaRouter2.requestCreateController(android.media.MediaRouter2$RoutingController, android.media.MediaRoute2Info, long):void");
    }

    private RoutingController getCurrentController() {
        return getControllers().get(r1.size() - 1);
    }

    public RoutingController getSystemController() {
        return this.mSystemController;
    }

    public List<RoutingController> getControllers() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, this.mSystemController);
        synchronized (sRouterLock) {
            arrayList.addAll(this.mNonSystemRoutingControllers.values());
        }
        return arrayList;
    }

    public void setRouteVolume(MediaRoute2Info mediaRoute2Info, int i) {
        MediaRouter2Stub mediaRouter2Stub;
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        synchronized (sRouterLock) {
            mediaRouter2Stub = this.mStub;
        }
        if (mediaRouter2Stub != null) {
            try {
                this.mMediaRouterService.setRouteVolumeWithRouter2(mediaRouter2Stub, mediaRoute2Info, i);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to set route volume.", e);
            }
        }
    }

    void syncRoutesOnHandler(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) {
        if (list == null || list.isEmpty() || routingSessionInfo == null) {
            Log.e(TAG, "syncRoutesOnHandler: Received wrong data. currentRoutes=" + list + ", currentSystemSessionInfo=" + routingSessionInfo);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        synchronized (sRouterLock) {
            List list2 = (List) list.stream().map($$Lambda$h1yTUs3x4ZC81Y72wBT4ojMDSZw.INSTANCE).collect(Collectors.toList());
            for (String str : this.mRoutes.keySet()) {
                if (!list2.contains(str) && this.mRoutes.get(str).hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    arrayList2.add(this.mRoutes.get(str));
                }
            }
            for (MediaRoute2Info mediaRoute2Info : list) {
                if (this.mRoutes.containsKey(mediaRoute2Info.getId())) {
                    if (!mediaRoute2Info.equals(this.mRoutes.get(mediaRoute2Info.getId())) && mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                        arrayList3.add(mediaRoute2Info);
                    }
                } else if (mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    arrayList.add(mediaRoute2Info);
                }
            }
            this.mRoutes.clear();
            for (MediaRoute2Info mediaRoute2Info2 : list) {
                this.mRoutes.put(mediaRoute2Info2.getId(), mediaRoute2Info2);
            }
            this.mShouldUpdateRoutes = true;
        }
        if (!arrayList.isEmpty()) {
            notifyRoutesAdded(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            notifyRoutesRemoved(arrayList2);
        }
        if (!arrayList3.isEmpty()) {
            notifyRoutesChanged(arrayList3);
        }
        RoutingSessionInfo routingSessionInfo2 = this.mSystemController.getRoutingSessionInfo();
        this.mSystemController.setRoutingSessionInfo(routingSessionInfo);
        if (routingSessionInfo2.equals(routingSessionInfo)) {
            return;
        }
        notifyControllerUpdated(this.mSystemController);
    }

    void addRoutesOnHandler(List<MediaRoute2Info> list) {
        ArrayList arrayList = new ArrayList();
        synchronized (sRouterLock) {
            for (MediaRoute2Info mediaRoute2Info : list) {
                this.mRoutes.put(mediaRoute2Info.getId(), mediaRoute2Info);
                if (mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    arrayList.add(mediaRoute2Info);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        notifyRoutesAdded(arrayList);
    }

    void removeRoutesOnHandler(List<MediaRoute2Info> list) {
        ArrayList arrayList = new ArrayList();
        synchronized (sRouterLock) {
            for (MediaRoute2Info mediaRoute2Info : list) {
                this.mRoutes.remove(mediaRoute2Info.getId());
                if (mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    arrayList.add(mediaRoute2Info);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        notifyRoutesRemoved(arrayList);
    }

    void changeRoutesOnHandler(List<MediaRoute2Info> list) {
        ArrayList arrayList = new ArrayList();
        synchronized (sRouterLock) {
            for (MediaRoute2Info mediaRoute2Info : list) {
                this.mRoutes.put(mediaRoute2Info.getId(), mediaRoute2Info);
                if (mediaRoute2Info.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    arrayList.add(mediaRoute2Info);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        notifyRoutesChanged(arrayList);
    }

    void createControllerOnHandler(int i, RoutingSessionInfo routingSessionInfo) {
        ControllerCreationRequest next;
        RoutingController routingController;
        Iterator<ControllerCreationRequest> it = this.mControllerCreationRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.mRequestId == i) {
                    break;
                }
            }
        }
        if (next == null) {
            Log.w(TAG, "createControllerOnHandler: Ignoring an unknown request.");
            return;
        }
        this.mControllerCreationRequests.remove(next);
        MediaRoute2Info mediaRoute2Info = next.mRoute;
        if (routingSessionInfo == null) {
            notifyTransferFailure(mediaRoute2Info);
            return;
        }
        if (!TextUtils.equals(mediaRoute2Info.getProviderId(), routingSessionInfo.getProviderId())) {
            Log.w(TAG, "The session's provider ID does not match the requested route's. (requested route's providerId=" + mediaRoute2Info.getProviderId() + ", actual providerId=" + routingSessionInfo.getProviderId() + ")");
            notifyTransferFailure(mediaRoute2Info);
            return;
        }
        RoutingController routingController2 = next.mOldController;
        if (!routingController2.scheduleRelease()) {
            Log.w(TAG, "createControllerOnHandler: Ignoring controller creation for released old controller. oldController=" + routingController2);
            if (!routingSessionInfo.isSystemSession()) {
                new RoutingController(routingSessionInfo).release();
            }
            notifyTransferFailure(mediaRoute2Info);
            return;
        }
        if (routingSessionInfo.isSystemSession()) {
            routingController = getSystemController();
            routingController.setRoutingSessionInfo(routingSessionInfo);
        } else {
            routingController = new RoutingController(routingSessionInfo);
            synchronized (sRouterLock) {
                this.mNonSystemRoutingControllers.put(routingController.getId(), routingController);
            }
        }
        notifyTransfer(routingController2, routingController);
    }

    void updateControllerOnHandler(RoutingSessionInfo routingSessionInfo) {
        RoutingController routingController;
        if (routingSessionInfo == null) {
            Log.w(TAG, "updateControllerOnHandler: Ignoring null sessionInfo.");
            return;
        }
        if (routingSessionInfo.isSystemSession()) {
            RoutingController systemController = getSystemController();
            systemController.setRoutingSessionInfo(routingSessionInfo);
            notifyControllerUpdated(systemController);
            return;
        }
        synchronized (sRouterLock) {
            routingController = this.mNonSystemRoutingControllers.get(routingSessionInfo.getId());
        }
        if (routingController == null) {
            Log.w(TAG, "updateControllerOnHandler: Matching controller not found. uniqueSessionId=" + routingSessionInfo.getId());
            return;
        }
        RoutingSessionInfo routingSessionInfo2 = routingController.getRoutingSessionInfo();
        if (!TextUtils.equals(routingSessionInfo2.getProviderId(), routingSessionInfo.getProviderId())) {
            Log.w(TAG, "updateControllerOnHandler: Provider IDs are not matched. old=" + routingSessionInfo2.getProviderId() + ", new=" + routingSessionInfo.getProviderId());
        } else {
            routingController.setRoutingSessionInfo(routingSessionInfo);
            notifyControllerUpdated(routingController);
        }
    }

    void releaseControllerOnHandler(RoutingSessionInfo routingSessionInfo) {
        RoutingController routingController;
        if (routingSessionInfo == null) {
            Log.w(TAG, "releaseControllerOnHandler: Ignoring null sessionInfo.");
            return;
        }
        synchronized (sRouterLock) {
            routingController = this.mNonSystemRoutingControllers.get(routingSessionInfo.getId());
        }
        if (routingController == null) {
            if (DEBUG) {
                Log.d(TAG, "releaseControllerOnHandler: Matching controller not found. uniqueSessionId=" + routingSessionInfo.getId());
            }
        } else {
            RoutingSessionInfo routingSessionInfo2 = routingController.getRoutingSessionInfo();
            if (!TextUtils.equals(routingSessionInfo2.getProviderId(), routingSessionInfo.getProviderId())) {
                Log.w(TAG, "releaseControllerOnHandler: Provider IDs are not matched. old=" + routingSessionInfo2.getProviderId() + ", new=" + routingSessionInfo.getProviderId());
            } else {
                routingController.releaseInternal(false);
            }
        }
    }

    void onRequestCreateControllerByManagerOnHandler(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info, long j) {
        RoutingController systemController;
        if (routingSessionInfo.isSystemSession()) {
            systemController = getSystemController();
        } else {
            synchronized (sRouterLock) {
                systemController = this.mNonSystemRoutingControllers.get(routingSessionInfo.getId());
            }
        }
        if (systemController == null) {
            return;
        }
        requestCreateController(systemController, mediaRoute2Info, j);
    }

    private List<MediaRoute2Info> filterRoutes(List<MediaRoute2Info> list, final RouteDiscoveryPreference routeDiscoveryPreference) {
        return (List) list.stream().filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2$-n_9YMjz-DiNbkGVy5mWYIh6im0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MediaRoute2Info) obj).hasAnyFeatures(routeDiscoveryPreference.getPreferredFeatures());
            }
        }).collect(Collectors.toList());
    }

    private void notifyRoutesAdded(List<MediaRoute2Info> list) {
        for (final RouteCallbackRecord routeCallbackRecord : this.mRouteCallbackRecords) {
            final List<MediaRoute2Info> listFilterRoutes = filterRoutes(list, routeCallbackRecord.mPreference);
            if (!listFilterRoutes.isEmpty()) {
                routeCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$v8tcF_CI0Ckh2OXwPJfsq_Llrhc
                    @Override // java.lang.Runnable
                    public final void run() {
                        routeCallbackRecord.mRouteCallback.onRoutesAdded(listFilterRoutes);
                    }
                });
            }
        }
    }

    private void notifyRoutesRemoved(List<MediaRoute2Info> list) {
        for (final RouteCallbackRecord routeCallbackRecord : this.mRouteCallbackRecords) {
            final List<MediaRoute2Info> listFilterRoutes = filterRoutes(list, routeCallbackRecord.mPreference);
            if (!listFilterRoutes.isEmpty()) {
                routeCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$aQlcYl_uvkHo6riXlQqOnF1mmJ8
                    @Override // java.lang.Runnable
                    public final void run() {
                        routeCallbackRecord.mRouteCallback.onRoutesRemoved(listFilterRoutes);
                    }
                });
            }
        }
    }

    private void notifyRoutesChanged(List<MediaRoute2Info> list) {
        for (final RouteCallbackRecord routeCallbackRecord : this.mRouteCallbackRecords) {
            final List<MediaRoute2Info> listFilterRoutes = filterRoutes(list, routeCallbackRecord.mPreference);
            if (!listFilterRoutes.isEmpty()) {
                routeCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$9wbCMJcdOiWv2zRaZy3bI6YO0EE
                    @Override // java.lang.Runnable
                    public final void run() {
                        routeCallbackRecord.mRouteCallback.onRoutesChanged(listFilterRoutes);
                    }
                });
            }
        }
    }

    private void notifyTransfer(final RoutingController routingController, final RoutingController routingController2) {
        for (final TransferCallbackRecord transferCallbackRecord : this.mTransferCallbackRecords) {
            transferCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$pi-qOCMee2zlCLhXaGrtoZ--NpY
                @Override // java.lang.Runnable
                public final void run() {
                    transferCallbackRecord.mTransferCallback.onTransfer(routingController, routingController2);
                }
            });
        }
    }

    private void notifyTransferFailure(final MediaRoute2Info mediaRoute2Info) {
        for (final TransferCallbackRecord transferCallbackRecord : this.mTransferCallbackRecords) {
            transferCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$iMZIl6O_7t87nIuHvVYPuzkkvas
                @Override // java.lang.Runnable
                public final void run() {
                    transferCallbackRecord.mTransferCallback.onTransferFailure(mediaRoute2Info);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStop(final RoutingController routingController) {
        for (final TransferCallbackRecord transferCallbackRecord : this.mTransferCallbackRecords) {
            transferCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$Z0Uxve9CS2dRdqEMkkdVSxjPvYU
                @Override // java.lang.Runnable
                public final void run() {
                    transferCallbackRecord.mTransferCallback.onStop(routingController);
                }
            });
        }
    }

    private void notifyControllerUpdated(final RoutingController routingController) {
        for (final ControllerCallbackRecord controllerCallbackRecord : this.mControllerCallbackRecords) {
            controllerCallbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2$iGxlxmYU5v2QmTkPacsUg_M1A3U
                @Override // java.lang.Runnable
                public final void run() {
                    controllerCallbackRecord.mCallback.onControllerUpdated(routingController);
                }
            });
        }
    }

    public class RoutingController {
        private static final int CONTROLLER_STATE_ACTIVE = 1;
        private static final int CONTROLLER_STATE_RELEASED = 3;
        private static final int CONTROLLER_STATE_RELEASING = 2;
        private static final int CONTROLLER_STATE_UNKNOWN = 0;
        private RoutingSessionInfo mSessionInfo;
        private final Object mControllerLock = new Object();
        private int mState = 1;

        RoutingController(RoutingSessionInfo routingSessionInfo) {
            this.mSessionInfo = routingSessionInfo;
        }

        public String getId() {
            String id;
            synchronized (this.mControllerLock) {
                id = this.mSessionInfo.getId();
            }
            return id;
        }

        public String getOriginalId() {
            String originalId;
            synchronized (this.mControllerLock) {
                originalId = this.mSessionInfo.getOriginalId();
            }
            return originalId;
        }

        public Bundle getControlHints() {
            Bundle controlHints;
            synchronized (this.mControllerLock) {
                controlHints = this.mSessionInfo.getControlHints();
            }
            return controlHints;
        }

        public List<MediaRoute2Info> getSelectedRoutes() {
            List<String> selectedRoutes;
            synchronized (this.mControllerLock) {
                selectedRoutes = this.mSessionInfo.getSelectedRoutes();
            }
            return getRoutesWithIds(selectedRoutes);
        }

        public List<MediaRoute2Info> getSelectableRoutes() {
            List<String> selectableRoutes;
            synchronized (this.mControllerLock) {
                selectableRoutes = this.mSessionInfo.getSelectableRoutes();
            }
            return getRoutesWithIds(selectableRoutes);
        }

        public List<MediaRoute2Info> getDeselectableRoutes() {
            List<String> deselectableRoutes;
            synchronized (this.mControllerLock) {
                deselectableRoutes = this.mSessionInfo.getDeselectableRoutes();
            }
            return getRoutesWithIds(deselectableRoutes);
        }

        public int getVolumeHandling() {
            int volumeHandling;
            synchronized (this.mControllerLock) {
                volumeHandling = this.mSessionInfo.getVolumeHandling();
            }
            return volumeHandling;
        }

        public int getVolumeMax() {
            int volumeMax;
            synchronized (this.mControllerLock) {
                volumeMax = this.mSessionInfo.getVolumeMax();
            }
            return volumeMax;
        }

        public int getVolume() {
            int volume;
            synchronized (this.mControllerLock) {
                volume = this.mSessionInfo.getVolume();
            }
            return volume;
        }

        public boolean isReleased() {
            boolean z;
            synchronized (this.mControllerLock) {
                z = this.mState == 3;
            }
            return z;
        }

        public void selectRoute(MediaRoute2Info mediaRoute2Info) {
            MediaRouter2Stub mediaRouter2Stub;
            Objects.requireNonNull(mediaRoute2Info, "route must not be null");
            if (isReleased()) {
                Log.w(MediaRouter2.TAG, "selectRoute: Called on released controller. Ignoring.");
                return;
            }
            if (MediaRouter2.checkRouteListContainsRouteId(getSelectedRoutes(), mediaRoute2Info.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring selecting a route that is already selected. route=" + mediaRoute2Info);
                return;
            }
            if (MediaRouter2.checkRouteListContainsRouteId(getSelectableRoutes(), mediaRoute2Info.getId())) {
                synchronized (MediaRouter2.sRouterLock) {
                    mediaRouter2Stub = MediaRouter2.this.mStub;
                }
                if (mediaRouter2Stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.selectRouteWithRouter2(mediaRouter2Stub, getId(), mediaRoute2Info);
                        return;
                    } catch (RemoteException e) {
                        Log.e(MediaRouter2.TAG, "Unable to select route for session.", e);
                        return;
                    }
                }
                return;
            }
            Log.w(MediaRouter2.TAG, "Ignoring selecting a non-selectable route=" + mediaRoute2Info);
        }

        public void deselectRoute(MediaRoute2Info mediaRoute2Info) {
            MediaRouter2Stub mediaRouter2Stub;
            Objects.requireNonNull(mediaRoute2Info, "route must not be null");
            if (isReleased()) {
                Log.w(MediaRouter2.TAG, "deselectRoute: called on released controller. Ignoring.");
                return;
            }
            if (!MediaRouter2.checkRouteListContainsRouteId(getSelectedRoutes(), mediaRoute2Info.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring deselecting a route that is not selected. route=" + mediaRoute2Info);
                return;
            }
            if (MediaRouter2.checkRouteListContainsRouteId(getDeselectableRoutes(), mediaRoute2Info.getId())) {
                synchronized (MediaRouter2.sRouterLock) {
                    mediaRouter2Stub = MediaRouter2.this.mStub;
                }
                if (mediaRouter2Stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.deselectRouteWithRouter2(mediaRouter2Stub, getId(), mediaRoute2Info);
                        return;
                    } catch (RemoteException e) {
                        Log.e(MediaRouter2.TAG, "Unable to deselect route from session.", e);
                        return;
                    }
                }
                return;
            }
            Log.w(MediaRouter2.TAG, "Ignoring deselecting a non-deselectable route=" + mediaRoute2Info);
        }

        void transferToRoute(MediaRoute2Info mediaRoute2Info) {
            MediaRouter2Stub mediaRouter2Stub;
            Objects.requireNonNull(mediaRoute2Info, "route must not be null");
            synchronized (this.mControllerLock) {
                if (isReleased()) {
                    Log.w(MediaRouter2.TAG, "transferToRoute: Called on released controller. Ignoring.");
                    return;
                }
                if (this.mSessionInfo.getTransferableRoutes().contains(mediaRoute2Info.getId())) {
                    synchronized (MediaRouter2.sRouterLock) {
                        mediaRouter2Stub = MediaRouter2.this.mStub;
                    }
                    if (mediaRouter2Stub != null) {
                        try {
                            MediaRouter2.this.mMediaRouterService.transferToRouteWithRouter2(mediaRouter2Stub, getId(), mediaRoute2Info);
                            return;
                        } catch (RemoteException e) {
                            Log.e(MediaRouter2.TAG, "Unable to transfer to route for session.", e);
                            return;
                        }
                    }
                    return;
                }
                Log.w(MediaRouter2.TAG, "Ignoring transferring to a non-transferable route=" + mediaRoute2Info);
            }
        }

        public void setVolume(int i) {
            MediaRouter2Stub mediaRouter2Stub;
            if (getVolumeHandling() == 0) {
                Log.w(MediaRouter2.TAG, "setVolume: The routing session has fixed volume. Ignoring.");
                return;
            }
            if (i < 0 || i > getVolumeMax()) {
                Log.w(MediaRouter2.TAG, "setVolume: The target volume is out of range. Ignoring");
                return;
            }
            if (!isReleased()) {
                synchronized (MediaRouter2.sRouterLock) {
                    mediaRouter2Stub = MediaRouter2.this.mStub;
                }
                if (mediaRouter2Stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.setSessionVolumeWithRouter2(mediaRouter2Stub, getId(), i);
                        return;
                    } catch (RemoteException e) {
                        Log.e(MediaRouter2.TAG, "setVolume: Failed to deliver request.", e);
                        return;
                    }
                }
                return;
            }
            Log.w(MediaRouter2.TAG, "setVolume: Called on released controller. Ignoring.");
        }

        public void release() {
            releaseInternal(true);
        }

        boolean scheduleRelease() {
            synchronized (this.mControllerLock) {
                if (this.mState != 1) {
                    return false;
                }
                this.mState = 2;
                synchronized (MediaRouter2.sRouterLock) {
                    if (!MediaRouter2.this.mNonSystemRoutingControllers.remove(getId(), this)) {
                        return true;
                    }
                    MediaRouter2.this.mHandler.postDelayed(new Runnable() { // from class: android.media.-$$Lambda$DyTQkidTsJoO5Bwuv81qQgvyPJk
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.release();
                        }
                    }, JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
                    return true;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0056 A[Catch: all -> 0x00a1, TryCatch #1 {, blocks: (B:19:0x0025, B:21:0x0034, B:23:0x003a, B:26:0x004d, B:28:0x0056, B:29:0x0065, B:31:0x0071, B:33:0x007d, B:35:0x0083, B:39:0x009a, B:38:0x0092, B:40:0x009f), top: B:50:0x0025, inners: #0, #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x009f A[Catch: all -> 0x00a1, DONT_GENERATE, TryCatch #1 {, blocks: (B:19:0x0025, B:21:0x0034, B:23:0x003a, B:26:0x004d, B:28:0x0056, B:29:0x0065, B:31:0x0071, B:33:0x007d, B:35:0x0083, B:39:0x009a, B:38:0x0092, B:40:0x009f), top: B:50:0x0025, inners: #0, #3 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void releaseInternal(boolean r5) {
            /*
                r4 = this;
                java.lang.Object r0 = r4.mControllerLock
                monitor-enter(r0)
                int r1 = r4.mState     // Catch: java.lang.Throwable -> La4
                r2 = 3
                if (r1 != r2) goto L18
                boolean r4 = android.media.MediaRouter2.access$300()     // Catch: java.lang.Throwable -> La4
                if (r4 == 0) goto L16
                java.lang.String r4 = "MR2"
                java.lang.String r5 = "releaseInternal: Called on released controller. Ignoring."
                android.util.Log.d(r4, r5)     // Catch: java.lang.Throwable -> La4
            L16:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
                return
            L18:
                r3 = 1
                if (r1 != r3) goto L1c
                goto L1d
            L1c:
                r3 = 0
            L1d:
                r4.mState = r2     // Catch: java.lang.Throwable -> La4
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
                java.lang.Object r1 = android.media.MediaRouter2.access$000()
                monitor-enter(r1)
                android.media.MediaRouter2 r0 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                java.util.Map r0 = android.media.MediaRouter2.access$200(r0)     // Catch: java.lang.Throwable -> La1
                java.lang.String r2 = r4.getId()     // Catch: java.lang.Throwable -> La1
                r0.remove(r2, r4)     // Catch: java.lang.Throwable -> La1
                if (r5 == 0) goto L54
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                android.media.MediaRouter2$MediaRouter2Stub r5 = r5.mStub     // Catch: java.lang.Throwable -> La1
                if (r5 == 0) goto L54
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                android.media.IMediaRouterService r5 = android.media.MediaRouter2.access$100(r5)     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                android.media.MediaRouter2 r0 = android.media.MediaRouter2.this     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                android.media.MediaRouter2$MediaRouter2Stub r0 = r0.mStub     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                java.lang.String r2 = r4.getId()     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                r5.releaseSessionWithRouter2(r0, r2)     // Catch: android.os.RemoteException -> L4c java.lang.Throwable -> La1
                goto L54
            L4c:
                r5 = move-exception
                java.lang.String r0 = "MR2"
                java.lang.String r2 = "Unable to release session"
                android.util.Log.e(r0, r2, r5)     // Catch: java.lang.Throwable -> La1
            L54:
                if (r3 == 0) goto L65
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                android.os.Handler r5 = r5.mHandler     // Catch: java.lang.Throwable -> La1
                android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg r0 = new java.util.function.BiConsumer() { // from class: android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg
                    static {
                        /*
                            android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg r0 = new android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg) android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg.INSTANCE android.media.-$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.media.$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.media.$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg.<init>():void");
                    }

                    @Override // java.util.function.BiConsumer
                    public final void accept(java.lang.Object r1, java.lang.Object r2) {
                        /*
                            r0 = this;
                            android.media.MediaRouter2 r1 = (android.media.MediaRouter2) r1
                            android.media.MediaRouter2$RoutingController r2 = (android.media.MediaRouter2.RoutingController) r2
                            android.media.MediaRouter2.RoutingController.lambda$releaseInternal$0(r1, r2)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.media.$$Lambda$MediaRouter2$RoutingController$Gpp_Ntqi92i1YOO8CKLCdesaTIg.accept(java.lang.Object, java.lang.Object):void");
                    }
                }     // Catch: java.lang.Throwable -> La1
                android.media.MediaRouter2 r2 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                android.os.Message r0 = com.android.internal.util.function.pooled.PooledLambda.obtainMessage(r0, r2, r4)     // Catch: java.lang.Throwable -> La1
                r5.sendMessage(r0)     // Catch: java.lang.Throwable -> La1
            L65:
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                java.util.concurrent.CopyOnWriteArrayList r5 = android.media.MediaRouter2.access$400(r5)     // Catch: java.lang.Throwable -> La1
                boolean r5 = r5.isEmpty()     // Catch: java.lang.Throwable -> La1
                if (r5 == 0) goto L9f
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                java.util.Map r5 = android.media.MediaRouter2.access$200(r5)     // Catch: java.lang.Throwable -> La1
                boolean r5 = r5.isEmpty()     // Catch: java.lang.Throwable -> La1
                if (r5 == 0) goto L9f
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                android.media.MediaRouter2$MediaRouter2Stub r5 = r5.mStub     // Catch: java.lang.Throwable -> La1
                if (r5 == 0) goto L9f
                android.media.MediaRouter2 r5 = android.media.MediaRouter2.this     // Catch: android.os.RemoteException -> L91 java.lang.Throwable -> La1
                android.media.IMediaRouterService r5 = android.media.MediaRouter2.access$100(r5)     // Catch: android.os.RemoteException -> L91 java.lang.Throwable -> La1
                android.media.MediaRouter2 r0 = android.media.MediaRouter2.this     // Catch: android.os.RemoteException -> L91 java.lang.Throwable -> La1
                android.media.MediaRouter2$MediaRouter2Stub r0 = r0.mStub     // Catch: android.os.RemoteException -> L91 java.lang.Throwable -> La1
                r5.unregisterRouter2(r0)     // Catch: android.os.RemoteException -> L91 java.lang.Throwable -> La1
                goto L9a
            L91:
                r5 = move-exception
                java.lang.String r0 = "MR2"
                java.lang.String r2 = "releaseInternal: Unable to unregister media router."
                android.util.Log.e(r0, r2, r5)     // Catch: java.lang.Throwable -> La1
            L9a:
                android.media.MediaRouter2 r4 = android.media.MediaRouter2.this     // Catch: java.lang.Throwable -> La1
                r5 = 0
                r4.mStub = r5     // Catch: java.lang.Throwable -> La1
            L9f:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> La1
                return
            La1:
                r4 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> La1
                throw r4
            La4:
                r4 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.MediaRouter2.RoutingController.releaseInternal(boolean):void");
        }

        public String toString() {
            return "RoutingController{ id=" + getId() + ", selectedRoutes={" + ((List) getSelectedRoutes().stream().map($$Lambda$h1yTUs3x4ZC81Y72wBT4ojMDSZw.INSTANCE).collect(Collectors.toList())) + "}, selectableRoutes={" + ((List) getSelectableRoutes().stream().map($$Lambda$h1yTUs3x4ZC81Y72wBT4ojMDSZw.INSTANCE).collect(Collectors.toList())) + "}, deselectableRoutes={" + ((List) getDeselectableRoutes().stream().map($$Lambda$h1yTUs3x4ZC81Y72wBT4ojMDSZw.INSTANCE).collect(Collectors.toList())) + "} }";
        }

        RoutingSessionInfo getRoutingSessionInfo() {
            RoutingSessionInfo routingSessionInfo;
            synchronized (this.mControllerLock) {
                routingSessionInfo = this.mSessionInfo;
            }
            return routingSessionInfo;
        }

        void setRoutingSessionInfo(RoutingSessionInfo routingSessionInfo) {
            synchronized (this.mControllerLock) {
                this.mSessionInfo = routingSessionInfo;
            }
        }

        private List<MediaRoute2Info> getRoutesWithIds(List<String> list) {
            List<MediaRoute2Info> list2;
            synchronized (MediaRouter2.sRouterLock) {
                Stream<String> stream = list.stream();
                Map<String, MediaRoute2Info> map = MediaRouter2.this.mRoutes;
                Objects.requireNonNull(map);
                list2 = (List) stream.map(new $$Lambda$Rv_QxUUu7BNGUhumIPDVokO3js(map)).filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2$RoutingController$nIHiiLCAnCUtoRKR-PHG5llkexw
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Objects.nonNull((MediaRoute2Info) obj);
                    }
                }).collect(Collectors.toList());
            }
            return list2;
        }
    }

    class SystemRoutingController extends RoutingController {
        @Override // android.media.MediaRouter2.RoutingController
        public boolean isReleased() {
            return false;
        }

        @Override // android.media.MediaRouter2.RoutingController
        void releaseInternal(boolean z) {
        }

        @Override // android.media.MediaRouter2.RoutingController
        boolean scheduleRelease() {
            return true;
        }

        SystemRoutingController(RoutingSessionInfo routingSessionInfo) {
            super(routingSessionInfo);
        }
    }

    static final class RouteCallbackRecord {
        public final Executor mExecutor;
        public final RouteDiscoveryPreference mPreference;
        public final RouteCallback mRouteCallback;

        RouteCallbackRecord(Executor executor, RouteCallback routeCallback, RouteDiscoveryPreference routeDiscoveryPreference) {
            this.mRouteCallback = routeCallback;
            this.mExecutor = executor;
            this.mPreference = routeDiscoveryPreference;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RouteCallbackRecord) && this.mRouteCallback == ((RouteCallbackRecord) obj).mRouteCallback;
        }

        public int hashCode() {
            return this.mRouteCallback.hashCode();
        }
    }

    static final class TransferCallbackRecord {
        public final Executor mExecutor;
        public final TransferCallback mTransferCallback;

        TransferCallbackRecord(Executor executor, TransferCallback transferCallback) {
            this.mTransferCallback = transferCallback;
            this.mExecutor = executor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TransferCallbackRecord) && this.mTransferCallback == ((TransferCallbackRecord) obj).mTransferCallback;
        }

        public int hashCode() {
            return this.mTransferCallback.hashCode();
        }
    }

    static final class ControllerCallbackRecord {
        public final ControllerCallback mCallback;
        public final Executor mExecutor;

        ControllerCallbackRecord(Executor executor, ControllerCallback controllerCallback) {
            this.mCallback = controllerCallback;
            this.mExecutor = executor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ControllerCallbackRecord) && this.mCallback == ((ControllerCallbackRecord) obj).mCallback;
        }

        public int hashCode() {
            return this.mCallback.hashCode();
        }
    }

    static final class ControllerCreationRequest {
        public final long mManagerRequestId;
        public final RoutingController mOldController;
        public final int mRequestId;
        public final MediaRoute2Info mRoute;

        ControllerCreationRequest(int i, long j, MediaRoute2Info mediaRoute2Info, RoutingController routingController) {
            this.mRequestId = i;
            this.mManagerRequestId = j;
            this.mRoute = (MediaRoute2Info) Objects.requireNonNull(mediaRoute2Info, "route must not be null");
            this.mOldController = (RoutingController) Objects.requireNonNull(routingController, "oldController must not be null");
        }
    }

    class MediaRouter2Stub extends IMediaRouter2.Stub {
        MediaRouter2Stub() {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRouterRegistered(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$CksL-jqEi-hiTblmaoYsF9RFNhI
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2) obj).syncRoutesOnHandler((List) obj2, (RoutingSessionInfo) obj3);
                }
            }, MediaRouter2.this, list, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesAdded(List<MediaRoute2Info> list) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$GDBl5-gL9MC1zwT0l4hm1g1GlJo
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2) obj).addRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2.this, list));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesRemoved(List<MediaRoute2Info> list) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$6Ui53eddfJioIlmC9jTvZLCP4Ko
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2) obj).removeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2.this, list));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesChanged(List<MediaRoute2Info> list) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$0btpQ_NfA-kmIzCaGLbKPR87T_w
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2) obj).changeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2.this, list));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$kIJH4VUCrLAMHcwXoMGS-kbeDl4
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2) obj).createControllerOnHandler(((Integer) obj2).intValue(), (RoutingSessionInfo) obj3);
                }
            }, MediaRouter2.this, Integer.valueOf(i), routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionInfoChanged(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$YjxB9j-bS28EDSuVrQ3v9fXoVMc
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2) obj).updateControllerOnHandler((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$TlLmIl-4wt3zS8IXSW8PyUZsJ3c
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2) obj).releaseControllerOnHandler((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void requestCreateSessionByManager(long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$QGfbdwwtEt7IcjeFf2YR9JBsZL0
                @Override // com.android.internal.util.function.QuadConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                    ((MediaRouter2) obj).onRequestCreateControllerByManagerOnHandler((RoutingSessionInfo) obj2, (MediaRoute2Info) obj3, ((Long) obj4).longValue());
                }
            }, MediaRouter2.this, routingSessionInfo, mediaRoute2Info, Long.valueOf(j)));
        }
    }
}
