package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.LocalizedImage;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class Image {
    public final Optional<AspectRatio> aspectRatio;
    public final Optional<LocalizedImage> localizedImage;

    private Image(Builder builder) {
        this.aspectRatio = Optional.fromNullable(builder.aspectRatio);
        this.localizedImage = Optional.fromNullable(builder.localizedImage);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return Objects.equal(this.aspectRatio, image.aspectRatio) && Objects.equal(this.localizedImage, image.localizedImage);
    }

    public int hashCode() {
        return Objects.hashCode(this.aspectRatio, this.localizedImage);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("aspectRatio", this.aspectRatio).add("localizedImage", this.localizedImage).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AspectRatio aspectRatio;
        public LocalizedImage localizedImage;

        public Image build() {
            return new Image(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Image> {
        private final EnumParser<AspectRatio> mAspectRatioParser;
        private final LocalizedImage.Parser mLocalizedImageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLocalizedImageParser = new LocalizedImage.Parser(objectMapper);
            this.mAspectRatioParser = EnumParser.newParser(AspectRatio.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Image parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Image:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Image parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Image:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Image parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Image");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 113896096) {
                        if (iHashCode == 1092174483 && next.equals("aspectRatio")) {
                            b = 0;
                        }
                    } else if (next.equals("localizedImage")) {
                        b = 1;
                    }
                    LocalizedImage localizedImage = null;
                    AspectRatio aspectRatio = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            aspectRatio = (AspectRatio) this.mAspectRatioParser.parse(jsonNode2);
                        }
                        builder.aspectRatio = aspectRatio;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            localizedImage = this.mLocalizedImageParser.parse(jsonNode2);
                        }
                        builder.localizedImage = localizedImage;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Image parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 113896096) {
                                if (iHashCode == 1092174483 && currentName.equals("aspectRatio")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("localizedImage")) {
                                b = 1;
                            }
                            LocalizedImage localizedImage = null;
                            AspectRatio aspectRatio = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    aspectRatio = (AspectRatio) this.mAspectRatioParser.parse(jsonParser);
                                }
                                builder.aspectRatio = aspectRatio;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedImage = this.mLocalizedImageParser.parse(jsonParser);
                                }
                                builder.localizedImage = localizedImage;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
