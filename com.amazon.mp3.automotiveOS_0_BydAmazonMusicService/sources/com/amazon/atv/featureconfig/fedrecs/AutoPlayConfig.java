package com.amazon.atv.featureconfig.fedrecs;

import com.amazon.atv.featureconfig.Config;
import com.amazon.atv.featureconfig.ImageType;
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
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AutoPlayConfig extends Config {
    public final Optional<ImageType> autoplayCardPreferredImage;
    public final Optional<String> autoplayCardText;
    public final boolean autoplayEnabled;
    public final boolean continuousPlaybackEnabled;
    public final int numTitlesToAutoplay;
    public final boolean showAutoplayCard;

    private AutoPlayConfig(Builder builder) {
        super(builder);
        this.continuousPlaybackEnabled = builder.continuousPlaybackEnabled;
        this.showAutoplayCard = builder.showAutoplayCard;
        this.autoplayEnabled = builder.autoplayEnabled;
        this.autoplayCardPreferredImage = Optional.fromNullable(builder.autoplayCardPreferredImage);
        this.autoplayCardText = Optional.fromNullable(builder.autoplayCardText);
        this.numTitlesToAutoplay = builder.numTitlesToAutoplay;
    }

    @Override // com.amazon.atv.featureconfig.Config
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutoPlayConfig)) {
            return false;
        }
        AutoPlayConfig autoPlayConfig = (AutoPlayConfig) obj;
        return super.equals(obj) && Objects.equal(Boolean.valueOf(this.continuousPlaybackEnabled), Boolean.valueOf(autoPlayConfig.continuousPlaybackEnabled)) && Objects.equal(Boolean.valueOf(this.showAutoplayCard), Boolean.valueOf(autoPlayConfig.showAutoplayCard)) && Objects.equal(Boolean.valueOf(this.autoplayEnabled), Boolean.valueOf(autoPlayConfig.autoplayEnabled)) && Objects.equal(this.autoplayCardPreferredImage, autoPlayConfig.autoplayCardPreferredImage) && Objects.equal(this.autoplayCardText, autoPlayConfig.autoplayCardText) && Objects.equal(Integer.valueOf(this.numTitlesToAutoplay), Integer.valueOf(autoPlayConfig.numTitlesToAutoplay));
    }

    @Override // com.amazon.atv.featureconfig.Config
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Boolean.valueOf(this.continuousPlaybackEnabled), Boolean.valueOf(this.showAutoplayCard), Boolean.valueOf(this.autoplayEnabled), this.autoplayCardPreferredImage, this.autoplayCardText, Integer.valueOf(this.numTitlesToAutoplay));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("continuousPlaybackEnabled", this.continuousPlaybackEnabled).add("showAutoplayCard", this.showAutoplayCard).add("autoplayEnabled", this.autoplayEnabled).add("autoplayCardPreferredImage", this.autoplayCardPreferredImage).add("autoplayCardText", this.autoplayCardText).add("numTitlesToAutoplay", this.numTitlesToAutoplay).toString();
    }

    @NotThreadSafe
    public static class Builder extends Config.Builder {
        public ImageType autoplayCardPreferredImage;
        public String autoplayCardText;
        public boolean autoplayEnabled;
        public boolean continuousPlaybackEnabled;
        public int numTitlesToAutoplay;
        public boolean showAutoplayCard;

        public AutoPlayConfig build() {
            return new AutoPlayConfig(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AutoPlayConfig> {
        private final EnumParser<ImageType> mImageTypeParser;
        private final SimpleParsers.StringParser mStringFieldParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mImageTypeParser = EnumParser.newParser(ImageType.class);
            this.mStringFieldParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AutoPlayConfig parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutoPlayConfig:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AutoPlayConfig parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutoPlayConfig:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.featureconfig.fedrecs.AutoPlayConfig parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 342
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.featureconfig.fedrecs.AutoPlayConfig.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.featureconfig.fedrecs.AutoPlayConfig");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AutoPlayConfig parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1929166003:
                                    if (currentName.equals("autoplayCardPreferredImage")) {
                                        b = 3;
                                    }
                                    break;
                                case 214879639:
                                    if (currentName.equals("continuousPlaybackEnabled")) {
                                        b = 0;
                                    }
                                    break;
                                case 256121520:
                                    if (currentName.equals("showAutoplayCard")) {
                                        b = 1;
                                    }
                                    break;
                                case 471303199:
                                    if (currentName.equals("numTitlesToAutoplay")) {
                                        b = 5;
                                    }
                                    break;
                                case 580417248:
                                    if (currentName.equals("autoplayCardText")) {
                                        b = 4;
                                    }
                                    break;
                                case 764699742:
                                    if (currentName.equals("autoplayEnabled")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            if (b == 0) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field continuousPlaybackEnabled can't be null");
                                }
                                builder.continuousPlaybackEnabled = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field showAutoplayCard can't be null");
                                }
                                builder.showAutoplayCard = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b != 2) {
                                String str = null;
                                ImageType imageType = null;
                                if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imageType = (ImageType) this.mImageTypeParser.parse(jsonParser);
                                    }
                                    builder.autoplayCardPreferredImage = imageType;
                                } else if (b == 4) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringFieldParser.parse(jsonParser);
                                    }
                                    builder.autoplayCardText = str;
                                } else if (b == 5) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field numTitlesToAutoplay can't be null");
                                    }
                                    builder.numTitlesToAutoplay = SimpleParsers.parsePrimitiveInt(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field autoplayEnabled can't be null");
                                }
                                builder.autoplayEnabled = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AutoPlayConfig so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.continuousPlaybackEnabled), this, "continuousPlaybackEnabled");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.autoplayEnabled), this, "autoplayEnabled");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.numTitlesToAutoplay), this, "numTitlesToAutoplay");
                    return builder.build();
                }
            }
        }
    }
}
