package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.AiringRating;
import com.amazon.atv.schedule.distribution.Error;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
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
public class GetRatingsForLinearAiringsResponse {
    public final ImmutableList<AiringRating> airingRatings;
    public final ImmutableList<Error> errors;

    private GetRatingsForLinearAiringsResponse(Builder builder) {
        this.errors = (ImmutableList) Preconditions.checkNotNull(builder.errors, "Unexpected null field: errors");
        this.airingRatings = (ImmutableList) Preconditions.checkNotNull(builder.airingRatings, "Unexpected null field: airingRatings");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetRatingsForLinearAiringsResponse)) {
            return false;
        }
        GetRatingsForLinearAiringsResponse getRatingsForLinearAiringsResponse = (GetRatingsForLinearAiringsResponse) obj;
        return Objects.equal(this.errors, getRatingsForLinearAiringsResponse.errors) && Objects.equal(this.airingRatings, getRatingsForLinearAiringsResponse.airingRatings);
    }

    public int hashCode() {
        return Objects.hashCode(this.errors, this.airingRatings);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(MAPWebViewEventHelper.KEY_ERRORS, this.errors).add("airingRatings", this.airingRatings).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AiringRating> airingRatings;
        public ImmutableList<Error> errors;

        public GetRatingsForLinearAiringsResponse build() {
            return new GetRatingsForLinearAiringsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetRatingsForLinearAiringsResponse> {
        private final ListParser<AiringRating> mAiringRatingListParser;
        private final ListParser<Error> mErrorListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mErrorListParser = ListParser.newParser(new Error.Parser(objectMapper));
            this.mAiringRatingListParser = ListParser.newParser(new AiringRating.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetRatingsForLinearAiringsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRatingsForLinearAiringsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetRatingsForLinearAiringsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRatingsForLinearAiringsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetRatingsForLinearAiringsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetRatingsForLinearAiringsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1294635157) {
                        if (iHashCode == 642983166 && next.equals("airingRatings")) {
                            b = 1;
                        }
                    } else if (next.equals(MAPWebViewEventHelper.KEY_ERRORS)) {
                        b = 0;
                    }
                    ImmutableList<AiringRating> immutableList = null;
                    ImmutableList<Error> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mErrorListParser.parse(jsonNode2);
                        }
                        builder.errors = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAiringRatingListParser.parse(jsonNode2);
                        }
                        builder.airingRatings = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetRatingsForLinearAiringsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.errors, this, MAPWebViewEventHelper.KEY_ERRORS);
            JsonParsingUtils.checkNotNull(builder.airingRatings, this, "airingRatings");
            return builder.build();
        }

        @Nonnull
        private GetRatingsForLinearAiringsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1294635157) {
                                if (iHashCode == 642983166 && currentName.equals("airingRatings")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(MAPWebViewEventHelper.KEY_ERRORS)) {
                                b = 0;
                            }
                            ImmutableList<AiringRating> immutableList = null;
                            ImmutableList<Error> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mErrorListParser.parse(jsonParser);
                                }
                                builder.errors = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAiringRatingListParser.parse(jsonParser);
                                }
                                builder.airingRatings = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetRatingsForLinearAiringsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.errors, this, MAPWebViewEventHelper.KEY_ERRORS);
                    JsonParsingUtils.checkNotNull(builder.airingRatings, this, "airingRatings");
                    return builder.build();
                }
            }
        }
    }
}
