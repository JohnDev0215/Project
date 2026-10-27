package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class PreviousCommandIssuedEvent extends SuperbowlEvent {
    private PreviousCommandIssuedEvent(Builder builder) {
        super(builder);
    }

    public String toString() {
        return "PreviousCommandIssuedEvent{namespace='" + getNamespace() + "', name='" + getName() + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<PreviousCommandIssuedEvent> {
        public Builder() {
            super("PlaybackController", "PreviousCommandIssued");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public PreviousCommandIssuedEvent build() {
            return new PreviousCommandIssuedEvent(this);
        }
    }
}
