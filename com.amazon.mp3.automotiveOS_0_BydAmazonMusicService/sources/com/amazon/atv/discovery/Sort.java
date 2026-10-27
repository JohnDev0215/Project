package com.amazon.atv.discovery;

import com.amazon.atv.discovery.SortBase;
import com.amazon.atv.discovery.SortOption;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Sort extends SortBase {
    public final ImmutableList<SortOption> sortOptionList;
    public final String type;

    private Sort(Builder builder) {
        super(builder);
        this.type = (String) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.sortOptionList = (ImmutableList) Preconditions.checkNotNull(builder.sortOptionList, "Unexpected null field: sortOptionList");
    }

    @Override // com.amazon.atv.discovery.SortBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sort)) {
            return false;
        }
        Sort sort = (Sort) obj;
        return super.equals(obj) && Objects.equal(this.type, sort.type) && Objects.equal(this.sortOptionList, sort.sortOptionList);
    }

    @Override // com.amazon.atv.discovery.SortBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.type, this.sortOptionList);
    }

    @Override // com.amazon.atv.discovery.SortBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("sortOptionList", this.sortOptionList).toString();
    }

    @NotThreadSafe
    public static class Builder extends SortBase.Builder {
        public ImmutableList<SortOption> sortOptionList;
        public String type;

        public Sort build() {
            return new Sort(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Sort> {
        private final ListParser<SortOption> mSortOptionListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSortOptionListParser = ListParser.newParser(new SortOption.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Sort parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Sort:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Sort parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Sort:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Sort parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Sort");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1898230031:
                            if (next.equals("sortOptionList")) {
                                b = 1;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 3;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    ImmutableList<SortOption> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSortOptionListParser.parse(jsonNode2);
                        }
                        builder.sortOptionList = immutableList;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Sort so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.sortOptionList, this, "sortOptionList");
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Sort parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1898230031:
                                    if (currentName.equals("sortOptionList")) {
                                        b = 1;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 3;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableList<SortOption> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.type = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSortOptionListParser.parse(jsonParser);
                                }
                                builder.sortOptionList = immutableList;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.text = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Sort so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.sortOptionList, this, "sortOptionList");
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    return builder.build();
                }
            }
        }
    }
}
