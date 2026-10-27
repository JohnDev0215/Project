package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.types.EntitlementType;
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
public class SelectedEntitlement {
    public final Optional<String> consumptionExpiration;
    public final Optional<String> customerIdentityContext;
    public final Optional<EntitlementType> entitlementType;
    public final Optional<String> grantedByCustomerId;
    public final Optional<Boolean> isRentalClockStarted;
    public final Optional<Integer> rentalTermHoursToPlayback;
    public final Optional<PRSException> rightsException;

    private SelectedEntitlement(Builder builder) {
        this.isRentalClockStarted = Optional.fromNullable(builder.isRentalClockStarted);
        this.consumptionExpiration = Optional.fromNullable(builder.consumptionExpiration);
        this.rightsException = Optional.fromNullable(builder.rightsException);
        this.grantedByCustomerId = Optional.fromNullable(builder.grantedByCustomerId);
        this.rentalTermHoursToPlayback = Optional.fromNullable(builder.rentalTermHoursToPlayback);
        this.entitlementType = Optional.fromNullable(builder.entitlementType);
        this.customerIdentityContext = Optional.fromNullable(builder.customerIdentityContext);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SelectedEntitlement)) {
            return false;
        }
        SelectedEntitlement selectedEntitlement = (SelectedEntitlement) obj;
        return Objects.equal(this.isRentalClockStarted, selectedEntitlement.isRentalClockStarted) && Objects.equal(this.consumptionExpiration, selectedEntitlement.consumptionExpiration) && Objects.equal(this.rightsException, selectedEntitlement.rightsException) && Objects.equal(this.grantedByCustomerId, selectedEntitlement.grantedByCustomerId) && Objects.equal(this.rentalTermHoursToPlayback, selectedEntitlement.rentalTermHoursToPlayback) && Objects.equal(this.entitlementType, selectedEntitlement.entitlementType) && Objects.equal(this.customerIdentityContext, selectedEntitlement.customerIdentityContext);
    }

    public int hashCode() {
        return Objects.hashCode(this.isRentalClockStarted, this.consumptionExpiration, this.rightsException, this.grantedByCustomerId, this.rentalTermHoursToPlayback, this.entitlementType, this.customerIdentityContext);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isRentalClockStarted", this.isRentalClockStarted).add("consumptionExpiration", this.consumptionExpiration).add("rightsException", this.rightsException).add("grantedByCustomerId", this.grantedByCustomerId).add("rentalTermHoursToPlayback", this.rentalTermHoursToPlayback).add("entitlementType", this.entitlementType).add("customerIdentityContext", this.customerIdentityContext).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String consumptionExpiration;
        public String customerIdentityContext;
        public EntitlementType entitlementType;
        public String grantedByCustomerId;
        public Boolean isRentalClockStarted;
        public Integer rentalTermHoursToPlayback;
        public PRSException rightsException;

        public SelectedEntitlement build() {
            return new SelectedEntitlement(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SelectedEntitlement> {
        private final PRSException.Parser mPRSExceptionParser;
        private final SimpleParsers.BooleanParser mbooleanTargetParser;
        private final SimpleParsers.StringParser mencryptedCustomerIdParser;
        private final EnumParser<EntitlementType> mentitlementTypeParser;
        private final SimpleParsers.StringParser midentityContextParser;
        private final SimpleParsers.IntegerParser mintegerTargetParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mbooleanTargetParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mintegerTargetParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mentitlementTypeParser = EnumParser.newParser(EntitlementType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mstringTargetParser = stringParser;
            this.mencryptedCustomerIdParser = stringParser;
            this.midentityContextParser = stringParser;
            this.mPRSExceptionParser = new PRSException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SelectedEntitlement parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SelectedEntitlement:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SelectedEntitlement parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SelectedEntitlement:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SelectedEntitlement parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Boolean bool;
            String str;
            PRSException pRSException;
            String str2;
            Integer num;
            EntitlementType entitlementType;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SelectedEntitlement");
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
                                b = 5;
                            }
                            break;
                        case -1079350445:
                            if (next.equals("customerIdentityContext")) {
                                b = 6;
                            }
                            break;
                        case -1024421832:
                            if (next.equals("rightsException")) {
                                b = 2;
                            }
                            break;
                        case -180513622:
                            if (next.equals("consumptionExpiration")) {
                                b = 1;
                            }
                            break;
                        case 179689557:
                            if (next.equals("rentalTermHoursToPlayback")) {
                                b = 4;
                            }
                            break;
                        case 958512043:
                            if (next.equals("grantedByCustomerId")) {
                                b = 3;
                            }
                            break;
                        case 2057158305:
                            if (next.equals("isRentalClockStarted")) {
                                b = 0;
                            }
                            break;
                    }
                    bool = null;
                    str3 = null;
                    entitlementType = null;
                    num = null;
                    str2 = null;
                    pRSException = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SelectedEntitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            bool = this.mbooleanTargetParser.parse(jsonNode2);
                        }
                        builder.isRentalClockStarted = bool;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.consumptionExpiration = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            pRSException = this.mPRSExceptionParser.parse(jsonNode2);
                        }
                        builder.rightsException = pRSException;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mencryptedCustomerIdParser.parse(jsonNode2);
                        }
                        builder.grantedByCustomerId = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            num = this.mintegerTargetParser.parse(jsonNode2);
                        }
                        builder.rentalTermHoursToPlayback = num;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            entitlementType = (EntitlementType) this.mentitlementTypeParser.parse(jsonNode2);
                        }
                        builder.entitlementType = entitlementType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str3 = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.customerIdentityContext = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SelectedEntitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SelectedEntitlement parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 5;
                                    }
                                    break;
                                case -1079350445:
                                    if (currentName.equals("customerIdentityContext")) {
                                        b = 6;
                                    }
                                    break;
                                case -1024421832:
                                    if (currentName.equals("rightsException")) {
                                        b = 2;
                                    }
                                    break;
                                case -180513622:
                                    if (currentName.equals("consumptionExpiration")) {
                                        b = 1;
                                    }
                                    break;
                                case 179689557:
                                    if (currentName.equals("rentalTermHoursToPlayback")) {
                                        b = 4;
                                    }
                                    break;
                                case 958512043:
                                    if (currentName.equals("grantedByCustomerId")) {
                                        b = 3;
                                    }
                                    break;
                                case 2057158305:
                                    if (currentName.equals("isRentalClockStarted")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            String str = null;
                            EntitlementType entitlementType = null;
                            Integer num = null;
                            String str2 = null;
                            PRSException pRSException = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mbooleanTargetParser.parse(jsonParser);
                                    }
                                    builder.isRentalClockStarted = bool;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.consumptionExpiration = str3;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        pRSException = this.mPRSExceptionParser.parse(jsonParser);
                                    }
                                    builder.rightsException = pRSException;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mencryptedCustomerIdParser.parse(jsonParser);
                                    }
                                    builder.grantedByCustomerId = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mintegerTargetParser.parse(jsonParser);
                                    }
                                    builder.rentalTermHoursToPlayback = num;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        entitlementType = (EntitlementType) this.mentitlementTypeParser.parse(jsonParser);
                                    }
                                    builder.entitlementType = entitlementType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.midentityContextParser.parse(jsonParser);
                                    }
                                    builder.customerIdentityContext = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SelectedEntitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SelectedEntitlement so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
