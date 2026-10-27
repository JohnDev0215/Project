package com.amazon.avtitleactionaggregationservice.model.consumptions;

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
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RentalPolicyMetadata {
    public final Optional<Long> durationOfPermittedWindowFromEntitlementGrant;
    public final Optional<Long> durationOfPermittedWindowFromPlayback;
    public final Optional<Date> entitlementGrantTime;
    public final Optional<Boolean> isRentalClockStarted;
    public final Optional<Date> playbackStartTime;
    public final Optional<Date> rentalExpiryTime;

    private RentalPolicyMetadata(Builder builder) {
        this.durationOfPermittedWindowFromPlayback = Optional.fromNullable(builder.durationOfPermittedWindowFromPlayback);
        this.entitlementGrantTime = Optional.fromNullable(builder.entitlementGrantTime);
        this.playbackStartTime = Optional.fromNullable(builder.playbackStartTime);
        this.durationOfPermittedWindowFromEntitlementGrant = Optional.fromNullable(builder.durationOfPermittedWindowFromEntitlementGrant);
        this.rentalExpiryTime = Optional.fromNullable(builder.rentalExpiryTime);
        this.isRentalClockStarted = Optional.fromNullable(builder.isRentalClockStarted);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RentalPolicyMetadata)) {
            return false;
        }
        RentalPolicyMetadata rentalPolicyMetadata = (RentalPolicyMetadata) obj;
        return Objects.equal(this.durationOfPermittedWindowFromPlayback, rentalPolicyMetadata.durationOfPermittedWindowFromPlayback) && Objects.equal(this.entitlementGrantTime, rentalPolicyMetadata.entitlementGrantTime) && Objects.equal(this.playbackStartTime, rentalPolicyMetadata.playbackStartTime) && Objects.equal(this.durationOfPermittedWindowFromEntitlementGrant, rentalPolicyMetadata.durationOfPermittedWindowFromEntitlementGrant) && Objects.equal(this.rentalExpiryTime, rentalPolicyMetadata.rentalExpiryTime) && Objects.equal(this.isRentalClockStarted, rentalPolicyMetadata.isRentalClockStarted);
    }

    public int hashCode() {
        return Objects.hashCode(this.durationOfPermittedWindowFromPlayback, this.entitlementGrantTime, this.playbackStartTime, this.durationOfPermittedWindowFromEntitlementGrant, this.rentalExpiryTime, this.isRentalClockStarted);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("durationOfPermittedWindowFromPlayback", this.durationOfPermittedWindowFromPlayback).add("entitlementGrantTime", this.entitlementGrantTime).add("playbackStartTime", this.playbackStartTime).add("durationOfPermittedWindowFromEntitlementGrant", this.durationOfPermittedWindowFromEntitlementGrant).add("rentalExpiryTime", this.rentalExpiryTime).add("isRentalClockStarted", this.isRentalClockStarted).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long durationOfPermittedWindowFromEntitlementGrant;
        public Long durationOfPermittedWindowFromPlayback;
        public Date entitlementGrantTime;
        public Boolean isRentalClockStarted;
        public Date playbackStartTime;
        public Date rentalExpiryTime;

        public RentalPolicyMetadata build() {
            return new RentalPolicyMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RentalPolicyMetadata> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final SimpleParsers.DateParser mDateTimeParser;
        private final SimpleParsers.LongParser mTimeMillisecondsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mTimeMillisecondsParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RentalPolicyMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalPolicyMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RentalPolicyMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalPolicyMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RentalPolicyMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RentalPolicyMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1775710372:
                            if (next.equals("entitlementGrantTime")) {
                                b = 1;
                            }
                            break;
                        case -193159518:
                            if (next.equals("durationOfPermittedWindowFromPlayback")) {
                                b = 0;
                            }
                            break;
                        case 136785636:
                            if (next.equals("rentalExpiryTime")) {
                                b = 4;
                            }
                            break;
                        case 569737844:
                            if (next.equals("playbackStartTime")) {
                                b = 2;
                            }
                            break;
                        case 675670390:
                            if (next.equals("durationOfPermittedWindowFromEntitlementGrant")) {
                                b = 3;
                            }
                            break;
                        case 2057158305:
                            if (next.equals("isRentalClockStarted")) {
                                b = 5;
                            }
                            break;
                    }
                    Boolean bool = null;
                    Long l = null;
                    Date date = null;
                    Date date2 = null;
                    Long l2 = null;
                    Date date3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mTimeMillisecondsParser.parse(jsonNode2);
                        }
                        builder.durationOfPermittedWindowFromPlayback = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.entitlementGrantTime = date;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.playbackStartTime = date2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mTimeMillisecondsParser.parse(jsonNode2);
                        }
                        builder.durationOfPermittedWindowFromEntitlementGrant = l2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            date3 = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.rentalExpiryTime = date3;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.isRentalClockStarted = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RentalPolicyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RentalPolicyMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1775710372:
                                    if (currentName.equals("entitlementGrantTime")) {
                                        b = 1;
                                    }
                                    break;
                                case -193159518:
                                    if (currentName.equals("durationOfPermittedWindowFromPlayback")) {
                                        b = 0;
                                    }
                                    break;
                                case 136785636:
                                    if (currentName.equals("rentalExpiryTime")) {
                                        b = 4;
                                    }
                                    break;
                                case 569737844:
                                    if (currentName.equals("playbackStartTime")) {
                                        b = 2;
                                    }
                                    break;
                                case 675670390:
                                    if (currentName.equals("durationOfPermittedWindowFromEntitlementGrant")) {
                                        b = 3;
                                    }
                                    break;
                                case 2057158305:
                                    if (currentName.equals("isRentalClockStarted")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            Long l = null;
                            Date date = null;
                            Date date2 = null;
                            Long l2 = null;
                            Date date3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mTimeMillisecondsParser.parse(jsonParser);
                                }
                                builder.durationOfPermittedWindowFromPlayback = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.entitlementGrantTime = date;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.playbackStartTime = date2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mTimeMillisecondsParser.parse(jsonParser);
                                }
                                builder.durationOfPermittedWindowFromEntitlementGrant = l2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date3 = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.rentalExpiryTime = date3;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.isRentalClockStarted = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RentalPolicyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
