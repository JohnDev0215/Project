package com.stingray.companion.model;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/companion/model/CompanionAppMode;", "", "<init>", "(Ljava/lang/String;I)V", "FULL", "SONG_QUEUE_MANAGER", "SONG_QUEUE_ONLY", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CompanionAppMode {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ CompanionAppMode[] $VALUES;
    public static final CompanionAppMode FULL = new CompanionAppMode("FULL", 0);
    public static final CompanionAppMode SONG_QUEUE_MANAGER = new CompanionAppMode("SONG_QUEUE_MANAGER", 1);
    public static final CompanionAppMode SONG_QUEUE_ONLY = new CompanionAppMode("SONG_QUEUE_ONLY", 2);

    private static final /* synthetic */ CompanionAppMode[] $values() {
        return new CompanionAppMode[]{FULL, SONG_QUEUE_MANAGER, SONG_QUEUE_ONLY};
    }

    static {
        CompanionAppMode[] companionAppModeArr$values = $values();
        $VALUES = companionAppModeArr$values;
        $ENTRIES = r.e(companionAppModeArr$values);
    }

    private CompanionAppMode(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static CompanionAppMode valueOf(String str) {
        return (CompanionAppMode) Enum.valueOf(CompanionAppMode.class, str);
    }

    public static CompanionAppMode[] values() {
        return (CompanionAppMode[]) $VALUES.clone();
    }
}
