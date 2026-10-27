package com.android.settingslib.media;

import android.media.MediaRouter2Manager;
import android.media.RoutingSessionInfo;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class InfoMediaManager extends MediaManager {
    private LocalBluetoothManager mBluetoothManager;
    private MediaDevice mCurrentConnectedDevice;

    @VisibleForTesting
    final Executor mExecutor;

    @VisibleForTesting
    final RouterManagerCallback mMediaRouterCallback;

    @VisibleForTesting
    String mPackageName;

    @VisibleForTesting
    MediaRouter2Manager mRouterManager;

    class RouterManagerCallback extends MediaRouter2Manager.Callback {
    }

    static {
        Log.isLoggable("InfoMediaManager", 3);
    }

    boolean connectDeviceWithoutPackageName(MediaDevice mediaDevice) {
        List activeSessions = this.mRouterManager.getActiveSessions();
        if (activeSessions.size() <= 0) {
            return false;
        }
        this.mRouterManager.transfer((RoutingSessionInfo) activeSessions.get(0), mediaDevice.mRouteInfo);
        return true;
    }

    private RoutingSessionInfo getRoutingSessionInfo() {
        return (RoutingSessionInfo) this.mRouterManager.getRoutingSessions(this.mPackageName).get(r1.size() - 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    @com.android.internal.annotations.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void addMediaDevice(android.media.MediaRoute2Info r9) {
        /*
            r8 = this;
            int r0 = r9.getType()
            if (r0 == 0) goto L79
            r1 = 2000(0x7d0, float:2.803E-42)
            if (r0 == r1) goto L79
            r1 = 2
            if (r0 == r1) goto L6d
            r1 = 3
            if (r0 == r1) goto L6d
            r1 = 4
            if (r0 == r1) goto L6d
            r1 = 8
            if (r0 == r1) goto L45
            r1 = 9
            if (r0 == r1) goto L6d
            r1 = 22
            if (r0 == r1) goto L6d
            r1 = 23
            if (r0 == r1) goto L45
            r1 = 1001(0x3e9, float:1.403E-42)
            if (r0 == r1) goto L79
            r1 = 1002(0x3ea, float:1.404E-42)
            if (r0 == r1) goto L79
            switch(r0) {
                case 11: goto L6d;
                case 12: goto L6d;
                case 13: goto L6d;
                default: goto L2e;
            }
        L2e:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r1 = "addMediaDevice() unknown device type : "
            r9.append(r1)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            java.lang.String r0 = "InfoMediaManager"
            android.util.Log.w(r0, r9)
            goto L6b
        L45:
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            java.lang.String r1 = r9.getAddress()
            android.bluetooth.BluetoothDevice r0 = r0.getRemoteDevice(r1)
            com.android.settingslib.bluetooth.LocalBluetoothManager r1 = r8.mBluetoothManager
            com.android.settingslib.bluetooth.CachedBluetoothDeviceManager r1 = r1.getCachedDeviceManager()
            com.android.settingslib.bluetooth.CachedBluetoothDevice r4 = r1.findDevice(r0)
            if (r4 == 0) goto L6b
            com.android.settingslib.media.BluetoothMediaDevice r0 = new com.android.settingslib.media.BluetoothMediaDevice
            android.content.Context r3 = r8.mContext
            android.media.MediaRouter2Manager r5 = r8.mRouterManager
            java.lang.String r7 = r8.mPackageName
            r2 = r0
            r6 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            goto La4
        L6b:
            r0 = 0
            goto La4
        L6d:
            com.android.settingslib.media.PhoneMediaDevice r0 = new com.android.settingslib.media.PhoneMediaDevice
            android.content.Context r1 = r8.mContext
            android.media.MediaRouter2Manager r2 = r8.mRouterManager
            java.lang.String r3 = r8.mPackageName
            r0.<init>(r1, r2, r9, r3)
            goto La4
        L79:
            com.android.settingslib.media.InfoMediaDevice r0 = new com.android.settingslib.media.InfoMediaDevice
            android.content.Context r1 = r8.mContext
            android.media.MediaRouter2Manager r2 = r8.mRouterManager
            java.lang.String r3 = r8.mPackageName
            r0.<init>(r1, r2, r9, r3)
            java.lang.String r1 = r8.mPackageName
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto La4
            android.media.RoutingSessionInfo r1 = r8.getRoutingSessionInfo()
            java.util.List r1 = r1.getSelectedRoutes()
            java.lang.String r9 = r9.getId()
            boolean r9 = r1.contains(r9)
            if (r9 == 0) goto La4
            com.android.settingslib.media.MediaDevice r9 = r8.mCurrentConnectedDevice
            if (r9 != 0) goto La4
            r8.mCurrentConnectedDevice = r0
        La4:
            if (r0 == 0) goto Lab
            java.util.List<com.android.settingslib.media.MediaDevice> r8 = r8.mMediaDevices
            r8.add(r0)
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.media.InfoMediaManager.addMediaDevice(android.media.MediaRoute2Info):void");
    }
}
