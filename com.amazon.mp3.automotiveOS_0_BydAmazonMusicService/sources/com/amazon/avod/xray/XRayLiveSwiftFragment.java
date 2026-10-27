package com.amazon.avod.xray;

import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.PollingAction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRaySwiftFragment;
import com.amazon.avod.xray.XrayLiveFragmentMapEntry;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayLiveSwiftFragment extends XRaySwiftFragment {
    public final long activePollingIntervalMillis;
    public final Optional<ImmutableList<String>> cdnNamePreferenceList;
    public final Optional<ImmutableMap<String, XrayLiveFragmentMapEntry>> liveFragmentMap;
    public final long passivePollingIntervalMillis;

    @Deprecated
    public final long pollingInterval;

    @Deprecated
    public final Optional<String> url;

    private XRayLiveSwiftFragment(Builder builder) {
        super(builder);
        this.liveFragmentMap = Optional.fromNullable(builder.liveFragmentMap);
        this.activePollingIntervalMillis = builder.activePollingIntervalMillis;
        this.cdnNamePreferenceList = Optional.fromNullable(builder.cdnNamePreferenceList);
        this.passivePollingIntervalMillis = builder.passivePollingIntervalMillis;
        this.url = Optional.fromNullable(builder.url);
        this.pollingInterval = builder.pollingInterval;
    }

    @Override // com.amazon.avod.xray.XRaySwiftFragment, com.amazon.avod.xray.XRayFragmentBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayLiveSwiftFragment)) {
            return false;
        }
        XRayLiveSwiftFragment xRayLiveSwiftFragment = (XRayLiveSwiftFragment) obj;
        return super.equals(obj) && Objects.equal(this.liveFragmentMap, xRayLiveSwiftFragment.liveFragmentMap) && Objects.equal(Long.valueOf(this.activePollingIntervalMillis), Long.valueOf(xRayLiveSwiftFragment.activePollingIntervalMillis)) && Objects.equal(this.cdnNamePreferenceList, xRayLiveSwiftFragment.cdnNamePreferenceList) && Objects.equal(Long.valueOf(this.passivePollingIntervalMillis), Long.valueOf(xRayLiveSwiftFragment.passivePollingIntervalMillis)) && Objects.equal(this.url, xRayLiveSwiftFragment.url) && Objects.equal(Long.valueOf(this.pollingInterval), Long.valueOf(xRayLiveSwiftFragment.pollingInterval));
    }

    @Override // com.amazon.avod.xray.XRaySwiftFragment, com.amazon.avod.xray.XRayFragmentBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.liveFragmentMap, Long.valueOf(this.activePollingIntervalMillis), this.cdnNamePreferenceList, Long.valueOf(this.passivePollingIntervalMillis), this.url, Long.valueOf(this.pollingInterval));
    }

    @Override // com.amazon.avod.xray.XRaySwiftFragment, com.amazon.avod.xray.XRayFragmentBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("liveFragmentMap", this.liveFragmentMap).add("activePollingIntervalMillis", this.activePollingIntervalMillis).add("cdnNamePreferenceList", this.cdnNamePreferenceList).add("passivePollingIntervalMillis", this.passivePollingIntervalMillis).add(ImagesContract.URL, this.url).add("pollingInterval", this.pollingInterval).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRaySwiftFragment.Builder {
        public long activePollingIntervalMillis;
        public ImmutableList<String> cdnNamePreferenceList;
        public ImmutableMap<String, XrayLiveFragmentMapEntry> liveFragmentMap;
        public long passivePollingIntervalMillis;

        @Deprecated
        public long pollingInterval;

        @Deprecated
        public String url;

        @Override // com.amazon.avod.xray.XRaySwiftFragment.Builder
        public XRayLiveSwiftFragment build() {
            return new XRayLiveSwiftFragment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayLiveSwiftFragment> {
        private final EnumParser<FragmentVersion> mFragmentVersionParser;
        private final NavigationalActionBase.Parser mNavigationalActionBaseParser;
        private final ListParser<PollingAction> mPollingApiActionsParser;
        private final ListParser<String> mSimpleStringListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, XrayLiveFragmentMapEntry> mXRayLiveFragmentMapParser;
        private final MapParser<String, String> mXRayParameterMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mSimpleStringListParser = ListParser.newParser(stringParser);
            this.mPollingApiActionsParser = ListParser.newParser(new PollingAction.Parser(objectMapper));
            this.mXRayLiveFragmentMapParser = MapParser.newParser(stringParser, new XrayLiveFragmentMapEntry.Parser(objectMapper));
            this.mXRayParameterMapParser = MapParser.newParser(stringParser, stringParser);
            this.mNavigationalActionBaseParser = new NavigationalActionBase.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mFragmentVersionParser = EnumParser.newParser(FragmentVersion.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayLiveSwiftFragment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayLiveSwiftFragment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayLiveSwiftFragment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayLiveSwiftFragment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayLiveSwiftFragment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayLiveSwiftFragment");
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
                        case -1580671343:
                            if (next.equals("cdnNamePreferenceList")) {
                                b = 4;
                            }
                            break;
                        case -1488616792:
                            if (next.equals("activePollingIntervalMillis")) {
                                b = 2;
                            }
                            break;
                        case -1413669105:
                            if (next.equals("initialPageLoadAction")) {
                                b = 6;
                            }
                            break;
                        case -223122010:
                            if (next.equals("pollingApiActions")) {
                                b = 1;
                            }
                            break;
                        case -199210584:
                            if (next.equals("pollingInterval")) {
                                b = 8;
                            }
                            break;
                        case 116079:
                            if (next.equals(ImagesContract.URL)) {
                                b = 7;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 9;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 3;
                            }
                            break;
                        case 929879136:
                            if (next.equals("liveFragmentMap")) {
                                b = 0;
                            }
                            break;
                    }
                    ImmutableMap<String, XrayLiveFragmentMapEntry> immutableMap = null;
                    FragmentVersion fragmentVersion = null;
                    String str = null;
                    NavigationalActionBase navigationalActionBase = null;
                    ImmutableList<String> immutableList = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    ImmutableList<PollingAction> immutableList2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mXRayLiveFragmentMapParser.parse(jsonNode2);
                            }
                            builder.liveFragmentMap = immutableMap;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                immutableList2 = this.mPollingApiActionsParser.parse(jsonNode2);
                            }
                            builder.pollingApiActions = immutableList2;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                            }
                            builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                immutableMap2 = this.mXRayParameterMapParser.parse(jsonNode2);
                            }
                            builder.parameters = immutableMap2;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mSimpleStringListParser.parse(jsonNode2);
                            }
                            builder.cdnNamePreferenceList = immutableList;
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
                                navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonNode2);
                            }
                            builder.initialPageLoadAction = navigationalActionBase;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.url = str;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field pollingInterval can't be null");
                            }
                            builder.pollingInterval = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonNode2);
                            }
                            builder.version = fragmentVersion;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayLiveSwiftFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayLiveSwiftFragment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1580671343:
                                    if (currentName.equals("cdnNamePreferenceList")) {
                                        b = 4;
                                    }
                                    break;
                                case -1488616792:
                                    if (currentName.equals("activePollingIntervalMillis")) {
                                        b = 2;
                                    }
                                    break;
                                case -1413669105:
                                    if (currentName.equals("initialPageLoadAction")) {
                                        b = 6;
                                    }
                                    break;
                                case -223122010:
                                    if (currentName.equals("pollingApiActions")) {
                                        b = 1;
                                    }
                                    break;
                                case -199210584:
                                    if (currentName.equals("pollingInterval")) {
                                        b = 8;
                                    }
                                    break;
                                case 116079:
                                    if (currentName.equals(ImagesContract.URL)) {
                                        b = 7;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 9;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 3;
                                    }
                                    break;
                                case 929879136:
                                    if (currentName.equals("liveFragmentMap")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            ImmutableMap<String, XrayLiveFragmentMapEntry> immutableMap = null;
                            FragmentVersion fragmentVersion = null;
                            String str = null;
                            NavigationalActionBase navigationalActionBase = null;
                            ImmutableList<String> immutableList = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            ImmutableList<PollingAction> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mXRayLiveFragmentMapParser.parse(jsonParser);
                                    }
                                    builder.liveFragmentMap = immutableMap;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mPollingApiActionsParser.parse(jsonParser);
                                    }
                                    builder.pollingApiActions = immutableList2;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field activePollingIntervalMillis can't be null");
                                    }
                                    builder.activePollingIntervalMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mXRayParameterMapParser.parse(jsonParser);
                                    }
                                    builder.parameters = immutableMap2;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mSimpleStringListParser.parse(jsonParser);
                                    }
                                    builder.cdnNamePreferenceList = immutableList;
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
                                        navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonParser);
                                    }
                                    builder.initialPageLoadAction = navigationalActionBase;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.url = str;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field pollingInterval can't be null");
                                    }
                                    builder.pollingInterval = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonParser);
                                    }
                                    builder.version = fragmentVersion;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayLiveSwiftFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
