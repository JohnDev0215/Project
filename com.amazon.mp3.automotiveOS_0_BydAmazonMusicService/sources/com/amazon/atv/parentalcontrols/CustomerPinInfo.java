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
public class CustomerPinInfo {
    public final Optional<Integer> length;
    public final boolean pinSetup;
    public final Optional<PinUsages> usages;
    public final boolean usagesDefined;

    private CustomerPinInfo(Builder builder) {
        this.pinSetup = builder.pinSetup;
        this.usagesDefined = builder.usagesDefined;
        this.usages = Optional.fromNullable(builder.usages);
        this.length = Optional.fromNullable(builder.length);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomerPinInfo)) {
            return false;
        }
        CustomerPinInfo customerPinInfo = (CustomerPinInfo) obj;
        return Objects.equal(Boolean.valueOf(this.pinSetup), Boolean.valueOf(customerPinInfo.pinSetup)) && Objects.equal(Boolean.valueOf(this.usagesDefined), Boolean.valueOf(customerPinInfo.usagesDefined)) && Objects.equal(this.usages, customerPinInfo.usages) && Objects.equal(this.length, customerPinInfo.length);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.pinSetup), Boolean.valueOf(this.usagesDefined), this.usages, this.length);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("pinSetup", this.pinSetup).add("usagesDefined", this.usagesDefined).add("usages", this.usages).add("length", this.length).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Integer length;
        public boolean pinSetup;
        public PinUsages usages;
        public boolean usagesDefined;

        public CustomerPinInfo build() {
            return new CustomerPinInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CustomerPinInfo> {
        private final PinUsages.Parser mPinUsagesParser;
        private final SimpleParsers.IntegerParser mnullableIntegerParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mnullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mPinUsagesParser = new PinUsages.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CustomerPinInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CustomerPinInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CustomerPinInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CustomerPinInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CustomerPinInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CustomerPinInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1106363674:
                            if (next.equals("length")) {
                                b = 3;
                            }
                            break;
                        case -836159758:
                            if (next.equals("usages")) {
                                b = 2;
                            }
                            break;
                        case -446149144:
                            if (next.equals("pinSetup")) {
                                b = 0;
                            }
                            break;
                        case 324692663:
                            if (next.equals("usagesDefined")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b == 0) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field pinSetup can't be null");
                        }
                        builder.pinSetup = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b != 1) {
                        Integer num = null;
                        PinUsages pinUsages = null;
                        if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                pinUsages = this.mPinUsagesParser.parse(jsonNode2);
                            }
                            builder.usages = pinUsages;
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                num = this.mnullableIntegerParser.parse(jsonNode2);
                            }
                            builder.length = num;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field usagesDefined can't be null");
                        }
                        builder.usagesDefined = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CustomerPinInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.pinSetup), this, "pinSetup");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CustomerPinInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1106363674:
                                    if (currentName.equals("length")) {
                                        b = 3;
                                    }
                                    break;
                                case -836159758:
                                    if (currentName.equals("usages")) {
                                        b = 2;
                                    }
                                    break;
                                case -446149144:
                                    if (currentName.equals("pinSetup")) {
                                        b = 0;
                                    }
                                    break;
                                case 324692663:
                                    if (currentName.equals("usagesDefined")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            if (b == 0) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field pinSetup can't be null");
                                }
                                builder.pinSetup = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b != 1) {
                                Integer num = null;
                                PinUsages pinUsages = null;
                                if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        pinUsages = this.mPinUsagesParser.parse(jsonParser);
                                    }
                                    builder.usages = pinUsages;
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mnullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.length = num;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field usagesDefined can't be null");
                                }
                                builder.usagesDefined = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CustomerPinInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.pinSetup), this, "pinSetup");
                    return builder.build();
                }
            }
        }
    }
}
