package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.TitleActionsDataResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetTitleActionsDataResponse {
    public final Optional<String> diagnostics;
    public final Optional<ImmutableMap<String, TitleActionsDataResponse>> response;

    private GetTitleActionsDataResponse(Builder builder) {
        this.response = Optional.fromNullable(builder.response);
        this.diagnostics = Optional.fromNullable(builder.diagnostics);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTitleActionsDataResponse)) {
            return false;
        }
        GetTitleActionsDataResponse getTitleActionsDataResponse = (GetTitleActionsDataResponse) obj;
        return Objects.equal(this.response, getTitleActionsDataResponse.response) && Objects.equal(this.diagnostics, getTitleActionsDataResponse.diagnostics);
    }

    public int hashCode() {
        return Objects.hashCode(this.response, this.diagnostics);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("response", this.response).add("diagnostics", this.diagnostics).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String diagnostics;
        public ImmutableMap<String, TitleActionsDataResponse> response;

        public GetTitleActionsDataResponse build() {
            return new GetTitleActionsDataResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTitleActionsDataResponse> {
        private final MapParser<String, TitleActionsDataResponse> mIdentifierToTitleActionsResponseMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIdentifierToTitleActionsResponseMapParser = MapParser.newParser(stringParser, new TitleActionsDataResponse.Parser(objectMapper));
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTitleActionsDataResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsDataResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTitleActionsDataResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleActionsDataResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetTitleActionsDataResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTitleActionsDataResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -740386388) {
                        if (iHashCode == -340323263 && next.equals("response")) {
                            b = 0;
                        }
                    } else if (next.equals("diagnostics")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableMap<String, TitleActionsDataResponse> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mIdentifierToTitleActionsResponseMapParser.parse(jsonNode2);
                        }
                        builder.response = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.diagnostics = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleActionsDataResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetTitleActionsDataResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -740386388) {
                                if (iHashCode == -340323263 && currentName.equals("response")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("diagnostics")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableMap<String, TitleActionsDataResponse> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mIdentifierToTitleActionsResponseMapParser.parse(jsonParser);
                                }
                                builder.response = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.diagnostics = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleActionsDataResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
