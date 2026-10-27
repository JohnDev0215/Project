package com.amazon.scrublib.models;

import kotlin.Metadata;

/* JADX INFO: compiled from: Point.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/amazon/scrublib/models/Point;", "", "", "toString", "", "hashCode", "other", "", "equals", "x", "I", "getX", "()I", "setX", "(I)V", "y", "getY", "setY", "<init>", "(II)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Point {
    private int x;
    private int y;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Point)) {
            return false;
        }
        Point point = (Point) other;
        return this.x == point.x && this.y == point.y;
    }

    public int hashCode() {
        return (Integer.hashCode(this.x) * 31) + Integer.hashCode(this.y);
    }

    public String toString() {
        return "Point(x=" + this.x + ", y=" + this.y + ')';
    }

    public Point(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int i) {
        this.x = i;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int i) {
        this.y = i;
    }
}
