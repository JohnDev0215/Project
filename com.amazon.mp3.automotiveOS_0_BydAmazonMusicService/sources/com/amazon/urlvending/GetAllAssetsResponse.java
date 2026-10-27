package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.DebStep;
import com.amazon.urlvending.GetAssetsResponseOrException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetAllAssetsResponse {
    public final Optional<ImmutableMap<String, ImmutableList<DebStep>>> debInfo;
    public final Optional<ImmutableList<GetAssetsResponseOrException>> getAllAssetsResponseList;

    private GetAllAssetsResponse(Builder builder) {
        this.debInfo = Optional.fromNullable(builder.debInfo);
        this.getAllAssetsResponseList = Optional.fromNullable(builder.getAllAssetsResponseList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAllAssetsResponse)) {
            return false;
        }
        GetAllAssetsResponse getAllAssetsResponse = (GetAllAssetsResponse) obj;
        return Objects.equal(this.debInfo, getAllAssetsResponse.debInfo) && Objects.equal(this.getAllAssetsResponseList, getAllAssetsResponse.getAllAssetsResponseList);
    }

    public int hashCode() {
        return Objects.hashCode(this.debInfo, this.getAllAssetsResponseList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("debInfo", this.debInfo).add("getAllAssetsResponseList", this.getAllAssetsResponseList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, ImmutableList<DebStep>> debInfo;
        public ImmutableList<GetAssetsResponseOrException> getAllAssetsResponseList;

        public GetAllAssetsResponse build() {
            return new GetAllAssetsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAllAssetsResponse> {
        private final MapParser<String, ImmutableList<DebStep>> mDebInfoMapParser;
        private final ListParser<GetAssetsResponseOrException> mGetAssetsResponseListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetAssetsResponseListParser = ListParser.newParser(new GetAssetsResponseOrException.Parser(objectMapper));
            this.mDebInfoMapParser = MapParser.newParser(SimpleParsers.StringParser.INSTANCE, ListParser.newParser(new DebStep.Parser(objectMapper)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAllAssetsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAllAssetsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAllAssetsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAllAssetsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAllAssetsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAllAssetsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 405081453) {
                        if (iHashCode == 1540387919 && next.equals("debInfo")) {
                            b = 0;
                        }
                    } else if (next.equals("getAllAssetsResponseList")) {
                        b = 1;
                    }
                    ImmutableList<GetAssetsResponseOrException> immutableList = null;
                    ImmutableMap<String, ImmutableList<DebStep>> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebInfoMapParser.parse(jsonNode2);
                        }
                        builder.debInfo = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mGetAssetsResponseListParser.parse(jsonNode2);
                        }
                        builder.getAllAssetsResponseList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAllAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAllAssetsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 405081453) {
                                if (iHashCode == 1540387919 && currentName.equals("debInfo")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("getAllAssetsResponseList")) {
                                b = 1;
                            }
                            ImmutableList<GetAssetsResponseOrException> immutableList = null;
                            ImmutableMap<String, ImmutableList<DebStep>> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebInfoMapParser.parse(jsonParser);
                                }
                                builder.debInfo = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mGetAssetsResponseListParser.parse(jsonParser);
                                }
                                builder.getAllAssetsResponseList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAllAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
