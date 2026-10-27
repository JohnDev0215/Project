package com.amazon.atvtransitiontimecodeservice;

import com.amazon.atvtransitiontimecodeservice.EventData;
import com.amazon.atvtransitiontimecodeservice.EventTimecode;
import com.amazon.atvtransitiontimecodeservice.types.ActionToPerform;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class MatureContentEvent {
    public final Optional<ActionToPerform> action;
    public final Optional<EventData> data;
    public final Optional<EventTimecode> when;

    private MatureContentEvent(Builder builder) {
        this.when = Optional.fromNullable(builder.when);
        this.action = Optional.fromNullable(builder.action);
        this.data = Optional.fromNullable(builder.data);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MatureContentEvent)) {
            return false;
        }
        MatureContentEvent matureContentEvent = (MatureContentEvent) obj;
        return Objects.equal(this.when, matureContentEvent.when) && Objects.equal(this.action, matureContentEvent.action) && Objects.equal(this.data, matureContentEvent.data);
    }

    public int hashCode() {
        return Objects.hashCode(this.when, this.action, this.data);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("when", this.when).add("action", this.action).add("data", this.data).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ActionToPerform action;
        public EventData data;
        public EventTimecode when;

        public MatureContentEvent build() {
            return new MatureContentEvent(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<MatureContentEvent> {
        private final EnumParser<ActionToPerform> mActionToPerformParser;
        private final EventData.Parser mEventDataParser;
        private final EventTimecode.Parser mEventTimecodeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEventTimecodeParser = new EventTimecode.Parser(objectMapper);
            this.mActionToPerformParser = EnumParser.newParser(ActionToPerform.class);
            this.mEventDataParser = new EventData.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public MatureContentEvent parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MatureContentEvent:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public MatureContentEvent parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MatureContentEvent:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private MatureContentEvent parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "MatureContentEvent");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1422950858) {
                        if (iHashCode != 3076010) {
                            if (iHashCode == 3648314 && next.equals("when")) {
                                b = 0;
                            }
                        } else if (next.equals("data")) {
                            b = 2;
                        }
                    } else if (next.equals("action")) {
                        b = 1;
                    }
                    EventData eventData = null;
                    EventTimecode eventTimecode = null;
                    ActionToPerform actionToPerform = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            eventTimecode = this.mEventTimecodeParser.parse(jsonNode2);
                        }
                        builder.when = eventTimecode;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            actionToPerform = (ActionToPerform) this.mActionToPerformParser.parse(jsonNode2);
                        }
                        builder.action = actionToPerform;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            eventData = this.mEventDataParser.parse(jsonNode2);
                        }
                        builder.data = eventData;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing MatureContentEvent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private MatureContentEvent parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1422950858) {
                                if (iHashCode != 3076010) {
                                    if (iHashCode == 3648314 && currentName.equals("when")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("data")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("action")) {
                                b = 1;
                            }
                            EventData eventData = null;
                            EventTimecode eventTimecode = null;
                            ActionToPerform actionToPerform = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eventTimecode = this.mEventTimecodeParser.parse(jsonParser);
                                }
                                builder.when = eventTimecode;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    actionToPerform = (ActionToPerform) this.mActionToPerformParser.parse(jsonParser);
                                }
                                builder.action = actionToPerform;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eventData = this.mEventDataParser.parse(jsonParser);
                                }
                                builder.data = eventData;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing MatureContentEvent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
