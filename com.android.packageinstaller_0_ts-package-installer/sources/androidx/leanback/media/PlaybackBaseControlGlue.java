package androidx.leanback.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.media.PlaybackGlueHost;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ControlButtonPresenterSelector;
import androidx.leanback.widget.OnActionClickedListener;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackRowPresenter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackBaseControlGlue<T extends PlayerAdapter> extends PlaybackGlue implements OnActionClickedListener, View.OnKeyListener {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REPEAT = 512;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SHUFFLE = 1024;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    static final boolean DEBUG = false;
    static final String TAG = "PlaybackTransportGlue";
    final PlayerAdapter.Callback mAdapterCallback;
    boolean mBuffering;
    PlaybackControlsRow mControlsRow;
    PlaybackRowPresenter mControlsRowPresenter;
    Drawable mCover;
    int mErrorCode;
    String mErrorMessage;
    boolean mErrorSet;
    boolean mFadeWhenPlaying;
    boolean mIsPlaying;
    PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
    final T mPlayerAdapter;
    PlaybackGlueHost.PlayerCallback mPlayerCallback;
    CharSequence mSubtitle;
    CharSequence mTitle;
    int mVideoHeight;
    int mVideoWidth;

    public abstract void onActionClicked(Action action);

    protected abstract PlaybackRowPresenter onCreateRowPresenter();

    public abstract boolean onKey(View view, int i, KeyEvent keyEvent);

    public PlaybackBaseControlGlue(Context context, T impl) {
        super(context);
        this.mIsPlaying = DEBUG;
        this.mFadeWhenPlaying = true;
        this.mBuffering = DEBUG;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mErrorSet = DEBUG;
        PlayerAdapter.Callback callback = new PlayerAdapter.Callback() { // from class: androidx.leanback.media.PlaybackBaseControlGlue.1
            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onPlayStateChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onPlayStateChanged();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onCurrentPositionChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onUpdateProgress();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onBufferedPositionChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onUpdateBufferedProgress();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onDurationChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onUpdateDuration();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onPlayCompleted(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onPlayCompleted();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onPreparedStateChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onPreparedStateChanged();
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onVideoSizeChanged(PlayerAdapter wrapper, int width, int height) {
                PlaybackBaseControlGlue.this.mVideoWidth = width;
                PlaybackBaseControlGlue.this.mVideoHeight = height;
                if (PlaybackBaseControlGlue.this.mPlayerCallback != null) {
                    PlaybackBaseControlGlue.this.mPlayerCallback.onVideoSizeChanged(width, height);
                }
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onError(PlayerAdapter wrapper, int errorCode, String errorMessage) {
                PlaybackBaseControlGlue.this.mErrorSet = true;
                PlaybackBaseControlGlue.this.mErrorCode = errorCode;
                PlaybackBaseControlGlue.this.mErrorMessage = errorMessage;
                if (PlaybackBaseControlGlue.this.mPlayerCallback != null) {
                    PlaybackBaseControlGlue.this.mPlayerCallback.onError(errorCode, errorMessage);
                }
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onBufferingStateChanged(PlayerAdapter wrapper, boolean start) {
                PlaybackBaseControlGlue.this.mBuffering = start;
                if (PlaybackBaseControlGlue.this.mPlayerCallback != null) {
                    PlaybackBaseControlGlue.this.mPlayerCallback.onBufferingStateChanged(start);
                }
            }

            @Override // androidx.leanback.media.PlayerAdapter.Callback
            public void onMetadataChanged(PlayerAdapter wrapper) {
                PlaybackBaseControlGlue.this.onMetadataChanged();
            }
        };
        this.mAdapterCallback = callback;
        this.mPlayerAdapter = impl;
        impl.setCallback(callback);
    }

    public final T getPlayerAdapter() {
        return this.mPlayerAdapter;
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost host) {
        super.onAttachedToHost(host);
        host.setOnKeyInterceptListener(this);
        host.setOnActionClickedListener(this);
        onCreateDefaultControlsRow();
        onCreateDefaultRowPresenter();
        host.setPlaybackRowPresenter(getPlaybackRowPresenter());
        host.setPlaybackRow(getControlsRow());
        this.mPlayerCallback = host.getPlayerCallback();
        onAttachHostCallback();
        this.mPlayerAdapter.onAttachedToHost(host);
    }

    void onAttachHostCallback() {
        int i;
        PlaybackGlueHost.PlayerCallback playerCallback = this.mPlayerCallback;
        if (playerCallback != null) {
            int i2 = this.mVideoWidth;
            if (i2 != 0 && (i = this.mVideoHeight) != 0) {
                playerCallback.onVideoSizeChanged(i2, i);
            }
            if (this.mErrorSet) {
                this.mPlayerCallback.onError(this.mErrorCode, this.mErrorMessage);
            }
            this.mPlayerCallback.onBufferingStateChanged(this.mBuffering);
        }
    }

    void onDetachHostCallback() {
        this.mErrorSet = DEBUG;
        this.mErrorCode = 0;
        this.mErrorMessage = null;
        PlaybackGlueHost.PlayerCallback playerCallback = this.mPlayerCallback;
        if (playerCallback != null) {
            playerCallback.onBufferingStateChanged(DEBUG);
        }
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onHostStart() {
        this.mPlayerAdapter.setProgressUpdatingEnabled(true);
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onHostStop() {
        this.mPlayerAdapter.setProgressUpdatingEnabled(DEBUG);
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        onDetachHostCallback();
        this.mPlayerCallback = null;
        this.mPlayerAdapter.onDetachedFromHost();
        this.mPlayerAdapter.setProgressUpdatingEnabled(DEBUG);
        super.onDetachedFromHost();
    }

    void onCreateDefaultControlsRow() {
        if (this.mControlsRow == null) {
            PlaybackControlsRow controlsRow = new PlaybackControlsRow(this);
            setControlsRow(controlsRow);
        }
    }

    void onCreateDefaultRowPresenter() {
        if (this.mControlsRowPresenter == null) {
            setPlaybackRowPresenter(onCreateRowPresenter());
        }
    }

    public void setControlsOverlayAutoHideEnabled(boolean enable) {
        this.mFadeWhenPlaying = enable;
        if (!enable && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(DEBUG);
        }
    }

    public boolean isControlsOverlayAutoHideEnabled() {
        return this.mFadeWhenPlaying;
    }

    public void setControlsRow(PlaybackControlsRow controlsRow) {
        this.mControlsRow = controlsRow;
        controlsRow.setCurrentPosition(-1L);
        this.mControlsRow.setDuration(-1L);
        this.mControlsRow.setBufferedPosition(-1L);
        if (this.mControlsRow.getPrimaryActionsAdapter() == null) {
            ArrayObjectAdapter adapter = new ArrayObjectAdapter(new ControlButtonPresenterSelector());
            onCreatePrimaryActions(adapter);
            this.mControlsRow.setPrimaryActionsAdapter(adapter);
        }
        if (this.mControlsRow.getSecondaryActionsAdapter() == null) {
            ArrayObjectAdapter secondaryActions = new ArrayObjectAdapter(new ControlButtonPresenterSelector());
            onCreateSecondaryActions(secondaryActions);
            getControlsRow().setSecondaryActionsAdapter(secondaryActions);
        }
        updateControlsRow();
    }

    public void setPlaybackRowPresenter(PlaybackRowPresenter presenter) {
        this.mControlsRowPresenter = presenter;
    }

    public PlaybackControlsRow getControlsRow() {
        return this.mControlsRow;
    }

    public PlaybackRowPresenter getPlaybackRowPresenter() {
        return this.mControlsRowPresenter;
    }

    private void updateControlsRow() {
        onMetadataChanged();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public final boolean isPlaying() {
        return this.mPlayerAdapter.isPlaying();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void play() {
        this.mPlayerAdapter.play();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void pause() {
        this.mPlayerAdapter.pause();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void next() {
        this.mPlayerAdapter.next();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void previous() {
        this.mPlayerAdapter.previous();
    }

    protected static void notifyItemChanged(ArrayObjectAdapter adapter, Object object) {
        int index = adapter.indexOf(object);
        if (index >= 0) {
            adapter.notifyArrayItemRangeChanged(index, 1);
        }
    }

    protected void onCreatePrimaryActions(ArrayObjectAdapter primaryActionsAdapter) {
    }

    protected void onCreateSecondaryActions(ArrayObjectAdapter secondaryActionsAdapter) {
    }

    protected void onUpdateProgress() {
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow != null) {
            playbackControlsRow.setCurrentPosition(this.mPlayerAdapter.isPrepared() ? getCurrentPosition() : -1L);
        }
    }

    protected void onUpdateBufferedProgress() {
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow != null) {
            playbackControlsRow.setBufferedPosition(this.mPlayerAdapter.getBufferedPosition());
        }
    }

    protected void onUpdateDuration() {
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow != null) {
            playbackControlsRow.setDuration(this.mPlayerAdapter.isPrepared() ? this.mPlayerAdapter.getDuration() : -1L);
        }
    }

    public final long getDuration() {
        return this.mPlayerAdapter.getDuration();
    }

    public long getCurrentPosition() {
        return this.mPlayerAdapter.getCurrentPosition();
    }

    public final long getBufferedPosition() {
        return this.mPlayerAdapter.getBufferedPosition();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public final boolean isPrepared() {
        return this.mPlayerAdapter.isPrepared();
    }

    protected void onPreparedStateChanged() {
        onUpdateDuration();
        List<PlaybackGlue.PlayerCallback> callbacks = getPlayerCallbacks();
        if (callbacks != null) {
            int size = callbacks.size();
            for (int i = 0; i < size; i++) {
                callbacks.get(i).onPreparedStateChanged(this);
            }
        }
    }

    public void setArt(Drawable cover) {
        if (this.mCover == cover) {
            return;
        }
        this.mCover = cover;
        this.mControlsRow.setImageDrawable(cover);
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    public Drawable getArt() {
        return this.mCover;
    }

    public void setSubtitle(CharSequence subtitle) {
        if (TextUtils.equals(subtitle, this.mSubtitle)) {
            return;
        }
        this.mSubtitle = subtitle;
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public void setTitle(CharSequence title) {
        if (TextUtils.equals(title, this.mTitle)) {
            return;
        }
        this.mTitle = title;
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    protected void onMetadataChanged() {
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow == null) {
            return;
        }
        playbackControlsRow.setImageDrawable(getArt());
        this.mControlsRow.setDuration(getDuration());
        this.mControlsRow.setCurrentPosition(getCurrentPosition());
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    protected void onPlayStateChanged() {
        List<PlaybackGlue.PlayerCallback> callbacks = getPlayerCallbacks();
        if (callbacks != null) {
            int size = callbacks.size();
            for (int i = 0; i < size; i++) {
                callbacks.get(i).onPlayStateChanged(this);
            }
        }
    }

    protected void onPlayCompleted() {
        List<PlaybackGlue.PlayerCallback> callbacks = getPlayerCallbacks();
        if (callbacks != null) {
            int size = callbacks.size();
            for (int i = 0; i < size; i++) {
                callbacks.get(i).onPlayCompleted(this);
            }
        }
    }

    public final void seekTo(long position) {
        this.mPlayerAdapter.seekTo(position);
    }

    public long getSupportedActions() {
        return this.mPlayerAdapter.getSupportedActions();
    }
}
