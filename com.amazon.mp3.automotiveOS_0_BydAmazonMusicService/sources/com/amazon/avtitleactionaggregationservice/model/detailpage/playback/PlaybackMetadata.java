package com.amazon.avtitleactionaggregationservice.model.detailpage.playback;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.LinearData;
import com.amazon.avtitleactionaggregationservice.model.VideoMaterialType;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
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
public class PlaybackMetadata {
    public final Optional<Boolean> isLinear;
    public final Optional<String> legacyOfferAsin;
    public final Optional<LinearData> linearData;
    public final Optional<String> maxAllowedLookbackDuration;
    public final Optional<String> playbackConsumptionType;
    public final Optional<VideoMaterialType> videoMaterialType;
    public final Optional<VideoQuality> videoQuality;

    private PlaybackMetadata(Builder builder) {
        this.playbackConsumptionType = Optional.fromNullable(builder.playbackConsumptionType);
        this.legacyOfferAsin = Optional.fromNullable(builder.legacyOfferAsin);
        this.maxAllowedLookbackDuration = Optional.fromNullable(builder.maxAllowedLookbackDuration);
        this.linearData = Optional.fromNullable(builder.linearData);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.videoMaterialType = Optional.fromNullable(builder.videoMaterialType);
        this.isLinear = Optional.fromNullable(builder.isLinear);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackMetadata)) {
            return false;
        }
        PlaybackMetadata playbackMetadata = (PlaybackMetadata) obj;
        return Objects.equal(this.playbackConsumptionType, playbackMetadata.playbackConsumptionType) && Objects.equal(this.legacyOfferAsin, playbackMetadata.legacyOfferAsin) && Objects.equal(this.maxAllowedLookbackDuration, playbackMetadata.maxAllowedLookbackDuration) && Objects.equal(this.linearData, playbackMetadata.linearData) && Objects.equal(this.videoQuality, playbackMetadata.videoQuality) && Objects.equal(this.videoMaterialType, playbackMetadata.videoMaterialType) && Objects.equal(this.isLinear, playbackMetadata.isLinear);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackConsumptionType, this.legacyOfferAsin, this.maxAllowedLookbackDuration, this.linearData, this.videoQuality, this.videoMaterialType, this.isLinear);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackConsumptionType", this.playbackConsumptionType).add("legacyOfferAsin", this.legacyOfferAsin).add("maxAllowedLookbackDuration", this.maxAllowedLookbackDuration).add("linearData", this.linearData).add("videoQuality", this.videoQuality).add("videoMaterialType", this.videoMaterialType).add("isLinear", this.isLinear).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean isLinear;
        public String legacyOfferAsin;
        public LinearData linearData;
        public String maxAllowedLookbackDuration;
        public String playbackConsumptionType;
        public VideoMaterialType videoMaterialType;
        public VideoQuality videoQuality;

        public PlaybackMetadata build() {
            return new PlaybackMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackMetadata> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final LinearData.Parser mLinearDataParser;
        private final SimpleParsers.StringParser mStringParser;
        private final EnumParser<VideoMaterialType> mVideoMaterialTypeParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLinearDataParser = new LinearData.Parser(objectMapper);
            this.mVideoMaterialTypeParser = EnumParser.newParser(VideoMaterialType.class);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            LinearData linearData;
            VideoQuality videoQuality;
            VideoMaterialType videoMaterialType;
            Boolean bool;
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -594463862:
                            if (next.equals("legacyOfferAsin")) {
                                b = 1;
                            }
                            break;
                        case -539491044:
                            if (next.equals("videoMaterialType")) {
                                b = 5;
                            }
                            break;
                        case -428637969:
                            if (next.equals("isLinear")) {
                                b = 6;
                            }
                            break;
                        case -376764546:
                            if (next.equals("maxAllowedLookbackDuration")) {
                                b = 2;
                            }
                            break;
                        case 37566938:
                            if (next.equals("playbackConsumptionType")) {
                                b = 0;
                            }
                            break;
                        case 210819183:
                            if (next.equals("linearData")) {
                                b = 3;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 4;
                            }
                            break;
                    }
                    str = null;
                    bool = null;
                    videoMaterialType = null;
                    videoQuality = null;
                    linearData = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playbackConsumptionType = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.legacyOfferAsin = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.maxAllowedLookbackDuration = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            linearData = this.mLinearDataParser.parse(jsonNode2);
                        }
                        builder.linearData = linearData;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            videoMaterialType = (VideoMaterialType) this.mVideoMaterialTypeParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = videoMaterialType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isLinear = bool;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -594463862:
                                    if (currentName.equals("legacyOfferAsin")) {
                                        b = 1;
                                    }
                                    break;
                                case -539491044:
                                    if (currentName.equals("videoMaterialType")) {
                                        b = 5;
                                    }
                                    break;
                                case -428637969:
                                    if (currentName.equals("isLinear")) {
                                        b = 6;
                                    }
                                    break;
                                case -376764546:
                                    if (currentName.equals("maxAllowedLookbackDuration")) {
                                        b = 2;
                                    }
                                    break;
                                case 37566938:
                                    if (currentName.equals("playbackConsumptionType")) {
                                        b = 0;
                                    }
                                    break;
                                case 210819183:
                                    if (currentName.equals("linearData")) {
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
                            Boolean bool = null;
                            VideoMaterialType videoMaterialType = null;
                            VideoQuality videoQuality = null;
                            LinearData linearData = null;
                            String str2 = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.playbackConsumptionType = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.legacyOfferAsin = str3;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.maxAllowedLookbackDuration = str2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        linearData = this.mLinearDataParser.parse(jsonParser);
                                    }
                                    builder.linearData = linearData;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = videoQuality;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoMaterialType = (VideoMaterialType) this.mVideoMaterialTypeParser.parse(jsonParser);
                                    }
                                    builder.videoMaterialType = videoMaterialType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.isLinear = bool;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
