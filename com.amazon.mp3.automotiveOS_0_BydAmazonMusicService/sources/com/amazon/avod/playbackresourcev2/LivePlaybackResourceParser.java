package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.playbackresource.PlaybackUrlsTransformer;
import com.amazon.avod.playbackresourcev2.LivePlaybackResourcesResponse;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.util.DLog;
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
public class LivePlaybackResourceParser implements JacksonJsonStreamParser<PlaybackResourcesV2Wrapper> {
    private final LivePlaybackResourcesResponse.Parser mLivePlaybackResourcesResponseParser;
    private final PlaybackUrlsTransformer mPlaybackUrlsTransformer;
    private final String mTitleId;

    public LivePlaybackResourceParser(@Nonnull String str) {
        this(str, new LivePlaybackResourcesResponse.Parser(JacksonCache.OBJECT_MAPPER), new PlaybackUrlsTransformer());
    }

    LivePlaybackResourceParser(@Nonnull String str, @Nonnull LivePlaybackResourcesResponse.Parser parser, @Nonnull PlaybackUrlsTransformer playbackUrlsTransformer) {
        this.mTitleId = (String) Preconditions.checkNotNull(str);
        this.mLivePlaybackResourcesResponseParser = (LivePlaybackResourcesResponse.Parser) Preconditions.checkNotNull(parser, "livePlaybackResourcesResponseParser");
        this.mPlaybackUrlsTransformer = (PlaybackUrlsTransformer) Preconditions.checkNotNull(playbackUrlsTransformer, "playbackUrlsTransformer");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public PlaybackResourcesV2Wrapper parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        Optional optionalAbsent;
        Optional optionalAbsent2;
        LivePlaybackResourcesResponse livePlaybackResourcesResponse = this.mLivePlaybackResourcesResponseParser.parse(jsonParser);
        if (livePlaybackResourcesResponse.getError().isPresent()) {
            return new PlaybackResourcesV2Wrapper(livePlaybackResourcesResponse.getError().get());
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        final PlaybackResourcesV2.Builder builder2 = new PlaybackResourcesV2.Builder();
        builder2.withTitleId(this.mTitleId);
        Optional<LivePlaybackUrls> livePlaybackUrls = livePlaybackResourcesResponse.getLivePlaybackUrls();
        if (livePlaybackUrls.isPresent()) {
            Optional<Prsv2Error> error = livePlaybackUrls.get().getError();
            if (error.isPresent()) {
                builder.put(Resource.PlaybackUrls, Prsv2Error.transformToPRSException(error.get()));
            }
            optionalAbsent = Optional.fromNullable(this.mPlaybackUrlsTransformer.transform(livePlaybackUrls, livePlaybackUrls.get().getPlaybackSettings(), livePlaybackResourcesResponse.getSession()));
        } else {
            optionalAbsent = Optional.absent();
        }
        optionalAbsent.transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.LivePlaybackResourceParser$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder2.withAudioVideoUrls((AudioVideoUrls) obj);
            }
        });
        livePlaybackResourcesResponse.getXrayMetadata().transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.LivePlaybackResourceParser$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder2.withXrayMetadata((XRayFragmentBaseV2) obj);
            }
        });
        livePlaybackResourcesResponse.getPlaybackData().transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.LivePlaybackResourceParser$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder2.withPlaybackData((PlaybackData) obj);
            }
        });
        Optional<SyeUrlResponseV2> syeUrls = livePlaybackResourcesResponse.getSyeUrls();
        if (syeUrls.isPresent()) {
            Optional<Prsv2Error> error2 = syeUrls.get().getError();
            if (error2.isPresent()) {
                builder.put(Resource.SyeUrlsV2, Prsv2Error.transformToPRSException(error2.get()));
                DLog.warnf("SyeUrlResponseV2 returned error %s playback will fallback to dash.", error2.get());
            }
            optionalAbsent2 = Optional.fromNullable(syeUrls.get().transformToSyeUrlResponse());
        } else {
            optionalAbsent2 = Optional.absent();
        }
        optionalAbsent2.transform(new Function() { // from class: com.amazon.avod.playbackresourcev2.LivePlaybackResourceParser$$ExternalSyntheticLambda3
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return builder2.withSyeUrls((SyeUrlResponse) obj);
            }
        });
        builder2.withErrorsByResource(builder.build());
        return new PlaybackResourcesV2Wrapper(builder2.build());
    }
}
