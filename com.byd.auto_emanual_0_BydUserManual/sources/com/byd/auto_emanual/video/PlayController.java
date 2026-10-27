package com.byd.auto_emanual.video;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.ActivityChooserView;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.Classify;
import com.byd.auto_emanual.util.LoadingUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.video.util.TimeFormatter;

/* JADX INFO: loaded from: classes.dex */
public class PlayController extends LinearLayout {
    public static final int MAX_OF_SEEK_BAR = 10000;
    private static final String TAG = "PlayController";
    public static final String ZOOM_IN = "zoomIn";
    public static final String ZOOM_OUT = "zoomOut";
    private TextView currentProgress;
    private RelativeLayout fullScreen;
    private ImageView fullScreenIcon;
    private boolean isPauseByUser;
    private boolean isSeekByUser;
    private TextView mFast;
    private ImageView mLoading;
    private LinearLayout mLoadingContainer;
    private LinearLayout mNetError;
    private ImageView mStartPauseBig;
    float oldY;
    private View.OnClickListener onClickListener;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    private SeekBar seekBar;
    private RelativeLayout startPause;
    private ImageView startPauseIcon;
    private TextView total_duration;
    private VideoView videoView;
    float y;
    private ZoomListener zoomListener;

    public interface ZoomListener {
        void doZoom(String zoomTag);
    }

    private int getActualProgress(int current, int all) {
        return current;
    }

    public void setStartPause(boolean isStart) {
    }

    public PlayController(Context context) {
        this(context, null);
    }

    public PlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onClickListener = new View.OnClickListener() { // from class: com.byd.auto_emanual.video.PlayController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MLog.d(PlayController.TAG, "onClick " + v);
                if (PlayController.this.videoView == null) {
                    MLog.d(PlayController.TAG, "please set VideoView first.");
                }
                int id = v.getId();
                if (id == R.id.full_screen) {
                    PlayController.this.doFullScreenClick();
                } else {
                    if (id != R.id.start_pause) {
                        return;
                    }
                    PlayController.this.doStartPauseClick();
                }
            }
        };
        this.onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.byd.auto_emanual.video.PlayController.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    PlayController.this.videoView.showPlayController();
                    PlayController.this.mStartPauseBig.setVisibility(4);
                    PlayController.this.mFast.setVisibility(0);
                    PlayController.this.setCurrentProgress(seekBar.getProgress());
                    PlayController.this.isSeekByUser = true;
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MLog.v(PlayController.TAG, "onStartTrackingTouch ");
                PlayController.this.videoView.updateSeekBarProgress(SeekBarState.STOP, seekBar.getProgress());
                PlayController.this.videoView.pause();
                PlayController.this.mStartPauseBig.setVisibility(4);
                PlayController.this.mFast.setVisibility(0);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                MLog.v(PlayController.TAG, "onStopTrackingTouch ");
                PlayController.this.videoView.seekTo(seekBar.getProgress());
                if (PlayController.this.videoView.isPlaying()) {
                    PlayController.this.videoView.updateSeekBarProgress(SeekBarState.GO, seekBar.getProgress());
                }
                PlayController.this.mFast.setText("");
                PlayController.this.mFast.setCompoundDrawables(null, new ColorDrawable(0), null, null);
                PlayController.this.mFast.setVisibility(4);
                PlayController.this.mStartPauseBig.setVisibility(0);
                PlayController.this.isSeekByUser = false;
            }
        };
        init(context);
    }

    private void init(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.video_player_controller_layout, (ViewGroup) this, true);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.full_screen);
        this.fullScreen = relativeLayout;
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$PlayController$Bwey0_cYUBxklwmWhZuaDZRxGyY
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$init$0$PlayController(view, motionEvent);
            }
        });
        this.fullScreenIcon = (ImageView) viewInflate.findViewById(R.id.full_screen_icon);
        RelativeLayout relativeLayout2 = (RelativeLayout) viewInflate.findViewById(R.id.start_pause);
        this.startPause = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$PlayController$Oolzc0sA1UvP46QEM8Z5-23vZzU
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$init$1$PlayController(view, motionEvent);
            }
        });
        this.startPauseIcon = (ImageView) viewInflate.findViewById(R.id.start_pause_icon);
        this.currentProgress = (TextView) viewInflate.findViewById(R.id.current_progress);
        this.total_duration = (TextView) viewInflate.findViewById(R.id.total_duration);
        this.fullScreen.setOnClickListener(this.onClickListener);
        this.startPause.setOnClickListener(this.onClickListener);
        this.currentProgress.setOnClickListener(this.onClickListener);
        this.total_duration.setOnClickListener(this.onClickListener);
        SeekBar seekBar = (SeekBar) viewInflate.findViewById(R.id.video_progress);
        this.seekBar = seekBar;
        seekBar.setOnClickListener(this.onClickListener);
        this.seekBar.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
    }

    public /* synthetic */ boolean lambda$init$0$PlayController(View view, MotionEvent motionEvent) {
        return doOnTouchEventOnView(motionEvent);
    }

    public /* synthetic */ boolean lambda$init$1$PlayController(View view, MotionEvent motionEvent) {
        return doOnTouchEventOnView(motionEvent);
    }

    private boolean doOnTouchEventOnView(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.oldY = event.getY();
            return false;
        }
        if (action != 1 || !ZOOM_OUT.equals(getZoomTag())) {
            return false;
        }
        this.y = event.getY();
        float f = getResources().getDisplayMetrics().heightPixels;
        String str = TAG;
        MLog.v(str, "onTouchEvent " + this.y + ", " + this.oldY + ", " + f);
        if (this.y - this.oldY >= 0.0f) {
            return false;
        }
        MLog.v(str, "onTouchEvent showNavigationBar");
        if (getVisibility() == 0) {
            this.videoView.hidePlayController();
        }
        return true;
    }

    private void startPause() {
        this.videoView.startPause();
    }

    private void fullScreen(String fullScreenTag) {
        ZoomListener zoomListener = this.zoomListener;
        if (zoomListener != null) {
            zoomListener.doZoom(fullScreenTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doFullScreenClick() {
        String str = (String) this.fullScreen.getTag();
        fullScreen(str);
        updateFullScreenState(str);
    }

    public void doStartPauseClick() {
        updateStartPauseAAA((String) this.startPause.getTag());
        startPause();
    }

    private void updateFullScreenState(String fullScreenTag) {
        if (fullScreenTag == null || ZOOM_IN.equals(fullScreenTag)) {
            this.fullScreenIcon.setBackground(getResources().getDrawable(R.drawable.icon_video_zoom_out, null));
            this.fullScreen.setTag(ZOOM_OUT);
        } else {
            this.fullScreenIcon.setBackground(getResources().getDrawable(R.drawable.icon_video_zoom_in, null));
            this.fullScreen.setTag(ZOOM_IN);
        }
    }

    public String getZoomTag() {
        return (String) this.fullScreen.getTag();
    }

    private void updateStartPauseAAA(String startOrPause) {
        if (startOrPause == null || "pause".equals(startOrPause)) {
            this.isPauseByUser = true;
            this.startPauseIcon.setBackground(getResources().getDrawable(R.drawable.icon_video_start, null));
            this.mStartPauseBig.setBackground(getResources().getDrawable(R.drawable.icon_video_start_big, null));
            this.startPause.setTag(Classify.START);
            return;
        }
        this.isPauseByUser = false;
        this.startPauseIcon.setBackground(getResources().getDrawable(R.drawable.icon_video_pause, null));
        this.mStartPauseBig.setBackground(getResources().getDrawable(R.drawable.icon_video_pause_big, null));
        this.startPause.setTag("pause");
    }

    public void setSeekBarMax(int max) {
        this.seekBar.setMax(max);
    }

    public void setSeekBarProgress(int current, int all) {
        this.seekBar.setProgress(getActualProgress(current, all), false);
    }

    public void setSeekBarSecondaryProgress(int current, int all) {
        this.seekBar.setSecondaryProgress(getSecondProgress(current, all));
    }

    public int getSecondaryProgress() {
        return this.seekBar.getSecondaryProgress();
    }

    public void updateStartPauseState(String startOrPause) {
        updateStartPauseAAA(startOrPause);
    }

    private int getSecondProgress(int current, int all) {
        int max = this.seekBar.getMax();
        if (max <= 100) {
            return 0;
        }
        double d = ((((double) current) * 1.0d) / ((double) all)) * ((double) max);
        return d > 2.147483647E9d ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) d;
    }

    public void setCurrentProgress(int progress) {
        String strReplaceAll = this.currentProgress.getText().toString().replaceAll(":", "");
        int i = -1;
        int i2 = (TextUtils.isEmpty(strReplaceAll) || !Utils.isNumeric(strReplaceAll)) ? -1 : Integer.parseInt(strReplaceAll);
        String strStringForTime = TimeFormatter.instance().stringForTime(progress);
        this.currentProgress.setText(strStringForTime);
        if (this.mFast.getVisibility() != 0) {
            return;
        }
        String strReplaceAll2 = strStringForTime.replaceAll(":", "");
        if (!TextUtils.isEmpty(strReplaceAll2) && Utils.isNumeric(strReplaceAll2)) {
            i = Integer.parseInt(strReplaceAll2);
        }
        if (i != i2) {
            MLog.d("TAG", i + ", " + i2);
        }
        if (i > i2) {
            this.mFast.setCompoundDrawables(null, getFastDrawable(R.drawable.icon_video_fast_forward), null, null);
        } else if (i < i2) {
            this.mFast.setCompoundDrawables(null, getFastDrawable(R.drawable.icon_video_fast_backward), null, null);
        }
        this.mFast.setText(strStringForTime);
    }

    private Drawable getFastDrawable(int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    public void setTotalDuration(int duration) {
        this.total_duration.setText(TimeFormatter.instance().stringForTime(duration));
    }

    public boolean isSeekByUser() {
        return this.isSeekByUser;
    }

    public void seekBarByUser(boolean isSeekByUser) {
        this.isSeekByUser = isSeekByUser;
    }

    public boolean isPauseByUser() {
        return this.isPauseByUser;
    }

    public void pauseByUser(boolean isPauseByUser) {
        this.isPauseByUser = isPauseByUser;
    }

    public void setVideoView(VideoView view) {
        this.videoView = view;
    }

    public void hideController() {
        setVisibility(4);
    }

    public void showPlayController() {
        setVisibility(0);
    }

    public void setOtherState(ImageView startPauseBig, TextView fast, ImageView loading, LinearLayout loadingContainer, LinearLayout netError) {
        this.mStartPauseBig = startPauseBig;
        this.mFast = fast;
        this.mLoading = loading;
        this.mLoadingContainer = loadingContainer;
        this.mNetError = netError;
    }

    public void hideLoading() {
        LoadingUtil.stopLoading(this.mLoading);
        this.mLoading.setVisibility(8);
        this.mLoadingContainer.setVisibility(8);
    }

    public void showLoading() {
        hideNetError();
        LoadingUtil.startLoading(getContext(), this.mLoading);
        this.mLoading.setVisibility(0);
        this.mLoadingContainer.setVisibility(0);
    }

    public void showNetError() {
        this.mNetError.setVisibility(0);
    }

    public void hideNetError() {
        this.mNetError.setVisibility(8);
    }

    public void setZoomListener(ZoomListener zoomListener) {
        this.zoomListener = zoomListener;
    }
}
