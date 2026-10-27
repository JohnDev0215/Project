package com.amazon.atv.cuepoint;

import com.amazon.atv.cuepoint.AssetMetadata;
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
public class GetRecommendedCuepointAssetsResponse {
    public final Optional<String> adSystem;
    public final Optional<ImmutableList<AssetMetadata>> assetMetadataList;

    private GetRecommendedCuepointAssetsResponse(Builder builder) {
        this.assetMetadataList = Optional.fromNullable(builder.assetMetadataList);
        this.adSystem = Optional.fromNullable(builder.adSystem);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetRecommendedCuepointAssetsResponse)) {
            return false;
        }
        GetRecommendedCuepointAssetsResponse getRecommendedCuepointAssetsResponse = (GetRecommendedCuepointAssetsResponse) obj;
        return Objects.equal(this.assetMetadataList, getRecommendedCuepointAssetsResponse.assetMetadataList) && Objects.equal(this.adSystem, getRecommendedCuepointAssetsResponse.adSystem);
    }

    public int hashCode() {
        return Objects.hashCode(this.assetMetadataList, this.adSystem);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("assetMetadataList", this.assetMetadataList).add("adSystem", this.adSystem).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String adSystem;
        public ImmutableList<AssetMetadata> assetMetadataList;

        public GetRecommendedCuepointAssetsResponse build() {
            return new GetRecommendedCuepointAssetsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetRecommendedCuepointAssetsResponse> {
        private final ListParser<AssetMetadata> mAssetMetadataListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAssetMetadataListParser = ListParser.newParser(new AssetMetadata.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetRecommendedCuepointAssetsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRecommendedCuepointAssetsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetRecommendedCuepointAssetsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetRecommendedCuepointAssetsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetRecommendedCuepointAssetsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetRecommendedCuepointAssetsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1698528206) {
                        if (iHashCode == -892221571 && next.equals("assetMetadataList")) {
                            b = 0;
                        }
                    } else if (next.equals("adSystem")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableList<AssetMetadata> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAssetMetadataListParser.parse(jsonNode2);
                        }
                        builder.assetMetadataList = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.adSystem = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetRecommendedCuepointAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetRecommendedCuepointAssetsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1698528206) {
                                if (iHashCode == -892221571 && currentName.equals("assetMetadataList")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("adSystem")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableList<AssetMetadata> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAssetMetadataListParser.parse(jsonParser);
                                }
                                builder.assetMetadataList = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.adSystem = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetRecommendedCuepointAssetsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
