package com.amazon.atv.schedule;

import com.amazon.atv.schedule.ChannelMetadata;
import com.amazon.atv.schedule.ChannelScheduleEntry;
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
public class ChannelSchedule {
    public final Optional<String> channelID;
    public final Optional<ChannelMetadata> channelMetadata;
    public final Optional<String> channelName;
    public final Optional<ImmutableList<ChannelScheduleEntry>> scheduled;

    private ChannelSchedule(Builder builder) {
        this.channelName = Optional.fromNullable(builder.channelName);
        this.scheduled = Optional.fromNullable(builder.scheduled);
        this.channelID = Optional.fromNullable(builder.channelID);
        this.channelMetadata = Optional.fromNullable(builder.channelMetadata);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChannelSchedule)) {
            return false;
        }
        ChannelSchedule channelSchedule = (ChannelSchedule) obj;
        return Objects.equal(this.channelName, channelSchedule.channelName) && Objects.equal(this.scheduled, channelSchedule.scheduled) && Objects.equal(this.channelID, channelSchedule.channelID) && Objects.equal(this.channelMetadata, channelSchedule.channelMetadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.channelName, this.scheduled, this.channelID, this.channelMetadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("channelName", this.channelName).add("scheduled", this.scheduled).add("channelID", this.channelID).add("channelMetadata", this.channelMetadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String channelID;
        public ChannelMetadata channelMetadata;
        public String channelName;
        public ImmutableList<ChannelScheduleEntry> scheduled;

        public ChannelSchedule build() {
            return new ChannelSchedule(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ChannelSchedule> {
        private final ChannelMetadata.Parser mChannelMetadataParser;
        private final ListParser<ChannelScheduleEntry> mChannelScheduleEntryListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mChannelMetadataParser = new ChannelMetadata.Parser(objectMapper);
            this.mChannelScheduleEntryListParser = ListParser.newParser(new ChannelScheduleEntry.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ChannelSchedule parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ChannelSchedule:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ChannelSchedule parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ChannelSchedule:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ChannelSchedule parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ChannelSchedule");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -160710483:
                            if (next.equals("scheduled")) {
                                b = 1;
                            }
                            break;
                        case 18065746:
                            if (next.equals("channelMetadata")) {
                                b = 3;
                            }
                            break;
                        case 273953326:
                            if (next.equals("channelName")) {
                                b = 0;
                            }
                            break;
                        case 1461735774:
                            if (next.equals("channelID")) {
                                b = 2;
                            }
                            break;
                    }
                    ChannelMetadata channelMetadata = null;
                    String str = null;
                    ImmutableList<ChannelScheduleEntry> immutableList = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.channelName = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mChannelScheduleEntryListParser.parse(jsonNode2);
                        }
                        builder.scheduled = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.channelID = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            channelMetadata = this.mChannelMetadataParser.parse(jsonNode2);
                        }
                        builder.channelMetadata = channelMetadata;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ChannelSchedule so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ChannelSchedule parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -160710483:
                                    if (currentName.equals("scheduled")) {
                                        b = 1;
                                    }
                                    break;
                                case 18065746:
                                    if (currentName.equals("channelMetadata")) {
                                        b = 3;
                                    }
                                    break;
                                case 273953326:
                                    if (currentName.equals("channelName")) {
                                        b = 0;
                                    }
                                    break;
                                case 1461735774:
                                    if (currentName.equals("channelID")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ChannelMetadata channelMetadata = null;
                            String str = null;
                            ImmutableList<ChannelScheduleEntry> immutableList = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.channelName = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mChannelScheduleEntryListParser.parse(jsonParser);
                                }
                                builder.scheduled = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.channelID = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    channelMetadata = this.mChannelMetadataParser.parse(jsonParser);
                                }
                                builder.channelMetadata = channelMetadata;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ChannelSchedule so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
