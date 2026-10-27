package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

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
public class RentalTreatment {
    public final Optional<Long> rentalWindowFromFirstPlayMillis;
    public final Optional<Long> rentalWindowFromPurchaseMillis;

    private RentalTreatment(Builder builder) {
        this.rentalWindowFromPurchaseMillis = Optional.fromNullable(builder.rentalWindowFromPurchaseMillis);
        this.rentalWindowFromFirstPlayMillis = Optional.fromNullable(builder.rentalWindowFromFirstPlayMillis);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RentalTreatment)) {
            return false;
        }
        RentalTreatment rentalTreatment = (RentalTreatment) obj;
        return Objects.equal(this.rentalWindowFromPurchaseMillis, rentalTreatment.rentalWindowFromPurchaseMillis) && Objects.equal(this.rentalWindowFromFirstPlayMillis, rentalTreatment.rentalWindowFromFirstPlayMillis);
    }

    public int hashCode() {
        return Objects.hashCode(this.rentalWindowFromPurchaseMillis, this.rentalWindowFromFirstPlayMillis);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rentalWindowFromPurchaseMillis", this.rentalWindowFromPurchaseMillis).add("rentalWindowFromFirstPlayMillis", this.rentalWindowFromFirstPlayMillis).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long rentalWindowFromFirstPlayMillis;
        public Long rentalWindowFromPurchaseMillis;

        public RentalTreatment build() {
            return new RentalTreatment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RentalTreatment> {
        private final SimpleParsers.LongParser mLongParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RentalTreatment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTreatment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RentalTreatment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTreatment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RentalTreatment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RentalTreatment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1194967668) {
                        if (iHashCode == 741747269 && next.equals("rentalWindowFromPurchaseMillis")) {
                            b = 0;
                        }
                    } else if (next.equals("rentalWindowFromFirstPlayMillis")) {
                        b = 1;
                    }
                    Long l = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.rentalWindowFromPurchaseMillis = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.rentalWindowFromFirstPlayMillis = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RentalTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RentalTreatment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1194967668) {
                                if (iHashCode == 741747269 && currentName.equals("rentalWindowFromPurchaseMillis")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("rentalWindowFromFirstPlayMillis")) {
                                b = 1;
                            }
                            Long l = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.rentalWindowFromPurchaseMillis = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.rentalWindowFromFirstPlayMillis = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RentalTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
