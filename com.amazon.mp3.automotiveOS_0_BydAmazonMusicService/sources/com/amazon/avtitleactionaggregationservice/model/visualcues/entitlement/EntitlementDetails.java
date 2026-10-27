package com.amazon.avtitleactionaggregationservice.model.visualcues.entitlement;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class EntitlementDetails {
    public final Optional<String> benefitName;
    public final Optional<String> entitlementSourceId;
    public final Optional<EntitlementType> entitlementType;
    public final Optional<Boolean> isLinear;
    public final Optional<Integer> rank;

    private EntitlementDetails(Builder builder) {
        this.entitlementType = Optional.fromNullable(builder.entitlementType);
        this.entitlementSourceId = Optional.fromNullable(builder.entitlementSourceId);
        this.isLinear = Optional.fromNullable(builder.isLinear);
        this.rank = Optional.fromNullable(builder.rank);
        this.benefitName = Optional.fromNullable(builder.benefitName);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntitlementDetails)) {
            return false;
        }
        EntitlementDetails entitlementDetails = (EntitlementDetails) obj;
        return Objects.equal(this.entitlementType, entitlementDetails.entitlementType) && Objects.equal(this.entitlementSourceId, entitlementDetails.entitlementSourceId) && Objects.equal(this.isLinear, entitlementDetails.isLinear) && Objects.equal(this.rank, entitlementDetails.rank) && Objects.equal(this.benefitName, entitlementDetails.benefitName);
    }

    public int hashCode() {
        return Objects.hashCode(this.entitlementType, this.entitlementSourceId, this.isLinear, this.rank, this.benefitName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("entitlementType", this.entitlementType).add("entitlementSourceId", this.entitlementSourceId).add("isLinear", this.isLinear).add("rank", this.rank).add("benefitName", this.benefitName).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitName;
        public String entitlementSourceId;
        public EntitlementType entitlementType;
        public Boolean isLinear;
        public Integer rank;

        public EntitlementDetails build() {
            return new EntitlementDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<EntitlementDetails> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final EnumParser<EntitlementType> mEntitlementTypeParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mEntitlementTypeParser = EnumParser.newParser(EntitlementType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public EntitlementDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EntitlementDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public EntitlementDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EntitlementDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private EntitlementDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "EntitlementDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1649733113:
                            if (next.equals("entitlementType")) {
                                b = 0;
                            }
                            break;
                        case -550207550:
                            if (next.equals("benefitName")) {
                                b = 4;
                            }
                            break;
                        case -428637969:
                            if (next.equals("isLinear")) {
                                b = 2;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 3;
                            }
                            break;
                        case 1589848803:
                            if (next.equals("entitlementSourceId")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    EntitlementType entitlementType = null;
                    String str2 = null;
                    Boolean bool = null;
                    Integer num = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonNode2);
                        }
                        builder.entitlementType = entitlementType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.entitlementSourceId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isLinear = bool;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.rank = num;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitName = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing EntitlementDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private EntitlementDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1649733113:
                                    if (currentName.equals("entitlementType")) {
                                        b = 0;
                                    }
                                    break;
                                case -550207550:
                                    if (currentName.equals("benefitName")) {
                                        b = 4;
                                    }
                                    break;
                                case -428637969:
                                    if (currentName.equals("isLinear")) {
                                        b = 2;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 3;
                                    }
                                    break;
                                case 1589848803:
                                    if (currentName.equals("entitlementSourceId")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            EntitlementType entitlementType = null;
                            String str2 = null;
                            Boolean bool = null;
                            Integer num = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonParser);
                                }
                                builder.entitlementType = entitlementType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.entitlementSourceId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.isLinear = bool;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.rank = num;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.benefitName = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing EntitlementDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
