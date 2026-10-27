package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRayActions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayActionsCollection {
    public final ImmutableList<XRayActions> actionsList;
    public final long activePollingIntervalMillis;
    public final Optional<ImmutableMap<String, String>> nextDocumentUrlMap;

    @Deprecated
    public final Optional<String> nextPath;

    @Deprecated
    public final Optional<String> nextUrl;
    public final long passivePollingIntervalMillis;

    @Deprecated
    public final long pollingIntervalMillis;
    public final Optional<String> uniqueReferenceId;

    private XRayActionsCollection(Builder builder) {
        this.nextUrl = Optional.fromNullable(builder.nextUrl);
        this.uniqueReferenceId = Optional.fromNullable(builder.uniqueReferenceId);
        this.nextDocumentUrlMap = Optional.fromNullable(builder.nextDocumentUrlMap);
        this.actionsList = (ImmutableList) Preconditions.checkNotNull(builder.actionsList, "Unexpected null field: actionsList");
        this.activePollingIntervalMillis = builder.activePollingIntervalMillis;
        this.passivePollingIntervalMillis = builder.passivePollingIntervalMillis;
        this.nextPath = Optional.fromNullable(builder.nextPath);
        this.pollingIntervalMillis = builder.pollingIntervalMillis;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayActionsCollection)) {
            return false;
        }
        XRayActionsCollection xRayActionsCollection = (XRayActionsCollection) obj;
        return Objects.equal(this.nextUrl, xRayActionsCollection.nextUrl) && Objects.equal(this.uniqueReferenceId, xRayActionsCollection.uniqueReferenceId) && Objects.equal(this.nextDocumentUrlMap, xRayActionsCollection.nextDocumentUrlMap) && Objects.equal(this.actionsList, xRayActionsCollection.actionsList) && Objects.equal(Long.valueOf(this.activePollingIntervalMillis), Long.valueOf(xRayActionsCollection.activePollingIntervalMillis)) && Objects.equal(Long.valueOf(this.passivePollingIntervalMillis), Long.valueOf(xRayActionsCollection.passivePollingIntervalMillis)) && Objects.equal(this.nextPath, xRayActionsCollection.nextPath) && Objects.equal(Long.valueOf(this.pollingIntervalMillis), Long.valueOf(xRayActionsCollection.pollingIntervalMillis));
    }

    public int hashCode() {
        return Objects.hashCode(this.nextUrl, this.uniqueReferenceId, this.nextDocumentUrlMap, this.actionsList, Long.valueOf(this.activePollingIntervalMillis), Long.valueOf(this.passivePollingIntervalMillis), this.nextPath, Long.valueOf(this.pollingIntervalMillis));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("nextUrl", this.nextUrl).add("uniqueReferenceId", this.uniqueReferenceId).add("nextDocumentUrlMap", this.nextDocumentUrlMap).add("actionsList", this.actionsList).add("activePollingIntervalMillis", this.activePollingIntervalMillis).add("passivePollingIntervalMillis", this.passivePollingIntervalMillis).add("nextPath", this.nextPath).add("pollingIntervalMillis", this.pollingIntervalMillis).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<XRayActions> actionsList;
        public long activePollingIntervalMillis;
        public ImmutableMap<String, String> nextDocumentUrlMap;

        @Deprecated
        public String nextPath;

        @Deprecated
        public String nextUrl;
        public long passivePollingIntervalMillis;

        @Deprecated
        public long pollingIntervalMillis;
        public String uniqueReferenceId;

        public XRayActionsCollection build() {
            return new XRayActionsCollection(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayActionsCollection> {
        private final ListParser<XRayActions> mActionsListParser;
        private final MapParser<String, String> mSimpleStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mActionsListParser = ListParser.newParser(new XRayActions.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mSimpleStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayActionsCollection parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayActionsCollection:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayActionsCollection parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayActionsCollection:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayActionsCollection parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayActionsCollection");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1675779801:
                            if (next.equals("passivePollingIntervalMillis")) {
                                b = 5;
                            }
                            break;
                        case -1488616792:
                            if (next.equals("activePollingIntervalMillis")) {
                                b = 4;
                            }
                            break;
                        case -979443403:
                            if (next.equals("uniqueReferenceId")) {
                                b = 1;
                            }
                            break;
                        case -498095045:
                            if (next.equals("nextDocumentUrlMap")) {
                                b = 2;
                            }
                            break;
                        case 1132196366:
                            if (next.equals("pollingIntervalMillis")) {
                                b = 7;
                            }
                            break;
                        case 1424273848:
                            if (next.equals("nextPath")) {
                                b = 6;
                            }
                            break;
                        case 1601021755:
                            if (next.equals("actionsList")) {
                                b = 3;
                            }
                            break;
                        case 1847064956:
                            if (next.equals("nextUrl")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    ImmutableList<XRayActions> immutableList = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str3 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.nextUrl = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.uniqueReferenceId = str3;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mSimpleStringMapParser.parse(jsonNode2);
                            }
                            builder.nextDocumentUrlMap = immutableMap;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mActionsListParser.parse(jsonNode2);
                            }
                            builder.actionsList = immutableList;
                            break;
                        case 4:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                            }
                            builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field passivePollingIntervalMillis can't be null");
                            }
                            builder.passivePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.nextPath = str2;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field pollingIntervalMillis can't be null");
                            }
                            builder.pollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayActionsCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.actionsList, this, "actionsList");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayActionsCollection parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1675779801:
                                    if (currentName.equals("passivePollingIntervalMillis")) {
                                        b = 5;
                                    }
                                    break;
                                case -1488616792:
                                    if (currentName.equals("activePollingIntervalMillis")) {
                                        b = 4;
                                    }
                                    break;
                                case -979443403:
                                    if (currentName.equals("uniqueReferenceId")) {
                                        b = 1;
                                    }
                                    break;
                                case -498095045:
                                    if (currentName.equals("nextDocumentUrlMap")) {
                                        b = 2;
                                    }
                                    break;
                                case 1132196366:
                                    if (currentName.equals("pollingIntervalMillis")) {
                                        b = 7;
                                    }
                                    break;
                                case 1424273848:
                                    if (currentName.equals("nextPath")) {
                                        b = 6;
                                    }
                                    break;
                                case 1601021755:
                                    if (currentName.equals("actionsList")) {
                                        b = 3;
                                    }
                                    break;
                                case 1847064956:
                                    if (currentName.equals("nextUrl")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            ImmutableList<XRayActions> immutableList = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nextUrl = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.uniqueReferenceId = str3;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mSimpleStringMapParser.parse(jsonParser);
                                    }
                                    builder.nextDocumentUrlMap = immutableMap;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mActionsListParser.parse(jsonParser);
                                    }
                                    builder.actionsList = immutableList;
                                    break;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                                    }
                                    builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field passivePollingIntervalMillis can't be null");
                                    }
                                    builder.passivePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nextPath = str2;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field pollingIntervalMillis can't be null");
                                    }
                                    builder.pollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayActionsCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.actionsList, this, "actionsList");
                    return builder.build();
                }
            }
        }
    }
}
