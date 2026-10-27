package com.amazon.alexa.voice.core.processor.superbowl.context;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlaybackState {
    private final long offsetInMilliseconds;
    private final AudioPlayerState playerActivity;
    private final String token;

    public AudioPlaybackState(AudioPlayerState audioPlayerState, long j, String str) {
        Preconditions.notNull(str, "token == null");
        Preconditions.notNull(audioPlayerState, "playerActivity == null");
        Preconditions.min(j, 0L, "offsetInMilliseconds cannot be negative");
        this.token = str;
        this.playerActivity = audioPlayerState;
        this.offsetInMilliseconds = j;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.token);
        jSONObject.put("playerActivity", this.playerActivity.getId());
        jSONObject.put("offsetInMilliseconds", this.offsetInMilliseconds);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioPlaybackState.class != obj.getClass()) {
            return false;
        }
        AudioPlaybackState audioPlaybackState = (AudioPlaybackState) obj;
        return this.offsetInMilliseconds == audioPlaybackState.offsetInMilliseconds && this.token.equals(audioPlaybackState.token) && this.playerActivity == audioPlaybackState.playerActivity;
    }

    public int hashCode() {
        int iHashCode = ((this.token.hashCode() * 31) + this.playerActivity.hashCode()) * 31;
        long j = this.offsetInMilliseconds;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "AudioPlaybackState{token='" + this.token + "', playerActivity=" + this.playerActivity + ", offsetInMilliseconds=" + this.offsetInMilliseconds + '}';
    }
}
