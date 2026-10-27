package com.amazon.atv.discovery;

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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class CacheRefreshEvent {
    public final EventType eventType;
    public final Optional<ImmutableList<String>> qualifiers;
    public final UpdatePolicyType updatePolicy;

    private CacheRefreshEvent(Builder builder) {
        this.eventType = (EventType) Preconditions.checkNotNull(builder.eventType, "Unexpected null field: eventType");
        this.qualifiers = Optional.fromNullable(builder.qualifiers);
        this.updatePolicy = (UpdatePolicyType) Preconditions.checkNotNull(builder.updatePolicy, "Unexpected null field: updatePolicy");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CacheRefreshEvent)) {
            return false;
        }
        CacheRefreshEvent cacheRefreshEvent = (CacheRefreshEvent) obj;
        return Objects.equal(this.eventType, cacheRefreshEvent.eventType) && Objects.equal(this.qualifiers, cacheRefreshEvent.qualifiers) && Objects.equal(this.updatePolicy, cacheRefreshEvent.updatePolicy);
    }

    public int hashCode() {
        return Objects.hashCode(this.eventType, this.qualifiers, this.updatePolicy);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("eventType", this.eventType).add("qualifiers", this.qualifiers).add("updatePolicy", this.updatePolicy).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public EventType eventType;
        public ImmutableList<String> qualifiers;
        public UpdatePolicyType updatePolicy;

        public CacheRefreshEvent build() {
            return new CacheRefreshEvent(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CacheRefreshEvent> {
        private final EnumParser<EventType> mEventTypeParser;
        private final ListParser<String> mStringListParser;
        private final EnumParser<UpdatePolicyType> mUpdatePolicyTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mUpdatePolicyTypeParser = EnumParser.newParser(UpdatePolicyType.class);
            this.mStringListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
            this.mEventTypeParser = EnumParser.newParser(EventType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CacheRefreshEvent parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CacheRefreshEvent:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CacheRefreshEvent parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CacheRefreshEvent:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CacheRefreshEvent parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CacheRefreshEvent");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1030926501) {
                        if (iHashCode != -31447799) {
                            if (iHashCode == 31430900 && next.equals("eventType")) {
                                b = 0;
                            }
                        } else if (next.equals("qualifiers")) {
                            b = 1;
                        }
                    } else if (next.equals("updatePolicy")) {
                        b = 2;
                    }
                    UpdatePolicyType updatePolicyType = null;
                    EventType eventType = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            eventType = (EventType) this.mEventTypeParser.parse(jsonNode2);
                        }
                        builder.eventType = eventType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.qualifiers = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            updatePolicyType = (UpdatePolicyType) this.mUpdatePolicyTypeParser.parse(jsonNode2);
                        }
                        builder.updatePolicy = updatePolicyType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CacheRefreshEvent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.eventType, this, "eventType");
            JsonParsingUtils.checkNotNull(builder.updatePolicy, this, "updatePolicy");
            return builder.build();
        }

        @Nonnull
        private CacheRefreshEvent parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1030926501) {
                                if (iHashCode != -31447799) {
                                    if (iHashCode == 31430900 && currentName.equals("eventType")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("qualifiers")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("updatePolicy")) {
                                b = 2;
                            }
                            UpdatePolicyType updatePolicyType = null;
                            EventType eventType = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eventType = (EventType) this.mEventTypeParser.parse(jsonParser);
                                }
                                builder.eventType = eventType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.qualifiers = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    updatePolicyType = (UpdatePolicyType) this.mUpdatePolicyTypeParser.parse(jsonParser);
                                }
                                builder.updatePolicy = updatePolicyType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CacheRefreshEvent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.eventType, this, "eventType");
                    JsonParsingUtils.checkNotNull(builder.updatePolicy, this, "updatePolicy");
                    return builder.build();
                }
            }
        }
    }
}
