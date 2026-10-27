package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.urlvending.SyeUrls;
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
public class GetSyeAssetsV2Response {
    public final Optional<SyeUrls> syeUrls;

    private GetSyeAssetsV2Response(Builder builder) {
        this.syeUrls = Optional.fromNullable(builder.syeUrls);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetSyeAssetsV2Response) {
            return Objects.equal(this.syeUrls, ((GetSyeAssetsV2Response) obj).syeUrls);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.syeUrls);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("syeUrls", this.syeUrls).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SyeUrls syeUrls;

        public GetSyeAssetsV2Response build() {
            return new GetSyeAssetsV2Response(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetSyeAssetsV2Response> {
        private final SyeUrls.Parser mSyeUrlsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeUrlsParser = new SyeUrls.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetSyeAssetsV2Response parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsV2Response:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetSyeAssetsV2Response parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsV2Response:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetSyeAssetsV2Response parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetSyeAssetsV2Response");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -1751210941 && next.equals("syeUrls")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.syeUrls = jsonNode2.isNull() ? null : this.mSyeUrlsParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetSyeAssetsV2Response so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetSyeAssetsV2Response parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -1751210941 && currentName.equals("syeUrls")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.syeUrls = currentToken == JsonToken.VALUE_NULL ? null : this.mSyeUrlsParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetSyeAssetsV2Response so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
