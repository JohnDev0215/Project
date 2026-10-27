package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TitleActionsDataRequest {
    public final Optional<String> currentTerritory;
    public final Optional<String> customerId;
    public final String deviceId;
    public final String deviceTypeId;
    public final Optional<ImmutableList<DebugMode>> enabledDebugModes;
    public final Optional<Features> features;
    public final Optional<String> geoToken;
    public final Optional<String> homeTerritory;
    public final ImmutableList<String> identifiers;
    public final Optional<String> identityContext;
    public final Optional<String> ipAddress;
    public final Optional<String> marketplaceId;
    public final Optional<ImmutableList<String>> preferredLocaleList;
    public final Optional<ImmutableMap<String, String>> weblabOverrides;

    @NotThreadSafe
    public static abstract class Builder {
        public String currentTerritory;
        public String customerId;
        public String deviceId;
        public String deviceTypeId;
        public ImmutableList<DebugMode> enabledDebugModes;
        public Features features;
        public String geoToken;
        public String homeTerritory;
        public ImmutableList<String> identifiers;
        public String identityContext;
        public String ipAddress;
        public String marketplaceId;
        public ImmutableList<String> preferredLocaleList;
        public ImmutableMap<String, String> weblabOverrides;
    }

    protected TitleActionsDataRequest(Builder builder) {
        this.homeTerritory = Optional.fromNullable(builder.homeTerritory);
        this.weblabOverrides = Optional.fromNullable(builder.weblabOverrides);
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
        this.preferredLocaleList = Optional.fromNullable(builder.preferredLocaleList);
        this.features = Optional.fromNullable(builder.features);
        this.enabledDebugModes = Optional.fromNullable(builder.enabledDebugModes);
        this.deviceId = (String) Preconditions.checkNotNull(builder.deviceId, "Unexpected null field: deviceId");
        this.currentTerritory = Optional.fromNullable(builder.currentTerritory);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.identityContext = Optional.fromNullable(builder.identityContext);
        this.deviceTypeId = (String) Preconditions.checkNotNull(builder.deviceTypeId, "Unexpected null field: deviceTypeId");
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.ipAddress = Optional.fromNullable(builder.ipAddress);
        this.customerId = Optional.fromNullable(builder.customerId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleActionsDataRequest)) {
            return false;
        }
        TitleActionsDataRequest titleActionsDataRequest = (TitleActionsDataRequest) obj;
        return Objects.equal(this.homeTerritory, titleActionsDataRequest.homeTerritory) && Objects.equal(this.weblabOverrides, titleActionsDataRequest.weblabOverrides) && Objects.equal(this.identifiers, titleActionsDataRequest.identifiers) && Objects.equal(this.preferredLocaleList, titleActionsDataRequest.preferredLocaleList) && Objects.equal(this.features, titleActionsDataRequest.features) && Objects.equal(this.enabledDebugModes, titleActionsDataRequest.enabledDebugModes) && Objects.equal(this.deviceId, titleActionsDataRequest.deviceId) && Objects.equal(this.currentTerritory, titleActionsDataRequest.currentTerritory) && Objects.equal(this.marketplaceId, titleActionsDataRequest.marketplaceId) && Objects.equal(this.identityContext, titleActionsDataRequest.identityContext) && Objects.equal(this.deviceTypeId, titleActionsDataRequest.deviceTypeId) && Objects.equal(this.geoToken, titleActionsDataRequest.geoToken) && Objects.equal(this.ipAddress, titleActionsDataRequest.ipAddress) && Objects.equal(this.customerId, titleActionsDataRequest.customerId);
    }

    public int hashCode() {
        return Objects.hashCode(this.homeTerritory, this.weblabOverrides, this.identifiers, this.preferredLocaleList, this.features, this.enabledDebugModes, this.deviceId, this.currentTerritory, this.marketplaceId, this.identityContext, this.deviceTypeId, this.geoToken, this.ipAddress, this.customerId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("homeTerritory", this.homeTerritory).add("weblabOverrides", this.weblabOverrides).add("identifiers", this.identifiers).add("preferredLocaleList", this.preferredLocaleList).add("features", this.features).add("enabledDebugModes", this.enabledDebugModes).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("currentTerritory", this.currentTerritory).add("marketplaceId", this.marketplaceId).add("identityContext", this.identityContext).add("deviceTypeId", this.deviceTypeId).add("geoToken", this.geoToken).add("ipAddress", this.ipAddress).add("customerId", this.customerId).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<TitleActionsDataRequest> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.TITLEACTIONSDATAREQUEST_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
