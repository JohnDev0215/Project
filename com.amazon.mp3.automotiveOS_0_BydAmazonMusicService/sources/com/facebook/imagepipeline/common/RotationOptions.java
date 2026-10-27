package com.facebook.imagepipeline.common;

import androidx.annotation.Nullable;
import com.facebook.common.util.HashCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class RotationOptions {
    private static final RotationOptions ROTATION_OPTIONS_AUTO_ROTATE = new RotationOptions(-1, false);
    private static final RotationOptions ROTATION_OPTIONS_DISABLE_ROTATION = new RotationOptions(-2, false);
    private static final RotationOptions ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME = new RotationOptions(-1, true);
    private final boolean mDeferUntilRendered;
    private final int mRotation;

    public static RotationOptions autoRotate() {
        return ROTATION_OPTIONS_AUTO_ROTATE;
    }

    public static RotationOptions disableRotation() {
        return ROTATION_OPTIONS_DISABLE_ROTATION;
    }

    public static RotationOptions autoRotateAtRenderTime() {
        return ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME;
    }

    private RotationOptions(int rotation, boolean canDeferUntilRendered) {
        this.mRotation = rotation;
        this.mDeferUntilRendered = canDeferUntilRendered;
    }

    public boolean useImageMetadata() {
        return this.mRotation == -1;
    }

    public boolean rotationEnabled() {
        return this.mRotation != -2;
    }

    public int getForcedAngle() {
        if (useImageMetadata()) {
            throw new IllegalStateException("Rotation is set to use EXIF");
        }
        return this.mRotation;
    }

    public boolean canDeferUntilRendered() {
        return this.mDeferUntilRendered;
    }

    public int hashCode() {
        return HashCodeUtil.hashCode(Integer.valueOf(this.mRotation), Boolean.valueOf(this.mDeferUntilRendered));
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RotationOptions)) {
            return false;
        }
        RotationOptions rotationOptions = (RotationOptions) other;
        return this.mRotation == rotationOptions.mRotation && this.mDeferUntilRendered == rotationOptions.mDeferUntilRendered;
    }

    public String toString() {
        return String.format(null, "%d defer:%b", Integer.valueOf(this.mRotation), Boolean.valueOf(this.mDeferUntilRendered));
    }
}
