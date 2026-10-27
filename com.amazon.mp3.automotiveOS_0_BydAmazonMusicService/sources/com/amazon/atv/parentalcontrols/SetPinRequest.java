package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.CustomerRequest;
import com.amazon.atv.parentalcontrols.PinUsages;
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
public class SetPinRequest extends CustomerRequest {
    public final Optional<String> pin;
    public final Optional<PinUsages> usages;

    private SetPinRequest(Builder builder) {
        super(builder);
        this.usages = Optional.fromNullable(builder.usages);
        this.pin = Optional.fromNullable(builder.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetPinRequest)) {
            return false;
        }
        SetPinRequest setPinRequest = (SetPinRequest) obj;
        return super.equals(obj) && Objects.equal(this.usages, setPinRequest.usages) && Objects.equal(this.pin, setPinRequest.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.usages, this.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("usages", this.usages).add("pin", this.pin).toString();
    }

    @NotThreadSafe
    public static class Builder extends CustomerRequest.Builder {
        public String pin;
        public PinUsages usages;

        public SetPinRequest build() {
            return new SetPinRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SetPinRequest> {
        private final PinUsages.Parser mPinUsagesParser;
        private final SimpleParsers.StringParser midentityContextParser;
        private final SimpleParsers.StringParser mlegacyCustomerIdParser;
        private final SimpleParsers.StringParser mmarketplaceIdParser;
        private final SimpleParsers.StringParser mpinValueParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinUsagesParser = new PinUsages.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mpinValueParser = stringParser;
            this.midentityContextParser = stringParser;
            this.mlegacyCustomerIdParser = stringParser;
            this.mmarketplaceIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SetPinRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetPinRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SetPinRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetPinRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SetPinRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SetPinRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 4;
                            }
                            break;
                        case -836159758:
                            if (next.equals("usages")) {
                                b = 0;
                            }
                            break;
                        case 110997:
                            if (next.equals("pin")) {
                                b = 3;
                            }
                            break;
                        case 197700369:
                            if (next.equals("identityContext")) {
                                b = 2;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    PinUsages pinUsages = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pinUsages = this.mPinUsagesParser.parse(jsonNode2);
                        }
                        builder.usages = pinUsages;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mmarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.identityContext = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str4 = this.mpinValueParser.parse(jsonNode2);
                        }
                        builder.pin = str4;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mlegacyCustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SetPinRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SetPinRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 4;
                                    }
                                    break;
                                case -836159758:
                                    if (currentName.equals("usages")) {
                                        b = 0;
                                    }
                                    break;
                                case 110997:
                                    if (currentName.equals("pin")) {
                                        b = 3;
                                    }
                                    break;
                                case 197700369:
                                    if (currentName.equals("identityContext")) {
                                        b = 2;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            PinUsages pinUsages = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinUsages = this.mPinUsagesParser.parse(jsonParser);
                                }
                                builder.usages = pinUsages;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mmarketplaceIdParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.midentityContextParser.parse(jsonParser);
                                }
                                builder.identityContext = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str4 = this.mpinValueParser.parse(jsonParser);
                                }
                                builder.pin = str4;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlegacyCustomerIdParser.parse(jsonParser);
                                }
                                builder.customerId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SetPinRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    return builder.build();
                }
            }
        }
    }
}
