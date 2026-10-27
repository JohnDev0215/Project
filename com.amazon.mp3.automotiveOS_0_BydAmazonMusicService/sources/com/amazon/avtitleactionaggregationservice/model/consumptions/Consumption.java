package com.amazon.avtitleactionaggregationservice.model.consumptions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.LanguageProperties;
import com.amazon.avtitleactionaggregationservice.model.consumptions.ConsumptionDetails;
import com.amazon.avtitleactionaggregationservice.model.consumptions.Eligibility;
import com.amazon.avtitleactionaggregationservice.model.consumptions.EntitlementDetails;
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
public class Consumption {
    public final Optional<ConsumptionDetails> details;
    public final Optional<Eligibility> eligibility;
    public final Optional<EntitlementDetails> entitlementDetails;
    public final Optional<LanguageProperties> languageProperties;

    private Consumption(Builder builder) {
        this.entitlementDetails = Optional.fromNullable(builder.entitlementDetails);
        this.eligibility = Optional.fromNullable(builder.eligibility);
        this.languageProperties = Optional.fromNullable(builder.languageProperties);
        this.details = Optional.fromNullable(builder.details);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Consumption)) {
            return false;
        }
        Consumption consumption = (Consumption) obj;
        return Objects.equal(this.entitlementDetails, consumption.entitlementDetails) && Objects.equal(this.eligibility, consumption.eligibility) && Objects.equal(this.languageProperties, consumption.languageProperties) && Objects.equal(this.details, consumption.details);
    }

    public int hashCode() {
        return Objects.hashCode(this.entitlementDetails, this.eligibility, this.languageProperties, this.details);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("entitlementDetails", this.entitlementDetails).add("eligibility", this.eligibility).add("languageProperties", this.languageProperties).add("details", this.details).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ConsumptionDetails details;
        public Eligibility eligibility;
        public EntitlementDetails entitlementDetails;
        public LanguageProperties languageProperties;

        public Consumption build() {
            return new Consumption(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Consumption> {
        private final ConsumptionDetails.Parser mConsumptionDetailsParser;
        private final Eligibility.Parser mEligibilityParser;
        private final EntitlementDetails.Parser mEntitlementDetailsParser;
        private final LanguageProperties.Parser mLanguagePropertiesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEntitlementDetailsParser = new EntitlementDetails.Parser(objectMapper);
            this.mConsumptionDetailsParser = new ConsumptionDetails.Parser(objectMapper);
            this.mLanguagePropertiesParser = new LanguageProperties.Parser(objectMapper);
            this.mEligibilityParser = new Eligibility.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Consumption parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Consumption:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Consumption parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Consumption:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Consumption parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Consumption");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1772461995:
                            if (next.equals("entitlementDetails")) {
                                b = 0;
                            }
                            break;
                        case -930847859:
                            if (next.equals("eligibility")) {
                                b = 1;
                            }
                            break;
                        case 1110683307:
                            if (next.equals("languageProperties")) {
                                b = 2;
                            }
                            break;
                        case 1557721666:
                            if (next.equals("details")) {
                                b = 3;
                            }
                            break;
                    }
                    ConsumptionDetails consumptionDetails = null;
                    EntitlementDetails entitlementDetails = null;
                    Eligibility eligibility = null;
                    LanguageProperties languageProperties = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            entitlementDetails = this.mEntitlementDetailsParser.parse(jsonNode2);
                        }
                        builder.entitlementDetails = entitlementDetails;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            eligibility = this.mEligibilityParser.parse(jsonNode2);
                        }
                        builder.eligibility = eligibility;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            languageProperties = this.mLanguagePropertiesParser.parse(jsonNode2);
                        }
                        builder.languageProperties = languageProperties;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            consumptionDetails = this.mConsumptionDetailsParser.parse(jsonNode2);
                        }
                        builder.details = consumptionDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Consumption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Consumption parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1772461995:
                                    if (currentName.equals("entitlementDetails")) {
                                        b = 0;
                                    }
                                    break;
                                case -930847859:
                                    if (currentName.equals("eligibility")) {
                                        b = 1;
                                    }
                                    break;
                                case 1110683307:
                                    if (currentName.equals("languageProperties")) {
                                        b = 2;
                                    }
                                    break;
                                case 1557721666:
                                    if (currentName.equals("details")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            ConsumptionDetails consumptionDetails = null;
                            EntitlementDetails entitlementDetails = null;
                            Eligibility eligibility = null;
                            LanguageProperties languageProperties = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlementDetails = this.mEntitlementDetailsParser.parse(jsonParser);
                                }
                                builder.entitlementDetails = entitlementDetails;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eligibility = this.mEligibilityParser.parse(jsonParser);
                                }
                                builder.eligibility = eligibility;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    languageProperties = this.mLanguagePropertiesParser.parse(jsonParser);
                                }
                                builder.languageProperties = languageProperties;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    consumptionDetails = this.mConsumptionDetailsParser.parse(jsonParser);
                                }
                                builder.details = consumptionDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Consumption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
