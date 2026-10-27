package com.amazon.atv.cuepoint;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetCuepointPlaylistResponse {
    public final Optional<CuepointPlaylist> cuepointPlaylist;

    private GetCuepointPlaylistResponse(Builder builder) {
        this.cuepointPlaylist = Optional.fromNullable(builder.cuepointPlaylist);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetCuepointPlaylistResponse) {
            return Objects.equal(this.cuepointPlaylist, ((GetCuepointPlaylistResponse) obj).cuepointPlaylist);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.cuepointPlaylist);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cuepointPlaylist", this.cuepointPlaylist).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CuepointPlaylist cuepointPlaylist;

        public GetCuepointPlaylistResponse build() {
            return new GetCuepointPlaylistResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetCuepointPlaylistResponse> {
        private final CuepointPlaylist.Parser mCuepointPlaylistParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCuepointPlaylistParser = new CuepointPlaylist.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetCuepointPlaylistResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetCuepointPlaylistResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetCuepointPlaylistResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetCuepointPlaylistResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetCuepointPlaylistResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetCuepointPlaylistResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -993422289 && next.equals("cuepointPlaylist")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.cuepointPlaylist = jsonNode2.isNull() ? null : this.mCuepointPlaylistParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetCuepointPlaylistResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetCuepointPlaylistResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -993422289 && currentName.equals("cuepointPlaylist")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.cuepointPlaylist = currentToken == JsonToken.VALUE_NULL ? null : this.mCuepointPlaylistParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetCuepointPlaylistResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
