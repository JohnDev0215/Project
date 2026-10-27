package androidx.mediarouter.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.display.DisplayManagerCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.media.VolumeProviderCompat;
import androidx.mediarouter.media.MediaRoute2Provider;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher;
import androidx.mediarouter.media.RemoteControlClientCompat;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaRouter {
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    static final String METADATA_FEATURE_DYNAMIC_GROUP = "dynamic_group";
    static final String METADATA_FEATURE_SEAMLESS_TRANSFER = "seamless_transfer";
    public static final String METADATA_NAME_FEATURE = "androidx.mediarouter.FEATURE";
    public static final int UNSELECT_REASON_DISCONNECTED = 1;
    public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
    public static final int UNSELECT_REASON_STOPPED = 2;
    public static final int UNSELECT_REASON_UNKNOWN = 0;
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords = new ArrayList<>();
    final Context mContext;
    static final String TAG = "MediaRouter";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    MediaRouter(Context context) {
        this.mContext = context;
    }

    public static MediaRouter getInstance(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (sGlobal == null) {
            GlobalMediaRouter globalMediaRouter = new GlobalMediaRouter(context.getApplicationContext());
            sGlobal = globalMediaRouter;
            globalMediaRouter.start();
        }
        return sGlobal.getRouter(context);
    }

    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        return sGlobal.getRoutes();
    }

    RouteInfo getRoute(String uniqueId) {
        checkCallingThread();
        return sGlobal.getRoute(uniqueId);
    }

    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        return sGlobal.getProviders();
    }

    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return sGlobal.getDefaultRoute();
    }

    public RouteInfo getBluetoothRoute() {
        checkCallingThread();
        return sGlobal.getBluetoothRoute();
    }

    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return sGlobal.getSelectedRoute();
    }

    public RouteInfo updateSelectedRoute(MediaRouteSelector selector) {
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "updateSelectedRoute: " + selector);
        }
        RouteInfo route = sGlobal.getSelectedRoute();
        if (!route.isDefaultOrBluetooth() && !route.matchesSelector(selector)) {
            RouteInfo route2 = sGlobal.chooseFallbackRoute();
            sGlobal.selectRoute(route2, 3);
            return route2;
        }
        return route;
    }

    public void selectRoute(RouteInfo route) {
        if (route == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "selectRoute: " + route);
        }
        sGlobal.selectRoute(route, 3);
    }

    public void unselect(int reason) {
        if (reason < 0 || reason > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        checkCallingThread();
        RouteInfo fallbackRoute = sGlobal.chooseFallbackRoute();
        if (sGlobal.getSelectedRoute() != fallbackRoute) {
            sGlobal.selectRoute(fallbackRoute, reason);
        } else {
            GlobalMediaRouter globalMediaRouter = sGlobal;
            globalMediaRouter.selectRoute(globalMediaRouter.getDefaultRoute(), reason);
        }
    }

    public void addMemberToDynamicGroup(RouteInfo route) {
        checkCallingThread();
        sGlobal.addMemberToDynamicGroup(route);
    }

    public void removeMemberFromDynamicGroup(RouteInfo route) {
        checkCallingThread();
        sGlobal.removeMemberFromDynamicGroup(route);
    }

    public void transferToRoute(RouteInfo route) {
        checkCallingThread();
        sGlobal.transferToRoute(route);
    }

    public boolean isRouteAvailable(MediaRouteSelector selector, int flags) {
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        return sGlobal.isRouteAvailable(selector, flags);
    }

    public void addCallback(MediaRouteSelector selector, Callback callback) {
        addCallback(selector, callback, 0);
    }

    public void addCallback(MediaRouteSelector selector, Callback callback, int flags) {
        CallbackRecord record;
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addCallback: selector=" + selector + ", callback=" + callback + ", flags=" + Integer.toHexString(flags));
        }
        int index = findCallbackRecord(callback);
        if (index < 0) {
            record = new CallbackRecord(this, callback);
            this.mCallbackRecords.add(record);
        } else {
            record = this.mCallbackRecords.get(index);
        }
        boolean updateNeeded = false;
        if (flags != record.mFlags) {
            record.mFlags = flags;
            updateNeeded = true;
        }
        if (!record.mSelector.contains(selector)) {
            record.mSelector = new MediaRouteSelector.Builder(record.mSelector).addSelector(selector).build();
            updateNeeded = true;
        }
        if (updateNeeded) {
            sGlobal.updateDiscoveryRequest();
        }
    }

    public void removeCallback(Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "removeCallback: callback=" + callback);
        }
        int index = findCallbackRecord(callback);
        if (index >= 0) {
            this.mCallbackRecords.remove(index);
            sGlobal.updateDiscoveryRequest();
        }
    }

    private int findCallbackRecord(Callback callback) {
        int count = this.mCallbackRecords.size();
        for (int i = 0; i < count; i++) {
            if (this.mCallbackRecords.get(i).mCallback == callback) {
                return i;
            }
        }
        return -1;
    }

    public void addProvider(MediaRouteProvider providerInstance) {
        if (providerInstance == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addProvider: " + providerInstance);
        }
        sGlobal.addProvider(providerInstance);
    }

    public void removeProvider(MediaRouteProvider providerInstance) {
        if (providerInstance == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "removeProvider: " + providerInstance);
        }
        sGlobal.removeProvider(providerInstance);
    }

    public void addRemoteControlClient(Object remoteControlClient) {
        if (remoteControlClient == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addRemoteControlClient: " + remoteControlClient);
        }
        sGlobal.addRemoteControlClient(remoteControlClient);
    }

    public void removeRemoteControlClient(Object remoteControlClient) {
        if (remoteControlClient == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        if (DEBUG) {
            Log.d(TAG, "removeRemoteControlClient: " + remoteControlClient);
        }
        sGlobal.removeRemoteControlClient(remoteControlClient);
    }

    public void setMediaSession(Object mediaSession) {
        if (DEBUG) {
            Log.d(TAG, "addMediaSession: " + mediaSession);
        }
        sGlobal.setMediaSession(mediaSession);
    }

    public void setMediaSessionCompat(MediaSessionCompat mediaSession) {
        if (DEBUG) {
            Log.d(TAG, "addMediaSessionCompat: " + mediaSession);
        }
        sGlobal.setMediaSessionCompat(mediaSession);
    }

    public MediaSessionCompat.Token getMediaSessionToken() {
        return sGlobal.getMediaSessionToken();
    }

    static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static boolean isTransferEnabled() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return false;
        }
        return globalMediaRouter.isTransferEnabled();
    }

    static int getGlobalCallbackCount() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return 0;
        }
        return globalMediaRouter.getCallbackCount();
    }

    public static class RouteInfo {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int CONNECTION_STATE_CONNECTED = 2;
        public static final int CONNECTION_STATE_CONNECTING = 1;
        public static final int CONNECTION_STATE_DISCONNECTED = 0;
        public static final int DEVICE_TYPE_BLUETOOTH = 3;
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TV = 1;
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
        static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
        private boolean mCanDisconnect;
        private int mConnectionState;
        private String mDescription;
        MediaRouteDescriptor mDescriptor;
        final String mDescriptorId;
        private int mDeviceType;
        MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor mDynamicDescriptor;
        private DynamicGroupState mDynamicGroupState;
        boolean mEnabled;
        private Bundle mExtras;
        private Uri mIconUri;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private final ProviderInfo mProvider;
        private IntentSender mSettingsIntent;
        final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;
        private final ArrayList<IntentFilter> mControlFilters = new ArrayList<>();
        private int mPresentationDisplayId = -1;
        private List<RouteInfo> mMemberRoutes = new ArrayList();

        RouteInfo(ProviderInfo provider, String descriptorId, String uniqueId) {
            this.mProvider = provider;
            this.mDescriptorId = descriptorId;
            this.mUniqueId = uniqueId;
        }

        public ProviderInfo getProvider() {
            return this.mProvider;
        }

        public String getId() {
            return this.mUniqueId;
        }

        public String getName() {
            return this.mName;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        @Deprecated
        public boolean isConnecting() {
            return this.mConnectionState == 1;
        }

        public int getConnectionState() {
            return this.mConnectionState;
        }

        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getSelectedRoute() == this;
        }

        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getDefaultRoute() == this;
        }

        public boolean isBluetooth() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getBluetoothRoute() == this;
        }

        public boolean isDeviceSpeaker() {
            int defaultAudioRouteNameResourceId = Resources.getSystem().getIdentifier("default_audio_route_name", "string", "android");
            return isDefault() && TextUtils.equals(Resources.getSystem().getText(defaultAudioRouteNameResourceId), this.mName);
        }

        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }

        public boolean matchesSelector(MediaRouteSelector selector) {
            if (selector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            MediaRouter.checkCallingThread();
            return selector.matchesControlFilters(this.mControlFilters);
        }

        public boolean supportsControlCategory(String category) {
            if (category == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            MediaRouter.checkCallingThread();
            int count = this.mControlFilters.size();
            for (int i = 0; i < count; i++) {
                if (this.mControlFilters.get(i).hasCategory(category)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsControlAction(String category, String action) {
            if (category == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (action == null) {
                throw new IllegalArgumentException("action must not be null");
            }
            MediaRouter.checkCallingThread();
            int count = this.mControlFilters.size();
            for (int i = 0; i < count; i++) {
                IntentFilter filter = this.mControlFilters.get(i);
                if (filter.hasCategory(category) && filter.hasAction(action)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsControlRequest(Intent intent) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            ContentResolver contentResolver = MediaRouter.sGlobal.getContentResolver();
            int count = this.mControlFilters.size();
            for (int i = 0; i < count; i++) {
                if (this.mControlFilters.get(i).match(contentResolver, intent, true, MediaRouter.TAG) >= 0) {
                    return true;
                }
            }
            return false;
        }

        public void sendControlRequest(Intent intent, ControlRequestCallback callback) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.sendControlRequest(this, intent, callback);
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }

        public int getDeviceType() {
            return this.mDeviceType;
        }

        public boolean isDefaultOrBluetooth() {
            if (isDefault() || this.mDeviceType == 3) {
                return true;
            }
            return isSystemMediaRouteProvider(this) && supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        boolean isSelectable() {
            return this.mDescriptor != null && this.mEnabled;
        }

        private static boolean isSystemMediaRouteProvider(RouteInfo route) {
            return TextUtils.equals(route.getProviderInstance().getMetadata().getPackageName(), "android");
        }

        public int getVolumeHandling() {
            return this.mVolumeHandling;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getVolumeMax() {
            return this.mVolumeMax;
        }

        public boolean canDisconnect() {
            return this.mCanDisconnect;
        }

        public void requestSetVolume(int volume) {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, volume)));
        }

        public void requestUpdateVolume(int delta) {
            MediaRouter.checkCallingThread();
            if (delta != 0) {
                MediaRouter.sGlobal.requestUpdateVolume(this, delta);
            }
        }

        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
                this.mPresentationDisplay = MediaRouter.sGlobal.getDisplay(this.mPresentationDisplayId);
            }
            return this.mPresentationDisplay;
        }

        public int getPresentationDisplayId() {
            return this.mPresentationDisplayId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public IntentSender getSettingsIntent() {
            return this.mSettingsIntent;
        }

        public void select() {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.selectRoute(this, 3);
        }

        public boolean isGroup() {
            return getMemberRoutes().size() >= 1;
        }

        public DynamicGroupState getDynamicGroupState() {
            if (this.mDynamicGroupState == null && this.mDynamicDescriptor != null) {
                this.mDynamicGroupState = new DynamicGroupState();
            }
            return this.mDynamicGroupState;
        }

        public List<RouteInfo> getMemberRoutes() {
            return Collections.unmodifiableList(this.mMemberRoutes);
        }

        public MediaRouteProvider.DynamicGroupRouteController getDynamicGroupController() {
            MediaRouteProvider.RouteController controller = MediaRouter.sGlobal.mSelectedRouteController;
            if (controller instanceof MediaRouteProvider.DynamicGroupRouteController) {
                return (MediaRouteProvider.DynamicGroupRouteController) controller;
            }
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MediaRouter.RouteInfo{ uniqueId=" + this.mUniqueId + ", name=" + this.mName + ", description=" + this.mDescription + ", iconUri=" + this.mIconUri + ", enabled=" + this.mEnabled + ", connectionState=" + this.mConnectionState + ", canDisconnect=" + this.mCanDisconnect + ", playbackType=" + this.mPlaybackType + ", playbackStream=" + this.mPlaybackStream + ", deviceType=" + this.mDeviceType + ", volumeHandling=" + this.mVolumeHandling + ", volume=" + this.mVolume + ", volumeMax=" + this.mVolumeMax + ", presentationDisplayId=" + this.mPresentationDisplayId + ", extras=" + this.mExtras + ", settingsIntent=" + this.mSettingsIntent + ", providerPackageName=" + this.mProvider.getPackageName());
            if (isGroup()) {
                sb.append(", members=[");
                int count = this.mMemberRoutes.size();
                for (int i = 0; i < count; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    if (this.mMemberRoutes.get(i) != this) {
                        sb.append(this.mMemberRoutes.get(i).getId());
                    }
                }
                sb.append(']');
            }
            sb.append(" }");
            return sb.toString();
        }

        int maybeUpdateDescriptor(MediaRouteDescriptor descriptor) {
            if (this.mDescriptor == descriptor) {
                return 0;
            }
            int changes = updateDescriptor(descriptor);
            return changes;
        }

        private boolean isSameControlFilters(List<IntentFilter> filters1, List<IntentFilter> filters2) {
            if (filters1 == filters2) {
                return true;
            }
            if (filters1 == null || filters2 == null) {
                return false;
            }
            ListIterator<IntentFilter> e1 = filters1.listIterator();
            ListIterator<IntentFilter> e2 = filters2.listIterator();
            while (e1.hasNext() && e2.hasNext()) {
                if (!isSameControlFilter(e1.next(), e2.next())) {
                    return false;
                }
            }
            if (!e1.hasNext() && !e2.hasNext()) {
                return true;
            }
            return false;
        }

        private boolean isSameControlFilter(IntentFilter filter1, IntentFilter filter2) {
            int countActions;
            if (filter1 == filter2) {
                return true;
            }
            if (filter1 == null || filter2 == null || (countActions = filter1.countActions()) != filter2.countActions()) {
                return false;
            }
            for (int i = 0; i < countActions; i++) {
                if (!filter1.getAction(i).equals(filter2.getAction(i))) {
                    return false;
                }
            }
            int countCategories = filter1.countCategories();
            if (countCategories != filter2.countCategories()) {
                return false;
            }
            for (int i2 = 0; i2 < countCategories; i2++) {
                if (!filter1.getCategory(i2).equals(filter2.getCategory(i2))) {
                    return false;
                }
            }
            return true;
        }

        int updateDescriptor(MediaRouteDescriptor descriptor) {
            int changes = 0;
            this.mDescriptor = descriptor;
            if (descriptor == null) {
                return 0;
            }
            if (!ObjectsCompat.equals(this.mName, descriptor.getName())) {
                this.mName = descriptor.getName();
                changes = 0 | 1;
            }
            if (!ObjectsCompat.equals(this.mDescription, descriptor.getDescription())) {
                this.mDescription = descriptor.getDescription();
                changes |= 1;
            }
            if (!ObjectsCompat.equals(this.mIconUri, descriptor.getIconUri())) {
                this.mIconUri = descriptor.getIconUri();
                changes |= 1;
            }
            if (this.mEnabled != descriptor.isEnabled()) {
                this.mEnabled = descriptor.isEnabled();
                changes |= 1;
            }
            if (this.mConnectionState != descriptor.getConnectionState()) {
                this.mConnectionState = descriptor.getConnectionState();
                changes |= 1;
            }
            if (!isSameControlFilters(this.mControlFilters, descriptor.getControlFilters())) {
                this.mControlFilters.clear();
                this.mControlFilters.addAll(descriptor.getControlFilters());
                changes |= 1;
            }
            if (this.mPlaybackType != descriptor.getPlaybackType()) {
                this.mPlaybackType = descriptor.getPlaybackType();
                changes |= 1;
            }
            if (this.mPlaybackStream != descriptor.getPlaybackStream()) {
                this.mPlaybackStream = descriptor.getPlaybackStream();
                changes |= 1;
            }
            if (this.mDeviceType != descriptor.getDeviceType()) {
                this.mDeviceType = descriptor.getDeviceType();
                changes |= 1;
            }
            if (this.mVolumeHandling != descriptor.getVolumeHandling()) {
                this.mVolumeHandling = descriptor.getVolumeHandling();
                changes |= 3;
            }
            if (this.mVolume != descriptor.getVolume()) {
                this.mVolume = descriptor.getVolume();
                changes |= 3;
            }
            if (this.mVolumeMax != descriptor.getVolumeMax()) {
                this.mVolumeMax = descriptor.getVolumeMax();
                changes |= 3;
            }
            if (this.mPresentationDisplayId != descriptor.getPresentationDisplayId()) {
                this.mPresentationDisplayId = descriptor.getPresentationDisplayId();
                this.mPresentationDisplay = null;
                changes |= 5;
            }
            if (!ObjectsCompat.equals(this.mExtras, descriptor.getExtras())) {
                this.mExtras = descriptor.getExtras();
                changes |= 1;
            }
            if (!ObjectsCompat.equals(this.mSettingsIntent, descriptor.getSettingsActivity())) {
                this.mSettingsIntent = descriptor.getSettingsActivity();
                changes |= 1;
            }
            if (this.mCanDisconnect != descriptor.canDisconnectAndKeepPlaying()) {
                this.mCanDisconnect = descriptor.canDisconnectAndKeepPlaying();
                changes |= 5;
            }
            boolean memberChanged = false;
            List<String> groupMemberIds = descriptor.getGroupMemberIds();
            List<RouteInfo> routes = new ArrayList<>();
            if (groupMemberIds.size() != this.mMemberRoutes.size()) {
                memberChanged = true;
            }
            for (String groupMemberId : groupMemberIds) {
                String uniqueId = MediaRouter.sGlobal.getUniqueId(getProvider(), groupMemberId);
                RouteInfo groupMember = MediaRouter.sGlobal.getRoute(uniqueId);
                if (groupMember != null) {
                    routes.add(groupMember);
                    if (!memberChanged && !this.mMemberRoutes.contains(groupMember)) {
                        memberChanged = true;
                    }
                }
            }
            if (memberChanged) {
                this.mMemberRoutes = routes;
                return changes | 1;
            }
            return changes;
        }

        String getDescriptorId() {
            return this.mDescriptorId;
        }

        public MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }

        void updateDynamicDescriptors(Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> dynamicDescriptors) {
            this.mMemberRoutes.clear();
            for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicDescriptor : dynamicDescriptors) {
                RouteInfo route = findRouteByDynamicRouteDescriptor(dynamicDescriptor);
                if (route != null) {
                    route.mDynamicDescriptor = dynamicDescriptor;
                    if (dynamicDescriptor.getSelectionState() == 2 || dynamicDescriptor.getSelectionState() == 3) {
                        this.mMemberRoutes.add(route);
                    }
                }
            }
            MediaRouter.sGlobal.mCallbackHandler.post(259, this);
        }

        RouteInfo findRouteByDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicDescriptor) {
            String descriptorId = dynamicDescriptor.getRouteDescriptor().getId();
            return getProvider().findRouteByDescriptorId(descriptorId);
        }

        public class DynamicGroupState {
            public DynamicGroupState() {
            }

            public int getSelectionState() {
                if (RouteInfo.this.mDynamicDescriptor != null) {
                    return RouteInfo.this.mDynamicDescriptor.getSelectionState();
                }
                return 1;
            }

            public boolean isUnselectable() {
                return RouteInfo.this.mDynamicDescriptor == null || RouteInfo.this.mDynamicDescriptor.isUnselectable();
            }

            public boolean isGroupable() {
                return RouteInfo.this.mDynamicDescriptor != null && RouteInfo.this.mDynamicDescriptor.isGroupable();
            }

            public boolean isTransferable() {
                return RouteInfo.this.mDynamicDescriptor != null && RouteInfo.this.mDynamicDescriptor.isTransferable();
            }
        }
    }

    public static final class ProviderInfo {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        final MediaRouteProvider mProviderInstance;
        private Resources mResources;
        private boolean mResourcesNotAvailable;
        final List<RouteInfo> mRoutes = new ArrayList();

        ProviderInfo(MediaRouteProvider provider) {
            this.mProviderInstance = provider;
            this.mMetadata = provider.getMetadata();
        }

        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }

        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }

        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }

        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return Collections.unmodifiableList(this.mRoutes);
        }

        Resources getResources() {
            if (this.mResources == null && !this.mResourcesNotAvailable) {
                String packageName = getPackageName();
                Context context = MediaRouter.sGlobal.getProviderContext(packageName);
                if (context != null) {
                    this.mResources = context.getResources();
                } else {
                    Log.w(MediaRouter.TAG, "Unable to obtain resources for route provider package: " + packageName);
                    this.mResourcesNotAvailable = true;
                }
            }
            return this.mResources;
        }

        boolean updateDescriptor(MediaRouteProviderDescriptor descriptor) {
            if (this.mDescriptor != descriptor) {
                this.mDescriptor = descriptor;
                return true;
            }
            return false;
        }

        int findRouteIndexByDescriptorId(String id) {
            int count = this.mRoutes.size();
            for (int i = 0; i < count; i++) {
                if (this.mRoutes.get(i).mDescriptorId.equals(id)) {
                    return i;
                }
            }
            return -1;
        }

        RouteInfo findRouteByDescriptorId(String id) {
            int count = this.mRoutes.size();
            for (int i = 0; i < count; i++) {
                if (this.mRoutes.get(i).mDescriptorId.equals(id)) {
                    return this.mRoutes.get(i);
                }
            }
            return null;
        }

        boolean supportsDynamicGroup() {
            MediaRouteProviderDescriptor mediaRouteProviderDescriptor = this.mDescriptor;
            return mediaRouteProviderDescriptor != null && mediaRouteProviderDescriptor.supportsDynamicGroupRoute();
        }

        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + getPackageName() + " }";
        }
    }

    public static abstract class Callback {
        @Deprecated
        public void onRouteSelected(MediaRouter router, RouteInfo route) {
        }

        public void onRouteSelected(MediaRouter router, RouteInfo route, int reason) {
            onRouteSelected(router, route);
        }

        @Deprecated
        public void onRouteUnselected(MediaRouter router, RouteInfo route) {
        }

        public void onRouteUnselected(MediaRouter router, RouteInfo route, int reason) {
            onRouteUnselected(router, route);
        }

        public void onRouteAdded(MediaRouter router, RouteInfo route) {
        }

        public void onRouteRemoved(MediaRouter router, RouteInfo route) {
        }

        public void onRouteChanged(MediaRouter router, RouteInfo route) {
        }

        public void onRouteVolumeChanged(MediaRouter router, RouteInfo route) {
        }

        public void onRoutePresentationDisplayChanged(MediaRouter router, RouteInfo route) {
        }

        public void onProviderAdded(MediaRouter router, ProviderInfo provider) {
        }

        public void onProviderRemoved(MediaRouter router, ProviderInfo provider) {
        }

        public void onProviderChanged(MediaRouter router, ProviderInfo provider) {
        }
    }

    public static abstract class ControlRequestCallback {
        public void onResult(Bundle data) {
        }

        public void onError(String error, Bundle data) {
        }
    }

    private static final class CallbackRecord {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;

        public CallbackRecord(MediaRouter router, Callback callback) {
            this.mRouter = router;
            this.mCallback = callback;
        }

        public boolean filterRouteEvent(RouteInfo route) {
            return (this.mFlags & 2) != 0 || route.matchesSelector(this.mSelector);
        }
    }

    private static final class GlobalMediaRouter implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
        final Context mApplicationContext;
        private RouteInfo mBluetoothRoute;
        private int mCallbackCount;
        private MediaSessionCompat mCompatSession;
        private RouteInfo mDefaultRoute;
        private MediaRouteDiscoveryRequest mDiscoveryRequest;
        private final DisplayManagerCompat mDisplayManager;
        final boolean mDynamicGroupEnabled;
        private final boolean mLowRam;
        private MediaSessionRecord mMediaSession;
        final MediaRoute2Provider mMr2Provider;
        MediaSessionCompat mRccMediaSession;
        private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
        RouteInfo mSelectedRoute;
        MediaRouteProvider.RouteController mSelectedRouteController;
        final SystemMediaRouteProvider mSystemProvider;
        final boolean mTransferEnabled;
        final ArrayList<WeakReference<MediaRouter>> mRouters = new ArrayList<>();
        private final ArrayList<RouteInfo> mRoutes = new ArrayList<>();
        private final Map<Pair<String, String>, String> mUniqueIdMap = new HashMap();
        private final ArrayList<ProviderInfo> mProviders = new ArrayList<>();
        private final ArrayList<RemoteControlClientRecord> mRemoteControlClients = new ArrayList<>();
        final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
        private final ProviderCallback mProviderCallback = new ProviderCallback();
        final CallbackHandler mCallbackHandler = new CallbackHandler();
        private final Map<String, MediaRouteProvider.RouteController> mRouteControllerMap = new HashMap();
        private MediaSessionCompat.OnActiveChangeListener mSessionActiveListener = new MediaSessionCompat.OnActiveChangeListener() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.1
            @Override // android.support.v4.media.session.MediaSessionCompat.OnActiveChangeListener
            public void onActiveChanged() {
                if (GlobalMediaRouter.this.mRccMediaSession != null) {
                    if (GlobalMediaRouter.this.mRccMediaSession.isActive()) {
                        GlobalMediaRouter globalMediaRouter = GlobalMediaRouter.this;
                        globalMediaRouter.addRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
                    } else {
                        GlobalMediaRouter globalMediaRouter2 = GlobalMediaRouter.this;
                        globalMediaRouter2.removeRemoteControlClient(globalMediaRouter2.mRccMediaSession.getRemoteControlClient());
                    }
                }
            }
        };
        MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.2
            @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
            public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController controller, MediaRouteDescriptor groupRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> routes) {
                if (controller == GlobalMediaRouter.this.mSelectedRouteController) {
                    if (groupRouteDescriptor != null) {
                        if (!TextUtils.equals(GlobalMediaRouter.this.mSelectedRoute.getDescriptorId(), groupRouteDescriptor.getId())) {
                            ProviderInfo provider = GlobalMediaRouter.this.mSelectedRoute.getProvider();
                            String groupId = groupRouteDescriptor.getId();
                            String uniqueId = GlobalMediaRouter.this.assignRouteUniqueId(provider, groupId);
                            RouteInfo route = new RouteInfo(provider, groupId, uniqueId);
                            route.maybeUpdateDescriptor(groupRouteDescriptor);
                            GlobalMediaRouter.this.replaceSelectedRouteForSelectedRouteController(route, 3);
                        } else {
                            GlobalMediaRouter globalMediaRouter = GlobalMediaRouter.this;
                            globalMediaRouter.updateRouteDescriptorAndNotify(globalMediaRouter.mSelectedRoute, groupRouteDescriptor);
                        }
                    }
                    GlobalMediaRouter.this.mSelectedRoute.updateDynamicDescriptors(routes);
                }
            }
        };

        GlobalMediaRouter(Context applicationContext) {
            this.mApplicationContext = applicationContext;
            this.mDisplayManager = DisplayManagerCompat.getInstance(applicationContext);
            this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager) applicationContext.getSystemService("activity"));
            String feature = null;
            try {
                ApplicationInfo appInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                feature = appInfo.metaData.getString(MediaRouter.METADATA_NAME_FEATURE);
            } catch (Exception ex) {
                Log.w(MediaRouter.TAG, "GlobalMediaRouter: Exception while getting feature.", ex);
            }
            if (TextUtils.equals(feature, MediaRouter.METADATA_FEATURE_SEAMLESS_TRANSFER)) {
                this.mDynamicGroupEnabled = true;
                this.mTransferEnabled = Build.VERSION.SDK_INT >= 30;
            } else if (TextUtils.equals(feature, MediaRouter.METADATA_FEATURE_DYNAMIC_GROUP)) {
                this.mDynamicGroupEnabled = true;
                this.mTransferEnabled = false;
            } else {
                this.mDynamicGroupEnabled = false;
                this.mTransferEnabled = false;
            }
            if (this.mTransferEnabled) {
                this.mMr2Provider = new MediaRoute2Provider(this.mApplicationContext, new Mr2ProviderCallback());
            } else {
                this.mMr2Provider = null;
            }
            this.mSystemProvider = SystemMediaRouteProvider.obtain(applicationContext, this);
        }

        public void start() {
            addProvider(this.mSystemProvider);
            MediaRoute2Provider mediaRoute2Provider = this.mMr2Provider;
            if (mediaRoute2Provider != null) {
                addProvider(mediaRoute2Provider);
            }
            RegisteredMediaRouteProviderWatcher registeredMediaRouteProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
            this.mRegisteredProviderWatcher = registeredMediaRouteProviderWatcher;
            registeredMediaRouteProviderWatcher.start();
        }

        public MediaRouter getRouter(Context context) {
            int i = this.mRouters.size();
            while (true) {
                i--;
                if (i >= 0) {
                    MediaRouter router = this.mRouters.get(i).get();
                    if (router == null) {
                        this.mRouters.remove(i);
                    } else if (router.mContext == context) {
                        return router;
                    }
                } else {
                    MediaRouter router2 = new MediaRouter(context);
                    this.mRouters.add(new WeakReference<>(router2));
                    return router2;
                }
            }
        }

        public ContentResolver getContentResolver() {
            return this.mApplicationContext.getContentResolver();
        }

        public Context getProviderContext(String packageName) {
            if (packageName.equals(SystemMediaRouteProvider.PACKAGE_NAME)) {
                return this.mApplicationContext;
            }
            try {
                return this.mApplicationContext.createPackageContext(packageName, 4);
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }

        public Display getDisplay(int displayId) {
            return this.mDisplayManager.getDisplay(displayId);
        }

        public void sendControlRequest(RouteInfo route, Intent intent, ControlRequestCallback callback) {
            MediaRouteProvider.RouteController routeController;
            if ((route != this.mSelectedRoute || (routeController = this.mSelectedRouteController) == null || !routeController.onControlRequest(intent, callback)) && callback != null) {
                callback.onError(null, null);
            }
        }

        public void requestSetVolume(RouteInfo route, int volume) {
            MediaRouteProvider.RouteController controller;
            MediaRouteProvider.RouteController routeController;
            if (route == this.mSelectedRoute && (routeController = this.mSelectedRouteController) != null) {
                routeController.onSetVolume(volume);
            } else if (!this.mRouteControllerMap.isEmpty() && (controller = this.mRouteControllerMap.get(route.mUniqueId)) != null) {
                controller.onSetVolume(volume);
            }
        }

        public void requestUpdateVolume(RouteInfo route, int delta) {
            MediaRouteProvider.RouteController routeController;
            if (route == this.mSelectedRoute && (routeController = this.mSelectedRouteController) != null) {
                routeController.onUpdateVolume(delta);
            }
        }

        public RouteInfo getRoute(String uniqueId) {
            for (RouteInfo info : this.mRoutes) {
                if (info.mUniqueId.equals(uniqueId)) {
                    return info;
                }
            }
            return null;
        }

        public List<RouteInfo> getRoutes() {
            return this.mRoutes;
        }

        List<ProviderInfo> getProviders() {
            return this.mProviders;
        }

        RouteInfo getDefaultRoute() {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo == null) {
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            }
            return routeInfo;
        }

        RouteInfo getBluetoothRoute() {
            return this.mBluetoothRoute;
        }

        RouteInfo getSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo == null) {
                throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
            }
            return routeInfo;
        }

        void addMemberToDynamicGroup(RouteInfo route) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
            if (this.mSelectedRoute.getMemberRoutes().contains(route) || state == null || !state.isGroupable()) {
                Log.w(MediaRouter.TAG, "Ignoring attemp to add a non-groupable route to dynamic group : " + route);
                return;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onAddMemberRoute(route.getDescriptorId());
        }

        void removeMemberFromDynamicGroup(RouteInfo route) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
            if (!this.mSelectedRoute.getMemberRoutes().contains(route) || state == null || !state.isUnselectable()) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to remove a non-unselectable member route : " + route);
                return;
            }
            if (this.mSelectedRoute.getMemberRoutes().size() <= 1) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to remove the last member route.");
            } else {
                ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onRemoveMemberRoute(route.getDescriptorId());
            }
        }

        void transferToRoute(RouteInfo route) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
            if (state == null || !state.isTransferable()) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to transfer to a non-transferable route.");
            } else {
                ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onUpdateMemberRoutes(Collections.singletonList(route.getDescriptorId()));
            }
        }

        void selectRoute(RouteInfo route, int unselectReason) {
            if (!this.mRoutes.contains(route)) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select removed route: " + route);
                return;
            }
            if (!route.mEnabled) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select disabled route: " + route);
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                MediaRouteProvider providerInstance = route.getProviderInstance();
                MediaRoute2Provider mediaRoute2Provider = this.mMr2Provider;
                if (providerInstance == mediaRoute2Provider && this.mSelectedRoute != route) {
                    mediaRoute2Provider.transferTo(route.getDescriptorId());
                    return;
                }
            }
            setSelectedRouteInternal(route, unselectReason);
        }

        public boolean isRouteAvailable(MediaRouteSelector selector, int flags) {
            if (selector.isEmpty()) {
                return false;
            }
            if ((flags & 2) == 0 && this.mLowRam) {
                return true;
            }
            int routeCount = this.mRoutes.size();
            for (int i = 0; i < routeCount; i++) {
                RouteInfo route = this.mRoutes.get(i);
                if (((flags & 1) == 0 || !route.isDefaultOrBluetooth()) && route.matchesSelector(selector)) {
                    return true;
                }
            }
            return false;
        }

        public void updateDiscoveryRequest() {
            boolean discover = false;
            boolean activeScan = false;
            MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
            int callbackCount = 0;
            int i = this.mRouters.size();
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                MediaRouter router = this.mRouters.get(i).get();
                if (router == null) {
                    this.mRouters.remove(i);
                } else {
                    int count = router.mCallbackRecords.size();
                    callbackCount += count;
                    for (int j = 0; j < count; j++) {
                        CallbackRecord callback = router.mCallbackRecords.get(j);
                        builder.addSelector(callback.mSelector);
                        if ((callback.mFlags & 1) != 0) {
                            activeScan = true;
                            discover = true;
                        }
                        if ((callback.mFlags & 4) != 0 && !this.mLowRam) {
                            discover = true;
                        }
                        if ((callback.mFlags & 8) != 0) {
                            discover = true;
                        }
                    }
                }
            }
            this.mCallbackCount = callbackCount;
            MediaRouteSelector selector = discover ? builder.build() : MediaRouteSelector.EMPTY;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mDiscoveryRequest;
            if (mediaRouteDiscoveryRequest != null && mediaRouteDiscoveryRequest.getSelector().equals(selector) && this.mDiscoveryRequest.isActiveScan() == activeScan) {
                return;
            }
            if (selector.isEmpty() && !activeScan) {
                if (this.mDiscoveryRequest == null) {
                    return;
                } else {
                    this.mDiscoveryRequest = null;
                }
            } else {
                this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(selector, activeScan);
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Updated discovery request: " + this.mDiscoveryRequest);
            }
            if (discover && !activeScan && this.mLowRam) {
                Log.i(MediaRouter.TAG, "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
            }
            int providerCount = this.mProviders.size();
            for (int i2 = 0; i2 < providerCount; i2++) {
                this.mProviders.get(i2).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }

        int getCallbackCount() {
            return this.mCallbackCount;
        }

        boolean isDynamicGroupEnabled() {
            return this.mDynamicGroupEnabled;
        }

        boolean isTransferEnabled() {
            return this.mTransferEnabled;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
        public void addProvider(MediaRouteProvider providerInstance) {
            if (findProviderInfo(providerInstance) == null) {
                ProviderInfo provider = new ProviderInfo(providerInstance);
                this.mProviders.add(provider);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider added: " + provider);
                }
                this.mCallbackHandler.post(513, provider);
                updateProviderContents(provider, providerInstance.getDescriptor());
                providerInstance.setCallback(this.mProviderCallback);
                providerInstance.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
        public void removeProvider(MediaRouteProvider providerInstance) {
            ProviderInfo provider = findProviderInfo(providerInstance);
            if (provider != null) {
                providerInstance.setCallback(null);
                providerInstance.setDiscoveryRequest(null);
                updateProviderContents(provider, null);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider removed: " + provider);
                }
                this.mCallbackHandler.post(514, provider);
                this.mProviders.remove(provider);
            }
        }

        void updateProviderDescriptor(MediaRouteProvider providerInstance, MediaRouteProviderDescriptor descriptor) {
            ProviderInfo provider = findProviderInfo(providerInstance);
            if (provider != null) {
                updateProviderContents(provider, descriptor);
            }
        }

        private ProviderInfo findProviderInfo(MediaRouteProvider providerInstance) {
            int count = this.mProviders.size();
            for (int i = 0; i < count; i++) {
                if (this.mProviders.get(i).mProviderInstance == providerInstance) {
                    return this.mProviders.get(i);
                }
            }
            return null;
        }

        private void updateProviderContents(ProviderInfo provider, MediaRouteProviderDescriptor providerDescriptor) {
            if (!provider.updateDescriptor(providerDescriptor)) {
                return;
            }
            int targetIndex = 0;
            boolean selectedRouteDescriptorChanged = false;
            if (providerDescriptor == null || (!providerDescriptor.isValid() && providerDescriptor != this.mSystemProvider.getDescriptor())) {
                Log.w(MediaRouter.TAG, "Ignoring invalid provider descriptor: " + providerDescriptor);
            } else {
                List<MediaRouteDescriptor> routeDescriptors = providerDescriptor.getRoutes();
                List<Pair<RouteInfo, MediaRouteDescriptor>> addedGroups = new ArrayList<>();
                List<Pair<RouteInfo, MediaRouteDescriptor>> updatedGroups = new ArrayList<>();
                for (MediaRouteDescriptor routeDescriptor : routeDescriptors) {
                    if (routeDescriptor == null || !routeDescriptor.isValid()) {
                        Log.w(MediaRouter.TAG, "Ignoring invalid system route descriptor: " + routeDescriptor);
                        selectedRouteDescriptorChanged = selectedRouteDescriptorChanged;
                    } else {
                        String id = routeDescriptor.getId();
                        int sourceIndex = provider.findRouteIndexByDescriptorId(id);
                        if (sourceIndex < 0) {
                            String uniqueId = assignRouteUniqueId(provider, id);
                            RouteInfo route = new RouteInfo(provider, id, uniqueId);
                            boolean selectedRouteDescriptorChanged2 = selectedRouteDescriptorChanged;
                            int targetIndex2 = targetIndex + 1;
                            provider.mRoutes.add(targetIndex, route);
                            this.mRoutes.add(route);
                            if (routeDescriptor.getGroupMemberIds().size() > 0) {
                                addedGroups.add(new Pair<>(route, routeDescriptor));
                            } else {
                                route.maybeUpdateDescriptor(routeDescriptor);
                                if (MediaRouter.DEBUG) {
                                    Log.d(MediaRouter.TAG, "Route added: " + route);
                                }
                                this.mCallbackHandler.post(257, route);
                            }
                            selectedRouteDescriptorChanged = selectedRouteDescriptorChanged2;
                            targetIndex = targetIndex2;
                        } else {
                            boolean selectedRouteDescriptorChanged3 = selectedRouteDescriptorChanged;
                            if (sourceIndex < targetIndex) {
                                Log.w(MediaRouter.TAG, "Ignoring route descriptor with duplicate id: " + routeDescriptor);
                                selectedRouteDescriptorChanged = selectedRouteDescriptorChanged3;
                            } else {
                                RouteInfo route2 = provider.mRoutes.get(sourceIndex);
                                int targetIndex3 = targetIndex + 1;
                                Collections.swap(provider.mRoutes, sourceIndex, targetIndex);
                                if (routeDescriptor.getGroupMemberIds().size() > 0) {
                                    updatedGroups.add(new Pair<>(route2, routeDescriptor));
                                } else if (updateRouteDescriptorAndNotify(route2, routeDescriptor) != 0 && route2 == this.mSelectedRoute) {
                                    selectedRouteDescriptorChanged = true;
                                    targetIndex = targetIndex3;
                                }
                                targetIndex = targetIndex3;
                                selectedRouteDescriptorChanged = selectedRouteDescriptorChanged3;
                            }
                        }
                    }
                }
                boolean selectedRouteDescriptorChanged4 = selectedRouteDescriptorChanged;
                for (Pair<RouteInfo, MediaRouteDescriptor> pair : addedGroups) {
                    RouteInfo route3 = pair.first;
                    route3.maybeUpdateDescriptor(pair.second);
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route added: " + route3);
                    }
                    this.mCallbackHandler.post(257, route3);
                }
                for (Pair<RouteInfo, MediaRouteDescriptor> pair2 : updatedGroups) {
                    RouteInfo route4 = pair2.first;
                    if (updateRouteDescriptorAndNotify(route4, pair2.second) != 0 && route4 == this.mSelectedRoute) {
                        selectedRouteDescriptorChanged4 = true;
                    }
                }
                selectedRouteDescriptorChanged = selectedRouteDescriptorChanged4;
            }
            for (int i = provider.mRoutes.size() - 1; i >= targetIndex; i--) {
                RouteInfo route5 = provider.mRoutes.get(i);
                route5.maybeUpdateDescriptor(null);
                this.mRoutes.remove(route5);
            }
            updateSelectedRouteIfNeeded(selectedRouteDescriptorChanged);
            for (int i2 = provider.mRoutes.size() - 1; i2 >= targetIndex; i2--) {
                RouteInfo route6 = provider.mRoutes.remove(i2);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Route removed: " + route6);
                }
                this.mCallbackHandler.post(258, route6);
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Provider changed: " + provider);
            }
            this.mCallbackHandler.post(515, provider);
        }

        int updateRouteDescriptorAndNotify(RouteInfo route, MediaRouteDescriptor routeDescriptor) {
            int changes = route.maybeUpdateDescriptor(routeDescriptor);
            if (changes != 0) {
                if ((changes & 1) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route changed: " + route);
                    }
                    this.mCallbackHandler.post(259, route);
                }
                if ((changes & 2) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route volume changed: " + route);
                    }
                    this.mCallbackHandler.post(260, route);
                }
                if ((changes & 4) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route presentation display changed: " + route);
                    }
                    this.mCallbackHandler.post(261, route);
                }
            }
            return changes;
        }

        String assignRouteUniqueId(ProviderInfo provider, String routeDescriptorId) {
            String componentName = provider.getComponentName().flattenToShortString();
            String uniqueId = componentName + ":" + routeDescriptorId;
            if (findRouteByUniqueId(uniqueId) < 0) {
                this.mUniqueIdMap.put(new Pair<>(componentName, routeDescriptorId), uniqueId);
                return uniqueId;
            }
            Log.w(MediaRouter.TAG, "Either " + routeDescriptorId + " isn't unique in " + componentName + " or we're trying to assign a unique ID for an already added route");
            int i = 2;
            while (true) {
                String newUniqueId = String.format(Locale.US, "%s_%d", uniqueId, Integer.valueOf(i));
                if (findRouteByUniqueId(newUniqueId) >= 0) {
                    i++;
                } else {
                    this.mUniqueIdMap.put(new Pair<>(componentName, routeDescriptorId), newUniqueId);
                    return newUniqueId;
                }
            }
        }

        private int findRouteByUniqueId(String uniqueId) {
            int count = this.mRoutes.size();
            for (int i = 0; i < count; i++) {
                if (this.mRoutes.get(i).mUniqueId.equals(uniqueId)) {
                    return i;
                }
            }
            return -1;
        }

        String getUniqueId(ProviderInfo provider, String routeDescriptorId) {
            String componentName = provider.getComponentName().flattenToShortString();
            return this.mUniqueIdMap.get(new Pair(componentName, routeDescriptorId));
        }

        void updateSelectedRouteIfNeeded(boolean selectedRouteDescriptorChanged) {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo != null && !routeInfo.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
                this.mDefaultRoute = null;
            }
            if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it = this.mRoutes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RouteInfo route = it.next();
                    if (isSystemDefaultRoute(route) && route.isSelectable()) {
                        this.mDefaultRoute = route;
                        Log.i(MediaRouter.TAG, "Found default route: " + this.mDefaultRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo2 = this.mBluetoothRoute;
            if (routeInfo2 != null && !routeInfo2.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
                this.mBluetoothRoute = null;
            }
            if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it2 = this.mRoutes.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    RouteInfo route2 = it2.next();
                    if (isSystemLiveAudioOnlyRoute(route2) && route2.isSelectable()) {
                        this.mBluetoothRoute = route2;
                        Log.i(MediaRouter.TAG, "Found bluetooth route: " + this.mBluetoothRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo3 = this.mSelectedRoute;
            if (routeInfo3 == null || !routeInfo3.isEnabled()) {
                Log.i(MediaRouter.TAG, "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
                setSelectedRouteInternal(chooseFallbackRoute(), 0);
                return;
            }
            if (selectedRouteDescriptorChanged) {
                if (this.mSelectedRoute.isGroup()) {
                    List<RouteInfo> routes = this.mSelectedRoute.getMemberRoutes();
                    Set<String> idSet = new HashSet<>();
                    Iterator<RouteInfo> it3 = routes.iterator();
                    while (it3.hasNext()) {
                        idSet.add(it3.next().mUniqueId);
                    }
                    Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> iter = this.mRouteControllerMap.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, MediaRouteProvider.RouteController> entry = iter.next();
                        if (!idSet.contains(entry.getKey())) {
                            MediaRouteProvider.RouteController controller = entry.getValue();
                            controller.onUnselect(0);
                            controller.onRelease();
                            iter.remove();
                        }
                    }
                    for (RouteInfo route3 : routes) {
                        if (!this.mRouteControllerMap.containsKey(route3.mUniqueId)) {
                            MediaRouteProvider.RouteController controller2 = route3.getProviderInstance().onCreateRouteController(route3.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                            controller2.onSelect();
                            this.mRouteControllerMap.put(route3.mUniqueId, controller2);
                        }
                    }
                }
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        RouteInfo chooseFallbackRoute() {
            for (RouteInfo route : this.mRoutes) {
                if (route != this.mDefaultRoute && isSystemLiveAudioOnlyRoute(route) && route.isSelectable()) {
                    return route;
                }
            }
            return this.mDefaultRoute;
        }

        private boolean isSystemLiveAudioOnlyRoute(RouteInfo route) {
            return route.getProviderInstance() == this.mSystemProvider && route.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !route.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        private boolean isSystemDefaultRoute(RouteInfo route) {
            return route.getProviderInstance() == this.mSystemProvider && route.mDescriptorId.equals(SystemMediaRouteProvider.DEFAULT_ROUTE_ID);
        }

        void setSelectedRouteInternal(RouteInfo routeInfo, int i) {
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerOnCreateRouteController;
            if (MediaRouter.sGlobal == null || (this.mBluetoothRoute != null && routeInfo.isDefault())) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (int i2 = 3; i2 < stackTrace.length; i2++) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    sb.append(stackTraceElement.getClassName());
                    sb.append(".");
                    sb.append(stackTraceElement.getMethodName());
                    sb.append(":");
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append("  ");
                }
                if (MediaRouter.sGlobal == null) {
                    Log.w(MediaRouter.TAG, "setSelectedRouteInternal is called while sGlobal is null: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + sb.toString());
                } else {
                    Log.w(MediaRouter.TAG, "Default route is selected while a BT route is available: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + sb.toString());
                }
            }
            if (this.mSelectedRoute == routeInfo) {
                return;
            }
            clearSelectedRoute(i);
            if (routeInfo.getProvider().supportsDynamicGroup()) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerOnCreateDynamicGroupRouteController = routeInfo.getProviderInstance().onCreateDynamicGroupRouteController(routeInfo.mDescriptorId);
                dynamicGroupRouteControllerOnCreateRouteController = dynamicGroupRouteControllerOnCreateDynamicGroupRouteController;
                if (dynamicGroupRouteControllerOnCreateDynamicGroupRouteController != null) {
                    dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mApplicationContext), this.mDynamicRoutesListener);
                    dynamicGroupRouteControllerOnCreateRouteController = dynamicGroupRouteControllerOnCreateDynamicGroupRouteController;
                }
            } else {
                dynamicGroupRouteControllerOnCreateRouteController = routeInfo.getProviderInstance().onCreateRouteController(routeInfo.mDescriptorId);
            }
            this.mSelectedRouteController = dynamicGroupRouteControllerOnCreateRouteController;
            this.mSelectedRoute = routeInfo;
            if (dynamicGroupRouteControllerOnCreateRouteController != null) {
                dynamicGroupRouteControllerOnCreateRouteController.onSelect();
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Route selected: " + this.mSelectedRoute);
            }
            this.mCallbackHandler.post(262, this.mSelectedRoute, i);
            if (this.mSelectedRoute.isGroup()) {
                List<RouteInfo> memberRoutes = this.mSelectedRoute.getMemberRoutes();
                this.mRouteControllerMap.clear();
                for (RouteInfo routeInfo2 : memberRoutes) {
                    MediaRouteProvider.RouteController routeControllerOnCreateRouteController = routeInfo2.getProviderInstance().onCreateRouteController(routeInfo2.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                    routeControllerOnCreateRouteController.onSelect();
                    this.mRouteControllerMap.put(routeInfo2.mUniqueId, routeControllerOnCreateRouteController);
                }
            }
            updatePlaybackInfoFromSelectedRoute();
        }

        void replaceSelectedRouteForSelectedRouteController(RouteInfo route, int reason) {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo == route) {
                return;
            }
            this.mCallbackHandler.post(263, routeInfo, reason);
            this.mSelectedRoute = route;
            this.mCallbackHandler.post(262, route, reason);
            updatePlaybackInfoFromSelectedRoute();
        }

        void clearSelectedRoute(int unselectReason) {
            if (this.mSelectedRoute == null) {
                return;
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Route unselected: " + this.mSelectedRoute + " reason: " + unselectReason);
            }
            this.mCallbackHandler.post(263, this.mSelectedRoute, unselectReason);
            MediaRouteProvider.RouteController routeController = this.mSelectedRouteController;
            if (routeController != null) {
                routeController.onUnselect(unselectReason);
                this.mSelectedRouteController.onRelease();
                this.mSelectedRouteController = null;
            }
            if (!this.mRouteControllerMap.isEmpty()) {
                for (MediaRouteProvider.RouteController controller : this.mRouteControllerMap.values()) {
                    controller.onUnselect(unselectReason);
                    controller.onRelease();
                }
                this.mRouteControllerMap.clear();
            }
            this.mSelectedRoute = null;
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.SyncCallback
        public void onSystemRouteSelectedByDescriptorId(String id) {
            RouteInfo route;
            this.mCallbackHandler.removeMessages(262);
            ProviderInfo provider = findProviderInfo(this.mSystemProvider);
            if (provider != null && (route = provider.findRouteByDescriptorId(id)) != null) {
                route.select();
            }
        }

        public void addRemoteControlClient(Object rcc) {
            int index = findRemoteControlClientRecord(rcc);
            if (index < 0) {
                RemoteControlClientRecord record = new RemoteControlClientRecord(rcc);
                this.mRemoteControlClients.add(record);
            }
        }

        public void removeRemoteControlClient(Object rcc) {
            int index = findRemoteControlClientRecord(rcc);
            if (index >= 0) {
                RemoteControlClientRecord record = this.mRemoteControlClients.remove(index);
                record.disconnect();
            }
        }

        public void setMediaSession(Object session) {
            setMediaSessionRecord(session != null ? new MediaSessionRecord(this, session) : null);
        }

        public void setMediaSessionCompat(MediaSessionCompat session) {
            this.mCompatSession = session;
            if (Build.VERSION.SDK_INT >= 21) {
                setMediaSessionRecord(session != null ? new MediaSessionRecord(session) : null);
                return;
            }
            if (Build.VERSION.SDK_INT >= 14) {
                MediaSessionCompat mediaSessionCompat = this.mRccMediaSession;
                if (mediaSessionCompat != null) {
                    removeRemoteControlClient(mediaSessionCompat.getRemoteControlClient());
                    this.mRccMediaSession.removeOnActiveChangeListener(this.mSessionActiveListener);
                }
                this.mRccMediaSession = session;
                if (session != null) {
                    session.addOnActiveChangeListener(this.mSessionActiveListener);
                    if (session.isActive()) {
                        addRemoteControlClient(session.getRemoteControlClient());
                    }
                }
            }
        }

        private void setMediaSessionRecord(MediaSessionRecord mediaSessionRecord) {
            MediaSessionRecord mediaSessionRecord2 = this.mMediaSession;
            if (mediaSessionRecord2 != null) {
                mediaSessionRecord2.clearVolumeHandling();
            }
            this.mMediaSession = mediaSessionRecord;
            if (mediaSessionRecord != null) {
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        public MediaSessionCompat.Token getMediaSessionToken() {
            MediaSessionRecord mediaSessionRecord = this.mMediaSession;
            if (mediaSessionRecord != null) {
                return mediaSessionRecord.getToken();
            }
            MediaSessionCompat mediaSessionCompat = this.mCompatSession;
            if (mediaSessionCompat != null) {
                return mediaSessionCompat.getSessionToken();
            }
            return null;
        }

        private int findRemoteControlClientRecord(Object rcc) {
            int count = this.mRemoteControlClients.size();
            for (int i = 0; i < count; i++) {
                RemoteControlClientRecord record = this.mRemoteControlClients.get(i);
                if (record.getRemoteControlClient() == rcc) {
                    return i;
                }
            }
            return -1;
        }

        private void updatePlaybackInfoFromSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo != null) {
                this.mPlaybackInfo.volume = routeInfo.getVolume();
                this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
                this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
                this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
                this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
                if (this.mTransferEnabled && this.mSelectedRoute.getProviderInstance() == this.mMr2Provider) {
                    this.mPlaybackInfo.volumeControlId = MediaRoute2Provider.getSessionIdForRouteController(this.mSelectedRouteController);
                } else {
                    this.mPlaybackInfo.volumeControlId = null;
                }
                int count = this.mRemoteControlClients.size();
                for (int i = 0; i < count; i++) {
                    RemoteControlClientRecord record = this.mRemoteControlClients.get(i);
                    record.updatePlaybackInfo();
                }
                if (this.mMediaSession != null) {
                    if (this.mSelectedRoute == getDefaultRoute() || this.mSelectedRoute == getBluetoothRoute()) {
                        this.mMediaSession.clearVolumeHandling();
                        return;
                    }
                    int controlType = 0;
                    if (this.mPlaybackInfo.volumeHandling == 1) {
                        controlType = 2;
                    }
                    this.mMediaSession.configureVolume(controlType, this.mPlaybackInfo.volumeMax, this.mPlaybackInfo.volume, this.mPlaybackInfo.volumeControlId);
                    return;
                }
                return;
            }
            MediaSessionRecord mediaSessionRecord = this.mMediaSession;
            if (mediaSessionRecord != null) {
                mediaSessionRecord.clearVolumeHandling();
            }
        }

        private final class ProviderCallback extends MediaRouteProvider.Callback {
            ProviderCallback() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(MediaRouteProvider provider, MediaRouteProviderDescriptor descriptor) {
                GlobalMediaRouter.this.updateProviderDescriptor(provider, descriptor);
            }
        }

        private final class Mr2ProviderCallback extends MediaRoute2Provider.Callback {
            private Mr2ProviderCallback() {
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onSelectRoute(String routeDescriptorId, int reason) {
                RouteInfo routeToSelect = null;
                Iterator<RouteInfo> it = GlobalMediaRouter.this.getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RouteInfo routeInfo = it.next();
                    if (routeInfo.getProviderInstance() == GlobalMediaRouter.this.mMr2Provider && TextUtils.equals(routeDescriptorId, routeInfo.getDescriptorId())) {
                        routeToSelect = routeInfo;
                        break;
                    }
                }
                if (routeToSelect == null) {
                    Log.w(MediaRouter.TAG, "onSelectRoute: The target RouteInfo is not found for descriptorId=" + routeDescriptorId);
                    return;
                }
                GlobalMediaRouter.this.setSelectedRouteInternal(routeToSelect, reason);
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onSelectFallbackRoute(int reason) {
                setSelectedRouteToFallbackRoute(reason);
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onReleaseController(MediaRouteProvider.RouteController controller) {
                if (controller == null) {
                    return;
                }
                if (controller == GlobalMediaRouter.this.mSelectedRouteController) {
                    setSelectedRouteToFallbackRoute(2);
                } else if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "A RouteController unrelated to the selected route is released. controller=" + controller);
                }
            }

            void setSelectedRouteToFallbackRoute(int reason) {
                RouteInfo fallbackRoute = GlobalMediaRouter.this.chooseFallbackRoute();
                if (GlobalMediaRouter.this.getSelectedRoute() != fallbackRoute) {
                    GlobalMediaRouter.this.setSelectedRouteInternal(fallbackRoute, reason);
                }
            }
        }

        private final class MediaSessionRecord {
            private int mControlType;
            private int mMaxVolume;
            private final MediaSessionCompat mMsCompat;
            private VolumeProviderCompat mVpCompat;

            MediaSessionRecord(GlobalMediaRouter globalMediaRouter, Object mediaSession) {
                this(MediaSessionCompat.fromMediaSession(globalMediaRouter.mApplicationContext, mediaSession));
            }

            MediaSessionRecord(MediaSessionCompat mediaSessionCompat) {
                this.mMsCompat = mediaSessionCompat;
            }

            public void configureVolume(int controlType, int max, int current, String volumeControlId) {
                if (this.mMsCompat != null) {
                    VolumeProviderCompat volumeProviderCompat = this.mVpCompat;
                    if (volumeProviderCompat != null && controlType == this.mControlType && max == this.mMaxVolume) {
                        volumeProviderCompat.setCurrentVolume(current);
                        return;
                    }
                    VolumeProviderCompat volumeProviderCompat2 = new VolumeProviderCompat(controlType, max, current, volumeControlId) { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1
                        @Override // androidx.media.VolumeProviderCompat
                        public void onSetVolumeTo(final int volume) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (GlobalMediaRouter.this.mSelectedRoute != null) {
                                        GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(volume);
                                    }
                                }
                            });
                        }

                        @Override // androidx.media.VolumeProviderCompat
                        public void onAdjustVolume(final int direction) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (GlobalMediaRouter.this.mSelectedRoute != null) {
                                        GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(direction);
                                    }
                                }
                            });
                        }
                    };
                    this.mVpCompat = volumeProviderCompat2;
                    this.mMsCompat.setPlaybackToRemote(volumeProviderCompat2);
                }
            }

            public void clearVolumeHandling() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    mediaSessionCompat.setPlaybackToLocal(GlobalMediaRouter.this.mPlaybackInfo.playbackStream);
                    this.mVpCompat = null;
                }
            }

            public MediaSessionCompat.Token getToken() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    return mediaSessionCompat.getSessionToken();
                }
                return null;
            }
        }

        private final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
            private boolean mDisconnected;
            private final RemoteControlClientCompat mRccCompat;

            public RemoteControlClientRecord(Object rcc) {
                RemoteControlClientCompat remoteControlClientCompatObtain = RemoteControlClientCompat.obtain(GlobalMediaRouter.this.mApplicationContext, rcc);
                this.mRccCompat = remoteControlClientCompatObtain;
                remoteControlClientCompatObtain.setVolumeCallback(this);
                updatePlaybackInfo();
            }

            public Object getRemoteControlClient() {
                return this.mRccCompat.getRemoteControlClient();
            }

            public void disconnect() {
                this.mDisconnected = true;
                this.mRccCompat.setVolumeCallback(null);
            }

            public void updatePlaybackInfo() {
                this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.this.mPlaybackInfo);
            }

            @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
            public void onVolumeSetRequest(int volume) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(volume);
                }
            }

            @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
            public void onVolumeUpdateRequest(int direction) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(direction);
                }
            }
        }

        private final class CallbackHandler extends Handler {
            public static final int MSG_PROVIDER_ADDED = 513;
            public static final int MSG_PROVIDER_CHANGED = 515;
            public static final int MSG_PROVIDER_REMOVED = 514;
            public static final int MSG_ROUTE_ADDED = 257;
            public static final int MSG_ROUTE_CHANGED = 259;
            public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
            public static final int MSG_ROUTE_REMOVED = 258;
            public static final int MSG_ROUTE_SELECTED = 262;
            public static final int MSG_ROUTE_UNSELECTED = 263;
            public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
            private static final int MSG_TYPE_MASK = 65280;
            private static final int MSG_TYPE_PROVIDER = 512;
            private static final int MSG_TYPE_ROUTE = 256;
            private final ArrayList<CallbackRecord> mTempCallbackRecords = new ArrayList<>();

            CallbackHandler() {
            }

            public void post(int msg, Object obj) {
                obtainMessage(msg, obj).sendToTarget();
            }

            public void post(int msg, Object obj, int arg) {
                Message message = obtainMessage(msg, obj);
                message.arg1 = arg;
                message.sendToTarget();
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                int what = msg.what;
                Object obj = msg.obj;
                int arg = msg.arg1;
                if (what == 259 && GlobalMediaRouter.this.getSelectedRoute().getId().equals(((RouteInfo) obj).getId())) {
                    GlobalMediaRouter.this.updateSelectedRouteIfNeeded(true);
                }
                syncWithSystemProvider(what, obj);
                try {
                    int i = GlobalMediaRouter.this.mRouters.size();
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        MediaRouter router = GlobalMediaRouter.this.mRouters.get(i).get();
                        if (router == null) {
                            GlobalMediaRouter.this.mRouters.remove(i);
                        } else {
                            this.mTempCallbackRecords.addAll(router.mCallbackRecords);
                        }
                    }
                    int callbackCount = this.mTempCallbackRecords.size();
                    for (int i2 = 0; i2 < callbackCount; i2++) {
                        invokeCallback(this.mTempCallbackRecords.get(i2), what, obj, arg);
                    }
                } finally {
                    this.mTempCallbackRecords.clear();
                }
            }

            private void syncWithSystemProvider(int what, Object obj) {
                if (what != 262) {
                    switch (what) {
                        case 257:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((RouteInfo) obj);
                            break;
                        case 258:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((RouteInfo) obj);
                            break;
                        case 259:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((RouteInfo) obj);
                            break;
                    }
                }
                GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected((RouteInfo) obj);
            }

            private void invokeCallback(CallbackRecord record, int what, Object obj, int arg) {
                MediaRouter router = record.mRouter;
                Callback callback = record.mCallback;
                int i = 65280 & what;
                if (i != 256) {
                    if (i == 512) {
                        ProviderInfo provider = (ProviderInfo) obj;
                        switch (what) {
                            case 513:
                                callback.onProviderAdded(router, provider);
                                break;
                            case 514:
                                callback.onProviderRemoved(router, provider);
                                break;
                            case 515:
                                callback.onProviderChanged(router, provider);
                                break;
                        }
                    }
                    return;
                }
                RouteInfo route = (RouteInfo) obj;
                if (record.filterRouteEvent(route)) {
                    switch (what) {
                        case 257:
                            callback.onRouteAdded(router, route);
                            break;
                        case 258:
                            callback.onRouteRemoved(router, route);
                            break;
                        case 259:
                            callback.onRouteChanged(router, route);
                            break;
                        case 260:
                            callback.onRouteVolumeChanged(router, route);
                            break;
                        case 261:
                            callback.onRoutePresentationDisplayChanged(router, route);
                            break;
                        case 262:
                            callback.onRouteSelected(router, route, arg);
                            break;
                        case 263:
                            callback.onRouteUnselected(router, route, arg);
                            break;
                    }
                }
            }
        }
    }
}
