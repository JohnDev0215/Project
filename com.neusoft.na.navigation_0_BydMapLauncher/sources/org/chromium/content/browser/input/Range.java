package org.chromium.content.browser.input;

/* JADX INFO: loaded from: classes3.dex */
public class Range {
    private int mEnd;
    private int mStart;

    public Range(int i, int i2) {
        set(i, i2);
    }

    public int start() {
        return this.mStart;
    }

    public int end() {
        return this.mEnd;
    }

    public void set(int i, int i2) {
        this.mStart = Math.min(i, i2);
        this.mEnd = Math.max(i, i2);
    }

    public void clamp(int i, int i2) {
        this.mStart = Math.min(Math.max(this.mStart, i), i2);
        this.mEnd = Math.max(Math.min(this.mEnd, i2), i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Range range = (Range) obj;
        return this.mStart == range.mStart && this.mEnd == range.mEnd;
    }

    public int hashCode() {
        return (this.mStart * 11) + (this.mEnd * 31);
    }

    public String toString() {
        return "[ " + this.mStart + ", " + this.mEnd + " ]";
    }
}
