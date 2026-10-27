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

    private BYDAutoRadarDevice(Context context) {
        super(context);
        this.mRadarManagerImpl = RadarManagerImpl.getInstance(context);
    }

    public static BYDAutoRadarDevice getInstance(Context context) {
        if (mBYDAutoRadarDevice == null) {
            synchronized (BYDAutoPM2p5Device.class) {
                if (mBYDAutoRadarDevice == null) {
                    mBYDAutoRadarDevice = new BYDAutoRadarDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoRadarDevice;
    }

    public int[] getAllRadarDistance() {
        return this.mRadarManagerImpl.getAllRadarDistance();
    }

    public int[] getAllRadarObstacleDistances() {
        return new int[0];
    }

    public int[] getAllRadarProbeStates() {
        return this.mRadarManagerImpl.getAllRadarProbeStates();
    }

    public int[][] getAllRadarStatus() {
        return (int[][]) Array.newInstance((Class<?>) int.class, 0, 0);
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_RADAR_GET;
    }

    public int getRadarManufacture() {
        return 0;
    }

    public int getRadarObstacleDistance(int i7) {
        return this.mRadarManagerImpl.getRadarObstacleDistance(i7);
    }

    public int getRadarProbeState(int i7) {
        return this.mRadarManagerImpl.getRadarProbeState(i7);
    }

    public int getReverseRadarKeyState() {
        return this.mRadarManagerImpl.getReverseRadarKeyState();
    }

    public int getReverseRadarSwitchState() {
        return this.mRadarManagerImpl.getReverseRadarSwitchState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_RADAR_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        this.mRadarManagerImpl.registerListener(absBYDAutoRadarListener);
    }

    public void registerListener(AbsBYDAutoRadarListener absBYDAutoRadarListener, int[] iArr) {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mRadarManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllStatus() {
    }

    public int setRadarSoundState(int i7) {
        return 0;
    }

    public int setReverseRadarSwitchState(int i7) {
        return this.mRadarManagerImpl.setReverseRadarSwitchState(i7);
    }

    public void unregisterListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        this.mRadarManagerImpl.unregisterListener(absBYDAutoRadarListener);
    }
}
