package com.amazon.atv.purchase;

import com.amazon.atv.purchase.activity.ErrorActionHandler;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorAction {
    public final Optional<String> formattedMessage;
    public final Optional<String> formattedTitle;
    public final ErrorActionHandler handler;
    public final Optional<ImmutableMap<String, String>> messageArgs;
    public final Optional<String> messageId;
    public final Optional<ImmutableMap<String, String>> titleArgs;
    public final Optional<String> titleId;

    private ErrorAction(Builder builder) {
        this.formattedMessage = Optional.fromNullable(builder.formattedMessage);
        this.formattedTitle = Optional.fromNullable(builder.formattedTitle);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.messageId = Optional.fromNullable(builder.messageId);
        this.titleArgs = Optional.fromNullable(builder.titleArgs);
        this.handler = (ErrorActionHandler) Preconditions.checkNotNull(builder.handler, "Unexpected null field: handler");
        this.messageArgs = Optional.fromNullable(builder.messageArgs);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErrorAction)) {
            return false;
        }
        ErrorAction errorAction = (ErrorAction) obj;
        return Objects.equal(this.formattedMessage, errorAction.formattedMessage) && Objects.equal(this.formattedTitle, errorAction.formattedTitle) && Objects.equal(this.titleId, errorAction.titleId) && Objects.equal(this.messageId, errorAction.messageId) && Objects.equal(this.titleArgs, errorAction.titleArgs) && Objects.equal(this.handler, errorAction.handler) && Objects.equal(this.messageArgs, errorAction.messageArgs);
    }

    public int hashCode() {
        return Objects.hashCode(this.formattedMessage, this.formattedTitle, this.titleId, this.messageId, this.titleArgs, this.handler, this.messageArgs);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("formattedMessage", this.formattedMessage).add("formattedTitle", this.formattedTitle).add("titleId", this.titleId).add("messageId", this.messageId).add("titleArgs", this.titleArgs).add("handler", this.handler).add("messageArgs", this.messageArgs).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String formattedMessage;
        public String formattedTitle;
        public ErrorActionHandler handler;
        public ImmutableMap<String, String> messageArgs;
        public String messageId;
        public ImmutableMap<String, String> titleArgs;
        public String titleId;

        public ErrorAction build() {
            return new ErrorAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ErrorAction> {
        private final EnumParser<ErrorActionHandler> mErrorActionHandlerParser;
        private final MapParser<String, String> mErrorMessageArgsParser;
        private final SimpleParsers.StringParser mErrorMessageIdParser;
        private final SimpleParsers.StringParser mFormattedErrorMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mErrorActionHandlerParser = EnumParser.newParser(ErrorActionHandler.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mErrorMessageArgsParser = MapParser.newParser(stringParser, stringParser);
            this.mFormattedErrorMessageParser = stringParser;
            this.mErrorMessageIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ErrorAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ErrorAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ErrorAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ErrorAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ErrorAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            String str3;
            String str4;
            ImmutableMap<String, String> immutableMap;
            ErrorActionHandler errorActionHandler;
            ImmutableMap<String, String> immutableMap2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "ErrorAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2136310955:
                            if (next.equals("titleArgs")) {
                                b = 4;
                            }
                            break;
                        case -1697409284:
                            if (next.equals("formattedTitle")) {
                                b = 1;
                            }
                            break;
                        case -1440013438:
                            if (next.equals("messageId")) {
                                b = 3;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 2;
                            }
                            break;
                        case -1155540053:
                            if (next.equals("formattedMessage")) {
                                b = 0;
                            }
                            break;
                        case -873666172:
                            if (next.equals("messageArgs")) {
                                b = 6;
                            }
                            break;
                        case 692803402:
                            if (next.equals("handler")) {
                                b = 5;
                            }
                            break;
                    }
                    str = null;
                    immutableMap2 = null;
                    errorActionHandler = null;
                    immutableMap = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ErrorAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mFormattedErrorMessageParser.parse(jsonNode2);
                        }
                        builder.formattedMessage = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mFormattedErrorMessageParser.parse(jsonNode2);
                        }
                        builder.formattedTitle = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mErrorMessageIdParser.parse(jsonNode2);
                        }
                        builder.titleId = str3;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mErrorMessageIdParser.parse(jsonNode2);
                        }
                        builder.messageId = str4;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mErrorMessageArgsParser.parse(jsonNode2);
                        }
                        builder.titleArgs = immutableMap;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            errorActionHandler = (ErrorActionHandler) this.mErrorActionHandlerParser.parse(jsonNode2);
                        }
                        builder.handler = errorActionHandler;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mErrorMessageArgsParser.parse(jsonNode2);
                        }
                        builder.messageArgs = immutableMap2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ErrorAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.handler, this, "handler");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ErrorAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2136310955:
                                    if (currentName.equals("titleArgs")) {
                                        b = 4;
                                    }
                                    break;
                                case -1697409284:
                                    if (currentName.equals("formattedTitle")) {
                                        b = 1;
                                    }
                                    break;
                                case -1440013438:
                                    if (currentName.equals("messageId")) {
                                        b = 3;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 2;
                                    }
                                    break;
                                case -1155540053:
                                    if (currentName.equals("formattedMessage")) {
                                        b = 0;
                                    }
                                    break;
                                case -873666172:
                                    if (currentName.equals("messageArgs")) {
                                        b = 6;
                                    }
                                    break;
                                case 692803402:
                                    if (currentName.equals("handler")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableMap<String, String> immutableMap = null;
                            ErrorActionHandler errorActionHandler = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mFormattedErrorMessageParser.parse(jsonParser);
                                    }
                                    builder.formattedMessage = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mFormattedErrorMessageParser.parse(jsonParser);
                                    }
                                    builder.formattedTitle = str4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mErrorMessageIdParser.parse(jsonParser);
                                    }
                                    builder.titleId = str3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mErrorMessageIdParser.parse(jsonParser);
                                    }
                                    builder.messageId = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mErrorMessageArgsParser.parse(jsonParser);
                                    }
                                    builder.titleArgs = immutableMap2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        errorActionHandler = (ErrorActionHandler) this.mErrorActionHandlerParser.parse(jsonParser);
                                    }
                                    builder.handler = errorActionHandler;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mErrorMessageArgsParser.parse(jsonParser);
                                    }
                                    builder.messageArgs = immutableMap;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ErrorAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ErrorAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.handler, this, "handler");
                    return builder.build();
                }
            }
        }
    }
}
