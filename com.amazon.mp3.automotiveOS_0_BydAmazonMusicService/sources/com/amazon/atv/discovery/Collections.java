package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Collection;
import com.amazon.atv.discovery.PaginationAction;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Collections {
    public final ImmutableList<Collection> collectionList;
    public final Optional<PaginationAction> paginationLink;

    private Collections(Builder builder) {
        this.collectionList = (ImmutableList) Preconditions.checkNotNull(builder.collectionList, "Unexpected null field: collectionList");
        this.paginationLink = Optional.fromNullable(builder.paginationLink);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Collections)) {
            return false;
        }
        Collections collections = (Collections) obj;
        return Objects.equal(this.collectionList, collections.collectionList) && Objects.equal(this.paginationLink, collections.paginationLink);
    }

    public int hashCode() {
        return Objects.hashCode(this.collectionList, this.paginationLink);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("collectionList", this.collectionList).add("paginationLink", this.paginationLink).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<Collection> collectionList;
        public PaginationAction paginationLink;

        public Collections build() {
            return new Collections(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Collections> {
        private final ListParser<Collection> mCollectionListParser;
        private final PaginationAction.Parser mPaginationActionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPaginationActionParser = new PaginationAction.Parser(objectMapper);
            this.mCollectionListParser = ListParser.newParser(new Collection.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Collections parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Collections:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Collections parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Collections:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Collections parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Collections");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 142868148) {
                        if (iHashCode == 310727772 && next.equals("collectionList")) {
                            b = 0;
                        }
                    } else if (next.equals("paginationLink")) {
                        b = 1;
                    }
                    PaginationAction paginationAction = null;
                    ImmutableList<Collection> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mCollectionListParser.parse(jsonNode2);
                        }
                        builder.collectionList = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            paginationAction = this.mPaginationActionParser.parse(jsonNode2);
                        }
                        builder.paginationLink = paginationAction;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Collections so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.collectionList, this, "collectionList");
            return builder.build();
        }

        @Nonnull
        private Collections parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 142868148) {
                                if (iHashCode == 310727772 && currentName.equals("collectionList")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("paginationLink")) {
                                b = 1;
                            }
                            PaginationAction paginationAction = null;
                            ImmutableList<Collection> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mCollectionListParser.parse(jsonParser);
                                }
                                builder.collectionList = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    paginationAction = this.mPaginationActionParser.parse(jsonParser);
                                }
                                builder.paginationLink = paginationAction;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Collections so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.collectionList, this, "collectionList");
                    return builder.build();
                }
            }
        }
    }
}
