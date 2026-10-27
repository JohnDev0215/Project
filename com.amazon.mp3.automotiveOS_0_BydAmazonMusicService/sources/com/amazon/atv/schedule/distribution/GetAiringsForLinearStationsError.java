package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.CSSException;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetAiringsForLinearStationsError {
    public final Optional<CSSException> error;
    public final Optional<String> stationId;

    private GetAiringsForLinearStationsError(Builder builder) {
        this.error = Optional.fromNullable(builder.error);
        this.stationId = Optional.fromNullable(builder.stationId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAiringsForLinearStationsError)) {
            return false;
        }
        GetAiringsForLinearStationsError getAiringsForLinearStationsError = (GetAiringsForLinearStationsError) obj;
        return Objects.equal(this.error, getAiringsForLinearStationsError.error) && Objects.equal(this.stationId, getAiringsForLinearStationsError.stationId);
    }

    public int hashCode() {
        return Objects.hashCode(this.error, this.stationId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("error", this.error).add("stationId", this.stationId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CSSException error;
        public String stationId;

        public GetAiringsForLinearStationsError build() {
            return new GetAiringsForLinearStationsError(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAiringsForLinearStationsError> {
        private final CSSException.Parser mCSSExceptionParser;
        private final SimpleParsers.StringParser mStationIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCSSExceptionParser = new CSSException.Parser(objectMapper);
            this.mStationIdParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAiringsForLinearStationsError parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsError:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAiringsForLinearStationsError parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringsForLinearStationsError:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetAiringsForLinearStationsError parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAiringsForLinearStationsError");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2081387153) {
                        if (iHashCode == 96784904 && next.equals("error")) {
                            b = 0;
                        }
                    } else if (next.equals("stationId")) {
                        b = 1;
                    }
                    String str = null;
                    CSSException cSSException = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            cSSException = this.mCSSExceptionParser.parse(jsonNode2);
                        }
                        builder.error = cSSException;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStationIdParser.parse(jsonNode2);
                        }
                        builder.stationId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAiringsForLinearStationsError so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetAiringsForLinearStationsError parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2081387153) {
                                if (iHashCode == 96784904 && currentName.equals("error")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("stationId")) {
                                b = 1;
                            }
                            String str = null;
                            CSSException cSSException = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cSSException = this.mCSSExceptionParser.parse(jsonParser);
                                }
                                builder.error = cSSException;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStationIdParser.parse(jsonParser);
                                }
                                builder.stationId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAiringsForLinearStationsError so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
