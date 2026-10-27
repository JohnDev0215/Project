package android.hardware.bydauto.ac;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoAcListener";

    public void onAcRearStarted() {
    }

    public void onAcRearStoped() {
    }

    public void onAcStarted() {
    }

    public void onAcStoped() {
    }

    public void onElectricTuyereControlSysStateChange() {
    }

    public void on3daOnlineStateChanged(int i) {
    }

    public void onACAssistModeTips(int i) {
    }

    public void onACBTWindTips(int i) {
    }

    public void onACDefrostRearPanelNotAvailableTips(int i) {
    }

    public void onACKeepEngineRunTips(int i) {
    }

    public void onACMaxEVModeTips(int i) {
    }

    public void onACPreheatTips(int i) {
    }

    public void onACSystemStateChange(int i) {
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i) {
    }

    public void onAcCompressorManualSignChanged(int i) {
    }

    public void onAcCompressorModeChanged(int i) {
    }

    public void onAcCtrlModeChanged(int i) {
    }

    public void onAcCycleModeChanged(int i) {
    }

    public void onAcDefrostOnlineStateChanged(int i) {
    }

    public void onAcFaultNumShownStateChanged(int i) {
    }

    public void onAcHeatingStateChanged(int i) {
    }

    public void onAcKeyActionStateChanged(int i) {
    }

    public void onAcMaxCoolingStateChanged(int i) {
    }

    public void onAcOnlineStateChanged(int i) {
    }

    public void onAcPanelLockPromptStateChanged(int i) {
    }

    public void onAcPromptBoxShownStateChanged(int i) {
    }

    public void onAcPtcPreheatSignalChanged(int i) {
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i) {
    }

    public void onAcRearPanelLockStateChanged(int i) {
    }

    public void onAcRemoteCtrlTimeChanged(int i) {
    }

    public void onAcTemperatureControlModeChanaged(int i) {
    }

    public void onAcTemperatureControlModeChanged(int i) {
    }

    public void onAcVentilationStateChanged(int i) {
    }

    public void onAcWarmStateChanged(int i) {
    }

    public void onAcWarmTypeOnlineStateChanged(int i) {
    }

    public void onAcWindLevelChanged(int i) {
    }

    public void onAcWindLevelManualSignChanged(int i) {
    }

    public void onAcWindModeChanged(int i) {
    }

    public void onAcWindModeManualSignChanged(int i) {
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i) {
    }

    public void onAirOutleOnlineStateChanged(int i) {
    }

    public void onAutoCleanAirStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i) {
    }

    public void onFrontRowAirOutletStateChanged(int i) {
    }

    public void onHeatPumpCompressorWorkModeChanged(int i) {
    }

    public void onHighTempAntivirusCountDownChanged(int i) {
    }

    public void onHighTempAntivirusStateChanged(int i) {
    }

    public void onHighTempSterilizeTips(int i) {
    }

    public void onIntelligentControlStateChanged(int i) {
    }

    public void onMaxAcStateChanged(int i) {
    }

    public void onOtaSubBatteryTemperatureChanged(int i) {
    }

    public void onQuickCleanAirStateChanged(int i) {
    }

    public void onQuickCleanTipChanged(int i) {
    }

    public void onRearAcCtrlModeChanged(int i) {
    }

    public void onRearAcKeyLockStateChanged(int i) {
    }

    public void onRearAcLockStateChanged(int i) {
    }

    public void onRearAcMaxWindLevelChanged(int i) {
    }

    public void onRearAcWindLevelChanged(int i) {
    }

    public void onRearAcWindModeChanged(int i) {
    }

    public void onTemperatureUnitChanged(int i) {
    }

    public void onVoiceCmdResultChanged(int i) {
    }

    public void onACWorkModeChanged(int i, int i2) {
    }

    public void onAcDefrostStateChanged(int i, int i2) {
    }

    public void onAirOutletStateChanged(int i, int i2) {
    }

    public void onAirOutletValueChanged(int i, float f) {
    }

    public void onAvoidBlowPeopleStateChanged(int i, int i2) {
    }

    public void onBlowPeopleState(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }

    public void onFreeWindBTNStateChanged(int i, int i2) {
    }

    public void onSweepBlowStateChanged(int i, int i2) {
    }

    public void onTemperatureChanged(int i, int i2) {
    }
}
