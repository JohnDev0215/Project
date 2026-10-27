package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.CuepointPlaylistForTenantRequest;
import com.amazon.atvplaybackresource.PlaybackUrlsForTenantRequest;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesForTenantRequest {
    public final Optional<CuepointPlaylistForTenantRequest> cuepointPlaylistRequest;
    public final Optional<Boolean> includeDebugInfo;
    public final String ipAddress;
    public final Optional<PlaybackUrlsForTenantRequest> playbackUrlsRequest;
    public final String tenantId;

    private PlaybackResourcesForTenantRequest(Builder builder) {
        this.includeDebugInfo = Optional.fromNullable(builder.includeDebugInfo);
        this.cuepointPlaylistRequest = Optional.fromNullable(builder.cuepointPlaylistRequest);
        this.ipAddress = (String) Preconditions.checkNotNull(builder.ipAddress, "Unexpected null field: ipAddress");
        this.tenantId = (String) Preconditions.checkNotNull(builder.tenantId, "Unexpected null field: tenantId");
        this.playbackUrlsRequest = Optional.fromNullable(builder.playbackUrlsRequest);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackResourcesForTenantRequest)) {
            return false;
        }
        PlaybackResourcesForTenantRequest playbackResourcesForTenantRequest = (PlaybackResourcesForTenantRequest) obj;
        return Objects.equal(this.includeDebugInfo, playbackResourcesForTenantRequest.includeDebugInfo) && Objects.equal(this.cuepointPlaylistRequest, playbackResourcesForTenantRequest.cuepointPlaylistRequest) && Objects.equal(this.ipAddress, playbackResourcesForTenantRequest.ipAddress) && Objects.equal(this.tenantId, playbackResourcesForTenantRequest.tenantId) && Objects.equal(this.playbackUrlsRequest, playbackResourcesForTenantRequest.playbackUrlsRequest);
    }

    public int hashCode() {
        return Objects.hashCode(this.includeDebugInfo, this.cuepointPlaylistRequest, this.ipAddress, this.tenantId, this.playbackUrlsRequest);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("includeDebugInfo", this.includeDebugInfo).add("cuepointPlaylistRequest", this.cuepointPlaylistRequest).add("ipAddress", this.ipAddress).add("tenantId", this.tenantId).add("playbackUrlsRequest", this.playbackUrlsRequest).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CuepointPlaylistForTenantRequest cuepointPlaylistRequest;
        public Boolean includeDebugInfo;
        public String ipAddress;
        public PlaybackUrlsForTenantRequest playbackUrlsRequest;
        public String tenantId;

        public PlaybackResourcesForTenantRequest build() {
            return new PlaybackResourcesForTenantRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackResourcesForTenantRequest> {
        private final CuepointPlaylistForTenantRequest.Parser mCuepointPlaylistForTenantRequestParser;
        private final SimpleParsers.BooleanParser mIncludeDebugInfoParser;
        private final PlaybackUrlsForTenantRequest.Parser mPlaybackUrlsForTenantRequestParser;
        private final SimpleParsers.StringParser mipParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCuepointPlaylistForTenantRequestParser = new CuepointPlaylistForTenantRequest.Parser(objectMapper);
            this.mIncludeDebugInfoParser = SimpleParsers.BooleanParser.INSTANCE;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mipParser = stringParser;
            this.mstringTargetParser = stringParser;
            this.mPlaybackUrlsForTenantRequestParser = new PlaybackUrlsForTenantRequest.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackResourcesForTenantRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesForTenantRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackResourcesForTenantRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackResourcesForTenantRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesForTenantRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackResourcesForTenantRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1824287271:
                            if (next.equals("includeDebugInfo")) {
                                b = 0;
                            }
                            break;
                        case -1719002128:
                            if (next.equals("playbackUrlsRequest")) {
                                b = 4;
                            }
                            break;
                        case -1306693787:
                            if (next.equals("tenantId")) {
                                b = 3;
                            }
                            break;
                        case -605637792:
                            if (next.equals("cuepointPlaylistRequest")) {
                                b = 1;
                            }
                            break;
                        case 1634032845:
                            if (next.equals("ipAddress")) {
                                b = 2;
                            }
                            break;
                    }
                    PlaybackUrlsForTenantRequest playbackUrlsForTenantRequest = null;
                    Boolean bool = null;
                    CuepointPlaylistForTenantRequest cuepointPlaylistForTenantRequest = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mIncludeDebugInfoParser.parse(jsonNode2);
                        }
                        builder.includeDebugInfo = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            cuepointPlaylistForTenantRequest = this.mCuepointPlaylistForTenantRequestParser.parse(jsonNode2);
                        }
                        builder.cuepointPlaylistRequest = cuepointPlaylistForTenantRequest;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mipParser.parse(jsonNode2);
                        }
                        builder.ipAddress = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.tenantId = str2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            playbackUrlsForTenantRequest = this.mPlaybackUrlsForTenantRequestParser.parse(jsonNode2);
                        }
                        builder.playbackUrlsRequest = playbackUrlsForTenantRequest;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackResourcesForTenantRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.ipAddress, this, "ipAddress");
            JsonParsingUtils.checkNotNull(builder.tenantId, this, "tenantId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackResourcesForTenantRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1824287271:
                                    if (currentName.equals("includeDebugInfo")) {
                                        b = 0;
                                    }
                                    break;
                                case -1719002128:
                                    if (currentName.equals("playbackUrlsRequest")) {
                                        b = 4;
                                    }
                                    break;
                                case -1306693787:
                                    if (currentName.equals("tenantId")) {
                                        b = 3;
                                    }
                                    break;
                                case -605637792:
                                    if (currentName.equals("cuepointPlaylistRequest")) {
                                        b = 1;
                                    }
                                    break;
                                case 1634032845:
                                    if (currentName.equals("ipAddress")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            PlaybackUrlsForTenantRequest playbackUrlsForTenantRequest = null;
                            Boolean bool = null;
                            CuepointPlaylistForTenantRequest cuepointPlaylistForTenantRequest = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mIncludeDebugInfoParser.parse(jsonParser);
                                }
                                builder.includeDebugInfo = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cuepointPlaylistForTenantRequest = this.mCuepointPlaylistForTenantRequestParser.parse(jsonParser);
                                }
                                builder.cuepointPlaylistRequest = cuepointPlaylistForTenantRequest;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mipParser.parse(jsonParser);
                                }
                                builder.ipAddress = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.tenantId = str2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackUrlsForTenantRequest = this.mPlaybackUrlsForTenantRequestParser.parse(jsonParser);
                                }
                                builder.playbackUrlsRequest = playbackUrlsForTenantRequest;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackResourcesForTenantRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.ipAddress, this, "ipAddress");
                    JsonParsingUtils.checkNotNull(builder.tenantId, this, "tenantId");
                    return builder.build();
                }
            }
        }
    }
}
