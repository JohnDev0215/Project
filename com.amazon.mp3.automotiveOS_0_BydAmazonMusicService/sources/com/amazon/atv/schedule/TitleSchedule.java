package com.amazon.atv.schedule;

import com.amazon.atv.schedule.TitleScheduleEntry;
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
public class TitleSchedule {
    public final Optional<ImmutableList<TitleScheduleEntry>> schedule;
    public final Optional<String> titleId;

    private TitleSchedule(Builder builder) {
        this.schedule = Optional.fromNullable(builder.schedule);
        this.titleId = Optional.fromNullable(builder.titleId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleSchedule)) {
            return false;
        }
        TitleSchedule titleSchedule = (TitleSchedule) obj;
        return Objects.equal(this.schedule, titleSchedule.schedule) && Objects.equal(this.titleId, titleSchedule.titleId);
    }

    public int hashCode() {
        return Objects.hashCode(this.schedule, this.titleId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("schedule", this.schedule).add("titleId", this.titleId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<TitleScheduleEntry> schedule;
        public String titleId;

        public TitleSchedule build() {
            return new TitleSchedule(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleSchedule> {
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<TitleScheduleEntry> mTitleScheduleEntryListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTitleScheduleEntryListParser = ListParser.newParser(new TitleScheduleEntry.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleSchedule parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleSchedule:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleSchedule parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleSchedule:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleSchedule parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleSchedule");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1307249261) {
                        if (iHashCode == -697920873 && next.equals("schedule")) {
                            b = 0;
                        }
                    } else if (next.equals("titleId")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableList<TitleScheduleEntry> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTitleScheduleEntryListParser.parse(jsonNode2);
                        }
                        builder.schedule = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleSchedule so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TitleSchedule parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1307249261) {
                                if (iHashCode == -697920873 && currentName.equals("schedule")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("titleId")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableList<TitleScheduleEntry> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTitleScheduleEntryListParser.parse(jsonParser);
                                }
                                builder.schedule = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.titleId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleSchedule so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
