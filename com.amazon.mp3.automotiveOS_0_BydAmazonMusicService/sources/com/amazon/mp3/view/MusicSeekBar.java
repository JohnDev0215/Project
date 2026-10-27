package com.amazon.mp3.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.util.RunnableDebouncer;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;

/* JADX INFO: loaded from: classes4.dex */
public class MusicSeekBar extends SeekBar implements View.OnTouchListener {
    private final Runnable mDismissTimeProgressRunnable;
    private View.OnTouchListener mListener;
    private final SeekBar.OnSeekBarChangeListener mOnUserDraggedListener;
    private final PlaybackController mPlaybackController;
    private TextView mProgressTime;
    private RunnableDebouncer mRunnableDebouncer;
    private boolean mSeekBarActiveMode;
    private int[] mSeekBarLocation;
    private int mSeekBarMaxProgress;
    private final View.OnTouchListener mSeekBarOnTouchListener;
    private int mSeekBarPopOverOffset;
    private PopupWindow mTimeProgress;
    private TextView mTrackTime;
    private final Runnable mUpdateSeekBarProgressRunnable;

    public MusicSeekBar(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicSeekBar(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mSeekBarMaxProgress = 1;
        this.mRunnableDebouncer = new RunnableDebouncer(new Handler(Looper.getMainLooper()));
        this.mPlaybackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        this.mSeekBarOnTouchListener = new View.OnTouchListener() { // from class: com.amazon.mp3.view.MusicSeekBar.1
            private int mSeekBarX = -1;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (ScreenUtil.isPortrait()) {
                    MusicSeekBar.this.mSeekBarActiveMode = true;
                    float rawX = motionEvent.getRawX();
                    if (this.mSeekBarX == -1) {
                        int[] iArr = new int[2];
                        MusicSeekBar.this.getLocationInWindow(iArr);
                        this.mSeekBarX = iArr[0];
                    }
                    if (getDisplayX(rawX) + (MusicSeekBar.this.mTimeProgress.getWidth() / 2) < this.mSeekBarX) {
                        return false;
                    }
                    int seekBarY = MusicSeekBar.this.getSeekBarY();
                    MusicSeekBar.this.mTimeProgress.showAtLocation(((Activity) MusicSeekBar.this.getContext()).getWindow().getDecorView(), 0, getDisplayX(rawX), seekBarY);
                    MusicSeekBar musicSeekBar = MusicSeekBar.this;
                    MusicSeekBar.this.mTimeProgress.update(getDisplayX(rawX), seekBarY, musicSeekBar.scaleWidthByFontScaling(musicSeekBar.getResources().getDimensionPixelSize(R.dimen.now_playing_popover_width)), MusicSeekBar.this.getResources().getDimensionPixelSize(R.dimen.now_playing_popover_height));
                }
                return false;
            }

            private int getDisplayX(float x) {
                return ((int) x) - (MusicSeekBar.this.mTimeProgress.getWidth() / 2);
            }
        };
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.mp3.view.MusicSeekBar.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                if (fromUser || MusicSeekBar.this.mSeekBarActiveMode) {
                    long durationMillis = MusicSeekBar.this.mPlaybackController.getDurationMillis();
                    String cachedFormattedTime = StringUtil.getCachedFormattedTime(progress);
                    String cachedFormattedTime2 = StringUtil.getCachedFormattedTime(durationMillis);
                    View contentView = MusicSeekBar.this.mTimeProgress.getContentView();
                    TextView textView = (TextView) contentView.findViewById(R.id.NowPlayingProgressPopoverText);
                    TextView textView2 = (TextView) contentView.findViewById(R.id.NowPlayingDurationPopoverText);
                    textView.setText(cachedFormattedTime);
                    textView2.setText(cachedFormattedTime2);
                    if (ScreenUtil.isPortrait()) {
                        int left = ((int) (MusicSeekBar.this.getLeft() + (MusicSeekBar.this.getWidth() * (MusicSeekBar.this.getProgress() / MusicSeekBar.this.mSeekBarMaxProgress)))) - (MusicSeekBar.this.mTimeProgress.getWidth() / 2);
                        int seekBarY = MusicSeekBar.this.getSeekBarY();
                        MusicSeekBar.this.mTimeProgress.showAtLocation(((Activity) MusicSeekBar.this.getContext()).getWindow().getDecorView(), 0, left, seekBarY);
                        MusicSeekBar musicSeekBar = MusicSeekBar.this;
                        MusicSeekBar.this.mTimeProgress.update(left, seekBarY, musicSeekBar.scaleWidthByFontScaling(musicSeekBar.getResources().getDimensionPixelSize(R.dimen.now_playing_popover_width)), MusicSeekBar.this.getResources().getDimensionPixelSize(R.dimen.now_playing_popover_height));
                    }
                    if (!fromUser || CastingUtil.isCasting()) {
                        return;
                    }
                    MusicSeekBar.this.onUserUpdatedSeekBar();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(final SeekBar seekBar) {
                if (!CastingUtil.isCasting()) {
                    MusicSeekBar.this.onUserUpdatedSeekBar();
                }
                MusicSeekBar musicSeekBar = MusicSeekBar.this;
                musicSeekBar.setOnTouchListener(musicSeekBar.mSeekBarOnTouchListener);
                MusicSeekBar.this.mRunnableDebouncer.remove(MusicSeekBar.this.mUpdateSeekBarProgressRunnable);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(final SeekBar seekBar) {
                MusicSeekBar.this.onUserUpdatedSeekBar();
                MusicSeekBar.this.mRunnableDebouncer.post(MusicSeekBar.this.mUpdateSeekBarProgressRunnable);
            }
        };
        this.mOnUserDraggedListener = onSeekBarChangeListener;
        this.mDismissTimeProgressRunnable = new Runnable() { // from class: com.amazon.mp3.view.MusicSeekBar.3
            @Override // java.lang.Runnable
            public void run() {
                MusicSeekBar.this.mSeekBarActiveMode = false;
                MusicSeekBar.this.mTimeProgress.dismiss();
            }
        };
        this.mUpdateSeekBarProgressRunnable = new Runnable() { // from class: com.amazon.mp3.view.MusicSeekBar.4
            @Override // java.lang.Runnable
            public void run() {
                if (MusicSeekBar.this.mPlaybackController.getDurationMillis() <= 0) {
                    return;
                }
                long positionMillis = MusicSeekBar.this.mPlaybackController.getPositionMillis();
                if (MusicSeekBar.this.getVisibility() == 0 && positionMillis >= 0) {
                    MusicSeekBar.this.setProgress((int) positionMillis);
                }
                MusicSeekBar.this.updateTimeInfo();
                if (MusicSeekBar.this.mPlaybackController.getPlayStatus().isPositionChanging()) {
                    MusicSeekBar.this.mRunnableDebouncer.postDelayed(this, 16L);
                }
            }
        };
        setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.mTimeProgress = new PopupWindow(((Activity) getContext()).getLayoutInflater().inflate(R.layout.library_now_playing_progress_popover, (ViewGroup) null));
        this.mSeekBarPopOverOffset = getResources().getDimensionPixelOffset(R.dimen.now_playing_popover_y_offset);
        setMax(1);
    }

    @Override // android.view.View
    public void setOnTouchListener(final View.OnTouchListener listener) {
        super.setOnTouchListener(this);
        this.mListener = listener;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(final View v, final MotionEvent event) {
        return this.mListener.onTouch(v, event);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mTimeProgress.dismiss();
    }

    public SeekBar.OnSeekBarChangeListener getSeekBarChangeListener() {
        return this.mOnUserDraggedListener;
    }

    public void setProgressTimeView(final TextView view) {
        this.mProgressTime = view;
    }

    public void setTrackTimeView(final TextView view) {
        this.mTrackTime = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeInfo() {
        long positionMillis = this.mPlaybackController.getPositionMillis();
        long durationMillis = this.mPlaybackController.getDurationMillis();
        long jMin = Math.min(positionMillis, durationMillis);
        if (this.mProgressTime == null || this.mTrackTime == null) {
            return;
        }
        String cachedFormattedTime = jMin != Long.MIN_VALUE ? StringUtil.getCachedFormattedTime(jMin) : "0:00";
        String cachedFormattedTime2 = durationMillis != Long.MIN_VALUE ? StringUtil.getCachedFormattedTime(durationMillis) : "--:--";
        this.mProgressTime.setText(cachedFormattedTime);
        this.mTrackTime.setText(cachedFormattedTime2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserUpdatedSeekBar() {
        this.mPlaybackController.seek(getProgress(), 0);
        this.mRunnableDebouncer.remove(this.mDismissTimeProgressRunnable);
        this.mRunnableDebouncer.postDelayed(this.mDismissTimeProgressRunnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int scaleWidthByFontScaling(final int value) {
        return (int) Math.ceil(value * getResources().getConfiguration().fontScale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSeekBarY() {
        if (this.mSeekBarLocation == null) {
            this.mSeekBarLocation = new int[2];
        }
        getLocationOnScreen(this.mSeekBarLocation);
        return this.mSeekBarLocation[1] - this.mSeekBarPopOverOffset;
    }
}
