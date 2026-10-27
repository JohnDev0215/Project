package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.json.ServiceResponseParser;
import com.amazon.avod.util.URLUtils;
import com.amazon.bolthttp.Request;
import com.google.common.base.Joiner;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseResponseParser extends ServiceResponseParser<PlaybackResourcesV2Wrapper> {
    public DrmLicenseResponseParser(@Nonnull String str) {
        super(new DrmLicenseResourceParser(str));
    }

    @Override // com.amazon.avod.json.ServiceResponseParser
    @Nonnull
    protected String getSaveFilename(@Nonnull Request<PlaybackResourcesV2Wrapper> request) {
        return Joiner.on("-").skipNulls().join("prs", URLUtils.getRequestParameters(request).get("titleId"), new Object[0]) + ".json";
    }
}
