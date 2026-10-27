package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.LogoImage;
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
public class PagePresentationData {
    public final Optional<LogoImage> logoImage;
    public final Optional<String> templateId;
    public final String version;

    private PagePresentationData(Builder builder) {
        this.version = (String) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
        this.templateId = Optional.fromNullable(builder.templateId);
        this.logoImage = Optional.fromNullable(builder.logoImage);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PagePresentationData)) {
            return false;
        }
        PagePresentationData pagePresentationData = (PagePresentationData) obj;
        return Objects.equal(this.version, pagePresentationData.version) && Objects.equal(this.templateId, pagePresentationData.templateId) && Objects.equal(this.logoImage, pagePresentationData.logoImage);
    }

    public int hashCode() {
        return Objects.hashCode(this.version, this.templateId, this.logoImage);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("templateId", this.templateId).add("logoImage", this.logoImage).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public LogoImage logoImage;
        public String templateId;
        public String version;

        public PagePresentationData build() {
            return new PagePresentationData(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PagePresentationData> {
        private final LogoImage.Parser mLogoImageParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLogoImageParser = new LogoImage.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PagePresentationData parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PagePresentationData:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PagePresentationData parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PagePresentationData:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PagePresentationData parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PagePresentationData");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1580939664) {
                        if (iHashCode != 351608024) {
                            if (iHashCode == 1304010549 && next.equals("templateId")) {
                                b = 1;
                            }
                        } else if (next.equals("version")) {
                            b = 0;
                        }
                    } else if (next.equals("logoImage")) {
                        b = 2;
                    }
                    LogoImage logoImage = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.version = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.templateId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            logoImage = this.mLogoImageParser.parse(jsonNode2);
                        }
                        builder.logoImage = logoImage;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PagePresentationData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        @Nonnull
        private PagePresentationData parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1580939664) {
                                if (iHashCode != 351608024) {
                                    if (iHashCode == 1304010549 && currentName.equals("templateId")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("version")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("logoImage")) {
                                b = 2;
                            }
                            LogoImage logoImage = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.version = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.templateId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    logoImage = this.mLogoImageParser.parse(jsonParser);
                                }
                                builder.logoImage = logoImage;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PagePresentationData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
