package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FatalContentEventError extends ContentEventErrorBase {
    private final String mConsumptionId;
    private final ContentSessionType mEffectiveSessionType;

    public FatalContentEventError(PlayableContent playableContent, ContentSessionType contentSessionType, ContentException contentException, @Nullable String str, @Nonnull ContentSessionType contentSessionType2) {
        this(playableContent, contentSessionType, contentException, null, null, null, str, contentSessionType2);
    }

    public FatalContentEventError(PlayableContent playableContent, ContentSessionType contentSessionType, ContentException contentException, ContentUrl contentUrl, SmoothStreamingURI smoothStreamingURI, @Nullable Map<String, List<String>> map, @Nullable String str, @Nonnull ContentSessionType contentSessionType2) {
        super(playableContent, contentSessionType, contentException, contentUrl, smoothStreamingURI, map);
        this.mConsumptionId = str;
        this.mEffectiveSessionType = (ContentSessionType) Preconditions.checkNotNull(contentSessionType2, "effectiveSessionType");
    }

    @Override // com.amazon.avod.content.event.ContentEventErrorBase
    public boolean isRetriable() {
        return ContentSessionType.isCacheSession(this.mEffectiveSessionType);
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}
