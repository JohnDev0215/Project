package com.amazon.atv.parentalcontrols;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class PinChallengeDetails {
    public final Optional<PinChallengeDetails> alternativeChallenge;
    public final String localisedMessage;
    public final int pinLength;
    public final Optional<String> pinProfileACI;
    public final TitleRestrictionType type;

    private PinChallengeDetails(Builder builder) {
        this.type = (TitleRestrictionType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.alternativeChallenge = Optional.fromNullable(builder.alternativeChallenge);
        this.pinProfileACI = Optional.fromNullable(builder.pinProfileACI);
        this.pinLength = builder.pinLength;
        this.localisedMessage = (String) Preconditions.checkNotNull(builder.localisedMessage, "Unexpected null field: localisedMessage");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PinChallengeDetails)) {
            return false;
        }
        PinChallengeDetails pinChallengeDetails = (PinChallengeDetails) obj;
        return Objects.equal(this.type, pinChallengeDetails.type) && Objects.equal(this.alternativeChallenge, pinChallengeDetails.alternativeChallenge) && Objects.equal(this.pinProfileACI, pinChallengeDetails.pinProfileACI) && Objects.equal(Integer.valueOf(this.pinLength), Integer.valueOf(pinChallengeDetails.pinLength)) && Objects.equal(this.localisedMessage, pinChallengeDetails.localisedMessage);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.alternativeChallenge, this.pinProfileACI, Integer.valueOf(this.pinLength), this.localisedMessage);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("alternativeChallenge", this.alternativeChallenge).add("pinProfileACI", this.pinProfileACI).add("pinLength", this.pinLength).add("localisedMessage", this.localisedMessage).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinChallengeDetails alternativeChallenge;
        public String localisedMessage;
        public int pinLength;
        public String pinProfileACI;
        public TitleRestrictionType type;

        public PinChallengeDetails build() {
            return new PinChallengeDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PinChallengeDetails> {
        private final SimpleParsers.StringParser mlocalisedMessageParser;
        private final SimpleParsers.StringParser mpinProfileACIParser;
        private final EnumParser<TitleRestrictionType> mtitleRestrictionTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mtitleRestrictionTypeParser = EnumParser.newParser(TitleRestrictionType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mpinProfileACIParser = stringParser;
            this.mlocalisedMessageParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PinChallengeDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinChallengeDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PinChallengeDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinChallengeDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PinChallengeDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PinChallengeDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1796412365:
                            if (next.equals("localisedMessage")) {
                                b = 4;
                            }
                            break;
                        case -1332937325:
                            if (next.equals("pinProfileACI")) {
                                b = 2;
                            }
                            break;
                        case -1146317605:
                            if (next.equals("pinLength")) {
                                b = 3;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 0;
                            }
                            break;
                        case 440715062:
                            if (next.equals("alternativeChallenge")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    TitleRestrictionType titleRestrictionType = null;
                    PinChallengeDetails pinChallengeDetails = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            titleRestrictionType = (TitleRestrictionType) this.mtitleRestrictionTypeParser.parse(jsonNode2);
                        }
                        builder.type = titleRestrictionType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = parse(jsonNode2);
                        }
                        builder.alternativeChallenge = pinChallengeDetails;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mpinProfileACIParser.parse(jsonNode2);
                        }
                        builder.pinProfileACI = str2;
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field pinLength can't be null");
                        }
                        builder.pinLength = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mlocalisedMessageParser.parse(jsonNode2);
                        }
                        builder.localisedMessage = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PinChallengeDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.localisedMessage, this, "localisedMessage");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PinChallengeDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1796412365:
                                    if (currentName.equals("localisedMessage")) {
                                        b = 4;
                                    }
                                    break;
                                case -1332937325:
                                    if (currentName.equals("pinProfileACI")) {
                                        b = 2;
                                    }
                                    break;
                                case -1146317605:
                                    if (currentName.equals("pinLength")) {
                                        b = 3;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 0;
                                    }
                                    break;
                                case 440715062:
                                    if (currentName.equals("alternativeChallenge")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            String str = null;
                            TitleRestrictionType titleRestrictionType = null;
                            PinChallengeDetails pinChallengeDetails = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleRestrictionType = (TitleRestrictionType) this.mtitleRestrictionTypeParser.parse(jsonParser);
                                }
                                builder.type = titleRestrictionType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = parse(jsonParser);
                                }
                                builder.alternativeChallenge = pinChallengeDetails;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mpinProfileACIParser.parse(jsonParser);
                                }
                                builder.pinProfileACI = str2;
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field pinLength can't be null");
                                }
                                builder.pinLength = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlocalisedMessageParser.parse(jsonParser);
                                }
                                builder.localisedMessage = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PinChallengeDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.localisedMessage, this, "localisedMessage");
                    return builder.build();
                }
            }
        }
    }
}
