package com.amazon.urlvending;

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

/* JADX INFO: loaded from: classes5.dex */
public class Customer {
    public final Optional<String> encryptedCustomerId;
    public final Optional<String> geoToken;
    public final Optional<String> ipAddress;
    public final Optional<String> preferredLanguage;

    private Customer(Builder builder) {
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.preferredLanguage = Optional.fromNullable(builder.preferredLanguage);
        this.ipAddress = Optional.fromNullable(builder.ipAddress);
        this.encryptedCustomerId = Optional.fromNullable(builder.encryptedCustomerId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equal(this.geoToken, customer.geoToken) && Objects.equal(this.preferredLanguage, customer.preferredLanguage) && Objects.equal(this.ipAddress, customer.ipAddress) && Objects.equal(this.encryptedCustomerId, customer.encryptedCustomerId);
    }

    public int hashCode() {
        return Objects.hashCode(this.geoToken, this.preferredLanguage, this.ipAddress, this.encryptedCustomerId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("geoToken", this.geoToken).add("preferredLanguage", this.preferredLanguage).add("ipAddress", this.ipAddress).add("encryptedCustomerId", this.encryptedCustomerId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String encryptedCustomerId;
        public String geoToken;
        public String ipAddress;
        public String preferredLanguage;

        public Customer build() {
            return new Customer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Customer> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
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
                                b = 3;
                            }
                            break;
                        case -214174983:
                            if (next.equals("preferredLanguage")) {
                                b = 1;
                            }
                            break;
                        case 1634032845:
                            if (next.equals("ipAddress")) {
                                b = 2;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.geoToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.preferredLanguage = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.ipAddress = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.encryptedCustomerId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
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
                                        b = 3;
                                    }
                                    break;
                                case -214174983:
                                    if (currentName.equals("preferredLanguage")) {
                                        b = 1;
                                    }
                                    break;
                                case 1634032845:
                                    if (currentName.equals("ipAddress")) {
                                        b = 2;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.geoToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.preferredLanguage = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.ipAddress = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.encryptedCustomerId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Customer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
