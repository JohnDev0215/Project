package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.atv.title.v1.fragment.acquisition.v1.DisplayTreatments;
import com.amazon.atv.title.v1.fragment.acquisition.v1.Metadata;
import com.amazon.atv.title.v1.fragment.acquisition.v1.Price;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class Offer {
    public final Optional<Price> currentPrice;
    public final Optional<DisplayTreatments> displayTreatments;
    public final Optional<Metadata> metadata;
    public final Optional<String> offerAsin;
    public final Optional<String> offerToken;

    protected Offer(Builder builder) {
        this.currentPrice = Optional.fromNullable(builder.currentPrice);
        this.displayTreatments = Optional.fromNullable(builder.displayTreatments);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.offerAsin = Optional.fromNullable(builder.offerAsin);
        this.offerToken = Optional.fromNullable(builder.offerToken);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Offer)) {
            return false;
        }
        Offer offer = (Offer) obj;
        return Objects.equal(this.currentPrice, offer.currentPrice) && Objects.equal(this.displayTreatments, offer.displayTreatments) && Objects.equal(this.metadata, offer.metadata) && Objects.equal(this.offerAsin, offer.offerAsin) && Objects.equal(this.offerToken, offer.offerToken);
    }

    public int hashCode() {
        return Objects.hashCode(this.currentPrice, this.displayTreatments, this.metadata, this.offerAsin, this.offerToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("currentPrice", this.currentPrice).add("displayTreatments", this.displayTreatments).add("metadata", this.metadata).add("offerAsin", this.offerAsin).add("offerToken", this.offerToken).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Price currentPrice;
        public DisplayTreatments displayTreatments;
        public Metadata metadata;
        public String offerAsin;
        public String offerToken;

        public Offer build() {
            return new Offer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Offer> {
        private final DisplayTreatments.Parser mDisplayTreatmentsParser;
        private final Metadata.Parser mMetadataParser;
        private final Price.Parser mPriceParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mMetadataParser = new Metadata.Parser(objectMapper);
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mDisplayTreatmentsParser = new DisplayTreatments.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Offer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Offer:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Offer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Offer:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Offer parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Offer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 2;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 4;
                            }
                            break;
                        case 557711709:
                            if (next.equals("displayTreatments")) {
                                b = 1;
                            }
                            break;
                        case 1454999376:
                            if (next.equals("currentPrice")) {
                                b = 0;
                            }
                            break;
                        case 1943983443:
                            if (next.equals("offerAsin")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    Price price = null;
                    DisplayTreatments displayTreatments = null;
                    Metadata metadata = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.currentPrice = price;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            displayTreatments = this.mDisplayTreatmentsParser.parse(jsonNode2);
                        }
                        builder.displayTreatments = displayTreatments;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            metadata = this.mMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = metadata;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.offerAsin = str2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Offer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Offer parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 2;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 4;
                                    }
                                    break;
                                case 557711709:
                                    if (currentName.equals("displayTreatments")) {
                                        b = 1;
                                    }
                                    break;
                                case 1454999376:
                                    if (currentName.equals("currentPrice")) {
                                        b = 0;
                                    }
                                    break;
                                case 1943983443:
                                    if (currentName.equals("offerAsin")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            Price price = null;
                            DisplayTreatments displayTreatments = null;
                            Metadata metadata = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.currentPrice = price;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    displayTreatments = this.mDisplayTreatmentsParser.parse(jsonParser);
                                }
                                builder.displayTreatments = displayTreatments;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    metadata = this.mMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = metadata;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.offerAsin = str2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.offerToken = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Offer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
