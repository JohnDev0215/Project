package com.amazon.atv.discovery;

import com.amazon.atv.discovery.ImageSize;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class LogoImage {
    public final String image;
    public final ImageSize imageSize;
    public final String version;

    private LogoImage(Builder builder) {
        this.version = (String) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
        this.imageSize = (ImageSize) Preconditions.checkNotNull(builder.imageSize, "Unexpected null field: imageSize");
        this.image = (String) Preconditions.checkNotNull(builder.image, "Unexpected null field: image");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogoImage)) {
            return false;
        }
        LogoImage logoImage = (LogoImage) obj;
        return Objects.equal(this.version, logoImage.version) && Objects.equal(this.imageSize, logoImage.imageSize) && Objects.equal(this.image, logoImage.image);
    }

    public int hashCode() {
        return Objects.hashCode(this.version, this.imageSize, this.image);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("imageSize", this.imageSize).add(ContextMappingConstants.IMAGE, this.image).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String image;
        public ImageSize imageSize;
        public String version;

        public LogoImage build() {
            return new LogoImage(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LogoImage> {
        private final ImageSize.Parser mImageSizeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mImageSizeParser = new ImageSize.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LogoImage parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LogoImage:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LogoImage parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LogoImage:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LogoImage parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LogoImage");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -878192644) {
                        if (iHashCode != 100313435) {
                            if (iHashCode == 351608024 && next.equals("version")) {
                                b = 0;
                            }
                        } else if (next.equals(ContextMappingConstants.IMAGE)) {
                            b = 2;
                        }
                    } else if (next.equals("imageSize")) {
                        b = 1;
                    }
                    String str = null;
                    String str2 = null;
                    ImageSize imageSize = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.version = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            imageSize = this.mImageSizeParser.parse(jsonNode2);
                        }
                        builder.imageSize = imageSize;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.image = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LogoImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            JsonParsingUtils.checkNotNull(builder.imageSize, this, "imageSize");
            JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
            return builder.build();
        }

        @Nonnull
        private LogoImage parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -878192644) {
                                if (iHashCode != 100313435) {
                                    if (iHashCode == 351608024 && currentName.equals("version")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                    b = 2;
                                }
                            } else if (currentName.equals("imageSize")) {
                                b = 1;
                            }
                            String str = null;
                            String str2 = null;
                            ImageSize imageSize = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.version = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    imageSize = this.mImageSizeParser.parse(jsonParser);
                                }
                                builder.imageSize = imageSize;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.image = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LogoImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    JsonParsingUtils.checkNotNull(builder.imageSize, this, "imageSize");
                    JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
                    return builder.build();
                }
            }
        }
    }
}
