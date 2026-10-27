package com.android.car;

import android.app.ActivityManager;
import android.car.media.ICarMedia;
import android.car.media.ICarMediaSourceListener;
import android.car.user.CarUserManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import com.android.car.user.CarUserService;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class CarMediaService extends ICarMedia.Stub implements CarServiceBase {
    private static final int AUTOPLAY_CONFIG_ALWAYS = 1;
    private static final int AUTOPLAY_CONFIG_NEVER = 0;
    private static final int AUTOPLAY_CONFIG_RETAIN_PER_SOURCE = 2;
    private static final int AUTOPLAY_CONFIG_RETAIN_PREVIOUS = 3;
    private static final String COMPONENT_NAME_SEPARATOR = ",";
    private static final String EXTRA_AUTOPLAY = "com.android.car.media.autoplay";
    private static final String MEDIA_CONNECTION_ACTION = "com.android.car.media.MEDIA_CONNECTION";
    private static final int MEDIA_SOURCE_MODES = 2;
    private static final String PLAYBACK_STATE_KEY = "playback_state";
    private static final String SHARED_PREF = "com.android.car.media.car_media_service";
    private static final String SOURCE_KEY = "media_source_component";
    private static final String SOURCE_KEY_SEPARATOR = "_";
    private MediaController mActiveUserMediaController;
    private final Context mContext;
    private int mCurrentPlaybackState;
    private boolean mIndependentPlaybackConfig;
    private boolean mIsPackageUpdateReceiverRegistered;
    private final MediaSessionManager mMediaSessionManager;
    private final IntentFilter mPackageUpdateFilter;
    private boolean mPendingInit;
    private int mPlayOnBootConfig;
    private int mPlayOnMediaSourceChangedConfig;
    private SessionChangedListener mSessionsListener;
    private SharedPreferences mSharedPrefs;
    private final UserManager mUserManager;
    private final CarUserService mUserService;
    private final MediaSessionUpdater mMediaSessionUpdater = new MediaSessionUpdater();
    private ComponentName[] mPrimaryMediaComponents = new ComponentName[2];
    private final RemoteCallbackList<ICarMediaSourceListener>[] mMediaSourceListeners = new RemoteCallbackList[2];
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mHandler = new Handler(this.mHandlerThread.getLooper());
    private final Object mLock = new Object();
    private ComponentName[] mRemovedMediaSourceComponents = new ComponentName[2];
    private final BroadcastReceiver mPackageUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.CarMediaService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getData() == null) {
                return;
            }
            String intentPackage = intent.getData().getSchemeSpecificPart();
            if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                synchronized (CarMediaService.this.mLock) {
                    for (int i = 0; i < 2; i++) {
                        if (CarMediaService.this.mPrimaryMediaComponents[i] != null && CarMediaService.this.mPrimaryMediaComponents[i].getPackageName().equals(intentPackage)) {
                            if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                                for (ComponentName component : CarMediaService.this.getLastMediaSources(i)) {
                                    if (!CarMediaService.this.mPrimaryMediaComponents[i].getPackageName().equals(component.getPackageName())) {
                                        CarMediaService.this.mRemovedMediaSourceComponents[i] = CarMediaService.this.mPrimaryMediaComponents[i];
                                        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
                                            Log.d(CarLog.TAG_MEDIA, "temporarily replacing updated media source " + CarMediaService.this.mPrimaryMediaComponents[i] + "with backup source: " + component);
                                        }
                                        CarMediaService.this.setPrimaryMediaSource(component, i);
                                        return;
                                    }
                                }
                                Log.e(CarLog.TAG_MEDIA, "No available backup media source");
                            } else {
                                if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
                                    Log.d(CarLog.TAG_MEDIA, "replacing removed media source " + CarMediaService.this.mPrimaryMediaComponents[i] + "with backup source: " + CarMediaService.this.getLastMediaSource(i));
                                }
                                CarMediaService.this.mRemovedMediaSourceComponents[i] = null;
                                CarMediaService.this.setPrimaryMediaSource(CarMediaService.this.getLastMediaSource(i), i);
                            }
                        }
                    }
                    return;
                }
            }
            if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction()) || "android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                for (int i2 = 0; i2 < 2; i2++) {
                    if (CarMediaService.this.mRemovedMediaSourceComponents[i2] != null && CarMediaService.this.mRemovedMediaSourceComponents[i2].getPackageName().equals(intentPackage)) {
                        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
                            Log.d(CarLog.TAG_MEDIA, "restoring removed source: " + CarMediaService.this.mRemovedMediaSourceComponents[i2]);
                        }
                        CarMediaService carMediaService = CarMediaService.this;
                        carMediaService.setPrimaryMediaSource(carMediaService.mRemovedMediaSourceComponents[i2], i2);
                    }
                }
            }
        }
    };
    private final CarUserManager.UserLifecycleListener mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.-$$Lambda$CarMediaService$fzLdgpOrRrZ3gDOtQKx-iD3G7eY
        public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
            this.f$0.lambda$new$0$CarMediaService(userLifecycleEvent);
        }
    };
    private MediaController.Callback mMediaControllerCallback = new MediaController.Callback() { // from class: com.android.car.CarMediaService.2
        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState state) {
            if (!CarMediaService.this.isCurrentUserEphemeral()) {
                CarMediaService.this.savePlaybackState(state);
            }
        }
    };

    public /* synthetic */ void lambda$new$0$CarMediaService(CarUserManager.UserLifecycleEvent event) {
        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
            Log.d(CarLog.TAG_MEDIA, "CarMediaService.onEvent(" + event + ")");
        }
        int eventType = event.getEventType();
        if (eventType == 2) {
            maybeInitUser(event.getUserId());
        } else if (eventType == 4) {
            onUserUnlock(event.getUserId());
        }
    }

    public CarMediaService(Context context, CarUserService userService) {
        this.mContext = context;
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mMediaSessionManager = (MediaSessionManager) this.mContext.getSystemService(MediaSessionManager.class);
        this.mMediaSourceListeners[0] = new RemoteCallbackList<>();
        this.mMediaSourceListeners[1] = new RemoteCallbackList<>();
        this.mIndependentPlaybackConfig = this.mContext.getResources().getBoolean(R.bool.config_mediaSourceIndependentPlayback);
        IntentFilter intentFilter = new IntentFilter();
        this.mPackageUpdateFilter = intentFilter;
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        this.mPackageUpdateFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        this.mPackageUpdateFilter.addAction("android.intent.action.PACKAGE_ADDED");
        this.mPackageUpdateFilter.addDataScheme("package");
        this.mUserService = userService;
        userService.addUserLifecycleListener(this.mUserLifecycleListener);
        this.mPlayOnMediaSourceChangedConfig = this.mContext.getResources().getInteger(R.integer.config_mediaSourceChangedAutoplay);
        this.mPlayOnBootConfig = this.mContext.getResources().getInteger(R.integer.config_mediaBootAutoplay);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        int currentUser = ActivityManager.getCurrentUser();
        maybeInitUser(currentUser);
    }

    private void maybeInitUser(int userId) {
        if (userId == 0) {
            return;
        }
        if (this.mUserManager.isUserUnlocked(userId)) {
            initUser(userId);
        } else {
            this.mPendingInit = true;
        }
    }

    private void initUser(int userId) {
        ComponentName lastMediaSource;
        if (this.mSharedPrefs == null) {
            this.mSharedPrefs = this.mContext.getSharedPreferences(SHARED_PREF, 0);
        }
        if (this.mIsPackageUpdateReceiverRegistered) {
            this.mContext.unregisterReceiver(this.mPackageUpdateReceiver);
        }
        UserHandle currentUser = new UserHandle(userId);
        this.mContext.registerReceiverAsUser(this.mPackageUpdateReceiver, currentUser, this.mPackageUpdateFilter, null, null);
        this.mIsPackageUpdateReceiverRegistered = true;
        this.mPrimaryMediaComponents[0] = isCurrentUserEphemeral() ? getDefaultMediaSource() : getLastMediaSource(0);
        ComponentName[] componentNameArr = this.mPrimaryMediaComponents;
        if (!isCurrentUserEphemeral()) {
            lastMediaSource = getLastMediaSource(1);
        } else {
            lastMediaSource = getDefaultMediaSource();
        }
        componentNameArr[1] = lastMediaSource;
        this.mActiveUserMediaController = null;
        updateMediaSessionCallbackForCurrentUser();
        notifyListeners(0);
        notifyListeners(1);
        startMediaConnectorService(shouldStartPlayback(this.mPlayOnBootConfig), currentUser);
    }

    private void startMediaConnectorService(boolean startPlayback, UserHandle currentUser) {
        Intent serviceStart = new Intent(MEDIA_CONNECTION_ACTION);
        serviceStart.setPackage(this.mContext.getResources().getString(R.string.serviceMediaConnection));
        serviceStart.putExtra(EXTRA_AUTOPLAY, startPlayback);
        this.mContext.startForegroundServiceAsUser(serviceStart, currentUser);
    }

    private boolean sharedPrefsInitialized() {
        if (this.mSharedPrefs == null) {
            Log.e(CarLog.TAG_MEDIA, "SharedPreferences are not initialized!");
            String className = getClass().getName();
            for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
                String log = ste.toString();
                if (log.contains(className)) {
                    Log.e(CarLog.TAG_MEDIA, log);
                }
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCurrentUserEphemeral() {
        return this.mUserManager.getUserInfo(ActivityManager.getCurrentUser()).isEphemeral();
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        this.mMediaSessionUpdater.unregisterCallbacks();
        this.mUserService.removeUserLifecycleListener(this.mUserLifecycleListener);
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarMediaService*");
        StringBuilder sb = new StringBuilder();
        sb.append("\tCurrent playback media component: ");
        ComponentName[] componentNameArr = this.mPrimaryMediaComponents;
        sb.append(componentNameArr[0] == null ? "-" : componentNameArr[0].flattenToString());
        writer.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\tCurrent browse media component: ");
        ComponentName[] componentNameArr2 = this.mPrimaryMediaComponents;
        sb2.append(componentNameArr2[1] != null ? componentNameArr2[1].flattenToString() : "-");
        writer.println(sb2.toString());
        if (this.mActiveUserMediaController != null) {
            writer.println("\tCurrent media controller: " + this.mActiveUserMediaController.getPackageName());
            writer.println("\tCurrent browse service extra: " + getClassName(this.mActiveUserMediaController));
        }
        writer.println("\tNumber of active media sessions: " + this.mMediaSessionManager.getActiveSessionsForUser(null, ActivityManager.getCurrentUser()).size());
        writer.println("\tPlayback media source history: ");
        for (ComponentName name : getLastMediaSources(0)) {
            writer.println("\t" + name.flattenToString());
        }
        writer.println("\tBrowse media source history: ");
        for (ComponentName name2 : getLastMediaSources(1)) {
            writer.println("\t" + name2.flattenToString());
        }
    }

    public void setMediaSource(ComponentName componentName, int mode) {
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
            Log.d(CarLog.TAG_MEDIA, "Changing media source to: " + componentName.getPackageName());
        }
        setPrimaryMediaSource(componentName, mode);
    }

    public ComponentName getMediaSource(int mode) {
        ComponentName componentName;
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        synchronized (this.mLock) {
            componentName = this.mPrimaryMediaComponents[mode];
        }
        return componentName;
    }

    public void registerMediaSourceListener(ICarMediaSourceListener callback, int mode) {
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        synchronized (this.mLock) {
            this.mMediaSourceListeners[mode].register(callback);
        }
    }

    public void unregisterMediaSourceListener(ICarMediaSourceListener callback, int mode) {
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        synchronized (this.mLock) {
            this.mMediaSourceListeners[mode].unregister(callback);
        }
    }

    public List<ComponentName> getLastMediaSources(int mode) {
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        String key = getMediaSourceKey(mode);
        String serialized = this.mSharedPrefs.getString(key, "");
        return (List) getComponentNameList(serialized).stream().map(new Function() { // from class: com.android.car.-$$Lambda$CarMediaService$91Do4JGu_urhve_I7vfgSyGPbss
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ComponentName.unflattenFromString((String) obj);
            }
        }).collect(Collectors.toList());
    }

    public boolean isIndependentPlaybackConfig() {
        boolean z;
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        synchronized (this.mLock) {
            z = this.mIndependentPlaybackConfig;
        }
        return z;
    }

    public void setIndependentPlaybackConfig(boolean independent) {
        ICarImpl.assertPermission(this.mContext, "android.permission.MEDIA_CONTENT_CONTROL");
        synchronized (this.mLock) {
            this.mIndependentPlaybackConfig = independent;
        }
    }

    private void onUserUnlock(final int userId) {
        this.mMainHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarMediaService$o4kDP3-7l-T-9mA-xTPQ2q-Mf78
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onUserUnlock$2$CarMediaService(userId);
            }
        });
    }

    public /* synthetic */ void lambda$onUserUnlock$2$CarMediaService(int userId) {
        if (userId != 0 && userId == ActivityManager.getCurrentUser() && this.mPendingInit) {
            initUser(userId);
            this.mPendingInit = false;
            if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
                Log.d(CarLog.TAG_MEDIA, "User " + userId + " is now unlocked");
            }
        }
    }

    private void updateMediaSessionCallbackForCurrentUser() {
        SessionChangedListener sessionChangedListener = this.mSessionsListener;
        if (sessionChangedListener != null) {
            this.mMediaSessionManager.removeOnActiveSessionsChangedListener(sessionChangedListener);
        }
        SessionChangedListener sessionChangedListener2 = new SessionChangedListener(ActivityManager.getCurrentUser());
        this.mSessionsListener = sessionChangedListener2;
        this.mMediaSessionManager.addOnActiveSessionsChangedListener(sessionChangedListener2, null, ActivityManager.getCurrentUser(), null);
        this.mMediaSessionUpdater.registerCallbacks(this.mMediaSessionManager.getActiveSessionsForUser(null, ActivityManager.getCurrentUser()));
    }

    private void stopAndUnregisterCallback() {
        MediaController mediaController = this.mActiveUserMediaController;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.mMediaControllerCallback);
            if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
                Log.d(CarLog.TAG_MEDIA, "stopping " + this.mActiveUserMediaController.getPackageName());
            }
            MediaController.TransportControls controls = this.mActiveUserMediaController.getTransportControls();
            if (controls != null) {
                controls.stop();
                return;
            }
            Log.e(CarLog.TAG_MEDIA, "Can't stop playback, transport controls unavailable " + this.mActiveUserMediaController.getPackageName());
        }
    }

    private class SessionChangedListener implements MediaSessionManager.OnActiveSessionsChangedListener {
        private final int mCurrentUser;

        SessionChangedListener(int currentUser) {
            this.mCurrentUser = currentUser;
        }

        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public void onActiveSessionsChanged(List<MediaController> controllers) {
            if (ActivityManager.getCurrentUser() != this.mCurrentUser) {
                Log.e(CarLog.TAG_MEDIA, "Active session callback for old user: " + this.mCurrentUser);
                return;
            }
            CarMediaService.this.mMediaSessionUpdater.registerCallbacks(controllers);
        }
    }

    private class MediaControllerCallback extends MediaController.Callback {
        private final MediaController mMediaController;
        private int mPreviousPlaybackState;

        private MediaControllerCallback(MediaController mediaController) {
            this.mMediaController = mediaController;
            PlaybackState state = mediaController.getPlaybackState();
            this.mPreviousPlaybackState = state == null ? 0 : state.getState();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void register() {
            this.mMediaController.registerCallback(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unregister() {
            this.mMediaController.unregisterCallback(this);
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState state) {
            if (state.getState() == 3 && state.getState() != this.mPreviousPlaybackState) {
                ComponentName mediaSource = CarMediaService.this.getMediaSource(this.mMediaController.getPackageName(), CarMediaService.getClassName(this.mMediaController));
                if (mediaSource != null && !mediaSource.equals(CarMediaService.this.mPrimaryMediaComponents[0]) && Log.isLoggable(CarLog.TAG_MEDIA, 4)) {
                    Log.i(CarLog.TAG_MEDIA, "Changing media source due to playback state change: " + mediaSource.flattenToString());
                }
                CarMediaService.this.setPrimaryMediaSource(mediaSource, 0);
            }
            this.mPreviousPlaybackState = state.getState();
        }
    }

    private class MediaSessionUpdater {
        private Map<MediaSession.Token, MediaControllerCallback> mCallbacks;

        private MediaSessionUpdater() {
            this.mCallbacks = new HashMap();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void registerCallbacks(List<MediaController> newControllers) {
            List<MediaController> additions = new ArrayList<>(newControllers.size());
            Map<MediaSession.Token, MediaControllerCallback> updatedCallbacks = new HashMap<>(newControllers.size());
            for (MediaController controller : newControllers) {
                MediaSession.Token token = controller.getSessionToken();
                MediaControllerCallback callback = this.mCallbacks.get(token);
                if (callback == null) {
                    callback = new MediaControllerCallback(controller);
                    callback.register();
                    additions.add(controller);
                }
                updatedCallbacks.put(token, callback);
            }
            for (MediaSession.Token token2 : this.mCallbacks.keySet()) {
                if (!updatedCallbacks.containsKey(token2)) {
                    this.mCallbacks.get(token2).unregister();
                }
            }
            this.mCallbacks = updatedCallbacks;
            CarMediaService.this.updatePrimaryMediaSourceWithCurrentlyPlaying(additions);
            if (CarMediaService.this.mActiveUserMediaController == null) {
                CarMediaService.this.updateActiveMediaController(newControllers);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unregisterCallbacks() {
            for (Map.Entry<MediaSession.Token, MediaControllerCallback> entry : this.mCallbacks.entrySet()) {
                entry.getValue().unregister();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimaryMediaSource(ComponentName componentName, int mode) {
        synchronized (this.mLock) {
            if (this.mPrimaryMediaComponents[mode] == null || !this.mPrimaryMediaComponents[mode].equals(componentName)) {
                if (!this.mIndependentPlaybackConfig) {
                    setPlaybackMediaSource(componentName);
                    setBrowseMediaSource(componentName);
                } else if (mode == 0) {
                    setPlaybackMediaSource(componentName);
                } else if (mode == 1) {
                    setBrowseMediaSource(componentName);
                }
            }
        }
    }

    private void setPlaybackMediaSource(ComponentName playbackMediaSource) {
        stopAndUnregisterCallback();
        this.mActiveUserMediaController = null;
        synchronized (this.mLock) {
            this.mPrimaryMediaComponents[0] = playbackMediaSource;
        }
        if (playbackMediaSource != null && !TextUtils.isEmpty(playbackMediaSource.flattenToString())) {
            if (!isCurrentUserEphemeral()) {
                saveLastMediaSource(playbackMediaSource, 0);
            }
            if (playbackMediaSource.equals(this.mRemovedMediaSourceComponents[0])) {
                this.mRemovedMediaSourceComponents[0] = null;
            }
        }
        notifyListeners(0);
        startMediaConnectorService(shouldStartPlayback(this.mPlayOnMediaSourceChangedConfig), new UserHandle(ActivityManager.getCurrentUser()));
        this.mCurrentPlaybackState = 0;
        updateActiveMediaController(this.mMediaSessionManager.getActiveSessionsForUser(null, ActivityManager.getCurrentUser()));
    }

    private void setBrowseMediaSource(ComponentName browseMediaSource) {
        synchronized (this.mLock) {
            this.mPrimaryMediaComponents[1] = browseMediaSource;
        }
        if (browseMediaSource != null && !TextUtils.isEmpty(browseMediaSource.flattenToString())) {
            if (!isCurrentUserEphemeral()) {
                saveLastMediaSource(browseMediaSource, 1);
            }
            if (browseMediaSource.equals(this.mRemovedMediaSourceComponents[1])) {
                this.mRemovedMediaSourceComponents[1] = null;
            }
        }
        notifyListeners(1);
    }

    private void notifyListeners(int mode) {
        synchronized (this.mLock) {
            int i = this.mMediaSourceListeners[mode].beginBroadcast();
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    try {
                        ICarMediaSourceListener callback = this.mMediaSourceListeners[mode].getBroadcastItem(i2);
                        callback.onMediaSourceChanged(this.mPrimaryMediaComponents[mode]);
                    } catch (RemoteException e) {
                        Log.e(CarLog.TAG_MEDIA, "calling onMediaSourceChanged failed " + e);
                    }
                    i = i2;
                } else {
                    this.mMediaSourceListeners[mode].finishBroadcast();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePrimaryMediaSourceWithCurrentlyPlaying(List<MediaController> controllers) {
        for (MediaController controller : controllers) {
            if (controller.getPlaybackState() != null && controller.getPlaybackState().getState() == 3) {
                String newPackageName = controller.getPackageName();
                String newClassName = getClassName(controller);
                if (!matchPrimaryMediaSource(newPackageName, newClassName, 0)) {
                    ComponentName mediaSource = getMediaSource(newPackageName, newClassName);
                    if (Log.isLoggable(CarLog.TAG_MEDIA, 4)) {
                        if (mediaSource != null) {
                            Log.i(CarLog.TAG_MEDIA, "MediaController changed, updating media source to: " + mediaSource.flattenToString());
                        } else {
                            Log.i(CarLog.TAG_MEDIA, "MediaController changed, but no media browse service found in package: " + newPackageName);
                        }
                    }
                    setPrimaryMediaSource(mediaSource, 0);
                    return;
                }
                return;
            }
        }
    }

    private boolean matchPrimaryMediaSource(String newPackageName, String newClassName, int mode) {
        synchronized (this.mLock) {
            if (this.mPrimaryMediaComponents[mode] != null && this.mPrimaryMediaComponents[mode].getPackageName().equals(newPackageName)) {
                if (TextUtils.isEmpty(newClassName)) {
                    return true;
                }
                return newClassName.equals(this.mPrimaryMediaComponents[mode].getClassName());
            }
            return false;
        }
    }

    public boolean isMediaService(ComponentName componentName) {
        return getMediaService(componentName) != null;
    }

    private ComponentName getMediaService(ComponentName componentName) {
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent mediaIntent = new Intent();
        mediaIntent.setPackage(packageName);
        mediaIntent.setAction(MediaBrowserServiceCompat.SERVICE_INTERFACE);
        List<ResolveInfo> mediaServices = packageManager.queryIntentServicesAsUser(mediaIntent, 64, ActivityManager.getCurrentUser());
        for (ResolveInfo service : mediaServices) {
            String serviceName = service.serviceInfo.name;
            if (!TextUtils.isEmpty(serviceName) && (TextUtils.isEmpty(className) || serviceName.equals(className))) {
                return new ComponentName(packageName, serviceName);
            }
        }
        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
            Log.d(CarLog.TAG_MEDIA, "No MediaBrowseService with ComponentName: " + componentName.flattenToString());
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ComponentName getMediaSource(String packageName, String className) {
        return getMediaService(new ComponentName(packageName, className));
    }

    private void saveLastMediaSource(ComponentName component, int mode) {
        if (!sharedPrefsInitialized()) {
            return;
        }
        String componentName = component.flattenToString();
        String key = getMediaSourceKey(mode);
        String serialized = this.mSharedPrefs.getString(key, null);
        if (serialized == null) {
            this.mSharedPrefs.edit().putString(key, componentName).apply();
            return;
        }
        Deque<String> componentNames = new ArrayDeque<>(getComponentNameList(serialized));
        componentNames.remove(componentName);
        componentNames.addFirst(componentName);
        this.mSharedPrefs.edit().putString(key, serializeComponentNameList(componentNames)).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ComponentName getLastMediaSource(int mode) {
        if (sharedPrefsInitialized()) {
            String key = getMediaSourceKey(mode);
            String serialized = this.mSharedPrefs.getString(key, "");
            if (!TextUtils.isEmpty(serialized)) {
                for (String name : getComponentNameList(serialized)) {
                    ComponentName componentName = ComponentName.unflattenFromString(name);
                    if (isMediaService(componentName)) {
                        return componentName;
                    }
                }
            }
        }
        return getDefaultMediaSource();
    }

    private ComponentName getDefaultMediaSource() {
        String defaultMediaSource = this.mContext.getString(R.string.config_defaultMediaSource);
        ComponentName defaultComponent = ComponentName.unflattenFromString(defaultMediaSource);
        if (isMediaService(defaultComponent)) {
            return defaultComponent;
        }
        return null;
    }

    private String serializeComponentNameList(Deque<String> componentNames) {
        return (String) componentNames.stream().collect(Collectors.joining(COMPONENT_NAME_SEPARATOR));
    }

    private List<String> getComponentNameList(String serialized) {
        String[] componentNames = serialized.split(COMPONENT_NAME_SEPARATOR);
        return Arrays.asList(componentNames);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePlaybackState(PlaybackState playbackState) {
        if (!sharedPrefsInitialized()) {
            return;
        }
        int state = playbackState != null ? playbackState.getState() : 0;
        this.mCurrentPlaybackState = state;
        String key = getPlaybackStateKey();
        this.mSharedPrefs.edit().putInt(key, state).apply();
    }

    private String getPlaybackStateKey() {
        String string;
        synchronized (this.mLock) {
            StringBuilder sb = new StringBuilder();
            sb.append(PLAYBACK_STATE_KEY);
            sb.append(ActivityManager.getCurrentUser());
            sb.append(this.mPrimaryMediaComponents[0] == null ? "" : this.mPrimaryMediaComponents[0].flattenToString());
            string = sb.toString();
        }
        return string;
    }

    private String getMediaSourceKey(int mode) {
        return SOURCE_KEY + mode + SOURCE_KEY_SEPARATOR + ActivityManager.getCurrentUser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActiveMediaController(List<MediaController> mediaControllers) {
        if (this.mPrimaryMediaComponents[0] == null) {
            return;
        }
        MediaController mediaController = this.mActiveUserMediaController;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.mMediaControllerCallback);
            this.mActiveUserMediaController = null;
        }
        for (MediaController controller : mediaControllers) {
            if (matchPrimaryMediaSource(controller.getPackageName(), getClassName(controller), 0)) {
                this.mActiveUserMediaController = controller;
                PlaybackState state = controller.getPlaybackState();
                if (!isCurrentUserEphemeral()) {
                    savePlaybackState(state);
                }
                this.mActiveUserMediaController.registerCallback(this.mMediaControllerCallback, this.mHandler);
                return;
            }
        }
    }

    private boolean shouldStartPlayback(int config) {
        if (config == 0) {
            return false;
        }
        if (config == 1) {
            return true;
        }
        if (config == 2) {
            return sharedPrefsInitialized() && this.mSharedPrefs.getInt(getPlaybackStateKey(), 0) == 3;
        }
        if (config == 3) {
            return this.mCurrentPlaybackState == 3;
        }
        Log.e(CarLog.TAG_MEDIA, "Unsupported playback configuration: " + config);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getClassName(MediaController controller) {
        Bundle sessionExtras = controller.getExtras();
        String value = sessionExtras == null ? "" : sessionExtras.getString("android.media.session.BROWSE_SERVICE");
        return value != null ? value : "";
    }
}
