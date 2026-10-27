package com.amazon.atv.parentalcontrols;

import com.amazon.atv.catalog.ratings.Rating;
import com.amazon.atv.parentalcontrols.AllowedContent;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
public class BrowseRestrictions {
    public final Optional<AllowedContent> allowedContent;
    public final Optional<ImmutableList<Rating>> allowedRatings;

    private BrowseRestrictions(Builder builder) {
        this.allowedContent = Optional.fromNullable(builder.allowedContent);
        this.allowedRatings = Optional.fromNullable(builder.allowedRatings);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrowseRestrictions)) {
            return false;
        }
        BrowseRestrictions browseRestrictions = (BrowseRestrictions) obj;
        return Objects.equal(this.allowedContent, browseRestrictions.allowedContent) && Objects.equal(this.allowedRatings, browseRestrictions.allowedRatings);
    }

    public int hashCode() {
        return Objects.hashCode(this.allowedContent, this.allowedRatings);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("allowedContent", this.allowedContent).add("allowedRatings", this.allowedRatings).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AllowedContent allowedContent;
        public ImmutableList<Rating> allowedRatings;

        public BrowseRestrictions build() {
            return new BrowseRestrictions(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BrowseRestrictions> {
        private final AllowedContent.Parser mAllowedContentParser;
        private final ListParser<Rating> mratingsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAllowedContentParser = new AllowedContent.Parser(objectMapper);
            this.mratingsListParser = ListParser.newParser(new Rating.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BrowseRestrictions parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowseRestrictions:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BrowseRestrictions parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowseRestrictions:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private BrowseRestrictions parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "BrowseRestrictions");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1064574385) {
                        if (iHashCode == 1096641454 && next.equals("allowedRatings")) {
                            b = 1;
                        }
                    } else if (next.equals("allowedContent")) {
                        b = 0;
                    }
                    ImmutableList<Rating> immutableList = null;
                    AllowedContent allowedContent = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            allowedContent = this.mAllowedContentParser.parse(jsonNode2);
                        }
                        builder.allowedContent = allowedContent;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mratingsListParser.parse(jsonNode2);
                        }
                        builder.allowedRatings = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BrowseRestrictions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private BrowseRestrictions parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1064574385) {
                                if (iHashCode == 1096641454 && currentName.equals("allowedRatings")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("allowedContent")) {
                                b = 0;
                            }
                            ImmutableList<Rating> immutableList = null;
                            AllowedContent allowedContent = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    allowedContent = this.mAllowedContentParser.parse(jsonParser);
                                }
                                builder.allowedContent = allowedContent;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mratingsListParser.parse(jsonParser);
                                }
                                builder.allowedRatings = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BrowseRestrictions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
