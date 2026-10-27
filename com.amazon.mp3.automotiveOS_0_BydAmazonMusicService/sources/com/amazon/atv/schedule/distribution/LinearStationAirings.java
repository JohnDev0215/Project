package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.LinearAiring;
import com.amazon.atv.schedule.distribution.LinearStation;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
public class LinearStationAirings {
    public final Optional<ImmutableList<LinearAiring>> linearAirings;
    public final Optional<LinearStation> station;

    private LinearStationAirings(Builder builder) {
        this.linearAirings = Optional.fromNullable(builder.linearAirings);
        this.station = Optional.fromNullable(builder.station);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearStationAirings)) {
            return false;
        }
        LinearStationAirings linearStationAirings = (LinearStationAirings) obj;
        return Objects.equal(this.linearAirings, linearStationAirings.linearAirings) && Objects.equal(this.station, linearStationAirings.station);
    }

    public int hashCode() {
        return Objects.hashCode(this.linearAirings, this.station);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("linearAirings", this.linearAirings).add("station", this.station).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<LinearAiring> linearAirings;
        public LinearStation station;

        public LinearStationAirings build() {
            return new LinearStationAirings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearStationAirings> {
        private final ListParser<LinearAiring> mLinearAiringListParser;
        private final LinearStation.Parser mLinearStationParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLinearAiringListParser = ListParser.newParser(new LinearAiring.Parser(objectMapper));
            this.mLinearStationParser = new LinearStation.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearStationAirings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearStationAirings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearStationAirings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearStationAirings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LinearStationAirings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearStationAirings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1897135820) {
                        if (iHashCode == -1162883530 && next.equals("linearAirings")) {
                            b = 0;
                        }
                    } else if (next.equals("station")) {
                        b = 1;
                    }
                    LinearStation linearStation = null;
                    ImmutableList<LinearAiring> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mLinearAiringListParser.parse(jsonNode2);
                        }
                        builder.linearAirings = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            linearStation = this.mLinearStationParser.parse(jsonNode2);
                        }
                        builder.station = linearStation;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearStationAirings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private LinearStationAirings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1897135820) {
                                if (iHashCode == -1162883530 && currentName.equals("linearAirings")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("station")) {
                                b = 1;
                            }
                            LinearStation linearStation = null;
                            ImmutableList<LinearAiring> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mLinearAiringListParser.parse(jsonParser);
                                }
                                builder.linearAirings = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    linearStation = this.mLinearStationParser.parse(jsonParser);
                                }
                                builder.station = linearStation;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearStationAirings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
