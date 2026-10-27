package com.amazon.ui.foundations.styles;

import kotlin.Metadata;

/* JADX INFO: compiled from: OutlineStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010B\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0011¢\u0006\u0004\b\u000f\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/ui/foundations/styles/OutlineStyle;", "", "", "toString", "", "hashCode", "other", "", "equals", "color", "I", "getColor", "()I", "width", "getWidth", "<init>", "(II)V", "", "(IF)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class OutlineStyle {
    private final int color;
    private final int width;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OutlineStyle)) {
            return false;
        }
        OutlineStyle outlineStyle = (OutlineStyle) other;
        return this.color == outlineStyle.color && this.width == outlineStyle.width;
    }

    public int hashCode() {
        return (Integer.hashCode(this.color) * 31) + Integer.hashCode(this.width);
    }

    public String toString() {
        return "OutlineStyle(color=" + this.color + ", width=" + this.width + ')';
    }

    public OutlineStyle(int i, int i2) {
        this.color = i;
        this.width = i2;
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final int getColor() {
        return this.color;
    }

    public final int getWidth() {
        return this.width;
    }

    public OutlineStyle(int i, float f) {
        this(i, (int) f);
    }
}
