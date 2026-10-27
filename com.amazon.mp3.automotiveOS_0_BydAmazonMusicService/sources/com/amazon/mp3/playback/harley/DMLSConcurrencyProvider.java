package com.amazon.mp3.playback.harley;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.DMLSApi;
import com.amazon.mp3.net.dmls.DMLSApiProvider;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.DMLSStreamingStatusResponse;
import com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.net.dmls.StatusCode;
import com.amazon.mp3.net.dmls.StreamingStatus;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.concurrency.ConcurrentDeviceInfo;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class DMLSConcurrencyProvider implements ConcurrencyProvider {
    private static final long MANY_MILLISECONDS;
    private static final long MANY_SECONDS;
    private static final String TAG = "DMLSConcurrencyProvider";
    private final PlaybackConfig mPlaybackConfig;

    @Override // com.amazon.music.media.playback.concurrency.ConcurrencyProvider
    public boolean shouldUpdateInBackground() {
        return true;
    }

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        MANY_SECONDS = timeUnit.toSeconds(365L);
        MANY_MILLISECONDS = timeUnit.toMillis(365L);
    }

    public DMLSConcurrencyProvider(final PlaybackConfig playbackConfig) {
        this.mPlaybackConfig = (PlaybackConfig) Validate.notNull(playbackConfig);
    }

    @Override // com.amazon.music.media.playback.concurrency.ConcurrencyProvider
    public List<ConcurrentDeviceInfo> updatePlayStatus(final int status, final MediaItem item, final long positionMillis, final String terminateDeviceName) throws IOException {
        StreamingStatus streamingStatus;
        IStreamingConcurrencyStatusResponse iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus;
        ContentId contentId = getContentId(item);
        DMLSApi dMLSApi = DMLSApiProvider.get();
        if (dMLSApi != null && contentId != null) {
            if (status == 0) {
                streamingStatus = StreamingStatus.PLAY;
            } else if (status == 1) {
                streamingStatus = StreamingStatus.PAUSE;
            } else if (status == 2) {
                streamingStatus = StreamingStatus.STOP;
            } else {
                streamingStatus = StreamingStatus.UNDEFINED;
            }
            long durationMillis = item != null ? item.getDurationMillis() : 0L;
            try {
                if (terminateDeviceName == null) {
                    iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus = dMLSApi.updateStreamingStatus(contentId, streamingStatus, durationMillis, positionMillis);
                } else {
                    iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus = dMLSApi.terminateAndUpdateStreamingStatus(contentId, streamingStatus, durationMillis, positionMillis, terminateDeviceName);
                }
                updateGhostListeningThreshold(iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus);
                if (iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus.getStatusCode() != StatusCode.MAX_CONCURRENCY_REACHED) {
                    return null;
                }
                return getDeviceList(iStreamingConcurrencyStatusResponseTerminateAndUpdateStreamingStatus);
            } catch (AbstractHttpClient.HttpClientException | DMLSExceptions.DMLSException | JSONException unused) {
                return null;
            } catch (DMLSExceptions.StatusCodeException e) {
                Log.warning(TAG, "Caught StatusCodeException in updateStreamingStatus: " + e.toString());
                if (e.getStatus() == StatusCode.MAX_CONCURRENCY_REACHED) {
                    return Collections.singletonList(new ConcurrentDeviceInfo(null, 0L));
                }
            }
        }
        return null;
    }

    private void updateGhostListeningThreshold(final IStreamingConcurrencyStatusResponse response) {
        long millis;
        if (this.mPlaybackConfig.isUsingGhostListeningThresholdFromServer()) {
            long inactivityThreshold = response.getInactivityThreshold();
            if (inactivityThreshold > 0 && inactivityThreshold < MANY_SECONDS) {
                millis = TimeUnit.SECONDS.toMillis(inactivityThreshold);
            } else {
                millis = MANY_MILLISECONDS;
            }
            this.mPlaybackConfig.setGhostListeningTimeoutMillis(millis);
        }
    }

    private List<ConcurrentDeviceInfo> getDeviceList(final IStreamingConcurrencyStatusResponse response) {
        ArrayList arrayList = new ArrayList();
        for (DMLSStreamingStatusResponse dMLSStreamingStatusResponse : response.getStreamingResponseList()) {
            if (!StringUtils.isEmpty(dMLSStreamingStatusResponse.getRemoteDeviceName())) {
                arrayList.add(new ConcurrentDeviceInfo(dMLSStreamingStatusResponse.getRemoteDeviceName(), System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(dMLSStreamingStatusResponse.getElapsedTimeSeconds())));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new ConcurrentDeviceInfo(null, 0L));
        }
        return arrayList;
    }

    private ContentId getContentId(final MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
        IdentifierType identifierType = IdentifierType.ASIN;
        if (mediaItemIdString == null) {
            mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.OWNED, null);
            identifierType = IdentifierType.COID;
        }
        if (mediaItemIdString == null) {
            mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LUID, null);
            identifierType = IdentifierType.COID;
        }
        if (mediaItemIdString == null) {
            Log.error(TAG, "getContentId() was not able to retrieve an MediaItemId for the given MediaItem.");
            return null;
        }
        try {
            return new ContentId(identifierType, mediaItemIdString);
        } catch (DMLSExceptions.DMLSException unused) {
            return null;
        }
    }
}
