package com.amazon.music.voice.launcher.events;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.aom.CapabilityRegistry$Interface;
import com.amazon.music.platform.aom.launcher.events.EventTarget;
import com.amazon.music.platform.aom.launcher.events.EventType;
import com.amazon.music.platform.aom.launcher.events.LaunchOutcome;
import com.amazon.music.platform.aom.launcher.events.LaunchReason;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: LaunchTargetFailedEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u000b2\u00020\u0001:\u0002\f\u000bB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0003\u001a\u00020\u0002H\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent;", "Lorg/json/JSONObject;", "getPayload", "Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent$Builder;", "builder", "Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent$Builder;", "getBuilder", "()Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent$Builder;", "<init>", "(Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent$Builder;)V", "Companion", "Builder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class LaunchTargetFailedEvent extends SuperbowlEvent {
    private static final String NAMESPACE = CapabilityRegistry$Interface.LAUNCHER.getInterfaceName();
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchTargetFailedEvent(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.builder.getToken());
        jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.builder.getEventType().toString());
        jSONObject.put("target", new JSONObject().put("token", this.builder.getEventTarget().getToken()));
        jSONObject.put("outcome", this.builder.getOutcome().name());
        if (!this.builder.getReasons().isEmpty()) {
            jSONObject.put("reasons", new JSONArray((Collection<?>) CollectionsKt__CollectionsKt.mutableListOf(((LaunchReason) ((ArrayList) this.builder.getReasons()).get(0)).toString())));
        }
        return jSONObject;
    }

    /* JADX INFO: compiled from: LaunchTargetFailedEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B9\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b \u0010!J\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0006\u001a\u0004\b\u001a\u0010\bR\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent$Builder;", "Lcom/amazon/music/voice/launcher/events/LaunchTargetFailedEvent;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "Lcom/amazon/music/platform/aom/launcher/events/EventTarget;", "eventTarget", "Lcom/amazon/music/platform/aom/launcher/events/EventTarget;", "getEventTarget", "()Lcom/amazon/music/platform/aom/launcher/events/EventTarget;", "Lcom/amazon/music/platform/aom/launcher/events/LaunchOutcome;", "outcome", "Lcom/amazon/music/platform/aom/launcher/events/LaunchOutcome;", "getOutcome", "()Lcom/amazon/music/platform/aom/launcher/events/LaunchOutcome;", "", "Lcom/amazon/music/platform/aom/launcher/events/LaunchReason;", "reasons", "Ljava/util/Collection;", "getReasons", "()Ljava/util/Collection;", "messageId", "getMessageId", "Lcom/amazon/music/platform/aom/launcher/events/EventType;", "eventType", "Lcom/amazon/music/platform/aom/launcher/events/EventType;", "getEventType", "()Lcom/amazon/music/platform/aom/launcher/events/EventType;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/aom/launcher/events/EventTarget;Lcom/amazon/music/platform/aom/launcher/events/LaunchOutcome;Ljava/util/Collection;Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends SuperbowlEvent.Builder<LaunchTargetFailedEvent> {
        private final EventTarget eventTarget;
        private final EventType eventType;
        private final String messageId;
        private final LaunchOutcome outcome;
        private final Collection<LaunchReason> reasons;
        private final String token;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Builder(String token, EventTarget eventTarget, LaunchOutcome outcome, Collection<? extends LaunchReason> reasons, String str) {
            super(LaunchTargetFailedEvent.NAMESPACE, "ErrorResponse");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(eventTarget, "eventTarget");
            Intrinsics.checkNotNullParameter(outcome, "outcome");
            Intrinsics.checkNotNullParameter(reasons, "reasons");
            this.token = token;
            this.eventTarget = eventTarget;
            this.outcome = outcome;
            this.reasons = reasons;
            this.messageId = str;
            this.eventType = EventType.LAUNCH_TARGET_FAILED;
        }

        public final EventTarget getEventTarget() {
            return this.eventTarget;
        }

        public final LaunchOutcome getOutcome() {
            return this.outcome;
        }

        public final Collection<LaunchReason> getReasons() {
            return this.reasons;
        }

        public final String getToken() {
            return this.token;
        }

        public final EventType getEventType() {
            return this.eventType;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public LaunchTargetFailedEvent build() {
            String str = this.messageId;
            if (str != null) {
                messageId(str);
            }
            return new LaunchTargetFailedEvent(this);
        }
    }
}
