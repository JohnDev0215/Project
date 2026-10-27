package com.amazon.avod.playback.session.workflow.tasks;

import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.RestartBeginEvent;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RestartPlaybackSessionTask extends BasicTeardownTask {
    private final PlaybackRestartEvent mCause;
    private final VideoSpecification mNewVideoSpecification;

    @Override // com.amazon.avod.playback.session.workflow.tasks.BasicTeardownTask
    protected void executePostContentSessionTerminationTasks() throws MediaException {
    }

    public RestartPlaybackSessionTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
        super(eventBus, exceptionCallback, playbackSessionResources, playbackSessionContext, false, null);
        this.mNewVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "newVideoSpecification");
        this.mCause = (PlaybackRestartEvent) Preconditions.checkNotNull(playbackRestartEvent, "cause");
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.BasicTeardownTask
    protected void executePreContentSessionTerminationTasks() throws MediaException {
        ContentSession contentSession = (ContentSession) Preconditions.checkNotNull(this.mPlaybackSessionContext.getContentSession(), "contentSession");
        VideoSpecification videoSpecification = (VideoSpecification) Preconditions.checkNotNull(this.mPlaybackSessionContext.getVideoSpec(), "videoSpecification");
        StreamSelections streamSelections = contentSession.getContext().getStreamSelections();
        StreamIndex audioStream = streamSelections.getAudioStream();
        this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(new RestartBeginEvent(videoSpecification, this.mNewVideoSpecification, (String) MoreObjects.firstNonNull(audioStream.getLanguage(), EnvironmentCompat.MEDIA_UNKNOWN), audioStream.getFourCC(), streamSelections.getStartAudioQualityLevel().getBitrate(), streamSelections.getSelectionParams(), this.mCause));
    }
}
