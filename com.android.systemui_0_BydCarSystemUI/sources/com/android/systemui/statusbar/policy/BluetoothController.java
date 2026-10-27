package com.android.systemui.statusbar.policy;

import android.bluetooth.BluetoothDevice;
import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface BluetoothController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onCallStateChanged(int state) {
        }

        default void onConnectedChanged(boolean isConnected, String address) {
        }

        default void onEnableChanged(boolean enabled) {
        }

        default void onServiceConnectionChanged(boolean isConnected) {
        }
    }

    BluetoothDevice getConnectedDevice();

    boolean isConnected();

    boolean isEnable();

    boolean isMediaEnable();

    boolean isPanEnable();

    boolean isPhoneEnable();

    boolean onCalling();

    void setEnable(boolean enable);
}
