package com.amazon.atv.discovery;

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
public class Crumb {
    public final String text;

    private Crumb(Builder builder) {
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Crumb) {
            return Objects.equal(this.text, ((Crumb) obj).text);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.text);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String text;

        public Crumb build() {
            return new Crumb(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Crumb> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Crumb parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Crumb:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Crumb parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Crumb:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Crumb parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Crumb");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 3556653 && next.equals("text")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.text = jsonNode2.isNull() ? null : this.mStringParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Crumb so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            return builder.build();
        }

        @Nonnull
        private Crumb parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 3556653 && currentName.equals("text")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.text = currentToken == JsonToken.VALUE_NULL ? null : this.mStringParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Crumb so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    return builder.build();
                }
            }
        }
    }
}
