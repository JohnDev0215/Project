package com.amazon.atv.schedule;

import com.amazon.atv.schedule.ScheduleTime;
import com.amazon.atv.title.Title;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ChannelScheduleEntry {
    public final Optional<ImmutableList<String>> scheduleAttributes;
    public final Optional<ScheduleTime> time;
    public final Optional<Title> title;
    public final Optional<String> titleAvailabilityStatus;
    public final Optional<String> titleId;
    public final Optional<String> titleRetrievalErrorMessage;

    private ChannelScheduleEntry(Builder builder) {
        this.titleRetrievalErrorMessage = Optional.fromNullable(builder.titleRetrievalErrorMessage);
        this.time = Optional.fromNullable(builder.time);
        this.title = Optional.fromNullable(builder.title);
        this.scheduleAttributes = Optional.fromNullable(builder.scheduleAttributes);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.titleAvailabilityStatus = Optional.fromNullable(builder.titleAvailabilityStatus);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChannelScheduleEntry)) {
            return false;
        }
        ChannelScheduleEntry channelScheduleEntry = (ChannelScheduleEntry) obj;
        return Objects.equal(this.titleRetrievalErrorMessage, channelScheduleEntry.titleRetrievalErrorMessage) && Objects.equal(this.time, channelScheduleEntry.time) && Objects.equal(this.title, channelScheduleEntry.title) && Objects.equal(this.scheduleAttributes, channelScheduleEntry.scheduleAttributes) && Objects.equal(this.titleId, channelScheduleEntry.titleId) && Objects.equal(this.titleAvailabilityStatus, channelScheduleEntry.titleAvailabilityStatus);
    }

    public int hashCode() {
        return Objects.hashCode(this.titleRetrievalErrorMessage, this.time, this.title, this.scheduleAttributes, this.titleId, this.titleAvailabilityStatus);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleRetrievalErrorMessage", this.titleRetrievalErrorMessage).add("time", this.time).add("title", this.title).add("scheduleAttributes", this.scheduleAttributes).add("titleId", this.titleId).add("titleAvailabilityStatus", this.titleAvailabilityStatus).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> scheduleAttributes;
        public ScheduleTime time;
        public Title title;
        public String titleAvailabilityStatus;
        public String titleId;
        public String titleRetrievalErrorMessage;

        public ChannelScheduleEntry build() {
            return new ChannelScheduleEntry(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ChannelScheduleEntry> {
        private final ListParser<String> mScheduleAttributeListParser;
        private final ScheduleTime.Parser mScheduleTimeParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.StringParser mTitleAvailabilityStatusParser;
        private final Title.Parser mTitleParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mScheduleTimeParser = new ScheduleTime.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mScheduleAttributeListParser = ListParser.newParser(stringParser);
            this.mTitleParser = new Title.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mTitleAvailabilityStatusParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ChannelScheduleEntry parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ChannelScheduleEntry:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ChannelScheduleEntry parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ChannelScheduleEntry:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ChannelScheduleEntry parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ChannelScheduleEntry");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 4;
                            }
                            break;
                        case -908104005:
                            if (next.equals("titleRetrievalErrorMessage")) {
                                b = 0;
                            }
                            break;
                        case -86304347:
                            if (next.equals("titleAvailabilityStatus")) {
                                b = 5;
                            }
                            break;
                        case 3560141:
                            if (next.equals("time")) {
                                b = 1;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 2;
                            }
                            break;
                        case 1589446478:
                            if (next.equals("scheduleAttributes")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    ScheduleTime scheduleTime = null;
                    Title title = null;
                    ImmutableList<String> immutableList = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleRetrievalErrorMessage = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            scheduleTime = this.mScheduleTimeParser.parse(jsonNode2);
                        }
                        builder.time = scheduleTime;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            title = this.mTitleParser.parse(jsonNode2);
                        }
                        builder.title = title;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mScheduleAttributeListParser.parse(jsonNode2);
                        }
                        builder.scheduleAttributes = immutableList;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleId = str3;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            str = this.mTitleAvailabilityStatusParser.parse(jsonNode2);
                        }
                        builder.titleAvailabilityStatus = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ChannelScheduleEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ChannelScheduleEntry parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 4;
                                    }
                                    break;
                                case -908104005:
                                    if (currentName.equals("titleRetrievalErrorMessage")) {
                                        b = 0;
                                    }
                                    break;
                                case -86304347:
                                    if (currentName.equals("titleAvailabilityStatus")) {
                                        b = 5;
                                    }
                                    break;
                                case 3560141:
                                    if (currentName.equals("time")) {
                                        b = 1;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 2;
                                    }
                                    break;
                                case 1589446478:
                                    if (currentName.equals("scheduleAttributes")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ScheduleTime scheduleTime = null;
                            Title title = null;
                            ImmutableList<String> immutableList = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.titleRetrievalErrorMessage = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    scheduleTime = this.mScheduleTimeParser.parse(jsonParser);
                                }
                                builder.time = scheduleTime;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    title = this.mTitleParser.parse(jsonParser);
                                }
                                builder.title = title;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mScheduleAttributeListParser.parse(jsonParser);
                                }
                                builder.scheduleAttributes = immutableList;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.titleId = str3;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mTitleAvailabilityStatusParser.parse(jsonParser);
                                }
                                builder.titleAvailabilityStatus = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ChannelScheduleEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
