package com.amazon.atv.schedule;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class ScheduleTime {
    public final Optional<Date> endTime;
    public final Optional<Date> startTime;
    public final Optional<TimeZone> timezone;

    private ScheduleTime(Builder builder) {
        this.timezone = Optional.fromNullable(builder.timezone);
        this.endTime = Optional.fromNullable(builder.endTime);
        this.startTime = Optional.fromNullable(builder.startTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScheduleTime)) {
            return false;
        }
        ScheduleTime scheduleTime = (ScheduleTime) obj;
        return Objects.equal(this.timezone, scheduleTime.timezone) && Objects.equal(this.endTime, scheduleTime.endTime) && Objects.equal(this.startTime, scheduleTime.startTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.timezone, this.endTime, this.startTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("timezone", this.timezone).add("endTime", this.endTime).add("startTime", this.startTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Date endTime;
        public Date startTime;
        public TimeZone timezone;

        public ScheduleTime build() {
            return new ScheduleTime(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ScheduleTime> {
        private final EnumParser<TimeZone> mTimeZoneParser;
        private final SimpleParsers.DateParser mTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimeZoneParser = EnumParser.newParser(TimeZone.class);
            this.mTimestampParser = SimpleParsers.DateParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ScheduleTime parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ScheduleTime:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ScheduleTime parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ScheduleTime:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ScheduleTime parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ScheduleTime");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2129294769) {
                        if (iHashCode != -2076227591) {
                            if (iHashCode == -1607243192 && next.equals("endTime")) {
                                b = 1;
                            }
                        } else if (next.equals("timezone")) {
                            b = 0;
                        }
                    } else if (next.equals("startTime")) {
                        b = 2;
                    }
                    Date date = null;
                    TimeZone timeZone = null;
                    Date date2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            timeZone = (TimeZone) this.mTimeZoneParser.parse(jsonNode2);
                        }
                        builder.timezone = timeZone;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.endTime = date2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.startTime = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ScheduleTime so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private ScheduleTime parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2129294769) {
                                if (iHashCode != -2076227591) {
                                    if (iHashCode == -1607243192 && currentName.equals("endTime")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("timezone")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("startTime")) {
                                b = 2;
                            }
                            Date date = null;
                            TimeZone timeZone = null;
                            Date date2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeZone = (TimeZone) this.mTimeZoneParser.parse(jsonParser);
                                }
                                builder.timezone = timeZone;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.endTime = date2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.startTime = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ScheduleTime so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
