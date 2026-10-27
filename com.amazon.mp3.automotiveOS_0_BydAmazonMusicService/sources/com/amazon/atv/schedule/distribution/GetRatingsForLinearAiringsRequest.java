package com.amazon.atv.schedule.distribution;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetRatingsForLinearAiringsRequest {
    public final ImmutableList<String> airingIds;
    public final Optional<String> regulatoryTerritory;
    public final String videoTerritory;

    private GetRatingsForLinearAiringsRequest(Builder builder) {
        this.airingIds = (ImmutableList) Preconditions.checkNotNull(builder.airingIds, "Unexpected null field: airingIds");
        this.videoTerritory = (String) Preconditions.checkNotNull(builder.videoTerritory, "Unexpected null field: videoTerritory");
        this.regulatoryTerritory = Optional.fromNullable(builder.regulatoryTerritory);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetRatingsForLinearAiringsRequest)) {
            return false;
        }
        GetRatingsForLinearAiringsRequest getRatingsForLinearAiringsRequest = (GetRatingsForLinearAiringsRequest) obj;
        return Objects.equal(this.airingIds, getRatingsForLinearAiringsRequest.airingIds) && Objects.equal(this.videoTerritory, getRatingsForLinearAiringsRequest.videoTerritory) && Objects.equal(this.regulatoryTerritory, getRatingsForLinearAiringsRequest.regulatoryTerritory);
    }

    public int hashCode() {
        return Objects.hashCode(this.airingIds, this.videoTerritory, this.regulatoryTerritory);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("airingIds", this.airingIds).add("videoTerritory", this.videoTerritory).add("regulatoryTerritory", this.regulatoryTerritory).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> airingIds;
        public String regulatoryTerritory;
        public String videoTerritory;

        public GetRatingsForLinearAiringsRequest build() {
            return new GetRatingsForLinearAiringsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetRatingsForLinearAiringsRequest> {
        private final ListParser<String> mAiringIdListParser;
        private final SimpleParsers.StringParser mTerritoryParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAiringIdListParser = ListParser.newParser(stringParser);
            this.mTerritoryParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetRatingsForLinearAiringsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRatingsForLinearAiringsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetRatingsForLinearAiringsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRatingsForLinearAiringsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetRatingsForLinearAiringsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetRatingsForLinearAiringsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1798844961) {
                        if (iHashCode != 289062208) {
                            if (iHashCode == 1414951010 && next.equals("regulatoryTerritory")) {
                                b = 2;
                            }
                        } else if (next.equals("airingIds")) {
                            b = 0;
                        }
                    } else if (next.equals("videoTerritory")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableList<String> immutableList = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAiringIdListParser.parse(jsonNode2);
                        }
                        builder.airingIds = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.videoTerritory = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.regulatoryTerritory = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetRatingsForLinearAiringsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.airingIds, this, "airingIds");
            JsonParsingUtils.checkNotNull(builder.videoTerritory, this, "videoTerritory");
            return builder.build();
        }

        @Nonnull
        private GetRatingsForLinearAiringsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1798844961) {
                                if (iHashCode != 289062208) {
                                    if (iHashCode == 1414951010 && currentName.equals("regulatoryTerritory")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("airingIds")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("videoTerritory")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableList<String> immutableList = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAiringIdListParser.parse(jsonParser);
                                }
                                builder.airingIds = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mTerritoryParser.parse(jsonParser);
                                }
                                builder.videoTerritory = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mTerritoryParser.parse(jsonParser);
                                }
                                builder.regulatoryTerritory = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetRatingsForLinearAiringsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.airingIds, this, "airingIds");
                    JsonParsingUtils.checkNotNull(builder.videoTerritory, this, "videoTerritory");
                    return builder.build();
                }
            }
        }
    }
}
