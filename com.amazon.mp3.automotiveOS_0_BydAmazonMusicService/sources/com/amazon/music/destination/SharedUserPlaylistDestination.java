package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class SharedUserPlaylistDestination extends Destination {
    private final Action action;
    private final String playlistId;

    public SharedUserPlaylistDestination(String str, Action action, String str2, String str3) {
        super(str2, str3);
        this.playlistId = str;
        this.action = action;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public Action getAction() {
        return this.action;
    }
}
