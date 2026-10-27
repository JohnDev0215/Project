package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class SequenceEntry {
    final int SequenceID;
    final String URI;

    public SequenceEntry(int i, String str) {
        this.SequenceID = i;
        this.URI = str;
    }

    public int getSequenceID() {
        return this.SequenceID;
    }

    public String getURI() {
        return this.URI;
    }

    public String toString() {
        return "SequenceEntry{SequenceID=" + this.SequenceID + ",URI=" + this.URI + "}";
    }
}
