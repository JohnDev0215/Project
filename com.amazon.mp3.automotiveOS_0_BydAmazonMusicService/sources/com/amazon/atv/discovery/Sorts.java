package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Sort;
import com.amazon.atv.discovery.SortsBase;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Sorts extends SortsBase {
    public final ImmutableList<Sort> sortList;
    public final Optional<String> text;

    private Sorts(Builder builder) {
        super(builder);
        this.text = Optional.fromNullable(builder.text);
        this.sortList = (ImmutableList) Preconditions.checkNotNull(builder.sortList, "Unexpected null field: sortList");
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sorts)) {
            return false;
        }
        Sorts sorts = (Sorts) obj;
        return super.equals(obj) && Objects.equal(this.text, sorts.text) && Objects.equal(this.sortList, sorts.sortList);
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.text, this.sortList);
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add("sortList", this.sortList).toString();
    }

    @NotThreadSafe
    public static class Builder extends SortsBase.Builder {
        public ImmutableList<Sort> sortList;
        public String text;

        public Sorts build() {
            return new Sorts(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Sorts> {
        private final ListParser<Sort> mSortListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSortListParser = ListParser.newParser(new Sort.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Sorts parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Sorts:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Sorts parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Sorts:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Sorts parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Sorts");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3556653) {
                        if (iHashCode != 351608024) {
                            if (iHashCode == 1661606748 && next.equals("sortList")) {
                                b = 2;
                            }
                        } else if (next.equals("version")) {
                            b = 0;
                        }
                    } else if (next.equals("text")) {
                        b = 1;
                    }
                    if (b != 0) {
                        ImmutableList<Sort> immutableList = null;
                        String str = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                        } else if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mSortListParser.parse(jsonNode2);
                            }
                            builder.sortList = immutableList;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Sorts so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.sortList, this, "sortList");
            return builder.build();
        }

        @Nonnull
        private Sorts parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3556653) {
                                if (iHashCode != 351608024) {
                                    if (iHashCode == 1661606748 && currentName.equals("sortList")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("version")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("text")) {
                                b = 1;
                            }
                            if (b != 0) {
                                ImmutableList<Sort> immutableList = null;
                                String str = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                } else if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mSortListParser.parse(jsonParser);
                                    }
                                    builder.sortList = immutableList;
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Sorts so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.sortList, this, "sortList");
                    return builder.build();
                }
            }
        }
    }
}
