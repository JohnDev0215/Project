package com.amazon.atv.featureconfig.fedrecs;

import com.amazon.atv.featureconfig.Config;
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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AutoPlayCustomerPreferences extends Config {
    public final Optional<Boolean> autoplayEnabled;
    public final Optional<Boolean> continuousPlaybackEnabled;

    private AutoPlayCustomerPreferences(Builder builder) {
        super(builder);
        this.continuousPlaybackEnabled = Optional.fromNullable(builder.continuousPlaybackEnabled);
        this.autoplayEnabled = Optional.fromNullable(builder.autoplayEnabled);
    }

    @Override // com.amazon.atv.featureconfig.Config
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutoPlayCustomerPreferences)) {
            return false;
        }
        AutoPlayCustomerPreferences autoPlayCustomerPreferences = (AutoPlayCustomerPreferences) obj;
        return super.equals(obj) && Objects.equal(this.continuousPlaybackEnabled, autoPlayCustomerPreferences.continuousPlaybackEnabled) && Objects.equal(this.autoplayEnabled, autoPlayCustomerPreferences.autoplayEnabled);
    }

    @Override // com.amazon.atv.featureconfig.Config
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.continuousPlaybackEnabled, this.autoplayEnabled);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("continuousPlaybackEnabled", this.continuousPlaybackEnabled).add("autoplayEnabled", this.autoplayEnabled).toString();
    }

    @NotThreadSafe
    public static class Builder extends Config.Builder {
        public Boolean autoplayEnabled;
        public Boolean continuousPlaybackEnabled;

        public AutoPlayCustomerPreferences build() {
            return new AutoPlayCustomerPreferences(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AutoPlayCustomerPreferences> {
        private final SimpleParsers.BooleanParser mBooleanFieldParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBooleanFieldParser = SimpleParsers.BooleanParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AutoPlayCustomerPreferences parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutoPlayCustomerPreferences:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AutoPlayCustomerPreferences parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutoPlayCustomerPreferences:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AutoPlayCustomerPreferences parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AutoPlayCustomerPreferences");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 214879639) {
                        if (iHashCode == 764699742 && next.equals("autoplayEnabled")) {
                            b = 1;
                        }
                    } else if (next.equals("continuousPlaybackEnabled")) {
                        b = 0;
                    }
                    Boolean bool = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanFieldParser.parse(jsonNode2);
                        }
                        builder.continuousPlaybackEnabled = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanFieldParser.parse(jsonNode2);
                        }
                        builder.autoplayEnabled = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AutoPlayCustomerPreferences so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private AutoPlayCustomerPreferences parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 214879639) {
                                if (iHashCode == 764699742 && currentName.equals("autoplayEnabled")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("continuousPlaybackEnabled")) {
                                b = 0;
                            }
                            Boolean bool = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanFieldParser.parse(jsonParser);
                                }
                                builder.continuousPlaybackEnabled = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanFieldParser.parse(jsonParser);
                                }
                                builder.autoplayEnabled = bool;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AutoPlayCustomerPreferences so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
