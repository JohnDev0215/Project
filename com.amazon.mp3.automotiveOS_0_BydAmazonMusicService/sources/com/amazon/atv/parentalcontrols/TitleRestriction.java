package com.amazon.atv.parentalcontrols;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class TitleRestriction {
    public final RestrictableAction action;
    public final RestrictionReason reason;
    public final TitleRestrictionType type;

    private TitleRestriction(Builder builder) {
        this.reason = (RestrictionReason) Preconditions.checkNotNull(builder.reason, "Unexpected null field: reason");
        this.action = (RestrictableAction) Preconditions.checkNotNull(builder.action, "Unexpected null field: action");
        this.type = (TitleRestrictionType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleRestriction)) {
            return false;
        }
        TitleRestriction titleRestriction = (TitleRestriction) obj;
        return Objects.equal(this.reason, titleRestriction.reason) && Objects.equal(this.action, titleRestriction.action) && Objects.equal(this.type, titleRestriction.type);
    }

    public int hashCode() {
        return Objects.hashCode(this.reason, this.action, this.type);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("reason", this.reason).add("action", this.action).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public RestrictableAction action;
        public RestrictionReason reason;
        public TitleRestrictionType type;

        public TitleRestriction build() {
            return new TitleRestriction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleRestriction> {
        private final EnumParser<RestrictableAction> mrestrictableActionParser;
        private final EnumParser<RestrictionReason> mrestrictionReasonParser;
        private final EnumParser<TitleRestrictionType> mtitleRestrictionTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mrestrictionReasonParser = EnumParser.newParser(RestrictionReason.class);
            this.mtitleRestrictionTypeParser = EnumParser.newParser(TitleRestrictionType.class);
            this.mrestrictableActionParser = EnumParser.newParser(RestrictableAction.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleRestriction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleRestriction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleRestriction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleRestriction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TitleRestriction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleRestriction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1422950858) {
                        if (iHashCode != -934964668) {
                            if (iHashCode == 3575610 && next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                        } else if (next.equals("reason")) {
                            b = 0;
                        }
                    } else if (next.equals("action")) {
                        b = 1;
                    }
                    TitleRestrictionType titleRestrictionType = null;
                    RestrictionReason restrictionReason = null;
                    RestrictableAction restrictableAction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            restrictionReason = (RestrictionReason) this.mrestrictionReasonParser.parse(jsonNode2);
                        }
                        builder.reason = restrictionReason;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            restrictableAction = (RestrictableAction) this.mrestrictableActionParser.parse(jsonNode2);
                        }
                        builder.action = restrictableAction;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            titleRestrictionType = (TitleRestrictionType) this.mtitleRestrictionTypeParser.parse(jsonNode2);
                        }
                        builder.type = titleRestrictionType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.reason, this, "reason");
            JsonParsingUtils.checkNotNull(builder.action, this, "action");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        @Nonnull
        private TitleRestriction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1422950858) {
                                if (iHashCode != -934964668) {
                                    if (iHashCode == 3575610 && currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("reason")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("action")) {
                                b = 1;
                            }
                            TitleRestrictionType titleRestrictionType = null;
                            RestrictionReason restrictionReason = null;
                            RestrictableAction restrictableAction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    restrictionReason = (RestrictionReason) this.mrestrictionReasonParser.parse(jsonParser);
                                }
                                builder.reason = restrictionReason;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    restrictableAction = (RestrictableAction) this.mrestrictableActionParser.parse(jsonParser);
                                }
                                builder.action = restrictableAction;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleRestrictionType = (TitleRestrictionType) this.mtitleRestrictionTypeParser.parse(jsonParser);
                                }
                                builder.type = titleRestrictionType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleRestriction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.reason, this, "reason");
                    JsonParsingUtils.checkNotNull(builder.action, this, "action");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
