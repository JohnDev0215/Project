package com.amazon.music.voice.emp.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EMPPlayerError.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPPlayerError;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent;", "Lorg/json/JSONObject;", "getPayload", "", "toString", "Lcom/amazon/music/voice/emp/events/EMPPlayerError$Builder;", "builder", "Lcom/amazon/music/voice/emp/events/EMPPlayerError$Builder;", "getBuilder", "()Lcom/amazon/music/voice/emp/events/EMPPlayerError$Builder;", "<init>", "(Lcom/amazon/music/voice/emp/events/EMPPlayerError$Builder;)V", "Companion", "Builder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class EMPPlayerError extends SuperbowlEvent {
    private static final Logger Log;
    private static final String TAG;
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EMPPlayerError(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    static {
        String simpleName = EMPPlayerError.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        Log = logger;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("errorName", this.builder.getErrorName());
        jSONObject.put("code", this.builder.getCode());
        jSONObject.put(MediaTrack.ROLE_DESCRIPTION, this.builder.getDescription());
        jSONObject.put("fatal", this.builder.getIsFatal());
        jSONObject.put("playerId", this.builder.getPlayerId());
        String skillToken = this.builder.getSkillToken();
        if (skillToken != null) {
            jSONObject.put("skillToken", skillToken);
        }
        jSONObject.put("playbackSessionId", this.builder.getSessionId());
        return jSONObject;
    }

    public String toString() {
        return TAG + "{namespace='" + ((Object) getNamespace()) + "', name='" + ((Object) getName()) + "', messageId='" + ((Object) getMessageId()) + "', dialogRequestId='" + ((Object) getDialogRequestId()) + "'}";
    }

    /* JADX INFO: compiled from: EMPPlayerError.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u0017\u0010\u0007\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\nR\u0017\u0010\r\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\nR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0015\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u00038\u0006X\u0086D¢\u0006\f\n\u0004\b\u0018\u0010\b\u001a\u0004\b\u0019\u0010\nR$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\b\u001a\u0004\b\u001a\u0010\n\"\u0004\b\u001b\u0010\u001c¨\u0006!"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPPlayerError$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent$Builder;", "Lcom/amazon/music/voice/emp/events/EMPPlayerError;", "", "skillToken", "withSkillToken", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, Splash.PARAMS_SESSION_ID, "Ljava/lang/String;", "getSessionId", "()Ljava/lang/String;", "errorName", "getErrorName", MediaTrack.ROLE_DESCRIPTION, "getDescription", "", "code", "J", "getCode", "()J", "", "isFatal", "Z", "()Z", "playerId", "getPlayerId", "getSkillToken", "setSkillToken", "(Ljava/lang/String;)V", "Lcom/amazon/music/voice/emp/events/EMPError;", "error", "<init>", "(Lcom/amazon/music/voice/emp/events/EMPError;Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends SuperbowlEvent.Builder<EMPPlayerError> {
        private final long code;
        private final String description;
        private final String errorName;
        private final boolean isFatal;
        private final String playerId;
        private final String sessionId;
        private String skillToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(EMPError error, String sessionId) {
            super("ExternalMediaPlayer", "PlayerError");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            this.sessionId = sessionId;
            this.errorName = error.name();
            this.description = error.getDescription();
            this.code = error.getErrorCode();
            this.isFatal = error.isFatal();
            this.playerId = "AmazonMusic";
        }

        public final String getSessionId() {
            return this.sessionId;
        }

        public final String getErrorName() {
            return this.errorName;
        }

        public final String getDescription() {
            return this.description;
        }

        public final long getCode() {
            return this.code;
        }

        /* JADX INFO: renamed from: isFatal, reason: from getter */
        public final boolean getIsFatal() {
            return this.isFatal;
        }

        public final String getPlayerId() {
            return this.playerId;
        }

        public final String getSkillToken() {
            return this.skillToken;
        }

        public final Builder withSkillToken(String skillToken) {
            this.skillToken = skillToken;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public EMPPlayerError build() {
            return new EMPPlayerError(this);
        }
    }
}
