package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.CuepointPlaylistForTenantResponse;
import com.amazon.atvplaybackresource.DebugInfo;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PlaybackUrlsForTenantResponse;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesForTenantResponse {
    public final Optional<CuepointPlaylistForTenantResponse> cuepointPlaylistResponse;
    public final Optional<ImmutableMap<String, DebugInfo>> debugInfos;
    public final Optional<PRSException> error;
    public final Optional<PlaybackUrlsForTenantResponse> playbackUrlsResponse;

    private PlaybackResourcesForTenantResponse(Builder builder) {
        this.playbackUrlsResponse = Optional.fromNullable(builder.playbackUrlsResponse);
        this.debugInfos = Optional.fromNullable(builder.debugInfos);
        this.error = Optional.fromNullable(builder.error);
        this.cuepointPlaylistResponse = Optional.fromNullable(builder.cuepointPlaylistResponse);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackResourcesForTenantResponse)) {
            return false;
        }
        PlaybackResourcesForTenantResponse playbackResourcesForTenantResponse = (PlaybackResourcesForTenantResponse) obj;
        return Objects.equal(this.playbackUrlsResponse, playbackResourcesForTenantResponse.playbackUrlsResponse) && Objects.equal(this.debugInfos, playbackResourcesForTenantResponse.debugInfos) && Objects.equal(this.error, playbackResourcesForTenantResponse.error) && Objects.equal(this.cuepointPlaylistResponse, playbackResourcesForTenantResponse.cuepointPlaylistResponse);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackUrlsResponse, this.debugInfos, this.error, this.cuepointPlaylistResponse);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackUrlsResponse", this.playbackUrlsResponse).add("debugInfos", this.debugInfos).add("error", this.error).add("cuepointPlaylistResponse", this.cuepointPlaylistResponse).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CuepointPlaylistForTenantResponse cuepointPlaylistResponse;
        public ImmutableMap<String, DebugInfo> debugInfos;
        public PRSException error;
        public PlaybackUrlsForTenantResponse playbackUrlsResponse;

        public PlaybackResourcesForTenantResponse build() {
            return new PlaybackResourcesForTenantResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackResourcesForTenantResponse> {
        private final CuepointPlaylistForTenantResponse.Parser mCuepointPlaylistForTenantResponseParser;
        private final MapParser<String, DebugInfo> mDebugInfoListParser;
        private final PRSException.Parser mPRSExceptionParser;
        private final PlaybackUrlsForTenantResponse.Parser mPlaybackUrlsForTenantResponseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPlaybackUrlsForTenantResponseParser = new PlaybackUrlsForTenantResponse.Parser(objectMapper);
            this.mDebugInfoListParser = MapParser.newParser(SimpleParsers.StringParser.INSTANCE, new DebugInfo.Parser(objectMapper));
            this.mCuepointPlaylistForTenantResponseParser = new CuepointPlaylistForTenantResponse.Parser(objectMapper);
            this.mPRSExceptionParser = new PRSException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackResourcesForTenantResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesForTenantResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackResourcesForTenantResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesForTenantResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesForTenantResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackResourcesForTenantResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1860720910:
                            if (next.equals("debugInfos")) {
                                b = 1;
                            }
                            break;
                        case -1696524544:
                            if (next.equals("playbackUrlsResponse")) {
                                b = 0;
                            }
                            break;
                        case -1541968496:
                            if (next.equals("cuepointPlaylistResponse")) {
                                b = 3;
                            }
                            break;
                        case 96784904:
                            if (next.equals("error")) {
                                b = 2;
                            }
                            break;
                    }
                    CuepointPlaylistForTenantResponse cuepointPlaylistForTenantResponse = null;
                    PlaybackUrlsForTenantResponse playbackUrlsForTenantResponse = null;
                    ImmutableMap<String, DebugInfo> immutableMap = null;
                    PRSException pRSException = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            playbackUrlsForTenantResponse = this.mPlaybackUrlsForTenantResponseParser.parse(jsonNode2);
                        }
                        builder.playbackUrlsResponse = playbackUrlsForTenantResponse;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugInfoListParser.parse(jsonNode2);
                        }
                        builder.debugInfos = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            pRSException = this.mPRSExceptionParser.parse(jsonNode2);
                        }
                        builder.error = pRSException;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            cuepointPlaylistForTenantResponse = this.mCuepointPlaylistForTenantResponseParser.parse(jsonNode2);
                        }
                        builder.cuepointPlaylistResponse = cuepointPlaylistForTenantResponse;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackResourcesForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesForTenantResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1860720910:
                                    if (currentName.equals("debugInfos")) {
                                        b = 1;
                                    }
                                    break;
                                case -1696524544:
                                    if (currentName.equals("playbackUrlsResponse")) {
                                        b = 0;
                                    }
                                    break;
                                case -1541968496:
                                    if (currentName.equals("cuepointPlaylistResponse")) {
                                        b = 3;
                                    }
                                    break;
                                case 96784904:
                                    if (currentName.equals("error")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            CuepointPlaylistForTenantResponse cuepointPlaylistForTenantResponse = null;
                            PlaybackUrlsForTenantResponse playbackUrlsForTenantResponse = null;
                            ImmutableMap<String, DebugInfo> immutableMap = null;
                            PRSException pRSException = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackUrlsForTenantResponse = this.mPlaybackUrlsForTenantResponseParser.parse(jsonParser);
                                }
                                builder.playbackUrlsResponse = playbackUrlsForTenantResponse;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugInfoListParser.parse(jsonParser);
                                }
                                builder.debugInfos = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pRSException = this.mPRSExceptionParser.parse(jsonParser);
                                }
                                builder.error = pRSException;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cuepointPlaylistForTenantResponse = this.mCuepointPlaylistForTenantResponseParser.parse(jsonParser);
                                }
                                builder.cuepointPlaylistResponse = cuepointPlaylistForTenantResponse;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackResourcesForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
