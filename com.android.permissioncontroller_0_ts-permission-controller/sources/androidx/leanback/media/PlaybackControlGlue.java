package androidx.leanback.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ControlButtonPresenterSelector;
import androidx.leanback.widget.OnActionClickedListener;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackControlsRowPresenter;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SparseArrayObjectAdapter;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PlaybackControlGlue extends PlaybackGlue implements OnActionClickedListener, View.OnKeyListener {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    private static final int NUMBER_OF_SEEK_SPEEDS = 5;
    public static final int PLAYBACK_SPEED_FAST_L0 = 10;
    public static final int PLAYBACK_SPEED_FAST_L1 = 11;
    public static final int PLAYBACK_SPEED_FAST_L2 = 12;
    public static final int PLAYBACK_SPEED_FAST_L3 = 13;
    public static final int PLAYBACK_SPEED_FAST_L4 = 14;
    public static final int PLAYBACK_SPEED_INVALID = -1;
    public static final int PLAYBACK_SPEED_NORMAL = 1;
    public static final int PLAYBACK_SPEED_PAUSED = 0;
    static final String TAG = "PlaybackControlGlue";
    private static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    private PlaybackControlsRow mControlsRow;
    private PlaybackRowPresenter mControlsRowPresenter;
    private boolean mFadeWhenPlaying;
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private final int[] mFastForwardSpeeds;
    final WeakReference<PlaybackControlGlue> mGlueWeakReference;
    private PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
    private int mPlaybackSpeed;
    private PlaybackControlsRow.RewindAction mRewindAction;
    private final int[] mRewindSpeeds;
    private PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;

    public abstract int getCurrentPosition();

    public abstract int getCurrentSpeedId();

    public abstract Drawable getMediaArt();

    public abstract int getMediaDuration();

    public abstract CharSequence getMediaSubtitle();

    public abstract CharSequence getMediaTitle();

    public abstract long getSupportedActions();

    public abstract boolean hasValidMedia();

    public abstract boolean isMediaPlaying();

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            PlaybackControlGlue glue;
            if (msg.what == 100 && (glue = (PlaybackControlGlue) ((WeakReference) msg.obj).get()) != null) {
                glue.updatePlaybackState();
            }
        }
    }

    public PlaybackControlGlue(Context context, int[] seekSpeeds) {
        this(context, seekSpeeds, seekSpeeds);
    }

    public PlaybackControlGlue(Context context, int[] fastForwardSpeeds, int[] rewindSpeeds) {
        super(context);
        this.mPlaybackSpeed = 1;
        this.mFadeWhenPlaying = true;
        this.mGlueWeakReference = new WeakReference<>(this);
        if (fastForwardSpeeds.length == 0 || fastForwardSpeeds.length > 5) {
            throw new IllegalStateException("invalid fastForwardSpeeds array size");
        }
        this.mFastForwardSpeeds = fastForwardSpeeds;
        if (rewindSpeeds.length == 0 || rewindSpeeds.length > 5) {
            throw new IllegalStateException("invalid rewindSpeeds array size");
        }
        this.mRewindSpeeds = rewindSpeeds;
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost host) {
        super.onAttachedToHost(host);
        host.setOnKeyInterceptListener(this);
        host.setOnActionClickedListener(this);
        if (getControlsRow() == null || getPlaybackRowPresenter() == null) {
            onCreateControlsRowAndPresenter();
        }
        host.setPlaybackRowPresenter(getPlaybackRowPresenter());
        host.setPlaybackRow(getControlsRow());
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onHostStart() {
        enableProgressUpdating(true);
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onHostStop() {
        enableProgressUpdating(false);
    }

    @Override // androidx.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        enableProgressUpdating(false);
        super.onDetachedFromHost();
    }

    protected void onCreateControlsRowAndPresenter() {
        if (getControlsRow() == null) {
            PlaybackControlsRow controlsRow = new PlaybackControlsRow(this);
            setControlsRow(controlsRow);
        }
        if (getPlaybackRowPresenter() == null) {
            AbstractDetailsDescriptionPresenter detailsPresenter = new AbstractDetailsDescriptionPresenter() { // from class: androidx.leanback.media.PlaybackControlGlue.1
                @Override // androidx.leanback.widget.AbstractDetailsDescriptionPresenter
                protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object object) {
                    PlaybackControlGlue glue = (PlaybackControlGlue) object;
                    if (glue.hasValidMedia()) {
                        viewHolder.getTitle().setText(glue.getMediaTitle());
                        viewHolder.getSubtitle().setText(glue.getMediaSubtitle());
                    } else {
                        viewHolder.getTitle().setText("");
                        viewHolder.getSubtitle().setText("");
                    }
                }
            };
            setPlaybackRowPresenter(new PlaybackControlsRowPresenter(detailsPresenter) { // from class: androidx.leanback.media.PlaybackControlGlue.2
                @Override // androidx.leanback.widget.PlaybackControlsRowPresenter, androidx.leanback.widget.RowPresenter
                protected void onBindRowViewHolder(RowPresenter.ViewHolder vh, Object item) {
                    super.onBindRowViewHolder(vh, item);
                    vh.setOnKeyListener(PlaybackControlGlue.this);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRowPresenter, androidx.leanback.widget.RowPresenter
                protected void onUnbindRowViewHolder(RowPresenter.ViewHolder vh) {
                    super.onUnbindRowViewHolder(vh);
                    vh.setOnKeyListener(null);
                }
            });
        }
    }

    public int[] getFastForwardSpeeds() {
        return this.mFastForwardSpeeds;
    }

    public int[] getRewindSpeeds() {
        return this.mRewindSpeeds;
    }

    public void setFadingEnabled(boolean enable) {
        this.mFadeWhenPlaying = enable;
        if (!enable && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(false);
        }
    }

    public boolean isFadingEnabled() {
        return this.mFadeWhenPlaying;
    }

    public void setControlsRow(PlaybackControlsRow controlsRow) {
        this.mControlsRow = controlsRow;
        controlsRow.setPrimaryActionsAdapter(createPrimaryActionsAdapter(new ControlButtonPresenterSelector()));
        ArrayObjectAdapter secondaryActions = new ArrayObjectAdapter(new ControlButtonPresenterSelector());
        onCreateSecondaryActions(secondaryActions);
        getControlsRow().setSecondaryActionsAdapter(secondaryActions);
        updateControlsRow();
    }

    protected SparseArrayObjectAdapter createPrimaryActionsAdapter(PresenterSelector presenterSelector) {
        SparseArrayObjectAdapter adapter = new SparseArrayObjectAdapter(presenterSelector);
        onCreatePrimaryActions(adapter);
        return adapter;
    }

    @Deprecated
    public void setControlsRowPresenter(PlaybackControlsRowPresenter presenter) {
        this.mControlsRowPresenter = presenter;
    }

    public PlaybackControlsRow getControlsRow() {
        return this.mControlsRow;
    }

    @Deprecated
    public PlaybackControlsRowPresenter getControlsRowPresenter() {
        PlaybackRowPresenter playbackRowPresenter = this.mControlsRowPresenter;
        if (playbackRowPresenter instanceof PlaybackControlsRowPresenter) {
            return (PlaybackControlsRowPresenter) playbackRowPresenter;
        }
        return null;
    }

    public void setPlaybackRowPresenter(PlaybackRowPresenter presenter) {
        this.mControlsRowPresenter = presenter;
    }

    public PlaybackRowPresenter getPlaybackRowPresenter() {
        return this.mControlsRowPresenter;
    }

    public void enableProgressUpdating(boolean enable) {
    }

    public int getUpdatePeriod() {
        return 500;
    }

    public void updateProgress() {
        int position = getCurrentPosition();
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow != null) {
            playbackControlsRow.setCurrentTime(position);
        }
    }

    public void onActionClicked(Action action) {
        dispatchAction(action, null);
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != 4 && keyCode != 111) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    SparseArrayObjectAdapter primaryActionsAdapter = (SparseArrayObjectAdapter) this.mControlsRow.getPrimaryActionsAdapter();
                    Action action = this.mControlsRow.getActionForKeyCode(primaryActionsAdapter, keyCode);
                    if (action == null || !(action == primaryActionsAdapter.lookup(64) || action == primaryActionsAdapter.lookup(32) || action == primaryActionsAdapter.lookup(128) || action == primaryActionsAdapter.lookup(16) || action == primaryActionsAdapter.lookup(256))) {
                        return false;
                    }
                    if (event.getAction() == 0) {
                        dispatchAction(action, event);
                    }
                    return true;
            }
        }
        int i = this.mPlaybackSpeed;
        boolean abortSeek = i >= 10 || i <= -10;
        if (!abortSeek) {
            return false;
        }
        this.mPlaybackSpeed = 1;
        play(1);
        updatePlaybackStatusAfterUserAction();
        return keyCode == 4 || keyCode == 111;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean dispatchAction(androidx.leanback.widget.Action r7, android.view.KeyEvent r8) {
        /*
            r6 = this;
            r0 = 0
            androidx.leanback.widget.PlaybackControlsRow$PlayPauseAction r1 = r6.mPlayPauseAction
            r2 = 1
            if (r7 != r1) goto L52
            r1 = 85
            r3 = 0
            if (r8 == 0) goto L1c
            int r4 = r8.getKeyCode()
            if (r4 == r1) goto L1c
            int r4 = r8.getKeyCode()
            r5 = 126(0x7e, float:1.77E-43)
            if (r4 != r5) goto L1a
            goto L1c
        L1a:
            r4 = r3
            goto L1d
        L1c:
            r4 = r2
        L1d:
            if (r8 == 0) goto L30
            int r5 = r8.getKeyCode()
            if (r5 == r1) goto L30
            int r1 = r8.getKeyCode()
            r5 = 127(0x7f, float:1.78E-43)
            if (r1 != r5) goto L2e
            goto L30
        L2e:
            r1 = r3
            goto L31
        L30:
            r1 = r2
        L31:
            if (r1 == 0) goto L42
            int r5 = r6.mPlaybackSpeed
            if (r4 == 0) goto L3a
            if (r5 != r2) goto L42
            goto L3c
        L3a:
            if (r5 == 0) goto L42
        L3c:
            r6.mPlaybackSpeed = r3
            r6.pause()
            goto L4d
        L42:
            if (r4 == 0) goto L4d
            int r3 = r6.mPlaybackSpeed
            if (r3 == r2) goto L4d
            r6.mPlaybackSpeed = r2
            r6.play(r2)
        L4d:
            r6.updatePlaybackStatusAfterUserAction()
            r0 = 1
            goto Lac
        L52:
            androidx.leanback.widget.PlaybackControlsRow$SkipNextAction r1 = r6.mSkipNextAction
            if (r7 != r1) goto L5b
            r6.next()
            r0 = 1
            goto Lac
        L5b:
            androidx.leanback.widget.PlaybackControlsRow$SkipPreviousAction r1 = r6.mSkipPreviousAction
            if (r7 != r1) goto L64
            r6.previous()
            r0 = 1
            goto Lac
        L64:
            androidx.leanback.widget.PlaybackControlsRow$FastForwardAction r1 = r6.mFastForwardAction
            if (r7 != r1) goto L88
            int r1 = r6.mPlaybackSpeed
            int r3 = r6.getMaxForwardSpeedId()
            if (r1 >= r3) goto L86
            int r1 = r6.mPlaybackSpeed
            switch(r1) {
                case 10: goto L7a;
                case 11: goto L7a;
                case 12: goto L7a;
                case 13: goto L7a;
                default: goto L75;
            }
        L75:
            r1 = 10
            r6.mPlaybackSpeed = r1
            goto L7e
        L7a:
            int r1 = r1 + r2
            r6.mPlaybackSpeed = r1
        L7e:
            int r1 = r6.mPlaybackSpeed
            r6.play(r1)
            r6.updatePlaybackStatusAfterUserAction()
        L86:
            r0 = 1
            goto Lac
        L88:
            androidx.leanback.widget.PlaybackControlsRow$RewindAction r1 = r6.mRewindAction
            if (r7 != r1) goto Lac
            int r1 = r6.mPlaybackSpeed
            int r3 = r6.getMaxRewindSpeedId()
            int r3 = -r3
            if (r1 <= r3) goto Lab
            int r1 = r6.mPlaybackSpeed
            switch(r1) {
                case -13: goto L9f;
                case -12: goto L9f;
                case -11: goto L9f;
                case -10: goto L9f;
                default: goto L9a;
            }
        L9a:
            r1 = -10
            r6.mPlaybackSpeed = r1
            goto La3
        L9f:
            int r1 = r1 - r2
            r6.mPlaybackSpeed = r1
        La3:
            int r1 = r6.mPlaybackSpeed
            r6.play(r1)
            r6.updatePlaybackStatusAfterUserAction()
        Lab:
            r0 = 1
        Lac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.media.PlaybackControlGlue.dispatchAction(androidx.leanback.widget.Action, android.view.KeyEvent):boolean");
    }

    private int getMaxForwardSpeedId() {
        return (this.mFastForwardSpeeds.length - 1) + 10;
    }

    private int getMaxRewindSpeedId() {
        return (this.mRewindSpeeds.length - 1) + 10;
    }

    private void updateControlsRow() {
        updateRowMetadata();
        updateControlButtons();
        sHandler.removeMessages(100, this.mGlueWeakReference);
        updatePlaybackState();
    }

    private void updatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mPlaybackSpeed);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        Handler handler = sHandler;
        handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000L);
    }

    public void play(int speed) {
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public final void play() {
        play(1);
    }

    private void updateRowMetadata() {
        if (this.mControlsRow == null) {
            return;
        }
        if (!hasValidMedia()) {
            this.mControlsRow.setImageDrawable(null);
            this.mControlsRow.setTotalTime(0);
            this.mControlsRow.setCurrentTime(0);
        } else {
            this.mControlsRow.setImageDrawable(getMediaArt());
            this.mControlsRow.setTotalTime(getMediaDuration());
            this.mControlsRow.setCurrentTime(getCurrentPosition());
        }
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    void updatePlaybackState() {
        if (hasValidMedia()) {
            int currentSpeedId = getCurrentSpeedId();
            this.mPlaybackSpeed = currentSpeedId;
            updatePlaybackState(currentSpeedId);
        }
    }

    void updateControlButtons() {
        SparseArrayObjectAdapter primaryActionsAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        long actions = getSupportedActions();
        if ((actions & 16) != 0 && this.mSkipPreviousAction == null) {
            PlaybackControlsRow.SkipPreviousAction skipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(getContext());
            this.mSkipPreviousAction = skipPreviousAction;
            primaryActionsAdapter.set(16, skipPreviousAction);
        } else if ((16 & actions) == 0 && this.mSkipPreviousAction != null) {
            primaryActionsAdapter.clear(16);
            this.mSkipPreviousAction = null;
        }
        if ((actions & 32) != 0 && this.mRewindAction == null) {
            PlaybackControlsRow.RewindAction rewindAction = new PlaybackControlsRow.RewindAction(getContext(), this.mRewindSpeeds.length);
            this.mRewindAction = rewindAction;
            primaryActionsAdapter.set(32, rewindAction);
        } else if ((32 & actions) == 0 && this.mRewindAction != null) {
            primaryActionsAdapter.clear(32);
            this.mRewindAction = null;
        }
        if ((actions & 64) != 0 && this.mPlayPauseAction == null) {
            PlaybackControlsRow.PlayPauseAction playPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
            this.mPlayPauseAction = playPauseAction;
            primaryActionsAdapter.set(64, playPauseAction);
        } else if ((64 & actions) == 0 && this.mPlayPauseAction != null) {
            primaryActionsAdapter.clear(64);
            this.mPlayPauseAction = null;
        }
        if ((actions & 128) != 0 && this.mFastForwardAction == null) {
            PlaybackControlsRow.FastForwardAction fastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext(), this.mFastForwardSpeeds.length);
            this.mFastForwardAction = fastForwardAction;
            primaryActionsAdapter.set(128, fastForwardAction);
        } else if ((128 & actions) == 0 && this.mFastForwardAction != null) {
            primaryActionsAdapter.clear(128);
            this.mFastForwardAction = null;
        }
        if ((actions & 256) != 0 && this.mSkipNextAction == null) {
            PlaybackControlsRow.SkipNextAction skipNextAction = new PlaybackControlsRow.SkipNextAction(getContext());
            this.mSkipNextAction = skipNextAction;
            primaryActionsAdapter.set(256, skipNextAction);
        } else if ((256 & actions) == 0 && this.mSkipNextAction != null) {
            primaryActionsAdapter.clear(256);
            this.mSkipNextAction = null;
        }
    }

    private void updatePlaybackState(int playbackSpeed) {
        if (this.mControlsRow == null) {
            return;
        }
        SparseArrayObjectAdapter primaryActionsAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        if (this.mFastForwardAction != null) {
            int index = 0;
            if (playbackSpeed >= 10) {
                index = (playbackSpeed - 10) + 1;
            }
            if (this.mFastForwardAction.getIndex() != index) {
                this.mFastForwardAction.setIndex(index);
                notifyItemChanged(primaryActionsAdapter, this.mFastForwardAction);
            }
        }
        if (this.mRewindAction != null) {
            int index2 = 0;
            if (playbackSpeed <= -10) {
                index2 = ((-playbackSpeed) - 10) + 1;
            }
            if (this.mRewindAction.getIndex() != index2) {
                this.mRewindAction.setIndex(index2);
                notifyItemChanged(primaryActionsAdapter, this.mRewindAction);
            }
        }
        if (playbackSpeed == 0) {
            updateProgress();
            enableProgressUpdating(false);
        } else {
            enableProgressUpdating(true);
        }
        if (this.mFadeWhenPlaying && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(playbackSpeed == 1);
        }
        if (this.mPlayPauseAction != null) {
            int index3 = playbackSpeed == 0 ? 0 : 1;
            if (this.mPlayPauseAction.getIndex() != index3) {
                this.mPlayPauseAction.setIndex(index3);
                notifyItemChanged(primaryActionsAdapter, this.mPlayPauseAction);
            }
        }
        List<PlaybackGlue.PlayerCallback> callbacks = getPlayerCallbacks();
        if (callbacks != null) {
            int size = callbacks.size();
            for (int i = 0; i < size; i++) {
                callbacks.get(i).onPlayStateChanged(this);
            }
        }
    }

    private static void notifyItemChanged(SparseArrayObjectAdapter adapter, Object object) {
        int index = adapter.indexOf(object);
        if (index >= 0) {
            adapter.notifyArrayItemRangeChanged(index, 1);
        }
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public boolean isPlaying() {
        return isMediaPlaying();
    }

    protected void onCreatePrimaryActions(SparseArrayObjectAdapter primaryActionsAdapter) {
    }

    protected void onCreateSecondaryActions(ArrayObjectAdapter secondaryActionsAdapter) {
    }

    protected void onStateChanged() {
        if (!hasValidMedia()) {
            return;
        }
        if (sHandler.hasMessages(100, this.mGlueWeakReference)) {
            sHandler.removeMessages(100, this.mGlueWeakReference);
            if (getCurrentSpeedId() != this.mPlaybackSpeed) {
                Handler handler = sHandler;
                handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000L);
                return;
            } else {
                updatePlaybackState();
                return;
            }
        }
        updatePlaybackState();
    }

    protected void onMetadataChanged() {
        updateRowMetadata();
    }
}
