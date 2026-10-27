package com.amazon.atv.parentalcontrols;

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
public class IntlCustomerPinInfo {
    public final Optional<Integer> length;
    public final boolean pinSetup;
    public final Optional<IntlPinUsages> usages;
    public final boolean usagesDefined;

    private IntlCustomerPinInfo(Builder builder) {
        this.pinSetup = builder.pinSetup;
        this.usages = Optional.fromNullable(builder.usages);
        this.length = Optional.fromNullable(builder.length);
        this.usagesDefined = builder.usagesDefined;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntlCustomerPinInfo)) {
            return false;
        }
        IntlCustomerPinInfo intlCustomerPinInfo = (IntlCustomerPinInfo) obj;
        return Objects.equal(Boolean.valueOf(this.pinSetup), Boolean.valueOf(intlCustomerPinInfo.pinSetup)) && Objects.equal(this.usages, intlCustomerPinInfo.usages) && Objects.equal(this.length, intlCustomerPinInfo.length) && Objects.equal(Boolean.valueOf(this.usagesDefined), Boolean.valueOf(intlCustomerPinInfo.usagesDefined));
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.pinSetup), this.usages, this.length, Boolean.valueOf(this.usagesDefined));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("pinSetup", this.pinSetup).add("usages", this.usages).add("length", this.length).add("usagesDefined", this.usagesDefined).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Integer length;
        public boolean pinSetup;
        public IntlPinUsages usages;
        public boolean usagesDefined;

        public IntlCustomerPinInfo build() {
            return new IntlCustomerPinInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<IntlCustomerPinInfo> {
        private final IntlPinUsages.Parser mIntlPinUsagesParser;
        private final SimpleParsers.IntegerParser mnullableIntegerParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntlPinUsagesParser = new IntlPinUsages.Parser(objectMapper);
            this.mnullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public IntlCustomerPinInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlCustomerPinInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public IntlCustomerPinInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlCustomerPinInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private IntlCustomerPinInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "IntlCustomerPinInfo");
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
                                b = 2;
                            }
                            break;
                        case -836159758:
                            if (next.equals("usages")) {
                                b = 1;
                            }
                            break;
                        case -446149144:
                            if (next.equals("pinSetup")) {
                                b = 0;
                            }
                            break;
                        case 324692663:
                            if (next.equals("usagesDefined")) {
                                b = 3;
                            }
                            break;
                    }
                    if (b != 0) {
                        Integer num = null;
                        IntlPinUsages intlPinUsages = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                intlPinUsages = this.mIntlPinUsagesParser.parse(jsonNode2);
                            }
                            builder.usages = intlPinUsages;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                num = this.mnullableIntegerParser.parse(jsonNode2);
                            }
                            builder.length = num;
                        } else if (b == 3) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field usagesDefined can't be null");
                            }
                            builder.usagesDefined = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field pinSetup can't be null");
                        }
                        builder.pinSetup = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing IntlCustomerPinInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.pinSetup), this, "pinSetup");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private IntlCustomerPinInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 2;
                                    }
                                    break;
                                case -836159758:
                                    if (currentName.equals("usages")) {
                                        b = 1;
                                    }
                                    break;
                                case -446149144:
                                    if (currentName.equals("pinSetup")) {
                                        b = 0;
                                    }
                                    break;
                                case 324692663:
                                    if (currentName.equals("usagesDefined")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                Integer num = null;
                                IntlPinUsages intlPinUsages = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        intlPinUsages = this.mIntlPinUsagesParser.parse(jsonParser);
                                    }
                                    builder.usages = intlPinUsages;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mnullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.length = num;
                                } else if (b == 3) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field usagesDefined can't be null");
                                    }
                                    builder.usagesDefined = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field pinSetup can't be null");
                                }
                                builder.pinSetup = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing IntlCustomerPinInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
