package com.stingray.karaoke.car.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/karaoke/car/player/PlayerActionType;", "", "<init>", "(Ljava/lang/String;I)V", "Play", "Pause", "Stop", "Seek", "SkipNext", "SkipPrevious", "ToggleLeadVocal", "Replay", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlayerActionType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlayerActionType[] $VALUES;
    public static final PlayerActionType Play = new PlayerActionType("Play", 0);
    public static final PlayerActionType Pause = new PlayerActionType("Pause", 1);
    public static final PlayerActionType Stop = new PlayerActionType("Stop", 2);
    public static final PlayerActionType Seek = new PlayerActionType("Seek", 3);
    public static final PlayerActionType SkipNext = new PlayerActionType("SkipNext", 4);
    public static final PlayerActionType SkipPrevious = new PlayerActionType("SkipPrevious", 5);
    public static final PlayerActionType ToggleLeadVocal = new PlayerActionType("ToggleLeadVocal", 6);
    public static final PlayerActionType Replay = new PlayerActionType("Replay", 7);

    private static final /* synthetic */ PlayerActionType[] $values() {
        return new PlayerActionType[]{Play, Pause, Stop, Seek, SkipNext, SkipPrevious, ToggleLeadVocal, Replay};
    }

    static {
        PlayerActionType[] playerActionTypeArr$values = $values();
        $VALUES = playerActionTypeArr$values;
        $ENTRIES = r.e(playerActionTypeArr$values);
    }

    private PlayerActionType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlayerActionType valueOf(String str) {
        return (PlayerActionType) Enum.valueOf(PlayerActionType.class, str);
    }

    public static PlayerActionType[] values() {
        return (PlayerActionType[]) $VALUES.clone();
    }
}
