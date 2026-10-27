package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.XRayBaseAction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayActions {
    public final ImmutableList<XRayBaseAction> actionList;
    public final long sourceTime;

    private XRayActions(Builder builder) {
        this.sourceTime = builder.sourceTime;
        this.actionList = (ImmutableList) Preconditions.checkNotNull(builder.actionList, "Unexpected null field: actionList");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayActions)) {
            return false;
        }
        XRayActions xRayActions = (XRayActions) obj;
        return Objects.equal(Long.valueOf(this.sourceTime), Long.valueOf(xRayActions.sourceTime)) && Objects.equal(this.actionList, xRayActions.actionList);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.sourceTime), this.actionList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("sourceTime", this.sourceTime).add("actionList", this.actionList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<XRayBaseAction> actionList;
        public long sourceTime;

        public XRayActions build() {
            return new XRayActions(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayActions> {
        private final ListParser<XRayBaseAction> mXrayActionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mXrayActionListParser = ListParser.newParser(new XRayBaseAction.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayActions parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayActions:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayActions parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayActions:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private XRayActions parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayActions");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1111447160) {
                        if (iHashCode == 1851627508 && next.equals("actionList")) {
                            b = 1;
                        }
                    } else if (next.equals("sourceTime")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.actionList = jsonNode2.isNull() ? null : this.mXrayActionListParser.parse(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field sourceTime can't be null");
                        }
                        builder.sourceTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayActions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.sourceTime), this, "sourceTime");
            JsonParsingUtils.checkNotNull(builder.actionList, this, "actionList");
            return builder.build();
        }

        @Nonnull
        private XRayActions parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1111447160) {
                                if (iHashCode == 1851627508 && currentName.equals("actionList")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("sourceTime")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.actionList = currentToken == JsonToken.VALUE_NULL ? null : this.mXrayActionListParser.parse(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field sourceTime can't be null");
                                }
                                builder.sourceTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayActions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.sourceTime), this, "sourceTime");
                    JsonParsingUtils.checkNotNull(builder.actionList, this, "actionList");
                    return builder.build();
                }
            }
        }
    }
}
