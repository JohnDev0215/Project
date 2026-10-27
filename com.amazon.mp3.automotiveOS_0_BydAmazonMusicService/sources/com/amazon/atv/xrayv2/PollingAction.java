package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Action;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PollingAction extends Action {
    public final Optional<ImmutableList<Integer>> acceptableErrorCodes;
    public final long activePollingIntervalMillis;
    public final long jitter;
    public final long passivePollingIntervalMillis;
    public final Action targetAction;

    private PollingAction(Builder builder) {
        super(builder);
        this.passivePollingIntervalMillis = builder.passivePollingIntervalMillis;
        this.targetAction = (Action) Preconditions.checkNotNull(builder.targetAction, "Unexpected null field: targetAction");
        this.activePollingIntervalMillis = builder.activePollingIntervalMillis;
        this.acceptableErrorCodes = Optional.fromNullable(builder.acceptableErrorCodes);
        this.jitter = builder.jitter;
    }

    @Override // com.amazon.atv.xrayv2.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PollingAction)) {
            return false;
        }
        PollingAction pollingAction = (PollingAction) obj;
        return super.equals(obj) && Objects.equal(Long.valueOf(this.passivePollingIntervalMillis), Long.valueOf(pollingAction.passivePollingIntervalMillis)) && Objects.equal(this.targetAction, pollingAction.targetAction) && Objects.equal(Long.valueOf(this.activePollingIntervalMillis), Long.valueOf(pollingAction.activePollingIntervalMillis)) && Objects.equal(this.acceptableErrorCodes, pollingAction.acceptableErrorCodes) && Objects.equal(Long.valueOf(this.jitter), Long.valueOf(pollingAction.jitter));
    }

    @Override // com.amazon.atv.xrayv2.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Long.valueOf(this.passivePollingIntervalMillis), this.targetAction, Long.valueOf(this.activePollingIntervalMillis), this.acceptableErrorCodes, Long.valueOf(this.jitter));
    }

    @Override // com.amazon.atv.xrayv2.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).add("passivePollingIntervalMillis", this.passivePollingIntervalMillis).add("targetAction", this.targetAction).add("activePollingIntervalMillis", this.activePollingIntervalMillis).add("acceptableErrorCodes", this.acceptableErrorCodes).add("jitter", this.jitter).toString();
    }

    @NotThreadSafe
    public static class Builder extends Action.Builder {
        public ImmutableList<Integer> acceptableErrorCodes;
        public long activePollingIntervalMillis;
        public long jitter;
        public long passivePollingIntervalMillis;
        public Action targetAction;

        public PollingAction build() {
            return new PollingAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PollingAction> {
        private final Action.Parser mActionParser;
        private final EnumParser<ActionType> mActionTypeParser;
        private final Analytics.Parser mAnalyticsParser;
        private final ListParser<Integer> mIntegerListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntegerListParser = ListParser.newParser(SimpleParsers.IntegerParser.INSTANCE);
            this.mActionParser = new Action.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mActionTypeParser = EnumParser.newParser(ActionType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PollingAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PollingAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PollingAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PollingAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PollingAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PollingAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 0;
                            }
                            break;
                        case -1675779801:
                            if (next.equals("passivePollingIntervalMillis")) {
                                b = 2;
                            }
                            break;
                        case -1488616792:
                            if (next.equals("activePollingIntervalMillis")) {
                                b = 5;
                            }
                            break;
                        case -1159737108:
                            if (next.equals("jitter")) {
                                b = 8;
                            }
                            break;
                        case -1071535321:
                            if (next.equals("targetAction")) {
                                b = 4;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 3;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 7;
                            }
                            break;
                        case 1124836096:
                            if (next.equals("acceptableErrorCodes")) {
                                b = 6;
                            }
                            break;
                    }
                    Analytics analytics = null;
                    ImmutableList<Integer> immutableList = null;
                    Action action = null;
                    ActionType actionType = null;
                    String str = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field passivePollingIntervalMillis can't be null");
                            }
                            builder.passivePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                actionType = (ActionType) this.mActionTypeParser.parse(jsonNode2);
                            }
                            builder.type = actionType;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                action = this.mActionParser.parse(jsonNode2);
                            }
                            builder.targetAction = action;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                            }
                            builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mIntegerListParser.parse(jsonNode2);
                            }
                            builder.acceptableErrorCodes = immutableList;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field jitter can't be null");
                            }
                            builder.jitter = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PollingAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.passivePollingIntervalMillis), this, "passivePollingIntervalMillis");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.targetAction, this, "targetAction");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.activePollingIntervalMillis), this, "activePollingIntervalMillis");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PollingAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 0;
                                    }
                                    break;
                                case -1675779801:
                                    if (currentName.equals("passivePollingIntervalMillis")) {
                                        b = 2;
                                    }
                                    break;
                                case -1488616792:
                                    if (currentName.equals("activePollingIntervalMillis")) {
                                        b = 5;
                                    }
                                    break;
                                case -1159737108:
                                    if (currentName.equals("jitter")) {
                                        b = 8;
                                    }
                                    break;
                                case -1071535321:
                                    if (currentName.equals("targetAction")) {
                                        b = 4;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 3;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 7;
                                    }
                                    break;
                                case 1124836096:
                                    if (currentName.equals("acceptableErrorCodes")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            ImmutableList<Integer> immutableList = null;
                            Action action = null;
                            ActionType actionType = null;
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field passivePollingIntervalMillis can't be null");
                                    }
                                    builder.passivePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        actionType = (ActionType) this.mActionTypeParser.parse(jsonParser);
                                    }
                                    builder.type = actionType;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        action = this.mActionParser.parse(jsonParser);
                                    }
                                    builder.targetAction = action;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                                    }
                                    builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mIntegerListParser.parse(jsonParser);
                                    }
                                    builder.acceptableErrorCodes = immutableList;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field jitter can't be null");
                                    }
                                    builder.jitter = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PollingAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.passivePollingIntervalMillis), this, "passivePollingIntervalMillis");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.targetAction, this, "targetAction");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.activePollingIntervalMillis), this, "activePollingIntervalMillis");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
