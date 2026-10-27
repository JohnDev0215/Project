package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.types.AssetIdType;
import com.amazon.urlvending.types.AssetQuality;
import com.amazon.urlvending.types.AssetType;
import com.amazon.urlvending.types.LanguageFeature;
import com.amazon.urlvending.types.Resolution;
import com.amazon.urlvending.types.SubtitleFormat;
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
public class Asset {
    public final Optional<String> assetId;
    public final Optional<AssetIdType> assetIdType;
    public final Optional<String> assetMaxBitrate;
    public final Optional<String> assetMinBitrate;
    public final Optional<String> assetOfferType;

    @Deprecated
    public final Optional<AssetQuality> assetQuality;
    public final Optional<Resolution> assetResolution;
    public final Optional<String> assetTag;

    @Deprecated
    public final Optional<AssetType> assetType;
    public final Optional<String> audioTrackId;
    public final Optional<String> iso8601DurationMaximum;
    public final Optional<LanguageFeature> languageFeature;
    public final Optional<ImmutableList<String>> liveManifestTypes;
    public final Optional<String> policyId;
    public final Optional<String> pvid;
    public final Optional<SubtitleFormat> subtitleFormat;
    public final Optional<ImmutableList<String>> supportedThumbnailRepresentations;

    private Asset(Builder builder) {
        this.assetQuality = Optional.fromNullable(builder.assetQuality);
        this.supportedThumbnailRepresentations = Optional.fromNullable(builder.supportedThumbnailRepresentations);
        this.assetIdType = Optional.fromNullable(builder.assetIdType);
        this.subtitleFormat = Optional.fromNullable(builder.subtitleFormat);
        this.policyId = Optional.fromNullable(builder.policyId);
        this.assetMinBitrate = Optional.fromNullable(builder.assetMinBitrate);
        this.assetResolution = Optional.fromNullable(builder.assetResolution);
        this.assetTag = Optional.fromNullable(builder.assetTag);
        this.iso8601DurationMaximum = Optional.fromNullable(builder.iso8601DurationMaximum);
        this.languageFeature = Optional.fromNullable(builder.languageFeature);
        this.assetMaxBitrate = Optional.fromNullable(builder.assetMaxBitrate);
        this.assetId = Optional.fromNullable(builder.assetId);
        this.assetOfferType = Optional.fromNullable(builder.assetOfferType);
        this.assetType = Optional.fromNullable(builder.assetType);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.pvid = Optional.fromNullable(builder.pvid);
        this.liveManifestTypes = Optional.fromNullable(builder.liveManifestTypes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) obj;
        return Objects.equal(this.assetQuality, asset.assetQuality) && Objects.equal(this.supportedThumbnailRepresentations, asset.supportedThumbnailRepresentations) && Objects.equal(this.assetIdType, asset.assetIdType) && Objects.equal(this.subtitleFormat, asset.subtitleFormat) && Objects.equal(this.policyId, asset.policyId) && Objects.equal(this.assetMinBitrate, asset.assetMinBitrate) && Objects.equal(this.assetResolution, asset.assetResolution) && Objects.equal(this.assetTag, asset.assetTag) && Objects.equal(this.iso8601DurationMaximum, asset.iso8601DurationMaximum) && Objects.equal(this.languageFeature, asset.languageFeature) && Objects.equal(this.assetMaxBitrate, asset.assetMaxBitrate) && Objects.equal(this.assetId, asset.assetId) && Objects.equal(this.assetOfferType, asset.assetOfferType) && Objects.equal(this.assetType, asset.assetType) && Objects.equal(this.audioTrackId, asset.audioTrackId) && Objects.equal(this.pvid, asset.pvid) && Objects.equal(this.liveManifestTypes, asset.liveManifestTypes);
    }

    public int hashCode() {
        return Objects.hashCode(this.assetQuality, this.supportedThumbnailRepresentations, this.assetIdType, this.subtitleFormat, this.policyId, this.assetMinBitrate, this.assetResolution, this.assetTag, this.iso8601DurationMaximum, this.languageFeature, this.assetMaxBitrate, this.assetId, this.assetOfferType, this.assetType, this.audioTrackId, this.pvid, this.liveManifestTypes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("assetQuality", this.assetQuality).add("supportedThumbnailRepresentations", this.supportedThumbnailRepresentations).add("assetIdType", this.assetIdType).add("subtitleFormat", this.subtitleFormat).add("policyId", this.policyId).add("assetMinBitrate", this.assetMinBitrate).add("assetResolution", this.assetResolution).add("assetTag", this.assetTag).add("iso8601DurationMaximum", this.iso8601DurationMaximum).add("languageFeature", this.languageFeature).add("assetMaxBitrate", this.assetMaxBitrate).add("assetId", this.assetId).add("assetOfferType", this.assetOfferType).add("assetType", this.assetType).add("audioTrackId", this.audioTrackId).add("pvid", this.pvid).add("liveManifestTypes", this.liveManifestTypes).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String assetId;
        public AssetIdType assetIdType;
        public String assetMaxBitrate;
        public String assetMinBitrate;
        public String assetOfferType;

        @Deprecated
        public AssetQuality assetQuality;
        public Resolution assetResolution;
        public String assetTag;

        @Deprecated
        public AssetType assetType;
        public String audioTrackId;
        public String iso8601DurationMaximum;
        public LanguageFeature languageFeature;
        public ImmutableList<String> liveManifestTypes;
        public String policyId;
        public String pvid;
        public SubtitleFormat subtitleFormat;
        public ImmutableList<String> supportedThumbnailRepresentations;

        public Asset build() {
            return new Asset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Asset> {
        private final EnumParser<AssetIdType> mAssetIdTypeParser;
        private final EnumParser<AssetQuality> mAssetQualityParser;
        private final EnumParser<AssetType> mAssetTypeParser;
        private final EnumParser<LanguageFeature> mLanguageFeatureParser;
        private final ListParser<String> mLiveManifestTypeListParser;
        private final EnumParser<Resolution> mResolutionParser;
        private final SimpleParsers.StringParser mStringParser;
        private final EnumParser<SubtitleFormat> mSubtitleFormatParser;
        private final ListParser<String> mThumbnailRepresentationListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mResolutionParser = EnumParser.newParser(Resolution.class);
            this.mAssetQualityParser = EnumParser.newParser(AssetQuality.class);
            this.mLanguageFeatureParser = EnumParser.newParser(LanguageFeature.class);
            this.mAssetIdTypeParser = EnumParser.newParser(AssetIdType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mThumbnailRepresentationListParser = ListParser.newParser(stringParser);
            this.mLiveManifestTypeListParser = ListParser.newParser(stringParser);
            this.mSubtitleFormatParser = EnumParser.newParser(SubtitleFormat.class);
            this.mAssetTypeParser = EnumParser.newParser(AssetType.class);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Asset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Asset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Asset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Asset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Asset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            AssetQuality assetQuality;
            ImmutableList<String> immutableList;
            AssetIdType assetIdType;
            SubtitleFormat subtitleFormat;
            String str;
            String str2;
            Resolution resolution;
            String str3;
            String str4;
            LanguageFeature languageFeature;
            String str5;
            String str6;
            String str7;
            AssetType assetType;
            String str8;
            String str9;
            ImmutableList<String> immutableList2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Asset");
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
                                b = 16;
                            }
                            break;
                        case -1987177818:
                            if (next.equals("assetOfferType")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -1524940817:
                            if (next.equals("assetQuality")) {
                                b = 0;
                            }
                            break;
                        case -704776149:
                            if (next.equals("assetId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -552980359:
                            if (next.equals("assetMaxBitrate")) {
                                b = 10;
                            }
                            break;
                        case -373213558:
                            if (next.equals("assetTag")) {
                                b = 7;
                            }
                            break;
                        case 3453345:
                            if (next.equals("pvid")) {
                                b = 15;
                            }
                            break;
                        case 328597896:
                            if (next.equals("supportedThumbnailRepresentations")) {
                                b = 1;
                            }
                            break;
                        case 546908653:
                            if (next.equals("policyId")) {
                                b = 4;
                            }
                            break;
                        case 952626693:
                            if (next.equals("assetIdType")) {
                                b = 2;
                            }
                            break;
                        case 1070289852:
                            if (next.equals("assetResolution")) {
                                b = 6;
                            }
                            break;
                        case 1315305034:
                            if (next.equals("assetType")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 1788476872:
                            if (next.equals("iso8601DurationMaximum")) {
                                b = 8;
                            }
                            break;
                        case 1919018955:
                            if (next.equals("assetMinBitrate")) {
                                b = 5;
                            }
                            break;
                        case 1927331375:
                            if (next.equals("subtitleFormat")) {
                                b = 3;
                            }
                            break;
                        case 2062847102:
                            if (next.equals("languageFeature")) {
                                b = 9;
                            }
                            break;
                    }
                    assetQuality = null;
                    immutableList2 = null;
                    str9 = null;
                    str8 = null;
                    assetType = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    languageFeature = null;
                    str4 = null;
                    str3 = null;
                    resolution = null;
                    str2 = null;
                    str = null;
                    subtitleFormat = null;
                    assetIdType = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            assetQuality = (AssetQuality) this.mAssetQualityParser.parse(jsonNode2);
                        }
                        builder.assetQuality = assetQuality;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mThumbnailRepresentationListParser.parse(jsonNode2);
                        }
                        builder.supportedThumbnailRepresentations = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            assetIdType = (AssetIdType) this.mAssetIdTypeParser.parse(jsonNode2);
                        }
                        builder.assetIdType = assetIdType;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            subtitleFormat = (SubtitleFormat) this.mSubtitleFormatParser.parse(jsonNode2);
                        }
                        builder.subtitleFormat = subtitleFormat;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.policyId = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetMinBitrate = str2;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            resolution = (Resolution) this.mResolutionParser.parse(jsonNode2);
                        }
                        builder.assetResolution = resolution;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetTag = str3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.iso8601DurationMaximum = str4;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            languageFeature = (LanguageFeature) this.mLanguageFeatureParser.parse(jsonNode2);
                        }
                        builder.languageFeature = languageFeature;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetMaxBitrate = str5;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetId = str6;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.assetOfferType = str7;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            assetType = (AssetType) this.mAssetTypeParser.parse(jsonNode2);
                        }
                        builder.assetType = assetType;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str8;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pvid = str9;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mLiveManifestTypeListParser.parse(jsonNode2);
                        }
                        builder.liveManifestTypes = immutableList2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Asset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 16;
                                    }
                                    break;
                                case -1987177818:
                                    if (currentName.equals("assetOfferType")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -1524940817:
                                    if (currentName.equals("assetQuality")) {
                                        b = 0;
                                    }
                                    break;
                                case -704776149:
                                    if (currentName.equals("assetId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -552980359:
                                    if (currentName.equals("assetMaxBitrate")) {
                                        b = 10;
                                    }
                                    break;
                                case -373213558:
                                    if (currentName.equals("assetTag")) {
                                        b = 7;
                                    }
                                    break;
                                case 3453345:
                                    if (currentName.equals("pvid")) {
                                        b = 15;
                                    }
                                    break;
                                case 328597896:
                                    if (currentName.equals("supportedThumbnailRepresentations")) {
                                        b = 1;
                                    }
                                    break;
                                case 546908653:
                                    if (currentName.equals("policyId")) {
                                        b = 4;
                                    }
                                    break;
                                case 952626693:
                                    if (currentName.equals("assetIdType")) {
                                        b = 2;
                                    }
                                    break;
                                case 1070289852:
                                    if (currentName.equals("assetResolution")) {
                                        b = 6;
                                    }
                                    break;
                                case 1315305034:
                                    if (currentName.equals("assetType")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 1788476872:
                                    if (currentName.equals("iso8601DurationMaximum")) {
                                        b = 8;
                                    }
                                    break;
                                case 1919018955:
                                    if (currentName.equals("assetMinBitrate")) {
                                        b = 5;
                                    }
                                    break;
                                case 1927331375:
                                    if (currentName.equals("subtitleFormat")) {
                                        b = 3;
                                    }
                                    break;
                                case 2062847102:
                                    if (currentName.equals("languageFeature")) {
                                        b = 9;
                                    }
                                    break;
                            }
                            AssetQuality assetQuality = null;
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            String str2 = null;
                            AssetType assetType = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            LanguageFeature languageFeature = null;
                            String str6 = null;
                            String str7 = null;
                            Resolution resolution = null;
                            String str8 = null;
                            String str9 = null;
                            SubtitleFormat subtitleFormat = null;
                            AssetIdType assetIdType = null;
                            ImmutableList<String> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        assetQuality = (AssetQuality) this.mAssetQualityParser.parse(jsonParser);
                                    }
                                    builder.assetQuality = assetQuality;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mThumbnailRepresentationListParser.parse(jsonParser);
                                    }
                                    builder.supportedThumbnailRepresentations = immutableList2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        assetIdType = (AssetIdType) this.mAssetIdTypeParser.parse(jsonParser);
                                    }
                                    builder.assetIdType = assetIdType;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        subtitleFormat = (SubtitleFormat) this.mSubtitleFormatParser.parse(jsonParser);
                                    }
                                    builder.subtitleFormat = subtitleFormat;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.policyId = str9;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetMinBitrate = str8;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        resolution = (Resolution) this.mResolutionParser.parse(jsonParser);
                                    }
                                    builder.assetResolution = resolution;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetTag = str7;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.iso8601DurationMaximum = str6;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        languageFeature = (LanguageFeature) this.mLanguageFeatureParser.parse(jsonParser);
                                    }
                                    builder.languageFeature = languageFeature;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetMaxBitrate = str5;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetId = str4;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.assetOfferType = str3;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        assetType = (AssetType) this.mAssetTypeParser.parse(jsonParser);
                                    }
                                    builder.assetType = assetType;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str2;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.pvid = str;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mLiveManifestTypeListParser.parse(jsonParser);
                                    }
                                    builder.liveManifestTypes = immutableList;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Asset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
