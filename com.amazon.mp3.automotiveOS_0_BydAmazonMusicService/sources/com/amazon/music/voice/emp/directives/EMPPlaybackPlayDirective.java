package com.amazon.music.voice.emp.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: EMPPlaybackPlayDirective.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0003\u0007\b\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective;", "builder", "Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective$Builder;", "(Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective$Builder;)V", "getBuilder", "()Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective$Builder;", "Builder", "Companion", "Factory", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EMPPlaybackPlayDirective extends SuperbowlDirective {
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EMPPlaybackPlayDirective(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    public final Builder getBuilder() {
        return this.builder;
    }

    /* JADX INFO: compiled from: EMPPlaybackPlayDirective.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u001b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\"\u0010#J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\b\u001a\u00020\u00048\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\b\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR$\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0010\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R$\u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u0014¨\u0006$"}, d2 = {"Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective$Builder;", "Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "token", "playbackContextToken", "id", "playerId", "", "index", "offset", "offsetInMilliseconds", Splash.PARAMS_SESSION_ID, "playbackSessionId", "skillToken", "Ljava/lang/String;", "getPlaybackContextToken", "()Ljava/lang/String;", "setPlaybackContextToken", "(Ljava/lang/String;)V", "getPlayerId", "setPlayerId", "Ljava/lang/Long;", "getIndex", "()Ljava/lang/Long;", "setIndex", "(Ljava/lang/Long;)V", "getOffsetInMilliseconds", "setOffsetInMilliseconds", "getPlaybackSessionId", "setPlaybackSessionId", "getSkillToken", "setSkillToken", "<init>", "()V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends SuperbowlDirective.Builder<EMPPlaybackPlayDirective> {
        private Long index;
        private Long offsetInMilliseconds;
        public String playbackContextToken;
        private String playbackSessionId;
        public String playerId;
        private String skillToken;

        public Builder() {
            super("ExternalMediaPlayer", "Play");
        }

        public final String getPlaybackContextToken() {
            String str = this.playbackContextToken;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("playbackContextToken");
            return null;
        }

        public final void setPlaybackContextToken(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.playbackContextToken = str;
        }

        public final void setPlayerId(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.playerId = str;
        }

        public final String getPlaybackSessionId() {
            return this.playbackSessionId;
        }

        public final String getSkillToken() {
            return this.skillToken;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public EMPPlaybackPlayDirective build() {
            return new EMPPlaybackPlayDirective(this);
        }

        public final Builder playbackContextToken(String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            String strStringPlus = Intrinsics.stringPlus(Intrinsics.stringPlus("", "alexa:/"), token);
            Long l = this.index;
            if (l != null) {
                strStringPlus = strStringPlus + "?index=" + l.longValue();
            }
            Long l2 = this.offsetInMilliseconds;
            if (l2 != null) {
                strStringPlus = strStringPlus + "?offsetInMilliseconds=" + l2.longValue();
            }
            setPlaybackContextToken(strStringPlus);
            return this;
        }

        public final Builder playerId(String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            setPlayerId(id);
            return this;
        }

        public final Builder index(long index) {
            this.index = Long.valueOf(index);
            return this;
        }

        public final Builder offsetInMilliseconds(long offset) {
            this.offsetInMilliseconds = Long.valueOf(offset);
            return this;
        }

        public final Builder playbackSessionId(String sessionId) {
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            this.playbackSessionId = sessionId;
            return this;
        }

        public final Builder skillToken(String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            this.skillToken = token;
            return this;
        }
    }

    /* JADX INFO: compiled from: EMPPlaybackPlayDirective.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective$Factory;", "Lcom/amazon/alexa/voice/core/processor/superbowl/directives/DirectiveBaseFactory;", "Lcom/amazon/music/voice/emp/directives/EMPPlaybackPlayDirective;", "()V", "create", "p0", "Lorg/json/JSONObject;", "p1", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContentProvider;", "getIndex", "", "json", "getOffsetInMilliseconds", "getPlaybackContextToken", "", "getPlaybackSessionId", "getPlayerId", "getSkillToken", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Factory extends DirectiveBaseFactory<EMPPlaybackPlayDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public EMPPlaybackPlayDirective create(JSONObject p0, ContentProvider p1) {
            SuperbowlDirective superbowlDirectiveBuild = new Builder().playerId(getPlayerId(p0)).index(getIndex(p0)).playbackSessionId(getPlaybackSessionId(p0)).offsetInMilliseconds(getOffsetInMilliseconds(p0)).playbackContextToken(getPlaybackContextToken(p0)).skillToken(getSkillToken(p0)).dialogRequestId(getDialogRequestId(p0)).messageId(getMessageId(p0)).build();
            Intrinsics.checkNotNullExpressionValue(superbowlDirectiveBuild, "Builder()\n              …                 .build()");
            return (EMPPlaybackPlayDirective) superbowlDirectiveBuild;
        }

        private final String getPlaybackContextToken(JSONObject json) throws Exception {
            JSONObject jSONObject;
            String string = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                string = jSONObject.getString("playbackContextToken");
            }
            if (string != null) {
                return string;
            }
            throw new Exception("Unable to parse PlaybackContextToken");
        }

        private final String getPlayerId(JSONObject json) throws Exception {
            JSONObject jSONObject;
            String string = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                string = jSONObject.getString("playerId");
            }
            if (string != null) {
                return string;
            }
            throw new Exception("Unable to parse PlayerID");
        }

        private final long getIndex(JSONObject json) throws Exception {
            JSONObject jSONObject;
            Long lValueOf = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                lValueOf = Long.valueOf(jSONObject.getLong("index"));
            }
            if (lValueOf != null) {
                return lValueOf.longValue();
            }
            throw new Exception("Unable to parse Index");
        }

        private final long getOffsetInMilliseconds(JSONObject json) throws Exception {
            JSONObject jSONObject;
            Long lValueOf = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                lValueOf = Long.valueOf(jSONObject.getLong("offsetInMilliseconds"));
            }
            if (lValueOf != null) {
                return lValueOf.longValue();
            }
            throw new Exception("Unable to parse OffsetInMilliseconds");
        }

        private final String getPlaybackSessionId(JSONObject json) throws Exception {
            JSONObject jSONObject;
            String string = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                string = jSONObject.getString("playbackSessionId");
            }
            if (string != null) {
                return string;
            }
            throw new Exception("Unable to parse PlaybackSessionId");
        }

        private final String getSkillToken(JSONObject json) throws Exception {
            JSONObject jSONObject;
            String string = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                string = jSONObject.getString("skillToken");
            }
            if (string != null) {
                return string;
            }
            throw new Exception("Unable to parse skillToken");
        }
    }
}
