package com.amazon.atv.playbackauthority.service.model;

import com.amazon.atv.playbackauthority.service.EntitlementType;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class EntitlementMetadata {
    public final Optional<String> benefitId;
    public final Optional<String> benefitTierId;
    public final Optional<String> contractId;
    public final Optional<String> entitlementReferenceId;
    public final EntitlementType entitlementType;
    public final String grantedByCustomerId;
    public final Optional<Boolean> isRentalClockStarted;
    public final Optional<String> orderId;
    public final Optional<String> playableVideoId;
    public final Optional<String> policyId;
    public final Optional<Integer> rentalTermHoursToPlayback;
    public final Optional<Integer> rentalTermHoursToStart;
    public final Optional<Date> windowEnd;
    public final Date windowStart;

    private EntitlementMetadata(Builder builder) {
        this.entitlementReferenceId = Optional.fromNullable(builder.entitlementReferenceId);
        this.isRentalClockStarted = Optional.fromNullable(builder.isRentalClockStarted);
        this.contractId = Optional.fromNullable(builder.contractId);
        this.windowStart = (Date) Preconditions.checkNotNull(builder.windowStart, "Unexpected null field: windowStart");
        this.policyId = Optional.fromNullable(builder.policyId);
        this.playableVideoId = Optional.fromNullable(builder.playableVideoId);
        this.orderId = Optional.fromNullable(builder.orderId);
        this.entitlementType = (EntitlementType) Preconditions.checkNotNull(builder.entitlementType, "Unexpected null field: entitlementType");
        this.windowEnd = Optional.fromNullable(builder.windowEnd);
        this.rentalTermHoursToStart = Optional.fromNullable(builder.rentalTermHoursToStart);
        this.benefitId = Optional.fromNullable(builder.benefitId);
        this.benefitTierId = Optional.fromNullable(builder.benefitTierId);
        this.grantedByCustomerId = (String) Preconditions.checkNotNull(builder.grantedByCustomerId, "Unexpected null field: grantedByCustomerId");
        this.rentalTermHoursToPlayback = Optional.fromNullable(builder.rentalTermHoursToPlayback);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntitlementMetadata)) {
            return false;
        }
        EntitlementMetadata entitlementMetadata = (EntitlementMetadata) obj;
        return Objects.equal(this.entitlementReferenceId, entitlementMetadata.entitlementReferenceId) && Objects.equal(this.isRentalClockStarted, entitlementMetadata.isRentalClockStarted) && Objects.equal(this.contractId, entitlementMetadata.contractId) && Objects.equal(this.windowStart, entitlementMetadata.windowStart) && Objects.equal(this.policyId, entitlementMetadata.policyId) && Objects.equal(this.playableVideoId, entitlementMetadata.playableVideoId) && Objects.equal(this.orderId, entitlementMetadata.orderId) && Objects.equal(this.entitlementType, entitlementMetadata.entitlementType) && Objects.equal(this.windowEnd, entitlementMetadata.windowEnd) && Objects.equal(this.rentalTermHoursToStart, entitlementMetadata.rentalTermHoursToStart) && Objects.equal(this.benefitId, entitlementMetadata.benefitId) && Objects.equal(this.benefitTierId, entitlementMetadata.benefitTierId) && Objects.equal(this.grantedByCustomerId, entitlementMetadata.grantedByCustomerId) && Objects.equal(this.rentalTermHoursToPlayback, entitlementMetadata.rentalTermHoursToPlayback);
    }

    public int hashCode() {
        return Objects.hashCode(this.entitlementReferenceId, this.isRentalClockStarted, this.contractId, this.windowStart, this.policyId, this.playableVideoId, this.orderId, this.entitlementType, this.windowEnd, this.rentalTermHoursToStart, this.benefitId, this.benefitTierId, this.grantedByCustomerId, this.rentalTermHoursToPlayback);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("entitlementReferenceId", this.entitlementReferenceId).add("isRentalClockStarted", this.isRentalClockStarted).add("contractId", this.contractId).add("windowStart", this.windowStart).add("policyId", this.policyId).add("playableVideoId", this.playableVideoId).add("orderId", this.orderId).add("entitlementType", this.entitlementType).add("windowEnd", this.windowEnd).add("rentalTermHoursToStart", this.rentalTermHoursToStart).add("benefitId", this.benefitId).add("benefitTierId", this.benefitTierId).add("grantedByCustomerId", this.grantedByCustomerId).add("rentalTermHoursToPlayback", this.rentalTermHoursToPlayback).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String benefitId;
        public String benefitTierId;
        public String contractId;
        public String entitlementReferenceId;
        public EntitlementType entitlementType;
        public String grantedByCustomerId;
        public Boolean isRentalClockStarted;
        public String orderId;
        public String playableVideoId;
        public String policyId;
        public Integer rentalTermHoursToPlayback;
        public Integer rentalTermHoursToStart;
        public Date windowEnd;
        public Date windowStart;

        public EntitlementMetadata build() {
            return new EntitlementMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<EntitlementMetadata> {
        private final EnumParser<EntitlementType> mEntitlementTypeParser;
        private final SimpleParsers.StringParser mbenefitIdStringParser;
        private final SimpleParsers.StringParser mbenefitTierIdStringParser;
        private final SimpleParsers.StringParser mcontractIdStringParser;
        private final SimpleParsers.StringParser mentitlementReferenceIdParser;
        private final SimpleParsers.StringParser mgrantedByCustomerIdStringParser;
        private final SimpleParsers.BooleanParser misRentalClockStartedBooleanParser;
        private final SimpleParsers.StringParser morderIdStringParser;
        private final SimpleParsers.StringParser mplayableVideoIdStringParser;
        private final SimpleParsers.StringParser mpolicyIdStringParser;
        private final SimpleParsers.IntegerParser mrentalTermHoursToPlaybackIntegerParser;
        private final SimpleParsers.IntegerParser mrentalTermHoursToStartIntegerParser;
        private final SimpleParsers.DateParser mwindowEndTimestampParser;
        private final SimpleParsers.DateParser mwindowStartTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEntitlementTypeParser = EnumParser.newParser(EntitlementType.class);
            SimpleParsers.DateParser dateParser = SimpleParsers.DateParser.INSTANCE;
            this.mwindowStartTimestampParser = dateParser;
            this.mwindowEndTimestampParser = dateParser;
            this.misRentalClockStartedBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            SimpleParsers.IntegerParser integerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mrentalTermHoursToStartIntegerParser = integerParser;
            this.mrentalTermHoursToPlaybackIntegerParser = integerParser;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mentitlementReferenceIdParser = stringParser;
            this.mcontractIdStringParser = stringParser;
            this.mpolicyIdStringParser = stringParser;
            this.mplayableVideoIdStringParser = stringParser;
            this.morderIdStringParser = stringParser;
            this.mbenefitIdStringParser = stringParser;
            this.mbenefitTierIdStringParser = stringParser;
            this.mgrantedByCustomerIdStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public EntitlementMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EntitlementMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public EntitlementMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EntitlementMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private EntitlementMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Boolean bool;
            String str2;
            Date date;
            String str3;
            String str4;
            String str5;
            EntitlementType entitlementType;
            Date date2;
            Integer num;
            String str6;
            String str7;
            String str8;
            Integer num2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "EntitlementMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1847836536:
                            if (next.equals("playableVideoId")) {
                                b = 5;
                            }
                            break;
                        case -1649733113:
                            if (next.equals("entitlementType")) {
                                b = 7;
                            }
                            break;
                        case -1624322471:
                            if (next.equals("entitlementReferenceId")) {
                                b = 0;
                            }
                            break;
                        case -1207110391:
                            if (next.equals("orderId")) {
                                b = 6;
                            }
                            break;
                        case -492929944:
                            if (next.equals("rentalTermHoursToStart")) {
                                b = 9;
                            }
                            break;
                        case -289538540:
                            if (next.equals("benefitTierId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -217026869:
                            if (next.equals("windowEnd")) {
                                b = 8;
                            }
                            break;
                        case 179689557:
                            if (next.equals("rentalTermHoursToPlayback")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 546908653:
                            if (next.equals("policyId")) {
                                b = 4;
                            }
                            break;
                        case 624238029:
                            if (next.equals("contractId")) {
                                b = 2;
                            }
                            break;
                        case 723448850:
                            if (next.equals("benefitId")) {
                                b = 10;
                            }
                            break;
                        case 958512043:
                            if (next.equals("grantedByCustomerId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1903685202:
                            if (next.equals("windowStart")) {
                                b = 3;
                            }
                            break;
                        case 2057158305:
                            if (next.equals("isRentalClockStarted")) {
                                b = 1;
                            }
                            break;
                    }
                    str = null;
                    num2 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    num = null;
                    date2 = null;
                    entitlementType = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    date = null;
                    str2 = null;
                    bool = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing EntitlementMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mentitlementReferenceIdParser.parse(jsonNode2);
                        }
                        builder.entitlementReferenceId = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            bool = this.misRentalClockStartedBooleanParser.parse(jsonNode2);
                        }
                        builder.isRentalClockStarted = bool;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mcontractIdStringParser.parse(jsonNode2);
                        }
                        builder.contractId = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            date = this.mwindowStartTimestampParser.parse(jsonNode2);
                        }
                        builder.windowStart = date;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mpolicyIdStringParser.parse(jsonNode2);
                        }
                        builder.policyId = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mplayableVideoIdStringParser.parse(jsonNode2);
                        }
                        builder.playableVideoId = str4;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str5 = this.morderIdStringParser.parse(jsonNode2);
                        }
                        builder.orderId = str5;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonNode2);
                        }
                        builder.entitlementType = entitlementType;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            date2 = this.mwindowEndTimestampParser.parse(jsonNode2);
                        }
                        builder.windowEnd = date2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            num = this.mrentalTermHoursToStartIntegerParser.parse(jsonNode2);
                        }
                        builder.rentalTermHoursToStart = num;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mbenefitIdStringParser.parse(jsonNode2);
                        }
                        builder.benefitId = str6;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mbenefitTierIdStringParser.parse(jsonNode2);
                        }
                        builder.benefitTierId = str7;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mgrantedByCustomerIdStringParser.parse(jsonNode2);
                        }
                        builder.grantedByCustomerId = str8;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            num2 = this.mrentalTermHoursToPlaybackIntegerParser.parse(jsonNode2);
                        }
                        builder.rentalTermHoursToPlayback = num2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing EntitlementMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.windowStart, this, "windowStart");
            JsonParsingUtils.checkNotNull(builder.entitlementType, this, "entitlementType");
            JsonParsingUtils.checkNotNull(builder.grantedByCustomerId, this, "grantedByCustomerId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private EntitlementMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1847836536:
                                    if (currentName.equals("playableVideoId")) {
                                        b = 5;
                                    }
                                    break;
                                case -1649733113:
                                    if (currentName.equals("entitlementType")) {
                                        b = 7;
                                    }
                                    break;
                                case -1624322471:
                                    if (currentName.equals("entitlementReferenceId")) {
                                        b = 0;
                                    }
                                    break;
                                case -1207110391:
                                    if (currentName.equals("orderId")) {
                                        b = 6;
                                    }
                                    break;
                                case -492929944:
                                    if (currentName.equals("rentalTermHoursToStart")) {
                                        b = 9;
                                    }
                                    break;
                                case -289538540:
                                    if (currentName.equals("benefitTierId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -217026869:
                                    if (currentName.equals("windowEnd")) {
                                        b = 8;
                                    }
                                    break;
                                case 179689557:
                                    if (currentName.equals("rentalTermHoursToPlayback")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 546908653:
                                    if (currentName.equals("policyId")) {
                                        b = 4;
                                    }
                                    break;
                                case 624238029:
                                    if (currentName.equals("contractId")) {
                                        b = 2;
                                    }
                                    break;
                                case 723448850:
                                    if (currentName.equals("benefitId")) {
                                        b = 10;
                                    }
                                    break;
                                case 958512043:
                                    if (currentName.equals("grantedByCustomerId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1903685202:
                                    if (currentName.equals("windowStart")) {
                                        b = 3;
                                    }
                                    break;
                                case 2057158305:
                                    if (currentName.equals("isRentalClockStarted")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            Integer num = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            Integer num2 = null;
                            Date date = null;
                            EntitlementType entitlementType = null;
                            String str5 = null;
                            String str6 = null;
                            String str7 = null;
                            Date date2 = null;
                            String str8 = null;
                            Boolean bool = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mentitlementReferenceIdParser.parse(jsonParser);
                                    }
                                    builder.entitlementReferenceId = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.misRentalClockStartedBooleanParser.parse(jsonParser);
                                    }
                                    builder.isRentalClockStarted = bool;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mcontractIdStringParser.parse(jsonParser);
                                    }
                                    builder.contractId = str8;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date2 = this.mwindowStartTimestampParser.parse(jsonParser);
                                    }
                                    builder.windowStart = date2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mpolicyIdStringParser.parse(jsonParser);
                                    }
                                    builder.policyId = str7;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mplayableVideoIdStringParser.parse(jsonParser);
                                    }
                                    builder.playableVideoId = str6;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.morderIdStringParser.parse(jsonParser);
                                    }
                                    builder.orderId = str5;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        entitlementType = (EntitlementType) this.mEntitlementTypeParser.parse(jsonParser);
                                    }
                                    builder.entitlementType = entitlementType;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        date = this.mwindowEndTimestampParser.parse(jsonParser);
                                    }
                                    builder.windowEnd = date;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num2 = this.mrentalTermHoursToStartIntegerParser.parse(jsonParser);
                                    }
                                    builder.rentalTermHoursToStart = num2;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mbenefitIdStringParser.parse(jsonParser);
                                    }
                                    builder.benefitId = str4;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mbenefitTierIdStringParser.parse(jsonParser);
                                    }
                                    builder.benefitTierId = str3;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mgrantedByCustomerIdStringParser.parse(jsonParser);
                                    }
                                    builder.grantedByCustomerId = str2;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mrentalTermHoursToPlaybackIntegerParser.parse(jsonParser);
                                    }
                                    builder.rentalTermHoursToPlayback = num;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing EntitlementMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing EntitlementMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.windowStart, this, "windowStart");
                    JsonParsingUtils.checkNotNull(builder.entitlementType, this, "entitlementType");
                    JsonParsingUtils.checkNotNull(builder.grantedByCustomerId, this, "grantedByCustomerId");
                    return builder.build();
                }
            }
        }
    }
}
