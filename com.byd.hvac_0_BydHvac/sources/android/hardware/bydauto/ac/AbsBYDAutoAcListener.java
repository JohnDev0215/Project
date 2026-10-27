package android.hardware.bydauto.ac;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements d {
    public static final String TAG = "AbsBYDAutoAcListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void on3daOnlineStateChanged(int i) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onACAssistModeTips(int i) {
        Log.v(TAG, "onACAssistModeTips");
    }

    public void onACBTWindTips(int i) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int i) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onACKeepEngineRunTips(int i) {
        Log.v(TAG, "onACKeepEngineRunTips");
    }

    public void onACMaxEVModeTips(int i) {
        Log.v(TAG, "onACMaxEVModeTips");
    }

    public void onACPreheatTips(int i) {
        Log.v(TAG, "onACPreheatTips");
    }

    public void onACSystemStateChange(int i) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onACWorkCommandChanged(int i) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i);
    }

    public void onACWorkModeChanged(int i, int i2) {
        Log.v(TAG, "onACWorkModeChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcCockpitPurificationStatusChanged(int i) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i);
    }

    public void onAcCompressorManualSignChanged(int i) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int i) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcCtrlModeChanged(int i) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int i) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcDefrostOnlineStateChanged(int i) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onAcDefrostStateChanged(int i, int i2) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int i) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    public void onAcHeatingStateChanged(int i) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i);
    }

    public void onAcKeyActionStateChanged(int i) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int i) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcOnlineStateChanged(int i) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + i);
    }

    public void onAcPanelLockPromptStateChanged(int i) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    public void onAcPromptBoxShownStateChanged(int i) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcPtcPreheatSignalChanged(int i) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onAcRearPanelLockStateChanged(int i) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcRemoteCtrlTimeChanged(int i) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcTemperatureControlModeChanaged(int i) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    public void onAcTemperatureControlModeChanged(int i) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcTuyereSwitchStateChanged(int i, int i2) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i2);
    }

    public void onAcVentilationStateChanged(int i) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcWarmStateChanged(int i) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int i) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWindLevelChanged(int i) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onAcWindLevelManualSignChanged(int i) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindModeChanged(int i) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onAcWindModeManualSignChanged(int i) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    public void onAirOutleOnlineStateChanged(int i) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletStateChanged(int i, int i2) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onAirOutletValueChanged(int i, float f) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onAutoCleanAirStateChanged(int i) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onAvoidBlowPeopleStateChanged(int i, int i2) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onBlowPeopleState(int i, int i2) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onFreeWindBTNStateChanged(int i, int i2) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int i) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onHeatPumpCompressorWorkModeChanged(int i) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int i) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onHighTempAntivirusStateChanged(int i) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempSterilizeTips(int i) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onIntelligentControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onMaxAcStateChanged(int i) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int i) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onQuickCleanAirStateChanged(int i) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int i) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void onRearAcCtrlModeChanged(int i) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onRearAcKeyLockStateChanged(int i) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onRearAcLockStateChanged(int i) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onRearAcMaxWindLevelChanged(int i) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int i) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onRearAcWindModeChanged(int i) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onSweepBlowStateChanged(int i, int i2) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onTemperatureChanged(int i, int i2) {
        Log.v(TAG, "onTemperatureChanged area:" + i + " value:" + i2);
    }

    public void onTemperatureUnitChanged(int i) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onVoiceCmdResultChanged(int i) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }
}
