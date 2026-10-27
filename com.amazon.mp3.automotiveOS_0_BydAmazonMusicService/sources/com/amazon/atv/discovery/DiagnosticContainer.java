package com.amazon.atv.discovery;

import com.amazon.atv.discovery.DiagnosticToken;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticContainer {
    public final Optional<ImmutableList<DiagnosticToken>> diagnosticTokenList;

    private DiagnosticContainer(Builder builder) {
        this.diagnosticTokenList = Optional.fromNullable(builder.diagnosticTokenList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DiagnosticContainer) {
            return Objects.equal(this.diagnosticTokenList, ((DiagnosticContainer) obj).diagnosticTokenList);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.diagnosticTokenList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("diagnosticTokenList", this.diagnosticTokenList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<DiagnosticToken> diagnosticTokenList;

        public DiagnosticContainer build() {
            return new DiagnosticContainer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DiagnosticContainer> {
        private final ListParser<DiagnosticToken> mDiagnosticTokenListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDiagnosticTokenListParser = ListParser.newParser(new DiagnosticToken.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DiagnosticContainer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiagnosticContainer:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DiagnosticContainer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DiagnosticContainer:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DiagnosticContainer parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DiagnosticContainer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 2135927376 && next.equals("diagnosticTokenList")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.diagnosticTokenList = jsonNode2.isNull() ? null : this.mDiagnosticTokenListParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DiagnosticContainer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DiagnosticContainer parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 2135927376 && currentName.equals("diagnosticTokenList")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.diagnosticTokenList = currentToken == JsonToken.VALUE_NULL ? null : this.mDiagnosticTokenListParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DiagnosticContainer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
