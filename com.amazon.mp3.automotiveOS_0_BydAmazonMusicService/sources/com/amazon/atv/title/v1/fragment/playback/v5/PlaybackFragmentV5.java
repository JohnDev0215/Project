package com.amazon.atv.title.v1.fragment.playback.v5;

import com.amazon.atv.title.v1.PlaybackFragment;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackFragmentV5 extends PlaybackFragment {
    private PlaybackFragmentV5(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackFragmentV5)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.title.v1.PlaybackFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends PlaybackFragment.Builder {
        public PlaybackFragmentV5 build() {
            return new PlaybackFragmentV5(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackFragmentV5> {
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackFragmentV5 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackFragmentV5:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackFragmentV5 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackFragmentV5:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackFragmentV5 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackFragmentV5");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -54813502) {
                        if (iHashCode == 351608024 && next.equals("version")) {
                            b = 0;
                        }
                    } else if (next.equals("validUntil")) {
                        b = 1;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackFragmentV5 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            return builder.build();
        }

        @Nonnull
        private PlaybackFragmentV5 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -54813502) {
                                if (iHashCode == 351608024 && currentName.equals("version")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("validUntil")) {
                                b = 1;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mFragmentVersionParser.parse(jsonParser);
                                }
                                builder.version = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mValidUntilTimestampParser.parse(jsonParser);
                                }
                                builder.validUntil = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackFragmentV5 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    return builder.build();
                }
            }
        }
    }
}
