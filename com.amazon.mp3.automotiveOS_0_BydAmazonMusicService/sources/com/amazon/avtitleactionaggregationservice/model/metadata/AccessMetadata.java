package com.amazon.avtitleactionaggregationservice.model.metadata;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.metadata.OtherSource;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AccessMetadata {
    public final Optional<Date> accessChangeDate;
    public final Optional<String> messageId;
    public final Optional<ImmutableList<OtherSource>> otherSources;
    public final Optional<ImmutableList<Integer>> sequenceNumbers;

    private AccessMetadata(Builder builder) {
        this.accessChangeDate = Optional.fromNullable(builder.accessChangeDate);
        this.otherSources = Optional.fromNullable(builder.otherSources);
        this.sequenceNumbers = Optional.fromNullable(builder.sequenceNumbers);
        this.messageId = Optional.fromNullable(builder.messageId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessMetadata)) {
            return false;
        }
        AccessMetadata accessMetadata = (AccessMetadata) obj;
        return Objects.equal(this.accessChangeDate, accessMetadata.accessChangeDate) && Objects.equal(this.otherSources, accessMetadata.otherSources) && Objects.equal(this.sequenceNumbers, accessMetadata.sequenceNumbers) && Objects.equal(this.messageId, accessMetadata.messageId);
    }

    public int hashCode() {
        return Objects.hashCode(this.accessChangeDate, this.otherSources, this.sequenceNumbers, this.messageId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("accessChangeDate", this.accessChangeDate).add("otherSources", this.otherSources).add("sequenceNumbers", this.sequenceNumbers).add("messageId", this.messageId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Date accessChangeDate;
        public String messageId;
        public ImmutableList<OtherSource> otherSources;
        public ImmutableList<Integer> sequenceNumbers;

        public AccessMetadata build() {
            return new AccessMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AccessMetadata> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final ListParser<OtherSource> mOtherSourcesParser;
        private final ListParser<Integer> mSequenceNumbersParser;
        private final SimpleParsers.StringParser mmessageIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mOtherSourcesParser = ListParser.newParser(new OtherSource.Parser(objectMapper));
            this.mSequenceNumbersParser = ListParser.newParser(SimpleParsers.IntegerParser.INSTANCE);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mmessageIdParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AccessMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AccessMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AccessMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AccessMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AccessMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1440013438:
                            if (next.equals("messageId")) {
                                b = 3;
                            }
                            break;
                        case -1216830840:
                            if (next.equals("otherSources")) {
                                b = 1;
                            }
                            break;
                        case -103332862:
                            if (next.equals("accessChangeDate")) {
                                b = 0;
                            }
                            break;
                        case 975807305:
                            if (next.equals("sequenceNumbers")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    Date date = null;
                    ImmutableList<OtherSource> immutableList = null;
                    ImmutableList<Integer> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.accessChangeDate = date;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOtherSourcesParser.parse(jsonNode2);
                        }
                        builder.otherSources = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSequenceNumbersParser.parse(jsonNode2);
                        }
                        builder.sequenceNumbers = immutableList2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mmessageIdParser.parse(jsonNode2);
                        }
                        builder.messageId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AccessMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AccessMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1440013438:
                                    if (currentName.equals("messageId")) {
                                        b = 3;
                                    }
                                    break;
                                case -1216830840:
                                    if (currentName.equals("otherSources")) {
                                        b = 1;
                                    }
                                    break;
                                case -103332862:
                                    if (currentName.equals("accessChangeDate")) {
                                        b = 0;
                                    }
                                    break;
                                case 975807305:
                                    if (currentName.equals("sequenceNumbers")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            Date date = null;
                            ImmutableList<OtherSource> immutableList = null;
                            ImmutableList<Integer> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.accessChangeDate = date;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mOtherSourcesParser.parse(jsonParser);
                                }
                                builder.otherSources = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mSequenceNumbersParser.parse(jsonParser);
                                }
                                builder.sequenceNumbers = immutableList2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mmessageIdParser.parse(jsonParser);
                                }
                                builder.messageId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AccessMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
