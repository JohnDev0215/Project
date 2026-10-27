package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class Metadata {
    public final Optional<Family> family;
    public final Optional<OfferType> offerType;
    public final boolean preorder;
    public final Optional<Quality> quality;

    private Metadata(Builder builder) {
        this.preorder = builder.preorder;
        this.offerType = Optional.fromNullable(builder.offerType);
        this.quality = Optional.fromNullable(builder.quality);
        this.family = Optional.fromNullable(builder.family);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Metadata)) {
            return false;
        }
        Metadata metadata = (Metadata) obj;
        return Objects.equal(Boolean.valueOf(this.preorder), Boolean.valueOf(metadata.preorder)) && Objects.equal(this.offerType, metadata.offerType) && Objects.equal(this.quality, metadata.quality) && Objects.equal(this.family, metadata.family);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.preorder), this.offerType, this.quality, this.family);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("preorder", this.preorder).add("offerType", this.offerType).add("quality", this.quality).add("family", this.family).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Family family;
        public OfferType offerType;
        public boolean preorder;
        public Quality quality;

        public Metadata build() {
            return new Metadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Metadata> {
        private final EnumParser<Family> mFamilyParser;
        private final EnumParser<OfferType> mOfferTypeParser;
        private final EnumParser<Quality> mQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mQualityParser = EnumParser.newParser(Quality.class);
            this.mOfferTypeParser = EnumParser.newParser(OfferType.class);
            this.mFamilyParser = EnumParser.newParser(Family.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Metadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Metadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Metadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Metadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Metadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Metadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1281860764:
                            if (next.equals("family")) {
                                b = 3;
                            }
                            break;
                        case -1279982965:
                            if (next.equals("preorder")) {
                                b = 0;
                            }
                            break;
                        case 651215103:
                            if (next.equals("quality")) {
                                b = 2;
                            }
                            break;
                        case 1944555446:
                            if (next.equals("offerType")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b != 0) {
                        Family family = null;
                        OfferType offerType = null;
                        Quality quality = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                offerType = (OfferType) this.mOfferTypeParser.parse(jsonNode2);
                            }
                            builder.offerType = offerType;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                quality = (Quality) this.mQualityParser.parse(jsonNode2);
                            }
                            builder.quality = quality;
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                family = (Family) this.mFamilyParser.parse(jsonNode2);
                            }
                            builder.family = family;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field preorder can't be null");
                        }
                        builder.preorder = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Metadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Metadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1281860764:
                                    if (currentName.equals("family")) {
                                        b = 3;
                                    }
                                    break;
                                case -1279982965:
                                    if (currentName.equals("preorder")) {
                                        b = 0;
                                    }
                                    break;
                                case 651215103:
                                    if (currentName.equals("quality")) {
                                        b = 2;
                                    }
                                    break;
                                case 1944555446:
                                    if (currentName.equals("offerType")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                Family family = null;
                                OfferType offerType = null;
                                Quality quality = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        offerType = (OfferType) this.mOfferTypeParser.parse(jsonParser);
                                    }
                                    builder.offerType = offerType;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        quality = (Quality) this.mQualityParser.parse(jsonParser);
                                    }
                                    builder.quality = quality;
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        family = (Family) this.mFamilyParser.parse(jsonParser);
                                    }
                                    builder.family = family;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field preorder can't be null");
                                }
                                builder.preorder = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Metadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
