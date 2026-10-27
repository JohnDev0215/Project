package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class RecognizeSpeechEvent extends SuperbowlEvent {
    private final String profile;

    private RecognizeSpeechEvent(Builder builder) {
        super(builder);
        this.profile = builder.profile;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Scopes.PROFILE, this.profile);
        jSONObject.put("format", sanitizeAudioFormat());
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RecognizeSpeechEvent.class == obj.getClass() && super.equals(obj)) {
            return this.profile.equals(((RecognizeSpeechEvent) obj).profile);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        return (super.hashCode() * 31) + this.profile.hashCode();
    }

    public String toString() {
        return "RecognizeSpeech{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', audioFormat=" + getAudioFormat() + ", audioSource=" + getAudioSource() + ", profile='" + this.profile + "'}";
    }

    public static final class Builder extends SuperbowlEvent.Builder<RecognizeSpeechEvent> {
        String profile;

        public Builder() {
            super("SpeechRecognizer", "Recognize");
        }

        public Builder profile(String str) {
            this.profile = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public RecognizeSpeechEvent build() {
            Preconditions.notNull(this.audioSource, "audioSource == null");
            Preconditions.notNull(this.audioFormat, "audioFormat == null");
            if (this.profile == null) {
                this.profile = "CLOSE_TALK";
            }
            return new RecognizeSpeechEvent(this);
        }
    }
}
