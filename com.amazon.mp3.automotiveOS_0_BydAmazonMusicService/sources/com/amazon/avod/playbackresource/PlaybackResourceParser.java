package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PRSOwnershipException;
import com.amazon.atvplaybackresource.PlaybackResourcesResponse;
import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.atvplaybackresource.SelectedEntitlement;
import com.amazon.atvplaybackresource.types.EntitlementType;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourceParser implements JacksonJsonStreamParser<PlaybackResourcesWrapper> {
    private final PlaybackResourcesResponse.Parser mPlaybackResourcesResponseParser;
    private final PlaybackUrlsTransformer mPlaybackUrlsTransformer;
    private final SubtitleTransformer mSubtitleTransformer;
    private final TrickplayUrlTransformer mTrickplayUrlTransformer;

    public PlaybackResourceParser() {
        this(new PlaybackResourcesResponse.Parser(JacksonCache.OBJECT_MAPPER), new SubtitleTransformer(), new TrickplayUrlTransformer(), new PlaybackUrlsTransformer());
    }

    PlaybackResourceParser(@Nonnull PlaybackResourcesResponse.Parser parser, @Nonnull SubtitleTransformer subtitleTransformer, @Nonnull TrickplayUrlTransformer trickplayUrlTransformer, @Nonnull PlaybackUrlsTransformer playbackUrlsTransformer) {
        this.mPlaybackResourcesResponseParser = (PlaybackResourcesResponse.Parser) Preconditions.checkNotNull(parser, "playbackResourcesResponseParser");
        this.mSubtitleTransformer = (SubtitleTransformer) Preconditions.checkNotNull(subtitleTransformer, "subtitleTransformer");
        this.mTrickplayUrlTransformer = (TrickplayUrlTransformer) Preconditions.checkNotNull(trickplayUrlTransformer, "trickplayUrlTransformer");
        this.mPlaybackUrlsTransformer = (PlaybackUrlsTransformer) Preconditions.checkNotNull(playbackUrlsTransformer, "playbackUrlsTransformer");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public PlaybackResourcesWrapper parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        ImmutableMap<Resource, PRSException> immutableMapOf;
        PlaybackResourcesResponse playbackResourcesResponse = this.mPlaybackResourcesResponseParser.parse(jsonParser);
        if (playbackResourcesResponse.error.isPresent()) {
            return new PlaybackResourcesWrapper(playbackResourcesResponse.error.get());
        }
        ResponseTitleRendition responseTitleRenditionOrNull = playbackResourcesResponse.returnedTitleRendition.orNull();
        if (responseTitleRenditionOrNull == null) {
            throw new JsonContractException("No title rendition present");
        }
        SelectedEntitlement selectedEntitlementOrNull = responseTitleRenditionOrNull.selectedEntitlement.orNull();
        if (selectedEntitlementOrNull == null) {
            throw new JsonContractException("No entitlement info");
        }
        Optional<EntitlementType> optional = selectedEntitlementOrNull.entitlementType;
        if (playbackResourcesResponse.errorsByResource.isPresent()) {
            immutableMapOf = playbackResourcesResponse.errorsByResource.get();
        } else {
            immutableMapOf = ImmutableMap.of();
        }
        return new PlaybackResourcesWrapper(new PlaybackResources(optional, playbackResourcesResponse.error, this.mSubtitleTransformer.transform(playbackResourcesResponse.subtitleUrls, false), this.mSubtitleTransformer.transform(playbackResourcesResponse.forcedNarratives, true), this.mTrickplayUrlTransformer.transform(playbackResourcesResponse.trickplayUrls), isEntitled(playbackResourcesResponse.error, selectedEntitlementOrNull), playbackResourcesResponse.subtitlePresets, playbackResourcesResponse.cuepointPlaylist, Optional.fromNullable(playbackResourcesResponse.playbackUrls.isPresent() ? this.mPlaybackUrlsTransformer.transform(playbackResourcesResponse.playbackUrls, playbackResourcesResponse.playbackSettings, playbackResourcesResponse.returnedTitleRendition, playbackResourcesResponse.cuepointPlaylist) : null), playbackResourcesResponse.playReadyLicense, playbackResourcesResponse.widevine2License, playbackResourcesResponse.syeUrlsV2, playbackResourcesResponse.playbackSettings, immutableMapOf, playbackResourcesResponse.returnedTitleRendition, playbackResourcesResponse.xrayMetadata));
    }

    private static boolean isEntitled(@Nonnull Optional<PRSException> optional, @Nonnull SelectedEntitlement selectedEntitlement) {
        return !((optional.orNull() instanceof PRSOwnershipException) || selectedEntitlement.rightsException.isPresent());
    }
}
