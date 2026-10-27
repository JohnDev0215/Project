package com.amazon.atvtransitiontimecodeservice;

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
public class TTSBatchRequest {
    public final Optional<String> encryptedMarketplaceId;
    public final Optional<ImmutableList<String>> vcids;

    private TTSBatchRequest(Builder builder) {
        this.encryptedMarketplaceId = Optional.fromNullable(builder.encryptedMarketplaceId);
        this.vcids = Optional.fromNullable(builder.vcids);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TTSBatchRequest)) {
            return false;
        }
        TTSBatchRequest tTSBatchRequest = (TTSBatchRequest) obj;
        return Objects.equal(this.encryptedMarketplaceId, tTSBatchRequest.encryptedMarketplaceId) && Objects.equal(this.vcids, tTSBatchRequest.vcids);
    }

    public int hashCode() {
        return Objects.hashCode(this.encryptedMarketplaceId, this.vcids);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("encryptedMarketplaceId", this.encryptedMarketplaceId).add("vcids", this.vcids).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String encryptedMarketplaceId;
        public ImmutableList<String> vcids;

        public TTSBatchRequest build() {
            return new TTSBatchRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TTSBatchRequest> {
        private final SimpleParsers.StringParser mencryptedMarketplaceIdParser;
        private final ListParser<String> mvcidListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mvcidListParser = ListParser.newParser(stringParser);
            this.mencryptedMarketplaceIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TTSBatchRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TTSBatchRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TTSBatchRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TTSBatchRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TTSBatchRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TTSBatchRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 112028907) {
                        if (iHashCode == 1096294754 && next.equals("encryptedMarketplaceId")) {
                            b = 0;
                        }
                    } else if (next.equals("vcids")) {
                        b = 1;
                    }
                    ImmutableList<String> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mencryptedMarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.encryptedMarketplaceId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mvcidListParser.parse(jsonNode2);
                        }
                        builder.vcids = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TTSBatchRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TTSBatchRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 112028907) {
                                if (iHashCode == 1096294754 && currentName.equals("encryptedMarketplaceId")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("vcids")) {
                                b = 1;
                            }
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mencryptedMarketplaceIdParser.parse(jsonParser);
                                }
                                builder.encryptedMarketplaceId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mvcidListParser.parse(jsonParser);
                                }
                                builder.vcids = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TTSBatchRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
