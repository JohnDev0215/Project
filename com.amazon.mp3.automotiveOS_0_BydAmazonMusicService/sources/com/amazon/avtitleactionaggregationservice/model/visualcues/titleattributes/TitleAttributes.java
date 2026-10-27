package com.amazon.avtitleactionaggregationservice.model.visualcues.titleattributes;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.visualcues.titleattributes.SeasonTitleFragment;
import com.amazon.music.find.utils.PageUriUtils;
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
public class TitleAttributes {
    public final Optional<EntityType> entityType;
    public final Optional<SeasonTitleFragment> seasonTitleFragment;

    private TitleAttributes(Builder builder) {
        this.entityType = Optional.fromNullable(builder.entityType);
        this.seasonTitleFragment = Optional.fromNullable(builder.seasonTitleFragment);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleAttributes)) {
            return false;
        }
        TitleAttributes titleAttributes = (TitleAttributes) obj;
        return Objects.equal(this.entityType, titleAttributes.entityType) && Objects.equal(this.seasonTitleFragment, titleAttributes.seasonTitleFragment);
    }

    public int hashCode() {
        return Objects.hashCode(this.entityType, this.seasonTitleFragment);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, this.entityType).add("seasonTitleFragment", this.seasonTitleFragment).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public EntityType entityType;
        public SeasonTitleFragment seasonTitleFragment;

        public TitleAttributes build() {
            return new TitleAttributes(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleAttributes> {
        private final EnumParser<EntityType> mEntityTypeParser;
        private final SeasonTitleFragment.Parser mSeasonTitleFragmentParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSeasonTitleFragmentParser = new SeasonTitleFragment.Parser(objectMapper);
            this.mEntityTypeParser = EnumParser.newParser(EntityType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleAttributes parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleAttributes:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleAttributes parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleAttributes:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleAttributes parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleAttributes");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1482998339) {
                        if (iHashCode == -346005851 && next.equals("seasonTitleFragment")) {
                            b = 1;
                        }
                    } else if (next.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                        b = 0;
                    }
                    SeasonTitleFragment seasonTitleFragment = null;
                    EntityType entityType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            entityType = (EntityType) this.mEntityTypeParser.parse(jsonNode2);
                        }
                        builder.entityType = entityType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            seasonTitleFragment = this.mSeasonTitleFragmentParser.parse(jsonNode2);
                        }
                        builder.seasonTitleFragment = seasonTitleFragment;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleAttributes so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TitleAttributes parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1482998339) {
                                if (iHashCode == -346005851 && currentName.equals("seasonTitleFragment")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                                b = 0;
                            }
                            SeasonTitleFragment seasonTitleFragment = null;
                            EntityType entityType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entityType = (EntityType) this.mEntityTypeParser.parse(jsonParser);
                                }
                                builder.entityType = entityType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    seasonTitleFragment = this.mSeasonTitleFragmentParser.parse(jsonParser);
                                }
                                builder.seasonTitleFragment = seasonTitleFragment;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleAttributes so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
