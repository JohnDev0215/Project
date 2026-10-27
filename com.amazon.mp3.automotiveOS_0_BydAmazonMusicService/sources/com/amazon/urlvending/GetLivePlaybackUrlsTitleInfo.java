package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.types.LiveManifestType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsTitleInfo {
    public final Optional<String> assetMaxBitrate;
    public final Optional<String> assetMinBitrate;
    public final Optional<String> assetResolution;
    public final Optional<String> audioTrackId;
    public final Optional<String> contentId;
    public final Optional<String> daiId;
    public final Optional<String> iso8601DurationMaximum;
    public final Optional<String> languageFormat;
    public final Optional<String> legacyOfferGroupId;
    public final Optional<ImmutableList<LiveManifestType>> liveManifestTypes;
    public final Optional<String> playableVideoId;
    public final Optional<String> subtitleFormat;

    private GetLivePlaybackUrlsTitleInfo(Builder builder) {
        this.assetResolution = Optional.fromNullable(builder.assetResolution);
        this.liveManifestTypes = Optional.fromNullable(builder.liveManifestTypes);
        this.assetMinBitrate = Optional.fromNullable(builder.assetMinBitrate);
        this.daiId = Optional.fromNullable(builder.daiId);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.iso8601DurationMaximum = Optional.fromNullable(builder.iso8601DurationMaximum);
        this.playableVideoId = Optional.fromNullable(builder.playableVideoId);
        this.legacyOfferGroupId = Optional.fromNullable(builder.legacyOfferGroupId);
        this.assetMaxBitrate = Optional.fromNullable(builder.assetMaxBitrate);
        this.subtitleFormat = Optional.fromNullable(builder.subtitleFormat);
        this.languageFormat = Optional.fromNullable(builder.languageFormat);
        this.contentId = Optional.fromNullable(builder.contentId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsTitleInfo)) {
            return false;
        }
        GetLivePlaybackUrlsTitleInfo getLivePlaybackUrlsTitleInfo = (GetLivePlaybackUrlsTitleInfo) obj;
        return Objects.equal(this.assetResolution, getLivePlaybackUrlsTitleInfo.assetResolution) && Objects.equal(this.liveManifestTypes, getLivePlaybackUrlsTitleInfo.liveManifestTypes) && Objects.equal(this.assetMinBitrate, getLivePlaybackUrlsTitleInfo.assetMinBitrate) && Objects.equal(this.daiId, getLivePlaybackUrlsTitleInfo.daiId) && Objects.equal(this.audioTrackId, getLivePlaybackUrlsTitleInfo.audioTrackId) && Objects.equal(this.iso8601DurationMaximum, getLivePlaybackUrlsTitleInfo.iso8601DurationMaximum) && Objects.equal(this.playableVideoId, getLivePlaybackUrlsTitleInfo.playableVideoId) && Objects.equal(this.legacyOfferGroupId, getLivePlaybackUrlsTitleInfo.legacyOfferGroupId) && Objects.equal(this.assetMaxBitrate, getLivePlaybackUrlsTitleInfo.assetMaxBitrate) && Objects.equal(this.subtitleFormat, getLivePlaybackUrlsTitleInfo.subtitleFormat) && Objects.equal(this.languageFormat, getLivePlaybackUrlsTitleInfo.languageFormat) && Objects.equal(this.contentId, getLivePlaybackUrlsTitleInfo.contentId);
    }

    public int hashCode() {
        return Objects.hashCode(this.assetResolution, this.liveManifestTypes, this.assetMinBitrate, this.daiId, this.audioTrackId, this.iso8601DurationMaximum, this.playableVideoId, this.legacyOfferGroupId, this.assetMaxBitrate, this.subtitleFormat, this.languageFormat, this.contentId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("assetResolution", this.assetResolution).add("liveManifestTypes", this.liveManifestTypes).add("assetMinBitrate", this.assetMinBitrate).add("daiId", this.daiId).add("audioTrackId", this.audioTrackId).add("iso8601DurationMaximum", this.iso8601DurationMaximum).add("playableVideoId", this.playableVideoId).add("legacyOfferGroupId", this.legacyOfferGroupId).add("assetMaxBitrate", this.assetMaxBitrate).add("subtitleFormat", this.subtitleFormat).add("languageFormat", this.languageFormat).add("contentId", this.contentId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String assetMaxBitrate;
        public String assetMinBitrate;
        public String assetResolution;
        public String audioTrackId;
        public String contentId;
        public String daiId;
        public String iso8601DurationMaximum;
        public String languageFormat;
        public String legacyOfferGroupId;
        public ImmutableList<LiveManifestType> liveManifestTypes;
        public String playableVideoId;
        public String subtitleFormat;

        public GetLivePlaybackUrlsTitleInfo build() {
            return new GetLivePlaybackUrlsTitleInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsTitleInfo> {
        private final ListParser<LiveManifestType> mGetLivePlaybackUrlsLiveManifestTypesParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetLivePlaybackUrlsLiveManifestTypesParser = ListParser.newParser(EnumParser.newParser(LiveManifestType.class));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsTitleInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsTitleInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsTitleInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsTitleInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsTitleInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            ImmutableList<LiveManifestType> immutableList;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsTitleInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2054152706:
                            if (next.equals("liveManifestTypes")) {
                                b = 1;
                            }
                            break;
                        case -1847836536:
                            if (next.equals("playableVideoId")) {
                                b = 6;
                            }
                            break;
                        case -616458897:
                            if (next.equals("languageFormat")) {
                                b = 10;
                            }
                            break;
                        case -552980359:
                            if (next.equals("assetMaxBitrate")) {
                                b = 8;
                            }
                            break;
                        case -515466137:
                            if (next.equals("legacyOfferGroupId")) {
                                b = 7;
                            }
                            break;
                        case -407108748:
                            if (next.equals("contentId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 95345095:
                            if (next.equals("daiId")) {
                                b = 3;
                            }
                            break;
                        case 1070289852:
                            if (next.equals("assetResolution")) {
                                b = 0;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 4;
                            }
                            break;
                        case 1788476872:
                            if (next.equals("iso8601DurationMaximum")) {
                                b = 5;
                            }
                            break;
                        case 1919018955:
                            if (next.equals("assetMinBitrate")) {
                                b = 2;
                            }
                            break;
                        case 1927331375:
                            if (next.equals("subtitleFormat")) {
                                b = 9;
                            }
                            break;
                    }
                    str = null;
                    str11 = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsTitleInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetResolution = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mGetLivePlaybackUrlsLiveManifestTypesParser.parse(jsonNode2);
                        }
                        builder.liveManifestTypes = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetMinBitrate = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.daiId = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.iso8601DurationMaximum = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.playableVideoId = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.legacyOfferGroupId = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetMaxBitrate = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtitleFormat = str9;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.languageFormat = str10;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str11 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.contentId = str11;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsTitleInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetLivePlaybackUrlsTitleInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2054152706:
                                    if (currentName.equals("liveManifestTypes")) {
                                        b = 1;
                                    }
                                    break;
                                case -1847836536:
                                    if (currentName.equals("playableVideoId")) {
                                        b = 6;
                                    }
                                    break;
                                case -616458897:
                                    if (currentName.equals("languageFormat")) {
                                        b = 10;
                                    }
                                    break;
                                case -552980359:
                                    if (currentName.equals("assetMaxBitrate")) {
                                        b = 8;
                                    }
                                    break;
                                case -515466137:
                                    if (currentName.equals("legacyOfferGroupId")) {
                                        b = 7;
                                    }
                                    break;
                                case -407108748:
                                    if (currentName.equals("contentId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 95345095:
                                    if (currentName.equals("daiId")) {
                                        b = 3;
                                    }
                                    break;
                                case 1070289852:
                                    if (currentName.equals("assetResolution")) {
                                        b = 0;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 4;
                                    }
                                    break;
                                case 1788476872:
                                    if (currentName.equals("iso8601DurationMaximum")) {
                                        b = 5;
                                    }
                                    break;
                                case 1919018955:
                                    if (currentName.equals("assetMinBitrate")) {
                                        b = 2;
                                    }
                                    break;
                                case 1927331375:
                                    if (currentName.equals("subtitleFormat")) {
                                        b = 9;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            String str9 = null;
                            String str10 = null;
                            String str11 = null;
                            ImmutableList<LiveManifestType> immutableList = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetResolution = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mGetLivePlaybackUrlsLiveManifestTypesParser.parse(jsonParser);
                                    }
                                    builder.liveManifestTypes = immutableList;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str11 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetMinBitrate = str11;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.daiId = str10;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str9;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.iso8601DurationMaximum = str8;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.playableVideoId = str7;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.legacyOfferGroupId = str6;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetMaxBitrate = str5;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtitleFormat = str4;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.languageFormat = str3;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.contentId = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsTitleInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsTitleInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
