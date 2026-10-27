package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.AudioQuality;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.atvplaybackdevice.types.VideoQuality;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RequestTitleRendition {
    public final Optional<String> audioTrackId;
    public final Optional<AudioQuality> desiredAudioQuality;
    public final Optional<VideoQuality> desiredVideoQuality;
    public final Optional<String> siteSectionId;
    public final String titleId;
    public final VideoMaterialType videoMaterialType;
    public final Optional<String> videoMaxBitrate;
    public final Optional<String> videoMinBitrate;

    private RequestTitleRendition(Builder builder) {
        this.desiredVideoQuality = Optional.fromNullable(builder.desiredVideoQuality);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.videoMinBitrate = Optional.fromNullable(builder.videoMinBitrate);
        this.titleId = (String) Preconditions.checkNotNull(builder.titleId, "Unexpected null field: titleId");
        this.videoMaterialType = (VideoMaterialType) Preconditions.checkNotNull(builder.videoMaterialType, "Unexpected null field: videoMaterialType");
        this.siteSectionId = Optional.fromNullable(builder.siteSectionId);
        this.videoMaxBitrate = Optional.fromNullable(builder.videoMaxBitrate);
        this.desiredAudioQuality = Optional.fromNullable(builder.desiredAudioQuality);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestTitleRendition)) {
            return false;
        }
        RequestTitleRendition requestTitleRendition = (RequestTitleRendition) obj;
        return Objects.equal(this.desiredVideoQuality, requestTitleRendition.desiredVideoQuality) && Objects.equal(this.audioTrackId, requestTitleRendition.audioTrackId) && Objects.equal(this.videoMinBitrate, requestTitleRendition.videoMinBitrate) && Objects.equal(this.titleId, requestTitleRendition.titleId) && Objects.equal(this.videoMaterialType, requestTitleRendition.videoMaterialType) && Objects.equal(this.siteSectionId, requestTitleRendition.siteSectionId) && Objects.equal(this.videoMaxBitrate, requestTitleRendition.videoMaxBitrate) && Objects.equal(this.desiredAudioQuality, requestTitleRendition.desiredAudioQuality);
    }

    public int hashCode() {
        return Objects.hashCode(this.desiredVideoQuality, this.audioTrackId, this.videoMinBitrate, this.titleId, this.videoMaterialType, this.siteSectionId, this.videoMaxBitrate, this.desiredAudioQuality);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("desiredVideoQuality", this.desiredVideoQuality).add("audioTrackId", this.audioTrackId).add("videoMinBitrate", this.videoMinBitrate).add("titleId", this.titleId).add("videoMaterialType", this.videoMaterialType).add("siteSectionId", this.siteSectionId).add("videoMaxBitrate", this.videoMaxBitrate).add("desiredAudioQuality", this.desiredAudioQuality).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioTrackId;
        public AudioQuality desiredAudioQuality;
        public VideoQuality desiredVideoQuality;
        public String siteSectionId;
        public String titleId;
        public VideoMaterialType videoMaterialType;
        public String videoMaxBitrate;
        public String videoMinBitrate;

        public RequestTitleRendition build() {
            return new RequestTitleRendition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RequestTitleRendition> {
        private final EnumParser<AudioQuality> maudioQualityParser;
        private final SimpleParsers.StringParser maudioTrackIdParser;
        private final SimpleParsers.StringParser mstringTargetParser;
        private final SimpleParsers.StringParser mtitleIdParser;
        private final EnumParser<VideoMaterialType> mvideoMaterialTypeParser;
        private final EnumParser<VideoQuality> mvideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mvideoMaterialTypeParser = EnumParser.newParser(VideoMaterialType.class);
            this.mvideoQualityParser = EnumParser.newParser(VideoQuality.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.maudioTrackIdParser = stringParser;
            this.mstringTargetParser = stringParser;
            this.mtitleIdParser = stringParser;
            this.maudioQualityParser = EnumParser.newParser(AudioQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RequestTitleRendition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RequestTitleRendition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RequestTitleRendition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RequestTitleRendition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RequestTitleRendition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            VideoQuality videoQuality;
            String str;
            String str2;
            String str3;
            VideoMaterialType videoMaterialType;
            String str4;
            String str5;
            AudioQuality audioQuality;
            JsonParsingUtils.throwIfNotObject(jsonNode, "RequestTitleRendition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1992611772:
                            if (next.equals("videoMaxBitrate")) {
                                b = 6;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 3;
                            }
                            break;
                        case -1028539389:
                            if (next.equals("desiredAudioQuality")) {
                                b = 7;
                            }
                            break;
                        case -539491044:
                            if (next.equals("videoMaterialType")) {
                                b = 4;
                            }
                            break;
                        case 410660798:
                            if (next.equals("desiredVideoQuality")) {
                                b = 0;
                            }
                            break;
                        case 479387542:
                            if (next.equals("videoMinBitrate")) {
                                b = 2;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 1;
                            }
                            break;
                        case 1951458137:
                            if (next.equals("siteSectionId")) {
                                b = 5;
                            }
                            break;
                    }
                    videoQuality = null;
                    audioQuality = null;
                    str5 = null;
                    str4 = null;
                    videoMaterialType = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RequestTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonNode2);
                        }
                        builder.desiredVideoQuality = videoQuality;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.maudioTrackIdParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.videoMinBitrate = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mtitleIdParser.parse(jsonNode2);
                        }
                        builder.titleId = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            videoMaterialType = (VideoMaterialType) this.mvideoMaterialTypeParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = videoMaterialType;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.siteSectionId = str4;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.videoMaxBitrate = str5;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonNode2);
                        }
                        builder.desiredAudioQuality = audioQuality;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RequestTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
            JsonParsingUtils.checkNotNull(builder.videoMaterialType, this, "videoMaterialType");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RequestTitleRendition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1992611772:
                                    if (currentName.equals("videoMaxBitrate")) {
                                        b = 6;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 3;
                                    }
                                    break;
                                case -1028539389:
                                    if (currentName.equals("desiredAudioQuality")) {
                                        b = 7;
                                    }
                                    break;
                                case -539491044:
                                    if (currentName.equals("videoMaterialType")) {
                                        b = 4;
                                    }
                                    break;
                                case 410660798:
                                    if (currentName.equals("desiredVideoQuality")) {
                                        b = 0;
                                    }
                                    break;
                                case 479387542:
                                    if (currentName.equals("videoMinBitrate")) {
                                        b = 2;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 1;
                                    }
                                    break;
                                case 1951458137:
                                    if (currentName.equals("siteSectionId")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            VideoQuality videoQuality = null;
                            AudioQuality audioQuality = null;
                            String str = null;
                            String str2 = null;
                            VideoMaterialType videoMaterialType = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonParser);
                                    }
                                    builder.desiredVideoQuality = videoQuality;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.maudioTrackIdParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str5;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.videoMinBitrate = str4;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mtitleIdParser.parse(jsonParser);
                                    }
                                    builder.titleId = str3;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoMaterialType = (VideoMaterialType) this.mvideoMaterialTypeParser.parse(jsonParser);
                                    }
                                    builder.videoMaterialType = videoMaterialType;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.siteSectionId = str2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.videoMaxBitrate = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonParser);
                                    }
                                    builder.desiredAudioQuality = audioQuality;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RequestTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RequestTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
                    JsonParsingUtils.checkNotNull(builder.videoMaterialType, this, "videoMaterialType");
                    return builder.build();
                }
            }
        }
    }
}
