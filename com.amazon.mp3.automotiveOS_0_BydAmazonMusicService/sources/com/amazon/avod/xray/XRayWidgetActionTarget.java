package com.amazon.avod.xray;

import com.amazon.atv.discovery.Widget;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRayActionTargetBase;
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
public class XRayWidgetActionTarget extends XRayActionTargetBase {
    public final Widget widget;

    private XRayWidgetActionTarget(Builder builder) {
        super(builder);
        this.widget = (Widget) Preconditions.checkNotNull(builder.widget, "Unexpected null field: widget");
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XRayWidgetActionTarget) {
            return super.equals(obj) && Objects.equal(this.widget, ((XRayWidgetActionTarget) obj).widget);
        }
        return false;
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.widget);
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("widget", this.widget).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayActionTargetBase.Builder {
        public Widget widget;

        public XRayWidgetActionTarget build() {
            return new XRayWidgetActionTarget(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayWidgetActionTarget> {
        private final SimpleParsers.StringParser mStringParser;
        private final Widget.Parser mWidgetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidgetParser = new Widget.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayWidgetActionTarget parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayWidgetActionTarget:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayWidgetActionTarget parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayWidgetActionTarget:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private XRayWidgetActionTarget parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayWidgetActionTarget");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -788047292) {
                        if (iHashCode != 3575610) {
                            if (iHashCode == 351608024 && next.equals("version")) {
                                b = 1;
                            }
                        } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                            b = 0;
                        }
                    } else if (next.equals("widget")) {
                        b = 2;
                    }
                    Widget widget = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str;
                    } else if (b != 1) {
                        if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                widget = this.mWidgetParser.parse(jsonNode2);
                            }
                            builder.widget = widget;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayWidgetActionTarget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.widget, this, "widget");
            return builder.build();
        }

        @Nonnull
        private XRayWidgetActionTarget parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -788047292) {
                                if (iHashCode != 3575610) {
                                    if (iHashCode == 351608024 && currentName.equals("version")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("widget")) {
                                b = 2;
                            }
                            Widget widget = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.type = str;
                            } else if (b != 1) {
                                if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        widget = this.mWidgetParser.parse(jsonParser);
                                    }
                                    builder.widget = widget;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayWidgetActionTarget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.widget, this, "widget");
                    return builder.build();
                }
            }
        }
    }
}
