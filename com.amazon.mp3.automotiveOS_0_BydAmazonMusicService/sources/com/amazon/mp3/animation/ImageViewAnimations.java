package com.amazon.mp3.animation;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageViewAnimations {
    private ImageViewAnimations() {
    }

    public static void fade(final ImageView imageView, final float targetAlpha) {
        imageView.animate().alpha(targetAlpha).setDuration(500L);
    }
}
