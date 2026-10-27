package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class PersonalizedPlaylistsDestination extends Destination {
    private final Action action;
    private final String personalizedPlaylistsType;

    public PersonalizedPlaylistsDestination(String str, String str2, String str3, Action action) {
        super(str, str2);
        this.personalizedPlaylistsType = str3;
        this.action = action;
    }

    public String getPersonalizedPlaylistsType() {
        return this.personalizedPlaylistsType;
    }

    public Action getAction() {
        return this.action;
    }
}
