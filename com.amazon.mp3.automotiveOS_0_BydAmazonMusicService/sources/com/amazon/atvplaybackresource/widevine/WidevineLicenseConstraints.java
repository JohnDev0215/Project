package com.amazon.atvplaybackresource.widevine;

import com.amazon.atvplaybackresource.widevine.WidevineLicenseHdcpConstraint;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class WidevineLicenseConstraints {
    public final Optional<WidevineLicenseHdcpConstraint> hdcp;

    private WidevineLicenseConstraints(Builder builder) {
        this.hdcp = Optional.fromNullable(builder.hdcp);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WidevineLicenseConstraints) {
            return Objects.equal(this.hdcp, ((WidevineLicenseConstraints) obj).hdcp);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.hdcp);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("hdcp", this.hdcp).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public WidevineLicenseHdcpConstraint hdcp;

        public WidevineLicenseConstraints build() {
            return new WidevineLicenseConstraints(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineLicenseConstraints> {
        private final WidevineLicenseHdcpConstraint.Parser mWidevineLicenseHdcpConstraintParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidevineLicenseHdcpConstraintParser = new WidevineLicenseHdcpConstraint.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineLicenseConstraints parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseConstraints:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineLicenseConstraints parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseConstraints:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineLicenseConstraints parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseConstraints");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 3197545 && next.equals("hdcp")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.hdcp = jsonNode2.isNull() ? null : this.mWidevineLicenseHdcpConstraintParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseConstraints so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private WidevineLicenseConstraints parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 3197545 && currentName.equals("hdcp")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.hdcp = currentToken == JsonToken.VALUE_NULL ? null : this.mWidevineLicenseHdcpConstraintParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseConstraints so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
