package com.amazon.atv.featureconfig;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SetCustomerPreferenceRequest {
    public final String clientId;
    public final String featureName;
    public final ImmutableMap<String, String> featurePreferences;
    public final String requestId;
    public final Optional<Scope> scope;

    private SetCustomerPreferenceRequest(Builder builder) {
        this.requestId = (String) Preconditions.checkNotNull(builder.requestId, "Unexpected null field: requestId");
        this.featurePreferences = (ImmutableMap) Preconditions.checkNotNull(builder.featurePreferences, "Unexpected null field: featurePreferences");
        this.scope = Optional.fromNullable(builder.scope);
        this.clientId = (String) Preconditions.checkNotNull(builder.clientId, "Unexpected null field: clientId");
        this.featureName = (String) Preconditions.checkNotNull(builder.featureName, "Unexpected null field: featureName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetCustomerPreferenceRequest)) {
            return false;
        }
        SetCustomerPreferenceRequest setCustomerPreferenceRequest = (SetCustomerPreferenceRequest) obj;
        return Objects.equal(this.requestId, setCustomerPreferenceRequest.requestId) && Objects.equal(this.featurePreferences, setCustomerPreferenceRequest.featurePreferences) && Objects.equal(this.scope, setCustomerPreferenceRequest.scope) && Objects.equal(this.clientId, setCustomerPreferenceRequest.clientId) && Objects.equal(this.featureName, setCustomerPreferenceRequest.featureName);
    }

    public int hashCode() {
        return Objects.hashCode(this.requestId, this.featurePreferences, this.scope, this.clientId, this.featureName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(TetheredMessageKey.requestId, this.requestId).add("featurePreferences", this.featurePreferences).add("scope", this.scope).add("clientId", this.clientId).add("featureName", this.featureName).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String clientId;
        public String featureName;
        public ImmutableMap<String, String> featurePreferences;
        public String requestId;
        public Scope scope;

        public SetCustomerPreferenceRequest build() {
            return new SetCustomerPreferenceRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SetCustomerPreferenceRequest> {
        private final MapParser<String, String> mFeaturePreferencesParser;
        private final EnumParser<Scope> mScopeParser;
        private final SimpleParsers.StringParser mclientIdParser;
        private final SimpleParsers.StringParser mfeatureNameParser;
        private final SimpleParsers.StringParser mrequestIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mScopeParser = EnumParser.newParser(Scope.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFeaturePreferencesParser = MapParser.newParser(stringParser, stringParser);
            this.mrequestIdParser = stringParser;
            this.mclientIdParser = stringParser;
            this.mfeatureNameParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SetCustomerPreferenceRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetCustomerPreferenceRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SetCustomerPreferenceRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetCustomerPreferenceRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.featureconfig.SetCustomerPreferenceRequest parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 276
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.featureconfig.SetCustomerPreferenceRequest.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.featureconfig.SetCustomerPreferenceRequest");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SetCustomerPreferenceRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1465469406:
                                    if (currentName.equals("featurePreferences")) {
                                        b = 1;
                                    }
                                    break;
                                case -377160031:
                                    if (currentName.equals("featureName")) {
                                        b = 4;
                                    }
                                    break;
                                case 109264468:
                                    if (currentName.equals("scope")) {
                                        b = 2;
                                    }
                                    break;
                                case 693933066:
                                    if (currentName.equals(TetheredMessageKey.requestId)) {
                                        b = 0;
                                    }
                                    break;
                                case 908408390:
                                    if (currentName.equals("clientId")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            Scope scope = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mrequestIdParser.parse(jsonParser);
                                }
                                builder.requestId = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mFeaturePreferencesParser.parse(jsonParser);
                                }
                                builder.featurePreferences = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    scope = (Scope) this.mScopeParser.parse(jsonParser);
                                }
                                builder.scope = scope;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mclientIdParser.parse(jsonParser);
                                }
                                builder.clientId = str3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mfeatureNameParser.parse(jsonParser);
                                }
                                builder.featureName = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SetCustomerPreferenceRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.requestId, this, TetheredMessageKey.requestId);
                    JsonParsingUtils.checkNotNull(builder.featurePreferences, this, "featurePreferences");
                    JsonParsingUtils.checkNotNull(builder.clientId, this, "clientId");
                    JsonParsingUtils.checkNotNull(builder.featureName, this, "featureName");
                    return builder.build();
                }
            }
        }
    }
}
