package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.AiringsRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetAiringsForLinearStationsRequest {
    public final AiringsRequest airingRequest;
    public final Optional<ImmutableList<String>> preferredLocales;
    public final Optional<String> regulatoryTerritory;
    public final ImmutableList<String> stations;
    public final String videoTerritory;

    private GetAiringsForLinearStationsRequest(Builder builder) {
        this.preferredLocales = Optional.fromNullable(builder.preferredLocales);
        this.stations = (ImmutableList) Preconditions.checkNotNull(builder.stations, "Unexpected null field: stations");
        this.videoTerritory = (String) Preconditions.checkNotNull(builder.videoTerritory, "Unexpected null field: videoTerritory");
        this.airingRequest = (AiringsRequest) Preconditions.checkNotNull(builder.airingRequest, "Unexpected null field: airingRequest");
        this.regulatoryTerritory = Optional.fromNullable(builder.regulatoryTerritory);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAiringsForLinearStationsRequest)) {
            return false;
        }
        GetAiringsForLinearStationsRequest getAiringsForLinearStationsRequest = (GetAiringsForLinearStationsRequest) obj;
        return Objects.equal(this.preferredLocales, getAiringsForLinearStationsRequest.preferredLocales) && Objects.equal(this.stations, getAiringsForLinearStationsRequest.stations) && Objects.equal(this.videoTerritory, getAiringsForLinearStationsRequest.videoTerritory) && Objects.equal(this.airingRequest, getAiringsForLinearStationsRequest.airingRequest) && Objects.equal(this.regulatoryTerritory, getAiringsForLinearStationsRequest.regulatoryTerritory);
    }

    public int hashCode() {
        return Objects.hashCode(this.preferredLocales, this.stations, this.videoTerritory, this.airingRequest, this.regulatoryTerritory);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("preferredLocales", this.preferredLocales).add("stations", this.stations).add("videoTerritory", this.videoTerritory).add("airingRequest", this.airingRequest).add("regulatoryTerritory", this.regulatoryTerritory).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AiringsRequest airingRequest;
        public ImmutableList<String> preferredLocales;
        public String regulatoryTerritory;
        public ImmutableList<String> stations;
        public String videoTerritory;

        public GetAiringsForLinearStationsRequest build() {
            return new GetAiringsForLinearStationsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAiringsForLinearStationsRequest> {
        private final AiringsRequest.Parser mAiringsRequestParser;
        private final ListParser<String> mLocaleListParser;
        private final ListParser<String> mStationIdListParser;
        private final SimpleParsers.StringParser mTerritoryParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLocaleListParser = ListParser.newParser(stringParser);
            this.mStationIdListParser = ListParser.newParser(stringParser);
            this.mAiringsRequestParser = new AiringsRequest.Parser(objectMapper);
            this.mTerritoryParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAiringsForLinearStationsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAiringsForLinearStationsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAiringsForLinearStationsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAiringsForLinearStationsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2110298728:
                            if (next.equals("preferredLocales")) {
                                b = 0;
                            }
                            break;
                        case -1798844961:
                            if (next.equals("videoTerritory")) {
                                b = 2;
                            }
                            break;
                        case 755078423:
                            if (next.equals("airingRequest")) {
                                b = 3;
                            }
                            break;
                        case 1318331839:
                            if (next.equals("stations")) {
                                b = 1;
                            }
                            break;
                        case 1414951010:
                            if (next.equals("regulatoryTerritory")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    ImmutableList<String> immutableList = null;
                    ImmutableList<String> immutableList2 = null;
                    String str2 = null;
                    AiringsRequest airingsRequest = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mLocaleListParser.parse(jsonNode2);
                        }
                        builder.preferredLocales = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mStationIdListParser.parse(jsonNode2);
                        }
                        builder.stations = immutableList2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.videoTerritory = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            airingsRequest = this.mAiringsRequestParser.parse(jsonNode2);
                        }
                        builder.airingRequest = airingsRequest;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mTerritoryParser.parse(jsonNode2);
                        }
                        builder.regulatoryTerritory = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAiringsForLinearStationsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.stations, this, "stations");
            JsonParsingUtils.checkNotNull(builder.videoTerritory, this, "videoTerritory");
            JsonParsingUtils.checkNotNull(builder.airingRequest, this, "airingRequest");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAiringsForLinearStationsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2110298728:
                                    if (currentName.equals("preferredLocales")) {
                                        b = 0;
                                    }
                                    break;
                                case -1798844961:
                                    if (currentName.equals("videoTerritory")) {
                                        b = 2;
                                    }
                                    break;
                                case 755078423:
                                    if (currentName.equals("airingRequest")) {
                                        b = 3;
                                    }
                                    break;
                                case 1318331839:
                                    if (currentName.equals("stations")) {
                                        b = 1;
                                    }
                                    break;
                                case 1414951010:
                                    if (currentName.equals("regulatoryTerritory")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableList<String> immutableList = null;
                            ImmutableList<String> immutableList2 = null;
                            String str2 = null;
                            AiringsRequest airingsRequest = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mLocaleListParser.parse(jsonParser);
                                }
                                builder.preferredLocales = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mStationIdListParser.parse(jsonParser);
                                }
                                builder.stations = immutableList2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mTerritoryParser.parse(jsonParser);
                                }
                                builder.videoTerritory = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    airingsRequest = this.mAiringsRequestParser.parse(jsonParser);
                                }
                                builder.airingRequest = airingsRequest;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mTerritoryParser.parse(jsonParser);
                                }
                                builder.regulatoryTerritory = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAiringsForLinearStationsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.stations, this, "stations");
                    JsonParsingUtils.checkNotNull(builder.videoTerritory, this, "videoTerritory");
                    JsonParsingUtils.checkNotNull(builder.airingRequest, this, "airingRequest");
                    return builder.build();
                }
            }
        }
    }
}
