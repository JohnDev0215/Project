package com.amazon.atv.purchase;

import com.amazon.atv.purchase.PurchaseVideoRequest;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class CdpPurchaseVideoRequest extends PurchaseVideoRequest {
    public final Optional<String> pin;

    private CdpPurchaseVideoRequest(Builder builder) {
        super(builder);
        this.pin = Optional.fromNullable(builder.pin);
    }

    @Override // com.amazon.atv.purchase.PurchaseVideoRequest, com.amazon.atv.purchase.PurchaseRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CdpPurchaseVideoRequest) {
            return super.equals(obj) && Objects.equal(this.pin, ((CdpPurchaseVideoRequest) obj).pin);
        }
        return false;
    }

    @Override // com.amazon.atv.purchase.PurchaseVideoRequest, com.amazon.atv.purchase.PurchaseRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.pin);
    }

    @Override // com.amazon.atv.purchase.PurchaseVideoRequest, com.amazon.atv.purchase.PurchaseRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("pin", this.pin).toString();
    }

    @NotThreadSafe
    public static class Builder extends PurchaseVideoRequest.Builder {
        public String pin;

        @Override // com.amazon.atv.purchase.PurchaseVideoRequest.Builder
        public CdpPurchaseVideoRequest build() {
            return new CdpPurchaseVideoRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdpPurchaseVideoRequest> {
        private final SimpleParsers.StringParser mAuthSupportedModelParser;
        private final SimpleParsers.StringParser masinParser;
        private final SimpleParsers.StringParser massociateTagParser;
        private final SimpleParsers.StringParser mclientAppVersionParser;
        private final SimpleParsers.StringParser mclientIPParser;
        private final SimpleParsers.StringParser mfirmwareParser;
        private final SimpleParsers.StringParser mpaymentContractIdParser;
        private final SimpleParsers.StringParser mpinParser;
        private final SimpleParsers.StringParser mpurchaseChannelParser;
        private final SimpleParsers.StringParser mtestAuthorizationParser;
        private final SimpleParsers.StringParser mubidParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mfirmwareParser = stringParser;
            this.mpinParser = stringParser;
            this.masinParser = stringParser;
            this.mpaymentContractIdParser = stringParser;
            this.mpurchaseChannelParser = stringParser;
            this.mclientIPParser = stringParser;
            this.mubidParser = stringParser;
            this.mAuthSupportedModelParser = stringParser;
            this.massociateTagParser = stringParser;
            this.mtestAuthorizationParser = stringParser;
            this.mclientAppVersionParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdpPurchaseVideoRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseVideoRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdpPurchaseVideoRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpPurchaseVideoRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CdpPurchaseVideoRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdpPurchaseVideoRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -555337285:
                            if (next.equals("firmware")) {
                                b = 6;
                            }
                            break;
                        case -496444301:
                            if (next.equals("paymentContractId")) {
                                b = 10;
                            }
                            break;
                        case -318989118:
                            if (next.equals("purchaseChannel")) {
                                b = 0;
                            }
                            break;
                        case 110997:
                            if (next.equals("pin")) {
                                b = 1;
                            }
                            break;
                        case 3003607:
                            if (next.equals("asin")) {
                                b = 3;
                            }
                            break;
                        case 3583080:
                            if (next.equals("ubid")) {
                                b = 4;
                            }
                            break;
                        case 513689863:
                            if (next.equals("testAuthorization")) {
                                b = 9;
                            }
                            break;
                        case 908408370:
                            if (next.equals("clientIP")) {
                                b = 2;
                            }
                            break;
                        case 1582332828:
                            if (next.equals("associateTag")) {
                                b = 8;
                            }
                            break;
                        case 1627035363:
                            if (next.equals("authSupportedModel")) {
                                b = 5;
                            }
                            break;
                        case 1993867390:
                            if (next.equals("multiFactorAuthSupportedInApp")) {
                                b = 7;
                            }
                            break;
                        case 1994428834:
                            if (next.equals("clientAppVersion")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpPurchaseVideoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mpurchaseChannelParser.parse(jsonNode2);
                        }
                        builder.purchaseChannel = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mpinParser.parse(jsonNode2);
                        }
                        builder.pin = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mclientIPParser.parse(jsonNode2);
                        }
                        builder.clientIP = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.masinParser.parse(jsonNode2);
                        }
                        builder.asin = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mubidParser.parse(jsonNode2);
                        }
                        builder.ubid = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mAuthSupportedModelParser.parse(jsonNode2);
                        }
                        builder.authSupportedModel = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mfirmwareParser.parse(jsonNode2);
                        }
                        builder.firmware = str;
                        continue;
                    case 7:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field multiFactorAuthSupportedInApp can't be null");
                        }
                        builder.multiFactorAuthSupportedInApp = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        continue;
                        break;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.massociateTagParser.parse(jsonNode2);
                        }
                        builder.associateTag = str;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str = this.mtestAuthorizationParser.parse(jsonNode2);
                        }
                        builder.testAuthorization = str;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str = this.mpaymentContractIdParser.parse(jsonNode2);
                        }
                        builder.paymentContractId = str;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str = this.mclientAppVersionParser.parse(jsonNode2);
                        }
                        builder.clientAppVersion = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpPurchaseVideoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CdpPurchaseVideoRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -555337285:
                                    if (currentName.equals("firmware")) {
                                        b = 6;
                                    }
                                    break;
                                case -496444301:
                                    if (currentName.equals("paymentContractId")) {
                                        b = 10;
                                    }
                                    break;
                                case -318989118:
                                    if (currentName.equals("purchaseChannel")) {
                                        b = 0;
                                    }
                                    break;
                                case 110997:
                                    if (currentName.equals("pin")) {
                                        b = 1;
                                    }
                                    break;
                                case 3003607:
                                    if (currentName.equals("asin")) {
                                        b = 3;
                                    }
                                    break;
                                case 3583080:
                                    if (currentName.equals("ubid")) {
                                        b = 4;
                                    }
                                    break;
                                case 513689863:
                                    if (currentName.equals("testAuthorization")) {
                                        b = 9;
                                    }
                                    break;
                                case 908408370:
                                    if (currentName.equals("clientIP")) {
                                        b = 2;
                                    }
                                    break;
                                case 1582332828:
                                    if (currentName.equals("associateTag")) {
                                        b = 8;
                                    }
                                    break;
                                case 1627035363:
                                    if (currentName.equals("authSupportedModel")) {
                                        b = 5;
                                    }
                                    break;
                                case 1993867390:
                                    if (currentName.equals("multiFactorAuthSupportedInApp")) {
                                        b = 7;
                                    }
                                    break;
                                case 1994428834:
                                    if (currentName.equals("clientAppVersion")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mpurchaseChannelParser.parse(jsonParser);
                                    }
                                    builder.purchaseChannel = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mpinParser.parse(jsonParser);
                                    }
                                    builder.pin = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mclientIPParser.parse(jsonParser);
                                    }
                                    builder.clientIP = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.masinParser.parse(jsonParser);
                                    }
                                    builder.asin = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mubidParser.parse(jsonParser);
                                    }
                                    builder.ubid = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mAuthSupportedModelParser.parse(jsonParser);
                                    }
                                    builder.authSupportedModel = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mfirmwareParser.parse(jsonParser);
                                    }
                                    builder.firmware = str;
                                    continue;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field multiFactorAuthSupportedInApp can't be null");
                                    }
                                    builder.multiFactorAuthSupportedInApp = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    continue;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.massociateTagParser.parse(jsonParser);
                                    }
                                    builder.associateTag = str;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mtestAuthorizationParser.parse(jsonParser);
                                    }
                                    builder.testAuthorization = str;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mpaymentContractIdParser.parse(jsonParser);
                                    }
                                    builder.paymentContractId = str;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mclientAppVersionParser.parse(jsonParser);
                                    }
                                    builder.clientAppVersion = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpPurchaseVideoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpPurchaseVideoRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
                    return builder.build();
                }
            }
        }
    }
}
