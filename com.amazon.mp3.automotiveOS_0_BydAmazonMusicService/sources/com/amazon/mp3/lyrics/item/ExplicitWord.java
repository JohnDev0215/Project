package com.amazon.mp3.lyrics.item;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitWord {
    private int mEndIndex;
    private int mStartIndex;

    public ExplicitWord(int startIndex, int endIndex) {
        this.mStartIndex = startIndex;
        this.mEndIndex = endIndex;
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public void setStartIndex(int startIndex) {
        this.mStartIndex = startIndex;
    }

    public int getEndIndex() {
        return this.mEndIndex;
    }

    public void setEndIndex(int endIndex) {
        this.mEndIndex = endIndex;
    }
}
