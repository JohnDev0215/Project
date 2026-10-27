package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackBaseEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackStutterStartedEvent extends AudioPlaybackBaseEvent {
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

    private AudioPlaybackStutterStartedEvent(Builder builder) {
        super(builder);
    }

    public static final class Builder extends AudioPlaybackBaseEvent.Builder<AudioPlaybackStutterStartedEvent> {
        public Builder() {
            super("PlaybackStutterStarted");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public AudioPlaybackStutterStartedEvent build() {
            return new AudioPlaybackStutterStartedEvent(this);
        }
    }
}
