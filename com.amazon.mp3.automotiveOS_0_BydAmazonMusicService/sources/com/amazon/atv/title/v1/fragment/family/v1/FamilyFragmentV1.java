package com.amazon.atv.title.v1.fragment.family.v1;

import com.amazon.atv.title.Title;
import com.amazon.atv.title.v1.FamilyFragment;
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
public class FamilyFragmentV1 extends FamilyFragment {
    public final Optional<ImmutableList<Title>> tvAncestors;
    public final Optional<Title> tvParent;

    private FamilyFragmentV1(Builder builder) {
        super(builder);
        this.tvParent = Optional.fromNullable(builder.tvParent);
        this.tvAncestors = Optional.fromNullable(builder.tvAncestors);
    }

    @Override // com.amazon.atv.title.v1.FamilyFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FamilyFragmentV1)) {
            return false;
        }
        FamilyFragmentV1 familyFragmentV1 = (FamilyFragmentV1) obj;
        return super.equals(obj) && Objects.equal(this.tvParent, familyFragmentV1.tvParent) && Objects.equal(this.tvAncestors, familyFragmentV1.tvAncestors);
    }

    @Override // com.amazon.atv.title.v1.FamilyFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.tvParent, this.tvAncestors);
    }

    @Override // com.amazon.atv.title.v1.FamilyFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).add("tvParent", this.tvParent).add("tvAncestors", this.tvAncestors).toString();
    }

    @NotThreadSafe
    public static class Builder extends FamilyFragment.Builder {
        public ImmutableList<Title> tvAncestors;
        public Title tvParent;

        public FamilyFragmentV1 build() {
            return new FamilyFragmentV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<FamilyFragmentV1> {
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final ListParser<Title> mTitleListParser;
        private final Title.Parser mTitleParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTitleListParser = ListParser.newParser(new Title.Parser(objectMapper));
            this.mTitleParser = new Title.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FamilyFragmentV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FamilyFragmentV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FamilyFragmentV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FamilyFragmentV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FamilyFragmentV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "FamilyFragmentV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2121665378:
                            if (next.equals("tvAncestors")) {
                                b = 2;
                            }
                            break;
                        case -54813502:
                            if (next.equals("validUntil")) {
                                b = 3;
                            }
                            break;
                        case 37198284:
                            if (next.equals("tvParent")) {
                                b = 1;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    Title title = null;
                    ImmutableList<Title> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            title = this.mTitleParser.parse(jsonNode2);
                        }
                        builder.tvParent = title;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTitleListParser.parse(jsonNode2);
                        }
                        builder.tvAncestors = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing FamilyFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FamilyFragmentV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2121665378:
                                    if (currentName.equals("tvAncestors")) {
                                        b = 2;
                                    }
                                    break;
                                case -54813502:
                                    if (currentName.equals("validUntil")) {
                                        b = 3;
                                    }
                                    break;
                                case 37198284:
                                    if (currentName.equals("tvParent")) {
                                        b = 1;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Title title = null;
                            ImmutableList<Title> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    title = this.mTitleParser.parse(jsonParser);
                                }
                                builder.tvParent = title;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTitleListParser.parse(jsonParser);
                                }
                                builder.tvAncestors = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mValidUntilTimestampParser.parse(jsonParser);
                                }
                                builder.validUntil = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing FamilyFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
