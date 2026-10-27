package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.XRayFragmentBase;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayLegacyFragment extends XRayFragmentBase {
    public final String url;

    private XRayLegacyFragment(Builder builder) {
        super(builder);
        this.url = (String) Preconditions.checkNotNull(builder.url, "Unexpected null field: url");
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XRayLegacyFragment) {
            return super.equals(obj) && Objects.equal(this.url, ((XRayLegacyFragment) obj).url);
        }
        return false;
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.url);
    }

    @Override // com.amazon.avod.xray.XRayFragmentBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add(ImagesContract.URL, this.url).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayFragmentBase.Builder {
        public String url;

        public XRayLegacyFragment build() {
            return new XRayLegacyFragment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayLegacyFragment> {
        private final EnumParser<FragmentVersion> mFragmentVersionParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFragmentVersionParser = EnumParser.newParser(FragmentVersion.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayLegacyFragment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayLegacyFragment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayLegacyFragment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayLegacyFragment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private XRayLegacyFragment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayLegacyFragment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 116079) {
                        if (iHashCode == 351608024 && next.equals("version")) {
                            b = 1;
                        }
                    } else if (next.equals(ImagesContract.URL)) {
                        b = 0;
                    }
                    FragmentVersion fragmentVersion = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.url = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = fragmentVersion;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayLegacyFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        @Nonnull
        private XRayLegacyFragment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 116079) {
                                if (iHashCode == 351608024 && currentName.equals("version")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(ImagesContract.URL)) {
                                b = 0;
                            }
                            FragmentVersion fragmentVersion = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.url = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    fragmentVersion = (FragmentVersion) this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = fragmentVersion;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayLegacyFragment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.url, this, ImagesContract.URL);
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
