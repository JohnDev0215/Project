package org.chromium.mojom.device;

import org.chromium.mojo.bindings.Callbacks;
import org.chromium.mojo.bindings.Interface;

/* JADX INFO: loaded from: classes3.dex */
public interface VibrationManager extends Interface {
    public static final Interface.Manager<VibrationManager, Proxy> MANAGER = VibrationManager_Internal.MANAGER;

    public interface CancelResponse extends Callbacks.Callback0 {
    }

    public interface Proxy extends VibrationManager, Interface.Proxy {
    }

    public interface VibrateResponse extends Callbacks.Callback0 {
    }

    void cancel(CancelResponse cancelResponse);

    void vibrate(long j, VibrateResponse vibrateResponse);
}
