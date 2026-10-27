package com.byd.auto_emanual.video;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.Classify;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.util.LocalPathUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.NetUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.db.VideoDbUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VideoView extends SurfaceView implements IPlayControl, VideoPath {
    private static final int MSG_DOWNLOAD_ERROR = 3;
    private static final int MSG_DOWNLOAD_RESUME = 10;
    private static final int MSG_HIDE_LOADING = 7;
    private static final int MSG_HIDE_NET_ERROR = 9;
    private static final int MSG_HIDE_PLAY_CONTROLLER = 4;
    private static final int MSG_SHOW_LOADING = 6;
    private static final int MSG_SHOW_NET_ERROR = 8;
    private static final int MSG_UPDATE_SEEK_BAR_PROGRESS = 1;
    private static final int MSG_UPDATE_SEEK_BAR_SECOND_PROGRESS = 2;
    private static final int MSG_UPDATE_START_PAUSE_STATE = 5;
    private static final int STATE_END = 7;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 21;
    private static final int STATE_STARTED = 3;
    private static final int STATE_STOPPED = 6;
    private static final String TAG = "VideoView";
    private static final int TIME_DOWNLOAD_ERROR_RETRY = 1000;
    private static final int TIME_DOWNLOAD_RESUME_TO_PLAY = 500;
    private static final int TIME_PLAY_CONTROLLER_SHOW = 3000;
    private static final int TIME_UPDATE_SEEK_BAR_PROGRESS_DURATION = 100;
    private int MAX_COUNT_ILLEGAL_STATE;
    private int TIME_DOUBLE_CLICK_INTERVAL;
    int clickCount;
    Runnable clickRunnable;
    private int count_illegal_state;
    private Downloader.IDownloadListener downloadListener;
    private boolean isDefaultPlaying;
    private boolean isSought;
    private String localPath;
    private AudioAttributes mAudioAttributes;
    private int mAudioFocusType;
    private AudioManager mAudioManager;
    private RelativeLayout mBack;
    private Context mContext;
    private int mCurrentState;
    private View mFooterBackground;
    private View mHeaderBackground;
    private ImageView mLoading;
    private MediaPlayer mMediaPlayer;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private PlayController mPlayController;
    SurfaceHolder.Callback mSHCallback;
    private ImageView mStartPauseBig;
    private SurfaceHolder mSurfaceHolder;
    private Uri mUri;
    private int mVideoHeight;
    private TextView mVideoTitle;
    private int mVideoWidth;
    float oldY;
    private volatile String topVideoPath;
    private VideoHandler videoHandler;
    float y;

    @Override // com.byd.auto_emanual.video.IPlayControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void next() {
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void previous() {
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void zoom(String zoomTag) {
    }

    public VideoView(Context context) {
        this(context, null);
    }

    public VideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isSought = false;
        this.mAudioFocusType = 1;
        this.TIME_DOUBLE_CLICK_INTERVAL = 300;
        this.isDefaultPlaying = true;
        this.topVideoPath = "";
        this.clickCount = 0;
        this.clickRunnable = new Runnable() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoView$GMYyUdnHTOBGFK9v1T6S2OZrS8U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1$VideoView();
            }
        };
        this.MAX_COUNT_ILLEGAL_STATE = 2;
        this.mOnPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.byd.auto_emanual.video.VideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mp) {
                MLog.d(VideoView.TAG, "onPrepared " + VideoView.this.topVideoPath);
                VideoView.this.mCurrentState = 2;
                VideoView.this.initMediaControllerAfterPrepared();
                VideoView.this.start();
                if (VideoView.this.isDefaultPlaying) {
                    return;
                }
                VideoView.this.pause();
            }
        };
        this.mOnCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.byd.auto_emanual.video.VideoView.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mp) {
                MLog.d(VideoView.TAG, "onCompletion");
                VideoView.this.seekTo(0);
                VideoView.this.mPlayController.setSeekBarProgress(0, VideoView.this.getDuration());
                VideoView.this.mPlayController.setCurrentProgress(0);
                VideoView.this.showPlayController();
                VideoView.this.keepPlayController();
                VideoView.this.mCurrentState = 5;
            }
        };
        this.mOnInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoView$F11T5KNhMJ0_W9ipXO9jb2VW3OU
            @Override // android.media.MediaPlayer.OnInfoListener
            public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                return this.f$0.lambda$new$2$VideoView(mediaPlayer, i, i2);
            }
        };
        this.mOnErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.byd.auto_emanual.video.VideoView.3
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mp, int what, int extra) {
                MLog.e(VideoView.TAG, "onError (" + what + "," + extra + ")");
                VideoView.this.updateSeekBarProgress(SeekBarState.STOP, VideoView.this.getCurrentPosition());
                VideoView.this.mCurrentState = -1;
                VideoView.this.reset();
                if (NetUtil.isNetAvailable(VideoView.this.mContext)) {
                    return true;
                }
                VideoView.this.showNetError();
                return true;
            }
        };
        this.mOnVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.byd.auto_emanual.video.VideoView.4
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                MLog.d(VideoView.TAG, "onVideoSizeChanged");
                VideoView.this.mVideoWidth = mp.getVideoWidth();
                VideoView.this.mVideoHeight = mp.getVideoHeight();
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    return;
                }
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                VideoView.this.requestLayout();
            }
        };
        this.mOnSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.byd.auto_emanual.video.VideoView.5
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mp) {
                MLog.d(VideoView.TAG, "onSeekComplete " + VideoView.this.getCurrentPosition());
                if (VideoView.this.mCurrentState == 5) {
                    VideoView.this.pause();
                } else {
                    VideoView.this.start();
                }
                VideoView.this.isSought = true;
            }
        };
        this.mSHCallback = new SurfaceHolder.Callback() { // from class: com.byd.auto_emanual.video.VideoView.6
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder) {
                MLog.d(VideoView.TAG, "surfaceCreated");
                VideoView.this.mSurfaceHolder = holder;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                MLog.d(VideoView.TAG, "surfaceChanged");
                VideoView.this.mMediaPlayer.setDisplay(VideoView.this.mSurfaceHolder);
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder) {
                MLog.d(VideoView.TAG, "surfaceDestroyed");
                VideoView.this.mSurfaceHolder = null;
            }
        };
        this.mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoView$Y5YUDWVLIPKz9Yn_QrdvxAjXgGo
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                this.f$0.lambda$new$3$VideoView(i);
            }
        };
        this.videoHandler = new VideoHandler(Looper.getMainLooper(), this);
        this.downloadListener = new Downloader.IDownloadListener() { // from class: com.byd.auto_emanual.video.VideoView.7
            private final int minSizeToPlay = 1048576;
            private Map<String, Integer> errorTimes = new HashMap();
            private int maxErrorTime = 5;

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadStart(String path, String localPath, int from, int to, int fileSize) {
                MLog.v(VideoView.TAG, "onDownloadStart from = " + from + ", to = " + from + ", fileSize = " + fileSize + " " + path);
                if (path.equalsIgnoreCase(VideoView.this.topVideoPath)) {
                    VideoView.this.updateSeekBarSecondaryProgress(from, fileSize);
                    VideoView.this.showLoading();
                }
                VideoView.this.insertCacheState(path, localPath, from, fileSize);
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloading(String path, String localPath, int from, int downloadedSize, int fileSize) {
                int i = from + downloadedSize;
                VideoView.this.updateCacheProgress(path, i);
                if (path.equalsIgnoreCase(VideoView.this.topVideoPath)) {
                    VideoView.this.updateSeekBarSecondaryProgress(i, fileSize);
                    if (VideoView.this.canPrepareForStart() && i * 10 > fileSize && i > 1048576) {
                        MLog.v(VideoView.TAG, "onDownloading prepareForStart \n path = " + path + " \n localPath = " + localPath + "\n topVideoPath = " + VideoView.this.topVideoPath);
                        VideoView.this.prepareForStart(localPath);
                    }
                    if (i * 10 <= fileSize || i <= 1048576) {
                        return;
                    }
                    if (VideoView.this.mCurrentState == 4) {
                        VideoView.this.videoHandler.sendMessageDelayed(VideoView.this.videoHandler.obtainMessage(10), 500L);
                    } else {
                        int unused = VideoView.this.mCurrentState;
                    }
                }
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadError(String path, String localPath, int from, int responseCode) {
                int iUpdateErrorTime = updateErrorTime(path);
                MLog.v(VideoView.TAG, "onDownloadError responseCode = " + responseCode + ", localPath = " + localPath + ", errorTime = " + iUpdateErrorTime);
                File file = new File(localPath);
                if (file.exists() && responseCode == 416) {
                    file.delete();
                }
                if (iUpdateErrorTime > this.maxErrorTime) {
                    Toast.makeText(VideoView.this.mContext, R.string.settings_download_failed, 0).show();
                } else {
                    VideoView.this.videoHandler.sendMessageDelayed(VideoView.this.videoHandler.obtainMessage(3, path), 1000L);
                }
            }

            private int updateErrorTime(String path) {
                int iIntValue = this.errorTimes.containsKey(path) ? this.errorTimes.get(path).intValue() + 1 : 0;
                this.errorTimes.put(path, Integer.valueOf(iIntValue));
                return iIntValue;
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadComplete(String path, String localPath) {
                MLog.i(VideoView.TAG, "onDownloadComplete localPath = " + localPath);
                this.errorTimes.remove(path);
                VideoView.this.hideLoading();
                if (path.equalsIgnoreCase(VideoView.this.topVideoPath)) {
                    VideoView videoView = VideoView.this;
                    videoView.updateSeekBarSecondaryProgress(videoView.getDuration(), VideoView.this.getDuration());
                    VideoView.this.prepareForStart(localPath);
                }
                VideoView.this.updateCacheState(path);
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onNetworkUnavailable() {
                MLog.i(VideoView.TAG, "onNetworkUnavailable");
                VideoView.this.showNetError();
            }
        };
        init(context);
    }

    private void init(Context context) {
        String str = TAG;
        MLog.v(str, "init");
        this.mContext = context;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioAttributes = new AudioAttributes.Builder().setUsage(1).setContentType(3).build();
        getHolder().addCallback(this.mSHCallback);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        initMediaPlayer();
        setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.video.-$$Lambda$VideoView$qIEpVAM5tnONdlkTvGvPEk-6ovc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$0$VideoView(view);
            }
        });
        MLog.v(str, "init end");
    }

    public /* synthetic */ void lambda$init$0$VideoView(View view) {
        onClick();
    }

    public void onClick() {
        int i = this.clickCount + 1;
        this.clickCount = i;
        if (i >= 2) {
            this.videoHandler.removeCallbacks(this.clickRunnable);
            this.videoHandler.post(this.clickRunnable);
        } else {
            this.videoHandler.removeCallbacks(this.clickRunnable);
            this.videoHandler.postDelayed(this.clickRunnable, this.TIME_DOUBLE_CLICK_INTERVAL);
        }
    }

    public /* synthetic */ void lambda$new$1$VideoView() {
        if (this.clickCount <= 1) {
            doClick();
        } else {
            doDoubleClick();
        }
        this.clickCount = 0;
    }

    private void doDoubleClick() {
        MLog.v(TAG, "doDoubleClick");
        startPause();
    }

    private void doClick() {
        MLog.v(TAG, "doClick");
        if (this.mPlayController.getVisibility() == 0) {
            hidePlayController();
        } else {
            showPlayController();
        }
    }

    public void startPause() {
        if (isPlaying()) {
            pause();
        } else {
            start();
        }
    }

    private void initMediaPlayer() {
        MLog.v(TAG, "initMediaPlayer");
        if (!Utils.notShowVideo(getContext())) {
            requestAudioFocus();
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(this.mOnPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.mOnVideoSizeChangedListener);
        this.mMediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
        this.mMediaPlayer.setOnSeekCompleteListener(this.mOnSeekCompleteListener);
        this.mMediaPlayer.setOnInfoListener(this.mOnInfoListener);
        this.mMediaPlayer.setOnErrorListener(this.mOnErrorListener);
        this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
        this.mMediaPlayer.setAudioAttributes(this.mAudioAttributes);
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
        this.mCurrentState = 0;
    }

    private void setDataSource(Uri uri) {
        String str = TAG;
        MLog.v(str, "setDataSource uri");
        if (this.mCurrentState != 0) {
            MLog.d(str, "state is incorrect, can't setDataSource. mCurrentState = " + this.mCurrentState);
            return;
        }
        if (uri == null) {
            MLog.d(str, "setDataSource uri = " + uri);
            return;
        }
        this.mUri = uri;
        try {
            this.mMediaPlayer.setDataSource(this.mContext, uri);
            this.mCurrentState = 1;
        } catch (IOException e) {
            MLog.e(TAG, "setDataSource error, uri = " + this.mUri, e);
            this.mOnErrorListener.onError(this.mMediaPlayer, -1004, 0);
        }
    }

    private void setDataSource(String path) {
        String str = TAG;
        MLog.d(str, "setDataSource path = " + path);
        if (this.mCurrentState != 0) {
            MLog.d(str, "state is incorrect, can't setDataSource. mCurrentState = " + this.mCurrentState);
            return;
        }
        this.count_illegal_state++;
        try {
            this.mMediaPlayer.setDataSource(path);
            this.mCurrentState = 1;
            prepareAsync();
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            if ((e instanceof IllegalStateException) && this.count_illegal_state < this.MAX_COUNT_ILLEGAL_STATE) {
                MLog.e(TAG, "setDataSource error to reset.", e);
                reset();
                setDataSource(path);
                prepareAsync();
            } else {
                MLog.e(TAG, "setDataSource error, " + path, e);
                this.mOnErrorListener.onError(this.mMediaPlayer, -1004, 0);
            }
        }
        this.count_illegal_state = 0;
    }

    private void prepareAsync() {
        String str = TAG;
        MLog.d(str, "prepareAsync");
        int i = this.mCurrentState;
        if (i != 1 && i != 6) {
            MLog.d(str, "state is incorrect, can't prepareAsync. mCurrentState = " + this.mCurrentState);
        } else {
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 21;
        }
    }

    public void release() {
        MLog.d(TAG, "release");
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mMediaPlayer = null;
        }
        this.mCurrentState = 7;
        abandonAudioFocus();
    }

    public boolean isPlaying() {
        return this.mCurrentState == 3 && this.mMediaPlayer.isPlaying();
    }

    public void updateStartPauseState(String startOrPause) {
        this.videoHandler.obtainMessage(5, startOrPause).sendToTarget();
    }

    public void updateSeekBarProgress(SeekBarState state, int progress) {
        Message messageObtainMessage = this.videoHandler.obtainMessage(1, new MessageObject(state, progress));
        int i = AnonymousClass8.$SwitchMap$com$byd$auto_emanual$video$SeekBarState[state.ordinal()];
        if (i == 1) {
            this.videoHandler.sendMessage(messageObtainMessage);
            return;
        }
        if (i == 2) {
            this.videoHandler.removeMessages(messageObtainMessage.what);
        } else if (i == 3) {
            this.videoHandler.sendMessageDelayed(messageObtainMessage, 100L);
        } else {
            MLog.i(TAG, "state = " + state + " is not correct when updateSeekBarProgress");
        }
    }

    /* JADX INFO: renamed from: com.byd.auto_emanual.video.VideoView$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$auto_emanual$video$SeekBarState;

        static {
            int[] iArr = new int[SeekBarState.values().length];
            $SwitchMap$com$byd$auto_emanual$video$SeekBarState = iArr;
            try {
                iArr[SeekBarState.GO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$auto_emanual$video$SeekBarState[SeekBarState.STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$auto_emanual$video$SeekBarState[SeekBarState.DELAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSeekBarSecondaryProgress(int sizeOfDownloaded, int fileSize) {
        Message messageObtainMessage = this.videoHandler.obtainMessage(2);
        this.videoHandler.removeMessages(messageObtainMessage.what);
        messageObtainMessage.arg1 = sizeOfDownloaded;
        messageObtainMessage.arg2 = fileSize;
        this.videoHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertCacheState(String path, String localPath, int from, int fileSize) {
        if (VideoDbUtil.getInstance(this.mContext).getMediaCacheId(path) < 0) {
            VideoDbUtil.getInstance(this.mContext).addMediaCache(localPath, path, 0, fileSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCacheState(String url) {
        VideoDbUtil.getInstance(this.mContext).changeCacheState(url, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCacheProgress(String path, int allDownloadSize) {
        VideoDbUtil.getInstance(this.mContext).updateCachePos(path, allDownloadSize);
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void start() {
        String str = TAG;
        MLog.d(str, "start play");
        int i = this.mCurrentState;
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            MLog.d(str, "state is incorrect, can't start. mCurrentState = " + this.mCurrentState);
            return;
        }
        if (this.mMediaPlayer.getCurrentPosition() > 0 && this.mPlayController.getSecondaryProgress() > 0 && this.mMediaPlayer.getCurrentPosition() > this.mPlayController.getSecondaryProgress()) {
            MLog.d(str, "getCurrentPosition() > getSecondaryProgress(), can't start to play ! ");
            return;
        }
        this.mCurrentState = 3;
        requestAudioFocus();
        this.mMediaPlayer.start();
        if (this.mPlayController.getVisibility() == 0) {
            showPlayController();
        }
        updateStartPauseState(Classify.START);
        hideLoading();
        hideNetError();
    }

    private int getStartPosition() {
        int currentPosition = (this.mCurrentState != 5 || this.isSought) ? getCurrentPosition() : 0;
        this.isSought = false;
        return currentPosition;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void pause() {
        if (this.mMediaPlayer == null) {
            return;
        }
        String str = TAG;
        MLog.d(str, "pause mCurrentState = " + this.mCurrentState + ", mMediaPlayer.isPlaying() =" + this.mMediaPlayer.isPlaying());
        int i = this.mCurrentState;
        if (i != 3 && i != 4 && i != 5) {
            MLog.d(str, "state is incorrect, can't pause. mCurrentState = " + this.mCurrentState);
            return;
        }
        updateStartPauseState("pause");
        updateSeekBarProgress(SeekBarState.STOP, getCurrentPosition());
        keepPlayController();
        abandonAudioFocus();
        this.mMediaPlayer.pause();
        this.mCurrentState = 4;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void stop() {
        String str = TAG;
        MLog.d(str, "stop");
        int i = this.mCurrentState;
        if (i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
            MLog.d(str, "state is incorrect, can't stop. mCurrentState = " + this.mCurrentState);
            return;
        }
        updateSeekBarProgress(SeekBarState.STOP, getCurrentPosition());
        this.mMediaPlayer.stop();
        abandonAudioFocus();
        this.mCurrentState = 6;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void seekTo(int pos) {
        String str = TAG;
        MLog.d(str, "seekTo " + pos);
        int i = this.mCurrentState;
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            MLog.d(str, "state is incorrect, can't seekTo. mCurrentState = " + this.mCurrentState);
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.mMediaPlayer.seekTo(pos, 3);
        } else {
            this.mMediaPlayer.seekTo(pos);
        }
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public int getCurrentPosition() {
        if (this.mCurrentState == -1) {
            MLog.d(TAG, "state is incorrect, can't getCurrentPosition. mCurrentState = " + this.mCurrentState);
            return 0;
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public int getDuration() {
        int i = this.mCurrentState;
        if (i == 0 || i == 1 || i == -1 || i == 21) {
            MLog.d(TAG, "state is incorrect, can't getDuration. mCurrentState = " + this.mCurrentState);
            return 0;
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public void reset() {
        MLog.d(TAG, "reset");
        hideLoading();
        hideNetError();
        this.mMediaPlayer.reset();
        this.mPlayController.setCurrentProgress(0);
        this.mPlayController.setTotalDuration(0);
        this.mCurrentState = 0;
        if (NetUtil.isNetAvailable(this.mContext)) {
            showLoading();
        }
    }

    public void setLooping(boolean isLooping) {
        this.mMediaPlayer.setLooping(isLooping);
    }

    public void setTopVideoPath(String path) {
        this.topVideoPath = path;
    }

    @Override // com.byd.auto_emanual.video.IPlayControl
    public void setVolume(float volume) {
        this.mMediaPlayer.setVolume(volume, volume);
    }

    @Override // com.byd.auto_emanual.video.VideoPath
    public void setDataPath(String path) {
        String str = TAG;
        MLog.d(str, "setDataPath " + path);
        if (TextUtils.isEmpty(path)) {
            MLog.i(str, "setDataPath path is null or empty.");
        }
        LocalPathUtil.getLocalPath(this.mContext, path, this.downloadListener);
    }

    public String getVideoPath(String path) {
        return LocalPathUtil.getLocalPath(this.mContext, path, this.downloadListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canPrepareForStart() {
        return this.mCurrentState == 0;
    }

    public void isDefaultPlaying(boolean isPlaying) {
        this.isDefaultPlaying = isPlaying;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareForStart(String localPath) {
        MLog.d(TAG, "prepareForStart mCurrentState = " + this.mCurrentState);
        if (canPrepareForStart()) {
            this.mPlayController.seekBarByUser(false);
            this.mPlayController.pauseByUser(false);
            setDataSource(localPath);
            this.localPath = localPath;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMediaControllerAfterPrepared() {
        int duration = getDuration();
        this.mPlayController.setSeekBarMax(duration);
        this.mPlayController.setCurrentProgress(0);
        this.mPlayController.setTotalDuration(duration);
    }

    public /* synthetic */ boolean lambda$new$2$VideoView(MediaPlayer mediaPlayer, int i, int i2) {
        String str = TAG;
        MLog.v(str, "onInfo (" + i + "," + i2 + ") " + this.topVideoPath);
        if (i != 3) {
            return false;
        }
        setBackgroundColor(0);
        setForeground(new ColorDrawable(0));
        int startPosition = getStartPosition();
        MLog.v(str, "MEDIA_INFO_VIDEO_RENDERING_START position = " + startPosition);
        updateSeekBarProgress(SeekBarState.GO, startPosition);
        return true;
    }

    private boolean requestAudioFocus() {
        String str = TAG;
        MLog.d(str, "requestAudioFocus");
        if (this.mAudioFocusType == 0 || 1 == this.mAudioManager.requestAudioFocus(this.mOnAudioFocusChangeListener, 3, 1)) {
            return true;
        }
        MLog.i(str, "requestAudioFocus failed.");
        return false;
    }

    private void abandonAudioFocus() {
        MLog.d(TAG, "abandonAudioFocus");
        if (this.mAudioFocusType != 0) {
            this.mAudioManager.abandonAudioFocus(this.mOnAudioFocusChangeListener);
        }
    }

    public /* synthetic */ void lambda$new$3$VideoView(int i) {
        MLog.d(TAG, "onAudioFocusChange " + i);
        if (i == -3 || i == -2 || i == -1) {
            pause();
        } else {
            if (i != 1) {
                return;
            }
            start();
        }
    }

    public void setMediaController(PlayController controller) {
        this.mPlayController = controller;
    }

    public void setVideoTitle(TextView videoTitle) {
        this.mVideoTitle = videoTitle;
    }

    public void setBack(RelativeLayout back) {
        this.mBack = back;
    }

    public void setHeaderAndFooterBackground(View headerBackground, View footerBackground) {
        this.mHeaderBackground = headerBackground;
        this.mFooterBackground = footerBackground;
    }

    public void setOtherState(ImageView startPauseBig) {
        this.mStartPauseBig = startPauseBig;
    }

    public void updateViewSize(int width, int height) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        setLayoutParams(layoutParams);
    }

    private class MessageObject {
        private int seekBarProgress;
        private SeekBarState seekBarState;

        public MessageObject(SeekBarState state, int progress) {
            this.seekBarState = state;
            this.seekBarProgress = progress;
        }
    }

    static class VideoHandler extends Handler {
        private WeakReference<VideoView> videoViewWeakReference;

        public VideoHandler(Looper looper, VideoView view) {
            super(looper);
            this.videoViewWeakReference = new WeakReference<>(view);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.videoViewWeakReference.get() == null) {
            }
            int currentPosition = 0;
            switch (msg.what) {
                case 1:
                    SeekBarState seekBarState = ((MessageObject) msg.obj).seekBarState;
                    if (seekBarState == SeekBarState.GO) {
                        currentPosition = ((MessageObject) msg.obj).seekBarProgress;
                    } else if (seekBarState == SeekBarState.DELAY) {
                        currentPosition = this.videoViewWeakReference.get().getCurrentPosition();
                    }
                    if (currentPosition <= this.videoViewWeakReference.get().mPlayController.getSecondaryProgress() || this.videoViewWeakReference.get().mPlayController.getSecondaryProgress() <= 0) {
                        this.videoViewWeakReference.get().mPlayController.setSeekBarProgress(currentPosition, this.videoViewWeakReference.get().getDuration());
                        this.videoViewWeakReference.get().mPlayController.setCurrentProgress(currentPosition);
                        this.videoViewWeakReference.get().updateSeekBarProgress(SeekBarState.DELAY, this.videoViewWeakReference.get().getCurrentPosition());
                    } else {
                        MLog.d(VideoView.TAG, "progress > getSecondaryProgress(), can't set seekBarProgress! ");
                    }
                    break;
                case 2:
                    this.videoViewWeakReference.get().mPlayController.setSeekBarSecondaryProgress(msg.arg1, msg.arg2);
                    break;
                case 3:
                    LocalPathUtil.getLocalPath(MApp.context, (String) msg.obj, this.videoViewWeakReference.get().downloadListener);
                    this.videoViewWeakReference.get().pause();
                    this.videoViewWeakReference.get().updateSeekBarProgress(SeekBarState.STOP, this.videoViewWeakReference.get().getCurrentPosition());
                    break;
                case 4:
                    this.videoViewWeakReference.get().hidePlayController();
                    break;
                case 5:
                    this.videoViewWeakReference.get().mPlayController.updateStartPauseState((String) msg.obj);
                    break;
                case 6:
                    this.videoViewWeakReference.get().setBackground(ContextCompat.getDrawable(this.videoViewWeakReference.get().getContext(), R.drawable.video_loading_background));
                    this.videoViewWeakReference.get().mPlayController.showLoading();
                    break;
                case 7:
                    this.videoViewWeakReference.get().setBackgroundColor(0);
                    this.videoViewWeakReference.get().mPlayController.hideLoading();
                    break;
                case 8:
                    this.videoViewWeakReference.get().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    this.videoViewWeakReference.get().mPlayController.showNetError();
                    this.videoViewWeakReference.get().mStartPauseBig.setVisibility(4);
                    break;
                case 9:
                    this.videoViewWeakReference.get().setBackgroundColor(0);
                    this.videoViewWeakReference.get().mPlayController.hideNetError();
                    break;
                case 10:
                    if (!this.videoViewWeakReference.get().mPlayController.isSeekByUser() && !this.videoViewWeakReference.get().mPlayController.isPauseByUser()) {
                        this.videoViewWeakReference.get().seekTo(this.videoViewWeakReference.get().getCurrentPosition());
                        break;
                    }
                    break;
            }
        }
    }

    public Downloader.IDownloadListener getDownloadListener() {
        return this.downloadListener;
    }

    public void showPlayController() {
        String str = TAG;
        MLog.v(str, "showPlayController mCurrentState = " + this.mCurrentState);
        keepPlayController();
        this.mHeaderBackground.setVisibility(0);
        this.mVideoTitle.setVisibility(0);
        if (this.mBack.getVisibility() != 8) {
            this.mBack.setVisibility(0);
        }
        if (this.mPlayController.getVisibility() != 8) {
            this.mPlayController.setVisibility(0);
            int i = this.mCurrentState;
            if (i != -1 && i != 0 && i != 1 && i != 21) {
                this.mStartPauseBig.setVisibility(0);
            }
        }
        this.mFooterBackground.setVisibility(0);
        MLog.v(str, "sendEmptyMessageDelayed MSG_HIDE_PLAY_CONTROLLER " + isPlaying() + " " + this.mCurrentState + " " + this.mMediaPlayer.isPlaying());
        if (isPlaying()) {
            this.videoHandler.sendEmptyMessageDelayed(4, 3000L);
        }
    }

    public void hidePlayController() {
        MLog.v(TAG, "hidePlayController");
        keepPlayController();
        if (this.mPlayController.getVisibility() != 8) {
            this.mPlayController.setVisibility(4);
        }
        this.mVideoTitle.setVisibility(4);
        if (this.mBack.getVisibility() != 8) {
            this.mBack.setVisibility(4);
        }
        this.mHeaderBackground.setVisibility(4);
        this.mFooterBackground.setVisibility(4);
        this.mStartPauseBig.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepPlayController() {
        this.videoHandler.removeMessages(4);
    }

    public void showLoading() {
        this.videoHandler.sendEmptyMessage(6);
    }

    public void hideLoading() {
        this.videoHandler.removeMessages(6);
        this.videoHandler.sendEmptyMessage(7);
    }

    public void showNetError() {
        hideLoading();
        this.videoHandler.sendEmptyMessage(8);
    }

    public void hideNetError() {
        this.videoHandler.removeMessages(8);
        this.videoHandler.sendEmptyMessage(9);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.oldY = event.getY();
        } else if (action == 1 && PlayController.ZOOM_OUT.equals(this.mPlayController.getZoomTag()) && !ViewUtil.isPort(getResources().getConfiguration().orientation)) {
            this.y = event.getY();
            float f = getResources().getDisplayMetrics().heightPixels;
            String str = TAG;
            MLog.v(str, "onTouchEvent " + this.y + ", " + this.oldY + ", " + f);
            float f2 = this.y;
            float f3 = this.oldY;
            if (f2 - f3 < 0.0f && f3 > f) {
                MLog.v(str, "onTouchEvent showNavigationBar");
                if (this.mPlayController.getVisibility() != 0) {
                    return false;
                }
                hidePlayController();
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }
}
