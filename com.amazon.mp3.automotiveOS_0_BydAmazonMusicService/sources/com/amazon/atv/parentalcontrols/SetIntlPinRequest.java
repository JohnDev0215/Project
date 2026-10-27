package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.InternationalCustomerRequest;
import com.amazon.atv.parentalcontrols.IntlPinUsages;
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
public class SetIntlPinRequest extends InternationalCustomerRequest {
    public final Optional<String> pin;
    public final Optional<IntlPinUsages> usages;

    private SetIntlPinRequest(Builder builder) {
        super(builder);
        this.usages = Optional.fromNullable(builder.usages);
        this.pin = Optional.fromNullable(builder.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetIntlPinRequest)) {
            return false;
        }
        SetIntlPinRequest setIntlPinRequest = (SetIntlPinRequest) obj;
        return super.equals(obj) && Objects.equal(this.usages, setIntlPinRequest.usages) && Objects.equal(this.pin, setIntlPinRequest.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.usages, this.pin);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("usages", this.usages).add("pin", this.pin).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerRequest.Builder {
        public String pin;
        public IntlPinUsages usages;

        public SetIntlPinRequest build() {
            return new SetIntlPinRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SetIntlPinRequest> {
        private final IntlPinUsages.Parser mIntlPinUsagesParser;
        private final SimpleParsers.StringParser mpinValueParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntlPinUsagesParser = new IntlPinUsages.Parser(objectMapper);
            this.mpinValueParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SetIntlPinRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetIntlPinRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SetIntlPinRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetIntlPinRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SetIntlPinRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SetIntlPinRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -836159758) {
                        if (iHashCode == 110997 && next.equals("pin")) {
                            b = 1;
                        }
                    } else if (next.equals("usages")) {
                        b = 0;
                    }
                    String str = null;
                    IntlPinUsages intlPinUsages = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            intlPinUsages = this.mIntlPinUsagesParser.parse(jsonNode2);
                        }
                        builder.usages = intlPinUsages;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mpinValueParser.parse(jsonNode2);
                        }
                        builder.pin = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SetIntlPinRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SetIntlPinRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -836159758) {
                                if (iHashCode == 110997 && currentName.equals("pin")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("usages")) {
                                b = 0;
                            }
                            String str = null;
                            IntlPinUsages intlPinUsages = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    intlPinUsages = this.mIntlPinUsagesParser.parse(jsonParser);
                                }
                                builder.usages = intlPinUsages;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mpinValueParser.parse(jsonParser);
                                }
                                builder.pin = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SetIntlPinRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
