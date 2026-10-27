package com.amazon.atvplaybackoptimizationservice;

import com.amazon.atvplaybackoptimizationservice.Asset;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackSettingsRequest {
    public final Asset asset;
    public final Customer customer;
    public final Device device;
    public final Optional<String> responseFormatVersion;

    private GetPlaybackSettingsRequest(Builder builder) {
        this.responseFormatVersion = Optional.fromNullable(builder.responseFormatVersion);
        this.customer = (Customer) Preconditions.checkNotNull(builder.customer, "Unexpected null field: customer");
        this.asset = (Asset) Preconditions.checkNotNull(builder.asset, "Unexpected null field: asset");
        this.device = (Device) Preconditions.checkNotNull(builder.device, "Unexpected null field: device");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPlaybackSettingsRequest)) {
            return false;
        }
        GetPlaybackSettingsRequest getPlaybackSettingsRequest = (GetPlaybackSettingsRequest) obj;
        return Objects.equal(this.responseFormatVersion, getPlaybackSettingsRequest.responseFormatVersion) && Objects.equal(this.customer, getPlaybackSettingsRequest.customer) && Objects.equal(this.asset, getPlaybackSettingsRequest.asset) && Objects.equal(this.device, getPlaybackSettingsRequest.device);
    }

    public int hashCode() {
        return Objects.hashCode(this.responseFormatVersion, this.customer, this.asset, this.device);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("responseFormatVersion", this.responseFormatVersion).add("customer", this.customer).add("asset", this.asset).add("device", this.device).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Asset asset;
        public Customer customer;
        public Device device;
        public String responseFormatVersion;

        public GetPlaybackSettingsRequest build() {
            return new GetPlaybackSettingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPlaybackSettingsRequest> {
        private final Asset.Parser mAssetParser;
        private final Customer.Parser mCustomerParser;
        private final Device.Parser mDeviceParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAssetParser = new Asset.Parser(objectMapper);
            this.mCustomerParser = new Customer.Parser(objectMapper);
            this.mDeviceParser = new Device.Parser(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPlaybackSettingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackSettingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPlaybackSettingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackSettingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetPlaybackSettingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetPlaybackSettingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1335157162:
                            if (next.equals("device")) {
                                b = 3;
                            }
                            break;
                        case 93121264:
                            if (next.equals("asset")) {
                                b = 2;
                            }
                            break;
                        case 606175198:
                            if (next.equals("customer")) {
                                b = 1;
                            }
                            break;
                        case 1507128864:
                            if (next.equals("responseFormatVersion")) {
                                b = 0;
                            }
                            break;
                    }
                    Device device = null;
                    String str = null;
                    Customer customer = null;
                    Asset asset = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.responseFormatVersion = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            customer = this.mCustomerParser.parse(jsonNode2);
                        }
                        builder.customer = customer;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            asset = this.mAssetParser.parse(jsonNode2);
                        }
                        builder.asset = asset;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            device = this.mDeviceParser.parse(jsonNode2);
                        }
                        builder.device = device;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetPlaybackSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.customer, this, "customer");
            JsonParsingUtils.checkNotNull(builder.asset, this, "asset");
            JsonParsingUtils.checkNotNull(builder.device, this, "device");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetPlaybackSettingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1335157162:
                                    if (currentName.equals("device")) {
                                        b = 3;
                                    }
                                    break;
                                case 93121264:
                                    if (currentName.equals("asset")) {
                                        b = 2;
                                    }
                                    break;
                                case 606175198:
                                    if (currentName.equals("customer")) {
                                        b = 1;
                                    }
                                    break;
                                case 1507128864:
                                    if (currentName.equals("responseFormatVersion")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            Device device = null;
                            String str = null;
                            Customer customer = null;
                            Asset asset = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.responseFormatVersion = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    customer = this.mCustomerParser.parse(jsonParser);
                                }
                                builder.customer = customer;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    asset = this.mAssetParser.parse(jsonParser);
                                }
                                builder.asset = asset;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    device = this.mDeviceParser.parse(jsonParser);
                                }
                                builder.device = device;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPlaybackSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.customer, this, "customer");
                    JsonParsingUtils.checkNotNull(builder.asset, this, "asset");
                    JsonParsingUtils.checkNotNull(builder.device, this, "device");
                    return builder.build();
                }
            }
        }
    }
}
