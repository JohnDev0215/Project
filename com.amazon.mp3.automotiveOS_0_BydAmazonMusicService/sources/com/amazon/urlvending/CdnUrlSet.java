package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.types.ProtocolType;
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
public class CdnUrlSet {
    public final Optional<String> cdn;
    public final Optional<ProtocolType> protocol;
    public final Optional<ImmutableList<String>> urls;

    private CdnUrlSet(Builder builder) {
        this.urls = Optional.fromNullable(builder.urls);
        this.protocol = Optional.fromNullable(builder.protocol);
        this.cdn = Optional.fromNullable(builder.cdn);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CdnUrlSet)) {
            return false;
        }
        CdnUrlSet cdnUrlSet = (CdnUrlSet) obj;
        return Objects.equal(this.urls, cdnUrlSet.urls) && Objects.equal(this.protocol, cdnUrlSet.protocol) && Objects.equal(this.cdn, cdnUrlSet.cdn);
    }

    public int hashCode() {
        return Objects.hashCode(this.urls, this.protocol, this.cdn);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urls", this.urls).add("protocol", this.protocol).add("cdn", this.cdn).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String cdn;
        public ProtocolType protocol;
        public ImmutableList<String> urls;

        public CdnUrlSet build() {
            return new CdnUrlSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CdnUrlSet> {
        private final EnumParser<ProtocolType> mProtocolTypeParser;
        private final ListParser<String> mStringListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringListParser = ListParser.newParser(stringParser);
            this.mStringParser = stringParser;
            this.mProtocolTypeParser = EnumParser.newParser(ProtocolType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CdnUrlSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdnUrlSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CdnUrlSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CdnUrlSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CdnUrlSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CdnUrlSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -989163880) {
                        if (iHashCode != 98349) {
                            if (iHashCode == 3598564 && next.equals("urls")) {
                                b = 0;
                            }
                        } else if (next.equals("cdn")) {
                            b = 2;
                        }
                    } else if (next.equals("protocol")) {
                        b = 1;
                    }
                    String str = null;
                    ImmutableList<String> immutableList = null;
                    ProtocolType protocolType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.urls = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonNode2);
                        }
                        builder.protocol = protocolType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.cdn = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CdnUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CdnUrlSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -989163880) {
                                if (iHashCode != 98349) {
                                    if (iHashCode == 3598564 && currentName.equals("urls")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("cdn")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("protocol")) {
                                b = 1;
                            }
                            String str = null;
                            ImmutableList<String> immutableList = null;
                            ProtocolType protocolType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.urls = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    protocolType = (ProtocolType) this.mProtocolTypeParser.parse(jsonParser);
                                }
                                builder.protocol = protocolType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.cdn = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CdnUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
