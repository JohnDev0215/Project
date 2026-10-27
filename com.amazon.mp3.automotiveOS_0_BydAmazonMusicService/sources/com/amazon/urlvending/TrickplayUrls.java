package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.urlvending.TrickplayUrlsCdnSet;
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

/* JADX INFO: loaded from: classes5.dex */
public class TrickplayUrls {
    public final Optional<ImmutableList<TrickplayUrlsCdnSet>> trickplayUrlsCdnSets;

    private TrickplayUrls(Builder builder) {
        this.trickplayUrlsCdnSets = Optional.fromNullable(builder.trickplayUrlsCdnSets);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TrickplayUrls) {
            return Objects.equal(this.trickplayUrlsCdnSets, ((TrickplayUrls) obj).trickplayUrlsCdnSets);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.trickplayUrlsCdnSets);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("trickplayUrlsCdnSets", this.trickplayUrlsCdnSets).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<TrickplayUrlsCdnSet> trickplayUrlsCdnSets;

        public TrickplayUrls build() {
            return new TrickplayUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TrickplayUrls> {
        private final ListParser<TrickplayUrlsCdnSet> mTrickplayUrlsCdnSetsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTrickplayUrlsCdnSetsParser = ListParser.newParser(new TrickplayUrlsCdnSet.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TrickplayUrls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TrickplayUrls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TrickplayUrls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TrickplayUrls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TrickplayUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TrickplayUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 967074067 && next.equals("trickplayUrlsCdnSets")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.trickplayUrlsCdnSets = jsonNode2.isNull() ? null : this.mTrickplayUrlsCdnSetsParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TrickplayUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TrickplayUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 967074067 && currentName.equals("trickplayUrlsCdnSets")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.trickplayUrlsCdnSets = currentToken == JsonToken.VALUE_NULL ? null : this.mTrickplayUrlsCdnSetsParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TrickplayUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
