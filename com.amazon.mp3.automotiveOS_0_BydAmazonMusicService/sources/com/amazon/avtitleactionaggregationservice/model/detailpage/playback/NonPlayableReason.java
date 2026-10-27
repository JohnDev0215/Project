package com.amazon.avtitleactionaggregationservice.model.detailpage.playback;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class NonPlayableReason {
    public final Optional<String> downstreamReason;
    public final Optional<NonPlayableReasonEnum> reason;

    private NonPlayableReason(Builder builder) {
        this.downstreamReason = Optional.fromNullable(builder.downstreamReason);
        this.reason = Optional.fromNullable(builder.reason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NonPlayableReason)) {
            return false;
        }
        NonPlayableReason nonPlayableReason = (NonPlayableReason) obj;
        return Objects.equal(this.downstreamReason, nonPlayableReason.downstreamReason) && Objects.equal(this.reason, nonPlayableReason.reason);
    }

    public int hashCode() {
        return Objects.hashCode(this.downstreamReason, this.reason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("downstreamReason", this.downstreamReason).add("reason", this.reason).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String downstreamReason;
        public NonPlayableReasonEnum reason;

        public NonPlayableReason build() {
            return new NonPlayableReason(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NonPlayableReason> {
        private final EnumParser<NonPlayableReasonEnum> mNonPlayableReasonEnumParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNonPlayableReasonEnumParser = EnumParser.newParser(NonPlayableReasonEnum.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NonPlayableReason parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NonPlayableReason:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NonPlayableReason parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NonPlayableReason:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private NonPlayableReason parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "NonPlayableReason");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -934964668) {
                        if (iHashCode == 900296582 && next.equals("downstreamReason")) {
                            b = 0;
                        }
                    } else if (next.equals("reason")) {
                        b = 1;
                    }
                    NonPlayableReasonEnum nonPlayableReasonEnum = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.downstreamReason = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            nonPlayableReasonEnum = (NonPlayableReasonEnum) this.mNonPlayableReasonEnumParser.parse(jsonNode2);
                        }
                        builder.reason = nonPlayableReasonEnum;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NonPlayableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private NonPlayableReason parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -934964668) {
                                if (iHashCode == 900296582 && currentName.equals("downstreamReason")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("reason")) {
                                b = 1;
                            }
                            NonPlayableReasonEnum nonPlayableReasonEnum = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.downstreamReason = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    nonPlayableReasonEnum = (NonPlayableReasonEnum) this.mNonPlayableReasonEnumParser.parse(jsonParser);
                                }
                                builder.reason = nonPlayableReasonEnum;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NonPlayableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
