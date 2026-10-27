package com.amazon.avtitleactionaggregationservice.model.detailpage.metadata;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.utils.PageUriUtils;
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
    public final Optional<ChangeType> changeType;
    public final Optional<EntityType> entityType;
    public final Optional<ImmutableList<String>> otherSources;
    public final Optional<ImmutableList<String>> sequence;

    private AccessMetadata(Builder builder) {
        this.accessChangeDate = Optional.fromNullable(builder.accessChangeDate);
        this.entityType = Optional.fromNullable(builder.entityType);
        this.otherSources = Optional.fromNullable(builder.otherSources);
        this.sequence = Optional.fromNullable(builder.sequence);
        this.changeType = Optional.fromNullable(builder.changeType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessMetadata)) {
            return false;
        }
        AccessMetadata accessMetadata = (AccessMetadata) obj;
        return Objects.equal(this.accessChangeDate, accessMetadata.accessChangeDate) && Objects.equal(this.entityType, accessMetadata.entityType) && Objects.equal(this.otherSources, accessMetadata.otherSources) && Objects.equal(this.sequence, accessMetadata.sequence) && Objects.equal(this.changeType, accessMetadata.changeType);
    }

    public int hashCode() {
        return Objects.hashCode(this.accessChangeDate, this.entityType, this.otherSources, this.sequence, this.changeType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("accessChangeDate", this.accessChangeDate).add(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, this.entityType).add("otherSources", this.otherSources).add("sequence", this.sequence).add("changeType", this.changeType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Date accessChangeDate;
        public ChangeType changeType;
        public EntityType entityType;
        public ImmutableList<String> otherSources;
        public ImmutableList<String> sequence;

        public AccessMetadata build() {
            return new AccessMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AccessMetadata> {
        private final EnumParser<ChangeType> mChangeTypeParser;
        private final SimpleParsers.DateParser mDateTimeParser;
        private final EnumParser<EntityType> mEntityTypeParser;
        private final ListParser<String> mOtherSourceIdListParser;
        private final ListParser<String> mSequenceListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEntityTypeParser = EnumParser.newParser(EntityType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mOtherSourceIdListParser = ListParser.newParser(stringParser);
            this.mSequenceListParser = ListParser.newParser(stringParser);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mChangeTypeParser = EnumParser.newParser(ChangeType.class);
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
                        case -2131902710:
                            if (next.equals("changeType")) {
                                b = 4;
                            }
                            break;
                        case -1482998339:
                            if (next.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                                b = 1;
                            }
                            break;
                        case -1216830840:
                            if (next.equals("otherSources")) {
                                b = 2;
                            }
                            break;
                        case -103332862:
                            if (next.equals("accessChangeDate")) {
                                b = 0;
                            }
                            break;
                        case 1349547969:
                            if (next.equals("sequence")) {
                                b = 3;
                            }
                            break;
                    }
                    ChangeType changeType = null;
                    Date date = null;
                    EntityType entityType = null;
                    ImmutableList<String> immutableList = null;
                    ImmutableList<String> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.accessChangeDate = date;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            entityType = (EntityType) this.mEntityTypeParser.parse(jsonNode2);
                        }
                        builder.entityType = entityType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOtherSourceIdListParser.parse(jsonNode2);
                        }
                        builder.otherSources = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mSequenceListParser.parse(jsonNode2);
                        }
                        builder.sequence = immutableList2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            changeType = (ChangeType) this.mChangeTypeParser.parse(jsonNode2);
                        }
                        builder.changeType = changeType;
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
                                case -2131902710:
                                    if (currentName.equals("changeType")) {
                                        b = 4;
                                    }
                                    break;
                                case -1482998339:
                                    if (currentName.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                                        b = 1;
                                    }
                                    break;
                                case -1216830840:
                                    if (currentName.equals("otherSources")) {
                                        b = 2;
                                    }
                                    break;
                                case -103332862:
                                    if (currentName.equals("accessChangeDate")) {
                                        b = 0;
                                    }
                                    break;
                                case 1349547969:
                                    if (currentName.equals("sequence")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            ChangeType changeType = null;
                            Date date = null;
                            EntityType entityType = null;
                            ImmutableList<String> immutableList = null;
                            ImmutableList<String> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.accessChangeDate = date;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entityType = (EntityType) this.mEntityTypeParser.parse(jsonParser);
                                }
                                builder.entityType = entityType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mOtherSourceIdListParser.parse(jsonParser);
                                }
                                builder.otherSources = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mSequenceListParser.parse(jsonParser);
                                }
                                builder.sequence = immutableList2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    changeType = (ChangeType) this.mChangeTypeParser.parse(jsonParser);
                                }
                                builder.changeType = changeType;
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
