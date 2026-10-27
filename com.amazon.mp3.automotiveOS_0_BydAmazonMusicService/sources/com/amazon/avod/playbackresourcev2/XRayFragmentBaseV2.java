package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.xray.XRayFragmentBase;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class XRayFragmentBaseV2 {
    private final Optional<XRayFragmentBase> mXRayFragmentBase;

    XRayFragmentBaseV2(@Nonnull Builder builder) {
        this.mXRayFragmentBase = Optional.fromNullable(builder.mXRayFragmentBase);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("xRayFragmentBaseV2", this.mXRayFragmentBase).toString();
    }

    static class Builder {
        private XRayFragmentBase mXRayFragmentBase;

        Builder() {
        }

        XRayFragmentBaseV2 build() {
            return new XRayFragmentBaseV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<XRayFragmentBaseV2> {
        private final XRayFragmentBase.Parser mXrayFragmentParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, XRayFragmentBaseV2.class);
            this.mXrayFragmentParser = new XRayFragmentBase.Parser(objectMapper);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public XRayFragmentBaseV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayFragmentBaseV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                try {
                    if ("result".equals(next)) {
                        builder.mXRayFragmentBase = this.mXrayFragmentParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayFragmentBaseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public XRayFragmentBaseV2 parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    try {
                        if ("result".equals(currentName)) {
                            builder.mXRayFragmentBase = this.mXrayFragmentParser.parse(jsonParser);
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayFragmentBaseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
