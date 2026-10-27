package android.hardware.bydauto.radar;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.radar.tsimpl.RadarManagerImpl;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoRadarDevice extends AbsBYDAutoDevice {
    public static final int LINK_ERROR = 65535;
    public static final String PERMISSION_BYDAUTO_RADAR_COMMON = "android.permission.BYDAUTO_RADAR_COMMON";
    public static final String PERMISSION_BYDAUTO_RADAR_GET = "android.permission.BYDAUTO_RADAR_GET";
    public static final String PERMISSION_BYDAUTO_RADAR_SET = "android.permission.BYDAUTO_RADAR_SET";
    public static final int RADAR_AREA_FRONT_LEFT_MID = 7;
    public static final int RADAR_AREA_FRONT_RIGHT_MID = 8;
    public static final int RADAR_AREA_LEFT = 5;
    public static final int RADAR_AREA_LEFT_FRONT = 1;
    public static final int RADAR_AREA_LEFT_REAR = 3;
    public static final int RADAR_AREA_MIDDLE_REAR = 9;
    public static final int RADAR_AREA_RIGHT = 6;
    public static final int RADAR_AREA_RIGHT_FRONT = 2;
    public static final int RADAR_AREA_RIGHT_REAR = 4;
    public static final int RADAR_COMMAND_BUSY = -2147482647;
    public static final int RADAR_COMMAND_FAILED = -2147482648;
    public static final int RADAR_COMMAND_INVALID_VALUE = -2147482645;
    public static final int RADAR_COMMAND_SUCCESS = 0;
    public static final int RADAR_COMMAND_TIMEOUT = -2147482646;
    public static final int RADAR_DISTANCE_MAX = 155;
    public static final int RADAR_DISTANCE_MIN = 0;
    public static final int RADAR_MANUFACTURE_15_SECTOR = 0;
    public static final int RADAR_MANUFACTURE_VALEO = 1;
    public static final int RADAR_OBSTACLE_DISTANCE_ALL = 2;
    public static final int RADAR_OBSTACLE_DISTANCE_MAX = 6;
    public static final int RADAR_OBSTACLE_DISTANCE_MIN = 0;
    public static final int RADAR_OBSTACLE_DISTANCE_SAFE = 14;
    public static final int RADAR_OBSTACLE_DIS_MAX = 12;
    public static final int RADAR_PROBE_STATE_ABNORMAL = 0;
    public static final int RADAR_PROBE_STATE_ALL = 1;
    public static final int RADAR_PROBE_STATE_GREEN = 2;
    public static final int RADAR_PROBE_STATE_RED = 4;
    public static final int RADAR_PROBE_STATE_SAFE = 1;
    public static final int RADAR_PROBE_STATE_YELLOW = 3;
    public static final int RADAR_REVERSE_SWITCH_OFF = 0;
    public static final int RADAR_REVERSE_SWITCH_ON = 1;
    public static final int RADAR_SOUND_STATE_OFF = 0;
    public static final int RADAR_SOUND_STATE_ON = 1;
    public static final int RADAR_STATUS_ALL = 1;
    protected static final String TAG = "BYDAutoRadarDevice";
    private static volatile BYDAutoRadarDevice mBYDAutoRadarDevice;
    private RadarManagerImpl mRadarManagerImpl;

    private BYDAutoRadarDevice(Context con) {
        super(con);
        this.mRadarManagerImpl = RadarManagerImpl.getInstance(con);
    }

    public static BYDAutoRadarDevice getInstance(Context con) {
        if (mBYDAutoRadarDevice == null) {
            synchronized (BYDAutoPM2p5Device.class) {
                if (mBYDAutoRadarDevice == null) {
                    mBYDAutoRadarDevice = new BYDAutoRadarDevice(con.getApplicationContext());
                }
            }
        }
        return mBYDAutoRadarDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_RADAR_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_RADAR_GET;
    }

    public int getRadarProbeState(int area) {
        return this.mRadarManagerImpl.getRadarProbeState(area);
    }

    public int[] getAllRadarProbeStates() {
        return this.mRadarManagerImpl.getAllRadarProbeStates();
    }

    public int getRadarObstacleDistance(int area) {
        return this.mRadarManagerImpl.getRadarObstacleDistance(area);
    }

    public int[] getAllRadarDistance() {
        return this.mRadarManagerImpl.getAllRadarDistance();
    }

    public int[] getAllRadarObstacleDistances() {
        return new int[0];
    }

    public int[][] getAllRadarStatus() {
        return (int[][]) Array.newInstance((Class<?>) int.class, 0, 0);
    }

    public int getReverseRadarSwitchState() {
        return this.mRadarManagerImpl.getReverseRadarSwitchState();
    }

    public int setReverseRadarSwitchState(int state) {
        return this.mRadarManagerImpl.setReverseRadarSwitchState(state);
    }

    public int setRadarSoundState(int state) {
        return 0;
    }

    public int getRadarManufacture() {
        return 0;
    }

    public void setAllStatus() {
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoRadarListener listener) {
        this.mRadarManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoRadarListener listener, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoRadarListener listener) {
        this.mRadarManagerImpl.unregisterListener(listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mRadarManagerImpl.set(eventTypes, eventValue);
    }

    public int getReverseRadarKeyState() {
        return this.mRadarManagerImpl.getReverseRadarKeyState();
    }
}
