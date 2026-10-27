package com.spotify.player.legacyplayer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PlayOptions$Trigger {
    public static final PlayOptions$Trigger c;
    public static final PlayOptions$Trigger e;
    public static final PlayOptions$Trigger k;
    public static final /* synthetic */ PlayOptions$Trigger[] l;

    static {
        PlayOptions$Trigger playOptions$Trigger = new PlayOptions$Trigger("IMMEDIATELY", 0);
        c = playOptions$Trigger;
        PlayOptions$Trigger playOptions$Trigger2 = new PlayOptions$Trigger("ADVANCE_PAST_TRACK", 1);
        e = playOptions$Trigger2;
        PlayOptions$Trigger playOptions$Trigger3 = new PlayOptions$Trigger("ADVANCE_PAST_CONTEXT", 2);
        k = playOptions$Trigger3;
        l = new PlayOptions$Trigger[]{playOptions$Trigger, playOptions$Trigger2, playOptions$Trigger3};
    }

    public static PlayOptions$Trigger valueOf(String str) {
        return (PlayOptions$Trigger) Enum.valueOf(PlayOptions$Trigger.class, str);
    }

    public static PlayOptions$Trigger[] values() {
        return (PlayOptions$Trigger[]) l.clone();
    }
}
