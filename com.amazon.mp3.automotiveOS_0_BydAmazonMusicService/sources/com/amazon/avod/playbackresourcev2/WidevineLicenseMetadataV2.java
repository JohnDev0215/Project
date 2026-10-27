package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackresource.widevine.WidevineKeyMetadata;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseMetadataV2 {
    private final ImmutableList<WidevineKeyMetadata> mKeyMetadata;

    private WidevineLicenseMetadataV2(Builder builder) {
        this.mKeyMetadata = (ImmutableList) Preconditions.checkNotNull(builder.mKeyMetadata, "Unexpected null field: keyMetadata");
    }

    @Nonnull
    public ImmutableList<WidevineKeyMetadata> getKeyMetadata() {
        return this.mKeyMetadata;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("keyMetadata", this.mKeyMetadata).toString();
    }

    static class Builder {
        private ImmutableList<WidevineKeyMetadata> mKeyMetadata;

        Builder() {
        }

        public WidevineLicenseMetadataV2 build() {
            return new WidevineLicenseMetadataV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<WidevineLicenseMetadataV2> {
        private final ListParser<WidevineKeyMetadata> mWidevineKeyMetadataListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, WidevineLicenseMetadataV2.class);
            this.mWidevineKeyMetadataListParser = ListParser.newParser(new WidevineKeyMetadata.Parser(objectMapper));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public WidevineLicenseMetadataV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineLicenseMetadataV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -1712279890 && next.equals("keyMetadata")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.mKeyMetadata = jsonNode2.isNull() ? null : this.mWidevineKeyMetadataListParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidevineLicenseMetadataV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.mKeyMetadata, this, "keyMetadata");
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public WidevineLicenseMetadataV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    JsonToken currentToken = jsonParser.getCurrentToken();
                    byte b = -1;
                    try {
                        if (currentName.hashCode() == -1712279890 && currentName.equals("keyMetadata")) {
                            b = 0;
                        }
                        if (b == 0) {
                            builder.mKeyMetadata = currentToken == JsonToken.VALUE_NULL ? null : this.mWidevineKeyMetadataListParser.parse(jsonParser);
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidevineLicenseMetadataV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            JsonParsingUtils.checkNotNull(builder.mKeyMetadata, this, "keyMetadata");
            return builder.build();
        }
    }
}
