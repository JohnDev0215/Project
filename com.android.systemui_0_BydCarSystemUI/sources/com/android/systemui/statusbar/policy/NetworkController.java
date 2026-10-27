package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.statusbar.policy.TBoxSignalController;

/* JADX INFO: loaded from: classes.dex */
public interface NetworkController extends CallbackController<SignalCallback> {

    public interface SignalCallback {
        default void setMobileDataEnabled(boolean enabled) {
        }

        default void setTBoxIndicators(TBoxSignalController.TBoxSignalState state) {
        }

        default void setTBoxNoneSignal(TBoxSignalController.TBoxSignalState state) {
        }

        default void setWifiIndicators(SignalController.State state, IconState statusIcon, String description, boolean isTransient, String statusLabel) {
        }

        default void showTelephoneData(boolean show) {
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    void addCallback(SignalCallback cb);

    void getState();

    SignalController.State getTBoxState();

    String getWifiConnectedName();

    boolean isDataEnabled();

    boolean isDataEnabledFormSetting();

    boolean isOnline();

    boolean isShowTelephoneData();

    boolean isWifiConnected();

    boolean isWifiEnabled();

    @Override // com.android.systemui.statusbar.policy.CallbackController
    void removeCallback(SignalCallback cb);

    void setMobileDataEnabled(boolean enabled);

    void setWifiEnabled(boolean enabled);

    public static class IconState {
        public final int icon;
        public final boolean visible;

        public IconState(boolean visible, int icon) {
            this.visible = visible;
            this.icon = icon;
        }

        public IconState(boolean visible, int icon, Context context) {
            this(visible, icon);
        }
    }
}
