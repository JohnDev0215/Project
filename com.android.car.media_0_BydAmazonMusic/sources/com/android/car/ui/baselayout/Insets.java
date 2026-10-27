package com.android.car.ui.baselayout;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import java.util.Objects;
import spi.c;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Insets.class != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.mLeft == insets.mLeft && this.mRight == insets.mRight && this.mTop == insets.mTop && this.mBottom == insets.mBottom;
    }

    public int getBottom() {
        return this.mBottom;
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

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mLeft), Integer.valueOf(this.mRight), Integer.valueOf(this.mTop), Integer.valueOf(this.mBottom));
    }

    @NonNull
    public String toString() {
        StringBuilder sbK = c.k("{ left: ");
        sbK.append(this.mLeft);
        sbK.append(", right: ");
        sbK.append(this.mRight);
        sbK.append(", top: ");
        sbK.append(this.mTop);
        sbK.append(", bottom: ");
        sbK.append(this.mBottom);
        sbK.append(" }");
        return sbK.toString();
    }

    public Insets(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mRight = i3;
        this.mTop = i2;
        this.mBottom = i4;
    }
}
