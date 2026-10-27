package com.amazon.av.clientdownloadservice;

import androidx.core.app.NotificationCompat;
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
public class responseStatus {
    public final Optional<ErrorCodeType> errorCode;
    public final Optional<String> notification;
    public final Optional<ResponseStatusType> status;

    private responseStatus(Builder builder) {
        this.notification = Optional.fromNullable(builder.notification);
        this.errorCode = Optional.fromNullable(builder.errorCode);
        this.status = Optional.fromNullable(builder.status);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof responseStatus)) {
            return false;
        }
        responseStatus responsestatus = (responseStatus) obj;
        return Objects.equal(this.notification, responsestatus.notification) && Objects.equal(this.errorCode, responsestatus.errorCode) && Objects.equal(this.status, responsestatus.status);
    }

    public int hashCode() {
        return Objects.hashCode(this.notification, this.errorCode, this.status);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("notification", this.notification).add("errorCode", this.errorCode).add(NotificationCompat.CATEGORY_STATUS, this.status).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ErrorCodeType errorCode;
        public String notification;
        public ResponseStatusType status;

        public responseStatus build() {
            return new responseStatus(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<responseStatus> {
        private final EnumParser<ErrorCodeType> merrorCodeTypeParser;
        private final EnumParser<ResponseStatusType> mresponseStatusTypeParser;
        private final SimpleParsers.StringParser mstringTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mresponseStatusTypeParser = EnumParser.newParser(ResponseStatusType.class);
            this.merrorCodeTypeParser = EnumParser.newParser(ErrorCodeType.class);
            this.mstringTypeParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public responseStatus parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("responseStatus:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public responseStatus parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("responseStatus:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private responseStatus parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "responseStatus");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -892481550) {
                        if (iHashCode != 329035797) {
                            if (iHashCode == 595233003 && next.equals("notification")) {
                                b = 0;
                            }
                        } else if (next.equals("errorCode")) {
                            b = 1;
                        }
                    } else if (next.equals(NotificationCompat.CATEGORY_STATUS)) {
                        b = 2;
                    }
                    ResponseStatusType responseStatusType = null;
                    String str = null;
                    ErrorCodeType errorCodeType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.notification = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            errorCodeType = (ErrorCodeType) this.merrorCodeTypeParser.parse(jsonNode2);
                        }
                        builder.errorCode = errorCodeType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            responseStatusType = (ResponseStatusType) this.mresponseStatusTypeParser.parse(jsonNode2);
                        }
                        builder.status = responseStatusType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing responseStatus so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private responseStatus parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -892481550) {
                                if (iHashCode != 329035797) {
                                    if (iHashCode == 595233003 && currentName.equals("notification")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("errorCode")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(NotificationCompat.CATEGORY_STATUS)) {
                                b = 2;
                            }
                            ResponseStatusType responseStatusType = null;
                            String str = null;
                            ErrorCodeType errorCodeType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.notification = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorCodeType = (ErrorCodeType) this.merrorCodeTypeParser.parse(jsonParser);
                                }
                                builder.errorCode = errorCodeType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    responseStatusType = (ResponseStatusType) this.mresponseStatusTypeParser.parse(jsonParser);
                                }
                                builder.status = responseStatusType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing responseStatus so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
