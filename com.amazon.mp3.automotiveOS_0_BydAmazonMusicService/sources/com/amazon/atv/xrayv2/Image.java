package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.ImageSize;
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
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Image {
    public final Optional<String> alternateText;
    public final float aspectRatio;
    public final boolean gradientRequired;
    public final Optional<ImageSize> size;
    public final String url;
    public final int version;

    private Image(Builder builder) {
        this.size = Optional.fromNullable(builder.size);
        this.gradientRequired = builder.gradientRequired;
        this.aspectRatio = builder.aspectRatio;
        this.alternateText = Optional.fromNullable(builder.alternateText);
        this.url = (String) Preconditions.checkNotNull(builder.url, "Unexpected null field: url");
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return Objects.equal(this.size, image.size) && Objects.equal(Boolean.valueOf(this.gradientRequired), Boolean.valueOf(image.gradientRequired)) && Objects.equal(Float.valueOf(this.aspectRatio), Float.valueOf(image.aspectRatio)) && Objects.equal(this.alternateText, image.alternateText) && Objects.equal(this.url, image.url) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(image.version));
    }

    public int hashCode() {
        return Objects.hashCode(this.size, Boolean.valueOf(this.gradientRequired), Float.valueOf(this.aspectRatio), this.alternateText, this.url, Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("size", this.size).add("gradientRequired", this.gradientRequired).add("aspectRatio", this.aspectRatio).add("alternateText", this.alternateText).add(ImagesContract.URL, this.url).add("version", this.version).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String alternateText;
        public float aspectRatio;
        public boolean gradientRequired;
        public ImageSize size;
        public String url;
        public int version;

        public Image build() {
            return new Image(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Image> {
        private final ImageSize.Parser mImageSizeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mImageSizeParser = new ImageSize.Parser(objectMapper);
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

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
                    switch (next.hashCode()) {
                        case -145609529:
                            if (next.equals("alternateText")) {
                                b = 3;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 4;
                            }
                            break;
                        case 3530753:
                            if (next.equals("size")) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 5;
                            }
                            break;
                        case 1092174483:
                            if (next.equals("aspectRatio")) {
                                b = 2;
                            }
                            break;
                        case 2125551343:
                            if (next.equals("gradientRequired")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    ImageSize imageSize = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            imageSize = this.mImageSizeParser.parse(jsonNode2);
                        }
                        builder.size = imageSize;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field gradientRequired can't be null");
                        }
                        builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field aspectRatio can't be null");
                        }
                        builder.aspectRatio = SimpleParsers.parsePrimitiveFloat(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.alternateText = str2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str;
                    } else if (b == 5) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
                            switch (currentName.hashCode()) {
                                case -145609529:
                                    if (currentName.equals("alternateText")) {
                                        b = 3;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 4;
                                    }
                                    break;
                                case 3530753:
                                    if (currentName.equals("size")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 5;
                                    }
                                    break;
                                case 1092174483:
                                    if (currentName.equals("aspectRatio")) {
                                        b = 2;
                                    }
                                    break;
                                case 2125551343:
                                    if (currentName.equals("gradientRequired")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            ImageSize imageSize = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    imageSize = this.mImageSizeParser.parse(jsonParser);
                                }
                                builder.size = imageSize;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field gradientRequired can't be null");
                                }
                                builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field aspectRatio can't be null");
                                }
                                builder.aspectRatio = SimpleParsers.parsePrimitiveFloat(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.alternateText = str2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.url = str;
                            } else if (b == 5) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
