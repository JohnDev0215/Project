package com.amazon.scrublib.models;

import com.amazon.scrublib.utils.AtomicInt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/scrublib/models/AtomicPlayerState;", "", "initialEnum", "Lcom/amazon/scrublib/models/PlayerState;", "(Lcom/amazon/scrublib/models/PlayerState;)V", "_enumType", "Lcom/amazon/scrublib/utils/AtomicInt;", "get", "set", "", "input", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AtomicPlayerState {
    private final AtomicInt _enumType;

    public AtomicPlayerState(PlayerState initialEnum) {
        Intrinsics.checkNotNullParameter(initialEnum, "initialEnum");
        this._enumType = new AtomicInt(initialEnum.getId());
    }

    public final PlayerState get() {
        return PlayerState.INSTANCE.fromInt(this._enumType.get());
    }

    public final void set(PlayerState input) {
        Intrinsics.checkNotNullParameter(input, "input");
        this._enumType.set(input.getId());
    }
}
