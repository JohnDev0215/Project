package com.amazon.atv.parentalcontrols;

import com.amazon.atv.catalog.ratings.Rating;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetIntlAllowedRatingsResponse {
    public final ImmutableList<Rating> allowedRatings;
    public final boolean parentalControlsRestrictionsApply;

    private GetIntlAllowedRatingsResponse(Builder builder) {
        this.allowedRatings = (ImmutableList) Preconditions.checkNotNull(builder.allowedRatings, "Unexpected null field: allowedRatings");
        this.parentalControlsRestrictionsApply = builder.parentalControlsRestrictionsApply;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIntlAllowedRatingsResponse)) {
            return false;
        }
        GetIntlAllowedRatingsResponse getIntlAllowedRatingsResponse = (GetIntlAllowedRatingsResponse) obj;
        return Objects.equal(this.allowedRatings, getIntlAllowedRatingsResponse.allowedRatings) && Objects.equal(Boolean.valueOf(this.parentalControlsRestrictionsApply), Boolean.valueOf(getIntlAllowedRatingsResponse.parentalControlsRestrictionsApply));
    }

    public int hashCode() {
        return Objects.hashCode(this.allowedRatings, Boolean.valueOf(this.parentalControlsRestrictionsApply));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("allowedRatings", this.allowedRatings).add("parentalControlsRestrictionsApply", this.parentalControlsRestrictionsApply).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<Rating> allowedRatings;
        public boolean parentalControlsRestrictionsApply;

        public GetIntlAllowedRatingsResponse build() {
            return new GetIntlAllowedRatingsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlAllowedRatingsResponse> {
        private final ListParser<Rating> mratingsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mratingsListParser = ListParser.newParser(new Rating.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlAllowedRatingsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlAllowedRatingsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlAllowedRatingsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlAllowedRatingsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetIntlAllowedRatingsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlAllowedRatingsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -143847268) {
                        if (iHashCode == 1096641454 && next.equals("allowedRatings")) {
                            b = 0;
                        }
                    } else if (next.equals("parentalControlsRestrictionsApply")) {
                        b = 1;
                    }
                    if (b == 0) {
                        builder.allowedRatings = jsonNode2.isNull() ? null : this.mratingsListParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field parentalControlsRestrictionsApply can't be null");
                        }
                        builder.parentalControlsRestrictionsApply = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlAllowedRatingsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.allowedRatings, this, "allowedRatings");
            return builder.build();
        }

        @Nonnull
        private GetIntlAllowedRatingsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -143847268) {
                                if (iHashCode == 1096641454 && currentName.equals("allowedRatings")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("parentalControlsRestrictionsApply")) {
                                b = 1;
                            }
                            if (b == 0) {
                                builder.allowedRatings = currentToken == JsonToken.VALUE_NULL ? null : this.mratingsListParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field parentalControlsRestrictionsApply can't be null");
                                }
                                builder.parentalControlsRestrictionsApply = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlAllowedRatingsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.allowedRatings, this, "allowedRatings");
                    return builder.build();
                }
            }
        }
    }
}
