package com.amazon.atvplaybackoptimizationservice;

import com.amazon.atvplaybackoptimizationservice.GeoInfo;
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
    public final String encryptedCustomerId;
    public final Optional<GeoInfo> geoInfo;
    public final Optional<String> geoToken;
    public final Optional<String> ip;
    public final Optional<String> marketplaceId;

    private Customer(Builder builder) {
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.ip = Optional.fromNullable(builder.ip);
        this.encryptedCustomerId = (String) Preconditions.checkNotNull(builder.encryptedCustomerId, "Unexpected null field: encryptedCustomerId");
        this.geoInfo = Optional.fromNullable(builder.geoInfo);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.countryOfResidence = Optional.fromNullable(builder.countryOfResidence);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equal(this.geoToken, customer.geoToken) && Objects.equal(this.ip, customer.ip) && Objects.equal(this.encryptedCustomerId, customer.encryptedCustomerId) && Objects.equal(this.geoInfo, customer.geoInfo) && Objects.equal(this.marketplaceId, customer.marketplaceId) && Objects.equal(this.countryOfResidence, customer.countryOfResidence);
    }

    public int hashCode() {
        return Objects.hashCode(this.geoToken, this.ip, this.encryptedCustomerId, this.geoInfo, this.marketplaceId, this.countryOfResidence);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("geoToken", this.geoToken).add("ip", this.ip).add("encryptedCustomerId", this.encryptedCustomerId).add("geoInfo", this.geoInfo).add("marketplaceId", this.marketplaceId).add("countryOfResidence", this.countryOfResidence).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String countryOfResidence;
        public String encryptedCustomerId;
        public GeoInfo geoInfo;
        public String geoToken;
        public String ip;
        public String marketplaceId;

        public Customer build() {
            return new Customer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Customer> {
        private final GeoInfo.Parser mGeoInfoParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGeoInfoParser = new GeoInfo.Parser(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
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
            JsonParsingUtils.throwIfNotObject(jsonNode, "Customer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -408372611:
                            if (next.equals("encryptedCustomerId")) {
                                b = 2;
                            }
                            break;
                        case -80062561:
                            if (next.equals("geoInfo")) {
                                b = 3;
                            }
                            break;
                        case 3367:
                            if (next.equals("ip")) {
                                b = 1;
                            }
                            break;
                        case 565568889:
                            if (next.equals("countryOfResidence")) {
                                b = 5;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 4;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    GeoInfo geoInfo = null;
                    String str5 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.geoToken = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.ip = str3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str4 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.encryptedCustomerId = str4;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            geoInfo = this.mGeoInfoParser.parse(jsonNode2);
                        }
                        builder.geoInfo = geoInfo;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str5 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str5;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.countryOfResidence = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.encryptedCustomerId, this, "encryptedCustomerId");
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
                                case -408372611:
                                    if (currentName.equals("encryptedCustomerId")) {
                                        b = 2;
                                    }
                                    break;
                                case -80062561:
                                    if (currentName.equals("geoInfo")) {
                                        b = 3;
                                    }
                                    break;
                                case 3367:
                                    if (currentName.equals("ip")) {
                                        b = 1;
                                    }
                                    break;
                                case 565568889:
                                    if (currentName.equals("countryOfResidence")) {
                                        b = 5;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 4;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            GeoInfo geoInfo = null;
                            String str5 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.geoToken = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.ip = str3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str4 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.encryptedCustomerId = str4;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    geoInfo = this.mGeoInfoParser.parse(jsonParser);
                                }
                                builder.geoInfo = geoInfo;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str5 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str5;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.countryOfResidence = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.encryptedCustomerId, this, "encryptedCustomerId");
                    return builder.build();
                }
            }
        }
    }
}
