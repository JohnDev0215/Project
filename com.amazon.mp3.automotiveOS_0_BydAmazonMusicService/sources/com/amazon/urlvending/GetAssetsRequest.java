package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.AdAsset;
import com.amazon.urlvending.Asset;
import com.amazon.urlvending.AssetProperties;
import com.amazon.urlvending.Customer;
import com.amazon.urlvending.Device;
import com.amazon.urlvending.PlaylistComponent;
import com.amazon.urlvending.types.AdStitchingSegmentInfoType;
import com.amazon.urlvending.types.AdStitchingType;
import com.amazon.urlvending.types.ConsumptionType;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class GetAssetsRequest {

    @Deprecated
    public final Optional<ImmutableList<AdAsset>> adAssets;
    public final Optional<ImmutableMap<String, String>> adParams;
    public final Optional<ImmutableList<AdStitchingSegmentInfoType>> adStitchingSegmentInfoTypes;
    public final Optional<AdStitchingType> adStitchingType;
    public final Optional<Asset> asset;
    public final Optional<AssetProperties> assetProperties;

    @Deprecated
    public final Optional<ImmutableMap<String, String>> cdnSessionIds;
    public final Optional<ConsumptionType> consumptionType;
    public final Optional<ImmutableMap<String, ImmutableList<PlaylistComponent>>> cuepoints;
    public final Optional<Customer> customer;
    public final boolean daiDesired;
    public final Optional<Device> device;

    @Deprecated
    public final boolean disableHoldbacks;
    public final Optional<String> marketplaceId;
    public final Optional<String> materialType;
    public final Optional<String> passthroughAdParams;
    public final Optional<String> tenantId;
    public final Optional<String> videoServiceProvider;
    public final boolean withDebInfo;

    private GetAssetsRequest(Builder builder) {
        this.assetProperties = Optional.fromNullable(builder.assetProperties);
        this.cdnSessionIds = Optional.fromNullable(builder.cdnSessionIds);
        this.withDebInfo = builder.withDebInfo;
        this.adAssets = Optional.fromNullable(builder.adAssets);
        this.consumptionType = Optional.fromNullable(builder.consumptionType);
        this.adStitchingType = Optional.fromNullable(builder.adStitchingType);
        this.videoServiceProvider = Optional.fromNullable(builder.videoServiceProvider);
        this.materialType = Optional.fromNullable(builder.materialType);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.daiDesired = builder.daiDesired;
        this.cuepoints = Optional.fromNullable(builder.cuepoints);
        this.customer = Optional.fromNullable(builder.customer);
        this.passthroughAdParams = Optional.fromNullable(builder.passthroughAdParams);
        this.adParams = Optional.fromNullable(builder.adParams);
        this.asset = Optional.fromNullable(builder.asset);
        this.device = Optional.fromNullable(builder.device);
        this.disableHoldbacks = builder.disableHoldbacks;
        this.adStitchingSegmentInfoTypes = Optional.fromNullable(builder.adStitchingSegmentInfoTypes);
        this.tenantId = Optional.fromNullable(builder.tenantId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAssetsRequest)) {
            return false;
        }
        GetAssetsRequest getAssetsRequest = (GetAssetsRequest) obj;
        return Objects.equal(this.assetProperties, getAssetsRequest.assetProperties) && Objects.equal(this.cdnSessionIds, getAssetsRequest.cdnSessionIds) && Objects.equal(Boolean.valueOf(this.withDebInfo), Boolean.valueOf(getAssetsRequest.withDebInfo)) && Objects.equal(this.adAssets, getAssetsRequest.adAssets) && Objects.equal(this.consumptionType, getAssetsRequest.consumptionType) && Objects.equal(this.adStitchingType, getAssetsRequest.adStitchingType) && Objects.equal(this.videoServiceProvider, getAssetsRequest.videoServiceProvider) && Objects.equal(this.materialType, getAssetsRequest.materialType) && Objects.equal(this.marketplaceId, getAssetsRequest.marketplaceId) && Objects.equal(Boolean.valueOf(this.daiDesired), Boolean.valueOf(getAssetsRequest.daiDesired)) && Objects.equal(this.cuepoints, getAssetsRequest.cuepoints) && Objects.equal(this.customer, getAssetsRequest.customer) && Objects.equal(this.passthroughAdParams, getAssetsRequest.passthroughAdParams) && Objects.equal(this.adParams, getAssetsRequest.adParams) && Objects.equal(this.asset, getAssetsRequest.asset) && Objects.equal(this.device, getAssetsRequest.device) && Objects.equal(Boolean.valueOf(this.disableHoldbacks), Boolean.valueOf(getAssetsRequest.disableHoldbacks)) && Objects.equal(this.adStitchingSegmentInfoTypes, getAssetsRequest.adStitchingSegmentInfoTypes) && Objects.equal(this.tenantId, getAssetsRequest.tenantId);
    }

    public int hashCode() {
        return Objects.hashCode(this.assetProperties, this.cdnSessionIds, Boolean.valueOf(this.withDebInfo), this.adAssets, this.consumptionType, this.adStitchingType, this.videoServiceProvider, this.materialType, this.marketplaceId, Boolean.valueOf(this.daiDesired), this.cuepoints, this.customer, this.passthroughAdParams, this.adParams, this.asset, this.device, Boolean.valueOf(this.disableHoldbacks), this.adStitchingSegmentInfoTypes, this.tenantId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("assetProperties", this.assetProperties).add("cdnSessionIds", this.cdnSessionIds).add("withDebInfo", this.withDebInfo).add("adAssets", this.adAssets).add("consumptionType", this.consumptionType).add("adStitchingType", this.adStitchingType).add("videoServiceProvider", this.videoServiceProvider).add("materialType", this.materialType).add("marketplaceId", this.marketplaceId).add("daiDesired", this.daiDesired).add("cuepoints", this.cuepoints).add("customer", this.customer).add("passthroughAdParams", this.passthroughAdParams).add("adParams", this.adParams).add("asset", this.asset).add("device", this.device).add("disableHoldbacks", this.disableHoldbacks).add("adStitchingSegmentInfoTypes", this.adStitchingSegmentInfoTypes).add("tenantId", this.tenantId).toString();
    }

    @NotThreadSafe
    public static class Builder {

        @Deprecated
        public ImmutableList<AdAsset> adAssets;
        public ImmutableMap<String, String> adParams;
        public ImmutableList<AdStitchingSegmentInfoType> adStitchingSegmentInfoTypes;
        public AdStitchingType adStitchingType;
        public Asset asset;
        public AssetProperties assetProperties;

        @Deprecated
        public ImmutableMap<String, String> cdnSessionIds;
        public ConsumptionType consumptionType;
        public ImmutableMap<String, ImmutableList<PlaylistComponent>> cuepoints;
        public Customer customer;
        public boolean daiDesired;
        public Device device;

        @Deprecated
        public boolean disableHoldbacks;
        public String marketplaceId;
        public String materialType;
        public String passthroughAdParams;
        public String tenantId;
        public String videoServiceProvider;
        public boolean withDebInfo;

        public GetAssetsRequest build() {
            return new GetAssetsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAssetsRequest> {
        private final ListParser<AdAsset> mAdAssetListParser;
        private final MapParser<String, String> mAdParamMapParser;
        private final ListParser<AdStitchingSegmentInfoType> mAdStitchingSegmentInfoTypesParser;
        private final EnumParser<AdStitchingType> mAdStitchingTypeParser;
        private final Asset.Parser mAssetParser;
        private final AssetProperties.Parser mAssetPropertiesParser;
        private final MapParser<String, String> mCdnSessionIdMapParser;
        private final EnumParser<ConsumptionType> mConsumptionTypeParser;
        private final MapParser<String, ImmutableList<PlaylistComponent>> mCuepointMapParser;
        private final Customer.Parser mCustomerParser;
        private final Device.Parser mDeviceParser;
        private final SimpleParsers.StringParser mMaterialTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAssetParser = new Asset.Parser(objectMapper);
            this.mConsumptionTypeParser = EnumParser.newParser(ConsumptionType.class);
            this.mAssetPropertiesParser = new AssetProperties.Parser(objectMapper);
            this.mAdStitchingTypeParser = EnumParser.newParser(AdStitchingType.class);
            this.mAdAssetListParser = ListParser.newParser(new AdAsset.Parser(objectMapper));
            this.mAdStitchingSegmentInfoTypesParser = ListParser.newParser(EnumParser.newParser(AdStitchingSegmentInfoType.class));
            this.mCustomerParser = new Customer.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mCdnSessionIdMapParser = MapParser.newParser(stringParser, stringParser);
            this.mCuepointMapParser = MapParser.newParser(stringParser, ListParser.newParser(new PlaylistComponent.Parser(objectMapper)));
            this.mAdParamMapParser = MapParser.newParser(stringParser, stringParser);
            this.mDeviceParser = new Device.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mMaterialTypeParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAssetsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAssetsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAssetsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAssetsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAssetsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2115601151:
                            if (next.equals("materialType")) {
                                b = 7;
                            }
                            break;
                        case -1980493655:
                            if (next.equals("withDebInfo")) {
                                b = 2;
                            }
                            break;
                        case -1806627959:
                            if (next.equals("adParams")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -1753187514:
                            if (next.equals("adStitchingSegmentInfoTypes")) {
                                b = 17;
                            }
                            break;
                        case -1335157162:
                            if (next.equals("device")) {
                                b = 15;
                            }
                            break;
                        case -1326414587:
                            if (next.equals("disableHoldbacks")) {
                                b = 16;
                            }
                            break;
                        case -1306693787:
                            if (next.equals("tenantId")) {
                                b = 18;
                            }
                            break;
                        case -658725624:
                            if (next.equals("adStitchingType")) {
                                b = 5;
                            }
                            break;
                        case -167715474:
                            if (next.equals("daiDesired")) {
                                b = 9;
                            }
                            break;
                        case 93121264:
                            if (next.equals("asset")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 305419343:
                            if (next.equals("cdnSessionIds")) {
                                b = 1;
                            }
                            break;
                        case 368095605:
                            if (next.equals("consumptionType")) {
                                b = 4;
                            }
                            break;
                        case 606175198:
                            if (next.equals("customer")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 915881277:
                            if (next.equals("passthroughAdParams")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 8;
                            }
                            break;
                        case 1556900299:
                            if (next.equals("videoServiceProvider")) {
                                b = 6;
                            }
                            break;
                        case 1744267331:
                            if (next.equals("assetProperties")) {
                                b = 0;
                            }
                            break;
                        case 1956785366:
                            if (next.equals("cuepoints")) {
                                b = 10;
                            }
                            break;
                        case 2075559302:
                            if (next.equals("adAssets")) {
                                b = 3;
                            }
                            break;
                    }
                    AssetProperties assetProperties = null;
                    String str = null;
                    ImmutableList<AdStitchingSegmentInfoType> immutableList = null;
                    Device device = null;
                    Asset asset = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str2 = null;
                    Customer customer = null;
                    ImmutableMap<String, ImmutableList<PlaylistComponent>> immutableMap2 = null;
                    String str3 = null;
                    String str4 = null;
                    String str5 = null;
                    AdStitchingType adStitchingType = null;
                    ConsumptionType consumptionType = null;
                    ImmutableList<AdAsset> immutableList2 = null;
                    ImmutableMap<String, String> immutableMap3 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                assetProperties = this.mAssetPropertiesParser.parse(jsonNode2);
                            }
                            builder.assetProperties = assetProperties;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                immutableMap3 = this.mCdnSessionIdMapParser.parse(jsonNode2);
                            }
                            builder.cdnSessionIds = immutableMap3;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field withDebInfo can't be null");
                            }
                            builder.withDebInfo = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                immutableList2 = this.mAdAssetListParser.parse(jsonNode2);
                            }
                            builder.adAssets = immutableList2;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonNode2);
                            }
                            builder.consumptionType = consumptionType;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                adStitchingType = (AdStitchingType) this.mAdStitchingTypeParser.parse(jsonNode2);
                            }
                            builder.adStitchingType = adStitchingType;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.videoServiceProvider = str5;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mMaterialTypeParser.parse(jsonNode2);
                            }
                            builder.materialType = str4;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.marketplaceId = str3;
                            break;
                        case 9:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field daiDesired can't be null");
                            }
                            builder.daiDesired = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                immutableMap2 = this.mCuepointMapParser.parse(jsonNode2);
                            }
                            builder.cuepoints = immutableMap2;
                            break;
                        case 11:
                            if (!jsonNode2.isNull()) {
                                customer = this.mCustomerParser.parse(jsonNode2);
                            }
                            builder.customer = customer;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.passthroughAdParams = str2;
                            break;
                        case 13:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mAdParamMapParser.parse(jsonNode2);
                            }
                            builder.adParams = immutableMap;
                            break;
                        case 14:
                            if (!jsonNode2.isNull()) {
                                asset = this.mAssetParser.parse(jsonNode2);
                            }
                            builder.asset = asset;
                            break;
                        case 15:
                            if (!jsonNode2.isNull()) {
                                device = this.mDeviceParser.parse(jsonNode2);
                            }
                            builder.device = device;
                            break;
                        case 16:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field disableHoldbacks can't be null");
                            }
                            builder.disableHoldbacks = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 17:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mAdStitchingSegmentInfoTypesParser.parse(jsonNode2);
                            }
                            builder.adStitchingSegmentInfoTypes = immutableList;
                            break;
                        case 18:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.tenantId = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAssetsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2115601151:
                                    if (currentName.equals("materialType")) {
                                        b = 7;
                                    }
                                    break;
                                case -1980493655:
                                    if (currentName.equals("withDebInfo")) {
                                        b = 2;
                                    }
                                    break;
                                case -1806627959:
                                    if (currentName.equals("adParams")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -1753187514:
                                    if (currentName.equals("adStitchingSegmentInfoTypes")) {
                                        b = 17;
                                    }
                                    break;
                                case -1335157162:
                                    if (currentName.equals("device")) {
                                        b = 15;
                                    }
                                    break;
                                case -1326414587:
                                    if (currentName.equals("disableHoldbacks")) {
                                        b = 16;
                                    }
                                    break;
                                case -1306693787:
                                    if (currentName.equals("tenantId")) {
                                        b = 18;
                                    }
                                    break;
                                case -658725624:
                                    if (currentName.equals("adStitchingType")) {
                                        b = 5;
                                    }
                                    break;
                                case -167715474:
                                    if (currentName.equals("daiDesired")) {
                                        b = 9;
                                    }
                                    break;
                                case 93121264:
                                    if (currentName.equals("asset")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 305419343:
                                    if (currentName.equals("cdnSessionIds")) {
                                        b = 1;
                                    }
                                    break;
                                case 368095605:
                                    if (currentName.equals("consumptionType")) {
                                        b = 4;
                                    }
                                    break;
                                case 606175198:
                                    if (currentName.equals("customer")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 915881277:
                                    if (currentName.equals("passthroughAdParams")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 8;
                                    }
                                    break;
                                case 1556900299:
                                    if (currentName.equals("videoServiceProvider")) {
                                        b = 6;
                                    }
                                    break;
                                case 1744267331:
                                    if (currentName.equals("assetProperties")) {
                                        b = 0;
                                    }
                                    break;
                                case 1956785366:
                                    if (currentName.equals("cuepoints")) {
                                        b = 10;
                                    }
                                    break;
                                case 2075559302:
                                    if (currentName.equals("adAssets")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            AssetProperties assetProperties = null;
                            String str = null;
                            ImmutableList<AdStitchingSegmentInfoType> immutableList = null;
                            Device device = null;
                            Asset asset = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str2 = null;
                            Customer customer = null;
                            ImmutableMap<String, ImmutableList<PlaylistComponent>> immutableMap2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            AdStitchingType adStitchingType = null;
                            ConsumptionType consumptionType = null;
                            ImmutableList<AdAsset> immutableList2 = null;
                            ImmutableMap<String, String> immutableMap3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        assetProperties = this.mAssetPropertiesParser.parse(jsonParser);
                                    }
                                    builder.assetProperties = assetProperties;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mCdnSessionIdMapParser.parse(jsonParser);
                                    }
                                    builder.cdnSessionIds = immutableMap3;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field withDebInfo can't be null");
                                    }
                                    builder.withDebInfo = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mAdAssetListParser.parse(jsonParser);
                                    }
                                    builder.adAssets = immutableList2;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        consumptionType = (ConsumptionType) this.mConsumptionTypeParser.parse(jsonParser);
                                    }
                                    builder.consumptionType = consumptionType;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        adStitchingType = (AdStitchingType) this.mAdStitchingTypeParser.parse(jsonParser);
                                    }
                                    builder.adStitchingType = adStitchingType;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.videoServiceProvider = str5;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mMaterialTypeParser.parse(jsonParser);
                                    }
                                    builder.materialType = str4;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str3;
                                    break;
                                case 9:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field daiDesired can't be null");
                                    }
                                    builder.daiDesired = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mCuepointMapParser.parse(jsonParser);
                                    }
                                    builder.cuepoints = immutableMap2;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        customer = this.mCustomerParser.parse(jsonParser);
                                    }
                                    builder.customer = customer;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.passthroughAdParams = str2;
                                    break;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mAdParamMapParser.parse(jsonParser);
                                    }
                                    builder.adParams = immutableMap;
                                    break;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        asset = this.mAssetParser.parse(jsonParser);
                                    }
                                    builder.asset = asset;
                                    break;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        device = this.mDeviceParser.parse(jsonParser);
                                    }
                                    builder.device = device;
                                    break;
                                case 16:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field disableHoldbacks can't be null");
                                    }
                                    builder.disableHoldbacks = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAdStitchingSegmentInfoTypesParser.parse(jsonParser);
                                    }
                                    builder.adStitchingSegmentInfoTypes = immutableList;
                                    break;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.tenantId = str;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAssetsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
