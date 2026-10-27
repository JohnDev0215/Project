package com.stingray.companion.model;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/companion/model/PlayerState;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "PLAYING", "PAUSED", "ERROR", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlayerState {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlayerState[] $VALUES;
    public static final PlayerState IDLE = new PlayerState("IDLE", 0);
    public static final PlayerState PLAYING = new PlayerState("PLAYING", 1);
    public static final PlayerState PAUSED = new PlayerState("PAUSED", 2);
    public static final PlayerState ERROR = new PlayerState("ERROR", 3);

    private static final /* synthetic */ PlayerState[] $values() {
        return new PlayerState[]{IDLE, PLAYING, PAUSED, ERROR};
    }

    static {
        PlayerState[] playerStateArr$values = $values();
        $VALUES = playerStateArr$values;
        $ENTRIES = r.e(playerStateArr$values);
    }

    private PlayerState(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlayerState valueOf(String str) {
        return (PlayerState) Enum.valueOf(PlayerState.class, str);
    }

    public static PlayerState[] values() {
        return (PlayerState[]) $VALUES.clone();
    }
}
