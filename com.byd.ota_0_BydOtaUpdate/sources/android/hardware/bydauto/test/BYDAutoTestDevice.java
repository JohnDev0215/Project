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
    public static final int FLAG_BLOCK_OFFSCREEN = 1;
    public static final int FLAG_NOT_BLOCK_OFFSCREEN = 0;
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
    public static final String TAG = "BYDAutoTestDevice";
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
    public static final int TYPE_CHARGE = 1;
    public static final int TYPE_OTA = 0;
    private static BYDAutoTestDevice sInstance;
    private TestManagerImpl mManagerImpl;
    private TestSettingsManagerImpl mTestSettingsManagerImpl;

    public BYDAutoTestDevice(Context context) {
        super(context);
        this.mManagerImpl = TestManagerImpl.getInstance(context);
        this.mTestSettingsManagerImpl = TestSettingsManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoTestDevice getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BYDAutoTestDevice(context);
        }
        return sInstance;
    }

    public int enableConsoleSuspand(boolean z) {
        return this.mManagerImpl.enableConsoleSuspand(z);
    }

    public int enableTCPDump(boolean z) {
        return this.mManagerImpl.enableTCPDump(z);
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

    public int getAutoHasDevice(int i2) {
        return this.mManagerImpl.getAutoHasDevice(i2);
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

    public int getPowerSupplyMode(int i2) {
        return this.mManagerImpl.getPowerSupplyMode(i2);
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
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int queryOffScreenFlag(int i2) {
        return this.mManagerImpl.queryOffScreenFlag(i2);
    }

    public void readConfigurationWordInfo(short s) {
        this.mManagerImpl.readConfigurationWordInfo(s);
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.registerTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.registerListener(absBYDAutoTestListener);
    }

    public void reqNetworkOperatorMode() {
        this.mManagerImpl.reqNetworkOperatorMode();
    }

    public void reqOperatorInfo() {
        this.mManagerImpl.reqOperatorInfo();
    }

    public void reqSimCardState() {
        this.mManagerImpl.reqSimCardState();
    }

    public int sendApkInstallState(int i2, int i3, int i4, String str) {
        throw new RuntimeException("Stub!");
    }

    public int sendDLinkCountryCode(int i2) {
        return this.mManagerImpl.sendDLinkCountryCode(i2);
    }

    public int setAVAHDebugMode(int i2) {
        throw new RuntimeException("Stub!");
    }

    public int setAudioDebugMode(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void setAutomaticNetwork() {
        this.mManagerImpl.setAutomaticNetwork();
    }

    public int setCarType(int i2) {
        return this.mTestSettingsManagerImpl.setCarType(i2);
    }

    public int setCloudServieEv(int i2) {
        return this.mManagerImpl.setCloudServieEv(i2);
    }

    public int setConfigArea(String str) {
        return this.mTestSettingsManagerImpl.setConfigArea(str);
    }

    public int setCpaaInstall(boolean z) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(z);
    }

    public void setDefaultLanguage(int i2) {
        this.mTestSettingsManagerImpl.setDefaultLanguage(i2);
    }

    public void setEnergyTest(int i2) {
        this.mManagerImpl.setEnergyTest(i2);
    }

    public int setFMDebugMode(int i2) {
        throw new RuntimeException("Stub!");
    }

    public int setIflyDebugMode(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void setManuallyStationedNetwork(String str, int i2) {
        this.mManagerImpl.setManuallyStationedNetwork(str, i2);
    }

    public int setNetworkOperator(String str) {
        return this.mManagerImpl.setNetworkOperator(str);
    }

    public int setOffScreenFlag(int i2, int i3) {
        return this.mManagerImpl.setOffScreenFlag(i2, i3);
    }

    public int setRestartTBox(int i2) {
        return this.mManagerImpl.setRestartTBox(i2);
    }

    public int setTecLevel(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.unregisterTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.unregisterListener(absBYDAutoTestListener);
    }

    public void writeConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        this.mManagerImpl.writeConfigurationWordInfo(s, arrayList);
    }

    public void obdRequest(int i2, int i3, ArrayList<NetMap> arrayList, boolean z) {
        this.mManagerImpl.obdRequest(i2, i3, arrayList, z);
    }

    public int setCpaaInstall(int i2, boolean z) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(i2, z);
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
