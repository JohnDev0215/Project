package com.amazon.avtitleactionaggregationservice.model.detailpage.download;

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
public class NonDownloadableReason {
    public final Optional<String> downstreamReason;
    public final Optional<NonDownloadableReasonEnum> nonDownloadableReason;

    private NonDownloadableReason(Builder builder) {
        this.downstreamReason = Optional.fromNullable(builder.downstreamReason);
        this.nonDownloadableReason = Optional.fromNullable(builder.nonDownloadableReason);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NonDownloadableReason)) {
            return false;
        }
        NonDownloadableReason nonDownloadableReason = (NonDownloadableReason) obj;
        return Objects.equal(this.downstreamReason, nonDownloadableReason.downstreamReason) && Objects.equal(this.nonDownloadableReason, nonDownloadableReason.nonDownloadableReason);
    }

    public int hashCode() {
        return Objects.hashCode(this.downstreamReason, this.nonDownloadableReason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("downstreamReason", this.downstreamReason).add("nonDownloadableReason", this.nonDownloadableReason).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String downstreamReason;
        public NonDownloadableReasonEnum nonDownloadableReason;

        public NonDownloadableReason build() {
            return new NonDownloadableReason(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NonDownloadableReason> {
        private final EnumParser<NonDownloadableReasonEnum> mNonDownloadableReasonEnumParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNonDownloadableReasonEnumParser = EnumParser.newParser(NonDownloadableReasonEnum.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NonDownloadableReason parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NonDownloadableReason:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NonDownloadableReason parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NonDownloadableReason:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private NonDownloadableReason parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "NonDownloadableReason");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 900296582) {
                        if (iHashCode == 1025774707 && next.equals("nonDownloadableReason")) {
                            b = 1;
                        }
                    } else if (next.equals("downstreamReason")) {
                        b = 0;
                    }
                    NonDownloadableReasonEnum nonDownloadableReasonEnum = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.downstreamReason = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            nonDownloadableReasonEnum = (NonDownloadableReasonEnum) this.mNonDownloadableReasonEnumParser.parse(jsonNode2);
                        }
                        builder.nonDownloadableReason = nonDownloadableReasonEnum;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NonDownloadableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private NonDownloadableReason parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 900296582) {
                                if (iHashCode == 1025774707 && currentName.equals("nonDownloadableReason")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("downstreamReason")) {
                                b = 0;
                            }
                            NonDownloadableReasonEnum nonDownloadableReasonEnum = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.downstreamReason = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    nonDownloadableReasonEnum = (NonDownloadableReasonEnum) this.mNonDownloadableReasonEnumParser.parse(jsonParser);
                                }
                                builder.nonDownloadableReason = nonDownloadableReasonEnum;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NonDownloadableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
