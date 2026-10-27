package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.json.ServiceResponseParser;
import com.amazon.avod.util.URLUtils;
import com.amazon.bolthttp.Request;
import com.google.common.base.Joiner;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LivePlaybackResourceResponseParser extends ServiceResponseParser<PlaybackResourcesV2Wrapper> {
    public LivePlaybackResourceResponseParser(@Nonnull String str) {
        super(new LivePlaybackResourceParser(str));
    }

    @Override // com.amazon.avod.json.ServiceResponseParser
    @Nonnull
    protected String getSaveFilename(@Nonnull Request<PlaybackResourcesV2Wrapper> request) {
        return Joiner.on("-").skipNulls().join("prs", URLUtils.getRequestParameters(request).get("titleId"), new Object[0]) + ".json";
    }
}
