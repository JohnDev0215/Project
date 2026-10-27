package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.atv.title.v1.AcquisitionFragment;
import com.amazon.atv.title.v1.fragment.acquisition.v1.PriceSummaries;
import com.amazon.atv.title.v1.fragment.acquisition.v1.SvodOffer;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AcquisitionFragmentV1 extends AcquisitionFragment {
    public final Optional<PriceSummaries> priceSummaries;
    public final Optional<String> summaryDisplayString;
    public final Optional<ImmutableList<SvodOffer>> svodOffers;

    private AcquisitionFragmentV1(Builder builder) {
        super(builder);
        this.summaryDisplayString = Optional.fromNullable(builder.summaryDisplayString);
        this.priceSummaries = Optional.fromNullable(builder.priceSummaries);
        this.svodOffers = Optional.fromNullable(builder.svodOffers);
    }

    @Override // com.amazon.atv.title.v1.AcquisitionFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AcquisitionFragmentV1)) {
            return false;
        }
        AcquisitionFragmentV1 acquisitionFragmentV1 = (AcquisitionFragmentV1) obj;
        return super.equals(obj) && Objects.equal(this.summaryDisplayString, acquisitionFragmentV1.summaryDisplayString) && Objects.equal(this.priceSummaries, acquisitionFragmentV1.priceSummaries) && Objects.equal(this.svodOffers, acquisitionFragmentV1.svodOffers);
    }

    @Override // com.amazon.atv.title.v1.AcquisitionFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.summaryDisplayString, this.priceSummaries, this.svodOffers);
    }

    @Override // com.amazon.atv.title.v1.AcquisitionFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).add("summaryDisplayString", this.summaryDisplayString).add("priceSummaries", this.priceSummaries).add("svodOffers", this.svodOffers).toString();
    }

    @NotThreadSafe
    public static class Builder extends AcquisitionFragment.Builder {
        public PriceSummaries priceSummaries;
        public String summaryDisplayString;
        public ImmutableList<SvodOffer> svodOffers;

        public AcquisitionFragmentV1 build() {
            return new AcquisitionFragmentV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AcquisitionFragmentV1> {
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final PriceSummaries.Parser mPriceSummariesParser;
        private final ListParser<SvodOffer> mSvodOfferListParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSvodOfferListParser = ListParser.newParser(new SvodOffer.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mbasicStringParser = stringParser;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
            this.mPriceSummariesParser = new PriceSummaries.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AcquisitionFragmentV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AcquisitionFragmentV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AcquisitionFragmentV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AcquisitionFragmentV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AcquisitionFragmentV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AcquisitionFragmentV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -162138245:
                            if (next.equals("priceSummaries")) {
                                b = 2;
                            }
                            break;
                        case -54813502:
                            if (next.equals("validUntil")) {
                                b = 4;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 412545389:
                            if (next.equals("summaryDisplayString")) {
                                b = 0;
                            }
                            break;
                        case 1438036783:
                            if (next.equals("svodOffers")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    PriceSummaries priceSummaries = null;
                    ImmutableList<SvodOffer> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.summaryDisplayString = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            priceSummaries = this.mPriceSummariesParser.parse(jsonNode2);
                        }
                        builder.priceSummaries = priceSummaries;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSvodOfferListParser.parse(jsonNode2);
                        }
                        builder.svodOffers = immutableList;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AcquisitionFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AcquisitionFragmentV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -162138245:
                                    if (currentName.equals("priceSummaries")) {
                                        b = 2;
                                    }
                                    break;
                                case -54813502:
                                    if (currentName.equals("validUntil")) {
                                        b = 4;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 412545389:
                                    if (currentName.equals("summaryDisplayString")) {
                                        b = 0;
                                    }
                                    break;
                                case 1438036783:
                                    if (currentName.equals("svodOffers")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            PriceSummaries priceSummaries = null;
                            ImmutableList<SvodOffer> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.summaryDisplayString = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = str3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    priceSummaries = this.mPriceSummariesParser.parse(jsonParser);
                                }
                                builder.priceSummaries = priceSummaries;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSvodOfferListParser.parse(jsonParser);
                                }
                                builder.svodOffers = immutableList;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mValidUntilTimestampParser.parse(jsonParser);
                                }
                                builder.validUntil = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AcquisitionFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
