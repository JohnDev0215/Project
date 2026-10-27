package com.spotify.player.model.command.options;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PlayOperation {
    public static final /* synthetic */ PlayOperation[] c = {new PlayOperation("REPLACE", 0), new PlayOperation("ENQUEUE", 1), new PlayOperation("PUSH", 2)};

    /* JADX INFO: Fake field, exist only in values array */
    PlayOperation EF5;

    public static PlayOperation valueOf(String str) {
        return (PlayOperation) Enum.valueOf(PlayOperation.class, str);
    }

    public static PlayOperation[] values() {
        return (PlayOperation[]) c.clone();
    }
}
