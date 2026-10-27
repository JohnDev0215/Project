package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Image;
import com.amazon.atv.xrayv2.RenderingComponentConfig;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class ImageV2 {
    public final Image image;
    public final RenderingComponentConfig renderData;
    public final Optional<ImageFormat> type;
    public final int version;

    private ImageV2(Builder builder) {
        this.renderData = (RenderingComponentConfig) Preconditions.checkNotNull(builder.renderData, "Unexpected null field: renderData");
        this.version = builder.version;
        this.image = (Image) Preconditions.checkNotNull(builder.image, "Unexpected null field: image");
        this.type = Optional.fromNullable(builder.type);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageV2)) {
            return false;
        }
        ImageV2 imageV2 = (ImageV2) obj;
        return Objects.equal(this.renderData, imageV2.renderData) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(imageV2.version)) && Objects.equal(this.image, imageV2.image) && Objects.equal(this.type, imageV2.type);
    }

    public int hashCode() {
        return Objects.hashCode(this.renderData, Integer.valueOf(this.version), this.image, this.type);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("renderData", this.renderData).add("version", this.version).add(ContextMappingConstants.IMAGE, this.image).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Image image;
        public RenderingComponentConfig renderData;
        public ImageFormat type;
        public int version;

        public ImageV2 build() {
            return new ImageV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ImageV2> {
        private final EnumParser<ImageFormat> mImageFormatParser;
        private final Image.Parser mImageParser;
        private final RenderingComponentConfig.Parser mRenderingComponentConfigParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mImageFormatParser = EnumParser.newParser(ImageFormat.class);
            this.mImageParser = new Image.Parser(objectMapper);
            this.mRenderingComponentConfigParser = new RenderingComponentConfig.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ImageV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ImageV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ImageV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ImageV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 3;
                            }
                            break;
                        case 100313435:
                            if (next.equals(ContextMappingConstants.IMAGE)) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 1193601632:
                            if (next.equals("renderData")) {
                                b = 0;
                            }
                            break;
                    }
                    ImageFormat imageFormat = null;
                    RenderingComponentConfig renderingComponentConfig = null;
                    Image image = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            renderingComponentConfig = this.mRenderingComponentConfigParser.parse(jsonNode2);
                        }
                        builder.renderData = renderingComponentConfig;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            image = this.mImageParser.parse(jsonNode2);
                        }
                        builder.image = image;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            imageFormat = (ImageFormat) this.mImageFormatParser.parse(jsonNode2);
                        }
                        builder.type = imageFormat;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ImageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.renderData, this, "renderData");
            JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ImageV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 3;
                                    }
                                    break;
                                case 100313435:
                                    if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 1193601632:
                                    if (currentName.equals("renderData")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            ImageFormat imageFormat = null;
                            RenderingComponentConfig renderingComponentConfig = null;
                            Image image = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    renderingComponentConfig = this.mRenderingComponentConfigParser.parse(jsonParser);
                                }
                                builder.renderData = renderingComponentConfig;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    image = this.mImageParser.parse(jsonParser);
                                }
                                builder.image = image;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    imageFormat = (ImageFormat) this.mImageFormatParser.parse(jsonParser);
                                }
                                builder.type = imageFormat;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ImageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.renderData, this, "renderData");
                    JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
                    return builder.build();
                }
            }
        }
    }
}
