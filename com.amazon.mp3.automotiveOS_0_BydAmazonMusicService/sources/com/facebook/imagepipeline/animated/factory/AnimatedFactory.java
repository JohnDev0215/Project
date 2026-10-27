package com.facebook.imagepipeline.animated.factory;

import android.content.Context;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
public interface AnimatedFactory {
    @Nullable
    DrawableFactory getAnimatedDrawableFactory(@Nullable Context context);

    @Nullable
    ImageDecoder getGifDecoder();

    @Nullable
    ImageDecoder getWebPDecoder();
}
