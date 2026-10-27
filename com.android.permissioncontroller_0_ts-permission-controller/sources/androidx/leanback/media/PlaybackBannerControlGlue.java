package androidx.leanback.media;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackControlsRowPresenter;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.RowPresenter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackBannerControlGlue<T extends PlayerAdapter> extends PlaybackBaseControlGlue<T> {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    private static final int NUMBER_OF_SEEK_SPEEDS = 5;
    public static final int PLAYBACK_SPEED_FAST_L0 = 10;
    public static final int PLAYBACK_SPEED_FAST_L1 = 11;
    public static final int PLAYBACK_SPEED_FAST_L2 = 12;
    public static final int PLAYBACK_SPEED_FAST_L3 = 13;
    public static final int PLAYBACK_SPEED_FAST_L4 = 14;
    public static final int PLAYBACK_SPEED_INVALID = -1;
    public static final int PLAYBACK_SPEED_NORMAL = 1;
    public static final int PLAYBACK_SPEED_PAUSED = 0;
    private static final String TAG = PlaybackBannerControlGlue.class.getSimpleName();
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private final int[] mFastForwardSpeeds;
    private boolean mIsCustomizedFastForwardSupported;
    private boolean mIsCustomizedRewindSupported;
    private int mPlaybackSpeed;
    private PlaybackControlsRow.RewindAction mRewindAction;
    private final int[] mRewindSpeeds;
    private PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;
    private long mStartPosition;
    private long mStartTime;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ACTION_ {
    }

    public PlaybackBannerControlGlue(Context context, int[] seekSpeeds, T impl) {
        this(context, seekSpeeds, seekSpeeds, impl);
    }

    public PlaybackBannerControlGlue(Context context, int[] fastForwardSpeeds, int[] rewindSpeeds, T impl) {
        super(context, impl);
        this.mPlaybackSpeed = 0;
        this.mStartPosition = 0L;
        if (fastForwardSpeeds.length == 0 || fastForwardSpeeds.length > 5) {
            throw new IllegalArgumentException("invalid fastForwardSpeeds array size");
        }
        this.mFastForwardSpeeds = fastForwardSpeeds;
        if (rewindSpeeds.length == 0 || rewindSpeeds.length > 5) {
            throw new IllegalArgumentException("invalid rewindSpeeds array size");
        }
        this.mRewindSpeeds = rewindSpeeds;
        if ((this.mPlayerAdapter.getSupportedActions() & 128) != 0) {
            this.mIsCustomizedFastForwardSupported = true;
        }
        if ((this.mPlayerAdapter.getSupportedActions() & 32) != 0) {
            this.mIsCustomizedRewindSupported = true;
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    public void setControlsRow(PlaybackControlsRow controlsRow) {
        super.setControlsRow(controlsRow);
        onUpdatePlaybackState();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onCreatePrimaryActions(ArrayObjectAdapter primaryActionsAdapter) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        long supportedActions = getSupportedActions();
        if ((supportedActions & 16) != 0 && this.mSkipPreviousAction == null) {
            PlaybackControlsRow.SkipPreviousAction skipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(getContext());
            this.mSkipPreviousAction = skipPreviousAction;
            primaryActionsAdapter.add(skipPreviousAction);
        } else if ((16 & supportedActions) == 0 && (obj = this.mSkipPreviousAction) != null) {
            primaryActionsAdapter.remove(obj);
            this.mSkipPreviousAction = null;
        }
        if ((supportedActions & 32) != 0 && this.mRewindAction == null) {
            PlaybackControlsRow.RewindAction rewindAction = new PlaybackControlsRow.RewindAction(getContext(), this.mRewindSpeeds.length);
            this.mRewindAction = rewindAction;
            primaryActionsAdapter.add(rewindAction);
        } else if ((32 & supportedActions) == 0 && (obj2 = this.mRewindAction) != null) {
            primaryActionsAdapter.remove(obj2);
            this.mRewindAction = null;
        }
        if ((supportedActions & 64) != 0 && this.mPlayPauseAction == null) {
            this.mPlayPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
            PlaybackControlsRow.PlayPauseAction playPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
            this.mPlayPauseAction = playPauseAction;
            primaryActionsAdapter.add(playPauseAction);
        } else if ((64 & supportedActions) == 0 && this.mPlayPauseAction != null) {
            primaryActionsAdapter.remove(this.mPlayPauseAction);
            this.mPlayPauseAction = null;
        }
        if ((supportedActions & 128) != 0 && this.mFastForwardAction == null) {
            this.mFastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext(), this.mFastForwardSpeeds.length);
            PlaybackControlsRow.FastForwardAction fastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext(), this.mFastForwardSpeeds.length);
            this.mFastForwardAction = fastForwardAction;
            primaryActionsAdapter.add(fastForwardAction);
        } else if ((128 & supportedActions) == 0 && (obj3 = this.mFastForwardAction) != null) {
            primaryActionsAdapter.remove(obj3);
            this.mFastForwardAction = null;
        }
        if ((supportedActions & 256) != 0 && this.mSkipNextAction == null) {
            PlaybackControlsRow.SkipNextAction skipNextAction = new PlaybackControlsRow.SkipNextAction(getContext());
            this.mSkipNextAction = skipNextAction;
            primaryActionsAdapter.add(skipNextAction);
        } else if ((256 & supportedActions) == 0 && (obj4 = this.mSkipNextAction) != null) {
            primaryActionsAdapter.remove(obj4);
            this.mSkipNextAction = null;
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected PlaybackRowPresenter onCreateRowPresenter() {
        AbstractDetailsDescriptionPresenter detailsPresenter = new AbstractDetailsDescriptionPresenter() { // from class: androidx.leanback.media.PlaybackBannerControlGlue.1
            @Override // androidx.leanback.widget.AbstractDetailsDescriptionPresenter
            protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object object) {
                PlaybackBannerControlGlue glue = (PlaybackBannerControlGlue) object;
                viewHolder.getTitle().setText(glue.getTitle());
                viewHolder.getSubtitle().setText(glue.getSubtitle());
            }
        };
        PlaybackControlsRowPresenter rowPresenter = new PlaybackControlsRowPresenter(detailsPresenter) { // from class: androidx.leanback.media.PlaybackBannerControlGlue.2
            @Override // androidx.leanback.widget.PlaybackControlsRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onBindRowViewHolder(RowPresenter.ViewHolder vh, Object item) {
                super.onBindRowViewHolder(vh, item);
                vh.setOnKeyListener(PlaybackBannerControlGlue.this);
            }

            @Override // androidx.leanback.widget.PlaybackControlsRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onUnbindRowViewHolder(RowPresenter.ViewHolder vh) {
                super.onUnbindRowViewHolder(vh);
                vh.setOnKeyListener(null);
            }
        };
        return rowPresenter;
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
                    }
                    return true;
            }
        }
        int i = this.mPlaybackSpeed;
        boolean abortSeek = i >= 10 || i <= -10;
        if (!abortSeek) {
            return false;
        }
        play();
        onUpdatePlaybackStatusAfterUserAction();
        return keyCode == 4 || keyCode == 111;
    }

    void onUpdatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mIsPlaying);
    }

    private void incrementFastForwardPlaybackSpeed() {
        int i = this.mPlaybackSpeed;
        switch (i) {
            case 10:
            case 11:
            case 12:
            case 13:
                this.mPlaybackSpeed = i + 1;
                break;
            default:
                this.mPlaybackSpeed = 10;
                break;
        }
    }

    private void decrementRewindPlaybackSpeed() {
        int i = this.mPlaybackSpeed;
        switch (i) {
            case -13:
            case -12:
            case -11:
            case -10:
                this.mPlaybackSpeed = i - 1;
                break;
            default:
                this.mPlaybackSpeed = -10;
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x003d  */
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
            if (r7 != r1) goto L4b
            r1 = 0
            r3 = 85
            if (r8 == 0) goto L1c
            int r4 = r8.getKeyCode()
            if (r4 == r3) goto L1c
            int r4 = r8.getKeyCode()
            r5 = 126(0x7e, float:1.77E-43)
            if (r4 != r5) goto L1a
            goto L1c
        L1a:
            r4 = r1
            goto L1d
        L1c:
            r4 = r2
        L1d:
            if (r8 == 0) goto L2d
            int r5 = r8.getKeyCode()
            if (r5 == r3) goto L2d
            int r3 = r8.getKeyCode()
            r5 = 127(0x7f, float:1.78E-43)
            if (r3 != r5) goto L2e
        L2d:
            r1 = r2
        L2e:
            if (r1 == 0) goto L3d
            int r3 = r6.mPlaybackSpeed
            if (r4 == 0) goto L37
            if (r3 != r2) goto L3d
            goto L39
        L37:
            if (r3 == 0) goto L3d
        L39:
            r6.pause()
            goto L46
        L3d:
            if (r4 == 0) goto L46
            int r3 = r6.mPlaybackSpeed
            if (r3 == r2) goto L46
            r6.play()
        L46:
            r6.onUpdatePlaybackStatusAfterUserAction()
            r0 = 1
            goto Lb3
        L4b:
            androidx.leanback.widget.PlaybackControlsRow$SkipNextAction r1 = r6.mSkipNextAction
            if (r7 != r1) goto L54
            r6.next()
            r0 = 1
            goto Lb3
        L54:
            androidx.leanback.widget.PlaybackControlsRow$SkipPreviousAction r1 = r6.mSkipPreviousAction
            if (r7 != r1) goto L5d
            r6.previous()
            r0 = 1
            goto Lb3
        L5d:
            androidx.leanback.widget.PlaybackControlsRow$FastForwardAction r1 = r6.mFastForwardAction
            if (r7 != r1) goto L88
            T extends androidx.leanback.media.PlayerAdapter r1 = r6.mPlayerAdapter
            boolean r1 = r1.isPrepared()
            if (r1 == 0) goto L86
            int r1 = r6.mPlaybackSpeed
            int r3 = r6.getMaxForwardSpeedId()
            if (r1 >= r3) goto L86
            boolean r1 = r6.mIsCustomizedFastForwardSupported
            if (r1 == 0) goto L7d
            r6.mIsPlaying = r2
            T extends androidx.leanback.media.PlayerAdapter r1 = r6.mPlayerAdapter
            r1.fastForward()
            goto L80
        L7d:
            r6.fakePause()
        L80:
            r6.incrementFastForwardPlaybackSpeed()
            r6.onUpdatePlaybackStatusAfterUserAction()
        L86:
            r0 = 1
            goto Lb3
        L88:
            androidx.leanback.widget.PlaybackControlsRow$RewindAction r1 = r6.mRewindAction
            if (r7 != r1) goto Lb3
            T extends androidx.leanback.media.PlayerAdapter r1 = r6.mPlayerAdapter
            boolean r1 = r1.isPrepared()
            if (r1 == 0) goto Lb2
            int r1 = r6.mPlaybackSpeed
            int r3 = r6.getMaxRewindSpeedId()
            int r3 = -r3
            if (r1 <= r3) goto Lb2
            boolean r1 = r6.mIsCustomizedFastForwardSupported
            if (r1 == 0) goto La9
            r6.mIsPlaying = r2
            T extends androidx.leanback.media.PlayerAdapter r1 = r6.mPlayerAdapter
            r1.rewind()
            goto Lac
        La9:
            r6.fakePause()
        Lac:
            r6.decrementRewindPlaybackSpeed()
            r6.onUpdatePlaybackStatusAfterUserAction()
        Lb2:
            r0 = 1
        Lb3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.media.PlaybackBannerControlGlue.dispatchAction(androidx.leanback.widget.Action, android.view.KeyEvent):boolean");
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onPlayStateChanged() {
        onUpdatePlaybackState();
        super.onPlayStateChanged();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onPlayCompleted() {
        super.onPlayCompleted();
        this.mIsPlaying = false;
        this.mPlaybackSpeed = 0;
        this.mStartPosition = getCurrentPosition();
        this.mStartTime = System.currentTimeMillis();
        onUpdatePlaybackState();
    }

    void onUpdatePlaybackState() {
        updatePlaybackState(this.mIsPlaying);
    }

    private void updatePlaybackState(boolean isPlaying) {
        if (this.mControlsRow == null) {
            return;
        }
        int index = 0;
        if (!isPlaying) {
            onUpdateProgress();
            this.mPlayerAdapter.setProgressUpdatingEnabled(false);
        } else {
            this.mPlayerAdapter.setProgressUpdatingEnabled(true);
        }
        if (this.mFadeWhenPlaying && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(isPlaying);
        }
        ArrayObjectAdapter primaryActionsAdapter = (ArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        if (this.mPlayPauseAction != null) {
            if (isPlaying) {
                index = 1;
            }
            if (this.mPlayPauseAction.getIndex() != index) {
                this.mPlayPauseAction.setIndex(index);
                notifyItemChanged(primaryActionsAdapter, this.mPlayPauseAction);
            }
        }
        if (this.mFastForwardAction != null) {
            int index2 = 0;
            int i = this.mPlaybackSpeed;
            if (i >= 10) {
                index2 = (i - 10) + 1;
            }
            if (this.mFastForwardAction.getIndex() != index2) {
                this.mFastForwardAction.setIndex(index2);
                notifyItemChanged(primaryActionsAdapter, this.mFastForwardAction);
            }
        }
        if (this.mRewindAction != null) {
            int index3 = 0;
            int i2 = this.mPlaybackSpeed;
            if (i2 <= -10) {
                index3 = ((-i2) - 10) + 1;
            }
            if (this.mRewindAction.getIndex() != index3) {
                this.mRewindAction.setIndex(index3);
                notifyItemChanged(primaryActionsAdapter, this.mRewindAction);
            }
        }
    }

    public int[] getFastForwardSpeeds() {
        return this.mFastForwardSpeeds;
    }

    public int[] getRewindSpeeds() {
        return this.mRewindSpeeds;
    }

    private int getMaxForwardSpeedId() {
        return (this.mFastForwardSpeeds.length - 1) + 10;
    }

    private int getMaxRewindSpeedId() {
        return (this.mRewindSpeeds.length - 1) + 10;
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    public long getCurrentPosition() {
        int index;
        int i = this.mPlaybackSpeed;
        if (i == 0 || i == 1) {
            return this.mPlayerAdapter.getCurrentPosition();
        }
        if (i >= 10) {
            if (this.mIsCustomizedFastForwardSupported) {
                return this.mPlayerAdapter.getCurrentPosition();
            }
            int index2 = i - 10;
            index = getFastForwardSpeeds()[index2];
        } else if (i <= -10) {
            if (!this.mIsCustomizedRewindSupported) {
                int index3 = (-i) - 10;
                index = -getRewindSpeeds()[index3];
            } else {
                return this.mPlayerAdapter.getCurrentPosition();
            }
        } else {
            return -1L;
        }
        long position = this.mStartPosition + ((System.currentTimeMillis() - this.mStartTime) * ((long) index));
        if (position > getDuration()) {
            this.mPlaybackSpeed = 0;
            long position2 = getDuration();
            this.mPlayerAdapter.seekTo(position2);
            this.mStartPosition = 0L;
            pause();
            return position2;
        }
        if (position < 0) {
            this.mPlaybackSpeed = 0;
            this.mPlayerAdapter.seekTo(0L);
            this.mStartPosition = 0L;
            pause();
            return 0L;
        }
        return position;
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void play() {
        if (!this.mPlayerAdapter.isPrepared()) {
            return;
        }
        if (this.mPlaybackSpeed == 0 && this.mPlayerAdapter.getCurrentPosition() >= this.mPlayerAdapter.getDuration()) {
            this.mStartPosition = 0L;
        } else {
            this.mStartPosition = getCurrentPosition();
        }
        this.mStartTime = System.currentTimeMillis();
        this.mIsPlaying = true;
        this.mPlaybackSpeed = 1;
        this.mPlayerAdapter.seekTo(this.mStartPosition);
        super.play();
        onUpdatePlaybackState();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void pause() {
        this.mIsPlaying = false;
        this.mPlaybackSpeed = 0;
        this.mStartPosition = getCurrentPosition();
        this.mStartTime = System.currentTimeMillis();
        super.pause();
        onUpdatePlaybackState();
    }

    private void fakePause() {
        this.mIsPlaying = true;
        this.mStartPosition = getCurrentPosition();
        this.mStartTime = System.currentTimeMillis();
        super.pause();
        onUpdatePlaybackState();
    }
}
