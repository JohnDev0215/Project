package com.amazon.atvtransitiontimecodeservice;

import com.amazon.atvtransitiontimecodeservice.types.ContentType;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetTransitionTimecodesForPidInput {
    public final Optional<ContentType> contentType;
    public final Optional<String> pid;

    private GetTransitionTimecodesForPidInput(Builder builder) {
        this.pid = Optional.fromNullable(builder.pid);
        this.contentType = Optional.fromNullable(builder.contentType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTransitionTimecodesForPidInput)) {
            return false;
        }
        GetTransitionTimecodesForPidInput getTransitionTimecodesForPidInput = (GetTransitionTimecodesForPidInput) obj;
        return Objects.equal(this.pid, getTransitionTimecodesForPidInput.pid) && Objects.equal(this.contentType, getTransitionTimecodesForPidInput.contentType);
    }

    public int hashCode() {
        return Objects.hashCode(this.pid, this.contentType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("pid", this.pid).add("contentType", this.contentType).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ContentType contentType;
        public String pid;

        public GetTransitionTimecodesForPidInput build() {
            return new GetTransitionTimecodesForPidInput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTransitionTimecodesForPidInput> {
        private final EnumParser<ContentType> mcontentTypeParser;
        private final SimpleParsers.StringParser mpidParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mpidParser = SimpleParsers.StringParser.INSTANCE;
            this.mcontentTypeParser = EnumParser.newParser(ContentType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTransitionTimecodesForPidInput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTransitionTimecodesForPidInput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTransitionTimecodesForPidInput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTransitionTimecodesForPidInput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetTransitionTimecodesForPidInput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTransitionTimecodesForPidInput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -389131437) {
                        if (iHashCode == 110987 && next.equals("pid")) {
                            b = 0;
                        }
                    } else if (next.equals("contentType")) {
                        b = 1;
                    }
                    ContentType contentType = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mpidParser.parse(jsonNode2);
                        }
                        builder.pid = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            contentType = (ContentType) this.mcontentTypeParser.parse(jsonNode2);
                        }
                        builder.contentType = contentType;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTransitionTimecodesForPidInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetTransitionTimecodesForPidInput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -389131437) {
                                if (iHashCode == 110987 && currentName.equals("pid")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("contentType")) {
                                b = 1;
                            }
                            ContentType contentType = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mpidParser.parse(jsonParser);
                                }
                                builder.pid = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    contentType = (ContentType) this.mcontentTypeParser.parse(jsonParser);
                                }
                                builder.contentType = contentType;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTransitionTimecodesForPidInput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
