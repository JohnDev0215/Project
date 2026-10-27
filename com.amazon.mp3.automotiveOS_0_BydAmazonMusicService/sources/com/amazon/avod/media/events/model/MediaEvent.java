package com.amazon.avod.media.events.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface MediaEvent {

    public enum MediaEventType {
        Device,
        Network,
        Display,
        Audio,
        Weblab,
        PlayerHeuristic,
        Interface,
        Interaction,
        Remote,
        Error,
        Playback,
        Content,
        Download,
        Acquisition,
        TimedText,
        AdIdentifier,
        Diagnostic,
        Track,
        Failover,
        Bitstream
    }

    @JsonIgnore
    @Nonnull
    MediaEventType getMediaEventType();
}
