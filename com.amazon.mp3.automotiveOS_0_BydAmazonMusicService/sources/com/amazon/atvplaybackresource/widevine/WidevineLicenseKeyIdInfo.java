package com.amazon.atvplaybackresource.widevine;

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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseKeyIdInfo {
    public final ImmutableList<String> contentKeyIds;
    public final Optional<String> hdcpTestKeyId;

    private WidevineLicenseKeyIdInfo(Builder builder) {
        this.hdcpTestKeyId = Optional.fromNullable(builder.hdcpTestKeyId);
        this.contentKeyIds = (ImmutableList) Preconditions.checkNotNull(builder.contentKeyIds, "Unexpected null field: contentKeyIds");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineLicenseKeyIdInfo)) {
            return false;
        }
        WidevineLicenseKeyIdInfo widevineLicenseKeyIdInfo = (WidevineLicenseKeyIdInfo) obj;
        return Objects.equal(this.hdcpTestKeyId, widevineLicenseKeyIdInfo.hdcpTestKeyId) && Objects.equal(this.contentKeyIds, widevineLicenseKeyIdInfo.contentKeyIds);
    }

    public int hashCode() {
        return Objects.hashCode(this.hdcpTestKeyId, this.contentKeyIds);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("hdcpTestKeyId", this.hdcpTestKeyId).add("contentKeyIds", this.contentKeyIds).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> contentKeyIds;
        public String hdcpTestKeyId;

        public WidevineLicenseKeyIdInfo build() {
            return new WidevineLicenseKeyIdInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineLicenseKeyIdInfo> {
        private final ListParser<String> mWidevineKeyIdCollectionParser;
        private final SimpleParsers.StringParser mWidevineKeyIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mWidevineKeyIdCollectionParser = ListParser.newParser(stringParser);
            this.mWidevineKeyIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineLicenseKeyIdInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseKeyIdInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineLicenseKeyIdInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseKeyIdInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineLicenseKeyIdInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseKeyIdInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1960026625) {
                        if (iHashCode == -569044558 && next.equals("contentKeyIds")) {
                            b = 1;
                        }
                    } else if (next.equals("hdcpTestKeyId")) {
                        b = 0;
                    }
                    ImmutableList<String> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mWidevineKeyIdParser.parse(jsonNode2);
                        }
                        builder.hdcpTestKeyId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mWidevineKeyIdCollectionParser.parse(jsonNode2);
                        }
                        builder.contentKeyIds = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseKeyIdInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.contentKeyIds, this, "contentKeyIds");
            return builder.build();
        }

        @Nonnull
        private WidevineLicenseKeyIdInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1960026625) {
                                if (iHashCode == -569044558 && currentName.equals("contentKeyIds")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("hdcpTestKeyId")) {
                                b = 0;
                            }
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mWidevineKeyIdParser.parse(jsonParser);
                                }
                                builder.hdcpTestKeyId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mWidevineKeyIdCollectionParser.parse(jsonParser);
                                }
                                builder.contentKeyIds = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseKeyIdInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.contentKeyIds, this, "contentKeyIds");
                    return builder.build();
                }
            }
        }
    }
}
