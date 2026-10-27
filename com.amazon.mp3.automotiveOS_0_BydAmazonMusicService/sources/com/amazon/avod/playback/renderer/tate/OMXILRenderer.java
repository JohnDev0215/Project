package com.amazon.avod.playback.renderer.tate;

import android.view.View;
import android.view.ViewGroup;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.AudioTrackAdapter;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.media.playback.util.VideoRegionBuilder;
import com.amazon.avod.media.playback.util.VideoRegionRules;
import com.amazon.avod.media.playback.util.VideoTrackAdapter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.VideoRegionInterpolator;
import com.amazon.avod.playback.renderer.shared.NativeCodecTypes;
import com.amazon.avod.playback.renderer.shared.NativeRendererBase;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public final class OMXILRenderer extends NativeRendererBase {
    private static final AtomicBoolean INVALIDATE_OMXIL_STATE = new AtomicBoolean(false);
    private int mBehaviorFlags;
    private boolean mIsConfiguredForEncryptedContent;
    private final MediaDefaultConfiguration mMediaDefaultConfiguration;
    private final OMXILRendererJni mOMXILRendererJni;
    private final OMXILRendererConfig mRendererConfig;
    private final boolean mSendClearContentToSecurePath;
    private final boolean mSendClearIntersititalsToSecurePath;
    private double mVideoAspectRatio;

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setHdrStatus(boolean z) {
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setPlaybackSpeed(float f) {
    }

    public static void invalidateOMXILState() {
        INVALIDATE_OMXIL_STATE.set(true);
    }

    public OMXILRenderer(MediaSystemSharedContext mediaSystemSharedContext) {
        this(mediaSystemSharedContext.getExecutorService(), PlaybackConfig.INSTANCE, OMXILRendererConfig.INSTANCE, new VideoRegionInterpolator(), new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration()), new OMXILRendererJni(), MediaDefaultConfiguration.getInstance());
    }

    private OMXILRenderer(ExecutorService executorService, PlaybackConfig playbackConfig, OMXILRendererConfig oMXILRendererConfig, VideoRegionInterpolator videoRegionInterpolator, ZoomCalculator zoomCalculator, OMXILRendererJni oMXILRendererJni, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration) {
        super(executorService, playbackConfig, videoRegionInterpolator, zoomCalculator, oMXILRendererJni, mediaDefaultConfiguration);
        this.mBehaviorFlags = 0;
        this.mIsConfiguredForEncryptedContent = false;
        this.mVideoAspectRatio = -1.0d;
        this.mOMXILRendererJni = oMXILRendererJni;
        this.mRendererConfig = oMXILRendererConfig;
        this.mSendClearContentToSecurePath = oMXILRendererConfig.getSendClearContentToSecurePath();
        this.mSendClearIntersititalsToSecurePath = oMXILRendererConfig.shouldSendClearIntersititalsToSecurePath();
        this.mMediaDefaultConfiguration = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int initialize(String str, String str2, boolean z) throws PlaybackException {
        if (this.mRendererConfig.isLongLivedAIVPlayEnabled()) {
            this.mBehaviorFlags |= 1;
        }
        if (INVALIDATE_OMXIL_STATE.getAndSet(false)) {
            this.mBehaviorFlags |= 2;
        }
        this.mOMXILRendererJni.initialize(this.mBehaviorFlags, new VideoTrackAdapter(str), new AudioTrackAdapter());
        return super.initialize(this.mBehaviorFlags, 0, str, str2, z);
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void configureOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        Preconditions.checkState(videoRenderingSettings.getSurfaceHandlingMode() != SurfaceHandlingMode.AUDIO_ONLY, "Audio only surface handling mode not supported by the OMX renderer!");
        super.configureOutputDisplay(videoRenderingSettings);
        ViewGroup parentView = videoRenderingSettings.getParentView();
        if (parentView == null || !this.mMediaDefaultConfiguration.isDynamicVideoSurfaceResizeEnabled()) {
            return;
        }
        this.mZoomCalculator.setParentLayoutOverride(parentView);
        parentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.amazon.avod.playback.renderer.tate.OMXILRenderer.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (OMXILRenderer.this.mVideoAspectRatio == -1.0d) {
                    DLog.logf("skip changing video size as aspect ratio is not available");
                    return;
                }
                int width = view.getWidth();
                int i9 = i7 - i5;
                int height = view.getHeight();
                int i10 = i8 - i6;
                if (width == i9 && height == i10) {
                    return;
                }
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                VideoRegion videoRegionBuild = VideoRegionBuilder.fromVideoRegionRules(new VideoRegionRules().left(iArr[0]).top(iArr[1])).build((float) OMXILRenderer.this.mVideoAspectRatio, view.getMeasuredWidth() + iArr[0], view.getMeasuredHeight() + iArr[1]);
                DLog.logf("Video surface parent layout size changed to (w=%s h=%s), Resizing video surface to (w=%s h=%s).", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(videoRegionBuild.getWidth()), Integer.valueOf(videoRegionBuild.getHeight()));
                OMXILRenderer.this.setVideoRegion(videoRegionBuild);
            }
        });
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int configureCodecs(@Nonnull VideoConfig videoConfig, @Nonnull AudioConfig audioConfig, boolean z, @Nonnull TimeSpan timeSpan) throws PlaybackException {
        this.mContentBufferLookAheadSize = (TimeSpan) Preconditions.checkNotNull(timeSpan, "contentBufferLookAheadSize");
        this.mIsConfiguredForEncryptedContent = z;
        boolean z2 = this.mSendClearContentToSecurePath || z;
        DLog.logf("Configuring renderer with VideoConfig=%s, AudioConfig=%s, isEncrypted=%s, sendToSecurePath=%s", videoConfig, audioConfig, Boolean.valueOf(z), Boolean.valueOf(z2));
        try {
            super.configureStreamHandlers(videoConfig, audioConfig);
            this.mVideoAspectRatio = videoConfig.getDisplayAspectRatio();
            if (this.mMediaDefaultConfiguration.isMemStoreBasedRendererZeroMemCopyAllowed() && this.mMediaDefaultConfiguration.isOmxIlZeroMemCopyEnabled()) {
                DLog.logf("OMX IL renderer is using zero memcopy media source path");
                int i = this.mBehaviorFlags | 4;
                this.mBehaviorFlags = i;
                this.mOMXILRendererJni.setBehaviorFlags(i);
            }
            return this.mOMXILRendererJni.configureRenderer(NativeCodecTypes.valueOf(videoConfig.getFourCC()), NativeCodecTypes.valueOf(audioConfig.getFourCC()), audioConfig.getSampleRate(), audioConfig.getChannels(), audioConfig.getBitRate(), z2, this.mBehaviorFlags);
        } catch (OMXILPlaybackException e) {
            if (e.getNativeErrorCode() == OMXILPlaybackError.OMX_COMPONENT_NOT_FOUND.getID()) {
                DLog.errorf("OMX_COMPONENT_NOT_FOUND error, probably caused by an attempt to play Dolby 5.1 audio on a platform that does not support it");
            }
            throw e;
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void submitSample(SampleHolder sampleHolder) throws PlaybackException {
        if (sampleHolder.getEncryptionInfo() == null && (this.mSendClearContentToSecurePath || (this.mIsConfiguredForEncryptedContent && this.mSendClearIntersititalsToSecurePath))) {
            sampleHolder.setEncryptionInfo(SampleEncryptionInfo.generateClearTextEncryptionInfo(sampleHolder.getSize()));
        }
        super.submitSample(sampleHolder);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void checkRendererError(boolean z) throws MediaException {
        int iCheckRendererError;
        if (z && (iCheckRendererError = this.mPlaybackStatus.checkRendererError()) != 0) {
            throw new OMXILPlaybackException("Error returned on NativePlaybackStatus", iCheckRendererError);
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void dispose(boolean z) {
        super.dispose(z);
    }
}
