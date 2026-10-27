package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.TimeRange;
import com.amazon.atv.xrayv2.TimedItemChange;
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
public class TimedItemChanges {
    public final ImmutableList<TimedItemChange> changesCollection;
    public final Optional<ImmutableList<String>> initialItemIds;
    public final TimeRange timeRange;

    private TimedItemChanges(Builder builder) {
        this.changesCollection = (ImmutableList) Preconditions.checkNotNull(builder.changesCollection, "Unexpected null field: changesCollection");
        this.initialItemIds = Optional.fromNullable(builder.initialItemIds);
        this.timeRange = (TimeRange) Preconditions.checkNotNull(builder.timeRange, "Unexpected null field: timeRange");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimedItemChanges)) {
            return false;
        }
        TimedItemChanges timedItemChanges = (TimedItemChanges) obj;
        return Objects.equal(this.changesCollection, timedItemChanges.changesCollection) && Objects.equal(this.initialItemIds, timedItemChanges.initialItemIds) && Objects.equal(this.timeRange, timedItemChanges.timeRange);
    }

    public int hashCode() {
        return Objects.hashCode(this.changesCollection, this.initialItemIds, this.timeRange);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("changesCollection", this.changesCollection).add("initialItemIds", this.initialItemIds).add("timeRange", this.timeRange).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<TimedItemChange> changesCollection;
        public ImmutableList<String> initialItemIds;
        public TimeRange timeRange;

        public TimedItemChanges build() {
            return new TimedItemChanges(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimedItemChanges> {
        private final ListParser<String> mInitialItemIdListParser;
        private final TimeRange.Parser mTimeRangeParser;
        private final ListParser<TimedItemChange> mTimedItemChangeListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimedItemChangeListParser = ListParser.newParser(new TimedItemChange.Parser(objectMapper));
            this.mInitialItemIdListParser = ListParser.newParser(SimpleParsers.StringParser.INSTANCE);
            this.mTimeRangeParser = new TimeRange.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimedItemChanges parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimedItemChanges:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimedItemChanges parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimedItemChanges:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TimedItemChanges parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimedItemChanges");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -735464767) {
                        if (iHashCode != -537619007) {
                            if (iHashCode == 24096368 && next.equals("timeRange")) {
                                b = 2;
                            }
                        } else if (next.equals("changesCollection")) {
                            b = 0;
                        }
                    } else if (next.equals("initialItemIds")) {
                        b = 1;
                    }
                    TimeRange timeRange = null;
                    ImmutableList<TimedItemChange> immutableList = null;
                    ImmutableList<String> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTimedItemChangeListParser.parse(jsonNode2);
                        }
                        builder.changesCollection = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mInitialItemIdListParser.parse(jsonNode2);
                        }
                        builder.initialItemIds = immutableList2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            timeRange = this.mTimeRangeParser.parse(jsonNode2);
                        }
                        builder.timeRange = timeRange;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimedItemChanges so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.changesCollection, this, "changesCollection");
            JsonParsingUtils.checkNotNull(builder.timeRange, this, "timeRange");
            return builder.build();
        }

        @Nonnull
        private TimedItemChanges parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -735464767) {
                                if (iHashCode != -537619007) {
                                    if (iHashCode == 24096368 && currentName.equals("timeRange")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("changesCollection")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("initialItemIds")) {
                                b = 1;
                            }
                            TimeRange timeRange = null;
                            ImmutableList<TimedItemChange> immutableList = null;
                            ImmutableList<String> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTimedItemChangeListParser.parse(jsonParser);
                                }
                                builder.changesCollection = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mInitialItemIdListParser.parse(jsonParser);
                                }
                                builder.initialItemIds = immutableList2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeRange = this.mTimeRangeParser.parse(jsonParser);
                                }
                                builder.timeRange = timeRange;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimedItemChanges so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.changesCollection, this, "changesCollection");
                    JsonParsingUtils.checkNotNull(builder.timeRange, this, "timeRange");
                    return builder.build();
                }
            }
        }
    }
}
