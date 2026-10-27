package com.amazon.atv.purchase;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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

/* JADX INFO: loaded from: classes2.dex */
public class CdpPurchaseStreamingURLInfoSet {
    public final Optional<String> sessionId;

    private CdpPurchaseStreamingURLInfoSet(Builder builder) {
        this.sessionId = Optional.fromNullable(builder.sessionId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CdpPurchaseStreamingURLInfoSet) {
            return Objects.equal(this.sessionId, ((CdpPurchaseStreamingURLInfoSet) obj).sessionId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.sessionId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_SESSION_ID, this.sessionId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String sessionId;

        public CdpPurchaseStreamingURLInfoSet build() {
            return new CdpPurchaseStreamingURLInfoSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdpPurchaseStreamingURLInfoSet> {
        private final SimpleParsers.StringParser msessionIDParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.msessionIDParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdpPurchaseStreamingURLInfoSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseStreamingURLInfoSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdpPurchaseStreamingURLInfoSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseStreamingURLInfoSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CdpPurchaseStreamingURLInfoSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdpPurchaseStreamingURLInfoSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 607796817 && next.equals(Splash.PARAMS_SESSION_ID)) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.sessionId = jsonNode2.isNull() ? null : this.msessionIDParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpPurchaseStreamingURLInfoSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CdpPurchaseStreamingURLInfoSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 607796817 && currentName.equals(Splash.PARAMS_SESSION_ID)) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.sessionId = currentToken == JsonToken.VALUE_NULL ? null : this.msessionIDParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpPurchaseStreamingURLInfoSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
