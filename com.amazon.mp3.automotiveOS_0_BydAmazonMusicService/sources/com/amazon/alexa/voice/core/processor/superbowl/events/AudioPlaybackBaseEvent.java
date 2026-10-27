package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioPlaybackBaseEvent extends SuperbowlEvent {
    private final long offsetInMilliseconds;
    private final String token;

    protected AudioPlaybackBaseEvent(Builder<? extends AudioPlaybackBaseEvent> builder) {
        super(builder);
        Preconditions.notNull(builder.token, "token == null");
        Preconditions.min(builder.offsetInMilliseconds, 0L, "offsetInMilliseconds cannot be negative");
        this.token = builder.token;
        this.offsetInMilliseconds = builder.offsetInMilliseconds;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected final JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.token);
        jSONObject.put("offsetInMilliseconds", this.offsetInMilliseconds);
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        AudioPlaybackBaseEvent audioPlaybackBaseEvent = (AudioPlaybackBaseEvent) obj;
        if (this.offsetInMilliseconds != audioPlaybackBaseEvent.offsetInMilliseconds) {
            return false;
        }
        return this.token.equals(audioPlaybackBaseEvent.token);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        long j = this.offsetInMilliseconds;
        return ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.token.hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + "{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', offsetInMilliseconds=" + this.offsetInMilliseconds + ", token='" + this.token + "'}";
    }

    public static abstract class Builder<T extends AudioPlaybackBaseEvent> extends SuperbowlEvent.Builder<T> {
        long offsetInMilliseconds;
        String token;

        protected Builder(String str) {
            super("AudioPlayer", str);
        }

        public final Builder<T> token(String str) {
            this.token = str;
            return this;
        }

        public final Builder<T> offsetInMilliseconds(long j) {
            this.offsetInMilliseconds = j;
            return this;
        }
    }
}
