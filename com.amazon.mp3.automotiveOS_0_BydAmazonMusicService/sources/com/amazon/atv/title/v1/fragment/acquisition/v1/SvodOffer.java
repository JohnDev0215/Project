package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.atv.title.v1.fragment.acquisition.v1.DisplayTreatments;
import com.amazon.atv.title.v1.fragment.acquisition.v1.Metadata;
import com.amazon.atv.title.v1.fragment.acquisition.v1.Offer;
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
public class SvodOffer extends Offer {
    public final Optional<String> benefitId;
    public final Optional<String> benefitName;

    private SvodOffer(Builder builder) {
        super(builder);
        this.benefitName = Optional.fromNullable(builder.benefitName);
        this.benefitId = Optional.fromNullable(builder.benefitId);
    }

    @Override // com.amazon.atv.title.v1.fragment.acquisition.v1.Offer
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SvodOffer)) {
            return false;
        }
        SvodOffer svodOffer = (SvodOffer) obj;
        return super.equals(obj) && Objects.equal(this.benefitName, svodOffer.benefitName) && Objects.equal(this.benefitId, svodOffer.benefitId);
    }

    @Override // com.amazon.atv.title.v1.fragment.acquisition.v1.Offer
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.benefitName, this.benefitId);
    }

    @Override // com.amazon.atv.title.v1.fragment.acquisition.v1.Offer
    public String toString() {
        return MoreObjects.toStringHelper(this).add("benefitName", this.benefitName).add("benefitId", this.benefitId).toString();
    }

    @NotThreadSafe
    public static class Builder extends Offer.Builder {
        public String benefitId;
        public String benefitName;

        @Override // com.amazon.atv.title.v1.fragment.acquisition.v1.Offer.Builder
        public SvodOffer build() {
            return new SvodOffer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SvodOffer> {
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
        public SvodOffer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodOffer:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SvodOffer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SvodOffer:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SvodOffer parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Price price;
            String str;
            DisplayTreatments displayTreatments;
            Metadata metadata;
            String str2;
            String str3;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SvodOffer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -550207550:
                            if (next.equals("benefitName")) {
                                b = 1;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 3;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 6;
                            }
                            break;
                        case 557711709:
                            if (next.equals("displayTreatments")) {
                                b = 2;
                            }
                            break;
                        case 723448850:
                            if (next.equals("benefitId")) {
                                b = 4;
                            }
                            break;
                        case 1454999376:
                            if (next.equals("currentPrice")) {
                                b = 0;
                            }
                            break;
                        case 1943983443:
                            if (next.equals("offerAsin")) {
                                b = 5;
                            }
                            break;
                    }
                    price = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    metadata = null;
                    displayTreatments = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodOffer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.currentPrice = price;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.benefitName = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            displayTreatments = this.mDisplayTreatmentsParser.parse(jsonNode2);
                        }
                        builder.displayTreatments = displayTreatments;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            metadata = this.mMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = metadata;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.offerAsin = str3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SvodOffer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SvodOffer parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -550207550:
                                    if (currentName.equals("benefitName")) {
                                        b = 1;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 3;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 6;
                                    }
                                    break;
                                case 557711709:
                                    if (currentName.equals("displayTreatments")) {
                                        b = 2;
                                    }
                                    break;
                                case 723448850:
                                    if (currentName.equals("benefitId")) {
                                        b = 4;
                                    }
                                    break;
                                case 1454999376:
                                    if (currentName.equals("currentPrice")) {
                                        b = 0;
                                    }
                                    break;
                                case 1943983443:
                                    if (currentName.equals("offerAsin")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            Price price = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            Metadata metadata = null;
                            DisplayTreatments displayTreatments = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        price = this.mPriceParser.parse(jsonParser);
                                    }
                                    builder.currentPrice = price;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mbasicStringParser.parse(jsonParser);
                                    }
                                    builder.benefitName = str4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        displayTreatments = this.mDisplayTreatmentsParser.parse(jsonParser);
                                    }
                                    builder.displayTreatments = displayTreatments;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        metadata = this.mMetadataParser.parse(jsonParser);
                                    }
                                    builder.metadata = metadata;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mbasicStringParser.parse(jsonParser);
                                    }
                                    builder.benefitId = str3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mbasicStringParser.parse(jsonParser);
                                    }
                                    builder.offerAsin = str2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mbasicStringParser.parse(jsonParser);
                                    }
                                    builder.offerToken = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SvodOffer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SvodOffer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
