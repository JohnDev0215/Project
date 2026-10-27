package com.amazon.avod.playback.player.actions;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.playback.VideoSpecification;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LoadAction extends Action {
    private final ContentSession mContentSession;
    private final VideoSpecification mVideoSpec;

    public LoadAction(@Nonnull VideoSpecification videoSpecification, @Nonnull ContentSession contentSession) {
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification);
        this.mContentSession = (ContentSession) Preconditions.checkNotNull(contentSession);
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.InitialLoad;
    }

    @Nonnull
    public VideoSpecification getSpec() {
        return this.mVideoSpec;
    }

    @Nonnull
    public ContentSession getContentSession() {
        return this.mContentSession;
    }
}
