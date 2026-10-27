package android.media;

import android.app.Service;
import android.content.Intent;
import android.media.IMediaRoute2ProviderService;
import android.media.MediaRoute2ProviderInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaRoute2ProviderService extends Service {
    private static final int MAX_REQUEST_IDS_SIZE = 500;
    public static final int REASON_INVALID_COMMAND = 4;
    public static final int REASON_NETWORK_ERROR = 2;
    public static final int REASON_REJECTED = 1;
    public static final int REASON_ROUTE_NOT_AVAILABLE = 3;
    public static final int REASON_UNKNOWN_ERROR = 0;
    public static final long REQUEST_ID_NONE = 0;
    public static final String SERVICE_INTERFACE = "android.media.MediaRoute2ProviderService";
    private volatile MediaRoute2ProviderInfo mProviderInfo;
    private IMediaRoute2ProviderServiceCallback mRemoteCallback;
    private MediaRoute2ProviderServiceStub mStub;
    private static final String TAG = "MR2ProviderService";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private final Object mSessionLock = new Object();
    private final Object mRequestIdsLock = new Object();
    private final AtomicBoolean mStatePublishScheduled = new AtomicBoolean(false);
    private final Deque<Long> mRequestIds = new ArrayDeque(500);
    private final ArrayMap<String, RoutingSessionInfo> mSessionInfo = new ArrayMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }

    public abstract void onCreateSession(long j, String str, String str2, Bundle bundle);

    public abstract void onDeselectRoute(long j, String str, String str2);

    public void onDiscoveryPreferenceChanged(RouteDiscoveryPreference routeDiscoveryPreference) {
    }

    public abstract void onReleaseSession(long j, String str);

    public abstract void onSelectRoute(long j, String str, String str2);

    public abstract void onSetRouteVolume(long j, String str, int i);

    public abstract void onSetSessionVolume(long j, String str, int i);

    public abstract void onTransferToRoute(long j, String str, String str2);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (this.mStub == null) {
            this.mStub = new MediaRoute2ProviderServiceStub();
        }
        return this.mStub;
    }

    public final RoutingSessionInfo getSessionInfo(String str) {
        RoutingSessionInfo routingSessionInfo;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("sessionId must not be empty");
        }
        synchronized (this.mSessionLock) {
            routingSessionInfo = this.mSessionInfo.get(str);
        }
        return routingSessionInfo;
    }

    public final List<RoutingSessionInfo> getAllSessionInfo() {
        ArrayList arrayList;
        synchronized (this.mSessionLock) {
            arrayList = new ArrayList(this.mSessionInfo.values());
        }
        return arrayList;
    }

    public final void notifySessionCreated(long j, RoutingSessionInfo routingSessionInfo) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        if (DEBUG) {
            Log.d(TAG, "notifySessionCreated: Creating a session. requestId=" + j + ", sessionInfo=" + routingSessionInfo);
        }
        if (j != 0 && !removeRequestId(j)) {
            Log.w(TAG, "notifySessionCreated: The requestId doesn't exist. requestId=" + j);
            return;
        }
        String id = routingSessionInfo.getId();
        synchronized (this.mSessionLock) {
            if (this.mSessionInfo.containsKey(id)) {
                Log.w(TAG, "notifySessionCreated: Ignoring duplicate session id.");
                return;
            }
            this.mSessionInfo.put(routingSessionInfo.getId(), routingSessionInfo);
            IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback = this.mRemoteCallback;
            if (iMediaRoute2ProviderServiceCallback == null) {
                return;
            }
            try {
                iMediaRoute2ProviderServiceCallback.notifySessionCreated(j, routingSessionInfo);
            } catch (RemoteException unused) {
                Log.w(TAG, "Failed to notify session created.");
            }
        }
    }

    public final void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) {
        Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
        if (DEBUG) {
            Log.d(TAG, "notifySessionUpdated: Updating session id=" + routingSessionInfo);
        }
        String id = routingSessionInfo.getId();
        synchronized (this.mSessionLock) {
            if (this.mSessionInfo.containsKey(id)) {
                this.mSessionInfo.put(id, routingSessionInfo);
                IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback = this.mRemoteCallback;
                if (iMediaRoute2ProviderServiceCallback == null) {
                    return;
                }
                try {
                    iMediaRoute2ProviderServiceCallback.notifySessionUpdated(routingSessionInfo);
                } catch (RemoteException unused) {
                    Log.w(TAG, "Failed to notify session info changed.");
                }
                return;
            }
            Log.w(TAG, "notifySessionUpdated: Ignoring unknown session info.");
        }
    }

    public final void notifySessionReleased(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("sessionId must not be empty");
        }
        if (DEBUG) {
            Log.d(TAG, "notifySessionReleased: Releasing session id=" + str);
        }
        synchronized (this.mSessionLock) {
            RoutingSessionInfo routingSessionInfoRemove = this.mSessionInfo.remove(str);
            if (routingSessionInfoRemove == null) {
                Log.w(TAG, "notifySessionReleased: Ignoring unknown session info.");
                return;
            }
            IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback = this.mRemoteCallback;
            if (iMediaRoute2ProviderServiceCallback == null) {
                return;
            }
            try {
                iMediaRoute2ProviderServiceCallback.notifySessionReleased(routingSessionInfoRemove);
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to notify session released.", e);
            }
        }
    }

    public final void notifyRequestFailed(long j, int i) {
        if (this.mRemoteCallback == null) {
            return;
        }
        if (!removeRequestId(j)) {
            Log.w(TAG, "notifyRequestFailed: The requestId doesn't exist. requestId=" + j);
            return;
        }
        try {
            this.mRemoteCallback.notifyRequestFailed(j, i);
        } catch (RemoteException unused) {
            Log.w(TAG, "Failed to notify that the request has failed.");
        }
    }

    public final void notifyRoutes(Collection<MediaRoute2Info> collection) {
        Objects.requireNonNull(collection, "routes must not be null");
        this.mProviderInfo = new MediaRoute2ProviderInfo.Builder().addRoutes(collection).build();
        schedulePublishState();
    }

    void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) {
        this.mRemoteCallback = iMediaRoute2ProviderServiceCallback;
        schedulePublishState();
    }

    void schedulePublishState() {
        if (this.mStatePublishScheduled.compareAndSet(false, true)) {
            this.mHandler.post(new Runnable() { // from class: android.media.-$$Lambda$MediaRoute2ProviderService$5hzmqiG5B0J3WUTluElOW7zaifk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.publishState();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishState() {
        IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback;
        if (this.mStatePublishScheduled.compareAndSet(true, false) && (iMediaRoute2ProviderServiceCallback = this.mRemoteCallback) != null) {
            try {
                iMediaRoute2ProviderServiceCallback.updateState(this.mProviderInfo);
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to publish provider state.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequestId(long j) {
        synchronized (this.mRequestIdsLock) {
            if (this.mRequestIds.size() >= 500) {
                this.mRequestIds.removeFirst();
            }
            this.mRequestIds.addLast(Long.valueOf(j));
        }
    }

    private boolean removeRequestId(long j) {
        boolean zRemoveFirstOccurrence;
        synchronized (this.mRequestIdsLock) {
            zRemoveFirstOccurrence = this.mRequestIds.removeFirstOccurrence(Long.valueOf(j));
        }
        return zRemoveFirstOccurrence;
    }

    final class MediaRoute2ProviderServiceStub extends IMediaRoute2ProviderService.Stub {
        MediaRoute2ProviderServiceStub() {
        }

        private boolean checkCallerIsSystem() {
            return Binder.getCallingUid() == 1000;
        }

        private boolean checkSessionIdIsValid(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaRoute2ProviderService.TAG, str2 + ": Ignoring empty sessionId from system service.");
                return false;
            }
            if (MediaRoute2ProviderService.this.getSessionInfo(str) != null) {
                return true;
            }
            Log.w(MediaRoute2ProviderService.TAG, str2 + ": Ignoring unknown session from system service. sessionId=" + str);
            return false;
        }

        private boolean checkRouteIdIsValid(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                if (MediaRoute2ProviderService.this.mProviderInfo != null && MediaRoute2ProviderService.this.mProviderInfo.getRoute(str) != null) {
                    return true;
                }
                Log.w(MediaRoute2ProviderService.TAG, str2 + ": Ignoring unknown route from system service. routeId=" + str);
                return false;
            }
            Log.w(MediaRoute2ProviderService.TAG, str2 + ": Ignoring empty routeId from system service.");
            return false;
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) {
            if (checkCallerIsSystem()) {
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$IMr-JAZQWCpxrw3z7jgypgZMrX0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ((MediaRoute2ProviderService) obj).setCallback((IMediaRoute2ProviderServiceCallback) obj2);
                    }
                }, MediaRoute2ProviderService.this, iMediaRoute2ProviderServiceCallback));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void updateDiscoveryPreference(RouteDiscoveryPreference routeDiscoveryPreference) {
            if (checkCallerIsSystem()) {
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.media.-$$Lambda$CCMq1dvhL7TrDR4T3GBso02j-As
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ((MediaRoute2ProviderService) obj).onDiscoveryPreferenceChanged((RouteDiscoveryPreference) obj2);
                    }
                }, MediaRoute2ProviderService.this, routeDiscoveryPreference));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setRouteVolume(long j, String str, int i) {
            if (checkCallerIsSystem() && checkRouteIdIsValid(str, "setRouteVolume")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$2zv7OQwRLw0E1o82GD8r8XLPiA8
                    @Override // com.android.internal.util.function.QuadConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                        ((MediaRoute2ProviderService) obj).onSetRouteVolume(((Long) obj2).longValue(), (String) obj3, ((Integer) obj4).intValue());
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, Integer.valueOf(i)));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void requestCreateSession(long j, String str, String str2, Bundle bundle) {
            if (checkCallerIsSystem() && checkRouteIdIsValid(str2, "requestCreateSession")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuintConsumer() { // from class: android.media.-$$Lambda$IUzeAjuZMlkZl_T9o1tSMxcdSd8
                    @Override // com.android.internal.util.function.QuintConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                        ((MediaRoute2ProviderService) obj).onCreateSession(((Long) obj2).longValue(), (String) obj3, (String) obj4, (Bundle) obj5);
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, str2, bundle));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void selectRoute(long j, String str, String str2) {
            if (checkCallerIsSystem() && checkSessionIdIsValid(str, "selectRoute") && checkRouteIdIsValid(str2, "selectRoute")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$LODeYnHvcEbDvUHO8NISJPfGelo
                    @Override // com.android.internal.util.function.QuadConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                        ((MediaRoute2ProviderService) obj).onSelectRoute(((Long) obj2).longValue(), (String) obj3, (String) obj4);
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, str2));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void deselectRoute(long j, String str, String str2) {
            if (checkCallerIsSystem() && checkSessionIdIsValid(str, "deselectRoute") && checkRouteIdIsValid(str2, "deselectRoute")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$pB8NfQUhOPSZuQYEKL41jIfeLB4
                    @Override // com.android.internal.util.function.QuadConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                        ((MediaRoute2ProviderService) obj).onDeselectRoute(((Long) obj2).longValue(), (String) obj3, (String) obj4);
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, str2));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void transferToRoute(long j, String str, String str2) {
            if (checkCallerIsSystem() && checkSessionIdIsValid(str, "transferToRoute") && checkRouteIdIsValid(str2, "transferToRoute")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$YrziRuVwWMy1gfvgryAjFLtu7Bc
                    @Override // com.android.internal.util.function.QuadConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                        ((MediaRoute2ProviderService) obj).onTransferToRoute(((Long) obj2).longValue(), (String) obj3, (String) obj4);
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, str2));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setSessionVolume(long j, String str, int i) {
            if (checkCallerIsSystem() && checkSessionIdIsValid(str, "setSessionVolume")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.media.-$$Lambda$hGi2fLbALSKR9q8mdWtCQBgoWM8
                    @Override // com.android.internal.util.function.QuadConsumer
                    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                        ((MediaRoute2ProviderService) obj).onSetSessionVolume(((Long) obj2).longValue(), (String) obj3, ((Integer) obj4).intValue());
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str, Integer.valueOf(i)));
            }
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void releaseSession(long j, String str) {
            if (checkCallerIsSystem() && checkSessionIdIsValid(str, "releaseSession")) {
                MediaRoute2ProviderService.this.addRequestId(j);
                MediaRoute2ProviderService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.media.-$$Lambda$7F35WpfMpMKJQPjqN_I_Whfmc4k
                    @Override // com.android.internal.util.function.TriConsumer
                    public final void accept(Object obj, Object obj2, Object obj3) {
                        ((MediaRoute2ProviderService) obj).onReleaseSession(((Long) obj2).longValue(), (String) obj3);
                    }
                }, MediaRoute2ProviderService.this, Long.valueOf(j), str));
            }
        }
    }
}
