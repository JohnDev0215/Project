package com.amazon.atv.cuepoint;

import com.amazon.atv.cuepoint.CuepointFulfillmentAsset;
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
public class CuepointFulfillmentMetadata {
    public final Optional<ImmutableList<CuepointFulfillmentAsset>> cuepointFulfillmentAssetList;

    private CuepointFulfillmentMetadata(Builder builder) {
        this.cuepointFulfillmentAssetList = Optional.fromNullable(builder.cuepointFulfillmentAssetList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CuepointFulfillmentMetadata) {
            return Objects.equal(this.cuepointFulfillmentAssetList, ((CuepointFulfillmentMetadata) obj).cuepointFulfillmentAssetList);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.cuepointFulfillmentAssetList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cuepointFulfillmentAssetList", this.cuepointFulfillmentAssetList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<CuepointFulfillmentAsset> cuepointFulfillmentAssetList;

        public CuepointFulfillmentMetadata build() {
            return new CuepointFulfillmentMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CuepointFulfillmentMetadata> {
        private final ListParser<CuepointFulfillmentAsset> mCuepointFulfillmentAssetListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCuepointFulfillmentAssetListParser = ListParser.newParser(new CuepointFulfillmentAsset.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CuepointFulfillmentMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointFulfillmentMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CuepointFulfillmentMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointFulfillmentMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CuepointFulfillmentMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CuepointFulfillmentMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 1041993293 && next.equals("cuepointFulfillmentAssetList")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.cuepointFulfillmentAssetList = jsonNode2.isNull() ? null : this.mCuepointFulfillmentAssetListParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CuepointFulfillmentMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CuepointFulfillmentMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 1041993293 && currentName.equals("cuepointFulfillmentAssetList")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.cuepointFulfillmentAssetList = currentToken == JsonToken.VALUE_NULL ? null : this.mCuepointFulfillmentAssetListParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CuepointFulfillmentMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
