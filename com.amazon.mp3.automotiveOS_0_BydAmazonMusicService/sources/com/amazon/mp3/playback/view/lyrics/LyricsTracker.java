package com.amazon.mp3.playback.view.lyrics;

import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class LyricsTracker {
    private int mCurrentPos;
    private Lyrics mLyrics;

    public LyricsTracker() {
        this.mCurrentPos = 0;
        this.mLyrics = null;
    }

    public LyricsTracker(Lyrics lyrics) {
        this.mCurrentPos = 0;
        this.mLyrics = lyrics;
    }

    public int findPositionFromTime(long time) {
        if (this.mLyrics == null || time == 0) {
            return 0;
        }
        int i = this.mCurrentPos;
        int i2 = i + 1;
        LyricsLine line = getLine(i);
        LyricsLine line2 = getLine(i2);
        if (time >= line.getStartTime()) {
            if (line2 == null || time < line2.getStartTime()) {
                return this.mCurrentPos;
            }
            if (time < line2.getEndTime() - 1) {
                this.mCurrentPos = i2;
                return i2;
            }
        } else if (this.mCurrentPos == 0) {
            return 0;
        }
        int iBinarySearchPosition = binarySearchPosition(this.mLyrics.getLines(), time);
        this.mCurrentPos = iBinarySearchPosition;
        return iBinarySearchPosition;
    }

    private LyricsLine getLine(int position) {
        if (position >= this.mLyrics.getLines().size()) {
            return null;
        }
        return this.mLyrics.getLines().get(position);
    }

    private int binarySearchPosition(List<LyricsLine> lines, long time) {
        if (lines != null && lines.size() != 0) {
            int size = lines.size() - 1;
            int i = 0;
            while (i <= size) {
                int i2 = (i + size) / 2;
                int i3 = i2 + 1;
                long startTime = lines.get(i2).getStartTime();
                long startTime2 = (i3 < lines.size() ? lines.get(i3).getStartTime() : Long.MAX_VALUE) - 1;
                if (time < startTime) {
                    size = i2 - 1;
                } else {
                    if (time <= startTime2) {
                        return i2;
                    }
                    i = i3;
                }
            }
        }
        return 0;
    }
}
