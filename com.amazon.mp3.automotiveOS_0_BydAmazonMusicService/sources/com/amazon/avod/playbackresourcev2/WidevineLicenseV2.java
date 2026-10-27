package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresourcev2.WidevineLicenseMetadataV2;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseV2 {
    private final ByteBuffer mLicense;
    private final WidevineLicenseMetadataV2 mMetadata;

    private WidevineLicenseV2(Builder builder) {
        this.mMetadata = (WidevineLicenseMetadataV2) Preconditions.checkNotNull(builder.mMetadata, "Unexpected null field: metadata");
        this.mLicense = (ByteBuffer) Preconditions.checkNotNull(builder.mLicense, "Unexpected null field: license");
    }

    @Nonnull
    public WidevineLicenseMetadataV2 getMetadata() {
        return this.mMetadata;
    }

    @Nonnull
    public ByteBuffer getLicense() {
        return this.mLicense;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("license", this.mLicense).add("metadata", this.mMetadata).toString();
    }

    static class Builder {
        private ByteBuffer mLicense;
        private WidevineLicenseMetadataV2 mMetadata;

        Builder() {
        }

        public WidevineLicenseV2 build() {
            return new WidevineLicenseV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<WidevineLicenseV2> {
        private final WidevineLicenseMetadataV2.Parser mWidevineLicenseMetadataParser;
        private final SimpleParsers.BlobParser mWidevineLicenseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, WidevineLicenseV2.class);
            this.mWidevineLicenseMetadataParser = new WidevineLicenseMetadataV2.Parser(objectMapper);
            this.mWidevineLicenseParser = SimpleParsers.BlobParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public WidevineLicenseV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
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
                    WidevineLicenseMetadataV2 widevineLicenseMetadataV2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseMetadataV2 = this.mWidevineLicenseMetadataParser.parse(jsonNode2);
                        }
                        builder.mMetadata = widevineLicenseMetadataV2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            byteBuffer = this.mWidevineLicenseParser.parse(jsonNode2);
                        }
                        builder.mLicense = byteBuffer;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseResource so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.mMetadata, this, "metadata");
            JsonParsingUtils.checkNotNull(builder.mLicense, this, "license");
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public WidevineLicenseV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (!JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    JsonParsingUtils.checkNotNull(builder.mMetadata, this, "metadata");
                    JsonParsingUtils.checkNotNull(builder.mLicense, this, "license");
                    return builder.build();
                }
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
                        WidevineLicenseMetadataV2 widevineLicenseMetadataV2 = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                widevineLicenseMetadataV2 = this.mWidevineLicenseMetadataParser.parse(jsonParser);
                            }
                            builder.mMetadata = widevineLicenseMetadataV2;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                byteBuffer = this.mWidevineLicenseParser.parse(jsonParser);
                            }
                            builder.mLicense = byteBuffer;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseResource so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
            }
        }
    }
}
