package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.NavigationalAction;
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
public class BrowseNode {
    public final Optional<Analytics> analytics;
    public final Optional<ImmutableList<BrowseNode>> children;
    public final Optional<NavigationalAction> link;
    public final Optional<String> subtext;
    public final String text;

    private BrowseNode(Builder builder) {
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.analytics = Optional.fromNullable(builder.analytics);
        this.link = Optional.fromNullable(builder.link);
        this.children = Optional.fromNullable(builder.children);
        this.subtext = Optional.fromNullable(builder.subtext);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrowseNode)) {
            return false;
        }
        BrowseNode browseNode = (BrowseNode) obj;
        return Objects.equal(this.text, browseNode.text) && Objects.equal(this.analytics, browseNode.analytics) && Objects.equal(this.link, browseNode.link) && Objects.equal(this.children, browseNode.children) && Objects.equal(this.subtext, browseNode.subtext);
    }

    public int hashCode() {
        return Objects.hashCode(this.text, this.analytics, this.link, this.children, this.subtext);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add("analytics", this.analytics).add("link", this.link).add("children", this.children).add("subtext", this.subtext).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Analytics analytics;
        public ImmutableList<BrowseNode> children;
        public NavigationalAction link;
        public String subtext;
        public String text;

        public BrowseNode build() {
            return new BrowseNode(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BrowseNode> {
        private final Analytics.Parser mAnalyticsParser;
        private final ListParser<BrowseNode> mBrowseNodeListParser;
        private final NavigationalAction.Parser mNavigationalActionParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNavigationalActionParser = new NavigationalAction.Parser(objectMapper);
            this.mBrowseNodeListParser = ListParser.newParser(this);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BrowseNode parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowseNode:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BrowseNode parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowseNode:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BrowseNode parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "BrowseNode");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1867586707:
                            if (next.equals("subtext")) {
                                b = 4;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 1;
                            }
                            break;
                        case 3321850:
                            if (next.equals("link")) {
                                b = 2;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 0;
                            }
                            break;
                        case 1659526655:
                            if (next.equals("children")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    Analytics analytics = null;
                    NavigationalAction navigationalAction = null;
                    ImmutableList<BrowseNode> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            navigationalAction = this.mNavigationalActionParser.parse(jsonNode2);
                        }
                        builder.link = navigationalAction;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mBrowseNodeListParser.parse(jsonNode2);
                        }
                        builder.children = immutableList;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BrowseNode so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BrowseNode parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1867586707:
                                    if (currentName.equals("subtext")) {
                                        b = 4;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 1;
                                    }
                                    break;
                                case 3321850:
                                    if (currentName.equals("link")) {
                                        b = 2;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 0;
                                    }
                                    break;
                                case 1659526655:
                                    if (currentName.equals("children")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Analytics analytics = null;
                            NavigationalAction navigationalAction = null;
                            ImmutableList<BrowseNode> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.text = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    analytics = this.mAnalyticsParser.parse(jsonParser);
                                }
                                builder.analytics = analytics;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    navigationalAction = this.mNavigationalActionParser.parse(jsonParser);
                                }
                                builder.link = navigationalAction;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mBrowseNodeListParser.parse(jsonParser);
                                }
                                builder.children = immutableList;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.subtext = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BrowseNode so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    return builder.build();
                }
            }
        }
    }
}
