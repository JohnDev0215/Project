package com.spotify.player.model.command.options;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PlayTrigger {
    public static final /* synthetic */ PlayTrigger[] c = {new PlayTrigger("IMMEDIATELY", 0), new PlayTrigger("ADVANCED_PAST_TRACK", 1), new PlayTrigger("ADVANCED_PAST_CONTEXT", 2)};

    /* JADX INFO: Fake field, exist only in values array */
    PlayTrigger EF5;

    public static PlayTrigger valueOf(String str) {
        return (PlayTrigger) Enum.valueOf(PlayTrigger.class, str);
    }

    public static PlayTrigger[] values() {
        return (PlayTrigger[]) c.clone();
    }
}
