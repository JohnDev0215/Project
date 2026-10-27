package com.amazon.mp3.casting;

import android.content.Context;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.GetTrackFromMpqsResponseTask;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.metrics.mts.event.types.ContentSubscriptionMode;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class GetTrackFromMetadataThingShadowTask extends GetTrackFromMpqsResponseTask {
    private static final String TAG = GetTrackFromMetadataThingShadowTask.class.getSimpleName();
    private final GetTrackFromMetadataThingShadowHandler mGetTrackFromMetadataThingShadowHandler;
    private final MetadataThingShadow.Track mMetadataThingShadowTrack;

    public interface GetTrackFromMetadataThingShadowHandler {
        void onCompleted(final MetadataThingShadow.Track metadataThingShadowTrack, final MusicTrack track);
    }

    public GetTrackFromMetadataThingShadowTask(final Context context, final MetadataThingShadow.Track metadataThingShadowTrack, final GetTrackFromMetadataThingShadowHandler getTrackFromMetadataThingShadowHandler) {
        super(context, null, null, null);
        this.mMetadataThingShadowTrack = metadataThingShadowTrack;
        this.mGetTrackFromMetadataThingShadowHandler = getTrackFromMetadataThingShadowHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.voice.GetTrackFromMpqsResponseTask, android.os.AsyncTask
    public MusicTrack doInBackground(final Void... voids) {
        MusicTrack trackFromResponse;
        if (this.mMetadataThingShadowTrack == null || (trackFromResponse = getTrackFromResponse()) == null) {
            return null;
        }
        CastingTrack castingTrack = new CastingTrack(trackFromResponse);
        castingTrack.setMpqsTrackId(this.mMetadataThingShadowTrack.trackId);
        castingTrack.setCanRateTrack(this.mMetadataThingShadowTrack.canRateTrack.booleanValue());
        String str = this.mMetadataThingShadowTrack.rating;
        if (str != null) {
            castingTrack.setRating(getTrackRating(str));
        }
        MetadataThingShadow.Track.QueueMetadata queueMetadata = this.mMetadataThingShadowTrack.queueMetadata;
        if (queueMetadata != null) {
            castingTrack.setQueueId(queueMetadata.queueId);
        }
        Log.debug(TAG, "Casting track: " + castingTrack);
        return castingTrack;
    }

    @Override // com.amazon.mp3.voice.GetTrackFromMpqsResponseTask
    protected MusicTrack getTrackFromResponse() {
        String identifierType = getIdentifierType();
        String identifier = getIdentifier();
        String serviceTier = getServiceTier();
        if ("MEDIA_CONTENT_ID".equals(identifierType)) {
            Log.error(TAG, "Unsupported identifier type returned from getTrackFromResponse: " + identifierType + ". Casting will disconnect");
            CastingSessionManager.getInstance().disconnect(TerminationReason.UNSUPPORTED_CONTENT);
            return null;
        }
        if ("STORE".equals(serviceTier)) {
            Log.error(TAG, "Unsupported serviceTier returned from getTrackFromResponse: " + serviceTier + ". Casting will disconnect");
            CastingSessionManager.getInstance().disconnect(TerminationReason.UNSUPPORTED_CONTENT);
            return null;
        }
        if ("OBJECT_ID".equals(identifierType)) {
            return getTrackFromObjectId(identifier);
        }
        if ("ASIN".equals(identifierType)) {
            return new MetadataThingShadowTrackRequester(this.mContext, this.mMetadataThingShadowTrack).getTrackFromAsin(identifier);
        }
        Log.warning(TAG, "Create music track from Thing Shadow for Unknown identifier type returned from getTrackFromResponse: " + identifierType);
        return getMusicTrackFromMetadataThingShadow(this.mMetadataThingShadowTrack);
    }

    @Override // com.amazon.mp3.voice.GetTrackFromMpqsResponseTask
    protected String getIdentifierType() {
        return this.mMetadataThingShadowTrack.identifierType;
    }

    @Override // com.amazon.mp3.voice.GetTrackFromMpqsResponseTask
    protected String getIdentifier() {
        return this.mMetadataThingShadowTrack.identifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.voice.GetTrackFromMpqsResponseTask, android.os.AsyncTask
    public void onPostExecute(final MusicTrack track) {
        this.mGetTrackFromMetadataThingShadowHandler.onCompleted(this.mMetadataThingShadowTrack, track);
    }

    private String getServiceTier() {
        return this.mMetadataThingShadowTrack.serviceTier;
    }

    private MusicTrack getMusicTrackFromMetadataThingShadow(final MetadataThingShadow.Track track) {
        ContentCatalogStatus contentCatalogStatus;
        MetadataThingShadow.Track.Artist[] artistArr = track.artists;
        String str = (artistArr == null || artistArr.length <= 0) ? "" : artistArr[0].asin;
        HashMap<String, String> map = track.artUrlMap;
        String str2 = map != null ? map.get("FULL") : "";
        MusicTrack musicTrack = new MusicTrack();
        musicTrack.setTitle(track.name);
        musicTrack.setId(track.trackId);
        musicTrack.setAlbumArtUrl(str2);
        musicTrack.setArtistName(track.artistName);
        musicTrack.setArtistAsin(str);
        musicTrack.setAlbumName(track.albumName);
        musicTrack.setAlbumAsin(track.albumAsin);
        musicTrack.setAlbumArtistName(track.albumArtistName);
        musicTrack.setOwnershipStatus(ContentOwnershipStatus.UNKNOWN);
        if (ContentSubscriptionMode.PRIME.getAttribute().equals(track.serviceTier)) {
            contentCatalogStatus = ContentCatalogStatus.PRIME;
        } else {
            contentCatalogStatus = ContentSubscriptionMode.UNLIMITED.getAttribute().equals(track.serviceTier) ? ContentCatalogStatus.HAWKFIRE : ContentCatalogStatus.UNKNOWN;
        }
        musicTrack.setContentCatalogStatus(contentCatalogStatus);
        if (track.durationInMs != null) {
            musicTrack.setDuration((int) TimeUnit.MILLISECONDS.toSeconds(r6.longValue()));
        }
        return musicTrack;
    }
}
