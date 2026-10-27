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

    BYDAutoTestDevice(Context con) {
        super(con);
        this.mManagerImpl = TestManagerImpl.getInstance(con);
        this.mTestSettingsManagerImpl = TestSettingsManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoTestDevice getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new BYDAutoTestDevice(con);
        }
        return sInstance;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_TEST_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_TEST_GET;
    }

    public int setTecLevel(int level) {
        throw new RuntimeException("Stub!");
    }

    public int setAudioDebugMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAudioDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setAVAHDebugMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAVAHDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setFMDebugMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getFMDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int setIflyDebugMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getIflyDebugMode() {
        throw new RuntimeException("Stub!");
    }

    public int sendApkInstallState(int state, int successNum, int failNum, String sn) {
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
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoTestListener listener) {
        this.mManagerImpl.registerTestListener(listener);
        this.mTestSettingsManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoTestListener listener, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoTestListener listener) {
        this.mManagerImpl.unregisterTestListener(listener);
        this.mTestSettingsManagerImpl.unregisterListener(listener);
    }

    public int enableConsoleSuspand(boolean state) {
        return this.mManagerImpl.enableConsoleSuspand(state);
    }

    public int enableTCPDump(boolean state) {
        return this.mManagerImpl.enableTCPDump(state);
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

    public void readConfigurationWordInfo(short did) {
        this.mManagerImpl.readConfigurationWordInfo(did);
    }

    public void writeConfigurationWordInfo(short did, ArrayList<Byte> data) {
        this.mManagerImpl.writeConfigurationWordInfo(did, data);
    }

    public int getPowerSupplyMode(int device) {
        return this.mManagerImpl.getPowerSupplyMode(device);
    }

    public int getAutoHasDevice(int device) {
        return this.mManagerImpl.getAutoHasDevice(device);
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

    public int setNetworkOperator(String code) {
        return this.mManagerImpl.setNetworkOperator(code);
    }

    public int setRestartTBox(int state) {
        return this.mManagerImpl.setRestartTBox(state);
    }

    public int setCloudServieEv(int ev) {
        return this.mManagerImpl.setCloudServieEv(ev);
    }

    public void obdRequest(String id, String data) {
        this.mManagerImpl.obdRequest(id, data);
    }

    public void obdRequest(int iviObdOperationType, int ignCycleNum, ArrayList<NetMap> netMapList, boolean mapOpenStatus) {
        this.mManagerImpl.obdRequest(iviObdOperationType, ignCycleNum, netMapList, mapOpenStatus);
    }

    public int sendDLinkCountryCode(int countryCode) {
        return this.mManagerImpl.sendDLinkCountryCode(countryCode);
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

    public int setConfigArea(String code) {
        return this.mTestSettingsManagerImpl.setConfigArea(code);
    }

    public int setCpaaInstall(boolean state) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(state);
    }

    public int setCpaaInstall(int type, boolean state) {
        return this.mTestSettingsManagerImpl.setCpaaInstall(type, state);
    }

    public int setCarType(int code) {
        return this.mTestSettingsManagerImpl.setCarType(code);
    }

    public void setDefaultLanguage(int countryCode) {
        this.mTestSettingsManagerImpl.setDefaultLanguage(countryCode);
    }

    public int setOffScreenFlag(int type, int flag) {
        return this.mManagerImpl.setOffScreenFlag(type, flag);
    }

    public int queryOffScreenFlag(int type) {
        return this.mManagerImpl.queryOffScreenFlag(type);
    }

    public void reqNetworkOperatorMode() {
        this.mManagerImpl.reqNetworkOperatorMode();
    }

    public void reqOperatorInfo() {
        this.mManagerImpl.reqOperatorInfo();
    }

    public void setManuallyStationedNetwork(String network, int sigLevel) {
        this.mManagerImpl.setManuallyStationedNetwork(network, sigLevel);
    }

    public void setAutomaticNetwork() {
        this.mManagerImpl.setAutomaticNetwork();
    }

    public void reqSimCardState() {
        this.mManagerImpl.reqSimCardState();
    }

    public void setEnergyTest(int status) {
        this.mManagerImpl.setEnergyTest(status);
    }
}
