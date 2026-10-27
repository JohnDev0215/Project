package org.chromium.mojom.device;

import org.chromium.mojo.bindings.Callbacks;
import org.chromium.mojo.bindings.Interface;

/* JADX INFO: loaded from: classes3.dex */
public interface BatteryMonitor extends Interface {
    public static final Interface.Manager<BatteryMonitor, Proxy> MANAGER = BatteryMonitor_Internal.MANAGER;

    public interface Proxy extends BatteryMonitor, Interface.Proxy {
    }

    public interface QueryNextStatusResponse extends Callbacks.Callback1<BatteryStatus> {
    }

    void queryNextStatus(QueryNextStatusResponse queryNextStatusResponse);
}
