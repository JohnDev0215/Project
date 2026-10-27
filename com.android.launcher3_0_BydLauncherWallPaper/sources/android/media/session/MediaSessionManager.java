package android.media.session;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.media.IRemoteVolumeController;
import android.media.Session2Token;
import android.media.session.IActiveSessionsListener;
import android.media.session.IOnMediaKeyEventDispatchedListener;
import android.media.session.IOnMediaKeyEventSessionChangedListener;
import android.media.session.IOnMediaKeyListener;
import android.media.session.IOnVolumeKeyLongPressListener;
import android.media.session.ISession2TokensListener;
import android.media.session.ISessionManager;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaSessionManager {
    public static final int RESULT_MEDIA_KEY_HANDLED = 1;
    public static final int RESULT_MEDIA_KEY_NOT_HANDLED = 0;
    private static final String TAG = "SessionManager";
    private Context mContext;
    private MediaSession.Token mCurMediaKeyEventSession;
    private String mCurMediaKeyEventSessionPackage;
    private final OnMediaKeyEventDispatchedListenerStub mOnMediaKeyEventDispatchedListenerStub;
    private final OnMediaKeyEventSessionChangedListenerStub mOnMediaKeyEventSessionChangedListenerStub;
    private OnMediaKeyListenerImpl mOnMediaKeyListener;
    private OnVolumeKeyLongPressListenerImpl mOnVolumeKeyLongPressListener;
    private final Object mLock = new Object();
    private final ArrayMap<OnActiveSessionsChangedListener, SessionsChangedWrapper> mListeners = new ArrayMap<>();
    private final ArrayMap<OnSession2TokensChangedListener, Session2TokensChangedWrapper> mSession2TokensListeners = new ArrayMap<>();
    private final Map<OnMediaKeyEventDispatchedListener, Executor> mOnMediaKeyEventDispatchedListeners = new HashMap();
    private final Map<OnMediaKeyEventSessionChangedListener, Executor> mMediaKeyEventSessionChangedCallbacks = new HashMap();
    private final ISessionManager mService = ISessionManager.Stub.asInterface(ServiceManager.getService(Context.MEDIA_SESSION_SERVICE));

    public interface OnActiveSessionsChangedListener {
        void onActiveSessionsChanged(List<MediaController> list);
    }

    @SystemApi
    public interface OnMediaKeyEventDispatchedListener {
        void onMediaKeyEventDispatched(KeyEvent keyEvent, String str, MediaSession.Token token);
    }

    @SystemApi
    public interface OnMediaKeyEventSessionChangedListener {
        void onMediaKeyEventSessionChanged(String str, MediaSession.Token token);
    }

    @SystemApi
    public interface OnMediaKeyListener {
        boolean onMediaKey(KeyEvent keyEvent);
    }

    public interface OnSession2TokensChangedListener {
        void onSession2TokensChanged(List<Session2Token> list);
    }

    @SystemApi
    public interface OnVolumeKeyLongPressListener {
        void onVolumeKeyLongPress(KeyEvent keyEvent);
    }

    public MediaSessionManager(Context context) {
        this.mOnMediaKeyEventDispatchedListenerStub = new OnMediaKeyEventDispatchedListenerStub();
        this.mOnMediaKeyEventSessionChangedListenerStub = new OnMediaKeyEventSessionChangedListenerStub();
        this.mContext = context;
    }

    public ISession createSession(MediaSession.CallbackStub callbackStub, String str, Bundle bundle) {
        try {
            return this.mService.createSession(this.mContext.getPackageName(), callbackStub, str, bundle, UserHandle.myUserId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifySession2Created(Session2Token session2Token) {
        if (session2Token == null) {
            throw new IllegalArgumentException("token shouldn't be null");
        }
        if (session2Token.getType() != 0) {
            throw new IllegalArgumentException("token's type should be TYPE_SESSION");
        }
        try {
            this.mService.notifySession2Created(session2Token);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public List<MediaController> getActiveSessions(ComponentName componentName) {
        return getActiveSessionsForUser(componentName, UserHandle.myUserId());
    }

    public List<MediaController> getActiveSessionsForUser(ComponentName componentName, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            List<MediaSession.Token> sessions = this.mService.getSessions(componentName, i);
            int size = sessions.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new MediaController(this.mContext, sessions.get(i2)));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get active sessions: ", e);
        }
        return arrayList;
    }

    public List<Session2Token> getSession2Tokens() {
        return getSession2Tokens(UserHandle.myUserId());
    }

    public List<Session2Token> getSession2Tokens(int i) {
        try {
            ParceledListSlice session2Tokens = this.mService.getSession2Tokens(i);
            return session2Tokens == null ? new ArrayList() : session2Tokens.getList();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get session tokens", e);
            return new ArrayList();
        }
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onActiveSessionsChangedListener, ComponentName componentName) {
        addOnActiveSessionsChangedListener(onActiveSessionsChangedListener, componentName, null);
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onActiveSessionsChangedListener, ComponentName componentName, Handler handler) {
        addOnActiveSessionsChangedListener(onActiveSessionsChangedListener, componentName, UserHandle.myUserId(), handler);
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onActiveSessionsChangedListener, ComponentName componentName, int i, Handler handler) {
        if (onActiveSessionsChangedListener == null) {
            throw new IllegalArgumentException("listener may not be null");
        }
        if (handler == null) {
            handler = new Handler();
        }
        synchronized (this.mLock) {
            if (this.mListeners.get(onActiveSessionsChangedListener) != null) {
                Log.w(TAG, "Attempted to add session listener twice, ignoring.");
                return;
            }
            SessionsChangedWrapper sessionsChangedWrapper = new SessionsChangedWrapper(this.mContext, onActiveSessionsChangedListener, handler);
            try {
                this.mService.addSessionsListener(sessionsChangedWrapper.mStub, componentName, i);
                this.mListeners.put(onActiveSessionsChangedListener, sessionsChangedWrapper);
            } catch (RemoteException e) {
                Log.e(TAG, "Error in addOnActiveSessionsChangedListener.", e);
            }
        }
    }

    public void removeOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onActiveSessionsChangedListener) {
        if (onActiveSessionsChangedListener == null) {
            throw new IllegalArgumentException("listener may not be null");
        }
        synchronized (this.mLock) {
            SessionsChangedWrapper sessionsChangedWrapperRemove = this.mListeners.remove(onActiveSessionsChangedListener);
            if (sessionsChangedWrapperRemove != null) {
                try {
                    try {
                        this.mService.removeSessionsListener(sessionsChangedWrapperRemove.mStub);
                    } catch (RemoteException e) {
                        Log.e(TAG, "Error in removeOnActiveSessionsChangedListener.", e);
                    }
                } finally {
                    sessionsChangedWrapperRemove.release();
                }
            }
        }
    }

    public void addOnSession2TokensChangedListener(OnSession2TokensChangedListener onSession2TokensChangedListener) {
        addOnSession2TokensChangedListener(UserHandle.myUserId(), onSession2TokensChangedListener, new Handler());
    }

    public void addOnSession2TokensChangedListener(OnSession2TokensChangedListener onSession2TokensChangedListener, Handler handler) {
        addOnSession2TokensChangedListener(UserHandle.myUserId(), onSession2TokensChangedListener, handler);
    }

    public void addOnSession2TokensChangedListener(int i, OnSession2TokensChangedListener onSession2TokensChangedListener, Handler handler) {
        if (onSession2TokensChangedListener == null) {
            throw new IllegalArgumentException("listener shouldn't be null");
        }
        synchronized (this.mLock) {
            if (this.mSession2TokensListeners.get(onSession2TokensChangedListener) != null) {
                Log.w(TAG, "Attempted to add session listener twice, ignoring.");
                return;
            }
            Session2TokensChangedWrapper session2TokensChangedWrapper = new Session2TokensChangedWrapper(onSession2TokensChangedListener, handler);
            try {
                this.mService.addSession2TokensListener(session2TokensChangedWrapper.getStub(), i);
                this.mSession2TokensListeners.put(onSession2TokensChangedListener, session2TokensChangedWrapper);
            } catch (RemoteException e) {
                Log.e(TAG, "Error in addSessionTokensListener.", e);
                e.rethrowFromSystemServer();
            }
        }
    }

    public void removeOnSession2TokensChangedListener(OnSession2TokensChangedListener onSession2TokensChangedListener) {
        Session2TokensChangedWrapper session2TokensChangedWrapperRemove;
        if (onSession2TokensChangedListener == null) {
            throw new IllegalArgumentException("listener may not be null");
        }
        synchronized (this.mLock) {
            session2TokensChangedWrapperRemove = this.mSession2TokensListeners.remove(onSession2TokensChangedListener);
        }
        if (session2TokensChangedWrapperRemove != null) {
            try {
                this.mService.removeSession2TokensListener(session2TokensChangedWrapperRemove.getStub());
            } catch (RemoteException e) {
                Log.e(TAG, "Error in removeSessionTokensListener.", e);
                e.rethrowFromSystemServer();
            }
        }
    }

    public void registerRemoteVolumeController(IRemoteVolumeController iRemoteVolumeController) {
        try {
            this.mService.registerRemoteVolumeController(iRemoteVolumeController);
        } catch (RemoteException e) {
            Log.e(TAG, "Error in registerRemoteVolumeController.", e);
        }
    }

    public void unregisterRemoteVolumeController(IRemoteVolumeController iRemoteVolumeController) {
        try {
            this.mService.unregisterRemoteVolumeController(iRemoteVolumeController);
        } catch (RemoteException e) {
            Log.e(TAG, "Error in unregisterRemoteVolumeController.", e);
        }
    }

    public void dispatchMediaKeyEvent(KeyEvent keyEvent) {
        dispatchMediaKeyEvent(keyEvent, false);
    }

    public void dispatchMediaKeyEvent(KeyEvent keyEvent, boolean z) {
        dispatchMediaKeyEventInternal(false, keyEvent, z);
    }

    public void dispatchMediaKeyEventAsSystemService(KeyEvent keyEvent) {
        dispatchMediaKeyEventInternal(true, keyEvent, false);
    }

    private void dispatchMediaKeyEventInternal(boolean z, KeyEvent keyEvent, boolean z2) {
        try {
            this.mService.dispatchMediaKeyEvent(this.mContext.getPackageName(), z, keyEvent, z2);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send key event.", e);
        }
    }

    public boolean dispatchMediaKeyEventAsSystemService(MediaSession.Token token, KeyEvent keyEvent) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken shouldn't be null");
        }
        if (keyEvent == null) {
            throw new IllegalArgumentException("keyEvent shouldn't be null");
        }
        if (!KeyEvent.isMediaSessionKey(keyEvent.getKeyCode())) {
            return false;
        }
        try {
            return this.mService.dispatchMediaKeyEventToSessionAsSystemService(this.mContext.getPackageName(), token, keyEvent);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send key event.", e);
            return false;
        }
    }

    public void dispatchVolumeKeyEvent(KeyEvent keyEvent, int i, boolean z) {
        dispatchVolumeKeyEventInternal(false, keyEvent, i, z);
    }

    public void dispatchVolumeKeyEventAsSystemService(KeyEvent keyEvent, int i) {
        dispatchVolumeKeyEventInternal(true, keyEvent, i, false);
    }

    private void dispatchVolumeKeyEventInternal(boolean z, KeyEvent keyEvent, int i, boolean z2) {
        try {
            this.mService.dispatchVolumeKeyEvent(this.mContext.getPackageName(), this.mContext.getOpPackageName(), z, keyEvent, i, z2);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send volume key event.", e);
        }
    }

    public void dispatchVolumeKeyEventAsSystemService(MediaSession.Token token, KeyEvent keyEvent) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken shouldn't be null");
        }
        if (keyEvent == null) {
            throw new IllegalArgumentException("keyEvent shouldn't be null");
        }
        try {
            this.mService.dispatchVolumeKeyEventToSessionAsSystemService(this.mContext.getPackageName(), this.mContext.getOpPackageName(), token, keyEvent);
        } catch (RemoteException e) {
            Log.wtf(TAG, "Error calling dispatchVolumeKeyEventAsSystemService", e);
        }
    }

    public void dispatchAdjustVolume(int i, int i2, int i3) {
        try {
            this.mService.dispatchAdjustVolume(this.mContext.getPackageName(), this.mContext.getOpPackageName(), i, i2, i3);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send adjust volume.", e);
        }
    }

    public boolean isTrustedForMediaControl(RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo == null) {
            throw new IllegalArgumentException("userInfo may not be null");
        }
        if (remoteUserInfo.getPackageName() == null) {
            return false;
        }
        try {
            return this.mService.isTrusted(remoteUserInfo.getPackageName(), remoteUserInfo.getPid(), remoteUserInfo.getUid());
        } catch (RemoteException e) {
            Log.wtf(TAG, "Cannot communicate with the service.", e);
            return false;
        }
    }

    public boolean isGlobalPriorityActive() {
        try {
            return this.mService.isGlobalPriorityActive();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to check if the global priority is active.", e);
            return false;
        }
    }

    @SystemApi
    public void setOnVolumeKeyLongPressListener(OnVolumeKeyLongPressListener onVolumeKeyLongPressListener, Handler handler) {
        synchronized (this.mLock) {
            try {
                try {
                    if (onVolumeKeyLongPressListener == null) {
                        this.mOnVolumeKeyLongPressListener = null;
                        this.mService.setOnVolumeKeyLongPressListener(null);
                    } else {
                        if (handler == null) {
                            handler = new Handler();
                        }
                        OnVolumeKeyLongPressListenerImpl onVolumeKeyLongPressListenerImpl = new OnVolumeKeyLongPressListenerImpl(onVolumeKeyLongPressListener, handler);
                        this.mOnVolumeKeyLongPressListener = onVolumeKeyLongPressListenerImpl;
                        this.mService.setOnVolumeKeyLongPressListener(onVolumeKeyLongPressListenerImpl);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to set volume key long press listener", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SystemApi
    public void setOnMediaKeyListener(OnMediaKeyListener onMediaKeyListener, Handler handler) {
        synchronized (this.mLock) {
            try {
                try {
                    if (onMediaKeyListener == null) {
                        this.mOnMediaKeyListener = null;
                        this.mService.setOnMediaKeyListener(null);
                    } else {
                        if (handler == null) {
                            handler = new Handler();
                        }
                        OnMediaKeyListenerImpl onMediaKeyListenerImpl = new OnMediaKeyListenerImpl(onMediaKeyListener, handler);
                        this.mOnMediaKeyListener = onMediaKeyListenerImpl;
                        this.mService.setOnMediaKeyListener(onMediaKeyListenerImpl);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to set media key listener", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SystemApi
    public void addOnMediaKeyEventDispatchedListener(Executor executor, OnMediaKeyEventDispatchedListener onMediaKeyEventDispatchedListener) {
        Objects.requireNonNull(executor, "executor shouldn't be null");
        Objects.requireNonNull(onMediaKeyEventDispatchedListener, "listener shouldn't be null");
        synchronized (this.mLock) {
            try {
                this.mOnMediaKeyEventDispatchedListeners.put(onMediaKeyEventDispatchedListener, executor);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to set media key listener", e);
            }
            if (this.mOnMediaKeyEventDispatchedListeners.size() == 1) {
                this.mService.addOnMediaKeyEventDispatchedListener(this.mOnMediaKeyEventDispatchedListenerStub);
            }
        }
    }

    @SystemApi
    public void removeOnMediaKeyEventDispatchedListener(OnMediaKeyEventDispatchedListener onMediaKeyEventDispatchedListener) {
        Objects.requireNonNull(onMediaKeyEventDispatchedListener, "listener shouldn't be null");
        synchronized (this.mLock) {
            try {
                this.mOnMediaKeyEventDispatchedListeners.remove(onMediaKeyEventDispatchedListener);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to set media key event dispatched listener", e);
            }
            if (this.mOnMediaKeyEventDispatchedListeners.size() == 0) {
                this.mService.removeOnMediaKeyEventDispatchedListener(this.mOnMediaKeyEventDispatchedListenerStub);
            }
        }
    }

    @SystemApi
    public void addOnMediaKeyEventSessionChangedListener(Executor executor, final OnMediaKeyEventSessionChangedListener onMediaKeyEventSessionChangedListener) {
        Objects.requireNonNull(executor, "executor shouldn't be null");
        Objects.requireNonNull(onMediaKeyEventSessionChangedListener, "listener shouldn't be null");
        synchronized (this.mLock) {
            try {
                this.mMediaKeyEventSessionChangedCallbacks.put(onMediaKeyEventSessionChangedListener, executor);
                executor.execute(new Runnable() { // from class: android.media.session.-$$Lambda$MediaSessionManager$iqju8rOJhjMxRhGFL3SEcFJFjjU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$addOnMediaKeyEventSessionChangedListener$0$MediaSessionManager(onMediaKeyEventSessionChangedListener);
                    }
                });
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to set media key listener", e);
            }
            if (this.mMediaKeyEventSessionChangedCallbacks.size() == 1) {
                this.mService.addOnMediaKeyEventSessionChangedListener(this.mOnMediaKeyEventSessionChangedListenerStub);
            }
        }
    }

    public /* synthetic */ void lambda$addOnMediaKeyEventSessionChangedListener$0$MediaSessionManager(OnMediaKeyEventSessionChangedListener onMediaKeyEventSessionChangedListener) {
        onMediaKeyEventSessionChangedListener.onMediaKeyEventSessionChanged(this.mCurMediaKeyEventSessionPackage, this.mCurMediaKeyEventSession);
    }

    @SystemApi
    public void removeOnMediaKeyEventSessionChangedListener(OnMediaKeyEventSessionChangedListener onMediaKeyEventSessionChangedListener) {
        Objects.requireNonNull(onMediaKeyEventSessionChangedListener, "listener shouldn't be null");
        synchronized (this.mLock) {
            try {
                this.mMediaKeyEventSessionChangedCallbacks.remove(onMediaKeyEventSessionChangedListener);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to set media key listener", e);
            }
            if (this.mMediaKeyEventSessionChangedCallbacks.size() == 0) {
                this.mService.removeOnMediaKeyEventSessionChangedListener(this.mOnMediaKeyEventSessionChangedListenerStub);
            }
        }
    }

    public void setCustomMediaKeyDispatcherForTesting(String str) {
        try {
            this.mService.setCustomMediaKeyDispatcherForTesting(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to set custom media key dispatcher name", e);
        }
    }

    public void setCustomSessionPolicyProviderForTesting(String str) {
        try {
            this.mService.setCustomSessionPolicyProviderForTesting(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to set custom session policy provider name", e);
        }
    }

    public int getSessionPolicies(MediaSession.Token token) {
        try {
            return this.mService.getSessionPolicies(token);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get session policies", e);
            return 0;
        }
    }

    public void setSessionPolicies(MediaSession.Token token, int i) {
        try {
            this.mService.setSessionPolicies(token, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to set session policies", e);
        }
    }

    public static final class RemoteUserInfo {
        private final String mPackageName;
        private final int mPid;
        private final int mUid;

        public RemoteUserInfo(String str, int i, int i2) {
            this.mPackageName = str;
            this.mPid = i;
            this.mUid = i2;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public int getPid() {
            return this.mPid;
        }

        public int getUid() {
            return this.mUid;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof RemoteUserInfo)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            RemoteUserInfo remoteUserInfo = (RemoteUserInfo) obj;
            return TextUtils.equals(this.mPackageName, remoteUserInfo.mPackageName) && this.mPid == remoteUserInfo.mPid && this.mUid == remoteUserInfo.mUid;
        }

        public int hashCode() {
            return Objects.hash(this.mPackageName, Integer.valueOf(this.mPid), Integer.valueOf(this.mUid));
        }
    }

    private static final class SessionsChangedWrapper {
        private Context mContext;
        private Handler mHandler;
        private OnActiveSessionsChangedListener mListener;
        private final IActiveSessionsListener.Stub mStub = new IActiveSessionsListener.Stub() { // from class: android.media.session.MediaSessionManager.SessionsChangedWrapper.1
            @Override // android.media.session.IActiveSessionsListener
            public void onActiveSessionsChanged(final List<MediaSession.Token> list) {
                Handler handler = SessionsChangedWrapper.this.mHandler;
                if (handler != null) {
                    handler.post(new Runnable() { // from class: android.media.session.MediaSessionManager.SessionsChangedWrapper.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Context context = SessionsChangedWrapper.this.mContext;
                            if (context != null) {
                                ArrayList arrayList = new ArrayList();
                                int size = list.size();
                                for (int i = 0; i < size; i++) {
                                    arrayList.add(new MediaController(context, (MediaSession.Token) list.get(i)));
                                }
                                OnActiveSessionsChangedListener onActiveSessionsChangedListener = SessionsChangedWrapper.this.mListener;
                                if (onActiveSessionsChangedListener != null) {
                                    onActiveSessionsChangedListener.onActiveSessionsChanged(arrayList);
                                }
                            }
                        }
                    });
                }
            }
        };

        public SessionsChangedWrapper(Context context, OnActiveSessionsChangedListener onActiveSessionsChangedListener, Handler handler) {
            this.mContext = context;
            this.mListener = onActiveSessionsChangedListener;
            this.mHandler = handler;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void release() {
            this.mListener = null;
            this.mContext = null;
            this.mHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class Session2TokensChangedWrapper {
        private final Handler mHandler;
        private final OnSession2TokensChangedListener mListener;
        private final ISession2TokensListener.Stub mStub = new AnonymousClass1();

        /* JADX INFO: renamed from: android.media.session.MediaSessionManager$Session2TokensChangedWrapper$1, reason: invalid class name */
        class AnonymousClass1 extends ISession2TokensListener.Stub {
            AnonymousClass1() {
            }

            public /* synthetic */ void lambda$onSession2TokensChanged$0$MediaSessionManager$Session2TokensChangedWrapper$1(List list) {
                Session2TokensChangedWrapper.this.mListener.onSession2TokensChanged(list);
            }

            @Override // android.media.session.ISession2TokensListener
            public void onSession2TokensChanged(final List<Session2Token> list) {
                Session2TokensChangedWrapper.this.mHandler.post(new Runnable() { // from class: android.media.session.-$$Lambda$MediaSessionManager$Session2TokensChangedWrapper$1$xey3dGfZW2qEiasafnyndCk-bxc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSession2TokensChanged$0$MediaSessionManager$Session2TokensChangedWrapper$1(list);
                    }
                });
            }
        }

        Session2TokensChangedWrapper(OnSession2TokensChangedListener onSession2TokensChangedListener, Handler handler) {
            this.mListener = onSession2TokensChangedListener;
            this.mHandler = handler == null ? new Handler() : new Handler(handler.getLooper());
        }

        public ISession2TokensListener.Stub getStub() {
            return this.mStub;
        }
    }

    private static final class OnVolumeKeyLongPressListenerImpl extends IOnVolumeKeyLongPressListener.Stub {
        private Handler mHandler;
        private OnVolumeKeyLongPressListener mListener;

        public OnVolumeKeyLongPressListenerImpl(OnVolumeKeyLongPressListener onVolumeKeyLongPressListener, Handler handler) {
            this.mListener = onVolumeKeyLongPressListener;
            this.mHandler = handler;
        }

        @Override // android.media.session.IOnVolumeKeyLongPressListener
        public void onVolumeKeyLongPress(final KeyEvent keyEvent) {
            Handler handler;
            if (this.mListener == null || (handler = this.mHandler) == null) {
                Log.w(MediaSessionManager.TAG, "Failed to call volume key long-press listener. Either mListener or mHandler is null");
            } else {
                handler.post(new Runnable() { // from class: android.media.session.MediaSessionManager.OnVolumeKeyLongPressListenerImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        OnVolumeKeyLongPressListenerImpl.this.mListener.onVolumeKeyLongPress(keyEvent);
                    }
                });
            }
        }
    }

    private static final class OnMediaKeyListenerImpl extends IOnMediaKeyListener.Stub {
        private Handler mHandler;
        private OnMediaKeyListener mListener;

        public OnMediaKeyListenerImpl(OnMediaKeyListener onMediaKeyListener, Handler handler) {
            this.mListener = onMediaKeyListener;
            this.mHandler = handler;
        }

        @Override // android.media.session.IOnMediaKeyListener
        public void onMediaKey(final KeyEvent keyEvent, final ResultReceiver resultReceiver) {
            Handler handler;
            if (this.mListener == null || (handler = this.mHandler) == null) {
                Log.w(MediaSessionManager.TAG, "Failed to call media key listener. Either mListener or mHandler is null");
            } else {
                handler.post(new Runnable() { // from class: android.media.session.MediaSessionManager.OnMediaKeyListenerImpl.1
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zOnMediaKey = OnMediaKeyListenerImpl.this.mListener.onMediaKey(keyEvent);
                        Log.d(MediaSessionManager.TAG, "The media key listener is returned " + zOnMediaKey);
                        ResultReceiver resultReceiver2 = resultReceiver;
                        if (resultReceiver2 != null) {
                            resultReceiver2.send(zOnMediaKey ? 1 : 0, null);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class OnMediaKeyEventDispatchedListenerStub extends IOnMediaKeyEventDispatchedListener.Stub {
        private OnMediaKeyEventDispatchedListenerStub() {
        }

        @Override // android.media.session.IOnMediaKeyEventDispatchedListener
        public void onMediaKeyEventDispatched(final KeyEvent keyEvent, final String str, final MediaSession.Token token) {
            synchronized (MediaSessionManager.this.mLock) {
                for (final Map.Entry entry : MediaSessionManager.this.mOnMediaKeyEventDispatchedListeners.entrySet()) {
                    ((Executor) entry.getValue()).execute(new Runnable() { // from class: android.media.session.-$$Lambda$MediaSessionManager$OnMediaKeyEventDispatchedListenerStub$fRJYYw4OPvg5UsezHsJA5X6D-Yw
                        @Override // java.lang.Runnable
                        public final void run() {
                            ((MediaSessionManager.OnMediaKeyEventDispatchedListener) entry.getKey()).onMediaKeyEventDispatched(keyEvent, str, token);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class OnMediaKeyEventSessionChangedListenerStub extends IOnMediaKeyEventSessionChangedListener.Stub {
        private OnMediaKeyEventSessionChangedListenerStub() {
        }

        @Override // android.media.session.IOnMediaKeyEventSessionChangedListener
        public void onMediaKeyEventSessionChanged(final String str, final MediaSession.Token token) {
            synchronized (MediaSessionManager.this.mLock) {
                MediaSessionManager.this.mCurMediaKeyEventSessionPackage = str;
                MediaSessionManager.this.mCurMediaKeyEventSession = token;
                for (final Map.Entry entry : MediaSessionManager.this.mMediaKeyEventSessionChangedCallbacks.entrySet()) {
                    ((Executor) entry.getValue()).execute(new Runnable() { // from class: android.media.session.-$$Lambda$MediaSessionManager$OnMediaKeyEventSessionChangedListenerStub$L7Q6nNmfdZREp_dB3bvvN0BNeVQ
                        @Override // java.lang.Runnable
                        public final void run() {
                            ((MediaSessionManager.OnMediaKeyEventSessionChangedListener) entry.getKey()).onMediaKeyEventSessionChanged(str, token);
                        }
                    });
                }
            }
        }
    }
}
