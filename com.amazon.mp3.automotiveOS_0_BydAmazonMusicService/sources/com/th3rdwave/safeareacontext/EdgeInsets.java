package com.th3rdwave.safeareacontext;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EdgeInsets.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\t¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/th3rdwave/safeareacontext/EdgeInsets;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "top", "F", "getTop", "()F", "right", "getRight", "bottom", "getBottom", "left", "getLeft", "<init>", "(FFFF)V", "react-native-safe-area-context_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class EdgeInsets {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EdgeInsets)) {
            return false;
        }
        EdgeInsets edgeInsets = (EdgeInsets) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.top), (Object) Float.valueOf(edgeInsets.top)) && Intrinsics.areEqual((Object) Float.valueOf(this.right), (Object) Float.valueOf(edgeInsets.right)) && Intrinsics.areEqual((Object) Float.valueOf(this.bottom), (Object) Float.valueOf(edgeInsets.bottom)) && Intrinsics.areEqual((Object) Float.valueOf(this.left), (Object) Float.valueOf(edgeInsets.left));
    }

    public int hashCode() {
        return (((((Float.hashCode(this.top) * 31) + Float.hashCode(this.right)) * 31) + Float.hashCode(this.bottom)) * 31) + Float.hashCode(this.left);
    }

    public String toString() {
        return "EdgeInsets(top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", left=" + this.left + ')';
    }

    public EdgeInsets(float f, float f2, float f3, float f4) {
        this.top = f;
        this.right = f2;
        this.bottom = f3;
        this.left = f4;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }
}
