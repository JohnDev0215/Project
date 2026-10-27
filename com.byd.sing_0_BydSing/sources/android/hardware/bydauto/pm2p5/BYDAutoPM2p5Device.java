package android.hardware.bydauto.pm2p5;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.pm2p5.tsimpl.Pm2p5ManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoPM2p5Device extends AbsBYDAutoDevice {
    public static final int AUTO_PURIFICATION_STATE_ON = 1;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final String FEATURE_ANION_DETECT = "AnionDetect";
    public static final String FEATURE_DUAL_CHANNEL_DETECT = "DualChannelDetect";
    public static final int INVALID = 0;
    public static final int NEGATIVE_ION_STATE_OFF = 2;
    public static final int NEGATIVE_ION_STATE_ON = 1;
    public static final String PERMISSION_BYDAUTO_PM2P5_COMMON = "android.permission.BYDAUTO_PM2P5_COMMON";
    public static final String PERMISSION_BYDAUTO_PM2P5_GET = "android.permission.BYDAUTO_PM2P5_GET";
    public static final String PERMISSION_BYDAUTO_PM2P5_SET = "android.permission.BYDAUTO_PM2P5_SET";
    public static final int PM2P5_COMMAND_BUSY = -2147482647;
    public static final int PM2P5_COMMAND_FAILED = -2147482648;
    public static final int PM2P5_COMMAND_INVALID_VALUE = -2147482645;
    public static final int PM2P5_COMMAND_SUCCESS = 0;
    public static final int PM2P5_COMMAND_TIMEOUT = -2147482646;
    public static final String PM2P5_GET_PERM = "android.permission.BYDAUTO_PM2P5_GET";
    public static final int PM2P5_LEVEL_EXCELLENT = 1;
    public static final int PM2P5_LEVEL_GOOD = 2;
    public static final int PM2P5_LEVEL_HEAVY = 5;
    public static final int PM2P5_LEVEL_INVALID = 0;
    public static final int PM2P5_LEVEL_LOW_GRADE = 3;
    public static final int PM2P5_LEVEL_MIDDLE = 4;
    public static final int PM2P5_LEVEL_SERIOUS = 6;
    public static final int PM2P5_ONLINE_STATE_NULL = 0;
    public static final int PM2P5_ONLINE_STATE_OFF = 0;
    public static final int PM2P5_ONLINE_STATE_ON = 1;
    public static final String PM2P5_SET_PERM = "android.permission.BYDAUTO_PM2P5_SET";
    public static final int PM2P5_STATE_CLOSE = 3;
    public static final int PM2P5_STATE_IN = 1;
    public static final int PM2P5_STATE_OFF = 0;
    public static final int PM2P5_STATE_ON = 1;
    public static final int PM2P5_STATE_OUT = 2;
    public static final int PM2P5_VALUE_MAX = 3000;
    public static final int PM2P5_VALUE_MIN = 0;
    public static final int PROMPT_INFO_INNER_LOOP = 2;
    public static final int PROMPT_INFO_NORMAL = 0;
    public static final int PROMPT_INFO_START_AC = 1;
    public static final int RAPID_PURIFICATION_PM2P5_STATE_OFF = 2;
    public static final int RAPID_PURIFICATION_PM2P5_STATE_ON = 1;
    public static final int RAPID_PURIFICATION_PM2P5_STATE_STOP = 3;
    public static final int STATE_CLOSE = 2;
    public static final int STATE_OPEN = 1;
    protected static final String TAG = "BYDAutoPM2p5Device";
    public static final int WARNING_INFO_EXCESS_IN = 1;
    public static final int WARNING_INFO_EXCESS_OUT = 2;
    public static final int WARNING_INFO_NORMAL = 0;
    private static volatile BYDAutoPM2p5Device mBYDAutoPM2p5Device;
    private Pm2p5ManagerImpl mPm2p5ManagerImpl;

    private BYDAutoPM2p5Device(Context context) {
        super(context);
        this.mPm2p5ManagerImpl = Pm2p5ManagerImpl.getInstance(context);
    }

    public static BYDAutoPM2p5Device getInstance(Context context) {
        if (mBYDAutoPM2p5Device == null) {
            synchronized (BYDAutoPM2p5Device.class) {
                if (mBYDAutoPM2p5Device == null) {
                    mBYDAutoPM2p5Device = new BYDAutoPM2p5Device(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoPM2p5Device;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mPm2p5ManagerImpl.get(iArr, cls);
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
        return "android.permission.BYDAUTO_PM2P5_GET";
    }

    public int getNegativeIonState() {
        return this.mPm2p5ManagerImpl.getNegativeIonState();
    }

    public int getPM2p5AnionState() {
        return this.mPm2p5ManagerImpl.getPM2p5AnionState();
    }

    public int[] getPM2p5CheckState() {
        return this.mPm2p5ManagerImpl.getPM2p5CheckState();
    }

    public int getPM2p5DualChannelDetectState() {
        return this.mPm2p5ManagerImpl.getPM2p5DualChannelDetectState();
    }

    public int[] getPM2p5Level() {
        return this.mPm2p5ManagerImpl.getPM2p5Level();
    }

    public int getPM2p5OnlineState() {
        return this.mPm2p5ManagerImpl.getPM2p5OnlineState();
    }

    public int[] getPM2p5Value() {
        return this.mPm2p5ManagerImpl.getPM2p5Value();
    }

    public int getPM2p5WarningInfo() {
        return this.mPm2p5ManagerImpl.getPM2p5WarningInfo();
    }

    public int getPromptInfo() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_PM2P5_SET";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        this.mPm2p5ManagerImpl.registerListener(absBYDAutoPM2p5Listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mPm2p5ManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllStatus() {
    }

    public int setNegativeIonState(int i) {
        return this.mPm2p5ManagerImpl.setNegativeIonState(i);
    }

    public int setPM2p5AnionDetectState(int i) {
        return this.mPm2p5ManagerImpl.setPM2p5AnionDetectState(i);
    }

    public int setPM2p5CheckState(int i) {
        return this.mPm2p5ManagerImpl.setPM2p5CheckState(0, i);
    }

    public int setPM2p5DualChannelDetectState(int i) {
        return this.mPm2p5ManagerImpl.setPM2p5DualChannelDetectState(i);
    }

    public int syncPM2p5AllStatus() {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        this.mPm2p5ManagerImpl.unregisterListener(absBYDAutoPM2p5Listener);
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener, int[] iArr) {
        this.mPm2p5ManagerImpl.registerListener(absBYDAutoPM2p5Listener);
    }
}
