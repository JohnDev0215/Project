package com.amazon.avod.media.playback;

import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPresentationFactory {
    VideoPresentation newVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions);
}
