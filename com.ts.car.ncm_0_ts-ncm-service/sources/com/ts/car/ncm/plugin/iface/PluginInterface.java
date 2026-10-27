package com.ts.car.ncm.plugin.iface;

import com.ts.car.core.annotation.FoundationInterface;

/* JADX INFO: loaded from: classes.dex */
@FoundationInterface
public interface PluginInterface {
    void registerPluginListener(PluginListener pluginListener);

    void unregisterListener(PluginListener pluginListener);
}
