package androidx.mediarouter.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: loaded from: classes2.dex */
public class RemotePlaybackClient {
    private final ActionReceiver mActionReceiver;
    private final Context mContext;
    private final PendingIntent mItemStatusPendingIntent;
    private final PendingIntent mMessagePendingIntent;
    OnMessageReceivedListener mOnMessageReceivedListener;
    private final MediaRouter.RouteInfo mRoute;
    private boolean mRouteSupportsMessaging;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    StatusCallback mStatusCallback;
    static final String TAG = "RemotePlaybackClient";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public interface OnMessageReceivedListener {
        void onMessageReceived(String str, Bundle bundle);
    }

    public RemotePlaybackClient(Context context, MediaRouter.RouteInfo route) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        this.mContext = context;
        this.mRoute = route;
        IntentFilter actionFilter = new IntentFilter();
        actionFilter.addAction(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        actionFilter.addAction(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        actionFilter.addAction(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        ActionReceiver actionReceiver = new ActionReceiver();
        this.mActionReceiver = actionReceiver;
        context.registerReceiver(actionReceiver, actionFilter);
        Intent itemStatusIntent = new Intent(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        itemStatusIntent.setPackage(context.getPackageName());
        this.mItemStatusPendingIntent = PendingIntent.getBroadcast(context, 0, itemStatusIntent, 0);
        Intent sessionStatusIntent = new Intent(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        sessionStatusIntent.setPackage(context.getPackageName());
        this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(context, 0, sessionStatusIntent, 0);
        Intent messageIntent = new Intent(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        messageIntent.setPackage(context.getPackageName());
        this.mMessagePendingIntent = PendingIntent.getBroadcast(context, 0, messageIntent, 0);
        detectFeatures();
    }

    public void release() {
        this.mContext.unregisterReceiver(this.mActionReceiver);
    }

    public boolean isRemotePlaybackSupported() {
        return this.mRouteSupportsRemotePlayback;
    }

    public boolean isQueuingSupported() {
        return this.mRouteSupportsQueuing;
    }

    public boolean isSessionManagementSupported() {
        return this.mRouteSupportsSessionManagement;
    }

    public boolean isMessagingSupported() {
        return this.mRouteSupportsMessaging;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public void setSessionId(String sessionId) {
        if (!ObjectsCompat.equals(this.mSessionId, sessionId)) {
            if (DEBUG) {
                Log.d(TAG, "Session id is now: " + sessionId);
            }
            this.mSessionId = sessionId;
            StatusCallback statusCallback = this.mStatusCallback;
            if (statusCallback != null) {
                statusCallback.onSessionChanged(sessionId);
            }
        }
    }

    public boolean hasSession() {
        return this.mSessionId != null;
    }

    public void setStatusCallback(StatusCallback callback) {
        this.mStatusCallback = callback;
    }

    public void setOnMessageReceivedListener(OnMessageReceivedListener listener) {
        this.mOnMessageReceivedListener = listener;
    }

    public void play(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, ItemActionCallback callback) {
        playOrEnqueue(contentUri, mimeType, metadata, positionMillis, extras, callback, MediaControlIntent.ACTION_PLAY);
    }

    public void enqueue(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, ItemActionCallback callback) {
        playOrEnqueue(contentUri, mimeType, metadata, positionMillis, extras, callback, MediaControlIntent.ACTION_ENQUEUE);
    }

    private void playOrEnqueue(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, ItemActionCallback callback, String action) {
        if (contentUri == null) {
            throw new IllegalArgumentException("contentUri must not be null");
        }
        throwIfRemotePlaybackNotSupported();
        if (action.equals(MediaControlIntent.ACTION_ENQUEUE)) {
            throwIfQueuingNotSupported();
        }
        Intent intent = new Intent(action);
        intent.setDataAndType(contentUri, mimeType);
        intent.putExtra(MediaControlIntent.EXTRA_ITEM_STATUS_UPDATE_RECEIVER, this.mItemStatusPendingIntent);
        if (metadata != null) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_METADATA, metadata);
        }
        if (positionMillis != 0) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, positionMillis);
        }
        performItemAction(intent, this.mSessionId, null, extras, callback);
    }

    public void seek(String itemId, long positionMillis, Bundle extras, ItemActionCallback callback) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_SEEK);
        intent.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, positionMillis);
        performItemAction(intent, this.mSessionId, itemId, extras, callback);
    }

    public void getStatus(String itemId, Bundle extras, ItemActionCallback callback) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_GET_STATUS);
        performItemAction(intent, this.mSessionId, itemId, extras, callback);
    }

    public void remove(String itemId, Bundle extras, ItemActionCallback callback) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfQueuingNotSupported();
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_REMOVE);
        performItemAction(intent, this.mSessionId, itemId, extras, callback);
    }

    public void pause(Bundle extras, SessionActionCallback callback) {
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_PAUSE);
        performSessionAction(intent, this.mSessionId, extras, callback);
    }

    public void resume(Bundle extras, SessionActionCallback callback) {
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_RESUME);
        performSessionAction(intent, this.mSessionId, extras, callback);
    }

    public void stop(Bundle extras, SessionActionCallback callback) {
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_STOP);
        performSessionAction(intent, this.mSessionId, extras, callback);
    }

    public void startSession(Bundle extras, SessionActionCallback callback) {
        throwIfSessionManagementNotSupported();
        Intent intent = new Intent(MediaControlIntent.ACTION_START_SESSION);
        intent.putExtra(MediaControlIntent.EXTRA_SESSION_STATUS_UPDATE_RECEIVER, this.mSessionStatusPendingIntent);
        if (this.mRouteSupportsMessaging) {
            intent.putExtra(MediaControlIntent.EXTRA_MESSAGE_RECEIVER, this.mMessagePendingIntent);
        }
        performSessionAction(intent, null, extras, callback);
    }

    public void sendMessage(Bundle message, SessionActionCallback callback) {
        throwIfNoCurrentSession();
        throwIfMessageNotSupported();
        Intent intent = new Intent(MediaControlIntent.ACTION_SEND_MESSAGE);
        performSessionAction(intent, this.mSessionId, message, callback);
    }

    public void getSessionStatus(Bundle extras, SessionActionCallback callback) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_GET_SESSION_STATUS);
        performSessionAction(intent, this.mSessionId, extras, callback);
    }

    public void endSession(Bundle extras, SessionActionCallback callback) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_END_SESSION);
        performSessionAction(intent, this.mSessionId, extras, callback);
    }

    private void performItemAction(final Intent intent, final String sessionId, final String itemId, Bundle extras, final ItemActionCallback callback) {
        intent.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (sessionId != null) {
            intent.putExtra(MediaControlIntent.EXTRA_SESSION_ID, sessionId);
        }
        if (itemId != null) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_ID, itemId);
        }
        if (extras != null) {
            intent.putExtras(extras);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.RemotePlaybackClient.1
            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle data) {
                if (data != null) {
                    String sessionIdResult = RemotePlaybackClient.inferMissingResult(sessionId, data.getString(MediaControlIntent.EXTRA_SESSION_ID));
                    MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(data.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
                    String itemIdResult = RemotePlaybackClient.inferMissingResult(itemId, data.getString(MediaControlIntent.EXTRA_ITEM_ID));
                    MediaItemStatus itemStatus = MediaItemStatus.fromBundle(data.getBundle(MediaControlIntent.EXTRA_ITEM_STATUS));
                    RemotePlaybackClient.this.adoptSession(sessionIdResult);
                    if (sessionIdResult != null && itemIdResult != null && itemStatus != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d(RemotePlaybackClient.TAG, "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(data) + ", sessionId=" + sessionIdResult + ", sessionStatus=" + sessionStatus + ", itemId=" + itemIdResult + ", itemStatus=" + itemStatus);
                        }
                        callback.onResult(data, sessionIdResult, sessionStatus, itemIdResult, itemStatus);
                        return;
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, callback, data);
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String error, Bundle data) {
                RemotePlaybackClient.this.handleError(intent, callback, error, data);
            }
        });
    }

    private void performSessionAction(final Intent intent, final String sessionId, Bundle extras, final SessionActionCallback callback) {
        intent.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (sessionId != null) {
            intent.putExtra(MediaControlIntent.EXTRA_SESSION_ID, sessionId);
        }
        if (extras != null) {
            intent.putExtras(extras);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.RemotePlaybackClient.2
            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle data) {
                boolean zEquals;
                boolean zEquals2;
                if (data != null) {
                    String sessionIdResult = RemotePlaybackClient.inferMissingResult(sessionId, data.getString(MediaControlIntent.EXTRA_SESSION_ID));
                    MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(data.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
                    RemotePlaybackClient.this.adoptSession(sessionIdResult);
                    if (sessionIdResult != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d(RemotePlaybackClient.TAG, "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(data) + ", sessionId=" + sessionIdResult + ", sessionStatus=" + sessionStatus);
                        }
                        try {
                            callback.onResult(data, sessionIdResult, sessionStatus);
                            if (zEquals) {
                                if (zEquals2) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                            return;
                        } finally {
                            if (intent.getAction().equals(MediaControlIntent.ACTION_END_SESSION) && sessionIdResult.equals(RemotePlaybackClient.this.mSessionId)) {
                                RemotePlaybackClient.this.setSessionId(null);
                            }
                        }
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, callback, data);
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String error, Bundle data) {
                RemotePlaybackClient.this.handleError(intent, callback, error, data);
            }
        });
    }

    void adoptSession(String sessionId) {
        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    void handleInvalidResult(Intent intent, ActionCallback callback, Bundle data) {
        Log.w(TAG, "Received invalid result data from " + intent.getAction() + ": data=" + bundleToString(data));
        callback.onError(null, 0, data);
    }

    void handleError(Intent intent, ActionCallback callback, String error, Bundle data) {
        int code;
        if (data != null) {
            code = data.getInt(MediaControlIntent.EXTRA_ERROR_CODE, 0);
        } else {
            code = 0;
        }
        if (DEBUG) {
            Log.w(TAG, "Received error from " + intent.getAction() + ": error=" + error + ", code=" + code + ", data=" + bundleToString(data));
        }
        callback.onError(error, code, data);
    }

    private void detectFeatures() {
        boolean z = routeSupportsAction(MediaControlIntent.ACTION_PLAY) && routeSupportsAction(MediaControlIntent.ACTION_SEEK) && routeSupportsAction(MediaControlIntent.ACTION_GET_STATUS) && routeSupportsAction(MediaControlIntent.ACTION_PAUSE) && routeSupportsAction(MediaControlIntent.ACTION_RESUME) && routeSupportsAction(MediaControlIntent.ACTION_STOP);
        this.mRouteSupportsRemotePlayback = z;
        this.mRouteSupportsQueuing = z && routeSupportsAction(MediaControlIntent.ACTION_ENQUEUE) && routeSupportsAction(MediaControlIntent.ACTION_REMOVE);
        this.mRouteSupportsSessionManagement = this.mRouteSupportsRemotePlayback && routeSupportsAction(MediaControlIntent.ACTION_START_SESSION) && routeSupportsAction(MediaControlIntent.ACTION_GET_SESSION_STATUS) && routeSupportsAction(MediaControlIntent.ACTION_END_SESSION);
        this.mRouteSupportsMessaging = doesRouteSupportMessaging();
    }

    private boolean routeSupportsAction(String action) {
        return this.mRoute.supportsControlAction(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK, action);
    }

    private boolean doesRouteSupportMessaging() {
        for (IntentFilter filter : this.mRoute.getControlFilters()) {
            if (filter.hasAction(MediaControlIntent.ACTION_SEND_MESSAGE)) {
                return true;
            }
        }
        return false;
    }

    private void throwIfRemotePlaybackNotSupported() {
        if (!this.mRouteSupportsRemotePlayback) {
            throw new UnsupportedOperationException("The route does not support remote playback.");
        }
    }

    private void throwIfQueuingNotSupported() {
        if (!this.mRouteSupportsQueuing) {
            throw new UnsupportedOperationException("The route does not support queuing.");
        }
    }

    private void throwIfSessionManagementNotSupported() {
        if (!this.mRouteSupportsSessionManagement) {
            throw new UnsupportedOperationException("The route does not support session management.");
        }
    }

    private void throwIfMessageNotSupported() {
        if (!this.mRouteSupportsMessaging) {
            throw new UnsupportedOperationException("The route does not support message.");
        }
    }

    private void throwIfNoCurrentSession() {
        if (this.mSessionId == null) {
            throw new IllegalStateException("There is no current session.");
        }
    }

    static String inferMissingResult(String request, String result) {
        if (result == null) {
            return request;
        }
        if (request == null || request.equals(result)) {
            return result;
        }
        return null;
    }

    private static void logRequest(Intent intent) {
        if (DEBUG) {
            Log.d(TAG, "Sending request: " + intent);
        }
    }

    static String bundleToString(Bundle bundle) {
        if (bundle != null) {
            bundle.size();
            return bundle.toString();
        }
        return "null";
    }

    private final class ActionReceiver extends BroadcastReceiver {
        public static final String ACTION_ITEM_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_ITEM_STATUS_CHANGED";
        public static final String ACTION_MESSAGE_RECEIVED = "androidx.mediarouter.media.actions.ACTION_MESSAGE_RECEIVED";
        public static final String ACTION_SESSION_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_SESSION_STATUS_CHANGED";

        ActionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String sessionId = intent.getStringExtra(MediaControlIntent.EXTRA_SESSION_ID);
            if (sessionId == null || !sessionId.equals(RemotePlaybackClient.this.mSessionId)) {
                Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing or invalid session id: sessionId=" + sessionId);
                return;
            }
            MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(intent.getBundleExtra(MediaControlIntent.EXTRA_SESSION_STATUS));
            String action = intent.getAction();
            if (action.equals(ACTION_ITEM_STATUS_CHANGED)) {
                String itemId = intent.getStringExtra(MediaControlIntent.EXTRA_ITEM_ID);
                if (itemId == null) {
                    Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing item id.");
                    return;
                }
                MediaItemStatus itemStatus = MediaItemStatus.fromBundle(intent.getBundleExtra(MediaControlIntent.EXTRA_ITEM_STATUS));
                if (itemStatus == null) {
                    Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing item status.");
                    return;
                }
                if (RemotePlaybackClient.DEBUG) {
                    Log.d(RemotePlaybackClient.TAG, "Received item status callback: sessionId=" + sessionId + ", sessionStatus=" + sessionStatus + ", itemId=" + itemId + ", itemStatus=" + itemStatus);
                }
                if (RemotePlaybackClient.this.mStatusCallback != null) {
                    RemotePlaybackClient.this.mStatusCallback.onItemStatusChanged(intent.getExtras(), sessionId, sessionStatus, itemId, itemStatus);
                    return;
                }
                return;
            }
            if (action.equals(ACTION_SESSION_STATUS_CHANGED)) {
                if (sessionStatus == null) {
                    Log.w(RemotePlaybackClient.TAG, "Discarding spurious media status callback with missing session status.");
                    return;
                }
                if (RemotePlaybackClient.DEBUG) {
                    Log.d(RemotePlaybackClient.TAG, "Received session status callback: sessionId=" + sessionId + ", sessionStatus=" + sessionStatus);
                }
                if (RemotePlaybackClient.this.mStatusCallback != null) {
                    RemotePlaybackClient.this.mStatusCallback.onSessionStatusChanged(intent.getExtras(), sessionId, sessionStatus);
                    return;
                }
                return;
            }
            if (action.equals(ACTION_MESSAGE_RECEIVED)) {
                if (RemotePlaybackClient.DEBUG) {
                    Log.d(RemotePlaybackClient.TAG, "Received message callback: sessionId=" + sessionId);
                }
                if (RemotePlaybackClient.this.mOnMessageReceivedListener != null) {
                    RemotePlaybackClient.this.mOnMessageReceivedListener.onMessageReceived(sessionId, intent.getBundleExtra(MediaControlIntent.EXTRA_MESSAGE));
                }
            }
        }
    }

    public static abstract class StatusCallback {
        public void onItemStatusChanged(Bundle data, String sessionId, MediaSessionStatus sessionStatus, String itemId, MediaItemStatus itemStatus) {
        }

        public void onSessionStatusChanged(Bundle data, String sessionId, MediaSessionStatus sessionStatus) {
        }

        public void onSessionChanged(String sessionId) {
        }
    }

    public static abstract class ActionCallback {
        public void onError(String error, int code, Bundle data) {
        }
    }

    public static abstract class ItemActionCallback extends ActionCallback {
        public void onResult(Bundle data, String sessionId, MediaSessionStatus sessionStatus, String itemId, MediaItemStatus itemStatus) {
        }
    }

    public static abstract class SessionActionCallback extends ActionCallback {
        public void onResult(Bundle data, String sessionId, MediaSessionStatus sessionStatus) {
        }
    }
}
