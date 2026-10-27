package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface UsbController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onSdCardStateChanged(boolean isMounted) {
        }

        default void onUsbStateChanged(boolean isConnected) {
        }
    }

    void getState();

    boolean isSdCardMounted();

    boolean isUSBConnected();
}
