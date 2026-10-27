package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.TimeShiftChild;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class TimeShift {
    public final Optional<TimeShiftChild> backward;
    public final Optional<TimeShiftChild> forward;
    public final Optional<TimeShiftChild> pause;

    private TimeShift(Builder builder) {
        this.backward = Optional.fromNullable(builder.backward);
        this.forward = Optional.fromNullable(builder.forward);
        this.pause = Optional.fromNullable(builder.pause);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeShift)) {
            return false;
        }
        TimeShift timeShift = (TimeShift) obj;
        return Objects.equal(this.backward, timeShift.backward) && Objects.equal(this.forward, timeShift.forward) && Objects.equal(this.pause, timeShift.pause);
    }

    public int hashCode() {
        return Objects.hashCode(this.backward, this.forward, this.pause);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("backward", this.backward).add("forward", this.forward).add("pause", this.pause).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public TimeShiftChild backward;
        public TimeShiftChild forward;
        public TimeShiftChild pause;

        public TimeShift build() {
            return new TimeShift(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimeShift> {
        private final TimeShiftChild.Parser mTimeShiftChildParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimeShiftChildParser = new TimeShiftChild.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimeShift parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeShift:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimeShift parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeShift:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TimeShift parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimeShift");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -677145915) {
                        if (iHashCode != 106440182) {
                            if (iHashCode == 2121976803 && next.equals("backward")) {
                                b = 0;
                            }
                        } else if (next.equals("pause")) {
                            b = 2;
                        }
                    } else if (next.equals("forward")) {
                        b = 1;
                    }
                    TimeShiftChild timeShiftChild = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            timeShiftChild = this.mTimeShiftChildParser.parse(jsonNode2);
                        }
                        builder.backward = timeShiftChild;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            timeShiftChild = this.mTimeShiftChildParser.parse(jsonNode2);
                        }
                        builder.forward = timeShiftChild;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            timeShiftChild = this.mTimeShiftChildParser.parse(jsonNode2);
                        }
                        builder.pause = timeShiftChild;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeShift so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TimeShift parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -677145915) {
                                if (iHashCode != 106440182) {
                                    if (iHashCode == 2121976803 && currentName.equals("backward")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("pause")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("forward")) {
                                b = 1;
                            }
                            TimeShiftChild timeShiftChild = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeShiftChild = this.mTimeShiftChildParser.parse(jsonParser);
                                }
                                builder.backward = timeShiftChild;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeShiftChild = this.mTimeShiftChildParser.parse(jsonParser);
                                }
                                builder.forward = timeShiftChild;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeShiftChild = this.mTimeShiftChildParser.parse(jsonParser);
                                }
                                builder.pause = timeShiftChild;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeShift so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
