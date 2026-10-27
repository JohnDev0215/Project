package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XrayLiveFragmentMapEntry {
    public final Optional<ImmutableMap<String, String>> documentUrlMap;

    @Deprecated
    public final Optional<String> domain;

    @Deprecated
    public final Optional<String> path;

    @Deprecated
    public final long pollingInterval;

    @Deprecated
    public final Optional<String> url;

    private XrayLiveFragmentMapEntry(Builder builder) {
        this.path = Optional.fromNullable(builder.path);
        this.url = Optional.fromNullable(builder.url);
        this.pollingInterval = builder.pollingInterval;
        this.documentUrlMap = Optional.fromNullable(builder.documentUrlMap);
        this.domain = Optional.fromNullable(builder.domain);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayLiveFragmentMapEntry)) {
            return false;
        }
        XrayLiveFragmentMapEntry xrayLiveFragmentMapEntry = (XrayLiveFragmentMapEntry) obj;
        return Objects.equal(this.path, xrayLiveFragmentMapEntry.path) && Objects.equal(this.url, xrayLiveFragmentMapEntry.url) && Objects.equal(Long.valueOf(this.pollingInterval), Long.valueOf(xrayLiveFragmentMapEntry.pollingInterval)) && Objects.equal(this.documentUrlMap, xrayLiveFragmentMapEntry.documentUrlMap) && Objects.equal(this.domain, xrayLiveFragmentMapEntry.domain);
    }

    public int hashCode() {
        return Objects.hashCode(this.path, this.url, Long.valueOf(this.pollingInterval), this.documentUrlMap, this.domain);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("path", this.path).add(ImagesContract.URL, this.url).add("pollingInterval", this.pollingInterval).add("documentUrlMap", this.documentUrlMap).add("domain", this.domain).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, String> documentUrlMap;

        @Deprecated
        public String domain;

        @Deprecated
        public String path;

        @Deprecated
        public long pollingInterval;

        @Deprecated
        public String url;

        public XrayLiveFragmentMapEntry build() {
            return new XrayLiveFragmentMapEntry(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayLiveFragmentMapEntry> {
        private final MapParser<String, String> mSimpleStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mSimpleStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XrayLiveFragmentMapEntry parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayLiveFragmentMapEntry:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayLiveFragmentMapEntry parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayLiveFragmentMapEntry:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayLiveFragmentMapEntry parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XrayLiveFragmentMapEntry");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1326197564:
                            if (next.equals("domain")) {
                                b = 4;
                            }
                            break;
                        case -199210584:
                            if (next.equals("pollingInterval")) {
                                b = 2;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 1;
                            }
                            break;
                        case 3433509:
                            if (next.equals("path")) {
                                b = 0;
                            }
                            break;
                        case 1619589736:
                            if (next.equals("documentUrlMap")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    ImmutableMap<String, String> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.path = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str3;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field pollingInterval can't be null");
                        }
                        builder.pollingInterval = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mSimpleStringMapParser.parse(jsonNode2);
                        }
                        builder.documentUrlMap = immutableMap;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.domain = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayLiveFragmentMapEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayLiveFragmentMapEntry parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1326197564:
                                    if (currentName.equals("domain")) {
                                        b = 4;
                                    }
                                    break;
                                case -199210584:
                                    if (currentName.equals("pollingInterval")) {
                                        b = 2;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 1;
                                    }
                                    break;
                                case 3433509:
                                    if (currentName.equals("path")) {
                                        b = 0;
                                    }
                                    break;
                                case 1619589736:
                                    if (currentName.equals("documentUrlMap")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.path = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.url = str3;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field pollingInterval can't be null");
                                }
                                builder.pollingInterval = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mSimpleStringMapParser.parse(jsonParser);
                                }
                                builder.documentUrlMap = immutableMap;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.domain = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayLiveFragmentMapEntry so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
