package com.amazon.atv.playbackauthority.service.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AdConfiguration {
    public final Optional<ImmutableMap<String, String>> parameters;
    public final boolean showDynamicAd;

    private AdConfiguration(Builder builder) {
        this.showDynamicAd = builder.showDynamicAd;
        this.parameters = Optional.fromNullable(builder.parameters);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdConfiguration)) {
            return false;
        }
        AdConfiguration adConfiguration = (AdConfiguration) obj;
        return Objects.equal(Boolean.valueOf(this.showDynamicAd), Boolean.valueOf(adConfiguration.showDynamicAd)) && Objects.equal(this.parameters, adConfiguration.parameters);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.showDynamicAd), this.parameters);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("showDynamicAd", this.showDynamicAd).add("parameters", this.parameters).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, String> parameters;
        public boolean showDynamicAd;

        public AdConfiguration build() {
            return new AdConfiguration(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AdConfiguration> {
        private final MapParser<String, String> mAdParametersParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mAdParametersParser = MapParser.newParser(stringParser, stringParser);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AdConfiguration parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdConfiguration:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AdConfiguration parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdConfiguration:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AdConfiguration parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AdConfiguration");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 458736106) {
                        if (iHashCode == 1150592581 && next.equals("showDynamicAd")) {
                            b = 0;
                        }
                    } else if (next.equals("parameters")) {
                        b = 1;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.parameters = jsonNode2.isNull() ? null : this.mAdParametersParser.parse(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field showDynamicAd can't be null");
                        }
                        builder.showDynamicAd = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AdConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.showDynamicAd), this, "showDynamicAd");
            return builder.build();
        }

        @Nonnull
        private AdConfiguration parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 458736106) {
                                if (iHashCode == 1150592581 && currentName.equals("showDynamicAd")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("parameters")) {
                                b = 1;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.parameters = currentToken == JsonToken.VALUE_NULL ? null : this.mAdParametersParser.parse(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field showDynamicAd can't be null");
                                }
                                builder.showDynamicAd = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AdConfiguration so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.showDynamicAd), this, "showDynamicAd");
                    return builder.build();
                }
            }
        }
    }
}
