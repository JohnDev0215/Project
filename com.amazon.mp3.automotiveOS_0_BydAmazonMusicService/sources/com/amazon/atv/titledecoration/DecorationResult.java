package com.amazon.atv.titledecoration;

import com.amazon.atv.title.Title;
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

/* JADX INFO: loaded from: classes2.dex */
public class DecorationResult {
    public final Optional<Title> title;
    public final Optional<String> titleId;

    private DecorationResult(Builder builder) {
        this.titleId = Optional.fromNullable(builder.titleId);
        this.title = Optional.fromNullable(builder.title);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DecorationResult)) {
            return false;
        }
        DecorationResult decorationResult = (DecorationResult) obj;
        return Objects.equal(this.titleId, decorationResult.titleId) && Objects.equal(this.title, decorationResult.title);
    }

    public int hashCode() {
        return Objects.hashCode(this.titleId, this.title);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleId", this.titleId).add("title", this.title).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Title title;
        public String titleId;

        public DecorationResult build() {
            return new DecorationResult(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DecorationResult> {
        private final SimpleParsers.StringParser mTitleIdParser;
        private final Title.Parser mTitleParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTitleParser = new Title.Parser(objectMapper);
            this.mTitleIdParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DecorationResult parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DecorationResult:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DecorationResult parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DecorationResult:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DecorationResult parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DecorationResult");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1307249261) {
                        if (iHashCode == 110371416 && next.equals("title")) {
                            b = 1;
                        }
                    } else if (next.equals("titleId")) {
                        b = 0;
                    }
                    Title title = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mTitleIdParser.parse(jsonNode2);
                        }
                        builder.titleId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            title = this.mTitleParser.parse(jsonNode2);
                        }
                        builder.title = title;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DecorationResult so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DecorationResult parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1307249261) {
                                if (iHashCode == 110371416 && currentName.equals("title")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("titleId")) {
                                b = 0;
                            }
                            Title title = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mTitleIdParser.parse(jsonParser);
                                }
                                builder.titleId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    title = this.mTitleParser.parse(jsonParser);
                                }
                                builder.title = title;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DecorationResult so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
