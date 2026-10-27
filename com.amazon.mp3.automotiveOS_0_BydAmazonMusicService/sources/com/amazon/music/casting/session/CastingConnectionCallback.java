package com.amazon.music.casting.session;

import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public interface CastingConnectionCallback {
    void onConnected(CastingDevice castingDevice);

    void onDisconnected(TerminationReason terminationReason);

    void onError(CastingException castingException);
}
