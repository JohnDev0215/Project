package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.ControlBarPresenter;
import androidx.leanback.widget.PlaybackControlsPresenter;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PlaybackSeekDataProvider;
import androidx.leanback.widget.PlaybackSeekUi;
import androidx.leanback.widget.PlaybackTransportRowView;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SeekBar;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackTransportRowPresenter extends PlaybackRowPresenter {
    Presenter mDescriptionPresenter;
    OnActionClickedListener mOnActionClickedListener;
    ControlBarPresenter mPlaybackControlsPresenter;
    boolean mProgressColorSet;
    ControlBarPresenter mSecondaryControlsPresenter;
    boolean mSecondaryProgressColorSet;
    float mDefaultSeekIncrement = 0.01f;
    int mProgressColor = 0;
    int mSecondaryProgressColor = 0;
    private final ControlBarPresenter.OnControlSelectedListener mOnControlSelectedListener = new ControlBarPresenter.OnControlSelectedListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.1
        @Override // androidx.leanback.widget.ControlBarPresenter.OnControlSelectedListener
        public void onControlSelected(Presenter.ViewHolder itemViewHolder, Object item, ControlBarPresenter.BoundData data) {
            ViewHolder vh = ((BoundData) data).mRowViewHolder;
            if (vh.mSelectedViewHolder != itemViewHolder || vh.mSelectedItem != item) {
                vh.mSelectedViewHolder = itemViewHolder;
                vh.mSelectedItem = item;
                vh.dispatchItemSelection();
            }
        }
    };
    private final ControlBarPresenter.OnControlClickedListener mOnControlClickedListener = new ControlBarPresenter.OnControlClickedListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.2
        @Override // androidx.leanback.widget.ControlBarPresenter.OnControlClickedListener
        public void onControlClicked(Presenter.ViewHolder itemViewHolder, Object item, ControlBarPresenter.BoundData data) {
            ViewHolder vh = ((BoundData) data).mRowViewHolder;
            if (vh.getOnItemViewClickedListener() != null) {
                vh.getOnItemViewClickedListener().onItemClicked(itemViewHolder, item, vh, vh.getRow());
            }
            if (PlaybackTransportRowPresenter.this.mOnActionClickedListener != null && (item instanceof Action)) {
                PlaybackTransportRowPresenter.this.mOnActionClickedListener.onActionClicked((Action) item);
            }
        }
    };

    static class BoundData extends PlaybackControlsPresenter.BoundData {
        ViewHolder mRowViewHolder;

        BoundData() {
        }
    }

    public class ViewHolder extends PlaybackRowPresenter.ViewHolder implements PlaybackSeekUi {
        BoundData mControlsBoundData;
        final ViewGroup mControlsDock;
        ControlBarPresenter.ViewHolder mControlsVh;
        final TextView mCurrentTime;
        long mCurrentTimeInMs;
        final ViewGroup mDescriptionDock;
        final Presenter.ViewHolder mDescriptionViewHolder;
        final ImageView mImageView;
        boolean mInSeek;
        final PlaybackControlsRow.OnPlaybackProgressCallback mListener;
        PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
        long[] mPositions;
        int mPositionsLength;
        final SeekBar mProgressBar;
        BoundData mSecondaryBoundData;
        final ViewGroup mSecondaryControlsDock;
        ControlBarPresenter.ViewHolder mSecondaryControlsVh;
        long mSecondaryProgressInMs;
        PlaybackSeekUi.Client mSeekClient;
        PlaybackSeekDataProvider mSeekDataProvider;
        Object mSelectedItem;
        Presenter.ViewHolder mSelectedViewHolder;
        final StringBuilder mTempBuilder;
        int mThumbHeroIndex;
        PlaybackSeekDataProvider.ResultCallback mThumbResult;
        final ThumbsBar mThumbsBar;
        final TextView mTotalTime;
        long mTotalTimeInMs;

        void updateProgressInSeek(boolean forward) {
            long newPos;
            int thumbHeroIndex;
            long pos = this.mCurrentTimeInMs;
            int i = this.mPositionsLength;
            if (i > 0) {
                int index = Arrays.binarySearch(this.mPositions, 0, i, pos);
                if (forward) {
                    if (index >= 0) {
                        if (index < this.mPositionsLength - 1) {
                            newPos = this.mPositions[index + 1];
                            thumbHeroIndex = index + 1;
                        } else {
                            newPos = this.mTotalTimeInMs;
                            thumbHeroIndex = index;
                        }
                    } else {
                        int insertIndex = (-1) - index;
                        if (insertIndex <= this.mPositionsLength - 1) {
                            newPos = this.mPositions[insertIndex];
                            thumbHeroIndex = insertIndex;
                        } else {
                            long newPos2 = this.mTotalTimeInMs;
                            thumbHeroIndex = insertIndex > 0 ? insertIndex - 1 : 0;
                            newPos = newPos2;
                        }
                    }
                } else if (index >= 0) {
                    if (index > 0) {
                        newPos = this.mPositions[index - 1];
                        thumbHeroIndex = index - 1;
                    } else {
                        newPos = 0;
                        thumbHeroIndex = 0;
                    }
                } else {
                    int insertIndex2 = (-1) - index;
                    if (insertIndex2 > 0) {
                        int i2 = insertIndex2 - 1;
                        newPos = this.mPositions[insertIndex2 - 1];
                        thumbHeroIndex = i2;
                    } else {
                        newPos = 0;
                        thumbHeroIndex = 0;
                    }
                }
                updateThumbsInSeek(thumbHeroIndex, forward);
            } else {
                long interval = (long) (this.mTotalTimeInMs * PlaybackTransportRowPresenter.this.getDefaultSeekIncrement());
                long newPos3 = (forward ? interval : -interval) + pos;
                if (newPos3 > this.mTotalTimeInMs) {
                    newPos = this.mTotalTimeInMs;
                } else if (newPos3 >= 0) {
                    newPos = newPos3;
                } else {
                    newPos = 0;
                }
            }
            double ratio = newPos / this.mTotalTimeInMs;
            this.mProgressBar.setProgress((int) (2.147483647E9d * ratio));
            this.mSeekClient.onSeekPositionChanged(newPos);
        }

        void updateThumbsInSeek(int thumbHeroIndex, boolean forward) {
            int oldEnd;
            int oldStart;
            int i;
            if (this.mThumbHeroIndex == thumbHeroIndex) {
                return;
            }
            int totalNum = this.mThumbsBar.getChildCount();
            if (totalNum < 0 || (totalNum & 1) == 0) {
                throw new RuntimeException();
            }
            int heroChildIndex = totalNum / 2;
            int start = Math.max(thumbHeroIndex - (totalNum / 2), 0);
            int end = Math.min((totalNum / 2) + thumbHeroIndex, this.mPositionsLength - 1);
            int newRequestEnd = this.mThumbHeroIndex;
            if (newRequestEnd < 0) {
                oldStart = start;
                oldEnd = end;
            } else {
                forward = thumbHeroIndex > newRequestEnd;
                int oldStart2 = Math.max(this.mThumbHeroIndex - (totalNum / 2), 0);
                int oldEnd2 = Math.min(this.mThumbHeroIndex + (totalNum / 2), this.mPositionsLength - 1);
                if (forward) {
                    int newRequestStart = Math.max(oldEnd2 + 1, start);
                    for (int i2 = start; i2 <= newRequestStart - 1; i2++) {
                        ThumbsBar thumbsBar = this.mThumbsBar;
                        thumbsBar.setThumbBitmap((i2 - thumbHeroIndex) + heroChildIndex, thumbsBar.getThumbBitmap((i2 - this.mThumbHeroIndex) + heroChildIndex));
                    }
                    oldStart = newRequestStart;
                    oldEnd = end;
                } else {
                    int newRequestEnd2 = Math.min(oldStart2 - 1, end);
                    for (int i3 = end; i3 >= newRequestEnd2 + 1; i3--) {
                        ThumbsBar thumbsBar2 = this.mThumbsBar;
                        thumbsBar2.setThumbBitmap((i3 - thumbHeroIndex) + heroChildIndex, thumbsBar2.getThumbBitmap((i3 - this.mThumbHeroIndex) + heroChildIndex));
                    }
                    oldEnd = newRequestEnd2;
                    oldStart = start;
                }
            }
            this.mThumbHeroIndex = thumbHeroIndex;
            if (forward) {
                for (int i4 = oldStart; i4 <= oldEnd; i4++) {
                    this.mSeekDataProvider.getThumbnail(i4, this.mThumbResult);
                }
            } else {
                for (int i5 = oldEnd; i5 >= oldStart; i5--) {
                    this.mSeekDataProvider.getThumbnail(i5, this.mThumbResult);
                }
            }
            int childIndex = 0;
            while (true) {
                i = this.mThumbHeroIndex;
                if (childIndex >= (heroChildIndex - i) + start) {
                    break;
                }
                this.mThumbsBar.setThumbBitmap(childIndex, null);
                childIndex++;
            }
            int childIndex2 = heroChildIndex + end;
            for (int childIndex3 = (childIndex2 - i) + 1; childIndex3 < totalNum; childIndex3++) {
                this.mThumbsBar.setThumbBitmap(childIndex3, null);
            }
        }

        boolean onForward() {
            if (!startSeek()) {
                return false;
            }
            updateProgressInSeek(true);
            return true;
        }

        boolean onBackward() {
            if (!startSeek()) {
                return false;
            }
            updateProgressInSeek(false);
            return true;
        }

        public ViewHolder(View rootView, Presenter descriptionPresenter) {
            super(rootView);
            this.mTotalTimeInMs = Long.MIN_VALUE;
            this.mCurrentTimeInMs = Long.MIN_VALUE;
            this.mTempBuilder = new StringBuilder();
            this.mControlsBoundData = new BoundData();
            this.mSecondaryBoundData = new BoundData();
            this.mThumbHeroIndex = -1;
            this.mListener = new PlaybackControlsRow.OnPlaybackProgressCallback() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.1
                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onCurrentPositionChanged(PlaybackControlsRow row, long ms) {
                    ViewHolder.this.setCurrentPosition(ms);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onDurationChanged(PlaybackControlsRow row, long ms) {
                    ViewHolder.this.setTotalTime(ms);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onBufferedPositionChanged(PlaybackControlsRow row, long ms) {
                    ViewHolder.this.setBufferedPosition(ms);
                }
            };
            this.mThumbResult = new PlaybackSeekDataProvider.ResultCallback() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.2
                @Override // androidx.leanback.widget.PlaybackSeekDataProvider.ResultCallback
                public void onThumbnailLoaded(Bitmap bitmap, int index) {
                    int childIndex = index - (ViewHolder.this.mThumbHeroIndex - (ViewHolder.this.mThumbsBar.getChildCount() / 2));
                    if (childIndex < 0 || childIndex >= ViewHolder.this.mThumbsBar.getChildCount()) {
                        return;
                    }
                    ViewHolder.this.mThumbsBar.setThumbBitmap(childIndex, bitmap);
                }
            };
            this.mImageView = (ImageView) rootView.findViewById(R.id.image);
            this.mDescriptionDock = (ViewGroup) rootView.findViewById(R.id.description_dock);
            this.mCurrentTime = (TextView) rootView.findViewById(R.id.current_time);
            this.mTotalTime = (TextView) rootView.findViewById(R.id.total_time);
            SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.playback_progress);
            this.mProgressBar = seekBar;
            seekBar.setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PlaybackTransportRowPresenter.this.onProgressBarClicked(ViewHolder.this);
                }
            });
            this.mProgressBar.setOnKeyListener(new View.OnKeyListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.4
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                    if (keyCode != 4) {
                        if (keyCode != 66) {
                            if (keyCode != 69) {
                                if (keyCode != 81) {
                                    if (keyCode != 111) {
                                        if (keyCode != 89) {
                                            if (keyCode != 90) {
                                                switch (keyCode) {
                                                    case 19:
                                                    case 20:
                                                        return ViewHolder.this.mInSeek;
                                                    case 21:
                                                        break;
                                                    case 22:
                                                        break;
                                                    case 23:
                                                        break;
                                                    default:
                                                        return false;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (keyEvent.getAction() == 0) {
                                    ViewHolder.this.onForward();
                                }
                                return true;
                            }
                            if (keyEvent.getAction() == 0) {
                                ViewHolder.this.onBackward();
                            }
                            return true;
                        }
                        if (!ViewHolder.this.mInSeek) {
                            return false;
                        }
                        if (keyEvent.getAction() == 1) {
                            ViewHolder.this.stopSeek(false);
                        }
                        return true;
                    }
                    if (!ViewHolder.this.mInSeek) {
                        return false;
                    }
                    if (keyEvent.getAction() == 1) {
                        ViewHolder.this.stopSeek(Build.VERSION.SDK_INT < 21 || !ViewHolder.this.mProgressBar.isAccessibilityFocused());
                    }
                    return true;
                }
            });
            this.mProgressBar.setAccessibilitySeekListener(new SeekBar.AccessibilitySeekListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.5
                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekForward() {
                    return ViewHolder.this.onForward();
                }

                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekBackward() {
                    return ViewHolder.this.onBackward();
                }
            });
            this.mProgressBar.setMax(Integer.MAX_VALUE);
            this.mControlsDock = (ViewGroup) rootView.findViewById(R.id.controls_dock);
            this.mSecondaryControlsDock = (ViewGroup) rootView.findViewById(R.id.secondary_controls_dock);
            Presenter.ViewHolder viewHolderOnCreateViewHolder = descriptionPresenter == null ? null : descriptionPresenter.onCreateViewHolder(this.mDescriptionDock);
            this.mDescriptionViewHolder = viewHolderOnCreateViewHolder;
            if (viewHolderOnCreateViewHolder != null) {
                this.mDescriptionDock.addView(viewHolderOnCreateViewHolder.view);
            }
            this.mThumbsBar = (ThumbsBar) rootView.findViewById(R.id.thumbs_row);
        }

        public final Presenter.ViewHolder getDescriptionViewHolder() {
            return this.mDescriptionViewHolder;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi
        public void setPlaybackSeekUiClient(PlaybackSeekUi.Client client) {
            this.mSeekClient = client;
        }

        boolean startSeek() {
            if (this.mInSeek) {
                return true;
            }
            PlaybackSeekUi.Client client = this.mSeekClient;
            if (client == null || !client.isSeekEnabled() || this.mTotalTimeInMs <= 0) {
                return false;
            }
            this.mInSeek = true;
            this.mSeekClient.onSeekStarted();
            PlaybackSeekDataProvider playbackSeekDataProvider = this.mSeekClient.getPlaybackSeekDataProvider();
            this.mSeekDataProvider = playbackSeekDataProvider;
            long[] seekPositions = playbackSeekDataProvider != null ? playbackSeekDataProvider.getSeekPositions() : null;
            this.mPositions = seekPositions;
            if (seekPositions != null) {
                int pos = Arrays.binarySearch(seekPositions, this.mTotalTimeInMs);
                if (pos >= 0) {
                    this.mPositionsLength = pos + 1;
                } else {
                    this.mPositionsLength = (-1) - pos;
                }
            } else {
                this.mPositionsLength = 0;
            }
            this.mControlsVh.view.setVisibility(8);
            this.mSecondaryControlsVh.view.setVisibility(4);
            this.mDescriptionViewHolder.view.setVisibility(4);
            this.mThumbsBar.setVisibility(0);
            return true;
        }

        void stopSeek(boolean cancelled) {
            if (!this.mInSeek) {
                return;
            }
            this.mInSeek = false;
            this.mSeekClient.onSeekFinished(cancelled);
            PlaybackSeekDataProvider playbackSeekDataProvider = this.mSeekDataProvider;
            if (playbackSeekDataProvider != null) {
                playbackSeekDataProvider.reset();
            }
            this.mThumbHeroIndex = -1;
            this.mThumbsBar.clearThumbBitmaps();
            this.mSeekDataProvider = null;
            this.mPositions = null;
            this.mPositionsLength = 0;
            this.mControlsVh.view.setVisibility(0);
            this.mSecondaryControlsVh.view.setVisibility(0);
            this.mDescriptionViewHolder.view.setVisibility(0);
            this.mThumbsBar.setVisibility(4);
        }

        void dispatchItemSelection() {
            if (!isSelected()) {
                return;
            }
            if (this.mSelectedViewHolder == null) {
                if (getOnItemViewSelectedListener() != null) {
                    getOnItemViewSelectedListener().onItemSelected(null, null, this, getRow());
                }
            } else if (getOnItemViewSelectedListener() != null) {
                getOnItemViewSelectedListener().onItemSelected(this.mSelectedViewHolder, this.mSelectedItem, this, getRow());
            }
        }

        Presenter getPresenter(boolean primary) {
            ObjectAdapter adapter;
            if (primary) {
                adapter = ((PlaybackControlsRow) getRow()).getPrimaryActionsAdapter();
            } else {
                adapter = ((PlaybackControlsRow) getRow()).getSecondaryActionsAdapter();
            }
            if (adapter == null) {
                return null;
            }
            if (!(adapter.getPresenterSelector() instanceof ControlButtonPresenterSelector)) {
                return adapter.getPresenter(adapter.size() > 0 ? adapter.get(0) : null);
            }
            ControlButtonPresenterSelector selector = (ControlButtonPresenterSelector) adapter.getPresenterSelector();
            return selector.getSecondaryPresenter();
        }

        public final TextView getDurationView() {
            return this.mTotalTime;
        }

        protected void onSetDurationLabel(long totalTimeMs) {
            if (this.mTotalTime != null) {
                PlaybackTransportRowPresenter.formatTime(totalTimeMs, this.mTempBuilder);
                this.mTotalTime.setText(this.mTempBuilder.toString());
            }
        }

        void setTotalTime(long totalTimeMs) {
            if (this.mTotalTimeInMs != totalTimeMs) {
                this.mTotalTimeInMs = totalTimeMs;
                onSetDurationLabel(totalTimeMs);
            }
        }

        public final TextView getCurrentPositionView() {
            return this.mCurrentTime;
        }

        protected void onSetCurrentPositionLabel(long currentTimeMs) {
            if (this.mCurrentTime != null) {
                PlaybackTransportRowPresenter.formatTime(currentTimeMs, this.mTempBuilder);
                this.mCurrentTime.setText(this.mTempBuilder.toString());
            }
        }

        void setCurrentPosition(long currentTimeMs) {
            if (currentTimeMs != this.mCurrentTimeInMs) {
                this.mCurrentTimeInMs = currentTimeMs;
                onSetCurrentPositionLabel(currentTimeMs);
            }
            if (!this.mInSeek) {
                int progressRatio = 0;
                long j = this.mTotalTimeInMs;
                if (j > 0) {
                    double ratio = this.mCurrentTimeInMs / j;
                    progressRatio = (int) (2.147483647E9d * ratio);
                }
                this.mProgressBar.setProgress(progressRatio);
            }
        }

        void setBufferedPosition(long progressMs) {
            this.mSecondaryProgressInMs = progressMs;
            double ratio = progressMs / this.mTotalTimeInMs;
            double progressRatio = 2.147483647E9d * ratio;
            this.mProgressBar.setSecondaryProgress((int) progressRatio);
        }
    }

    static void formatTime(long ms, StringBuilder sb) {
        sb.setLength(0);
        if (ms < 0) {
            sb.append("--");
            return;
        }
        long seconds = ms / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long seconds2 = seconds - (minutes * 60);
        long minutes2 = minutes - (60 * hours);
        if (hours > 0) {
            sb.append(hours);
            sb.append(':');
            if (minutes2 < 10) {
                sb.append('0');
            }
        }
        sb.append(minutes2);
        sb.append(':');
        if (seconds2 < 10) {
            sb.append('0');
        }
        sb.append(seconds2);
    }

    public PlaybackTransportRowPresenter() {
        setHeaderPresenter(null);
        setSelectEffectEnabled(false);
        ControlBarPresenter controlBarPresenter = new ControlBarPresenter(R.layout.lb_control_bar);
        this.mPlaybackControlsPresenter = controlBarPresenter;
        controlBarPresenter.setDefaultFocusToMiddle(false);
        ControlBarPresenter controlBarPresenter2 = new ControlBarPresenter(R.layout.lb_control_bar);
        this.mSecondaryControlsPresenter = controlBarPresenter2;
        controlBarPresenter2.setDefaultFocusToMiddle(false);
        this.mPlaybackControlsPresenter.setOnControlSelectedListener(this.mOnControlSelectedListener);
        this.mSecondaryControlsPresenter.setOnControlSelectedListener(this.mOnControlSelectedListener);
        this.mPlaybackControlsPresenter.setOnControlClickedListener(this.mOnControlClickedListener);
        this.mSecondaryControlsPresenter.setOnControlClickedListener(this.mOnControlClickedListener);
    }

    public void setDescriptionPresenter(Presenter descriptionPresenter) {
        this.mDescriptionPresenter = descriptionPresenter;
    }

    public void setOnActionClickedListener(OnActionClickedListener listener) {
        this.mOnActionClickedListener = listener;
    }

    public OnActionClickedListener getOnActionClickedListener() {
        return this.mOnActionClickedListener;
    }

    public void setProgressColor(int color) {
        this.mProgressColor = color;
        this.mProgressColorSet = true;
    }

    public int getProgressColor() {
        return this.mProgressColor;
    }

    public void setSecondaryProgressColor(int color) {
        this.mSecondaryProgressColor = color;
        this.mSecondaryProgressColorSet = true;
    }

    public int getSecondaryProgressColor() {
        return this.mSecondaryProgressColor;
    }

    @Override // androidx.leanback.widget.PlaybackRowPresenter
    public void onReappear(RowPresenter.ViewHolder rowViewHolder) {
        ViewHolder vh = (ViewHolder) rowViewHolder;
        if (vh.view.hasFocus()) {
            vh.mProgressBar.requestFocus();
        }
    }

    private static int getDefaultProgressColor(Context context) {
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.playbackProgressPrimaryColor, outValue, true)) {
            return context.getResources().getColor(outValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_playback_progress_color_no_theme);
    }

    private static int getDefaultSecondaryProgressColor(Context context) {
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.playbackProgressSecondaryColor, outValue, true)) {
            return context.getResources().getColor(outValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_playback_progress_secondary_color_no_theme);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_playback_transport_controls_row, parent, false);
        ViewHolder vh = new ViewHolder(v, this.mDescriptionPresenter);
        initRow(vh);
        return vh;
    }

    private void initRow(final ViewHolder vh) {
        int defaultSecondaryProgressColor;
        vh.mControlsVh = (ControlBarPresenter.ViewHolder) this.mPlaybackControlsPresenter.onCreateViewHolder(vh.mControlsDock);
        vh.mProgressBar.setProgressColor(this.mProgressColorSet ? this.mProgressColor : getDefaultProgressColor(vh.mControlsDock.getContext()));
        SeekBar seekBar = vh.mProgressBar;
        if (this.mSecondaryProgressColorSet) {
            defaultSecondaryProgressColor = this.mSecondaryProgressColor;
        } else {
            defaultSecondaryProgressColor = getDefaultSecondaryProgressColor(vh.mControlsDock.getContext());
        }
        seekBar.setSecondaryProgressColor(defaultSecondaryProgressColor);
        vh.mControlsDock.addView(vh.mControlsVh.view);
        vh.mSecondaryControlsVh = (ControlBarPresenter.ViewHolder) this.mSecondaryControlsPresenter.onCreateViewHolder(vh.mSecondaryControlsDock);
        vh.mSecondaryControlsDock.addView(vh.mSecondaryControlsVh.view);
        ((PlaybackTransportRowView) vh.view.findViewById(R.id.transport_row)).setOnUnhandledKeyListener(new PlaybackTransportRowView.OnUnhandledKeyListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.3
            @Override // androidx.leanback.widget.PlaybackTransportRowView.OnUnhandledKeyListener
            public boolean onUnhandledKey(KeyEvent event) {
                if (vh.getOnKeyListener() != null) {
                    return vh.getOnKeyListener().onKey(vh.view, event.getKeyCode(), event);
                }
                return false;
            }
        });
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);
        ViewHolder vh = (ViewHolder) holder;
        PlaybackControlsRow row = (PlaybackControlsRow) vh.getRow();
        if (row.getItem() == null) {
            vh.mDescriptionDock.setVisibility(8);
        } else {
            vh.mDescriptionDock.setVisibility(0);
            if (vh.mDescriptionViewHolder != null) {
                this.mDescriptionPresenter.onBindViewHolder(vh.mDescriptionViewHolder, row.getItem());
            }
        }
        if (row.getImageDrawable() == null) {
            vh.mImageView.setVisibility(8);
        } else {
            vh.mImageView.setVisibility(0);
        }
        vh.mImageView.setImageDrawable(row.getImageDrawable());
        vh.mControlsBoundData.adapter = row.getPrimaryActionsAdapter();
        vh.mControlsBoundData.presenter = vh.getPresenter(true);
        vh.mControlsBoundData.mRowViewHolder = vh;
        this.mPlaybackControlsPresenter.onBindViewHolder(vh.mControlsVh, vh.mControlsBoundData);
        vh.mSecondaryBoundData.adapter = row.getSecondaryActionsAdapter();
        vh.mSecondaryBoundData.presenter = vh.getPresenter(false);
        vh.mSecondaryBoundData.mRowViewHolder = vh;
        this.mSecondaryControlsPresenter.onBindViewHolder(vh.mSecondaryControlsVh, vh.mSecondaryBoundData);
        vh.setTotalTime(row.getDuration());
        vh.setCurrentPosition(row.getCurrentPosition());
        vh.setBufferedPosition(row.getBufferedPosition());
        row.setOnPlaybackProgressChangedListener(vh.mListener);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        PlaybackControlsRow row = (PlaybackControlsRow) vh.getRow();
        if (vh.mDescriptionViewHolder != null) {
            this.mDescriptionPresenter.onUnbindViewHolder(vh.mDescriptionViewHolder);
        }
        this.mPlaybackControlsPresenter.onUnbindViewHolder(vh.mControlsVh);
        this.mSecondaryControlsPresenter.onUnbindViewHolder(vh.mSecondaryControlsVh);
        row.setOnPlaybackProgressChangedListener(null);
        super.onUnbindRowViewHolder(holder);
    }

    protected void onProgressBarClicked(ViewHolder vh) {
        if (vh != null) {
            if (vh.mPlayPauseAction == null) {
                vh.mPlayPauseAction = new PlaybackControlsRow.PlayPauseAction(vh.view.getContext());
            }
            if (vh.getOnItemViewClickedListener() != null) {
                vh.getOnItemViewClickedListener().onItemClicked(vh, vh.mPlayPauseAction, vh, vh.getRow());
            }
            OnActionClickedListener onActionClickedListener = this.mOnActionClickedListener;
            if (onActionClickedListener != null) {
                onActionClickedListener.onActionClicked(vh.mPlayPauseAction);
            }
        }
    }

    public void setDefaultSeekIncrement(float ratio) {
        this.mDefaultSeekIncrement = ratio;
    }

    public float getDefaultSeekIncrement() {
        return this.mDefaultSeekIncrement;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewSelected(RowPresenter.ViewHolder vh, boolean selected) {
        super.onRowViewSelected(vh, selected);
        if (selected) {
            ((ViewHolder) vh).dispatchItemSelection();
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewAttachedToWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewAttachedToWindow(vh);
        Presenter presenter = this.mDescriptionPresenter;
        if (presenter != null) {
            presenter.onViewAttachedToWindow(((ViewHolder) vh).mDescriptionViewHolder);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewDetachedFromWindow(vh);
        Presenter presenter = this.mDescriptionPresenter;
        if (presenter != null) {
            presenter.onViewDetachedFromWindow(((ViewHolder) vh).mDescriptionViewHolder);
        }
    }
}
