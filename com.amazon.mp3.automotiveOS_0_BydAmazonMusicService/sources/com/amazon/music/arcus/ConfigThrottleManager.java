package com.amazon.music.arcus;

import com.amazon.music.arcus.ConfigRefreshGate;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
class ConfigThrottleManager implements ConfigRefreshGate {
    private final AtomicBoolean throttled = new AtomicBoolean(false);

    ConfigThrottleManager() {
    }

    public boolean isThrottled() {
        return this.throttled.get();
    }

    public void setIsThrottled(boolean z) {
        this.throttled.set(z);
    }

    @Override // com.amazon.music.arcus.ConfigRefreshGate
    public ConfigRefreshGate.ConfigRefreshGateState getGateState() {
        return isThrottled() ? ConfigRefreshGate.ConfigRefreshGateState.REFRESH_BLOCKED : ConfigRefreshGate.ConfigRefreshGateState.REFRESH_ALLOWED;
    }
}
