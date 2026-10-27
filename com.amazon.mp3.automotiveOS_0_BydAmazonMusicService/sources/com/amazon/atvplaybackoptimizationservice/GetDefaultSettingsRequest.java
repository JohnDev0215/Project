package com.amazon.atvplaybackoptimizationservice;

import com.amazon.atvplaybackoptimizationservice.Customer;
import com.amazon.atvplaybackoptimizationservice.Device;
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
public class GetDefaultSettingsRequest {
    public final Optional<Customer> customer;
    public final Optional<Device> device;
    public final Optional<String> responseFormatVersion;

    private GetDefaultSettingsRequest(Builder builder) {
        this.responseFormatVersion = Optional.fromNullable(builder.responseFormatVersion);
        this.device = Optional.fromNullable(builder.device);
        this.customer = Optional.fromNullable(builder.customer);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetDefaultSettingsRequest)) {
            return false;
        }
        GetDefaultSettingsRequest getDefaultSettingsRequest = (GetDefaultSettingsRequest) obj;
        return Objects.equal(this.responseFormatVersion, getDefaultSettingsRequest.responseFormatVersion) && Objects.equal(this.device, getDefaultSettingsRequest.device) && Objects.equal(this.customer, getDefaultSettingsRequest.customer);
    }

    public int hashCode() {
        return Objects.hashCode(this.responseFormatVersion, this.device, this.customer);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("responseFormatVersion", this.responseFormatVersion).add("device", this.device).add("customer", this.customer).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Customer customer;
        public Device device;
        public String responseFormatVersion;

        public GetDefaultSettingsRequest build() {
            return new GetDefaultSettingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetDefaultSettingsRequest> {
        private final Customer.Parser mCustomerParser;
        private final Device.Parser mDeviceParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDeviceParser = new Device.Parser(objectMapper);
            this.mCustomerParser = new Customer.Parser(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetDefaultSettingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetDefaultSettingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetDefaultSettingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetDefaultSettingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetDefaultSettingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetDefaultSettingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1335157162) {
                        if (iHashCode != 606175198) {
                            if (iHashCode == 1507128864 && next.equals("responseFormatVersion")) {
                                b = 0;
                            }
                        } else if (next.equals("customer")) {
                            b = 2;
                        }
                    } else if (next.equals("device")) {
                        b = 1;
                    }
                    Customer customer = null;
                    String str = null;
                    Device device = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.responseFormatVersion = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            device = this.mDeviceParser.parse(jsonNode2);
                        }
                        builder.device = device;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            customer = this.mCustomerParser.parse(jsonNode2);
                        }
                        builder.customer = customer;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetDefaultSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetDefaultSettingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1335157162) {
                                if (iHashCode != 606175198) {
                                    if (iHashCode == 1507128864 && currentName.equals("responseFormatVersion")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("customer")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("device")) {
                                b = 1;
                            }
                            Customer customer = null;
                            String str = null;
                            Device device = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.responseFormatVersion = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    device = this.mDeviceParser.parse(jsonParser);
                                }
                                builder.device = device;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    customer = this.mCustomerParser.parse(jsonParser);
                                }
                                builder.customer = customer;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetDefaultSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
