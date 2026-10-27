package com.amazon.dv.xray.game.runtime.service.shared;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.dv.xray.game.runtime.service.shared.XRayId;
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
public class SubmitAnswerInputPayload {
    public final XRayId xrayId;

    private SubmitAnswerInputPayload(Builder builder) {
        this.xrayId = (XRayId) Preconditions.checkNotNull(builder.xrayId, "Unexpected null field: xrayId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SubmitAnswerInputPayload) {
            return Objects.equal(this.xrayId, ((SubmitAnswerInputPayload) obj).xrayId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.xrayId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("xrayId", this.xrayId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public XRayId xrayId;

        public SubmitAnswerInputPayload build() {
            return new SubmitAnswerInputPayload(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SubmitAnswerInputPayload> {
        private final XRayId.Parser mXRayIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mXRayIdParser = new XRayId.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SubmitAnswerInputPayload parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubmitAnswerInputPayload:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SubmitAnswerInputPayload parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubmitAnswerInputPayload:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SubmitAnswerInputPayload parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SubmitAnswerInputPayload");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -751179411 && next.equals("xrayId")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.xrayId = jsonNode2.isNull() ? null : this.mXRayIdParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubmitAnswerInputPayload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.xrayId, this, "xrayId");
            return builder.build();
        }

        @Nonnull
        private SubmitAnswerInputPayload parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -751179411 && currentName.equals("xrayId")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.xrayId = currentToken == JsonToken.VALUE_NULL ? null : this.mXRayIdParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubmitAnswerInputPayload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.xrayId, this, "xrayId");
                    return builder.build();
                }
            }
        }
    }
}
