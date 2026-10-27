package com.amazon.music.voice.text;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TextMessageEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u000b2\u00020\u0001:\u0002\f\u000bB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0003\u001a\u00020\u0002H\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/music/voice/text/TextMessageEvent;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent;", "Lorg/json/JSONObject;", "getPayload", "Lcom/amazon/music/voice/text/TextMessageEvent$Builder;", "builder", "Lcom/amazon/music/voice/text/TextMessageEvent$Builder;", "getBuilder", "()Lcom/amazon/music/voice/text/TextMessageEvent$Builder;", "<init>", "(Lcom/amazon/music/voice/text/TextMessageEvent$Builder;)V", "Companion", "Builder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class TextMessageEvent extends SuperbowlEvent {
    private final Builder builder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextMessageEvent(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("text", this.builder.getTextMessage());
        return jSONObject;
    }

    /* JADX INFO: compiled from: TextMessageEvent.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/music/voice/text/TextMessageEvent$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent$Builder;", "Lcom/amazon/music/voice/text/TextMessageEvent;", "textMessage", "", "(Ljava/lang/String;)V", "getTextMessage", "()Ljava/lang/String;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder extends SuperbowlEvent.Builder<TextMessageEvent> {
        private final String textMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(String textMessage) {
            super("Alexa.Input.Text", "TextMessage");
            Intrinsics.checkNotNullParameter(textMessage, "textMessage");
            this.textMessage = textMessage;
        }

        public final String getTextMessage() {
            return this.textMessage;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public TextMessageEvent build() {
            return new TextMessageEvent(this);
        }
    }
}
