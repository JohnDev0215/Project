package com.amazon.mp3.playback.view;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.impl.DefaultPlaybackProvider;

/* JADX INFO: loaded from: classes3.dex */
public class CloudQueueMiniPlayerView extends MiniPlayerView {
    private static final String TAG = CloudQueueMiniPlayerView.class.getSimpleName();
    final ActionValidatedPlaybackController mPlaybackController;

    public CloudQueueMiniPlayerView(Context context) {
        super(context);
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerView, com.amazon.mp3.playback.view.PlayerView
    public void hasNextTrack(final boolean hasNext) {
        super.hasNextTrack(hasNext);
        if (this.mNextButton == null) {
            return;
        }
        if (isCloudQueueSequencer()) {
            this.mNextButton.setEnabled(true);
            if ((this.mPlaybackController.getCurrentMediaItem() instanceof AdMediaItem) || !this.mPlaybackController.canSkipNext()) {
                this.mNextButton.setImageResource(R.drawable.ic_playback_next_disabled);
                return;
            } else {
                this.mNextButton.setImageResource(R.drawable.mini_player_btn_next_tray);
                return;
            }
        }
        this.mNextButton.setEnabled(hasNext);
        this.mNextButton.setImageResource(R.drawable.mini_player_btn_next_tray);
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerView, com.amazon.mp3.playback.view.PlayerView
    public void hasPrevTrack(final boolean hasPrev) {
        super.hasPrevTrack(hasPrev);
        if (this.mPrevButton == null) {
            return;
        }
        if (isCloudQueueSequencer()) {
            this.mPrevButton.setEnabled(true);
            if (!this.mPlaybackController.canSkipPrevious()) {
                this.mPrevButton.setImageResource(R.drawable.ic_playback_previous_disabled);
                return;
            } else {
                this.mPrevButton.setImageResource(R.drawable.mini_player_btn_previous_tray);
                return;
            }
        }
        this.mPrevButton.setEnabled(hasPrev);
        this.mPrevButton.setImageResource(R.drawable.mini_player_btn_previous_tray);
    }

    private String getAsin() {
        return UpsellUtil.getAsinFromMediaItem(this.mPlaybackController.getCurrentMediaItem());
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerView
    protected void OnSwipeRightError() {
        if (isCloudQueueSequencer()) {
            UpsellUtil.raiseBlockingUpsell(getContext(), getAsin(), this.mPlaybackController.getCurrentMediaItem() instanceof AdMediaItem ? UpsellReason.AD_SKIP : UpsellReason.TRACK_PREVIOUS);
        }
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerView
    protected void OnSwipeLeftError() {
        if (isCloudQueueSequencer()) {
            UpsellUtil.raiseBlockingUpsell(getContext(), getAsin(), this.mPlaybackController.getCurrentMediaItem() instanceof AdMediaItem ? UpsellReason.AD_SKIP : UpsellReason.TRACK_NEXT);
        }
    }

    private boolean isCloudQueueSequencer() {
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        return (playbackProvider instanceof DefaultPlaybackProvider) && (((DefaultPlaybackProvider) playbackProvider).getSequencer() instanceof FreeTierSequencer);
    }
}
