package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class Triggers {
    static SimpleTrigger<Type> TEARDOWN_COMPLETE_TRIGGER = new SimpleTrigger<>(Type.TEARDOWN_COMPLETE);

    public enum Type {
        PREPARE,
        ACTIVATE,
        RESTART,
        TEARDOWN,
        TEARDOWN_COMPLETE
    }

    Triggers() {
    }

    static final class Prepare implements Trigger<Type> {

        @Nullable
        private final AudioVideoUrls mAudioVideoUrls;

        @Nonnull
        private final VideoSpecification mSpec;

        @Nullable
        private final File mStoragePath;

        @Nullable
        private final String mUrlSetId;

        Prepare(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nullable String str, @Nullable AudioVideoUrls audioVideoUrls) {
            this.mSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "spec");
            this.mStoragePath = file;
            this.mUrlSetId = str;
            this.mAudioVideoUrls = audioVideoUrls;
        }

        @Nonnull
        VideoSpecification getSpec() {
            return this.mSpec;
        }

        @Nullable
        public File getStoragePath() {
            return this.mStoragePath;
        }

        @Nullable
        public String getUrlSetId() {
            return this.mUrlSetId;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.PREPARE;
        }

        @Nullable
        public AudioVideoUrls getAudioVideoUrls() {
            return this.mAudioVideoUrls;
        }
    }

    static final class Activate implements Trigger<Type> {

        @Nonnull
        private final VideoRenderingSettings mSettings;

        Activate(@Nonnull VideoRenderingSettings videoRenderingSettings) {
            this.mSettings = (VideoRenderingSettings) Preconditions.checkNotNull(videoRenderingSettings, "settings");
        }

        @Nonnull
        VideoRenderingSettings getSettings() {
            return this.mSettings;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.ACTIVATE;
        }
    }

    static final class Restart implements Trigger<Type> {

        @Nonnull
        final PlaybackRestartEvent mCause;

        @Nonnull
        final VideoSpecification newSpec;

        Restart(@Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
            this.newSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "inNewSpec");
            this.mCause = (PlaybackRestartEvent) Preconditions.checkNotNull(playbackRestartEvent, "playbackRestartEvent");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.RESTART;
        }
    }

    static final class TearDown implements Trigger<Type> {
        private final boolean mShouldRetainReusableComponents;

        TearDown(boolean z) {
            this.mShouldRetainReusableComponents = z;
        }

        public boolean shouldRetainReusableComponents() {
            return this.mShouldRetainReusableComponents;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.TEARDOWN;
        }
    }
}
