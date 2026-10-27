package com.amazon.music.voice.playbackstate;

import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackStateContext extends Context {
    private static final Logger LOG = LoggerFactory.getLogger(PlaybackStateContext.class.getSimpleName());
    private Builder builder;

    public PlaybackStateContext(Builder builder) {
        super(builder);
        this.builder = builder;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Builder builder = this.builder;
        if (builder.media == null) {
            return jSONObject;
        }
        jSONObject.put("state", builder.playerState.toString());
        jSONObject.put("positionMilliseconds", this.builder.trackPosition);
        jSONObject.put("supportedOperations", getSupportedOperations());
        jSONObject.put("media", this.builder.media.toJsonObject());
        if (this.builder.isAlexaEMPPlaybackEnabled) {
            jSONObject.put("players", getPlayers());
        }
        jSONObject.put("shuffle", this.builder.shuffleMode.name());
        jSONObject.put("repeat", this.builder.repeatMode.name());
        jSONObject.put("favorite", this.builder.favoriteState.name());
        LOG.debug("getPayload: " + jSONObject.toString());
        return jSONObject;
    }

    private JSONArray getSupportedOperations() {
        JSONArray jSONArray = new JSONArray();
        for (MediaOperation mediaOperation : MediaOperation.values()) {
            if (this.builder.media.isMediaOperationSupported(mediaOperation)) {
                jSONArray.put(mediaOperation.getOperationName());
            }
        }
        return jSONArray;
    }

    private JSONArray getPlayers() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("playerId", "AmazonMusic");
        jSONObject.put("state", this.builder.playerState);
        jSONObject.put("supportedOperations", getSupportedOperations());
        jSONObject.put("positionMilliseconds", this.builder.trackPosition);
        jSONObject.put("media", this.builder.media.toJsonObject());
        jSONObject.put("shuffle", this.builder.shuffleMode.name());
        jSONObject.put("repeat", this.builder.repeatMode.name());
        jSONObject.put("favorite", this.builder.favoriteState.name());
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    public static final class Builder extends Context.Builder<PlaybackStateContext> {
        PlayerStateProvider.FavoriteState favoriteState;
        boolean isAlexaEMPPlaybackEnabled;
        MediaMetadata media;
        PlayerStateProvider.PlayerState playerState;
        PlayerStateProvider.RepeatMode repeatMode;
        PlayerStateProvider.ShuffleMode shuffleMode;
        int trackPosition;

        public Builder() {
            super("Alexa.PlaybackStateReporter", "PlaybackState");
            this.isAlexaEMPPlaybackEnabled = false;
            this.shuffleMode = PlayerStateProvider.ShuffleMode.SHUFFLED;
            this.repeatMode = PlayerStateProvider.RepeatMode.NOT_REPEATED;
            this.favoriteState = PlayerStateProvider.FavoriteState.NOT_RATED;
        }

        public Builder media(MediaMetadata mediaMetadata) {
            this.media = mediaMetadata;
            return this;
        }

        public Builder playerState(PlayerStateProvider.PlayerState playerState) {
            this.playerState = playerState;
            return this;
        }

        public Builder trackPosition(int i) {
            this.trackPosition = i;
            return this;
        }

        public Builder enableAlexaEMPPlayback(boolean z) {
            this.isAlexaEMPPlaybackEnabled = z;
            return this;
        }

        public Builder shuffleMode(PlayerStateProvider.ShuffleMode shuffleMode) {
            this.shuffleMode = shuffleMode;
            return this;
        }

        public Builder repeatMode(PlayerStateProvider.RepeatMode repeatMode) {
            this.repeatMode = repeatMode;
            return this;
        }

        public Builder favoriteState(PlayerStateProvider.FavoriteState favoriteState) {
            this.favoriteState = favoriteState;
            return this;
        }

        public PlaybackStateContext build() {
            return new PlaybackStateContext(this);
        }
    }
}
