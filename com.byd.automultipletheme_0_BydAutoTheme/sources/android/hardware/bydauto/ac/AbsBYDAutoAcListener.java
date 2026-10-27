package android.hardware.bydauto.ac;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoAcListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcOnlineStateChanged(int i) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + i);
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcCtrlModeChanged(int i) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onRearAcCtrlModeChanged(int i) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int i) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int i) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWarmStateChanged(int i) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcVentilationStateChanged(int i) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcTemperatureControlModeChanged(int i) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcDefrostStateChanged(int i, int i2) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int i) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcCompressorManualSignChanged(int i) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int i) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcWindModeManualSignChanged(int i) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    public void onAcWindModeChanged(int i) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onRearAcWindModeChanged(int i) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onAcWindLevelManualSignChanged(int i) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindLevelChanged(int i) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int i) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onTemperatureUnitChanged(int i) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onTemperatureChanged(int i, int i2) {
        Log.v(TAG, "onTemperatureChanged");
    }

    public void onAcPromptBoxShownStateChanged(int i) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int i) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcKeyActionStateChanged(int i) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onRearAcLockStateChanged(int i) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onVoiceCmdResultChanged(int i) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }

    public void onFeatureChanged(String str, int i) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onRearAcMaxWindLevelChanged(int i) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onAcRemoteCtrlTimeChanged(int i) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcPtcPreheatSignalChanged(int i) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcDefrostOnlineStateChanged(int i) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int i) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onAcRearPanelLockStateChanged(int i) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onQuickCleanAirStateChanged(int i) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int i) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void on3daOnlineStateChanged(int i) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onAutoCleanAirStateChanged(int i) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onHighTempAntivirusStateChanged(int i) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int i) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onAcPanelLockPromptStateChanged(int i) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    public void onRearAcKeyLockStateChanged(int i) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onAcTemperatureControlModeChanaged(int i) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onMaxAcStateChanged(int i) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onACSystemStateChange(int i) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onHighTempSterilizeTips(int i) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onACAssistModeTips(int i) {
        Log.v(TAG, "onACAssistModeTips");
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

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onACBTWindTips(int i) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int i) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onHeatPumpCompressorWorkModeChanged(int i) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onAirOutleOnlineStateChanged(int i) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletValueChanged(int i, float f) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onFreeWindBTNStateChanged(int i, int i2) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onBlowPeopleState(int i, int i2) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public void onAvoidBlowPeopleStateChanged(int i, int i2) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onSweepBlowStateChanged(int i, int i2) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onAirOutletStateChanged(int i, int i2) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onIntelligentControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int i) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onACWorkModeChanged(int i, int i2) {
        Log.v(TAG, "onACWorkModeChanged");
    }
}
