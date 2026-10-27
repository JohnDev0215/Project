package com.amazon.atv.featureconfig;

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
public class CustomerPreference {
    public final Optional<String> featureName;
    public final Optional<ImmutableMap<String, String>> featurePreferences;

    private CustomerPreference(Builder builder) {
        this.featureName = Optional.fromNullable(builder.featureName);
        this.featurePreferences = Optional.fromNullable(builder.featurePreferences);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomerPreference)) {
            return false;
        }
        CustomerPreference customerPreference = (CustomerPreference) obj;
        return Objects.equal(this.featureName, customerPreference.featureName) && Objects.equal(this.featurePreferences, customerPreference.featurePreferences);
    }

    public int hashCode() {
        return Objects.hashCode(this.featureName, this.featurePreferences);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("featureName", this.featureName).add("featurePreferences", this.featurePreferences).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String featureName;
        public ImmutableMap<String, String> featurePreferences;

        public CustomerPreference build() {
            return new CustomerPreference(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CustomerPreference> {
        private final MapParser<String, String> mFeaturePreferencesParser;
        private final SimpleParsers.StringParser mfeatureNameParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFeaturePreferencesParser = MapParser.newParser(stringParser, stringParser);
            this.mfeatureNameParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CustomerPreference parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CustomerPreference:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CustomerPreference parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CustomerPreference:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CustomerPreference parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CustomerPreference");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1465469406) {
                        if (iHashCode == -377160031 && next.equals("featureName")) {
                            b = 0;
                        }
                    } else if (next.equals("featurePreferences")) {
                        b = 1;
                    }
                    ImmutableMap<String, String> immutableMap = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mfeatureNameParser.parse(jsonNode2);
                        }
                        builder.featureName = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mFeaturePreferencesParser.parse(jsonNode2);
                        }
                        builder.featurePreferences = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CustomerPreference so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CustomerPreference parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1465469406) {
                                if (iHashCode == -377160031 && currentName.equals("featureName")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("featurePreferences")) {
                                b = 1;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mfeatureNameParser.parse(jsonParser);
                                }
                                builder.featureName = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mFeaturePreferencesParser.parse(jsonParser);
                                }
                                builder.featurePreferences = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CustomerPreference so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
