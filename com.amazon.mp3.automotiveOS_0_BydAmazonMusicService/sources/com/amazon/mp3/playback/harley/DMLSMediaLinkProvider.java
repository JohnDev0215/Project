package com.amazon.mp3.playback.harley;

import android.net.Uri;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.ContentResponse;
import com.amazon.mp3.net.dmls.DMLSApi;
import com.amazon.mp3.net.dmls.DMLSApiProvider;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.MediaLinks;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.util.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DMLSMediaLinkProvider implements MediaLinkProvider {
    private static final MediaLink.Type DEFAULT_LINK_TYPE = MediaLink.Type.HLS;
    private static final String TAG = "DMLSMediaLinkProvider";

    @Override // com.amazon.music.media.playback.MediaLinkProvider
    public MediaLinks getMediaLinks(final MediaItem mediaItem, final BitRateSelection bitRateSelection) throws PlaybackException {
        return getMediaLinksInternal(mediaItem, bitRateSelection, false, DEFAULT_LINK_TYPE);
    }

    @Override // com.amazon.music.media.playback.MediaLinkProvider
    public MediaLinks getMediaLinks(final MediaItem mediaItem, final BitRateSelection bitRateSelection, final boolean includeSpatial, final List<MediaLink.Type> linkTypes) throws PlaybackException {
        ArrayList arrayList = new ArrayList();
        PlaybackAudioQuality playbackAudioQuality = PlaybackAudioQuality.UNKNOWN;
        Iterator<MediaLink.Type> it = linkTypes.iterator();
        while (it.hasNext()) {
            MediaLinks mediaLinksInternal = getMediaLinksInternal(mediaItem, bitRateSelection, includeSpatial, it.next());
            if (mediaLinksInternal != null) {
                arrayList.addAll(mediaLinksInternal.getLinks());
                PlaybackAudioQuality playbackAudioQuality2 = mediaLinksInternal.getBestAvailablePlaybackAttributes().getPlaybackAudioQuality();
                if (playbackAudioQuality2.compareTo(playbackAudioQuality) > 0) {
                    playbackAudioQuality = playbackAudioQuality2;
                }
            }
        }
        return new MediaLinks(arrayList, new PlaybackAttributes(playbackAudioQuality, -1, -1, -1));
    }

    private MediaLinks getMediaLinksInternal(final MediaItem mediaItem, final BitRateSelection bitRateSelection, final boolean includeSpatial, final MediaLink.Type linkType) throws PlaybackException {
        if (MediaItemHelper.isInterludeMediaItem(mediaItem)) {
            Log.debug(TAG, "ASDF: getInterludeMediaLinks() - retrieving by URL scheme");
            return getInterludeMediaLinks(mediaItem, bitRateSelection);
        }
        String str = TAG;
        Log.debug(str, "ASDF: getMediaLinks() - retrieving by ASIN scheme");
        MediaLinks mediaLinkByType = getMediaLinkByType(mediaItem, IdentifierType.ASIN, MediaItemId.Type.ASIN, bitRateSelection, includeSpatial, linkType);
        if (mediaLinkByType == null) {
            Log.debug(str, "ASDF: getMediaLinks() - retrieving by OWNED scheme");
            mediaLinkByType = getMediaLinkByType(mediaItem, IdentifierType.COID, MediaItemId.Type.OWNED, bitRateSelection, includeSpatial, linkType);
        }
        if (mediaLinkByType == null) {
            Log.debug(str, "ASDF: getMediaLinks() - retrieving by LUID scheme");
            mediaLinkByType = getMediaLinkByType(mediaItem, IdentifierType.COID, MediaItemId.Type.LUID, bitRateSelection, includeSpatial, linkType);
        }
        if (mediaLinkByType == null) {
            Log.debug(str, "ASDF: getMediaLinks() - retrieving by LOCAL scheme");
            mediaLinkByType = getMediaLinkByType(mediaItem, IdentifierType.COID, MediaItemId.Type.LOCAL_URI, bitRateSelection, includeSpatial, linkType);
        }
        if (mediaLinkByType != null) {
            return mediaLinkByType;
        }
        throw new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).description("Problem retrieving streaming url from DMLS.").mediaItem(mediaItem).autoRetry(true));
    }

    private MediaLinks getMediaLinkByType(final MediaItem mediaItem, final IdentifierType dmlsIdType, final MediaItemId.Type mediaItemIdType, final BitRateSelection bitRateSelection, final boolean includeSpatial, final MediaLink.Type linkType) {
        ContentResponse contentResponseRetrieveGetStreamUrlResponse;
        PlaybackAudioQuality playbackAudioQuality;
        try {
            String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, mediaItemIdType, null);
            if (mediaItemIdString == null) {
                Log.debug(TAG, "ASDF: getMediaLinks() - null id");
                return null;
            }
            ContentId contentId = new ContentId(dmlsIdType, mediaItemIdString);
            DMLSApi dMLSApi = DMLSApiProvider.get();
            if (MediaLink.Type.DASH.equals(linkType)) {
                contentResponseRetrieveGetStreamUrlResponse = dMLSApi.retrieveStreamingURLsWithFirstChunkV2(Arrays.asList(contentId), bitRateSelection, includeSpatial).get(0);
            } else {
                contentResponseRetrieveGetStreamUrlResponse = dMLSApi.retrieveGetStreamUrlResponse(contentId);
            }
            if (contentResponseRetrieveGetStreamUrlResponse == null) {
                Log.debug(TAG, "ASDF: getMediaLinks() - null dmlsResponse " + mediaItemIdString);
                return null;
            }
            List<String> urlList = contentResponseRetrieveGetStreamUrlResponse.getUrlList();
            ArrayList arrayList = new ArrayList();
            List<ContentResponse.AssetQuality> assetQualities = contentResponseRetrieveGetStreamUrlResponse.getAssetQualities();
            try {
                if (urlList != null && urlList.size() > 0) {
                    for (String str : urlList) {
                        MediaLink mediaLink = new MediaLink(linkType, bitRateSelection, Uri.parse(str), (Clock.now() + contentResponseRetrieveGetStreamUrlResponse.getExpiration()) - System.currentTimeMillis());
                        String str2 = TAG;
                        Log.debug(str2, "ASDF: url: " + str);
                        Log.debug(str2, "ASDF: times: " + Clock.now() + " " + contentResponseRetrieveGetStreamUrlResponse.getExpiration() + " " + System.currentTimeMillis());
                        arrayList.add(mediaLink);
                    }
                    int rank = Integer.MIN_VALUE;
                    PlaybackAudioQuality playbackAudioQuality2 = PlaybackAudioQuality.UNKNOWN;
                    if (assetQualities != null) {
                        for (ContentResponse.AssetQuality assetQuality : assetQualities) {
                            Log.debug(TAG, "assetQuality: " + assetQuality);
                            if (assetQuality.getRank() > rank && (playbackAudioQuality = getPlaybackAudioQuality(assetQuality.getQuality())) != PlaybackAudioQuality.UNKNOWN) {
                                rank = assetQuality.getRank();
                                playbackAudioQuality2 = playbackAudioQuality;
                            }
                        }
                    }
                    Log.debug(TAG, "best audioQuality: " + playbackAudioQuality2);
                    return new MediaLinks(arrayList, new PlaybackAttributes(playbackAudioQuality2, -1, -1, -1));
                }
                Log.debug(TAG, "ASDF: getMediaLinks() - null or empty urls " + mediaItemIdString);
                return null;
            } catch (Exception e) {
                e = e;
                Log.info(TAG, "ASDF: getMediaLinks() - error ", e);
                return null;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private PlaybackAudioQuality getPlaybackAudioQuality(final String assetQuality) {
        assetQuality.hashCode();
        switch (assetQuality) {
            case "THREE_D":
            case "HI_RES":
                return PlaybackAudioQuality.UHD192;
            case "CD":
                return PlaybackAudioQuality.HD44;
            case "MP3":
                return PlaybackAudioQuality.HIGH;
            default:
                return PlaybackAudioQuality.UNKNOWN;
        }
    }

    private MediaLinks getInterludeMediaLinks(MediaItem mediaItem, BitRateSelection bitRateSelection) {
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.URL, null);
        if (mediaItemIdString == null) {
            return MediaLinks.EMPTY;
        }
        Uri uri = Uri.parse(mediaItemIdString);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MediaLink(MediaLink.Type.DASH, bitRateSelection, uri, Long.MAX_VALUE));
        return new MediaLinks(arrayList, new PlaybackAttributes(PlaybackAudioQuality.UNKNOWN, -1, -1, -1));
    }
}
