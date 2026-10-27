package com.amazon.mp3.library.provider.source.nowplaying;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.util.AlexaTrackRater;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.AlexaTrack;
import com.amazon.mp3.voice.StreamInfo;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.NetworkUnavailableException;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.sequencer.IndexedSequencer;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaSequencer extends IndexedSequencer {
    private static final long AVS_RESPONSE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(5);
    private static final String TAG = AlexaSequencer.class.getSimpleName();
    private AtomicBoolean isWaitingForAVSResponse;
    private final Handler mAVSResponseTimeoutHandler;
    private AlexaTrackProvider mAlexaTrackProvider;
    private final AlexaTrackRater mAlexaTrackRater;
    private Context mContext;
    private PlaybackMetricInformation mPlaybackMetricInformation;
    private AtomicInteger mRequestId;
    private Runnable mResetWaitingForAVSResponseRunnable;
    private final TrackProviderListener mTrackProviderListener;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.isWaitingForAVSResponse.compareAndSet(true, false)) {
            notifySequencerInfoChanged();
        }
    }

    public AlexaSequencer(final Context context, final AlexaTrackProvider alexaTrackProvider, final PlaybackMetricInformation playbackMetricInformation) {
        super(false);
        this.mRequestId = new AtomicInteger();
        this.isWaitingForAVSResponse = new AtomicBoolean(false);
        this.mAVSResponseTimeoutHandler = new Handler(Looper.getMainLooper());
        this.mResetWaitingForAVSResponseRunnable = new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        };
        this.mTrackProviderListener = new TrackProviderListener() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer.1
            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onCollectionChanged() {
            }

            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onCollectionRefreshed(boolean hasChanged) {
            }

            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onPositionChanged(int newPosition) {
            }

            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onCollectionLoaded(LoadCookie cookie) {
                AlexaSequencer.this.processTracks();
            }

            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onCollectionNameLoaded(String collectionName) {
                AlexaSequencer.this.updateMediaCollectionInfo(false);
            }

            @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
            public void onCountChanged(int newCount) {
                IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) AlexaSequencer.this.copySequencerInfo();
                sequencerInfo.setMaximumPosition(newCount);
                AlexaSequencer.this.setSequencerInfo(sequencerInfo);
            }
        };
        this.mContext = context;
        this.mAlexaTrackProvider = alexaTrackProvider;
        this.mPlaybackMetricInformation = playbackMetricInformation;
        this.mAlexaTrackRater = new AlexaTrackRater(this.mContext);
        loadCollection();
    }

    public List<MediaItem> getQueue() {
        return ((IndexedSequencerBase.SequencerInfo) this.info).getQueue();
    }

    private void loadCollection() {
        this.mAlexaTrackProvider.registerListener(this.mTrackProviderListener);
        loadCollectionProvider(this.mAlexaTrackProvider, 0, false, false, false);
        MediaCollectionInfo mediaCollectionInfoUpdateMediaCollectionInfo = updateMediaCollectionInfo(false);
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfoUpdateMediaCollectionInfo);
        sequencerInfoCopySequencerInfo.setCurrentPosition(0);
        sequencerInfoCopySequencerInfo.setCanShuffle(true);
        sequencerInfoCopySequencerInfo.setCanLoadMore(true);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(final int index, final ChangeReason changeReason) {
        if (this.isWaitingForAVSResponse.get()) {
            return;
        }
        if (index < getCurrentIndex()) {
            sendPreviousEventToAVS();
            notifySequencerInfoChanged();
        } else if (index > getCurrentIndex()) {
            sendNextEventToAVS();
            notifySequencerInfoChanged();
        }
    }

    public void onAVSResponse(final int indexToPlay) {
        this.isWaitingForAVSResponse.compareAndSet(true, false);
        this.mAVSResponseTimeoutHandler.removeCallbacks(this.mResetWaitingForAVSResponseRunnable);
        if (indexToPlay == getCurrentIndex()) {
            notifySequencerInfoChanged();
        } else {
            super.setCurrentIndex(indexToPlay, null);
        }
        AlexaMediaItem alexaMediaItem = (AlexaMediaItem) getMediaItem(indexToPlay);
        if (alexaMediaItem == null || !alexaMediaItem.isFirstTrackInQueue() || CarModeUtility.isInCarMode()) {
            return;
        }
        startNowPlayingActivity();
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencer, com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getMediaItem(final int index) {
        if (this.isWaitingForAVSResponse.get()) {
            return null;
        }
        return super.getMediaItem(index);
    }

    private void sendNextEventToAVS() {
        if (VoiceManagerSingleton.isInitialized() && hasInternetConnection() && this.isWaitingForAVSResponse.compareAndSet(false, true)) {
            VoiceManagerSingleton.getInstance().issueNextCommand();
            this.mAVSResponseTimeoutHandler.postDelayed(this.mResetWaitingForAVSResponseRunnable, AVS_RESPONSE_TIMEOUT_MILLIS);
        }
    }

    private void sendPreviousEventToAVS() {
        if (VoiceManagerSingleton.isInitialized() && hasInternetConnection() && this.isWaitingForAVSResponse.compareAndSet(false, true)) {
            VoiceManagerSingleton.getInstance().issuePreviousCommand();
            this.mAVSResponseTimeoutHandler.postDelayed(this.mResetWaitingForAVSResponseRunnable, AVS_RESPONSE_TIMEOUT_MILLIS);
        }
    }

    public void queueNextTrack(final AlexaTrackProvider nextTrackProvider) {
        if (isUrlInQueue(nextTrackProvider.getStreamInfo())) {
            return;
        }
        this.mAlexaTrackProvider = nextTrackProvider;
        nextTrackProvider.registerListener(this.mTrackProviderListener);
        loadCollectionProvider(nextTrackProvider, getQueueEnd(), false, false, false);
    }

    public int getIndexOfTrackInQueue(final StreamInfo streamInfo) {
        for (int i = 0; i < getQueue().size(); i++) {
            StreamInfo streamInfo2 = ((AlexaMediaItem) getQueue().get(i)).getStreamInfo();
            if (streamInfo2.getStreamUrl().equals(streamInfo.getStreamUrl()) || streamInfo2.getStreamToken().equals(streamInfo.getStreamToken())) {
                return i;
            }
        }
        return -9999;
    }

    public boolean isUrlInQueue(final StreamInfo streamInfo) {
        return getIndexOfTrackInQueue(streamInfo) != -9999;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processTracks() {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer.2
            @Override // java.lang.Runnable
            public void run() {
                ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AlexaSequencer.this.updateMediaCollectionInfo(true);
                    }
                });
                MusicTrack musicTrackFetchTrack = AlexaSequencer.this.fetchTrack();
                if (musicTrackFetchTrack == null) {
                    return;
                }
                AlexaSequencer.this.addTrack(musicTrackFetchTrack);
            }
        });
    }

    private void startNowPlayingActivity() {
        Context context = this.mContext;
        Activity activity = VoiceManagerSingleton.getInstance().getActivity();
        if (activity != null && !activity.isDestroyed()) {
            context = activity;
        }
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
        if (activity == null || activity.isDestroyed()) {
            intentForContentUri.setFlags(268435456);
        }
        context.startActivity(intentForContentUri);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTrack(final MusicTrack track) {
        MediaItem mediaItemCreateAlexaMediaItem = MediaItemHelper.createAlexaMediaItem((AlexaTrack) track, this.mPlaybackMetricInformation, getMediaCollectionInfo(), this.mAlexaTrackRater, getRatingsProvider());
        getMetricsTimer("TIME_SINCE_READY").start();
        addMediaItem(mediaItemCreateAlexaMediaItem);
    }

    private void addMediaItem(final MediaItem fetchedItem) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaSequencer.3
            @Override // java.lang.Runnable
            public void run() {
                IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) AlexaSequencer.this.copySequencerInfo();
                sequencerInfo.getQueue().add(fetchedItem);
                sequencerInfo.setCanLoadMore(true);
                AlexaSequencer.this.setSequencerInfo(sequencerInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MusicTrack fetchTrack() {
        AlexaTrackProvider alexaTrackProvider = this.mAlexaTrackProvider;
        if (alexaTrackProvider != null && alexaTrackProvider.getCount() != 0) {
            return this.mAlexaTrackProvider.getTrack(0);
        }
        Log.debug(TAG, "fetchTrack finished");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaCollectionInfo updateMediaCollectionInfo(final boolean setSequencerInfo) {
        String str;
        long albumId;
        String str2;
        MusicTrack track = this.mAlexaTrackProvider.getTrack(0);
        MediaCollectionInfo mediaCollectionInfo = getMediaCollectionInfo();
        long j = SequencerHelper.NO_ALBUMID_AVAILABLE;
        if (track != null) {
            String albumAsin = track.getAlbumAsin() != null ? track.getAlbumAsin() : "";
            String artistAsin = track.getArtistAsin() != null ? track.getArtistAsin() : "";
            albumId = track.getAlbumId();
            str = artistAsin;
            str2 = albumAsin;
        } else {
            str = "";
            albumId = j;
            str2 = str;
        }
        AlexaTrackProvider alexaTrackProvider = this.mAlexaTrackProvider;
        MediaCollectionInfo mediaCollectionInfoUpdateMediaCollectionInfo = SequencerHelper.updateMediaCollectionInfo(alexaTrackProvider, mediaCollectionInfo, alexaTrackProvider.getUri(), str2, str, albumId);
        if (setSequencerInfo) {
            IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
            sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfoUpdateMediaCollectionInfo);
            Iterator<MediaItem> it = sequencerInfoCopySequencerInfo.getQueue().iterator();
            while (it.hasNext()) {
                it.next().setSourceMediaCollectionInfo(mediaCollectionInfoUpdateMediaCollectionInfo);
            }
            setSequencerInfo(sequencerInfoCopySequencerInfo);
            MediaItem currentMediaItem = getCurrentMediaItem();
            if (currentMediaItem != null) {
                notifyMetadataChanged(currentMediaItem);
            }
        }
        return mediaCollectionInfoUpdateMediaCollectionInfo;
    }

    private void loadCollectionProvider(final TrackProvider provider, final int position, final boolean shuffleAllTracks, final boolean restoreFromCasting, final boolean refreshCollection) {
        provider.loadCollection(new LoadCookie(this.mRequestId.incrementAndGet(), position, position, this.mPlaybackMetricInformation, shuffleAllTracks, restoreFromCasting, refreshCollection));
        provider.loadCollectionName();
    }

    private boolean hasInternetConnection() {
        if (ConnectivityUtil.hasAnyInternetConnection(this.mContext)) {
            return true;
        }
        notifyErrorFetchingMediaItems(new NetworkUnavailableException(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).autoRetry(false)));
        Log.error(TAG, "No network connection");
        return false;
    }
}
