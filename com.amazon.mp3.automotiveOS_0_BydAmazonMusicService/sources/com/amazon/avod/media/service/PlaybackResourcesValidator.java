package com.amazon.avod.media.service;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesValidator {
    public PlaybackResources validatePlayerResources(@Nonnull String str, @Nonnull Optional<PlaybackResourcesWrapper> optional) throws ContentException {
        if (!optional.isPresent()) {
            DLog.errorf("Error making getPlaybackResources call for %s", str);
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "Failed to get player resources");
        }
        if (!optional.get().getPlaybackResources().isPresent()) {
            DLog.errorf("Error getting PlaybackResources for %s message: %s", str, optional.get().getError().get().message.get());
            throw new ContentException(toContentError(optional.get().getError().get().errorCode.get()));
        }
        PlaybackResources playbackResources = optional.get().getPlaybackResources().get();
        Optional<AudioVideoUrls> audioVideoUrls = playbackResources.getAudioVideoUrls();
        if (playbackResources.isEntitled() && audioVideoUrls.isPresent()) {
            return playbackResources;
        }
        ImmutableMap<Resource, PRSException> errorsByResource = playbackResources.getErrorsByResource();
        String str2 = null;
        Resource resource = Resource.PlaybackUrls;
        if (errorsByResource.containsKey(resource)) {
            str2 = errorsByResource.get(resource).errorCode.get();
            DLog.errorf("Error getting PlaybackUrls for %s message: %s", str, errorsByResource.get(resource).message.get());
        } else {
            Resource resource2 = Resource.PlaybackSettings;
            if (errorsByResource.containsKey(resource2)) {
                str2 = errorsByResource.get(resource2).errorCode.get();
                DLog.errorf("Error getting PlaybackSettings for %s message: %s", str, errorsByResource.get(resource2).message.get());
            }
        }
        throw new ContentException(toContentError(str2));
    }

    public PlaybackResourcesV2 validatePlayerResourcesV2(@Nonnull String str, @Nonnull Optional<PlaybackResourcesV2Wrapper> optional) throws ContentException {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(optional, "resourcesWrapper");
        if (!optional.isPresent()) {
            DLog.errorf("Error making getPlaybackResources call for %s", str);
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "Failed to get player resources");
        }
        if (!optional.get().getPlaybackResources().isPresent()) {
            DLog.errorf("Error getting PlaybackResources for %s message: %s", str, optional.get().getError().get().getMessage().orNull());
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, String.format("Failed to get player resources with code %s", optional.get().getError().get().getCode().orNull()));
        }
        PlaybackResourcesV2 playbackResourcesV2 = optional.get().getPlaybackResources().get();
        if (!playbackResourcesV2.getAudioVideoUrls().isPresent()) {
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "Failed to parse audioVideoUrls");
        }
        PRSException pRSException = playbackResourcesV2.getErrorsByResource().get(Resource.PlaybackUrls);
        if (pRSException == null) {
            return playbackResourcesV2;
        }
        DLog.errorf("Error getting PlaybackUrls for %s message: %s", str, pRSException.message.orNull());
        throw new ContentException(toContentError(pRSException.errorCode.orNull()));
    }

    private ContentException.ContentError toContentError(@Nullable String str) {
        return GetPlaybackResourcesServiceClient.toContentError(str);
    }
}
