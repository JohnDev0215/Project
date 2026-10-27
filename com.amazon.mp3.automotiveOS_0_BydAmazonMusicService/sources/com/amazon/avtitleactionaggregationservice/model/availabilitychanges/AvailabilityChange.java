package com.amazon.avtitleactionaggregationservice.model.availabilitychanges;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.AvailabilityChangeType;
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
public class AvailabilityChange {
    public final Optional<String> benefitId;
    public final Optional<Date> changeTime;
    public final Optional<AvailabilityChangeType> changeType;

    private AvailabilityChange(Builder builder) {
        this.changeType = Optional.fromNullable(builder.changeType);
        this.benefitId = Optional.fromNullable(builder.benefitId);
        this.changeTime = Optional.fromNullable(builder.changeTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvailabilityChange)) {
            return false;
        }
        AvailabilityChange availabilityChange = (AvailabilityChange) obj;
        return Objects.equal(this.changeType, availabilityChange.changeType) && Objects.equal(this.benefitId, availabilityChange.benefitId) && Objects.equal(this.changeTime, availabilityChange.changeTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.changeType, this.benefitId, this.changeTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("changeType", this.changeType).add("benefitId", this.benefitId).add("changeTime", this.changeTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitId;
        public Date changeTime;
        public AvailabilityChangeType changeType;

        public AvailabilityChange build() {
            return new AvailabilityChange(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AvailabilityChange> {
        private final EnumParser<AvailabilityChangeType> mAvailabilityChangeTypeParser;
        private final SimpleParsers.DateParser mDateTimeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAvailabilityChangeTypeParser = EnumParser.newParser(AvailabilityChangeType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AvailabilityChange parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvailabilityChange:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AvailabilityChange parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AvailabilityChange:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AvailabilityChange parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AvailabilityChange");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2131918179) {
                        if (iHashCode != -2131902710) {
                            if (iHashCode == 723448850 && next.equals("benefitId")) {
                                b = 1;
                            }
                        } else if (next.equals("changeType")) {
                            b = 0;
                        }
                    } else if (next.equals("changeTime")) {
                        b = 2;
                    }
                    Date date = null;
                    AvailabilityChangeType availabilityChangeType = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            availabilityChangeType = (AvailabilityChangeType) this.mAvailabilityChangeTypeParser.parse(jsonNode2);
                        }
                        builder.changeType = availabilityChangeType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.changeTime = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AvailabilityChange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AvailabilityChange parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2131918179) {
                                if (iHashCode != -2131902710) {
                                    if (iHashCode == 723448850 && currentName.equals("benefitId")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("changeType")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("changeTime")) {
                                b = 2;
                            }
                            Date date = null;
                            AvailabilityChangeType availabilityChangeType = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    availabilityChangeType = (AvailabilityChangeType) this.mAvailabilityChangeTypeParser.parse(jsonParser);
                                }
                                builder.changeType = availabilityChangeType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.benefitId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.changeTime = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AvailabilityChange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
