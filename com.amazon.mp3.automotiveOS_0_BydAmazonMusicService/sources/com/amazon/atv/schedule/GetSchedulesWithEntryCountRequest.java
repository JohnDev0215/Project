package com.amazon.atv.schedule;

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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetSchedulesWithEntryCountRequest {
    public final ImmutableList<String> channelIds;
    public final String configurationToken;
    public final MarketPlaceID marketplaceId;
    public final Optional<Date> startingAt;

    private GetSchedulesWithEntryCountRequest(Builder builder) {
        this.startingAt = Optional.fromNullable(builder.startingAt);
        this.marketplaceId = (MarketPlaceID) Preconditions.checkNotNull(builder.marketplaceId, "Unexpected null field: marketplaceId");
        this.channelIds = (ImmutableList) Preconditions.checkNotNull(builder.channelIds, "Unexpected null field: channelIds");
        this.configurationToken = (String) Preconditions.checkNotNull(builder.configurationToken, "Unexpected null field: configurationToken");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSchedulesWithEntryCountRequest)) {
            return false;
        }
        GetSchedulesWithEntryCountRequest getSchedulesWithEntryCountRequest = (GetSchedulesWithEntryCountRequest) obj;
        return Objects.equal(this.startingAt, getSchedulesWithEntryCountRequest.startingAt) && Objects.equal(this.marketplaceId, getSchedulesWithEntryCountRequest.marketplaceId) && Objects.equal(this.channelIds, getSchedulesWithEntryCountRequest.channelIds) && Objects.equal(this.configurationToken, getSchedulesWithEntryCountRequest.configurationToken);
    }

    public int hashCode() {
        return Objects.hashCode(this.startingAt, this.marketplaceId, this.channelIds, this.configurationToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("startingAt", this.startingAt).add("marketplaceId", this.marketplaceId).add("channelIds", this.channelIds).add("configurationToken", this.configurationToken).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> channelIds;
        public String configurationToken;
        public MarketPlaceID marketplaceId;
        public Date startingAt;

        public GetSchedulesWithEntryCountRequest build() {
            return new GetSchedulesWithEntryCountRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetSchedulesWithEntryCountRequest> {
        private final EnumParser<MarketPlaceID> mMarketplaceIDParser;
        private final ListParser<String> mStationListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.DateParser mTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStationListParser = ListParser.newParser(stringParser);
            this.mMarketplaceIDParser = EnumParser.newParser(MarketPlaceID.class);
            this.mTimestampParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetSchedulesWithEntryCountRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSchedulesWithEntryCountRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetSchedulesWithEntryCountRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSchedulesWithEntryCountRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSchedulesWithEntryCountRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetSchedulesWithEntryCountRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1930830155:
                            if (next.equals("channelIds")) {
                                b = 2;
                            }
                            break;
                        case -1564092269:
                            if (next.equals("startingAt")) {
                                b = 0;
                            }
                            break;
                        case 795934371:
                            if (next.equals("configurationToken")) {
                                b = 3;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    Date date = null;
                    MarketPlaceID marketPlaceID = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.startingAt = date;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            marketPlaceID = (MarketPlaceID) this.mMarketplaceIDParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = marketPlaceID;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStationListParser.parse(jsonNode2);
                        }
                        builder.channelIds = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.configurationToken = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetSchedulesWithEntryCountRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.channelIds, this, "channelIds");
            JsonParsingUtils.checkNotNull(builder.configurationToken, this, "configurationToken");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSchedulesWithEntryCountRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1930830155:
                                    if (currentName.equals("channelIds")) {
                                        b = 2;
                                    }
                                    break;
                                case -1564092269:
                                    if (currentName.equals("startingAt")) {
                                        b = 0;
                                    }
                                    break;
                                case 795934371:
                                    if (currentName.equals("configurationToken")) {
                                        b = 3;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            Date date = null;
                            MarketPlaceID marketPlaceID = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.startingAt = date;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    marketPlaceID = (MarketPlaceID) this.mMarketplaceIDParser.parse(jsonParser);
                                }
                                builder.marketplaceId = marketPlaceID;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStationListParser.parse(jsonParser);
                                }
                                builder.channelIds = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.configurationToken = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetSchedulesWithEntryCountRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.channelIds, this, "channelIds");
                    JsonParsingUtils.checkNotNull(builder.configurationToken, this, "configurationToken");
                    return builder.build();
                }
            }
        }
    }
}
