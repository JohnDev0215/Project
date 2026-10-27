package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.urlvending.ContentUrl;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RetriableServiceErrorEvent extends ContentEventErrorBase {
    private final String mGenericMessage;
    private final String mSpecificMessage;

    @Override // com.amazon.avod.content.event.ContentEventErrorBase
    public boolean isRetriable() {
        return true;
    }

    public RetriableServiceErrorEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, @Nullable ContentUrl contentUrl, @Nullable SmoothStreamingURI smoothStreamingURI, @Nullable Map<String, List<String>> map, @Nullable String str, @Nullable String str2) {
        super(playableContent, contentSessionType, contentException, contentUrl, smoothStreamingURI, map);
        this.mGenericMessage = str;
        this.mSpecificMessage = str2;
    }

    @Nullable
    public String getGenericMessage() {
        return this.mGenericMessage;
    }

    @Nullable
    public String getSpecificMessage() {
        return this.mSpecificMessage;
    }
}
