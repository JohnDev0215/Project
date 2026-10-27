package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.playbackresourcev2.DrmLicenseResourcesResponse;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseResourceParser implements JacksonJsonStreamParser<PlaybackResourcesV2Wrapper> {
    private final DrmLicenseResourcesResponse.Parser mDrmLicenseResourcesResponseParser;
    private final String mTitleId;

    public DrmLicenseResourceParser(@Nonnull String str) {
        this(str, new DrmLicenseResourcesResponse.Parser(JacksonCache.OBJECT_MAPPER));
    }

    DrmLicenseResourceParser(@Nonnull String str, @Nonnull DrmLicenseResourcesResponse.Parser parser) {
        this.mTitleId = (String) Preconditions.checkNotNull(str);
        this.mDrmLicenseResourcesResponseParser = (DrmLicenseResourcesResponse.Parser) Preconditions.checkNotNull(parser, "drmLicenseResourcesResponseParser");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public PlaybackResourcesV2Wrapper parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        DrmLicenseResourcesResponse drmLicenseResourcesResponse = this.mDrmLicenseResourcesResponseParser.parse(jsonParser);
        final PlaybackResourcesV2.Builder builder = new PlaybackResourcesV2.Builder();
        builder.withTitleId(this.mTitleId);
        drmLicenseResourcesResponse.getPlayReadyLicense().transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.DrmLicenseResourceParser$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder.withPlayReadyLicense((PlayReadyLicenseV2) obj);
            }
        });
        drmLicenseResourcesResponse.getWidevineLicense().transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.DrmLicenseResourceParser$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder.withWidevineLicense((WidevineLicenseV2) obj);
            }
        });
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        Optional<Prsv2Error> error = drmLicenseResourcesResponse.getError();
        if (error.isPresent()) {
            builder2.put(drmLicenseResourcesResponse.getPlayReadyLicense().isPresent() ? Resource.PlayReadyLicense : Resource.Widevine2License, Prsv2Error.transformToPRSException(error.get()));
        }
        builder.withErrorsByResource(builder2.build());
        return new PlaybackResourcesV2Wrapper(builder.build());
    }
}
