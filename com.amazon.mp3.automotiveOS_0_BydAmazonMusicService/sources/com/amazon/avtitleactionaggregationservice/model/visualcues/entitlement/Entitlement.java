package com.amazon.avtitleactionaggregationservice.model.visualcues.entitlement;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.visualcues.entitlement.EntitlementDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Entitlement {
    public final Optional<ImmutableList<EntitlementDetails>> entitlementDetailsList;
    public final Optional<Boolean> isEntitled;

    private Entitlement(Builder builder) {
        this.isEntitled = Optional.fromNullable(builder.isEntitled);
        this.entitlementDetailsList = Optional.fromNullable(builder.entitlementDetailsList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entitlement)) {
            return false;
        }
        Entitlement entitlement = (Entitlement) obj;
        return Objects.equal(this.isEntitled, entitlement.isEntitled) && Objects.equal(this.entitlementDetailsList, entitlement.entitlementDetailsList);
    }

    public int hashCode() {
        return Objects.hashCode(this.isEntitled, this.entitlementDetailsList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isEntitled", this.isEntitled).add("entitlementDetailsList", this.entitlementDetailsList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<EntitlementDetails> entitlementDetailsList;
        public Boolean isEntitled;

        public Entitlement build() {
            return new Entitlement(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Entitlement> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final ListParser<EntitlementDetails> mEntitlementDetailsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEntitlementDetailsListParser = ListParser.newParser(new EntitlementDetails.Parser(objectMapper));
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Entitlement parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Entitlement:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Entitlement parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Entitlement:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Entitlement parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Entitlement");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 654070419) {
                        if (iHashCode == 1399585343 && next.equals("isEntitled")) {
                            b = 0;
                        }
                    } else if (next.equals("entitlementDetailsList")) {
                        b = 1;
                    }
                    ImmutableList<EntitlementDetails> immutableList = null;
                    Boolean bool = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isEntitled = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mEntitlementDetailsListParser.parse(jsonNode2);
                        }
                        builder.entitlementDetailsList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Entitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Entitlement parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 654070419) {
                                if (iHashCode == 1399585343 && currentName.equals("isEntitled")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("entitlementDetailsList")) {
                                b = 1;
                            }
                            ImmutableList<EntitlementDetails> immutableList = null;
                            Boolean bool = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.isEntitled = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mEntitlementDetailsListParser.parse(jsonParser);
                                }
                                builder.entitlementDetailsList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Entitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
