package com.amazon.dv.xray.game.runtime.service.shared;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.dv.xray.game.runtime.service.shared.GetGameTabInputPayload;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetGameTabInputInternal {
    public final GetGameTabInputPayload payload;

    private GetGameTabInputInternal(Builder builder) {
        this.payload = (GetGameTabInputPayload) Preconditions.checkNotNull(builder.payload, "Unexpected null field: payload");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetGameTabInputInternal) {
            return Objects.equal(this.payload, ((GetGameTabInputInternal) obj).payload);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.payload);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("payload", this.payload).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public GetGameTabInputPayload payload;

        public GetGameTabInputInternal build() {
            return new GetGameTabInputInternal(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetGameTabInputInternal> {
        private final GetGameTabInputPayload.Parser mGetGameTabInputPayloadParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetGameTabInputPayloadParser = new GetGameTabInputPayload.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetGameTabInputInternal parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInputInternal:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetGameTabInputInternal parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInputInternal:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetGameTabInputInternal parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetGameTabInputInternal");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -786701938 && next.equals("payload")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.payload = jsonNode2.isNull() ? null : this.mGetGameTabInputPayloadParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetGameTabInputInternal so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
            return builder.build();
        }

        @Nonnull
        private GetGameTabInputInternal parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            if (currentName.hashCode() == -786701938 && currentName.equals("payload")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.payload = currentToken == JsonToken.VALUE_NULL ? null : this.mGetGameTabInputPayloadParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetGameTabInputInternal so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
                    return builder.build();
                }
            }
        }
    }
}
