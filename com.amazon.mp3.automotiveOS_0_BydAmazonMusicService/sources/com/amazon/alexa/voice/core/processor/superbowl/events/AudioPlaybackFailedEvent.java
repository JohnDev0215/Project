package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.alexa.voice.core.processor.superbowl.context.AudioPlaybackState;
import com.amazon.identity.auth.device.utils.AccountConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackFailedEvent extends SuperbowlEvent {
    private final Error error;
    private final AudioPlaybackState playbackState;
    private final String token;

    private AudioPlaybackFailedEvent(Builder builder) {
        super(builder);
        this.token = builder.token;
        this.playbackState = builder.playbackState;
        this.error = builder.error;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.token);
        jSONObject.put("currentPlaybackState", this.playbackState.toJsonObject());
        jSONObject.put("error", this.error.toJsonObject());
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioPlaybackFailedEvent.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        AudioPlaybackFailedEvent audioPlaybackFailedEvent = (AudioPlaybackFailedEvent) obj;
        if (this.token.equals(audioPlaybackFailedEvent.token) && this.playbackState.equals(audioPlaybackFailedEvent.playbackState)) {
            return this.error.equals(audioPlaybackFailedEvent.error);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        return (((((super.hashCode() * 31) + this.token.hashCode()) * 31) + this.playbackState.hashCode()) * 31) + this.error.hashCode();
    }

    public String toString() {
        return "AudioPlaybackFailedEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', playbackState=" + this.playbackState + ", error=" + this.error + ", token='" + this.token + "'}";
    }

    public static final class Error {
        private final String message;
        private final Type type;

        public Error(Type type, String str) {
            Preconditions.notNull(type, "type == null");
            this.type = type;
            this.message = str;
        }

        JSONObject toJsonObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type.getId());
            jSONObject.put("message", this.message);
            return jSONObject;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Error.class != obj.getClass()) {
                return false;
            }
            Error error = (Error) obj;
            if (this.type != error.type) {
                return false;
            }
            String str = this.message;
            String str2 = error.message;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            int iHashCode = this.type.hashCode() * 31;
            String str = this.message;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Error{type=" + this.type + ", message='" + this.message + "'}";
        }

        public enum Type {
            MEDIA_UNKNOWN("MEDIA_ERROR_UNKNOWN"),
            MEDIA_INVALID_REQUEST("MEDIA_ERROR_INVALID_REQUEST"),
            MEDIA_SERVICE_UNAVAILABLE("MEDIA_ERROR_SERVICE_UNAVAILABLE"),
            MEDIA_INTERNAL_SERVER_ERROR("MEDIA_ERROR_INTERNAL_SERVER_ERROR"),
            MEDIA_INTERNAL_DEVICE_ERROR("MEDIA_ERROR_INTERNAL_DEVICE_ERROR");

            private final String id;

            Type(String str) {
                this.id = str;
            }

            public String getId() {
                return this.id;
            }

            public static Type parse(String str) {
                for (Type type : values()) {
                    if (type.id.equalsIgnoreCase(str)) {
                        return type;
                    }
                }
                return MEDIA_UNKNOWN;
            }
        }
    }

    public static final class Builder extends SuperbowlEvent.Builder<AudioPlaybackFailedEvent> {
        Error error;
        AudioPlaybackState playbackState;
        String token;

        public Builder() {
            super("AudioPlayer", "PlaybackFailed");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder playbackState(AudioPlaybackState audioPlaybackState) {
            this.playbackState = audioPlaybackState;
            return this;
        }

        public Builder error(Error error) {
            this.error = error;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public AudioPlaybackFailedEvent build() {
            Preconditions.notNull(this.token, "token == null");
            Preconditions.notNull(this.error, "error == null");
            Preconditions.notNull(this.playbackState, "playbackState == null");
            return new AudioPlaybackFailedEvent(this);
        }
    }
}
