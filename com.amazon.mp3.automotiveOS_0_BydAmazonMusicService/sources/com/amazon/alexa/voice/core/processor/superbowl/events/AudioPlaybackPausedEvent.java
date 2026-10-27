package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackBaseEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackPausedEvent extends AudioPlaybackBaseEvent {
    @Override // com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackBaseEvent, com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackBaseEvent, com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackBaseEvent
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    private AudioPlaybackPausedEvent(Builder builder) {
        super(builder);
    }

    public static final class Builder extends AudioPlaybackBaseEvent.Builder<AudioPlaybackPausedEvent> {
        public Builder() {
            super("PlaybackPaused");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public AudioPlaybackPausedEvent build() {
            return new AudioPlaybackPausedEvent(this);
        }
    }
}
