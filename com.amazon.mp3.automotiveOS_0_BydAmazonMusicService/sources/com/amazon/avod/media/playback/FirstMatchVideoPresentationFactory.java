package com.amazon.avod.media.playback;

import com.amazon.avod.playback.sye.SyeVideoPresentationFactory;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FirstMatchVideoPresentationFactory implements VideoPresentationFactory {
    private final Set<VideoPresentationFactory> mFactories;
    private final VideoPresentationFactory mSyeFactory;

    public FirstMatchVideoPresentationFactory(SyeVideoPresentationFactory syeVideoPresentationFactory, Set<VideoPresentationFactory> set) {
        this.mFactories = set;
        this.mSyeFactory = syeVideoPresentationFactory;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationFactory
    public VideoPresentation newVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions) {
        Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        VideoPresentation videoPresentationNewVideoPresentation = this.mSyeFactory.newVideoPresentation(videoSpecification, file, videoOptions);
        if (videoPresentationNewVideoPresentation != null) {
            return videoPresentationNewVideoPresentation;
        }
        Iterator<VideoPresentationFactory> it = this.mFactories.iterator();
        while (it.hasNext()) {
            VideoPresentation videoPresentationNewVideoPresentation2 = it.next().newVideoPresentation(videoSpecification, file, videoOptions);
            if (videoPresentationNewVideoPresentation2 != null) {
                return videoPresentationNewVideoPresentation2;
            }
        }
        DLog.errorf("Unable to create VideoPresentation for spec: %s", videoSpecification);
        return null;
    }
}
