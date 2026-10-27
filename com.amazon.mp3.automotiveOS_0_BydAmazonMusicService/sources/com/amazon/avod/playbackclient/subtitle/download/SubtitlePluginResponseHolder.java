package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.media.download.plugin.PluginResponseHolder;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlePluginResponseHolder extends PluginResponseHolder {
    private final SubtitlePluginLog.Builder mSubtitleLogBuilder;

    public SubtitlePluginResponseHolder() {
        this(SubtitlePluginLog.newBuilder());
    }

    SubtitlePluginResponseHolder(@Nonnull SubtitlePluginLog.Builder builder) {
        this.mSubtitleLogBuilder = (SubtitlePluginLog.Builder) Preconditions.checkNotNull(builder, "SubtitlePluginLog.Builder cannot be null");
    }

    @Nonnull
    public SubtitlePluginLog.Builder getSubtitleLogBuilder() {
        return this.mSubtitleLogBuilder;
    }
}
