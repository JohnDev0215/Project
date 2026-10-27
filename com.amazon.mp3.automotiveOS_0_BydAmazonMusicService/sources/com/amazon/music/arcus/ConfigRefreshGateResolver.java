package com.amazon.music.arcus;

import com.amazon.music.arcus.ConfigRefreshGate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class ConfigRefreshGateResolver {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigRefreshGateResolver.class.getSimpleName());
    private AtomicBoolean currentlyRefreshing = new AtomicBoolean(false);
    private final Set<ConfigRefreshGate> refreshGates;

    public ConfigRefreshGateResolver(Set<ConfigRefreshGate> set) {
        this.refreshGates = (Set) Validate.notEmpty((Collection) Validate.notNull(set));
    }

    public boolean shouldConfigBeRefreshed() {
        if (isCurrentlyRefreshing()) {
            return false;
        }
        Iterator<ConfigRefreshGate> it = this.refreshGates.iterator();
        boolean zEquals = false;
        while (it.hasNext()) {
            ConfigRefreshGate.ConfigRefreshGateState gateState = it.next().getGateState();
            if (ConfigRefreshGate.ConfigRefreshGateState.REFRESH_BLOCKED.equals(gateState)) {
                return false;
            }
            zEquals |= ConfigRefreshGate.ConfigRefreshGateState.REFRESH_NEEDED.equals(gateState);
        }
        return zEquals;
    }

    public void setCurrentlyRefreshing(boolean z) {
        this.currentlyRefreshing.set(z);
    }

    public boolean isCurrentlyRefreshing() {
        return this.currentlyRefreshing.get();
    }
}
