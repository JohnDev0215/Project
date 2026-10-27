package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentMetadataAcquiredEvent extends ContentEventBase {
    private final String mDrmAssetId;

    public ContentMetadataAcquiredEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull String str) {
        super(playableContent, contentSessionType);
        this.mDrmAssetId = (String) Preconditions.checkNotNull(str, "drmAssetId");
    }

    public String getDrmAssetId() {
        return this.mDrmAssetId;
    }
}
