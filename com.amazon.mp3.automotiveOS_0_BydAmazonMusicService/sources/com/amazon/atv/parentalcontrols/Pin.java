package com.amazon.atv.parentalcontrols;

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
public class Pin {
    public final Optional<PinUsages> usages;
    public final Optional<String> value;

    private Pin(Builder builder) {
        this.usages = Optional.fromNullable(builder.usages);
        this.value = Optional.fromNullable(builder.value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pin)) {
            return false;
        }
        Pin pin = (Pin) obj;
        return Objects.equal(this.usages, pin.usages) && Objects.equal(this.value, pin.value);
    }

    public int hashCode() {
        return Objects.hashCode(this.usages, this.value);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("usages", this.usages).add("value", this.value).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinUsages usages;
        public String value;

        public Pin build() {
            return new Pin(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Pin> {
        private final PinUsages.Parser mPinUsagesParser;
        private final SimpleParsers.StringParser mpinValueParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinUsagesParser = new PinUsages.Parser(objectMapper);
            this.mpinValueParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Pin parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Pin:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Pin parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Pin:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Pin parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Pin");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -836159758) {
                        if (iHashCode == 111972721 && next.equals("value")) {
                            b = 1;
                        }
                    } else if (next.equals("usages")) {
                        b = 0;
                    }
                    String str = null;
                    PinUsages pinUsages = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pinUsages = this.mPinUsagesParser.parse(jsonNode2);
                        }
                        builder.usages = pinUsages;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mpinValueParser.parse(jsonNode2);
                        }
                        builder.value = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Pin so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Pin parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode == 111972721 && currentName.equals("value")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("usages")) {
                                b = 0;
                            }
                            String str = null;
                            PinUsages pinUsages = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinUsages = this.mPinUsagesParser.parse(jsonParser);
                                }
                                builder.usages = pinUsages;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mpinValueParser.parse(jsonParser);
                                }
                                builder.value = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Pin so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
