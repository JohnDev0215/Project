package com.amazon.avod.xray;

import com.amazon.atv.discovery.Item;
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
public class XRayItemActionTarget extends XRayActionTargetBase {
    public final Item item;

    private XRayItemActionTarget(Builder builder) {
        super(builder);
        this.item = (Item) Preconditions.checkNotNull(builder.item, "Unexpected null field: item");
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XRayItemActionTarget) {
            return super.equals(obj) && Objects.equal(this.item, ((XRayItemActionTarget) obj).item);
        }
        return false;
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.item);
    }

    @Override // com.amazon.avod.xray.XRayActionTargetBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("item", this.item).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayActionTargetBase.Builder {
        public Item item;

        public XRayItemActionTarget build() {
            return new XRayItemActionTarget(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayItemActionTarget> {
        private final Item.Parser mItemParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemParser = new Item.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayItemActionTarget parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayItemActionTarget:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayItemActionTarget parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayItemActionTarget:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private XRayItemActionTarget parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayItemActionTarget");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3242771) {
                        if (iHashCode != 3575610) {
                            if (iHashCode == 351608024 && next.equals("version")) {
                                b = 1;
                            }
                        } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                            b = 0;
                        }
                    } else if (next.equals("item")) {
                        b = 2;
                    }
                    Item item = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str;
                    } else if (b != 1) {
                        if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                item = this.mItemParser.parse(jsonNode2);
                            }
                            builder.item = item;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayItemActionTarget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.item, this, "item");
            return builder.build();
        }

        @Nonnull
        private XRayItemActionTarget parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3242771) {
                                if (iHashCode != 3575610) {
                                    if (iHashCode == 351608024 && currentName.equals("version")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("item")) {
                                b = 2;
                            }
                            Item item = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.type = str;
                            } else if (b != 1) {
                                if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        item = this.mItemParser.parse(jsonParser);
                                    }
                                    builder.item = item;
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayItemActionTarget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.item, this, "item");
                    return builder.build();
                }
            }
        }
    }
}
