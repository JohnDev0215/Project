package com.amazon.atvplaybackresource;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atvplaybackresource.PRSException;
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
public class CuepointPlaylistForTenantResponse {
    public final Optional<CuepointPlaylist> cuepoints;
    public final Optional<PRSException> error;

    private CuepointPlaylistForTenantResponse(Builder builder) {
        this.cuepoints = Optional.fromNullable(builder.cuepoints);
        this.error = Optional.fromNullable(builder.error);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CuepointPlaylistForTenantResponse)) {
            return false;
        }
        CuepointPlaylistForTenantResponse cuepointPlaylistForTenantResponse = (CuepointPlaylistForTenantResponse) obj;
        return Objects.equal(this.cuepoints, cuepointPlaylistForTenantResponse.cuepoints) && Objects.equal(this.error, cuepointPlaylistForTenantResponse.error);
    }

    public int hashCode() {
        return Objects.hashCode(this.cuepoints, this.error);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cuepoints", this.cuepoints).add("error", this.error).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CuepointPlaylist cuepoints;
        public PRSException error;

        public CuepointPlaylistForTenantResponse build() {
            return new CuepointPlaylistForTenantResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CuepointPlaylistForTenantResponse> {
        private final CuepointPlaylist.Parser mCuepointPlaylistParser;
        private final PRSException.Parser mPRSExceptionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCuepointPlaylistParser = new CuepointPlaylist.Parser(objectMapper);
            this.mPRSExceptionParser = new PRSException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CuepointPlaylistForTenantResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointPlaylistForTenantResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CuepointPlaylistForTenantResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointPlaylistForTenantResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private CuepointPlaylistForTenantResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CuepointPlaylistForTenantResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 96784904) {
                        if (iHashCode == 1956785366 && next.equals("cuepoints")) {
                            b = 0;
                        }
                    } else if (next.equals("error")) {
                        b = 1;
                    }
                    PRSException pRSException = null;
                    CuepointPlaylist cuepointPlaylist = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            cuepointPlaylist = this.mCuepointPlaylistParser.parse(jsonNode2);
                        }
                        builder.cuepoints = cuepointPlaylist;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pRSException = this.mPRSExceptionParser.parse(jsonNode2);
                        }
                        builder.error = pRSException;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CuepointPlaylistForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private CuepointPlaylistForTenantResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 96784904) {
                                if (iHashCode == 1956785366 && currentName.equals("cuepoints")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("error")) {
                                b = 1;
                            }
                            PRSException pRSException = null;
                            CuepointPlaylist cuepointPlaylist = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    cuepointPlaylist = this.mCuepointPlaylistParser.parse(jsonParser);
                                }
                                builder.cuepoints = cuepointPlaylist;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pRSException = this.mPRSExceptionParser.parse(jsonParser);
                                }
                                builder.error = pRSException;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CuepointPlaylistForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
