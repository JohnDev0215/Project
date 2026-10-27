package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AsinActionDestination extends Destination {
    private final Action action;
    private final String collectionAsin;
    private final String trackAsin;

    public AsinActionDestination(String str, String str2, Action action, String str3, String str4) {
        super(str3, str4);
        this.collectionAsin = str;
        this.trackAsin = str2;
        this.action = action;
    }

    public AsinActionDestination(String str, String str2, Action action, String str3, String str4, boolean z) {
        super(str3, str4, z);
        this.collectionAsin = str;
        this.trackAsin = str2;
        this.action = action;
    }

    public String getCollectionAsin() {
        return this.collectionAsin;
    }

    public String getTrackAsin() {
        return this.trackAsin;
    }

    public Action getAction() {
        return this.action;
    }
}
