package com.android.settingslib.media;

import android.app.Notification;
import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;
import android.media.RoutingSessionInfo;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public class InfoMediaManager extends MediaManager {
    private LocalBluetoothManager mBluetoothManager;
    private MediaDevice mCurrentConnectedDevice;
    final Executor mExecutor;
    final RouterManagerCallback mMediaRouterCallback;
    String mPackageName;
    MediaRouter2Manager mRouterManager;
    private static final String TAG = "InfoMediaManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public InfoMediaManager(Context context, String packageName, Notification notification, LocalBluetoothManager localBluetoothManager) {
        super(context, notification);
        this.mMediaRouterCallback = new RouterManagerCallback();
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mRouterManager = MediaRouter2Manager.getInstance(context);
        this.mBluetoothManager = localBluetoothManager;
        if (!TextUtils.isEmpty(packageName)) {
            this.mPackageName = packageName;
        }
    }

    @Override // com.android.settingslib.media.MediaManager
    public void startScan() {
        this.mMediaDevices.clear();
        this.mRouterManager.registerCallback(this.mExecutor, this.mMediaRouterCallback);
        refreshDevices();
    }

    @Override // com.android.settingslib.media.MediaManager
    public void stopScan() {
        this.mRouterManager.unregisterCallback(this.mMediaRouterCallback);
    }

    MediaDevice getCurrentConnectedDevice() {
        return this.mCurrentConnectedDevice;
    }

    boolean connectDeviceWithoutPackageName(MediaDevice device) {
        List<RoutingSessionInfo> infos = this.mRouterManager.getActiveSessions();
        if (infos.size() <= 0) {
            return false;
        }
        RoutingSessionInfo info = infos.get(0);
        this.mRouterManager.transfer(info, device.mRouteInfo);
        return true;
    }

    boolean addDeviceToPlayMedia(MediaDevice device) {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "addDeviceToPlayMedia() package name is null or empty!");
            return false;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null && info.getSelectableRoutes().contains(device.mRouteInfo.getId())) {
            this.mRouterManager.selectRoute(info, device.mRouteInfo);
            return true;
        }
        Log.w(TAG, "addDeviceToPlayMedia() Ignoring selecting a non-selectable device : " + device.getName());
        return false;
    }

    private RoutingSessionInfo getRoutingSessionInfo() {
        List<RoutingSessionInfo> sessionInfos = this.mRouterManager.getRoutingSessions(this.mPackageName);
        return sessionInfos.get(sessionInfos.size() - 1);
    }

    boolean removeDeviceFromPlayMedia(MediaDevice device) {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "removeDeviceFromMedia() package name is null or empty!");
            return false;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null && info.getSelectedRoutes().contains(device.mRouteInfo.getId())) {
            this.mRouterManager.deselectRoute(info, device.mRouteInfo);
            return true;
        }
        Log.w(TAG, "removeDeviceFromMedia() Ignoring deselecting a non-deselectable device : " + device.getName());
        return false;
    }

    boolean releaseSession() {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "releaseSession() package name is null or empty!");
            return false;
        }
        RoutingSessionInfo sessionInfo = getRoutingSessionInfo();
        if (sessionInfo != null) {
            this.mRouterManager.releaseSession(sessionInfo);
            return true;
        }
        Log.w(TAG, "releaseSession() Ignoring release session : " + this.mPackageName);
        return false;
    }

    List<MediaDevice> getSelectableMediaDevice() {
        List<MediaDevice> deviceList = new ArrayList<>();
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "getSelectableMediaDevice() package name is null or empty!");
            return deviceList;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            for (MediaRoute2Info route : this.mRouterManager.getSelectableRoutes(info)) {
                deviceList.add(new InfoMediaDevice(this.mContext, this.mRouterManager, route, this.mPackageName));
            }
            return deviceList;
        }
        Log.w(TAG, "getSelectableMediaDevice() cannot found selectable MediaDevice from : " + this.mPackageName);
        return deviceList;
    }

    List<MediaDevice> getDeselectableMediaDevice() {
        List<MediaDevice> deviceList = new ArrayList<>();
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.d(TAG, "getDeselectableMediaDevice() package name is null or empty!");
            return deviceList;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            for (MediaRoute2Info route : this.mRouterManager.getDeselectableRoutes(info)) {
                deviceList.add(new InfoMediaDevice(this.mContext, this.mRouterManager, route, this.mPackageName));
                Log.d(TAG, ((Object) route.getName()) + " is deselectable for " + this.mPackageName);
            }
            return deviceList;
        }
        Log.d(TAG, "getDeselectableMediaDevice() cannot found deselectable MediaDevice from : " + this.mPackageName);
        return deviceList;
    }

    List<MediaDevice> getSelectedMediaDevice() {
        List<MediaDevice> deviceList = new ArrayList<>();
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "getSelectedMediaDevice() package name is null or empty!");
            return deviceList;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            for (MediaRoute2Info route : this.mRouterManager.getSelectedRoutes(info)) {
                deviceList.add(new InfoMediaDevice(this.mContext, this.mRouterManager, route, this.mPackageName));
            }
            return deviceList;
        }
        Log.w(TAG, "getSelectedMediaDevice() cannot found selectable MediaDevice from : " + this.mPackageName);
        return deviceList;
    }

    void adjustSessionVolume(RoutingSessionInfo info, int volume) {
        if (info == null) {
            Log.w(TAG, "Unable to adjust session volume. RoutingSessionInfo is empty");
        } else {
            this.mRouterManager.setSessionVolume(info, volume);
        }
    }

    void adjustSessionVolume(int volume) {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "adjustSessionVolume() package name is null or empty!");
            return;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            Log.d(TAG, "adjustSessionVolume() adjust volume : " + volume + ", with : " + this.mPackageName);
            this.mRouterManager.setSessionVolume(info, volume);
            return;
        }
        Log.w(TAG, "adjustSessionVolume() can't found corresponding RoutingSession with : " + this.mPackageName);
    }

    public int getSessionVolumeMax() {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "getSessionVolumeMax() package name is null or empty!");
            return -1;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            return info.getVolumeMax();
        }
        Log.w(TAG, "getSessionVolumeMax() can't found corresponding RoutingSession with : " + this.mPackageName);
        return -1;
    }

    public int getSessionVolume() {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "getSessionVolume() package name is null or empty!");
            return -1;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            return info.getVolume();
        }
        Log.w(TAG, "getSessionVolume() can't found corresponding RoutingSession with : " + this.mPackageName);
        return -1;
    }

    CharSequence getSessionName() {
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(TAG, "Unable to get session name. The package name is null or empty!");
            return null;
        }
        RoutingSessionInfo info = getRoutingSessionInfo();
        if (info != null) {
            return info.getName();
        }
        Log.w(TAG, "Unable to get session name for package: " + this.mPackageName);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDevices() {
        this.mMediaDevices.clear();
        this.mCurrentConnectedDevice = null;
        if (TextUtils.isEmpty(this.mPackageName)) {
            buildAllRoutes();
        } else {
            buildAvailableRoutes();
        }
        dispatchDeviceListAdded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildAllRoutes() {
        for (MediaRoute2Info route : this.mRouterManager.getAllRoutes()) {
            if (DEBUG) {
                Log.d(TAG, "buildAllRoutes() route : " + ((Object) route.getName()) + ", volume : " + route.getVolume() + ", type : " + route.getType());
            }
            if (route.isSystemRoute()) {
                addMediaDevice(route);
            }
        }
    }

    List<RoutingSessionInfo> getActiveMediaSession() {
        return this.mRouterManager.getActiveSessions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildAvailableRoutes() {
        for (MediaRoute2Info route : this.mRouterManager.getAvailableRoutes(this.mPackageName)) {
            if (DEBUG) {
                Log.d(TAG, "buildAvailableRoutes() route : " + ((Object) route.getName()) + ", volume : " + route.getVolume() + ", type : " + route.getType());
            }
            addMediaDevice(route);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void addMediaDevice(android.media.MediaRoute2Info r12) {
        /*
            r11 = this;
            int r0 = r12.getType()
            r1 = 0
            if (r0 == 0) goto L7b
            r2 = 2000(0x7d0, float:2.803E-42)
            if (r0 == r2) goto L7b
            r2 = 2
            if (r0 == r2) goto L6e
            r2 = 3
            if (r0 == r2) goto L6e
            r2 = 4
            if (r0 == r2) goto L6e
            r2 = 8
            if (r0 == r2) goto L46
            r2 = 9
            if (r0 == r2) goto L6e
            r2 = 22
            if (r0 == r2) goto L6e
            r2 = 23
            if (r0 == r2) goto L46
            r2 = 1001(0x3e9, float:1.403E-42)
            if (r0 == r2) goto L7b
            r2 = 1002(0x3ea, float:1.404E-42)
            if (r0 == r2) goto L7b
            switch(r0) {
                case 11: goto L6e;
                case 12: goto L6e;
                case 13: goto L6e;
                default: goto L2f;
            }
        L2f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "addMediaDevice() unknown device type : "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "InfoMediaManager"
            android.util.Log.w(r3, r2)
            goto La7
        L46:
            android.bluetooth.BluetoothAdapter r2 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            java.lang.String r3 = r12.getAddress()
            android.bluetooth.BluetoothDevice r2 = r2.getRemoteDevice(r3)
            com.android.settingslib.bluetooth.LocalBluetoothManager r3 = r11.mBluetoothManager
            com.android.settingslib.bluetooth.CachedBluetoothDeviceManager r3 = r3.getCachedDeviceManager()
            com.android.settingslib.bluetooth.CachedBluetoothDevice r3 = r3.findDevice(r2)
            if (r3 == 0) goto La7
            com.android.settingslib.media.BluetoothMediaDevice r10 = new com.android.settingslib.media.BluetoothMediaDevice
            android.content.Context r5 = r11.mContext
            android.media.MediaRouter2Manager r7 = r11.mRouterManager
            java.lang.String r9 = r11.mPackageName
            r4 = r10
            r6 = r3
            r8 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r1 = r10
            goto La7
        L6e:
            com.android.settingslib.media.PhoneMediaDevice r2 = new com.android.settingslib.media.PhoneMediaDevice
            android.content.Context r3 = r11.mContext
            android.media.MediaRouter2Manager r4 = r11.mRouterManager
            java.lang.String r5 = r11.mPackageName
            r2.<init>(r3, r4, r12, r5)
            r1 = r2
            goto La7
        L7b:
            com.android.settingslib.media.InfoMediaDevice r2 = new com.android.settingslib.media.InfoMediaDevice
            android.content.Context r3 = r11.mContext
            android.media.MediaRouter2Manager r4 = r11.mRouterManager
            java.lang.String r5 = r11.mPackageName
            r2.<init>(r3, r4, r12, r5)
            r1 = r2
            java.lang.String r2 = r11.mPackageName
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto La7
            android.media.RoutingSessionInfo r2 = r11.getRoutingSessionInfo()
            java.util.List r2 = r2.getSelectedRoutes()
            java.lang.String r3 = r12.getId()
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto La7
            com.android.settingslib.media.MediaDevice r2 = r11.mCurrentConnectedDevice
            if (r2 != 0) goto La7
            r11.mCurrentConnectedDevice = r1
        La7:
            if (r1 == 0) goto Lae
            java.util.List<com.android.settingslib.media.MediaDevice> r2 = r11.mMediaDevices
            r2.add(r1)
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.media.InfoMediaManager.addMediaDevice(android.media.MediaRoute2Info):void");
    }

    class RouterManagerCallback extends MediaRouter2Manager.Callback {
        RouterManagerCallback() {
        }

        public void onRoutesAdded(List<MediaRoute2Info> routes) {
            InfoMediaManager.this.refreshDevices();
        }

        public void onPreferredFeaturesChanged(String packageName, List<String> preferredFeatures) {
            if (TextUtils.equals(InfoMediaManager.this.mPackageName, packageName)) {
                InfoMediaManager.this.refreshDevices();
            }
        }

        public void onRoutesChanged(List<MediaRoute2Info> routes) {
            InfoMediaManager.this.refreshDevices();
        }

        public void onRoutesRemoved(List<MediaRoute2Info> routes) {
            InfoMediaManager.this.refreshDevices();
        }

        public void onTransferred(RoutingSessionInfo oldSession, RoutingSessionInfo newSession) {
            if (InfoMediaManager.DEBUG) {
                Log.d(InfoMediaManager.TAG, "onTransferred() oldSession : " + ((Object) oldSession.getName()) + ", newSession : " + ((Object) newSession.getName()));
            }
            InfoMediaManager.this.mMediaDevices.clear();
            InfoMediaManager.this.mCurrentConnectedDevice = null;
            if (TextUtils.isEmpty(InfoMediaManager.this.mPackageName)) {
                InfoMediaManager.this.buildAllRoutes();
            } else {
                InfoMediaManager.this.buildAvailableRoutes();
            }
            String id = InfoMediaManager.this.mCurrentConnectedDevice != null ? InfoMediaManager.this.mCurrentConnectedDevice.getId() : null;
            InfoMediaManager.this.dispatchConnectedDeviceChanged(id);
        }

        public void onTransferFailed(RoutingSessionInfo session, MediaRoute2Info route) {
            InfoMediaManager.this.dispatchOnRequestFailed(0);
        }

        public void onRequestFailed(int reason) {
            InfoMediaManager.this.dispatchOnRequestFailed(reason);
        }

        public void onSessionUpdated(RoutingSessionInfo sessionInfo) {
            InfoMediaManager.this.dispatchDataChanged();
        }
    }
}
