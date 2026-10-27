package com.amazon.avod.media.playback;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VideoPresentationFactoryBase implements VideoPresentationFactory {
    private final HashSet<String> mSupportedMimeTypes;

    public VideoPresentationFactoryBase(List<String> list) {
        this.mSupportedMimeTypes = Sets.newHashSet(list);
    }

    @Deprecated
    protected VideoPlayerLifecyleEventHandler newLifecycleEventHandler() {
        return new VideoPlayerLifecyleEventHandler();
    }

    protected boolean supportsMimeType(String str) {
        return this.mSupportedMimeTypes.contains(str.toLowerCase(Locale.US));
    }
}
