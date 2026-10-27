package com.amazon.atvplaybackresource;

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
public class PlaybackUrlsForTenantRequest {
    public final String PID;
    public final String deviceTypeId;
    public final String marketplaceId;
    public final Optional<String> userAgent;

    private PlaybackUrlsForTenantRequest(Builder builder) {
        this.userAgent = Optional.fromNullable(builder.userAgent);
        this.PID = (String) Preconditions.checkNotNull(builder.PID, "Unexpected null field: PID");
        this.marketplaceId = (String) Preconditions.checkNotNull(builder.marketplaceId, "Unexpected null field: marketplaceId");
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackUrlsForTenantRequest)) {
            return false;
        }
        PlaybackUrlsForTenantRequest playbackUrlsForTenantRequest = (PlaybackUrlsForTenantRequest) obj;
        return Objects.equal(this.userAgent, playbackUrlsForTenantRequest.userAgent) && Objects.equal(this.PID, playbackUrlsForTenantRequest.PID) && Objects.equal(this.marketplaceId, playbackUrlsForTenantRequest.marketplaceId) && Objects.equal(this.deviceTypeId, playbackUrlsForTenantRequest.deviceTypeId);
    }

    public int hashCode() {
        return Objects.hashCode(this.userAgent, this.PID, this.marketplaceId, this.deviceTypeId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("userAgent", this.userAgent).add("PID", this.PID).add("marketplaceId", this.marketplaceId).add("deviceTypeId", this.deviceTypeId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String PID;
        public String deviceTypeId;
        public String marketplaceId;
        public String userAgent;

        public PlaybackUrlsForTenantRequest build() {
            return new PlaybackUrlsForTenantRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackUrlsForTenantRequest> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackUrlsForTenantRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlsForTenantRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackUrlsForTenantRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlsForTenantRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackUrlsForTenantRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackUrlsForTenantRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 3;
                            }
                            break;
                        case 79211:
                            if (next.equals("PID")) {
                                b = 1;
                            }
                            break;
                        case 311430650:
                            if (next.equals("userAgent")) {
                                b = 0;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.userAgent = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.PID = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackUrlsForTenantRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.PID, this, "PID");
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackUrlsForTenantRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 3;
                                    }
                                    break;
                                case 79211:
                                    if (currentName.equals("PID")) {
                                        b = 1;
                                    }
                                    break;
                                case 311430650:
                                    if (currentName.equals("userAgent")) {
                                        b = 0;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.userAgent = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.PID = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.deviceTypeId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackUrlsForTenantRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.PID, this, "PID");
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.deviceTypeId, this, "deviceTypeId");
                    return builder.build();
                }
            }
        }
    }
}
