package com.amazon.atv.catalog.ratings;

import com.amazon.atv.catalog.ratings.Rating;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class InternationalRating extends Rating {
    public final Optional<ImmutableList<String>> territoriesList;

    private InternationalRating(Builder builder) {
        super(builder);
        this.territoriesList = Optional.fromNullable(builder.territoriesList);
    }

    @Override // com.amazon.atv.catalog.ratings.Rating
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternationalRating) {
            return super.equals(obj) && Objects.equal(this.territoriesList, ((InternationalRating) obj).territoriesList);
        }
        return false;
    }

    @Override // com.amazon.atv.catalog.ratings.Rating
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.territoriesList);
    }

    @Override // com.amazon.atv.catalog.ratings.Rating
    public String toString() {
        return MoreObjects.toStringHelper(this).add("territoriesList", this.territoriesList).toString();
    }

    @NotThreadSafe
    public static class Builder extends Rating.Builder {
        public ImmutableList<String> territoriesList;

        @Override // com.amazon.atv.catalog.ratings.Rating.Builder
        public InternationalRating build() {
            return new InternationalRating(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InternationalRating> {
        private final ListParser<String> mStringListParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringListParser = ListParser.newParser(stringParser);
            this.mbasicStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InternationalRating parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalRating:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InternationalRating parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalRating:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InternationalRating parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InternationalRating");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3373707) {
                        if (iHashCode != 3575610) {
                            if (iHashCode == 732455670 && next.equals("territoriesList")) {
                                b = 1;
                            }
                        } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                            b = 2;
                        }
                    } else if (next.equals("name")) {
                        b = 0;
                    }
                    String str = null;
                    String str2 = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.name = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.territoriesList = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.type = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InternationalRating so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.name, this, "name");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        @Nonnull
        private InternationalRating parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3373707) {
                                if (iHashCode != 3575610) {
                                    if (iHashCode == 732455670 && currentName.equals("territoriesList")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 2;
                                }
                            } else if (currentName.equals("name")) {
                                b = 0;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.name = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.territoriesList = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.type = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InternationalRating so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.name, this, "name");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
