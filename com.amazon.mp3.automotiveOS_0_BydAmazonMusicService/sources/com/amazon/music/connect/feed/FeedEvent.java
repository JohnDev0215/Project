package com.amazon.music.connect.feed;

/* JADX INFO: loaded from: classes4.dex */
public class FeedEvent {
    public final String canonicalId;
    public boolean processed = false;
    public final String secondaryId;
    public final Type type;

    public enum Type {
        RELEASE_ITEM_CLICKED,
        FOLLOW_ITEM_CLICKED,
        GENERIC_URL_ITEM_CLICKED,
        FRAGMENT_RESUMED,
        FRAGMENT_DESTROYED
    }

    public FeedEvent(Type type, String str, String str2) {
        this.type = type;
        this.canonicalId = str;
        this.secondaryId = str2;
    }
}
