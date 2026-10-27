package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface BrightnessController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onBrightnessValueChanged(int value, int max, int min) {
        }

        default void onScreenBackLightEnableChanged(int id, boolean enable) {
        }

        default void onToggleModeChanged(boolean mode) {
        }
    }

    int getDefaultBacklight();

    int getMaximumBackLight();

    int getMinimumBackLight();

    void getToggleMode();

    void getValue();

    void setToggleMode(boolean toggleMode);

    void setValue(int brightness, boolean tracking, boolean stopTracking);
}
