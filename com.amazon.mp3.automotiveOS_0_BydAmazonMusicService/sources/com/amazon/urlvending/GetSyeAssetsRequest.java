package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.urlvending.SyeAdSettings;
import com.amazon.urlvending.SyeAsset;
import com.amazon.urlvending.SyeCustomer;
import com.amazon.urlvending.SyeDevice;
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
public class GetSyeAssetsRequest {
    public final Optional<SyeAdSettings> adSettings;
    public final Optional<SyeAsset> asset;
    public final Optional<SyeCustomer> customer;
    public final Optional<SyeDevice> device;

    private GetSyeAssetsRequest(Builder builder) {
        this.asset = Optional.fromNullable(builder.asset);
        this.adSettings = Optional.fromNullable(builder.adSettings);
        this.customer = Optional.fromNullable(builder.customer);
        this.device = Optional.fromNullable(builder.device);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSyeAssetsRequest)) {
            return false;
        }
        GetSyeAssetsRequest getSyeAssetsRequest = (GetSyeAssetsRequest) obj;
        return Objects.equal(this.asset, getSyeAssetsRequest.asset) && Objects.equal(this.adSettings, getSyeAssetsRequest.adSettings) && Objects.equal(this.customer, getSyeAssetsRequest.customer) && Objects.equal(this.device, getSyeAssetsRequest.device);
    }

    public int hashCode() {
        return Objects.hashCode(this.asset, this.adSettings, this.customer, this.device);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("asset", this.asset).add("adSettings", this.adSettings).add("customer", this.customer).add("device", this.device).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public SyeAdSettings adSettings;
        public SyeAsset asset;
        public SyeCustomer customer;
        public SyeDevice device;

        public GetSyeAssetsRequest build() {
            return new GetSyeAssetsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetSyeAssetsRequest> {
        private final SyeAdSettings.Parser mSyeAdSettingsParser;
        private final SyeAsset.Parser mSyeAssetParser;
        private final SyeCustomer.Parser mSyeCustomerParser;
        private final SyeDevice.Parser mSyeDeviceParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeAdSettingsParser = new SyeAdSettings.Parser(objectMapper);
            this.mSyeCustomerParser = new SyeCustomer.Parser(objectMapper);
            this.mSyeAssetParser = new SyeAsset.Parser(objectMapper);
            this.mSyeDeviceParser = new SyeDevice.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetSyeAssetsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetSyeAssetsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetSyeAssetsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSyeAssetsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetSyeAssetsRequest");
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
                        case -739485338:
                            if (next.equals("adSettings")) {
                                b = 1;
                            }
                            break;
                        case 93121264:
                            if (next.equals("asset")) {
                                b = 0;
                            }
                            break;
                        case 606175198:
                            if (next.equals("customer")) {
                                b = 2;
                            }
                            break;
                    }
                    SyeDevice syeDevice = null;
                    SyeAsset syeAsset = null;
                    SyeAdSettings syeAdSettings = null;
                    SyeCustomer syeCustomer = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            syeAsset = this.mSyeAssetParser.parse(jsonNode2);
                        }
                        builder.asset = syeAsset;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            syeAdSettings = this.mSyeAdSettingsParser.parse(jsonNode2);
                        }
                        builder.adSettings = syeAdSettings;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            syeCustomer = this.mSyeCustomerParser.parse(jsonNode2);
                        }
                        builder.customer = syeCustomer;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            syeDevice = this.mSyeDeviceParser.parse(jsonNode2);
                        }
                        builder.device = syeDevice;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetSyeAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetSyeAssetsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -739485338:
                                    if (currentName.equals("adSettings")) {
                                        b = 1;
                                    }
                                    break;
                                case 93121264:
                                    if (currentName.equals("asset")) {
                                        b = 0;
                                    }
                                    break;
                                case 606175198:
                                    if (currentName.equals("customer")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            SyeDevice syeDevice = null;
                            SyeAsset syeAsset = null;
                            SyeAdSettings syeAdSettings = null;
                            SyeCustomer syeCustomer = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    syeAsset = this.mSyeAssetParser.parse(jsonParser);
                                }
                                builder.asset = syeAsset;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    syeAdSettings = this.mSyeAdSettingsParser.parse(jsonParser);
                                }
                                builder.adSettings = syeAdSettings;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    syeCustomer = this.mSyeCustomerParser.parse(jsonParser);
                                }
                                builder.customer = syeCustomer;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    syeDevice = this.mSyeDeviceParser.parse(jsonParser);
                                }
                                builder.device = syeDevice;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetSyeAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
