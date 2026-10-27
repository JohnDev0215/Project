package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.XRayActionTargetBase;
import com.amazon.atv.xrayv2.XRayBaseAction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayAddAction extends XRayBaseAction {
    public final int index;
    public final long maxJitterMillis;
    public final int priority;
    public final XRayActionTargetBase target;
    public final long visibilityDurationMillis;
    public final long visibilityWindowMillis;

    private XRayAddAction(Builder builder) {
        super(builder);
        this.index = builder.index;
        this.priority = builder.priority;
        this.maxJitterMillis = builder.maxJitterMillis;
        this.visibilityDurationMillis = builder.visibilityDurationMillis;
        this.target = (XRayActionTargetBase) Preconditions.checkNotNull(builder.target, "Unexpected null field: target");
        this.visibilityWindowMillis = builder.visibilityWindowMillis;
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayAddAction)) {
            return false;
        }
        XRayAddAction xRayAddAction = (XRayAddAction) obj;
        return super.equals(obj) && Objects.equal(Integer.valueOf(this.index), Integer.valueOf(xRayAddAction.index)) && Objects.equal(Integer.valueOf(this.priority), Integer.valueOf(xRayAddAction.priority)) && Objects.equal(Long.valueOf(this.maxJitterMillis), Long.valueOf(xRayAddAction.maxJitterMillis)) && Objects.equal(Long.valueOf(this.visibilityDurationMillis), Long.valueOf(xRayAddAction.visibilityDurationMillis)) && Objects.equal(this.target, xRayAddAction.target) && Objects.equal(Long.valueOf(this.visibilityWindowMillis), Long.valueOf(xRayAddAction.visibilityWindowMillis));
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(this.index), Integer.valueOf(this.priority), Long.valueOf(this.maxJitterMillis), Long.valueOf(this.visibilityDurationMillis), this.target, Long.valueOf(this.visibilityWindowMillis));
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public String toString() {
        return MoreObjects.toStringHelper(this).add("index", this.index).add("priority", this.priority).add("maxJitterMillis", this.maxJitterMillis).add("visibilityDurationMillis", this.visibilityDurationMillis).add("target", this.target).add("visibilityWindowMillis", this.visibilityWindowMillis).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayBaseAction.Builder {
        public int index;
        public long maxJitterMillis;
        public int priority;
        public XRayActionTargetBase target;
        public long visibilityDurationMillis;
        public long visibilityWindowMillis;

        public XRayAddAction build() {
            return new XRayAddAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayAddAction> {
        private final SimpleParsers.StringParser mStringParser;
        private final XRayActionTargetBase.Parser mXRayActionTargetBaseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mXRayActionTargetBaseParser = new XRayActionTargetBase.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayAddAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayAddAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayAddAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayAddAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayAddAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayAddAction");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1852719956:
                            if (next.equals("visibilityDurationMillis")) {
                                b = 6;
                            }
                            break;
                        case -1165461084:
                            if (next.equals("priority")) {
                                b = 3;
                            }
                            break;
                        case -1074898296:
                            if (next.equals("visibilityWindowMillis")) {
                                b = 9;
                            }
                            break;
                        case -880905839:
                            if (next.equals("target")) {
                                b = 7;
                            }
                            break;
                        case -468846122:
                            if (next.equals("maxJitterMillis")) {
                                b = 4;
                            }
                            break;
                        case -441951604:
                            if (next.equals("targetId")) {
                                b = 8;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                            break;
                        case 100346066:
                            if (next.equals("index")) {
                                b = 1;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 5;
                            }
                            break;
                        case 1813478742:
                            if (next.equals("parentTargetId")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    XRayActionTargetBase xRayActionTargetBase = null;
                    String str3 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.parentTargetId = str;
                            break;
                        case 1:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field index can't be null");
                            }
                            builder.index = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.type = str3;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field priority can't be null");
                            }
                            builder.priority = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field maxJitterMillis can't be null");
                            }
                            builder.maxJitterMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field visibilityDurationMillis can't be null");
                            }
                            builder.visibilityDurationMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                xRayActionTargetBase = this.mXRayActionTargetBaseParser.parse(jsonNode2);
                            }
                            builder.target = xRayActionTargetBase;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.targetId = str2;
                            break;
                        case 9:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field visibilityWindowMillis can't be null");
                            }
                            builder.visibilityWindowMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayAddAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.parentTargetId, this, "parentTargetId");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.target, this, "target");
            JsonParsingUtils.checkNotNull(builder.targetId, this, "targetId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayAddAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1852719956:
                                    if (currentName.equals("visibilityDurationMillis")) {
                                        b = 6;
                                    }
                                    break;
                                case -1165461084:
                                    if (currentName.equals("priority")) {
                                        b = 3;
                                    }
                                    break;
                                case -1074898296:
                                    if (currentName.equals("visibilityWindowMillis")) {
                                        b = 9;
                                    }
                                    break;
                                case -880905839:
                                    if (currentName.equals("target")) {
                                        b = 7;
                                    }
                                    break;
                                case -468846122:
                                    if (currentName.equals("maxJitterMillis")) {
                                        b = 4;
                                    }
                                    break;
                                case -441951604:
                                    if (currentName.equals("targetId")) {
                                        b = 8;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 2;
                                    }
                                    break;
                                case 100346066:
                                    if (currentName.equals("index")) {
                                        b = 1;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 5;
                                    }
                                    break;
                                case 1813478742:
                                    if (currentName.equals("parentTargetId")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            XRayActionTargetBase xRayActionTargetBase = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.parentTargetId = str;
                                    break;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field index can't be null");
                                    }
                                    builder.index = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str3;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field priority can't be null");
                                    }
                                    builder.priority = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field maxJitterMillis can't be null");
                                    }
                                    builder.maxJitterMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field visibilityDurationMillis can't be null");
                                    }
                                    builder.visibilityDurationMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        xRayActionTargetBase = this.mXRayActionTargetBaseParser.parse(jsonParser);
                                    }
                                    builder.target = xRayActionTargetBase;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.targetId = str2;
                                    break;
                                case 9:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field visibilityWindowMillis can't be null");
                                    }
                                    builder.visibilityWindowMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayAddAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.parentTargetId, this, "parentTargetId");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.target, this, "target");
                    JsonParsingUtils.checkNotNull(builder.targetId, this, "targetId");
                    return builder.build();
                }
            }
        }
    }
}
