package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.TitleObfuscation;
import com.amazon.atv.parentalcontrols.TitleRestriction;
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
public class RestrictedTitle {
    public final String asin;
    public final Optional<TitleObfuscation> obfuscation;
    public final Optional<ImmutableList<TitleRestriction>> restrictions;

    private RestrictedTitle(Builder builder) {
        this.restrictions = Optional.fromNullable(builder.restrictions);
        this.asin = (String) Preconditions.checkNotNull(builder.asin, "Unexpected null field: asin");
        this.obfuscation = Optional.fromNullable(builder.obfuscation);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestrictedTitle)) {
            return false;
        }
        RestrictedTitle restrictedTitle = (RestrictedTitle) obj;
        return Objects.equal(this.restrictions, restrictedTitle.restrictions) && Objects.equal(this.asin, restrictedTitle.asin) && Objects.equal(this.obfuscation, restrictedTitle.obfuscation);
    }

    public int hashCode() {
        return Objects.hashCode(this.restrictions, this.asin, this.obfuscation);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("restrictions", this.restrictions).add("asin", this.asin).add("obfuscation", this.obfuscation).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String asin;
        public TitleObfuscation obfuscation;
        public ImmutableList<TitleRestriction> restrictions;

        public RestrictedTitle build() {
            return new RestrictedTitle(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RestrictedTitle> {
        private final TitleObfuscation.Parser mTitleObfuscationParser;
        private final SimpleParsers.StringParser masinParser;
        private final ListParser<TitleRestriction> mtitleRestrictionsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTitleObfuscationParser = new TitleObfuscation.Parser(objectMapper);
            this.mtitleRestrictionsListParser = ListParser.newParser(new TitleRestriction.Parser(objectMapper));
            this.masinParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RestrictedTitle parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RestrictedTitle:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RestrictedTitle parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RestrictedTitle:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RestrictedTitle parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RestrictedTitle");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1148295641) {
                        if (iHashCode != 3003607) {
                            if (iHashCode == 308161155 && next.equals("obfuscation")) {
                                b = 2;
                            }
                        } else if (next.equals("asin")) {
                            b = 1;
                        }
                    } else if (next.equals("restrictions")) {
                        b = 0;
                    }
                    TitleObfuscation titleObfuscation = null;
                    ImmutableList<TitleRestriction> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mtitleRestrictionsListParser.parse(jsonNode2);
                        }
                        builder.restrictions = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.masinParser.parse(jsonNode2);
                        }
                        builder.asin = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            titleObfuscation = this.mTitleObfuscationParser.parse(jsonNode2);
                        }
                        builder.obfuscation = titleObfuscation;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
            return builder.build();
        }

        @Nonnull
        private RestrictedTitle parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1148295641) {
                                if (iHashCode != 3003607) {
                                    if (iHashCode == 308161155 && currentName.equals("obfuscation")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("asin")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("restrictions")) {
                                b = 0;
                            }
                            TitleObfuscation titleObfuscation = null;
                            ImmutableList<TitleRestriction> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mtitleRestrictionsListParser.parse(jsonParser);
                                }
                                builder.restrictions = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.masinParser.parse(jsonParser);
                                }
                                builder.asin = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleObfuscation = this.mTitleObfuscationParser.parse(jsonParser);
                                }
                                builder.obfuscation = titleObfuscation;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
                    return builder.build();
                }
            }
        }
    }
}
