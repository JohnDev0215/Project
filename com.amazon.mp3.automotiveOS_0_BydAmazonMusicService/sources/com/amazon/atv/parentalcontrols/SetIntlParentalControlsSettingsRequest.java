package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.DefaultIntlParentalControlsSettings;
import com.amazon.atv.parentalcontrols.InternationalCustomerRequest;
import com.amazon.atv.parentalcontrols.IntlClientParentalControlsSettings;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class SetIntlParentalControlsSettingsRequest extends InternationalCustomerRequest {
    public final Optional<ImmutableList<IntlClientParentalControlsSettings>> clientSettings;
    public final Optional<ImmutableMap<String, ImmutableMap<String, String>>> countrySpecificFeatures;
    public final Optional<DefaultIntlParentalControlsSettings> defaultSettings;

    private SetIntlParentalControlsSettingsRequest(Builder builder) {
        super(builder);
        this.defaultSettings = Optional.fromNullable(builder.defaultSettings);
        this.clientSettings = Optional.fromNullable(builder.clientSettings);
        this.countrySpecificFeatures = Optional.fromNullable(builder.countrySpecificFeatures);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetIntlParentalControlsSettingsRequest)) {
            return false;
        }
        SetIntlParentalControlsSettingsRequest setIntlParentalControlsSettingsRequest = (SetIntlParentalControlsSettingsRequest) obj;
        return super.equals(obj) && Objects.equal(this.defaultSettings, setIntlParentalControlsSettingsRequest.defaultSettings) && Objects.equal(this.clientSettings, setIntlParentalControlsSettingsRequest.clientSettings) && Objects.equal(this.countrySpecificFeatures, setIntlParentalControlsSettingsRequest.countrySpecificFeatures);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.defaultSettings, this.clientSettings, this.countrySpecificFeatures);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("defaultSettings", this.defaultSettings).add("clientSettings", this.clientSettings).add("countrySpecificFeatures", this.countrySpecificFeatures).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerRequest.Builder {
        public ImmutableList<IntlClientParentalControlsSettings> clientSettings;
        public ImmutableMap<String, ImmutableMap<String, String>> countrySpecificFeatures;
        public DefaultIntlParentalControlsSettings defaultSettings;

        public SetIntlParentalControlsSettingsRequest build() {
            return new SetIntlParentalControlsSettingsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SetIntlParentalControlsSettingsRequest> {
        private final MapParser<String, ImmutableMap<String, String>> mCountrySpecificFeaturesParser;
        private final DefaultIntlParentalControlsSettings.Parser mDefaultIntlParentalControlsSettingsParser;
        private final ListParser<IntlClientParentalControlsSettings> mIntlParentalControlsClientSettingsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDefaultIntlParentalControlsSettingsParser = new DefaultIntlParentalControlsSettings.Parser(objectMapper);
            this.mIntlParentalControlsClientSettingsListParser = ListParser.newParser(new IntlClientParentalControlsSettings.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mCountrySpecificFeaturesParser = MapParser.newParser(stringParser, MapParser.newParser(stringParser, stringParser));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SetIntlParentalControlsSettingsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetIntlParentalControlsSettingsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SetIntlParentalControlsSettingsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetIntlParentalControlsSettingsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SetIntlParentalControlsSettingsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SetIntlParentalControlsSettingsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -862247548) {
                        if (iHashCode != -358576315) {
                            if (iHashCode == 1578178830 && next.equals("clientSettings")) {
                                b = 1;
                            }
                        } else if (next.equals("countrySpecificFeatures")) {
                            b = 2;
                        }
                    } else if (next.equals("defaultSettings")) {
                        b = 0;
                    }
                    ImmutableMap<String, ImmutableMap<String, String>> immutableMap = null;
                    DefaultIntlParentalControlsSettings defaultIntlParentalControlsSettings = null;
                    ImmutableList<IntlClientParentalControlsSettings> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            defaultIntlParentalControlsSettings = this.mDefaultIntlParentalControlsSettingsParser.parse(jsonNode2);
                        }
                        builder.defaultSettings = defaultIntlParentalControlsSettings;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mIntlParentalControlsClientSettingsListParser.parse(jsonNode2);
                        }
                        builder.clientSettings = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mCountrySpecificFeaturesParser.parse(jsonNode2);
                        }
                        builder.countrySpecificFeatures = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SetIntlParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SetIntlParentalControlsSettingsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -862247548) {
                                if (iHashCode != -358576315) {
                                    if (iHashCode == 1578178830 && currentName.equals("clientSettings")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("countrySpecificFeatures")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("defaultSettings")) {
                                b = 0;
                            }
                            ImmutableMap<String, ImmutableMap<String, String>> immutableMap = null;
                            DefaultIntlParentalControlsSettings defaultIntlParentalControlsSettings = null;
                            ImmutableList<IntlClientParentalControlsSettings> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    defaultIntlParentalControlsSettings = this.mDefaultIntlParentalControlsSettingsParser.parse(jsonParser);
                                }
                                builder.defaultSettings = defaultIntlParentalControlsSettings;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mIntlParentalControlsClientSettingsListParser.parse(jsonParser);
                                }
                                builder.clientSettings = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mCountrySpecificFeaturesParser.parse(jsonParser);
                                }
                                builder.countrySpecificFeatures = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SetIntlParentalControlsSettingsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
