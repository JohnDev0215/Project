package android.hardware.bydauto.test;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.test.bean.NetMap;
import android.hardware.bydauto.test.tsimpl.TestManagerImpl;
import android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
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
    public static final int TYPE_CHARGE = 1;
    public static final int TYPE_OTA = 0;
    private static BYDAutoTestDevice sInstance;
    private TestManagerImpl mManagerImpl;
    private TestSettingsManagerImpl mTestSettingsManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "android.permission.BYDAUTO_TEST_GET";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_TEST_SET";
    }

    BYDAutoTestDevice(Context context) {
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    public int setTecLevel(int i) {
        throw new RuntimeException("Stub!");
    }

    public int setAudioDebugMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public int getAudioDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setAVAHDebugMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public int getAVAHDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setFMDebugMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public int getFMDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setIflyDebugMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public int getIflyDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int sendApkInstallState(int i, int i2, int i3, String str) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.registerTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.registerListener(absBYDAutoTestListener);
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        this.mManagerImpl.unregisterTestListener(absBYDAutoTestListener);
        this.mTestSettingsManagerImpl.unregisterListener(absBYDAutoTestListener);
    }

    public int enableConsoleSuspand(boolean z) {
        return this.mManagerImpl.enableConsoleSuspand(z);
    }

    public int enableTCPDump(boolean z) {
        return this.mManagerImpl.enableTCPDump(z);
    }

    public void getConsoleSuspandState() {
        this.mManagerImpl.getConsoleSuspandState();
    }

    public void getTCPDumpState() {
        this.mManagerImpl.getTCPDumpState();
    }

    public String geCloudServiceVersion() {
        return this.mManagerImpl.geCloudServiceVersion();
    }

    public int getCloudServieEv() {
        return this.mManagerImpl.getCloudServieEv();
    }

    public int getCloudServiceConnectState() {
        return this.mManagerImpl.getCloudServiceConnectState();
    }

    public void readConfigurationWordInfo(short s) {
        this.mManagerImpl.readConfigurationWordInfo(s);
    }

    public void writeConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        this.mManagerImpl.writeConfigurationWordInfo(s, arrayList);
    }

    public int getPowerSupplyMode(int i) {
        return this.mManagerImpl.getPowerSupplyMode(i);
    }

    public int getAutoHasDevice(int i) {
        return this.mManagerImpl.getAutoHasDevice(i);
    }

    public int getAutoTBoxNetwork() {
        return this.mManagerImpl.getAutoTBoxNetwork();
    }

    public int getUSVersion() {
        return this.mManagerImpl.getUSVersion();
    }

    public int getHasSingleBt() {
        return this.mManagerImpl.getHasSingleBt();
    }

    public int getHasCarplayWired() {
        return this.mManagerImpl.getHasCarplayWired();
    }

    public int getSWCType() {
        return this.mManagerImpl.getSWCType();
    }

    public int getVehicleInfo() {
        return this.mManagerImpl.getVehicleInfo();
    }

    public int getConfigArea() {
        return this.mManagerImpl.getConfigArea();
    }

    public int getSteeringWheelPosition() {
        return this.mManagerImpl.getSteeringWheelPosition();
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

    public int getAutoAudioSource() {
        return this.mManagerImpl.getAutoAudioSource();
    }

    public int getNetworkOperatorList() {
        return this.mManagerImpl.getNetworkOperatorList();
    }

    public String getCurNetworkOperator() {
        return this.mManagerImpl.getCurNetworkOperator();
    }

    public int setNetworkOperator(String str) {
        return this.mManagerImpl.setNetworkOperator(str);
    }

    public int setRestartTBox(int i) {
        return this.mManagerImpl.setRestartTBox(i);
    }

    public int setCloudServieEv(int i) {
        return this.mManagerImpl.setCloudServieEv(i);
    }

    public void obdRequest(String str, String str2) {
        this.mManagerImpl.obdRequest(str, str2);
    }

    public void obdRequest(int i, int i2, ArrayList<NetMap> arrayList, boolean z) {
        this.mManagerImpl.obdRequest(i, i2, arrayList, z);
    }

    public int sendDLinkCountryCode(int i) {
        return this.mManagerImpl.sendDLinkCountryCode(i);
    }

    public int getAPN1FailReson() {
        return this.mManagerImpl.getApn1FailReson();
    }

    public String getAPN1Address() {
        return this.mManagerImpl.getApn1Address();
    }

    public String getYunUUIDStr() {
        return this.mManagerImpl.getYunUuidStr();
    }

    public int setConfigArea(String str) {
        return this.mTestSettingsManagerImpl.setConfigArea(str);
    }

    public int setCpaaInstall(boolean z) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(z);
    }

    public int setCpaaInstall(int i, boolean z) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(i, z);
    }

    public int setCarType(int i) {
        return this.mTestSettingsManagerImpl.setCarType(i);
    }

    public void setDefaultLanguage(int i) {
        this.mTestSettingsManagerImpl.setDefaultLanguage(i);
    }

    public int setOffScreenFlag(int i, int i2) {
        return this.mManagerImpl.setOffScreenFlag(i, i2);
    }

    public int queryOffScreenFlag(int i) {
        return this.mManagerImpl.queryOffScreenFlag(i);
    }

    public void reqNetworkOperatorMode() {
        this.mManagerImpl.reqNetworkOperatorMode();
    }

    public void reqOperatorInfo() {
        this.mManagerImpl.reqOperatorInfo();
    }

    public void setManuallyStationedNetwork(String str, int i) {
        this.mManagerImpl.setManuallyStationedNetwork(str, i);
    }

    public void setAutomaticNetwork() {
        this.mManagerImpl.setAutomaticNetwork();
    }

    public void reqSimCardState() {
        this.mManagerImpl.reqSimCardState();
    }

    public void setEnergyTest(int i) {
        this.mManagerImpl.setEnergyTest(i);
    }
}
