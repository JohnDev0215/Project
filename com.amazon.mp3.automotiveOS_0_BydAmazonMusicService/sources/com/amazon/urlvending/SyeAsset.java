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
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class SyeAsset {
    public final Optional<String> audioFormat;
    public final Optional<String> bitrateAdaption;
    public final Optional<String> compressionStandard;
    public final Optional<String> dynamicRange;
    public final Optional<String> frameRate;
    public final Optional<String> globalTitleIdentifier;
    public final Optional<String> streamingTechnology;
    public final Optional<String> videoContentId;
    public final Optional<String> videoQuality;

    private SyeAsset(Builder builder) {
        this.audioFormat = Optional.fromNullable(builder.audioFormat);
        this.compressionStandard = Optional.fromNullable(builder.compressionStandard);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.videoContentId = Optional.fromNullable(builder.videoContentId);
        this.bitrateAdaption = Optional.fromNullable(builder.bitrateAdaption);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.globalTitleIdentifier = Optional.fromNullable(builder.globalTitleIdentifier);
        this.frameRate = Optional.fromNullable(builder.frameRate);
        this.dynamicRange = Optional.fromNullable(builder.dynamicRange);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeAsset)) {
            return false;
        }
        SyeAsset syeAsset = (SyeAsset) obj;
        return Objects.equal(this.audioFormat, syeAsset.audioFormat) && Objects.equal(this.compressionStandard, syeAsset.compressionStandard) && Objects.equal(this.videoQuality, syeAsset.videoQuality) && Objects.equal(this.videoContentId, syeAsset.videoContentId) && Objects.equal(this.bitrateAdaption, syeAsset.bitrateAdaption) && Objects.equal(this.streamingTechnology, syeAsset.streamingTechnology) && Objects.equal(this.globalTitleIdentifier, syeAsset.globalTitleIdentifier) && Objects.equal(this.frameRate, syeAsset.frameRate) && Objects.equal(this.dynamicRange, syeAsset.dynamicRange);
    }

    public int hashCode() {
        return Objects.hashCode(this.audioFormat, this.compressionStandard, this.videoQuality, this.videoContentId, this.bitrateAdaption, this.streamingTechnology, this.globalTitleIdentifier, this.frameRate, this.dynamicRange);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("audioFormat", this.audioFormat).add("compressionStandard", this.compressionStandard).add("videoQuality", this.videoQuality).add("videoContentId", this.videoContentId).add("bitrateAdaption", this.bitrateAdaption).add("streamingTechnology", this.streamingTechnology).add("globalTitleIdentifier", this.globalTitleIdentifier).add("frameRate", this.frameRate).add("dynamicRange", this.dynamicRange).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioFormat;
        public String bitrateAdaption;
        public String compressionStandard;
        public String dynamicRange;
        public String frameRate;
        public String globalTitleIdentifier;
        public String streamingTechnology;
        public String videoContentId;
        public String videoQuality;

        public SyeAsset build() {
            return new SyeAsset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeAsset> {
        private final SimpleParsers.StringParser mAudioFormatParser;
        private final SimpleParsers.StringParser mBitrateAdaptionParser;
        private final SimpleParsers.StringParser mCompressionStandardParser;
        private final SimpleParsers.StringParser mDynamicRangeParser;
        private final SimpleParsers.StringParser mFrameRateParser;
        private final SimpleParsers.StringParser mStreamingTechnologyParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.StringParser mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAudioFormatParser = stringParser;
            this.mCompressionStandardParser = stringParser;
            this.mVideoQualityParser = stringParser;
            this.mStringParser = stringParser;
            this.mBitrateAdaptionParser = stringParser;
            this.mStreamingTechnologyParser = stringParser;
            this.mFrameRateParser = stringParser;
            this.mDynamicRangeParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeAsset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeAsset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeAsset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeAsset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeAsset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeAsset");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1957882301:
                            if (next.equals("compressionStandard")) {
                                b = 1;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 5;
                            }
                            break;
                        case -614336461:
                            if (next.equals("bitrateAdaption")) {
                                b = 4;
                            }
                            break;
                        case -90314439:
                            if (next.equals("videoContentId")) {
                                b = 3;
                            }
                            break;
                        case 65521517:
                            if (next.equals("audioFormat")) {
                                b = 0;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 2;
                            }
                            break;
                        case 545057773:
                            if (next.equals("frameRate")) {
                                b = 7;
                            }
                            break;
                        case 568932414:
                            if (next.equals("dynamicRange")) {
                                b = 8;
                            }
                            break;
                        case 927807230:
                            if (next.equals("globalTitleIdentifier")) {
                                b = 6;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mAudioFormatParser.parse(jsonNode2);
                        }
                        builder.audioFormat = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mCompressionStandardParser.parse(jsonNode2);
                        }
                        builder.compressionStandard = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.videoContentId = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mBitrateAdaptionParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaption = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mStreamingTechnologyParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.globalTitleIdentifier = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.mFrameRateParser.parse(jsonNode2);
                        }
                        builder.frameRate = str;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.mDynamicRangeParser.parse(jsonNode2);
                        }
                        builder.dynamicRange = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeAsset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1957882301:
                                    if (currentName.equals("compressionStandard")) {
                                        b = 1;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 5;
                                    }
                                    break;
                                case -614336461:
                                    if (currentName.equals("bitrateAdaption")) {
                                        b = 4;
                                    }
                                    break;
                                case -90314439:
                                    if (currentName.equals("videoContentId")) {
                                        b = 3;
                                    }
                                    break;
                                case 65521517:
                                    if (currentName.equals("audioFormat")) {
                                        b = 0;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 2;
                                    }
                                    break;
                                case 545057773:
                                    if (currentName.equals("frameRate")) {
                                        b = 7;
                                    }
                                    break;
                                case 568932414:
                                    if (currentName.equals("dynamicRange")) {
                                        b = 8;
                                    }
                                    break;
                                case 927807230:
                                    if (currentName.equals("globalTitleIdentifier")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mAudioFormatParser.parse(jsonParser);
                                    }
                                    builder.audioFormat = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mCompressionStandardParser.parse(jsonParser);
                                    }
                                    builder.compressionStandard = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.videoContentId = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mBitrateAdaptionParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaption = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStreamingTechnologyParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.globalTitleIdentifier = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mFrameRateParser.parse(jsonParser);
                                    }
                                    builder.frameRate = str;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mDynamicRangeParser.parse(jsonParser);
                                    }
                                    builder.dynamicRange = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
