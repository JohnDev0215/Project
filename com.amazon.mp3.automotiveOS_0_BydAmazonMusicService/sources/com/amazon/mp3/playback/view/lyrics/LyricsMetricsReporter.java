package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.LyricsViewType;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
class LyricsMetricsReporter {
    private static final Character[] FILTERED_CHARACTERS;
    private static final String TAG = "LyricsMetricsReporter";
    private static final Set<Character> mFilteredCharactersSet;
    private static LyricsMetricsReporter sInstance;
    private String mAsin;
    private int mCharacterCount;
    private final Context mContext;
    private MediaItem mDisplayedItem;
    private long mDurationMilliseconds;
    private boolean mIsLyricsDisplayed;
    private boolean mIsOwned;
    private IsOwnedAsyncTask mIsOwnedAsyncTask;
    private boolean mIsPlaybackStateMonitored;
    private long mLastLyricsShownTime;
    private boolean[] mLineViewed;
    private List<LyricsLine> mLyricsLines;
    private String mMarketplace;
    private final PlaybackController mPlaybackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
    private final PlaybackListener mPlaybackListener = new PlaybackListener(this, null);
    private int mPrevStartIndex = -1;
    private int mPrevEndIndex = -1;
    private final Set<Integer> mLyricsDisplayedSet = new HashSet();

    static {
        Character[] chArr = {'!', '\"', '#', Character.valueOf(Typography.dollar), '%', Character.valueOf(Typography.amp), '\'', '(', ')', '*', '+', '-', ',', '.', Character.valueOf(JsonPointer.SEPARATOR), ':', ';', Character.valueOf(Typography.less), '=', Character.valueOf(Typography.greater), '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', ' ', '\n', '\r', '\t', (char) 8197};
        FILTERED_CHARACTERS = chArr;
        mFilteredCharactersSet = new HashSet(Arrays.asList(chArr));
    }

    public static LyricsMetricsReporter getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LyricsMetricsReporter(context.getApplicationContext());
        }
        return sInstance;
    }

    public static int getCharacterCount(LyricsLine lyricsLine) {
        if (lyricsLine == null || lyricsLine.getLine() == null) {
            return 0;
        }
        int i = 0;
        for (char c : lyricsLine.getLine().toCharArray()) {
            if (!mFilteredCharactersSet.contains(Character.valueOf(c))) {
                i++;
            }
        }
        return i;
    }

    public void setLyrics(Lyrics lyrics) {
        if (this.mIsPlaybackStateMonitored) {
            return;
        }
        this.mAsin = lyrics.getAsin();
        this.mDisplayedItem = this.mPlaybackController.getCurrentMediaItem();
        this.mMarketplace = lyrics.getMarketplace();
        List<LyricsLine> lines = lyrics.getLines();
        this.mLyricsLines = lines;
        this.mLineViewed = new boolean[lines.size()];
        registerPlaystateReceivers();
        IsOwnedAsyncTask isOwnedAsyncTask = new IsOwnedAsyncTask(this, null);
        this.mIsOwnedAsyncTask = isOwnedAsyncTask;
        isOwnedAsyncTask.execute(lyrics);
    }

    public synchronized void displayLyrics(int id) {
        if (!this.mLyricsDisplayedSet.contains(Integer.valueOf(id))) {
            this.mLyricsDisplayedSet.add(Integer.valueOf(id));
            setIsLyricsDisplayed(isDisplayed());
        }
    }

    public synchronized void hideLyrics(int id) {
        if (this.mLyricsDisplayedSet.contains(Integer.valueOf(id))) {
            this.mLyricsDisplayedSet.remove(Integer.valueOf(id));
            setIsLyricsDisplayed(isDisplayed());
        }
    }

    private void setIsLyricsDisplayed(boolean isDisplayed) {
        if (isDisplayed) {
            if (this.mLastLyricsShownTime == 0) {
                this.mLastLyricsShownTime = SystemClock.uptimeMillis();
            }
        } else if (this.mIsLyricsDisplayed) {
            this.mDurationMilliseconds += SystemClock.uptimeMillis() - this.mLastLyricsShownTime;
            this.mLastLyricsShownTime = 0L;
        }
        this.mIsLyricsDisplayed = isDisplayed;
        Log.verbose(TAG, "Metrics Lyrics Duration " + this.mDurationMilliseconds);
    }

    public void linesViewed(int startIndex, int endIndex) {
        if (this.mIsLyricsDisplayed) {
            int i = this.mPrevStartIndex;
            if (startIndex == i && endIndex == this.mPrevEndIndex) {
                return;
            }
            if (startIndex == i + 1 && endIndex == this.mPrevEndIndex + 1) {
                recordLineViewed(endIndex);
            } else {
                for (int i2 = startIndex; i2 <= endIndex; i2++) {
                    recordLineViewed(i2);
                }
            }
            this.mPrevStartIndex = startIndex;
            this.mPrevEndIndex = endIndex;
        }
    }

    private LyricsMetricsReporter(Context context) {
        this.mContext = context;
    }

    private void reset() {
        this.mLineViewed = new boolean[this.mLineViewed.length];
        this.mPrevStartIndex = -1;
        this.mPrevEndIndex = -1;
        this.mDurationMilliseconds = 0L;
        this.mCharacterCount = 0;
        this.mIsLyricsDisplayed = false;
        this.mLastLyricsShownTime = 0L;
        this.mDisplayedItem = null;
    }

    private void recordLineViewed(int index) {
        if (index >= 0) {
            boolean[] zArr = this.mLineViewed;
            if (index < zArr.length && !zArr[index]) {
                zArr[index] = true;
                this.mCharacterCount += getCharacterCount(this.mLyricsLines.get(index));
            }
        }
    }

    private void finish() {
        IsOwnedAsyncTask isOwnedAsyncTask = this.mIsOwnedAsyncTask;
        if (isOwnedAsyncTask != null) {
            isOwnedAsyncTask.cancel(true);
        }
        unregisterPlaystateReceivers();
        finalizeReport();
        reset();
    }

    private void finalizeReport() {
        long jUptimeMillis = this.mDurationMilliseconds;
        if (this.mLastLyricsShownTime > 0) {
            jUptimeMillis += SystemClock.uptimeMillis() - this.mLastLyricsShownTime;
        }
        long j = jUptimeMillis;
        if (j < 1000) {
            return;
        }
        LyricsViewType lyricsViewTypeTranslateToMetricsViewType = translateToMetricsViewType(CachedViewState.getInstance().getLastNonGoneState());
        Log.verbose(TAG, "Metrics - Asin: " + this.mAsin + " Is owned: " + this.mIsOwned + " Duration: " + j + " CharacterCount: " + this.mCharacterCount);
        MetricsLogger.lyricsViewedFromNowPlaying(this.mAsin, this.mIsOwned, lyricsViewTypeTranslateToMetricsViewType, j, this.mCharacterCount);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.lyrics.LyricsMetricsReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState;

        static {
            int[] iArr = new int[ViewState.values().length];
            $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState = iArr;
            try {
                iArr[ViewState.FULL_SCREEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState[ViewState.PREVIEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState[ViewState.FRUGAL_LYRICS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState[ViewState.GONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState[ViewState.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private LyricsViewType translateToMetricsViewType(ViewState viewState) {
        boolean zIsPortrait = ScreenUtil.isPortrait();
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$lyrics$ViewState[viewState.ordinal()];
        if (i == 1) {
            return LyricsViewType.FULL_SCREEN;
        }
        if (i == 2) {
            return zIsPortrait ? LyricsViewType.PORTRAIT_PREVIEW : LyricsViewType.LANDSCAPE_PREVIEW;
        }
        if (i == 3) {
            return zIsPortrait ? LyricsViewType.PORTRAIT_CLOSED : LyricsViewType.LANDSCAPE_CLOSED;
        }
        if (i == 4 || i == 5) {
            Log.warning(TAG, "Cannot report metrics for a track that has no lyrics");
        }
        throw new RuntimeException("Translate to Metrics ViewType exception");
    }

    private void registerPlaystateReceivers() {
        this.mIsPlaybackStateMonitored = true;
        this.mPlaybackController.addOnPlayStateChangedListener(this.mPlaybackListener);
        this.mPlaybackController.addOnPlaybackEventListener(this.mPlaybackListener);
    }

    private void unregisterPlaystateReceivers() {
        this.mIsPlaybackStateMonitored = false;
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mPlaybackListener);
        this.mPlaybackController.removeOnPlaybackEventListener(this.mPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTrackChanged() {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTrackRepeated() {
        finalizeReport();
        this.mLineViewed = new boolean[this.mLineViewed.length];
        this.mPrevStartIndex = -1;
        this.mPrevEndIndex = -1;
        this.mDurationMilliseconds = 0L;
        this.mCharacterCount = 0;
        if (this.mIsLyricsDisplayed) {
            this.mLastLyricsShownTime = SystemClock.uptimeMillis();
        } else {
            this.mLastLyricsShownTime = 0L;
        }
    }

    private boolean isDisplayed() {
        return !this.mLyricsDisplayedSet.isEmpty();
    }

    private static class LyricsInfo extends LyricsTrackInfo {
        private final String mAsin;
        private final String mMarketplace;

        public LyricsInfo(Lyrics lyrics) {
            this.mAsin = lyrics.getAsin();
            this.mMarketplace = lyrics.getMarketplace();
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getAsin() {
            return this.mAsin;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getMarketplaceId() {
            return this.mMarketplace;
        }
    }

    private class IsOwnedAsyncTask extends AsyncTask<Lyrics, Void, Boolean> {
        private OwnershipStatusQuery mOwnershipStatusQuery;

        private IsOwnedAsyncTask() {
            this.mOwnershipStatusQuery = new OwnershipStatusQuery(LyricsMetricsReporter.this.mContext.getApplicationContext());
        }

        /* synthetic */ IsOwnedAsyncTask(LyricsMetricsReporter lyricsMetricsReporter, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(final Lyrics... params) {
            return Boolean.valueOf(isCancelled() ? false : this.mOwnershipStatusQuery.loadOwnershipStatusForInfo(new LyricsInfo(params[0])));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Boolean isOwned) {
            LyricsMetricsReporter.this.mIsOwned = isOwned.booleanValue();
        }
    }

    private class PlaybackListener implements OnPlayStateChangedListener, OnPlaybackEventListener {
        private PlaybackListener() {
        }

        /* synthetic */ PlaybackListener(LyricsMetricsReporter lyricsMetricsReporter, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            if (LyricsMetricsReporter.this.mPlaybackController.getCurrentMediaItem() == LyricsMetricsReporter.this.mDisplayedItem || LyricsMetricsReporter.this.mDisplayedItem == null) {
                return;
            }
            LyricsMetricsReporter.this.onTrackChanged();
        }

        @Override // com.amazon.music.media.playback.OnPlaybackEventListener
        public void onPlaybackEvent(final int event, final ControlSource controlSource) {
            if ((event == 4 || event == 27) && LyricsMetricsReporter.this.mPlaybackController.getCurrentMediaItem() == LyricsMetricsReporter.this.mDisplayedItem && LyricsMetricsReporter.this.mDisplayedItem != null) {
                LyricsMetricsReporter.this.onTrackRepeated();
            }
        }
    }
}
