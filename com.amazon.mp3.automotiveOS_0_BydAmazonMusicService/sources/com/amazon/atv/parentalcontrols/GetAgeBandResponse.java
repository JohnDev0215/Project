package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.AgeBandResponse;
import com.amazon.atv.parentalcontrols.AgeBandWithLocalisedMetadata;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetAgeBandResponse extends AgeBandResponse {
    public final Optional<ImmutableList<AgeBandWithLocalisedMetadata>> availableAgeBands;

    private GetAgeBandResponse(Builder builder) {
        super(builder);
        this.availableAgeBands = Optional.fromNullable(builder.availableAgeBands);
    }

    @Override // com.amazon.atv.parentalcontrols.AgeBandResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetAgeBandResponse) {
            return super.equals(obj) && Objects.equal(this.availableAgeBands, ((GetAgeBandResponse) obj).availableAgeBands);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.AgeBandResponse
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.availableAgeBands);
    }

    @Override // com.amazon.atv.parentalcontrols.AgeBandResponse
    public String toString() {
        return MoreObjects.toStringHelper(this).add("availableAgeBands", this.availableAgeBands).toString();
    }

    @NotThreadSafe
    public static class Builder extends AgeBandResponse.Builder {
        public ImmutableList<AgeBandWithLocalisedMetadata> availableAgeBands;

        public GetAgeBandResponse build() {
            return new GetAgeBandResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAgeBandResponse> {
        private final SimpleParsers.StringParser mageBandIdentifierParser;
        private final ListParser<AgeBandWithLocalisedMetadata> mavailableAgeBandsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mavailableAgeBandsParser = ListParser.newParser(new AgeBandWithLocalisedMetadata.Parser(objectMapper));
            this.mageBandIdentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAgeBandResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAgeBandResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAgeBandResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAgeBandResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAgeBandResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAgeBandResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1446365944) {
                        if (iHashCode == 2053218798 && next.equals("activeAgeBand")) {
                            b = 1;
                        }
                    } else if (next.equals("availableAgeBands")) {
                        b = 0;
                    }
                    String str = null;
                    ImmutableList<AgeBandWithLocalisedMetadata> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mavailableAgeBandsParser.parse(jsonNode2);
                        }
                        builder.availableAgeBands = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mageBandIdentifierParser.parse(jsonNode2);
                        }
                        builder.activeAgeBand = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAgeBandResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAgeBandResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1446365944) {
                                if (iHashCode == 2053218798 && currentName.equals("activeAgeBand")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("availableAgeBands")) {
                                b = 0;
                            }
                            String str = null;
                            ImmutableList<AgeBandWithLocalisedMetadata> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mavailableAgeBandsParser.parse(jsonParser);
                                }
                                builder.availableAgeBands = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mageBandIdentifierParser.parse(jsonParser);
                                }
                                builder.activeAgeBand = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAgeBandResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
