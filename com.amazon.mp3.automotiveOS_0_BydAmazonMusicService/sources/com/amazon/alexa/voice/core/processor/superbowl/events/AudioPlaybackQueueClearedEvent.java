package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackQueueClearedEvent extends SuperbowlEvent {
    private AudioPlaybackQueueClearedEvent(Builder builder) {
        super(builder);
    }

    public String toString() {
        return "AudioPlaybackQueueClearedEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<AudioPlaybackQueueClearedEvent> {
        public Builder() {
            super("AudioPlayer", "PlaybackQueueCleared");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public AudioPlaybackQueueClearedEvent build() {
            return new AudioPlaybackQueueClearedEvent(this);
        }
    }
}
