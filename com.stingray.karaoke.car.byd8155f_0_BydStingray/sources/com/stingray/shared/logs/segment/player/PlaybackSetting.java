package com.stingray.shared.logs.segment.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/shared/logs/segment/player/PlaybackSetting;", "", "<init>", "(Ljava/lang/String;I)V", "VIDEO_PLAYER_BACKGROUND", "FULL_SCREEN", "REPEAT_MODE", "LEAD_VOCALS", "AR_FILTER", "CAST_PLATFORM", "GAMING_MODE", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlaybackSetting {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlaybackSetting[] $VALUES;
    public static final PlaybackSetting VIDEO_PLAYER_BACKGROUND = new PlaybackSetting("VIDEO_PLAYER_BACKGROUND", 0);
    public static final PlaybackSetting FULL_SCREEN = new PlaybackSetting("FULL_SCREEN", 1);
    public static final PlaybackSetting REPEAT_MODE = new PlaybackSetting("REPEAT_MODE", 2);
    public static final PlaybackSetting LEAD_VOCALS = new PlaybackSetting("LEAD_VOCALS", 3);
    public static final PlaybackSetting AR_FILTER = new PlaybackSetting("AR_FILTER", 4);
    public static final PlaybackSetting CAST_PLATFORM = new PlaybackSetting("CAST_PLATFORM", 5);
    public static final PlaybackSetting GAMING_MODE = new PlaybackSetting("GAMING_MODE", 6);

    private static final /* synthetic */ PlaybackSetting[] $values() {
        return new PlaybackSetting[]{VIDEO_PLAYER_BACKGROUND, FULL_SCREEN, REPEAT_MODE, LEAD_VOCALS, AR_FILTER, CAST_PLATFORM, GAMING_MODE};
    }

    static {
        PlaybackSetting[] playbackSettingArr$values = $values();
        $VALUES = playbackSettingArr$values;
        $ENTRIES = r.e(playbackSettingArr$values);
    }

    private PlaybackSetting(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlaybackSetting valueOf(String str) {
        return (PlaybackSetting) Enum.valueOf(PlaybackSetting.class, str);
    }

    public static PlaybackSetting[] values() {
        return (PlaybackSetting[]) $VALUES.clone();
    }
}
