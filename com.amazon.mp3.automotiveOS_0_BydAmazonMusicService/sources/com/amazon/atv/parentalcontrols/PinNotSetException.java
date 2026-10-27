package com.amazon.atv.parentalcontrols;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PinNotSetException {
    private PinNotSetException(Builder builder) {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof PinNotSetException);
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinNotSetException build() {
            return new PinNotSetException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PinNotSetException> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PinNotSetException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinNotSetException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PinNotSetException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PinNotSetException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PinNotSetException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PinNotSetException");
            return new Builder().build();
        }

        @Nonnull
        private PinNotSetException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return new Builder().build();
        }
    }
}
