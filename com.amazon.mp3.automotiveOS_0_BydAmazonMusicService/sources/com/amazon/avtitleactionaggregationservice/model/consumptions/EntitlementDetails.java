package com.amazon.avtitleactionaggregationservice.model.consumptions;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.consumptions.RentalPolicyMetadata;
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
    public final Optional<RentalPolicyMetadata> rentalPolicy;

    private EntitlementDetails(Builder builder) {
        this.rentalPolicy = Optional.fromNullable(builder.rentalPolicy);
        this.entitlementType = Optional.fromNullable(builder.entitlementType);
        this.benefitName = Optional.fromNullable(builder.benefitName);
        this.entitlementSourceId = Optional.fromNullable(builder.entitlementSourceId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntitlementDetails)) {
            return false;
        }
        EntitlementDetails entitlementDetails = (EntitlementDetails) obj;
        return Objects.equal(this.rentalPolicy, entitlementDetails.rentalPolicy) && Objects.equal(this.entitlementType, entitlementDetails.entitlementType) && Objects.equal(this.benefitName, entitlementDetails.benefitName) && Objects.equal(this.entitlementSourceId, entitlementDetails.entitlementSourceId);
    }

    public int hashCode() {
        return Objects.hashCode(this.rentalPolicy, this.entitlementType, this.benefitName, this.entitlementSourceId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rentalPolicy", this.rentalPolicy).add("entitlementType", this.entitlementType).add("benefitName", this.benefitName).add("entitlementSourceId", this.entitlementSourceId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitName;
        public String entitlementSourceId;
        public EntitlementType entitlementType;
        public RentalPolicyMetadata rentalPolicy;

        public EntitlementDetails build() {
            return new EntitlementDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<EntitlementDetails> {
        private final EnumParser<EntitlementType> mEntitlementTypeParser;
        private final RentalPolicyMetadata.Parser mRentalPolicyMetadataParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRentalPolicyMetadataParser = new RentalPolicyMetadata.Parser(objectMapper);
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
                        case -1909677194:
                            if (next.equals("rentalPolicy")) {
                                b = 0;
                            }
                            break;
                        case -1649733113:
                            if (next.equals("entitlementType")) {
                                b = 1;
                            }
                            break;
                        case -550207550:
                            if (next.equals("benefitName")) {
                                b = 2;
                            }
                            break;
                        case 1589848803:
                            if (next.equals("entitlementSourceId")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    RentalPolicyMetadata rentalPolicyMetadata = null;
                    EntitlementType entitlementType = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            rentalPolicyMetadata = this.mRentalPolicyMetadataParser.parse(jsonNode2);
                        }
                        builder.rentalPolicy = rentalPolicyMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonNode2);
                        }
                        builder.entitlementType = entitlementType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.benefitName = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.entitlementSourceId = str;
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
                                case -1909677194:
                                    if (currentName.equals("rentalPolicy")) {
                                        b = 0;
                                    }
                                    break;
                                case -1649733113:
                                    if (currentName.equals("entitlementType")) {
                                        b = 1;
                                    }
                                    break;
                                case -550207550:
                                    if (currentName.equals("benefitName")) {
                                        b = 2;
                                    }
                                    break;
                                case 1589848803:
                                    if (currentName.equals("entitlementSourceId")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            RentalPolicyMetadata rentalPolicyMetadata = null;
                            EntitlementType entitlementType = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rentalPolicyMetadata = this.mRentalPolicyMetadataParser.parse(jsonParser);
                                }
                                builder.rentalPolicy = rentalPolicyMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonParser);
                                }
                                builder.entitlementType = entitlementType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.benefitName = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.entitlementSourceId = str;
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
