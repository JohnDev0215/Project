package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface HvacController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onAcTemperatureControlModeChanged(int mode) {
        }

        default void onAcVentilationStateChanged(int state) {
        }

        default void onCycleModeChanged(int mode) {
        }

        default void onFaultNumShownStateChanged(int state) {
        }

        default void onFrontDefrostChanged(boolean enabled) {
        }

        default void onFrontLeftTempChanged(int value) {
        }

        default void onFrontRightTempChanged(int value) {
        }

        default void onHasMessageChanged(int id, int state) {
        }

        default void onHvacPower(boolean enabled) {
        }

        default void onMaxCoolingStateChanged(int state) {
        }

        default void onMessage5sOnlineStateChanged(int id, int state) {
        }

        default void onPowerLevelChanged(int level) {
        }

        default void onRearDefrostChanged(boolean enabled) {
        }

        default void onRefreshHvac() {
        }

        default void onTemperatureUnitChanged(int value) {
        }

        default void onWindLevelChanged(int level) {
        }

        default void onWindModeChanged(int mode) {
        }
    }

    int getAcCycleMode();

    int getAcFaultNumShownState();

    int getAcHeatingButtonConfig();

    int getAcMaxCoolingState();

    int getAcTemperatureControlMode();

    int getAcType();

    int getAcVentilationState();

    int getAcWindLevel();

    int getAcWindMode();

    int getAcWindModeNum();

    int getFrontLeftTempValue();

    int getFrontRightTempValue();

    int getPowerLevel();

    int getTempRegulatingAccuracy();

    int getUnit();

    boolean isFrontDefrostEnabled();

    boolean isHvacPower();

    void refreshHvac();

    int setAcCycleMode(int value);

    int setAcTemperatureControlMode(int mode);

    int setAcVentilationState(int state);

    int setAcWindLevel(int level);

    int setFrontDefrostEnabled(boolean enabled);

    int setFrontLeftTempValue(int value, int unit);

    int setFrontRightTempValue(int value, int unit);

    int setHvacPower(boolean enabled);
}
