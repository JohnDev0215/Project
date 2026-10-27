package com.amazon.video.sdk.avod.playbackclient.debug;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playbackclient.debug.DebugDialogBuilder;
import com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener;
import com.amazon.video.player.R$dimen;
import com.amazon.video.player.R$drawable;
import com.amazon.video.player.R$id;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatch;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatchFactory;
import com.amazon.video.sdk.avod.playbackclient.control.states.PlaybackControllerContext;
import com.amazon.video.sdk.player.PlayerConfig;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class DebugDialogFeature {
    private Context mContext;
    private View mDebugDialogButton;
    private DiagnosticsController mDiagnosticsController;
    private Dialog mDialog;
    private DebugDialogBuilder mDialogBuilder;
    private final DialogBuilderFactory mDialogBuilderFactory;
    private final boolean mEnableDebugOptions;
    private final Handler mHandler;
    private boolean mIsActive;
    private PlaybackEventDispatch mPlaybackEventDispatch;
    private PlaybackExperienceController mPlaybackExperienceController;
    private final PlaybackStateEventListener mPlaybackStartListener;

    public DebugDialogFeature(@Nonnull PlayerConfig playerConfig) {
        this(playerConfig, new Handler(Looper.getMainLooper()), new DialogBuilderFactory());
    }

    DebugDialogFeature(@Nonnull PlayerConfig playerConfig, @Nonnull Handler handler, @Nonnull DialogBuilderFactory dialogBuilderFactory) {
        this.mIsActive = false;
        this.mDialog = null;
        this.mPlaybackStartListener = new BasePlaybackStateEventListener() { // from class: com.amazon.video.sdk.avod.playbackclient.debug.DebugDialogFeature.1
            @Override // com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener, com.amazon.avod.playback.PlaybackStateEventListener
            public void onStart(PlaybackEventContext playbackEventContext) {
                DebugDialogFeature.this.onPlaybackStarting();
            }
        };
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "handler");
        this.mDialogBuilderFactory = (DialogBuilderFactory) Preconditions.checkNotNull(dialogBuilderFactory, "dialogBuilderFactory");
        this.mEnableDebugOptions = playerConfig.getAdditionalConfigs().getEnableDebugOptions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlaybackStarting() {
        this.mDialogBuilder = this.mDialogBuilderFactory.create().setContext(this.mContext).setDiagnosticsController(this.mDiagnosticsController).setPlaybackExperienceController(this.mPlaybackExperienceController);
        this.mIsActive = true;
        final Dialog orCreateDialog = getOrCreateDialog();
        View view = this.mDebugDialogButton;
        if (view != null) {
            view.setVisibility(0);
            this.mDebugDialogButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.video.sdk.avod.playbackclient.debug.DebugDialogFeature.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    orCreateDialog.show();
                }
            });
        }
    }

    @Nonnull
    private Dialog getOrCreateDialog() {
        Preconditions.checkState(this.mIsActive);
        if (this.mDialog == null) {
            this.mDialog = this.mDialogBuilder.create();
        }
        return this.mDialog;
    }

    private void destroyDialog() {
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            return;
        }
        dialog.dismiss();
        this.mDialog = null;
        this.mDebugDialogButton = null;
    }

    public void onPrepared(@Nonnull VideoPresentation videoPresentation, @Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoPresentation, "videoPresentation");
        Preconditions.checkNotNull(videoRenderingSettings, "videoRenderingSettings");
        final ViewGroup parentView = videoRenderingSettings.getParentView();
        if (parentView == null || parentView.getContext() == null) {
            return;
        }
        this.mContext = parentView.getContext();
        DiagnosticsController diagnosticsController = videoPresentation.getDiagnosticsController();
        this.mDiagnosticsController = diagnosticsController;
        if (diagnosticsController != null && diagnosticsController.isEnabled() && this.mEnableDebugOptions) {
            VideoPlayer player = videoPresentation.getPlayer();
            this.mPlaybackExperienceController = player.getPlaybackExperienceController();
            PlaybackEventDispatch playbackEventDispatchCreateAndAttachDispatch = new PlaybackEventDispatchFactory().createAndAttachDispatch(player, new PlaybackControllerContext("Main"));
            this.mPlaybackEventDispatch = playbackEventDispatchCreateAndAttachDispatch;
            playbackEventDispatchCreateAndAttachDispatch.initialize();
            this.mPlaybackEventDispatch.addPlaybackStateEventListener(this.mPlaybackStartListener);
            View viewFindViewById = parentView.findViewById(R$id.avod_sdk_debug_button);
            this.mDebugDialogButton = viewFindViewById;
            if (viewFindViewById == null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                int dimensionPixelSize = parentView.getResources().getDimensionPixelSize(R$dimen.avod_debug_button_margins);
                layoutParams.rightMargin = dimensionPixelSize;
                layoutParams.topMargin = dimensionPixelSize;
                layoutParams.addRule(11);
                layoutParams.addRule(10);
                int dimensionPixelSize2 = parentView.getResources().getDimensionPixelSize(R$dimen.avod_spacing_xxsmall);
                ImageButton imageButton = new ImageButton(this.mContext);
                this.mDebugDialogButton = imageButton;
                imageButton.setLayoutParams(layoutParams);
                this.mDebugDialogButton.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
                this.mDebugDialogButton.setBackgroundResource(R$drawable.diagnostics_default);
                this.mHandler.post(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.debug.DebugDialogFeature$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onPrepared$0(parentView);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepared$0(ViewGroup viewGroup) {
        viewGroup.addView(this.mDebugDialogButton);
    }

    public void onTerminate(boolean z) {
        destroyDialog();
        if (this.mDiagnosticsController != null) {
            this.mHandler.post(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.debug.DebugDialogFeature$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTerminate$1();
                }
            });
        }
        this.mIsActive = false;
        this.mDebugDialogButton = null;
        this.mDialogBuilder = null;
        this.mContext = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onTerminate$1() {
        this.mDiagnosticsController.dispose();
    }

    static class DialogBuilderFactory {
        DialogBuilderFactory() {
        }

        @Nonnull
        public DebugDialogBuilder create() {
            return new DebugDialogBuilder();
        }
    }
}
