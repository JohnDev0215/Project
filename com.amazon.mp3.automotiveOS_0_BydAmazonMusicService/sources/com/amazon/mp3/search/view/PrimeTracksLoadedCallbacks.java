package com.amazon.mp3.search.view;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.playback.TracksLoadedHelper;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeTracksLoadedCallbacks implements LoaderManager.LoaderCallbacks<List<MusicTrack>> {
    private static int maxQueueSize;
    private boolean didStartFromSearch;
    private final FragmentActivity mActivity;
    private boolean mIsSingleTrackSFA;
    private final int mListPosition;
    private PlaybackListener mPlaybackListener;
    private PrimeCollectionTask.Task mPlaybackMode;
    private final PlaybackPageType mPlaybackPageType;
    private PrimeBrowseSongsPlaybackLoader mPrimeBrowseSongsPlaybackLoader;
    private final boolean mShuffle;
    private final PrimeBrowseSongsPlaybackLoader.SongsCollection mSongsCollection;
    private final long mStartTime;
    private String queueTitle;
    private Random random;

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(final Loader<List<MusicTrack>> listLoader) {
    }

    static {
        maxQueueSize = CastingUtil.isCastingToAlexa() ? 5 : 50;
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final ArrayAdapter<PrimeTrack> adapter, final int listPosition, final PlaybackPageType playbackPageType, final long startTime) {
        this(activity, new PrimeBrowseSongsPlaybackLoader.SongsCollection() { // from class: com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks.1
            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public int getCount() {
                return adapter.getCount();
            }

            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public PrimeTrack getItem(final int position) {
                return (PrimeTrack) adapter.getItem(position);
            }
        }, listPosition, playbackPageType, startTime, (PrimeCollectionTask.Task) null, (String) null, (PlaybackListener) null);
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final ArrayAdapter<PrimeTrack> adapter, final int listPosition, final PlaybackPageType playbackPageType, final long startTime, final boolean didStartFromSearch) {
        this(activity, adapter, listPosition, playbackPageType, startTime);
        this.didStartFromSearch = didStartFromSearch;
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final ArrayAdapter<PrimeTrack> adapter, final int listPosition, final PlaybackPageType playbackPageType, final long startTime, final boolean didStartFromSearch, final boolean isSingleTrackSFA) {
        this(activity, adapter, listPosition, playbackPageType, startTime, didStartFromSearch);
        this.mIsSingleTrackSFA = isSingleTrackSFA;
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final List<PrimeTrack> tracks, final int listPosition, final PlaybackPageType playbackPageType, final long startTime) {
        this(activity, tracks, listPosition, playbackPageType, startTime, (PrimeCollectionTask.Task) null);
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final List<PrimeTrack> tracks, final int listPosition, final PlaybackPageType playbackPageType, final long startTime, final PrimeCollectionTask.Task taskType) {
        this(activity, new PrimeBrowseSongsPlaybackLoader.SongsCollection() { // from class: com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks.2
            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public int getCount() {
                return tracks.size();
            }

            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public PrimeTrack getItem(final int position) {
                return (PrimeTrack) tracks.get(position);
            }
        }, listPosition, playbackPageType, startTime, taskType, (String) null, (PlaybackListener) null);
    }

    public PrimeTracksLoadedCallbacks(final FragmentActivity activity, final List<PrimeTrack> tracks, final int listPosition, final PlaybackPageType playbackPageType, final long startTime, final PrimeCollectionTask.Task taskType, final String queueTitle, @Nullable final PlaybackListener playbackListener) {
        this(activity, new PrimeBrowseSongsPlaybackLoader.SongsCollection() { // from class: com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks.3
            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public int getCount() {
                return tracks.size();
            }

            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public PrimeTrack getItem(final int position) {
                return (PrimeTrack) tracks.get(position);
            }
        }, listPosition, playbackPageType, startTime, taskType, queueTitle, playbackListener);
    }

    private PrimeTracksLoadedCallbacks(final FragmentActivity activity, final PrimeBrowseSongsPlaybackLoader.SongsCollection songsCollection, final int listPosition, final PlaybackPageType playbackPageType, final long startTime, final PrimeCollectionTask.Task taskType, final String playQueueTitle, @Nullable final PlaybackListener playbackListener) {
        this.mPlaybackMode = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        this.mIsSingleTrackSFA = false;
        this.mSongsCollection = songsCollection;
        this.mActivity = activity;
        this.mListPosition = listPosition;
        this.mPlaybackPageType = playbackPageType;
        this.mStartTime = startTime;
        if (taskType != null) {
            this.mPlaybackMode = taskType;
        }
        this.mShuffle = PrimeCollectionTask.Task.PLAYBACK_SHUFFLE.equals(this.mPlaybackMode);
        this.random = new Random();
        maxQueueSize = CastingUtil.isCastingToAlexa() ? 5 : 50;
        this.queueTitle = playQueueTitle;
        this.mPlaybackListener = playbackListener;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<List<MusicTrack>> onCreateLoader(final int i, final Bundle bundle) {
        PrimeBrowseSongsPlaybackLoader primeBrowseSongsPlaybackLoader = new PrimeBrowseSongsPlaybackLoader(this.mActivity, this.mSongsCollection);
        this.mPrimeBrowseSongsPlaybackLoader = primeBrowseSongsPlaybackLoader;
        return primeBrowseSongsPlaybackLoader;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    @SuppressLint({"CheckResult"})
    public void onLoadFinished(Loader<List<MusicTrack>> loader, final List<MusicTrack> result) {
        this.mActivity.getSupportLoaderManager().destroyLoader(loader.getId());
        Uri contentUri = MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getContentUri(MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED, result.get(this.mListPosition).getAsin());
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
        StartPlaybackUtil.startMusicTracksPlayback(this.mActivity, contentUri, this.queueTitle, result, this.mListPosition, false, this.mPlaybackMode == PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN, this.mPlaybackPageType, true, ControlSource.APP_UI, null, this.mIsSingleTrackSFA, this.mPlaybackListener, null, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks.4
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                new TracksLoadedHelper(PrimeTracksLoadedCallbacks.this.mActivity, PrimeTracksLoadedCallbacks.this.mListPosition, PrimeTracksLoadedCallbacks.this.mShuffle, PrimeTracksLoadedCallbacks.this.mStartTime, PrimeTracksLoadedCallbacks.this.mPlaybackMode, PrimeTracksLoadedCallbacks.this.mPlaybackPageType).onLoadedFinishedHelper(result);
            }
        });
    }

    public void setPlaybackmode(PrimeCollectionTask.Task playBackMode) {
        this.mPlaybackMode = playBackMode;
    }

    public static int getMaxQueueSize() {
        return maxQueueSize;
    }
}
