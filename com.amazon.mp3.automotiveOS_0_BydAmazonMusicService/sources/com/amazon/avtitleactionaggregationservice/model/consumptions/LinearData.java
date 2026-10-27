package com.amazon.avtitleactionaggregationservice.model.consumptions;

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
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LinearData {
    public final Optional<Date> linearEndTime;
    public final Optional<Date> linearStartTime;
    public final Optional<String> stationId;

    private LinearData(Builder builder) {
        this.stationId = Optional.fromNullable(builder.stationId);
        this.linearStartTime = Optional.fromNullable(builder.linearStartTime);
        this.linearEndTime = Optional.fromNullable(builder.linearEndTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearData)) {
            return false;
        }
        LinearData linearData = (LinearData) obj;
        return Objects.equal(this.stationId, linearData.stationId) && Objects.equal(this.linearStartTime, linearData.linearStartTime) && Objects.equal(this.linearEndTime, linearData.linearEndTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.stationId, this.linearStartTime, this.linearEndTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("stationId", this.stationId).add("linearStartTime", this.linearStartTime).add("linearEndTime", this.linearEndTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Date linearEndTime;
        public Date linearStartTime;
        public String stationId;

        public LinearData build() {
            return new LinearData(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearData> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearData parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearData:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearData parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearData:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LinearData parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearData");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2081387153) {
                        if (iHashCode != -1778249885) {
                            if (iHashCode == 1036997802 && next.equals("linearStartTime")) {
                                b = 1;
                            }
                        } else if (next.equals("linearEndTime")) {
                            b = 2;
                        }
                    } else if (next.equals("stationId")) {
                        b = 0;
                    }
                    Date date = null;
                    String str = null;
                    Date date2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.stationId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.linearStartTime = date2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.linearEndTime = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private LinearData parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode != -1778249885) {
                                    if (iHashCode == 1036997802 && currentName.equals("linearStartTime")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("linearEndTime")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("stationId")) {
                                b = 0;
                            }
                            Date date = null;
                            String str = null;
                            Date date2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.stationId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.linearStartTime = date2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.linearEndTime = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
