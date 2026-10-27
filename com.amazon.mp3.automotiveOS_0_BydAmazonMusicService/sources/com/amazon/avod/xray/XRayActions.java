package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRayBaseAction;
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
        this.actionList = (ImmutableList) Preconditions.checkNotNull(builder.actionList, "Unexpected null field: actionList");
        this.sourceTime = builder.sourceTime;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayActions)) {
            return false;
        }
        XRayActions xRayActions = (XRayActions) obj;
        return Objects.equal(this.actionList, xRayActions.actionList) && Objects.equal(Long.valueOf(this.sourceTime), Long.valueOf(xRayActions.sourceTime));
    }

    public int hashCode() {
        return Objects.hashCode(this.actionList, Long.valueOf(this.sourceTime));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("actionList", this.actionList).add("sourceTime", this.sourceTime).toString();
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
        private final ListParser<XRayBaseAction> mActionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mActionListParser = ListParser.newParser(new XRayBaseAction.Parser(objectMapper));
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
                            b = 0;
                        }
                    } else if (next.equals("sourceTime")) {
                        b = 1;
                    }
                    if (b == 0) {
                        builder.actionList = jsonNode2.isNull() ? null : this.mActionListParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field sourceTime can't be null");
                        }
                        builder.sourceTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayActions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.actionList, this, "actionList");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.sourceTime), this, "sourceTime");
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
                                    b = 0;
                                }
                            } else if (currentName.equals("sourceTime")) {
                                b = 1;
                            }
                            if (b == 0) {
                                builder.actionList = currentToken == JsonToken.VALUE_NULL ? null : this.mActionListParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field sourceTime can't be null");
                                }
                                builder.sourceTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayActions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.actionList, this, "actionList");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.sourceTime), this, "sourceTime");
                    return builder.build();
                }
            }
        }
    }
}
