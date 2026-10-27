package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.playready.AsinResponse;
import com.amazon.atvplaybackresource.playready.PlayReadyResponseMetadata;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicense {

    @Deprecated
    public final Optional<AsinResponse> asinResponse;
    public final Optional<String> encodedLicenseResponse;
    public final Optional<PlayReadyResponseMetadata> metadata;

    private PlayReadyLicense(Builder builder) {
        this.asinResponse = Optional.fromNullable(builder.asinResponse);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.encodedLicenseResponse = Optional.fromNullable(builder.encodedLicenseResponse);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayReadyLicense)) {
            return false;
        }
        PlayReadyLicense playReadyLicense = (PlayReadyLicense) obj;
        return Objects.equal(this.asinResponse, playReadyLicense.asinResponse) && Objects.equal(this.metadata, playReadyLicense.metadata) && Objects.equal(this.encodedLicenseResponse, playReadyLicense.encodedLicenseResponse);
    }

    public int hashCode() {
        return Objects.hashCode(this.asinResponse, this.metadata, this.encodedLicenseResponse);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("asinResponse", this.asinResponse).add("metadata", this.metadata).add("encodedLicenseResponse", this.encodedLicenseResponse).toString();
    }

    @NotThreadSafe
    public static class Builder {

        @Deprecated
        public AsinResponse asinResponse;
        public String encodedLicenseResponse;
        public PlayReadyResponseMetadata metadata;

        public PlayReadyLicense build() {
            return new PlayReadyLicense(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlayReadyLicense> {
        private final AsinResponse.Parser mAsinResponseParser;
        private final PlayReadyResponseMetadata.Parser mPlayReadyResponseMetadataParser;
        private final SimpleParsers.StringParser mencodedLicenseResponseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAsinResponseParser = new AsinResponse.Parser(objectMapper);
            this.mPlayReadyResponseMetadataParser = new PlayReadyResponseMetadata.Parser(objectMapper);
            this.mencodedLicenseResponseParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlayReadyLicense parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyLicense:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlayReadyLicense parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyLicense:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlayReadyLicense parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlayReadyLicense");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1541110216) {
                        if (iHashCode != -1298210636) {
                            if (iHashCode == -450004177 && next.equals("metadata")) {
                                b = 1;
                            }
                        } else if (next.equals("encodedLicenseResponse")) {
                            b = 2;
                        }
                    } else if (next.equals("asinResponse")) {
                        b = 0;
                    }
                    String str = null;
                    AsinResponse asinResponse = null;
                    PlayReadyResponseMetadata playReadyResponseMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            asinResponse = this.mAsinResponseParser.parse(jsonNode2);
                        }
                        builder.asinResponse = asinResponse;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            playReadyResponseMetadata = this.mPlayReadyResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = playReadyResponseMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mencodedLicenseResponseParser.parse(jsonNode2);
                        }
                        builder.encodedLicenseResponse = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlayReadyLicense so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlayReadyLicense parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1541110216) {
                                if (iHashCode != -1298210636) {
                                    if (iHashCode == -450004177 && currentName.equals("metadata")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("encodedLicenseResponse")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("asinResponse")) {
                                b = 0;
                            }
                            String str = null;
                            AsinResponse asinResponse = null;
                            PlayReadyResponseMetadata playReadyResponseMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    asinResponse = this.mAsinResponseParser.parse(jsonParser);
                                }
                                builder.asinResponse = asinResponse;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playReadyResponseMetadata = this.mPlayReadyResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = playReadyResponseMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mencodedLicenseResponseParser.parse(jsonParser);
                                }
                                builder.encodedLicenseResponse = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlayReadyLicense so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
