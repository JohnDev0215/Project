package com.amazon.atv.schedule;

import com.amazon.atv.schedule.Channel;
import com.amazon.atv.schedule.ScheduleTime;
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
public class TitleScheduleEntry {
    public final Optional<Channel> channel;
    public final Optional<String> channelId;
    public final Optional<ScheduleTime> time;

    private TitleScheduleEntry(Builder builder) {
        this.time = Optional.fromNullable(builder.time);
        this.channel = Optional.fromNullable(builder.channel);
        this.channelId = Optional.fromNullable(builder.channelId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleScheduleEntry)) {
            return false;
        }
        TitleScheduleEntry titleScheduleEntry = (TitleScheduleEntry) obj;
        return Objects.equal(this.time, titleScheduleEntry.time) && Objects.equal(this.channel, titleScheduleEntry.channel) && Objects.equal(this.channelId, titleScheduleEntry.channelId);
    }

    public int hashCode() {
        return Objects.hashCode(this.time, this.channel, this.channelId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("time", this.time).add("channel", this.channel).add("channelId", this.channelId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Channel channel;
        public String channelId;
        public ScheduleTime time;

        public TitleScheduleEntry build() {
            return new TitleScheduleEntry(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleScheduleEntry> {
        private final Channel.Parser mChannelParser;
        private final ScheduleTime.Parser mScheduleTimeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mScheduleTimeParser = new ScheduleTime.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mChannelParser = new Channel.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleScheduleEntry parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleScheduleEntry:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleScheduleEntry parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleScheduleEntry:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleScheduleEntry parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleScheduleEntry");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3560141) {
                        if (iHashCode != 738950403) {
                            if (iHashCode == 1461735806 && next.equals("channelId")) {
                                b = 2;
                            }
                        } else if (next.equals("channel")) {
                            b = 1;
                        }
                    } else if (next.equals("time")) {
                        b = 0;
                    }
                    String str = null;
                    ScheduleTime scheduleTime = null;
                    Channel channel = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            scheduleTime = this.mScheduleTimeParser.parse(jsonNode2);
                        }
                        builder.time = scheduleTime;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            channel = this.mChannelParser.parse(jsonNode2);
                        }
                        builder.channel = channel;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.channelId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleScheduleEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TitleScheduleEntry parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3560141) {
                                if (iHashCode != 738950403) {
                                    if (iHashCode == 1461735806 && currentName.equals("channelId")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("channel")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("time")) {
                                b = 0;
                            }
                            String str = null;
                            ScheduleTime scheduleTime = null;
                            Channel channel = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    scheduleTime = this.mScheduleTimeParser.parse(jsonParser);
                                }
                                builder.time = scheduleTime;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    channel = this.mChannelParser.parse(jsonParser);
                                }
                                builder.channel = channel;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.channelId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleScheduleEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
