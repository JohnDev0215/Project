package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.mp3.configuration.IntlConfiguration;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SetEndpointDirective extends SuperbowlDirective {
    private final String endpoint;

    SetEndpointDirective(Builder builder) {
        super(builder);
        this.endpoint = builder.endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SetEndpointDirective.class == obj.getClass() && super.equals(obj)) {
            return this.endpoint.equals(((SetEndpointDirective) obj).endpoint);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        return (super.hashCode() * 31) + this.endpoint.hashCode();
    }

    public String toString() {
        return "SetEndpointDirective{messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "', endpoint='" + this.endpoint + "'}";
    }

    public static final class Builder extends SuperbowlDirective.Builder<SetEndpointDirective> {
        String endpoint;

        public Builder() {
            super("System", "SetEndpoint");
        }

        public Builder endpoint(String str) {
            this.endpoint = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public SetEndpointDirective build() {
            Preconditions.notNull(this.endpoint, "endpoint == null");
            return new SetEndpointDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<SetEndpointDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public SetEndpointDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (SetEndpointDirective) new Builder().endpoint(jSONObject.getJSONObject("payload").getString(IntlConfiguration.ENDPOINT_T)).dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
