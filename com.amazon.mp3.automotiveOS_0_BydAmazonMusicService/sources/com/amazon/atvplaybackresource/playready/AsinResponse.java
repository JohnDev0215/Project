package com.amazon.atvplaybackresource.playready;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class AsinResponse {
    public final Optional<String> asin;
    public final Optional<String> message;
    public final Optional<String> playReadyKeyId;
    public final Optional<String> secretServiceKeyId;
    public final Optional<StatusCode> statusCode;

    private AsinResponse(Builder builder) {
        this.playReadyKeyId = Optional.fromNullable(builder.playReadyKeyId);
        this.secretServiceKeyId = Optional.fromNullable(builder.secretServiceKeyId);
        this.asin = Optional.fromNullable(builder.asin);
        this.message = Optional.fromNullable(builder.message);
        this.statusCode = Optional.fromNullable(builder.statusCode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AsinResponse)) {
            return false;
        }
        AsinResponse asinResponse = (AsinResponse) obj;
        return Objects.equal(this.playReadyKeyId, asinResponse.playReadyKeyId) && Objects.equal(this.secretServiceKeyId, asinResponse.secretServiceKeyId) && Objects.equal(this.asin, asinResponse.asin) && Objects.equal(this.message, asinResponse.message) && Objects.equal(this.statusCode, asinResponse.statusCode);
    }

    public int hashCode() {
        return Objects.hashCode(this.playReadyKeyId, this.secretServiceKeyId, this.asin, this.message, this.statusCode);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playReadyKeyId", this.playReadyKeyId).add("secretServiceKeyId", this.secretServiceKeyId).add("asin", this.asin).add("message", this.message).add("statusCode", this.statusCode).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String asin;
        public String message;
        public String playReadyKeyId;
        public String secretServiceKeyId;
        public StatusCode statusCode;

        public AsinResponse build() {
            return new AsinResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AsinResponse> {
        private final SimpleParsers.StringParser masinParser;
        private final SimpleParsers.StringParser mkeyIdParser;
        private final SimpleParsers.StringParser mmessageParser;
        private final EnumParser<StatusCode> mstatusCodeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstatusCodeParser = EnumParser.newParser(StatusCode.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mkeyIdParser = stringParser;
            this.masinParser = stringParser;
            this.mmessageParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AsinResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AsinResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AsinResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AsinResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AsinResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AsinResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1381246731:
                            if (next.equals("secretServiceKeyId")) {
                                b = 1;
                            }
                            break;
                        case 3003607:
                            if (next.equals("asin")) {
                                b = 2;
                            }
                            break;
                        case 247507199:
                            if (next.equals("statusCode")) {
                                b = 4;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 3;
                            }
                            break;
                        case 1380533835:
                            if (next.equals("playReadyKeyId")) {
                                b = 0;
                            }
                            break;
                    }
                    StatusCode statusCode = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mkeyIdParser.parse(jsonNode2);
                        }
                        builder.playReadyKeyId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mkeyIdParser.parse(jsonNode2);
                        }
                        builder.secretServiceKeyId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.masinParser.parse(jsonNode2);
                        }
                        builder.asin = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str4 = this.mmessageParser.parse(jsonNode2);
                        }
                        builder.message = str4;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            statusCode = (StatusCode) this.mstatusCodeParser.parse(jsonNode2);
                        }
                        builder.statusCode = statusCode;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AsinResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AsinResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1381246731:
                                    if (currentName.equals("secretServiceKeyId")) {
                                        b = 1;
                                    }
                                    break;
                                case 3003607:
                                    if (currentName.equals("asin")) {
                                        b = 2;
                                    }
                                    break;
                                case 247507199:
                                    if (currentName.equals("statusCode")) {
                                        b = 4;
                                    }
                                    break;
                                case 954925063:
                                    if (currentName.equals("message")) {
                                        b = 3;
                                    }
                                    break;
                                case 1380533835:
                                    if (currentName.equals("playReadyKeyId")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            StatusCode statusCode = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mkeyIdParser.parse(jsonParser);
                                }
                                builder.playReadyKeyId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mkeyIdParser.parse(jsonParser);
                                }
                                builder.secretServiceKeyId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.masinParser.parse(jsonParser);
                                }
                                builder.asin = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str4 = this.mmessageParser.parse(jsonParser);
                                }
                                builder.message = str4;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    statusCode = (StatusCode) this.mstatusCodeParser.parse(jsonParser);
                                }
                                builder.statusCode = statusCode;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AsinResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
