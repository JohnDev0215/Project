package com.amazon.music.voice.emp.playbackstatecontext;

import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EMPPlaybackStateContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext;", "Lcom/amazon/alexa/voice/core/processor/superbowl/Context;", "Lorg/json/JSONArray;", "getPlayers", "Lorg/json/JSONObject;", "getPayload", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext$Builder;", "builder", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext$Builder;", "getBuilder", "()Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext$Builder;", "<init>", "(Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext$Builder;)V", "Companion", "Builder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class EMPPlaybackStateContext extends Context {
    private static final Logger Log;
    private static final String TAG;
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EMPPlaybackStateContext(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    static {
        String simpleName = EMPPlaybackStateContext.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        Log = logger;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("agent", this.builder.getAgentId());
        jSONObject.put("spiVersion", this.builder.getSpiVersion());
        jSONObject.put("playerInFocus", this.builder.getPlayerId());
        jSONObject.put("players", getPlayers());
        Log.debug(Intrinsics.stringPlus("getPayload: ", jSONObject));
        return jSONObject;
    }

    private final JSONArray getPlayers() {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("playerId", this.builder.getPlayerId());
        jSONObject.put("loggedIn", this.builder.getIsLoggedIn());
        jSONObject.put("spiVersion", this.builder.getSpiVersion());
        jSONObject.put("launched", true);
        jSONObject.put("playerCookie", this.builder.getPlayerCookie());
        String skillToken = this.builder.getSkillToken();
        if (skillToken != null) {
            jSONObject.put("skillToken", skillToken);
        }
        String playerVersion = this.builder.getPlayerVersion();
        if (playerVersion != null) {
            jSONObject.put("playerVersion", playerVersion);
        }
        String playbackSessionId = this.builder.getPlaybackSessionId();
        if (playbackSessionId != null) {
            jSONObject.put("playbackSessionId", playbackSessionId);
        }
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    /* JADX INFO: compiled from: EMPPlaybackStateContext.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b%\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b)\u0010*J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\t\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0007R\u001a\u0010\u0010\u001a\u00020\u00078\u0006X\u0086D¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u00078\u0006X\u0086D¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R$\u0010\u0016\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0019R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u0005\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u00078\u0006X\u0086D¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b\"\u0010\u0013R$\u0010#\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010\u0011\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0019R$\u0010&\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0011\u001a\u0004\b'\u0010\u0013\"\u0004\b(\u0010\u0019¨\u0006,"}, d2 = {"Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/Context$Builder;", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "isLoggedIn", "setLoggedInState", "", "version", "withPlayerVersion", "id", "withSessionId", "token", "withSkillToken", "cookie", "withPlayerCookie", "agentId", "Ljava/lang/String;", "getAgentId", "()Ljava/lang/String;", "playerId", "getPlayerId", "playerCookie", "getPlayerCookie", "setPlayerCookie", "(Ljava/lang/String;)V", "skillToken", "getSkillToken", "setSkillToken", "Z", "()Z", "setLoggedIn", "(Z)V", "spiVersion", "getSpiVersion", "playerVersion", "getPlayerVersion", "setPlayerVersion", "playbackSessionId", "getPlaybackSessionId", "setPlaybackSessionId", "<init>", "()V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends Context.Builder<EMPPlaybackStateContext> {
        private static final Logger Log;
        private static final String TAG;
        private final String agentId;
        private boolean isLoggedIn;
        private String playbackSessionId;
        private String playerCookie;
        private final String playerId;
        private String playerVersion;
        private String skillToken;
        private final String spiVersion;

        public Builder() {
            super("ExternalMediaPlayer", "ExternalMediaPlayerState");
            this.agentId = "AMUANDOP09";
            this.playerId = "AmazonMusic";
            this.spiVersion = "2.0";
        }

        public final String getAgentId() {
            return this.agentId;
        }

        public final String getPlayerId() {
            return this.playerId;
        }

        public final String getPlayerCookie() {
            return this.playerCookie;
        }

        public final String getSkillToken() {
            return this.skillToken;
        }

        /* JADX INFO: renamed from: isLoggedIn, reason: from getter */
        public final boolean getIsLoggedIn() {
            return this.isLoggedIn;
        }

        public final String getSpiVersion() {
            return this.spiVersion;
        }

        public final String getPlayerVersion() {
            return this.playerVersion;
        }

        public final String getPlaybackSessionId() {
            return this.playbackSessionId;
        }

        static {
            String simpleName = Builder.class.getSimpleName();
            TAG = simpleName;
            Logger logger = LoggerFactory.getLogger(simpleName);
            Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
            Log = logger;
        }

        public EMPPlaybackStateContext build() throws Exception {
            if (this.playerVersion == null) {
                Log.info(TAG, "Player Version is a required parameter to build EMPPlaybackStateContext");
                throw new Exception("Player Version is a required parameter to build EMPPlaybackStateContext");
            }
            if (this.playerCookie == null) {
                Log.info(TAG, "Player Cookie is a required parameter to build EMPPlaybackStateContext");
                throw new Exception("Player Cookie is a required parameter to build EMPPlaybackStateContext");
            }
            return new EMPPlaybackStateContext(this);
        }

        public final Builder setLoggedInState(boolean isLoggedIn) {
            this.isLoggedIn = isLoggedIn;
            return this;
        }

        public final Builder withPlayerVersion(String version) {
            this.playerVersion = version;
            return this;
        }

        public final Builder withSessionId(String id) {
            this.playbackSessionId = id;
            return this;
        }

        public final Builder withSkillToken(String token) {
            this.skillToken = token;
            return this;
        }

        public final Builder withPlayerCookie(String cookie) {
            Intrinsics.checkNotNullParameter(cookie, "cookie");
            this.playerCookie = cookie;
            return this;
        }
    }
}
