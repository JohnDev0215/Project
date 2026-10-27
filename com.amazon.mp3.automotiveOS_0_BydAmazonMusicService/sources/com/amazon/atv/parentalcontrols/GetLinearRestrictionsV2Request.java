package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ClientDetails;
import com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetLinearRestrictionsV2Request extends GeoCustomerWithClientDetailsRequest {
    public final String channelId;
    public final Optional<String> customerTimezone;
    public final Long endTime;
    public final Long startTime;

    private GetLinearRestrictionsV2Request(Builder builder) {
        super(builder);
        this.customerTimezone = Optional.fromNullable(builder.customerTimezone);
        this.endTime = (Long) Preconditions.checkNotNull(builder.endTime, "Unexpected null field: endTime");
        this.channelId = (String) Preconditions.checkNotNull(builder.channelId, "Unexpected null field: channelId");
        this.startTime = (Long) Preconditions.checkNotNull(builder.startTime, "Unexpected null field: startTime");
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLinearRestrictionsV2Request)) {
            return false;
        }
        GetLinearRestrictionsV2Request getLinearRestrictionsV2Request = (GetLinearRestrictionsV2Request) obj;
        return super.equals(obj) && Objects.equal(this.customerTimezone, getLinearRestrictionsV2Request.customerTimezone) && Objects.equal(this.endTime, getLinearRestrictionsV2Request.endTime) && Objects.equal(this.channelId, getLinearRestrictionsV2Request.channelId) && Objects.equal(this.startTime, getLinearRestrictionsV2Request.startTime);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.customerTimezone, this.endTime, this.channelId, this.startTime);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerTimezone", this.customerTimezone).add("endTime", this.endTime).add("channelId", this.channelId).add("startTime", this.startTime).toString();
    }

    @NotThreadSafe
    public static class Builder extends GeoCustomerWithClientDetailsRequest.Builder {
        public String channelId;
        public String customerTimezone;
        public Long endTime;
        public Long startTime;

        public GetLinearRestrictionsV2Request build() {
            return new GetLinearRestrictionsV2Request(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLinearRestrictionsV2Request> {
        private final ClientDetails.Parser mClientDetailsParser;
        private final SimpleParsers.StringParser mcurrentTimezoneParser;
        private final SimpleParsers.LongParser mepochMilliParser;
        private final SimpleParsers.StringParser mgeoTokenParser;
        private final SimpleParsers.StringParser midentifierParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientDetailsParser = new ClientDetails.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mcurrentTimezoneParser = stringParser;
            this.mepochMilliParser = SimpleParsers.LongParser.INSTANCE;
            this.midentifierParser = stringParser;
            this.mgeoTokenParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLinearRestrictionsV2Request parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsV2Request:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLinearRestrictionsV2Request parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLinearRestrictionsV2Request:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006c  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 312
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 312
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atv.parentalcontrols.GetLinearRestrictionsV2Request");
        }
    }
}
