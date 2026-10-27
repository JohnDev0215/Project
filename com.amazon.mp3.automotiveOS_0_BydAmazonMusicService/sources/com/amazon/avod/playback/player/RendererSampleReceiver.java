package com.amazon.avod.playback.player;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.exceptions.StreamSeekOverException;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.AVSyncDebugReporter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleReadResult;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RendererSampleReceiver {
    private final AVSyncDebugReporter mAVSyncDebugReporter;
    private TimeSpan mAudioTrackRecreationTime;
    private final VideoRenderer mRenderer;
    private final SampleHolder mSampleHolder;
    private PlaybackSessionProtocol mSessionProtocol;
    private ContentSessionState mSessionState;
    private final boolean mShouldReportAVSyncInfo;
    private final boolean mShouldRestartOnCorruptFragment;
    private TimeSpan mVideoSurfaceRecreationTime;

    public enum ForwardSampleReturnCode {
        SUCCESS,
        CONTENT_BUFFERING,
        NO_SAMPLES_AVAILABLE,
        RENDERER_BUFFER_FULL,
        STREAM_SEEK_OVER_EXCEPTION,
        AUDIO_TRACK_IS_DEAD_ERROR,
        VIDEO_SURFACE_RECREATED
    }

    public RendererSampleReceiver(@Nonnull VideoRenderer videoRenderer, @Nonnull PlaybackConfig playbackConfig, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        this(videoRenderer, aVSyncDebugReporter, playbackConfig.isAVSyncStatsReportingEnabled(), playbackConfig.shouldRestartOnCorruptFragment());
    }

    RendererSampleReceiver(@Nonnull VideoRenderer videoRenderer, @Nonnull AVSyncDebugReporter aVSyncDebugReporter, boolean z, boolean z2) {
        TimeSpan timeSpan = TimeSpan.ZERO;
        this.mAudioTrackRecreationTime = timeSpan;
        this.mVideoSurfaceRecreationTime = timeSpan;
        this.mSampleHolder = new SampleHolder();
        this.mRenderer = (VideoRenderer) Preconditions.checkNotNull(videoRenderer, "renderer");
        this.mAVSyncDebugReporter = (AVSyncDebugReporter) Preconditions.checkNotNull(aVSyncDebugReporter);
        this.mShouldReportAVSyncInfo = z;
        this.mShouldRestartOnCorruptFragment = z2;
    }

    public void initialize(@Nonnull PlaybackSessionProtocol playbackSessionProtocol, @Nonnull ContentSessionState contentSessionState) {
        this.mSessionProtocol = (PlaybackSessionProtocol) Preconditions.checkNotNull(playbackSessionProtocol, "protocol");
        this.mSessionState = (ContentSessionState) Preconditions.checkNotNull(contentSessionState, "state");
    }

    public ForwardSampleReturnCode submitSample(@Nonnull PlaybackState playbackState, @Nullable StreamSeekOverException streamSeekOverException, @Nullable StreamSeekOverException streamSeekOverException2) throws MediaException {
        SampleType requiredNextSampleType;
        Preconditions.checkNotNull(playbackState, "state");
        if (streamSeekOverException != null) {
            requiredNextSampleType = SampleType.VIDEO_SAMPLE;
        } else if (streamSeekOverException2 != null) {
            requiredNextSampleType = SampleType.AUDIO_SAMPLE;
        } else {
            requiredNextSampleType = this.mRenderer.getRequiredNextSampleType();
        }
        boolean z = requiredNextSampleType == null || !this.mRenderer.hasSpaceForMoreSamples(requiredNextSampleType);
        try {
            this.mRenderer.checkRendererError(z);
            if (z) {
                return ForwardSampleReturnCode.RENDERER_BUFFER_FULL;
            }
            return readNextSample(requiredNextSampleType);
        } catch (MediaException e) {
            MediaInternalErrorCode errorCode = e.getErrorCode();
            PlaybackException.PlaybackError playbackError = PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_IS_DEAD;
            if (errorCode == playbackError && (e instanceof PlaybackException)) {
                DLog.warnf(playbackError.name() + " detected.");
                recreateAudioTrack();
                this.mAudioTrackRecreationTime = TimeSpan.fromMicroseconds(((PlaybackException) e).getMediaTimeUs());
                return ForwardSampleReturnCode.AUDIO_TRACK_IS_DEAD_ERROR;
            }
            MediaInternalErrorCode errorCode2 = e.getErrorCode();
            PlaybackException.PlaybackError playbackError2 = PlaybackException.PlaybackError.RENDERER_VIDEO_SURFACE_RECREATED;
            if (errorCode2 == playbackError2 && (e instanceof PlaybackException)) {
                DLog.warnf(playbackError2.name() + " detected.");
                recreateVideoTrack();
                this.mVideoSurfaceRecreationTime = TimeSpan.fromMicroseconds(((PlaybackException) e).getMediaTimeUs());
                return ForwardSampleReturnCode.VIDEO_SURFACE_RECREATED;
            }
            throw e;
        }
    }

    private void recreateAudioTrack() throws PlaybackException {
        this.mRenderer.pause();
        this.mRenderer.recreateAudioTrack();
    }

    private void recreateVideoTrack() throws PlaybackException {
        this.mRenderer.pause();
        this.mRenderer.recreateVideoTrack();
    }

    @Nonnull
    public TimeSpan getAudioTrackRecreationTime() {
        return this.mAudioTrackRecreationTime;
    }

    @Nonnull
    public TimeSpan getVideoSurfaceRecreationTime() {
        return this.mVideoSurfaceRecreationTime;
    }

    private ForwardSampleReturnCode readNextSample(SampleType sampleType) throws MediaException {
        try {
            SampleReadResult nextSample = this.mSessionProtocol.getNextSample(sampleType, this.mSampleHolder);
            if (nextSample == SampleReadResult.NO_MORE_DATA) {
                return ForwardSampleReturnCode.NO_SAMPLES_AVAILABLE;
            }
            if (nextSample == SampleReadResult.WAITING_FOR_IO) {
                return ForwardSampleReturnCode.CONTENT_BUFFERING;
            }
            try {
                if (this.mShouldReportAVSyncInfo) {
                    if (sampleType == SampleType.AUDIO_SAMPLE) {
                        this.mAVSyncDebugReporter.updateFirstAudioSamplePTS(this.mSampleHolder.getPresentationTime());
                    } else if (sampleType == SampleType.VIDEO_SAMPLE) {
                        this.mAVSyncDebugReporter.updateFirstVideoSamplePTS(this.mSampleHolder.getPresentationTime());
                    }
                }
                this.mRenderer.submitSample(this.mSampleHolder);
                QATriggerPlaybackFatal.getInstance().triggerFatalIfEnabled();
                if (this.mSampleHolder.getIsLastinFragment()) {
                    this.mSessionProtocol.releaseFragment(this.mSampleHolder.getType(), this.mSampleHolder);
                }
                this.mSampleHolder.releaseSample();
                return ForwardSampleReturnCode.SUCCESS;
            } catch (PlaybackException e) {
                PlaybackException.PlaybackError playbackError = (PlaybackException.PlaybackError) CastUtils.castTo(e.getErrorCode(), PlaybackException.PlaybackError.class);
                if (playbackError == null) {
                    playbackError = PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR;
                }
                throw new PlaybackException(playbackError, String.format(Locale.US, "%s: FragmentUrl: %s", e.getMessage(), this.mSampleHolder.getFragmentUrl()), e, e.getMediaTimeUs());
            }
        } catch (StreamSeekOverException e2) {
            throw e2;
        } catch (MediaException e3) {
            DLog.exceptionf(e3, "content exception: ", new Object[0]);
            if (e3 instanceof ContentException) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentException$ContentError[((ContentException) e3).getErrorCode().ordinal()];
                if (i == 1) {
                    DLog.exceptionf(e3, "Re-throwing PeriodView crash: ", new Object[0]);
                    throw e3;
                }
                if ((i == 2 || i == 3) && this.mShouldRestartOnCorruptFragment && this.mSessionState.getVideoSpecification().isLiveStream()) {
                    DLog.exceptionf(e3, "Convert content exception to playback exception: ", new Object[0]);
                    throw new PlaybackException(PlaybackException.PlaybackError.CORRUPT_FRAGMENT_ERROR, e3.getMessage());
                }
            }
            return ForwardSampleReturnCode.CONTENT_BUFFERING;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.player.RendererSampleReceiver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentException$ContentError;

        static {
            int[] iArr = new int[ContentException.ContentError.values().length];
            $SwitchMap$com$amazon$avod$content$ContentException$ContentError = iArr;
            try {
                iArr[ContentException.ContentError.VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentException$ContentError[ContentException.ContentError.UNKNOWN_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentException$ContentError[ContentException.ContentError.SAMPLE_TOO_LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void flush() {
        this.mAVSyncDebugReporter.flush();
    }
}
