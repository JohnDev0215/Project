package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.urlvending.Asset;
import com.amazon.urlvending.Customer;
import com.amazon.urlvending.Device;
import com.amazon.urlvending.types.ConsumptionType;
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
public class UrlVendingRequestV2 {
    public final Optional<Asset> asset;
    public final Optional<ConsumptionType> consumptionType;
    public final Optional<Customer> customer;
    public final Optional<Device> device;
    public final boolean disableHoldbacks;
    public final boolean masterSessionFulfillment;
    public final Optional<String> sessionId;
    public final Optional<String> videoServiceProviderId;
    public final boolean withDebInfo;

    private UrlVendingRequestV2(Builder builder) {
        this.sessionId = Optional.fromNullable(builder.sessionId);
        this.videoServiceProviderId = Optional.fromNullable(builder.videoServiceProviderId);
        this.device = Optional.fromNullable(builder.device);
        this.masterSessionFulfillment = builder.masterSessionFulfillment;
        this.consumptionType = Optional.fromNullable(builder.consumptionType);
        this.customer = Optional.fromNullable(builder.customer);
        this.withDebInfo = builder.withDebInfo;
        this.disableHoldbacks = builder.disableHoldbacks;
        this.asset = Optional.fromNullable(builder.asset);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UrlVendingRequestV2)) {
            return false;
        }
        UrlVendingRequestV2 urlVendingRequestV2 = (UrlVendingRequestV2) obj;
        return Objects.equal(this.sessionId, urlVendingRequestV2.sessionId) && Objects.equal(this.videoServiceProviderId, urlVendingRequestV2.videoServiceProviderId) && Objects.equal(this.device, urlVendingRequestV2.device) && Objects.equal(Boolean.valueOf(this.masterSessionFulfillment), Boolean.valueOf(urlVendingRequestV2.masterSessionFulfillment)) && Objects.equal(this.consumptionType, urlVendingRequestV2.consumptionType) && Objects.equal(this.customer, urlVendingRequestV2.customer) && Objects.equal(Boolean.valueOf(this.withDebInfo), Boolean.valueOf(urlVendingRequestV2.withDebInfo)) && Objects.equal(Boolean.valueOf(this.disableHoldbacks), Boolean.valueOf(urlVendingRequestV2.disableHoldbacks)) && Objects.equal(this.asset, urlVendingRequestV2.asset);
    }

    public int hashCode() {
        return Objects.hashCode(this.sessionId, this.videoServiceProviderId, this.device, Boolean.valueOf(this.masterSessionFulfillment), this.consumptionType, this.customer, Boolean.valueOf(this.withDebInfo), Boolean.valueOf(this.disableHoldbacks), this.asset);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_SESSION_ID, this.sessionId).add("videoServiceProviderId", this.videoServiceProviderId).add("device", this.device).add("masterSessionFulfillment", this.masterSessionFulfillment).add("consumptionType", this.consumptionType).add("customer", this.customer).add("withDebInfo", this.withDebInfo).add("disableHoldbacks", this.disableHoldbacks).add("asset", this.asset).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Asset asset;
        public ConsumptionType consumptionType;
        public Customer customer;
        public Device device;
        public boolean disableHoldbacks;
        public boolean masterSessionFulfillment;
        public String sessionId;
        public String videoServiceProviderId;
        public boolean withDebInfo;

        public UrlVendingRequestV2 build() {
            return new UrlVendingRequestV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<UrlVendingRequestV2> {
        private final Asset.Parser mAssetParser;
        private final EnumParser<ConsumptionType> mConsumptionTypeParser;
        private final Customer.Parser mCustomerParser;
        private final Device.Parser mDeviceParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAssetParser = new Asset.Parser(objectMapper);
            this.mCustomerParser = new Customer.Parser(objectMapper);
            this.mDeviceParser = new Device.Parser(objectMapper);
            this.mConsumptionTypeParser = EnumParser.newParser(ConsumptionType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public UrlVendingRequestV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlVendingRequestV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public UrlVendingRequestV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlVendingRequestV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlVendingRequestV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "UrlVendingRequestV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1980493655:
                            if (next.equals("withDebInfo")) {
                                b = 6;
                            }
                            break;
                        case -1335157162:
                            if (next.equals("device")) {
                                b = 2;
                            }
                            break;
                        case -1326414587:
                            if (next.equals("disableHoldbacks")) {
                                b = 7;
                            }
                            break;
                        case -526127254:
                            if (next.equals("masterSessionFulfillment")) {
                                b = 3;
                            }
                            break;
                        case 93121264:
                            if (next.equals("asset")) {
                                b = 8;
                            }
                            break;
                        case 368095605:
                            if (next.equals("consumptionType")) {
                                b = 4;
                            }
                            break;
                        case 606175198:
                            if (next.equals("customer")) {
                                b = 5;
                            }
                            break;
                        case 607796817:
                            if (next.equals(Splash.PARAMS_SESSION_ID)) {
                                b = 0;
                            }
                            break;
                        case 1532570694:
                            if (next.equals("videoServiceProviderId")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    Asset asset = null;
                    Customer customer = null;
                    ConsumptionType consumptionType = null;
                    Device device = null;
                    String str2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.sessionId = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.videoServiceProviderId = str2;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                device = this.mDeviceParser.parse(jsonNode2);
                            }
                            builder.device = device;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field masterSessionFulfillment can't be null");
                            }
                            builder.masterSessionFulfillment = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonNode2);
                            }
                            builder.consumptionType = consumptionType;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                customer = this.mCustomerParser.parse(jsonNode2);
                            }
                            builder.customer = customer;
                            break;
                        case 6:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field withDebInfo can't be null");
                            }
                            builder.withDebInfo = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field disableHoldbacks can't be null");
                            }
                            builder.disableHoldbacks = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                asset = this.mAssetParser.parse(jsonNode2);
                            }
                            builder.asset = asset;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing UrlVendingRequestV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlVendingRequestV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1980493655:
                                    if (currentName.equals("withDebInfo")) {
                                        b = 6;
                                    }
                                    break;
                                case -1335157162:
                                    if (currentName.equals("device")) {
                                        b = 2;
                                    }
                                    break;
                                case -1326414587:
                                    if (currentName.equals("disableHoldbacks")) {
                                        b = 7;
                                    }
                                    break;
                                case -526127254:
                                    if (currentName.equals("masterSessionFulfillment")) {
                                        b = 3;
                                    }
                                    break;
                                case 93121264:
                                    if (currentName.equals("asset")) {
                                        b = 8;
                                    }
                                    break;
                                case 368095605:
                                    if (currentName.equals("consumptionType")) {
                                        b = 4;
                                    }
                                    break;
                                case 606175198:
                                    if (currentName.equals("customer")) {
                                        b = 5;
                                    }
                                    break;
                                case 607796817:
                                    if (currentName.equals(Splash.PARAMS_SESSION_ID)) {
                                        b = 0;
                                    }
                                    break;
                                case 1532570694:
                                    if (currentName.equals("videoServiceProviderId")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            Asset asset = null;
                            Customer customer = null;
                            ConsumptionType consumptionType = null;
                            Device device = null;
                            String str2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.sessionId = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.videoServiceProviderId = str2;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        device = this.mDeviceParser.parse(jsonParser);
                                    }
                                    builder.device = device;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field masterSessionFulfillment can't be null");
                                    }
                                    builder.masterSessionFulfillment = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonParser);
                                    }
                                    builder.consumptionType = consumptionType;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        customer = this.mCustomerParser.parse(jsonParser);
                                    }
                                    builder.customer = customer;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field withDebInfo can't be null");
                                    }
                                    builder.withDebInfo = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field disableHoldbacks can't be null");
                                    }
                                    builder.disableHoldbacks = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        asset = this.mAssetParser.parse(jsonParser);
                                    }
                                    builder.asset = asset;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing UrlVendingRequestV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
