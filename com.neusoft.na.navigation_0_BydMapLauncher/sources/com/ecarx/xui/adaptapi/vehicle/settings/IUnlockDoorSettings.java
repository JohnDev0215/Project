package com.ecarx.xui.adaptapi.vehicle.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IUnlockDoorSettings {
    public static final int FLAMEOUT_UNLOCK_DOOR_ALL = 2;
    public static final int FLAMEOUT_UNLOCK_DOOR_DEFAULT = 2147483637;
    public static final int FLAMEOUT_UNLOCK_DOOR_DRIVER = 1;
    public static final int FLAMEOUT_UNLOCK_DOOR_OFF = 0;
    public static final int REMOTE_KEY_UNLOCK_DOOR_ALL = 2;
    public static final int REMOTE_KEY_UNLOCK_DOOR_DEFAULT = 2147483637;
    public static final int REMOTE_KEY_UNLOCK_DOOR_DRIVER_FIRST = 1;
    public static final int REMOTE_KEY_UNLOCK_DOOR_OFF = 0;

    public interface FlameoutUnlockDoorCallBack {
        void onFlameoutUnlockDoorModeChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlameoutUnlockDoorMode {
    }

    public interface RemoteKeyUnlockDoorCallBack {
        void onRemoteKeyUnlockDoorModeChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RemoteKeyUnlockDoorMode {
    }

    int getFlameoutUnlockDoorMode();

    int getRemoteKeyUnlockDoorMode();

    void registerFlameoutUnlockDoorCallBack(FlameoutUnlockDoorCallBack flameoutUnlockDoorCallBack);

    void registerRemoteKeyUnlockDoorCallBack(RemoteKeyUnlockDoorCallBack remoteKeyUnlockDoorCallBack);

    void setFlameoutUnlockDoorMode(int i);

    void setRemoteKeyUnlockDoorMode(int i);

    void unregisterFlameoutUnlockDoorCallBack(FlameoutUnlockDoorCallBack flameoutUnlockDoorCallBack);

    void unregisterRemoteKeyUnlockDoorCallBack(RemoteKeyUnlockDoorCallBack remoteKeyUnlockDoorCallBack);
}
