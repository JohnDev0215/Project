package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.LinearAsset;
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
public class PlaylistComponent {
    public final Optional<ImmutableList<LinearAsset>> linearAssets;
    public final Optional<Integer> sequence;

    private PlaylistComponent(Builder builder) {
        this.linearAssets = Optional.fromNullable(builder.linearAssets);
        this.sequence = Optional.fromNullable(builder.sequence);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaylistComponent)) {
            return false;
        }
        PlaylistComponent playlistComponent = (PlaylistComponent) obj;
        return Objects.equal(this.linearAssets, playlistComponent.linearAssets) && Objects.equal(this.sequence, playlistComponent.sequence);
    }

    public int hashCode() {
        return Objects.hashCode(this.linearAssets, this.sequence);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("linearAssets", this.linearAssets).add("sequence", this.sequence).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<LinearAsset> linearAssets;
        public Integer sequence;

        public PlaylistComponent build() {
            return new PlaylistComponent(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaylistComponent> {
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final ListParser<LinearAsset> mLinearAssetListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLinearAssetListParser = ListParser.newParser(new LinearAsset.Parser(objectMapper));
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaylistComponent parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaylistComponent:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaylistComponent parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaylistComponent:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaylistComponent parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaylistComponent");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 664485640) {
                        if (iHashCode == 1349547969 && next.equals("sequence")) {
                            b = 1;
                        }
                    } else if (next.equals("linearAssets")) {
                        b = 0;
                    }
                    Integer num = null;
                    ImmutableList<LinearAsset> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mLinearAssetListParser.parse(jsonNode2);
                        }
                        builder.linearAssets = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.sequence = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaylistComponent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlaylistComponent parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 664485640) {
                                if (iHashCode == 1349547969 && currentName.equals("sequence")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("linearAssets")) {
                                b = 0;
                            }
                            Integer num = null;
                            ImmutableList<LinearAsset> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mLinearAssetListParser.parse(jsonParser);
                                }
                                builder.linearAssets = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.sequence = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaylistComponent so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
