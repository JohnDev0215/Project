package com.amazon.video.sdk.avod.playbackclient.subtitle;

import androidx.core.view.ViewCompat;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleDefaults {
    public static final int TEXT_ARGB_COLOR = toArgbColor(-1, 1.0f);
    public static final int BACKGROUND_ARGB_COLOR = toArgbColor(-7829368, 0.5f);
    public static final int WINDOW_ARGB_COLOR = toArgbColor(-7829368, 0.0f);

    private SubtitleDefaults() {
    }

    public static int toArgbColor(int i, float f) {
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "Opacity must be in the [0, 1] range. Was [%s].", Float.valueOf(f));
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((((int) (f * 255.0f)) & 255) << 24);
    }
}
