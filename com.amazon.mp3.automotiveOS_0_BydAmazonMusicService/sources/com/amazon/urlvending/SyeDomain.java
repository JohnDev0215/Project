package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.SyeFrontendUrl;
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
public class SyeDomain {
    public final Optional<String> channelId;
    public final Optional<ImmutableList<SyeFrontendUrl>> frontendUrls;
    public final Optional<String> name;
    public final Optional<String> token;

    private SyeDomain(Builder builder) {
        this.token = Optional.fromNullable(builder.token);
        this.frontendUrls = Optional.fromNullable(builder.frontendUrls);
        this.name = Optional.fromNullable(builder.name);
        this.channelId = Optional.fromNullable(builder.channelId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeDomain)) {
            return false;
        }
        SyeDomain syeDomain = (SyeDomain) obj;
        return Objects.equal(this.token, syeDomain.token) && Objects.equal(this.frontendUrls, syeDomain.frontendUrls) && Objects.equal(this.name, syeDomain.name) && Objects.equal(this.channelId, syeDomain.channelId);
    }

    public int hashCode() {
        return Objects.hashCode(this.token, this.frontendUrls, this.name, this.channelId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("token", this.token).add("frontendUrls", this.frontendUrls).add("name", this.name).add("channelId", this.channelId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String channelId;
        public ImmutableList<SyeFrontendUrl> frontendUrls;
        public String name;
        public String token;

        public SyeDomain build() {
            return new SyeDomain(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeDomain> {
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SyeFrontendUrl> mSyeFrontendUrlListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeFrontendUrlListParser = ListParser.newParser(new SyeFrontendUrl.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeDomain parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeDomain:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeDomain parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeDomain:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeDomain parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeDomain");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1306815978:
                            if (next.equals("frontendUrls")) {
                                b = 1;
                            }
                            break;
                        case 3373707:
                            if (next.equals("name")) {
                                b = 2;
                            }
                            break;
                        case 110541305:
                            if (next.equals("token")) {
                                b = 0;
                            }
                            break;
                        case 1461735806:
                            if (next.equals("channelId")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    ImmutableList<SyeFrontendUrl> immutableList = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.token = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSyeFrontendUrlListParser.parse(jsonNode2);
                        }
                        builder.frontendUrls = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.name = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.channelId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeDomain so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeDomain parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1306815978:
                                    if (currentName.equals("frontendUrls")) {
                                        b = 1;
                                    }
                                    break;
                                case 3373707:
                                    if (currentName.equals("name")) {
                                        b = 2;
                                    }
                                    break;
                                case 110541305:
                                    if (currentName.equals("token")) {
                                        b = 0;
                                    }
                                    break;
                                case 1461735806:
                                    if (currentName.equals("channelId")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableList<SyeFrontendUrl> immutableList = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.token = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSyeFrontendUrlListParser.parse(jsonParser);
                                }
                                builder.frontendUrls = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.name = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.channelId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeDomain so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
