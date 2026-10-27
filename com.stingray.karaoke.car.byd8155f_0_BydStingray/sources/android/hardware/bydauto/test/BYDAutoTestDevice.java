package android.hardware.bydauto.test;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.test.bean.NetMap;
import android.hardware.bydauto.test.tsimpl.TestManagerImpl;
import android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoTestDevice extends AbsBYDAutoDevice {
    public static final int APK_NUM_MAX = 255;
    public static final int APK_NUM_MIN = 0;
    public static final int AUDIO_DEBUG_MODE_ENTER = 1;
    public static final int AVAH_DEBUG_1K = 1;
    public static final int AVAH_DEBUG_2K = 2;
    public static final int AVAH_DEBUG_3K = 3;
    public static final int CHARGE_COMMAND_BUSY = -2147482647;
    public static final int CHARGE_COMMAND_FAILED = -2147482648;
    public static final int CHARGE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int CHARGE_COMMAND_SUCCESS = 0;
    public static final int CHARGE_COMMAND_TIMEOUT = -2147482646;
    public static final int DEBUG_EXIT = 0;
    public static final int FM_DEBUG_1K = 1;
    public static final int FM_DEBUG_2K = 2;
    public static final int FM_DEBUG_3K = 3;
    public static final int IFLY_DEBUG_FUNCTION = 2;
    public static final int IFLY_DEBUG_RECORDING = 1;
    public static final int INSTALL_DONE = 2;
    public static final int INSTALL_GOING = 1;
    public static final int INSTALL_NOT_START = 0;
    public static final String PERMISSION_BYDAUTO_TEST_COMMON = "android.permission.BYDAUTO_TEST_COMMON";
    public static final String PERMISSION_BYDAUTO_TEST_GET = "android.permission.BYDAUTO_TEST_GET";
    public static final String PERMISSION_BYDAUTO_TEST_SET = "android.permission.BYDAUTO_TEST_SET";
    public static final int REQUEST_INSTALL = 1;
    protected static final String TAG = "BYDAutoTestDevice";
    public static final int TEST_COMMAND_BUSY = -2147482647;
    public static final int TEST_COMMAND_FAILED = -2147482648;
    public static final int TEST_COMMAND_INVALID_VALUE = -2147482645;
    public static final int TEST_COMMAND_SUCCESS = 0;
    public static final int TEST_COMMAND_TIMEOUT = -2147482646;
    public static final int TEST_SET_TEC_LEVEL_0 = 0;
    public static final int TEST_SET_TEC_LEVEL_1 = 1;
    public static final int TEST_SET_TEC_LEVEL_2 = 2;
    public static final int TEST_SET_TEC_LEVEL_3 = 3;
    public static final int TEST_SET_TEC_LEVEL_4 = 4;
    public static final int TEST_SET_TEC_LEVEL_5 = 5;
    private static BYDAutoTestDevice sInstance;
    private TestManagerImpl mManagerImpl;
    private TestSettingsManagerImpl mTestSettingsManagerImpl;

    public BYDAutoTestDevice(Context context) {
        super(context);
        this.mManagerImpl = TestManagerImpl.getInstance(context);
        this.mTestSettingsManagerImpl = TestSettingsManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoTestDevice getInstance(Context context) {
        try {
            if (sInstance == null) {
                sInstance = new BYDAutoTestDevice(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }

    public int enableConsoleSuspand(boolean z6) {
        return this.mManagerImpl.enableConsoleSuspand(z6);
    }

    public int enableTCPDump(boolean z6) {
        return this.mManagerImpl.enableTCPDump(z6);
    }

    public String geCloudServiceVersion() {
        return this.mManagerImpl.geCloudServiceVersion();
    }

    public String getAPN1Address() {
        return this.mManagerImpl.getApn1Address();
    }

    public int getAPN1FailReson() {
        return this.mManagerImpl.getApn1FailReson();
    }

    public int getAVAHDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int getAudioDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int getAutoAudioSource() {
        return this.mManagerImpl.getAutoAudioSource();
    }

    public int getAutoHasDevice(int i7) {
        return this.mManagerImpl.getAutoHasDevice(i7);
    }

    public int getAutoMicCount() {
        return this.mManagerImpl.getAutoMicCount();
    }

    public int getAutoPowerType() {
        return this.mManagerImpl.getAutoPowerType();
    }

    public int getAutoSpeakerCount() {
        return this.mManagerImpl.getAutoSpeakerCount();
    }

    public int getAutoTBoxNetwork() {
        return this.mManagerImpl.getAutoTBoxNetwork();
    }

    public int getCloudServiceConnectState() {
        return this.mManagerImpl.getCloudServiceConnectState();
    }

    public int getCloudServieEv() {
        return this.mManagerImpl.getCloudServieEv();
    }

    public int getConfigArea() {
        return this.mManagerImpl.getConfigArea();
    }

    public void getConsoleSuspandState() {
        this.mManagerImpl.getConsoleSuspandState();
    }

    public String getCurNetworkOperator() {
        return this.mManagerImpl.getCurNetworkOperator();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    public int getFMDebugMode() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_TEST_GET;
    }

    public int getHasCarplayWired() {
        return this.mManagerImpl.getHasCarplayWired();
    }

    public int getHasSingleBt() {
        return this.mManagerImpl.getHasSingleBt();
    }

    public int getIflyDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int getNetworkOperatorList() {
        return this.mManagerImpl.getNetworkOperatorList();
    }

    public int getPowerSupplyMode(int i7) {
        return this.mManagerImpl.getPowerSupplyMode(i7);
    }

    public int getSWCType() {
        return this.mManagerImpl.getSWCType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_TEST_SET;
    }

    public int getSteeringWheelPosition() {
        return this.mManagerImpl.getSteeringWheelPosition();
    }

    public void getTCPDumpState() {
        this.mManagerImpl.getTCPDumpState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public int getUSVersion() {
        return this.mManagerImpl.getUSVersion();
    }

    public int getVehicleInfo() {
        return this.mManagerImpl.getVehicleInfo();
    }

    public String getYunUUIDStr() {
        return this.mManagerImpl.getYunUuidStr();
    }

    public void obdRequest(String str, String str2) {
        this.mManagerImpl.obdRequest(str, str2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void readConfigurationWordInfo(short s6) {
        this.mManagerImpl.readConfigurationWordInfo(s6);
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.registerTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.registerListener(absBYDAutoTestListener);
    }

    public int sendApkInstallState(int i7, int i10, int i11, String str) {
        throw new RuntimeException("Stub!");
    }

    public int sendDLinkCountryCode(int i7) {
        return this.mManagerImpl.sendDLinkCountryCode(i7);
    }

    public int setAVAHDebugMode(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setAudioDebugMode(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setCloudServieEv(int i7) {
        return this.mManagerImpl.setCloudServieEv(i7);
    }

    public int setConfigArea(String str) {
        return this.mTestSettingsManagerImpl.setConfigArea(str);
    }

    public int setCpaaInstall(boolean z6) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(z6);
    }

    public int setFMDebugMode(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setIflyDebugMode(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setNetworkOperator(String str) {
        return this.mManagerImpl.setNetworkOperator(str);
    }

    public int setRestartTBox(int i7) {
        return this.mManagerImpl.setRestartTBox(i7);
    }

    public int setTecLevel(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.unregisterTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.unregisterListener(absBYDAutoTestListener);
    }

    public void writeConfigurationWordInfo(short s6, ArrayList<Byte> arrayList) {
        this.mManagerImpl.writeConfigurationWordInfo(s6, arrayList);
    }

    public void obdRequest(int i7, int i10, ArrayList<NetMap> arrayList, boolean z6) {
        this.mManagerImpl.obdRequest(i7, i10, arrayList, z6);
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
