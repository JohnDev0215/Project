package android.hardware.bydauto.ac;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoAcListener";

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcOnlineStateChanged(int state) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + state);
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcCtrlModeChanged(int mode) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onRearAcCtrlModeChanged(int mode) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int mode) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int state) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWarmStateChanged(int state) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcVentilationStateChanged(int state) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcTemperatureControlModeChanged(int mode) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcDefrostStateChanged(int area, int state) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int state) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcCompressorManualSignChanged(int sign) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int mode) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcWindModeManualSignChanged(int sign) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    public void onAcWindModeChanged(int mode) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onRearAcWindModeChanged(int mode) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onAcWindLevelManualSignChanged(int sign) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindLevelChanged(int level) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int level) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onTemperatureUnitChanged(int unit) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onTemperatureChanged(int area, int value) {
        Log.v(TAG, "onTemperatureChanged area:" + area + " value:" + value);
    }

    public void onAcPromptBoxShownStateChanged(int state) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int state) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int state) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int state) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcKeyActionStateChanged(int state) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onRearAcLockStateChanged(int state) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onVoiceCmdResultChanged(int result) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onRearAcMaxWindLevelChanged(int level) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onAcRemoteCtrlTimeChanged(int value) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcPtcPreheatSignalChanged(int value) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcDefrostOnlineStateChanged(int state) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int value) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onAcRearPanelLockStateChanged(int state) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int state) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onQuickCleanAirStateChanged(int state) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int tip) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void on3daOnlineStateChanged(int state) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onAutoCleanAirStateChanged(int state) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onHighTempAntivirusStateChanged(int state) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int time) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onAcPanelLockPromptStateChanged(int state) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    public void onRearAcKeyLockStateChanged(int state) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onAcTemperatureControlModeChanaged(int mode) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int state) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onMaxAcStateChanged(int state) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onACSystemStateChange(int state) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onHighTempSterilizeTips(int value) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onACAssistModeTips(int state) {
        Log.v(TAG, "onACAssistModeTips");
    }

    public void onACKeepEngineRunTips(int value) {
        Log.v(TAG, "onACKeepEngineRunTips");
    }

    public void onACMaxEVModeTips(int value) {
        Log.v(TAG, "onACMaxEVModeTips");
    }

    public void onACPreheatTips(int value) {
        Log.v(TAG, "onACPreheatTips");
    }

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onACBTWindTips(int state) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int state) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onHeatPumpCompressorWorkModeChanged(int mode) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onAirOutleOnlineStateChanged(int state) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletValueChanged(int area, float value) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onFreeWindBTNStateChanged(int area, int state) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onBlowPeopleState(int area, int state) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public void onAvoidBlowPeopleStateChanged(int area, int state) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onSweepBlowStateChanged(int area, int state) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onAirOutletStateChanged(int area, int state) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onIntelligentControlStateChanged(int state) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int state) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onACWorkModeChanged(int area, int mode) {
        Log.v(TAG, "onACWorkModeChanged");
    }

    public void onAcHeatingStateChanged(int state) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + state);
    }

    public void onAcCockpitPurificationStatusChanged(int state) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + state);
    }

    public void onAcTuyereSwitchStateChanged(int area, int state) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + state);
    }

    public void onACWorkCommandChanged(int state) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + state);
    }
}
