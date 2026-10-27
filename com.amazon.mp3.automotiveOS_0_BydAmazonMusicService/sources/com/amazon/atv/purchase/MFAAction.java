package com.amazon.atv.purchase;

import com.amazon.atv.purchase.LocalizedMessage;
import com.amazon.atv.purchase.activity.MFAActionHandler;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class MFAAction {
    public final Optional<String> challengeURI;
    public final MFAActionHandler handler;
    public final Optional<LocalizedMessage> message;
    public final Optional<String> paymentPlanId;
    public final Optional<LocalizedMessage> title;

    private MFAAction(Builder builder) {
        this.paymentPlanId = Optional.fromNullable(builder.paymentPlanId);
        this.challengeURI = Optional.fromNullable(builder.challengeURI);
        this.message = Optional.fromNullable(builder.message);
        this.title = Optional.fromNullable(builder.title);
        this.handler = (MFAActionHandler) Preconditions.checkNotNull(builder.handler, "Unexpected null field: handler");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MFAAction)) {
            return false;
        }
        MFAAction mFAAction = (MFAAction) obj;
        return Objects.equal(this.paymentPlanId, mFAAction.paymentPlanId) && Objects.equal(this.challengeURI, mFAAction.challengeURI) && Objects.equal(this.message, mFAAction.message) && Objects.equal(this.title, mFAAction.title) && Objects.equal(this.handler, mFAAction.handler);
    }

    public int hashCode() {
        return Objects.hashCode(this.paymentPlanId, this.challengeURI, this.message, this.title, this.handler);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("paymentPlanId", this.paymentPlanId).add("challengeURI", this.challengeURI).add("message", this.message).add("title", this.title).add("handler", this.handler).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String challengeURI;
        public MFAActionHandler handler;
        public LocalizedMessage message;
        public String paymentPlanId;
        public LocalizedMessage title;

        public MFAAction build() {
            return new MFAAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<MFAAction> {
        private final LocalizedMessage.Parser mLocalizedMessageParser;
        private final EnumParser<MFAActionHandler> mMFAActionHandlerParser;
        private final SimpleParsers.StringParser mchallengeURIParser;
        private final SimpleParsers.StringParser mpaymentPlanIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLocalizedMessageParser = new LocalizedMessage.Parser(objectMapper);
            this.mMFAActionHandlerParser = EnumParser.newParser(MFAActionHandler.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mpaymentPlanIdParser = stringParser;
            this.mchallengeURIParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public MFAAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MFAAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public MFAAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MFAAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MFAAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "MFAAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 110371416:
                            if (next.equals("title")) {
                                b = 3;
                            }
                            break;
                        case 112348681:
                            if (next.equals("challengeURI")) {
                                b = 1;
                            }
                            break;
                        case 692803402:
                            if (next.equals("handler")) {
                                b = 4;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 2;
                            }
                            break;
                        case 1337421770:
                            if (next.equals("paymentPlanId")) {
                                b = 0;
                            }
                            break;
                    }
                    MFAActionHandler mFAActionHandler = null;
                    String str = null;
                    String str2 = null;
                    LocalizedMessage localizedMessage = null;
                    LocalizedMessage localizedMessage2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mpaymentPlanIdParser.parse(jsonNode2);
                        }
                        builder.paymentPlanId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mchallengeURIParser.parse(jsonNode2);
                        }
                        builder.challengeURI = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            localizedMessage = this.mLocalizedMessageParser.parse(jsonNode2);
                        }
                        builder.message = localizedMessage;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            localizedMessage2 = this.mLocalizedMessageParser.parse(jsonNode2);
                        }
                        builder.title = localizedMessage2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            mFAActionHandler = (MFAActionHandler) this.mMFAActionHandlerParser.parse(jsonNode2);
                        }
                        builder.handler = mFAActionHandler;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing MFAAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.handler, this, "handler");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MFAAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 3;
                                    }
                                    break;
                                case 112348681:
                                    if (currentName.equals("challengeURI")) {
                                        b = 1;
                                    }
                                    break;
                                case 692803402:
                                    if (currentName.equals("handler")) {
                                        b = 4;
                                    }
                                    break;
                                case 954925063:
                                    if (currentName.equals("message")) {
                                        b = 2;
                                    }
                                    break;
                                case 1337421770:
                                    if (currentName.equals("paymentPlanId")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            MFAActionHandler mFAActionHandler = null;
                            String str = null;
                            String str2 = null;
                            LocalizedMessage localizedMessage = null;
                            LocalizedMessage localizedMessage2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mpaymentPlanIdParser.parse(jsonParser);
                                }
                                builder.paymentPlanId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mchallengeURIParser.parse(jsonParser);
                                }
                                builder.challengeURI = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedMessage = this.mLocalizedMessageParser.parse(jsonParser);
                                }
                                builder.message = localizedMessage;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedMessage2 = this.mLocalizedMessageParser.parse(jsonParser);
                                }
                                builder.title = localizedMessage2;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    mFAActionHandler = (MFAActionHandler) this.mMFAActionHandlerParser.parse(jsonParser);
                                }
                                builder.handler = mFAActionHandler;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing MFAAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.handler, this, "handler");
                    return builder.build();
                }
            }
        }
    }
}
