package com.amazon.mp3.playback.casting;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingAlertDialogActivity;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueLibraryTrackMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.StormTrackMediaItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueue;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackEventManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingQueueCallback;
import com.amazon.music.casting.session.CastingQueueResult;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.StationMediaItem;
import com.amazon.music.metrics.mts.event.definition.playback.TrackPlaybackInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControls;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazon.musicplayqueueservice.client.common.casting.StartAt;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public class CastingQueueHandler {
    private static final String TAG = "CastingQueueHandler";
    private final Context mContext;
    private ExecutorService mExecutorService;
    private Map<String, Map<String, String>> mMetricsContext;
    private final PlaybackController mPlaybackController;
    private boolean mShuffled;
    private long mStartPositionMillis;
    private List<Track> mTrackList;
    private int mTrackPosition;
    private boolean mWaitingForResult;

    private enum PlaybackPreference {
        STEREO,
        IMMERSIVE
    }

    private class QueueCallback implements CastingQueueCallback {
        private final CastingQueueCallback mCallback;

        private QueueCallback(final CastingQueueCallback callback) {
            this.mCallback = callback;
        }

        @Override // com.amazon.music.casting.session.CastingQueueCallback
        public void onQueueUpdated(final CastingQueueResult result) {
            Log.debug(CastingQueueHandler.TAG, "onQueueUpdated " + result);
            CastingQueueHandler.this.mWaitingForResult = false;
            if (result == null) {
                Log.error(CastingQueueHandler.TAG, "Error onQueueUpdated returned null result, remote playback may not work");
            } else {
                int i = AnonymousClass2.$SwitchMap$com$amazon$music$casting$session$CastingQueueResult[result.ordinal()];
                if (i == 1) {
                    CastingQueueHandler.saveQueueId(CastingQueueHandler.this.mContext, result.getQueueId());
                    Log.debug(CastingQueueHandler.TAG, "Casting queue updated successfully, playback should now start remotely");
                } else if (i == 2) {
                    CastingQueueHandler.this.mContext.startActivity(CastingAlertDialogActivity.getCastingAlertIntentForOfflineDevice(CastingQueueHandler.this.mContext));
                    CastingSessionManager.getInstance().disconnect(TerminationReason.NETWORK_ERROR);
                    Log.debug(CastingQueueHandler.TAG, "Casting queue update failed, disconnecting from session");
                } else if (i == 3) {
                    CastingQueueHandler.this.mContext.startActivity(CastingAlertDialogActivity.getCastingAlertIntentForUnknownError(CastingQueueHandler.this.mContext));
                    if (!result.getIsCreateQueueResult()) {
                        Log.debug(CastingQueueHandler.TAG, "Casting queue update failed with unknown failure");
                    } else {
                        CastingSessionManager.getInstance().disconnect(TerminationReason.CREATE_QUEUE_ERROR);
                        Log.debug(CastingQueueHandler.TAG, "Casting create queue update failed with unknown failure, disconnecting from session");
                    }
                }
            }
            CastingQueueCallback castingQueueCallback = this.mCallback;
            if (castingQueueCallback != null) {
                castingQueueCallback.onQueueUpdated(result);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.casting.CastingQueueHandler$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$session$CastingQueueResult;

        static {
            int[] iArr = new int[CastingQueueResult.values().length];
            $SwitchMap$com$amazon$music$casting$session$CastingQueueResult = iArr;
            try {
                iArr[CastingQueueResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$CastingQueueResult[CastingQueueResult.DEVICE_OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$CastingQueueResult[CastingQueueResult.UNKNOWN_FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    CastingQueueHandler(final ExecutorService executorService) {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mPlaybackController = controller;
        this.mTrackList = new ArrayList();
        this.mMetricsContext = new HashMap();
        this.mContext = controller.getPlayback().getPlaybackConfig().getContext();
        this.mExecutorService = executorService;
    }

    public void transferQueue(final String queueId, final MediaItem mediaItem, final long positionMillis, final CastingQueueCallback callback, @Nullable final CastingLatencyCallback latencyCallback) {
        this.mWaitingForResult = true;
        this.mExecutorService.submit(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingQueueHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$transferQueue$0(latencyCallback, queueId, mediaItem, positionMillis, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$transferQueue$0(CastingLatencyCallback castingLatencyCallback, String str, MediaItem mediaItem, long j, CastingQueueCallback castingQueueCallback) {
        if (castingLatencyCallback != null) {
            castingLatencyCallback.trackCastingTransferQueue(LatencyTrackingLeg.RENDERING);
        }
        TransferQueueRequest transferQueueRequest = getTransferQueueRequest(str, mediaItem, j);
        dumpRequest(transferQueueRequest);
        if (transferQueueRequest != null) {
            CastingSessionManager.getInstance().updateQueue(transferQueueRequest, new QueueCallback(castingQueueCallback));
        } else {
            handleUnsupportedContent(true);
        }
    }

    public void startPlayQueue(final Sequencer sequencer, final long positionMillis, final boolean shuffled, final CastingQueueCallback callback) {
        this.mWaitingForResult = true;
        this.mExecutorService.submit(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingQueueHandler.1
            @Override // java.lang.Runnable
            public void run() {
                CreateQueueRequest createQueueRequest = CastingQueueHandler.this.getCreateQueueRequest(sequencer, positionMillis, shuffled);
                CastingQueueHandler.this.dumpRequest(createQueueRequest);
                if (createQueueRequest != null) {
                    CastingSessionManager.getInstance().updateQueue(createQueueRequest, new QueueCallback(callback));
                } else {
                    CastingQueueHandler.this.handleUnsupportedContent(true);
                }
            }
        });
    }

    public static void saveQueueId(final Context context, final String queueId) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_last_casting_collection_queue_id), queueId);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CreateQueueRequest getCreateQueueRequest(final Sequencer sequencer, final long positionMillis, final boolean shuffled) {
        List<Track> list;
        Map<String, Map<String, String>> map;
        CreateQueueRequest createQueueRequest = new CreateQueueRequest();
        String targetId = getTargetId();
        this.mStartPositionMillis = positionMillis;
        this.mShuffled = shuffled;
        createTrackListAndMetricsContext(sequencer);
        if (TextUtils.isEmpty(targetId) || (list = this.mTrackList) == null || list.isEmpty() || (map = this.mMetricsContext) == null || map.isEmpty()) {
            Log.error(TAG, "Cannot create createQueue request with targetId = " + targetId + " mTrackList = " + this.mTrackList + " mMetricsContext = " + this.mMetricsContext);
            return null;
        }
        StartAt startAt = new StartAt();
        startAt.setTrackIdentifier(String.valueOf(this.mTrackPosition));
        startAt.setTrackIdentifierType("INDEX");
        startAt.setPositionInMs((int) this.mStartPositionMillis);
        createQueueRequest.setPlaybackPreference(getPlaybackPreference());
        createQueueRequest.setIdentifierType("TRACK_LIST");
        createQueueRequest.setMetricsContexts(this.mMetricsContext);
        createQueueRequest.setIdentifiers(this.mTrackList);
        createQueueRequest.setStartAt(startAt);
        createQueueRequest.setTargetId(targetId);
        return createQueueRequest;
    }

    private StartAt createStartAt(final MediaItem mediaItem, final long positionMillis) {
        if (mediaItem == null) {
            return null;
        }
        StartAt startAt = new StartAt();
        startAt.setPositionInMs((int) positionMillis);
        if (mediaItem instanceof StormTrackMediaItem) {
            String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LUID, null);
            if (mediaItemIdString != null) {
                startAt.setTrackIdentifier(mediaItemIdString);
                startAt.setTrackIdentifierType("ENTITY_REFERENCE_ID");
                return startAt;
            }
            Log.error(TAG, "createStartAt: unable to get identifier from storm media item " + mediaItem);
            return null;
        }
        if (mediaItem instanceof InterludeMediaItem) {
            MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
            if (sourceMediaCollectionInfo != null) {
                MediaCollectionId.Type type = MediaCollectionId.Type.CUSTOM;
                if (sourceMediaCollectionInfo.getId(type) != null) {
                    startAt.setTrackIdentifier(sourceMediaCollectionInfo.getId(type).getId());
                    startAt.setTrackIdentifierType("ENTITY_REFERENCE_ID");
                    Log.debug(TAG, "startAt- id: " + startAt.getTrackIdentifier() + " identifierType: " + startAt.getTrackIdentifierType());
                    return startAt;
                }
            }
            Log.error(TAG, "createStartAt: unable to get identifier from media interlude media item " + mediaItem);
            return null;
        }
        if (mediaItem instanceof CloudQueueLibraryTrackMediaItem) {
            String mediaItemIdString2 = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.OWNED, null);
            if (mediaItemIdString2 != null) {
                startAt.setTrackIdentifier(mediaItemIdString2);
                startAt.setTrackIdentifierType("OBJECT_ID");
                return startAt;
            }
            Log.error(TAG, "createStartAt: unable to get identifier from CloudQueueLibraryTrack mediaItem item " + mediaItem);
            return null;
        }
        String mediaItemIdString3 = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.MPQS, null);
        if (mediaItemIdString3 != null) {
            startAt.setTrackIdentifier(mediaItemIdString3);
            startAt.setTrackIdentifierType("TRACK_ID");
            return startAt;
        }
        if (MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.INTERLUDE, null) == null || !(mediaItem instanceof StationMediaItem)) {
            return null;
        }
        startAt.setTrackIdentifier(((StationMediaItem) mediaItem).getTrackItem().getTrackId());
        startAt.setTrackIdentifierType("TRACK_ID");
        return startAt;
    }

    private TransferQueueRequest getTransferQueueRequest(final String stationId, final MediaItem mediaItem, final long positionMillis) {
        TransferQueueRequest transferQueueRequest = new TransferQueueRequest();
        String targetId = getTargetId();
        if (TextUtils.isEmpty(targetId) || TextUtils.isEmpty(stationId)) {
            Log.error(TAG, "Cannot create transferQueue request with targetId = " + targetId + " and stationId = " + stationId);
            return null;
        }
        transferQueueRequest.setPlaybackPreference(getPlaybackPreference());
        transferQueueRequest.setStartAt(createStartAt(mediaItem, positionMillis));
        transferQueueRequest.setQueueId(stationId);
        transferQueueRequest.setTargetId(targetId);
        transferQueueRequest.setAllowedParentalControls(AllowedParentalControls.builder().withHasExplicitLanguage(Boolean.valueOf(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled())).build());
        return transferQueueRequest;
    }

    private String getTargetId() {
        CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
        return castingDevice != null ? castingDevice.getTargetId() : "";
    }

    private boolean matches(final MediaItem mediaItem, final MusicTrack track) {
        if (mediaItem != null && track != null) {
            if (!TextUtils.isEmpty(track.getAsin())) {
                if (track.getAsin().equals(MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, ""))) {
                    return true;
                }
            }
            if (!TextUtils.isEmpty(track.getLuid())) {
                if (track.getLuid().equals(MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LUID, ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createTrackListAndMetricsContext(final Sequencer sequencer) {
        int i;
        MusicTrack track;
        int i2;
        String str;
        List<MediaItem> list;
        String str2;
        CloudQueueSequencer cloudQueueSequencer;
        boolean zEquals;
        this.mTrackList.clear();
        String str3 = " tracks, leaving ";
        int i3 = 500;
        if (sequencer instanceof CloudQueueSequencer) {
            CloudQueueSequencer cloudQueueSequencer2 = (CloudQueueSequencer) sequencer;
            List<MediaItem> queue = cloudQueueSequencer2.getQueue();
            int size = queue.size();
            i = size >= 500 ? 0 : 1;
            MediaItem currentMediaItem = sequencer.getCurrentMediaItem();
            String asin = MediaItemHelper.getAsin(currentMediaItem);
            String onlineCirrusLuid = getOnlineCirrusLuid(currentMediaItem);
            this.mTrackPosition = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < size) {
                Track track2 = new Track();
                MediaItem mediaItem = queue.get(i4);
                if (MediaItemHelper.isSideLoadedMediaItem(mediaItem)) {
                    String str4 = TAG;
                    list = queue;
                    StringBuilder sb = new StringBuilder();
                    str = str3;
                    sb.append("skip side-loaded track: ");
                    sb.append(mediaItem.getName());
                    Log.debug(str4, sb.toString());
                    i5++;
                    str2 = onlineCirrusLuid;
                    cloudQueueSequencer = cloudQueueSequencer2;
                } else {
                    str = str3;
                    list = queue;
                    String asin2 = MediaItemHelper.getAsin(mediaItem);
                    String onlineCirrusLuid2 = getOnlineCirrusLuid(mediaItem);
                    if (mediaItem.isOwned() && isValid(onlineCirrusLuid2)) {
                        track2.setIdentifierType("OBJECT_ID");
                        track2.setIdentifier(onlineCirrusLuid2);
                        zEquals = onlineCirrusLuid2.equals(onlineCirrusLuid);
                    } else if (PrimePlaylistUtil.isAsin(asin2)) {
                        track2.setIdentifierType("ASIN");
                        track2.setIdentifier(asin2);
                        zEquals = asin2.equals(asin);
                    } else {
                        str2 = onlineCirrusLuid;
                        cloudQueueSequencer = cloudQueueSequencer2;
                        Log.debug(TAG, "skip track: " + mediaItem.getName());
                        i5++;
                    }
                    if (!this.mShuffled || i == 0) {
                        str2 = onlineCirrusLuid;
                        cloudQueueSequencer = cloudQueueSequencer2;
                    } else {
                        int shuffledIndex = cloudQueueSequencer2.getShuffledIndex(i4);
                        if (shuffledIndex >= 0) {
                            track2.setShuffledIndex(Integer.valueOf(shuffledIndex));
                        }
                        String str5 = TAG;
                        str2 = onlineCirrusLuid;
                        StringBuilder sb2 = new StringBuilder();
                        cloudQueueSequencer = cloudQueueSequencer2;
                        sb2.append("addTrack - index:");
                        sb2.append(i4);
                        sb2.append(" shuffledIndex:");
                        sb2.append(shuffledIndex);
                        Log.debug(str5, sb2.toString());
                    }
                    if (zEquals && this.mTrackPosition == -1) {
                        if (track2.getShuffledIndex() != null && track2.getShuffledIndex().intValue() >= 0) {
                            this.mTrackPosition = track2.getShuffledIndex().intValue();
                        } else {
                            this.mTrackPosition = i4 - i5;
                        }
                    }
                    HashMap map = new HashMap();
                    map.put("isShufflePlay", String.valueOf(this.mShuffled));
                    String strValueOf = String.valueOf(i4);
                    this.mMetricsContext.put(strValueOf, map);
                    track2.setMetricsContextIdentifier(strValueOf);
                    this.mTrackList.add(track2);
                }
                i4++;
                onlineCirrusLuid = str2;
                queue = list;
                str3 = str;
                cloudQueueSequencer2 = cloudQueueSequencer;
            }
            String str6 = str3;
            int i6 = this.mTrackPosition;
            if (i6 == -1 || i6 >= this.mTrackList.size()) {
                this.mTrackPosition = 0;
                this.mStartPositionMillis = 0L;
            }
            Log.debug(TAG, "skipped " + i5 + " of " + size + str6 + this.mTrackList.size());
            return;
        }
        MediaItem currentMediaItem2 = sequencer.getCurrentMediaItem();
        Cursor cursorQuery = PlayQueue.getInstance().query(null, "udo");
        if (cursorQuery == null) {
            Log.error(TAG, "Error, null cursor retrieved from querying play queue");
            return;
        }
        int count = cursorQuery.getCount();
        if (this.mShuffled) {
            if (count >= 500) {
                cursorQuery = PlayQueue.getInstance().query(null, "shuffle");
            }
            this.mTrackPosition = PlayQueueSequencer.getInstance().getActualIndex();
        } else {
            this.mTrackPosition = -1;
        }
        if (cursorQuery == null) {
            Log.error(TAG, "Error, null cursor retrieved from querying play queue");
            return;
        }
        cursorQuery.moveToFirst();
        int i7 = 0;
        int i8 = 0;
        while (!cursorQuery.isAfterLast() && this.mTrackList.size() < i3) {
            int i9 = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM));
            if (i9 == i) {
                i = i8;
                track = AmazonApplication.getLibraryItemFactory().getTrack("cirrus", cursorQuery.getLong(cursorQuery.getColumnIndex("match_hash")));
            } else {
                i = i8;
                track = null;
            }
            if (track == null || i9 == 0) {
                track = AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery);
            }
            if (matches(currentMediaItem2, track)) {
                if (this.mTrackPosition < 0) {
                    this.mTrackPosition = this.mTrackList.size();
                }
                if (!track.isAvailable()) {
                    this.mStartPositionMillis = 0L;
                }
            }
            if (track == null || !track.isAvailable() || (TextUtils.isEmpty(track.getAsin()) && !isValid(track.getLuid()))) {
                i2 = 500;
                i7++;
            } else {
                Track track3 = new Track();
                String asin3 = track.getAsin();
                String luid = track.getLuid();
                if (isValid(luid)) {
                    track3.setIdentifierType("OBJECT_ID");
                    track3.setIdentifier(luid);
                } else {
                    track3.setIdentifierType("ASIN");
                    track3.setIdentifier(asin3);
                }
                if (this.mShuffled) {
                    i2 = 500;
                    if (count < 500) {
                        track3.setShuffledIndex(Integer.valueOf(Integer.parseInt(cursorQuery.getString(cursorQuery.getColumnIndex("shuffle")))));
                    }
                } else {
                    i2 = 500;
                }
                Map<String, String> mapCreateTrackMetricsContext = createTrackMetricsContext(track);
                String strValueOf2 = String.valueOf(i);
                this.mMetricsContext.put(strValueOf2, mapCreateTrackMetricsContext);
                track3.setMetricsContextIdentifier(strValueOf2);
                this.mTrackList.add(track3);
                Log.debug(TAG, "addTrack - shuffledIndex: " + track3.getShuffledIndex());
            }
            i8 = i + 1;
            cursorQuery.moveToPosition(i8);
            i3 = i2;
            i = 1;
        }
        cursorQuery.close();
        int i10 = this.mTrackPosition;
        if (i10 < 0 || i10 >= this.mTrackList.size()) {
            this.mTrackPosition = 0;
            this.mStartPositionMillis = 0L;
        }
        Log.debug(TAG, "skipped " + i7 + " of " + count + " tracks, leaving " + this.mTrackList.size());
    }

    private String getCollectionName() {
        MediaCollectionInfo mediaCollectionInfo = this.mPlaybackController.getMediaCollectionInfo();
        return mediaCollectionInfo == null ? "" : mediaCollectionInfo.getName();
    }

    private Map<String, String> createTrackMetricsContext(final MusicTrack track) {
        HashMap map = new HashMap();
        SelectionSourceInfo selectionSourceInfo = SelectionSourceHelper.getSelectionSourceInfo(MediaItemHelper.getCurrentUri(), track, getCollectionName());
        TrackPlaybackInfo trackPlaybackInfoCreatePlaybackInfo = PlaybackEventManager.createPlaybackInfo(track);
        String contentSubscriptionMode = trackPlaybackInfoCreatePlaybackInfo.getContentSubscriptionMode();
        String selectionSourceId = selectionSourceInfo.getSelectionSourceId();
        String selectionSourceSessionId = selectionSourceInfo.getSelectionSourceSessionId();
        SelectionSourceType selectionSourceType = selectionSourceInfo.getSelectionSourceType();
        if (selectionSourceId != null) {
            map.put("selectionSourceId", selectionSourceId);
        }
        if (selectionSourceSessionId != null) {
            map.put("selectionSourceSessionId", selectionSourceSessionId);
        }
        if (selectionSourceType != null) {
            map.put("selectionSourceType", selectionSourceType.getMetricsValue());
        }
        map.put("contentSubscriptionMode", contentSubscriptionMode);
        map.put("isShufflePlay", String.valueOf(this.mShuffled));
        map.put("asin", trackPlaybackInfoCreatePlaybackInfo.getAsin());
        map.put("cdObjectId", trackPlaybackInfoCreatePlaybackInfo.getCloudDriveObjectId());
        return map;
    }

    public void handleUnsupportedContent(boolean disconnect) {
        this.mWaitingForResult = false;
        this.mContext.startActivity(CastingAlertDialogActivity.getCastingAlertIntentForUnsupportedContent(this.mContext));
        Log.debug(TAG, "Unsupported content, showing dialog");
        if (disconnect) {
            CastingSessionManager.getInstance().disconnect(TerminationReason.UNSUPPORTED_CONTENT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpRequest(final CreateQueueRequest request) {
        try {
            String str = TAG;
            Log.debug(str, "--- createQueue request ---");
            Log.debug(str, "request.getTargetId() = " + request.getTargetId());
            Log.debug(str, "request.getIdentifierType() = " + request.getIdentifierType());
            Log.debug(str, "request.getIdentifiers().size() = " + request.getIdentifiers().size());
            Log.debug(str, "starting track identifierType = " + request.getIdentifiers().get(this.mTrackPosition).getIdentifierType());
            Log.debug(str, "starting track identifier = " + request.getIdentifiers().get(this.mTrackPosition).getIdentifier());
            Log.debug(str, "startAt.getTrackIdentifier() = " + request.getStartAt().getTrackIdentifier());
            Log.debug(str, "startAt.getTrackIdentifierType() = " + request.getStartAt().getTrackIdentifierType());
            Log.debug(str, "startAt.getPositionInMs() = " + request.getStartAt().getPositionInMs());
            Log.debug(str, "---------------------------");
        } catch (NullPointerException unused) {
        }
    }

    private void dumpRequest(final TransferQueueRequest request) {
        try {
            String str = TAG;
            Log.debug(str, "--- transferQueue request ---");
            Log.debug(str, "request.getTargetId() = " + request.getTargetId());
            Log.debug(str, "request.getQueueId() = " + request.getQueueId());
            Log.debug(str, "startAt.getTrackIdentifier() = " + request.getStartAt().getTrackIdentifier());
            Log.debug(str, "startAt.getTrackIdentifierType() = " + request.getStartAt().getTrackIdentifierType());
            Log.debug(str, "startAt.getPositionInMs() = " + request.getStartAt().getPositionInMs());
            Log.debug(str, "-----------------------------");
        } catch (NullPointerException unused) {
        }
    }

    private boolean isValid(final String luid) {
        return (TextUtils.isEmpty(luid) || luid.startsWith("local-") || luid.startsWith("prime-") || !luid.contains("-")) ? false : true;
    }

    private String getPlaybackPreference() {
        return SettingsUtil.isSpatialModeSettingEnabled(this.mContext) ? PlaybackPreference.IMMERSIVE.name() : PlaybackPreference.STEREO.name();
    }

    private String getOnlineCirrusLuid(final MediaItem mediaItem) {
        String luidOrOwnedId = MediaItemHelper.getLuidOrOwnedId(mediaItem);
        if (MediaItemHelper.isFromCirrus(mediaItem)) {
            return luidOrOwnedId;
        }
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LOCAL_URI, null);
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(this.mContext);
        if (mediaItemIdString != null) {
            if (mediaItemIdString.startsWith("file:///")) {
                mediaItemIdString = Uri.parse(mediaItemIdString).getPath();
            }
            String trackLuidByLocalFileLocation = TrackUtil.getTrackLuidByLocalFileLocation(readOnlyDatabase, mediaItemIdString, 0);
            if (trackLuidByLocalFileLocation != null) {
                return trackLuidByLocalFileLocation;
            }
        }
        return TrackUtil.getTrackLuidByLocalAsin(readOnlyDatabase, MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null), 0);
    }
}
