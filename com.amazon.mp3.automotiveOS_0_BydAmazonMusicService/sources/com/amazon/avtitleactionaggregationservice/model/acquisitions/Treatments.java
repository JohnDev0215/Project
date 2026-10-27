package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.CreditsTreatment;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.DiscountTreatment;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.RentalTreatment;
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
public class Treatments {
    public final Optional<CreditsTreatment> creditsTreatment;
    public final Optional<DiscountTreatment> discountTreatment;
    public final Optional<RentalTreatment> rentalTreatment;

    private Treatments(Builder builder) {
        this.discountTreatment = Optional.fromNullable(builder.discountTreatment);
        this.creditsTreatment = Optional.fromNullable(builder.creditsTreatment);
        this.rentalTreatment = Optional.fromNullable(builder.rentalTreatment);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Treatments)) {
            return false;
        }
        Treatments treatments = (Treatments) obj;
        return Objects.equal(this.discountTreatment, treatments.discountTreatment) && Objects.equal(this.creditsTreatment, treatments.creditsTreatment) && Objects.equal(this.rentalTreatment, treatments.rentalTreatment);
    }

    public int hashCode() {
        return Objects.hashCode(this.discountTreatment, this.creditsTreatment, this.rentalTreatment);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("discountTreatment", this.discountTreatment).add("creditsTreatment", this.creditsTreatment).add("rentalTreatment", this.rentalTreatment).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CreditsTreatment creditsTreatment;
        public DiscountTreatment discountTreatment;
        public RentalTreatment rentalTreatment;

        public Treatments build() {
            return new Treatments(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Treatments> {
        private final CreditsTreatment.Parser mCreditsTreatmentParser;
        private final DiscountTreatment.Parser mDiscountTreatmentParser;
        private final RentalTreatment.Parser mRentalTreatmentParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRentalTreatmentParser = new RentalTreatment.Parser(objectMapper);
            this.mDiscountTreatmentParser = new DiscountTreatment.Parser(objectMapper);
            this.mCreditsTreatmentParser = new CreditsTreatment.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Treatments parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Treatments:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Treatments parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Treatments:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Treatments parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Treatments");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -876839881) {
                        if (iHashCode != 105948084) {
                            if (iHashCode == 1458860286 && next.equals("creditsTreatment")) {
                                b = 1;
                            }
                        } else if (next.equals("rentalTreatment")) {
                            b = 2;
                        }
                    } else if (next.equals("discountTreatment")) {
                        b = 0;
                    }
                    RentalTreatment rentalTreatment = null;
                    DiscountTreatment discountTreatment = null;
                    CreditsTreatment creditsTreatment = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            discountTreatment = this.mDiscountTreatmentParser.parse(jsonNode2);
                        }
                        builder.discountTreatment = discountTreatment;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            creditsTreatment = this.mCreditsTreatmentParser.parse(jsonNode2);
                        }
                        builder.creditsTreatment = creditsTreatment;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            rentalTreatment = this.mRentalTreatmentParser.parse(jsonNode2);
                        }
                        builder.rentalTreatment = rentalTreatment;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Treatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Treatments parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -876839881) {
                                if (iHashCode != 105948084) {
                                    if (iHashCode == 1458860286 && currentName.equals("creditsTreatment")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("rentalTreatment")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("discountTreatment")) {
                                b = 0;
                            }
                            RentalTreatment rentalTreatment = null;
                            DiscountTreatment discountTreatment = null;
                            CreditsTreatment creditsTreatment = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    discountTreatment = this.mDiscountTreatmentParser.parse(jsonParser);
                                }
                                builder.discountTreatment = discountTreatment;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    creditsTreatment = this.mCreditsTreatmentParser.parse(jsonParser);
                                }
                                builder.creditsTreatment = creditsTreatment;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rentalTreatment = this.mRentalTreatmentParser.parse(jsonParser);
                                }
                                builder.rentalTreatment = rentalTreatment;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Treatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
