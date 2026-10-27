package com.spotify.mediasession.imageprovider;

/* JADX INFO: loaded from: classes.dex */
public enum MediaUriUtil$ImageDimension {
    /* JADX INFO: Fake field, exist only in values array */
    LARGE(640),
    /* JADX INFO: Fake field, exist only in values array */
    NORMAL(320),
    /* JADX INFO: Fake field, exist only in values array */
    SMALL(160);

    private final int mValue;

    MediaUriUtil$ImageDimension(int i) {
        this.mValue = i;
    }

    public final int a() {
        return this.mValue;
    }
}
