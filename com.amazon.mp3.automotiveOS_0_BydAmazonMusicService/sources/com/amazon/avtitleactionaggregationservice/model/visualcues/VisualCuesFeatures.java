package com.amazon.avtitleactionaggregationservice.model.visualcues;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.OfferCategory;
import com.amazon.avtitleactionaggregationservice.model.OfferFeature;
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
public class VisualCuesFeatures {
    public final Optional<Boolean> accessMessaging;
    public final Optional<Boolean> entitlementDetails;
    public final Optional<ImmutableMap<OfferCategory, ImmutableList<OfferFeature>>> offerFilter;
    public final Optional<Boolean> supportsLinearData;

    private VisualCuesFeatures(Builder builder) {
        this.supportsLinearData = Optional.fromNullable(builder.supportsLinearData);
        this.entitlementDetails = Optional.fromNullable(builder.entitlementDetails);
        this.offerFilter = Optional.fromNullable(builder.offerFilter);
        this.accessMessaging = Optional.fromNullable(builder.accessMessaging);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VisualCuesFeatures)) {
            return false;
        }
        VisualCuesFeatures visualCuesFeatures = (VisualCuesFeatures) obj;
        return Objects.equal(this.supportsLinearData, visualCuesFeatures.supportsLinearData) && Objects.equal(this.entitlementDetails, visualCuesFeatures.entitlementDetails) && Objects.equal(this.offerFilter, visualCuesFeatures.offerFilter) && Objects.equal(this.accessMessaging, visualCuesFeatures.accessMessaging);
    }

    public int hashCode() {
        return Objects.hashCode(this.supportsLinearData, this.entitlementDetails, this.offerFilter, this.accessMessaging);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("supportsLinearData", this.supportsLinearData).add("entitlementDetails", this.entitlementDetails).add("offerFilter", this.offerFilter).add("accessMessaging", this.accessMessaging).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean accessMessaging;
        public Boolean entitlementDetails;
        public ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> offerFilter;
        public Boolean supportsLinearData;

        public VisualCuesFeatures build() {
            return new VisualCuesFeatures(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<VisualCuesFeatures> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final MapParser<OfferCategory, ImmutableList<OfferFeature>> mOfferCategoryToOfferFeatureListMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mOfferCategoryToOfferFeatureListMapParser = MapParser.newParser(EnumParser.newParser(OfferCategory.class), ListParser.newParser(EnumParser.newParser(OfferFeature.class)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public VisualCuesFeatures parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("VisualCuesFeatures:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public VisualCuesFeatures parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("VisualCuesFeatures:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private VisualCuesFeatures parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "VisualCuesFeatures");
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
                        case -8676108:
                            if (next.equals("offerFilter")) {
                                b = 2;
                            }
                            break;
                        case 857463315:
                            if (next.equals("supportsLinearData")) {
                                b = 0;
                            }
                            break;
                        case 1812034048:
                            if (next.equals("accessMessaging")) {
                                b = 3;
                            }
                            break;
                    }
                    Boolean bool = null;
                    Boolean bool2 = null;
                    Boolean bool3 = null;
                    ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool2 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.supportsLinearData = bool2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool3 = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.entitlementDetails = bool3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mOfferCategoryToOfferFeatureListMapParser.parse(jsonNode2);
                        }
                        builder.offerFilter = immutableMap;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.accessMessaging = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing VisualCuesFeatures so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private VisualCuesFeatures parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -8676108:
                                    if (currentName.equals("offerFilter")) {
                                        b = 2;
                                    }
                                    break;
                                case 857463315:
                                    if (currentName.equals("supportsLinearData")) {
                                        b = 0;
                                    }
                                    break;
                                case 1812034048:
                                    if (currentName.equals("accessMessaging")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            Boolean bool2 = null;
                            Boolean bool3 = null;
                            ImmutableMap<OfferCategory, ImmutableList<OfferFeature>> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool2 = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.supportsLinearData = bool2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool3 = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.entitlementDetails = bool3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mOfferCategoryToOfferFeatureListMapParser.parse(jsonParser);
                                }
                                builder.offerFilter = immutableMap;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.accessMessaging = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing VisualCuesFeatures so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
