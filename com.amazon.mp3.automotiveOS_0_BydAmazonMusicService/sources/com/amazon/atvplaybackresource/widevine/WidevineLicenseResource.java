package com.amazon.atvplaybackresource.widevine;

import com.amazon.atvplaybackresource.widevine.WidevineLicenseMetadata;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseResource {
    public final ByteBuffer license;
    public final WidevineLicenseMetadata metadata;

    private WidevineLicenseResource(Builder builder) {
        this.metadata = (WidevineLicenseMetadata) Preconditions.checkNotNull(builder.metadata, "Unexpected null field: metadata");
        this.license = (ByteBuffer) Preconditions.checkNotNull(builder.license, "Unexpected null field: license");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineLicenseResource)) {
            return false;
        }
        WidevineLicenseResource widevineLicenseResource = (WidevineLicenseResource) obj;
        return Objects.equal(this.metadata, widevineLicenseResource.metadata) && Objects.equal(this.license, widevineLicenseResource.license);
    }

    public int hashCode() {
        return Objects.hashCode(this.metadata, this.license);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("metadata", this.metadata).add("license", this.license).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ByteBuffer license;
        public WidevineLicenseMetadata metadata;

        public WidevineLicenseResource build() {
            return new WidevineLicenseResource(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineLicenseResource> {
        private final WidevineLicenseMetadata.Parser mWidevineLicenseMetadataParser;
        private final SimpleParsers.BlobParser mWidevineLicenseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidevineLicenseMetadataParser = new WidevineLicenseMetadata.Parser(objectMapper);
            this.mWidevineLicenseParser = SimpleParsers.BlobParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineLicenseResource parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseResource:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineLicenseResource parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineLicenseResource:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineLicenseResource parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseResource");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -450004177) {
                        if (iHashCode == 166757441 && next.equals("license")) {
                            b = 1;
                        }
                    } else if (next.equals("metadata")) {
                        b = 0;
                    }
                    ByteBuffer byteBuffer = null;
                    WidevineLicenseMetadata widevineLicenseMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseMetadata = this.mWidevineLicenseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = widevineLicenseMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mWidevineLicenseParser.parse(jsonNode2);
                        }
                        builder.license = byteBuffer;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseResource so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.metadata, this, "metadata");
            JsonParsingUtils.checkNotNull(builder.license, this, "license");
            return builder.build();
        }

        @Nonnull
        private WidevineLicenseResource parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -450004177) {
                                if (iHashCode == 166757441 && currentName.equals("license")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("metadata")) {
                                b = 0;
                            }
                            ByteBuffer byteBuffer = null;
                            WidevineLicenseMetadata widevineLicenseMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    widevineLicenseMetadata = this.mWidevineLicenseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = widevineLicenseMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    byteBuffer = this.mWidevineLicenseParser.parse(jsonParser);
                                }
                                builder.license = byteBuffer;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseResource so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.metadata, this, "metadata");
                    JsonParsingUtils.checkNotNull(builder.license, this, "license");
                    return builder.build();
                }
            }
        }
    }
}
