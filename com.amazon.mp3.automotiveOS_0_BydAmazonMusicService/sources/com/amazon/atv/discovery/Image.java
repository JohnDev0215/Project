package com.amazon.atv.discovery;

import com.amazon.atv.discovery.ImageSize;
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
    public final boolean gradientRequired;
    public final Optional<ImageSize> size;
    public final String url;
    public final int version;

    private Image(Builder builder) {
        this.version = builder.version;
        this.alternateText = Optional.fromNullable(builder.alternateText);
        this.url = (String) Preconditions.checkNotNull(builder.url, "Unexpected null field: url");
        this.size = Optional.fromNullable(builder.size);
        this.gradientRequired = builder.gradientRequired;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return Objects.equal(Integer.valueOf(this.version), Integer.valueOf(image.version)) && Objects.equal(this.alternateText, image.alternateText) && Objects.equal(this.url, image.url) && Objects.equal(this.size, image.size) && Objects.equal(Boolean.valueOf(this.gradientRequired), Boolean.valueOf(image.gradientRequired));
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version), this.alternateText, this.url, this.size, Boolean.valueOf(this.gradientRequired));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("alternateText", this.alternateText).add(ImagesContract.URL, this.url).add("size", this.size).add("gradientRequired", this.gradientRequired).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String alternateText;
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
                                b = 1;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 2;
                            }
                            break;
                        case 3530753:
                            if (next.equals("size")) {
                                b = 3;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 0;
                            }
                            break;
                        case 2125551343:
                            if (next.equals("gradientRequired")) {
                                b = 4;
                            }
                            break;
                    }
                    if (b != 0) {
                        ImageSize imageSize = null;
                        String str = null;
                        String str2 = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.alternateText = str;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.url = str2;
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                imageSize = this.mImageSizeParser.parse(jsonNode2);
                            }
                            builder.size = imageSize;
                        } else if (b == 4) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field gradientRequired can't be null");
                            }
                            builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
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
                                        b = 1;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 2;
                                    }
                                    break;
                                case 3530753:
                                    if (currentName.equals("size")) {
                                        b = 3;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 0;
                                    }
                                    break;
                                case 2125551343:
                                    if (currentName.equals("gradientRequired")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                ImageSize imageSize = null;
                                String str = null;
                                String str2 = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.alternateText = str;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.url = str2;
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imageSize = this.mImageSizeParser.parse(jsonParser);
                                    }
                                    builder.size = imageSize;
                                } else if (b == 4) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field gradientRequired can't be null");
                                    }
                                    builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Image so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
                    return builder.build();
                }
            }
        }
    }
}
