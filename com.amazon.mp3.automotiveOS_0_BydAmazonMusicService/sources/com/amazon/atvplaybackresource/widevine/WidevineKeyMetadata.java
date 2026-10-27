package com.amazon.atvplaybackresource.widevine;

import com.amazon.atvplaybackresource.widevine.WidevineLicenseHdcpConstraint;
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
public class WidevineKeyMetadata {
    public final Optional<WidevineLicenseHdcpConstraint> hdcp;
    public final String id;
    public final WidevineKeyType keyType;

    private WidevineKeyMetadata(Builder builder) {
        this.hdcp = Optional.fromNullable(builder.hdcp);
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.keyType = (WidevineKeyType) Preconditions.checkNotNull(builder.keyType, "Unexpected null field: keyType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineKeyMetadata)) {
            return false;
        }
        WidevineKeyMetadata widevineKeyMetadata = (WidevineKeyMetadata) obj;
        return Objects.equal(this.hdcp, widevineKeyMetadata.hdcp) && Objects.equal(this.id, widevineKeyMetadata.id) && Objects.equal(this.keyType, widevineKeyMetadata.keyType);
    }

    public int hashCode() {
        return Objects.hashCode(this.hdcp, this.id, this.keyType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("hdcp", this.hdcp).add("id", this.id).add("keyType", this.keyType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public WidevineLicenseHdcpConstraint hdcp;
        public String id;
        public WidevineKeyType keyType;

        public WidevineKeyMetadata build() {
            return new WidevineKeyMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineKeyMetadata> {
        private final SimpleParsers.StringParser mWidevineKeyIdParser;
        private final EnumParser<WidevineKeyType> mWidevineKeyTypeParser;
        private final WidevineLicenseHdcpConstraint.Parser mWidevineLicenseHdcpConstraintParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidevineLicenseHdcpConstraintParser = new WidevineLicenseHdcpConstraint.Parser(objectMapper);
            this.mWidevineKeyIdParser = SimpleParsers.StringParser.INSTANCE;
            this.mWidevineKeyTypeParser = EnumParser.newParser(WidevineKeyType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineKeyMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineKeyMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineKeyMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineKeyMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineKeyMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineKeyMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -815441351) {
                        if (iHashCode != 3355) {
                            if (iHashCode == 3197545 && next.equals("hdcp")) {
                                b = 0;
                            }
                        } else if (next.equals("id")) {
                            b = 1;
                        }
                    } else if (next.equals("keyType")) {
                        b = 2;
                    }
                    WidevineKeyType widevineKeyType = null;
                    WidevineLicenseHdcpConstraint widevineLicenseHdcpConstraint = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseHdcpConstraint = this.mWidevineLicenseHdcpConstraintParser.parse(jsonNode2);
                        }
                        builder.hdcp = widevineLicenseHdcpConstraint;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mWidevineKeyIdParser.parse(jsonNode2);
                        }
                        builder.id = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            widevineKeyType = (WidevineKeyType) this.mWidevineKeyTypeParser.parse(jsonNode2);
                        }
                        builder.keyType = widevineKeyType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineKeyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.keyType, this, "keyType");
            return builder.build();
        }

        @Nonnull
        private WidevineKeyMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -815441351) {
                                if (iHashCode != 3355) {
                                    if (iHashCode == 3197545 && currentName.equals("hdcp")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("id")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("keyType")) {
                                b = 2;
                            }
                            WidevineKeyType widevineKeyType = null;
                            WidevineLicenseHdcpConstraint widevineLicenseHdcpConstraint = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widevineLicenseHdcpConstraint = this.mWidevineLicenseHdcpConstraintParser.parse(jsonParser);
                                }
                                builder.hdcp = widevineLicenseHdcpConstraint;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mWidevineKeyIdParser.parse(jsonParser);
                                }
                                builder.id = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widevineKeyType = (WidevineKeyType) this.mWidevineKeyTypeParser.parse(jsonParser);
                                }
                                builder.keyType = widevineKeyType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineKeyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.keyType, this, "keyType");
                    return builder.build();
                }
            }
        }
    }
}
