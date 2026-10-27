package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.TrickplayUrlInfo;
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
public class TrickplayUrlsCdnSet {
    public final Optional<String> cdn;
    public final Optional<String> cdnWeightsRank;
    public final Optional<ImmutableList<TrickplayUrlInfo>> trickplayUrlInfoList;

    private TrickplayUrlsCdnSet(Builder builder) {
        this.cdnWeightsRank = Optional.fromNullable(builder.cdnWeightsRank);
        this.cdn = Optional.fromNullable(builder.cdn);
        this.trickplayUrlInfoList = Optional.fromNullable(builder.trickplayUrlInfoList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrickplayUrlsCdnSet)) {
            return false;
        }
        TrickplayUrlsCdnSet trickplayUrlsCdnSet = (TrickplayUrlsCdnSet) obj;
        return Objects.equal(this.cdnWeightsRank, trickplayUrlsCdnSet.cdnWeightsRank) && Objects.equal(this.cdn, trickplayUrlsCdnSet.cdn) && Objects.equal(this.trickplayUrlInfoList, trickplayUrlsCdnSet.trickplayUrlInfoList);
    }

    public int hashCode() {
        return Objects.hashCode(this.cdnWeightsRank, this.cdn, this.trickplayUrlInfoList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cdnWeightsRank", this.cdnWeightsRank).add("cdn", this.cdn).add("trickplayUrlInfoList", this.trickplayUrlInfoList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String cdn;
        public String cdnWeightsRank;
        public ImmutableList<TrickplayUrlInfo> trickplayUrlInfoList;

        public TrickplayUrlsCdnSet build() {
            return new TrickplayUrlsCdnSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TrickplayUrlsCdnSet> {
        private final SimpleParsers.StringParser mCdnParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<TrickplayUrlInfo> mTrickplayUrlInfoListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTrickplayUrlInfoListParser = ListParser.newParser(new TrickplayUrlInfo.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringParser = stringParser;
            this.mCdnParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TrickplayUrlsCdnSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TrickplayUrlsCdnSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TrickplayUrlsCdnSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TrickplayUrlsCdnSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TrickplayUrlsCdnSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TrickplayUrlsCdnSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1298460326) {
                        if (iHashCode != 98349) {
                            if (iHashCode == 349413748 && next.equals("trickplayUrlInfoList")) {
                                b = 2;
                            }
                        } else if (next.equals("cdn")) {
                            b = 1;
                        }
                    } else if (next.equals("cdnWeightsRank")) {
                        b = 0;
                    }
                    ImmutableList<TrickplayUrlInfo> immutableList = null;
                    String str = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdnWeightsRank = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mCdnParser.parse(jsonNode2);
                        }
                        builder.cdn = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTrickplayUrlInfoListParser.parse(jsonNode2);
                        }
                        builder.trickplayUrlInfoList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TrickplayUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TrickplayUrlsCdnSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1298460326) {
                                if (iHashCode != 98349) {
                                    if (iHashCode == 349413748 && currentName.equals("trickplayUrlInfoList")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("cdn")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("cdnWeightsRank")) {
                                b = 0;
                            }
                            ImmutableList<TrickplayUrlInfo> immutableList = null;
                            String str = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.cdnWeightsRank = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mCdnParser.parse(jsonParser);
                                }
                                builder.cdn = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTrickplayUrlInfoListParser.parse(jsonParser);
                                }
                                builder.trickplayUrlInfoList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TrickplayUrlsCdnSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
