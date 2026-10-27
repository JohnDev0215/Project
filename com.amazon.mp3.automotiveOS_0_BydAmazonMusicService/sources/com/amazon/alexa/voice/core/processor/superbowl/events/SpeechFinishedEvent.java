package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechFinishedEvent extends SuperbowlEvent {
    private final String token;

    private SpeechFinishedEvent(Builder builder) {
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
        if (obj != null && SpeechFinishedEvent.class == obj.getClass() && super.equals(obj)) {
            return this.token.equals(((SpeechFinishedEvent) obj).token);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        return (super.hashCode() * 31) + this.token.hashCode();
    }

    public String toString() {
        return "SpeechFinishedEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', token='" + this.token + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<SpeechFinishedEvent> {
        String token;

        public Builder() {
            super("SpeechSynthesizer", "SpeechFinished");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public SpeechFinishedEvent build() {
            Preconditions.notNull(this.token, "token == null");
            return new SpeechFinishedEvent(this);
        }
    }
}
