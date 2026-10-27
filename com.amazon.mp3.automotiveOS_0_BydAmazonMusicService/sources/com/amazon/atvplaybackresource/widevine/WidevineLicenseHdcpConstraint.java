package com.amazon.atvplaybackresource.widevine;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseHdcpConstraint {
    public final Hdcp hdcp;
    public final Optional<Integer> hdcpEnforcementResolutionPixels;

    private WidevineLicenseHdcpConstraint(Builder builder) {
        this.hdcp = (Hdcp) Preconditions.checkNotNull(builder.hdcp, "Unexpected null field: hdcp");
        this.hdcpEnforcementResolutionPixels = Optional.fromNullable(builder.hdcpEnforcementResolutionPixels);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineLicenseHdcpConstraint)) {
            return false;
        }
        WidevineLicenseHdcpConstraint widevineLicenseHdcpConstraint = (WidevineLicenseHdcpConstraint) obj;
        return Objects.equal(this.hdcp, widevineLicenseHdcpConstraint.hdcp) && Objects.equal(this.hdcpEnforcementResolutionPixels, widevineLicenseHdcpConstraint.hdcpEnforcementResolutionPixels);
    }

    public int hashCode() {
        return Objects.hashCode(this.hdcp, this.hdcpEnforcementResolutionPixels);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("hdcp", this.hdcp).add("hdcpEnforcementResolutionPixels", this.hdcpEnforcementResolutionPixels).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Hdcp hdcp;
        public Integer hdcpEnforcementResolutionPixels;

        public WidevineLicenseHdcpConstraint build() {
            return new WidevineLicenseHdcpConstraint(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineLicenseHdcpConstraint> {
        private final SimpleParsers.IntegerParser mHdcpEnforcementResolutionPixelsParser;
        private final EnumParser<Hdcp> mHdcpParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mHdcpEnforcementResolutionPixelsParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mHdcpParser = EnumParser.newParser(Hdcp.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineLicenseHdcpConstraint parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseHdcpConstraint:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineLicenseHdcpConstraint parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseHdcpConstraint:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineLicenseHdcpConstraint parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseHdcpConstraint");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -871256400) {
                        if (iHashCode == 3197545 && next.equals("hdcp")) {
                            b = 0;
                        }
                    } else if (next.equals("hdcpEnforcementResolutionPixels")) {
                        b = 1;
                    }
                    Integer num = null;
                    Hdcp hdcp = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            hdcp = (Hdcp) this.mHdcpParser.parse(jsonNode2);
                        }
                        builder.hdcp = hdcp;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            num = this.mHdcpEnforcementResolutionPixelsParser.parse(jsonNode2);
                        }
                        builder.hdcpEnforcementResolutionPixels = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseHdcpConstraint so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.hdcp, this, "hdcp");
            return builder.build();
        }

        @Nonnull
        private WidevineLicenseHdcpConstraint parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -871256400) {
                                if (iHashCode == 3197545 && currentName.equals("hdcp")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("hdcpEnforcementResolutionPixels")) {
                                b = 1;
                            }
                            Integer num = null;
                            Hdcp hdcp = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    hdcp = (Hdcp) this.mHdcpParser.parse(jsonParser);
                                }
                                builder.hdcp = hdcp;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mHdcpEnforcementResolutionPixelsParser.parse(jsonParser);
                                }
                                builder.hdcpEnforcementResolutionPixels = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseHdcpConstraint so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.hdcp, this, "hdcp");
                    return builder.build();
                }
            }
        }
    }
}
