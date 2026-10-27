package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.AvUrlInfo;
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

/* JADX INFO: loaded from: classes5.dex */
public class AvUrlsCdnSet {
    public final Optional<String> audioTrackId;
    public final Optional<ImmutableList<AvUrlInfo>> avUrlInfoList;
    public final Optional<String> bitrateAdaption;
    public final Optional<String> cdn;
    public final Optional<String> cdnWeightsRank;
    public final Optional<String> drm;
    public final Optional<String> encode;
    public final Optional<String> hdrFormat;
    public final Optional<String> majorVersion;
    public final Optional<String> streamingTechnology;
    public final Optional<String> urlSetId;

    private AvUrlsCdnSet(Builder builder) {
        this.cdn = Optional.fromNullable(builder.cdn);
        this.bitrateAdaption = Optional.fromNullable(builder.bitrateAdaption);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.avUrlInfoList = Optional.fromNullable(builder.avUrlInfoList);
        this.majorVersion = Optional.fromNullable(builder.majorVersion);
        this.urlSetId = Optional.fromNullable(builder.urlSetId);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.encode = Optional.fromNullable(builder.encode);
        this.cdnWeightsRank = Optional.fromNullable(builder.cdnWeightsRank);
        this.drm = Optional.fromNullable(builder.drm);
        this.hdrFormat = Optional.fromNullable(builder.hdrFormat);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvUrlsCdnSet)) {
            return false;
        }
        AvUrlsCdnSet avUrlsCdnSet = (AvUrlsCdnSet) obj;
        return Objects.equal(this.cdn, avUrlsCdnSet.cdn) && Objects.equal(this.bitrateAdaption, avUrlsCdnSet.bitrateAdaption) && Objects.equal(this.streamingTechnology, avUrlsCdnSet.streamingTechnology) && Objects.equal(this.avUrlInfoList, avUrlsCdnSet.avUrlInfoList) && Objects.equal(this.majorVersion, avUrlsCdnSet.majorVersion) && Objects.equal(this.urlSetId, avUrlsCdnSet.urlSetId) && Objects.equal(this.audioTrackId, avUrlsCdnSet.audioTrackId) && Objects.equal(this.encode, avUrlsCdnSet.encode) && Objects.equal(this.cdnWeightsRank, avUrlsCdnSet.cdnWeightsRank) && Objects.equal(this.drm, avUrlsCdnSet.drm) && Objects.equal(this.hdrFormat, avUrlsCdnSet.hdrFormat);
    }

    public int hashCode() {
        return Objects.hashCode(this.cdn, this.bitrateAdaption, this.streamingTechnology, this.avUrlInfoList, this.majorVersion, this.urlSetId, this.audioTrackId, this.encode, this.cdnWeightsRank, this.drm, this.hdrFormat);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cdn", this.cdn).add("bitrateAdaption", this.bitrateAdaption).add("streamingTechnology", this.streamingTechnology).add("avUrlInfoList", this.avUrlInfoList).add("majorVersion", this.majorVersion).add("urlSetId", this.urlSetId).add("audioTrackId", this.audioTrackId).add("encode", this.encode).add("cdnWeightsRank", this.cdnWeightsRank).add("drm", this.drm).add("hdrFormat", this.hdrFormat).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioTrackId;
        public ImmutableList<AvUrlInfo> avUrlInfoList;
        public String bitrateAdaption;
        public String cdn;
        public String cdnWeightsRank;
        public String drm;
        public String encode;
        public String hdrFormat;
        public String majorVersion;
        public String streamingTechnology;
        public String urlSetId;

        public AvUrlsCdnSet build() {
            return new AvUrlsCdnSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AvUrlsCdnSet> {
        private final ListParser<AvUrlInfo> mAvUrlInfoListParser;
        private final SimpleParsers.StringParser mBitrateAdaptionParser;
        private final SimpleParsers.StringParser mCdnParser;
        private final SimpleParsers.StringParser mCompressionStandardParser;
        private final SimpleParsers.StringParser mDrmParser;
        private final SimpleParsers.StringParser mDynamicRangeParser;
        private final SimpleParsers.StringParser mStreamingTechnologyParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAvUrlInfoListParser = ListParser.newParser(new AvUrlInfo.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mCdnParser = stringParser;
            this.mBitrateAdaptionParser = stringParser;
            this.mStreamingTechnologyParser = stringParser;
            this.mStringParser = stringParser;
            this.mCompressionStandardParser = stringParser;
            this.mDrmParser = stringParser;
            this.mDynamicRangeParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AvUrlsCdnSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvUrlsCdnSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AvUrlsCdnSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvUrlsCdnSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AvUrlsCdnSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            ImmutableList<AvUrlInfo> immutableList;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            JsonParsingUtils.throwIfNotObject(jsonNode, "AvUrlsCdnSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1298776554:
                            if (next.equals("encode")) {
                                b = 7;
                            }
                            break;
                        case -1298460326:
                            if (next.equals("cdnWeightsRank")) {
                                b = 8;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 2;
                            }
                            break;
                        case -1082382970:
                            if (next.equals("avUrlInfoList")) {
                                b = 3;
                            }
                            break;
                        case -981693202:
                            if (next.equals("urlSetId")) {
                                b = 5;
                            }
                            break;
                        case -614336461:
                            if (next.equals("bitrateAdaption")) {
                                b = 1;
                            }
                            break;
                        case -606876449:
                            if (next.equals("majorVersion")) {
                                b = 4;
                            }
                            break;
                        case -603068787:
                            if (next.equals("hdrFormat")) {
                                b = 10;
                            }
                            break;
                        case 98349:
                            if (next.equals("cdn")) {
                                b = 0;
                            }
                            break;
                        case 99743:
                            if (next.equals("drm")) {
                                b = 9;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 6;
                            }
                            break;
                    }
                    str = null;
                    str10 = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    immutableList = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AvUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mCdnParser.parse(jsonNode2);
                        }
                        builder.cdn = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mBitrateAdaptionParser.parse(jsonNode2);
                        }
                        builder.bitrateAdaption = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStreamingTechnologyParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAvUrlInfoListParser.parse(jsonNode2);
                        }
                        builder.avUrlInfoList = immutableList;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.majorVersion = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlSetId = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mCompressionStandardParser.parse(jsonNode2);
                        }
                        builder.encode = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdnWeightsRank = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mDrmParser.parse(jsonNode2);
                        }
                        builder.drm = str9;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mDynamicRangeParser.parse(jsonNode2);
                        }
                        builder.hdrFormat = str10;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AvUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AvUrlsCdnSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1298776554:
                                    if (currentName.equals("encode")) {
                                        b = 7;
                                    }
                                    break;
                                case -1298460326:
                                    if (currentName.equals("cdnWeightsRank")) {
                                        b = 8;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 2;
                                    }
                                    break;
                                case -1082382970:
                                    if (currentName.equals("avUrlInfoList")) {
                                        b = 3;
                                    }
                                    break;
                                case -981693202:
                                    if (currentName.equals("urlSetId")) {
                                        b = 5;
                                    }
                                    break;
                                case -614336461:
                                    if (currentName.equals("bitrateAdaption")) {
                                        b = 1;
                                    }
                                    break;
                                case -606876449:
                                    if (currentName.equals("majorVersion")) {
                                        b = 4;
                                    }
                                    break;
                                case -603068787:
                                    if (currentName.equals("hdrFormat")) {
                                        b = 10;
                                    }
                                    break;
                                case 98349:
                                    if (currentName.equals("cdn")) {
                                        b = 0;
                                    }
                                    break;
                                case 99743:
                                    if (currentName.equals("drm")) {
                                        b = 9;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 6;
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
                            ImmutableList<AvUrlInfo> immutableList = null;
                            String str9 = null;
                            String str10 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mCdnParser.parse(jsonParser);
                                    }
                                    builder.cdn = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mBitrateAdaptionParser.parse(jsonParser);
                                    }
                                    builder.bitrateAdaption = str10;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mStreamingTechnologyParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = str9;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAvUrlInfoListParser.parse(jsonParser);
                                    }
                                    builder.avUrlInfoList = immutableList;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.majorVersion = str8;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.urlSetId = str7;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str6;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mCompressionStandardParser.parse(jsonParser);
                                    }
                                    builder.encode = str5;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.cdnWeightsRank = str4;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mDrmParser.parse(jsonParser);
                                    }
                                    builder.drm = str3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mDynamicRangeParser.parse(jsonParser);
                                    }
                                    builder.hdrFormat = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AvUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AvUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
