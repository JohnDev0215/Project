package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackStutterFinishedEvent extends SuperbowlEvent {
    private final long offsetInMilliseconds;
    private final long stutterDurationInMilliseconds;
    private final String token;

    private AudioPlaybackStutterFinishedEvent(Builder builder) {
        super(builder);
        this.token = builder.token;
        this.offsetInMilliseconds = builder.offsetInMilliseconds;
        this.stutterDurationInMilliseconds = builder.stutterDurationInMilliseconds;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.token);
        jSONObject.put("offsetInMilliseconds", this.offsetInMilliseconds);
        jSONObject.put("stutterDurationInMilliseconds", this.stutterDurationInMilliseconds);
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioPlaybackStutterFinishedEvent.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        AudioPlaybackStutterFinishedEvent audioPlaybackStutterFinishedEvent = (AudioPlaybackStutterFinishedEvent) obj;
        if (this.offsetInMilliseconds == audioPlaybackStutterFinishedEvent.offsetInMilliseconds && this.stutterDurationInMilliseconds == audioPlaybackStutterFinishedEvent.stutterDurationInMilliseconds) {
            return this.token.equals(audioPlaybackStutterFinishedEvent.token);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        long j = this.offsetInMilliseconds;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.stutterDurationInMilliseconds;
        return ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.token.hashCode();
    }

    public String toString() {
        return "AudioPlaybackStutterFinishedEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', offsetInMilliseconds=" + this.offsetInMilliseconds + ", stutterDurationInMilliseconds=" + this.stutterDurationInMilliseconds + ", token='" + this.token + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<AudioPlaybackStutterFinishedEvent> {
        long offsetInMilliseconds;
        long stutterDurationInMilliseconds;
        String token;

        public Builder() {
            super("AudioPlayer", "PlaybackStutterFinished");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder offsetInMilliseconds(long j) {
            this.offsetInMilliseconds = j;
            return this;
        }

        public Builder stutterDurationInMilliseconds(long j) {
            this.stutterDurationInMilliseconds = j;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public AudioPlaybackStutterFinishedEvent build() {
            Preconditions.notNull(this.token, "token == null");
            Preconditions.min(this.offsetInMilliseconds, 0L, "offsetInMilliseconds cannot be negative");
            Preconditions.min(this.stutterDurationInMilliseconds, 0L, "stutterDurationInMilliseconds cannot be negative");
            return new AudioPlaybackStutterFinishedEvent(this);
        }
    }
}
