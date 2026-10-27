package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.DebStep;
import com.amazon.urlvending.GetLivePlaybackUrlsPlaybackUrls;
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
public class GetLivePlaybackUrlsResponse {
    public final Optional<ImmutableMap<String, ImmutableList<DebStep>>> debInfo;
    public final Optional<ImmutableMap<String, String>> holdbacks;
    public final Optional<GetLivePlaybackUrlsPlaybackUrls> playbackUrls;

    private GetLivePlaybackUrlsResponse(Builder builder) {
        this.playbackUrls = Optional.fromNullable(builder.playbackUrls);
        this.debInfo = Optional.fromNullable(builder.debInfo);
        this.holdbacks = Optional.fromNullable(builder.holdbacks);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsResponse)) {
            return false;
        }
        GetLivePlaybackUrlsResponse getLivePlaybackUrlsResponse = (GetLivePlaybackUrlsResponse) obj;
        return Objects.equal(this.playbackUrls, getLivePlaybackUrlsResponse.playbackUrls) && Objects.equal(this.debInfo, getLivePlaybackUrlsResponse.debInfo) && Objects.equal(this.holdbacks, getLivePlaybackUrlsResponse.holdbacks);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackUrls, this.debInfo, this.holdbacks);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackUrls", this.playbackUrls).add("debInfo", this.debInfo).add("holdbacks", this.holdbacks).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, ImmutableList<DebStep>> debInfo;
        public ImmutableMap<String, String> holdbacks;
        public GetLivePlaybackUrlsPlaybackUrls playbackUrls;

        public GetLivePlaybackUrlsResponse build() {
            return new GetLivePlaybackUrlsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsResponse> {
        private final MapParser<String, ImmutableList<DebStep>> mDebInfoMapParser;
        private final MapParser<String, String> mGetLivePlaybackUrlsHoldbacksMapParser;
        private final GetLivePlaybackUrlsPlaybackUrls.Parser mGetLivePlaybackUrlsPlaybackUrlsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetLivePlaybackUrlsPlaybackUrlsParser = new GetLivePlaybackUrlsPlaybackUrls.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mDebInfoMapParser = MapParser.newParser(stringParser, ListParser.newParser(new DebStep.Parser(objectMapper)));
            this.mGetLivePlaybackUrlsHoldbacksMapParser = MapParser.newParser(stringParser, stringParser);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLivePlaybackUrlsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1204273933) {
                        if (iHashCode != 1355525535) {
                            if (iHashCode == 1540387919 && next.equals("debInfo")) {
                                b = 1;
                            }
                        } else if (next.equals("playbackUrls")) {
                            b = 0;
                        }
                    } else if (next.equals("holdbacks")) {
                        b = 2;
                    }
                    ImmutableMap<String, String> immutableMap = null;
                    GetLivePlaybackUrlsPlaybackUrls getLivePlaybackUrlsPlaybackUrls = null;
                    ImmutableMap<String, ImmutableList<DebStep>> immutableMap2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            getLivePlaybackUrlsPlaybackUrls = this.mGetLivePlaybackUrlsPlaybackUrlsParser.parse(jsonNode2);
                        }
                        builder.playbackUrls = getLivePlaybackUrlsPlaybackUrls;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mDebInfoMapParser.parse(jsonNode2);
                        }
                        builder.debInfo = immutableMap2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mGetLivePlaybackUrlsHoldbacksMapParser.parse(jsonNode2);
                        }
                        builder.holdbacks = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLivePlaybackUrlsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1204273933) {
                                if (iHashCode != 1355525535) {
                                    if (iHashCode == 1540387919 && currentName.equals("debInfo")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("playbackUrls")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("holdbacks")) {
                                b = 2;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            GetLivePlaybackUrlsPlaybackUrls getLivePlaybackUrlsPlaybackUrls = null;
                            ImmutableMap<String, ImmutableList<DebStep>> immutableMap2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getLivePlaybackUrlsPlaybackUrls = this.mGetLivePlaybackUrlsPlaybackUrlsParser.parse(jsonParser);
                                }
                                builder.playbackUrls = getLivePlaybackUrlsPlaybackUrls;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap2 = this.mDebInfoMapParser.parse(jsonParser);
                                }
                                builder.debInfo = immutableMap2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mGetLivePlaybackUrlsHoldbacksMapParser.parse(jsonParser);
                                }
                                builder.holdbacks = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
