package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.GeographicalCoordinates;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Customer {
    public final Optional<String> countryOfResidence;
    public final Optional<String> currentCountry;
    public final Optional<String> currentTerritory;
    public final String customerSessionId;
    public final String encryptedCustomerId;
    public final Optional<String> geoToken;
    public final Optional<GeographicalCoordinates> geographicalCoordinates;
    public final Optional<String> identityContext;
    public final String ip;
    public final Optional<String> preferredLanguage;

    private Customer(Builder builder) {
        this.currentCountry = Optional.fromNullable(builder.currentCountry);
        this.customerSessionId = (String) Preconditions.checkNotNull(builder.customerSessionId, "Unexpected null field: customerSessionId");
        this.geographicalCoordinates = Optional.fromNullable(builder.geographicalCoordinates);
        this.identityContext = Optional.fromNullable(builder.identityContext);
        this.preferredLanguage = Optional.fromNullable(builder.preferredLanguage);
        this.encryptedCustomerId = (String) Preconditions.checkNotNull(builder.encryptedCustomerId, "Unexpected null field: encryptedCustomerId");
        this.countryOfResidence = Optional.fromNullable(builder.countryOfResidence);
        this.currentTerritory = Optional.fromNullable(builder.currentTerritory);
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.ip = (String) Preconditions.checkNotNull(builder.ip, "Unexpected null field: ip");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equal(this.currentCountry, customer.currentCountry) && Objects.equal(this.customerSessionId, customer.customerSessionId) && Objects.equal(this.geographicalCoordinates, customer.geographicalCoordinates) && Objects.equal(this.identityContext, customer.identityContext) && Objects.equal(this.preferredLanguage, customer.preferredLanguage) && Objects.equal(this.encryptedCustomerId, customer.encryptedCustomerId) && Objects.equal(this.countryOfResidence, customer.countryOfResidence) && Objects.equal(this.currentTerritory, customer.currentTerritory) && Objects.equal(this.geoToken, customer.geoToken) && Objects.equal(this.ip, customer.ip);
    }

    public int hashCode() {
        return Objects.hashCode(this.currentCountry, this.customerSessionId, this.geographicalCoordinates, this.identityContext, this.preferredLanguage, this.encryptedCustomerId, this.countryOfResidence, this.currentTerritory, this.geoToken, this.ip);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("currentCountry", this.currentCountry).add("customerSessionId", this.customerSessionId).add("geographicalCoordinates", this.geographicalCoordinates).add("identityContext", this.identityContext).add("preferredLanguage", this.preferredLanguage).add("encryptedCustomerId", this.encryptedCustomerId).add("countryOfResidence", this.countryOfResidence).add("currentTerritory", this.currentTerritory).add("geoToken", this.geoToken).add("ip", this.ip).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String countryOfResidence;
        public String currentCountry;
        public String currentTerritory;
        public String customerSessionId;
        public String encryptedCustomerId;
        public String geoToken;
        public GeographicalCoordinates geographicalCoordinates;
        public String identityContext;
        public String ip;
        public String preferredLanguage;

        public Customer build() {
            return new Customer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Customer> {
        private final GeographicalCoordinates.Parser mGeographicalCoordinatesParser;
        private final SimpleParsers.StringParser mcountryOfResidenceParser;
        private final SimpleParsers.StringParser mcurrentCountryParser;
        private final SimpleParsers.StringParser mcurrentTerritoryParser;
        private final SimpleParsers.StringParser mcustomerSessionIdParser;
        private final SimpleParsers.StringParser mencryptedCustomerIdParser;
        private final SimpleParsers.StringParser midentityContextParser;
        private final SimpleParsers.StringParser mipParser;
        private final SimpleParsers.StringParser mpreferredLanguageParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGeographicalCoordinatesParser = new GeographicalCoordinates.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mcurrentCountryParser = stringParser;
            this.mcustomerSessionIdParser = stringParser;
            this.midentityContextParser = stringParser;
            this.mpreferredLanguageParser = stringParser;
            this.mencryptedCustomerIdParser = stringParser;
            this.mcountryOfResidenceParser = stringParser;
            this.mcurrentTerritoryParser = stringParser;
            this.mstringTargetParser = stringParser;
            this.mipParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Customer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Customer:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Customer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Customer:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Customer parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            GeographicalCoordinates geographicalCoordinates;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Customer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -976788831:
                            if (next.equals("currentTerritory")) {
                                b = 7;
                            }
                            break;
                        case -746989549:
                            if (next.equals("customerSessionId")) {
                                b = 1;
                            }
                            break;
                        case -632043555:
                            if (next.equals("currentCountry")) {
                                b = 0;
                            }
                            break;
                        case -408372611:
                            if (next.equals("encryptedCustomerId")) {
                                b = 5;
                            }
                            break;
                        case -214174983:
                            if (next.equals("preferredLanguage")) {
                                b = 4;
                            }
                            break;
                        case 3367:
                            if (next.equals("ip")) {
                                b = 9;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 3;
                            }
                            break;
                        case 565568889:
                            if (next.equals("countryOfResidence")) {
                                b = 6;
                            }
                            break;
                        case 959979449:
                            if (next.equals("geographicalCoordinates")) {
                                b = 2;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 8;
                            }
                            break;
                    }
                    str = null;
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    geographicalCoordinates = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mcurrentCountryParser.parse(jsonNode2);
                        }
                        builder.currentCountry = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mcustomerSessionIdParser.parse(jsonNode2);
                        }
                        builder.customerSessionId = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            geographicalCoordinates = this.mGeographicalCoordinatesParser.parse(jsonNode2);
                        }
                        builder.geographicalCoordinates = geographicalCoordinates;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mpreferredLanguageParser.parse(jsonNode2);
                        }
                        builder.preferredLanguage = str4;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mencryptedCustomerIdParser.parse(jsonNode2);
                        }
                        builder.encryptedCustomerId = str5;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mcountryOfResidenceParser.parse(jsonNode2);
                        }
                        builder.countryOfResidence = str6;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mcurrentTerritoryParser.parse(jsonNode2);
                        }
                        builder.currentTerritory = str7;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.geoToken = str8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mipParser.parse(jsonNode2);
                        }
                        builder.ip = str9;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.customerSessionId, this, "customerSessionId");
            JsonParsingUtils.checkNotNull(builder.encryptedCustomerId, this, "encryptedCustomerId");
            JsonParsingUtils.checkNotNull(builder.ip, this, "ip");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Customer parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -976788831:
                                    if (currentName.equals("currentTerritory")) {
                                        b = 7;
                                    }
                                    break;
                                case -746989549:
                                    if (currentName.equals("customerSessionId")) {
                                        b = 1;
                                    }
                                    break;
                                case -632043555:
                                    if (currentName.equals("currentCountry")) {
                                        b = 0;
                                    }
                                    break;
                                case -408372611:
                                    if (currentName.equals("encryptedCustomerId")) {
                                        b = 5;
                                    }
                                    break;
                                case -214174983:
                                    if (currentName.equals("preferredLanguage")) {
                                        b = 4;
                                    }
                                    break;
                                case 3367:
                                    if (currentName.equals("ip")) {
                                        b = 9;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 3;
                                    }
                                    break;
                                case 565568889:
                                    if (currentName.equals("countryOfResidence")) {
                                        b = 6;
                                    }
                                    break;
                                case 959979449:
                                    if (currentName.equals("geographicalCoordinates")) {
                                        b = 2;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 8;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            String str8 = null;
                            GeographicalCoordinates geographicalCoordinates = null;
                            String str9 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mcurrentCountryParser.parse(jsonParser);
                                    }
                                    builder.currentCountry = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mcustomerSessionIdParser.parse(jsonParser);
                                    }
                                    builder.customerSessionId = str9;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        geographicalCoordinates = this.mGeographicalCoordinatesParser.parse(jsonParser);
                                    }
                                    builder.geographicalCoordinates = geographicalCoordinates;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.midentityContextParser.parse(jsonParser);
                                    }
                                    builder.identityContext = str8;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mpreferredLanguageParser.parse(jsonParser);
                                    }
                                    builder.preferredLanguage = str7;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mencryptedCustomerIdParser.parse(jsonParser);
                                    }
                                    builder.encryptedCustomerId = str6;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mcountryOfResidenceParser.parse(jsonParser);
                                    }
                                    builder.countryOfResidence = str5;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mcurrentTerritoryParser.parse(jsonParser);
                                    }
                                    builder.currentTerritory = str4;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.geoToken = str3;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mipParser.parse(jsonParser);
                                    }
                                    builder.ip = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.customerSessionId, this, "customerSessionId");
                    JsonParsingUtils.checkNotNull(builder.encryptedCustomerId, this, "encryptedCustomerId");
                    JsonParsingUtils.checkNotNull(builder.ip, this, "ip");
                    return builder.build();
                }
            }
        }
    }
}
