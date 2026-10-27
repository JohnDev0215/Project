package com.amazon.atv.discovery;

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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ImageSize {
    public final ImageSizeUnit unit;
    public final Integer x;
    public final Integer y;

    private ImageSize(Builder builder) {
        this.x = (Integer) Preconditions.checkNotNull(builder.x, "Unexpected null field: x");
        this.y = (Integer) Preconditions.checkNotNull(builder.y, "Unexpected null field: y");
        this.unit = (ImageSizeUnit) Preconditions.checkNotNull(builder.unit, "Unexpected null field: unit");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageSize)) {
            return false;
        }
        ImageSize imageSize = (ImageSize) obj;
        return Objects.equal(this.x, imageSize.x) && Objects.equal(this.y, imageSize.y) && Objects.equal(this.unit, imageSize.unit);
    }

    public int hashCode() {
        return Objects.hashCode(this.x, this.y, this.unit);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", this.x).add("y", this.y).add("unit", this.unit).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImageSizeUnit unit;
        public Integer x;
        public Integer y;

        public ImageSize build() {
            return new ImageSize(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ImageSize> {
        private final EnumParser<ImageSizeUnit> mImageSizeUnitParser;
        private final SimpleParsers.IntegerParser mNullableIntegerParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mImageSizeUnitParser = EnumParser.newParser(ImageSizeUnit.class);
            this.mNullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ImageSize parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageSize:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ImageSize parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageSize:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ImageSize parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ImageSize");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 120) {
                        if (iHashCode != 121) {
                            if (iHashCode == 3594628 && next.equals("unit")) {
                                b = 2;
                            }
                        } else if (next.equals("y")) {
                            b = 1;
                        }
                    } else if (next.equals("x")) {
                        b = 0;
                    }
                    ImageSizeUnit imageSizeUnit = null;
                    Integer num = null;
                    Integer num2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            num = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.x = num;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            num2 = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.y = num2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            imageSizeUnit = (ImageSizeUnit) this.mImageSizeUnitParser.parse(jsonNode2);
                        }
                        builder.unit = imageSizeUnit;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ImageSize so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.x, this, "x");
            JsonParsingUtils.checkNotNull(builder.y, this, "y");
            JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
            return builder.build();
        }

        @Nonnull
        private ImageSize parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 120) {
                                if (iHashCode != 121) {
                                    if (iHashCode == 3594628 && currentName.equals("unit")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("y")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("x")) {
                                b = 0;
                            }
                            ImageSizeUnit imageSizeUnit = null;
                            Integer num = null;
                            Integer num2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mNullableIntegerParser.parse(jsonParser);
                                }
                                builder.x = num;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num2 = this.mNullableIntegerParser.parse(jsonParser);
                                }
                                builder.y = num2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    imageSizeUnit = (ImageSizeUnit) this.mImageSizeUnitParser.parse(jsonParser);
                                }
                                builder.unit = imageSizeUnit;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ImageSize so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.x, this, "x");
                    JsonParsingUtils.checkNotNull(builder.y, this, "y");
                    JsonParsingUtils.checkNotNull(builder.unit, this, "unit");
                    return builder.build();
                }
            }
        }
    }
}
