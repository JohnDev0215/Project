package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.Asset;
import com.amazon.urlvending.AudioTrackMetadata;
import com.amazon.urlvending.DebStep;
import com.amazon.urlvending.SubtitleUrl;
import com.amazon.urlvending.UrlSet;
import com.amazon.urlvending.types.UrlSetsMapKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class UrlSets {
    public final Optional<Asset> asset;
    public final Optional<ImmutableList<AudioTrackMetadata>> audioTrackMetadataList;
    public final Optional<ImmutableList<DebStep>> debInfo;
    public final Optional<String> defaultAudioTrackId;
    public final Optional<String> fulfillmentSessionId;
    public final Optional<String> masterSessionId;
    public final Optional<ImmutableList<SubtitleUrl>> subtitleUrls;
    public final Optional<ImmutableMap<UrlSetsMapKey, UrlSet>> urlSetsMap;
    public final Optional<String> urlSetsMapKeyType;

    private UrlSets(Builder builder) {
        this.defaultAudioTrackId = Optional.fromNullable(builder.defaultAudioTrackId);
        this.asset = Optional.fromNullable(builder.asset);
        this.fulfillmentSessionId = Optional.fromNullable(builder.fulfillmentSessionId);
        this.urlSetsMap = Optional.fromNullable(builder.urlSetsMap);
        this.masterSessionId = Optional.fromNullable(builder.masterSessionId);
        this.debInfo = Optional.fromNullable(builder.debInfo);
        this.subtitleUrls = Optional.fromNullable(builder.subtitleUrls);
        this.audioTrackMetadataList = Optional.fromNullable(builder.audioTrackMetadataList);
        this.urlSetsMapKeyType = Optional.fromNullable(builder.urlSetsMapKeyType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UrlSets)) {
            return false;
        }
        UrlSets urlSets = (UrlSets) obj;
        return Objects.equal(this.defaultAudioTrackId, urlSets.defaultAudioTrackId) && Objects.equal(this.asset, urlSets.asset) && Objects.equal(this.fulfillmentSessionId, urlSets.fulfillmentSessionId) && Objects.equal(this.urlSetsMap, urlSets.urlSetsMap) && Objects.equal(this.masterSessionId, urlSets.masterSessionId) && Objects.equal(this.debInfo, urlSets.debInfo) && Objects.equal(this.subtitleUrls, urlSets.subtitleUrls) && Objects.equal(this.audioTrackMetadataList, urlSets.audioTrackMetadataList) && Objects.equal(this.urlSetsMapKeyType, urlSets.urlSetsMapKeyType);
    }

    public int hashCode() {
        return Objects.hashCode(this.defaultAudioTrackId, this.asset, this.fulfillmentSessionId, this.urlSetsMap, this.masterSessionId, this.debInfo, this.subtitleUrls, this.audioTrackMetadataList, this.urlSetsMapKeyType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("defaultAudioTrackId", this.defaultAudioTrackId).add("asset", this.asset).add("fulfillmentSessionId", this.fulfillmentSessionId).add("urlSetsMap", this.urlSetsMap).add("masterSessionId", this.masterSessionId).add("debInfo", this.debInfo).add("subtitleUrls", this.subtitleUrls).add("audioTrackMetadataList", this.audioTrackMetadataList).add("urlSetsMapKeyType", this.urlSetsMapKeyType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Asset asset;
        public ImmutableList<AudioTrackMetadata> audioTrackMetadataList;
        public ImmutableList<DebStep> debInfo;
        public String defaultAudioTrackId;
        public String fulfillmentSessionId;
        public String masterSessionId;
        public ImmutableList<SubtitleUrl> subtitleUrls;
        public ImmutableMap<UrlSetsMapKey, UrlSet> urlSetsMap;
        public String urlSetsMapKeyType;

        public UrlSets build() {
            return new UrlSets(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<UrlSets> {
        private final Asset.Parser mAssetParser;
        private final ListParser<AudioTrackMetadata> mAudioTrackMetadataListParser;
        private final ListParser<DebStep> mDebInfoParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SubtitleUrl> mSubtitleUrlListParser;
        private final MapParser<UrlSetsMapKey, UrlSet> mUrlSetsMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAssetParser = new Asset.Parser(objectMapper);
            this.mDebInfoParser = ListParser.newParser(new DebStep.Parser(objectMapper));
            this.mSubtitleUrlListParser = ListParser.newParser(new SubtitleUrl.Parser(objectMapper));
            this.mAudioTrackMetadataListParser = ListParser.newParser(new AudioTrackMetadata.Parser(objectMapper));
            this.mUrlSetsMapParser = MapParser.newParser(EnumParser.newParser(UrlSetsMapKey.class), new UrlSet.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public UrlSets parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlSets:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public UrlSets parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlSets:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlSets parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Asset asset;
            String str2;
            ImmutableMap<UrlSetsMapKey, UrlSet> immutableMap;
            String str3;
            ImmutableList<DebStep> immutableList;
            ImmutableList<SubtitleUrl> immutableList2;
            ImmutableList<AudioTrackMetadata> immutableList3;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "UrlSets");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2044469988:
                            if (next.equals("subtitleUrls")) {
                                b = 6;
                            }
                            break;
                        case -1687436785:
                            if (next.equals("masterSessionId")) {
                                b = 4;
                            }
                            break;
                        case -274577901:
                            if (next.equals("fulfillmentSessionId")) {
                                b = 2;
                            }
                            break;
                        case 93121264:
                            if (next.equals("asset")) {
                                b = 1;
                            }
                            break;
                        case 617228322:
                            if (next.equals("audioTrackMetadataList")) {
                                b = 7;
                            }
                            break;
                        case 975432637:
                            if (next.equals("urlSetsMapKeyType")) {
                                b = 8;
                            }
                            break;
                        case 1486870236:
                            if (next.equals("urlSetsMap")) {
                                b = 3;
                            }
                            break;
                        case 1508230129:
                            if (next.equals("defaultAudioTrackId")) {
                                b = 0;
                            }
                            break;
                        case 1540387919:
                            if (next.equals("debInfo")) {
                                b = 5;
                            }
                            break;
                    }
                    str = null;
                    str4 = null;
                    immutableList3 = null;
                    immutableList2 = null;
                    immutableList = null;
                    str3 = null;
                    immutableMap = null;
                    str2 = null;
                    asset = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing UrlSets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.defaultAudioTrackId = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            asset = this.mAssetParser.parse(jsonNode2);
                        }
                        builder.asset = asset;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.fulfillmentSessionId = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mUrlSetsMapParser.parse(jsonNode2);
                        }
                        builder.urlSetsMap = immutableMap;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.masterSessionId = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mDebInfoParser.parse(jsonNode2);
                        }
                        builder.debInfo = immutableList;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSubtitleUrlListParser.parse(jsonNode2);
                        }
                        builder.subtitleUrls = immutableList2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mAudioTrackMetadataListParser.parse(jsonNode2);
                        }
                        builder.audioTrackMetadataList = immutableList3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlSetsMapKeyType = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing UrlSets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlSets parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2044469988:
                                    if (currentName.equals("subtitleUrls")) {
                                        b = 6;
                                    }
                                    break;
                                case -1687436785:
                                    if (currentName.equals("masterSessionId")) {
                                        b = 4;
                                    }
                                    break;
                                case -274577901:
                                    if (currentName.equals("fulfillmentSessionId")) {
                                        b = 2;
                                    }
                                    break;
                                case 93121264:
                                    if (currentName.equals("asset")) {
                                        b = 1;
                                    }
                                    break;
                                case 617228322:
                                    if (currentName.equals("audioTrackMetadataList")) {
                                        b = 7;
                                    }
                                    break;
                                case 975432637:
                                    if (currentName.equals("urlSetsMapKeyType")) {
                                        b = 8;
                                    }
                                    break;
                                case 1486870236:
                                    if (currentName.equals("urlSetsMap")) {
                                        b = 3;
                                    }
                                    break;
                                case 1508230129:
                                    if (currentName.equals("defaultAudioTrackId")) {
                                        b = 0;
                                    }
                                    break;
                                case 1540387919:
                                    if (currentName.equals("debInfo")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableList<AudioTrackMetadata> immutableList = null;
                            ImmutableList<SubtitleUrl> immutableList2 = null;
                            ImmutableList<DebStep> immutableList3 = null;
                            String str3 = null;
                            ImmutableMap<UrlSetsMapKey, UrlSet> immutableMap = null;
                            String str4 = null;
                            Asset asset = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.defaultAudioTrackId = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        asset = this.mAssetParser.parse(jsonParser);
                                    }
                                    builder.asset = asset;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.fulfillmentSessionId = str4;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mUrlSetsMapParser.parse(jsonParser);
                                    }
                                    builder.urlSetsMap = immutableMap;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.masterSessionId = str3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mDebInfoParser.parse(jsonParser);
                                    }
                                    builder.debInfo = immutableList3;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mSubtitleUrlListParser.parse(jsonParser);
                                    }
                                    builder.subtitleUrls = immutableList2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAudioTrackMetadataListParser.parse(jsonParser);
                                    }
                                    builder.audioTrackMetadataList = immutableList;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.urlSetsMapKeyType = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing UrlSets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing UrlSets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
