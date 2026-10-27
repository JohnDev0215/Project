package com.amazon.avod.media.playback.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.GenericMediaException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.util.UrlUtils;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerState;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.util.DLog;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class StatefulAndroidMediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener {
    private int mBufferedPercentage;
    private int mCachedPosition;
    private final Context mContext;
    private PlaybackDataSource mInitialDataSource;
    private final MediaPlayer mMediaPlayer;
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;

    @Nullable
    private QOSCommunicationService mQOSCommunicationService;
    private final GenericVideoPlayerStateTracker mStateTracker;

    public StatefulAndroidMediaPlayer(Context context, @Nullable QOSCommunicationService qOSCommunicationService) {
        this(context, new MediaPlayer(), new GenericVideoPlayerStateTracker(), qOSCommunicationService);
    }

    StatefulAndroidMediaPlayer(Context context, MediaPlayer mediaPlayer, GenericVideoPlayerStateTracker genericVideoPlayerStateTracker, @Nullable QOSCommunicationService qOSCommunicationService) {
        this.mBufferedPercentage = 0;
        this.mCachedPosition = 0;
        this.mContext = context;
        this.mStateTracker = genericVideoPlayerStateTracker;
        this.mMediaPlayer = mediaPlayer;
        this.mQOSCommunicationService = qOSCommunicationService;
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        this.mOnBufferingUpdateListener = null;
    }

    public void setStateChangeListener(GenericVideoPlayerStateTracker.StateChangeListener stateChangeListener) {
        this.mStateTracker.setListener(stateChangeListener);
    }

    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    public PlaybackDataSource getPlaybackStatus() {
        return this.mInitialDataSource;
    }

    public void prepareAsync(Uri uri) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                this.mStateTracker.changeState(GenericVideoPlayerState.PREPARING);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                if (UrlUtils.isLocal(uri)) {
                    this.mInitialDataSource = PlaybackDataSource.DOWNLOADED;
                    FileInputStream fileInputStream2 = new FileInputStream(new File(uri.getPath()));
                    try {
                        this.mMediaPlayer.setDataSource(fileInputStream2.getFD());
                        fileInputStream = fileInputStream2;
                    } catch (Exception e) {
                        e = e;
                        DLog.exceptionf(e, "Exception calling prepareAsync on android MediaPlayer", new Object[0]);
                        this.mStateTracker.changeState(GenericVideoPlayerState.ERRORED);
                        MediaException mediaExceptionWrapIfNeeded = GenericMediaException.wrapIfNeeded(e, StandardErrorCode.ANDROID_MEDIAPLAYER_ERROR);
                        tryPostQOSEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, mediaExceptionWrapIfNeeded));
                        throw mediaExceptionWrapIfNeeded;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        try {
                            Closeables.close(fileInputStream, true);
                        } catch (IOException unused) {
                        }
                        throw th;
                    }
                } else {
                    this.mInitialDataSource = PlaybackDataSource.STREAMING;
                    this.mMediaPlayer.setDataSource(this.mContext, uri);
                }
                this.mMediaPlayer.prepareAsync();
                try {
                    Closeables.close(fileInputStream, true);
                } catch (IOException unused2) {
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int getBufferPercentage() {
        return this.mBufferedPercentage;
    }

    public GenericVideoPlayerState getState() {
        return this.mStateTracker.getState();
    }

    public void seekTo(int i) {
        GenericVideoPlayerState state = this.mStateTracker.getState();
        try {
            this.mStateTracker.changeState(GenericVideoPlayerState.SEEKING);
            this.mCachedPosition = i;
            this.mMediaPlayer.seekTo(i);
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
            this.mStateTracker.changeState(state);
        }
    }

    public void start() {
        try {
            GenericVideoPlayerState state = this.mStateTracker.getState();
            if (state == GenericVideoPlayerState.PAUSED_DURING_BUFFERING) {
                this.mStateTracker.changeState(GenericVideoPlayerState.BUFFERING);
                return;
            }
            if (state.isPlayback()) {
                this.mMediaPlayer.start();
                this.mStateTracker.changeState(GenericVideoPlayerState.PLAYING);
            } else {
                throw new IllegalStateException("Not able to start from state: " + state);
            }
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
        }
    }

    public void pause() {
        try {
            if (this.mStateTracker.getState() == GenericVideoPlayerState.BUFFERING) {
                this.mStateTracker.changeState(GenericVideoPlayerState.PAUSED_DURING_BUFFERING);
            } else if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                this.mStateTracker.changeState(GenericVideoPlayerState.PAUSED);
            }
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
        }
    }

    public void stop() {
        try {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
        }
        this.mStateTracker.changeState(GenericVideoPlayerState.TERMINATED);
        this.mMediaPlayer.release();
    }

    @Nonnegative
    public int getCurrentPosition() {
        try {
            if (this.mStateTracker.getState().isPlayback()) {
                this.mCachedPosition = Math.min(this.mMediaPlayer.getCurrentPosition(), this.mMediaPlayer.getDuration());
            }
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
        }
        int i = this.mCachedPosition;
        if (i < 0) {
            DLog.warnf("MediaPlayer returns negative playback position: %s", Integer.valueOf(i));
            this.mCachedPosition = 0;
        }
        return this.mCachedPosition;
    }

    public int getBufferPosition() {
        return (int) (((double) getDuration()) * (((double) this.mBufferedPercentage) / 100.0d));
    }

    public int getDuration() {
        try {
            if (this.mStateTracker.getState().isPlayback()) {
                return this.mMediaPlayer.getDuration();
            }
            return 0;
        } catch (IllegalStateException e) {
            DLog.exceptionf(e);
            return 0;
        }
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        try {
            this.mMediaPlayer.setDisplay(surfaceHolder);
        } catch (IllegalStateException unused) {
        }
    }

    public void setSurface(Surface surface) {
        this.mMediaPlayer.setSurface(surface);
    }

    public int getVideoWidth() {
        return this.mMediaPlayer.getVideoWidth();
    }

    public int getVideoHeight() {
        return this.mMediaPlayer.getVideoHeight();
    }

    public void setVolume(float f, float f2) {
        this.mMediaPlayer.setVolume(f, f2);
    }

    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mMediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        DLog.logf("onError invoked: %d,%d", Integer.valueOf(i), Integer.valueOf(i2));
        this.mStateTracker.changeState(GenericVideoPlayerState.ERRORED);
        tryPostQOSEvent(new RetriablePlaybackErrorEvent(TimeSpan.fromMilliseconds(getCurrentPosition()), new ContentException(ContentException.ContentError.UNKNOWN_ERROR, String.format(Locale.US, "onError invoked. what: %d extra: %d", Integer.valueOf(i), Integer.valueOf(i2)))));
        return true;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        DLog.logf("onCompletion invoked.");
        this.mStateTracker.changeState(GenericVideoPlayerState.COMPLETED);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        DLog.logf("onPrepared invoked.");
        this.mStateTracker.changeState(GenericVideoPlayerState.PREPARED);
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        DLog.logf("onPrepared invoked.");
        this.mStateTracker.changeState(GenericVideoPlayerState.PLAYING);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        DLog.devf("onBufferingUpdate received: %d%%", Integer.valueOf(i));
        this.mBufferedPercentage = i;
        MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = this.mOnBufferingUpdateListener;
        if (onBufferingUpdateListener != null) {
            onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i);
        }
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        DLog.logf("onInfo received: %d,%d", Integer.valueOf(i), Integer.valueOf(i2));
        boolean z = AndroidVideoPlayerConfig.INSTANCE.shouldPreventBufferBeforePrepare() && this.mStateTracker.getState().isPrePlayback();
        if (i == 701 && !z) {
            DLog.logf("MediaPlayer beginning buffering");
            this.mStateTracker.changeState(GenericVideoPlayerState.BUFFERING);
        } else if (i == 702) {
            DLog.logf("MediaPlayer ending buffering");
            if (this.mStateTracker.getState() == GenericVideoPlayerState.PAUSED_DURING_BUFFERING) {
                pause();
            } else {
                this.mStateTracker.changeState(GenericVideoPlayerState.PLAYING);
            }
        }
        return false;
    }

    private void tryPostQOSEvent(Object obj) {
        QOSCommunicationService qOSCommunicationService = this.mQOSCommunicationService;
        if (qOSCommunicationService != null) {
            qOSCommunicationService.getEventTransport().postEvent(obj);
        }
    }
}
