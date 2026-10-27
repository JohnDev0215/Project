package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.urlvending.GetAssetsResponse;
import com.amazon.urlvending.UrlVendingException;
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
public class GetAssetsResponseOrException {
    public final Optional<UrlVendingException> error;
    public final Optional<GetAssetsResponse> getAssetsResponse;

    private GetAssetsResponseOrException(Builder builder) {
        this.error = Optional.fromNullable(builder.error);
        this.getAssetsResponse = Optional.fromNullable(builder.getAssetsResponse);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAssetsResponseOrException)) {
            return false;
        }
        GetAssetsResponseOrException getAssetsResponseOrException = (GetAssetsResponseOrException) obj;
        return Objects.equal(this.error, getAssetsResponseOrException.error) && Objects.equal(this.getAssetsResponse, getAssetsResponseOrException.getAssetsResponse);
    }

    public int hashCode() {
        return Objects.hashCode(this.error, this.getAssetsResponse);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("error", this.error).add("getAssetsResponse", this.getAssetsResponse).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public UrlVendingException error;
        public GetAssetsResponse getAssetsResponse;

        public GetAssetsResponseOrException build() {
            return new GetAssetsResponseOrException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAssetsResponseOrException> {
        private final GetAssetsResponse.Parser mGetAssetsResponseParser;
        private final UrlVendingException.Parser mUrlVendingExceptionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetAssetsResponseParser = new GetAssetsResponse.Parser(objectMapper);
            this.mUrlVendingExceptionParser = new UrlVendingException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAssetsResponseOrException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsResponseOrException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAssetsResponseOrException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsResponseOrException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAssetsResponseOrException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAssetsResponseOrException");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 96784904) {
                        if (iHashCode == 387679706 && next.equals("getAssetsResponse")) {
                            b = 1;
                        }
                    } else if (next.equals("error")) {
                        b = 0;
                    }
                    GetAssetsResponse getAssetsResponse = null;
                    UrlVendingException urlVendingException = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            urlVendingException = this.mUrlVendingExceptionParser.parse(jsonNode2);
                        }
                        builder.error = urlVendingException;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            getAssetsResponse = this.mGetAssetsResponseParser.parse(jsonNode2);
                        }
                        builder.getAssetsResponse = getAssetsResponse;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAssetsResponseOrException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAssetsResponseOrException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 96784904) {
                                if (iHashCode == 387679706 && currentName.equals("getAssetsResponse")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("error")) {
                                b = 0;
                            }
                            GetAssetsResponse getAssetsResponse = null;
                            UrlVendingException urlVendingException = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    urlVendingException = this.mUrlVendingExceptionParser.parse(jsonParser);
                                }
                                builder.error = urlVendingException;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getAssetsResponse = this.mGetAssetsResponseParser.parse(jsonParser);
                                }
                                builder.getAssetsResponse = getAssetsResponse;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAssetsResponseOrException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
