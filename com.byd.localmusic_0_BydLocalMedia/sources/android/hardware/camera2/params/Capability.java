package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Range;
import android.util.Size;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class Capability {
    public static final int COUNT = 3;
    private final int mMaxStreamingHeight;
    private final int mMaxStreamingWidth;
    private final float mMaxZoomRatio;
    private final float mMinZoomRatio;
    private final int mMode;

    public Capability(int i, int i2, int i3, float f, float f2) {
        this.mMode = i;
        this.mMaxStreamingWidth = Preconditions.checkArgumentNonnegative(i2, "maxStreamingWidth must be nonnegative");
        this.mMaxStreamingHeight = Preconditions.checkArgumentNonnegative(i3, "maxStreamingHeight must be nonnegative");
        if (f > f2) {
            throw new IllegalArgumentException("minZoomRatio " + f + " is greater than maxZoomRatio " + f2);
        }
        this.mMinZoomRatio = Preconditions.checkArgumentPositive(f, "minZoomRatio must be positive");
        this.mMaxZoomRatio = Preconditions.checkArgumentPositive(f2, "maxZoomRatio must be positive");
    }

    public int getMode() {
        return this.mMode;
    }

    public Size getMaxStreamingSize() {
        return new Size(this.mMaxStreamingWidth, this.mMaxStreamingHeight);
    }

    public Range<Float> getZoomRatioRange() {
        return new Range<>(Float.valueOf(this.mMinZoomRatio), Float.valueOf(this.mMaxZoomRatio));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Capability)) {
            return false;
        }
        Capability capability = (Capability) obj;
        return this.mMode == capability.mMode && this.mMaxStreamingWidth == capability.mMaxStreamingWidth && this.mMaxStreamingHeight == capability.mMaxStreamingHeight && this.mMinZoomRatio == capability.mMinZoomRatio && this.mMaxZoomRatio == capability.mMaxZoomRatio;
    }

    public int hashCode() {
        return HashCodeHelpers.hashCode(this.mMode, this.mMaxStreamingWidth, this.mMaxStreamingHeight, this.mMinZoomRatio, this.mMaxZoomRatio);
    }

    public String toString() {
        return String.format("(mode:%d, maxStreamingSize:%d x %d, zoomRatio: %f-%f)", Integer.valueOf(this.mMode), Integer.valueOf(this.mMaxStreamingWidth), Integer.valueOf(this.mMaxStreamingHeight), Float.valueOf(this.mMinZoomRatio), Float.valueOf(this.mMaxZoomRatio));
    }
}
