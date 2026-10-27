package com.amazon.atv.cuepoint;

import com.amazon.atv.cuepoint.CuepointFulfillmentMetadata;
import com.amazon.atv.cuepoint.CuepointPlaylistMetadata;
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
public class CuepointPlaylist {
    public final CuepointPlaylistMetadata additionalMetadata;
    public final Optional<CuepointFulfillmentMetadata> cuepointFulfillmentMetadata;
    public final String encodedManifest;
    public final ManifestFormat format;

    private CuepointPlaylist(Builder builder) {
        this.cuepointFulfillmentMetadata = Optional.fromNullable(builder.cuepointFulfillmentMetadata);
        this.additionalMetadata = (CuepointPlaylistMetadata) Preconditions.checkNotNull(builder.additionalMetadata, "Unexpected null field: additionalMetadata");
        this.encodedManifest = (String) Preconditions.checkNotNull(builder.encodedManifest, "Unexpected null field: encodedManifest");
        this.format = (ManifestFormat) Preconditions.checkNotNull(builder.format, "Unexpected null field: format");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CuepointPlaylist)) {
            return false;
        }
        CuepointPlaylist cuepointPlaylist = (CuepointPlaylist) obj;
        return Objects.equal(this.cuepointFulfillmentMetadata, cuepointPlaylist.cuepointFulfillmentMetadata) && Objects.equal(this.additionalMetadata, cuepointPlaylist.additionalMetadata) && Objects.equal(this.encodedManifest, cuepointPlaylist.encodedManifest) && Objects.equal(this.format, cuepointPlaylist.format);
    }

    public int hashCode() {
        return Objects.hashCode(this.cuepointFulfillmentMetadata, this.additionalMetadata, this.encodedManifest, this.format);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cuepointFulfillmentMetadata", this.cuepointFulfillmentMetadata).add("additionalMetadata", this.additionalMetadata).add("encodedManifest", this.encodedManifest).add("format", this.format).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CuepointPlaylistMetadata additionalMetadata;
        public CuepointFulfillmentMetadata cuepointFulfillmentMetadata;
        public String encodedManifest;
        public ManifestFormat format;

        public CuepointPlaylist build() {
            return new CuepointPlaylist(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CuepointPlaylist> {
        private final CuepointFulfillmentMetadata.Parser mCuepointFulfillmentMetadataParser;
        private final CuepointPlaylistMetadata.Parser mCuepointPlaylistMetadataParser;
        private final EnumParser<ManifestFormat> mManifestFormatParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCuepointPlaylistMetadataParser = new CuepointPlaylistMetadata.Parser(objectMapper);
            this.mManifestFormatParser = EnumParser.newParser(ManifestFormat.class);
            this.mCuepointFulfillmentMetadataParser = new CuepointFulfillmentMetadata.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CuepointPlaylist parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointPlaylist:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CuepointPlaylist parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointPlaylist:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CuepointPlaylist parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CuepointPlaylist");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1807865507:
                            if (next.equals("encodedManifest")) {
                                b = 2;
                            }
                            break;
                        case -1268779017:
                            if (next.equals("format")) {
                                b = 3;
                            }
                            break;
                        case -181717840:
                            if (next.equals("cuepointFulfillmentMetadata")) {
                                b = 0;
                            }
                            break;
                        case 2011021430:
                            if (next.equals("additionalMetadata")) {
                                b = 1;
                            }
                            break;
                    }
                    ManifestFormat manifestFormat = null;
                    CuepointFulfillmentMetadata cuepointFulfillmentMetadata = null;
                    CuepointPlaylistMetadata cuepointPlaylistMetadata = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            cuepointFulfillmentMetadata = this.mCuepointFulfillmentMetadataParser.parse(jsonNode2);
                        }
                        builder.cuepointFulfillmentMetadata = cuepointFulfillmentMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            cuepointPlaylistMetadata = this.mCuepointPlaylistMetadataParser.parse(jsonNode2);
                        }
                        builder.additionalMetadata = cuepointPlaylistMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.encodedManifest = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            manifestFormat = (ManifestFormat) this.mManifestFormatParser.parse(jsonNode2);
                        }
                        builder.format = manifestFormat;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CuepointPlaylist so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.additionalMetadata, this, "additionalMetadata");
            JsonParsingUtils.checkNotNull(builder.encodedManifest, this, "encodedManifest");
            JsonParsingUtils.checkNotNull(builder.format, this, "format");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CuepointPlaylist parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1807865507:
                                    if (currentName.equals("encodedManifest")) {
                                        b = 2;
                                    }
                                    break;
                                case -1268779017:
                                    if (currentName.equals("format")) {
                                        b = 3;
                                    }
                                    break;
                                case -181717840:
                                    if (currentName.equals("cuepointFulfillmentMetadata")) {
                                        b = 0;
                                    }
                                    break;
                                case 2011021430:
                                    if (currentName.equals("additionalMetadata")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ManifestFormat manifestFormat = null;
                            CuepointFulfillmentMetadata cuepointFulfillmentMetadata = null;
                            CuepointPlaylistMetadata cuepointPlaylistMetadata = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cuepointFulfillmentMetadata = this.mCuepointFulfillmentMetadataParser.parse(jsonParser);
                                }
                                builder.cuepointFulfillmentMetadata = cuepointFulfillmentMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cuepointPlaylistMetadata = this.mCuepointPlaylistMetadataParser.parse(jsonParser);
                                }
                                builder.additionalMetadata = cuepointPlaylistMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.encodedManifest = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    manifestFormat = (ManifestFormat) this.mManifestFormatParser.parse(jsonParser);
                                }
                                builder.format = manifestFormat;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CuepointPlaylist so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.additionalMetadata, this, "additionalMetadata");
                    JsonParsingUtils.checkNotNull(builder.encodedManifest, this, "encodedManifest");
                    JsonParsingUtils.checkNotNull(builder.format, this, "format");
                    return builder.build();
                }
            }
        }
    }
}
