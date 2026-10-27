package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class ExpectSpeechDirective extends SuperbowlDirective {
    private final long timeoutInMilliseconds;

    ExpectSpeechDirective(Builder builder) {
        super(builder);
        this.timeoutInMilliseconds = builder.timeoutInMilliseconds;
    }

    public long getTimeoutInMilliseconds() {
        return this.timeoutInMilliseconds;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ExpectSpeechDirective.class == obj.getClass() && super.equals(obj) && this.timeoutInMilliseconds == ((ExpectSpeechDirective) obj).timeoutInMilliseconds;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        long j = this.timeoutInMilliseconds;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "ExpectSpeech{messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', timeoutInMilliseconds=" + this.timeoutInMilliseconds + '}';
    }

    public static final class Builder extends SuperbowlDirective.Builder<ExpectSpeechDirective> {
        long timeoutInMilliseconds;

        public Builder() {
            super("SpeechRecognizer", "ExpectSpeech");
        }

        public Builder timeoutInMilliseconds(long j) {
            this.timeoutInMilliseconds = j;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public ExpectSpeechDirective build() {
            return new ExpectSpeechDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<ExpectSpeechDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public ExpectSpeechDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (ExpectSpeechDirective) new Builder().timeoutInMilliseconds(jSONObject.getJSONObject("payload").getLong("timeoutInMilliseconds")).dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
