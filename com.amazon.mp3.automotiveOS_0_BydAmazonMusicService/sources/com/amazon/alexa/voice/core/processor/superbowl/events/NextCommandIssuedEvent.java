package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class NextCommandIssuedEvent extends SuperbowlEvent {
    private NextCommandIssuedEvent(Builder builder) {
        super(builder);
    }

    public String toString() {
        return "NextCommandIssuedEvent{namespace='" + getNamespace() + "', name='" + getName() + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<NextCommandIssuedEvent> {
        public Builder() {
            super("PlaybackController", "NextCommandIssued");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public NextCommandIssuedEvent build() {
            return new NextCommandIssuedEvent(this);
        }
    }
}
