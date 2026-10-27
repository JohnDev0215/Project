package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class ExternalDestination extends Destination {
    private final String url;

    public ExternalDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }
}
