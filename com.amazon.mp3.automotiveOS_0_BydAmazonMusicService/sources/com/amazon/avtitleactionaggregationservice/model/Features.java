package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Features {
    public final Optional<Boolean> accessMessaging;
    public final Optional<Boolean> availabilityChanges;
    public final Optional<Boolean> consumptionDetails;
    public final Optional<Boolean> customerBookmark;
    public final Optional<Boolean> download;
    public final Optional<Boolean> entitlementDetails;
    public final Optional<Boolean> languageProperties;
    public final Optional<ImmutableMap<OfferCategory, ImmutableList<OfferFeature>>> offerFilter;
    public final Optional<Boolean> supportsLinearData;
    public final Optional<Boolean> supportsPlaybackEnvelope;

    private Features(Builder builder) {
        this.consumptionDetails = Optional.fromNullable(builder.consumptionDetails);
        this.entitlementDetails = Optional.fromNullable(builder.entitlementDetails);
        this.download = Optional.fromNullable(builder.download);
        this.supportsLinearData = Optional.fromNullable(builder.supportsLinearData);
        this.customerBookmark = Optional.fromNullable(builder.customerBookmark);
        this.availabilityChanges = Optional.fromNullable(builder.availabilityChanges);
        this.accessMessaging = Optional.fromNullable(builder.accessMessaging);
        this.offerFilter = Optional.fromNullable(builder.offerFilter);
        this.supportsPlaybackEnvelope = Optional.fromNullable(builder.supportsPlaybackEnvelope);
        this.languageProperties = Optional.fromNullable(builder.languageProperties);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Features)) {
            return false;
        }
        Features features = (Features) obj;
        return Objects.equal(this.consumptionDetails, features.consumptionDetails) && Objects.equal(this.entitlementDetails, features.entitlementDetails) && Objects.equal(this.download, features.download) && Objects.equal(this.supportsLinearData, features.supportsLinearData) && Objects.equal(this.customerBookmark, features.customerBookmark) && Objects.equal(this.availabilityChanges, features.availabilityChanges) && Objects.equal(this.accessMessaging, features.accessMessaging) && Objects.equal(this.offerFilter, features.offerFilter) && Objects.equal(this.supportsPlaybackEnvelope, features.supportsPlaybackEnvelope) && Objects.equal(this.languageProperties, features.languageProperties);
    }

    public int hashCode() {
        return Objects.hashCode(this.consumptionDetails, this.entitlementDetails, this.download, this.supportsLinearData, this.customerBookmark, this.availabilityChanges, this.accessMessaging, this.offerFilter, this.supportsPlaybackEnvelope, this.languageProperties);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("consumptionDetails", this.consumptionDetails).add("entitlementDetails", this.entitlementDetails).add("download", this.download).add("supportsLinearData", this.supportsLinearData).add("customerBookmark", this.customerBookmark).add("availabilityChanges", this.availabilityChanges).add("accessMessaging", this.accessMessaging).add("offerFilter", this.offerFilter).add("supportsPlaybackEnvelope", this.supportsPlaybackEnvelope).add("languageProperties", this.languageProperties).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean accessMessaging;
        public Boolean availabilityChanges;
        public Boolean consumptionDetails;
        public Boolean customerBookmark;
        public Boolean download;
        public Boolean entitlementDetails;
        public Boolean languageProperties;
        public ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> offerFilter;
        public Boolean supportsLinearData;
        public Boolean supportsPlaybackEnvelope;

        public Features build() {
            return new Features(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Features> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final MapParser<OfferCategory, ImmutableList<OfferFeature>> mOfferCategoryToOfferFeatureListMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mOfferCategoryToOfferFeatureListMapParser = MapParser.newParser(EnumParser.newParser(OfferCategory.class), ListParser.newParser(EnumParser.newParser(OfferFeature.class)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Features parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Features:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Features parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Features:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Features parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Boolean bool;
            Boolean bool2;
            Boolean bool3;
            Boolean bool4;
            Boolean bool5;
            Boolean bool6;
            Boolean bool7;
            ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> immutableMap;
            Boolean bool8;
            Boolean bool9;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Features");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1772461995:
                            if (next.equals("entitlementDetails")) {
                                b = 1;
                            }
                            break;
                        case -999398873:
                            if (next.equals("consumptionDetails")) {
                                b = 0;
                            }
                            break;
                        case -591120977:
                            if (next.equals("supportsPlaybackEnvelope")) {
                                b = 8;
                            }
                            break;
                        case -8676108:
                            if (next.equals("offerFilter")) {
                                b = 7;
                            }
                            break;
                        case 857463315:
                            if (next.equals("supportsLinearData")) {
                                b = 3;
                            }
                            break;
                        case 1110683307:
                            if (next.equals("languageProperties")) {
                                b = 9;
                            }
                            break;
                        case 1427818632:
                            if (next.equals("download")) {
                                b = 2;
                            }
                            break;
                        case 1810708712:
                            if (next.equals("availabilityChanges")) {
                                b = 5;
                            }
                            break;
                        case 1812034048:
                            if (next.equals("accessMessaging")) {
                                b = 6;
                            }
                            break;
                        case 1961675572:
                            if (next.equals("customerBookmark")) {
                                b = 4;
                            }
                            break;
                    }
                    bool = null;
                    bool9 = null;
                    bool8 = null;
                    immutableMap = null;
                    bool7 = null;
                    bool6 = null;
                    bool5 = null;
                    bool4 = null;
                    bool3 = null;
                    bool2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Features so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.consumptionDetails = bool;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.entitlementDetails = bool2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            bool3 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.download = bool3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            bool4 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.supportsLinearData = bool4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            bool5 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.customerBookmark = bool5;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            bool6 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.availabilityChanges = bool6;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            bool7 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.accessMessaging = bool7;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mOfferCategoryToOfferFeatureListMapParser.parse(jsonNode2);
                        }
                        builder.offerFilter = immutableMap;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            bool8 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.supportsPlaybackEnvelope = bool8;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            bool9 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.languageProperties = bool9;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Features so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Features parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1772461995:
                                    if (currentName.equals("entitlementDetails")) {
                                        b = 1;
                                    }
                                    break;
                                case -999398873:
                                    if (currentName.equals("consumptionDetails")) {
                                        b = 0;
                                    }
                                    break;
                                case -591120977:
                                    if (currentName.equals("supportsPlaybackEnvelope")) {
                                        b = 8;
                                    }
                                    break;
                                case -8676108:
                                    if (currentName.equals("offerFilter")) {
                                        b = 7;
                                    }
                                    break;
                                case 857463315:
                                    if (currentName.equals("supportsLinearData")) {
                                        b = 3;
                                    }
                                    break;
                                case 1110683307:
                                    if (currentName.equals("languageProperties")) {
                                        b = 9;
                                    }
                                    break;
                                case 1427818632:
                                    if (currentName.equals("download")) {
                                        b = 2;
                                    }
                                    break;
                                case 1810708712:
                                    if (currentName.equals("availabilityChanges")) {
                                        b = 5;
                                    }
                                    break;
                                case 1812034048:
                                    if (currentName.equals("accessMessaging")) {
                                        b = 6;
                                    }
                                    break;
                                case 1961675572:
                                    if (currentName.equals("customerBookmark")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            Boolean bool2 = null;
                            Boolean bool3 = null;
                            ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> immutableMap = null;
                            Boolean bool4 = null;
                            Boolean bool5 = null;
                            Boolean bool6 = null;
                            Boolean bool7 = null;
                            Boolean bool8 = null;
                            Boolean bool9 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.consumptionDetails = bool;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool9 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.entitlementDetails = bool9;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool8 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.download = bool8;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool7 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.supportsLinearData = bool7;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool6 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.customerBookmark = bool6;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool5 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.availabilityChanges = bool5;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool4 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.accessMessaging = bool4;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mOfferCategoryToOfferFeatureListMapParser.parse(jsonParser);
                                    }
                                    builder.offerFilter = immutableMap;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool3 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.supportsPlaybackEnvelope = bool3;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool2 = this.mBooleanParser.parse(jsonParser);
                                    }
                                    builder.languageProperties = bool2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Features so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Features so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
