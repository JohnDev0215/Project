package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechStartedEvent extends SuperbowlEvent {
    private final String token;

    private SpeechStartedEvent(Builder builder) {
        super(builder);
        this.token = builder.token;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.token);
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SpeechStartedEvent.class == obj.getClass() && super.equals(obj)) {
            return this.token.equals(((SpeechStartedEvent) obj).token);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        return (super.hashCode() * 31) + this.token.hashCode();
    }

    public String toString() {
        return "SpeechStartedEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', token='" + this.token + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<SpeechStartedEvent> {
        String token;

        public Builder() {
            super("SpeechSynthesizer", "SpeechStarted");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public SpeechStartedEvent build() {
            Preconditions.notNull(this.token, "token == null");
            return new SpeechStartedEvent(this);
        }
    }
}
