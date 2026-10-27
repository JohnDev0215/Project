package com.amazon.alexa.voice.core.processor.superbowl.context;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackContext extends Context {
    private final AudioPlaybackState playbackState;

    private AudioPlaybackContext(Builder builder) {
        super(builder);
        this.playbackState = new AudioPlaybackState(builder.playerActivity, builder.offsetInMilliseconds, builder.token);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    protected JSONObject getPayload() throws JSONException {
        return this.playbackState.toJsonObject();
    }

    public String toString() {
        return "AudioPlaybackContext{namespace='" + getNamespace() + "', name='" + getName() + "', playbackState='" + this.playbackState + '}';
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AudioPlaybackContext.class == obj.getClass() && super.equals(obj)) {
            return this.playbackState.equals(((AudioPlaybackContext) obj).playbackState);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public int hashCode() {
        return (super.hashCode() * 31) + this.playbackState.hashCode();
    }

    public static final class Builder extends Context.Builder<AudioPlaybackContext> {
        private long offsetInMilliseconds;
        private AudioPlayerState playerActivity;
        private String token;

        public Builder() {
            super("AudioPlayer", "PlaybackState");
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder playerActivity(AudioPlayerState audioPlayerState) {
            this.playerActivity = audioPlayerState;
            return this;
        }

        public Builder offset(long j) {
            this.offsetInMilliseconds = j;
            return this;
        }

        public AudioPlaybackContext build() {
            Preconditions.notNull(this.token, "token == null");
            Preconditions.notNull(this.playerActivity, "playerActivity == null");
            Preconditions.min(this.offsetInMilliseconds, 0L, "Offset cannot be negative");
            return new AudioPlaybackContext(this);
        }
    }
}
