package com.amazon.atv.purchase;

import com.amazon.atv.purchase.activity.ErrorCode;
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
public class CdpError {
    public final Optional<ErrorCode> code;
    public final Optional<String> debugMessage;
    public final Optional<String> message;

    private CdpError(Builder builder) {
        this.debugMessage = Optional.fromNullable(builder.debugMessage);
        this.code = Optional.fromNullable(builder.code);
        this.message = Optional.fromNullable(builder.message);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CdpError)) {
            return false;
        }
        CdpError cdpError = (CdpError) obj;
        return Objects.equal(this.debugMessage, cdpError.debugMessage) && Objects.equal(this.code, cdpError.code) && Objects.equal(this.message, cdpError.message);
    }

    public int hashCode() {
        return Objects.hashCode(this.debugMessage, this.code, this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("debugMessage", this.debugMessage).add("code", this.code).add("message", this.message).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ErrorCode code;
        public String debugMessage;
        public String message;

        public CdpError build() {
            return new CdpError(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdpError> {
        private final EnumParser<ErrorCode> mcdpErrorCodeParser;
        private final SimpleParsers.StringParser merrorMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.merrorMessageParser = SimpleParsers.StringParser.INSTANCE;
            this.mcdpErrorCodeParser = EnumParser.newParser(ErrorCode.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdpError parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpError:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdpError parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdpError:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CdpError parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdpError");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3059181) {
                        if (iHashCode != 954925063) {
                            if (iHashCode == 1858063924 && next.equals("debugMessage")) {
                                b = 0;
                            }
                        } else if (next.equals("message")) {
                            b = 2;
                        }
                    } else if (next.equals("code")) {
                        b = 1;
                    }
                    String str = null;
                    String str2 = null;
                    ErrorCode errorCode = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.merrorMessageParser.parse(jsonNode2);
                        }
                        builder.debugMessage = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            errorCode = (ErrorCode) this.mcdpErrorCodeParser.parse(jsonNode2);
                        }
                        builder.code = errorCode;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.merrorMessageParser.parse(jsonNode2);
                        }
                        builder.message = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdpError so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CdpError parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3059181) {
                                if (iHashCode != 954925063) {
                                    if (iHashCode == 1858063924 && currentName.equals("debugMessage")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("message")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("code")) {
                                b = 1;
                            }
                            String str = null;
                            String str2 = null;
                            ErrorCode errorCode = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.merrorMessageParser.parse(jsonParser);
                                }
                                builder.debugMessage = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorCode = (ErrorCode) this.mcdpErrorCodeParser.parse(jsonParser);
                                }
                                builder.code = errorCode;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.merrorMessageParser.parse(jsonParser);
                                }
                                builder.message = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdpError so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
