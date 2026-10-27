package com.spotify.player.model.command.options;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RestorePaused {
    public static final /* synthetic */ RestorePaused[] c = {new RestorePaused("RESTORE", 0), new RestorePaused("PAUSE", 1), new RestorePaused("RESUME", 2)};

    /* JADX INFO: Fake field, exist only in values array */
    RestorePaused EF5;

    public static RestorePaused valueOf(String str) {
        return (RestorePaused) Enum.valueOf(RestorePaused.class, str);
    }

    public static RestorePaused[] values() {
        return (RestorePaused[]) c.clone();
    }
}
