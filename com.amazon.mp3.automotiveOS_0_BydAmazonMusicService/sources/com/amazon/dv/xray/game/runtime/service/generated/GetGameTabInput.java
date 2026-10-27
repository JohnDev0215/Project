package com.amazon.dv.xray.game.runtime.service.generated;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.dv.xray.game.runtime.service.shared.GetGameTabInputPayload;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class GetGameTabInput {
    public final String locale;
    public final GetGameTabInputPayload payload;

    private GetGameTabInput(Builder builder) {
        this.payload = (GetGameTabInputPayload) Preconditions.checkNotNull(builder.payload, "Unexpected null field: payload");
        this.locale = (String) Preconditions.checkNotNull(builder.locale, "Unexpected null field: locale");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGameTabInput)) {
            return false;
        }
        GetGameTabInput getGameTabInput = (GetGameTabInput) obj;
        return Objects.equal(this.payload, getGameTabInput.payload) && Objects.equal(this.locale, getGameTabInput.locale);
    }

    public int hashCode() {
        return Objects.hashCode(this.payload, this.locale);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("payload", this.payload).add(Splash.PARAMS_LOCALE, this.locale).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String locale;
        public GetGameTabInputPayload payload;

        public GetGameTabInput build() {
            return new GetGameTabInput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetGameTabInput> {
        private final GetGameTabInputPayload.Parser mGetGameTabInputPayloadParser;
        private final SimpleParsers.StringParser mStringValueParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringValueParser = SimpleParsers.StringParser.INSTANCE;
            this.mGetGameTabInputPayloadParser = new GetGameTabInputPayload.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetGameTabInput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetGameTabInput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetGameTabInput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetGameTabInput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetGameTabInput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1097462182) {
                        if (iHashCode == -786701938 && next.equals("payload")) {
                            b = 0;
                        }
                    } else if (next.equals(Splash.PARAMS_LOCALE)) {
                        b = 1;
                    }
                    String str = null;
                    GetGameTabInputPayload getGameTabInputPayload = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            getGameTabInputPayload = this.mGetGameTabInputPayloadParser.parse(jsonNode2);
                        }
                        builder.payload = getGameTabInputPayload;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringValueParser.parse(jsonNode2);
                        }
                        builder.locale = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetGameTabInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
            JsonParsingUtils.checkNotNull(builder.locale, this, Splash.PARAMS_LOCALE);
            return builder.build();
        }

        @Nonnull
        private GetGameTabInput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode == -786701938 && currentName.equals("payload")) {
                                    b = 0;
                                }
                            } else if (currentName.equals(Splash.PARAMS_LOCALE)) {
                                b = 1;
                            }
                            String str = null;
                            GetGameTabInputPayload getGameTabInputPayload = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getGameTabInputPayload = this.mGetGameTabInputPayloadParser.parse(jsonParser);
                                }
                                builder.payload = getGameTabInputPayload;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringValueParser.parse(jsonParser);
                                }
                                builder.locale = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetGameTabInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.payload, this, "payload");
                    JsonParsingUtils.checkNotNull(builder.locale, this, Splash.PARAMS_LOCALE);
                    return builder.build();
                }
            }
        }
    }
}
