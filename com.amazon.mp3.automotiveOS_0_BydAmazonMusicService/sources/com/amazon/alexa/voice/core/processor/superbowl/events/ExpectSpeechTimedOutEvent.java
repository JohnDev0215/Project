package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class ExpectSpeechTimedOutEvent extends SuperbowlEvent {
    private ExpectSpeechTimedOutEvent(Builder builder) {
        super(builder);
    }

    public String toString() {
        return "ExpectSpeechTimedOutEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<ExpectSpeechTimedOutEvent> {
        public Builder() {
            super("SpeechRecognizer", "ExpectSpeechTimedOut");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public ExpectSpeechTimedOutEvent build() {
            return new ExpectSpeechTimedOutEvent(this);
        }
    }
}
