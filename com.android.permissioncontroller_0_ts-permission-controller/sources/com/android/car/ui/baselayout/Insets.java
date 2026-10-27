package com.android.car.ui.baselayout;

import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public final class Insets {
    private final int mBottom;
    private final int mLeft;
    private final int mRight;
    private final int mTop;

    public Insets() {
        this.mBottom = 0;
        this.mTop = 0;
        this.mRight = 0;
        this.mLeft = 0;
    }

    public Insets(int left, int top, int right, int bottom) {
        this.mLeft = left;
        this.mRight = right;
        this.mTop = top;
        this.mBottom = bottom;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getBottom() {
        return this.mBottom;
    }

    public String toString() {
        return "{ left: " + this.mLeft + ", right: " + this.mRight + ", top: " + this.mTop + ", bottom: " + this.mBottom + " }";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insets insets = (Insets) o;
        if (this.mLeft == insets.mLeft && this.mRight == insets.mRight && this.mTop == insets.mTop && this.mBottom == insets.mBottom) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mLeft), Integer.valueOf(this.mRight), Integer.valueOf(this.mTop), Integer.valueOf(this.mBottom));
    }
}
