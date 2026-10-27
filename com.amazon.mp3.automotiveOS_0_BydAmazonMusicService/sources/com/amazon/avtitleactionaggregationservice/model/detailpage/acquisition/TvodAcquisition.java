package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.TvodOfferType;
import com.amazon.avtitleactionaggregationservice.model.detailpage.Price;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.Treatments;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.TvodOfferMetadata;
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
public class TvodAcquisition {
    public final Optional<String> contractId;
    public final Optional<Price> finalPrice;
    public final Optional<TvodOfferMetadata> metadata;
    public final Optional<String> offerAsin;
    public final Optional<Long> offerStartDate;
    public final Optional<String> offerToken;
    public final Optional<TvodOfferType> offerType;
    public final Optional<Price> originalPrice;
    public final Optional<Treatments> treatments;

    private TvodAcquisition(Builder builder) {
        this.offerAsin = Optional.fromNullable(builder.offerAsin);
        this.contractId = Optional.fromNullable(builder.contractId);
        this.offerToken = Optional.fromNullable(builder.offerToken);
        this.originalPrice = Optional.fromNullable(builder.originalPrice);
        this.offerStartDate = Optional.fromNullable(builder.offerStartDate);
        this.treatments = Optional.fromNullable(builder.treatments);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.finalPrice = Optional.fromNullable(builder.finalPrice);
        this.offerType = Optional.fromNullable(builder.offerType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodAcquisition)) {
            return false;
        }
        TvodAcquisition tvodAcquisition = (TvodAcquisition) obj;
        return Objects.equal(this.offerAsin, tvodAcquisition.offerAsin) && Objects.equal(this.contractId, tvodAcquisition.contractId) && Objects.equal(this.offerToken, tvodAcquisition.offerToken) && Objects.equal(this.originalPrice, tvodAcquisition.originalPrice) && Objects.equal(this.offerStartDate, tvodAcquisition.offerStartDate) && Objects.equal(this.treatments, tvodAcquisition.treatments) && Objects.equal(this.metadata, tvodAcquisition.metadata) && Objects.equal(this.finalPrice, tvodAcquisition.finalPrice) && Objects.equal(this.offerType, tvodAcquisition.offerType);
    }

    public int hashCode() {
        return Objects.hashCode(this.offerAsin, this.contractId, this.offerToken, this.originalPrice, this.offerStartDate, this.treatments, this.metadata, this.finalPrice, this.offerType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("offerAsin", this.offerAsin).add("contractId", this.contractId).add("offerToken", this.offerToken).add("originalPrice", this.originalPrice).add("offerStartDate", this.offerStartDate).add("treatments", this.treatments).add("metadata", this.metadata).add("finalPrice", this.finalPrice).add("offerType", this.offerType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String contractId;
        public Price finalPrice;
        public TvodOfferMetadata metadata;
        public String offerAsin;
        public Long offerStartDate;
        public String offerToken;
        public TvodOfferType offerType;
        public Price originalPrice;
        public Treatments treatments;

        public TvodAcquisition build() {
            return new TvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodAcquisition> {
        private final SimpleParsers.LongParser mLongParser;
        private final Price.Parser mPriceParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Treatments.Parser mTreatmentsParser;
        private final TvodOfferMetadata.Parser mTvodOfferMetadataParser;
        private final EnumParser<TvodOfferType> mTvodOfferTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTreatmentsParser = new Treatments.Parser(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mTvodOfferTypeParser = EnumParser.newParser(TvodOfferType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
            this.mTvodOfferMetadataParser = new TvodOfferMetadata.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TvodAcquisition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TvodAcquisition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TvodAcquisition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            Price price;
            Long l;
            Treatments treatments;
            TvodOfferMetadata tvodOfferMetadata;
            Price price2;
            TvodOfferType tvodOfferType;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TvodAcquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2020207564:
                            if (next.equals("offerStartDate")) {
                                b = 4;
                            }
                            break;
                        case -1963616517:
                            if (next.equals("treatments")) {
                                b = 5;
                            }
                            break;
                        case -1875216013:
                            if (next.equals("finalPrice")) {
                                b = 7;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 6;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 2;
                            }
                            break;
                        case 624238029:
                            if (next.equals("contractId")) {
                                b = 1;
                            }
                            break;
                        case 1849471480:
                            if (next.equals("originalPrice")) {
                                b = 3;
                            }
                            break;
                        case 1943983443:
                            if (next.equals("offerAsin")) {
                                b = 0;
                            }
                            break;
                        case 1944555446:
                            if (next.equals("offerType")) {
                                b = 8;
                            }
                            break;
                    }
                    str = null;
                    tvodOfferType = null;
                    price2 = null;
                    tvodOfferMetadata = null;
                    treatments = null;
                    l = null;
                    price = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerAsin = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.contractId = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.originalPrice = price;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.offerStartDate = l;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            treatments = this.mTreatmentsParser.parse(jsonNode2);
                        }
                        builder.treatments = treatments;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            tvodOfferMetadata = this.mTvodOfferMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = tvodOfferMetadata;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            price2 = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.finalPrice = price2;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonNode2);
                        }
                        builder.offerType = tvodOfferType;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TvodAcquisition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2020207564:
                                    if (currentName.equals("offerStartDate")) {
                                        b = 4;
                                    }
                                    break;
                                case -1963616517:
                                    if (currentName.equals("treatments")) {
                                        b = 5;
                                    }
                                    break;
                                case -1875216013:
                                    if (currentName.equals("finalPrice")) {
                                        b = 7;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 6;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 2;
                                    }
                                    break;
                                case 624238029:
                                    if (currentName.equals("contractId")) {
                                        b = 1;
                                    }
                                    break;
                                case 1849471480:
                                    if (currentName.equals("originalPrice")) {
                                        b = 3;
                                    }
                                    break;
                                case 1943983443:
                                    if (currentName.equals("offerAsin")) {
                                        b = 0;
                                    }
                                    break;
                                case 1944555446:
                                    if (currentName.equals("offerType")) {
                                        b = 8;
                                    }
                                    break;
                            }
                            String str = null;
                            TvodOfferType tvodOfferType = null;
                            Price price = null;
                            TvodOfferMetadata tvodOfferMetadata = null;
                            Treatments treatments = null;
                            Long l = null;
                            Price price2 = null;
                            String str2 = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.offerAsin = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.contractId = str3;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.offerToken = str2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        price2 = this.mPriceParser.parse(jsonParser);
                                    }
                                    builder.originalPrice = price2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.offerStartDate = l;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        treatments = this.mTreatmentsParser.parse(jsonParser);
                                    }
                                    builder.treatments = treatments;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        tvodOfferMetadata = this.mTvodOfferMetadataParser.parse(jsonParser);
                                    }
                                    builder.metadata = tvodOfferMetadata;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        price = this.mPriceParser.parse(jsonParser);
                                    }
                                    builder.finalPrice = price;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonParser);
                                    }
                                    builder.offerType = tvodOfferType;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
