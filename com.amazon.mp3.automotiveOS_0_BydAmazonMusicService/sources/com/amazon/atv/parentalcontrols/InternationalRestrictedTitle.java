package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ArtworkDecoration;
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
public class InternationalRestrictedTitle {
    public final Optional<ArtworkDecoration> artwork;
    public final String identifier;
    public final Optional<ImmutableList<TitleRestriction>> restrictions;

    private InternationalRestrictedTitle(Builder builder) {
        this.restrictions = Optional.fromNullable(builder.restrictions);
        this.artwork = Optional.fromNullable(builder.artwork);
        this.identifier = (String) Preconditions.checkNotNull(builder.identifier, "Unexpected null field: identifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InternationalRestrictedTitle)) {
            return false;
        }
        InternationalRestrictedTitle internationalRestrictedTitle = (InternationalRestrictedTitle) obj;
        return Objects.equal(this.restrictions, internationalRestrictedTitle.restrictions) && Objects.equal(this.artwork, internationalRestrictedTitle.artwork) && Objects.equal(this.identifier, internationalRestrictedTitle.identifier);
    }

    public int hashCode() {
        return Objects.hashCode(this.restrictions, this.artwork, this.identifier);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("restrictions", this.restrictions).add("artwork", this.artwork).add("identifier", this.identifier).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ArtworkDecoration artwork;
        public String identifier;
        public ImmutableList<TitleRestriction> restrictions;

        public InternationalRestrictedTitle build() {
            return new InternationalRestrictedTitle(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InternationalRestrictedTitle> {
        private final ArtworkDecoration.Parser mArtworkDecorationParser;
        private final SimpleParsers.StringParser midentifierParser;
        private final ListParser<TitleRestriction> mtitleRestrictionsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mtitleRestrictionsListParser = ListParser.newParser(new TitleRestriction.Parser(objectMapper));
            this.mArtworkDecorationParser = new ArtworkDecoration.Parser(objectMapper);
            this.midentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InternationalRestrictedTitle parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalRestrictedTitle:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InternationalRestrictedTitle parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InternationalRestrictedTitle:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InternationalRestrictedTitle parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InternationalRestrictedTitle");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1618432855) {
                        if (iHashCode != -1148295641) {
                            if (iHashCode == -731949068 && next.equals("artwork")) {
                                b = 1;
                            }
                        } else if (next.equals("restrictions")) {
                            b = 0;
                        }
                    } else if (next.equals("identifier")) {
                        b = 2;
                    }
                    String str = null;
                    ImmutableList<TitleRestriction> immutableList = null;
                    ArtworkDecoration artworkDecoration = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mtitleRestrictionsListParser.parse(jsonNode2);
                        }
                        builder.restrictions = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            artworkDecoration = this.mArtworkDecorationParser.parse(jsonNode2);
                        }
                        builder.artwork = artworkDecoration;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.identifier = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InternationalRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
            return builder.build();
        }

        @Nonnull
        private InternationalRestrictedTitle parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1618432855) {
                                if (iHashCode != -1148295641) {
                                    if (iHashCode == -731949068 && currentName.equals("artwork")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("restrictions")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("identifier")) {
                                b = 2;
                            }
                            String str = null;
                            ImmutableList<TitleRestriction> immutableList = null;
                            ArtworkDecoration artworkDecoration = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mtitleRestrictionsListParser.parse(jsonParser);
                                }
                                builder.restrictions = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    artworkDecoration = this.mArtworkDecorationParser.parse(jsonParser);
                                }
                                builder.artwork = artworkDecoration;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentifierParser.parse(jsonParser);
                                }
                                builder.identifier = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InternationalRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
                    return builder.build();
                }
            }
        }
    }
}
