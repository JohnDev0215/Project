package com.amazon.atvplaybackresource.widevine;

import com.amazon.atvplaybackresource.widevine.WidevineKeyMetadata;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseConstraints;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseKeyIdInfo;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseMetadata {

    @Deprecated
    public final Optional<WidevineLicenseConstraints> constraints;

    @Deprecated
    public final WidevineLicenseKeyIdInfo keyIds;
    public final ImmutableList<WidevineKeyMetadata> keyMetadata;

    private WidevineLicenseMetadata(Builder builder) {
        this.constraints = Optional.fromNullable(builder.constraints);
        this.keyIds = (WidevineLicenseKeyIdInfo) Preconditions.checkNotNull(builder.keyIds, "Unexpected null field: keyIds");
        this.keyMetadata = (ImmutableList) Preconditions.checkNotNull(builder.keyMetadata, "Unexpected null field: keyMetadata");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineLicenseMetadata)) {
            return false;
        }
        WidevineLicenseMetadata widevineLicenseMetadata = (WidevineLicenseMetadata) obj;
        return Objects.equal(this.constraints, widevineLicenseMetadata.constraints) && Objects.equal(this.keyIds, widevineLicenseMetadata.keyIds) && Objects.equal(this.keyMetadata, widevineLicenseMetadata.keyMetadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.constraints, this.keyIds, this.keyMetadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("constraints", this.constraints).add("keyIds", this.keyIds).add("keyMetadata", this.keyMetadata).toString();
    }

    @NotThreadSafe
    public static class Builder {

        @Deprecated
        public WidevineLicenseConstraints constraints;

        @Deprecated
        public WidevineLicenseKeyIdInfo keyIds;
        public ImmutableList<WidevineKeyMetadata> keyMetadata;

        public WidevineLicenseMetadata build() {
            return new WidevineLicenseMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineLicenseMetadata> {
        private final ListParser<WidevineKeyMetadata> mWidevineKeyMetadataListParser;
        private final WidevineLicenseConstraints.Parser mWidevineLicenseConstraintsParser;
        private final WidevineLicenseKeyIdInfo.Parser mWidevineLicenseKeyIdInfoParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidevineLicenseConstraintsParser = new WidevineLicenseConstraints.Parser(objectMapper);
            this.mWidevineKeyMetadataListParser = ListParser.newParser(new WidevineKeyMetadata.Parser(objectMapper));
            this.mWidevineLicenseKeyIdInfoParser = new WidevineLicenseKeyIdInfo.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineLicenseMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineLicenseMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineLicenseMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1712279890) {
                        if (iHashCode != -1606703562) {
                            if (iHashCode == -1134694439 && next.equals("keyIds")) {
                                b = 1;
                            }
                        } else if (next.equals("constraints")) {
                            b = 0;
                        }
                    } else if (next.equals("keyMetadata")) {
                        b = 2;
                    }
                    ImmutableList<WidevineKeyMetadata> immutableList = null;
                    WidevineLicenseConstraints widevineLicenseConstraints = null;
                    WidevineLicenseKeyIdInfo widevineLicenseKeyIdInfo = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseConstraints = this.mWidevineLicenseConstraintsParser.parse(jsonNode2);
                        }
                        builder.constraints = widevineLicenseConstraints;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseKeyIdInfo = this.mWidevineLicenseKeyIdInfoParser.parse(jsonNode2);
                        }
                        builder.keyIds = widevineLicenseKeyIdInfo;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mWidevineKeyMetadataListParser.parse(jsonNode2);
                        }
                        builder.keyMetadata = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.keyIds, this, "keyIds");
            JsonParsingUtils.checkNotNull(builder.keyMetadata, this, "keyMetadata");
            return builder.build();
        }

        @Nonnull
        private WidevineLicenseMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1712279890) {
                                if (iHashCode != -1606703562) {
                                    if (iHashCode == -1134694439 && currentName.equals("keyIds")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("constraints")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("keyMetadata")) {
                                b = 2;
                            }
                            ImmutableList<WidevineKeyMetadata> immutableList = null;
                            WidevineLicenseConstraints widevineLicenseConstraints = null;
                            WidevineLicenseKeyIdInfo widevineLicenseKeyIdInfo = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widevineLicenseConstraints = this.mWidevineLicenseConstraintsParser.parse(jsonParser);
                                }
                                builder.constraints = widevineLicenseConstraints;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widevineLicenseKeyIdInfo = this.mWidevineLicenseKeyIdInfoParser.parse(jsonParser);
                                }
                                builder.keyIds = widevineLicenseKeyIdInfo;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mWidevineKeyMetadataListParser.parse(jsonParser);
                                }
                                builder.keyMetadata = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.keyIds, this, "keyIds");
                    JsonParsingUtils.checkNotNull(builder.keyMetadata, this, "keyMetadata");
                    return builder.build();
                }
            }
        }
    }
}
