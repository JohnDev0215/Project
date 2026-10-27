package com.amazon.atv.purchase;

import com.amazon.atv.purchase.PurchaseRequest;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseOfferRequest extends PurchaseRequest {
    public final Optional<String> countryOfResidence;
    public final Optional<String> currentCountry;
    public final Optional<String> empApplicationId;
    public final boolean isEmpTestOrder;
    public final String offerToken;
    public final Optional<String> paymentContractId;
    public final boolean useEmpPayment;

    private PurchaseOfferRequest(Builder builder) {
        super(builder);
        this.countryOfResidence = Optional.fromNullable(builder.countryOfResidence);
        this.useEmpPayment = builder.useEmpPayment;
        this.isEmpTestOrder = builder.isEmpTestOrder;
        this.offerToken = (String) Preconditions.checkNotNull(builder.offerToken, "Unexpected null field: offerToken");
        this.currentCountry = Optional.fromNullable(builder.currentCountry);
        this.empApplicationId = Optional.fromNullable(builder.empApplicationId);
        this.paymentContractId = Optional.fromNullable(builder.paymentContractId);
    }

    @Override // com.amazon.atv.purchase.PurchaseRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PurchaseOfferRequest)) {
            return false;
        }
        PurchaseOfferRequest purchaseOfferRequest = (PurchaseOfferRequest) obj;
        return super.equals(obj) && Objects.equal(this.countryOfResidence, purchaseOfferRequest.countryOfResidence) && Objects.equal(Boolean.valueOf(this.useEmpPayment), Boolean.valueOf(purchaseOfferRequest.useEmpPayment)) && Objects.equal(Boolean.valueOf(this.isEmpTestOrder), Boolean.valueOf(purchaseOfferRequest.isEmpTestOrder)) && Objects.equal(this.offerToken, purchaseOfferRequest.offerToken) && Objects.equal(this.currentCountry, purchaseOfferRequest.currentCountry) && Objects.equal(this.empApplicationId, purchaseOfferRequest.empApplicationId) && Objects.equal(this.paymentContractId, purchaseOfferRequest.paymentContractId);
    }

    @Override // com.amazon.atv.purchase.PurchaseRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.countryOfResidence, Boolean.valueOf(this.useEmpPayment), Boolean.valueOf(this.isEmpTestOrder), this.offerToken, this.currentCountry, this.empApplicationId, this.paymentContractId);
    }

    @Override // com.amazon.atv.purchase.PurchaseRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("countryOfResidence", this.countryOfResidence).add("useEmpPayment", this.useEmpPayment).add("isEmpTestOrder", this.isEmpTestOrder).add("offerToken", this.offerToken).add("currentCountry", this.currentCountry).add("empApplicationId", this.empApplicationId).add("paymentContractId", this.paymentContractId).toString();
    }

    @NotThreadSafe
    public static class Builder extends PurchaseRequest.Builder {
        public String countryOfResidence;
        public String currentCountry;
        public String empApplicationId;
        public boolean isEmpTestOrder;
        public String offerToken;
        public String paymentContractId;
        public boolean useEmpPayment;

        public PurchaseOfferRequest build() {
            return new PurchaseOfferRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PurchaseOfferRequest> {
        private final SimpleParsers.StringParser mAuthSupportedModelParser;
        private final SimpleParsers.StringParser massociateTagParser;
        private final SimpleParsers.StringParser mclientAppVersionParser;
        private final SimpleParsers.StringParser mclientIPParser;
        private final SimpleParsers.StringParser mcountryOfResidenceParser;
        private final SimpleParsers.StringParser mcurrentCountryParser;
        private final SimpleParsers.StringParser mempApplicationIdParser;
        private final SimpleParsers.StringParser mfirmwareParser;
        private final SimpleParsers.StringParser mofferTokenParser;
        private final SimpleParsers.StringParser mpaymentContractIdParser;
        private final SimpleParsers.StringParser mpurchaseChannelParser;
        private final SimpleParsers.StringParser mtestAuthorizationParser;
        private final SimpleParsers.StringParser mubidParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.massociateTagParser = stringParser;
            this.mcurrentCountryParser = stringParser;
            this.mcountryOfResidenceParser = stringParser;
            this.mofferTokenParser = stringParser;
            this.mempApplicationIdParser = stringParser;
            this.mtestAuthorizationParser = stringParser;
            this.mclientAppVersionParser = stringParser;
            this.mpaymentContractIdParser = stringParser;
            this.mpurchaseChannelParser = stringParser;
            this.mclientIPParser = stringParser;
            this.mubidParser = stringParser;
            this.mAuthSupportedModelParser = stringParser;
            this.mfirmwareParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PurchaseOfferRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseOfferRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PurchaseOfferRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseOfferRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PurchaseOfferRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PurchaseOfferRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -726600443:
                            if (next.equals("useEmpPayment")) {
                                b = 8;
                            }
                            break;
                        case -632043555:
                            if (next.equals("currentCountry")) {
                                b = 3;
                            }
                            break;
                        case -555337285:
                            if (next.equals("firmware")) {
                                b = 10;
                            }
                            break;
                        case -496444301:
                            if (next.equals("paymentContractId")) {
                                b = 5;
                            }
                            break;
                        case -318989118:
                            if (next.equals("purchaseChannel")) {
                                b = 6;
                            }
                            break;
                        case 3583080:
                            if (next.equals("ubid")) {
                                b = 0;
                            }
                            break;
                        case 89663139:
                            if (next.equals("empApplicationId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 151374077:
                            if (next.equals("offerToken")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 513689863:
                            if (next.equals("testAuthorization")) {
                                b = 4;
                            }
                            break;
                        case 565568889:
                            if (next.equals("countryOfResidence")) {
                                b = 7;
                            }
                            break;
                        case 908408370:
                            if (next.equals("clientIP")) {
                                b = 9;
                            }
                            break;
                        case 1366247486:
                            if (next.equals("isEmpTestOrder")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1582332828:
                            if (next.equals("associateTag")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1627035363:
                            if (next.equals("authSupportedModel")) {
                                b = 1;
                            }
                            break;
                        case 1993867390:
                            if (next.equals("multiFactorAuthSupportedInApp")) {
                                b = 2;
                            }
                            break;
                        case 1994428834:
                            if (next.equals("clientAppVersion")) {
                                b = 15;
                            }
                            break;
                    }
                    String str = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mubidParser.parse(jsonNode2);
                            }
                            builder.ubid = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str = this.mAuthSupportedModelParser.parse(jsonNode2);
                            }
                            builder.authSupportedModel = str;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field multiFactorAuthSupportedInApp can't be null");
                            }
                            builder.multiFactorAuthSupportedInApp = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                str = this.mcurrentCountryParser.parse(jsonNode2);
                            }
                            builder.currentCountry = str;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str = this.mtestAuthorizationParser.parse(jsonNode2);
                            }
                            builder.testAuthorization = str;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str = this.mpaymentContractIdParser.parse(jsonNode2);
                            }
                            builder.paymentContractId = str;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str = this.mpurchaseChannelParser.parse(jsonNode2);
                            }
                            builder.purchaseChannel = str;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                str = this.mcountryOfResidenceParser.parse(jsonNode2);
                            }
                            builder.countryOfResidence = str;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field useEmpPayment can't be null");
                            }
                            builder.useEmpPayment = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                str = this.mclientIPParser.parse(jsonNode2);
                            }
                            builder.clientIP = str;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                str = this.mfirmwareParser.parse(jsonNode2);
                            }
                            builder.firmware = str;
                            break;
                        case 11:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field isEmpTestOrder can't be null");
                            }
                            builder.isEmpTestOrder = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                str = this.mofferTokenParser.parse(jsonNode2);
                            }
                            builder.offerToken = str;
                            break;
                        case 13:
                            if (!jsonNode2.isNull()) {
                                str = this.massociateTagParser.parse(jsonNode2);
                            }
                            builder.associateTag = str;
                            break;
                        case 14:
                            if (!jsonNode2.isNull()) {
                                str = this.mempApplicationIdParser.parse(jsonNode2);
                            }
                            builder.empApplicationId = str;
                            break;
                        case 15:
                            if (!jsonNode2.isNull()) {
                                str = this.mclientAppVersionParser.parse(jsonNode2);
                            }
                            builder.clientAppVersion = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PurchaseOfferRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.offerToken, this, "offerToken");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PurchaseOfferRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -726600443:
                                    if (currentName.equals("useEmpPayment")) {
                                        b = 8;
                                    }
                                    break;
                                case -632043555:
                                    if (currentName.equals("currentCountry")) {
                                        b = 3;
                                    }
                                    break;
                                case -555337285:
                                    if (currentName.equals("firmware")) {
                                        b = 10;
                                    }
                                    break;
                                case -496444301:
                                    if (currentName.equals("paymentContractId")) {
                                        b = 5;
                                    }
                                    break;
                                case -318989118:
                                    if (currentName.equals("purchaseChannel")) {
                                        b = 6;
                                    }
                                    break;
                                case 3583080:
                                    if (currentName.equals("ubid")) {
                                        b = 0;
                                    }
                                    break;
                                case 89663139:
                                    if (currentName.equals("empApplicationId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 151374077:
                                    if (currentName.equals("offerToken")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 513689863:
                                    if (currentName.equals("testAuthorization")) {
                                        b = 4;
                                    }
                                    break;
                                case 565568889:
                                    if (currentName.equals("countryOfResidence")) {
                                        b = 7;
                                    }
                                    break;
                                case 908408370:
                                    if (currentName.equals("clientIP")) {
                                        b = 9;
                                    }
                                    break;
                                case 1366247486:
                                    if (currentName.equals("isEmpTestOrder")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1582332828:
                                    if (currentName.equals("associateTag")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1627035363:
                                    if (currentName.equals("authSupportedModel")) {
                                        b = 1;
                                    }
                                    break;
                                case 1993867390:
                                    if (currentName.equals("multiFactorAuthSupportedInApp")) {
                                        b = 2;
                                    }
                                    break;
                                case 1994428834:
                                    if (currentName.equals("clientAppVersion")) {
                                        b = 15;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mubidParser.parse(jsonParser);
                                    }
                                    builder.ubid = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mAuthSupportedModelParser.parse(jsonParser);
                                    }
                                    builder.authSupportedModel = str;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field multiFactorAuthSupportedInApp can't be null");
                                    }
                                    builder.multiFactorAuthSupportedInApp = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mcurrentCountryParser.parse(jsonParser);
                                    }
                                    builder.currentCountry = str;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mtestAuthorizationParser.parse(jsonParser);
                                    }
                                    builder.testAuthorization = str;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mpaymentContractIdParser.parse(jsonParser);
                                    }
                                    builder.paymentContractId = str;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mpurchaseChannelParser.parse(jsonParser);
                                    }
                                    builder.purchaseChannel = str;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mcountryOfResidenceParser.parse(jsonParser);
                                    }
                                    builder.countryOfResidence = str;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field useEmpPayment can't be null");
                                    }
                                    builder.useEmpPayment = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mclientIPParser.parse(jsonParser);
                                    }
                                    builder.clientIP = str;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mfirmwareParser.parse(jsonParser);
                                    }
                                    builder.firmware = str;
                                    break;
                                case 11:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field isEmpTestOrder can't be null");
                                    }
                                    builder.isEmpTestOrder = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mofferTokenParser.parse(jsonParser);
                                    }
                                    builder.offerToken = str;
                                    break;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.massociateTagParser.parse(jsonParser);
                                    }
                                    builder.associateTag = str;
                                    break;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mempApplicationIdParser.parse(jsonParser);
                                    }
                                    builder.empApplicationId = str;
                                    break;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mclientAppVersionParser.parse(jsonParser);
                                    }
                                    builder.clientAppVersion = str;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PurchaseOfferRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.offerToken, this, "offerToken");
                    return builder.build();
                }
            }
        }
    }
}
