package android.media;

import android.app.job.JobInfo;
import android.content.Context;
import android.media.IMediaRouter2Manager;
import android.media.IMediaRouterService;
import android.media.MediaRouter2Manager;
import android.media.RoutingSessionInfo;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaRouter2Manager {
    public static final int REQUEST_ID_NONE = 0;
    private static final String TAG = "MR2Manager";
    public static final int TRANSFER_TIMEOUT_MS = 30000;
    private static MediaRouter2Manager sInstance;
    private static final Object sLock = new Object();
    private Client mClient;
    private final Context mContext;
    final Handler mHandler;
    private final IMediaRouterService mMediaRouterService;
    private final MediaSessionManager mMediaSessionManager;
    final String mPackageName;
    final CopyOnWriteArrayList<CallbackRecord> mCallbackRecords = new CopyOnWriteArrayList<>();
    private final Object mRoutesLock = new Object();
    private final Map<String, MediaRoute2Info> mRoutes = new HashMap();
    final ConcurrentMap<String, List<String>> mPreferredFeaturesMap = new ConcurrentHashMap();
    private final AtomicInteger mNextRequestId = new AtomicInteger(1);
    private final CopyOnWriteArrayList<TransferRequest> mTransferRequests = new CopyOnWriteArrayList<>();

    public static class Callback {
        public void onPreferredFeaturesChanged(String str, List<String> list) {
        }

        public void onRequestFailed(int i) {
        }

        public void onRoutesAdded(List<MediaRoute2Info> list) {
        }

        public void onRoutesChanged(List<MediaRoute2Info> list) {
        }

        public void onRoutesRemoved(List<MediaRoute2Info> list) {
        }

        public void onSessionReleased(RoutingSessionInfo routingSessionInfo) {
        }

        public void onSessionUpdated(RoutingSessionInfo routingSessionInfo) {
        }

        public void onTransferFailed(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        }

        public void onTransferred(RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
        }
    }

    public static MediaRouter2Manager getInstance(Context context) {
        MediaRouter2Manager mediaRouter2Manager;
        Objects.requireNonNull(context, "context must not be null");
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new MediaRouter2Manager(context);
            }
            mediaRouter2Manager = sInstance;
        }
        return mediaRouter2Manager;
    }

    private MediaRouter2Manager(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mMediaRouterService = IMediaRouterService.Stub.asInterface(ServiceManager.getService(Context.MEDIA_ROUTER_SERVICE));
        this.mMediaSessionManager = (MediaSessionManager) context.getSystemService(Context.MEDIA_SESSION_SERVICE);
        this.mPackageName = applicationContext.getPackageName();
        Handler handler = new Handler(context.getMainLooper());
        this.mHandler = handler;
        handler.post(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$6CaLroIN47dcx97REg4--u3cVRg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.getOrCreateClient();
            }
        });
    }

    public void registerCallback(Executor executor, Callback callback) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(callback, "callback must not be null");
        if (this.mCallbackRecords.addIfAbsent(new CallbackRecord(executor, callback))) {
            return;
        }
        Log.w(TAG, "Ignoring to register the same callback twice.");
    }

    public void unregisterCallback(Callback callback) {
        Objects.requireNonNull(callback, "callback must not be null");
        if (this.mCallbackRecords.remove(new CallbackRecord(null, callback))) {
            return;
        }
        Log.w(TAG, "unregisterCallback: Ignore unknown callback. " + callback);
    }

    public void startScan() {
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.startScan(orCreateClient);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to get sessions. Service probably died.", e);
            }
        }
    }

    public void stopScan() {
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.stopScan(orCreateClient);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to get sessions. Service probably died.", e);
            }
        }
    }

    public MediaController getMediaControllerForRoutingSession(RoutingSessionInfo routingSessionInfo) {
        for (MediaController mediaController : this.mMediaSessionManager.getActiveSessions(null)) {
            if (areSessionsMatched(mediaController, routingSessionInfo)) {
                return mediaController;
            }
        }
        return null;
    }

    public List<MediaRoute2Info> getAvailableRoutes(String str) {
        Objects.requireNonNull(str, "packageName must not be null");
        return getAvailableRoutesForRoutingSession(getRoutingSessions(str).get(r2.size() - 1));
    }

    public List<MediaRoute2Info> getAvailableRoutesForRoutingSession(RoutingSessionInfo routingSessionInfo) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        ArrayList arrayList = new ArrayList();
        List<String> listEmptyList = this.mPreferredFeaturesMap.get(routingSessionInfo.getClientPackageName());
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        synchronized (this.mRoutesLock) {
            for (MediaRoute2Info mediaRoute2Info : this.mRoutes.values()) {
                if (mediaRoute2Info.hasAnyFeatures(listEmptyList) || routingSessionInfo.getSelectedRoutes().contains(mediaRoute2Info.getId()) || routingSessionInfo.getTransferableRoutes().contains(mediaRoute2Info.getId())) {
                    arrayList.add(mediaRoute2Info);
                }
            }
        }
        return arrayList;
    }

    public RoutingSessionInfo getSystemRoutingSession() {
        for (RoutingSessionInfo routingSessionInfo : getActiveSessions()) {
            if (routingSessionInfo.isSystemSession()) {
                return routingSessionInfo;
            }
        }
        throw new IllegalStateException("No system routing session");
    }

    public RoutingSessionInfo getRoutingSessionForMediaController(MediaController mediaController) {
        MediaController.PlaybackInfo playbackInfo = mediaController.getPlaybackInfo();
        if (playbackInfo == null) {
            return null;
        }
        if (playbackInfo.getPlaybackType() == 1) {
            return new RoutingSessionInfo.Builder(getSystemRoutingSession()).setClientPackageName(mediaController.getPackageName()).build();
        }
        for (RoutingSessionInfo routingSessionInfo : getActiveSessions()) {
            if (!routingSessionInfo.isSystemSession() && areSessionsMatched(mediaController, routingSessionInfo)) {
                return routingSessionInfo;
            }
        }
        return null;
    }

    public List<RoutingSessionInfo> getRoutingSessions(String str) {
        Objects.requireNonNull(str, "packageName must not be null");
        ArrayList arrayList = new ArrayList();
        for (RoutingSessionInfo routingSessionInfo : getActiveSessions()) {
            if (routingSessionInfo.isSystemSession()) {
                arrayList.add(new RoutingSessionInfo.Builder(routingSessionInfo).setClientPackageName(str).build());
            } else if (TextUtils.equals(routingSessionInfo.getClientPackageName(), str)) {
                arrayList.add(routingSessionInfo);
            }
        }
        return arrayList;
    }

    public List<RoutingSessionInfo> getActiveSessions() {
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                return this.mMediaRouterService.getActiveSessions(orCreateClient);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to get sessions. Service probably died.", e);
            }
        }
        return Collections.emptyList();
    }

    public List<MediaRoute2Info> getAllRoutes() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mRoutesLock) {
            arrayList.addAll(this.mRoutes.values());
        }
        return arrayList;
    }

    public void selectRoute(String str, MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(str, "packageName must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        Log.v(TAG, "Selecting route. packageName= " + str + ", route=" + mediaRoute2Info);
        transfer(getRoutingSessions(str).get(r3.size() - 1), mediaRoute2Info);
    }

    public void transfer(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        Log.v(TAG, "Transferring routing session. session= " + routingSessionInfo + ", route=" + mediaRoute2Info);
        synchronized (this.mRoutesLock) {
            if (!this.mRoutes.containsKey(mediaRoute2Info.getId())) {
                Log.w(TAG, "transfer: Ignoring an unknown route id=" + mediaRoute2Info.getId());
                notifyTransferFailed(routingSessionInfo, mediaRoute2Info);
            } else if (routingSessionInfo.getTransferableRoutes().contains(mediaRoute2Info.getId())) {
                transferToRoute(routingSessionInfo, mediaRoute2Info);
            } else {
                requestCreateSession(routingSessionInfo, mediaRoute2Info);
            }
        }
    }

    public void setRouteVolume(MediaRoute2Info mediaRoute2Info, int i) {
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        if (mediaRoute2Info.getVolumeHandling() == 0) {
            Log.w(TAG, "setRouteVolume: the route has fixed volume. Ignoring.");
            return;
        }
        if (i < 0 || i > mediaRoute2Info.getVolumeMax()) {
            Log.w(TAG, "setRouteVolume: the target volume is out of range. Ignoring");
            return;
        }
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.setRouteVolumeWithManager(orCreateClient, this.mNextRequestId.getAndIncrement(), mediaRoute2Info, i);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to set route volume.", e);
            }
        }
    }

    public void setSessionVolume(RoutingSessionInfo routingSessionInfo, int i) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        if (routingSessionInfo.getVolumeHandling() == 0) {
            Log.w(TAG, "setSessionVolume: the route has fixed volume. Ignoring.");
            return;
        }
        if (i < 0 || i > routingSessionInfo.getVolumeMax()) {
            Log.w(TAG, "setSessionVolume: the target volume is out of range. Ignoring");
            return;
        }
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.setSessionVolumeWithManager(orCreateClient, this.mNextRequestId.getAndIncrement(), routingSessionInfo.getId(), i);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to set session volume.", e);
            }
        }
    }

    void addRoutesOnHandler(List<MediaRoute2Info> list) {
        synchronized (this.mRoutesLock) {
            for (MediaRoute2Info mediaRoute2Info : list) {
                this.mRoutes.put(mediaRoute2Info.getId(), mediaRoute2Info);
            }
        }
        if (list.size() > 0) {
            notifyRoutesAdded(list);
        }
    }

    void removeRoutesOnHandler(List<MediaRoute2Info> list) {
        synchronized (this.mRoutesLock) {
            Iterator<MediaRoute2Info> it = list.iterator();
            while (it.hasNext()) {
                this.mRoutes.remove(it.next().getId());
            }
        }
        if (list.size() > 0) {
            notifyRoutesRemoved(list);
        }
    }

    void changeRoutesOnHandler(List<MediaRoute2Info> list) {
        synchronized (this.mRoutesLock) {
            for (MediaRoute2Info mediaRoute2Info : list) {
                this.mRoutes.put(mediaRoute2Info.getId(), mediaRoute2Info);
            }
        }
        if (list.size() > 0) {
            notifyRoutesChanged(list);
        }
    }

    void createSessionOnHandler(int i, RoutingSessionInfo routingSessionInfo) {
        TransferRequest next;
        Iterator<TransferRequest> it = this.mTransferRequests.iterator();
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
            return;
        }
        this.mTransferRequests.remove(next);
        MediaRoute2Info mediaRoute2Info = next.mTargetRoute;
        if (routingSessionInfo == null) {
            notifyTransferFailed(next.mOldSessionInfo, mediaRoute2Info);
            return;
        }
        if (!routingSessionInfo.getSelectedRoutes().contains(mediaRoute2Info.getId())) {
            Log.w(TAG, "The session does not contain the requested route. (requestedRouteId=" + mediaRoute2Info.getId() + ", actualRoutes=" + routingSessionInfo.getSelectedRoutes() + ")");
            notifyTransferFailed(next.mOldSessionInfo, mediaRoute2Info);
        } else if (!TextUtils.equals(mediaRoute2Info.getProviderId(), routingSessionInfo.getProviderId())) {
            Log.w(TAG, "The session's provider ID does not match the requested route's. (requested route's providerId=" + mediaRoute2Info.getProviderId() + ", actual providerId=" + routingSessionInfo.getProviderId() + ")");
            notifyTransferFailed(next.mOldSessionInfo, mediaRoute2Info);
        } else {
            notifyTransferred(next.mOldSessionInfo, routingSessionInfo);
        }
    }

    void handleFailureOnHandler(int i, int i2) {
        TransferRequest next;
        Iterator<TransferRequest> it = this.mTransferRequests.iterator();
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
        if (next != null) {
            this.mTransferRequests.remove(next);
            notifyTransferFailed(next.mOldSessionInfo, next.mTargetRoute);
        } else {
            notifyRequestFailed(i2);
        }
    }

    void handleSessionsUpdatedOnHandler(RoutingSessionInfo routingSessionInfo) {
        Iterator<TransferRequest> it = this.mTransferRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TransferRequest next = it.next();
            if (TextUtils.equals(next.mOldSessionInfo.getId(), routingSessionInfo.getId()) && routingSessionInfo.getSelectedRoutes().contains(next.mTargetRoute.getId())) {
                this.mTransferRequests.remove(next);
                notifyTransferred(next.mOldSessionInfo, routingSessionInfo);
                break;
            }
        }
        notifySessionUpdated(routingSessionInfo);
    }

    private void notifyRoutesAdded(final List<MediaRoute2Info> list) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$bcZoIL-yeKCi_LtZS8-QGdzk0yg
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesAdded(list);
                }
            });
        }
    }

    private void notifyRoutesRemoved(final List<MediaRoute2Info> list) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$9sV-ZD2odmOcz7GdqYdANck4k2s
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesRemoved(list);
                }
            });
        }
    }

    private void notifyRoutesChanged(final List<MediaRoute2Info> list) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$TYtuSGD4sW6S4PEj0FN8cVu0B5c
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesChanged(list);
                }
            });
        }
    }

    void notifySessionUpdated(final RoutingSessionInfo routingSessionInfo) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$Y9UEEVcUkafMEOB3tem8oSs0Dok
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onSessionUpdated(routingSessionInfo);
                }
            });
        }
    }

    void notifySessionReleased(final RoutingSessionInfo routingSessionInfo) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$D9VaCG93B7iiAmkV6m9vS5QYhpY
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onSessionReleased(routingSessionInfo);
                }
            });
        }
    }

    void notifyRequestFailed(final int i) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$aGVaqmL5m3DpLENGt2cVjhakP_c
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRequestFailed(i);
                }
            });
        }
    }

    void notifyTransferred(final RoutingSessionInfo routingSessionInfo, final RoutingSessionInfo routingSessionInfo2) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$B7KYL4QtCI_7hjCxwP9eD8KKdSo
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onTransferred(routingSessionInfo, routingSessionInfo2);
                }
            });
        }
    }

    void notifyTransferFailed(final RoutingSessionInfo routingSessionInfo, final MediaRoute2Info mediaRoute2Info) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$LD9HxRMQDHFHTwdDUiacVkiLjOc
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onTransferFailed(routingSessionInfo, mediaRoute2Info);
                }
            });
        }
    }

    void updatePreferredFeatures(final String str, final List<String> list) {
        if (list == null) {
            this.mPreferredFeaturesMap.remove(str);
            return;
        }
        List<String> listPut = this.mPreferredFeaturesMap.put(str, list);
        if ((listPut == null && list.size() == 0) || Objects.equals(list, listPut)) {
            return;
        }
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$ZjNlevTvAiVQuBvevRAIeGq0L-c
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onPreferredFeaturesChanged(str, list);
                }
            });
        }
    }

    public List<MediaRoute2Info> getSelectedRoutes(RoutingSessionInfo routingSessionInfo) {
        List<MediaRoute2Info> list;
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        synchronized (this.mRoutesLock) {
            Stream<String> stream = routingSessionInfo.getSelectedRoutes().stream();
            Map<String, MediaRoute2Info> map = this.mRoutes;
            Objects.requireNonNull(map);
            list = (List) stream.map(new $$Lambda$Rv_QxUUu7BNGUhumIPDVokO3js(map)).filter($$Lambda$MediaRouter2Manager$nIHiiLCAnCUtoRKRPHG5llkexw.INSTANCE).collect(Collectors.toList());
        }
        return list;
    }

    public List<MediaRoute2Info> getSelectableRoutes(RoutingSessionInfo routingSessionInfo) {
        List<MediaRoute2Info> list;
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        final List<String> selectedRoutes = routingSessionInfo.getSelectedRoutes();
        synchronized (this.mRoutesLock) {
            Stream<String> streamFilter = routingSessionInfo.getSelectableRoutes().stream().filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2Manager$7Up0DxQkdgBMvaMNe3ILhvDOtAU
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MediaRouter2Manager.lambda$getSelectableRoutes$9(selectedRoutes, (String) obj);
                }
            });
            Map<String, MediaRoute2Info> map = this.mRoutes;
            Objects.requireNonNull(map);
            list = (List) streamFilter.map(new $$Lambda$Rv_QxUUu7BNGUhumIPDVokO3js(map)).filter($$Lambda$MediaRouter2Manager$nIHiiLCAnCUtoRKRPHG5llkexw.INSTANCE).collect(Collectors.toList());
        }
        return list;
    }

    static /* synthetic */ boolean lambda$getSelectableRoutes$9(List list, String str) {
        return !list.contains(str);
    }

    public List<MediaRoute2Info> getDeselectableRoutes(RoutingSessionInfo routingSessionInfo) {
        List<MediaRoute2Info> list;
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        final List<String> selectedRoutes = routingSessionInfo.getSelectedRoutes();
        synchronized (this.mRoutesLock) {
            Stream<String> streamFilter = routingSessionInfo.getDeselectableRoutes().stream().filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2Manager$qJwEK5P_BglPcmXQLmw44s1M2g4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return selectedRoutes.contains((String) obj);
                }
            });
            Map<String, MediaRoute2Info> map = this.mRoutes;
            Objects.requireNonNull(map);
            list = (List) streamFilter.map(new $$Lambda$Rv_QxUUu7BNGUhumIPDVokO3js(map)).filter($$Lambda$MediaRouter2Manager$nIHiiLCAnCUtoRKRPHG5llkexw.INSTANCE).collect(Collectors.toList());
        }
        return list;
    }

    public void selectRoute(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        if (routingSessionInfo.getSelectedRoutes().contains(mediaRoute2Info.getId())) {
            Log.w(TAG, "Ignoring selecting a route that is already selected. route=" + mediaRoute2Info);
            return;
        }
        if (!routingSessionInfo.getSelectableRoutes().contains(mediaRoute2Info.getId())) {
            Log.w(TAG, "Ignoring selecting a non-selectable route=" + mediaRoute2Info);
            return;
        }
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.selectRouteWithManager(orCreateClient, this.mNextRequestId.getAndIncrement(), routingSessionInfo.getId(), mediaRoute2Info);
            } catch (RemoteException e) {
                Log.e(TAG, "selectRoute: Failed to send a request.", e);
            }
        }
    }

    public void deselectRoute(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        if (!routingSessionInfo.getSelectedRoutes().contains(mediaRoute2Info.getId())) {
            Log.w(TAG, "Ignoring deselecting a route that is not selected. route=" + mediaRoute2Info);
            return;
        }
        if (!routingSessionInfo.getDeselectableRoutes().contains(mediaRoute2Info.getId())) {
            Log.w(TAG, "Ignoring deselecting a non-deselectable route=" + mediaRoute2Info);
            return;
        }
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.deselectRouteWithManager(orCreateClient, this.mNextRequestId.getAndIncrement(), routingSessionInfo.getId(), mediaRoute2Info);
            } catch (RemoteException e) {
                Log.e(TAG, "deselectRoute: Failed to send a request.", e);
            }
        }
    }

    public void releaseSession(RoutingSessionInfo routingSessionInfo) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.releaseSessionWithManager(orCreateClient, this.mNextRequestId.getAndIncrement(), routingSessionInfo.getId());
            } catch (RemoteException e) {
                Log.e(TAG, "releaseSession: Failed to send a request", e);
            }
        }
    }

    private void transferToRoute(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        int iCreateTransferRequest = createTransferRequest(routingSessionInfo, mediaRoute2Info);
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.transferToRouteWithManager(orCreateClient, iCreateTransferRequest, routingSessionInfo.getId(), mediaRoute2Info);
            } catch (RemoteException e) {
                Log.e(TAG, "transferToRoute: Failed to send a request.", e);
            }
        }
    }

    private void requestCreateSession(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        if (TextUtils.isEmpty(routingSessionInfo.getClientPackageName())) {
            Log.w(TAG, "requestCreateSession: Can't create a session without package name.");
            notifyTransferFailed(routingSessionInfo, mediaRoute2Info);
            return;
        }
        int iCreateTransferRequest = createTransferRequest(routingSessionInfo, mediaRoute2Info);
        Client orCreateClient = getOrCreateClient();
        if (orCreateClient != null) {
            try {
                this.mMediaRouterService.requestCreateSessionWithManager(orCreateClient, iCreateTransferRequest, routingSessionInfo, mediaRoute2Info);
            } catch (RemoteException e) {
                Log.e(TAG, "requestCreateSession: Failed to send a request", e);
            }
        }
    }

    private int createTransferRequest(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        int andIncrement = this.mNextRequestId.getAndIncrement();
        TransferRequest transferRequest = new TransferRequest(andIncrement, routingSessionInfo, mediaRoute2Info);
        this.mTransferRequests.add(transferRequest);
        this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$MediaRouter2Manager$4_ivctd76zRkMuxtdONtRid6dQc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((MediaRouter2Manager) obj).handleTransferTimeout((MediaRouter2Manager.TransferRequest) obj2);
            }
        }, this, transferRequest), JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
        return andIncrement;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransferTimeout(TransferRequest transferRequest) {
        if (this.mTransferRequests.remove(transferRequest)) {
            notifyTransferFailed(transferRequest.mOldSessionInfo, transferRequest.mTargetRoute);
        }
    }

    private boolean areSessionsMatched(MediaController mediaController, RoutingSessionInfo routingSessionInfo) {
        String volumeControlId;
        MediaController.PlaybackInfo playbackInfo = mediaController.getPlaybackInfo();
        if (playbackInfo == null || (volumeControlId = playbackInfo.getVolumeControlId()) == null) {
            return false;
        }
        if (TextUtils.equals(volumeControlId, routingSessionInfo.getId())) {
            return true;
        }
        return TextUtils.equals(volumeControlId, routingSessionInfo.getOriginalId()) && TextUtils.equals(mediaController.getPackageName(), routingSessionInfo.getOwnerPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Client getOrCreateClient() {
        synchronized (sLock) {
            Client client = this.mClient;
            if (client != null) {
                return client;
            }
            Client client2 = new Client();
            try {
                this.mMediaRouterService.registerManager(client2, this.mPackageName);
                this.mClient = client2;
                return client2;
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to register media router manager.", e);
                return null;
            }
        }
    }

    final class CallbackRecord {
        public final Callback mCallback;
        public final Executor mExecutor;

        CallbackRecord(Executor executor, Callback callback) {
            this.mExecutor = executor;
            this.mCallback = callback;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CallbackRecord) && this.mCallback == ((CallbackRecord) obj).mCallback;
        }

        public int hashCode() {
            return this.mCallback.hashCode();
        }
    }

    static final class TransferRequest {
        public final RoutingSessionInfo mOldSessionInfo;
        public final int mRequestId;
        public final MediaRoute2Info mTargetRoute;

        TransferRequest(int i, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
            this.mRequestId = i;
            this.mOldSessionInfo = routingSessionInfo;
            this.mTargetRoute = mediaRoute2Info;
        }
    }

    class Client extends IMediaRouter2Manager.Stub {
        Client() {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$4IyOOteN43q4Cc1nrFEVHRZDQR4
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).createSessionOnHandler(((Integer) obj2).intValue(), (RoutingSessionInfo) obj3);
                }
            }, MediaRouter2Manager.this, Integer.valueOf(i), routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$PETbVQWlIutvfVZnuv5US69Oeqc
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).handleSessionsUpdatedOnHandler((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2Manager.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$AH-h1uSAnHQeMzljhRzolSq5pcw
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).notifySessionReleased((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2Manager.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRequestFailed(int i, int i2) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$9AAcv77YeDn_TuMOZWrk1ll1U-s
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).handleFailureOnHandler(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                }
            }, MediaRouter2Manager.this, Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyPreferredFeaturesChanged(String str, List<String> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$YJ5-To6upykTpprvI1Zyq9nyAoA
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).updatePreferredFeatures((String) obj2, (List) obj3);
                }
            }, MediaRouter2Manager.this, str, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesAdded(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$RtqMNkjIlrXjADtRniUO7ZdSJPk
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).addRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesRemoved(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$wa5IRxDT4WB1mq52me_IEojNB8s
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).removeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesChanged(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$qa8kNn12D7d-XOiA2G7uslod-cQ
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).changeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }
    }
}
