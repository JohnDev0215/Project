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
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonRetailDetailPageLink {
    public final boolean shouldShowExitWarning;
    public final String url;

    private AmazonRetailDetailPageLink(Builder builder) {
        this.url = (String) Preconditions.checkNotNull(builder.url, "Unexpected null field: url");
        this.shouldShowExitWarning = builder.shouldShowExitWarning;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AmazonRetailDetailPageLink)) {
            return false;
        }
        AmazonRetailDetailPageLink amazonRetailDetailPageLink = (AmazonRetailDetailPageLink) obj;
        return Objects.equal(this.url, amazonRetailDetailPageLink.url) && Objects.equal(Boolean.valueOf(this.shouldShowExitWarning), Boolean.valueOf(amazonRetailDetailPageLink.shouldShowExitWarning));
    }

    public int hashCode() {
        return Objects.hashCode(this.url, Boolean.valueOf(this.shouldShowExitWarning));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(ImagesContract.URL, this.url).add("shouldShowExitWarning", this.shouldShowExitWarning).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean shouldShowExitWarning;
        public String url;

        public AmazonRetailDetailPageLink build() {
            return new AmazonRetailDetailPageLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AmazonRetailDetailPageLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AmazonRetailDetailPageLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AmazonRetailDetailPageLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AmazonRetailDetailPageLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AmazonRetailDetailPageLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AmazonRetailDetailPageLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AmazonRetailDetailPageLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -105852114) {
                        if (iHashCode == 116079 && next.equals(ImagesContract.URL)) {
                            b = 0;
                        }
                    } else if (next.equals("shouldShowExitWarning")) {
                        b = 1;
                    }
                    if (b == 0) {
                        builder.url = jsonNode2.isNull() ? null : this.mStringParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field shouldShowExitWarning can't be null");
                        }
                        builder.shouldShowExitWarning = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AmazonRetailDetailPageLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
            return builder.build();
        }

        @Nonnull
        private AmazonRetailDetailPageLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -105852114) {
                                if (iHashCode == 116079 && currentName.equals(ImagesContract.URL)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("shouldShowExitWarning")) {
                                b = 1;
                            }
                            if (b == 0) {
                                builder.url = currentToken == JsonToken.VALUE_NULL ? null : this.mStringParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field shouldShowExitWarning can't be null");
                                }
                                builder.shouldShowExitWarning = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AmazonRetailDetailPageLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
                    return builder.build();
                }
            }
        }
    }
}
