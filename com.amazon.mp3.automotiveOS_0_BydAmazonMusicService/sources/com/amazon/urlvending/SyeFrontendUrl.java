package com.amazon.urlvending;

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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class SyeFrontendUrl {
    public final Optional<String> frontendUrl;
    public final Optional<String> urlSetId;

    private SyeFrontendUrl(Builder builder) {
        this.urlSetId = Optional.fromNullable(builder.urlSetId);
        this.frontendUrl = Optional.fromNullable(builder.frontendUrl);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeFrontendUrl)) {
            return false;
        }
        SyeFrontendUrl syeFrontendUrl = (SyeFrontendUrl) obj;
        return Objects.equal(this.urlSetId, syeFrontendUrl.urlSetId) && Objects.equal(this.frontendUrl, syeFrontendUrl.frontendUrl);
    }

    public int hashCode() {
        return Objects.hashCode(this.urlSetId, this.frontendUrl);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urlSetId", this.urlSetId).add("frontendUrl", this.frontendUrl).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String frontendUrl;
        public String urlSetId;

        public SyeFrontendUrl build() {
            return new SyeFrontendUrl(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeFrontendUrl> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeFrontendUrl parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeFrontendUrl:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeFrontendUrl parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeFrontendUrl:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SyeFrontendUrl parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeFrontendUrl");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1289081347) {
                        if (iHashCode == -981693202 && next.equals("urlSetId")) {
                            b = 0;
                        }
                    } else if (next.equals("frontendUrl")) {
                        b = 1;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlSetId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.frontendUrl = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeFrontendUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SyeFrontendUrl parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1289081347) {
                                if (iHashCode == -981693202 && currentName.equals("urlSetId")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("frontendUrl")) {
                                b = 1;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.urlSetId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.frontendUrl = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeFrontendUrl so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
