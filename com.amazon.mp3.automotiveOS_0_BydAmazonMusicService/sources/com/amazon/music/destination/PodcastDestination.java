package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastDestination extends Destination {
    private final String deeplinkUrl;

    public PodcastDestination(String str, String str2, String str3) {
        super(str, str2, true);
        this.deeplinkUrl = str3;
    }

    public String getDeeplinkUrl() {
        return this.deeplinkUrl;
    }
}
