package com.byd.auto_emanual.video;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.PlayController;
import com.byd.auto_emanual.video.util.ZoomInOutHelper;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayer extends RelativeLayout implements PlayController.ZoomListener {
    private static final String TAG = "VideoPlayer";
    private BackClickListener backClickListener;
    private TextView fast;
    private View footerBackground;
    private View headerBackground;
    private ImageView loading;
    private LinearLayout loadingContainer;
    private ImageView mute;
    private LinearLayout netError;
    private TextView netErrorText;
    float oldY;
    private PlayController playController;
    private ImageView startPauseBig;
    private RelativeLayout videoBack;
    private TextView videoTitle;
    private VideoView videoView;
    float y;

    public interface BackClickListener {
        void onBackClick();
    }

    public VideoPlayer(Context context) {
        this(context, null);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.video_player_layout, (ViewGroup) this, true);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.video_back);
        this.videoBack = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoPlayer$sEb9oVWaygnduiaJBlH2jJdd4yY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$0$VideoPlayer(view);
            }
        });
        this.videoTitle = (TextView) viewInflate.findViewById(R.id.video_title);
        this.videoView = (VideoView) findViewById(R.id.video_view);
        this.playController = (PlayController) findViewById(R.id.play_controller);
        ImageView imageView = (ImageView) findViewById(R.id.mute);
        this.mute = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoPlayer$T3MEPEhEU451ww0WaOipRHcqIEI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$1$VideoPlayer(view);
            }
        });
        this.headerBackground = findViewById(R.id.video_header_background);
        this.footerBackground = findViewById(R.id.video_footer_background);
        ImageView imageView2 = (ImageView) findViewById(R.id.video_start_pause_big);
        this.startPauseBig = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoPlayer$6WYz_2KxzA-xt2_5Vi-A7Wn3lx0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$2$VideoPlayer(view);
            }
        });
        this.fast = (TextView) findViewById(R.id.video_fast);
        this.loading = (ImageView) findViewById(R.id.video_loading);
        this.loadingContainer = (LinearLayout) findViewById(R.id.video_loading_container);
        this.netError = (LinearLayout) findViewById(R.id.video_net_error);
        this.netErrorText = (TextView) findViewById(R.id.video_net_error_state_text);
        this.playController.setVideoView(this.videoView);
        this.playController.setOtherState(this.startPauseBig, this.fast, this.loading, this.loadingContainer, this.netError);
        this.playController.setZoomListener(this);
        this.videoView.setMediaController(this.playController);
        this.videoView.setVideoTitle(this.videoTitle);
        this.videoView.setBack(this.videoBack);
        this.videoView.setHeaderAndFooterBackground(this.headerBackground, this.footerBackground);
        this.videoView.setOtherState(this.startPauseBig);
        setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoPlayer$NHe37xeLQcROrDdcpJUmh0dQQrM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$3$VideoPlayer(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$VideoPlayer(View view) {
        BackClickListener backClickListener = this.backClickListener;
        if (backClickListener != null) {
            backClickListener.onBackClick();
        }
    }

    public /* synthetic */ void lambda$init$1$VideoPlayer(View view) {
        if (this.mute.getTag() == null || ((String) this.mute.getTag()).equals("off")) {
            this.mute.setImageDrawable(getResources().getDrawable(R.drawable.icon_video_sound_on, null));
            this.mute.setTag("on");
            setVolume(1.0f);
        } else {
            this.mute.setImageDrawable(getResources().getDrawable(R.drawable.icon_video_sound_off, null));
            this.mute.setTag("off");
            setVolume(0.0f);
        }
    }

    public /* synthetic */ void lambda$init$2$VideoPlayer(View view) {
        this.playController.doStartPauseClick();
    }

    public /* synthetic */ void lambda$init$3$VideoPlayer(View view) {
        this.videoView.onClick();
    }

    public void setDataPath(String path) {
        this.videoView.setDataPath(path);
    }

    public String getVideoPath(String path) {
        return this.videoView.getVideoPath(path);
    }

    public Downloader.IDownloadListener getDownloadListener() {
        return this.videoView.getDownloadListener();
    }

    public int getCurrentPosition() {
        return this.videoView.getCurrentPosition();
    }

    public void setVideoViewBackGround(Drawable drawable) {
        this.videoView.setBackground(drawable);
    }

    public void setVideoViewForeground(Drawable drawable) {
        this.videoView.setForeground(drawable);
    }

    public void setVideoTitle(String title) {
        this.videoTitle.setText(title);
    }

    public void setBackVisibility(int visibility) {
        this.videoBack.setVisibility(visibility);
    }

    public void setVideoTitle(int left, int top, int textColor, int typeFace) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.videoTitle.getLayoutParams();
        layoutParams.setMargins(left, top, layoutParams.rightMargin, layoutParams.bottomMargin);
        this.videoTitle.setLayoutParams(layoutParams);
        this.videoTitle.setGravity(BadgeDrawable.TOP_START);
        this.videoTitle.setTextColor(textColor);
        this.videoTitle.setTypeface(null, typeFace);
    }

    public void setPlayControllerVisibility(int visibility) {
        this.playController.setVisibility(visibility);
    }

    public void setMuteVisibility(int visibility) {
        this.mute.setVisibility(visibility);
    }

    public void setVolume(float volume) {
        this.videoView.setVolume(volume);
    }

    public void pause() {
        this.videoView.pause();
    }

    public void start() {
        this.videoView.start();
    }

    public void setLooping(boolean isLooping) {
        this.videoView.setLooping(isLooping);
    }

    public void setTopVideoPath(String path) {
        this.videoView.setTopVideoPath(path);
    }

    public boolean isPlaying() {
        return this.videoView.isPlaying();
    }

    public void isDefaultPlaying(boolean isPlaying) {
        this.videoView.isDefaultPlaying(isPlaying);
    }

    public void reset() {
        this.videoView.reset();
    }

    public void setBackClickListener(BackClickListener backClickListener) {
        this.backClickListener = backClickListener;
    }

    public void release() {
        this.videoView.release();
    }

    @Override // com.byd.auto_emanual.video.PlayController.ZoomListener
    public void doZoom(String zoomTag) {
        ZoomInOutHelper.zoom(this, 0, zoomTag);
    }

    public String getZoomTag() {
        return this.playController.getZoomTag();
    }

    public void updateVideoViewSize(int width, int height) {
        this.videoView.updateViewSize(width, height);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.oldY = event.getY();
        } else if (action == 1 && PlayController.ZOOM_OUT.equals(this.playController.getZoomTag()) && ViewUtil.isPort(getResources().getConfiguration().orientation)) {
            this.y = event.getY();
            float f = getResources().getDisplayMetrics().heightPixels;
            String str = TAG;
            MLog.v(str, "onTouchEvent " + this.y + ", " + this.oldY + ", " + f);
            float f2 = this.y;
            float f3 = this.oldY;
            if (f2 - f3 < 0.0f && f3 > f) {
                MLog.v(str, "onTouchEvent showNavigationBar");
                if (this.playController.getVisibility() != 0) {
                    return false;
                }
                this.videoView.hidePlayController();
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        TextView textView = this.netErrorText;
        if (textView != null) {
            textView.setText(this.netError.getContext().getString(R.string.state_net_error));
        }
    }
}
