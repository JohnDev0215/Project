package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.utils.PageUriUtils;
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

/* JADX INFO: loaded from: classes2.dex */
public class Biography {
    public final Optional<Action> seeMore;
    public final Optional<String> shortDescription;

    private Biography(Builder builder) {
        this.seeMore = Optional.fromNullable(builder.seeMore);
        this.shortDescription = Optional.fromNullable(builder.shortDescription);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Biography)) {
            return false;
        }
        Biography biography = (Biography) obj;
        return Objects.equal(this.seeMore, biography.seeMore) && Objects.equal(this.shortDescription, biography.shortDescription);
    }

    public int hashCode() {
        return Objects.hashCode(this.seeMore, this.shortDescription);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(PageUriUtils.DO_SEE_MORE_VALUE, this.seeMore).add("shortDescription", this.shortDescription).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Action seeMore;
        public String shortDescription;

        public Biography build() {
            return new Biography(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Biography> {
        private final Action.Parser mActionParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mActionParser = new Action.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Biography parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Biography:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Biography parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Biography:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Biography parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Biography");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 1951089120) {
                        if (iHashCode == 1970932296 && next.equals(PageUriUtils.DO_SEE_MORE_VALUE)) {
                            b = 0;
                        }
                    } else if (next.equals("shortDescription")) {
                        b = 1;
                    }
                    String str = null;
                    Action action = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            action = this.mActionParser.parse(jsonNode2);
                        }
                        builder.seeMore = action;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.shortDescription = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Biography so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Biography parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 1951089120) {
                                if (iHashCode == 1970932296 && currentName.equals(PageUriUtils.DO_SEE_MORE_VALUE)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("shortDescription")) {
                                b = 1;
                            }
                            String str = null;
                            Action action = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    action = this.mActionParser.parse(jsonParser);
                                }
                                builder.seeMore = action;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.shortDescription = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Biography so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
