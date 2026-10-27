package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class XrayLink {
    public final Optional<String> instanceId;
    public final Optional<String> itemId;
    public final String pageId;
    public final Optional<String> pageSize;
    public final String pageType;
    public final Optional<String> sectionType;
    public final String serviceToken;
    public final Optional<String> startIndex;
    public final String widgetId;

    private XrayLink(Builder builder) {
        this.pageSize = Optional.fromNullable(builder.pageSize);
        this.widgetId = (String) Preconditions.checkNotNull(builder.widgetId, "Unexpected null field: widgetId");
        this.itemId = Optional.fromNullable(builder.itemId);
        this.sectionType = Optional.fromNullable(builder.sectionType);
        this.pageType = (String) Preconditions.checkNotNull(builder.pageType, "Unexpected null field: pageType");
        this.serviceToken = (String) Preconditions.checkNotNull(builder.serviceToken, "Unexpected null field: serviceToken");
        this.startIndex = Optional.fromNullable(builder.startIndex);
        this.instanceId = Optional.fromNullable(builder.instanceId);
        this.pageId = (String) Preconditions.checkNotNull(builder.pageId, "Unexpected null field: pageId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayLink)) {
            return false;
        }
        XrayLink xrayLink = (XrayLink) obj;
        return Objects.equal(this.pageSize, xrayLink.pageSize) && Objects.equal(this.widgetId, xrayLink.widgetId) && Objects.equal(this.itemId, xrayLink.itemId) && Objects.equal(this.sectionType, xrayLink.sectionType) && Objects.equal(this.pageType, xrayLink.pageType) && Objects.equal(this.serviceToken, xrayLink.serviceToken) && Objects.equal(this.startIndex, xrayLink.startIndex) && Objects.equal(this.instanceId, xrayLink.instanceId) && Objects.equal(this.pageId, xrayLink.pageId);
    }

    public int hashCode() {
        return Objects.hashCode(this.pageSize, this.widgetId, this.itemId, this.sectionType, this.pageType, this.serviceToken, this.startIndex, this.instanceId, this.pageId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("pageSize", this.pageSize).add("widgetId", this.widgetId).add("itemId", this.itemId).add("sectionType", this.sectionType).add("pageType", this.pageType).add("serviceToken", this.serviceToken).add("startIndex", this.startIndex).add("instanceId", this.instanceId).add("pageId", this.pageId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String instanceId;
        public String itemId;
        public String pageId;
        public String pageSize;
        public String pageType;
        public String sectionType;
        public String serviceToken;
        public String startIndex;
        public String widgetId;

        public XrayLink build() {
            return new XrayLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XrayLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            JsonParsingUtils.throwIfNotObject(jsonNode, "XrayLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1593646704:
                            if (next.equals("startIndex")) {
                                b = 6;
                            }
                            break;
                        case -1399201153:
                            if (next.equals("widgetId")) {
                                b = 1;
                            }
                            break;
                        case -1178662002:
                            if (next.equals("itemId")) {
                                b = 2;
                            }
                            break;
                        case -995752950:
                            if (next.equals("pageId")) {
                                b = 8;
                            }
                            break;
                        case 349760580:
                            if (next.equals("serviceToken")) {
                                b = 5;
                            }
                            break;
                        case 574851007:
                            if (next.equals("sectionType")) {
                                b = 3;
                            }
                            break;
                        case 859428656:
                            if (next.equals("pageSize")) {
                                b = 0;
                            }
                            break;
                        case 859473513:
                            if (next.equals("pageType")) {
                                b = 4;
                            }
                            break;
                        case 902024336:
                            if (next.equals("instanceId")) {
                                b = 7;
                            }
                            break;
                    }
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageSize = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.widgetId = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.itemId = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.sectionType = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageType = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.serviceToken = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.startIndex = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.instanceId = str;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageId = str;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.widgetId, this, "widgetId");
            JsonParsingUtils.checkNotNull(builder.pageType, this, "pageType");
            JsonParsingUtils.checkNotNull(builder.serviceToken, this, "serviceToken");
            JsonParsingUtils.checkNotNull(builder.pageId, this, "pageId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1593646704:
                                    if (currentName.equals("startIndex")) {
                                        b = 6;
                                    }
                                    break;
                                case -1399201153:
                                    if (currentName.equals("widgetId")) {
                                        b = 1;
                                    }
                                    break;
                                case -1178662002:
                                    if (currentName.equals("itemId")) {
                                        b = 2;
                                    }
                                    break;
                                case -995752950:
                                    if (currentName.equals("pageId")) {
                                        b = 8;
                                    }
                                    break;
                                case 349760580:
                                    if (currentName.equals("serviceToken")) {
                                        b = 5;
                                    }
                                    break;
                                case 574851007:
                                    if (currentName.equals("sectionType")) {
                                        b = 3;
                                    }
                                    break;
                                case 859428656:
                                    if (currentName.equals("pageSize")) {
                                        b = 0;
                                    }
                                    break;
                                case 859473513:
                                    if (currentName.equals("pageType")) {
                                        b = 4;
                                    }
                                    break;
                                case 902024336:
                                    if (currentName.equals("instanceId")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            String str = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.pageSize = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.widgetId = str;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.itemId = str;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.sectionType = str;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.pageType = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.serviceToken = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.startIndex = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.instanceId = str;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.pageId = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.widgetId, this, "widgetId");
                    JsonParsingUtils.checkNotNull(builder.pageType, this, "pageType");
                    JsonParsingUtils.checkNotNull(builder.serviceToken, this, "serviceToken");
                    JsonParsingUtils.checkNotNull(builder.pageId, this, "pageId");
                    return builder.build();
                }
            }
        }
    }
}
