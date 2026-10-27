package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class IntlClientParentalControlsSettings {
    public final Client client;
    public final boolean ratingsRestricted;

    private IntlClientParentalControlsSettings(Builder builder) {
        this.ratingsRestricted = builder.ratingsRestricted;
        this.client = (Client) Preconditions.checkNotNull(builder.client, "Unexpected null field: client");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntlClientParentalControlsSettings)) {
            return false;
        }
        IntlClientParentalControlsSettings intlClientParentalControlsSettings = (IntlClientParentalControlsSettings) obj;
        return Objects.equal(Boolean.valueOf(this.ratingsRestricted), Boolean.valueOf(intlClientParentalControlsSettings.ratingsRestricted)) && Objects.equal(this.client, intlClientParentalControlsSettings.client);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.ratingsRestricted), this.client);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("ratingsRestricted", this.ratingsRestricted).add(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, this.client).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Client client;
        public boolean ratingsRestricted;

        public IntlClientParentalControlsSettings build() {
            return new IntlClientParentalControlsSettings(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<IntlClientParentalControlsSettings> {
        private final Client.Parser mClientParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientParser = new Client.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public IntlClientParentalControlsSettings parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlClientParentalControlsSettings:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public IntlClientParentalControlsSettings parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlClientParentalControlsSettings:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private IntlClientParentalControlsSettings parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "IntlClientParentalControlsSettings");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1357712437) {
                        if (iHashCode == 1615809393 && next.equals("ratingsRestricted")) {
                            b = 0;
                        }
                    } else if (next.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                        b = 1;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.client = jsonNode2.isNull() ? null : this.mClientParser.parse(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field ratingsRestricted can't be null");
                        }
                        builder.ratingsRestricted = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing IntlClientParentalControlsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.ratingsRestricted), this, "ratingsRestricted");
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            return builder.build();
        }

        @Nonnull
        private IntlClientParentalControlsSettings parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1357712437) {
                                if (iHashCode == 1615809393 && currentName.equals("ratingsRestricted")) {
                                    b = 0;
                                }
                            } else if (currentName.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                b = 1;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.client = currentToken == JsonToken.VALUE_NULL ? null : this.mClientParser.parse(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field ratingsRestricted can't be null");
                                }
                                builder.ratingsRestricted = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing IntlClientParentalControlsSettings so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.ratingsRestricted), this, "ratingsRestricted");
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    return builder.build();
                }
            }
        }
    }
}
