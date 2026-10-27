package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ArtworkObfuscation;
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
public class TitleObfuscation {
    public final Optional<ArtworkObfuscation> artwork;
    public final boolean sharingPrevented;
    public final boolean synopsisHidden;

    private TitleObfuscation(Builder builder) {
        this.artwork = Optional.fromNullable(builder.artwork);
        this.synopsisHidden = builder.synopsisHidden;
        this.sharingPrevented = builder.sharingPrevented;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleObfuscation)) {
            return false;
        }
        TitleObfuscation titleObfuscation = (TitleObfuscation) obj;
        return Objects.equal(this.artwork, titleObfuscation.artwork) && Objects.equal(Boolean.valueOf(this.synopsisHidden), Boolean.valueOf(titleObfuscation.synopsisHidden)) && Objects.equal(Boolean.valueOf(this.sharingPrevented), Boolean.valueOf(titleObfuscation.sharingPrevented));
    }

    public int hashCode() {
        return Objects.hashCode(this.artwork, Boolean.valueOf(this.synopsisHidden), Boolean.valueOf(this.sharingPrevented));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("artwork", this.artwork).add("synopsisHidden", this.synopsisHidden).add("sharingPrevented", this.sharingPrevented).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ArtworkObfuscation artwork;
        public boolean sharingPrevented;
        public boolean synopsisHidden;

        public TitleObfuscation build() {
            return new TitleObfuscation(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleObfuscation> {
        private final ArtworkObfuscation.Parser mArtworkObfuscationParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mArtworkObfuscationParser = new ArtworkObfuscation.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleObfuscation parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleObfuscation:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleObfuscation parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleObfuscation:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleObfuscation parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleObfuscation");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -731949068) {
                        if (iHashCode != 1381446395) {
                            if (iHashCode == 1787371614 && next.equals("synopsisHidden")) {
                                b = 1;
                            }
                        } else if (next.equals("sharingPrevented")) {
                            b = 2;
                        }
                    } else if (next.equals("artwork")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.artwork = jsonNode2.isNull() ? null : this.mArtworkObfuscationParser.parse(jsonNode2);
                    } else if (b != 1) {
                        if (b == 2) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field sharingPrevented can't be null");
                            }
                            builder.sharingPrevented = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field synopsisHidden can't be null");
                        }
                        builder.synopsisHidden = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleObfuscation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.synopsisHidden), this, "synopsisHidden");
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.sharingPrevented), this, "sharingPrevented");
            return builder.build();
        }

        @Nonnull
        private TitleObfuscation parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -731949068) {
                                if (iHashCode != 1381446395) {
                                    if (iHashCode == 1787371614 && currentName.equals("synopsisHidden")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("sharingPrevented")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("artwork")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.artwork = currentToken == JsonToken.VALUE_NULL ? null : this.mArtworkObfuscationParser.parse(jsonParser);
                            } else if (b != 1) {
                                if (b == 2) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field sharingPrevented can't be null");
                                    }
                                    builder.sharingPrevented = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field synopsisHidden can't be null");
                                }
                                builder.synopsisHidden = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleObfuscation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.synopsisHidden), this, "synopsisHidden");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.sharingPrevented), this, "sharingPrevented");
                    return builder.build();
                }
            }
        }
    }
}
