package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class SeeMoreDestination extends Destination {
    private final String nextToken;

    public SeeMoreDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.nextToken = str;
    }

    public String getNextToken() {
        return this.nextToken;
    }
}
