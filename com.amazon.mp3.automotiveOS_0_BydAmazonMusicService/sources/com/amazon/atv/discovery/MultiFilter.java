package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Filter;
import com.amazon.atv.discovery.FilterValue;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class MultiFilter extends Filter {
    public final ImmutableList<FilterValue> filterValueList;
    public final Optional<SelectionType> selection;

    private MultiFilter(Builder builder) {
        super(builder);
        this.selection = Optional.fromNullable(builder.selection);
        this.filterValueList = (ImmutableList) Preconditions.checkNotNull(builder.filterValueList, "Unexpected null field: filterValueList");
    }

    @Override // com.amazon.atv.discovery.Filter
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiFilter)) {
            return false;
        }
        MultiFilter multiFilter = (MultiFilter) obj;
        return super.equals(obj) && Objects.equal(this.selection, multiFilter.selection) && Objects.equal(this.filterValueList, multiFilter.filterValueList);
    }

    @Override // com.amazon.atv.discovery.Filter
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.selection, this.filterValueList);
    }

    @Override // com.amazon.atv.discovery.Filter
    public String toString() {
        return MoreObjects.toStringHelper(this).add("selection", this.selection).add("filterValueList", this.filterValueList).toString();
    }

    @NotThreadSafe
    public static class Builder extends Filter.Builder {
        public ImmutableList<FilterValue> filterValueList;
        public SelectionType selection;

        public MultiFilter build() {
            return new MultiFilter(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<MultiFilter> {
        private final EnumParser<FilterType> mFilterTypeParser;
        private final ListParser<FilterValue> mFilterValueListParser;
        private final EnumParser<SelectionType> mSelectionTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFilterValueListParser = ListParser.newParser(new FilterValue.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFilterTypeParser = EnumParser.newParser(FilterType.class);
            this.mSelectionTypeParser = EnumParser.newParser(SelectionType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public MultiFilter parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MultiFilter:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public MultiFilter parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MultiFilter:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MultiFilter parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            SelectionType selectionType;
            String str;
            FilterType filterType;
            String str2;
            String str3;
            ImmutableList<FilterValue> immutableList;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "MultiFilter");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1715965556:
                            if (next.equals("selection")) {
                                b = 0;
                            }
                            break;
                        case -881391673:
                            if (next.equals("filterKey")) {
                                b = 3;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                            break;
                        case 346136969:
                            if (next.equals("debugAttribute")) {
                                b = 4;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 6;
                            }
                            break;
                        case 1575178519:
                            if (next.equals("filterValueList")) {
                                b = 5;
                            }
                            break;
                    }
                    selectionType = null;
                    str4 = null;
                    immutableList = null;
                    str3 = null;
                    str2 = null;
                    filterType = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing MultiFilter so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            selectionType = (SelectionType) this.mSelectionTypeParser.parse(jsonNode2);
                        }
                        builder.selection = selectionType;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            filterType = (FilterType) this.mFilterTypeParser.parse(jsonNode2);
                        }
                        builder.type = filterType;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.filterKey = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttribute = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mFilterValueListParser.parse(jsonNode2);
                        }
                        builder.filterValueList = immutableList;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.version = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing MultiFilter so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.filterKey, this, "filterKey");
            JsonParsingUtils.checkNotNull(builder.filterValueList, this, "filterValueList");
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MultiFilter parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1715965556:
                                    if (currentName.equals("selection")) {
                                        b = 0;
                                    }
                                    break;
                                case -881391673:
                                    if (currentName.equals("filterKey")) {
                                        b = 3;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 2;
                                    }
                                    break;
                                case 346136969:
                                    if (currentName.equals("debugAttribute")) {
                                        b = 4;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 6;
                                    }
                                    break;
                                case 1575178519:
                                    if (currentName.equals("filterValueList")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            SelectionType selectionType = null;
                            String str = null;
                            ImmutableList<FilterValue> immutableList = null;
                            String str2 = null;
                            String str3 = null;
                            FilterType filterType = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        selectionType = (SelectionType) this.mSelectionTypeParser.parse(jsonParser);
                                    }
                                    builder.selection = selectionType;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        filterType = (FilterType) this.mFilterTypeParser.parse(jsonParser);
                                    }
                                    builder.type = filterType;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.filterKey = str3;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttribute = str2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mFilterValueListParser.parse(jsonParser);
                                    }
                                    builder.filterValueList = immutableList;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.version = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing MultiFilter so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing MultiFilter so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.filterKey, this, "filterKey");
                    JsonParsingUtils.checkNotNull(builder.filterValueList, this, "filterValueList");
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
