package com.amazon.dv.xray.game.runtime.service.generated;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.dv.xray.game.runtime.service.shared.SubmitAnswerInputPayload;
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
public class SubmitAnswerInput {
    public final SubmitAnswerInputPayload payload;
    public final String questionId;
    public final String selectedOptionId;

    private SubmitAnswerInput(Builder builder) {
        this.selectedOptionId = (String) Preconditions.checkNotNull(builder.selectedOptionId, "Unexpected null field: selectedOptionId");
        this.payload = (SubmitAnswerInputPayload) Preconditions.checkNotNull(builder.payload, "Unexpected null field: payload");
        this.questionId = (String) Preconditions.checkNotNull(builder.questionId, "Unexpected null field: questionId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubmitAnswerInput)) {
            return false;
        }
        SubmitAnswerInput submitAnswerInput = (SubmitAnswerInput) obj;
        return Objects.equal(this.selectedOptionId, submitAnswerInput.selectedOptionId) && Objects.equal(this.payload, submitAnswerInput.payload) && Objects.equal(this.questionId, submitAnswerInput.questionId);
    }

    public int hashCode() {
        return Objects.hashCode(this.selectedOptionId, this.payload, this.questionId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("selectedOptionId", this.selectedOptionId).add("payload", this.payload).add("questionId", this.questionId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SubmitAnswerInputPayload payload;
        public String questionId;
        public String selectedOptionId;

        public SubmitAnswerInput build() {
            return new SubmitAnswerInput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SubmitAnswerInput> {
        private final SimpleParsers.StringParser mStringValueParser;
        private final SubmitAnswerInputPayload.Parser mSubmitAnswerInputPayloadParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSubmitAnswerInputPayloadParser = new SubmitAnswerInputPayload.Parser(objectMapper);
            this.mStringValueParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SubmitAnswerInput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubmitAnswerInput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SubmitAnswerInput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SubmitAnswerInput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SubmitAnswerInput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SubmitAnswerInput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -786701938) {
                        if (iHashCode != -410528725) {
                            if (iHashCode == 585294753 && next.equals("questionId")) {
                                b = 2;
                            }
                        } else if (next.equals("selectedOptionId")) {
                            b = 0;
                        }
                    } else if (next.equals("payload")) {
                        b = 1;
                    }
                    String str = null;
                    String str2 = null;
                    SubmitAnswerInputPayload submitAnswerInputPayload = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringValueParser.parse(jsonNode2);
                        }
                        builder.selectedOptionId = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            submitAnswerInputPayload = this.mSubmitAnswerInputPayloadParser.parse(jsonNode2);
                        }
                        builder.payload = submitAnswerInputPayload;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringValueParser.parse(jsonNode2);
                        }
                        builder.questionId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SubmitAnswerInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.selectedOptionId, this, "selectedOptionId");
            JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
            JsonParsingUtils.checkNotNull(builder.questionId, this, "questionId");
            return builder.build();
        }

        @Nonnull
        private SubmitAnswerInput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -786701938) {
                                if (iHashCode != -410528725) {
                                    if (iHashCode == 585294753 && currentName.equals("questionId")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("selectedOptionId")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("payload")) {
                                b = 1;
                            }
                            String str = null;
                            String str2 = null;
                            SubmitAnswerInputPayload submitAnswerInputPayload = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringValueParser.parse(jsonParser);
                                }
                                builder.selectedOptionId = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    submitAnswerInputPayload = this.mSubmitAnswerInputPayloadParser.parse(jsonParser);
                                }
                                builder.payload = submitAnswerInputPayload;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringValueParser.parse(jsonParser);
                                }
                                builder.questionId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SubmitAnswerInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.selectedOptionId, this, "selectedOptionId");
                    JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
                    JsonParsingUtils.checkNotNull(builder.questionId, this, "questionId");
                    return builder.build();
                }
            }
        }
    }
}
