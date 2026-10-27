package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.GetAssetsRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetAllAssetsRequest {
    public final Optional<GetAssetsRequest> getAssetsRequest;
    public final Optional<String> tenantId;

    private GetAllAssetsRequest(Builder builder) {
        this.getAssetsRequest = Optional.fromNullable(builder.getAssetsRequest);
        this.tenantId = Optional.fromNullable(builder.tenantId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAllAssetsRequest)) {
            return false;
        }
        GetAllAssetsRequest getAllAssetsRequest = (GetAllAssetsRequest) obj;
        return Objects.equal(this.getAssetsRequest, getAllAssetsRequest.getAssetsRequest) && Objects.equal(this.tenantId, getAllAssetsRequest.tenantId);
    }

    public int hashCode() {
        return Objects.hashCode(this.getAssetsRequest, this.tenantId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("getAssetsRequest", this.getAssetsRequest).add("tenantId", this.tenantId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public GetAssetsRequest getAssetsRequest;
        public String tenantId;

        public GetAllAssetsRequest build() {
            return new GetAllAssetsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAllAssetsRequest> {
        private final GetAssetsRequest.Parser mGetAssetsRequestParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetAssetsRequestParser = new GetAssetsRequest.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAllAssetsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAllAssetsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAllAssetsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAllAssetsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAllAssetsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAllAssetsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1306693787) {
                        if (iHashCode == 1396271574 && next.equals("getAssetsRequest")) {
                            b = 0;
                        }
                    } else if (next.equals("tenantId")) {
                        b = 1;
                    }
                    String str = null;
                    GetAssetsRequest getAssetsRequest = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            getAssetsRequest = this.mGetAssetsRequestParser.parse(jsonNode2);
                        }
                        builder.getAssetsRequest = getAssetsRequest;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.tenantId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAllAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAllAssetsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1306693787) {
                                if (iHashCode == 1396271574 && currentName.equals("getAssetsRequest")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("tenantId")) {
                                b = 1;
                            }
                            String str = null;
                            GetAssetsRequest getAssetsRequest = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getAssetsRequest = this.mGetAssetsRequestParser.parse(jsonParser);
                                }
                                builder.getAssetsRequest = getAssetsRequest;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.tenantId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAllAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
