package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.Drm;
import com.amazon.atvplaybackdevice.types.HdrFormat;
import com.amazon.atvplaybackdevice.types.StreamingTechnology;
import com.amazon.atvplaybackresource.AvUrlInfo;
import com.amazon.atvplaybackresource.types.Cdn;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
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

/* JADX INFO: loaded from: classes2.dex */
public class AvUrlsCdnSet {
    public final Optional<String> audioTrackId;
    public final Optional<ImmutableList<AvUrlInfo>> avUrlInfoList;
    public final Optional<Cdn> cdn;
    public final Optional<String> cdnWeightsRank;
    public final Optional<Drm> drm;
    public final Optional<String> encode;
    public final Optional<HdrFormat> hdrFormat;
    public final Optional<StreamingTechnology> streamingTechnology;
    public final Optional<String> urlSetId;

    private AvUrlsCdnSet(Builder builder) {
        this.cdn = Optional.fromNullable(builder.cdn);
        this.drm = Optional.fromNullable(builder.drm);
        this.hdrFormat = Optional.fromNullable(builder.hdrFormat);
        this.encode = Optional.fromNullable(builder.encode);
        this.cdnWeightsRank = Optional.fromNullable(builder.cdnWeightsRank);
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
        this.avUrlInfoList = Optional.fromNullable(builder.avUrlInfoList);
        this.urlSetId = Optional.fromNullable(builder.urlSetId);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvUrlsCdnSet)) {
            return false;
        }
        AvUrlsCdnSet avUrlsCdnSet = (AvUrlsCdnSet) obj;
        return Objects.equal(this.cdn, avUrlsCdnSet.cdn) && Objects.equal(this.drm, avUrlsCdnSet.drm) && Objects.equal(this.hdrFormat, avUrlsCdnSet.hdrFormat) && Objects.equal(this.encode, avUrlsCdnSet.encode) && Objects.equal(this.cdnWeightsRank, avUrlsCdnSet.cdnWeightsRank) && Objects.equal(this.streamingTechnology, avUrlsCdnSet.streamingTechnology) && Objects.equal(this.avUrlInfoList, avUrlsCdnSet.avUrlInfoList) && Objects.equal(this.urlSetId, avUrlsCdnSet.urlSetId) && Objects.equal(this.audioTrackId, avUrlsCdnSet.audioTrackId);
    }

    public int hashCode() {
        return Objects.hashCode(this.cdn, this.drm, this.hdrFormat, this.encode, this.cdnWeightsRank, this.streamingTechnology, this.avUrlInfoList, this.urlSetId, this.audioTrackId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cdn", this.cdn).add("drm", this.drm).add("hdrFormat", this.hdrFormat).add("encode", this.encode).add("cdnWeightsRank", this.cdnWeightsRank).add("streamingTechnology", this.streamingTechnology).add("avUrlInfoList", this.avUrlInfoList).add("urlSetId", this.urlSetId).add("audioTrackId", this.audioTrackId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioTrackId;
        public ImmutableList<AvUrlInfo> avUrlInfoList;
        public Cdn cdn;
        public String cdnWeightsRank;
        public Drm drm;
        public String encode;
        public HdrFormat hdrFormat;
        public StreamingTechnology streamingTechnology;
        public String urlSetId;

        public AvUrlsCdnSet build() {
            return new AvUrlsCdnSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AvUrlsCdnSet> {
        private final ListParser<AvUrlInfo> mAvUrlInfoListParser;
        private final SimpleParsers.StringParser maudioTrackIdParser;
        private final EnumParser<Cdn> mcdnParser;
        private final SimpleParsers.StringParser mcdnWeightsRankParser;
        private final EnumParser<Drm> mdrmParser;
        private final SimpleParsers.StringParser mencodeParser;
        private final EnumParser<HdrFormat> mhdrFormatParser;
        private final EnumParser<StreamingTechnology> mstreamingTechnologyParser;
        private final SimpleParsers.StringParser murlSetIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAvUrlInfoListParser = ListParser.newParser(new AvUrlInfo.Parser(objectMapper));
            this.mcdnParser = EnumParser.newParser(Cdn.class);
            this.mdrmParser = EnumParser.newParser(Drm.class);
            this.mhdrFormatParser = EnumParser.newParser(HdrFormat.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mencodeParser = stringParser;
            this.mcdnWeightsRankParser = stringParser;
            this.murlSetIdParser = stringParser;
            this.maudioTrackIdParser = stringParser;
            this.mstreamingTechnologyParser = EnumParser.newParser(StreamingTechnology.class);
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
            Cdn cdn;
            Drm drm;
            HdrFormat hdrFormat;
            String str;
            String str2;
            StreamingTechnology streamingTechnology;
            ImmutableList<AvUrlInfo> immutableList;
            String str3;
            String str4;
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
                                b = 3;
                            }
                            break;
                        case -1298460326:
                            if (next.equals("cdnWeightsRank")) {
                                b = 4;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 5;
                            }
                            break;
                        case -1082382970:
                            if (next.equals("avUrlInfoList")) {
                                b = 6;
                            }
                            break;
                        case -981693202:
                            if (next.equals("urlSetId")) {
                                b = 7;
                            }
                            break;
                        case -603068787:
                            if (next.equals("hdrFormat")) {
                                b = 2;
                            }
                            break;
                        case 98349:
                            if (next.equals("cdn")) {
                                b = 0;
                            }
                            break;
                        case 99743:
                            if (next.equals("drm")) {
                                b = 1;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 8;
                            }
                            break;
                    }
                    cdn = null;
                    str4 = null;
                    str3 = null;
                    immutableList = null;
                    streamingTechnology = null;
                    str2 = null;
                    str = null;
                    hdrFormat = null;
                    drm = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AvUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            cdn = (Cdn) this.mcdnParser.parse(jsonNode2);
                        }
                        builder.cdn = cdn;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            drm = (Drm) this.mdrmParser.parse(jsonNode2);
                        }
                        builder.drm = drm;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            hdrFormat = (HdrFormat) this.mhdrFormatParser.parse(jsonNode2);
                        }
                        builder.hdrFormat = hdrFormat;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mencodeParser.parse(jsonNode2);
                        }
                        builder.encode = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mcdnWeightsRankParser.parse(jsonNode2);
                        }
                        builder.cdnWeightsRank = str2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            streamingTechnology = (StreamingTechnology) this.mstreamingTechnologyParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = streamingTechnology;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAvUrlInfoListParser.parse(jsonNode2);
                        }
                        builder.avUrlInfoList = immutableList;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str3 = this.murlSetIdParser.parse(jsonNode2);
                        }
                        builder.urlSetId = str3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.maudioTrackIdParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str4;
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
                                        b = 3;
                                    }
                                    break;
                                case -1298460326:
                                    if (currentName.equals("cdnWeightsRank")) {
                                        b = 4;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 5;
                                    }
                                    break;
                                case -1082382970:
                                    if (currentName.equals("avUrlInfoList")) {
                                        b = 6;
                                    }
                                    break;
                                case -981693202:
                                    if (currentName.equals("urlSetId")) {
                                        b = 7;
                                    }
                                    break;
                                case -603068787:
                                    if (currentName.equals("hdrFormat")) {
                                        b = 2;
                                    }
                                    break;
                                case 98349:
                                    if (currentName.equals("cdn")) {
                                        b = 0;
                                    }
                                    break;
                                case 99743:
                                    if (currentName.equals("drm")) {
                                        b = 1;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 8;
                                    }
                                    break;
                            }
                            Cdn cdn = null;
                            String str = null;
                            String str2 = null;
                            ImmutableList<AvUrlInfo> immutableList = null;
                            StreamingTechnology streamingTechnology = null;
                            String str3 = null;
                            String str4 = null;
                            HdrFormat hdrFormat = null;
                            Drm drm = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cdn = (Cdn) this.mcdnParser.parse(jsonParser);
                                    }
                                    builder.cdn = cdn;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        drm = (Drm) this.mdrmParser.parse(jsonParser);
                                    }
                                    builder.drm = drm;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        hdrFormat = (HdrFormat) this.mhdrFormatParser.parse(jsonParser);
                                    }
                                    builder.hdrFormat = hdrFormat;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mencodeParser.parse(jsonParser);
                                    }
                                    builder.encode = str4;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mcdnWeightsRankParser.parse(jsonParser);
                                    }
                                    builder.cdnWeightsRank = str3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        streamingTechnology = (StreamingTechnology) this.mstreamingTechnologyParser.parse(jsonParser);
                                    }
                                    builder.streamingTechnology = streamingTechnology;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAvUrlInfoListParser.parse(jsonParser);
                                    }
                                    builder.avUrlInfoList = immutableList;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.murlSetIdParser.parse(jsonParser);
                                    }
                                    builder.urlSetId = str2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.maudioTrackIdParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str;
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
