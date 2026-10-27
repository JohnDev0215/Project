package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShadowStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/ui/foundations/styles/ShadowStyle;", "", "", "toString", "", "hashCode", "other", "", "equals", "color", "I", "getColor", "()I", "", "shadowX", "F", "getShadowX", "()F", "shadowY", "getShadowY", "<init>", "(IFF)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ShadowStyle {
    private final int color;
    private final float shadowX;
    private final float shadowY;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowStyle)) {
            return false;
        }
        ShadowStyle shadowStyle = (ShadowStyle) other;
        return this.color == shadowStyle.color && Intrinsics.areEqual((Object) Float.valueOf(this.shadowX), (Object) Float.valueOf(shadowStyle.shadowX)) && Intrinsics.areEqual((Object) Float.valueOf(this.shadowY), (Object) Float.valueOf(shadowStyle.shadowY));
    }

    public int hashCode() {
        return (((Integer.hashCode(this.color) * 31) + Float.hashCode(this.shadowX)) * 31) + Float.hashCode(this.shadowY);
    }

    public String toString() {
        return "ShadowStyle(color=" + this.color + ", shadowX=" + this.shadowX + ", shadowY=" + this.shadowY + ')';
    }

    public ShadowStyle(int i, float f, float f2) {
        this.color = i;
        this.shadowX = f;
        this.shadowY = f2;
        if (!(f >= 0.0f && f2 >= 0.0f)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final int getColor() {
        return this.color;
    }

    public final float getShadowX() {
        return this.shadowX;
    }

    public final float getShadowY() {
        return this.shadowY;
    }
}
