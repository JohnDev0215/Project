package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.GetAiringsForLinearStationsError;
import com.amazon.atv.schedule.distribution.LinearStationAirings;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetAiringsForLinearStationsResponse {
    public final ImmutableMap<String, LinearStationAirings> airings;
    public final ImmutableList<GetAiringsForLinearStationsError> errors;
    public final long now;

    private GetAiringsForLinearStationsResponse(Builder builder) {
        this.now = builder.now;
        this.errors = (ImmutableList) Preconditions.checkNotNull(builder.errors, "Unexpected null field: errors");
        this.airings = (ImmutableMap) Preconditions.checkNotNull(builder.airings, "Unexpected null field: airings");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAiringsForLinearStationsResponse)) {
            return false;
        }
        GetAiringsForLinearStationsResponse getAiringsForLinearStationsResponse = (GetAiringsForLinearStationsResponse) obj;
        return Objects.equal(Long.valueOf(this.now), Long.valueOf(getAiringsForLinearStationsResponse.now)) && Objects.equal(this.errors, getAiringsForLinearStationsResponse.errors) && Objects.equal(this.airings, getAiringsForLinearStationsResponse.airings);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.now), this.errors, this.airings);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("now", this.now).add(MAPWebViewEventHelper.KEY_ERRORS, this.errors).add("airings", this.airings).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, LinearStationAirings> airings;
        public ImmutableList<GetAiringsForLinearStationsError> errors;
        public long now;

        public GetAiringsForLinearStationsResponse build() {
            return new GetAiringsForLinearStationsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAiringsForLinearStationsResponse> {
        private final ListParser<GetAiringsForLinearStationsError> mGetAiringsForLinearStationsErrorsParser;
        private final MapParser<String, LinearStationAirings> mLinearStationAiringMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetAiringsForLinearStationsErrorsParser = ListParser.newParser(new GetAiringsForLinearStationsError.Parser(objectMapper));
            this.mLinearStationAiringMapParser = MapParser.newParser(SimpleParsers.StringParser.INSTANCE, new LinearStationAirings.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAiringsForLinearStationsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAiringsForLinearStationsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAiringsForLinearStationsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAiringsForLinearStationsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1294635157) {
                        if (iHashCode != -991876837) {
                            if (iHashCode == 109270 && next.equals("now")) {
                                b = 0;
                            }
                        } else if (next.equals("airings")) {
                            b = 2;
                        }
                    } else if (next.equals(MAPWebViewEventHelper.KEY_ERRORS)) {
                        b = 1;
                    }
                    if (b != 0) {
                        ImmutableMap<String, LinearStationAirings> immutableMap = null;
                        ImmutableList<GetAiringsForLinearStationsError> immutableList = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mGetAiringsForLinearStationsErrorsParser.parse(jsonNode2);
                            }
                            builder.errors = immutableList;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mLinearStationAiringMapParser.parse(jsonNode2);
                            }
                            builder.airings = immutableMap;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field now can't be null");
                        }
                        builder.now = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAiringsForLinearStationsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.errors, this, MAPWebViewEventHelper.KEY_ERRORS);
            JsonParsingUtils.checkNotNull(builder.airings, this, "airings");
            return builder.build();
        }

        @Nonnull
        private GetAiringsForLinearStationsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1294635157) {
                                if (iHashCode != -991876837) {
                                    if (iHashCode == 109270 && currentName.equals("now")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("airings")) {
                                    b = 2;
                                }
                            } else if (currentName.equals(MAPWebViewEventHelper.KEY_ERRORS)) {
                                b = 1;
                            }
                            if (b != 0) {
                                ImmutableMap<String, LinearStationAirings> immutableMap = null;
                                ImmutableList<GetAiringsForLinearStationsError> immutableList = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mGetAiringsForLinearStationsErrorsParser.parse(jsonParser);
                                    }
                                    builder.errors = immutableList;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mLinearStationAiringMapParser.parse(jsonParser);
                                    }
                                    builder.airings = immutableMap;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field now can't be null");
                                }
                                builder.now = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAiringsForLinearStationsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.errors, this, MAPWebViewEventHelper.KEY_ERRORS);
                    JsonParsingUtils.checkNotNull(builder.airings, this, "airings");
                    return builder.build();
                }
            }
        }
    }
}
