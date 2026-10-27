package com.android.systemui.shared.recents.utilities;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.ParcelableColorSpace;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapUtil {
    private static final String KEY_BUFFER = "bitmap_util_buffer";
    private static final String KEY_COLOR_SPACE = "bitmap_util_color_space";

    private BitmapUtil() {
    }

    public static Bundle hardwareBitmapToBundle(Bitmap bitmap) {
        ParcelableColorSpace parcelableColorSpace;
        if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Passed bitmap must have hardware config, found: " + bitmap.getConfig());
        }
        if (bitmap.getColorSpace() == null) {
            parcelableColorSpace = new ParcelableColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
        } else {
            parcelableColorSpace = new ParcelableColorSpace(bitmap.getColorSpace());
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BUFFER, bitmap.getHardwareBuffer());
        bundle.putParcelable(KEY_COLOR_SPACE, parcelableColorSpace);
        return bundle;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [android.graphics.ColorSpace, android.graphics.ParcelableColorSpace] */
    public static Bitmap bundleToHardwareBitmap(Bundle bundle) {
        if (!bundle.containsKey(KEY_BUFFER) || !bundle.containsKey(KEY_COLOR_SPACE)) {
            throw new IllegalArgumentException("Bundle does not contain a hardware bitmap");
        }
        HardwareBuffer hardwareBuffer = (HardwareBuffer) bundle.getParcelable(KEY_BUFFER);
        return Bitmap.wrapHardwareBuffer((HardwareBuffer) Objects.requireNonNull(hardwareBuffer), (ParcelableColorSpace) bundle.getParcelable(KEY_COLOR_SPACE));
    }
}
