package com.amazon.atv.discovery;

import com.amazon.atv.discovery.FilterBase;
import com.amazon.atv.discovery.FiltersBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
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
public class FiltersV2 extends FiltersBase {
    public final Optional<ImmutableList<FilterBase>> primary;
    public final Optional<ImmutableList<FilterBase>> secondary;

    private FiltersV2(Builder builder) {
        super(builder);
        this.primary = Optional.fromNullable(builder.primary);
        this.secondary = Optional.fromNullable(builder.secondary);
    }

    @Override // com.amazon.atv.discovery.FiltersBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FiltersV2)) {
            return false;
        }
        FiltersV2 filtersV2 = (FiltersV2) obj;
        return super.equals(obj) && Objects.equal(this.primary, filtersV2.primary) && Objects.equal(this.secondary, filtersV2.secondary);
    }

    @Override // com.amazon.atv.discovery.FiltersBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.primary, this.secondary);
    }

    @Override // com.amazon.atv.discovery.FiltersBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("primary", this.primary).add("secondary", this.secondary).toString();
    }

    @NotThreadSafe
    public static class Builder extends FiltersBase.Builder {
        public ImmutableList<FilterBase> primary;
        public ImmutableList<FilterBase> secondary;

        public FiltersV2 build() {
            return new FiltersV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<FiltersV2> {
        private final ListParser<FilterBase> mFilterBaseListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFilterBaseListParser = ListParser.newParser(new FilterBase.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FiltersV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FiltersV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FiltersV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FiltersV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private FiltersV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "FiltersV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -817598092) {
                        if (iHashCode != -314765822) {
                            if (iHashCode == 351608024 && next.equals("version")) {
                                b = 0;
                            }
                        } else if (next.equals("primary")) {
                            b = 1;
                        }
                    } else if (next.equals("secondary")) {
                        b = 2;
                    }
                    if (b != 0) {
                        ImmutableList<FilterBase> immutableList = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mFilterBaseListParser.parse(jsonNode2);
                            }
                            builder.primary = immutableList;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mFilterBaseListParser.parse(jsonNode2);
                            }
                            builder.secondary = immutableList;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing FiltersV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private FiltersV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -817598092) {
                                if (iHashCode != -314765822) {
                                    if (iHashCode == 351608024 && currentName.equals("version")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("primary")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("secondary")) {
                                b = 2;
                            }
                            if (b != 0) {
                                ImmutableList<FilterBase> immutableList = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mFilterBaseListParser.parse(jsonParser);
                                    }
                                    builder.primary = immutableList;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mFilterBaseListParser.parse(jsonParser);
                                    }
                                    builder.secondary = immutableList;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing FiltersV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
