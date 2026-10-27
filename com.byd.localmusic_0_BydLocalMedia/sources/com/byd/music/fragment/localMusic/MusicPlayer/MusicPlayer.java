package com.byd.music.fragment.localMusic.MusicPlayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.blankj.utilcode.util.Utils;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.ShowCarModeUtil;
import com.byd.music.utils.StringSubUtil;
import com.byd.music.utils.ToastUtil;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class MusicPlayer {
    public static final int PLAY_STATE_PAUSE = 2;
    public static final int PLAY_STATE_PLAYING = 1;
    public static final int PLAY_STATE_STOP = 4;
    private IPlayerListener iPlayerListener;
    private LocalMusicBean playLocalBean;
    private String playPath;
    private String TAG = MusicPlayer.class.getSimpleName();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int playErrorTime = 0;
    private boolean isPlayPreClick = false;
    private boolean isPlayNextClick = false;
    private final int START_PLAY = 120;
    private int progress = 0;
    private int state = 0;
    public int endProgress = 0;
    public Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 120) {
                return;
            }
            LogUtil.e(MusicPlayer.this.TAG, "MusicPlayer: handler: START_PLAY");
            MusicPlayer musicPlayer = MusicPlayer.this;
            musicPlayer.startPlay(musicPlayer.progress);
        }
    };
    public Runnable runnable = new Runnable() { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayer.2
        @Override // java.lang.Runnable
        public void run() {
            if (MusicPlayer.this.mediaPlayer == null) {
                LogUtil.e(MusicPlayer.this.TAG, "runnable:removeCallbacks: mediaPlayer is null");
                MusicPlayer.this.handler.removeCallbacks(MusicPlayer.this.runnable);
                return;
            }
            if (!MusicPlayer.this.mediaPlayer.isPlaying()) {
                LogUtil.e(MusicPlayer.this.TAG, "runnable:removeCallbacks: player isPlaying= false");
                MusicPlayer.this.handler.removeCallbacks(MusicPlayer.this.runnable);
                MusicPlayer.this.handler.postDelayed(this, 100L);
                return;
            }
            if (!MusicPlayHelper.getInstance().isPlaying.get()) {
                LogUtil.e(MusicPlayer.this.TAG, "runnable:removeCallbacks: helper isPlaying= false");
                MusicPlayer.this.handler.removeCallbacks(MusicPlayer.this.runnable);
                return;
            }
            if (MusicPlayer.this.mediaPlayer != null && MusicPlayer.this.mediaPlayer.getCurrentPosition() >= MusicPlayer.this.endProgress) {
                LogUtil.e(MusicPlayer.this.TAG, "runnable:removeCallbacks  endProgress= " + MusicPlayer.this.endProgress);
                MusicPlayer.this.handler.removeCallbacks(MusicPlayer.this.runnable);
            }
            if (MusicPlayer.this.iPlayerListener != null && MusicPlayer.this.mediaPlayer != null) {
                MusicPlayer.this.iPlayerListener.onProgress(MusicPlayer.this.mediaPlayer.getCurrentPosition());
            }
            if (MusicPlayer.this.mediaPlayer == null || !MusicPlayHelper.getInstance().isPlaying.get()) {
                return;
            }
            if (MusicPlayer.this.mediaPlayer.getCurrentPosition() > 1000) {
                MusicPlayer.this.handler.postDelayed(this, 1000 - (MusicPlayer.this.mediaPlayer.getCurrentPosition() % 1000));
            } else {
                MusicPlayer.this.handler.postDelayed(this, 100L);
            }
        }
    };

    static /* synthetic */ int access$708(MusicPlayer musicPlayer) {
        int i = musicPlayer.playErrorTime;
        musicPlayer.playErrorTime = i + 1;
        return i;
    }

    public void setiPlayerListener(IPlayerListener iPlayerListener) {
        this.iPlayerListener = iPlayerListener;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
        LogUtil.d(this.TAG, "setState:" + i);
        IPlayerListener iPlayerListener = this.iPlayerListener;
        if (iPlayerListener != null) {
            iPlayerListener.onChangeState(i);
        }
    }

    public void play(LocalMusicBean localMusicBean, int i) {
        this.playPath = localMusicBean.getUri();
        this.progress = i;
        StringBuilder sb = new StringBuilder();
        sb.append("play:playPath=");
        sb.append(StringSubUtil.subString(this.playPath));
        sb.append(" isHoldAudioFocus:");
        sb.append(AudioFocusManager.getInstance().isHoldAudioFocus());
        sb.append(" storageType=");
        sb.append(localMusicBean.getStorageType());
        sb.append(" progress=");
        sb.append(i);
        sb.append(" duration=");
        sb.append(localMusicBean.getDuration());
        if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
            int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
            sb.append(" audioFocus：");
            sb.append(iRequestAudioFocus);
            if (1 != iRequestAudioFocus) {
                sb.append(" play_failed");
                LogUtil.e(this.TAG, sb.toString());
                if (DeviceInfoUtils.isInCall()) {
                    return;
                }
                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                return;
            }
        }
        LogUtil.e(this.TAG, sb.toString());
        MusicPlayHelper.getInstance().setIsCollectMusic(localMusicBean.getStorageType(), localMusicBean.isCollect());
        MusicPlayHelper.getInstance().setMusicEngineByStorageType(localMusicBean.getStorageType());
        MusicPlayHelper.getInstance().setCurrentMusic(localMusicBean);
        MusicPlayHelper.getInstance().setIsCollectMusic(localMusicBean.getStorageType(), MusicPlayHelper.getInstance().getCurrentPlayLocalMusic().isCollect());
        if (this.mediaPlayer == null) {
            LogUtil.e(this.TAG, " mediaPlayer.play() :MediaPlayer is NULL and init");
            this.mediaPlayer = new MediaPlayer();
        } else {
            LogUtil.e(this.TAG, " mediaPlayer.isPlaying()=" + this.mediaPlayer.isPlaying());
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.stop();
            }
            this.mediaPlayer.reset();
        }
        MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(i, localMusicBean.getDuration(), getState(), localMusicBean.getStorageType()));
        this.playLocalBean = localMusicBean;
        this.handler.removeMessages(120);
        this.handler.sendEmptyMessageDelayed(120, MusicPlayHelper.getInstance().getIsPlaying().get() ? 1000L : 300L);
        MusicPlayHelper.getInstance().setIsPlaying(true);
    }

    public void removeMsg() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(120);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlay(final int i) {
        final StringBuilder sb = new StringBuilder();
        sb.append("startPlay11 playPath = ");
        sb.append(StringSubUtil.subString(this.playPath));
        if (this.mediaPlayer == null) {
            sb.append(" null");
            LogUtil.e(this.TAG, sb.toString());
            return;
        }
        try {
            if (ShowCarModeUtil.isShowCarFileExist(this.playPath)) {
                try {
                    this.mediaPlayer.setDataSource(Utils.getApp().getResources().getAssets().openFd(LocalMusicUtils.BYD_SHOW_CAR_NAME));
                } catch (IOException e) {
                    LogUtil.e(this.TAG, "getAssets FAIL" + e.getMessage());
                }
            } else {
                this.mediaPlayer.setDataSource(this.playPath);
            }
            LogUtil.d(this.TAG, sb.toString());
            this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayer.3
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    LogUtil.e(MusicPlayer.this.TAG, "mediaPlayer setOnPreparedListener!");
                    if (MusicPlayer.this.playLocalBean == null) {
                        if (MusicPlayer.this.mediaPlayer != null) {
                            MusicPlayer musicPlayer = MusicPlayer.this;
                            musicPlayer.endProgress = musicPlayer.mediaPlayer.getDuration();
                        }
                    } else {
                        MusicPlayer musicPlayer2 = MusicPlayer.this;
                        musicPlayer2.endProgress = musicPlayer2.playLocalBean.getDuration();
                    }
                    StringBuilder sb2 = sb;
                    sb2.append(" endProgress:");
                    sb2.append(MusicPlayer.this.endProgress);
                    if (MusicPlayer.this.endProgress == 0) {
                        StringBuilder sb3 = sb;
                        sb3.append(" fileExist=");
                        sb3.append(LocalMusicUtils.checkMusicFileExist(MusicPlayer.this.playPath));
                    }
                    LogUtil.e(MusicPlayer.this.TAG, sb.toString());
                    MusicPlayer.this.iPlayerListener.onDuration(MusicPlayer.this.endProgress);
                    if (MusicPlayer.this.mediaPlayer != null) {
                        MusicPlayer.this.mediaPlayer.seekTo(i);
                        LogUtil.e(MusicPlayer.this.TAG, "startPlay mediaPlayer.seekTo progress = " + i);
                    }
                    boolean z = MusicPlayHelper.getInstance().getIsPlaying().get();
                    LogUtil.e(MusicPlayer.this.TAG, "startPlay mediaPlayer.setOnPreparedListener = " + z);
                    if (z) {
                        MusicPlayer musicPlayer3 = MusicPlayer.this;
                        musicPlayer3.startPlay(musicPlayer3.playLocalBean);
                    }
                }
            });
            this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayer.4
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (MusicPlayHelper.getInstance().getIsPlaying().get()) {
                        LogUtil.e(MusicPlayer.this.TAG, "setOnCompletionListener");
                        MusicPlayer.this.iPlayerListener.onChangeState(4);
                    }
                }
            });
            setErrorListener();
            try {
                this.mediaPlayer.prepareAsync();
            } catch (Exception e2) {
                sb.append("startPlay prepareAsync Exception: ");
                sb.append(e2.getMessage());
                e2.printStackTrace();
                LogUtil.e(this.TAG, sb.toString());
                onErrorBeforeStartPlay();
            }
        } catch (Exception e3) {
            sb.append("startPlay setDataSource Exception : ");
            sb.append(e3.getMessage());
            e3.printStackTrace();
            LogUtil.e(this.TAG, sb.toString());
            onErrorBeforeStartPlay();
        }
    }

    public void startPlay(LocalMusicBean localMusicBean) {
        int i = 0;
        if (!localMusicBean.getUri().equals(this.playPath)) {
            LogUtil.e(this.TAG, "startPlay22: playPath is not equals and reset progress to play!");
            play(localMusicBean, 0);
            return;
        }
        LogUtil.d(this.TAG, "startPlay22: progress= " + this.progress + " endProgress=" + this.endProgress);
        int i2 = this.endProgress;
        if (i2 <= 0 || this.progress > i2) {
            int duration = localMusicBean.getDuration();
            int i3 = this.progress;
            if (i3 > 0 && i3 <= duration) {
                i = i3;
            }
            LogUtil.e(this.TAG, "startPlay22: endProgress or progress value is inValid and play with progress= " + i);
            play(localMusicBean, i);
            return;
        }
        if (this.mediaPlayer != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("startPlay22 playPath = ");
            sb.append(StringSubUtil.subString(this.playPath));
            sb.append(" isHoldAudioFocus:");
            sb.append(AudioFocusManager.getInstance().isHoldAudioFocus());
            if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
                int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
                sb.append("audioFocus=");
                sb.append(iRequestAudioFocus);
                if (1 != iRequestAudioFocus) {
                    if (!DeviceInfoUtils.isInCall()) {
                        ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                    }
                    sb.append(" play_failed");
                    LogUtil.e(this.TAG, sb.toString());
                    return;
                }
            }
            sb.append(" getDuration= ");
            sb.append(localMusicBean.getDuration());
            LogUtil.d(this.TAG, sb.toString());
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(localMusicBean.getStorageType());
            MusicPlayHelper.getInstance().setIsPlaying(true);
            this.handler.postDelayed(this.runnable, 0L);
            this.mediaPlayer.start();
            if (this.mediaPlayer.isPlaying()) {
                setState(1);
                return;
            }
            return;
        }
        LogUtil.d(this.TAG, "startPlay NULL");
        play(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(), MusicPlayHelper.getInstance().getLocalProgressByStorageType(localMusicBean.getStorageType()));
    }

    public boolean isPlaying() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            return mediaPlayer.isPlaying();
        } catch (Exception e) {
            LogUtil.e(this.TAG, e.getMessage());
            return false;
        }
    }

    public void pause() {
        StringBuilder sb = new StringBuilder("pause():");
        sb.append(" isPlaying= ");
        sb.append(isPlaying());
        if (isPlaying()) {
            setState(2);
            this.mediaPlayer.pause();
            sb.append(" and player.pause()");
        }
        LogUtil.d(this.TAG, sb.toString());
        removeMsg();
        MusicPlayHelper.getInstance().setIsPlaying(false);
        this.handler.removeCallbacks(this.runnable);
    }

    public void seekPlay(int i) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
            IPlayerListener iPlayerListener = this.iPlayerListener;
            if (iPlayerListener != null) {
                iPlayerListener.onProgress(i);
            }
            if (this.mediaPlayer.isPlaying()) {
                this.handler.postDelayed(this.runnable, 0L);
                setState(1);
            }
        }
    }

    public void stopUpdate() {
        this.handler.removeCallbacks(this.runnable);
    }

    public void stop() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
        LogUtil.d(this.TAG, "stop");
        MusicPlayHelper.getInstance().setIsPlaying(false);
        try {
            if (this.mediaPlayer != null) {
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.stop();
                }
                this.mediaPlayer.reset();
                this.mediaPlayer.release();
                this.mediaPlayer = null;
                this.iPlayerListener.reSet();
            }
        } catch (IllegalStateException e) {
            LogUtil.d(this.TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopBeforePlay() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
        try {
            if (this.mediaPlayer != null) {
                LogUtil.d(this.TAG, "stopBeforePlay:" + this.mediaPlayer.isPlaying());
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.stop();
                    setState(2);
                }
                this.mediaPlayer.reset();
                this.mediaPlayer.release();
                this.mediaPlayer = null;
                this.iPlayerListener.reSet();
            }
        } catch (IllegalStateException e) {
            LogUtil.d(this.TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void releaseMediaPlayer() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
        MusicPlayHelper.getInstance().setIsPlaying(false);
        try {
            if (this.mediaPlayer != null) {
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.stop();
                }
                this.mediaPlayer.reset();
                this.mediaPlayer.release();
                this.mediaPlayer = null;
                this.iPlayerListener.reSet();
            }
        } catch (Throwable th) {
            LogUtil.e(this.TAG, th.getMessage());
            th.printStackTrace();
        }
    }

    public void setErrorListener() {
        this.mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayer.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (!MusicPlayHelper.getInstance().getIsPlaying().get()) {
                    return false;
                }
                LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic();
                StringBuilder sb = new StringBuilder();
                sb.append("OnErrorListener what:");
                sb.append(i);
                sb.append(" playMusic=");
                sb.append(currentPlayLocalMusic == null ? " null" : StringSubUtil.subString(currentPlayLocalMusic.getUri()));
                sb.append(" musicName = ");
                sb.append(currentPlayLocalMusic == null ? "" : StringSubUtil.subString(currentPlayLocalMusic.getTitle()));
                MusicPlayer.access$708(MusicPlayer.this);
                sb.append(" errorTime:");
                sb.append(MusicPlayer.this.playErrorTime);
                LogUtil.e(MusicPlayer.this.TAG, sb.toString());
                if (i == 1) {
                    ToastUtil.showCenter(Utils.getApp().getApplicationContext().getString(R.string.play_error));
                }
                MusicPlayer.this.iPlayerListener.playError(MusicPlayer.this.playErrorTime);
                if (MusicPlayer.this.isPlayPreClick) {
                    MusicPlayer.this.isPlayPreClick = false;
                    MusicPlayer.this.iPlayerListener.playPreNextError(true);
                    return true;
                }
                MusicPlayer.this.iPlayerListener.playPreNextError(false);
                MusicPlayer.this.setPlayNextClick(false);
                return true;
            }
        });
    }

    private void onErrorBeforeStartPlay() {
        if (MusicPlayHelper.getInstance().getIsPlaying().get()) {
            LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic();
            StringBuilder sb = new StringBuilder();
            sb.append("onErrorBeforeStartPlay ");
            sb.append(" playMusic=");
            sb.append(currentPlayLocalMusic == null ? " null" : StringSubUtil.subString(currentPlayLocalMusic.getUri()));
            sb.append(" musicName = ");
            sb.append(currentPlayLocalMusic == null ? "" : StringSubUtil.subString(currentPlayLocalMusic.getTitle()));
            this.playErrorTime++;
            sb.append(" errorTime:");
            sb.append(this.playErrorTime);
            LogUtil.e(this.TAG, sb.toString());
            int i = this.playErrorTime;
            if (i < 5) {
                ToastUtil.showCenter(Utils.getApp().getApplicationContext().getString(R.string.play_error));
                if (this.isPlayPreClick) {
                    this.isPlayPreClick = false;
                    this.iPlayerListener.playPreNextError(true);
                    return;
                } else {
                    this.iPlayerListener.playPreNextError(false);
                    setPlayNextClick(false);
                    return;
                }
            }
            this.iPlayerListener.playError(i);
            setPlayErrorTime(0);
        }
    }

    public int getPlayErrorTime() {
        return this.playErrorTime;
    }

    public void setPlayErrorTime(int i) {
        this.playErrorTime = i;
    }

    public boolean isPlayPreClick() {
        return this.isPlayPreClick;
    }

    public void setPlayPreClick(boolean z) {
        this.isPlayPreClick = z;
    }

    public boolean isPlayNextClick() {
        return this.isPlayNextClick;
    }

    public void setPlayNextClick(boolean z) {
        this.isPlayNextClick = z;
    }
}
