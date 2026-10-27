package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface CarController extends CallbackController<Callback>, Dumpable {
    public static final int INVALID = -1;
    public static final int[] INVALID_ARRAY = {-1};

    public interface Callback {
        default void onAINavigationStateChanged(int value) {
        }

        default void onAcDefrostStateChanged(int area, int state) {
        }

        default void onAcRearPanelLockStateChanged(int value) {
        }

        default void onAdaptiveFrontLightFault(byte fault) {
        }

        default void onAiDistanceLightStateChanged(int value) {
        }

        default void onAutoParkingStateChanged(int value) {
        }

        default void onAvmDockerStatusChanged(int value) {
        }

        default void onBacklightLevelsChanged(int level) {
        }

        default void onBlindFault(byte fault) {
        }

        default void onBlindMonitorStateChanged(int type, int value) {
        }

        default void onCameraUseChange(String packageName, boolean state) {
        }

        default void onChargingListenerConnected(boolean isBinding) {
        }

        default void onChargingScheduleStateChanged(int state) {
        }

        default void onDoorLightStateChanged(int value) {
        }

        default void onDoorLockStatusChanged(int area, int state) {
        }

        default void onDoorStateChanged(int area, int state) {
        }

        default void onDynamicSuspensionModeChanged(int value) {
        }

        default void onElecCarBodyStablySysStateChanged(int value) {
        }

        default void onEmergencyLaneAssistance32FStateChanged(int state) {
        }

        default void onEnergyFeedbackLevelChanged(int value) {
        }

        default void onEpbStateChanged(int state) {
        }

        default void onEspOffKeyStatusChanged(int state) {
        }

        default void onHasMessageChanged(int id, int state) {
        }

        default void onHudStateChanged(int state) {
        }

        default void onISLIWarningLevelSwitchStatusChanged(int state) {
        }

        default void onInsideLightDoorStateChanged(int value) {
        }

        default void onInstrumentBrightnessChanged(int value) {
        }

        default void onLaneAssistTypeChanged(int value) {
        }

        default void onMessage5sOnlineStateChanged(int id, int state) {
        }

        default void onMicUseChange(String packageName, boolean state) {
        }

        default void onMusicInfoUrlChange(String musicName, String artistName, int playState, int musicSource, String imgUrl) {
        }

        default void onMusicPlayStateCallback(int state, int mediaMode) {
        }

        default void onOperationModeChanged(int mode) {
        }

        default void onOperationModeNormalChanged(int mode) {
        }

        default void onPM2p5CheckStateChanged(int inState, int outState) {
        }

        default void onPM2p5DualChannelDetectChanged(int state) {
        }

        default void onPM2p5ValueChanged(int valueIn, int valueOut) {
        }

        default void onRequestVideoFocusChanged(int requestType) {
        }

        default void onReverseRadarSwitchStateChanged(int value) {
        }

        default void onSessionStatusChangedOnAndroidAuto(boolean connected) {
        }

        default void onSessionStatusChangedOnCarPlay(boolean connected) {
        }

        default void onSteepDescentSlowDownStateChanged(int value) {
        }

        default void onSteeringWheelWarmStateChanged(int value) {
        }

        default void onTrafficSignRecognizeStateChanged(int signType, int value) {
        }

        default void onTransportModeChanged(int state) {
        }

        default void onTransportModeSwitchChanged(int state) {
        }

        default void onTsrFault(byte fault) {
        }

        default void onUpdateFlag(boolean flag) {
        }

        default void onVSPStateChanged(int state) {
        }

        default void onWirelessChargingFRStateChanged(int value) {
        }

        default void onWirelessChargingFRSwitchStateChanged(int value) {
        }

        default void onWirelessChargingPowerFLStateChanged(int value) {
        }

        default void onWirelessChargingPowerFRStateChanged(int value) {
        }

        default void onWirelessChargingStateChanged(int value) {
        }

        default void onWirelessChargingSwitchStateChanged(int value) {
        }

        default void showCarModelChange(int value) {
        }
    }

    void disconnectDevice(int deviceType);

    int get5LevelSeatHeatingState(int area);

    int get5LevelSeatVentilatingState(int area);

    int getAINavigationState();

    int getAcDefrostState(int area);

    int getAcRearPanelLockState();

    int getAdaptiveFrontLightFault();

    int getAiDistanceLightState();

    boolean getAndroidAutoConnectionStatus();

    int getAutoParkingState();

    boolean getAutoTBoxNetworkSupport();

    int getAutoType();

    int getBacklightLevels();

    int getBlindFault();

    int getBlindMonitorState();

    int getBlindMonitorType();

    String getCameraPackageName();

    boolean getCarPlayConnectionStatus();

    int getCarPlaySessionStatusStatus();

    int getChargingScheduleState();

    int getCntTrafficSignRecognize();

    int getCurrentOperationMode();

    int getDiSusMode();

    int getDoorLightState();

    int getDoorLockStatus(int area);

    int getDynamicSuspensionMode();

    int getEPBState();

    int getElecCarBodyStablySysState();

    int getEmergencyLaneAssistance32FState();

    int getEnergyFeedback();

    int getEnergyFeedbackSupport();

    int getHudState();

    int getISLIWarningLevelSwitchStatus();

    int getIndoorLightSwitchState();

    int getInstrumentBrightness();

    int getIntelligentSpeedLimitAlert2B4State();

    int getIntelligentSpeedLimitMapVersionDisplay();

    int getLaneAssistType();

    int getLeftStrutMorotRunningState();

    int getLightStatus(int type);

    int getLuggageDoorState();

    String getMicPackageName();

    void getMusicPlayState();

    int[] getPM2p5CheckState();

    int getPM2p5DualChannelDetectState();

    int[] getPM2p5Value();

    int getPowerLevel();

    int getReverseRadarSwitchState();

    int getRightStrutMorotRunningState();

    int getScreenAngle();

    boolean getScreenRotationOverHotState();

    int getSeatHeatingNotL1PState();

    int getSeatHeatingState(int area);

    int getSeatHeatingState2(int area);

    int getSeatVentilatingNotL1PState();

    int getSeatVentilatingState(int area);

    int getSeatVentilatingState2(int area);

    int getShowCarModelType();

    int getSteepDescentSlowDownState();

    int getSteeringWheelHeatingState();

    int getSupportDoubleConfirm();

    int getSupportEmergencyLaneKeepAssist();

    int getSupportLaneDepartureStop();

    int getSupportLaneDepartureWarning();

    int getSupportTSRFlow();

    int getTempRegulatingAccuracy();

    int getTrafficSignRecognize2B4State();

    int getTrafficSignRecognizeState();

    int getTransportMode();

    int getTsrFault();

    boolean getUpdateFlag();

    int getVSPState();

    int getWirelessChargingFRState();

    int getWirelessChargingFRSwitchState();

    int getWirelessChargingPowerFLState();

    int getWirelessChargingPowerFRState();

    int getWirelessChargingStatus();

    int getWirelessChargingSwitchState();

    int hasMessage(int id);

    void initAndroidAuto(boolean flagFrom);

    void initAvm();

    void initCarplay(boolean flagFrom);

    boolean isAaVrActive();

    boolean isAndroidAutoConnected();

    boolean isAndroidAutoFocus();

    boolean isAvmForeground();

    boolean isCarPlayConnected();

    boolean isCarPlayFocus();

    boolean isCarPlayPhoneCall();

    boolean isChargingListenerConnected();

    boolean isRMode();

    boolean isWirelessAndroidAutoConnected();

    boolean isWirelessCarPlayConnected();

    void setAINavigationState(int value);

    void setAcDefrostState(int state);

    void setAcRearPanelLockState(int value);

    void setAiDistanceLightState(int value);

    void setAutoParkingState(int value);

    void setBlindMonitorState(int value);

    void setCurrentOperationMode(int mode);

    void setDoorLightState(int value);

    void setDoorLockStatus(int areas, int state);

    void setDynamicSuspensionMode(int value);

    void setEPBState(int state);

    void setElecCarBodyStablySysState(int value);

    int setEmergencyLaneAssistanceState(int state);

    void setEnergyFeedback(int value);

    void setHudState(int value);

    void setISLIWarningLevelSwitchStatus(int state);

    void setInsideLightDoorState(int value);

    void setInstrumentBrightness(int value);

    void setLaneAssistType(int value);

    int setPadRotation(int orientation);

    void setPowerOffChargedState(int state);

    void setRearWindowLock(int state);

    void setReverseRadarSwitchState(int value);

    void setSteepDescentSlowDownState(int value);

    void setSteeringWheelHeatingState(int value);

    void setTrafficSignRecognizeState(int value);

    void setTransportMode(int mode);

    void setTransportModeSpringBack();

    int setVSPState(int state);

    void setWirelessChargingFRSwitchState(int value);

    void setWirelessChargingSwitchState(int value);

    void settingLuggage(int state);

    int syncHvacIcon2Qnx(int value);

    void voiceCtlBackDoor(int cmd);
}
