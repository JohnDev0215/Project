package androidx.leanback.media;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PlaybackSeekDataProvider;
import androidx.leanback.widget.PlaybackSeekUi;
import androidx.leanback.widget.PlaybackTransportRowPresenter;
import androidx.leanback.widget.RowPresenter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackTransportControlGlue<T extends PlayerAdapter> extends PlaybackBaseControlGlue<T> {
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    static final String TAG = "PlaybackTransportGlue";
    static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    final WeakReference<PlaybackBaseControlGlue> mGlueWeakReference;
    final PlaybackTransportControlGlue<T>.SeekUiClient mPlaybackSeekUiClient;
    boolean mSeekEnabled;
    PlaybackSeekDataProvider mSeekProvider;

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            PlaybackTransportControlGlue glue;
            if (msg.what == 100 && (glue = (PlaybackTransportControlGlue) ((WeakReference) msg.obj).get()) != null) {
                glue.onUpdatePlaybackState();
            }
        }
    }

    public PlaybackTransportControlGlue(Context context, T impl) {
        super(context, impl);
        this.mGlueWeakReference = new WeakReference<>(this);
        this.mPlaybackSeekUiClient = new SeekUiClient();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    public void setControlsRow(PlaybackControlsRow controlsRow) {
        super.setControlsRow(controlsRow);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        onUpdatePlaybackState();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onCreatePrimaryActions(ArrayObjectAdapter primaryActionsAdapter) {
        PlaybackControlsRow.PlayPauseAction playPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
        this.mPlayPauseAction = playPauseAction;
        primaryActionsAdapter.add(playPauseAction);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected PlaybackRowPresenter onCreateRowPresenter() {
        AbstractDetailsDescriptionPresenter detailsPresenter = new AbstractDetailsDescriptionPresenter() { // from class: androidx.leanback.media.PlaybackTransportControlGlue.1
            @Override // androidx.leanback.widget.AbstractDetailsDescriptionPresenter
            protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object obj) {
                PlaybackBaseControlGlue glue = (PlaybackBaseControlGlue) obj;
                viewHolder.getTitle().setText(glue.getTitle());
                viewHolder.getSubtitle().setText(glue.getSubtitle());
            }
        };
        PlaybackTransportRowPresenter rowPresenter = new PlaybackTransportRowPresenter() { // from class: androidx.leanback.media.PlaybackTransportControlGlue.2
            @Override // androidx.leanback.widget.PlaybackTransportRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onBindRowViewHolder(RowPresenter.ViewHolder vh, Object item) {
                super.onBindRowViewHolder(vh, item);
                vh.setOnKeyListener(PlaybackTransportControlGlue.this);
            }

            @Override // androidx.leanback.widget.PlaybackTransportRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onUnbindRowViewHolder(RowPresenter.ViewHolder vh) {
                super.onUnbindRowViewHolder(vh);
                vh.setOnKeyListener(null);
            }
        };
        rowPresenter.setDescriptionPresenter(detailsPresenter);
        return rowPresenter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        if (playbackGlueHost instanceof PlaybackSeekUi) {
            ((PlaybackSeekUi) playbackGlueHost).setPlaybackSeekUiClient(this.mPlaybackSeekUiClient);
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        super.onDetachedFromHost();
        if (getHost() instanceof PlaybackSeekUi) {
            ((PlaybackSeekUi) getHost()).setPlaybackSeekUiClient(null);
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onUpdateProgress() {
        if (!this.mPlaybackSeekUiClient.mIsSeek) {
            super.onUpdateProgress();
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.widget.OnActionClickedListener
    public void onActionClicked(Action action) {
        dispatchAction(action, null);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != 4 && keyCode != 111) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    ObjectAdapter primaryActionsAdapter = this.mControlsRow.getPrimaryActionsAdapter();
                    Action action = this.mControlsRow.getActionForKeyCode(primaryActionsAdapter, keyCode);
                    if (action == null) {
                        action = this.mControlsRow.getActionForKeyCode(this.mControlsRow.getSecondaryActionsAdapter(), keyCode);
                    }
                    if (action == null) {
                        return false;
                    }
                    if (event.getAction() == 0) {
                        dispatchAction(action, event);
                        return true;
                    }
                    return true;
            }
        }
        return false;
    }

    void onUpdatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mIsPlaying);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        Handler handler = sHandler;
        handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000L);
    }

    boolean dispatchAction(Action action, KeyEvent keyEvent) {
        if (!(action instanceof PlaybackControlsRow.PlayPauseAction)) {
            if (action instanceof PlaybackControlsRow.SkipNextAction) {
                next();
                return true;
            }
            if (!(action instanceof PlaybackControlsRow.SkipPreviousAction)) {
                return false;
            }
            previous();
            return true;
        }
        boolean canPlay = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 126;
        boolean canPause = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 127;
        if (canPause && this.mIsPlaying) {
            this.mIsPlaying = false;
            pause();
        } else if (canPlay && !this.mIsPlaying) {
            this.mIsPlaying = true;
            play();
        }
        onUpdatePlaybackStatusAfterUserAction();
        return true;
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onPlayStateChanged() {
        if (sHandler.hasMessages(100, this.mGlueWeakReference)) {
            sHandler.removeMessages(100, this.mGlueWeakReference);
            if (this.mPlayerAdapter.isPlaying() != this.mIsPlaying) {
                Handler handler = sHandler;
                handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000L);
            } else {
                onUpdatePlaybackState();
            }
        } else {
            onUpdatePlaybackState();
        }
        super.onPlayStateChanged();
    }

    void onUpdatePlaybackState() {
        this.mIsPlaying = this.mPlayerAdapter.isPlaying();
        updatePlaybackState(this.mIsPlaying);
    }

    private void updatePlaybackState(boolean isPlaying) {
        if (this.mControlsRow == null) {
            return;
        }
        int index = 1;
        if (isPlaying) {
            this.mPlayerAdapter.setProgressUpdatingEnabled(true);
        } else {
            onUpdateProgress();
            this.mPlayerAdapter.setProgressUpdatingEnabled(this.mPlaybackSeekUiClient.mIsSeek);
        }
        if (this.mFadeWhenPlaying && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(isPlaying);
        }
        if (this.mPlayPauseAction != null) {
            if (!isPlaying) {
                index = 0;
            }
            if (this.mPlayPauseAction.getIndex() != index) {
                this.mPlayPauseAction.setIndex(index);
                notifyItemChanged((ArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter(), this.mPlayPauseAction);
            }
        }
    }

    class SeekUiClient extends PlaybackSeekUi.Client {
        boolean mIsSeek;
        long mLastUserPosition;
        boolean mPausedBeforeSeek;
        long mPositionBeforeSeek;

        SeekUiClient() {
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public PlaybackSeekDataProvider getPlaybackSeekDataProvider() {
            return PlaybackTransportControlGlue.this.mSeekProvider;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public boolean isSeekEnabled() {
            return PlaybackTransportControlGlue.this.mSeekProvider != null || PlaybackTransportControlGlue.this.mSeekEnabled;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekStarted() {
            this.mIsSeek = true;
            this.mPausedBeforeSeek = !PlaybackTransportControlGlue.this.isPlaying();
            PlaybackTransportControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(true);
            this.mPositionBeforeSeek = PlaybackTransportControlGlue.this.mSeekProvider == null ? PlaybackTransportControlGlue.this.mPlayerAdapter.getCurrentPosition() : -1L;
            this.mLastUserPosition = -1L;
            PlaybackTransportControlGlue.this.pause();
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekPositionChanged(long pos) {
            if (PlaybackTransportControlGlue.this.mSeekProvider == null) {
                PlaybackTransportControlGlue.this.mPlayerAdapter.seekTo(pos);
            } else {
                this.mLastUserPosition = pos;
            }
            if (PlaybackTransportControlGlue.this.mControlsRow != null) {
                PlaybackTransportControlGlue.this.mControlsRow.setCurrentPosition(pos);
            }
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekFinished(boolean cancelled) {
            if (!cancelled) {
                long j = this.mLastUserPosition;
                if (j >= 0) {
                    PlaybackTransportControlGlue.this.seekTo(j);
                }
            } else {
                long j2 = this.mPositionBeforeSeek;
                if (j2 >= 0) {
                    PlaybackTransportControlGlue.this.seekTo(j2);
                }
            }
            this.mIsSeek = false;
            if (this.mPausedBeforeSeek) {
                PlaybackTransportControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(false);
                PlaybackTransportControlGlue.this.onUpdateProgress();
            } else {
                PlaybackTransportControlGlue.this.play();
            }
        }
    }

    public final void setSeekProvider(PlaybackSeekDataProvider seekProvider) {
        this.mSeekProvider = seekProvider;
    }

    public final PlaybackSeekDataProvider getSeekProvider() {
        return this.mSeekProvider;
    }

    public final void setSeekEnabled(boolean seekEnabled) {
        this.mSeekEnabled = seekEnabled;
    }

    public final boolean isSeekEnabled() {
        return this.mSeekEnabled;
    }
}
