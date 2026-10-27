package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.NonRetryableCSSException;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RatingsNotFoundException extends NonRetryableCSSException {
    private RatingsNotFoundException(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.schedule.distribution.NonRetryableCSSException, com.amazon.atv.schedule.distribution.CSSException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RatingsNotFoundException)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.schedule.distribution.NonRetryableCSSException, com.amazon.atv.schedule.distribution.CSSException
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.schedule.distribution.NonRetryableCSSException, com.amazon.atv.schedule.distribution.CSSException
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends NonRetryableCSSException.Builder {
        @Override // com.amazon.atv.schedule.distribution.NonRetryableCSSException.Builder, com.amazon.atv.schedule.distribution.CSSException.Builder
        public RatingsNotFoundException build() {
            return new RatingsNotFoundException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RatingsNotFoundException> {
        private final SimpleParsers.StringParser mErrorMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mErrorMessageParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RatingsNotFoundException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RatingsNotFoundException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RatingsNotFoundException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RatingsNotFoundException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RatingsNotFoundException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RatingsNotFoundException");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 954925063 && next.equals("message")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.message = jsonNode2.isNull() ? null : this.mErrorMessageParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RatingsNotFoundException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RatingsNotFoundException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 954925063 && currentName.equals("message")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.message = currentToken == JsonToken.VALUE_NULL ? null : this.mErrorMessageParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RatingsNotFoundException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
