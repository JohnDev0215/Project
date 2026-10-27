package android.hardware.bydauto.ac;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements d {
    public static final String TAG = "AbsBYDAutoAcListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void on3daOnlineStateChanged(int i3) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onACAssistModeTips(int i3) {
        Log.v(TAG, "onACAssistModeTips");
    }

    public void onACBTWindTips(int i3) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int i3) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onACKeepEngineRunTips(int i3) {
        Log.v(TAG, "onACKeepEngineRunTips");
    }

    public void onACMaxEVModeTips(int i3) {
        Log.v(TAG, "onACMaxEVModeTips");
    }

    public void onACPreheatTips(int i3) {
        Log.v(TAG, "onACPreheatTips");
    }

    public void onACSystemStateChange(int i3) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onACWorkModeChanged(int i3, int i4) {
        Log.v(TAG, "onACWorkModeChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i3) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcCompressorManualSignChanged(int i3) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int i3) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcCtrlModeChanged(int i3) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int i3) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcDefrostOnlineStateChanged(int i3) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onAcDefrostStateChanged(int i3, int i4) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int i3) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    public void onAcHeatingStateChanged(int i3) {
        Log.v(TAG, "onAcHeatingStateChanged state:" + i3);
    }

    public void onAcKeyActionStateChanged(int i3) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int i3) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcOnlineStateChanged(int i3) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + i3);
    }

    public void onAcPanelLockPromptStateChanged(int i3) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    public void onAcPromptBoxShownStateChanged(int i3) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcPtcPreheatSignalChanged(int i3) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i3) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onAcRearPanelLockStateChanged(int i3) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcRemoteCtrlTimeChanged(int i3) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcTemperatureControlModeChanaged(int i3) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    public void onAcTemperatureControlModeChanged(int i3) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcVentilationStateChanged(int i3) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcWarmStateChanged(int i3) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int i3) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWindLevelChanged(int i3) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onAcWindLevelManualSignChanged(int i3) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindModeChanged(int i3) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onAcWindModeManualSignChanged(int i3) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i3) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    public void onAirOutleOnlineStateChanged(int i3) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletStateChanged(int i3, int i4) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onAirOutletValueChanged(int i3, float f3) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onAutoCleanAirStateChanged(int i3) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onAvoidBlowPeopleStateChanged(int i3, int i4) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onBlowPeopleState(int i3, int i4) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i3) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i3) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onFreeWindBTNStateChanged(int i3, int i4) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int i3) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onHeatPumpCompressorWorkModeChanged(int i3) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int i3) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onHighTempAntivirusStateChanged(int i3) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempSterilizeTips(int i3) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onIntelligentControlStateChanged(int i3) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onMaxAcStateChanged(int i3) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int i3) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onQuickCleanAirStateChanged(int i3) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int i3) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void onRearAcCtrlModeChanged(int i3) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onRearAcKeyLockStateChanged(int i3) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onRearAcLockStateChanged(int i3) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onRearAcMaxWindLevelChanged(int i3) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int i3) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onRearAcWindModeChanged(int i3) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onSweepBlowStateChanged(int i3, int i4) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onTemperatureChanged(int i3, int i4) {
        Log.v(TAG, "onTemperatureChanged area:" + i3 + " value:" + i4);
    }

    public void onTemperatureUnitChanged(int i3) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onVoiceCmdResultChanged(int i3) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }
}
