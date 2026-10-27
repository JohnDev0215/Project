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
import com.amazon.avod.xray.XRayFragmentBase;
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
public class XRaySwiftFragment extends XRayFragmentBase {
    public final Optional<NavigationalActionBase> initialPageLoadAction;
    public final ImmutableMap<String, String> parameters;
    public final Optional<ImmutableList<PollingAction>> pollingApiActions;

    protected XRaySwiftFragment(Builder builder) {
        super(builder);
        this.pollingApiActions = Optional.fromNullable(builder.pollingApiActions);
        this.parameters = (ImmutableMap) Preconditions.checkNotNull(builder.parameters, "Unexpected null field: parameters");
        this.initialPageLoadAction = Optional.fromNullable(builder.initialPageLoadAction);
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRaySwiftFragment)) {
            return false;
        }
        XRaySwiftFragment xRaySwiftFragment = (XRaySwiftFragment) obj;
        return super.equals(obj) && Objects.equal(this.pollingApiActions, xRaySwiftFragment.pollingApiActions) && Objects.equal(this.parameters, xRaySwiftFragment.parameters) && Objects.equal(this.initialPageLoadAction, xRaySwiftFragment.initialPageLoadAction);
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.pollingApiActions, this.parameters, this.initialPageLoadAction);
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("pollingApiActions", this.pollingApiActions).add("parameters", this.parameters).add("initialPageLoadAction", this.initialPageLoadAction).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayFragmentBase.Builder {
        public NavigationalActionBase initialPageLoadAction;
        public ImmutableMap<String, String> parameters;
        public ImmutableList<PollingAction> pollingApiActions;

        public XRaySwiftFragment build() {
            return new XRaySwiftFragment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRaySwiftFragment> {
        private final EnumParser<FragmentVersion> mFragmentVersionParser;
        private final NavigationalActionBase.Parser mNavigationalActionBaseParser;
        private final ListParser<PollingAction> mPollingApiActionsParser;
        private final MapParser<String, String> mXRayParameterMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPollingApiActionsParser = ListParser.newParser(new PollingAction.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mXRayParameterMapParser = MapParser.newParser(stringParser, stringParser);
            this.mNavigationalActionBaseParser = new NavigationalActionBase.Parser(objectMapper);
            this.mFragmentVersionParser = EnumParser.newParser(FragmentVersion.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRaySwiftFragment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRaySwiftFragment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRaySwiftFragment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRaySwiftFragment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRaySwiftFragment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRaySwiftFragment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1413669105:
                            if (next.equals("initialPageLoadAction")) {
                                b = 2;
                            }
                            break;
                        case -223122010:
                            if (next.equals("pollingApiActions")) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 1;
                            }
                            break;
                    }
                    FragmentVersion fragmentVersion = null;
                    ImmutableList<PollingAction> immutableList = null;
                    ImmutableMap<String, String> immutableMap = null;
                    NavigationalActionBase navigationalActionBase = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPollingApiActionsParser.parse(jsonNode2);
                        }
                        builder.pollingApiActions = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mXRayParameterMapParser.parse(jsonNode2);
                        }
                        builder.parameters = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonNode2);
                        }
                        builder.initialPageLoadAction = navigationalActionBase;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = fragmentVersion;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRaySwiftFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRaySwiftFragment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1413669105:
                                    if (currentName.equals("initialPageLoadAction")) {
                                        b = 2;
                                    }
                                    break;
                                case -223122010:
                                    if (currentName.equals("pollingApiActions")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            FragmentVersion fragmentVersion = null;
                            ImmutableList<PollingAction> immutableList = null;
                            ImmutableMap<String, String> immutableMap = null;
                            NavigationalActionBase navigationalActionBase = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mPollingApiActionsParser.parse(jsonParser);
                                }
                                builder.pollingApiActions = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mXRayParameterMapParser.parse(jsonParser);
                                }
                                builder.parameters = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonParser);
                                }
                                builder.initialPageLoadAction = navigationalActionBase;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = fragmentVersion;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRaySwiftFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
