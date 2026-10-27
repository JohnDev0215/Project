package com.amazon.av.clientdownloadservice;

import androidx.core.app.NotificationCompat;
import com.amazon.av.clientdownloadservice.OnDeviceDownloadActionData;
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
public class OnDeviceDownloadAction {
    public final Optional<String> action;
    public final Optional<OnDeviceDownloadActionData> actionData;
    public final Optional<ErrorCodeType> errorCode;
    public final Optional<String> status;
    public final Optional<String> titleId;

    private OnDeviceDownloadAction(Builder builder) {
        this.status = Optional.fromNullable(builder.status);
        this.actionData = Optional.fromNullable(builder.actionData);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.errorCode = Optional.fromNullable(builder.errorCode);
        this.action = Optional.fromNullable(builder.action);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OnDeviceDownloadAction)) {
            return false;
        }
        OnDeviceDownloadAction onDeviceDownloadAction = (OnDeviceDownloadAction) obj;
        return Objects.equal(this.status, onDeviceDownloadAction.status) && Objects.equal(this.actionData, onDeviceDownloadAction.actionData) && Objects.equal(this.titleId, onDeviceDownloadAction.titleId) && Objects.equal(this.errorCode, onDeviceDownloadAction.errorCode) && Objects.equal(this.action, onDeviceDownloadAction.action);
    }

    public int hashCode() {
        return Objects.hashCode(this.status, this.actionData, this.titleId, this.errorCode, this.action);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.status).add("actionData", this.actionData).add("titleId", this.titleId).add("errorCode", this.errorCode).add("action", this.action).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String action;
        public OnDeviceDownloadActionData actionData;
        public ErrorCodeType errorCode;
        public String status;
        public String titleId;

        public OnDeviceDownloadAction build() {
            return new OnDeviceDownloadAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<OnDeviceDownloadAction> {
        private final OnDeviceDownloadActionData.Parser mOnDeviceDownloadActionDataParser;
        private final EnumParser<ErrorCodeType> merrorCodeTypeParser;
        private final SimpleParsers.StringParser mstringTypeParser;
        private final SimpleParsers.StringParser mtitleIdentifierTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.merrorCodeTypeParser = EnumParser.newParser(ErrorCodeType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mstringTypeParser = stringParser;
            this.mtitleIdentifierTypeParser = stringParser;
            this.mOnDeviceDownloadActionDataParser = new OnDeviceDownloadActionData.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public OnDeviceDownloadAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownloadAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public OnDeviceDownloadAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownloadAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private OnDeviceDownloadAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "OnDeviceDownloadAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1422950858:
                            if (next.equals("action")) {
                                b = 4;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 2;
                            }
                            break;
                        case -892481550:
                            if (next.equals(NotificationCompat.CATEGORY_STATUS)) {
                                b = 0;
                            }
                            break;
                        case 329035797:
                            if (next.equals("errorCode")) {
                                b = 3;
                            }
                            break;
                        case 1851381504:
                            if (next.equals("actionData")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    OnDeviceDownloadActionData onDeviceDownloadActionData = null;
                    String str3 = null;
                    ErrorCodeType errorCodeType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.status = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            onDeviceDownloadActionData = this.mOnDeviceDownloadActionDataParser.parse(jsonNode2);
                        }
                        builder.actionData = onDeviceDownloadActionData;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mtitleIdentifierTypeParser.parse(jsonNode2);
                        }
                        builder.titleId = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            errorCodeType = (ErrorCodeType) this.merrorCodeTypeParser.parse(jsonNode2);
                        }
                        builder.errorCode = errorCodeType;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.action = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OnDeviceDownloadAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private OnDeviceDownloadAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1422950858:
                                    if (currentName.equals("action")) {
                                        b = 4;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 2;
                                    }
                                    break;
                                case -892481550:
                                    if (currentName.equals(NotificationCompat.CATEGORY_STATUS)) {
                                        b = 0;
                                    }
                                    break;
                                case 329035797:
                                    if (currentName.equals("errorCode")) {
                                        b = 3;
                                    }
                                    break;
                                case 1851381504:
                                    if (currentName.equals("actionData")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            OnDeviceDownloadActionData onDeviceDownloadActionData = null;
                            String str3 = null;
                            ErrorCodeType errorCodeType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.status = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    onDeviceDownloadActionData = this.mOnDeviceDownloadActionDataParser.parse(jsonParser);
                                }
                                builder.actionData = onDeviceDownloadActionData;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mtitleIdentifierTypeParser.parse(jsonParser);
                                }
                                builder.titleId = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorCodeType = (ErrorCodeType) this.merrorCodeTypeParser.parse(jsonParser);
                                }
                                builder.errorCode = errorCodeType;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.action = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OnDeviceDownloadAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
