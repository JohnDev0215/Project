package android.hardware.bydauto.ac;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

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

    public void on3daOnlineStateChanged(int i2) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onACAssistModeTips(int i2) {
        Log.v(TAG, "onACAssistModeTips");
    }

    public void onACBTWindTips(int i2) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int i2) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onACKeepEngineRunTips(int i2) {
        Log.v(TAG, "onACKeepEngineRunTips");
    }

    public void onACMaxEVModeTips(int i2) {
        Log.v(TAG, "onACMaxEVModeTips");
    }

    public void onACPreheatTips(int i2) {
        Log.v(TAG, "onACPreheatTips");
    }

    public void onACSystemStateChange(int i2) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onACWorkCommandChanged(int i2) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i2);
    }

    public void onACWorkModeChanged(int i2, int i3) {
        Log.v(TAG, "onACWorkModeChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i2) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcCockpitPurificationStatusChanged(int i2) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i2);
    }

    public void onAcCompressorManualSignChanged(int i2) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int i2) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcCtrlModeChanged(int i2) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int i2) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcDefrostOnlineStateChanged(int i2) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onAcDefrostStateChanged(int i2, int i3) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int i2) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    public void onAcHeatingStateChanged(int i2) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i2);
    }

    public void onAcKeyActionStateChanged(int i2) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int i2) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcOnlineStateChanged(int i2) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + i2);
    }

    public void onAcPanelLockPromptStateChanged(int i2) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    public void onAcPromptBoxShownStateChanged(int i2) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcPtcPreheatSignalChanged(int i2) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i2) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onAcRearPanelLockStateChanged(int i2) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcRemoteCtrlTimeChanged(int i2) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcTemperatureControlModeChanaged(int i2) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    public void onAcTemperatureControlModeChanged(int i2) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcTuyereSwitchStateChanged(int i2, int i3) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i3);
    }

    public void onAcVentilationStateChanged(int i2) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcWarmStateChanged(int i2) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int i2) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWindLevelChanged(int i2) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onAcWindLevelManualSignChanged(int i2) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindModeChanged(int i2) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onAcWindModeManualSignChanged(int i2) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i2) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    public void onAirOutleOnlineStateChanged(int i2) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletStateChanged(int i2, int i3) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onAirOutletValueChanged(int i2, float f2) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onAutoCleanAirStateChanged(int i2) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onAvoidBlowPeopleStateChanged(int i2, int i3) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onBlowPeopleState(int i2, int i3) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i2) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i2) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onFreeWindBTNStateChanged(int i2, int i3) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int i2) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onHeatPumpCompressorWorkModeChanged(int i2) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int i2) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onHighTempAntivirusStateChanged(int i2) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempSterilizeTips(int i2) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onIntelligentControlStateChanged(int i2) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onMaxAcStateChanged(int i2) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int i2) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onQuickCleanAirStateChanged(int i2) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int i2) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void onRearAcCtrlModeChanged(int i2) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onRearAcKeyLockStateChanged(int i2) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onRearAcLockStateChanged(int i2) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onRearAcMaxWindLevelChanged(int i2) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int i2) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onRearAcWindModeChanged(int i2) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onSweepBlowStateChanged(int i2, int i3) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onTemperatureChanged(int i2, int i3) {
        Log.v(TAG, "onTemperatureChanged area:" + i2 + " value:" + i3);
    }

    public void onTemperatureUnitChanged(int i2) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onVoiceCmdResultChanged(int i2) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }
}
