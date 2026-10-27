package com.amazon.avod.prs;

import com.amazon.avod.json.ServiceResponseParser;
import com.amazon.avod.playbackresource.PlaybackResourceParser;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.util.URLUtils;
import com.amazon.bolthttp.Request;
import com.google.common.base.Joiner;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PlaybackResourceResponseParser extends ServiceResponseParser<PlaybackResourcesWrapper> {
    PlaybackResourceResponseParser() {
        super(new PlaybackResourceParser());
    }

    @Override // com.amazon.avod.json.ServiceResponseParser
    @Nonnull
    protected String getSaveFilename(@Nonnull Request<PlaybackResourcesWrapper> request) {
        return Joiner.on("-").skipNulls().join("prs", URLUtils.getRequestParameters(request).get("titleId"), new Object[0]) + ".json";
    }
}
