package com.amazon.avod.xray.clickstream;

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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PageInfo {
    public final String pageType;
    public final String pageTypeId;
    public final String pageTypeIdSource;
    public final String subPageType;

    private PageInfo(Builder builder) {
        this.subPageType = (String) Preconditions.checkNotNull(builder.subPageType, "Unexpected null field: subPageType");
        this.pageTypeIdSource = (String) Preconditions.checkNotNull(builder.pageTypeIdSource, "Unexpected null field: pageTypeIdSource");
        this.pageType = (String) Preconditions.checkNotNull(builder.pageType, "Unexpected null field: pageType");
        this.pageTypeId = (String) Preconditions.checkNotNull(builder.pageTypeId, "Unexpected null field: pageTypeId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PageInfo)) {
            return false;
        }
        PageInfo pageInfo = (PageInfo) obj;
        return Objects.equal(this.subPageType, pageInfo.subPageType) && Objects.equal(this.pageTypeIdSource, pageInfo.pageTypeIdSource) && Objects.equal(this.pageType, pageInfo.pageType) && Objects.equal(this.pageTypeId, pageInfo.pageTypeId);
    }

    public int hashCode() {
        return Objects.hashCode(this.subPageType, this.pageTypeIdSource, this.pageType, this.pageTypeId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("subPageType", this.subPageType).add("pageTypeIdSource", this.pageTypeIdSource).add("pageType", this.pageType).add("pageTypeId", this.pageTypeId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String pageType;
        public String pageTypeId;
        public String pageTypeIdSource;
        public String subPageType;

        public PageInfo build() {
            return new PageInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PageInfo> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PageInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PageInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PageInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PageInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PageInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PageInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2120980279:
                            if (next.equals("subPageType")) {
                                b = 0;
                            }
                            break;
                        case -662678881:
                            if (next.equals("pageTypeIdSource")) {
                                b = 1;
                            }
                            break;
                        case 859473513:
                            if (next.equals("pageType")) {
                                b = 2;
                            }
                            break;
                        case 1320327524:
                            if (next.equals("pageTypeId")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subPageType = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageTypeIdSource = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageType = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.pageTypeId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PageInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.subPageType, this, "subPageType");
            JsonParsingUtils.checkNotNull(builder.pageTypeIdSource, this, "pageTypeIdSource");
            JsonParsingUtils.checkNotNull(builder.pageType, this, "pageType");
            JsonParsingUtils.checkNotNull(builder.pageTypeId, this, "pageTypeId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PageInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2120980279:
                                    if (currentName.equals("subPageType")) {
                                        b = 0;
                                    }
                                    break;
                                case -662678881:
                                    if (currentName.equals("pageTypeIdSource")) {
                                        b = 1;
                                    }
                                    break;
                                case 859473513:
                                    if (currentName.equals("pageType")) {
                                        b = 2;
                                    }
                                    break;
                                case 1320327524:
                                    if (currentName.equals("pageTypeId")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.subPageType = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.pageTypeIdSource = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.pageType = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.pageTypeId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PageInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.subPageType, this, "subPageType");
                    JsonParsingUtils.checkNotNull(builder.pageTypeIdSource, this, "pageTypeIdSource");
                    JsonParsingUtils.checkNotNull(builder.pageType, this, "pageType");
                    JsonParsingUtils.checkNotNull(builder.pageTypeId, this, "pageTypeId");
                    return builder.build();
                }
            }
        }
    }
}
