package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.playback.OnLyricsLineClickListener;
import com.amazon.mp3.playback.OnLyricsLoadedListener;
import com.amazon.mp3.playback.view.lyrics.LyricsAdapter;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.views.library.styles.StyleSheet;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsListViewContainer extends LyricsViewContainer {
    private static final String TAG = LyricsListViewContainer.class.getSimpleName();
    private ViewState mCurrentViewState;
    private ViewState mDesiredViewState;
    private boolean mFirstOnLayout;
    private boolean mFirstOnMeasure;
    private boolean mIsFullScreen;
    private LyricsAdapter mListAdapter;
    private int mLyricsLineCount;
    protected LyricsSharingDataModel mLyricsSharingDataModel;
    private boolean mLyricsSharingMode;
    private OnLyricsLineClickListener mOnLyricsLineClickListener;
    private OnLyricsLoadedListener mOnLyricsLoadedListener;
    private PlayStatus mPlayStatus;
    private PowerManager mPowerManager;
    private RecyclerView mRecyclerView;
    private final ViewStateScrollPolicy mScrollPolicy;
    private StyleSheet mStyleSheet;
    private boolean mUserSeek;
    private boolean mWindowHasFocus;

    public boolean getLyricsSharingMode() {
        return this.mLyricsSharingMode;
    }

    public void setLyricsSharingMode(boolean lyricsSharingMode) {
        this.mLyricsSharingMode = lyricsSharingMode;
    }

    public LyricsListViewContainer(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mWindowHasFocus = true;
        this.mScrollPolicy = new ViewStateScrollPolicy(this);
        this.mFirstOnMeasure = true;
        this.mFirstOnLayout = true;
        this.mLyricsSharingMode = false;
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void setPlaybackTime(long time) {
        if (this.mWindowHasFocus) {
            super.setPlaybackTime(time);
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void setCurrentPlayingLyricsLine(final int linePosition) {
        if (this.mLyricsSharingMode || linePosition == this.mListAdapter.getCurrentLinePosition()) {
            return;
        }
        this.mListAdapter.setCurrentLinePosition(linePosition);
        if (!isItemViewVisible(linePosition) && !this.mScrollPolicy.isScrollBlocked()) {
            this.mRecyclerView.post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setCurrentPlayingLyricsLine$0(linePosition);
                }
            });
            LyricsSharingDataModel lyricsSharingDataModel = this.mLyricsSharingDataModel;
            if (lyricsSharingDataModel != null) {
                lyricsSharingDataModel.setLastLineNumberInFocus(linePosition);
            }
        }
        this.mListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setCurrentPlayingLyricsLine$0(int i) {
        this.mRecyclerView.smoothScrollToPosition(i);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
    }

    public void setSelectedLineBackground(TextView textView) {
        this.mListAdapter.setSelectedLineBackground(textView);
    }

    public void setUnSelectedLineBackground(TextView textView) {
        this.mListAdapter.setUnSelectedLineBackground(textView);
    }

    private void setLyrics(@NonNull Lyrics lyrics) {
        init();
        if (this.mListAdapter == null) {
            LyricsAdapter lyricsAdapter = new LyricsAdapter(getContext(), lyrics, this.mStyleSheet, this.mIsFullScreen, false);
            this.mListAdapter = lyricsAdapter;
            this.mRecyclerView.setAdapter(lyricsAdapter);
            this.mListAdapter.setOnLyricsLineClickListener(this.mOnLyricsLineClickListener);
            this.mListAdapter.setOnCurrentLineHeightUpdatedListener(new LyricsAdapter.OnCurrentLineHeightUpdatedListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer$$ExternalSyntheticLambda2
                @Override // com.amazon.mp3.playback.view.lyrics.LyricsAdapter.OnCurrentLineHeightUpdatedListener
                public final void onCurrentLineHeightUpdated(int i) {
                    this.f$0.onCurrentLineHeightUpdated(i);
                }
            });
        } else {
            this.mRecyclerView.stopScroll();
            this.mListAdapter.updateLyrics(lyrics);
        }
        this.mListAdapter.setIsPlaying(this.mPlayStatus == PlayStatus.RENDERING);
        this.mRecyclerView.setOnScrollListener(this.mScrollPolicy);
        this.mRecyclerView.setOnTouchListener(this.mScrollPolicy);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void setPlayStatus(final PlayStatus playStatus) {
        this.mPlayStatus = playStatus;
        final ViewState currentViewState = getCurrentViewState();
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setPlayStatus$1(playStatus, currentViewState, (Subscriber) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlayStatus$1(PlayStatus playStatus, ViewState viewState, Subscriber subscriber) {
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setIsPlaying(playStatus == PlayStatus.RENDERING);
            this.mListAdapter.notifyDataSetChanged();
        }
        adjustKeepScreenOn(viewState, playStatus);
        subscriber.onNext(null);
        subscriber.onCompleted();
    }

    public void setIsUserSeeking(boolean seek) {
        this.mUserSeek = seek;
    }

    public ViewState getCurrentViewState() {
        ViewState viewState = this.mCurrentViewState;
        return viewState == null ? ViewState.GONE : viewState;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mWindowHasFocus = true;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(final boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        this.mWindowHasFocus = hasWindowFocus;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        onFirstMeasure();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
        super.onLayout(changed, l, t, r, b);
        onFirstLayout();
        recordLinesViewed();
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer, com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
    public void onLoadDelay() {
        super.onLoadDelay();
        changeToState(ViewState.GONE, true);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer, com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
    public void onLyricsLoaded(Lyrics lyrics) {
        super.onLyricsLoaded(lyrics);
        this.mLyricsLineCount = lyrics.getLines().size();
        setLyrics(lyrics);
        changeToState(this.mIsFullScreen ? ViewState.FULL_SCREEN : ViewState.PREVIEW, true);
        if (!isStage() || this.mListAdapter.getCurrentLinePosition() != 0 || isItemViewVisible(0) || this.mScrollPolicy.isScrollBlocked()) {
            return;
        }
        this.mRecyclerView.post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onLyricsLoaded$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLyricsLoaded$2() {
        this.mRecyclerView.smoothScrollToPosition(0);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer, com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
    public void onNoLyrics() {
        super.onNoLyrics();
        if (this.mListAdapter != null) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.stopScroll();
            }
            this.mListAdapter.updateNoLyrics();
        }
        this.mLyricsLineCount = 0;
        changeToState(ViewState.GONE, true);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public View getLyricsView() {
        return this.mRecyclerView;
    }

    public void setOnLyricsLineClickListener(OnLyricsLineClickListener listener) {
        this.mOnLyricsLineClickListener = listener;
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setOnLyricsLineClickListener(listener);
        }
    }

    public void setOnLyricsLoadedListener(OnLyricsLoadedListener listener) {
        this.mOnLyricsLoadedListener = listener;
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void onDestroy() {
        super.onDestroy();
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setOnLyricsLineClickListener(null);
            this.mListAdapter.setOnCurrentLineHeightUpdatedListener(null);
        }
        setOnLyricsLineClickListener(null);
        setOnLyricsLoadedListener(null);
    }

    public void changeToState(ViewState desiredViewState, boolean checkViewState) {
        adjustKeepScreenOn(desiredViewState, this.mPlayStatus);
        this.mDesiredViewState = desiredViewState;
        if (canChangeState()) {
            if (checkViewState && this.mDesiredViewState == this.mCurrentViewState) {
                return;
            }
            this.mCurrentViewState = desiredViewState;
            this.mScrollPolicy.resetScrollBlock();
            this.mCachedViewState.setViewState(this.mCurrentViewState);
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void initStyleSheet(StyleSheet styleSheet) {
        super.initStyleSheet(styleSheet);
        this.mStyleSheet = styleSheet;
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setStyleSheet(styleSheet);
        }
    }

    public void setIsFullScreen(boolean isFullScreen) {
        this.mIsFullScreen = isFullScreen;
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setIsFullScreen(isFullScreen);
        }
    }

    private void adjustKeepScreenOn(final ViewState viewState, final PlayStatus playStatus) {
        if (viewState == ViewState.FULL_SCREEN && playStatus.userExpectsPlayback()) {
            setKeepScreenOn(true);
        } else {
            setKeepScreenOn(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCurrentLineHeightUpdated(int currentLineHeight) {
        LyricsAdapter lyricsAdapter = this.mListAdapter;
        if (lyricsAdapter == null) {
            return;
        }
        int currentLinePosition = lyricsAdapter.getCurrentLinePosition();
        if (this.mScrollPolicy.isScrollBlocked() || !canAutoScroll(currentLinePosition)) {
            return;
        }
        this.mRecyclerView.smoothScrollToPosition(currentLinePosition);
    }

    private void onFirstMeasure() {
        if (this.mFirstOnMeasure) {
            init();
            this.mFirstOnMeasure = false;
        }
    }

    private void onFirstLayout() {
        if (this.mFirstOnLayout) {
            this.mFirstOnLayout = false;
            ViewState viewState = this.mDesiredViewState;
            if (viewState != null) {
                changeToState(viewState, false);
            }
        }
    }

    private void recordLinesViewed() {
        if (this.mWindowHasFocus && getCurrentViewState() != ViewState.GONE && this.mPowerManager.isScreenOn()) {
            int firstVisiblePosition = getFirstVisiblePosition(this.mRecyclerView);
            int lastVisiblePosition = getLastVisiblePosition(this.mRecyclerView);
            if (firstVisiblePosition < 0 || lastVisiblePosition < 0) {
                return;
            }
            int i = this.mLyricsLineCount;
            if (i <= lastVisiblePosition) {
                lastVisiblePosition = i - 1;
            }
            int i2 = lastVisiblePosition;
            while (lastVisiblePosition >= firstVisiblePosition) {
                View childAt = this.mRecyclerView.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt != null && childAt.getBottom() <= this.mRecyclerView.getBottom() - this.mRecyclerView.getTop()) {
                    break;
                }
                i2--;
                lastVisiblePosition--;
            }
            this.mLyricsMetricsReporter.linesViewed(firstVisiblePosition, i2);
        }
    }

    private void init() {
        if (this.mRecyclerView == null) {
            this.mPowerManager = (PowerManager) getContext().getSystemService("power");
            setOrientation(1);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.LyricsListView);
            this.mRecyclerView = recyclerView;
            recyclerView.setLayoutManager(new LyricsLayoutManager(getContext()));
            this.mRecyclerView.addItemDecoration(new LyricsItemDecoration());
        }
    }

    private boolean canAutoScroll(int currentPosition) {
        RecyclerView recyclerView = this.mRecyclerView;
        View childAt = recyclerView.getChildAt(currentPosition - getFirstVisiblePosition(recyclerView));
        if (childAt != null) {
            return (childAt.getTop() + childAt.getBottom()) / 2 != this.mRecyclerView.getHeight() / 2;
        }
        return true;
    }

    private boolean isItemViewVisible(int position) {
        RecyclerView recyclerView = this.mRecyclerView;
        return (recyclerView == null || recyclerView.getChildAt(position - getFirstVisiblePosition(recyclerView)) == null) ? false : true;
    }

    private boolean canChangeState() {
        return (this.mFirstOnLayout || this.mRecyclerView == null) ? false : true;
    }

    private int getFirstVisiblePosition(RecyclerView recyclerView) {
        if (recyclerView == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return -1;
        }
        return ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    private int getLastVisiblePosition(RecyclerView recyclerView) {
        if (recyclerView == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return -1;
        }
        return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    public void resetScrollPolicy() {
        this.mScrollPolicy.resetScrollBlock();
    }

    private static class ViewStateScrollPolicy extends RecyclerView.OnScrollListener implements View.OnTouchListener {
        private final LyricsListViewContainer mContainer;
        private final LyricsScrollPolicy mLyricsScrollPolicy = new LyricsScrollPolicy();

        public ViewStateScrollPolicy(LyricsListViewContainer container) {
            this.mContainer = container;
        }

        public boolean isScrollBlocked() {
            return this.mLyricsScrollPolicy.isScrollBlocked();
        }

        public void resetScrollBlock() {
            this.mLyricsScrollPolicy.resetScrollBlock();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            return this.mLyricsScrollPolicy.onTouch(v, event);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int scrollState) {
            super.onScrollStateChanged(recyclerView, scrollState);
            this.mLyricsScrollPolicy.onScrollStateChanged(recyclerView, scrollState);
            this.mContainer.mLyricsSharingDataModel.setLastLineNumberInFocus(((LyricsLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            this.mLyricsScrollPolicy.onScrolled(recyclerView, dx, dy);
        }
    }
}
