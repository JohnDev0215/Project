package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistDestination extends AsinActionDestination {
    private final String storyAsin;

    public PlaylistDestination(String str, String str2, Action action, String str3, String str4, String str5) {
        super(str, str2, action, str3, str4);
        this.storyAsin = str5;
    }

    public String getStoryAsin() {
        return this.storyAsin;
    }
}
