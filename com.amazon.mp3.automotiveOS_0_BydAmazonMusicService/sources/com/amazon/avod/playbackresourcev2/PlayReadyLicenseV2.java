package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackresource.playready.PlayReadyResponseMetadata;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseV2 {
    private final Optional<String> mEncodedLicenseResponse;
    private final Optional<PlayReadyResponseMetadata> mMetadata;

    private PlayReadyLicenseV2(Builder builder) {
        this.mMetadata = Optional.fromNullable(builder.metadata);
        this.mEncodedLicenseResponse = Optional.fromNullable(builder.encodedLicenseResponse);
    }

    @Nonnull
    public Optional<String> getEncodedLicenseResponse() {
        return this.mEncodedLicenseResponse;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("metadata", this.mMetadata).add("encodedLicenseResponse", this.mEncodedLicenseResponse).toString();
    }

    public static class Builder {
        public String encodedLicenseResponse;
        public PlayReadyResponseMetadata metadata;

        public PlayReadyLicenseV2 build() {
            return new PlayReadyLicenseV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<PlayReadyLicenseV2> {
        private final SimpleParsers.StringParser mEncodedLicenseResponseParser;
        private final PlayReadyResponseMetadata.Parser mPlayReadyResponseMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, PlayReadyLicenseV2.class);
            this.mPlayReadyResponseMetadataParser = new PlayReadyResponseMetadata.Parser(objectMapper);
            this.mEncodedLicenseResponseParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlayReadyLicenseV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlayReadyLicenseV2");
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
                    String str = null;
                    PlayReadyResponseMetadata playReadyResponseMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            playReadyResponseMetadata = this.mPlayReadyResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = playReadyResponseMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mEncodedLicenseResponseParser.parse(jsonNode2);
                        }
                        builder.encodedLicenseResponse = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlayReadyLicenseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public PlayReadyLicenseV2 parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
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
                            String str = null;
                            PlayReadyResponseMetadata playReadyResponseMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playReadyResponseMetadata = this.mPlayReadyResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = playReadyResponseMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mEncodedLicenseResponseParser.parse(jsonParser);
                                }
                                builder.encodedLicenseResponse = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlayReadyLicenseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
