package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.LanguageProperties;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.Price;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.Treatments;
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
    public final Optional<Family> family;
    public final Optional<LanguageProperties> languageProperties;
    public final Optional<String> offerAsin;
    public final Optional<String> offerToken;
    public final Optional<Price> originalPrice;
    public final Optional<Price> price;
    public final Optional<Integer> rank;
    public final Optional<Treatments> treatments;
    public final Optional<TvodOfferType> tvodOfferType;
    public final Optional<VideoQuality> videoQuality;

    private TvodAcquisition(Builder builder) {
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.rank = Optional.fromNullable(builder.rank);
        this.tvodOfferType = Optional.fromNullable(builder.tvodOfferType);
        this.offerAsin = Optional.fromNullable(builder.offerAsin);
        this.family = Optional.fromNullable(builder.family);
        this.originalPrice = Optional.fromNullable(builder.originalPrice);
        this.offerToken = Optional.fromNullable(builder.offerToken);
        this.languageProperties = Optional.fromNullable(builder.languageProperties);
        this.treatments = Optional.fromNullable(builder.treatments);
        this.price = Optional.fromNullable(builder.price);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodAcquisition)) {
            return false;
        }
        TvodAcquisition tvodAcquisition = (TvodAcquisition) obj;
        return Objects.equal(this.videoQuality, tvodAcquisition.videoQuality) && Objects.equal(this.rank, tvodAcquisition.rank) && Objects.equal(this.tvodOfferType, tvodAcquisition.tvodOfferType) && Objects.equal(this.offerAsin, tvodAcquisition.offerAsin) && Objects.equal(this.family, tvodAcquisition.family) && Objects.equal(this.originalPrice, tvodAcquisition.originalPrice) && Objects.equal(this.offerToken, tvodAcquisition.offerToken) && Objects.equal(this.languageProperties, tvodAcquisition.languageProperties) && Objects.equal(this.treatments, tvodAcquisition.treatments) && Objects.equal(this.price, tvodAcquisition.price);
    }

    public int hashCode() {
        return Objects.hashCode(this.videoQuality, this.rank, this.tvodOfferType, this.offerAsin, this.family, this.originalPrice, this.offerToken, this.languageProperties, this.treatments, this.price);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("videoQuality", this.videoQuality).add("rank", this.rank).add("tvodOfferType", this.tvodOfferType).add("offerAsin", this.offerAsin).add("family", this.family).add("originalPrice", this.originalPrice).add("offerToken", this.offerToken).add("languageProperties", this.languageProperties).add("treatments", this.treatments).add("price", this.price).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Family family;
        public LanguageProperties languageProperties;
        public String offerAsin;
        public String offerToken;
        public Price originalPrice;
        public Price price;
        public Integer rank;
        public Treatments treatments;
        public TvodOfferType tvodOfferType;
        public VideoQuality videoQuality;

        public TvodAcquisition build() {
            return new TvodAcquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodAcquisition> {
        private final EnumParser<Family> mFamilyParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final LanguageProperties.Parser mLanguagePropertiesParser;
        private final Price.Parser mPriceParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Treatments.Parser mTreatmentsParser;
        private final EnumParser<TvodOfferType> mTvodOfferTypeParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mFamilyParser = EnumParser.newParser(Family.class);
            this.mTreatmentsParser = new Treatments.Parser(objectMapper);
            this.mTvodOfferTypeParser = EnumParser.newParser(TvodOfferType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
            this.mLanguagePropertiesParser = new LanguageProperties.Parser(objectMapper);
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
            VideoQuality videoQuality;
            Integer num;
            TvodOfferType tvodOfferType;
            String str;
            Family family;
            Price price;
            String str2;
            LanguageProperties languageProperties;
            Treatments treatments;
            Price price2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TvodAcquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2039181921:
                            if (next.equals("tvodOfferType")) {
                                b = 2;
                            }
                            break;
                        case -1963616517:
                            if (next.equals("treatments")) {
                                b = 8;
                            }
                            break;
                        case -1281860764:
                            if (next.equals("family")) {
                                b = 4;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 1;
                            }
                            break;
                        case 106934601:
                            if (next.equals("price")) {
                                b = 9;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = 6;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 0;
                            }
                            break;
                        case 1110683307:
                            if (next.equals("languageProperties")) {
                                b = 7;
                            }
                            break;
                        case 1849471480:
                            if (next.equals("originalPrice")) {
                                b = 5;
                            }
                            break;
                        case 1943983443:
                            if (next.equals("offerAsin")) {
                                b = 3;
                            }
                            break;
                    }
                    videoQuality = null;
                    price2 = null;
                    treatments = null;
                    languageProperties = null;
                    str2 = null;
                    price = null;
                    family = null;
                    str = null;
                    tvodOfferType = null;
                    num = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodAcquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonNode2);
                        }
                        builder.tvodOfferType = tvodOfferType;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerAsin = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            family = (Family) this.mFamilyParser.parse(jsonNode2);
                        }
                        builder.family = family;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.originalPrice = price;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.offerToken = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            languageProperties = this.mLanguagePropertiesParser.parse(jsonNode2);
                        }
                        builder.languageProperties = languageProperties;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            treatments = this.mTreatmentsParser.parse(jsonNode2);
                        }
                        builder.treatments = treatments;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            price2 = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.price = price2;
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
                                case -2039181921:
                                    if (currentName.equals("tvodOfferType")) {
                                        b = 2;
                                    }
                                    break;
                                case -1963616517:
                                    if (currentName.equals("treatments")) {
                                        b = 8;
                                    }
                                    break;
                                case -1281860764:
                                    if (currentName.equals("family")) {
                                        b = 4;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 1;
                                    }
                                    break;
                                case 106934601:
                                    if (currentName.equals("price")) {
                                        b = 9;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = 6;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 0;
                                    }
                                    break;
                                case 1110683307:
                                    if (currentName.equals("languageProperties")) {
                                        b = 7;
                                    }
                                    break;
                                case 1849471480:
                                    if (currentName.equals("originalPrice")) {
                                        b = 5;
                                    }
                                    break;
                                case 1943983443:
                                    if (currentName.equals("offerAsin")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            VideoQuality videoQuality = null;
                            Price price = null;
                            Treatments treatments = null;
                            LanguageProperties languageProperties = null;
                            String str = null;
                            Price price2 = null;
                            Family family = null;
                            String str2 = null;
                            TvodOfferType tvodOfferType = null;
                            Integer num = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = videoQuality;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mIntegerParser.parse(jsonParser);
                                    }
                                    builder.rank = num;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        tvodOfferType = (TvodOfferType) this.mTvodOfferTypeParser.parse(jsonParser);
                                    }
                                    builder.tvodOfferType = tvodOfferType;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.offerAsin = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        family = (Family) this.mFamilyParser.parse(jsonParser);
                                    }
                                    builder.family = family;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        price2 = this.mPriceParser.parse(jsonParser);
                                    }
                                    builder.originalPrice = price2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.offerToken = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        languageProperties = this.mLanguagePropertiesParser.parse(jsonParser);
                                    }
                                    builder.languageProperties = languageProperties;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        treatments = this.mTreatmentsParser.parse(jsonParser);
                                    }
                                    builder.treatments = treatments;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        price = this.mPriceParser.parse(jsonParser);
                                    }
                                    builder.price = price;
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
