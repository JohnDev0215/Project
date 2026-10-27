package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.atv.title.v1.fragment.acquisition.v1.DiscountTreatment;
import com.amazon.atv.title.v1.fragment.acquisition.v1.RentalTreatment;
import com.amazon.atv.title.v1.fragment.acquisition.v1.SubscriptionTreatment;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
public class DisplayTreatments {
    public final Optional<DiscountTreatment> DISCOUNT;
    public final Optional<RentalTreatment> RENTAL;
    public final Optional<SubscriptionTreatment> SUBSCRIPTION;
    public final Optional<ImmutableList<TreatmentEnum>> eligibleTreatments;

    private DisplayTreatments(Builder builder) {
        this.SUBSCRIPTION = Optional.fromNullable(builder.SUBSCRIPTION);
        this.eligibleTreatments = Optional.fromNullable(builder.eligibleTreatments);
        this.RENTAL = Optional.fromNullable(builder.RENTAL);
        this.DISCOUNT = Optional.fromNullable(builder.DISCOUNT);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DisplayTreatments)) {
            return false;
        }
        DisplayTreatments displayTreatments = (DisplayTreatments) obj;
        return Objects.equal(this.SUBSCRIPTION, displayTreatments.SUBSCRIPTION) && Objects.equal(this.eligibleTreatments, displayTreatments.eligibleTreatments) && Objects.equal(this.RENTAL, displayTreatments.RENTAL) && Objects.equal(this.DISCOUNT, displayTreatments.DISCOUNT);
    }

    public int hashCode() {
        return Objects.hashCode(this.SUBSCRIPTION, this.eligibleTreatments, this.RENTAL, this.DISCOUNT);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("SUBSCRIPTION", this.SUBSCRIPTION).add("eligibleTreatments", this.eligibleTreatments).add("RENTAL", this.RENTAL).add("DISCOUNT", this.DISCOUNT).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DiscountTreatment DISCOUNT;
        public RentalTreatment RENTAL;
        public SubscriptionTreatment SUBSCRIPTION;
        public ImmutableList<TreatmentEnum> eligibleTreatments;

        public DisplayTreatments build() {
            return new DisplayTreatments(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DisplayTreatments> {
        private final DiscountTreatment.Parser mDiscountTreatmentParser;
        private final RentalTreatment.Parser mRentalTreatmentParser;
        private final SubscriptionTreatment.Parser mSubscriptionTreatmentParser;
        private final ListParser<TreatmentEnum> mTreatmentListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRentalTreatmentParser = new RentalTreatment.Parser(objectMapper);
            this.mDiscountTreatmentParser = new DiscountTreatment.Parser(objectMapper);
            this.mTreatmentListParser = ListParser.newParser(EnumParser.newParser(TreatmentEnum.class));
            this.mSubscriptionTreatmentParser = new SubscriptionTreatment.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DisplayTreatments parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DisplayTreatments:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DisplayTreatments parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DisplayTreatments:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DisplayTreatments parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DisplayTreatments");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1881247452:
                            if (next.equals("RENTAL")) {
                                b = 2;
                            }
                            break;
                        case -1636482787:
                            if (next.equals("SUBSCRIPTION")) {
                                b = 0;
                            }
                            break;
                        case 845646898:
                            if (next.equals("eligibleTreatments")) {
                                b = 1;
                            }
                            break;
                        case 1055810881:
                            if (next.equals("DISCOUNT")) {
                                b = 3;
                            }
                            break;
                    }
                    DiscountTreatment discountTreatment = null;
                    SubscriptionTreatment subscriptionTreatment = null;
                    ImmutableList<TreatmentEnum> immutableList = null;
                    RentalTreatment rentalTreatment = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            subscriptionTreatment = this.mSubscriptionTreatmentParser.parse(jsonNode2);
                        }
                        builder.SUBSCRIPTION = subscriptionTreatment;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTreatmentListParser.parse(jsonNode2);
                        }
                        builder.eligibleTreatments = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            rentalTreatment = this.mRentalTreatmentParser.parse(jsonNode2);
                        }
                        builder.RENTAL = rentalTreatment;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            discountTreatment = this.mDiscountTreatmentParser.parse(jsonNode2);
                        }
                        builder.DISCOUNT = discountTreatment;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DisplayTreatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DisplayTreatments parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1881247452:
                                    if (currentName.equals("RENTAL")) {
                                        b = 2;
                                    }
                                    break;
                                case -1636482787:
                                    if (currentName.equals("SUBSCRIPTION")) {
                                        b = 0;
                                    }
                                    break;
                                case 845646898:
                                    if (currentName.equals("eligibleTreatments")) {
                                        b = 1;
                                    }
                                    break;
                                case 1055810881:
                                    if (currentName.equals("DISCOUNT")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            DiscountTreatment discountTreatment = null;
                            SubscriptionTreatment subscriptionTreatment = null;
                            ImmutableList<TreatmentEnum> immutableList = null;
                            RentalTreatment rentalTreatment = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    subscriptionTreatment = this.mSubscriptionTreatmentParser.parse(jsonParser);
                                }
                                builder.SUBSCRIPTION = subscriptionTreatment;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTreatmentListParser.parse(jsonParser);
                                }
                                builder.eligibleTreatments = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rentalTreatment = this.mRentalTreatmentParser.parse(jsonParser);
                                }
                                builder.RENTAL = rentalTreatment;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    discountTreatment = this.mDiscountTreatmentParser.parse(jsonParser);
                                }
                                builder.DISCOUNT = discountTreatment;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DisplayTreatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
