package com.amazon.music.voice.emp.events;

import androidx.core.app.NotificationCompat;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContext;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EMPPlayerEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPPlayerEvent;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent;", "Lorg/json/JSONObject;", "getPayload", "", "toString", "Lcom/amazon/music/voice/emp/events/EMPPlayerEvent$Builder;", "builder", "Lcom/amazon/music/voice/emp/events/EMPPlayerEvent$Builder;", "getBuilder", "()Lcom/amazon/music/voice/emp/events/EMPPlayerEvent$Builder;", "<init>", "(Lcom/amazon/music/voice/emp/events/EMPPlayerEvent$Builder;)V", "Companion", "Builder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class EMPPlayerEvent extends SuperbowlEvent {
    private static final Logger Log;
    private static final String TAG;
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EMPPlayerEvent(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    static {
        String simpleName = EMPPlayerEvent.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        Log = logger;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("eventName", this.builder.getEventName());
        jSONObject.put("playbackSessionId", this.builder.getSessionId());
        jSONObject.put("playerId", this.builder.getPlayerID());
        String skillToken = this.builder.getSkillToken();
        if (skillToken != null) {
            jSONObject.put("skillToken", skillToken);
        }
        return jSONObject;
    }

    public String toString() {
        return EMPPlaybackStateContext.class.getSimpleName() + "{namespace='" + ((Object) getNamespace()) + "', name='" + ((Object) getName()) + "', messageId='" + ((Object) getMessageId()) + "', dialogRequestId='" + ((Object) getDialogRequestId()) + "'}";
    }

    /* JADX INFO: compiled from: EMPPlayerEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u0017\u0010\u0007\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u00020\u00038\u0006X\u0086D¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\nR$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\b\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPPlayerEvent$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent$Builder;", "Lcom/amazon/music/voice/emp/events/EMPPlayerEvent;", "", "skillToken", "withSkillToken", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, Splash.PARAMS_SESSION_ID, "Ljava/lang/String;", "getSessionId", "()Ljava/lang/String;", "eventName", "getEventName", "playerID", "getPlayerID", "getSkillToken", "setSkillToken", "(Ljava/lang/String;)V", "Lcom/amazon/music/voice/emp/events/EMPEvent;", NotificationCompat.CATEGORY_EVENT, "<init>", "(Lcom/amazon/music/voice/emp/events/EMPEvent;Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends SuperbowlEvent.Builder<EMPPlayerEvent> {
        private final String eventName;
        private final String playerID;
        private final String sessionId;
        private String skillToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(EMPEvent event, String sessionId) {
            super("ExternalMediaPlayer", "PlayerEvent");
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            this.sessionId = sessionId;
            this.eventName = event.getEventName();
            this.playerID = "AmazonMusic";
        }

        public final String getSessionId() {
            return this.sessionId;
        }

        public final String getEventName() {
            return this.eventName;
        }

        public final String getPlayerID() {
            return this.playerID;
        }

        public final String getSkillToken() {
            return this.skillToken;
        }

        public final Builder withSkillToken(String skillToken) {
            this.skillToken = skillToken;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public EMPPlayerEvent build() {
            return new EMPPlayerEvent(this);
        }
    }
}
