package com.amazon.mp3.lyrics.item;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsLine {
    private boolean islineSelected = false;
    private long mEndTime;
    private Collection<ExplicitWord> mExplicitWords;
    private String mLine;
    private long mStartTime;

    public boolean isLineSelected() {
        return this.islineSelected;
    }

    public void setLineSelected(boolean isLineSelected) {
        this.islineSelected = isLineSelected;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long startTime) {
        this.mStartTime = startTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long endTime) {
        this.mEndTime = endTime;
    }

    public String getLine() {
        return this.mLine;
    }

    public void setLine(String line) {
        this.mLine = line;
    }

    public void addExplicitWord(ExplicitWord explicitWord) {
        if (this.mExplicitWords == null) {
            this.mExplicitWords = new ArrayList();
        }
        this.mExplicitWords.add(explicitWord);
    }

    public String toString() {
        String str = this.mLine;
        return str != null ? str : "";
    }
}
