package com.amazon.atv.playbackauthority.service.model;

import com.amazon.atv.playbackauthority.service.NotConsumableReasonType;
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
public class NotConsumableReason {
    public final Optional<String> downstreamReason;
    public final String message;
    public final NotConsumableReasonType type;

    private NotConsumableReason(Builder builder) {
        this.downstreamReason = Optional.fromNullable(builder.downstreamReason);
        this.message = (String) Preconditions.checkNotNull(builder.message, "Unexpected null field: message");
        this.type = (NotConsumableReasonType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NotConsumableReason)) {
            return false;
        }
        NotConsumableReason notConsumableReason = (NotConsumableReason) obj;
        return Objects.equal(this.downstreamReason, notConsumableReason.downstreamReason) && Objects.equal(this.message, notConsumableReason.message) && Objects.equal(this.type, notConsumableReason.type);
    }

    public int hashCode() {
        return Objects.hashCode(this.downstreamReason, this.message, this.type);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("downstreamReason", this.downstreamReason).add("message", this.message).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String downstreamReason;
        public String message;
        public NotConsumableReasonType type;

        public NotConsumableReason build() {
            return new NotConsumableReason(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NotConsumableReason> {
        private final EnumParser<NotConsumableReasonType> mNotConsumableReasonTypeParser;
        private final SimpleParsers.StringParser mdownstreamReasonStringParser;
        private final SimpleParsers.StringParser mmessageStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNotConsumableReasonTypeParser = EnumParser.newParser(NotConsumableReasonType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mdownstreamReasonStringParser = stringParser;
            this.mmessageStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NotConsumableReason parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NotConsumableReason:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NotConsumableReason parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NotConsumableReason:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private NotConsumableReason parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "NotConsumableReason");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3575610) {
                        if (iHashCode != 900296582) {
                            if (iHashCode == 954925063 && next.equals("message")) {
                                b = 1;
                            }
                        } else if (next.equals("downstreamReason")) {
                            b = 0;
                        }
                    } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                        b = 2;
                    }
                    NotConsumableReasonType notConsumableReasonType = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mdownstreamReasonStringParser.parse(jsonNode2);
                        }
                        builder.downstreamReason = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mmessageStringParser.parse(jsonNode2);
                        }
                        builder.message = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            notConsumableReasonType = (NotConsumableReasonType) this.mNotConsumableReasonTypeParser.parse(jsonNode2);
                        }
                        builder.type = notConsumableReasonType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NotConsumableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.message, this, "message");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        @Nonnull
        private NotConsumableReason parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3575610) {
                                if (iHashCode != 900296582) {
                                    if (iHashCode == 954925063 && currentName.equals("message")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("downstreamReason")) {
                                    b = 0;
                                }
                            } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                            NotConsumableReasonType notConsumableReasonType = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mdownstreamReasonStringParser.parse(jsonParser);
                                }
                                builder.downstreamReason = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mmessageStringParser.parse(jsonParser);
                                }
                                builder.message = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    notConsumableReasonType = (NotConsumableReasonType) this.mNotConsumableReasonTypeParser.parse(jsonParser);
                                }
                                builder.type = notConsumableReasonType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NotConsumableReason so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.message, this, "message");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
