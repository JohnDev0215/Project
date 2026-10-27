package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.FailedRestrictedAiring;
import com.amazon.atv.parentalcontrols.GetAiringRestrictionsResponseMetadata;
import com.amazon.atv.parentalcontrols.SuccessfulRestrictedAiring;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetAiringRestrictionsResponse {
    public final Optional<ImmutableList<FailedRestrictedAiring>> failedRestrictedAiringList;
    public final Optional<GetAiringRestrictionsResponseMetadata> metadata;
    public final Optional<ImmutableList<SuccessfulRestrictedAiring>> successfulRestrictedAiringList;

    private GetAiringRestrictionsResponse(Builder builder) {
        this.successfulRestrictedAiringList = Optional.fromNullable(builder.successfulRestrictedAiringList);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.failedRestrictedAiringList = Optional.fromNullable(builder.failedRestrictedAiringList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAiringRestrictionsResponse)) {
            return false;
        }
        GetAiringRestrictionsResponse getAiringRestrictionsResponse = (GetAiringRestrictionsResponse) obj;
        return Objects.equal(this.successfulRestrictedAiringList, getAiringRestrictionsResponse.successfulRestrictedAiringList) && Objects.equal(this.metadata, getAiringRestrictionsResponse.metadata) && Objects.equal(this.failedRestrictedAiringList, getAiringRestrictionsResponse.failedRestrictedAiringList);
    }

    public int hashCode() {
        return Objects.hashCode(this.successfulRestrictedAiringList, this.metadata, this.failedRestrictedAiringList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("successfulRestrictedAiringList", this.successfulRestrictedAiringList).add("metadata", this.metadata).add("failedRestrictedAiringList", this.failedRestrictedAiringList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<FailedRestrictedAiring> failedRestrictedAiringList;
        public GetAiringRestrictionsResponseMetadata metadata;
        public ImmutableList<SuccessfulRestrictedAiring> successfulRestrictedAiringList;

        public GetAiringRestrictionsResponse build() {
            return new GetAiringRestrictionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAiringRestrictionsResponse> {
        private final ListParser<FailedRestrictedAiring> mFailedRestrictedAiringListParser;
        private final GetAiringRestrictionsResponseMetadata.Parser mGetAiringRestrictionsResponseMetadataParser;
        private final ListParser<SuccessfulRestrictedAiring> mSuccessfulRestrictedAiringListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSuccessfulRestrictedAiringListParser = ListParser.newParser(new SuccessfulRestrictedAiring.Parser(objectMapper));
            this.mFailedRestrictedAiringListParser = ListParser.newParser(new FailedRestrictedAiring.Parser(objectMapper));
            this.mGetAiringRestrictionsResponseMetadataParser = new GetAiringRestrictionsResponseMetadata.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAiringRestrictionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringRestrictionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAiringRestrictionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringRestrictionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAiringRestrictionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAiringRestrictionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -791390325) {
                        if (iHashCode != -450004177) {
                            if (iHashCode == 620433838 && next.equals("failedRestrictedAiringList")) {
                                b = 2;
                            }
                        } else if (next.equals("metadata")) {
                            b = 1;
                        }
                    } else if (next.equals("successfulRestrictedAiringList")) {
                        b = 0;
                    }
                    ImmutableList<FailedRestrictedAiring> immutableList = null;
                    ImmutableList<SuccessfulRestrictedAiring> immutableList2 = null;
                    GetAiringRestrictionsResponseMetadata getAiringRestrictionsResponseMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSuccessfulRestrictedAiringListParser.parse(jsonNode2);
                        }
                        builder.successfulRestrictedAiringList = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            getAiringRestrictionsResponseMetadata = this.mGetAiringRestrictionsResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = getAiringRestrictionsResponseMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mFailedRestrictedAiringListParser.parse(jsonNode2);
                        }
                        builder.failedRestrictedAiringList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAiringRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAiringRestrictionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -791390325) {
                                if (iHashCode != -450004177) {
                                    if (iHashCode == 620433838 && currentName.equals("failedRestrictedAiringList")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("metadata")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("successfulRestrictedAiringList")) {
                                b = 0;
                            }
                            ImmutableList<FailedRestrictedAiring> immutableList = null;
                            ImmutableList<SuccessfulRestrictedAiring> immutableList2 = null;
                            GetAiringRestrictionsResponseMetadata getAiringRestrictionsResponseMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mSuccessfulRestrictedAiringListParser.parse(jsonParser);
                                }
                                builder.successfulRestrictedAiringList = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getAiringRestrictionsResponseMetadata = this.mGetAiringRestrictionsResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = getAiringRestrictionsResponseMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mFailedRestrictedAiringListParser.parse(jsonParser);
                                }
                                builder.failedRestrictedAiringList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAiringRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
