package com.amazon.urlvending;

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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class AvUrlInfo {
    public final Optional<String> audioQuality;
    public final Optional<String> bitrate;
    public final Optional<String> cacheKey;
    public final Optional<String> origin;
    public final Optional<String> url;
    public final Optional<String> videoQuality;

    private AvUrlInfo(Builder builder) {
        this.origin = Optional.fromNullable(builder.origin);
        this.audioQuality = Optional.fromNullable(builder.audioQuality);
        this.bitrate = Optional.fromNullable(builder.bitrate);
        this.url = Optional.fromNullable(builder.url);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.cacheKey = Optional.fromNullable(builder.cacheKey);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvUrlInfo)) {
            return false;
        }
        AvUrlInfo avUrlInfo = (AvUrlInfo) obj;
        return Objects.equal(this.origin, avUrlInfo.origin) && Objects.equal(this.audioQuality, avUrlInfo.audioQuality) && Objects.equal(this.bitrate, avUrlInfo.bitrate) && Objects.equal(this.url, avUrlInfo.url) && Objects.equal(this.videoQuality, avUrlInfo.videoQuality) && Objects.equal(this.cacheKey, avUrlInfo.cacheKey);
    }

    public int hashCode() {
        return Objects.hashCode(this.origin, this.audioQuality, this.bitrate, this.url, this.videoQuality, this.cacheKey);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("origin", this.origin).add("audioQuality", this.audioQuality).add("bitrate", this.bitrate).add(ImagesContract.URL, this.url).add("videoQuality", this.videoQuality).add("cacheKey", this.cacheKey).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioQuality;
        public String bitrate;
        public String cacheKey;
        public String origin;
        public String url;
        public String videoQuality;

        public AvUrlInfo build() {
            return new AvUrlInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AvUrlInfo> {
        private final SimpleParsers.StringParser mAudioFormatParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.StringParser mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringParser = stringParser;
            this.mAudioFormatParser = stringParser;
            this.mVideoQualityParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AvUrlInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvUrlInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AvUrlInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvUrlInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AvUrlInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AvUrlInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1008619738:
                            if (next.equals("origin")) {
                                b = 0;
                            }
                            break;
                        case -935141303:
                            if (next.equals("audioQuality")) {
                                b = 1;
                            }
                            break;
                        case -433508483:
                            if (next.equals("cacheKey")) {
                                b = 5;
                            }
                            break;
                        case -102270099:
                            if (next.equals("bitrate")) {
                                b = 2;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 3;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.origin = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mAudioFormatParser.parse(jsonNode2);
                        }
                        builder.audioQuality = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.bitrate = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = str;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cacheKey = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AvUrlInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AvUrlInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1008619738:
                                    if (currentName.equals("origin")) {
                                        b = 0;
                                    }
                                    break;
                                case -935141303:
                                    if (currentName.equals("audioQuality")) {
                                        b = 1;
                                    }
                                    break;
                                case -433508483:
                                    if (currentName.equals("cacheKey")) {
                                        b = 5;
                                    }
                                    break;
                                case -102270099:
                                    if (currentName.equals("bitrate")) {
                                        b = 2;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 3;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.origin = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mAudioFormatParser.parse(jsonParser);
                                }
                                builder.audioQuality = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.bitrate = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.url = str;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mVideoQualityParser.parse(jsonParser);
                                }
                                builder.videoQuality = str;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.cacheKey = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AvUrlInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
