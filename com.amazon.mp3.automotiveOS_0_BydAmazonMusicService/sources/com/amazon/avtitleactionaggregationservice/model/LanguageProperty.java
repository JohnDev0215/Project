package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class LanguageProperty {
    public final Optional<String> id;
    public final Optional<String> locale;
    public final Optional<String> name;
    public final Optional<LanguagePropertyType> type;

    private LanguageProperty(Builder builder) {
        this.type = Optional.fromNullable(builder.type);
        this.locale = Optional.fromNullable(builder.locale);
        this.id = Optional.fromNullable(builder.id);
        this.name = Optional.fromNullable(builder.name);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LanguageProperty)) {
            return false;
        }
        LanguageProperty languageProperty = (LanguageProperty) obj;
        return Objects.equal(this.type, languageProperty.type) && Objects.equal(this.locale, languageProperty.locale) && Objects.equal(this.id, languageProperty.id) && Objects.equal(this.name, languageProperty.name);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.locale, this.id, this.name);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add(Splash.PARAMS_LOCALE, this.locale).add("id", this.id).add("name", this.name).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String id;
        public String locale;
        public String name;
        public LanguagePropertyType type;

        public LanguageProperty build() {
            return new LanguageProperty(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LanguageProperty> {
        private final EnumParser<LanguagePropertyType> mLanguagePropertyTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLanguagePropertyTypeParser = EnumParser.newParser(LanguagePropertyType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LanguageProperty parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LanguageProperty:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LanguageProperty parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LanguageProperty:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LanguageProperty parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LanguageProperty");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1097462182) {
                        if (iHashCode != 3355) {
                            if (iHashCode != 3373707) {
                                if (iHashCode == 3575610 && next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 0;
                                }
                            } else if (next.equals("name")) {
                                b = 3;
                            }
                        } else if (next.equals("id")) {
                            b = 2;
                        }
                    } else if (next.equals(Splash.PARAMS_LOCALE)) {
                        b = 1;
                    }
                    String str = null;
                    LanguagePropertyType languagePropertyType = null;
                    String str2 = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            languagePropertyType = (LanguagePropertyType) this.mLanguagePropertyTypeParser.parse(jsonNode2);
                        }
                        builder.type = languagePropertyType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.locale = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.name = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LanguageProperty so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private LanguageProperty parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1097462182) {
                                if (iHashCode != 3355) {
                                    if (iHashCode != 3373707) {
                                        if (iHashCode == 3575610 && currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                            b = 0;
                                        }
                                    } else if (currentName.equals("name")) {
                                        b = 3;
                                    }
                                } else if (currentName.equals("id")) {
                                    b = 2;
                                }
                            } else if (currentName.equals(Splash.PARAMS_LOCALE)) {
                                b = 1;
                            }
                            String str = null;
                            LanguagePropertyType languagePropertyType = null;
                            String str2 = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    languagePropertyType = (LanguagePropertyType) this.mLanguagePropertyTypeParser.parse(jsonParser);
                                }
                                builder.type = languagePropertyType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.locale = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.id = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.name = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LanguageProperty so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
