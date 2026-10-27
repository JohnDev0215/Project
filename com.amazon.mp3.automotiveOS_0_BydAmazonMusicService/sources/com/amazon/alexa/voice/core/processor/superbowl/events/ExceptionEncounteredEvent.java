package com.amazon.alexa.voice.core.processor.superbowl.events;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.identity.auth.device.utils.AccountConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class ExceptionEncounteredEvent extends SuperbowlEvent {
    private final Error error;
    private final String unparsedDirective;

    private ExceptionEncounteredEvent(Builder builder) {
        super(builder);
        this.unparsedDirective = builder.unparsedDirective;
        this.error = builder.error;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("unparsedDirective", this.unparsedDirective);
        jSONObject.put("error", this.error.toJsonObject());
        return jSONObject;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ExceptionEncounteredEvent.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        ExceptionEncounteredEvent exceptionEncounteredEvent = (ExceptionEncounteredEvent) obj;
        if (this.unparsedDirective.equals(exceptionEncounteredEvent.unparsedDirective)) {
            return this.error.equals(exceptionEncounteredEvent.error);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    public int hashCode() {
        return (((super.hashCode() * 31) + this.unparsedDirective.hashCode()) * 31) + this.error.hashCode();
    }

    public String toString() {
        return "ExceptionEncounteredEvent{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', unparsedDirective='" + this.unparsedDirective + "', error=" + this.error + '}';
    }

    public static final class Error {
        private final String message;
        private final Type type;

        public enum Type {
            UNEXPECTED_INFORMATION_RECEIVED("UNEXPECTED_INFORMATION_RECEIVED"),
            UNSUPPORTED_OPERATION("UNSUPPORTED_OPERATION"),
            INTERNAL_ERROR("INTERNAL_ERROR");

            private final String id;

            Type(String str) {
                this.id = str;
            }

            public String getId() {
                return this.id;
            }

            public static Type parse(String str) {
                for (Type type : values()) {
                    if (type.id.equalsIgnoreCase(str)) {
                        return type;
                    }
                }
                return INTERNAL_ERROR;
            }
        }

        public Error(Type type, String str) {
            Preconditions.notNull(type, "type == null");
            Preconditions.notNull(str, "message == null");
            this.type = type;
            this.message = str;
        }

        JSONObject toJsonObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type.getId());
            jSONObject.put("message", this.message);
            return jSONObject;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Error.class != obj.getClass()) {
                return false;
            }
            Error error = (Error) obj;
            if (this.type.equals(error.type)) {
                return this.message.equals(error.message);
            }
            return false;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            return "Error{type='" + this.type + "', message='" + this.message + "'}";
        }
    }

    public static final class Builder extends SuperbowlEvent.Builder<ExceptionEncounteredEvent> {
        Error error;
        String unparsedDirective;

        public Builder() {
            super("System", "ExceptionEncountered");
        }

        public Builder error(Error error) {
            this.error = error;
            return this;
        }

        public Builder unparsedDirective(String str) {
            this.unparsedDirective = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public ExceptionEncounteredEvent build() {
            Preconditions.notNull(this.error, "error == null");
            Preconditions.notNull(this.unparsedDirective, "unparsedDirective == null");
            return new ExceptionEncounteredEvent(this);
        }
    }
}
