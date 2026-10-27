package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackresource.TestOverrides;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RequestMetadata {
    public final String apiVersion;
    public final ConsumptionType consumptionType;
    public final ImmutableList<Resource> desiredResources;
    public final String marketplaceId;
    public final ResourceUsage resourceUsage;
    public final Optional<String> tenantId;
    public final Optional<TestOverrides> testOverrides;

    private RequestMetadata(Builder builder) {
        this.resourceUsage = (ResourceUsage) Preconditions.checkNotNull(builder.resourceUsage, "Unexpected null field: resourceUsage");
        this.marketplaceId = (String) Preconditions.checkNotNull(builder.marketplaceId, "Unexpected null field: marketplaceId");
        this.desiredResources = (ImmutableList) Preconditions.checkNotNull(builder.desiredResources, "Unexpected null field: desiredResources");
        this.apiVersion = (String) Preconditions.checkNotNull(builder.apiVersion, "Unexpected null field: apiVersion");
        this.testOverrides = Optional.fromNullable(builder.testOverrides);
        this.consumptionType = (ConsumptionType) Preconditions.checkNotNull(builder.consumptionType, "Unexpected null field: consumptionType");
        this.tenantId = Optional.fromNullable(builder.tenantId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestMetadata)) {
            return false;
        }
        RequestMetadata requestMetadata = (RequestMetadata) obj;
        return Objects.equal(this.resourceUsage, requestMetadata.resourceUsage) && Objects.equal(this.marketplaceId, requestMetadata.marketplaceId) && Objects.equal(this.desiredResources, requestMetadata.desiredResources) && Objects.equal(this.apiVersion, requestMetadata.apiVersion) && Objects.equal(this.testOverrides, requestMetadata.testOverrides) && Objects.equal(this.consumptionType, requestMetadata.consumptionType) && Objects.equal(this.tenantId, requestMetadata.tenantId);
    }

    public int hashCode() {
        return Objects.hashCode(this.resourceUsage, this.marketplaceId, this.desiredResources, this.apiVersion, this.testOverrides, this.consumptionType, this.tenantId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("resourceUsage", this.resourceUsage).add("marketplaceId", this.marketplaceId).add("desiredResources", this.desiredResources).add("apiVersion", this.apiVersion).add("testOverrides", this.testOverrides).add("consumptionType", this.consumptionType).add("tenantId", this.tenantId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String apiVersion;
        public ConsumptionType consumptionType;
        public ImmutableList<Resource> desiredResources;
        public String marketplaceId;
        public ResourceUsage resourceUsage;
        public String tenantId;
        public TestOverrides testOverrides;

        public RequestMetadata build() {
            return new RequestMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RequestMetadata> {
        private final ListParser<Resource> mDesiredResourcesListParser;
        private final TestOverrides.Parser mTestOverridesParser;
        private final EnumParser<ConsumptionType> mconsumptionTypeParser;
        private final EnumParser<ResourceUsage> mresourceUsageParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mconsumptionTypeParser = EnumParser.newParser(ConsumptionType.class);
            this.mDesiredResourcesListParser = ListParser.newParser(EnumParser.newParser(Resource.class));
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
            this.mresourceUsageParser = EnumParser.newParser(ResourceUsage.class);
            this.mTestOverridesParser = new TestOverrides.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RequestMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RequestMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RequestMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RequestMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RequestMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ResourceUsage resourceUsage;
            String str;
            ImmutableList<Resource> immutableList;
            String str2;
            TestOverrides testOverrides;
            ConsumptionType consumptionType;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "RequestMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2125624994:
                            if (next.equals("apiVersion")) {
                                b = 3;
                            }
                            break;
                        case -1306693787:
                            if (next.equals("tenantId")) {
                                b = 6;
                            }
                            break;
                        case -80265803:
                            if (next.equals("testOverrides")) {
                                b = 4;
                            }
                            break;
                        case 368095605:
                            if (next.equals("consumptionType")) {
                                b = 5;
                            }
                            break;
                        case 970334771:
                            if (next.equals("resourceUsage")) {
                                b = 0;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 1;
                            }
                            break;
                        case 1949306027:
                            if (next.equals("desiredResources")) {
                                b = 2;
                            }
                            break;
                    }
                    resourceUsage = null;
                    str3 = null;
                    consumptionType = null;
                    testOverrides = null;
                    str2 = null;
                    immutableList = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RequestMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            resourceUsage = (ResourceUsage) this.mresourceUsageParser.parse(jsonNode2);
                        }
                        builder.resourceUsage = resourceUsage;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mDesiredResourcesListParser.parse(jsonNode2);
                        }
                        builder.desiredResources = immutableList;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.apiVersion = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            testOverrides = this.mTestOverridesParser.parse(jsonNode2);
                        }
                        builder.testOverrides = testOverrides;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            consumptionType = (ConsumptionType) this.mconsumptionTypeParser.parse(jsonNode2);
                        }
                        builder.consumptionType = consumptionType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.tenantId = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RequestMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.resourceUsage, this, "resourceUsage");
            JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
            JsonParsingUtils.checkNotNull(builder.desiredResources, this, "desiredResources");
            JsonParsingUtils.checkNotNull(builder.apiVersion, this, "apiVersion");
            JsonParsingUtils.checkNotNull(builder.consumptionType, this, "consumptionType");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RequestMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2125624994:
                                    if (currentName.equals("apiVersion")) {
                                        b = 3;
                                    }
                                    break;
                                case -1306693787:
                                    if (currentName.equals("tenantId")) {
                                        b = 6;
                                    }
                                    break;
                                case -80265803:
                                    if (currentName.equals("testOverrides")) {
                                        b = 4;
                                    }
                                    break;
                                case 368095605:
                                    if (currentName.equals("consumptionType")) {
                                        b = 5;
                                    }
                                    break;
                                case 970334771:
                                    if (currentName.equals("resourceUsage")) {
                                        b = 0;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 1;
                                    }
                                    break;
                                case 1949306027:
                                    if (currentName.equals("desiredResources")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ResourceUsage resourceUsage = null;
                            String str = null;
                            ConsumptionType consumptionType = null;
                            TestOverrides testOverrides = null;
                            String str2 = null;
                            ImmutableList<Resource> immutableList = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        resourceUsage = (ResourceUsage) this.mresourceUsageParser.parse(jsonParser);
                                    }
                                    builder.resourceUsage = resourceUsage;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.marketplaceId = str3;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mDesiredResourcesListParser.parse(jsonParser);
                                    }
                                    builder.desiredResources = immutableList;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.apiVersion = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        testOverrides = this.mTestOverridesParser.parse(jsonParser);
                                    }
                                    builder.testOverrides = testOverrides;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        consumptionType = (ConsumptionType) this.mconsumptionTypeParser.parse(jsonParser);
                                    }
                                    builder.consumptionType = consumptionType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.tenantId = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RequestMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RequestMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.resourceUsage, this, "resourceUsage");
                    JsonParsingUtils.checkNotNull(builder.marketplaceId, this, "marketplaceId");
                    JsonParsingUtils.checkNotNull(builder.desiredResources, this, "desiredResources");
                    JsonParsingUtils.checkNotNull(builder.apiVersion, this, "apiVersion");
                    JsonParsingUtils.checkNotNull(builder.consumptionType, this, "consumptionType");
                    return builder.build();
                }
            }
        }
    }
}
