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
    private final MediaSessionManager mMediaSessionManager;
    final String mPackageName;
    final CopyOnWriteArrayList<CallbackRecord> mCallbackRecords = new CopyOnWriteArrayList<>();
    private final Object mRoutesLock = new Object();
    private final Map<String, MediaRoute2Info> mRoutes = new HashMap();
    final ConcurrentMap<String, List<String>> mPreferredFeaturesMap = new ConcurrentHashMap();
    private final AtomicInteger mNextRequestId = new AtomicInteger(1);
    private final CopyOnWriteArrayList<TransferRequest> mTransferRequests = new CopyOnWriteArrayList<>();
    private final IMediaRouterService mMediaRouterService = IMediaRouterService.Stub.asInterface(ServiceManager.getService(Context.MEDIA_ROUTER_SERVICE));

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
        this.mContext = context.getApplicationContext();
        this.mMediaSessionManager = (MediaSessionManager) context.getSystemService(Context.MEDIA_SESSION_SERVICE);
        this.mPackageName = this.mContext.getPackageName();
        Handler handler = new Handler(context.getMainLooper());
        this.mHandler = handler;
        handler.post(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$X76xPaxqgD0WPDz-D1Lxk5kiurY
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
        List<RoutingSessionInfo> routingSessions = getRoutingSessions(str);
        transfer(routingSessions.get(routingSessions.size() + (-1)), mediaRoute2Info);
    }

    public void transfer(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        Objects.requireNonNull(mediaRoute2Info, "route must not be null");
        Log.v(TAG, "Transferring routing session. session= " + routingSessionInfo + ", route=" + mediaRoute2Info);
        synchronized (this.mRoutesLock) {
            if (!this.mRoutes.containsKey(mediaRoute2Info.getId())) {
                Log.w(TAG, "transfer: Ignoring an unknown route id=" + mediaRoute2Info.getId());
                notifyTransferFailed(routingSessionInfo, mediaRoute2Info);
                return;
            }
            if (routingSessionInfo.getTransferableRoutes().contains(mediaRoute2Info.getId())) {
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
            return;
        }
        if (!TextUtils.equals(mediaRoute2Info.getProviderId(), routingSessionInfo.getProviderId())) {
            Log.w(TAG, "The session's provider ID does not match the requested route's. (requested route's providerId=" + mediaRoute2Info.getProviderId() + ", actual providerId=" + routingSessionInfo.getProviderId() + ")");
            notifyTransferFailed(next.mOldSessionInfo, mediaRoute2Info);
            return;
        }
        notifyTransferred(next.mOldSessionInfo, routingSessionInfo);
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
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$EHLJd_emeyLDkF_AwpiyC8_kDdc
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesAdded(list);
                }
            });
        }
    }

    private void notifyRoutesRemoved(final List<MediaRoute2Info> list) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$okgw16i6uRs7ol-81WusfPkAk9U
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesRemoved(list);
                }
            });
        }
    }

    private void notifyRoutesChanged(final List<MediaRoute2Info> list) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$VaiYDmtXUs-bgZtD2f2UpEjT-20
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRoutesChanged(list);
                }
            });
        }
    }

    void notifySessionUpdated(final RoutingSessionInfo routingSessionInfo) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$Wc_lT54fkpr3qSlIG7DSi5PeSVM
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onSessionUpdated(routingSessionInfo);
                }
            });
        }
    }

    void notifySessionReleased(final RoutingSessionInfo routingSessionInfo) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$KUHGEkWGlaVfd3Zi4oigZVFdRC4
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onSessionReleased(routingSessionInfo);
                }
            });
        }
    }

    void notifyRequestFailed(final int i) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$r2vzZsEyRJDQIJgQnYSaKy7jmX4
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onRequestFailed(i);
                }
            });
        }
    }

    void notifyTransferred(final RoutingSessionInfo routingSessionInfo, final RoutingSessionInfo routingSessionInfo2) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$woOi9vOXaxYUsZXZ_elbMeI3NFw
                @Override // java.lang.Runnable
                public final void run() {
                    callbackRecord.mCallback.onTransferred(routingSessionInfo, routingSessionInfo2);
                }
            });
        }
    }

    void notifyTransferFailed(final RoutingSessionInfo routingSessionInfo, final MediaRoute2Info mediaRoute2Info) {
        for (final CallbackRecord callbackRecord : this.mCallbackRecords) {
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$U66215BPr7th85YZ9Hpd5OVMOgU
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
            callbackRecord.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaRouter2Manager$bwWxXKc_6BGO9bmWeLY4XfIX2lw
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
            list = (List) stream.map(new $$Lambda$0KjW5dKQDNOkejDzbQIfcK35kWA(map)).filter($$Lambda$MediaRouter2Manager$8fo3RPrzkq5mg2wxR3lAN3cgNY.INSTANCE).collect(Collectors.toList());
        }
        return list;
    }

    public List<MediaRoute2Info> getSelectableRoutes(RoutingSessionInfo routingSessionInfo) {
        List<MediaRoute2Info> list;
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        final List<String> selectedRoutes = routingSessionInfo.getSelectedRoutes();
        synchronized (this.mRoutesLock) {
            Stream<String> streamFilter = routingSessionInfo.getSelectableRoutes().stream().filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2Manager$EwA0DuTJgJEWQVNlePEqNDMh4-s
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MediaRouter2Manager.lambda$getSelectableRoutes$9(selectedRoutes, (String) obj);
                }
            });
            Map<String, MediaRoute2Info> map = this.mRoutes;
            Objects.requireNonNull(map);
            list = (List) streamFilter.map(new $$Lambda$0KjW5dKQDNOkejDzbQIfcK35kWA(map)).filter($$Lambda$MediaRouter2Manager$8fo3RPrzkq5mg2wxR3lAN3cgNY.INSTANCE).collect(Collectors.toList());
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
            Stream<String> streamFilter = routingSessionInfo.getDeselectableRoutes().stream().filter(new Predicate() { // from class: android.media.-$$Lambda$MediaRouter2Manager$kLakEUmZ1WmAktt2ie1ETgzRYRw
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return selectedRoutes.contains((String) obj);
                }
            });
            Map<String, MediaRoute2Info> map = this.mRoutes;
            Objects.requireNonNull(map);
            list = (List) streamFilter.map(new $$Lambda$0KjW5dKQDNOkejDzbQIfcK35kWA(map)).filter($$Lambda$MediaRouter2Manager$8fo3RPrzkq5mg2wxR3lAN3cgNY.INSTANCE).collect(Collectors.toList());
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
        this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$MediaRouter2Manager$A_6LAn0k2ifEMlid1ntuc9gWf74
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
            if (this.mClient != null) {
                return this.mClient;
            }
            Client client = new Client();
            try {
                this.mMediaRouterService.registerManager(client, this.mPackageName);
                this.mClient = client;
                return client;
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
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$PToJpxlbOimKtSkpvLwcwYz6Bio
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).createSessionOnHandler(((Integer) obj2).intValue(), (RoutingSessionInfo) obj3);
                }
            }, MediaRouter2Manager.this, Integer.valueOf(i), routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$wPcK-ftqvhyA5Kgnc6jiR01yNiY
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).handleSessionsUpdatedOnHandler((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2Manager.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionReleased(RoutingSessionInfo routingSessionInfo) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$Z_tCdSCLbZ5gJYJTtsJCc8wAzw4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).notifySessionReleased((RoutingSessionInfo) obj2);
                }
            }, MediaRouter2Manager.this, routingSessionInfo));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRequestFailed(int i, int i2) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$t94cM9ipj3vOV_Gh5MlP8wygpjU
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).handleFailureOnHandler(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                }
            }, MediaRouter2Manager.this, Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyPreferredFeaturesChanged(String str, List<String> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$uUtNQdlApH9llAedmk42jVBM_FE
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((MediaRouter2Manager) obj).updatePreferredFeatures((String) obj2, (List) obj3);
                }
            }, MediaRouter2Manager.this, str, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesAdded(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$gt7p_JyIeiypjCr-OUbbXtG3M2E
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).addRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesRemoved(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$_MF8vjrGAjipPRcLld_WGC6Gj3M
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).removeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesChanged(List<MediaRoute2Info> list) {
            MediaRouter2Manager.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$S7cvg7fUGYEd7NUzH3t58CJRbbE
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((MediaRouter2Manager) obj).changeRoutesOnHandler((List) obj2);
                }
            }, MediaRouter2Manager.this, list));
        }
    }
}
